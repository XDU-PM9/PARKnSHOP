package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.CartEntity;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.PhotoEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.customerService.Cart;
import com.parknshop.service.customerService.IGetList;
import com.parknshop.service.customerService.Product;
import com.parknshop.service.customerService.Shop;
import com.parknshop.utils.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 16-12-9.
 */
@Service
public class GetList implements IGetList {

    @Resource
    private BaseDao<PhotoEntity> photoEntityBaseDao;
//    @Autowired
//    IPictureDao pictureDao;
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
                .setGoodsAmount(goodsEntity.getInventory())
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
                .setViews(goodsEntity.getViews())
                .setSales(goodsEntity.getSales())
                .setShopId(goodsEntity.getShopByShopId().getShopId())
                .setShopName(goodsEntity.getShopByShopId().getShopName())
                .setTips(String.valueOf(goodsEntity.getState()));
    }

    @Override
    public List<Product> getProducts(List<GoodsEntity> goodsEntityList){
        if(null==goodsEntityList){
            return null;
        }
        List products = new ArrayList<Product>();
        for(GoodsEntity goodsEntity:goodsEntityList){
            products.add(getProduct(goodsEntity));
        }
        return products;
    }

    @Override
    public Shop getShop(ShopEntity shopEntity) {
        return new Shop(shopEntity.getShopId(),shopEntity.getShopName(),shopEntity.getIntroduction(),shopEntity.getLogo(),shopEntity.getViews());
    }

    @Override
    public List<Shop> getShops(List<ShopEntity> shopEntityList) {
        if(null==shopEntityList){
            return null;
        }
        List shops=new ArrayList<Shop>();
        for(ShopEntity shopEntity:shopEntityList){
            shops.add(getShop(shopEntity));
        }
        return shops;
    }
    private  String   getPhoto(String  photogroup)
    {
        if(!"".equals(photogroup)&&null!=photogroup) {
            try {
//                return pictureDao.getPictures(photogroup).get(0).getAddress();
                return photoEntityBaseDao.find("from PhotoEntity where photoGroup=? order by photoId desc", new Object[]{photogroup}).get(0).getAddress();
            }catch (Exception e){
                Log.debug("photogroup \""+photogroup+"\" 不存在");
//                e.printStackTrace();
                return "";
            }
        }
        else
        {
            return  "";
        }
    }
    //获取该商品的第一张图片
 /*   private String getPhoto(String photoGroup) throws NullPointerException{
        if(null==photoGroup||"".equals(photoGroup)){
            return "";
        }
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
    }*/
}
