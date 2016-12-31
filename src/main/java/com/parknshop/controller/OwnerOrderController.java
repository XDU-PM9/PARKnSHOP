package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.owner.OrderCheckRequest;
import com.parknshop.bean.owner.OrderCheckResponse;
import com.parknshop.bean.owner.UncheckedOrderRequest;
import com.parknshop.bean.owner.UncheckedOrderResponse;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.*;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.serviceImpl.builder.OwnerBuilder;
import com.parknshop.utils.DateFormat;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/owner/getUncheckedOrderPage")
    public String getUncheckedOrderPage(HttpSession session){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        return "order/uncheckedOrder.html";
    }

    @RequestMapping("/owner/getAllOrderPage")
    public String getAllOrderPage(HttpSession session){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        return "order/allCheckedOrder.html";
    }

    /**
     * 获取全部发货的订单
     * @param request
     * @param session
     * @param data
     * @return
     */
    @RequestMapping(value = "/owner/checkedOrder",method = RequestMethod.POST)
    public @ResponseBody String getAllOrder(HttpServletRequest request, HttpSession session, @RequestBody String data){
        UncheckedOrderResponse response = new UncheckedOrderResponse();
        if (!checkLogin(session)){
            response.setSuccess(false);
            return mGson.toJson(response);
        }
        response.setSuccess(true);
        UncheckedOrderRequest bean = mGson.fromJson(data,UncheckedOrderRequest.class);

        Log.debug(bean);

        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        IListBean<OrdersEntity> orderList = mOrderService.getFinishOrder(entity.getOwnerId(),bean.getPage(),bean.getLines());

        response.setCount((int) orderList.getNumer());
        response.setCurrent((int) orderList.getCurrentPage());
        response.setTotal((int) orderList.getMaxPages());

        List<UncheckedOrderResponse.DataBean> dataList = new ArrayList<>();
        List<OrdersEntity> orders = orderList.getShopList();
        for (OrdersEntity item : orders){
            UncheckedOrderResponse.DataBean dataBean = new UncheckedOrderResponse.DataBean();
            dataBean.setOrdersId(item.getOrdersId());
            dataBean.setOrderNumber(item.getOrderNumber());
            dataBean.setSingleGoodId(item.getSingleGoodId());
            dataBean.setPhoto(item.getPhoto());
            dataBean.setGoodsName(item.getGoodsName());
            dataBean.setGoodsDescribe(item.getGoodsDescribe());
            dataBean.setAmount(item.getAmount());
            dataBean.setCreateTime(item.getCreateTime());
            dataBean.setPaidTime(item.getPaidTime());
            dataBean.setPrice(item.getPrice());
            dataBean.setCommission(item.getCommission());
            dataBean.setCommissionRate(item.getCommissionRate());
            dataBean.setComment(item.getComment());
            dataBean.setCommentTime(item.getCommentTime());
            dataBean.setOrdersId(item.getOrdersId());
            dataBean.setState(item.getState());
            dataBean.setAddress(item.getAddress());
            dataBean.setReciverPhone(item.getReciverPhone());
            dataBean.setReciver(item.getReciver());
            dataBean.setPostWay(item.getPostWay());

            dataList.add(dataBean);
        }
        response.setData(dataList);
        Log.debug(mGson.toJson(response));
        return mGson.toJson(response);
    }

    /**
     * 获取店铺未处理订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/owner/uncheckedOrder")
    public @ResponseBody String getUncheckedOrder(HttpServletRequest request, HttpSession session, @RequestBody String data){
        UncheckedOrderResponse response = new UncheckedOrderResponse();
        if (!checkLogin(session)){
            response.setSuccess(false);
            return mGson.toJson(response);
        }
        response.setSuccess(true);
        UncheckedOrderRequest bean = mGson.fromJson(data,UncheckedOrderRequest.class);

        Log.debug(bean);

        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        IListBean<OrdersEntity> orderList = mOrderService.getCustomerOrder(entity.getOwnerId(),bean.getPage(),bean.getLines());

        response.setCount((int) orderList.getNumer());
        response.setCurrent((int) orderList.getCurrentPage());
        response.setTotal((int) orderList.getMaxPages());

        List<UncheckedOrderResponse.DataBean> dataList = new ArrayList<>();
        List<OrdersEntity> orders = orderList.getShopList();
        for (OrdersEntity item : orders){
            UncheckedOrderResponse.DataBean dataBean = new UncheckedOrderResponse.DataBean();
            dataBean.setOrdersId(item.getOrdersId());
            dataBean.setOrderNumber(item.getOrderNumber());
            dataBean.setSingleGoodId(item.getSingleGoodId());
            dataBean.setPhoto(item.getPhoto());
            dataBean.setGoodsName(item.getGoodsName());
            dataBean.setGoodsDescribe(item.getGoodsDescribe());
            dataBean.setAmount(item.getAmount());
            dataBean.setCreateTime(item.getCreateTime());
            dataBean.setPaidTime(item.getPaidTime());
            dataBean.setPrice(item.getPrice());
            dataBean.setCommission(item.getCommission());
            dataBean.setCommissionRate(item.getCommissionRate());
            dataBean.setComment(item.getComment());
            dataBean.setCommentTime(item.getCommentTime());
            dataBean.setOrdersId(item.getOrdersId());
            dataBean.setState(item.getState());
            dataBean.setAddress(item.getAddress());
            dataBean.setReciverPhone(item.getReciverPhone());
            dataBean.setReciver(item.getReciver());
            dataBean.setPostWay(item.getPostWay());

            dataList.add(dataBean);
        }
        response.setData(dataList);
        Log.debug(mGson.toJson(response));
        return mGson.toJson(response);
    }

    @RequestMapping(value = "/owner/check",method = RequestMethod.POST)
    public @ResponseBody String check(HttpSession session,@RequestBody String data){
        OrderCheckResponse response = new OrderCheckResponse();
        if (!checkLogin(session)){
            response.setError(true);
            return mGson.toJson(response);
        }
        OrderCheckRequest bean = mGson.fromJson(data,OrderCheckRequest.class);
        Log.debug("Check order: "+bean.getOrderNumber());
        boolean success = mOrderService.receive(bean.getOrderNumber());
        response.setError(!success);
        Log.debug(mGson.toJson(response));
        return mGson.toJson(response);
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
