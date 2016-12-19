package com.parknshop.controller;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.service.IOwnerService;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by song on 16-12-17.
 * @author songzhengteng
 */

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired private IOwnerService mOwnerService;
    public static final String KEY_GOODS = "goods";


    @RequestMapping(value = "/detail")
    public String detail(HttpServletRequest request){
        int id  = 0;
        try {
            id = Integer.parseInt(request.getParameter("goodsId"));
        }catch (Exception e){
            //未传参数，或者参数非法，跳转到首页
            return "redirect:/";
        }
        Log.debug("id:"+id);
        GoodsDbBean goods = mOwnerService.getGoods(id);
        request.setAttribute(KEY_GOODS,goods);
        return "goods/goods.jsp";
    }
}
