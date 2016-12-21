/**
 * Project: RecCore
 * Created by: raulanatol at 22/10/13 16:33
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.social;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.model.documents.social.Comment;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.services.social.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raulanatol
 */
@Controller
@RequestMapping(value = "/v1/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Add item to list of items that the session user has.
     * @param itemId It of item.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public Comment commentItem(@PathVariable("itemId") String itemId, @RequestBody Comment comment) {
        ParameterHelper.assertAnyEmpty(itemId, comment.getComment());
        User user = SecurityHelper.getLoggedInUser();
        return commentService.create(user, itemId, comment.getComment());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{itemId}")
    @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
    public List<Comment> commentItem(@PathVariable("itemId") String itemId) {
        ParameterHelper.assertEmpty(itemId);
        return commentService.getCommentsByItemId(itemId);
    }

}
