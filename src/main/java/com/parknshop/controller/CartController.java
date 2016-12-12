package com.parknshop.controller;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.ICartService;
import com.parknshop.service.customerService.IGetList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 改变数量
     * @param goodsId
     * @param amount
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeAmount",method = RequestMethod.POST)
    public @ResponseBody String changeAmount(@RequestParam int goodsId,@RequestParam int amount, HttpSession session)
    {
        return String.valueOf(cartService.changeAmount(getUserId(session), goodsId, amount));
    }

    /**
     * 列出购物车
     * @param start
     * @param count
     * @param session
     * @return
     */
    @RequestMapping(value = "/listProduct",method = RequestMethod.POST)
    public @ResponseBody List listCart(@RequestParam int start,@RequestParam int count,HttpSession session)
    {
        List<CartEntity> cartEntityList=cartService.getProducts(getUserId(session),start,count);
        //返回一个商品列表，包含了商品的一些基本信息
        if(null!=cartEntityList) {
            return productsList.getCarts(cartEntityList);
        }else{
            List list=new ArrayList();
            list.add("error");
            return list;
        }
    }

    /**
     * 添加到购物车
     * @param goodsId
     * @param amount
     * @param session
     * @return "true"、"false" or "please login"
     */
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public @ResponseBody String addCart(@RequestParam int goodsId,@RequestParam int amount,HttpSession session){
        int userId=getUserId(session);
//        userId=12;
        if(userId<1){
            return "please login";
        }
        return String.valueOf(cartService.addProduct(userId,goodsId,amount));
    }

    /**
     * 从购物车中删除
     * @param goodsId
     * @param session
     * @return
     */
    @RequestMapping(value = "/removeProduct",method = RequestMethod.POST)
    public @ResponseBody String removeProduct(@RequestParam int goodsId,HttpSession session){
        return String.valueOf(cartService.removeProduct(getUserId(session),goodsId));
    }

    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}