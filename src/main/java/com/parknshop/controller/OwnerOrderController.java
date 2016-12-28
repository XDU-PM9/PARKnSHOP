package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.*;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.serviceImpl.builder.OwnerBuilder;
import com.parknshop.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by SONG on 2016/12/28.
 * @author 宋正腾
 * OwnerController太大了，拆分
 */
@Controller
public class OwnerOrderController {
    public static final String UNCHECKED_ORDER = "OwnerOrderController.uncheckedOrder";

    final IOrderService mOrderService;
    final IUserService mUserService;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @Autowired
    public OwnerOrderController(IOrderService orderService,IUserService userService) {
        this.mOrderService = orderService;
        this.mUserService = userService;
    }

    /**
     * 获取店铺未处理订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/owner/uncheckedOrder")
    public String getUncheckedOrder(HttpServletRequest request,HttpSession session){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        IListBean<OrdersEntity> orderList = mOrderService.getCustomerOrder(entity.getOwnerId());
        request.setAttribute(UNCHECKED_ORDER,orderList);

        return "order/unchecked.jsp";
    }

    boolean checkLogin(HttpSession session) {
        if (!mUserService.isLogin()) {
            return false;
        }
        //防止不同类型用户的登陆影响
        try {
            OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity) {
                throw new Exception("未登陆");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
