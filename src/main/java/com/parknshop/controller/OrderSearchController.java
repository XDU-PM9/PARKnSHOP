package com.parknshop.controller;

import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.IOrderSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Lenovo on 2016/12/26.
 */
@Controller
@RequestMapping("/viewBuyHistory")
public class OrderSearchController {


    //view buy history

    @Autowired
    private IOrderSearchService iOrderSearchService;


    @RequestMapping(value = "/toSearch",method = RequestMethod.GET)
    public String toSearch(Model model)
    {
        model.addAttribute("maxSize",0);
        return "/customer/user_orders.jsp";
    }

    //分页怎么办？
    @RequestMapping(value = "/dailySearch",method = RequestMethod.GET)
    public  String search(@RequestParam("day") String day,
                          @RequestParam("types")  String  types,
                          @RequestParam("page")  int page,
                          Model model, HttpSession session)
    {
        int userId=getUserId(session);
        List<OrdersEntity> ordersEntityList;
        switch (types)
        {
            case "daily":
                ordersEntityList=iOrderSearchService.datelySearch(day,userId);
                break;
            case "weekly":
                ordersEntityList=iOrderSearchService.weeklySearch(day,userId);
                break;
            case "monthly":
                ordersEntityList=iOrderSearchService.monthlySearch(day,userId);
                break;
            case "yearly":
                ordersEntityList=iOrderSearchService.yearlySearch(day,userId);
                break;
                default:ordersEntityList=null;
                    break;
        }

        int sizes=ordersEntityList.size();
        if(sizes==0) {
            model.addAttribute("ordersEntityList", null);
            model.addAttribute("maxSize",0);
        }else {
            int row = 1;
            double d = (sizes) / row + (sizes % row == 0 ? 0 : 1);
            int sina = (int) Math.ceil(d);
            int masSizes=0;
            masSizes=ordersEntityList.size();
            String descr="Have "+masSizes+" records In the "+day +" by "+types;
            List<OrdersEntity> ordersEntities = ordersEntityList.subList((page - 1) * row, page * row);
            model.addAttribute("ordersEntityList", ordersEntities);
            model.addAttribute("day", day);
            model.addAttribute("maxSize",masSizes);
            model.addAttribute("sina", sina);
            model.addAttribute("sizes", sizes);
            model.addAttribute("page", page);
            model.addAttribute("types",types);
            model.addAttribute("descr",descr);
        }
            return "/customer/user_orders.jsp";
    }

    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}
