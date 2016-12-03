package com.parknshop.controller;

import com.parknshop.entity.CartEntity;
import com.parknshop.service.customerService.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
/**
 * Created by H on 2016/11/30.
 */
@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping(value = "/changeAmount",method = RequestMethod.GET)
    public String  changeAmount(@RequestParam  int cartId,@RequestParam int ammount)
    {
        cartService.changeAmount(cartId,ammount);
        return "listCart";
    }


    @RequestMapping(value = "/listCart",method = RequestMethod.GET)
    public String listCart(@RequestParam int userId,Model model)
    {
        List<CartEntity> cartEntityList=cartService.find(userId);
        model.addAttribute("cartEntityList", cartEntityList);
        return "cartList";
    }


}
