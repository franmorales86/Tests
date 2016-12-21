/**
 * Project: RecCommon
 * Created by: raulanatol at 03/12/2012 19:26:16
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.exceptions.generic.UserException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author raulanatol
 */
public class URLHelperTest {

    /**
     * @see URLHelper#verifyIfExists(String)
     */
    @Test
    public void verifyIfExistsTrueTest() {
        assertTrue(URLHelper.verifyIfExists("http://www.google.com"));
        assertFalse(URLHelper.verifyIfExists("wwwgooglecom"));
        assertFalse(URLHelper.verifyIfExists(null));
        assertFalse(URLHelper.verifyIfExists("-"));
    }

    /**
     * @throws UserException
     * @see URLHelper#getShortCodeFromURL(String)
     */
    @Test
    public void getShortCodeFromUrlBasicTest() throws UserException {
        assertThat(URLHelper.getShortCodeFromURL("http://localhost/12354"), is("12354"));
        assertThat(URLHelper.getShortCodeFromURL("/12354"), is("12354"));
        assertThat(URLHelper.getShortCodeFromURL("http://local/host"), is("host"));
        assertThat(URLHelper.getShortCodeFromURL("local/host"), is("host"));
        assertThat(URLHelper.getShortCodeFromURL("http://localhost"), is("localhost"));
    }

    /**
     * @throws UserException
     * @see URLHelper#getShortCodeFromURL(String)
     */
    @Test(expected = UserException.class)
    public void getShortCodeFromUrlNoSlagResponseTest() throws UserException {
        assertThat(URLHelper.getShortCodeFromURL("12354"), nullValue());
    }

    /**
     * @throws UserException
     * @see URLHelper#getShortCodeFromURL(String)
     */
    @Test(expected = UserException.class)
    public void getShortCodeFromUrlNoMoreContentResponseTest() throws UserException {
        assertThat(URLHelper.getShortCodeFromURL("http://"), nullValue());
    }

    /**
     * @throws UserException
     * @see URLHelper#getShortCodeFromURL(String)
     */
    @Test(expected = UserException.class)
    public void getShortCodeFromUrlEmptyResponseTest() throws UserException {
        assertThat(URLHelper.getShortCodeFromURL(""), nullValue());
    }

    /**
     * @throws UserException
     * @see URLHelper#getShortCodeFromURL(String)
     */
    @Test(expected = UserException.class)
    public void getShortCodeFromUrlNullValueResponseTest() throws UserException {
        assertThat(URLHelper.getShortCodeFromURL(null), nullValue());
    }

    /**
     * @see URLHelper#generateShortCode()
     */
    @Test
    public void generateShortCodeBasicTest() {
        assertNotNull(URLHelper.generateShortCode());
    }

    /**
     * @see URLHelper#getDomainURL(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void getDomainURLWithNullParameters() {
        URLHelper.getDomainURL(null);
    }
    /**
     * @see URLHelper#getDomainURL(String)
     */
    @Test(expected = RecIllegalArgumentException.class)
    public void getDomainURLWithEmptyParameters() {
        URLHelper.getDomainURL("");
    }

    /**
     * @see URLHelper#getDomainURL(String)
     */
    @Test
    public void getDomainURLWithNormalParameters() {
        assertThat(URLHelper.getDomainURL("http://www.google.com/asd/Asda/AsdA/"), is("google.com"));
        assertThat(URLHelper.getDomainURL("https://www.google.es/search?q=asdada&oq=asdada&aqs=chrome.0.57j60j65l2j61l2.481j0&sourceid=chrome&ie=UTF-8"), is("google.es"));
        assertThat(URLHelper.getDomainURL("http://ad.zanox.com/ppc/?24353719C1892000920&ulp=[[XXX]]"), is("ad.zanox.com"));
        assertThat(URLHelper.getDomainURL("http://www.teltuo.com/moda/index.php?tt=10082_12_125388_&r=%2FXXX"), is("teltuo.com"));
        assertThat(URLHelper.getDomainURL("http://track.webgains.com/click.html?wgcampaignid=133005&wgprogramid=7561&wgtarget=XXX"), is("track.webgains.com"));
        assertThat(URLHelper.getDomainURL("http://www.amazon.com/gp/product/YYY/ref=as_li_ss_tl?ie=UTF8&camp=1789&creative=390957&creativeASIN=YYY&linkCode=as2&tag=recomendar-20"), is("amazon.com"));
        assertThat(URLHelper.getDomainURL("http://www.amazon.es/gp/product/YYY/ref=as_li_ss_tl?ie=UTF8&camp=3626&creative=24822&creativeASIN=YYY&linkCode=as2&tag=recomendarcom-21"), is("amazon.es"));
    }

    @Test
    public void getShortCodeFromUrlSimpleData() throws Exception {
        assertThat(URLHelper.getShortCodeFromURL("http://rcmd.me/2DF3fg"), is("2DF3fg"));
    }
}