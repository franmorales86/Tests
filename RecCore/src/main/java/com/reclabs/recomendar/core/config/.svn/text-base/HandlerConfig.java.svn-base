/**
 * Project: RecCore
 * Created by: fjmorales at 03/12/2012 13:51:30
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config;

import com.mongodb.MongoException;
import com.reclabs.recomendar.core.handlers.exceptions.ExtendedExceptionHandlerExceptionResolver;
import com.reclabs.recomendar.core.handlers.exceptions.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Configuration of exception manager of the API
 * @author fjmorales
 */
@Configuration
public class HandlerConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureHandlerExceptionResolvers(final List<HandlerExceptionResolver> exceptionResolvers) {
        ExtendedExceptionHandlerExceptionResolver customResolver = new ExtendedExceptionHandlerExceptionResolver();
        customResolver.setExceptionHandler(new GlobalExceptionHandler(getExcludeExceptions(), getInvolveExceptions()));
        customResolver.setMessageConverters(getMessageConverters());
        customResolver.afterPropertiesSet();
        exceptionResolvers.add(customResolver);
    }

    /**
     * List of all exception that has been excluded for the resolver
     * @return The excluded exceptions
     */
    private Collection<Class<? extends RuntimeException>> getExcludeExceptions() {
        Collection<Class<? extends RuntimeException>> result = new ArrayList<>();
        result.add(InsufficientAuthenticationException.class);
        result.add(AuthenticationException.class);
        return result;
    }

    /**
     * @return List of involve exceptions.
     */
    private Collection<Class<? extends RuntimeException>> getInvolveExceptions() {
        Collection<Class<? extends RuntimeException>> result = new ArrayList<>();
        result.add(MongoException.class);
        return result;
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return super.handlerExceptionResolver();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
