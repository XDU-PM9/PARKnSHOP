package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.CollectionEntity;
import com.parknshop.entity.CollectshopEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by H on 2016/12/5.
 */
@Transactional
@Service
public class CustomerService  implements ICustomerService {

    @Resource
    private BaseDao<UserEntity>          userEntityBaseDao;

    @Resource
    private  BaseDao<CollectionEntity>   collectionEntityBaseDao;

    @Resource
    private  BaseDao<CollectshopEntity>  collectshopEntityBaseDao;


    @Override
    public List<UserEntity> getAllCustomer()
    {
        Object[] objects=null;
        return userEntityBaseDao.find("from UserEntity",objects,10,8);
    }

    @Override
    public void setBlackList(Integer userId) {
        Object[] params=new Object[1];
        params[0]=userId;
            userEntityBaseDao.insert("update user set state=2 where userId=?",params);
    }

    @Override
    public void resetBlackList(Integer userId) {
        Object[] params=new Object[1];
        params[0]=userId;
        userEntityBaseDao.insert("update user set state=1 where userId=?",params);
    }

    @Override
    public UserEntity getCustomerById(Integer id) {
        Object[] params=new Object[1];
        params[0]=id;
        return userEntityBaseDao.get("from UserEntity ue where ue.userId=?",params);
    }

    @Override
    public void updateCustomerEntity(UserEntity userEntity) {

    }

    @Override
    public void deleteCustomer(Integer id) {
        Object[] params=new Object[1];
        params[0]=id;
        userEntityBaseDao.insert("update user set state=0 where userId=?",params);
    }

    @Override
    public void changePassword(String password,int userId) {
        Object[] params=new Object[2];
        params[0]=password;
        params[1]=userId;
        userEntityBaseDao.insert("update user set password=? where userId=?",params);
    }

    @Override
    public void changeUserImage(String url,int userId) {
        Object[] params=new Object[2];
        params[0]=url;
        params[1]=userId;
        userEntityBaseDao.insert("update user set url=? where userId=?",params);
    }

    @Override
    public List<CollectionEntity> queryAllCollect(Integer userId) {
        return null;
    }

    @Override
    public void insertCollect(Integer id, Integer userId) {
         Object[] parans=new Object[3];
         parans[0]=id;
         parans[1]=userId;
         parans[2]=new Timestamp(System.currentTimeMillis());
            collectionEntityBaseDao.insert("insert into collection(goodsId,userId,createTime) values(?,?,?)",parans);
    }

    @Override
    public void removeCollect(Integer id) {
            collectionEntityBaseDao.delete("delete from collection where collectionId=?",id);
    }

    @Override
    public List<CollectshopEntity> queryAllShop(Integer userId) {
        return null;
    }

    @Override
    public void insertShop(Integer id, Integer userId) {
        Object[] parans=new Object[3];
        parans[0]=id;
        parans[1]=userId;
        parans[2]=new Timestamp(System.currentTimeMillis());
        collectshopEntityBaseDao.insert("insert into collectShop(shopId,userId,createTime) values(?,?,?)",parans);
    }

    @Override
    public void removeShop(Integer id) {
              collectshopEntityBaseDao.delete("delete from collection where scollectId=?",id);
    }
}
