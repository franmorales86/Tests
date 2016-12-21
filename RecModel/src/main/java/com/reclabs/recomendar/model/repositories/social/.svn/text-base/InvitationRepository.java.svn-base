/**
 * Project: RecModel
 * Created by: raulanatol at 05/11/13 12:28
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.social;

import com.reclabs.recomendar.model.documents.social.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author raulanatol
 */
public interface InvitationRepository extends MongoRepository<Invitation, String> {

    List<Invitation> findByEmail(String email);
}
