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
//管理员设置黑名单
    public  void     setBlackList(Integer userId);
//管理员接触黑名单
    public  void     resetBlackList(Integer userId);

    public UserEntity getCustomerById(Integer id);

    public  void      updateCustomerEntity(UserEntity userEntity);

    //逻辑删除用户
    public  void      deleteCustomer(Integer id);

    public  void      changePassword(String password,int userId);

    public  void      changeUserImage(String url,int userId);

//此处为收藏的商品
    public  List<CollectionEntity>  queryAllCollect(Integer userId,int page);

    public int   querySize(int userId);

    public void  insertCollect(Integer id,Integer userId);

    public void  removeCollect(Integer id);

    //*****************************************************************
 //此处为收藏的店铺

    public  List<CollectshopEntity>   queryAllShop(Integer userId);

    public  void                      insertShop(Integer id,Integer userId);

    public   void                     removeShop(Integer id);


    //*****************************************************************
   //注册在其他地方已经实现了，所以不在此处显示
    // public  void      regist();
}
