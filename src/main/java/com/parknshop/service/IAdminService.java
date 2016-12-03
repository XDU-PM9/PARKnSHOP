package com.parknshop.service;

import com.parknshop.bean.ShopAndOwnerDbBean;

/**
 * Created by weina on 2016/12/2.
 */
public interface IAdminService {

    /**
     * 获取 申请商店 的列表
     * @param page  请求第几页
     * @param  lines  请求多少条
     * @return 返回一个存放了数据的 bean 接口 查看 service.IShopListBean
     *          页数不对，返回null
     */
    IListBean<ShopAndOwnerDbBean> getApplyShop(int page, int lines);

    /**
     * 同意开店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean suggestShop(int shopId);

    /**
     * 不同意开店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean rejectShop(int shopId);
}
