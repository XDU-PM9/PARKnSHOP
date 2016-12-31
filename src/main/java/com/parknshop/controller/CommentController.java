package com.parknshop.controller;

import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IOrderService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.ICommentService;
import com.parknshop.service.customerService.IOrderSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Created by Lenovo on 2016/12/26.
 */


@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private ICommentService commentService;
    @Autowired
    private IOrderSearchService orderService;

    @RequestMapping(value = "/toComment", method = RequestMethod.GET)
    public String toComment(@RequestParam int ordersId,Model model)
    {
        OrdersEntity ordersEntity=orderService.getOrderById(ordersId);
        if(null!=ordersEntity) {
            model.addAttribute("oders",ordersEntity);
            return "customer/comment_goods.jsp";
        }
        else
        {
            return "/";
        }
    }

    @RequestMapping(value = "/listComment", method = RequestMethod.GET)
    public String listComment(HttpSession session,Model model)
    {
        int userId=getUserId(session);
        List<OrdersEntity> objects;
        objects = commentService.listCom(userId);
        if(null!=objects) {
            model.addAttribute("orders",objects);
            return "/customer/comment.jsp";
        }
        else {
            return "/";
        }
    }
    @RequestMapping(value = "/insertComment", method = RequestMethod.POST)
    public String insertComment(@RequestParam("comment_context") String content,
                                @RequestParam("commentType")  int commentType,
                                 @RequestParam("ordersId") int ordersId)
    {

        if(commentService.insertComment(content,commentType,ordersId))
            return "redirect:/comment/listComment";
        else
            return "";
    }
    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}
