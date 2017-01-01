package com.parknshop.service.customerService;

import com.parknshop.entity.ShopEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-10.
 */
public interface ISearchShops {
    public ShopEntity searchShop(int shopId);
    public List<ShopEntity> searchShop(String shopName);
    public List<ShopEntity> searchShop(String shopName,int start,int count);
    public long getCount(String shopName);
    public long getCount();
    //设置规则
    public ISearchShops setRuler(Map ruler);
    //根据Map进行完成所有搜索设置
    public ISearchShops setByMap(Map map);
    //初始化搜索
    public ISearchShops initRuler();
    public ISearchShops setOrderByTime();
    public ISearchShops setOrderByTimeDesc();
    public ISearchShops setOrderByViews();
    public ISearchShops setOrderByViewsDesc();

    //设置shopname
    public ISearchShops setShopNmae(String shopNmae);
    //设置不对搜索进行过滤，即显示所有状态下的店铺
    public ISearchShops setAnyState();
    //设置分页
    public ISearchShops setScope(int start,int count);
    //设置完毕后调用此函数进行搜索
    public List<ShopEntity> searchShop();
}
