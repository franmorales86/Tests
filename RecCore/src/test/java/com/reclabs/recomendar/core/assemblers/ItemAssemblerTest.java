/**
 * Project: RecCore
 * Created by: raulanatol at 11/03/2013 15:01:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.assemblers;

import org.junit.Test;

/**
 * @author raulanatol
 * @see ItemAssembler
 */
public class ItemAssemblerTest {

    /**
     * @see ItemAssembler#fromAfiliationItemDTO(com.reclabs.recomendar.objects.AfiliationItemDTO)
     */
    @Test
    public void fromAfiliationItemDTOTest() {
        // AfiliationItemDTO item = new AfiliationItemDTO();
        // String id = "id";
        // String isbn = "isbn";
        // String ean = "ean";
        // String imageUrl = "imageURL";
        // String name = "name";
        // BigDecimal price = new BigDecimal(10);
        // String ItemUrl = "ItemUrl";
        // ProgramDTO program = new ProgramDTO();
        // List<TagDTO> tags = new ArrayList<>();
        // List<Tag> expectedTags = new ArrayList<>();
        // item.setEan(ean);
        // item.setId(id);
        // item.setImageUrl(imageUrl);
        // item.setIsbn(isbn);
        // item.setName(name);
        // item.setPrice(price);
        // item.setItemUrl(ItemUrl);
        // item.setProgram(program);
        // item.setTags(tags);
        // item.setDescription("description");
        // Item expected = new Item();
        // expected.setEan(ean);
        // expected.setId(id);
        // expected.setImageUrl(imageUrl);
        // expected.setIsbn(isbn);
        // expected.setName(name);
        // expected.setTags(expectedTags);
        // expected.setDescription("description");
        //
        // assertThat(ItemAssembler.fromAfiliationItemDTO(item), is(expected));
    }

    /**
     * @see ItemAssembler#ItemProgramFromAfiliationItemDTO(AfiliationItemDTO, Program)
     */
    @Test
    public void ItemProgramFromAfiliationItemDTOSimpleData() {
        // AfiliationItemDTO afiliationItemDTO = new AfiliationItemDTO();
        // BigDecimal price = new BigDecimal(12);
        // afiliationItemDTO.setPrice(price);
        // Program program = new Program();
        // ItemProgram actual = ItemAssembler.ItemProgramFromAfiliationItemDTO(afiliationItemDTO, program);
        //
        // ItemProgram expected = new ItemProgram();
        // expected.setProgram(program);
        // expected.setPrice(price);
        // expected.setCreatedDate(DateHelper.getCurrentDate());
        // assertThat(actual, is(expected));
    }
}
