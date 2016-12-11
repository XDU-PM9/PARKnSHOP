package com.parknshop.bean;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.IPictureDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.dao.daoImpl.PitureDao;
import com.parknshop.entity.PhotoEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by weina on 2016/12/11.
 */
public class GoodsDbBean {
    private int goodsId;
    private int shopId;
    private String goodsName;
    private String introduction;
    private Double price;
    private Double discount;
    private int inventory;
    private String photoGroup;
    private Date createTime;
    private int views;
    private int state;
    private List<PhotoEntity> picturePath;//图片组地址
    //hql 语句构建
    public static final String hql ="select new com.parknshop.bean.GoodsDbBean"+//
                    "(g.goodsId, s.shopId, g.goodsName, g.introduction, g.price, g.discount, g.inventory, g.photoGroup, g.createTime, g.views, g.state)  "+//
                    "from GoodsEntity as g,ShopEntity as s where g.shopByShopId.shopId = s.shopId";

    public GoodsDbBean(int goodsId, int shopId, String goodsName, String introduction, Double price, Double discount, int inventory, String photoGroup, Date createTime, int views, int state) {
        this.goodsId = goodsId;
        this.shopId = shopId;
        this.goodsName = goodsName;
        this.introduction = introduction;
        this.price = price;
        this.discount = discount;
        this.inventory = inventory;
        this.photoGroup = photoGroup;
        this.createTime = createTime;
        this.views = views;
        this.state = state;

        //初始化 图片列表
        initPhotoList();

    }
    private void initPhotoList(){
        IBaseDao<PhotoEntity> pDao = new BaseDao<>();
        IPictureDao dao = new PitureDao( pDao);
        this.picturePath = dao.getPictures(this.photoGroup);
    }

    public List<PhotoEntity> getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(List<PhotoEntity> picturePath) {
        this.picturePath = picturePath;
    }
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getPhotoGroup() {
        return photoGroup;
    }

    public void setPhotoGroup(String photoGroup) {
        this.photoGroup = photoGroup;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
