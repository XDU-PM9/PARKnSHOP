package com.parknshop.controller;

import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IOrderService;
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
        String orderNum = (String)req.getAttribute("orderNum");
        String address = (String)req.getAttribute("addressId");
        //二维码
        String url = pay.getQRCode(session,Pay.IP+"/pay/p?orderNum="+orderNum+"&addressId="+address);


        req.setAttribute("imgUrl",url);
        req.getRequestDispatcher("WEB-INF/pages/test.jsp").forward(req,resp);
    }
    //支付按钮
    @RequestMapping(value = "/p",method = RequestMethod.GET)
    public String payButton(Model model,
                            HttpServletRequest req){
        String orderNum = (String)req.getAttribute("orderNum");
        String address = req.getParameter("addressId");

        model.addAttribute("addressId",address);
        model.addAttribute("orderNum",orderNum);
        return "pay.jsp";
    }


    @RequestMapping(value = "/f",method = RequestMethod.GET)
    public String finalPay(ModelMap modelMap,
            HttpServletRequest req, HttpSession session){
        String orderNum = (String)req.getAttribute("orderNum");
        List<String> sList = new ArrayList<>();
        sList.add(orderNum);
        String msg;
        if(null == sList){
            msg = "error , i am sorry";
            req.setAttribute("msg",msg);
            return "payResult.jsp";
        }

        String address = req.getParameter("addressId");


        int result = orderService.payOrder((String[])sList.toArray(),Integer.parseInt(address));

        if(result == IOrderService.PAY_SUCCESS){
            msg = "pay success,thank you! you have bought:" + String.valueOf(sList.size());
        }else {
            msg=" pay fail.";
        }
        req.setAttribute("msg",msg);
        return "payResult.jsp";
    }
}
