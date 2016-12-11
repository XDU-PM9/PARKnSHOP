package com.parknshop.service.customerService;

import com.parknshop.entity.CartEntity;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.ShopEntity;

import java.util.List;

/**
 * Created by wei on 16-12-9.
 */
public interface IGetList {
    public Cart getCart(CartEntity cartEntity);
    public List<Cart> getCarts(List<CartEntity> cartEntityList);
    public Product getProduct(GoodsEntity goodsEntity);
    public List<Product> getProducts(List<GoodsEntity> goodsEntityList);
    public Shop getShop(ShopEntity shopEntity);
    public List<Shop> getShops(List<ShopEntity> shopEntityList);
}
