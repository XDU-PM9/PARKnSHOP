package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "collection", schema = "parknshop", catalog = "")
public class CollectionEntity {
    private Integer collectionId;
    private Timestamp createTime;
    private Integer state;
    private UserEntity userByUserId;
    private GoodsEntity goodsByGoodsId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "collectionId", nullable = false)
    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionEntity that = (CollectionEntity) o;

        if (collectionId != null ? !collectionId.equals(that.collectionId) : that.collectionId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collectionId != null ? collectionId.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
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
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId")
    public GoodsEntity getGoodsByGoodsId() {
        return goodsByGoodsId;
    }

    public void setGoodsByGoodsId(GoodsEntity goodsByGoodsId) {
        this.goodsByGoodsId = goodsByGoodsId;
    }
}
