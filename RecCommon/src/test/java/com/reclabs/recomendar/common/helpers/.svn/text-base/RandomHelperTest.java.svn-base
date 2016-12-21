/**
 * Project: RecCommon
 * Created by: raulanatol at 11/01/2013 20:21:11
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.types.CollectionHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 */
public class RandomHelperTest {

    /**
     * @see RandomHelper#getRandomPosition(int, int, List)
     */
    @Test
    public void getRandomPositionSingleTest() {
        int randomValue = RandomHelper.getRandomPosition(5, 11, Arrays.asList(6, 8, 10));
        assertTrue(randomValue >= 5);
        assertTrue(randomValue <= 11);
        assertTrue(randomValue != 6);
        assertTrue(randomValue != 8);
        assertTrue(randomValue != 10);
    }

    /**
     * @see RandomHelper#generateUsernameList(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void generateUsernameListWithNullPattern() {
        RandomHelper.generateUsernameList(null);
    }

    /**
     * @see RandomHelper#generateUsernameList(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void generateUsernameListWithEmptyPattern() {
        RandomHelper.generateUsernameList("");
    }

    /**
     * @see RandomHelper#generateUsernameList(String)
     */
    @Test
    public void generateUsernameListWithSimplePattern() {
        List<String> usernameList = RandomHelper.generateUsernameList("JohnDoe");
        assertFalse(CollectionHelper.isEmpty(usernameList));
        assertThat(usernameList.size(), is(10));
    }

    /**
     * @see RandomHelper#generateUsernameList(String)
     */
    @Test
    public void generateUsernameListWithSimplePatternMustBeContainPattern() {
        String pattern = "JohnDoe";
        List<String> usernameList = RandomHelper.generateUsernameList(pattern);
        assertFalse(CollectionHelper.isEmpty(usernameList));
        assertThat(usernameList.size(), is(10));
        assertTrue(usernameList.contains(pattern));
    }

    /**
     * @see RandomHelper#getRandomNumber(double)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void getRandomNumberWithZeroDigits() {
        RandomHelper.getRandomNumber(0);
    }

    /**
     * @see RandomHelper#getRandomNumber(double)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void getRandomNumberWithNegativeDigits() {
        RandomHelper.getRandomNumber(-15);
    }

    /**
     * @see RandomHelper#getRandomNumber(double)
     */
    @Test
    public void getRandomNumberWithNormalDigits() {
        String result = RandomHelper.getRandomNumber(5);
        assertFalse(StringHelper.isEmpty(result));
        assertThat(result.length(), is(5));
    }

    @Test
    public void getRandomNumberWithTenDigits() throws Exception {
        assertThat(RandomHelper.getRandomNumber(10).length(), is(10));
    }

    @Test
    public void getRandomNumberWithEightDigits() throws Exception {
        assertThat(RandomHelper.getRandomNumber(8).length(), is(8));
    }

    /**
     * @see RandomHelper#getRandomNumber(double)
     */
    @Test
    public void getRandomNumberWithNormalDigitsVerifyLimits() {
        String result = RandomHelper.getRandomNumber(5);
        int value = Integer.parseInt(result);
        assertTrue(value > -1);
        assertTrue(value < 99999);
    }

    /**
     * @see com.reclabs.recomendar.common.helpers.RandomHelper#getRandomNumberZeroToNine()
     */
    public void getNumberZeroToNineSimpleTest() {
        int result = RandomHelper.getRandomNumberZeroToNine();
        assertTrue(result > -1);
        assertTrue(result < 10);
    }
}
