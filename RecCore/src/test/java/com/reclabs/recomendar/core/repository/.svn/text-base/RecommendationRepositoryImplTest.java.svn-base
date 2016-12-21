/**
 * Project: RecCore
 * Created by: raulanatol at 20/06/13 10:20
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.model.documents.Recommendation;
import com.reclabs.recomendar.model.documents.users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * @author raulanatol
 * @see RecommendationRepositoryImpl
 */
public class RecommendationRepositoryImplTest extends RepositoryTest {
    private static final String VALID_ITEM_ID_1 = "valid_item_1";
    private static final String VALID_ITEM_ID_2 = "valid_item_2";
    private static final String VALID_USER_ID_1 = "valid_user_1";
    private static final String VALID_USER_ID_2 = "valid_user_2";


    private RecommendationRepositoryImpl recommendationRepository;
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp() {
        mongoTemplate = new MongoTemplate(mongo, DB_NAME);
        recommendationRepository = new RecommendationRepositoryImpl(this.mongoTemplate);

        //Create data.
        Recommendation insert1 = new Recommendation();
        insert1.setItemId(VALID_ITEM_ID_1);
        insert1.setUserId(VALID_USER_ID_1);
        recommendationRepository.save(insert1);

        Recommendation insert2 = new Recommendation();
        insert2.setItemId(VALID_ITEM_ID_2);
        insert2.setUserId(VALID_USER_ID_1);
        recommendationRepository.save(insert2);

        Recommendation insert3 = new Recommendation();
        insert3.setItemId(VALID_ITEM_ID_2);
        insert3.setUserId(VALID_USER_ID_2);
        recommendationRepository.save(insert3);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.dropCollection(Recommendation.class);
    }


    @Test
    public void findItemIdByUserIdWithInvalidUserId() {
        List<String> ids = recommendationRepository.findItemIdByUserId(null);
        assertTrue(CollectionHelper.isEmpty(ids));
    }

    /**
     * @see RecommendationRepositoryImpl#findItemIdByUserId(String)
     */
    @Test
    public void findItemIdByUserIdWithValidUserIds() {
        String userId = "user1";

        Recommendation recommendation1 = new Recommendation();
        recommendation1.setUserId(userId);
        recommendation1.setItemId("item1");
        Recommendation recommendation2 = new Recommendation();
        recommendation2.setUserId("user2");
        recommendation2.setItemId("item1");
        Recommendation recommendation3 = new Recommendation();
        recommendation3.setUserId("user3");
        recommendation3.setItemId("item1");
        Recommendation recommendation4 = new Recommendation();
        recommendation4.setUserId(userId);
        recommendation4.setItemId("item2");
        Recommendation recommendation5 = new Recommendation();
        recommendation5.setUserId("user5");
        recommendation5.setItemId("item2");

        recommendationRepository.save(recommendation1);
        recommendationRepository.save(recommendation2);
        recommendationRepository.save(recommendation3);
        recommendationRepository.save(recommendation4);
        recommendationRepository.save(recommendation5);

        List<String> result = recommendationRepository.findItemIdByUserId(userId);

        assertTrue("The size should be 2, but was " + result.size(), result.size() == 2);
        assertTrue("The itemId 'item1' was not found", result.contains(recommendation1.getItemId()));
        assertTrue("The itemId 'item2' was not found", result.contains(recommendation4.getItemId()));
    }

    @Test
    public void findByUserIdAndItemIdWithInvalidUserId() {
        Recommendation recommendation = recommendationRepository.findByUserIdAndItemId(null, VALID_ITEM_ID_1);
        assertNull(recommendation);
    }

    @Test
    public void findByUserIdAndItemIdWithInvalidItemId() {
        Recommendation recommendation = recommendationRepository.findByUserIdAndItemId(VALID_USER_ID_1, null);
        assertNull(recommendation);
    }

    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndNotResult() {
        Recommendation recommendation = recommendationRepository.findByUserIdAndItemId(VALID_USER_ID_2, VALID_ITEM_ID_1);
        assertNull(recommendation);
    }

    @Test
    public void findByUserIdAndItemIdWithValidItemIdAndWithResult() {
        Recommendation recommendation = recommendationRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertNotNull(recommendation);
    }

    /**
     * @see RecommendationRepositoryImpl#deleteByUserIdAndItemId(String, String)
     */
    @Test
    public void deleteByUserIdAndItemIdTest() {
        recommendationRepository.deleteByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1);
        assertTrue(recommendationRepository.findAll().size() == 2);
        assertNull(recommendationRepository.findByUserIdAndItemId(VALID_USER_ID_1, VALID_ITEM_ID_1));
    }

    /**
     * @see RecommendationRepositoryImpl#findRecommendationsByUserFromDate(String, java.util.Date)
     */
    @Test
    public void findRecommendationsByUserFromDate() {
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setUserId("user1");
        recommendation1.setCreatedDate(DateHelper.getFixedDate(1989, 1, 11));
        Recommendation recommendation2 = new Recommendation();
        recommendation2.setUserId("user2");
        recommendation2.setCreatedDate(DateHelper.getFixedDate(1995, 2, 24));
        Recommendation recommendation3 = new Recommendation();
        recommendation3.setUserId("user3");
        recommendation3.setCreatedDate(DateHelper.getFixedDate(2001, 9, 11));
        Recommendation recommendation4 = new Recommendation();
        recommendation4.setUserId("user1");
        recommendation4.setCreatedDate(DateHelper.getFixedDate(2004, 3, 11));
        Recommendation recommendation5 = new Recommendation();
        recommendation5.setUserId("user5");
        recommendation5.setCreatedDate(DateHelper.getFixedDate(2008, 8, 20));
        Recommendation recommendation6 = new Recommendation();
        recommendation6.setUserId("user1");
        recommendation6.setCreatedDate(DateHelper.getFixedDate(2013, 9, 27));

        recommendationRepository.save(recommendation1);
        recommendationRepository.save(recommendation2);
        recommendationRepository.save(recommendation3);
        recommendationRepository.save(recommendation4);
        recommendationRepository.save(recommendation5);
        recommendationRepository.save(recommendation6);

        List<Recommendation> result = recommendationRepository.findRecommendationsByUserFromDate("user1", DateHelper.getFixedDate(1996, 6, 6));

        assertTrue("The size should be 2, but was " + result.size(), result.size() == 2);
        assertTrue("The recommendation 4 was not found", result.contains(recommendation4));
        assertTrue("The recommendation 6 was not found", result.contains(recommendation6));
    }


}
