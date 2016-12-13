package com.parknshop.controller;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.customerService.ICartService;
import com.parknshop.service.customerService.IGetList;
import com.parknshop.service.customerService.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H on 2016/11/30.
 */
@Controller
public class CartController {
    @Autowired
    ICartService cartService;
    @Autowired
    IGetList productsList;

//    @RequestMapping(value = "/changeAmount",method = RequestMethod.POST)
//    public @ResponseBody String changeAmount(HttpServletRequest request, HttpSession session)
//    {
//        try {
//            int amount = Integer.parseInt(request.getParameter("amount"));
//            if (request.getParameter("cartId") != null) {
//                return String.valueOf(cartService.changeAmount(Integer.parseInt(request.getParameter("cartId")), amount));
//            } else {
//                return String.valueOf(cartService.changeAmount(getUserId(session), Integer.parseInt(request.getParameter("goodsId")), amount));
//            }
//        }catch (NumberFormatException e){
//            return "Paramter error";
//        }
//    }

    @RequestMapping(value = "/changeAmount",method = RequestMethod.GET)
    public  String changeAmount(@RequestParam int cartId,@RequestParam int amount, HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:/customer/login";
        }else {
            cartService.changeAmount(cartId,amount);
            return  "redirect:/listProduct?start=1&count=5";
        }
    }

    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
    public  String listCart(@RequestParam int start,@RequestParam int count,HttpSession session,Model model)
    {

        int userId=getUserId(session);
        if(userId<1){
            return "redirect:/customer/login";
        }else {
            List<CartEntity> cartEntityList=cartService.getProductsByPage(getUserId(session),start,count);
            if(null!=cartEntityList) {
                List<Cart> products=productsList.getCarts(cartEntityList);
                model.addAttribute("cartList",products);
            }
        }
        return "/customer/Shop_Cart.jsp";
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public  String addCart(@RequestParam int goodsId,@RequestParam int amount,HttpSession session) {
        int userId = getUserId(session);
//        userId=12;
        if (userId < 1) {
            return "redirect:/customer/login";
        } else {
            cartService.addProduct(userId, goodsId, amount);
            return "redirect:/listProduct?start=1&count=10";
        }
    }

    @RequestMapping(value = "/removeProduct",method = RequestMethod.GET)
    public  String removeProduct(@RequestParam int goodsId,HttpSession session){
        cartService.removeProduct(goodsId);
        return "redirect:/listProduct?start=1&count=5";
    }

    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}