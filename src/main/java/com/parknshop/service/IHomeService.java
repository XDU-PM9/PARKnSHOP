package com.parknshop.service;

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
    IListBean<SortGoodsListBean> getMostSales(int page,int lines);

    /**
     * 获取 最多浏览
     * @param page
     * @param lines
     * @return
     */
    IListBean<SortGoodsListBean> getMostView(int page,int lines);

    /**
     * 获取 高价格的
     * @param page
     * @param lines
     * @return
     */
    IListBean<SortGoodsListBean> getMostValue(int page,int lines);
}
