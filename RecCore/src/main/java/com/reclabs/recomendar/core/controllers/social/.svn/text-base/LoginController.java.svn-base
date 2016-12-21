/**
 * Project: RecCore
 * Created by: raulanatol at 05/02/2013 18:34:48
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.social;

import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.core.assemblers.UserAssembler;
import com.reclabs.recomendar.core.exceptions.common.PermissionDeniedException;
import com.reclabs.recomendar.core.misc.security.OAuthHelper;
import com.reclabs.recomendar.core.misc.security.SecurityHelper;
import com.reclabs.recomendar.core.services.security.UserDetailsServiceImpl;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.objects.user.UserDTO;
import com.reclabs.recomendar.model.services.UserService;
import com.reclabs.recomendar.model.services.social.InvitationService;
import com.reclabs.recomendar.model.types.user.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.reclabs.recomendar.core.misc.security.SecurityHelper.getLoggedInUser;

/**
 * Controlador que se encargará de gestionar el registro de los usuarios.
 * @author raulanatol
 */
// TODO SECURITY todos los elementos de este controlador sólo podrán ser accesibles por el clientId de Recomendar (esta
// parte de la api no deberá ser abierta)
@SuppressWarnings("ALL")
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private OAuthHelper oAuthHelper;

    @Value("${misc.web.url}")
    private String prefixURL;

    @Value("${misc.core.url}")
    private String coreURL;

    /**
     * Mostramos la pantalla de login de Recomendar.
     * @return La pantalla de login.
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Después de finalizar un signin de una red social. Se ejecuta este método.
     * Si necesitamos más datos del usuario le enviamos a la pantalla para completar dichos datos.
     * En caso de que no necesitemos más datos del usuario lo enviaremos al index.
     * @param webRequest
     * @param request
     * @return La pantalla de fin de login.
     */
    @RequestMapping("/social/signin")
    public ModelAndView signin(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result;
        User user = getLoggedInUser();
        if (user.getState() == UserState.HALF_DATA) {
            ModelMap modelMap = new ModelMap();
            UserDTO userDTO = userAssembler.toRight(user);
            String oldUser = userDTO.getUsername();
            userDTO.setUsername("");
            modelMap.put("user", userDTO);
            modelMap.put("usernameOld", oldUser);
            result = new ModelAndView("completeRegister", modelMap);
        } else {
            oAuthHelper.renewLoggedUser(request);
            result = new ModelAndView("redirect:" + prefixURL + "/login");
        }
        return result;
    }

    /**
     * Mostramos la pantalla de registro de la aplicación.
     * @return La pantalla de registro.
     */
    //FIXME bloquearlo
    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView result;
        if (SecurityHelper.existLoggedUser()) {
            result = new ModelAndView("index");
        } else {
            result = new ModelAndView("register", "user", new UserDTO());
        }
        return result;
    }

    /**
     * Show forget password form.
     * @return Data and view of forget password.
     */
    @RequestMapping("/forgetPassword")
    public ModelAndView forgetPassword() {
        return new ModelAndView("forgetPassword", "user", new UserDTO());
    }

    /**
     * Start the forget password procedure.
     * @param user The user data.
     * @return Model and view of response.
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ModelAndView forgetPasswordPost(@ModelAttribute("user") UserDTO user) {
        ModelMap model;
        if (!StringHelper.isEmpty(user.getEmail())) {
            User userToResetPassword = userService.findByEmail(user.getEmail());
            if (userToResetPassword != null) {
                userService.resetPassword(userToResetPassword);
                model = new ModelMap("ok", true);
            } else {
                LOGGER.warn("[Trying to reset password from an empty user][Email: {}]", user.getEmail());
                model = new ModelMap("error", true);
            }
        } else {
            LOGGER.warn("[Trying to reset password from an empty email]");
            model = new ModelMap("error", true);
        }
        return new ModelAndView("forgetPassword", model);
    }


    /**
     * Guardamos los datos de un usuario.
     * @param user
     * @param request
     * @return View of the result
     */
    @RequestMapping(value = "/registerSocial", method = RequestMethod.POST)
    public ModelAndView registerSocial(@ModelAttribute("user") final UserDTO user, HttpServletRequest request, WebRequest webRequest) {
        LOGGER.info("[Starting process to register an user]");
        ModelAndView result;
        if (NullHelper.isAnyNull(user.getName(), user.getEmail(), user.getUsername(), user.getPassword())) {
            result = new ModelAndView("completeRegister", "error", true);
        } else {
            verifyNoCheats(user);
            String newPassword = passwordEncoder.encodePassword(user.getPassword(), null);
            user.setPassword(newPassword);
            userService.completeRegister(user);
            ProviderSignInUtils.handlePostSignUp(user.getId(), webRequest);
            oAuthHelper.renewLoggedUser(request);
            result = new ModelAndView("redirect:http://www.recomendar.com/gracias");
        }
        return result;
    }

    @RequestMapping(value = "/registerSocial", method = RequestMethod.GET)
    public ModelAndView registerSocialGet(HttpServletRequest request) {
        return new ModelAndView("/messages/registerCompleted");
    }

    /**
     * Creamos el usuario de Recomendar.
     * @param user Los datos del nuevo usuario.
     * @return La redirección.
     */
    @RequestMapping(value = "/registerRec", method = RequestMethod.POST)
    public ModelAndView registerRec(@ModelAttribute("user") final UserDTO user) {
        ModelAndView result;
        if (NullHelper.isAnyEmpty(user.getName(), user.getEmail(), user.getUsername(), user.getPassword())) {
            result = new ModelAndView("register", "error", true);
        } else {
            String newPassword = passwordEncoder.encodePassword(user.getPassword(), null);
            user.setPassword(newPassword);
            User userCreated = userService.registerUser(user);
            SecurityHelper.performLogin(userCreated.getId(), userDetailsService);
            result = new ModelAndView("index", "message", "Registro completado.");
        }
        return result;
    }


    /**
     * Creamos el usuario de Recomendar.
     * @param user Los datos del nuevo usuario.
     * @return La redirección.
     */
    @RequestMapping(value = "/registerWithInvitation", method = RequestMethod.POST)
    public ModelAndView registerUserWithInvitation(@ModelAttribute("user") final UserDTO user) {
        ModelAndView result;
        if (NullHelper.isAnyEmpty(user.getName(), user.getEmail(), user.getUsername(), user.getPassword())) {
            result = new ModelAndView("register", "error", true);
        } else {
            String newPassword = passwordEncoder.encodePassword(user.getPassword(), null);
            user.setPassword(newPassword);
            User userCreated = userService.registerUser(user);
            SecurityHelper.performLogin(userCreated.getId(), userDetailsService);
            result = new ModelAndView("redirect:http://www.recomendar.com/gracias");
        }
        return result;
    }


    /**
     * @param userEmail
     * @param code
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteAccount/{userEmail}/{code}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable("userEmail") String userEmail, @PathVariable("code") String code, HttpServletResponse response, HttpServletRequest request) {
        LOGGER.info("[Starting process to delete account][UserEmail: {}][Code: {}]", userEmail, code);
        String result = "404";
        if (!StringHelper.isAnyEmpty(userEmail, code)) {
            try {
                userService.finishDeleteAccount(userEmail, code);
                SecurityHelper.logout(request, response);
                result = "redirect:" + prefixURL + "/logout";
            } catch (Exception ignore) {
                result = "404";
            }
        }
        return result;
    }

    /**
     * Verificamos que no se estén haciendo trampas a la hora de modificar datos de usuarios que no sean los
     * correctos.
     * @param user2Modify El usuario que se va a modificar.
     */
    private void verifyNoCheats(UserDTO user2Modify) {
        User sessionUser = SecurityHelper.getLoggedInUser();
        String sessionId = (sessionUser != null) ? sessionUser.getId() : null;
        if (sessionId == null || !StringHelper.equals(sessionId, user2Modify.getId())) {
            // Se está modificando un usuario diferente.
            LOGGER.error("The user with id {} try to modify userId {}", sessionId, user2Modify.getId());
            throw new PermissionDeniedException();
        }
    }

    /**
     * Verify user email process.
     * @param userEmail User email to verify.
     * @param code verification code.
     * @return Web with error o success result
     */
    @RequestMapping(value = "/verify/{userEmail}/{code}", method = RequestMethod.GET)
    public String verifyEmail(@PathVariable("userEmail") String userEmail, @PathVariable("code") String code, HttpServletRequest request) {
        String result;
        if (userService.verifyEmail(userEmail, code)) {
            oAuthHelper.renewLoggedUser(request);
            result = "redirect:" + prefixURL + "/login";
        } else {
            LOGGER.warn("[Impossible to verify mail][Email: {}][Code: {}]", userEmail, code);
            result = "redirect:" + prefixURL + "/404";
        }
        return result;
    }

    /**
     * Start process to register new user if the verification code is OK
     * @param email The email of the new user
     * @param code The code of the invitation
     * @return Web of register o error verification
     */
    @RequestMapping(value = "/verifyInvitation/{email}/{code}", method = RequestMethod.GET)
    public ModelAndView verifyInvitation(@PathVariable("email") String email, @PathVariable("code") String code) {
        ModelAndView result;
        if (invitationService.existInvitation(email, code)) {
            ModelMap modelMap = new ModelMap();
            User user = new User();
            user.setEmail(email);
            modelMap.put("user", user);
            modelMap.put("code", code);
            result = new ModelAndView("register/withInvitation", modelMap);
        } else {
            result = new ModelAndView(new RedirectView(prefixURL + "/invitationFailed"));
        }
        return result;
    }

    /**
     * The user can be register on Recomendar from the LandingPage
     * @return The registration form (from Landing) or Recomendar.com if the user is already registered.
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ModelAndView startOnRecomendar() {
        ModelAndView result;
        boolean userLogged = SecurityHelper.existLoggedUser();
        if (userLogged) {
            result = new ModelAndView(new RedirectView(prefixURL));
        } else {
            result = new ModelAndView("register/fromLanding");
        }
        return result;
    }
}
