package com.parknshop.service.customerService;

import com.parknshop.entity.ShopEntity;

import java.util.List;

/**
 * Created by wei on 16-12-10.
 */
public interface ISearchShops {
    public ShopEntity searchShop(int shopId);
    public List<ShopEntity> searchShop(String shopName);
    public List<ShopEntity> searchShop(String shopName,int start,int count);
    public long getCount(String shopName);
}
