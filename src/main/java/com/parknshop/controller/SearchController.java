package com.parknshop.controller;

import com.parknshop.service.customerService.IGetProductList;
import com.parknshop.service.customerService.ISearchProducts;
import com.parknshop.service.customerService.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-9.
 */
public class SearchController {
    @Autowired
    ISearchProducts searchProducts;
    @Autowired
    IGetProductList ProductList;

    @RequestMapping(value = "/searchByType",method = RequestMethod.POST)
    @ResponseBody
    public List searchByType(@RequestParam String type, @RequestParam int start, @RequestParam int count){
        searchProducts.initRuler();
        try {
            return ProductList.getProducts(searchProducts.searchProductsByType(type, start, count));
        }catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "/searchById",method = RequestMethod.POST)
    @ResponseBody
    public Product searchById(@RequestParam int goodsId
//            ,@RequestParam boolean noFitler
    ){
        searchProducts.initRuler();
//        if(noFitler){
//            searchProducts.setRuler(ISearchProducts.Ruler.ANYSTATE);
//        }
        try {
            return ProductList.getProduct(searchProducts.searchProductsById(goodsId));
        }catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "/searchByName",method = RequestMethod.POST)
    @ResponseBody
    public List searchByName(@RequestParam String name,@RequestParam int start,@RequestParam int count){
        searchProducts.initRuler();
        try{
            return ProductList.getProducts(searchProducts.searchProductsByName(name,start,count));
        }catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public List search(@RequestBody Map request){
        searchProducts.initRuler();
        try {
            return ProductList.getProducts(searchProducts.searchProductsByMap(request));
        }catch (NumberFormatException e){
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

//    private List tipsNoResult(){
//        List list=new ArrayList();
//        list.add("No result");
//        return list;
//    }
}
