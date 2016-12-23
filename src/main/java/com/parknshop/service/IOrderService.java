package com.parknshop.service;

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
    int STATE_COMMENT = 5;//评论


    /**
     * 添加到订单，
     * @param carts 购物车id  ,号分割
     * @return
     */
    int ADD_PARAM_ERRO = 999;//参数错误
    int ADD_SAVE_ERRO = 1000;//保存失败
    int ADD_SAVE_SUCCESS = 1001;//保存成功
    int addOrders(int[] carts);

}
