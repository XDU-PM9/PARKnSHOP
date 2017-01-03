package com.parknshop.controller;

import com.parknshop.entity.AddressEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOrderService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.IAddressService;
import com.parknshop.service.serviceImpl.OrderService;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/cartSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String cartSubmit(@RequestParam("ch") String cartId, HttpSession session, Model model,HttpServletRequest request) {
        String[] ch = cartId.split(",");
        int[] nums = new int[ch.length];
        for (int i = 0; i < ch.length; i++) {
            nums[i] = new Integer(ch[i]).intValue();
        }
        String ordersNum = iOrderService.addOrders(nums);
        if (null == ordersNum) {
            model.addAttribute("message", "Check out failure,please check out again!");
            //ajax请求
            if("XMLHttpRequest"==request.getHeader("X-Requested-With")){
                return "/listProduct?requestPage=1";
            }
            return "/listProduct?requestPage=1";
        } else {
            model.addAttribute("sdsdf", ordersNum);
            if("XMLHttpRequest"==request.getHeader("X-Requested-With")){
                return "/order/listCart?OrderNum="+ordersNum;
            }
            return "/order/listCart?OrdersNum="+ordersNum;
        }
    }


    @RequestMapping(value = "/confirmReceive",method = RequestMethod.POST)
    @ResponseBody
    public  String  confirmReceive(@RequestParam("orderNum") String  orderNum)
    {
        if(iOrderService.receive(orderNum))
        {
            return "success";
        }
        else {
            return "fail";
        }
    }

    @RequestMapping(value = "/listCart", method = RequestMethod.GET)
    public String listCart(@RequestParam("OrdersNum") String ordersNum, Model model, ModelMap modelMap) {
        List<String> listOrderNumber =  OrderService.sListMap.get(ordersNum);
        List<OrdersEntity> ordersEntityList = new ArrayList<>();
        if(null != listOrderNumber) {
            for (String number : listOrderNumber) {
                List<OrdersEntity> ordersEntities = iOrderService.getOrdersList(number);
                ordersEntityList.addAll(ordersEntities);
            }
        }else {
            try {
                List<OrdersEntity> ordersEntities = iOrderService.getOrdersList(ordersNum);
                ordersEntityList.addAll(ordersEntities);
            } catch (Exception e) {
                e.printStackTrace();
                Log.debug("" + "addOrderNumber error maybe it is null");
            }
        }

        for (Iterator<OrdersEntity> it = ordersEntityList.iterator(); it.hasNext(); ) {
            OrdersEntity val = it.next();
            if (val.getState() != IOrderService.STATE_NOT_PAY) {
                it.remove();
            }
        }
        if (ordersEntityList.size() > 0) {
            List<AddressEntity> addressEntities = iAddressService.getAllAddressByUserId(ordersEntityList.get(0).getUserByUserId().getUserId());
            modelMap.put("ordersEntityList", ordersEntityList);
            model.addAttribute("ordersEntityList", ordersEntityList);
            model.addAttribute("addressEntityList", addressEntities);
            model.addAttribute("orderNumber",ordersNum);
            return "/customer/buy.jsp";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/cartSubmit", method = RequestMethod.PUT)
    @ResponseBody
    public String cartSubmitPut(@RequestBody List<String> cartIds) {
        int[] orders = new int[cartIds.size()];
        try {
            for (int i = 0; i < cartIds.size(); i++) {
                orders[i] = Integer.parseInt(cartIds.get(i));
            }
        } catch (NumberFormatException e) {
            return "error";
        }
        String orderNum = iOrderService.addOrders(orders);
        if(null==orderNum){
            return "Generator order fail,please try again";
        }
        return "/order/listCart?OrdersNum="+orderNum;
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

    @RequestMapping("/listOrder")
    public String listOrder(HttpSession session, Model model) {
        int page = 1;
        int lines = 5;
        String pageString = request.getParameter("page");
        String linesString = request.getParameter("lines");
        try {
            page = null != pageString ? Integer.parseInt(pageString) : 1;
            lines = null != linesString ? Integer.parseInt(linesString) : 5;
        } catch (NumberFormatException e) {
            Log.debug("listOrder参数转换为int异常");
        }
        int userId = getUserId(session);
        int num=iOrderService.getOrdersNum(userId);
        IListBean<OrdersEntity> ordersList = iOrderService.getAllList(userId, page, lines);
//        switch (type){
//            //所有订单
//            case "":;break;
//            //未支付订单
//            case "notPay":ordersList= iOrderService.getNotPayList(userId,page,lines);break;
//            //已支付订单
//            case "pay":ordersList=iOrderService.getPayList(userId,page,lines);break;
//            //已支付未评论订单
//            case "notComment":ordersList=iOrderService.getNotCommentLit(userId,page,lines);break;
//        }
        if (ordersList.getNumer() > 0) {
            model.addAttribute("nums",num);
            model.addAttribute("orderList", ordersList.getShopList());
            model.addAttribute("pages", ordersList.getMaxPages());
            model.addAttribute("page", ordersList.getCurrentPage());
        }else
        {
            model.addAttribute("nums",num);
        }
        return "/customer/orders_center.jsp";
    }

    @RequestMapping("/cancel")
    public String cancleOrder(@RequestParam String OrdersNum) {
        iOrderService.cancelOrder(OrdersNum);
        return "redirect:/listProduct?requestPage=1";
    }

    private int getUserId(HttpSession session) {
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        } catch (Exception e) {
            return -1;
        }
    }
}
