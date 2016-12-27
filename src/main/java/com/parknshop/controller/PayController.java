package com.parknshop.controller;

import com.parknshop.service.IOrderService;
import com.parknshop.utils.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weina on 2016/12/27.
 */
@Controller
@RequestMapping(value = "/pay")
public class PayController {
    private final Pay pay;

    private final IOrderService orderService;
    @Autowired
    public PayController(Pay pay, IOrderService orderService) {
        this.pay = pay;
        this.orderService = orderService;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public void payGoods(Model model,
                           HttpServletRequest req,
                           HttpServletResponse resp,
                           HttpSession session) throws ServletException, IOException {

        String number = "28e3cd11-adce-4943-920c-09affe92a185";
        String address = "2";
        //二维码
        String url = pay.getQRCode(session,Pay.IP+"/pay/p?orderNum="+number+"&addressId="+address);



        req.setAttribute("imgUrl",url);
        req.getRequestDispatcher("WEB-INF/pages/test.jsp").forward(req,resp);
    }
    //支付按钮
    @RequestMapping(value = "/p",method = RequestMethod.GET)
    public String payButton(Model model,
                            HttpServletRequest req){
        String number = req.getParameter("orderNum");
        String address = req.getParameter("addressId");

        model.addAttribute("orderNum",number);
        model.addAttribute("addressId",address);
        return "pay.jsp";
    }
    @RequestMapping(value = "/f",method = RequestMethod.GET)
    public void finalPay(HttpServletRequest req){
        String number = req.getParameter("orderNum");
        String address = req.getParameter("addressId");

        System.out.println(orderService.payOrder(number,Integer.parseInt(address)));
    }
}
