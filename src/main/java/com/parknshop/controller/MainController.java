package com.parknshop.controller;

import com.parknshop.bean.AdvertisementDbBean;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.IListBean;
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

    public static final String AD_SHOP = "shop";
    public static final String AD_GOODS = "goods";
    public static final String HOT = "hot";

    private final IAdvertisement mAdveService;

    @Autowired
    public MainController(IAdvertisement adveService) {
        mAdveService = adveService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        IListBean<AdvertisementDbBean> adShop = mAdveService.getAllShop(1,10);
        IListBean<AdvertisementDbBean> adGoods = mAdveService.getAllGoods(1,10);

        request.setAttribute(AD_SHOP,adShop);
        request.setAttribute(AD_GOODS,adGoods);
        return "index_new.jsp";
    }
}