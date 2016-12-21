/**
 * Project: RecCore
 * Created by: raulanatol at 02/01/14 16:07
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.libs.recqueuedriver.RecQueueDriver;
import com.reclabs.libs.recqueuedriver.messages.SendyActionMessage;
import com.reclabs.libs.recqueuedriver.messages.ShareSocialNetworkItemMessage;
import com.reclabs.libs.recsendylib.elements.SendyAction;
import com.reclabs.libs.recsendylib.elements.SendyList;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.model.documents.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author raulanatol
 */
public class QueueHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueHelper.class);

    @Value("${amazon.queue.shareSocialNetworkItem}")
    private String queueSocialNetwork;

    @Value("${amazon.queue.sendy}")
    private String queueSendy;

    private RecQueueDriver recQueueDriver;

    public void setDriver(RecQueueDriver recQueueDriver) {
        this.recQueueDriver = recQueueDriver;
    }

    public void registerOnQueueNewEmail(User user) {
        try {
            SendyActionMessage sendyActionMessage = new SendyActionMessage();
            sendyActionMessage.setSendyList(SendyList.ALL_USERS);
            sendyActionMessage.setAction(SendyAction.SUBSCRIBE);
            sendyActionMessage.setEmail(user.getEmail());
            sendyActionMessage.setName(user.getUsername());
            recQueueDriver.sendMessage(queueSendy, sendyActionMessage);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to register new user email on the SendyQueue]", e);
        }
    }


    public void trackEventGetShortURL(String userId, String itemId) {
        try {
            ShareSocialNetworkItemMessage shareSocialNetworkItem = new ShareSocialNetworkItemMessage();
            shareSocialNetworkItem.setOwnerId(userId);
            shareSocialNetworkItem.setEventDate(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
            shareSocialNetworkItem.setItemId(itemId);
            recQueueDriver.sendMessage(queueSocialNetwork, shareSocialNetworkItem);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to register event on the social network queue]", e);
        }
    }

    public void registerUserDelete(User user) {
        try {
            unSubscribeGeneralMailMarketing(user);
            unSubscribeAllUsers(user);
            subscribeDeleteUsers(user);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to register user delete]", e);
        }
    }

    private void unSubscribeAllUsers(User user) {
        try {
            SendyActionMessage sendyActionMessage = new SendyActionMessage();
            sendyActionMessage.setSendyList(SendyList.ALL_USERS);
            sendyActionMessage.setAction(SendyAction.UN_SUBSCRIBE);
            sendyActionMessage.setEmail(user.getEmail());
            sendyActionMessage.setName(user.getUsername());
            recQueueDriver.sendMessage(queueSendy, sendyActionMessage);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to unSubscribe user to all users]", e);
        }
    }

    private void subscribeDeleteUsers(User user) {
        try {
            SendyActionMessage sendyActionMessage = new SendyActionMessage();
            sendyActionMessage.setSendyList(SendyList.UNSUBSCRIBE_REC_USERS);
            sendyActionMessage.setAction(SendyAction.SUBSCRIBE);
            sendyActionMessage.setEmail(user.getEmail());
            sendyActionMessage.setName(user.getUsername());
            recQueueDriver.sendMessage(queueSendy, sendyActionMessage);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to subscribe user to unSubscribe users]", e);
        }
    }

    private void unSubscribeGeneralMailMarketing(User user) {
        try {
            SendyActionMessage sendyActionMessage = new SendyActionMessage();
            sendyActionMessage.setSendyList(SendyList.GENERAL_MAIL_MARKETING);
            sendyActionMessage.setAction(SendyAction.UN_SUBSCRIBE);
            sendyActionMessage.setEmail(user.getEmail());
            sendyActionMessage.setName(user.getUsername());
            recQueueDriver.sendMessage(queueSendy, sendyActionMessage);
        } catch (Exception e) {
            LOGGER.warn("[Impossible to unSubscribe user to general mail marketing]", e);
        }
    }
}
