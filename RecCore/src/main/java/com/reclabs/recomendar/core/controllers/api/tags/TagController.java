/**
 * Project: RecCore
 * Created by: fjmorales at 12/03/2013 18:24:06
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.controllers.api.tags;

import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.core.assemblers.TagAssembler;
import com.reclabs.recomendar.esdriver.ESDriver;
import com.reclabs.recomendar.esdriver.actions.ESFind;
import com.reclabs.recomendar.esdriver.actions.queries.ESBoolQuery;
import com.reclabs.recomendar.esdriver.actions.queries.ESQueryStringQuery;
import com.reclabs.recomendar.esdriver.actions.queries.generics.ESWildcardQuery;
import com.reclabs.recomendar.esdriver.index.IndexType;
import com.reclabs.recomendar.esdriver.types.BoolQueryField;
import com.reclabs.recomendar.esdriver.types.BooldFieldType;
import com.reclabs.recomendar.model.documents.Tag;
import com.reclabs.recomendar.model.services.TagService;
import com.reclabs.recomendar.objects.ResponseDTO;
import com.reclabs.recomendar.objects.TagDTO;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author fjmorales
 */
@Controller
@RequestMapping(value = "/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    protected TagAssembler tagAssembler;

    @Autowired
    private ESDriver esDriver;

    /**
     * Metodo que se encarga de devolver todas las etiquetas disponibles en la base de datos
     * @return Listado de tags existentes
     */
    @ResponseBody
    @RequestMapping(value = "/find_all_tags", method = RequestMethod.GET)
    public List<TagDTO> findAllTags() {
        return tagService.findAllTags();
    }

    /**
     * Metodo que se encarga de buscar si una etiqueta existe a partir del nombre y sino existe la crea
     * @param tag Datos del tag a buscar
     * @return Datos de las etiquetas
     */
    @ResponseBody
    @RequestMapping(value = "/add_tag", method = RequestMethod.POST)
    public List<TagDTO> createOrFindTag(@RequestBody final TagDTO tag) {
        ParameterHelper.assertNull(tag);
        ParameterHelper.assertEmpty(tag.getName());
        throw new NotImplementedException();
        // return tagAssembler.toRight(tagService.findOrInsertTagsByName(Arrays.asList(tag.getName())));
    }

    /**
     * Realizamos la búsqueda de los tags.
     * @param query El patrón de búsqueda.
     * @return El listado de nombres de los tags.
     */
    @ResponseBody
    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public List<String> searchSimpleTag(@PathVariable String query) {
        ParameterHelper.assertEmpty(query);
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESWildcardQuery("name", query + "*"), BooldFieldType.MUST));
        ESFind findQuery = new ESFind(IndexType.TAG_INDEX, Arrays.asList("name"), new ESBoolQuery(where));
        return (List<String>) esDriver.searchSimpleFieldByQueryList(findQuery, String.class, "name");
    }

    /**
     * Search tags by query, based on definitions of parameters. In all cases, paginated the results list with page and size parameters
     * @param query Pattern of search
     * @param page Page of the paginated
     * @param size Number of item per page
     * @return Result list of tags
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Collection<Tag> searchTag(@RequestParam(value = "q", required = false) String query,
                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "20") Integer size) {
        ParameterHelper.assertEmpty(query);
        List<BoolQueryField> where = new ArrayList<>();
        where.add(new BoolQueryField(new ESQueryStringQuery("name", "*" + query + "*"), BooldFieldType.SHOULD));
        ESFind esQuery = new ESFind(IndexType.TAG_INDEX, new ESBoolQuery(where), page, size);
        return esDriver.searchByQueryList(esQuery, Tag.class);
    }

    /**
     * Delete the tag with id passed from parameters.
     * @param tagId Id of the tag
     * @return ResponseDTO.OK in case of success exception otherwise
     */
    @ResponseBody
    @RequestMapping(value = "/{tagId}", method = RequestMethod.DELETE)
    public ResponseDTO deleteTag(@PathVariable String tagId) {
        ParameterHelper.assertEmpty(tagId);
        tagService.deleteTag(tagId);
        return ResponseDTO.OK;
    }

    /**
     * @param tagId Id of the tag to rename
     * @param newName New name of the tag
     * @return ResponseDTO.OK it's all ok
     * @link http://hugo.publicfy.com:3033/pages/viewpage.action?pageId=3834167
     */
    @ResponseBody
    @RequestMapping(value = "/{tagId}/{newName}", method = RequestMethod.PUT)
    public ResponseDTO renameTag(@PathVariable String tagId, @PathVariable String newName) {
        ParameterHelper.assertAnyEmpty(tagId, newName);
        tagService.renameTag(tagId, newName);
        return ResponseDTO.OK;
    }
}
