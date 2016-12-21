/**
 * Project: RecCore
 * Created by: raulanatol at 22/10/13 16:26
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.social;

import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.documents.social.Comment;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.social.CommentRepository;
import com.reclabs.recomendar.model.services.social.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raulanatol
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(User owner, String itemId, String comment) {
        LOGGER.debug("[Starting process to create a comment][User: {}][ItemId: {}]");
        Comment newComment = new Comment();
        newComment.setItemId(itemId);
        newComment.setUserId(owner.getId());
        newComment.setComment(comment);
        newComment.setCreationDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        newComment.setOwnerName(owner.getUsername());
        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> getCommentsByItemId(String itemId) {
        return commentRepository.findByItemId(itemId);
    }
}
