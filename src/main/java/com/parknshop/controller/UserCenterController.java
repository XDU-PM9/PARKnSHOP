package com.parknshop.controller;

import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by H on 2016/12/6.
 */
@Controller
public class UserCenterController {


    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value="/listUserInfo",method = RequestMethod.GET)
    public String  listUserInfo(@RequestParam int userId, Model model)
    {
        UserEntity userEntity=customerService.getCustomerById(new Integer(userId));
        model.addAttribute("user", userEntity);
        return "customer/User_Personalinfo.jsp";
    }

    @RequestMapping(value="/goPassword",method = RequestMethod.GET)
    public String  goPassword(){
        return "customer/User_changePassword.jsp";
    }
//此处需要前端验证两次密码一致性，且应用Ajax判断密码的正确性，直至输对密码为止
    @RequestMapping(value="/changePassword",method = RequestMethod.POST)
    public String  changePassword(@RequestParam int userId,
                                  @RequestParam String  password,
                                  @RequestParam String  pass1,
                                  Model model)
    {
        UserEntity userEntity=customerService.getCustomerById(new Integer(userId));
        if(userEntity.getPassword().equals(password)) {
            customerService.changePassword(pass1, userId);
            model.addAttribute("user", userEntity);
            return "customer/User_Personalinfo.jsp";
        }
        else
            return "customer/User_changePassword.jsp";
    }

}
