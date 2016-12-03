package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "singlegood", schema = "parknshop", catalog = "")
public class SinglegoodEntity {
    private Integer singleGoodId;
    private String typeDescribe;
    private Integer inventory;
    private Integer state;
    private GoodsEntity goodsByGoodsId;

    @Id
    @Column(name = "singleGoodId", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getSingleGoodId() {
        return singleGoodId;
    }

    public void setSingleGoodId(Integer singleGoodId) {
        this.singleGoodId = singleGoodId;
    }

    @Basic
    @Column(name = "typeDescribe", nullable = true, length = 50)
    public String getTypeDescribe() {
        return typeDescribe;
    }

    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    @Basic
    @Column(name = "inventory", nullable = true)
    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Basic
    @Column(name = "state", nullable = true)
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

        SinglegoodEntity that = (SinglegoodEntity) o;

        if (singleGoodId != null ? !singleGoodId.equals(that.singleGoodId) : that.singleGoodId != null) return false;
        if (typeDescribe != null ? !typeDescribe.equals(that.typeDescribe) : that.typeDescribe != null) return false;
        if (inventory != null ? !inventory.equals(that.inventory) : that.inventory != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = singleGoodId != null ? singleGoodId.hashCode() : 0;
        result = 31 * result + (typeDescribe != null ? typeDescribe.hashCode() : 0);
        result = 31 * result + (inventory != null ? inventory.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId")
    public GoodsEntity getGoodsByGoodsId() {
        return goodsByGoodsId;
    }

    public void setGoodsByGoodsId(GoodsEntity goodsByGoodsId) {
        this.goodsByGoodsId = goodsByGoodsId;
    }
}
