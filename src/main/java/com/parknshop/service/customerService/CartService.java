package com.parknshop.service.customerService;

import com.parknshop.entity.CartEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by H on 2016/11/30.
 */
public interface CartService {

   public  boolean changeAmount(int cartId,int ammount);

   public  List<CartEntity>  find(int userId) ;

   public  void   insert(int  goodsId,int userId,int ammount);

   public  void   remove(int cartId);

   public CartEntity  getCartById(int cartId);

}
