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
@Table(name = "goods", schema = "parknshop", catalog = "")
public class GoodsEntity {
    private Integer goodsId;
    private String goodsName;
    private String introduction;
    private Double price;
    private Double discount;
    private Integer inventory;
    private String photoGroup;
    private Timestamp createTime;
    private Integer views;
    private Integer state;
    private String type;
    private int sales;
    private Collection<CollectionEntity> collectionsByGoodsId;
    private ShopEntity shopByShopId;
    private Collection<OrdersEntity> orderssByGoodsId;
    private Collection<SinglegoodEntity> singlegoodsByGoodsId;

    @Basic
    @Column(name ="sales",nullable = false,columnDefinition = "int DEFAULT 0")
    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "type",nullable = false,columnDefinition = "varchar(50) DEFAULT other")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "goodsId", nullable = false)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goodsName", nullable = false, length = 50)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "introduction", nullable = false, length = 500)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 2)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "inventory", nullable = false)
    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Basic
    @Column(name = "photoGroup", nullable = true, length = 50)
    public String getPhotoGroup() {
        return photoGroup;
    }

    public void setPhotoGroup(String photoGroup) {
        this.photoGroup = photoGroup;
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
    @Column(name = "views", nullable = false)
    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

        GoodsEntity that = (GoodsEntity) o;

        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (inventory != null ? !inventory.equals(that.inventory) : that.inventory != null) return false;
        if (photoGroup != null ? !photoGroup.equals(that.photoGroup) : that.photoGroup != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (views != null ? !views.equals(that.views) : that.views != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (inventory != null ? inventory.hashCode() : 0);
        result = 31 * result + (photoGroup != null ? photoGroup.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (views != null ? views.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<CollectionEntity> getCollectionsByGoodsId() {
        return collectionsByGoodsId;
    }

    public void setCollectionsByGoodsId(Collection<CollectionEntity> collectionsByGoodsId) {
        this.collectionsByGoodsId = collectionsByGoodsId;
    }

    @ManyToOne
    @JoinColumn(name = "shopId", referencedColumnName = "shopId", nullable = false)
    public ShopEntity getShopByShopId() {
        return shopByShopId;
    }

    public void setShopByShopId(ShopEntity shopByShopId) {
        this.shopByShopId = shopByShopId;
    }

    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<OrdersEntity> getOrderssByGoodsId() {
        return orderssByGoodsId;
    }

    public void setOrderssByGoodsId(Collection<OrdersEntity> orderssByGoodsId) {
        this.orderssByGoodsId = orderssByGoodsId;
    }

    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<SinglegoodEntity> getSinglegoodsByGoodsId() {
        return singlegoodsByGoodsId;
    }

    public void setSinglegoodsByGoodsId(Collection<SinglegoodEntity> singlegoodsByGoodsId) {
        this.singlegoodsByGoodsId = singlegoodsByGoodsId;
    }
}
