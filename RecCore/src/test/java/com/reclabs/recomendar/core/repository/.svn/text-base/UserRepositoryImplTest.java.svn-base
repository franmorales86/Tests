/**
 * Project: RecCore
 * Created by: fjmorales at 18/11/2012 18:19:23
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.DateHelper;
import com.reclabs.recomendar.common.helpers.types.NullHelper;
import com.reclabs.recomendar.common.types.DatePrecisionType;
import com.reclabs.recomendar.core.repository.generic.RepositoryTest;
import com.reclabs.recomendar.core.repository.users.UserRepositoryImpl;
import com.reclabs.recomendar.model.documents.users.SocialUser;
import com.reclabs.recomendar.model.documents.users.User;
import com.reclabs.recomendar.model.types.SexType;
import com.reclabs.recomendar.model.types.SocialUserProviderId;
import com.reclabs.recomendar.model.types.user.UserState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author fjmorales
 */
public class UserRepositoryImplTest extends RepositoryTest {

    private UserRepositoryImpl repoImpl;
    private MongoTemplate template;

    /**
     * Inicializacion del repositorio y la base de datos emulada
     */
    @Before
    public void setUp() {
        this.template = new MongoTemplate(mongo, DB_NAME);
        this.repoImpl = new UserRepositoryImpl(this.template);
    }

    /**
     * Eliminacion de los elementos insertados en el base de datos emulada
     */
    @After
    public void tearDown() {
        this.template.dropCollection(User.class);
        this.template.dropCollection(Collection.class);
    }

    private User initialize() {
        User user = new User();
        user.setName("testUserName");
        user.setEmail("test@test.com");
        user.setBirthday(DateHelper.getCurrentDate(DatePrecisionType.MILLISECOND));
        user.setLastname1("lastname");
        user.setSex(SexType.MALE);
        user.setUsername("testUsername");
        user.setState(UserState.ACTIVE);
        user.setAvatarURL("url");
        return user;
    }

    /**
     * Create a simpleUser with 2 socialUser
     * @return The user
     */
    private User getUserWithSocialUser() {
        User result = initialize();
        List<SocialUser> socialUsers = new ArrayList<>();
        SocialUser item1 = new SocialUser();
        item1.setUserId("uno1");
        item1.setSecret("secret");
        item1.setRefreshToken("refreshToken");
        item1.setProviderId("twitter");
        item1.setProviderUserId("user1");
        socialUsers.add(item1);

        SocialUser item2 = new SocialUser();
        item2.setUserId("uno2");
        item2.setSecret("secret2");
        item2.setRefreshToken("refreshToken2");
        item2.setProviderUserId("user2");
        item2.setProviderId("facebook");
        socialUsers.add(item2);
        result.setSocialUsers(socialUsers);
        return result;
    }

    /**
     * Test de las operaciones Insert/Update del document User
     */
    @Test
    public void testCreateUpdateUser() {
        // Test de insert en BBDD
        User user = initialize();
        this.repoImpl.save(user);

        List<User> userList = this.template.findAll(User.class);
        int samplesInCollection = userList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        User userBBDD = userList.get(0);
        assertThat(userBBDD, is(user));

        // Test de update en BBDD
        user.setName("testName2");
        this.repoImpl.save(user);

        userList = this.template.findAll(User.class);
        samplesInCollection = userList.size();

        assertEquals("Only 1 Sample should exist collection, but there are " + samplesInCollection, 1, samplesInCollection);
        userBBDD = userList.get(0);
        assertThat(userBBDD, is(user));
    }

    /**
     * Test de la operacion Delete del document User
     */
    @Test
    public void testDeleteUser() {
        User user = initialize();
        this.template.insert(user);

        // Test de delete by Entity
        this.repoImpl.delete(user);
        List<User> userList = this.template.findAll(User.class);
        int samplesInCollection = userList.size();
        assertEquals("No deberia existir ningun producto, pero hay " + samplesInCollection, 0, samplesInCollection);

        // Test de delete by Id
        this.template.insert(user);
        this.repoImpl.delete(user.getId());
        userList = this.template.findAll(User.class);
        samplesInCollection = userList.size();
        assertEquals("No deberia existir ninguna coleccion, pero hay " + samplesInCollection, 0, samplesInCollection);

        this.template.insert(user);
        userList = this.template.findAll(User.class);
        assertThat(userList.size(), is(1));
    }

    /**
     * @see UserRepositoryImpl#findOne(java.io.Serializable)
     */
    @Test
    public void testFindById() {
        User user = initialize();
        User user2 = initialize();
        user2.setName("testName2");
        this.repoImpl.save(user);

        try {
            this.repoImpl.save(user2);
        } catch (DuplicateKeyException e) {
            System.out.println("exc!" + e);
        }

        List<User> findAll = this.template.findAll(User.class);
        System.out.println(findAll);

        User userBBDD = this.repoImpl.findOne(user.getId());
        assertThat(userBBDD, is(user));

        userBBDD = this.repoImpl.findOne(user2.getId());
        assertThat(userBBDD, is(user2));

        userBBDD = this.repoImpl.findOne("tag3");
        assertNull(userBBDD);
    }

    /**
     * @see UserRepositoryImpl#findByExactlyName(String)
     */
    @Test
    public void testFindByExactlyName() {
        User user = initialize();
        User user2 = initialize();
        user2.setName("testUserName2");
        this.template.insert(user);
        this.template.insert(user2);

        List<User> userList = this.repoImpl.findByExactlyName("TESTUSERNAME");
        int samplesInCollection = userList.size();
        assertEquals("Deberian existir 1 etiqueta coincidentes, pero hay " + samplesInCollection, 1, samplesInCollection);

        userList = this.repoImpl.findByExactlyName("testUserName2");
        samplesInCollection = userList.size();
        assertEquals("Deberian existir 1 etiqueta coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        userList = this.repoImpl.findByExactlyName("est");
        samplesInCollection = userList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see UserRepositoryImpl#findByName(String)
     */
    @Test
    public void testFindByName() {
        User user = initialize();
        User user2 = initialize();
        user2.setName("testUserName status");
        this.template.insert(user);
        this.template.insert(user2);

        List<User> userList = this.repoImpl.findByName("ESTU");
        int samplesInCollection = userList.size();
        assertEquals("Deberian existir 2 etiquetas coincidentes, pero hay " + samplesInCollection, 2, samplesInCollection);

        userList = this.repoImpl.findByName("ame stat");
        samplesInCollection = userList.size();
        assertEquals("Deberian existir 1 etiqueta coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        userList = this.repoImpl.findByName("testN");
        samplesInCollection = userList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);
    }

    /**
     * @see UserRepositoryImpl#findByUsername(String)
     */
    @Test
    public void testFindByUsername() {
        User user = initialize();
        User user2 = initialize();
        user2.setUsername("testUsername2");
        this.template.insert(user);
        this.template.insert(user2);

        List<User> userList = this.repoImpl.findByUsername("TESTUSERNAME");
        int samplesInCollection = userList.size();
        assertEquals("Deberian existir 1 etiqueta coincidentes, pero hay " + samplesInCollection, 1, samplesInCollection);

        userList = this.repoImpl.findByUsername("testUserName2");
        samplesInCollection = userList.size();
        assertEquals("Deberian existir 1 etiqueta coincidente, pero hay " + samplesInCollection, 1, samplesInCollection);

        userList = this.repoImpl.findByUsername("est");
        samplesInCollection = userList.size();
        assertEquals("No deberian existir ninguna etiqueta coincidente, pero hay " + samplesInCollection, 0, samplesInCollection);

        List<User> users = repoImpl.findByUsername("noEncontrado");
        assertTrue(CollectionHelper.isEmpty(users));
    }

    /**
     * Test de los campos de friends
     */
    @Test
    public void testFriends() {
        User user = initialize();
        User user2 = initialize();
        User user3 = initialize();
        User user4 = initialize();
        user2.setUsername("testUsername2");
        user3.setUsername("testUsername3");
        user4.setUsername("testUsername4");

        List<User> friends = new ArrayList<>();
        friends.add(user2);
        friends.add(user3);
        user.setFriends(friends);

        this.template.insert(user2);
        this.template.insert(user3);
        this.template.insert(user4);
        this.template.insert(user);

        User userBBDD = this.repoImpl.findOne(user.getId());
        assertThat(userBBDD, is(user));
    }

    /**
     * Remove a socialUser for an User that exists.
     */
    @Test
    public void removeSocialUser() {
        User user = getUserWithSocialUser();
        template.insert(user);

        User userBBDD = repoImpl.findOne(user.getId());
        assertThat(userBBDD.getName(), is(user.getName()));
        assertThat(userBBDD.getSocialUsers().size(), is(2));

        repoImpl.removeSocialUser(userBBDD.getSocialUsers().get(0));
        User userBBDDWithoutSocialUser = repoImpl.findOne(user.getId());
        assertThat(userBBDDWithoutSocialUser.getSocialUsers().size(), is(1));
    }

    /**
     * @see UserRepositoryImpl#removeSocialUser(SocialUser)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void removeSocialUserWithSuffleAndInvalidSocialUsers() {
        String providerId1 = "FACEBOOK";
        String providerId2 = "TWITTER";

        String providerUserId1 = "123456ASDF";
        String providerUserId2 = "123456BLAS";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId(providerId1);
        socialUser1.setProviderUserId(providerUserId2);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId(providerId2);
        socialUser2.setProviderUserId(providerUserId1);

        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId(providerId1);
        socialUser3.setProviderUserId(providerUserId1);

        User user = new User();
        user.setId("user");
        user.setSocialUsers(Arrays.asList(socialUser1, socialUser2));

        repoImpl.save(user);

        repoImpl.removeSocialUser(socialUser3);

        User result = repoImpl.findOne("user");

        assertTrue("The user's social users size shuold be 2, but was " + result.getSocialUsers().size(), result.getSocialUsers().size() == 2);
    }

    @Test
    public void removeSocialUserWithValidSocialUser() {
        String providerId1 = "FACEBOOK";
        String providerId2 = "TWITTER";

        String providerUserId1 = "123456ASDF";
        String providerUserId2 = "123456BLAS";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId(providerId1);
        socialUser1.setProviderUserId(providerUserId2);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId(providerId2);
        socialUser2.setProviderUserId(providerUserId1);

        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId(providerId1);
        socialUser3.setProviderUserId(providerUserId1);

        User user = new User();
        user.setId("user");
        user.setSocialUsers(Arrays.asList(socialUser1, socialUser2, socialUser3));

        repoImpl.save(user);

        repoImpl.removeSocialUser(socialUser3);

        User result = repoImpl.findOne("user");

        assertTrue("The user's social-users size should be 2, but was " + result.getSocialUsers().size(), result.getSocialUsers().size() == 2);
    }

    /**
     * @see UserRepositoryImpl#existsUserByUsername(String)
     */
    @Test
    public void existsUserByUsernameTest() {
        User user = initialize();
        User user2 = initialize();
        user2.setName("testUserName status");
        user2.setUsername("testUsername2");
        this.template.insert(user);
        this.template.insert(user2);

        assertTrue("Deberian existir dos usuarios coincidentes, sin embargo no hay ningún usuario que coincida.", this.repoImpl.existsUserByUsername("testUsername"));
        assertTrue("Deberia existir 1 usuario coincidente, sin embargo no hay ningún usuario que coincida.", this.repoImpl.existsUserByUsername("testUsername2"));
        assertFalse("No deberia existir ningún usuario coincidente pero existe 1 usuario coincidente.", this.repoImpl.existsUserByUsername("testN"));
    }

    @Test
    public void verifyChangeOfAutoSharedParameter() throws Exception {
        String userId = "USER_1";
        List<SocialUser> socialUsers = new ArrayList<>();

        SocialUser facebookSocialUser = new SocialUser();
        facebookSocialUser.setProviderId(SocialUserProviderId.FACEBOOK.getProviderId());

        socialUsers.add(facebookSocialUser);

        User user = new User();
        user.setId(userId);
        user.setSocialUsers(socialUsers);

        template.save(user);
        User savedUser = template.findById(userId, User.class);

        assertNull(savedUser.getSocialUsers().get(0).getAutoShared());
        savedUser.getSocialUsers().get(0).setAutoShared(true);
        template.save(savedUser);

        User modifiedUser = template.findById(userId, User.class);
        assertTrue(modifiedUser.getSocialUsers().get(0).getAutoShared());
    }

    @Test
    public void findUserIdsByProviderIdAndProviderUserIdTest() {
        String userId1 = "HA5W3LL";
        String userId2 = "N3HAL3M";
        String userId3 = "5ANDY8R1D6E";
        String userId4 = "1V18R1D63";

        String providerId = "C0RT3XA9";
        String providerId2 = "C0RT3XA7";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId("facebook");
        socialUser1.setProviderUserId(providerId);

        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderUserId("twitter");

        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId("facebook");
        socialUser3.setProviderUserId(providerId2);

        User user1 = new User();
        user1.setId(userId1);
        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2));

        User user2 = new User();
        user2.setId(userId2);
        user2.setSocialUsers(Arrays.asList(socialUser1));

        User user3 = new User();
        user3.setId(userId3);
        user3.setSocialUsers(Arrays.asList(socialUser2, socialUser3));

        User user4 = new User();
        user4.setId(userId4);
        user4.setSocialUsers(Arrays.asList(socialUser2, socialUser1));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);

        List<String> usersResult = repoImpl.findUserIdsByProviderIdAndProviderUserId("facebook", providerId);

        assertTrue("Should be 3, but it was " + usersResult.size(), usersResult.size() == 3);
    }

    /**
     * @see UserRepositoryImpl#findUserIdsByProviderIdAndProviderUserId(String, String)
     */
    @Test
    public void findUserIdsByProviderIdAndProviderUserIdsWithValidUser() {
        String providerId1 = "FACEBOOK";
        String providerId2 = "TWITTER";

        //Valid
        String providerUserId1 = "ZXCVBN";
        String providerUserId2 = "ASDFGH";
        // Not valid
        String providerUserId3 = "123456";
        String providerUserId4 = "QWE123";
        String providerUserId5 = "MNBVCX";
        String providerUserId6 = "POIUYT";

        // Valid
        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId(providerId1);
        socialUser1.setProviderUserId(providerUserId1);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId(providerId1);
        socialUser2.setProviderUserId(providerUserId2);
        // Not valid
        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId(providerId2);
        socialUser3.setProviderUserId(providerUserId3);
        SocialUser socialUser4 = new SocialUser();
        socialUser4.setProviderId(providerId2);
        socialUser4.setProviderUserId(providerUserId4);
        SocialUser socialUser5 = new SocialUser();
        socialUser5.setProviderId(providerId1);
        socialUser5.setProviderUserId(providerUserId5);
        SocialUser socialUser6 = new SocialUser();
        socialUser6.setProviderId(providerId1);
        socialUser6.setProviderUserId(providerUserId6);
        SocialUser socialUser7 = new SocialUser();
        socialUser7.setProviderId(providerId2);
        socialUser7.setProviderUserId(providerUserId1);
        SocialUser socialUser8 = new SocialUser();
        socialUser8.setProviderId(providerId2);
        socialUser8.setProviderUserId(providerUserId2);

        User user1 = new User();
        user1.setId("user1");
        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2));
        User user2 = new User();
        user2.setId("user2");
        user2.setSocialUsers(Arrays.asList(socialUser1, socialUser5));
        User user3 = new User();
        user3.setId("user3");
        user3.setSocialUsers(Arrays.asList(socialUser3, socialUser4));
        User user4 = new User();
        user4.setId("user4");
        user4.setSocialUsers(Arrays.asList(socialUser7, socialUser8));
        User user5 = new User();
        user5.setId("user5");
        user5.setSocialUsers(Arrays.asList(socialUser1));
        User user6 = new User();
        user6.setId("user6");
        user6.setSocialUsers(Arrays.asList(socialUser2));
        User user7 = new User();
        user7.setId("user7");
        user7.setSocialUsers(Arrays.asList(socialUser5, socialUser7));
        User user8 = new User();
        user8.setId("user8");
        user8.setSocialUsers(Arrays.asList(socialUser6, socialUser8));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);
        repoImpl.save(user5);
        repoImpl.save(user6);
        repoImpl.save(user7);
        repoImpl.save(user8);

        Set<String> providerUserIds = new HashSet<>();

        providerUserIds.add(providerUserId1);
        providerUserIds.add(providerUserId2);

        List<String> result = repoImpl.findUserIdsByProviderIdAndProviderUserIds(providerId1, providerUserIds);

        assertTrue("The size should be 4, but was " + result.size(), result.size() == 4);
        assertTrue("The user1 userId was not found", result.contains(user1.getId()));
        assertTrue("The user2 userId was not found", result.contains(user2.getId()));
        assertTrue("The user5 userId was not found", result.contains(user5.getId()));
        assertTrue("The user6 userId was not found", result.contains(user6.getId()));
    }

    /**
     * @see UserRepositoryImpl#findByUserId(String)
     */
    @Test
    public void findByUserIdAndProviderIdTest() {
        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId("facebook");
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId("notfacebook");
        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId("fAcEbOoK");

        // Must be into result
        User user1 = new User();
        user1.setId("T35LA1");
        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2, socialUser3));

        User user2 = new User();
        user2.setId("K1RCHH0FF");
        user2.setSocialUsers(Arrays.asList(socialUser1));

        User user3 = new User();
        user3.setId("T35LA2");
        user3.setSocialUsers(Arrays.asList(socialUser2));

        User user4 = new User();
        user4.setId("6AU55");
        user4.setSocialUsers(Arrays.asList(socialUser2, socialUser1));

        User user5 = new User();
        user5.setId("T35LA3");
        user5.setSocialUsers(Arrays.asList(socialUser1));

        User user6 = new User();
        user6.setId("T35LA4");
        user6.setSocialUsers(Arrays.asList(socialUser3));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);
        repoImpl.save(user5);
        repoImpl.save(user6);

        List<User> usersResult = repoImpl.findByUserIdAndProviderId("T35LA1", "facebook");

        assertTrue("Should be 1, but it was " + usersResult.size(), usersResult.size() == 1);
        assertTrue("The user1 does not match", usersResult.contains(user1));
    }

    /**
     * @see UserRepositoryImpl#findPrimaryByUserIdAndProviderId(String, String)
     */
    @Test
    public void findPrimaryByUserIdAndProviderIdTest() {
        String userId = "MA5PAL0MA5";

        User user1 = new User();
        user1.setId(userId);
        User user2 = new User();
        user2.setId("AN0TH3RID");
        User user3 = new User();
        user3.setId("TH1RDID");

        SocialUser socialUser1 = new SocialUser();
        SocialUser socialUser2 = new SocialUser();
        socialUser1.setProviderId("FACEBOOK");
        socialUser2.setProviderId("TWITTER");

        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2));
        user2.setSocialUsers(Arrays.asList(socialUser2));
        user3.setSocialUsers(Arrays.asList(socialUser1));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);

        List<User> usersResult = repoImpl.findPrimaryByUserIdAndProviderId(userId, "FACEBOOK");

        assertTrue("The userResult size should be 1, but was " + usersResult.size(), usersResult.size() == 1);
        assertTrue("The user 1 is not into result", usersResult.contains(user1));
    }

    /**
     * @see UserRepositoryImpl#get(String, String, String)
     */
    @Test
    public void getWithValidProviderIdAndProviderUserId() {
        String userId = "CANT3RA5";
        String providerUserId = "FAC31D";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId("FACEBOOK");
        socialUser1.setProviderUserId(providerUserId);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderUserId("TWITTER");
        socialUser2.setProviderUserId(providerUserId);

        User user = new User();
        user.setId(userId);
        user.setSocialUsers(Arrays.asList(socialUser1, socialUser2));

        repoImpl.save(user);

        User result = repoImpl.get(userId, "FACEBOOK", providerUserId);

        assertTrue("The userId should be " + user.getId() + ", but was " + result.getId(), result.getId().equals(result.getId()));
    }

    /**
     * @see UserRepositoryImpl#get(String, String, String)
     */
    @Test
    public void getWithValidProviderIdAndInvalidProviderUserId() {
        String userId = "CANT3RA5";
        String providerUserId = "FAC31D";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId("FACEBOOK");
        socialUser1.setProviderUserId(providerUserId);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderUserId("TWITTER");
        socialUser2.setProviderUserId(providerUserId);

        User user = new User();
        user.setId(userId);
        user.setSocialUsers(Arrays.asList(socialUser1, socialUser2));

        repoImpl.save(user);

        User result = repoImpl.get(userId, "FACEBOOK", "notsame");

        assertTrue("The user is not null", NullHelper.isAnyNull(result));
    }

    /**
     * @see UserRepositoryImpl#get(String, String, String)
     */
    @Test
    public void getWithInvalidProviderIdAndValidProviderUserId() {
        String userId = "CANT3RA5";
        String providerUserId = "FAC31D";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId("FACEBOOK");
        socialUser1.setProviderUserId(providerUserId);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderUserId("TWITTER");
        socialUser2.setProviderUserId(providerUserId);

        User user = new User();
        user.setId(userId);
        user.setSocialUsers(Arrays.asList(socialUser1, socialUser2));

        repoImpl.save(user);

        User result = repoImpl.get(userId, "PROVIDERNAME", providerUserId);

        assertTrue("The user is not null", NullHelper.isAnyNull(result));
    }

    /**
     * @see UserRepositoryImpl#getByUsername(String)
     */
    @Test
    public void getByUsernameWithInvalidUsername1() {
        String username = "validusername";
        String userId = "exampleid";

        User user = new User();
        user.setId(userId);
        user.setUsername("falseusername");

        repoImpl.save(user);

        User result = repoImpl.getByUsername(username);

        assertTrue("Result should be null", NullHelper.isAnyNull(result));
    }

    /**
     * @see UserRepositoryImpl#getByUsername(String)
     */
    @Test
    public void getByUsernameWithInvalidUsername2() {
        String username = "validusername";
        String userId = "exampleid";

        User user = new User();
        user.setId(userId);
        user.setUsername(" validusername ");

        repoImpl.save(user);

        User result = repoImpl.getByUsername(username);

        assertTrue("Result should be null", NullHelper.isAnyNull(result));
    }

    /**
     * @see UserRepositoryImpl#getByUsername(String)
     */
    @Test
    public void getByUsernameWithInvalidUsername3() {
        String username = "validusername";
        String userId = "exampleid";

        User user1 = new User();
        user1.setId(userId);
        user1.setUsername(username);

        User user2 = new User();
        user2.setId("example2");
        user2.setUsername(username + "name");

        User user3 = new User();
        user3.setId("example3");
        user3.setUsername("name" + username);

        User user4 = new User();
        user4.setId("example4");
        user4.setUsername("name" + username + "name");

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);

        User result = repoImpl.getByUsername("valiDuserName");

        assertTrue("User was not equal. The resuldId was " + result.getId() + " and should be " + user1.getId(), result.getId().equals(user1.getId()));
    }


    @Test
    public void getByUserNameWithDotInTheUsername() throws Exception {
        String username = "valid.user";
        String userId = "USER_ID";
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        template.save(user);

        User result = repoImpl.getByUsername(username);
        assertThat(result, is(user));
    }

    /**
     * @see UserRepositoryImpl#findUsersByProviderIdAndProviderUserId(String, String)
     */
    @Test
    public void findUsersByProviderIdAndProviderUserId() {
        String providerUserId = "922202122";
        String providerId = "facebook";

        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId(providerId);
        socialUser1.setProviderUserId(providerUserId);
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId("twitter");
        socialUser2.setProviderUserId("922901123");
        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId(providerId);
        socialUser3.setProviderUserId("922202166");
        SocialUser socialUser4 = new SocialUser();
        socialUser4.setProviderId("otherprovider");
        socialUser4.setProviderUserId(providerUserId);

        User user1 = new User();
        user1.setId("user1");
        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2, socialUser3, socialUser4));
        User user2 = new User();
        user2.setId("user2");
        user2.setSocialUsers(Arrays.asList(socialUser2, socialUser3));
        User user3 = new User();
        user3.setId("user3");
        user3.setSocialUsers(Arrays.asList(socialUser3, socialUser4));
        User user4 = new User();
        user4.setId("user4");
        user4.setSocialUsers(Arrays.asList(socialUser1, socialUser4));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);

        List<User> usersResult = repoImpl.findUsersByProviderIdAndProviderUserId(providerId, providerUserId);

        assertTrue("The result size should be 2, but was " + usersResult.size(), usersResult.size() == 2);
        assertTrue("User 1 was not found", usersResult.contains(user1));
        assertTrue("User 4 was not found", usersResult.contains(user4));
    }

    /**
     * @see UserRepositoryImpl#findByEmail(String)
     */
    @Test
    public void findByEmailWithValidMail() {
        String email = "singularmail@recomendar.com";

        User user1 = new User();
        user1.setId("user1");
        user1.setEmail(email);
        User user2 = new User();
        user2.setId("user2");
        user2.setEmail("othermail@recomendar.com");

        repoImpl.save(user1);
        repoImpl.save(user2);

        User result = repoImpl.findByEmail(email);

        assertTrue("The user is not the same", result.getId().equals(user1.getId()));
    }

    /**
     * @see UserRepositoryImpl#findByEmail(String)
     */
    @Test
    public void findByEmailWithInvalidMail() {
        String email = "singularmail@recomendar.com";

        User user1 = new User();
        user1.setId("user1");
        user1.setEmail("fastmail@recomendar.com");
        User user2 = new User();
        user2.setId("user2");
        user2.setEmail("othermail@recomendar.com");

        repoImpl.save(user1);
        repoImpl.save(user2);

        User result = repoImpl.findByEmail(email);

        assertTrue("The user is not null", NullHelper.isAnyNull(result));
    }

    /**
     * @see UserRepositoryImpl#findUserIdsByProviderIdAndProviderUserId(String, String)
     */
    @Test
    public void findUserIdsByProviderIdAndProviderUserId() {
        String providerId1 = "facebook";
        String providerId2 = "twitter";

        //Valid
        String providerUserId1 = "ZXCVBN";
        String providerUserId2 = "ASDFGH";
        // Not valid
        String providerUserId3 = "123456";
        String providerUserId4 = "QWE123";
        String providerUserId5 = "MNBVCX";
        String providerUserId6 = "POIUYT";

        // Valid
        SocialUser socialUser1 = new SocialUser();
        socialUser1.setProviderId(providerId1);
        socialUser1.setProviderUserId(providerUserId1);
        // Not valid
        SocialUser socialUser2 = new SocialUser();
        socialUser2.setProviderId(providerId1);
        socialUser2.setProviderUserId(providerUserId2);
        SocialUser socialUser3 = new SocialUser();
        socialUser3.setProviderId(providerId2);
        socialUser3.setProviderUserId(providerUserId3);
        SocialUser socialUser4 = new SocialUser();
        socialUser4.setProviderId(providerId2);
        socialUser4.setProviderUserId(providerUserId4);
        SocialUser socialUser5 = new SocialUser();
        socialUser5.setProviderId(providerId1);
        socialUser5.setProviderUserId(providerUserId5);
        SocialUser socialUser6 = new SocialUser();
        socialUser6.setProviderId(providerId1);
        socialUser6.setProviderUserId(providerUserId6);
        SocialUser socialUser7 = new SocialUser();
        socialUser7.setProviderId(providerId2);
        socialUser7.setProviderUserId(providerUserId1);
        SocialUser socialUser8 = new SocialUser();
        socialUser8.setProviderId(providerId2);
        socialUser8.setProviderUserId(providerUserId2);

        User user1 = new User();
        user1.setId("user1");
        user1.setSocialUsers(Arrays.asList(socialUser1, socialUser2));
        User user2 = new User();
        user2.setId("user2");
        user2.setSocialUsers(Arrays.asList(socialUser1, socialUser5));
        User user3 = new User();
        user3.setId("user3");
        user3.setSocialUsers(Arrays.asList(socialUser3, socialUser4));
        User user4 = new User();
        user4.setId("user4");
        user4.setSocialUsers(Arrays.asList(socialUser7, socialUser8));
        User user5 = new User();
        user5.setId("user5");
        user5.setSocialUsers(Arrays.asList(socialUser1));
        User user6 = new User();
        user6.setId("user6");
        user6.setSocialUsers(Arrays.asList(socialUser2, socialUser7));

        repoImpl.save(user1);
        repoImpl.save(user2);
        repoImpl.save(user3);
        repoImpl.save(user4);
        repoImpl.save(user5);
        repoImpl.save(user6);

        List<String> result = repoImpl.findUserIdsByProviderIdAndProviderUserId(providerId1, providerUserId1);

        assertTrue("The size should be 3, but was " + result.size(), result.size() == 3);
        assertTrue("The user1 userId was not found", result.contains(user1.getId()));
        assertTrue("The user2 userId was not found", result.contains(user2.getId()));
        assertTrue("The user5 userId was not found", result.contains(user5.getId()));
    }

    @Test
    public void getUsernameOrEmailWithUsernameFind() throws Exception {
        String findValue = "USER_1";
        User user1 = new User();
        user1.setId("userId1");
        user1.setUsername(findValue);

        User user2 = new User();
        user2.setId("userId2");
        user2.setUsername("USER_2");

        template.save(user1);
        template.save(user2);

        assertThat(repoImpl.getByUsernameOrEmail(findValue), is(user1));
    }

    @Test
    public void getUsernameOrEmailWithEmailFind() throws Exception {
        String findValue = "USER_1@uno.com";
        User user1 = new User();
        user1.setId("userId1");
        user1.setUsername("USername_1");
        user1.setEmail(findValue);

        User user2 = new User();
        user2.setId("userId2");
        user2.setUsername("USER_2");
        user2.setEmail("NONE2");

        template.save(user1);
        template.save(user2);

        assertThat(repoImpl.getByUsernameOrEmail(findValue), is(user1));
    }

    @Test
    public void getUsernameOrEmailWithNOResults() throws Exception {
        String findValue = "UNKNOWN";
        User user1 = new User();
        user1.setId("userId1");
        user1.setUsername("USername_1");
        user1.setEmail("USER_1@uno.com");

        User user2 = new User();
        user2.setId("userId2");
        user2.setUsername("USER_2");
        user2.setEmail("NONE2");

        template.save(user1);
        template.save(user2);

        assertNull(repoImpl.getByUsernameOrEmail(findValue));
    }

    @Test
    public void getUsernameOrEmailWithDoubleCheck() throws Exception {
        String findValue = "EMAIL_AND_USERNAME";
        User user1 = new User();
        user1.setId("userId1");
        user1.setUsername("USername_1");
        user1.setEmail("USER_1@uno.com");

        User user2 = new User();
        user2.setId("userId2");
        user2.setUsername(findValue);
        user2.setEmail(findValue);

        template.save(user1);
        template.save(user2);

        assertThat(repoImpl.getByUsernameOrEmail(findValue), is(user2));
    }


}


