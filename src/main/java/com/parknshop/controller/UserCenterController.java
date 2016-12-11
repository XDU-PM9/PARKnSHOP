package com.parknshop.controller;

import com.parknshop.entity.CollectionEntity;
import com.parknshop.entity.CollectshopEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by H on 2016/12/6.
 */
@Controller
public class UserCenterController {


    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value="/listUserInfo",method = RequestMethod.GET)
    public String  listUserInfo(Model model, HttpSession session)
    {
         //  userEntity=session.getAttribute("user");
      //  UserEntity userEntity=customerService.getCustomerById(new Integer(userId));
        UserEntity  userEntity=(UserEntity)session.getAttribute("user");
        if(userEntity!=null) {
            model.addAttribute("user", userEntity);
            return "customer/User_Personalinfo.jsp";
        }
        else
        {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/goPassword",method = RequestMethod.GET)
    public String  goPassword(){
        return "customer/User_changePassword.jsp";
    }
//此处需要前端验证两次密码一致性，且应用Ajax判断密码的正确性，直至输对密码为止
    @RequestMapping(value="/changePassword",method = RequestMethod.POST)
    public String  changePassword(@RequestParam String  password, @RequestParam String  pass1, Model model,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null) {
            // UserEntity userEntity=customerService.getCustomerById(new Integer(userId));
            if (userEntity.getPassword().equals(password)) {
                customerService.changePassword(pass1, userEntity.getUserId());
                model.addAttribute("user", userEntity);
                return "customer/User_Personalinfo.jsp";
            } else
                return "customer/User_changePassword.jsp";
        }
        else
        {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/listCollect",method = RequestMethod.GET)
    public String listCollect(@RequestParam int requestPage, Model  model,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null) {
            int size = customerService.querySize(userEntity.getUserId());
            double d = size / 4;
            double sina = Math.ceil(d);
            List<CollectionEntity> collectionEntities = customerService.queryAllCollect(new Integer(userEntity.getUserId()), requestPage);
            model.addAttribute("Collects", collectionEntities);
            model.addAttribute("currentPage", requestPage);
            model.addAttribute("maxSize", size);
            model.addAttribute("sina", sina);
            return "customer/User_Collect.jsp";
        }
        else
        {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/listCollectShop",method = RequestMethod.GET)
    public String listCollectShop(@RequestParam int requestPage, Model  model,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null) {
            int size = customerService.queryShopsize(new Integer(userEntity.getUserId()));
            double d = size / 4;
            double sina = Math.ceil(d);
            List<CollectshopEntity> collectionEntities = customerService.queryAllShop(new Integer(userEntity.getUserId()), requestPage);
            model.addAttribute("Collects", collectionEntities);
            model.addAttribute("currentPage", requestPage);
            model.addAttribute("maxSize", size);
            model.addAttribute("sina", sina);
            return "customer/User_CollectShop.jsp";
        }else {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/insertCollectShop",method = RequestMethod.GET)
    public String  insertCollectShop(@RequestParam int  shopId,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null)
        {
            customerService.insertShop(shopId,userEntity.getUserId());
            return "redirect:listCollectShop?requestPage=1";
        }
        else
        {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/insertCollect",method = RequestMethod.GET)
    public String  insertCollect(@RequestParam int  goodsId,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null) {
            customerService.insertCollect(goodsId, userEntity.getUserId());
            return "redirect:listCollect?requestPage=1";
        }else {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/removeCollect",method = RequestMethod.GET)
    public String removeCollect(@RequestParam int collectionId)
    {
        customerService.removeCollect(new Integer(collectionId));
            return "redirect:listCollect?requestPage=1";
    }

    @RequestMapping(value="/removeCollectShop",method = RequestMethod.GET)
    public String removeCollectShop(@RequestParam int shopId)
    {
        customerService.removeShop(new Integer(shopId));
        return "redirect:listCollectShop?requestPage=1";
    }

}
