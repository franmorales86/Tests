/**
 * Project: RecModel
 * Created by: raulanatol at 22/10/13 15:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.social;

import com.reclabs.recomendar.model.documents.social.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author raulanatol
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    /**
     * @param itemId The id of the item
     * @return A list of comments from a specified itemId
     */
    List<Comment> findByItemId(String itemId);

}
