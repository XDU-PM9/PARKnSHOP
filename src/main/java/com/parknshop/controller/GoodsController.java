package com.parknshop.controller;

import com.parknshop.utils.Log;
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

    @RequestMapping(value = "/detail")
    public String detail(HttpServletRequest request){
        String id =  request.getParameter("goodsId");

        Log.debug("id:"+id);

        return "goods/goods.jsp";

    }
}
