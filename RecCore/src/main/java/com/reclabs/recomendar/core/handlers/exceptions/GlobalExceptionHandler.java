/**
 * Project: RecCore
 * Created by: fjmorales at 03/12/2012 13:54:40
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.handlers.exceptions;

import com.reclabs.recomendar.common.exceptions.generic.ApiException;
import com.reclabs.recomendar.common.exceptions.generic.ErrorException;
import com.reclabs.recomendar.common.types.CodeErrorType;
import com.reclabs.recomendar.objects.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.Collection;

/**
 * @author fjmorales
 */
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * Listado de excepciones que serán excluidas por este handler.
     */
    private final Collection<Class<? extends RuntimeException>> excludeExceptions;

    private final Collection<Class<? extends RuntimeException>> involveExceptions;

    public GlobalExceptionHandler(final Collection<Class<? extends RuntimeException>> excludeExceptions, Collection<Class<? extends RuntimeException>> involveExceptions) {
        this.excludeExceptions = excludeExceptions;
        this.involveExceptions = involveExceptions;
    }

    /**
     * Manejador para el resto de excepciones. Se excluyen aquellas excepciones de autenticacion que deben ser
     * devueltas a la vista
     * @param exception Excepcion lanzada por la aplicacion
     * @return Vista en formato JSON con el codigo y el mensaje de la excepcion
     * @throws Exception
     */

    /**
     * Manage exceptions.
     * Exceptions into excludeExceptions list will be excluded and throw it.
     * Exceptions into involvedExceptions list will be hide and throw a generic exception instead of exception.
     * @param exception exception throw for the application.
     * @return JSON with code and message for the exception.
     * @throws Exception In case of exceptions into excludeExceptions or involvedExceptions.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleOAuthException(final Exception exception) throws Exception {
        ifExcludeException(exception);
        ResponseDTO result = ifInvolvedException(exception);
        if (result == null) {
            result = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        }
        return new ModelAndView(new MappingJacksonJsonView(), "errors", result);
    }

    /**
     * Dada la excepciones la excluimos o no en caso de que esté o no en el listado de excepciones a excluir.
     * @param exception The exception to verify if is excluded
     * @throws Exception En caso de que la excepción sea excluible.
     */
    private void ifExcludeException(final Exception exception) throws Exception {
        for (Class<? extends RuntimeException> excludeExceptionClass : excludeExceptions) {
            if (exception.getClass().equals(excludeExceptionClass)) {
                throw exception;
            }
        }
    }

    private ResponseDTO ifInvolvedException(Exception exception) {
        ResponseDTO result = null;
        for (Class<? extends RuntimeException> involvedExceptionClass : involveExceptions) {
            if (exception.getClass().equals(involvedExceptionClass)) {
                LOGGER.warn("Involved Exception thrown: ", exception);
                result = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error");
            }
        }
        return result;
    }

    /**
     * Manejador de excepciones de la API
     * @param exception Excepcion lanzada por la aplicacion
     * @return Vista en formato JSON con el codigo y el mensaje de la excepcion
     * @throws Exception
     */
    @ExceptionHandler({ApiException.class, ErrorException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(final Exception exception) {
        ModelAndView result = null;
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        if (exception instanceof ApiException) {
            ApiException apiExc = (ApiException) exception;
            if (apiExc.getCode() == CodeErrorType.PERMISSION_DENIED.getCode()) {
                //REC-722
                result = new ModelAndView(new RedirectView("http://www.recomendar.com"));
                LOGGER.warn("[Permission denied]");
            } else {
                responseDTO.setCode(apiExc.getCode());
            }
        } else if (exception instanceof ErrorException) {
            ErrorException errorExc = (ErrorException) exception;
            responseDTO.setCode(errorExc.getCode());
        }

        if (result == null) {
            result = new ModelAndView(new MappingJacksonJsonView(), "errors", responseDTO);
        }
        return result;
    }
}
