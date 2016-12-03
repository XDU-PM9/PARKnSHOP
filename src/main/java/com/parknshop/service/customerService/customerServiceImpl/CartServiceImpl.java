package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.CartEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.CartService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.lang.Exception;
import java.util.Objects;

/**
 * Created by H on 2016/11/30.
 */
@Transactional
@Service("CartService")
public class CartServiceImpl implements CartService {

    @Resource
    BaseDao<CartEntity>   cartEntityDao;

    @Resource
    BaseDao<UserEntity>    userEntityDao;

    public  boolean changeAmount(int cartId,int ammount)
    {
        CartEntity ce=getCartById(cartId);
        ce.setAmount(ammount);
        try {
            cartEntityDao.update(ce);
        }catch(Exception e)
        {
            return false;
        }
          return  true;
    }

    public List<CartEntity> find(int userId)
    {
        Object[] o=new Object[1];
        UserEntity userEntity=userEntityDao.get("from UserEntity user ",o);
        o[0]=userId;
        return  cartEntityDao.find("from CartEntity ce where ce.userId=?",o);
    }

    public  void   insert(int  goodsId,int userId,int ammount)
    {

    }

    public  void   remove(int cartId)
    {
          Object[] params=new Object[1];
          params[0]=cartId;
          cartEntityDao.executeHql("delete from CartEntity ce where ce.cartId=?",params);
    }

    public CartEntity  getCartById(int cartId)
    {
        Object[] o=new Object[1];
        o[0]=cartId;
           return cartEntityDao.get("from CartEntity ce where ce.cartId=?",o);
    }

}
