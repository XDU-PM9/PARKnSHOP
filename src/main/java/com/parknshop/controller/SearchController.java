package com.parknshop.controller;

import com.parknshop.service.customerService.IGetList;
import com.parknshop.service.customerService.ISearchProducts;
import com.parknshop.service.customerService.ISearchShops;
import com.parknshop.service.customerService.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-9.
 */
@Controller
public class SearchController {
    @Autowired
    ISearchProducts searchProducts;
    @Autowired
    IGetList getList;
    @Autowired
    ISearchShops searchShops;

    //Get请求搜索商品
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(){
        return "search.jsp";
    }

    //Get请求搜索店铺
    @RequestMapping(value = "/searchShop",method = RequestMethod.GET)
    public String searchShop(){
        return "searchShop.jsp";
    }
    /**
     * 通过类型搜索商品
     *
     * @param type
     * @param start
     * @param count
     * @return
     */
    @RequestMapping(value = "/searchByType", method = RequestMethod.POST)
    @ResponseBody
    public List searchByType(@RequestParam String type, @RequestParam int start, @RequestParam int count) {
        searchProducts.initRuler();
        try {
            return getList.getProducts(searchProducts.searchProductsByType(type, start, count));
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 通过商品ID搜索商品
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/searchById", method = RequestMethod.POST)
    @ResponseBody
    public Product searchById(@RequestParam int goodsId
//            ,@RequestParam boolean noFitler
    ) {
        searchProducts.initRuler();
//        if(noFitler){
//            searchProducts.setRuler(ISearchProducts.Ruler.ANYSTATE);
//        }
        try {
            return getList.getProduct(searchProducts.searchProductsById(goodsId));
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 通过商品名字搜索商品
     *
     * @param name
     * @param start
     * @param count
     * @return
     */
    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    @ResponseBody
    public List searchByName(@RequestParam String name, @RequestParam int start, @RequestParam int count) {
        searchProducts.initRuler();
        try {
            return getList.getProducts(searchProducts.searchProductsByName(name, start, count));
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * 通过指定的json参数搜索商品
     *
     * @param request boolean time;(按时间排序,新的在后面)
     *                boolean timeDesc;（按时间降序排序，新的在前面）
     *                boolean price;(价格)
     *                boolean priceDesc;
     *                boolean view;(浏览量)
     *                boolean viewDesc;
     *                boolean discount;（折扣）
     *                boolean discountDesc;
     *                boolean sales;（销量）
     *                boolean salesDesc;
     *                String name;（商品名）
     *                String type;（类型）
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List search(@RequestBody Map request) {
        searchProducts.initRuler();
        try {
            return getList.getProducts(searchProducts.searchProductsByMap(request));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到商品的数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProductCount", method = RequestMethod.POST)
    @ResponseBody
    public String productCount(@RequestBody Map request) {
        searchProducts.initRuler();
        try {
            return String.valueOf(searchProducts.setByMap(request).getCount());
        }catch (NullPointerException e){
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 获得搜索到的店铺数量
     * @param request
     * @return
     */
    @RequestMapping(value = "/getShopCount",method=RequestMethod.POST)
    @ResponseBody
    public String shopCount(@RequestBody Map request){
        return String.valueOf(searchShops.setByMap(request).getCount());
    }

    /**
     * 搜索店铺
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchShop",method = RequestMethod.POST)
    @ResponseBody
    public List searchShop(@RequestBody Map request){
        try{
            return getList.getShops(searchShops.setByMap(request).searchShop());
        }catch (NumberFormatException e){
            e.printStackTrace();
            return null;
        }
    }
}
