package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.AdminEntity;
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
    @RequestMapping(value = "apply",method = RequestMethod.POST)
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
            List<ApplyResponseBean.ApplyEntity> data= new ArrayList<>();
            for (ShopAndOwnerDbBean entity:dataList.getShopList()){
                ApplyResponseBean.ApplyEntity applyEntity = new ApplyResponseBean.ApplyEntity();
                applyEntity.setOwnerName(entity.getUsername());

            }

        }else {
            response.setError(true);
        }
        return "";
    }
    @RequestMapping(value = "agree",method = RequestMethod.POST)
    public @ResponseBody String agree(@RequestBody byte[] info,HttpSession session){
        return "";
    }

    @RequestMapping(value = "disagree",method = RequestMethod.POST)
    public @ResponseBody String disagree(@RequestBody byte[] info,HttpSession session){
        return "";
    }
}
