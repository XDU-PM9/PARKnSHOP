package com.parknshop.controller;

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
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String showDetails(Model model){
        model.addAttribute("shopId",request.getParameter("shopId"));
        return "shop/shop.jsp";
    }
}
