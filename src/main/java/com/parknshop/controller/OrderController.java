package com.parknshop.controller;

import com.parknshop.entity.AddressEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IOrderService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Lenovo on 2016/12/23.
 */


@Controller
@SessionAttributes(value = "ordersEntityList")
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IAddressService iAddressService;

    @RequestMapping(value = "/cartSubmit", method = RequestMethod.POST)
    public String cartSubmit(@RequestParam("ch") String cartId, HttpSession session, Model model) {
        String[] ch = cartId.split(",");
        int[] nums = new int[ch.length];
        for (int i = 0; i < ch.length; i++) {
            nums[i] = new Integer(ch[i]).intValue();
        }
        String ordersNum = iOrderService.addOrders(nums);
        if (ordersNum.equals(null)) {
            model.addAttribute("message","Check out failure,please check out again!");
            return "redirect:../listProduct?requestPage=1";
        } else {
            model.addAttribute("sdsdf", ordersNum);
            return "redirect:/order/listCart?OrdersNum={sdsdf}";
        }
    }


    @RequestMapping(value ="/toOrdersCenter",method = RequestMethod.GET)
    public String  toOrdersCenter(Model model)
    {
        List<OrdersEntity>  ordersEntityList=iOrderService.getOrdersList("1");
        model.addAttribute("orders",ordersEntityList);
        return "/customer/order_center.jsp";
    }
    @RequestMapping(value = "/listCart", method = RequestMethod.GET)
    public String listCart(@RequestParam("OrdersNum") String ordersNum, Model model, ModelMap modelMap) {
        List<OrdersEntity> ordersEntityList = iOrderService.getOrdersList(ordersNum);
        List<AddressEntity> addressEntities = iAddressService.getAllAddressByUserId(ordersEntityList.get(0).getUserByUserId().getUserId());
        modelMap.put("ordersEntityList",ordersEntityList);
        model.addAttribute("ordersEntityList", ordersEntityList);
        model.addAttribute("addressEntityList", addressEntities);
        return "/customer/buy.jsp";
    }


    @RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
    @ResponseBody
    public String saveAddress(@RequestParam String province,
                              @RequestParam String country,
                              @RequestParam String others,
                              @RequestParam String name,
                              @RequestParam int zip,
                              @RequestParam long phoneNum,
                              HttpSession session) {
        int userId = getUserId(session);
        if (iAddressService.insertAddressEntity(province, country, others, name, phoneNum, zip, userId)) {
            return "true";
        } else {
            return "false";
        }
    }


    @RequestMapping(value = "/deleteAddress", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAddress(@RequestParam int addressId, HttpSession session) {
//        if (getUserId(session)<0) {
//             return "redirect:customer/login";
//        } else {
        iAddressService.deleteAddressEntity(addressId);
        return "redirect:listAddress";
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
