package com.parknshop.service.customerService;

import com.parknshop.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-5.
 */
public interface ISearchProducts {
    //搜索及过滤规则，带DESC后缀的表示逆序,ANYSTATE表示不过滤商品（能搜索到列入黑名单中及未激活的商品）
    public enum Ruler{
        TIME,PRICE,VIEW,SALES,DISCOUNT,TIMEDESC,PRICEDESC,VIEWDESC,SALESDESC,DISCOUNTDESC,ANYSTATE
    }
    //初始化规则,如不需要使用原先的规则，勿必先进行初始化
    public ISearchProducts initRuler();
    //设置规则
    public ISearchProducts setRuler(Ruler Ruler);
    public ISearchProducts setRuler(Map Ruler);
    //根据名字搜索商品
    public List<GoodsEntity> searchAllProductsByName(String name);
    //根据类型搜索商品
    public List<GoodsEntity> searchAllProductsByType(String type);
    //根据名字和类型搜索商品
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type);
    //根据名字搜索指定条数商品，即从第start条开始查询count条
    public List<GoodsEntity> searchProductsByName(String name, int start, int count);
    //根据类型搜索指定条数商品，即从第start条开始查询count条
    public List<GoodsEntity> searchProductsByType(String type, int start, int count);
    //根据名字搜索一页的商品，pageNum为页号（以1开始），pageSize为每页大小
    public List<GoodsEntity> searchProductsOnePageByName(String name, int pageNum, int pageSize);
    //根据类型搜索一页的商品，pageNum为页号（以1开始），pageSize为每页大小
    public List<GoodsEntity> searchProductsOnePageByType(String type, int pageNum, int pageSize);
    //根据名字和类型搜索指定条数商品，即从第start条开始查询count条
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type, int start, int count);
    //根据名字类型搜索一页的商品，pageNum为页号（以1开始），pageSize为每页大小
    public List<GoodsEntity> searchProductsOnePageByNameAndType(String name, String type, int pageNum, int pageSize);
    public List<GoodsEntity> searchProductsByMap(Map map);
    //根据商品ID搜索商品
    public GoodsEntity searchProductsById(int id);

    //设置搜索内容，设置完后调用getCount()得到数量或调用search()得到结果(注意设置规则以及规则重置）
    public ISearchProducts setGoodsName(String productName);
    public ISearchProducts setGoodsId(int goodsId);
    public ISearchProducts setGoodsType(String type);
    public ISearchProducts setShopId(int shopId);
    public ISearchProducts setScope(int start,int count);
    public long getCount();
    public List<GoodsEntity> search();
}