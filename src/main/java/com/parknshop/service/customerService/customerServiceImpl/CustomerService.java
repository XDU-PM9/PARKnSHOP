package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.*;
import com.parknshop.service.customerService.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by H on 2016/12/5.
 */
@Transactional
@Service
public class CustomerService implements ICustomerService {

    @Resource
    private BaseDao<UserEntity> userEntityBaseDao;

    @Resource
    private BaseDao<CollectionEntity> collectionEntityBaseDao;

    @Resource
    private BaseDao<CollectshopEntity> collectshopEntityBaseDao;

    @Resource
    private BaseDao<GoodsEntity> goodsEntityBaseDao;

    @Resource
    private BaseDao<ShopEntity> shopEntityBaseDao;

    @Resource
    private BaseDao<PhotoEntity> photoEntityBaseDao;

    @Override
    public List<UserEntity> getAllCustomer() {
        try {
            Object[] objects = null;
            return userEntityBaseDao.find("from UserEntity", objects, 10, 8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkExit(int userId,  String phone, String name) {

            int j=userEntityBaseDao.countNum("from UserEntity where phone=?",new Object[]{phone});
            if(j>1)
            {
                return false;
            }
            else
            {
                int k=userEntityBaseDao.countNum("from UserEntity where username=?",new Object[]{name});
                if(k>1)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
    }

    @Override
    public boolean setUserEntity(UserEntity userEntity) {
        try {
            userEntityBaseDao.update(userEntity);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setBlackList(Integer userId) {
        Object[] params = new Object[1];
        params[0] = userId;
        try {
            userEntityBaseDao.insert("update user set state=2 where userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void resetBlackList(Integer userId) {
        Object[] params = new Object[1];
        params[0] = userId;
        try {
        userEntityBaseDao.insert("update user set state=1 where userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public UserEntity getCustomerById(Integer id) {
        Object[] params = new Object[1];
        params[0] = id;
        try{
        return userEntityBaseDao.get("from UserEntity ue where ue.userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity getCustomerByName(String name, String password) {
        try {
        return userEntityBaseDao.get("from UserEntity ue where (ue.username=? or ue.phone=? or ue.email=?) and ue.password=?", new Object[]{name, name, name, password});
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCustomerEntity(UserEntity userEntity) {

    }

    @Override
    public boolean deleteCustomer(Integer id) {
        Object[] params = new Object[1];
        params[0] = id;
        try {

       return userEntityBaseDao.insert("update user set state=0 where userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(String password, int userId) {
        Object[] params = new Object[2];
        params[0] = password;
        params[1] = userId;
        try {

            return userEntityBaseDao.insert("update user set password=? where userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeUserImage(String url, int userId) {
        Object[] params = new Object[2];
        params[0] = url;
        params[1] = userId;
        try{
            return  userEntityBaseDao.insert("update user set userImage=? where userId=?", params);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<CollectionEntity> queryAllCollect(Integer userId, int page) {
        Object[] params = new Object[1];
        try {
        params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
        return collectionEntityBaseDao.find("from CollectionEntity  where  userByUserId=? order by createTime desc", params, page, 4);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int querySize(int userId) {
        Object[] params = new Object[1];
        try {
        params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
        return collectionEntityBaseDao.find("from CollectionEntity  where  userByUserId=? ", params).size();
        }catch (Exception e)
        {
            e.printStackTrace();
            return  0;
        }
    }

    private String getPictureLocation(String photogroup) {
        if (photogroup != null) {
            try {
                return photoEntityBaseDao.find("from PhotoEntity where photoGroup=?", new Object[]{photogroup}).get(0).getAddress();
            }catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean insertCollect(Integer id, Integer userId) {
        try {
            Object[] params = new Object[2];
            params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
            GoodsEntity goodsEntity = goodsEntityBaseDao.get("from GoodsEntity where goodsId=?", new Object[]{id});
            params[1] = goodsEntity;

            List<CollectionEntity> ces = collectionEntityBaseDao.find("from CollectionEntity  ce  where ce.userByUserId=? and ce.goodsByGoodsId=?", params);
            if (ces.size() == 0) {
                Object[] parans = new Object[4];
                parans[0] = id;
                parans[1] = userId;
                parans[2] = new Timestamp(System.currentTimeMillis());
                parans[3] = getPictureLocation(goodsEntity.getPhotoGroup());
                return   collectionEntityBaseDao.insert("insert into collection(goodsId,userId,createTime,picture) values(?,?,?,?)", parans);
            } else {
                Object[] parans = new Object[2];
                parans[0] = new Timestamp(System.currentTimeMillis());
                parans[1] = ces.get(0).getCollectionId();
               return collectionEntityBaseDao.insert("update collection set createTime=? where collectionId=?", parans);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean removeCollect(Integer id) {
        try {
          return   collectionEntityBaseDao.delete("delete from collection where collectionId=?", id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<CollectshopEntity> queryAllShop(Integer userId, int page) {
        Object[] params = new Object[1];
        try {
            params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
            return collectshopEntityBaseDao.find("from CollectshopEntity  where  userByUserId=? order by createTime desc", params, page, 4);
        }catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public int queryShopsize(Integer userId) {
        Object[] params = new Object[1];
        try {
            params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
            return collectshopEntityBaseDao.find("from  CollectshopEntity  where  userByUserId=? ", params).size();
        }catch (Exception e)
        {
            e.printStackTrace();
            return  0;
        }
    }

    @Override
    public boolean insertShop(Integer id, Integer userId) {
        Object[] params = new Object[2];
        try {
            params[0] = userEntityBaseDao.get("from UserEntity where userId=?", new Object[]{userId});
            ShopEntity shopEntity = shopEntityBaseDao.get("from ShopEntity where shopId=?", new Object[]{id});
            params[1] = shopEntity;
            List<CollectshopEntity> ces = collectshopEntityBaseDao.find("from CollectshopEntity ce  where ce.userByUserId=? and ce.shopByShopId=?", params);
            if (ces.size() == 0) {
                CollectshopEntity collectshopEntity=new CollectshopEntity();
                collectshopEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                collectshopEntity.setPicture(getPictureLocation(shopEntity.getPhotoGroup()));
                collectshopEntity.setShopByShopId(shopEntityBaseDao.get(ShopEntity.class,id));
                collectshopEntity.setUserByUserId(userEntityBaseDao.get(UserEntity.class,userId));
                try {
                    collectshopEntityBaseDao.save(collectshopEntity);
                    return true;
                }catch (Exception e)
                {
                    e.printStackTrace();
                    return false;
                }

            } else {
                Object[] parans = new Object[2];
                parans[0] = new Timestamp(System.currentTimeMillis());
                parans[1] = ces.get(0).getScollectId();
                return  collectshopEntityBaseDao.insert("update collectshop set createTime=? where scollectId=?", parans);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean removeShop(Integer id) {
        try {
          return   collectshopEntityBaseDao.delete("delete from collectshop where scollectId=?", id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }
    }
}
