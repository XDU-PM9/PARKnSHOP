package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "shop", schema = "parknshop", catalog = "")
public class ShopEntity {
    private Integer shopId;
    private String shopName="";
    private String introduction="";
    private String photoGroup="";
    private Integer views;
    private String logo="";
    private Timestamp createTime=new Timestamp(System.currentTimeMillis());
    private Integer state;
    private Collection<CollectshopEntity> collectshopsByShopId;
    private Collection<GoodsEntity> goodssByShopId;
    private OwnerEntity ownerByOwnerId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "shopId", nullable = false)
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "shopName", nullable = false, length = 50)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = 1000)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "photoGroup", nullable = false, length = 100)
    public String getPhotoGroup() {
        return photoGroup;
    }

    public void setPhotoGroup(String photoGroup) {
        this.photoGroup = photoGroup;
    }

    @Basic
    @Column(name = "views", nullable = false)
    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Basic
    @Column(name = "logo", nullable = true, length = 100)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "createTime", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopEntity that = (ShopEntity) o;

        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (shopName != null ? !shopName.equals(that.shopName) : that.shopName != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (photoGroup != null ? !photoGroup.equals(that.photoGroup) : that.photoGroup != null) return false;
        if (views != null ? !views.equals(that.views) : that.views != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId != null ? shopId.hashCode() : 0;
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (photoGroup != null ? photoGroup.hashCode() : 0);
        result = 31 * result + (views != null ? views.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "shopByShopId")
    public Collection<CollectshopEntity> getCollectshopsByShopId() {
        return collectshopsByShopId;
    }

    public void setCollectshopsByShopId(Collection<CollectshopEntity> collectshopsByShopId) {
        this.collectshopsByShopId = collectshopsByShopId;
    }

    @OneToMany(mappedBy = "shopByShopId")
    public Collection<GoodsEntity> getGoodssByShopId() {
        return goodssByShopId;
    }

    public void setGoodssByShopId(Collection<GoodsEntity> goodssByShopId) {
        this.goodssByShopId = goodssByShopId;
    }

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "ownerId")
    public OwnerEntity getOwnerByOwnerId() {
        return ownerByOwnerId;
    }

    public void setOwnerByOwnerId(OwnerEntity ownerByOwnerId) {
        this.ownerByOwnerId = ownerByOwnerId;
    }
}
