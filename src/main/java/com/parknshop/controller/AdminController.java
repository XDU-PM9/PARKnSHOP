package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.AdminEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IAdminService;
import com.parknshop.service.IListBean;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by niewenzhi on 2016/11/26.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IUserService mService;
    @Autowired
    IAdminService mAdminService;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "redirect:/#/admin/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody byte[] info, HttpSession session){
        mService.loginOut();
        String infoStr = new String(info);
        System.out.println(infoStr);
        LoginRequestBean requestBean = mGson.fromJson(infoStr,LoginRequestBean.class);
        int state = mService.loginAsAdmin(requestBean.getUserName(),requestBean.getPassword());
        AdminLoginResponseBean responseBean = new AdminLoginResponseBean();
        responseBean.setDate(new Date());
        AdminLoginResponseBean.DataBean data = new AdminLoginResponseBean.DataBean();
        if (state == IUserService.LOGIN_SUCCESS){
            responseBean.setError(false);
            responseBean.setMessage("Login successfully");
            AdminEntity adminEntity = (AdminEntity) session.getAttribute(IDefineString.SESSION_USER);
            data.setUserName(adminEntity.getUsername());
        }else {
            responseBean.setError(true);
            responseBean.setMessage("Login failed");
        }
        responseBean.setData(data);
        return mGson.toJson(responseBean);
    }

    /**
     * 店家的申请展示
     * @param info
     * @return
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public @ResponseBody String apply(@RequestBody byte[] info,HttpSession session){
        boolean login = mService.isLogin();
        ApplyResponseBean response = new ApplyResponseBean();
        if (login) {
            String infoStr = new String(info);
            System.out.println(infoStr);
            ApplyRequestBean request = mGson.fromJson(infoStr,ApplyRequestBean.class);
            IListBean<ShopAndOwnerDbBean> dataList =
                    mAdminService.getApplyShop(request.getIndex(),request.getIndex());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            List<ApplyResponseBean.DataBean> data= new ArrayList<>();
            for (ShopAndOwnerDbBean entity:dataList.getShopList()){
                ApplyResponseBean.DataBean applyEntity = new ApplyResponseBean.DataBean();
                applyEntity.setOwnerName(entity.getUsername());
                applyEntity.setOwnerImg(entity.getPicture());
                applyEntity.setOwnerEmail(entity.getEmail());
                applyEntity.setRealName(entity.getRealname());
                applyEntity.setRealImg(entity.getUserImage());
                applyEntity.setShopId(entity.getShopId());
                applyEntity.setShopName(entity.getShopName());
                applyEntity.setShopImg(entity.getLogo());
                applyEntity.setShopDesc(entity.getIntroduction());
                data.add(applyEntity);
            }
            response.setTotal((int)total);
            response.setRealSize((int)size);
            response.setData(data);
        }else {
            response.setError(true);
        }
        return mGson.toJson(response);
    }
    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    public @ResponseBody String agree(@RequestBody byte[] info,HttpSession session){
        boolean islogin = mService.isLogin();
        ShopCheckBean shopCheckBean = new ShopCheckBean();
        ReplyReponseBean replyReponseBean = new ReplyReponseBean();
        /*islogin=true;*/
        /*postman测试时取消上面注释,默认是登录状态*/
        if (islogin){
            String infoStr = new String(info);
            ReplyRequestBean requestBean=mGson.fromJson(infoStr,ReplyRequestBean.class);
            if(0!=requestBean.getResult()){
               boolean state = mAdminService.suggestShop(requestBean.getShopId());
                if (state)
                replyReponseBean.setError(false);
                else
                    replyReponseBean.setError(true);

            }
            else {
                boolean state=mAdminService.rejectShop(requestBean.getShopId());
                if (state)
                replyReponseBean.setError(true);
                else
                    replyReponseBean.setError(false);
            }

        }
        else
        replyReponseBean.setError(true);
        return mGson.toJson(replyReponseBean);
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody String loginout(){
        System.out.println("logout");
        mService.loginOut();
        return "";
    }

    /**
     * 管理店主
     * */
    @RequestMapping(value = "/searchowner",method = RequestMethod.POST)
    public @ResponseBody String SearchOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        SearchOwnerResponseBean responseBean = new SearchOwnerResponseBean();
        SearchOwnerResponseBean.DataBean dataBean = new SearchOwnerResponseBean.DataBean();
        isLogin=true;
        if (isLogin){
            String infoStr = new String(info);
            SearchOwnerRequestBean requestBean = mGson.fromJson(infoStr,SearchOwnerRequestBean.class);
            OwnerEntity ownerEntity = mAdminService.getOwnerById(requestBean.getOwnerId());
            if(ownerEntity != null){
                responseBean.setError(false);
                dataBean.setOwnerId(ownerEntity.getOwnerId());
                dataBean.setUsername(ownerEntity.getUsername());
                dataBean.setUserImage(ownerEntity.getUserImage());
                dataBean.setRealname(ownerEntity.getRealname());
                dataBean.setPhone(ownerEntity.getPhone());
                dataBean.setEmail(ownerEntity.getEmail());
                dataBean.setAddress(ownerEntity.getAddress());
                dataBean.setState(ownerEntity.getState());
            }else{
                responseBean.setError(true);
            }
        }else {
            responseBean.setError(true);
        }
        if(responseBean.isError() == true){//如果response的error=true，给dataBean赋一组明显错误的值
            dataBean.setOwnerId(-11111);
            dataBean.setUsername("--");
            dataBean.setUserImage("--");
            dataBean.setRealname("--");
            dataBean.setPhone("--");
            dataBean.setEmail("--");
            dataBean.setAddress("--");
            dataBean.setState(-11111);
        }
        responseBean.setData(dataBean);
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/blackowner",method = RequestMethod.POST)
    public @ResponseBody String BlackOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteOwnerRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteOwnerRequestBean.class);
            responseBean.setError(!mAdminService.blackOwner(requestBean.getOwnerId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/whiteowner",method = RequestMethod.POST)
    public @ResponseBody String WhiteOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteOwnerRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteOwnerRequestBean.class);
            responseBean.setError(!mAdminService.whiteOwner(requestBean.getOwnerId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/deleteowner",method = RequestMethod.POST)
    public @ResponseBody String DeleteOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteOwnerRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteOwnerRequestBean.class);
            responseBean.setError(!mAdminService.deleteOwner(requestBean.getOwnerId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    /**
     * 管理店铺
     * */
    @RequestMapping(value = "/searchshop",method = RequestMethod.POST)
    public @ResponseBody String SearchShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        SearchShopResponseBean responseBean = new SearchShopResponseBean();
        SearchShopResponseBean.DataBean dataBean = new SearchShopResponseBean.DataBean();
//        isLogin=true;
        if (isLogin){
            String infoStr = new String(info);
            SearchShopRequestBean requestBean = mGson.fromJson(infoStr,SearchShopRequestBean.class);
            ShopAndOwnerDbBean shopAndOwnerBean = mAdminService.getShopById(requestBean.getShopid());
            if(shopAndOwnerBean != null){
                responseBean.setError(false);
                dataBean.setShopId(shopAndOwnerBean.getShopId());
                dataBean.setShopName(shopAndOwnerBean.getShopName());
                dataBean.setIntroduction(shopAndOwnerBean.getIntroduction());
                dataBean.setPhotoGroup(shopAndOwnerBean.getPhotoGroup());
                dataBean.setViews(shopAndOwnerBean.getViews());
                dataBean.setLogo(shopAndOwnerBean.getLogo());
                dataBean.setState(shopAndOwnerBean.getShopState());
                dataBean.setOwnerId(shopAndOwnerBean.getOwnerId());
            }else{
                responseBean.setError(true);
            }
        }else {
            responseBean.setError(true);
        }
        if(responseBean.isError() == true){//如果response的error=true，给dataBean赋一组明显错误的值
            dataBean.setShopId(-11111);
            dataBean.setShopName("--");
            dataBean.setIntroduction("--");
            dataBean.setPhotoGroup("--");
            dataBean.setViews(-11111);
            dataBean.setLogo("--");
            dataBean.setState(-11111);
            dataBean.setOwnerId(-11111);
        }
        responseBean.setData(dataBean);
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/blackshop",method = RequestMethod.POST)
    public @ResponseBody String BlackShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.blackShop(requestBean.getShopid()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/whiteshop",method = RequestMethod.POST)
    public @ResponseBody String WhiteShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.whiteShop(requestBean.getShopid()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/deleteshop",method = RequestMethod.POST)
    public @ResponseBody String DeleteShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.deleteShop(requestBean.getShopid()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }
}
