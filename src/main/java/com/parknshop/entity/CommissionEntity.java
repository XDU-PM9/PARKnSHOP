package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "commission", schema = "parknshop", catalog = "")
public class CommissionEntity {
    private Integer commissionId;
    private Double rate;
    private Double shopPrice;
    private Double goodsPrice;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "commissionId", nullable = false)
    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 2)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommissionEntity that = (CommissionEntity) o;

        if (commissionId != null ? !commissionId.equals(that.commissionId) : that.commissionId != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commissionId != null ? commissionId.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "shopPrice", nullable = true, precision = 2)
    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    @Basic
    @Column(name = "goodsPrice", nullable = true, precision = 2)
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
