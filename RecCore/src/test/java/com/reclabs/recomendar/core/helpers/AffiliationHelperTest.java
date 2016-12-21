/**
 * Project: RecCore
 * Created by: raulanatol at 15/07/13 12:00
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import org.junit.Test;

import static com.mongodb.util.MyAsserts.assertFalse;
import static com.mongodb.util.MyAsserts.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author raulanatol
 */
public class AffiliationHelperTest {

    @Test
    public void getDomainFromItemInformationURLWithSecurePathURL() {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("https://foo.com/bar"), is("foo.com"));
    }

    @Test
    public void getDomainFromItemInformationURLWithHash() {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("http://www.foo.com#bar"), is("foo.com"));
    }

    @Test
    public void getDomainFromItemInformationURLWithSubDomain() {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("http://bar.foo.com"), is("foo.com"));
    }

    @Test
    public void getDomainFromItemInformationURLWithMultiSubDomain() {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("http://bar.foo.com.es"), is("com.es"));
    }

    @Test
    public void getDomainFromItemInformationURLWithHostPathURL() {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("etsy.com"), is("etsy.com"));
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getDomainFromItemInformationURLWithInvalidURL() {
        AffiliationHelper.getDomainFromItemInformationURL("foo_bar_foo_bar");
    }

    @Test(expected = RecIllegalArgumentException.class)
    public void getDomainFromItemInformationURLWithInvalidAndHashedURL() {
        AffiliationHelper.getDomainFromItemInformationURL("foo_bar_foo_bar#123");
    }


    @Test
    public void compareDomainWithEqualsValues() {
        assertTrue(AffiliationHelper.compareDomain("http://www.foo.com#bar", "foo.com"));
    }

    @Test
    public void compareDomainWithSimilarValues() {
        assertTrue(AffiliationHelper.compareDomain("http://www.foo.com#bar", "http://www.foo.com"));
    }

    @Test
    public void compareDomainWithSecureHTTPValues() {
        assertTrue(AffiliationHelper.compareDomain("https://www.foo.com#bar", "http://www.foo.com"));
    }

    @Test
    public void compareDomainWithDifferentValues() {
        assertFalse(AffiliationHelper.compareDomain("http://www.foo.com#bar", "foo.es"));
    }

    @Test
    public void compareDomainWithFinalSlash() throws Exception {
        assertTrue(AffiliationHelper.compareDomain("yoquierounodeesos.com/", "http://www.yoquierounodeesos.com/ojo-de-pez-para-iphone-3-y-4/"));
    }

    @Test
    public void compareDomainWithSimpleData1() {
        assertTrue(AffiliationHelper.compareDomain("http://es.dawanda.com/product/18369861-Handbemalte-Schuhe-TOILE-DE-FEMME-Unikate", "dawanda.com"));
    }

    @Test
    public void compareDomainWithSimpleData2() {
        assertTrue(AffiliationHelper.compareDomain("http://happyideas.com/tazas/647-taza-mrp-beso.html#.UY5arYK5CNQ", "happyideas.com"));
    }

    @Test
    public void compareDomainWithSimpleData3() {
        assertTrue(AffiliationHelper.compareDomain("http://www.dcshoes.es/es/council-tx/d0320305-/D0320305-,es_ES,pd.html#?intcmp=dc_es_shop_home-mea-3:dc_es_shop_%20push_pdt_council", "dcshoes.es"));
    }

    @Test
    public void compareDomainWithSimpleData4() {
        assertTrue(AffiliationHelper.compareDomain("http://www.lightinthebox.com/es/roswheel-5-3-pulgadas-de-pvc-transparente-operacion-touchable-paquete-bike-nota-adecuado-para-telefono-movil-1-y-2-htc-butterfly-12496-5-3_p515864.html", "lightinthebox.com"));
    }

    @Test
    public void compareDomainWithSimpleData5() {
        assertTrue(AffiliationHelper.compareDomain("http://makeando.eu/product/collage-tshirt/", "makeando.eu"));
    }

    @Test
    public void compareDomainWithSimpleData6() {
        assertTrue(AffiliationHelper.compareDomain("http://woodglass.es/galeria/ari-classic-sucupira", "woodglass.es"));
    }

    @Test
    public void compareDomainWithSimpleData7() {
        assertTrue(AffiliationHelper.compareDomain("www.firebox.com/product/6162/The-Balcony-BBQ", "http://www.firebox.com/"));
    }

    @Test
    public void getDomainFromItemInformationURLWithSimpleData1() throws Exception {
        assertThat(AffiliationHelper.getDomainFromItemInformationURL("http://www.yoquierounodeesos.com/led-visera/"), is("yoquierounodeesos.com"));
    }
}
