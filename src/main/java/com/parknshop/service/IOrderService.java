package com.parknshop.service;

import com.parknshop.entity.OrdersEntity;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * Created by weina on 2016/12/22.
 */
public interface IOrderService {
    int STATE_DELETE = -1;//删除
    int STATE_BUY = 0;//下订单
    int STATE_NOT_PAY=1;//未支付
    int STATE_PAY = 2;//支付成功
    int STATE_SEND = 3;//发货
    int STATE_GET = 4;//确认收货
    int STATE_COMMENT = 5;//已经评论


    /**
     * 添加到订单，
     * @param carts 购物车id  ,号分割
     * @return
     */
    int ADD_PARAM_ERRO = 999;//参数错误
    int ADD_SAVE_ERRO = 1000;//保存失败
    int ADD_SAVE_SUCCESS = 1001;//保存成功

    /**
     *
     * @param carts
     * @return
     */
    String addOrders(int[] carts);

    /**
     *获取某个订单
     * @param orderNum
     * @return
     */
    IListBean<OrdersEntity> getOrdersList(String orderNum,int page,int lines);
    List<OrdersEntity> getOrdersList(String orderNum);

    /**
     * 获取所有订单
     * @param userId
     * @param page
     * @param lines
     * @return
     */
    IListBean<OrdersEntity> getAllList(int userId,int page,int lines);

    /**
     * 获取未支付的 订单
     * @param userId
     * @return
     */
    IListBean<OrdersEntity> getNotPayList(int userId,int page,int lines);
    List<OrdersEntity> getNotPayList(int userId);
    /**
     * 获取 已经支付的订单， 包括已经支付，正在发货，已经收货，已经评论的订单
     * @param userId
     * @return
     */
    IListBean<OrdersEntity> getPayList(int userId,int page,int lines);
    List<OrdersEntity> getPayList(int userId);
    /**
     * 获取 已经支付，但是未评论的订单
     * @param userId
     * @return
     */
    IListBean<OrdersEntity> getNotCommentLit(int userId,int page,int lines);
    List<OrdersEntity> getNotCommentLit(int userId);
    /**
     * 支付函数， 调用此函数 就代表已经完成了支付，
     * 支付的金额 交付给 parknShop ,当订单完成 收货，支付给商家
     * @param orderNum 订单号
     * @param addressId 收件地址
     * @return
     */
    int PAY_SUCCESS=1000;
    int PAY_FAIL=1001;
    int PAY_WRONG_PARAM =1002;

    int payOrder(List<String> orderNum, int addressId);

    /**
     * 取消订单
     * @param orderNum
     * @return
     */
    int CANCEL_SUCCESS = 1000;
    int CANCEL_FAIL = 2000;
    int cancelOrder(String orderNum);
    /**
     * 收到物品
     * @param orderNum
     * @return
     */
    boolean receive(String orderNum);
    //商家**************************************************************
    // *****************************************************************
    /**
     * 发货
     * @param orderNum 订单号
     * @return
     */
    boolean sendGoods(String orderNum);

    /**
     * 商家获取  已经支付了的订单
     * @param ownerId
     * @return
     */
    IListBean<OrdersEntity> getCustomerOrder(int ownerId,int page,int lines);

    /**
     * 商家获取 已经发货的订单
     * @param ownerId
     * @param page
     * @param lines
     * @return
     */
    IListBean<OrdersEntity> getFinishOrder(int ownerId,int page,int lines);

    /**
     * 管理员
     */
    IListBean<OrdersEntity> getFinishOrderAdmin(int page,int lines);

}
