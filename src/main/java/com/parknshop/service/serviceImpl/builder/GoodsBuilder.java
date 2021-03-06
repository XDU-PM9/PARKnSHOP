package com.parknshop.service.serviceImpl.builder;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IGoodsBuilder;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceImpl.listBean.PersonShopListBean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by weina on 2016/12/11.
 */
@Component
public class GoodsBuilder implements IGoodsBuilder {
    private int shopId;
    private int ownerId;
    private String goodsName;
    private String introduction;
    private Double price;
    private Double discount;
    private int inventory;
    private String photoGroup;
    private Timestamp createTime;
    private int views;
    private int state;

    private String type;
    private int sales;

    private String postWay;
    public GoodsBuilder() {
        clear();
    }


    @Override
    public IGoodsBuilder clear() {
        this.shopId = 0;
        this.ownerId = 0;
        this.goodsName = "";
        this.introduction = "";
        this.price = 0.0;
        this.discount = 0.0;
        this.inventory = 0;
        this.photoGroup = "";
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.views = 0;
        this.state = GOOD_STATE_USING;

        this.type="unkown";
        this.sales=0;
        this.postWay="unkown";
        return this;
    }

    @Override
    public IGoodsBuilder setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public GoodsEntity builder() throws Exception {
        GoodsEntity goodsEntity = new GoodsEntity();
        if((this.shopId =getShopId())<0){
            throw new Exception("错误 id 或没有商店");
        }
        //暂时不处理折扣
        // TODO:处理折扣
        ShopEntity entity =new ShopEntity();
        entity.setShopId(this.shopId);
        goodsEntity.setShopByShopId(entity);
        goodsEntity.setGoodsName(this.goodsName);
        goodsEntity.setIntroduction(this.introduction);
        goodsEntity.setPrice(this.price);
        goodsEntity.setDiscount(0.0);
        goodsEntity.setInventory(this.inventory);
        goodsEntity.setPhotoGroup(this.photoGroup);
        goodsEntity.setCreateTime(this.createTime);
        goodsEntity.setViews(this.views);
        goodsEntity.setState(this.state);
        goodsEntity.setType(this.type);
        goodsEntity.setSales(this.sales);
        goodsEntity.setPostWay(this.postWay);
        return goodsEntity;
    }

    @Override
    public IGoodsBuilder setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    @Override
    public IGoodsBuilder setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    @Override
    public IGoodsBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public IGoodsBuilder setInventory(int inventory) {
        this.inventory = inventory;
        return this;
    }
    @Override
    public IGoodsBuilder setType(String type) {
        this.type = type;
        return  this;
    }

    @Override
    public IGoodsBuilder setPostWay(String postWay) {
        this.postWay =postWay;
        return this;
    }

    private int getShopId(){
        IBaseDao<ShopAndOwnerDbBean> mDao = new BaseDao<>();
        String hql = PersonShopListBean.hql+//
                    " and o.ownerId = ? "+//
                    " and s.state = ? ";
        Object[] param = {this.ownerId, IOwnerService.SHOP_STATE_USING};
        try {
            ShopAndOwnerDbBean bean = mDao.get(hql, param);
            if(null!= bean){
                return bean.getShopId();
            }else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
