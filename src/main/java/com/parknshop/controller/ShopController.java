package com.parknshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by SONG on 2016/12/20.
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String showDetails(){
        return "shop/shop.jsp";
    }
}
