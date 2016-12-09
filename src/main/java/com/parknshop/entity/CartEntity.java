package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "cart", schema = "parknshop")
public class CartEntity {
    private Integer cartId;
    private GoodsEntity goodsEntity;
//    private Integer goodsId;
    private Integer singleGoodId;
    private Integer amount;
    private Timestamp createTime;
    private UserEntity userByUserId;
//    private Integer userId;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cartId", nullable = false)
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "singleGoodId", nullable = true)
    public Integer getSingleGoodId() {
        return singleGoodId;
    }

    public void setSingleGoodId(Integer singleGoodId) {
        this.singleGoodId = singleGoodId;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "createTime", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (singleGoodId != null ? !singleGoodId.equals(that.singleGoodId) : that.singleGoodId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode() : 0;
        result = 31 * result + (singleGoodId != null ? singleGoodId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId", nullable = false)
    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public void setGoodsEntity(GoodsEntity userByUserId) {
        this.goodsEntity = userByUserId;
    }
//    @Column(name="userId",nullable = false)
//    public Integer getUserId(){
//        return this.userId;
//    }
//
//    public void setUserId(Integer userId){
//        this.userId=userId;
//    }
//
//    @Column(name = "goodsId",nullable = false)
//    public Integer getGoodsId(){
//        return this.goodsId;
//    }
//
//    public void setGoodsId(Integer goodsId){
//        this.goodsId=goodsId;
//    }
}
