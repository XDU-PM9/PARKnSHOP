package com.parknshop.service.customerService;

import com.parknshop.entity.CartEntity;

import java.util.List;

/**
 * Created by H on 2016/12/5.
 */
public interface ICartService {

    //改变单个商品的数量
    public  boolean changeAmount(int cartId,int amount);

    public boolean changeAmount(int userId,int goodsId,int amount);

    //跟据用户ID获取该用户购物车中的商品,max为起始值，count为获取条数
    public List<CartEntity> getProducts(int userId,int max,int count) ;

    public List<CartEntity> getProductsByPage(int userId,int page,int line) ;

    public  boolean addProduct(int  userId,int goodsId,int ammount);

    public  boolean removeProduct(int cartId);

    public boolean removeProduct(int userId,int goodsId);

    public CartEntity  getCartById(int cartId);

    public CartEntity getCart(int userId,int goodsId);

    public long getCount(int userID);
}
