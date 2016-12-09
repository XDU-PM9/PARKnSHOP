package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.*;
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

    @Resource
    private BaseDao<GoodsEntity> goodsEntityBaseDao;

    @Resource
    private  BaseDao<ShopEntity> shopEntityBaseDao;
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
    public List<CollectionEntity> queryAllCollect(Integer userId,int page) {
        Object[] params=new Object[1];
         params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        return collectionEntityBaseDao.find("from CollectionEntity  where  userByUserId=? order by createTime desc",params,page,8);
    }

    @Override
    public int querySize(int userId) {
        Object[] params=new Object[1];
        params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        return collectionEntityBaseDao.find("from CollectionEntity  where  userByUserId=? ",params).size();
    }

    @Override
    public void insertCollect(Integer id, Integer userId) {
        Object[] params=new Object[2];
        params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        params[1]=goodsEntityBaseDao.get("from GoodsEntity where goodsId=?",new Object[]{id});
        List<CollectionEntity> ces= collectionEntityBaseDao.find("from CollectionEntity  ce  where ce.userByUserId=? and ce.goodsByGoodsId=?",params);
         if(ces.size()==0) {
             Object[] parans=new Object[3];
             parans[0] = id;
             parans[1] = userId;
             parans[2] = new Timestamp(System.currentTimeMillis());
             collectionEntityBaseDao.insert("insert into collection(goodsId,userId,createTime) values(?,?,?)", parans);
         }
         else
         {
               Object[] parans=new Object[2];
              parans[0]=new Timestamp(System.currentTimeMillis());
              parans[1]=ces.get(0).getCollectionId();
              collectionEntityBaseDao.insert("update collection set createTime=? where collectionId=?",parans);
         }

    }

    @Override
    public void removeCollect(Integer id) {
            collectionEntityBaseDao.delete("delete from collection where collectionId=?",id);
    }

    @Override
    public List<CollectshopEntity> queryAllShop(Integer userId,int page)
    {
        Object[] params=new Object[1];
        params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        return collectshopEntityBaseDao.find("from CollectshopEntity  where  userByUserId=? order by createTime desc",params,page,8);
    }

    @Override
    public int queryShopsize(Integer userId) {
        Object[] params=new Object[1];
        params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        return collectshopEntityBaseDao.find("from  CollectshopEntity  where  userByUserId=? ",params).size();
    }

    @Override
    public void insertShop(Integer id, Integer userId) {
        Object[] params=new Object[2];
        params[0]=userEntityBaseDao.get("from UserEntity where userId=?",new Object[]{userId});
        params[1]=shopEntityBaseDao.get("from ShopEntity where shopId=?",new Object[]{id});
        List<CollectshopEntity> ces= collectshopEntityBaseDao.find("from CollectshopEntity ce  where ce.userByUserId=? and ce.shopByShopId=?",params);
        if(ces.size()==0)
        {
            Object[] parans=new Object[3];
            parans[0]=id;
            parans[1]=userId;
            parans[2]=new Timestamp(System.currentTimeMillis());
            collectshopEntityBaseDao.insert("insert into collectShop(shopId,userId,createTime) values(?,?,?)",parans);
        }
        else
        {
            /*Object[] parans=new Object[2];
            parans[0]=new Timestamp(System.currentTimeMillis());
            parans[1]=ces.get(0).getScollectId();
            collectshopEntityBaseDao.insert("update collectshop set createTime=? where scollectId=?",parans);
        */

            Object[] parans=new Object[3];
            parans[0]=id;
            parans[1]=userId;
            parans[2]=new Timestamp(System.currentTimeMillis());
            collectshopEntityBaseDao.insert("insert into collectShop(shopId,userId,createTime) values(?,?,?)",parans);
        }

    }

    @Override
    public void removeShop(Integer id) {
              collectshopEntityBaseDao.delete("delete from collectshop where scollectId=?",id);
    }
}
