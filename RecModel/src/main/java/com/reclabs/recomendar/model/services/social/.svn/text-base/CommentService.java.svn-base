/**
 * Project: RecModel
 * Created by: raulanatol at 22/10/13 15:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services.social;

import com.reclabs.recomendar.model.documents.social.Comment;
import com.reclabs.recomendar.model.documents.users.User;

import java.util.List;

/**
 * @author raulanatol
 */
public interface CommentService {

    /**
     * Create a new comment
     * @param owner the owner of the comment
     * @param itemId The id of the item
     * @param comment The text of the comment
     * @return The comment created
     */
    Comment create(User owner, String itemId, String comment);

    /**
     * @param itemId The itemId of the item
     * @return A list of comment for a specified item
     */
    List<Comment> getCommentsByItemId(String itemId);
}
