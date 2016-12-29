package com.parknshop.controller;

import com.parknshop.bean.AdvertisementDbBean;
import com.parknshop.bean.GoodsDbBean;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.IHomeService;
import com.parknshop.service.IListBean;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by niewenzhi on 2016/11/24.
 */
@Controller
public class MainController {

    public static final String AD_SHOP = "AD_SHOP";
    public static final String AD_GOODS = "AD_GOODS";
    public static final String HOT = "HOT_GOODS";
    private final IAdvertisement mAdveServiceOne;
    private final IAdvertisement mAdveServiceTwo;
    private final IHomeService mHomeService;

    @Autowired
    public MainController( IHomeService iHomeService, IAdvertisement mAdveServiceTwo, IAdvertisement mAdveServiceOne) {
        mHomeService = iHomeService;
        this.mAdveServiceTwo = mAdveServiceTwo;
        this.mAdveServiceOne = mAdveServiceOne;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        //热门商品
        IListBean<GoodsDbBean> hotGoods = mHomeService.getMostView(1,5);
        request.setAttribute(HOT,hotGoods);

        //广告店铺
        IListBean<AdvertisementDbBean> adShop = mAdveServiceOne.getTopShop(1,10);
        request.setAttribute(AD_SHOP,adShop);
        Log.debug(adShop);

        //广告商品
        IListBean<AdvertisementDbBean> adGoods = mAdveServiceTwo.getTopGoods(1,10);
        request.setAttribute(AD_GOODS,adGoods);
        Log.debug(adGoods);

        return "index_new.jsp";
    }


}