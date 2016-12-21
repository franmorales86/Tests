/**
 * Project: RecCore
 * Created by: Fran at 24/06/13 17:01
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.core.misc.items.SearchItemType;
import com.reclabs.recomendar.esdriver.actions.queries.ESQueryStringQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.helper.ESHelper;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.objects.items.ListItemsTagsOrdered;
import com.reclabs.recomendar.model.objects.tags.TagOrderDTO;
import org.apache.commons.lang.NotImplementedException;

import java.util.*;

/**
 * @author Fran
 */
public class ItemHelper {

    /**
     * Get an ordered list of tags by number of appearances in the items
     * @param items Lists of items
     * @param numberOfTags Limit of tags
     * @return List of items and tags
     */
    public static ListItemsTagsOrdered getOrderListItemsTags(Collection<Item> items, long numberOfTags) {
        ListItemsTagsOrdered result = new ListItemsTagsOrdered();
        Map<String, TagOrderDTO> order = new HashMap<>();
        for (Item item : items) {
            for (ItemTag tag : item.getTags()) {
                if (order.containsKey(tag.getTagId())) {
                    order.get(tag.getTagId()).setNumberOfItems(order.get(tag.getTagId()).getNumberOfItems() + 1);
                } else {
                    TagOrderDTO tagOrder = new TagOrderDTO();
                    tagOrder.setTag(tag);
                    tagOrder.setNumberOfItems(1L);
                    order.put(tag.getTagId(), tagOrder);
                }
            }
        }
        List<TagOrderDTO> tagOrders = new ArrayList<>(order.values());
        Collections.sort(tagOrders, new Comparator<TagOrderDTO>() {
            public int compare(TagOrderDTO o1, TagOrderDTO o2) {
                return (int) (o2.getNumberOfItems() - o1.getNumberOfItems());
            }
        });
        result.setTags(tagOrders.subList(0, Math.min((int) numberOfTags, tagOrders.size())));
        result.setItems(items);
        return result;
    }

    /**
     * Get a where conditions based on parameters query, category, tag
     * @param query Pattern of search
     * @param category Id of category
     * @param tag Id of tag
     * @return Where conditions
     */
    public static List<BoolQueryField> searchItem(String query, String category, Object tag) {
        SearchItemType searchItemType = SearchItemType.getSearchItemType(query, category, tag);
        List<BoolQueryField> where;
        switch (searchItemType) {
            case QUERY:
                where = searchItemByQuery(query);
                break;
            case QUERY_CATEGORY:
                where = searchItemByQueryAndCategory(query, category);
                break;
            case QUERY_TAG:
                where = searchItemByQueryAndTag(query, (String) tag);
                break;
            case QUERY_CATEGORY_TAG:
                where = searchItemByQueryAndCategoryAndTag(query, category, (String) tag);
                break;
            case CATEGORY:
                where = searchItemByCategory(category);
                break;
            case CATEGORY_TAG:
                where = searchItemByCategoryAndTag(category, (String) tag);
                break;
            case TAG:
                where = searchItemByTag((String) tag);
                break;
            case MULTI_TAG:
                where = searchItemByMultiTag((String[]) tag);
                break;
            case QUERY_MULTI_TAG:
                where = searchItemByQueryMultiTag(query, (String[]) tag);
                break;
            case QUERY_CATEGORY_MULTI_TAG:
                where = searchItemByQueryCategoryMultiTag(query, category, (String[]) tag);
                break;
            case CATEGORY_MULTI_TAG:
                where = searchItemByCategoryMultiTag(category, (String[]) tag);
                break;
            default:
                throw new NotImplementedException();
        }
        return where;
    }

    /**
     * @param categoryName
     * @param tags
     * @return
     */
    private static List<BoolQueryField> searchItemByCategoryMultiTag(String categoryName, String[] tags) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(categoryName)), BooldFieldType.MUST));
        for (String tagName : tags) {
            where.add(new BoolQueryField(new ESMatchQuery("item.tags.name", tagName), BooldFieldType.MUST));
        }
        return where;
    }

    /**
     * @param query
     * @param categoryName
     * @param tags
     * @return
     */
    private static List<BoolQueryField> searchItemByQueryCategoryMultiTag(String query, String categoryName, String[] tags) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + query + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + query + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(categoryName)), BooldFieldType.MUST));
        for (String tagName : tags) {
            where.add(new BoolQueryField(new ESMatchQuery("item.tags.name", tagName), BooldFieldType.MUST));
        }
        return where;
    }

    /**
     * @param query
     * @param tags
     * @return
     */
    private static List<BoolQueryField> searchItemByQueryMultiTag(String query, String[] tags) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + query + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + query + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", "*" + query + "*"), BooldFieldType.SHOULD));
        for (String tagName : tags) {
            where.add(new BoolQueryField(new ESMatchQuery("item.tags.name", tagName), BooldFieldType.MUST));
        }
        return where;
    }

    /**
     * Get where conditions from search items by Query
     * @param q Pattern of search
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByQuery(String q) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.tags.name", "*" + q + "*"), BooldFieldType.SHOULD));
        return where;
    }

    /**
     * Get where conditions from search items by Category
     * @param category Id of category
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByCategory(String category) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(category)), BooldFieldType.MUST));
        return where;
    }

    /**
     * Get where conditions from search items by Query and Category
     * @param q Pattern of search
     * @param category Id of category
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByQueryAndCategory(String q, String category) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(category)), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.tags.name", "*" + q + "*"), BooldFieldType.SHOULD));
        return where;
    }

    /**
     * Get where conditions from search items by Query and tag
     * @param q Pattern of search
     * @param tag Id of tag
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByQueryAndTag(String q, String tag) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESMatchQuery("item.tags.tagId", tag), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.tags.name", "*" + q + "*"), BooldFieldType.SHOULD));
        return where;
    }

    /**
     * Get where conditions from search items by Category and tag
     * @param category Id of category
     * @param tag Id of tag
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByCategoryAndTag(String category, String tag) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESMatchQuery("item.tags.tagId", tag), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(category)), BooldFieldType.MUST));
        return where;
    }

    /**
     * Get where conditions from search items by Tag
     * @param tag Id of tag
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByTag(String tag) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESMatchQuery("item.tags.tagId", tag), BooldFieldType.MUST));
        return where;
    }

    /**
     * Get where conditions from search item by multiTag
     * @param tags Name of tags to search
     * @return where conditions
     */
    private static List<BoolQueryField> searchItemByMultiTag(String[] tags) {
        List<BoolQueryField> where = new ArrayList<>();
        for (String tagName : tags) {
            where.add(new BoolQueryField(new ESMatchQuery("item.tags.name", tagName), BooldFieldType.MUST));
        }
        return where;
    }

    /**
     * Get where conditions from search items by Query, Category and tag
     * @param q Pattern of search
     * @param category Id of category
     * @param tag Id of tag
     * @return Where conditions
     */
    private static List<BoolQueryField> searchItemByQueryAndCategoryAndTag(String q, String category, String tag) {
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("item.category", ESHelper.toExactlyWords(category)), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESMatchQuery("item.tags.tagId", tag), BooldFieldType.MUST));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.name", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.description", "*" + q + "*"), BooldFieldType.SHOULD));
        where.add(new BoolQueryField(new ESQueryStringQuery("item.tags.name", "*" + q + "*"), BooldFieldType.SHOULD));
        return where;
    }


}
