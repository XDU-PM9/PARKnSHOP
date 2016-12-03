package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.LoginRequestBean;
import com.parknshop.bean.OwnerLoginResponseBean;
import com.parknshop.bean.RegisterRequestBean;
import com.parknshop.bean.RegisterResponseBean;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.serviceImpl.OwnerBuilder;
import com.parknshop.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by fallblank on 16-11-28.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {

    public final static String LOGIN_SUCCESS = "Login successfully";
    public final static String LOGIN_FAILED = "Login failed,error parameter";
    public final static String LOGIN_ACCOUNT_UNACTIVATED = "Account has not been activated";

    final
    IUserService mService;
    final
    IUserBuilder mUserBuilder;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @Autowired
    public OwnerController(IUserService mService, OwnerBuilder mUserBuilder) {
        this.mService = mService;
        this.mUserBuilder = mUserBuilder;
    }

    /**
     * 处理卖家登录
     * @param ownerInfo post发送过来的json
     * @param session   后端生产的session
     * @return  结果数据，json格式
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    String loginResponse(@RequestBody byte[] ownerInfo,HttpSession session){
        String ownerJson = new String(ownerInfo);
        LoginRequestBean ownerLoginBean = mGson.fromJson(ownerJson,LoginRequestBean.class);
        System.out.println(ownerLoginBean);
        int stateCode;
        if (checkValid(ownerLoginBean.getUserName(),ownerLoginBean.getPassword())){
            stateCode =  mService.loginAsOwner(ownerLoginBean.getUserName(),ownerLoginBean.getPassword());
        }else {
            stateCode = IUserService.LOGIN_ELSE;
        }
        OwnerLoginResponseBean responseBean = new OwnerLoginResponseBean();
        responseBean.setDate(new Date());
        switch (stateCode){
            case IUserService.LOGIN_SUCCESS:
                responseBean.setError(false);
                responseBean.setMessage(LOGIN_SUCCESS);
                System.out.println(mGson.toJson(responseBean));
                OwnerEntity ownerEntity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
                OwnerLoginResponseBean.DataBean data = new OwnerLoginResponseBean.DataBean();
                data.setUserName(ownerEntity.getUsername());
                data.setImage(ownerEntity.getPicture());
                responseBean.setData(data);
                break;
            case IUserService.LOGIN_NOACTIVE:
                responseBean.setError(true);
                responseBean.setMessage(LOGIN_ACCOUNT_UNACTIVATED);
                break;
            default:
                responseBean.setError(true);
                responseBean.setMessage(LOGIN_FAILED);
                break;
        }
        return mGson.toJson(responseBean);
    }


    @RequestMapping(value = "/loginout",method = RequestMethod.POST)
    public @ResponseBody String loginout(){
        mService.loginOut();
        return "";
    }

    /**
     * 处理卖家注册
     * @param registerInfo
     * @return
     */
    @RequestMapping(value ="/register",method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody byte[] registerInfo){
        String registerStr = new String(registerInfo);
        System.out.println(registerStr);
        RegisterRequestBean registerBean = mGson.fromJson(registerStr, RegisterRequestBean.class);
        mUserBuilder.clear();
        mUserBuilder.setUserName(registerBean.getUserName());
        mUserBuilder.setPassWord(registerBean.getPassword());
        mUserBuilder.setEmail(registerBean.getEmail());
        int state = mService.registerByOwner(mUserBuilder);
        System.out.println("state:"+state);
        RegisterResponseBean responseBean = new RegisterResponseBean();
        responseBean.setDate(new Date());
        switch (state){
            case IUserService.SUCCESS:
                responseBean.setError(false);
                responseBean.setMessage("register successfully");
                RegisterResponseBean.DataBean dataBean = new RegisterResponseBean.DataBean();
                dataBean.setUserName(registerBean.getUserName());
                dataBean.setImge("/resources/images/a.png");
                responseBean.setData(dataBean);
                break;
            default:
                responseBean.setError(true);
                responseBean.setMessage("register failed");
                responseBean.setData(null);
        }
        return mGson.toJson(responseBean);

    }




    /**
     * 检查参数是否为空
     * @param name  username
     * @param password  密码
     * @return  false：无效    true：有效
     */
    boolean checkValid(String name,String password){
        return !(name.equals(null)||password.equals(null));
    }


    /**
     * 店家更新个人信息
     * @param registerInfo
     * @return
     */
    public @ResponseBody String updateInfo(@RequestBody byte[] registerInfo) {
     return "";
    }

}
