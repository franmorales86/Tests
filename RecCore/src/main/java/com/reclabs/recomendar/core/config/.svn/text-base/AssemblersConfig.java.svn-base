/**
 * Project: RecCore
 * Created by: raulanatol at 23/03/2013 15:56:52
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.config;

import com.reclabs.recomendar.core.assemblers.*;
import com.reclabs.recomendar.core.assemblers.categories.OPTProductTagCategoryAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creación de los beans de ensamblado
 * @author raulanatol
 */
@Configuration
public class AssemblersConfig {
    /**
     * @return El assembler de usuarios.
     */
    @Bean
    public UserAssembler userAssembler() {
        return new UserAssembler();
    }

    /**
     * @return El socialUserAssembler
     */
    @Bean
    public SocialUserAssembler socialUserAssembler() {
        return new SocialUserAssembler();
    }

    /**
     * @return El assembler de categorías.
     */
    @Bean
    public CategoryAssembler categoryAssembler() {
        return new CategoryAssembler();
    }

    /**
     * @return El assembler para las etiquetas
     */
    @Bean
    public TagAssembler tagAssembler() {
        return new TagAssembler();
    }

    /**
     * @return El assembler para las etiquetas de una categoria
     */
    @Bean
    public OPTProductTagCategoryAssembler optProductTagCategoryAssembler() {
        return new OPTProductTagCategoryAssembler();
    }

    /**
     * @return El assembler para las comunidades
     */
    @Bean
    public CommunityAssembler communityAssembler() {
        return new CommunityAssembler();
    }

}
