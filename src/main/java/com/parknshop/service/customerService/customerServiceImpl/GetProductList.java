package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.customerService.IGetProductList;
import com.parknshop.service.customerService.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 16-12-9.
 */
@Service
public class GetProductList implements IGetProductList {

    @Override
    public Cart getCart(CartEntity cartEntity) throws NullPointerException{
        GoodsEntity goodsEntity=cartEntity.getGoodsEntity();
        ShopEntity shopEntity = goodsEntity.getShopByShopId();
        return new Cart()
                .setCartId(cartEntity.getCartId())
                .setGoodsId(goodsEntity.getGoodsId())
                .setAmount(cartEntity.getAmount())
                .setPrice(goodsEntity.getPrice())
                .setDiscount(goodsEntity.getDiscount())
                .setGoodsIntroduction(goodsEntity.getIntroduction())
                .setGoodsName(goodsEntity.getGoodsName())
                .setPicture(getPhoto(goodsEntity.getPhotoGroup()))
                .setShopId(shopEntity.getShopId())
                .setShopName(shopEntity.getShopName())
                .setTips(String.valueOf(goodsEntity.getState()));
    }

    @Override
    public List<Cart> getCarts(List<CartEntity> cartEntityList) throws NullPointerException{
        List Carts= new ArrayList<Cart>();
        for(CartEntity cartEntity:cartEntityList){
            Carts.add(getCart(cartEntity));
        }
        return Carts;
    }

    @Override
    public Product getProduct(GoodsEntity goodsEntity) throws NullPointerException{
        return new Product()
                .setGoodsId(goodsEntity.getGoodsId())
                .setGoodsName(goodsEntity.getGoodsName())
                .setPrice(goodsEntity.getPrice())
                .setDiscount(goodsEntity.getDiscount())
                .setGoodsIntroduction(goodsEntity.getIntroduction())
                .setPicture(getPhoto(goodsEntity.getPhotoGroup()))
                .setShopId(goodsEntity.getShopByShopId().getShopId())
                .setShopName(goodsEntity.getShopByShopId().getShopName())
                .setTips(String.valueOf(goodsEntity.getState()));
    }

    @Override
    public List<Product> getProducts(List<GoodsEntity> goodsEntityList) throws NullPointerException{
        List products = new ArrayList<Product>();
        for(GoodsEntity goodsEntity:goodsEntityList){
            products.add(getProduct(goodsEntity));
        }
        return products;
    }

    //获取该商品的第一张图片
    private String getPhoto(String photoGroup) throws NullPointerException{
        try{
            Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
            Query query=session.createQuery("select address from PhotoEntity where photoGroup = ? order by photoId desc ");
            String result = (String) query.setParameter(0,photoGroup).setMaxResults(1).list().get(0);
            session.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
