package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.PhotoEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.serviceImpl.OwnerBuilder;
import com.parknshop.service.serviceImpl.OwnerService;
import com.parknshop.service.serviceImpl.PersonShopListBean;
import com.parknshop.service.serviceImpl.UserService;
import com.parknshop.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by fallblank on 16-11-28.
 */
@Controller
@RequestMapping("/owner")
public class OwnerController {
    private static final String REQ_METHOD_GET = "GET";
    private static final String REQ_METHOD_POST = "POST";

    private static final String MSG = "msg";
    private static final String EMAIL = "email";

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

    @RequestMapping("/OwnerInformation")
    public String OwnerInfo(HttpServletRequest request) {
        return "owner/OwnerInformation.jsp";
    }

    @RequestMapping("/OwnerInfo_Edit")
    public String OwnerInfoEdit(HttpServletRequest request) {
        String method = request.getMethod();
        switch (method){
            case REQ_METHOD_GET:
                return "owner/OwnerInfo_Edit.jsp";
            case REQ_METHOD_POST:
                HttpSession session = request.getSession();
                OwnerEntity user = (OwnerEntity)session.getAttribute("user");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                user.setPhone(phone);
                user.setEmail(email);
                int state = mOwnerService.updateOwner(user);
                if(state == IOwnerService.UPDATE_SUCCESS){
                    return  "owner/updateSuccess.jsp";
                } else {
                    request.setAttribute(MSG,"Update failed");
                    return "owner/OwnerInfo_Edit.jsp";
                }

            default:
                return "owner/OwnerInfo_Edit.jsp";
        }
    }

    @RequestMapping("/ownerPassword_Edit")
    public  String OwnerPasswordEdit(HttpServletRequest request) {
        String method = request.getMethod();
        switch (method) {
            case REQ_METHOD_GET:
                return "owner/ownerPassword_Edit.jsp";
            case REQ_METHOD_POST:
                HttpSession session = request.getSession();

                OwnerEntity user = (OwnerEntity) session.getAttribute("user");
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmpwd = request.getParameter("confirmpwd");
                if (!user.getPassword().equals(oldPassword)) {
                    request.setAttribute(MSG, "OldPassword is wrong");
                    return "owner/ownerPassword_Edit.jsp";
                } else if (!newPassword.equals(confirmpwd)) {
                    request.setAttribute(MSG, "The passing words you entered must be the same in the latest two times");
                    return "owner/ownerPassword_Edit.jsp";
                } else {
                    user.setPassword(newPassword);
                    int state = mOwnerService.updateOwner(user);
                    if(state == IOwnerService.UPDATE_SUCCESS ){
                        return "owner/updateSuccess.jsp";
                    }else {
                        request.setAttribute(MSG,"Update failed");
                        return "owner/ownerPassword_Edit.jsp";
                    }
                }

            default:
                return "owner/ownerPassword_Edit.jsp";
        }
    }


}
