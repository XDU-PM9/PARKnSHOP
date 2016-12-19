package com.parknshop.bean;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IAdvertisement;

import java.util.Date;

/**
 * Created by weina on 2016/12/19.
 * 广告
 */
public class AdvertisementDbBean {

    private int advertId;
    private int type;
    private int userId;
    private int typeId;
    private Date startTime;
    private double price;
    private int state;
    //构造函数初始化，获取详细信息
    GoodsEntity goodsEntity;
    ShopEntity shopEntity;
    OwnerEntity ownerEntity;

    public final static String hql ="select new com.parknshop.bean.AdvertisementDbBean"+//
                        "(advertId,type,userId,typeId,startTime, price,state)" +//
                        "from AdvertEntity  where 1=1 ";

    public AdvertisementDbBean(int advertId, int type, int userId, int typeId, Date startTime, double price, int state) {
        this.advertId = advertId;
        this.type = type;
        this.userId = userId;
        this.typeId = typeId;
        this.startTime = startTime;
        this.price = price;
        this.state = state;
        try {
            if (type == IAdvertisement.AD_TYPE_SHOP) {
                initShopEntity();
            } else if (type == IAdvertisement.AD_TYOE_GOODS) {
                initGoodsEntity();
            }
            initOwnerEntity();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initGoodsEntity(){
        IBaseDao<GoodsEntity> goodsEntityIBaseDao = new BaseDao<>();
        goodsEntity = goodsEntityIBaseDao.get(GoodsEntity.class,this.typeId);
    }
    private void initShopEntity(){
        IBaseDao<ShopEntity> shopEntityIBaseDao = new BaseDao<>();
        shopEntity = shopEntityIBaseDao.get(ShopEntity.class,this.typeId);
    }
    private void initOwnerEntity(){
        IBaseDao<OwnerEntity> ownerEntityIBaseDao = new BaseDao<>();
        ownerEntity = ownerEntityIBaseDao.get(OwnerEntity.class,this.userId);
    }
    public int getAdvertId() {
        return advertId;
    }

    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return price;
    }

    public int getState() {
        return state;
    }

    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public OwnerEntity getOwnerEntity() {
        return ownerEntity;
    }

}
