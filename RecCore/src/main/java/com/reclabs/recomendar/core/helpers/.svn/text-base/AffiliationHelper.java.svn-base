/**
 * Project: RecCore
 * Created by: raulanatol at 13/07/13 21:08
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2013
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.helpers;

import com.reclabs.recomendar.common.exceptions.RecIllegalArgumentException;
import com.reclabs.recomendar.common.helpers.ParameterHelper;
import com.reclabs.recomendar.common.helpers.types.StringHelper;
import com.reclabs.recomendar.model.documents.brand.AffiliationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author raulanatol
 */
public class AffiliationHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AffiliationHelper.class);
    private static final Pattern DOMAIN_PATTERN = Pattern.compile(".*?([^.]+\\.[^.]+)");

    /**
     * @param affiliationManager Manager to affiliation
     * @param urlProductToManager URL of the product to manage
     * @return The url of the product affiliation.
     */
    public static String generateResult(AffiliationManager affiliationManager, String urlProductToManager) {
        String productValidURL = StringHelper.extract(urlProductToManager, affiliationManager.getEliminatePattern());
        return StringHelper.replaceToken(affiliationManager.getPattern(), "XXX", productValidURL);
    }

    /**
     * @param domainA Main domain
     * @param domainB Second domain to compare
     * @return True if the domainA is on domainB
     */
    public static boolean compareDomain(String domainA, String domainB) {
        return StringHelper.equals(getDomainFromItemInformationURL(domainA), getDomainFromItemInformationURL(domainB));
    }

    /**
     * Get the domain of an item url information.
     * @param itemInformationURL The url to extract the domain
     * @return The domain value
     */
    public static String getDomainFromItemInformationURL(String itemInformationURL) {
        String result;
        if (StringHelper.isEmpty(itemInformationURL)) {
            LOGGER.warn("[Trying to extract domain from an EMPTY informationURL]");
            result = null;
        } else {
            try {
                URI uri = new URI(itemInformationURL);
                String host = uri.getHost();
                if (StringHelper.isEmpty(host)) {
                    if (!itemInformationURL.startsWith("http://")) {
                        result = getDomainFromItemInformationURL("http://" + itemInformationURL);
                    } else {
                        LOGGER.warn("Impossible obtain domain from url {} second phase", itemInformationURL);
                        throw new RecIllegalArgumentException("Impossible obtain domain from url {}", itemInformationURL);
                    }
                } else {
                    Matcher matcher = DOMAIN_PATTERN.matcher(host);
                    if (matcher.matches()) {
                        result = matcher.group(1);
                    } else {
                        LOGGER.warn("Impossible obtain domain from url {}", itemInformationURL);
                        throw new RecIllegalArgumentException("Impossible obtain domain from url {}", itemInformationURL);
                    }
                }
            } catch (URISyntaxException e) {
                LOGGER.error("The url {} is invalid", itemInformationURL);
                throw new RecIllegalArgumentException("The url {} is invalid", itemInformationURL);
            }
        }
        return result;
    }

    /**
     * Gets brand from un alias String.
     * @param alias The alias format: BRANNAME_PROVIDERNAME_VENDORREGION
     * @return BRANDNAME
     */
    public static String extractBrandNameFromAlias(String alias) {
        ParameterHelper.assertEmpty(alias);
        String[] aliasArray = alias.split("_");
        if (aliasArray.length != 3) {
            LOGGER.error("[Trying to obtain brand name from this alias: {}]", alias);
            throw new RecIllegalArgumentException("Invalid alias");
        }
        return aliasArray[0];
    }
}
