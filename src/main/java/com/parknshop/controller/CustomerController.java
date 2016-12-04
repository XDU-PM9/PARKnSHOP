package com.parknshop.controller;

import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.enumStatic.LoginTypeEnum;
import com.parknshop.service.serviceImpl.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.parknshop.service.IUserService.LOGIN_SUCCESS;

/**
 * Created by wei on 16-12-1.
 */
//用户管理
@Controller
public class CustomerController{
    private final IUserService userService;
    private final IUserBuilder userBuilder;

    @Autowired
    public CustomerController(IUserService userService, UserBuilder userBuilder) {
        this.userService = userService;
        this.userBuilder = userBuilder;
    }

    @RequestMapping(value = "/customer/login",method = RequestMethod.GET)
    public String login(){
        return "customerLogin";
    }

    /**
     * 用户登录，接收一个json参数并返回json
     * @param request
     */
    @RequestMapping(value = "/customer/login",method = RequestMethod.POST)
    public @ResponseBody Map customerLogin(@RequestBody Map request //映射为Map
                                )throws Exception{
        Map response=new HashMap<String,Object>();
        int status=userService.loginAsUser((String)request.get("userName"),(String)request.get("password"));
        if(LOGIN_SUCCESS==status){
            response.put("error","false");
            response.put("message","login success");
            Map<String,String> data=new HashMap<String,String>();
            data.put("userName", (String) request.get("userName"));
            //TODO:用户头像网址
            data.put("imge","picture");
            response.put("data",data);
        }else {
            response.put("error", "true");
            switch (status) {
                case IUserService.LOGIN_ERRO:response.put("message", "Login fail,please check your username or password");break;
                case IUserService.LOGIN_HASDELETE:response.put("message","Account has been frozen");break;
                case IUserService.LOGIN_HASLOGIN:response.put("message","You have login the account");break;
                case IUserService.LOGIN_NOACTIVE:response.put("message","You account not activated");break;
                case IUserService.LOGIN_ERROPARAM:response.put("message","Param error");break;
                case IUserService.LOGIN_ELSE:response.put("message","Other Error");break;
                default:response.put("message","Other Error");break;
            }
        }
        response.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return response;    //自动转化为json
    }

    /**
     * 参数有效性检查，即检查用户名/邮箱是否存在
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/check",method = RequestMethod.POST)
    public @ResponseBody Map checkUserName(@RequestBody Map request){
        Map response=new HashMap<String,String>();
        response.put("status",userService.checkUserExist(LoginTypeEnum.USER, (String) request.get("data"))?"true":"false");
        return response;
    }

    @RequestMapping(value = "/customerRegister",method = RequestMethod.GET)
    public String register()
    {
        return "customerRegister";
    }

    /**
     * 用户注册
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/customer/register",method = RequestMethod.POST)
    public @ResponseBody Map customerRegister(@RequestBody Map request)throws Exception{
        userBuilder.clear();
        userBuilder.setUserName((String)request.get("userName")).setPassWord((String)request.get("password"))
                //.setPhone((String)request.get("phone"))
                .setEmail((String)request.get("email"));
        int status=userService.registerByUser(userBuilder);
        Map response=new HashMap<String,String>();
        if(IUserService.SUCCESS==status){
            response.put("error","false");
            response.put("message","Register success");
            Map<String,String> data=new HashMap<String,String>();
            data.put("userName", (String) request.get("userName"));
            //TODO:用户头像网址
            data.put("imge","picture");
            response.put("data",data);
        }else {
            response.put("error", "true");
            switch (status) {
                case IUserService.ERRO_EMPTYEMAIL:response.put("message", "Please input your email");break;
                case IUserService.ERRO_EMiAL:response.put("message","Email has been registered");break;
                case IUserService.ERRO_PHONE:response.put("message","Phone has been registered");break;
                case IUserService.ERRO_NAME:response.put("message","Username has been registered");break;
                case IUserService.ERRO_ELSE:response.put("message","Other error");break;
                case IUserService.ERRO_TYPE:response.put("message","Incorrect paramter");break;
                case IUserService.ERRO_SENDEMAIL:response.put("message","Activate email send fail");break;
                default:response.put("message","Unknown error");break;
            }
        }
        response.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return response;
    }

    @RequestMapping(value = "/customer/information",method = RequestMethod.POST)
    public @ResponseBody Map customerInformation(@RequestBody Map request){
        Map response=new HashMap();
        return response;
    }
}
