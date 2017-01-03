package com.parknshop.service;

import com.parknshop.bean.AdvertisementDbBean;

import java.util.List;

/**
 * Created by weina on 2016/12/19.
 * 广告
 */
public interface IAdvertisement {
    int AD_TYPE_SHOP = 0;//广告类型，商店
    int AD_TYOE_GOODS =1;//广告类型，商品

    /**
     *  申请广告位置，
     * @param id  商品或者 商店 id
     * @param price 广告费用
     * @return  成功 true 失败 false
     */
    int ALREADY_EXISTS = 1000;//已经存在

    int ILLEGAL_SHOP = 1002; //不合法的商店 拉黑或者删除状态
    int ILLEGAl_GOODS = 1003;//不合法的商品 删除状态
    int ELSE_ERRO = 1004;// 其他错误
    int ADD_SUCCESS = 2000;//添加成功
    int addAdvertisementShop(int id);
    int addAdvertisementGoods(int id);


    int AD_SATUS_REJECT = -2;//拒绝申请
    int AD_STATUS_APPLYING = -1;//申请中
    int AD_STATUS_CANCEL = 0;//取消广告位置，但是曾经生效过
    int AD_STATUS_EFFECT = 1;//正在广告

    /**
     * 同意 广告
     * @param id 商品或者 商店 id
     * @return 成功true 失败false
     */
    boolean acceptShop(int id);
    boolean acceptGoods(int id);

    /**
     * 拒绝 广告申请
     * @param id 商品或者 商店 id
     * @return 成功true 失败false
     */
    boolean rejectShop(int id);
    boolean rejectGoods(int id);

    /**
     * 取消 广告位置
     * @param id
     * @return
     */
    boolean cancelShop(int id);
    boolean cancelAdvertByName(int type,int shopId);
    boolean cancelGoods(int id);

    /**
     * 获取 广告的商品，店铺
     * @return 返回商品，店铺列表
     */
    List<AdvertisementDbBean> getTopShop();
    IListBean<AdvertisementDbBean> getTopShop(int pahe,int lines);

    List<AdvertisementDbBean> getTopGoods();
    IListBean<AdvertisementDbBean> getTopGoods(int pahe,int lines);

    /**
     * 获取所有  商品或商店信息
     * @return
     */
    IListBean<AdvertisementDbBean> getAllShop(int page,int lines);
    IListBean<AdvertisementDbBean> getAllGoods(int page,int lines);

    /**
     * 获取店主 所有  商品或商店信息
     * @param userId 店主id
     * @return
     */
    IListBean<AdvertisementDbBean> getMyShop(int userId,int page,int lines);
    IListBean<AdvertisementDbBean> getMyGoods(int userId,int page,int lines);

    /**
     * 检查 商品 或者 商店是否已经是广告
     * @param id
     * @return 存在 true 不存在false
     */
    boolean checkAdvertShopExist(int id);
    boolean checkAdvertGoodsExist(int id);
}
