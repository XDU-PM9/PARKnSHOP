package com.parknshop.service;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.serviceImpl.OwnerBuilder;

/**
 * Created by weina on 2016/12/2.
 */
public interface IAdminService {

    /** ----------------------------------------管理商店
     * 获取 申请商店 的列表
     * @param page  请求第几页
     * @param  lines  请求多少条
     * @return 返回一个存放了数据的 bean 接口 查看 service.IShopListBean
     *          页数不对，返回null
     */
    IListBean<ShopAndOwnerDbBean> getApplyShop(int page, int lines);

    /**
     * 获取 所有商店的列表 删除 的不显示
     * @param page 请求第几页
     * @param lines 请求多少条
     * @return 返回一个存放了数据的 bean 接口 查看 service.IShopListBean
     *          页数不对，返回null
     */
    IListBean<ShopAndOwnerDbBean> getAllShop(int page, int lines);
    /**
     * 同意开店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean suggestShop(int shopId);

    /**
     * 不同意开店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean rejectShop(int shopId);

    /**
     * 拉黑商店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean blackShop(int shopId);

    /**
     * 取消拉黑商店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean whiteShop(int shopId);

    /**
     * 删除商店
     * @param shopId 商店id
     * @return 处理成功 true 不成功 false
     */
    boolean deleteShop(int shopId);

    /**
     * 获取 商店 所有信息包括 用户
     * @param shopId 商店id
     * @return 成功商店 失败null
     */

    ShopAndOwnerDbBean getShopById(int shopId);

    /**
     * 根据 ownerId 获取owner
     * @param ownerId  ownerId
     * @return  成功返回实体，失败null
     */
    OwnerEntity getOwnerById(int ownerId);

    /**
     * 根据 userId 获取user
     * @param userId  userId
     * @return 成功返回实体，失败null
     */
    UserEntity getUserById(int userId);

    /** ----------------------------------------管理用户
     * 参数什么的同上
     */
    IListBean<UserEntity> getUserList(int page, int lines);
    boolean blackUser(int userId);//拉黑
    boolean whiteUser(int userId);//取消拉黑
    boolean deleteUser(int userId);//删除

    /** ----------------------------------------管理商家
     * 参数什么的同上
     */
    IListBean<OwnerEntity> getOwnerList(int page, int lines);
    boolean blackOwner(int ownerId);//拉黑
    boolean whiteOwner(int ownerId);//取消拉黑
    boolean deleteOwner(int ownerId);//删除
}
