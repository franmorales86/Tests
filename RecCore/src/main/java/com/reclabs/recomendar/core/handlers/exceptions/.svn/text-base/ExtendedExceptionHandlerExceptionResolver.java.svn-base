/**
 * Project: RecCore
 * Created by: fjmorales at 03/12/2012 13:53:04
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.handlers.exceptions;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Se extiende el resolutor de excepciones generico para utilizar el resolutor propio
 * @author fjmorales
 */
public class ExtendedExceptionHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {

    private Object handler;

    private ExceptionHandlerMethodResolver methodResolver;

    /**
     * Provide a handler with @{@link ExceptionHandler} methods.
     * @param handler
     */
    public void setExceptionHandler(final Object handler) {
        this.handler = handler;
        this.methodResolver = new ExceptionHandlerMethodResolver(handler.getClass());
    }

    @Override
    protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod, final Exception exception) {
        ServletInvocableHandlerMethod result = super.getExceptionHandlerMethod(handlerMethod, exception);
        if (result != null) {
            return result;
        }
        Method method = this.methodResolver.resolveMethod(exception);
        return (method != null) ? new ServletInvocableHandlerMethod(this.handler, method) : null;
    }


    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
        if (exception instanceof InsufficientAuthenticationException) {
            //ignore
            return null;
        } else {
            return super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
        }
    }
}
