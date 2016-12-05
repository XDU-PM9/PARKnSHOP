package com.parknshop.service.customerService;

import com.parknshop.entity.CartEntity;

import java.util.List;

/**
 * Created by H on 2016/12/5.
 */
public interface ICartService {



    public  boolean changeAmount(int cartId,int ammount);

    public List<CartEntity> find(int userId) ;

    public  void   insert(int  goodsId,int userId,int ammount);

    public  void   remove(int cartId);

    public CartEntity  getCartById(int cartId);
}
