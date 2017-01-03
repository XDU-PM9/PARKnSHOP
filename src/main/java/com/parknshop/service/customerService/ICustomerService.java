package com.parknshop.service.customerService;

import java.util.List;
import com.parknshop.entity.CollectionEntity;
import com.parknshop.entity.CollectshopEntity;
import com.parknshop.entity.UserEntity;

/**
 * Created by H on 2016/12/5.
 */

public interface ICustomerService {
//管理员得到所有用户
    public  List<UserEntity>   getAllCustomer();

    public boolean  checkExit(int userId,String phone,String name);
    public  boolean  setUserEntity(UserEntity userEntity);
//管理员设置黑名单
    public  void     setBlackList(Integer userId);
//管理员接触黑名单
    public  void     resetBlackList(Integer userId);

    public UserEntity getCustomerById(Integer id);

    public  UserEntity getCustomerByName(String name,String password);
    public  void      updateCustomerEntity(UserEntity userEntity);

    //逻辑删除用户
    public boolean deleteCustomer(Integer id);

    public boolean changePassword(String password, int userId);

    public boolean changeUserImage(String url, int userId);

//此处为收藏的商品
    public  List<CollectionEntity>  queryAllCollect(Integer userId,int page);

    public int   querySize(int userId);

    public boolean insertCollect(Integer id, Integer userId);

    public boolean removeCollect(Integer id);

    //*****************************************************************
 //此处为收藏的店铺

    public  List<CollectshopEntity>   queryAllShop(Integer userId,int page);

    public int                        queryShopsize(Integer userId);

    public boolean insertShop(Integer id, Integer userId);

    public boolean removeShop(Integer id);


    //*****************************************************************
   //注册在其他地方已经实现了，所以不在此处显示
    // public  void      regist();
}
