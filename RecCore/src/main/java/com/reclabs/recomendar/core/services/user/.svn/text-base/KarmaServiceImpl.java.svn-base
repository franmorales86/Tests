/**
 * Project: RecCore
 * Created by: raulanatol at 03/11/13 11:25
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.services.user;

import com.reclabs.recomendar.model.documents.statistics.Karma;
import com.reclabs.recomendar.model.repositories.user.KarmaRepository;
import com.reclabs.recomendar.model.services.user.KarmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raulanatol
 */
@Service
public class KarmaServiceImpl implements KarmaService {

    @Autowired
    private KarmaRepository karmaRepository;


    @Override
    public Karma getKarmaByUserId(String userId) {
        return karmaRepository.findOne(userId);
    }
}
