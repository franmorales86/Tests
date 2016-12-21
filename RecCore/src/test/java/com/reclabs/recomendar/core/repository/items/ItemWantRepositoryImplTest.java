/**
 * Project: RecCore
 * Created by: raulanatol at 20/06/13 10:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.items;

import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.items.ItemWant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

import static com.mongodb.util.MyAsserts.assertTrue;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;


/**
 * @author raulanatol
 * @see com.reclabs.recomendar.core.repository.items.ItemWantRepositoryImpl
 */
public class ItemWantRepositoryImplTest extends RepositoryTest {
    private static final String VALID_ITEM_ID_1 = "valid_item_1";
    private static final String VALID_ITEM_ID_2 = "valid_item_2";
    private static final String VALID_USER_ID_1 = "valid_user_1";
    private static final String VALID_USER_ID_2 = "valid_user_2";


    ItemWantRepositoryImpl itemWantRepository;
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        itemWantRepository = new ItemWantRepositoryImpl(this.mongoTemplate);

        //Create data.
        ItemWant insert1 = new ItemWant();
        insert1.setItemId(VALID_ITEM_ID_1);
        insert1.setUserId(VALID_USER_ID_1);
        insert1.setCreatedDate(new Date());
        itemWantRepository.save(insert1);

        ItemWant insert2 = new ItemWant();
        insert2.setItemId(VALID_ITEM_ID_2);
        insert2.setUserId(VALID_USER_ID_1);
        insert2.setCreatedDate(new Date());
        itemWantRepository.save(insert2);

        ItemWant insert3 = new ItemWant();
        insert3.setItemId(VALID_ITEM_ID_2);
        insert3.setUserId(VALID_USER_ID_2);
        insert3.setCreatedDate(new Date());
        itemWantRepository.save(insert3);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(ItemWant.class);
    }

    /**
     * @see com.reclabs.recomendar.core.repository.items.ItemWantRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithInvalidUserId() {
        ItemWant itemWant = itemWantRepository.findByUserIdAndItemId(null, VALID_ITEM_ID_1);
        assertNull(itemWant);
    }

    /**
     * @see com.reclabs.recomendar.core.repository.items.ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithInvalidItemId() {
        ItemWant itemWant = itemWantRepository.findByUserIdAndItemId(VALID_USER_ID_1, null);
        assertNull(itemWant);
    }

    /**
     * @see com.reclabs.recomendar.core.repository.items.ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndNotResult() {
        ItemWant itemWant = itemWantRepository.findByUserIdAndItemId(VALID_USER_ID_2, VALID_ITEM_ID_1);
        assertNull(itemWant);
    }

    /**
     * @see com.reclabs.recomendar.core.repository.items.ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndWithResult() {
        ItemWant itemWant = itemWantRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertNotNull(itemWant);
    }

    /**
     * @see com.reclabs.recomendar.core.repository.items.ItemHaveRepositoryImpl#deleteByUserIdAndItemId(String, String)
     */
    @Test
    public void deleteByUserIdAndItemIdTest() {
        itemWantRepository.deleteByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertTrue(itemWantRepository.findAll().size() == 2);
        assertNull(itemWantRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1));
    }
}
