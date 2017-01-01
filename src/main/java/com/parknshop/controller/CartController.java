package com.parknshop.controller;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.customerService.ICartService;
import com.parknshop.service.customerService.IGetList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private IUserService mUserService;

    @RequestMapping(value = "/changeAmount", method = RequestMethod.GET)
    public String changeAmount(@RequestParam int cartId, @RequestParam int amount, HttpSession session, Model model, HttpServletRequest request) {
//        int userId=getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        }else {
        if (amount > 0) {
            cartService.changeAmount(cartId, amount);
//            if("XMLHttpRequest".equals(request.getHeaders("x-requested-with"))){
//                return "success";
//            }
            return "redirect:/listProduct?requestPage=1";
        } else {
            model.addAttribute("husdfdskljaf", cartId);
//            if("XMLHttpRequest".equals(request.getHeaders("x-requested-with"))){
//                return "fail";
//            }
            return "redirect:/removeProduct?goodsId={husdfdskljaf}";
        }
//        }
    }

    @RequestMapping(value = "/changeAmount", method = RequestMethod.POST)
    @ResponseBody
    public String changeAmountPost(@RequestParam int cartId, @RequestParam int amount, HttpSession session, Model model, HttpServletRequest request) {
        if (amount > 0) {
            cartService.changeAmount(cartId, amount);
            return "success";
        } else {
            model.addAttribute("husdfdskljaf", cartId);
            return "false";
        }
    }


    @RequestMapping(value = "/listProduct", method = RequestMethod.GET)
    public String listCart(@RequestParam int requestPage, HttpSession session, Model model) {
//        int userId=getUserId(session);
//        if (userId<0) {
//            return "forward:/customer/login";
//        }else {
        List<CartEntity> cartEntityList = cartService.getProductsByPage(getUserId(session), requestPage, 10);
        if (null != cartEntityList) {
            List<Cart> products = productsList.getCarts(cartEntityList);
            model.addAttribute("cartList", products);
        }
//        }
//        return "/customer/cart.jsp";
        return "/customer/carts.jsp";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addCart(@RequestParam int goodsId, @RequestParam int amount, HttpSession session) {
        int userId = getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        } else {
        cartService.addProduct(userId, goodsId, amount);
        return "redirect:/listProduct?requestPage=1";
//        }
    }


    @RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
    public String removeProduct(@RequestParam int goodsId, HttpSession session) {
//        int userId = getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        }
//        else {
        cartService.removeProduct(goodsId);
        return "redirect:/listProduct?requestPage=1";
//        }
    }


    private int getUserId(HttpSession session) {
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        } catch (Exception e) {
            return -1;
        }
    }
}