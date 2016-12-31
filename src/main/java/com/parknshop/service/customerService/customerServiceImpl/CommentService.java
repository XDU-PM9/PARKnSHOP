package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by Lenovo on 2016/12/27.
 */
@Service
public class CommentService implements ICommentService{


    @Autowired
    private BaseDao<OrdersEntity> ordersEntityBaseDao;

    @Autowired
    private BaseDao<GoodsEntity> goodsEntityBaseDao;

    @Autowired
    private BaseDao<UserEntity> userEntityBaseDao;

    @Override
    public boolean insertComment(String s,int commentType,int ordersId) {
        try {
            OrdersEntity ordersEntity = ordersEntityBaseDao.get(OrdersEntity.class, ordersId);
            ordersEntity.setComment(s);
            ordersEntity.setCommentType(new Integer(commentType));
            ordersEntity.setCommentTime(new Date(System.currentTimeMillis()));
            ordersEntity.setState(new Integer("5"));
            ordersEntityBaseDao.update(ordersEntity);
                return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrdersEntity> listComment(int goodsId) {
        List<OrdersEntity> objects=ordersEntityBaseDao.find("from OrdersEntity where goodsByGoodsId=? and state=5",new Object[]{goodsEntityBaseDao.get(GoodsEntity.class,goodsId)});
        return objects;
    }

    @Override
    public List<OrdersEntity> listCom(int userId) {
        List<OrdersEntity> objects=ordersEntityBaseDao.find("from OrdersEntity where userByUserId=? and  state=5 order by commentTime Desc",new Object[]{userEntityBaseDao.get(UserEntity.class,userId)});
        return objects;
    }
}
