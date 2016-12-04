package com.parknshop.service;

import com.parknshop.bean.ShopAndOwnerDbBean;

import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
public interface IListBean<T> {

    /**
     *
     * @param page 第几页
     * @param lines  多少行
     */
    void init(int page,int lines);
    void init(Object object,int page,int lines);
    /**
     * 获取   商店 详细内容列表
     * @return 返回内容
     */
    List<T> getShopList();

    /**
     * 获取 当前页面
     * @return
     */
    long getCurrentPage();
    /**
     * 一共有多少页面
     */
    long getMaxPages();
    /**
     * 获取 数量
     */
    long getNumer();
}
