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
import com.reclabs.recomendar.model.documents.items.ItemHave;
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
 * @see com.reclabs.recomendar.core.repository.items.ItemHaveRepositoryImpl
 */
public class ItemHaveRepositoryImplTest extends RepositoryTest {
    private static final String VALID_ITEM_ID_1 = "valid_item_1";
    private static final String VALID_ITEM_ID_2 = "valid_item_2";
    private static final String VALID_USER_ID_1 = "valid_user_1";
    private static final String VALID_USER_ID_2 = "valid_user_2";


    ItemHaveRepositoryImpl itemHaveRepository;
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        itemHaveRepository = new ItemHaveRepositoryImpl(this.mongoTemplate);

        //Create data.
        ItemHave insert1 = new ItemHave();
        insert1.setItemId(VALID_ITEM_ID_1);
        insert1.setUserId(VALID_USER_ID_1);
        insert1.setCreatedDate(new Date());
        itemHaveRepository.save(insert1);

        ItemHave insert2 = new ItemHave();
        insert2.setItemId(VALID_ITEM_ID_2);
        insert2.setUserId(VALID_USER_ID_1);
        insert2.setCreatedDate(new Date());
        itemHaveRepository.save(insert2);

        ItemHave insert3 = new ItemHave();
        insert3.setItemId(VALID_ITEM_ID_2);
        insert3.setUserId(VALID_USER_ID_2);
        insert3.setCreatedDate(new Date());
        itemHaveRepository.save(insert3);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(ItemHave.class);
    }

    /**
     * @see ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithInvalidUserId() {
        ItemHave itemHave = itemHaveRepository.findByUserIdAndItemId(null, VALID_ITEM_ID_1);
        assertNull(itemHave);
    }

    /**
     * @see ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithInvalidItemId() {
        ItemHave itemHave = itemHaveRepository.findByUserIdAndItemId(VALID_USER_ID_1, null);
        assertNull(itemHave);
    }

    /**
     * @see ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndNotResult() {
        ItemHave itemHave = itemHaveRepository.findByUserIdAndItemId(VALID_USER_ID_2, VALID_ITEM_ID_1);
        assertNull(itemHave);
    }

    /**
     * @see ItemHaveRepositoryImpl#findByUserIdAndItemId(String, String)
     */
    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndWithResult() {
        ItemHave itemHave = itemHaveRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertNotNull(itemHave);
    }

    /**
     * @see ItemHaveRepositoryImpl#deleteByUserIdAndItemId(String, String)
     */
    @Test
    public void deleteByUserIdAndItemIdTest() {
        itemHaveRepository.deleteByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertTrue(itemHaveRepository.findAll().size() == 2);
        assertNull(itemHaveRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1));
    }
}
