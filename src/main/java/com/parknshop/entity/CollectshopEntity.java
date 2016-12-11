package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "collectshop", schema = "parknshop")
public class CollectshopEntity {
    private Integer scollectId;
    private Timestamp createTime;
    private Integer state;
    private UserEntity userByUserId;
    private ShopEntity shopByShopId;
    private String     picture;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "scollectId", nullable = false)
    public Integer getScollectId() {
        return scollectId;
    }

    public void setScollectId(Integer scollectId) {
        this.scollectId = scollectId;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    @Basic
    @Column(name = "picture", nullable = true)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectshopEntity that = (CollectshopEntity) o;

        if (scollectId != null ? !scollectId.equals(that.scollectId) : that.scollectId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scollectId != null ? scollectId.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "shopId", referencedColumnName = "shopId")
    public ShopEntity getShopByShopId() {
        return shopByShopId;
    }

    public void setShopByShopId(ShopEntity shopByShopId) {
        this.shopByShopId = shopByShopId;
    }
}
