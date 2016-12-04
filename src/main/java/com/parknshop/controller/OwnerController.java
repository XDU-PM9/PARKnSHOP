package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IOwnerService;
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
    final IOwnerService mOwnerService;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @Autowired
    public OwnerController(IUserService mService, OwnerBuilder mUserBuilder, IOwnerService ownerService) {
        this.mService = mService;
        this.mUserBuilder = mUserBuilder;
        mOwnerService = ownerService;
    }

    /**
     * 处理卖家登录
     * @param ownerInfo post发送过来的json
     * @param session   后端生产的session
     * @return  结果数据，json格式
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody byte[] ownerInfo,HttpSession session){
        String ownerJson = new String(ownerInfo);
        LoginRequestBean request = mGson.fromJson(ownerJson,LoginRequestBean.class);
        System.out.println(request);
        mService.loginOut();
        int stateCode;
        if (checkValid(request.getUserName(),request.getPassword())){
            stateCode =  mService.loginAsOwner(request.getUserName(),request.getPassword());
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
                //查询店铺信息
                queryShopInfo(request.getUserName(),responseBean);
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

    //未完
    private void queryShopInfo(String username,OwnerLoginResponseBean responseBean){

        return;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody String loginout(){
        System.out.println("loginout");
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



    private static final String MSG_STATE_ELSE = "Unknown error";
    private static final String MSG_STATE_NOSHOP = "The shop don't exists";
    private static final String MSG_STATE_DELETE = "The shop has been deleted";
    private static final String MSG_STATE_REJECT = "The shop Apply has been rejected ";
    private static final String MSG_STATE_CHECKING = "The shop is applying";
    private static final String MSG_STATE_BLACK = "The shop is in blacklist";
    private static final String MSG_STATE_USING = "The shop has been admitted,business is normal";
    /**
     * 检查当前用户是否已经开店
     * @return
     */
    @RequestMapping(value = "/applycheck",method = RequestMethod.POST)
    public @ResponseBody String applyCheck(HttpSession session){
        OwnerEntity owner = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        ShopCheckBean response = new ShopCheckBean();
        if (null == owner){
            response.setError(true);
            return mGson.toJson(response);
        }
        int state = mOwnerService.isHasShop(owner);
        String msg = MSG_STATE_ELSE;
        int status = 1;
        switch (state){
            case IOwnerService.SHOP_STATE_NOSHOP:
                msg = MSG_STATE_NOSHOP;
                status = 0;
                break;
            case IOwnerService.SHOP_STATE_DELETE:
                msg = MSG_STATE_DELETE;
                status = 1;
                break;
            case IOwnerService.SHOP_STATE_CHECKING:
                msg = MSG_STATE_CHECKING;
                status = 1;
                break;
            case IOwnerService.SHOP_STATE_BLAKE:
                msg = MSG_STATE_BLACK;
                state = 1;
                break;
            case IOwnerService.SHOP_STATE_USING:
                msg = MSG_STATE_USING;
                state = 1;
                break;
            default:
                msg = MSG_STATE_ELSE;
                status = 1;
                break;
        }
        response.setError(false);
        response.setStatus(status);
        response.setMessage(msg);
        return mGson.toJson(response);
    }

}
