package com.parknshop.service.serviceImpl;

import com.parknshop.bean.HqlBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.*;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOrderService;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.serviceImpl.listBean.OrderListBean;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final IListBean<OrdersEntity> ordersEntityIListBean;
    private final IBaseDao<AddressEntity> addressEntityIBaseDao;
    @Autowired
    public OrderService(IBaseDao<OrdersEntity> ordersEntityIBaseDao, IBaseDao<CartEntity> cartEntityIBaseDao, OrderListBean ordersEntityIListBean, IBaseDao<AddressEntity> addressEntityIBaseDao) {
        this.ordersEntityIBaseDao = ordersEntityIBaseDao;
        this.cartEntityIBaseDao = cartEntityIBaseDao;
        this.ordersEntityIListBean = ordersEntityIListBean;
        this.addressEntityIBaseDao = addressEntityIBaseDao;
    }


    private OrdersEntity  addOerder(String orderNumber, CartEntity cartEntity){
        if(cartEntity.getGoodsEntity().getShopByShopId().getState() != IOwnerService.SHOP_STATE_USING){
            return null;
        }

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderNumber(orderNumber);
        ordersEntity.setOwnerId(cartEntity.getGoodsEntity().getShopByShopId().getOwnerByOwnerId().getOwnerId());
        ordersEntity.setUserByUserId(cartEntity.getUserByUserId());
        ordersEntity.setSingleGoodId(cartEntity.getSingleGoodId());
        ordersEntity.setGoodsByGoodsId(cartEntity.getGoodsEntity());
        ordersEntity.setPhoto(cartEntity.getGoodsEntity().getPhotoGroup());//TODO:
        ordersEntity.setGoodsName(cartEntity.getGoodsEntity().getGoodsName());
        ordersEntity.setGoodsDescribe(cartEntity.getGoodsEntity().getIntroduction());
        ordersEntity.setPrice(cartEntity.getAmount()*cartEntity.getGoodsEntity().getPrice());
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
            ordersEntity.setCommission(entity.getRate()*cartEntity.getGoodsEntity().getPrice()*cartEntity.getAmount());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        ordersEntity.setComment(null);
        ordersEntity.setCommentTime(null);
        ordersEntity.setState(IOrderService.STATE_NOT_PAY);//未支付状态
        return ordersEntity;
    }

    @Override
    public String addOrders(int[] carts) {
        List<OrdersEntity> orderList = new ArrayList<>();
        String orderNumber = java.util.UUID.randomUUID().toString();
        for (int i : carts) {

            try {
                CartEntity entity = cartEntityIBaseDao.get(CartEntity.class, i);
                OrdersEntity ordersEntity = addOerder(orderNumber, entity);
                if (null != orderList) {
                    orderList.add(ordersEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;//ADD_PARAM_ERRO;
            }
        }

        try {
            ordersEntityIBaseDao.save(orderList);
            for(int i:carts)
                 cartEntityIBaseDao.delete("delete from cart where cartId=?",i);
            return orderNumber;//ADD_SAVE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;//ADD_SAVE_ERRO;
        }
    }

    @Override
    public List<OrdersEntity> getOrdersList(String orderNum) {
        try {
//           return ordersEntityIBaseDao.find("from OrdersEntity o where o.orderNumber=?", new Object[]{orderNum});
           return getOrderList(" and orderNumber =?",new Object[]{orderNum}).getShopList();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrdersEntity> getNotPayList(int userId) {
        return getOrderList("and userId = ? and state = ?",new Object[]{userId,IOrderService.STATE_NOT_PAY}).getShopList();
    }

    @Override
    public List<OrdersEntity> getPayList(int userId) {
        //大于未支付的都是已经支付的
        return getOrderList("and userId = ? and state > ?",new Object[]{userId,STATE_NOT_PAY}).getShopList();
    }

    @Override
    public List<OrdersEntity> getNotCommentLit(int userId) {
        //获取了 邮件 就可以评论
        return getOrderList("and userId = ? and state = ?",new Object[]{userId,STATE_GET}).getShopList();
    }

    @Override
    public int payOrder(String orderNum, int addressId) {
        List<OrdersEntity> list = getOrdersList(orderNum);
        AddressEntity addressEntity = addressEntityIBaseDao.get(AddressEntity.class,addressId);
//        double price =0.0;//TODO:管理员里面没有 balance
        if(null == list || null == addressEntity){
            return PAY_WRONG_PARAM;
        }

        for(OrdersEntity entity:list){
//            price += entity.getPrice()*entity.getAmount();
            entity.setAddress(addressEntity.getCountry()+addressEntity.getProvince()+addressEntity.getOthers());
            entity.setReciver(addressEntity.getName());
            entity.setReciverPhone(String.valueOf(addressEntity.getPhone()));
            entity.setPaidTime(new Date());
            entity.setState(IOrderService.STATE_PAY);
        }
        try{
            ordersEntityIBaseDao.update(list);
        }catch (Exception e){
            e.printStackTrace();
            return PAY_FAIL;
        }

        return PAY_SUCCESS;
    }

    @Override
    public boolean receive(String orderNum) {
        return updateStatus(orderNum,IOrderService.STATE_GET);
    }


    //初始化 订单
    private IListBean<OrdersEntity> getOrderList(String hql,List<Object> param,int pages,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(hql);
        hqlBean.setInnerParam(param);
        ordersEntityIListBean.init(hqlBean,pages,lines);
        return ordersEntityIListBean;
    }

    private IListBean<OrdersEntity> getOrderList(String hql,Object[] param,int pages,int lines){
        List<Object> list = Arrays.asList(param);
        return getOrderList(hql,list,pages,lines);
    }
    private IListBean<OrdersEntity> getOrderList(String hql,List<Object> param){
        return getOrderList(hql,param,1,1000);
    }
    private IListBean<OrdersEntity> getOrderList(String hql,Object[] param){
        return getOrderList(hql,param,1,1000);
    }
    private boolean updateStatus(String orderNum,int status) {
        List<OrdersEntity> list = getOrdersList(orderNum);
        for (OrdersEntity entity : list) {
            entity.setState(status);
        }
        try {
            ordersEntityIBaseDao.update(list);
            int i;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
