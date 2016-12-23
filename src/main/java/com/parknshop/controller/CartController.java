package com.parknshop.controller;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IOrderService;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.customerService.ICartService;
import com.parknshop.service.customerService.IGetList;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    IOrderService orderService;

    private  IUserService mUserService;

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
    public  String changeAmount(@RequestParam int cartId, @RequestParam int amount, HttpSession session, Model model)
    {
//        int userId=getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        }else {
            if(amount>0) {
                cartService.changeAmount(cartId, amount);
                return "redirect:/listProduct?requestPage=1";
            }else
            {
                model.addAttribute("husdfdskljaf",cartId);
                return "redirect:/removeProduct?goodsId={husdfdskljaf}";
            }
//        }
    }

    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
    public  String listCart(@RequestParam int requestPage,HttpSession session,Model model)
    {
//        int userId=getUserId(session);
//        if (userId<0) {
//            return "forward:/customer/login";
//        }else {
            List<CartEntity> cartEntityList=cartService.getProductsByPage(getUserId(session),requestPage,10);
            if(null!=cartEntityList) {
                List<Cart> products=productsList.getCarts(cartEntityList);
                model.addAttribute("cartList",products);
            }
//        }
        return "/customer/cart.jsp";
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public  String addCart(@RequestParam int goodsId,@RequestParam int amount,HttpSession session) {
        int userId = getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        } else {
            cartService.addProduct(userId, goodsId, amount);
            return "redirect:/listProduct?requestPage=1";
//        }
    }


    @RequestMapping(value = "/removeProduct",method = RequestMethod.GET)
    public  String removeProduct(@RequestParam int goodsId,HttpSession session){
//        int userId = getUserId(session);
//        if (userId<0) {
//            return "redirect:/customer/login";
//        }
//        else {
            cartService.removeProduct(goodsId);
            return "redirect:/listProduct?requestPage=1";
//        }
    }


    @RequestMapping(value = "/cartSubmit",method = RequestMethod.POST)
    public String cartSubmit(@RequestParam("ch") String  cartId, HttpSession session, Model model)
    {
        String[] ch=cartId.split(",");
        int[] nums=new int[ch.length];
        for(int i=0;i<ch.length;i++)
        {
            nums[i]=new Integer(ch[i]).intValue();
        }
        if(orderService.addOrders(nums)-1001==0) {

            return "redirect:/";
        }
        else {
                return  "";
        }



    }
    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}