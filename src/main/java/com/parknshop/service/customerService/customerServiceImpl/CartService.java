package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.CartEntity;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by H on 2016/11/30.
 */
@Transactional
@Service
public class CartService implements ICartService {

    @Resource
    BaseDao<CartEntity> cartEntityDao;

    @Resource
    BaseDao<UserEntity> userEntityDao;

    @Resource
    BaseDao<GoodsEntity> goodsEntityBaseDao;

    public boolean changeAmount(int cartId, int amount) {
        CartEntity ce = getCartById(cartId);
        ce.setAmount(amount);
        try {
            cartEntityDao.update(ce);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeAmount(int userId, int goodsId, int amount) {
        CartEntity cartEntity = getCart(userId, goodsId);
        cartEntity.setAmount(amount);
        try {
            cartEntityDao.update(cartEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<CartEntity> getProducts(int userId, int first, int count) {
        try {
            return cartEntityDao.findNumberRows("from CartEntity where userByUserId = ?", new Object[]{getUserEntity(userId)}, first, count);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CartEntity> getProductsByPage(int userId, int page, int line) {
        return getProducts(userId,(page-1)*line,line);
    }

    public boolean addProduct(int userId, int goodsId, int amount) {
        //如果商品不在购物车中，则添加，否则在购物车中添加数量
        CartEntity cart = getCart(userId, goodsId);
        if (null == cart) {
            try {
                CartEntity cartEntity = new CartEntity();
                cartEntity.setAmount(amount);
                cartEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                //  cartEntity.setGoodsId(goodsId);
                // cartEntity.setUserId(userId);
                cartEntity.setGoodsEntity(getGoodsEntity(goodsId));
                cartEntity.setUserByUserId(getUserEntity(userId));
                cartEntity.setSingleGoodId(0);
                cartEntityDao.save(cartEntity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return changeAmount(userId, goodsId, amount + cart.getAmount());
        }
    }

    public boolean removeProduct(int cartId) {
        try {
            //若成功执行且影响的行数大于0,返回true，否则返回false
            return cartEntityDao.delete("delete from cart where cartId = ?",new Integer(cartId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean removeProduct(int userId, int goodsId) {
        try {
            //若成功执行且影响的行数大于0,返回true，否则返回false
            return cartEntityDao.executeHql("delete from CartEntity where userByUserId = ? and goodsEntity = ", new Object[]{getUserEntity(userId), getGoodsEntity(goodsId)}) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CartEntity getCartById(int cartId) {
        try {
            return cartEntityDao.get("from CartEntity where cartId=?", new Object[]{cartId});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CartEntity getCart(int userId, int goodsId) {
        try {
            return cartEntityDao.get("from CartEntity where userByUserId = ? and goodsEntity = ", new Object[]{getUserEntity(userId), getGoodsEntity(goodsId)});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getCount(int userId) {
        try{
            return cartEntityDao.count("from CartEntity where userByUserId = ?",new Object[]{getUserEntity(userId)});
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private UserEntity getUserEntity(int userId) {
        try {
            return userEntityDao.get("from UserEntity where userId = ?", new Object[]{userId});
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private GoodsEntity getGoodsEntity(int goodsId) {
        try {
            return goodsEntityBaseDao.get("from GoodsEntity where goodsId = ?", new Object[]{goodsId});
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
