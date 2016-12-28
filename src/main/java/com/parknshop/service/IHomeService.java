package com.parknshop.service;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.service.IListBean;
import com.parknshop.service.serviceImpl.listBean.SortGoodsListBean;

/**
 * Created by weina on 2016/12/27.
 */
public interface IHomeService {

    /**
     * 获取 销售量最高 的商品
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsDbBean> getMostSales(int page, int lines);

    /**
     * 获取 最多浏览
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsDbBean> getMostView(int page,int lines);

    /**
     * 获取 高价格的
     * @param page
     * @param lines
     * @return
     */
    IListBean<GoodsDbBean> getMostValue(int page,int lines);
}