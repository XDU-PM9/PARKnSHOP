package com.parknshop.service;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.baseImpl.IUploadPictures;
import com.parknshop.service.enumStatic.LoginTypeEnum;

/**
 * Created by weina on 2016/12/2.
 */
public interface IOwnerService {
    /**
     * 商店状态
     */
    int SHOP_STATE_ELSE = -3;//其他错误
    int SHOP_STATE_NOSHOP =-2;//没有商店
    int SHOP_STATE_DELETE = -1;//商店已被删除
    int SHOP_STATE_REJECT = 0;//商店被拒绝
    int SHOP_STATE_CHECKING =1;//商店申请
    int SHOP_STATE_BLAKE = 2;//商店被拉黑
    int SHOP_STATE_USING = 3;//商店正在正常使用
    int isHasShop(OwnerEntity ownerEntity);
    /**
     * 商家 服务
     */
    /**
     * 用于更新 商家的个人信息 ,请用 IUserService 里的CheckUserExits
     * @param ownerEntity 此参数请从登陆后 session 中获得
     * @return
     */
    int UPDATE_SUCCESS = 1001;
    int UPDATE_FAIL = 1002;

    int updateOwner(OwnerEntity ownerEntity);


    int NEW_ERROPICTURE = -1;//图片上传失败
    int NEW_FAIL =0;//创建 商店
    int NEW_SUCCESS = 1;//商店创建成功
    /**
     * 创建新的 商店
     * @param ownerEntity 此参数请从登陆后 session 中获得
     * @param shopEntity  此参数 使用new 方法，类中已经初始化变量，需要提供
     *                    商店名字，商店描述，logo地址
     * @param pictures
     * @return
     */

    int newShop(OwnerEntity ownerEntity, ShopEntity shopEntity, IUploadPictures pictures);

    /**
     * 获取 申请商店 的列表
     * @param page  请求第几页
     * @param  lines  请求多少条
     * @return 返回一个存放了数据的 bean 接口 查看 service.IShopListBean
     *          页数不对，返回null
     */
    IListBean<ShopAndOwnerDbBean> getMyShop(OwnerEntity entity,int page, int lines);

    /**
     * 获取 商品列表
     * @param entity
     * @param page 请求第几页
     * @param lines 请求多少条
     * @return 返回一个存放了数据的 bean 接口 查看 service.IShopListBean
     *          页数不对，返回null
     */
    IListBean<GoodsDbBean> getMyGoods(ShopEntity entity, int page, int lines);
    /**
     * 创建新商品
     * @param goodsBuilder 记得设置 ownerId的值 ，理论上所有set 都设值
     * @param pictures
     * @return
     *  int NEW_ERROPICTURE = -1;//图片上传失败
     *  int NEW_FAIL =0;//创建 商店
     * int NEW_SUCCESS = 1;//商店创建成功
     */
    int NEW_BUILDER_ERROR = -2;
    int newGoods(IGoodsBuilder goodsBuilder,IUploadPictures pictures);

    /**
     * 删除商品
     * @param goodId
     * @return
     */
    boolean  deletGoods(int goodId);

    /**
     * 更新商品 信息
     * @param goodsEntity
     * @param uploadPictures 若为空则不更新图片
     * @return
     */
    boolean updateGoods(GoodsEntity goodsEntity,IUploadPictures uploadPictures);

    /**
     * 获取单个商品 并内部对  商品数量加一，（已经加锁操作,线程操作）
     * @param goodsId 商品id
     * @return  成功实体，失败null
     */
    GoodsDbBean getGoods(int goodsId);


}
