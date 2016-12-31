package com.parknshop.service.customerService;

import com.parknshop.entity.OrdersEntity;

import javax.persistence.criteria.Order;
import java.util.List;

/**
 * Created by Lenovo on 2016/12/26.
 */
public interface IOrderSearchService {

    public List<OrdersEntity> datelySearch(String s,int userId);
    public List<OrdersEntity> weeklySearch(String s,int userId);
    public List<OrdersEntity> monthlySearch(String s,int userId);
    public List<OrdersEntity> yearlySearch(String s,int userId);

    public OrdersEntity getOrderById(int ordersId);
}
