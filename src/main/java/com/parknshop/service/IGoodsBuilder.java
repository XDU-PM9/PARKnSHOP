package com.parknshop.service;

import com.parknshop.entity.GoodsEntity;

/**
 * Created by weina on 2016/12/11.
 */
public interface IGoodsBuilder {

    int GOOD_SATE_DELETE=-1;
    int GOOD_SATE_BLAKE=0;
    int GOOD_STATE_USING=1;
    /**
     * 如果 控制不住单列 就清空吧。
     */
    IGoodsBuilder clear();

    /**
     *  商家 id；
     * @param ownerId
     * @return
     */
    IGoodsBuilder setOwnerId(int ownerId);

    /**
     * 商品名字 50个字内
     * @param goodsName
     */
    IGoodsBuilder setGoodsName(String goodsName);

    /**
     * 商品介绍 500字内
     * @param introduction
     */
    IGoodsBuilder setIntroduction(String introduction);

    /**
     * 商品单价 精确到小数点后两位
     * @param price
     */
    IGoodsBuilder setPrice(Double price);

    /**
     * 库存
     * @param inventory
     */
    IGoodsBuilder setInventory(int inventory);

    /**
     * 类型
     * @param type
     * @return
     */
    IGoodsBuilder setType(String type);
    /**
     * 构造器
     * @return
     */
    GoodsEntity builder() throws Exception;
}
