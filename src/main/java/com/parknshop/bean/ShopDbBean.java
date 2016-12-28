package com.parknshop.bean;

import com.parknshop.entity.PhotoEntity;
import com.parknshop.entity.ShopEntity;

import java.util.List;

/**
 * Created by weina on 2016/12/28.
 */
public class ShopDbBean {
    ShopEntity shopEntity;

    public ShopEntity getShopEntity() {
        return shopEntity;
    }

    public void setShopEntity(ShopEntity shopEntity) {
        this.shopEntity = shopEntity;
    }

    public List<PhotoEntity> getPhotoEntityList() {
        return photoEntityList;
    }

    public void setPhotoEntityList(List<PhotoEntity> photoEntityList) {
        this.photoEntityList = photoEntityList;
    }

    List<PhotoEntity> photoEntityList;
}
