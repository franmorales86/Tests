/**
 * Project: RecCore
 * Created by: raulanatol at 02/05/2013 15:45:47
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.items;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.actions.queries.ESHasChildQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESMatchQuery;
import com.reclabs.recomendar.esdriver.index.ESTypes;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.model.documents.items.Item;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.items.ItemHaveService;
import com.reclabs.recomendar.objects.ResponseDTO;
import io.searchbox.core.search.sort.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Controller for all requests from items that a user have.
 * @author fjmorales
 */
@Controller
@RequestMapping(value = "/v1/itemHave/")
public class ItemHaveController {

    @Autowired
    private ItemHaveService itemHaveService;

    @Autowired
    private ESDriver esDriver;

    /**
     * Add item to list of items that the session user has.
     * @param itemId It of item.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO haveItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        User user = SecurityHelper.getLoggedInUser();
        itemHaveService.userHaveItem(user.getId(), itemId);
        return ResponseDTO.OK;
    }

    /**
     * Remove item that a user have by userId and itemId
     * @param itemId Id of item
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public ResponseDTO removeItemHaveByUserAndItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        User user = SecurityHelper.getLoggedInUser();
        itemHaveService.removeItemHaveByUserIdAndItemId(user.getId(), itemId);
        return ResponseDTO.OK;
    }

    /**
     * Returns items that user have ordered by most recommended.
     * @param userId Id of user
     * @param page Page of the paginated
     * @param size Number of item per page
     * @return List of items most recommended
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Collection<Item> itemHaveByUser(@RequestParam(value = "userId", required = false) String userId,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "20") Integer size) {
        String userIdSearch = userId;
        if (NullHelper.isAnyNull(userId)) {
            userIdSearch = SecurityHelper.getLoggedInUser().getId();
        }
        ParameterHelper.assertEmpty(userIdSearch);
        ESFind query = new ESFind(IndexType.ITEMHAVE_INDEX, new ESHasChildQuery(ESTypes.ITEMHAVE, new ESMatchQuery("userId", userIdSearch)), Arrays.asList(new Sort("createdDate", Sort.Sorting.DESC)), page, size);
        return esDriver.searchByQueryList(query, Item.class);
    }
}
