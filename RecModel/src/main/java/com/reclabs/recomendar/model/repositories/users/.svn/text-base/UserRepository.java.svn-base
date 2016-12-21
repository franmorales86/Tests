/**
 * Project: RecModel
 * Created by: raulanatol at 26/03/2013 10:25:00
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.repositories.users;

import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Repositorio de usuarios.
 * @author raulanatol
 */
//TODO cambiar al paquete correcto "user"
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Dado un nombre de un usuario lo buscamos en la base de datos.
     * @param username El nombre de usuario a buscar.
     * @return Listado de usuarios que tengan ese .
     */
    List<User> findByUsername(String username);

    /**
     * Dado un userId unívoco de usuario obtenemos el document de Usuarios: {@link User}
     * @param userId El identificador de usuario
     * @return El user
     */
    User findByUserId(String userId);

    /**
     * @param providerId Id of the provider
     * @param providerUserId Id of the user provider
     * @return Listado de userIds que correspondan con los parámetros indicados.
     */
    List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    /**
     * Obtenemos todos los usuarios con providerId and userId dados, debería devolver siempre 1 usuario.
     * @param providerId Id of the provider
     * @param providerUserId Id of the user provider
     * @return Debería devolver siempre 1
     */
    List<User> findUsersByProviderIdAndProviderUserId(String providerId, String providerUserId);

    /**
     * @param userId Id of the user
     * @param providerId Id of the provider
     * @return Listado de usuarios.
     */
    List<User> findByUserIdAndProviderId(String userId, String providerId);

    /**
     * @param userId Id of the user
     * @param providerId Id of the provider
     * @return Indice del ranking
     */
    Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId);

    /**
     * @param userId Id of the user
     * @param providerUserIds Ids of users and provider
     * @return Listado de usuarios.
     */
    List<User> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);

    /**
     * @param userId Id of the user
     * @param providerId Id of the provider
     * @return Listado de usuarios.
     */
    List<User> findPrimaryByUserIdAndProviderId(String userId, String providerId);

    /**
     * @param userId Id of the user
     * @param providerId Id of the provider
     * @param providerUserId The user and provider userID
     * @return El usuario.
     */
    User get(String userId, String providerId, String providerUserId);

    /**
     * @param providerId ProviderID
     * @param providerUserIds Provider user ids
     * @return Listado de UserIds
     */
    java.util.Collection<? extends String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);

    /**
     * Gets a list of user that have the socialUserProviderId and the socialUserProviderUserId specified
     * @param socialUserProviderId The social user provider id - Facebook, Twitter
     * @param providerUserIds The list of social user provider user id to find.
     * @return The list with the results or empty list.
     */
    List<User> findUsersByProviderIdAndProviderUserIds(SocialUserProviderId socialUserProviderId, Collection<String> providerUserIds);

    /**
     * Añadimos un socialUser a un usuario con id socialUser.userId
     * @param socialUser The social user
     */
    void addSocialUser(SocialUser socialUser);

    /**
     * Metodo que busca los usuarios que contengan en el campo nombre el parametro pasado
     * @param name Nombre a buscar
     * @return Listado de usuarios que contienen ese nombre
     */
    List<User> findByName(String name);

    /**
     * Metodo que busca los usuarios cuyo nombre sea exactamente igual al parametro pasado, sin tener en cuenta mayusculas/minusculas
     * @param name Nombre a buscar
     * @return Listado de usuarios con ese nombre
     */
    List<User> findByExactlyName(String name);

    /**
     * Dado el nombre de un usuario lo obtenemos de la base de datos.
     * @param username Username to get the user
     * @return El documento {@link User}
     */
    User getByUsername(String username);

    /**
     * Gets the user that username of email is the current parameter
     * @param usernameOrEmail The value to find
     * @return The user or null
     */
    User getByUsernameOrEmail(String usernameOrEmail);

    /**
     * @param email El email por el que buscar.
     * @return El user con email dado.
     */
    User findByEmail(String email);

    /**
     * Remove a socialUser pass to parameters.
     * @param socialUser Social user to remove
     */
    void removeSocialUser(SocialUser socialUser);

    /**
     * Obtiene si existe un usuario a partir del username
     * @param username Username a buscar
     * @return Indica si existe o no el usuario
     */
    boolean existsUserByUsername(String username);

    /**
     * From an username gets a valid username with the same pattern
     * @param username Username pattern
     * @return A valid username
     */
    String generateNewPossibleUserName(String username);
}
