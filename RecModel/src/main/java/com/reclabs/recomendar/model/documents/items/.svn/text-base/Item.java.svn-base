/**
 * Project: RecModel
 * Created by: fjmorales at 10/11/2012 10:37:36
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.documents.items;

import com.reclabs.recomendar.model.documents.brand.data.AffiliationManagerData;
import com.reclabs.recomendar.model.documents.brand.data.BrandData;
import com.reclabs.recomendar.model.documents.brand.data.MarketPlaceManagerData;
import com.reclabs.recomendar.model.types.StateProductType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Representa los datos de un producto
 * @author fjmorales
 */
@Document
public class Item implements Serializable {
    private static final long serialVersionUID = 3122347012604906143L;
    /**
     * Identificador del producto.
     */
    @Id
    private String id;

    /**
     * Nombre del producto
     */
    private String name;

    /**
     * Estado de actividad del producto
     */
    private StateProductType state;

    /**
     * Descripcion del producto
     */
    private String description;

    /**
     * Listado de todas las imágenes que tiene el producto.
     */
    private List<URLPath> imagesURL;

    /**
     * Fecha de alta del producto en la base de datos.
     */
    private Date createdDate;

    /**
     * Número de identificación europea, a modo código de barras. Sirve para comparar productos en el futuro.
     * <b>Ejemplo:</b> "3700225608322"
     * En caso de que la tenga.
     */
    private String ean;

    /**
     * Identificado isbn del producto.
     * En caso de que la tenga.
     */
    private String isbn;

    /**
     * Numero de recomendaciones del producto
     */
    private Long recommendations;

    /**
     * Listado de etiquetas asociados al producto.
     * Sin referencias entre ellas.
     */
    private List<ItemTag> tags;

    /**
     * Categoría del producto.
     */
    private String category;

    /**
     * The first url that item has had
     */
    private String informationURL;

    /**
     * The first url of the imagen used to verify if the image is duplicated
     */
    private String originImageURL;
    /**
     *
     */
    private List<AffiliationManagerData> affiliationManagerDataList;

    /**
     *
     */
    private List<MarketPlaceManagerData> marketPlaceManagerDataList;

    /**
     * Virtual relationship to upgrade the performance
     */
    private List<BrandData> brandDataList;

    /**
     * Id del usuario que es dueño del item.
     */
    private String ownerId;

    /**
     * Username of owner user
     */
    private String ownerUsername;

    /**
     * Indicates if user is VIP
     */
    private Boolean vip;

    /**
     * Date of item is VIP
     */
    private Date vipDate;

    /**
     * Indicates if item was verified in sandbox
     */
    private Boolean sandboxVerified;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the state
     */
    public StateProductType getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(StateProductType state) {
        this.state = state;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagesURL
     */
    public List<URLPath> getImagesURL() {
        return imagesURL;
    }

    /**
     * @param imagesURL the imagesURL to set
     */
    public void setImagesURL(List<URLPath> imagesURL) {
        this.imagesURL = imagesURL;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the recommendations
     */
    public Long getRecommendations() {
        return recommendations;
    }

    /**
     * @param recommendations the recommendations to set
     */
    public void setRecommendations(Long recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * @return the tags
     */
    public List<ItemTag> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<ItemTag> tags) {
        this.tags = tags;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getInformationURL() {
        return informationURL;
    }

    public void setInformationURL(String informationURL) {
        this.informationURL = informationURL;
    }

    public List<AffiliationManagerData> getAffiliationManagerDataList() {
        return affiliationManagerDataList;
    }

    public void setAffiliationManagerDataList(List<AffiliationManagerData> affiliationManagerDataList) {
        this.affiliationManagerDataList = affiliationManagerDataList;
    }

    public List<MarketPlaceManagerData> getMarketPlaceManagerDataList() {
        return marketPlaceManagerDataList;
    }

    public void setMarketPlaceManagerDataList(List<MarketPlaceManagerData> marketPlaceManagerDataList) {
        this.marketPlaceManagerDataList = marketPlaceManagerDataList;
    }

    public List<BrandData> getBrandDataList() {
        return brandDataList;
    }

    public void setBrandDataList(List<BrandData> brandDataList) {
        this.brandDataList = brandDataList;
    }

    public String getOriginImageURL() {
        return originImageURL;
    }

    public void setOriginImageURL(String originImageURL) {
        this.originImageURL = originImageURL;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Date getVipDate() {
        return vipDate;
    }

    public void setVipDate(Date vipDate) {
        this.vipDate = vipDate;
    }

    public Boolean getSandboxVerified() {
        return sandboxVerified;
    }

    public void setSandboxVerified(Boolean sandboxVerified) {
        this.sandboxVerified = sandboxVerified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (affiliationManagerDataList != null ? !affiliationManagerDataList.equals(item.affiliationManagerDataList) : item.affiliationManagerDataList != null)
            return false;
        if (brandDataList != null ? !brandDataList.equals(item.brandDataList) : item.brandDataList != null)
            return false;
        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        if (createdDate != null ? !createdDate.equals(item.createdDate) : item.createdDate != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (ean != null ? !ean.equals(item.ean) : item.ean != null) return false;
        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (imagesURL != null ? !imagesURL.equals(item.imagesURL) : item.imagesURL != null) return false;
        if (informationURL != null ? !informationURL.equals(item.informationURL) : item.informationURL != null)
            return false;
        if (isbn != null ? !isbn.equals(item.isbn) : item.isbn != null) return false;
        if (marketPlaceManagerDataList != null ? !marketPlaceManagerDataList.equals(item.marketPlaceManagerDataList) : item.marketPlaceManagerDataList != null)
            return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (originImageURL != null ? !originImageURL.equals(item.originImageURL) : item.originImageURL != null)
            return false;
        if (ownerId != null ? !ownerId.equals(item.ownerId) : item.ownerId != null) return false;
        if (ownerUsername != null ? !ownerUsername.equals(item.ownerUsername) : item.ownerUsername != null)
            return false;
        if (recommendations != null ? !recommendations.equals(item.recommendations) : item.recommendations != null)
            return false;
        if (sandboxVerified != null ? !sandboxVerified.equals(item.sandboxVerified) : item.sandboxVerified != null)
            return false;
        if (state != item.state) return false;
        if (tags != null ? !tags.equals(item.tags) : item.tags != null) return false;
        if (vip != null ? !vip.equals(item.vip) : item.vip != null) return false;
        if (vipDate != null ? !vipDate.equals(item.vipDate) : item.vipDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagesURL != null ? imagesURL.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (ean != null ? ean.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (recommendations != null ? recommendations.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (informationURL != null ? informationURL.hashCode() : 0);
        result = 31 * result + (originImageURL != null ? originImageURL.hashCode() : 0);
        result = 31 * result + (affiliationManagerDataList != null ? affiliationManagerDataList.hashCode() : 0);
        result = 31 * result + (marketPlaceManagerDataList != null ? marketPlaceManagerDataList.hashCode() : 0);
        result = 31 * result + (brandDataList != null ? brandDataList.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (ownerUsername != null ? ownerUsername.hashCode() : 0);
        result = 31 * result + (vip != null ? vip.hashCode() : 0);
        result = 31 * result + (vipDate != null ? vipDate.hashCode() : 0);
        result = 31 * result + (sandboxVerified != null ? sandboxVerified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", description='" + description + '\'' +
                ", imagesURL=" + imagesURL +
                ", createdDate=" + createdDate +
                ", ean='" + ean + '\'' +
                ", isbn='" + isbn + '\'' +
                ", recommendations=" + recommendations +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", informationURL='" + informationURL + '\'' +
                ", originImageURL='" + originImageURL + '\'' +
                ", affiliationManagerDataList=" + affiliationManagerDataList +
                ", marketPlaceManagerDataList=" + marketPlaceManagerDataList +
                ", brandDataList=" + brandDataList +
                ", ownerId='" + ownerId + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", vip=" + vip +
                ", vipDate=" + vipDate +
                ", sandboxVerified=" + sandboxVerified +
                '}';
    }
}
