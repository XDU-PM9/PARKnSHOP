package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IListBean;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fallblank on 16-11-28.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {
    private static final String REQ_METHOD_GET = "GET";
    private static final String REQ_METHOD_POST = "POST";

    public static final String MSG = "msg";
    public static final String EMAIL = "email";
    public static final String SHOPS = "shops";

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

    @RequestMapping("/index")
    public String index(HttpSession session){
        OwnerEntity entity;
        if (!mService.isLogin()){
            return "redirect:/owner/login";
        }
        try {
            entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        }catch (Exception e){
            return "redirect:/owner/login";
        }
        return "owner/index.jsp";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String method = request.getMethod();
        switch (method){
            case REQ_METHOD_GET:
                return "owner/login.jsp";
            case REQ_METHOD_POST:
                String username = (String) request.getParameter("username");
                String password = (String) request.getParameter("password");
                mService.loginOut();//注销session
                int state = mService.loginAsOwner(username,password);
                if (state == IUserService.LOGIN_SUCCESS){
                    return "redirect:/owner/index";
                }else if (state == IUserService.LOGIN_ERRO){
                    request.setAttribute(MSG,"Login failed");
                }else if (state == IUserService.LOGIN_HASDELETE){
                    request.setAttribute(MSG,"Account has been Suspended");
                }else if (state == IUserService.LOGIN_HASLOGIN){
                    request.setAttribute(MSG,"Account has been logined");
                }else if (state == IUserService.LOGIN_NOACTIVE){
                    request.setAttribute(MSG,"Account has not been activated");
                }else if (state == IUserService.LOGIN_ERROPARAM){
                    request.setAttribute(MSG,"Error in username or password");
                }else {
                    request.setAttribute(MSG,"Unknown error");
                }
                return "owner/login.jsp";
            default:
                return "owner/login.jsp";
        }
    }

    @RequestMapping("logout")
    public String logout(){
        mService.loginOut();
        return "redirect:/owner/login";
    }

    @RequestMapping("/register")
    public String regist(HttpServletRequest request){
        String method = request.getMethod();
        switch (method){
            case REQ_METHOD_GET:
                return "owner/register.jsp";
            case REQ_METHOD_POST:
                String email = request.getParameter("e-mail");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                mUserBuilder.clear();
                mUserBuilder.setEmail(email);
                mUserBuilder.setUserName(username);
                mUserBuilder.setPassWord(password);
                int state = mService.registerByOwner(mUserBuilder);
                if (state == IUserService.SUCCESS){
                    request.setAttribute(EMAIL,email);
                    return "owner/registerSuccess.jsp";
                }else if (state == IUserService.ERRO_EMPTYEMAIL){
                    request.setAttribute(MSG,"Empty e-mail");
                }else if (state == IUserService.ERRO_EMiAL){
                    request.setAttribute(MSG,"E-mail address has been registed");
                }else if (state == IUserService.ERRO_PHONE){
                    request.setAttribute(MSG,"Phone number has been registed");
                }else if (state == IUserService.ERRO_NAME){
                    request.setAttribute(MSG,"Username has been registed");
                }else {
                    request.setAttribute(MSG,"Unknown Error");
                }
                return  "owner/register.jsp";
            default:
                return "owner/register.jsp";
        }
    }


    /**
     * 查询开店情况
     * @return 根据用户信息返回具体页面
     */
    @RequestMapping("/query")
    public String query(HttpSession session,HttpServletRequest request){
        //处理未登陆的意外情况
        if (!mService.isLogin()){
            return "redirect:/owner/login";
        }
        //处理登陆用户不是owner的情况
        OwnerEntity entity;
        try {
            entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity){
                throw new Exception("未登陆");
            }
        }catch (Exception e){
            return "redirect:/owner/login";
        }

        int state = mOwnerService.isHasShop(entity);
        if (canApply(entity)){
            request.setAttribute(MSG,"");
        }else {
            IListBean<ShopAndOwnerDbBean> temp = mOwnerService.getMyShop(entity,1,10000);
            int count = (int) temp.getNumer();
            List<ShopAndOwnerDbBean> list = temp.getShopList();
            ShopBean shopBean = new ShopBean();
            shopBean.setCount(count);
            List<ShopBean.Shop> shopList = new ArrayList<>();
            for (ShopAndOwnerDbBean item : list){
                ShopBean.Shop shop = new ShopBean.Shop();
                shop.setName(item.getShopName());
                shop.setDesc(item.getIntroduction());
                shop.setLogo(item.getLogo());
                shop.setState(item.getShopState());
                shopList.add(shop);
            }
            request.setAttribute(SHOPS,mGson.toJson(shopBean));
        }
        return "owner/query_shops.jsp";
    }

    @RequestMapping("/apply")
    public String apply(HttpSession session,HttpServletRequest request){
        //处理未登陆的意外情况
        if (!mService.isLogin()){
            return "redirect:/owner/login";
        }
        //处理登陆用户不是owner的情况
        OwnerEntity entity;
        try {
            entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity){
                throw new Exception("未登陆");
            }
        }catch (Exception e){
            return "redirect:/owner/login";
        }
        if (request.getMethod() == REQ_METHOD_GET){
            return "owner/apply_shop.jsp";
        }
        String realName = request.getParameter("realName");
        String id = request.getParameter("idNumber");
        String shopName = request.getParameter("shopName");
        String shopDesc = request.getParameter("shopDesc");
        System.out.println(realName+":"+id+"\n"+shopName+":"+shopDesc);
        return "owner/apply_shop.jsp";

    }

    /**
     * 检查是否能开店
     */
    private boolean canApply(OwnerEntity entity){
        int state = mOwnerService.isHasShop(entity);
        if (state == IOwnerService.SHOP_STATE_NOSHOP || state == IOwnerService.SHOP_STATE_REJECT){
            return true;
        }
        return false;
    }
}
