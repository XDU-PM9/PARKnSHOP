package com.parknshop.controller;

import com.parknshop.service.customerService.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Lenovo on 2016/12/26.
 */


@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private ICommentService commentService;
    @RequestMapping(value = "/insertComment", method = RequestMethod.GET)
    public String test(@RequestParam("content") String content,
                       @RequestParam("ordersId") int ordersId)
    {

        if(commentService.insertComment(content,ordersId))
            return "";
        else
            return "";
    }

}
