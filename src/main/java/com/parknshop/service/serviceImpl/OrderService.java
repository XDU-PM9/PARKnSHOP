package com.parknshop.service.serviceImpl;

import com.parknshop.bean.HqlBean;
import com.parknshop.bean.OrderDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.IPictureDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.dao.daoImpl.PitureDao;
import com.parknshop.entity.*;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOrderService;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceImpl.listBean.OrderListBean;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.text.SimpleDateFormat;
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
    private final IBaseDao<GoodsEntity> goodsEntityIBaseDao;
    private final IPictureDao pictureDao;
    @Autowired
    public OrderService(IBaseDao<OrdersEntity> ordersEntityIBaseDao, IBaseDao<CartEntity> cartEntityIBaseDao, OrderListBean ordersEntityIListBean, IBaseDao<AddressEntity> addressEntityIBaseDao, IBaseDao<GoodsEntity> goodsEntityIBaseDao, IPictureDao pictureDao) {
        this.ordersEntityIBaseDao = ordersEntityIBaseDao;
        this.cartEntityIBaseDao = cartEntityIBaseDao;
        this.ordersEntityIListBean = ordersEntityIListBean;
        this.addressEntityIBaseDao = addressEntityIBaseDao;
        this.goodsEntityIBaseDao = goodsEntityIBaseDao;
        this.pictureDao = pictureDao;
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
        ordersEntity.setPhoto(pictureDao.getPictures(cartEntity.getGoodsEntity().getPhotoGroup()).get(0).getAddress());
        ordersEntity.setGoodsName(cartEntity.getGoodsEntity().getGoodsName());
        ordersEntity.setGoodsDescribe(cartEntity.getGoodsEntity().getIntroduction());
        ordersEntity.setPrice(cartEntity.getAmount()*cartEntity.getGoodsEntity().getPrice());
        //地址
        ordersEntity.setPostWay(cartEntity.getGoodsEntity().getPostWay());
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

        //判断数量
        synchronized (this){
            GoodsEntity goodsEntity = cartEntity.getGoodsEntity();
            if(cartEntity.getAmount() > goodsEntity.getInventory() || 0  >= goodsEntity.getInventory()){
                return null;//数量过了
            }else {
                goodsEntity.setSales(goodsEntity.getSales()+1);
                goodsEntity.setInventory(goodsEntity.getInventory()-cartEntity.getAmount());
                try {
                    goodsEntityIBaseDao.update(goodsEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

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
                if (null != ordersEntity) {
                    orderList.add(ordersEntity);
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;//ADD_PARAM_ERRO;
            }
        }

        try {
            if(orderList.size()>0) {
                ordersEntityIBaseDao.save(orderList);
                for (int i : carts) {
                    cartEntityIBaseDao.delete("update cart set state='0' where cartId=?", i);
                }
                return orderNumber;//ADD_SAVE_SUCCESS;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;//ADD_SAVE_ERRO;
        }
    }

    @Override
    public  IListBean<OrdersEntity> getOrdersList(String orderNum,int page,int lines) {
        try {
//           return ordersEntityIBaseDao.find("from OrdersEntity o where o.orderNumber=?", new Object[]{orderNum});
           return getOrderList(" and orderNumber =?",new Object[]{orderNum},page,lines);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrdersEntity> getOrdersList(String orderNum) {
       return getOrdersList(orderNum,1,Integer.MAX_VALUE).getShopList();
    }

    @Override
    public IListBean<OrdersEntity> getAllList(int userId, int page, int lines) {
        return getOrderList("and userId = ? and state > ?",new Object[]{userId,IOrderService.STATE_BUY},page,lines);
    }

    @Override
    public  IListBean<OrdersEntity> getNotPayList(int userId,int page,int lines) {
        return getOrderList("and userId = ? and state = ?",new Object[]{userId,IOrderService.STATE_NOT_PAY},page,lines);
    }

    @Override
    public List<OrdersEntity> getNotPayList(int userId) {
        return getNotPayList(userId,1,Integer.MAX_VALUE).getShopList();
    }

    @Override
    public  IListBean<OrdersEntity> getPayList(int userId,int page,int lines) {
        //大于未支付的都是已经支付的
        return getOrderList("and userId = ? and state > ?",new Object[]{userId,STATE_NOT_PAY},page,lines);
    }

    @Override
    public List<OrdersEntity> getPayList(int userId) {
        return getPayList(userId,1,Integer.MAX_VALUE).getShopList();
    }

    @Override
    public  IListBean<OrdersEntity> getNotCommentLit(int userId,int page,int lines) {
        //获取了 邮件 就可以评论
        return getOrderList("and userId = ? and state = ?",new Object[]{userId,STATE_GET},page,lines);
    }

    @Override
    public List<OrdersEntity> getNotCommentLit(int userId) {
        return getNotCommentLit(userId,1,Integer.MAX_VALUE).getShopList();
    }


    @Override
    public int payOrder(List<String> orderNum, int addressId) {
        List<OrdersEntity> list = new ArrayList<>();
        for(String e:orderNum) {
            List<OrdersEntity> otherList = getOrdersList(e,1,Integer.MAX_VALUE).getShopList();
            list.addAll(otherList);
        }
        AddressEntity addressEntity = addressEntityIBaseDao.get(AddressEntity.class,addressId);
//        double price =0.0;//TODO:管理员里面没有 balance
        if(null == list || null == addressEntity){
            return PAY_WRONG_PARAM;
        }

        for(OrdersEntity entity:list){
//            price += entity.getPrice()*entity.getAmount();
            if(entity.getState() == IOrderService.STATE_PAY){
                return PAY_FAIL;
            }

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
    public int cancelOrder(String orderNum) {
        List<Object> saveList =new ArrayList<>();
        List<OrdersEntity> ordersEntities = getOrdersList(orderNum);
        //置于删除状态
        try {
            for (OrdersEntity entity : ordersEntities) {
                entity.setState(IOrderService.STATE_DELETE);//删除订单
                CartEntity cartEntity = getCart(entity.getUserByUserId().getUserId(), entity.getGoodsByGoodsId().getGoodsId());
                cartEntity.setState("1");
                cartEntity.setAmount(entity.getAmount());
                GoodsEntity goodsEntity = goodsEntityIBaseDao.get(GoodsEntity.class, entity.getGoodsByGoodsId().getGoodsId());
                goodsEntity.setInventory(goodsEntity.getInventory() + entity.getAmount());
                saveList.add(entity);
                saveList.add(cartEntity);
                saveList.add(goodsEntity);
            }
            IBaseDao<Object> objectIBaseDao = new BaseDao<>();
            objectIBaseDao.update(saveList);
            return CANCEL_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return CANCEL_FAIL;
        }
    }

    /**
     * 获取 指定 购物车
     * @param userId
     * @param goodsId
     * @return
     */

    private CartEntity getCart(int userId,int goodsId){
        String hql = "from CartEntity where userId = ? and goodsId = ? and state = ? order by cartId desc";
        Object[] param = {userId,goodsId,"0"};
        return cartEntityIBaseDao.find(hql,param,1,1).get(0);
    }

    @Override
    public boolean receive(String orderNum) {
        return updateStatus(orderNum,IOrderService.STATE_GET);
    }

    @Override
    public boolean sendGoods(String orderNum) {
        return updateStatus(orderNum,IOrderService.STATE_SEND);
    }


    @Override
    public IListBean<OrdersEntity> getFinishOrder(int ownerId, int page, int lines) {
        return getOrderList(" and ownerId = ? and state > ? ",new Object[]{ownerId,STATE_PAY},page,lines);
    }

    @Override
    public IListBean<OrdersEntity> getFinishOrderAdminYear(int page, int lines) {
        return getOrderList("  and state > ? and YEAR(createTime)=YEAR(NOW()) ",new Object[]{STATE_SEND},page,lines);
    }
    public static void main(String[] args){
        OrderService orderService = new OrderService(new BaseDao<>(),new BaseDao<>(),new OrderListBean(),new BaseDao<>(),new BaseDao<>(),new PitureDao(new BaseDao<>()));
        IListBean<OrdersEntity> list = orderService.getFinishOrderAdminMonth(1,10);
        IListBean<OrdersEntity> listBean = orderService.getFinishOrder(1,1,10);
        Date d=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date ye = new Date(d.getTime() - (long)31 * 24 * 60 * 60 * 1000);

        Log.debug("success   "+ye.toString());
    }

    @Override
    public IListBean<OrdersEntity> getFinishOrderAdminMonth(int page, int lines) {
        Date d=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date ye = new Date(d.getTime() - (long)31 * 24 * 60 * 60 * 1000);
        return getOrderList("  and state > ? and date( ? ) <= date(createTime) ",new Object[]{STATE_SEND,ye},page,lines);
    }

    @Override
    public IListBean<OrdersEntity> getFinishOrderAdminWeek(int page, int lines) {
        Date d=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date ye = new Date(d.getTime() - (long)7 * 24 * 60 * 60 * 1000);
        return getOrderList("  and state > ? and date( ? ) <= date(createTime) ",new Object[]{STATE_SEND,ye},page,lines);
    }

    @Override
    public IListBean<OrdersEntity> getFinishOrderAdminToday(int page, int lines) {
        return getOrderList("  and state > ? and to_days(createTime) = to_days(now()) ",new Object[]{STATE_SEND},page,lines);
    }


    @Override
    public IListBean<OrdersEntity> getCustomerOrder(int ownerId,int page,int lines) {
        return getOrderList(" and ownerId = ? and state = ? ",new Object[]{ownerId,STATE_PAY},page,lines);
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
        List<OrdersEntity> list = getOrdersList(orderNum,1,Integer.MAX_VALUE).getShopList();
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
