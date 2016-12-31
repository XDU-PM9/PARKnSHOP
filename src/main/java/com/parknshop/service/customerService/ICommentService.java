package com.parknshop.service.customerService;

import com.parknshop.entity.OrdersEntity;

import java.util.List;

/**
 * Created by Lenovo on 2016/12/26.
 */
public interface ICommentService {

    public boolean                insertComment(String s,int commentType,int ordersId);
    public List<OrdersEntity>     listComment(int goodsId);
    public List<OrdersEntity>     listCom(int userId);
}
