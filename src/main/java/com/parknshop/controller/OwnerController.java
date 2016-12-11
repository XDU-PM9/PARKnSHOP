package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.baseImpl.IUploadPictures;
import com.parknshop.service.serviceImpl.OwnerBuilder;
import com.parknshop.utils.DateFormat;
import com.parknshop.utils.OwnerFileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @RequestMapping("")
    public String dispatcher(){
        return "redirect:/owner/index";
    }

    @RequestMapping("/index")
    public String index(HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        return "owner/index.jsp";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String method = request.getMethod();
        switch (method) {
            case REQ_METHOD_GET:
                return "owner/login.jsp";
            case REQ_METHOD_POST:
                String username = (String) request.getParameter("username");
                String password = (String) request.getParameter("password");
                mService.loginOut();//注销session
                int state = mService.loginAsOwner(username, password);
                if (state == IUserService.LOGIN_SUCCESS) {
                    return "redirect:/owner/index";
                } else if (state == IUserService.LOGIN_ERRO) {
                    request.setAttribute(MSG, "Login failed");
                } else if (state == IUserService.LOGIN_HASDELETE) {
                    request.setAttribute(MSG, "Account has been Suspended");
                } else if (state == IUserService.LOGIN_HASLOGIN) {
                    request.setAttribute(MSG, "Account has been logined");
                } else if (state == IUserService.LOGIN_NOACTIVE) {
                    request.setAttribute(MSG, "Account has not been activated");
                } else if (state == IUserService.LOGIN_ERROPARAM) {
                    request.setAttribute(MSG, "Error in username or password");
                } else {
                    request.setAttribute(MSG, "Unknown error");
                }
                return "owner/login.jsp";
            default:
                return "owner/login.jsp";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        mService.loginOut();
        return "redirect:/owner/login";
    }

    @RequestMapping("/register")
    public String regist(HttpServletRequest request) {
        String method = request.getMethod();
        switch (method) {
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
                if (state == IUserService.SUCCESS) {
                    request.setAttribute(EMAIL, email);
                    return "owner/registerSuccess.jsp";
                } else if (state == IUserService.ERRO_EMPTYEMAIL) {
                    request.setAttribute(MSG, "Empty e-mail");
                } else if (state == IUserService.ERRO_EMiAL) {
                    request.setAttribute(MSG, "E-mail address has been registed");
                } else if (state == IUserService.ERRO_PHONE) {
                    request.setAttribute(MSG, "Phone number has been registed");
                } else if (state == IUserService.ERRO_NAME) {
                    request.setAttribute(MSG, "Username has been registed");
                } else {
                    request.setAttribute(MSG, "Unknown Error");
                }
                return "owner/register.jsp";
            default:
                return "owner/register.jsp";
        }
    }

    @RequestMapping("/OwnerInformation")
    public String OwnerInfo() {
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
                } else if(newPassword.equals(user.getPassword())){
                    request.setAttribute(MSG,"The new password is the same as old password");
                    return "owner/ownerPassword_Edit.jsp";
                } else {
                    user.setPassword(newPassword);
                    int state = mOwnerService.updateOwner(user);
                    if (state == IOwnerService.UPDATE_SUCCESS) {
                        return "owner/updateSuccess.jsp";
                    } else {
                        request.setAttribute(MSG, "Update failed");
                        return "owner/ownerPassword_Edit.jsp";
                    }
                }

            default:
                return "owner/ownerPassword_Edit.jsp";
        }
    }

    /**
     * 查询开店情况
     *
     * @return 根据用户信息返回具体页面
     */
    @RequestMapping("/query")
    public String query(HttpSession session, HttpServletRequest request) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);

        if (canApply(entity)) {
            request.setAttribute(MSG, "1");
        } else {
            request.setAttribute(MSG, "0");
        }
        IListBean<ShopAndOwnerDbBean> temp = mOwnerService.getMyShop(entity, 1, 10000);
        int count = (int) temp.getNumer();
        List<ShopAndOwnerDbBean> list = temp.getShopList();
        ShopBean shopBean = new ShopBean();
        shopBean.setCount(count);
        List<ShopBean.Shop> shopList = new ArrayList<>();
        for (ShopAndOwnerDbBean item : list) {
            ShopBean.Shop shop = new ShopBean.Shop();
            shop.setName(item.getShopName());
            shop.setDesc(item.getIntroduction());
            shop.setLogo(item.getLogo());
            switch (item.getShopState()){
                case IOwnerService.SHOP_STATE_USING:
                    shop.setState("Normal");
                    break;
                case IOwnerService.SHOP_STATE_BLAKE:
                    shop.setState("Blacklist");
                    break;
                case IOwnerService.SHOP_STATE_CHECKING:
                    shop.setState("Applying");
                    break;
                case IOwnerService.SHOP_STATE_REJECT:
                    shop.setState("Rejected");
                    break;
                case IOwnerService.SHOP_STATE_DELETE:
                    shop.setState("Deleted");
                    break;
                case IOwnerService.SHOP_STATE_NOSHOP:
                    shop.setState("No shop");
                    break;
                default:
                    shop.setState("Unknown error");
                    break;
            }
            shopList.add(shop);
        }
        shopBean.setShops(shopList);
        request.setAttribute(SHOPS, mGson.toJson(shopBean));

        return "owner/query_shops.jsp";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public String applyGet(HttpSession session) {
        //处理未登陆的意外情况
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        return "owner/apply_shop.html";
    }


    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String applyPost(HttpSession session,
                            @RequestParam(value = "person") MultipartFile personImage,
                            @RequestParam(value = "logo") MultipartFile logoImage,
                            @RequestParam(value = "theme") MultipartFile themeImage,
                            @RequestParam(value = "realName") String realName,
                            @RequestParam(value = "idNumber") String idNumber,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "shopName") String shopName,
                            @RequestParam(value = "shopDesc") String shopDesc) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }

        String contextPath = session.getServletContext().getRealPath("/");
        String person, logo, theme;
        try {
            person = OwnerFileSaver.saveOwnerImage(personImage, contextPath);
            logo = OwnerFileSaver.saveOwnerImage(logoImage, contextPath);
            theme = OwnerFileSaver.saveOwnerImage(themeImage, contextPath);
        } catch (Exception e) {
            //服务器存储错误
            return "redirect:/";
        }
        OwnerEntity ownerEntity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        ownerEntity.setRealname(realName);
        ownerEntity.setIdentityId(idNumber);
        ownerEntity.setPhone(phone);
        mOwnerService.updateOwner(ownerEntity);

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setShopName(shopName);
        shopEntity.setIntroduction(shopDesc);
        shopEntity.setLogo(logo);

        IUploadPictures callback = new IUploadPictures() {
            @Override
            public List<String> getPicturePaths() {
                List<String> list = new ArrayList<>();
                list.add(theme);
                return list;
            }
        };
        int state = mOwnerService.newShop(ownerEntity, shopEntity, callback);
        if (state == IOwnerService.NEW_SUCCESS) {
            return "owner/apply_shop_success.jsp";
        } else {
            return "owner/apply_shop_fail.jsp";
        }
    }



    /**
     * 检查是否能开店
     */
    private boolean canApply(OwnerEntity entity) {
        int state = mOwnerService.isHasShop(entity);
        if (state == IOwnerService.SHOP_STATE_NOSHOP || state == IOwnerService.SHOP_STATE_REJECT) {
            return true;
        }
        return false;
    }


    /**
     * 检查是否登陆
     *
     * @param session 请求携带的session
     * @return false未登陆，true已登录
     */
    private boolean checkLogin(HttpSession session) {
        if (!mService.isLogin()) {
            return false;
        }
        //防止不同类型用户的登陆影响
        try {
            OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity) {
                throw new Exception("未登陆");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
