/**
 * Project: RecCore
 * Created by: raulanatol at 09/11/2012 22:10:12
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.users;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.RandomHelper;
import com.reclabs.recomendar.common.helpers.RegExpHelper;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.types.RegexOptionsType;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.repositories.users.UserRepository;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author raulanatol
 */
//TODO cambiar al paquete correcto "user"
@Repository
public class UserRepositoryImpl extends SimpleMongoRepository<User, String> implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    public UserRepositoryImpl(final MongoTemplate mongoTemplate) {
        super(new MongoRepositoryFactory(mongoTemplate).<User, String>getEntityInformation(User.class), mongoTemplate);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByUsername(java.lang.String)
     */
    @Override
    public List<User> findByUsername(final String username) {
        Query query = new Query(Criteria.where("username").regex(RegExpHelper.addStartEndSymbol(username), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByName(java.lang.String)
     */
    @Override
    public List<User> findByName(final String name) {
        Query query = new Query(Criteria.where("name").regex(name, RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByExactlyName(java.lang.String)
     */
    @Override
    public List<User> findByExactlyName(final String name) {
        // Se realiza la busqueda por nombre no teniendo en cuenta mayusculas y minusculas
        Query query = new Query(Criteria.where("name").regex(RegExpHelper.addStartEndSymbol(name), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByUserId(java.lang.String)
     */
    @Override
    public User findByUserId(final String userId) {
        return getMongoOperations().findById(userId, User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#findUserIdsByProviderIdAndProviderUserId(java.lang
     * .String, java.lang.String)
     */
    @Override
    public List<String> findUserIdsByProviderIdAndProviderUserId(final String providerId, final String providerUserId) {
        List<String> ids = new ArrayList<>();
        List<User> users = findUsersByProviderIdAndProviderUserId(providerId, providerUserId);
        for (User user : users) {
            ids.add(user.getId());
        }
        return ids;
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByUserIdAndProviderId(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<User> findByUserIdAndProviderId(final String userId, final String providerId) {
        return getMongoOperations().find(query(where("id").is(userId).and("socialUsers.providerId").is(providerId)), User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#selectMaxRankByUserIdAndProviderId(java.lang.String
     * , java.lang.String)
     */
    @Override
    public Integer selectMaxRankByUserIdAndProviderId(final String userId, final String providerId) {
        LOGGER.warn("Not implemented: " + userId + " " + providerId);
        // TODO ver como se usará xq no estamos almacenando rank
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#findByUserIdAndProviderUserIds(java.lang.String,
     * org.springframework.util.MultiValueMap)
     */
    @Override
    public List<User> findByUserIdAndProviderUserIds(final String userId, final MultiValueMap<String, String> providerUserIds) {
        // TODO ver como se usa. Seguramente sean un userId al que se le pasan un listado de providers y
        // providerUserId y debemos obtener todos los usuarios que contienen esos parámetros.
        LOGGER.warn("Not implemented method: ", userId, providerUserIds);
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#findPrimaryByUserIdAndProviderId(java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<User> findPrimaryByUserIdAndProviderId(final String userId, final String providerId) {
        Query query = new Query(Criteria.where("id").is(userId).and("socialUsers.providerId").is(providerId));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#get(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public User get(final String userId, final String providerId, final String providerUserId) {
        return getMongoOperations().findOne(query(where("id").is(userId).and("socialUsers.providerId").is(providerId).and("socialUsers.providerUserId").is(providerUserId)), User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#findUserIdsByProviderIdAndProviderUserIds(java.
     * lang.String, java.util.Set)
     */
    @Override
    public List<String> findUserIdsByProviderIdAndProviderUserIds(final String providerId, final Set<String> providerUserIds) {
        List<String> result = new ArrayList<>();
        List<User> users = findUsersByProviderIdAndProviderUserIds(SocialUserProviderId.byProviderId(providerId), providerUserIds);
        for (User user : users) {
            result.add(user.getId());
        }
        return result;
    }

    @Override
    public List<User> findUsersByProviderIdAndProviderUserIds(SocialUserProviderId socialUserProviderId, Collection<String> providerUserIds) {
        Query query = new Query(Criteria.where("socialUsers").elemMatch(where("providerId").is(socialUserProviderId).and("providerUserId").in(providerUserIds)));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#addSocialUser(com.reclabs.recomendar.model.documents
     * .SocialUser)
     */
    @Override
    public void addSocialUser(final SocialUser socialUser) {
        LOGGER.warn("Not implemented yet!");
    }

    @Override
    public User getByUsername(final String username) {
        Query query = new Query(Criteria.where("username").regex(RegExpHelper.addStartEndSymbol(username), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return getMongoOperations().findOne(query, User.class);
    }

    @Override
    public User getByUsernameOrEmail(String usernameOrEmail) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        Criteria usernameCriteria = Criteria.where("username").regex(RegExpHelper.addStartEndSymbol(usernameOrEmail), RegexOptionsType.CASE_INSENSITIVE.getCode());
        Criteria emailCriteria = Criteria.where("email").regex(RegExpHelper.addStartEndSymbol(usernameOrEmail), RegexOptionsType.CASE_INSENSITIVE.getCode());
        criteria.orOperator(usernameCriteria, emailCriteria);
        query.addCriteria(criteria);
        return getMongoOperations().findOne(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#findUsersByProviderIdAndProviderUserId(java.lang
     * .String, java.lang.String)
     */
    @Override
    public List<User> findUsersByProviderIdAndProviderUserId(final String providerId, final String providerUserId) {
        Query query = new Query(Criteria.where("socialUsers").elemMatch(where("providerId").is(providerId).and("providerUserId").is(providerUserId)));
        return getMongoOperations().find(query, User.class);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.UserRepository#findByEmail(java.lang.String)
     */
    @Override
    public User findByEmail(final String email) {
        return getMongoOperations().findOne(query(where("email").is(email)), User.class);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.reclabs.recomendar.model.repositories.UserRepository#removeSocialUser(com.reclabs.recomendar.model.documents
     * .users.SocialUser)
     */
    @Override
    public void removeSocialUser(SocialUser socialUser) {
        Query query = new Query(Criteria.where("socialUsers").elemMatch(where("providerId").is(socialUser.getProviderId()).and("providerUserId").is(socialUser.getProviderUserId())));
        User user = getMongoOperations().findOne(query, User.class);
        if (user == null) {
            LOGGER.error("SociaUser is not valid {}", socialUser);
            throw new RecIllegalArgumentException("SocialUser is not valid");
        }
        user.getSocialUsers().remove(socialUser);
        getMongoOperations().save(user);
    }

    /*
     * (non-Javadoc)
     * @see com.reclabs.recomendar.model.repositories.users.UserRepository#existsUserByUsername(java.lang.String)
     */
    @Override
    public boolean existsUserByUsername(String username) {
        Query query = Query.query(Criteria.where("username").regex(RegExpHelper.addStartEndSymbol(username), RegexOptionsType.CASE_INSENSITIVE.getCode()));
        return (getMongoOperations().count(query, User.class) > 0);
    }

    @Override
    public String generateNewPossibleUserName(String username) {
        List<String> possibleUsernameList = RandomHelper.generateUsernameList(username);
        Query query = Query.query(Criteria.where("username").in(possibleUsernameList));
        query.fields().include("username");
        List<String> users = getMongoOperations().find(query, String.class);
        return CollectionHelper.diffCollectionsAndGetOne(possibleUsernameList, users);
    }
}
