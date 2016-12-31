package com.parknshop.controller;

import com.parknshop.bean.ShopDbBean;
import com.parknshop.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SONG on 2016/12/20.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    public static final String SHOP = "ShopController.Shop";
    final IOwnerService mOwnerService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public ShopController(IOwnerService mOwnerService) {
        this.mOwnerService = mOwnerService;
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String showDetails(Model model){
        model.addAttribute("shopId",request.getParameter("shopId"));
        int id = Integer.parseInt(request.getParameter("shopId"));
        ShopDbBean shop = mOwnerService.getShop(id);
        request.setAttribute(SHOP,shop);

        return "shop/shop.jsp";
    }
}
