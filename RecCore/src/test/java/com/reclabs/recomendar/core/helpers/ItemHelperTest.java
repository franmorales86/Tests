/**
 * Project: RecCore
 * Created by: Fran at 24/06/13 17:24
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.items.ItemTag;
import com.reclabs.recomendar.model.objects.items.ListItemsTagsOrdered;
import com.reclabs.recomendar.model.objects.tags.TagOrderDTO;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Fran
 */
public class ItemHelperTest {

    /**
     * @see ItemHelper#getOrderListItemsTags(java.util.Collection, long)
     */
    @Test
    public void getOrderListItemsTagsTest() {
        ItemTag tag1 = new ItemTag();
        tag1.setTagId("tag1");
        ItemTag tag2 = new ItemTag();
        tag2.setTagId("tag2");
        ItemTag tag3 = new ItemTag();
        tag3.setTagId("tag3");
        ItemTag tag4 = new ItemTag();
        tag4.setTagId("tag4");

        Item item1 = new Item();
        item1.setTags(Arrays.asList(tag1, tag2));
        Item item2 = new Item();
        item2.setTags(Arrays.asList(tag2, tag3, tag4));
        Item item3 = new Item();
        item3.setTags(Arrays.asList(tag1, tag2, tag4));
        Item item4 = new Item();
        item4.setTags(Arrays.asList(tag1, tag2));

        ListItemsTagsOrdered result = new ListItemsTagsOrdered();
        TagOrderDTO resultTag1 = new TagOrderDTO();
        resultTag1.setTag(tag1);
        resultTag1.setNumberOfItems(3L);
        TagOrderDTO resultTag2 = new TagOrderDTO();
        resultTag2.setTag(tag2);
        resultTag2.setNumberOfItems(4L);
        TagOrderDTO resultTag3 = new TagOrderDTO();
        resultTag3.setTag(tag3);
        resultTag3.setNumberOfItems(1L);
        TagOrderDTO resultTag4 = new TagOrderDTO();
        resultTag4.setTag(tag4);
        resultTag4.setNumberOfItems(2L);
        result.setItems(Arrays.asList(item1, item2, item3, item4));
        result.setTags((Arrays.asList(resultTag2, resultTag1, resultTag4)));

        assertThat(ItemHelper.getOrderListItemsTags(Arrays.asList(item1, item2, item3, item4), 3), is(result));
        result.setTags((Arrays.asList(resultTag2, resultTag1)));
        assertThat(ItemHelper.getOrderListItemsTags(Arrays.asList(item1, item2, item3, item4), 2), is(result));
        result.setTags((Arrays.asList(resultTag2)));
        assertThat(ItemHelper.getOrderListItemsTags(Arrays.asList(item1, item2, item3, item4), 1), is(result));
        result.setTags((Arrays.asList(resultTag2, resultTag1, resultTag4, resultTag3)));
        assertThat(ItemHelper.getOrderListItemsTags(Arrays.asList(item1, item2, item3, item4), 8), is(result));
    }
}
