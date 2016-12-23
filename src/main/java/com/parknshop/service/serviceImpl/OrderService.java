package com.parknshop.service.serviceImpl;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.AddressEntity;
import com.parknshop.entity.CartEntity;
import com.parknshop.entity.CommissionEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.service.IOrderService;
import com.parknshop.service.customerService.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by weina on 2016/12/22.
 */
@Scope(value = "prototype")
@Service
public class OrderService implements IOrderService {
    final private IBaseDao<OrdersEntity> ordersEntityIBaseDao;
    private final IBaseDao<CartEntity> cartEntityIBaseDao;
    @Autowired
    public OrderService(IBaseDao<OrdersEntity> ordersEntityIBaseDao, IBaseDao<CartEntity> cartEntityIBaseDao) {
        this.ordersEntityIBaseDao = ordersEntityIBaseDao;
        this.cartEntityIBaseDao = cartEntityIBaseDao;
    }
    @Override
    public int addOrders(String carts) {
        String[] listString = carts.split(",");
        List<OrdersEntity> orderList = new ArrayList<>();
        String orderNumber =java.util.UUID.randomUUID().toString();
        for(String o:listString){

            try {
                CartEntity entity = cartEntityIBaseDao.get(CartEntity.class, Integer.parseInt(o));
                OrdersEntity ordersEntity = addOerder(orderNumber,entity);
                if(null != orderList){
                    orderList.add(ordersEntity);
                }
            }catch (Exception e){
                e.printStackTrace();
                return ADD_PARAM_ERRO;
            }
        }
        try {
            ordersEntityIBaseDao.save(orderList);
            return ADD_SAVE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ADD_SAVE_ERRO;
        }

    }

    private OrdersEntity  addOerder(String orderNumber, CartEntity cartEntity){
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderNumber(orderNumber);
        ordersEntity.setUserByUserId(cartEntity.getUserByUserId());
        ordersEntity.setSingleGoodId(cartEntity.getSingleGoodId());
        ordersEntity.setGoodsByGoodsId(cartEntity.getGoodsEntity());
        ordersEntity.setPhoto(cartEntity.getGoodsEntity().getPhotoGroup());//TODO:
        ordersEntity.setGoodsName(cartEntity.getGoodsEntity().getGoodsName());
        ordersEntity.setGoodsDescribe(cartEntity.getGoodsEntity().getIntroduction());
        //地址
        ordersEntity.setAddress(null);
        ordersEntity.setAmount(cartEntity.getAmount());
        ordersEntity.setCreateTime(new Date());
        ordersEntity.setPaidTime(null);//支付
        ordersEntity.setReciver(null);
        ordersEntity.setReciverPhone(null);
        IBaseDao<CommissionEntity> dao = new BaseDao<>();
        try {
            CommissionEntity entity = dao.get(CommissionEntity.class, 1);
            ordersEntity.setCommissionRate(entity.getRate());
            ordersEntity.setCommission(entity.getRate()*cartEntity.getGoodsEntity().getPrice());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        ordersEntity.setComment(null);
        ordersEntity.setCommentTime(null);
        ordersEntity.setState(IOrderService.STATE_NOT_PAY);//未支付状态
        return ordersEntity;
    }

}
