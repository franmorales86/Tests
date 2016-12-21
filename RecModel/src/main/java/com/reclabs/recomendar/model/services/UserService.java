/**
 * Project: RecModel
 * Created by: raulanatol at 09/11/2012 22:14:59
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.services;

import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.objects.user.UserOwnDataDTO;
import com.reclabs.recomendar.model.objects.user.UserSimpleDataDTO;
import com.reclabs.recomendar.model.types.SocialUserProviderId;

import java.util.List;

/**
 * @author raulanatol
 */
public interface UserService {

    /**
     * Obtener una lista de usuarios con un patrón de entrada. p.e pattern="jua" return
     * <"Juan","juanIto","juana"...>
     * @param pattern Pattern to find
     * @return List<User> o Null si no lo encuentra
     */
    List<User> findListUsersByUsername(String pattern);

    /**
     * Elimina los datos de un usuario
     * @param username Username del usuario a borrar
     */
    void deleteUser(String username);

    /**
     * Creamos un usuario nuevo en la base de datos.
     * @param user Los datos del usuario nuevo.
     * @return El usuario creado con su id autoGenerado nuevo.
     */
    User createNewUser(User user);

    /**
     * @param page Number of page
     * @param size Elements per page
     * @return User list
     */
    List<User> findAllPaginated(int page, int size);

    /**
     * Dado un DTO de usuario actualizamos todos esos datos en el usuario al que hacen referencia.
     * Para completar el registro del mismo.
     * @param user Los datos a actualizar.
     */
    void completeRegister(UserDTO user);

    /**
     * Creamos un nuevo usuario de Recomendar en la base de datos. Dándolo de alta.
     * @param user The user to register
     * @return El usuario ya registrado.
     */
    User registerUser(final UserDTO user);

    /**
     * Dado un usuario y un socialUser los enlazamos...
     * @param user2Link El usuario al que le enlazaremos el SocialUser
     * @param socialUser Los datos del SocialUser a enlazar.
     * @return El User una vez enlazados los datos.
     */
    User addSocialUser(User user2Link, SocialUser socialUser);

    /**
     * Remove the socialUser pass to parameters.
     * @param socialUser The elemento to remove from database.
     */
    void remSocialUser(SocialUser socialUser);

    /**
     * Dado el username de un usuario obtenemos el User que lo representa.
     * @param username El nombre del usuario a buscar.
     * @return El User si existe y null si no existe.
     */
    User findByUsername(String username);

    /**
     * @param email El email a verificar.
     * @return El User si existe y null si no.
     */
    User findByEmail(String email);

    /**
     * Dado un username obtenemos los datos simples del usuario
     * @param username El nombre del usuario a buscar la información.
     * @return Los datos simples del usuario.
     */
    UserSimpleDataDTO getUserSimpleDataByUsername(String username);

    /**
     * Gets the simple user data of an user id specified.
     * @param id The user id
     * @return The user simple data dto or exception if id is invalid.
     */
    UserSimpleDataDTO getUserSimpleDataById(String id);

    /**
     * @param user The user data to transform
     * @return Obtain the UserOwnDataDTO from a User pass to parameters.
     */
    UserOwnDataDTO getUserOwnDataByUser(User user);

    /**
     * Crea un nuevo usuario a partir de un dto
     * @param user Datos del nuevo usuario
     * @return Datos del nuevo usuario asociado
     */
    UserDTO createUser(UserDTO user);

    /**
     * Eliminamos el usuario de la base de datos a partir del identificador
     * @param userId Identificador del usuario
     */
    void deleteUserByUserId(String userId);

    /**
     * Actualiza los datos de un usuario
     * @param user Datos del usuario
     */
    void updateUser(UserDTO user);

    /**
     * Update the current user on the database.
     * @param user2Update user to update.
     */
    void updateUser(User user2Update);

    /**
     * Actualizamos los datos del usuario con los datos pasados por parámetros.
     * @param userId La id del usuario a modificar.
     * @param userOwnDataDTO El dato del usuario.
     */
    void updateUserData(String userId, UserOwnDataDTO userOwnDataDTO);

    /**
     * Checks if the providerId and providerUserId exist in the database.
     * @param providerId Provider id of the connection.
     * @param providerUserId User id of the provider connection.
     * @return true if the linkedUser exist.
     */
    boolean existLinkedUser(String providerId, String providerUserId);

    /**
     * Verify email of the user with the data for parameters.
     * <b>Note: This method modify user document</b>
     * @param userEmail Email of user
     * @param verificationCode Verify code of the email
     * @return true if the user is verified. False otherwise
     */
    boolean verifyEmail(String userEmail, String verificationCode);

    /**
     * Gets a valid username from un possible valid username.
     * A valid username is an name of user unique on database.
     * @param possibleUserName Recommendation of username.
     * @return The valid username. For example: if possibleUsername is johnDoe and johnDoe exist on database this method return johnDoe12 that not exist on recomendar users database.
     */
    String getValidUsername(String possibleUserName);

    /**
     * Change the user password.
     * @param user User to modify.
     * @param oldPassword The old password.
     * @param newPassword The new password.
     */
    void changePassword(User user, String oldPassword, String newPassword);

    /**
     * Start the process to delete account for the user pass to parameters.
     * @param user User to start delete account process
     */
    void deleteAccount(User user);

    /**
     * Finish the delete account process.
     * @param email The email of user to delete.
     * @param code The code to verify the process
     */
    void finishDeleteAccount(String email, String code);

    /**
     * Reset password of user pass to parameters.
     * @param user User to reset password.
     */
    void resetPassword(User user);

    /**
     * Gets user by id from the database
     * @param userId The id of the user to search
     * @return The user found.
     */
    User getUserById(String userId);

    /**
     * @return Gets the default avatar url of users.
     */
    String getDefaultUserAvatar();

    /**
     * Ban the user indicate
     * @param user The user to ban
     */
    void banUser(User user);

    /**
     * Reduce on 1 point the pending recommendation value of the current user.
     * @param userId The id of the user to reduce
     */
    void reduceRecommendation(String userId);

    /**
     * Change the autoShared of a specified social network.
     * @param user The user to modify
     * @param socialUserProviderId The social ID identifier
     * @param value The value of autoShared
     */
    void setAutoShared(User user, SocialUserProviderId socialUserProviderId, boolean value);

    /**
     * Return the number of pending recommendations of an user
     * @param userId The userId to find
     * @return > 0 number of pending recommendations.
     */
    Long getPendingRecommendationsFromUser(String userId);

    /**
     * Gets a list of users from a specified list of Facebook user ids
     * @param friendsIds The list to find
     * @return The list of users
     */
    List<User> getUserByFacebookFriendsIds(List<String> friendsIds);
}
