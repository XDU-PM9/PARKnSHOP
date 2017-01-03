package com.parknshop.controller;

import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.IOrderService;
import com.parknshop.service.serviceImpl.OrderService;
import com.parknshop.utils.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weina on 2016/12/27.
 */
@Controller
@RequestMapping(value = "/pay")
public class PayController {
    private final Pay pay;
    protected final IAdvertisement iAdvertisement;

    private final IOrderService orderService;
    @Autowired
    public PayController(Pay pay, IOrderService orderService, IAdvertisement iAdvertisement) {
        this.pay = pay;
        this.orderService = orderService;
        this.iAdvertisement = iAdvertisement;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public void payGoods(Model model,
                           HttpServletRequest req,
                           HttpServletResponse resp,
                           HttpSession session) throws ServletException, IOException {
        String orderNum = req.getParameter("orderNum");
        String address = req.getParameter("addressId");
        //二维码
        String url = pay.getQRCode(session,Pay.IP+"/pay/p?orderNum="+orderNum+"&addressId="+address);

        req.setAttribute("path","/order/listOrder");
        req.setAttribute("imgUrl",url);
        req.getRequestDispatcher("WEB-INF/pages/test.jsp").forward(req,resp);
    }
    //支付按钮
    @RequestMapping(value = "/p",method = RequestMethod.GET)
    public String payButton(Model model,
                            HttpServletRequest req){
        String orderNum = req.getParameter("orderNum");
        String address = req.getParameter("addressId");

        String type = req.getParameter("type");
        String typeId = req.getParameter("typeId");
        String typeString="";
        if(null !=type && null !=typeId ) {

            if (IAdvertisement.AD_TYPE_SHOP == Integer.parseInt(type)) {
                typeString = "shop";
            } else if (IAdvertisement.AD_TYOE_GOODS == Integer.parseInt(type)) {
                typeString = "goods";
            } else {
                typeString = "else";
            }
        }


        model.addAttribute("typeString",typeString);
        model.addAttribute("type",type);
        model.addAttribute("typeId",typeId);
        model.addAttribute("addressId",address);
        model.addAttribute("orderNum",orderNum);
        return "pay.jsp";
    }


    @RequestMapping(value = "/f",method = RequestMethod.GET)
    public String finalPay(ModelMap modelMap,
            HttpServletRequest req, HttpSession session){
        String orderNum = req.getParameter("orderNum");
        List<String> sList = OrderService.sListMap.get(orderNum);
        if(null == sList) {//有可能传入进来的就是 orderNum
            sList = new ArrayList<>();
            sList.add(orderNum);
        }
        String msg;
        if(null == sList){
            msg = "error , i am sorry";
            req.setAttribute("msg",msg);
            return "payResult.jsp";
        }

        String address = req.getParameter("addressId");


        int result = orderService.payOrder(sList,Integer.parseInt(address));
        if(result == IOrderService.PAY_SUCCESS){
            msg = "pay success,thank you! you have bought:" + String.valueOf(sList.size());
        }else {
            msg=" pay fail.";
        }
        req.setAttribute("msg",msg);
        return "payResult.jsp";
    }

    //广告****************************************
    //*******************************************
    @RequestMapping(value = "/a",method = RequestMethod.GET)
    public String payAdvert(HttpServletRequest req,
                            HttpSession session){
        String type = req.getParameter("type");
        String typeId = req.getParameter("typeId");


        String url = pay.getQRCode(session,Pay.IP+"/pay/p?type="+type+"&typeId="+typeId);
        if("0".equals(type)) {
            req.setAttribute("path", "/owner/query");
        }else if("1".equals(type)){
            req.setAttribute("path", "/owner/goodsPage");
        }
        req.setAttribute("imgUrl",url);
        return "test.jsp";
    }

    @RequestMapping(value = "/af",method = RequestMethod.GET)
    public String fianlAdvertPay(HttpServletRequest req){
        String type = req.getParameter("type");
        String typeId = req.getParameter("typeId");
        int result=0;
        String msg;
        if(type.equals(String .valueOf(IAdvertisement.AD_TYPE_SHOP))){
            result  = iAdvertisement.addAdvertisementShop(Integer.parseInt(typeId));
        }else if(type.equals(String .valueOf(IAdvertisement.AD_TYOE_GOODS))){
            result = iAdvertisement.addAdvertisementGoods(Integer.parseInt(typeId));
        }else {
            msg = "else erro";
        }
        switch (result) {
            case IAdvertisement.ALREADY_EXISTS:
                msg = "already exist";
                break;
            case IAdvertisement.ILLEGAL_SHOP:
            case IAdvertisement.ILLEGAl_GOODS:
                msg = "illegal apply";
                break;
            case IAdvertisement.ADD_SUCCESS:
                msg = "success";
                break;
            default:
                msg = "else erro";
                break;

        }
        req.setAttribute("msg",msg);

        return "payResult.jsp";
    }

}
