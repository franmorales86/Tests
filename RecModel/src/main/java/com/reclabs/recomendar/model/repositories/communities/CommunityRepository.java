/**
 * Project: RecModel
 * Created by: fjmorales at 05/04/2013 16:46:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.communities;

import com.reclabs.recomendar.model.documents.communities.Community;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author fjmorales
 */
public interface CommunityRepository extends MongoRepository<Community, String> {
    //
}
