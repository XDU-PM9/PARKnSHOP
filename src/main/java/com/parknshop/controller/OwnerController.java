package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.bean.owner.GoodsDetaiResponse;
import com.parknshop.bean.owner.GoodsDetailBean;
import com.parknshop.bean.owner.GoodsListBean;
import com.parknshop.bean.owner.GoodsRequest;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.PhotoEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.*;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.baseImpl.IUploadPictures;
import com.parknshop.service.serviceImpl.builder.OwnerBuilder;
import com.parknshop.utils.DateFormat;
import com.parknshop.utils.Log;
import com.parknshop.utils.OwnerFileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public static final String GOODS = "goods";

    final IUserService mUserService;
    final IUserBuilder mUserBuilder;
    final IOwnerService mOwnerService;
    final IGoodsBuilder mGoodsBuilder;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @Autowired
    public OwnerController(IUserService mUserService, OwnerBuilder mUserBuilder, IOwnerService ownerService, IGoodsBuilder mGoodsBuilder) {
        this.mUserService = mUserService;
        this.mUserBuilder = mUserBuilder;
        mOwnerService = ownerService;
        this.mGoodsBuilder = mGoodsBuilder;
    }

    @RequestMapping("")
    public String dispatcher() {
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
                mUserService.loginOut();//注销session
                int state = mUserService.loginAsOwner(username, password);
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
        mUserService.loginOut();
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
                int state = mUserService.registerByOwner(mUserBuilder);
                if (state == IUserService.SUCCESS) {
                    request.setAttribute(EMAIL, email);
                    return "owner/register_success.jsp";
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

    @RequestMapping("/ownerInfo")
    public String OwnerInfo(HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        OwnerEntity user = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        if (user.getUserImage() == null) {
            user.setUserImage("/resources/images/owner/default_owner.png");
        }
        return "owner/owner_info.jsp";
    }

    @RequestMapping(value = "/ownerInfoEdit", method = RequestMethod.GET)
    public String OwnerInfoEditGet(HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        return "owner/owner_info_edit.jsp";
    }


    @RequestMapping(value = "/ownerInfoEdit", method = RequestMethod.POST)
    public String OwnerInfoEditPost(HttpServletRequest request, HttpSession session,
                                    @RequestParam("username") String username,
                                    @RequestParam("email") String email,
                                    @RequestParam("phone") String phone) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }

        OwnerEntity user = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);

        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);
        int state = mOwnerService.updateOwner(user);
        if (state == IOwnerService.UPDATE_SUCCESS) {
            return "owner/update_success.jsp";
        } else {
            request.setAttribute(MSG, "Update failed");
            return "owner/owner_info_edit.jsp";
        }

    }

    @RequestMapping("/ownerPasswordEdit")
    public String OwnerPasswordEdit(HttpServletRequest request) {
        if (!checkLogin(request.getSession())) {
            return "redirect:/owner/login";
        }
        String method = request.getMethod();
        switch (method) {
            case REQ_METHOD_GET:
                return "owner/owner_password_edit.jsp";
            case REQ_METHOD_POST:
                HttpSession session = request.getSession();

                OwnerEntity user = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
                String oldPassword = request.getParameter("old");
                String newPassword = request.getParameter("new");
                String confirmpwd = request.getParameter("confirm");
                if (!user.getPassword().equals(oldPassword)) {
                    request.setAttribute(MSG, "Old password is wrong.");
                    return "owner/owner_password_edit.jsp";
                } else if (!newPassword.equals(confirmpwd)) {
                    request.setAttribute(MSG, "The password you entered must be the same.");
                    return "owner/owner_password_edit.jsp";
                } else if (newPassword.equals(user.getPassword())) {
                    request.setAttribute(MSG, "The new password is the same as old password");
                    return "owner/owner_password_edit.jsp";
                } else {
                    user.setPassword(newPassword);
                    int state = mOwnerService.updateOwner(user);
                    if (state == IOwnerService.UPDATE_SUCCESS) {
                        return "owner/update_success.jsp";
                    } else {
                        request.setAttribute(MSG, "Update failed");
                        request.setAttribute(MSG, "Server occur a unknown error");
                        return "owner/owner_password_edit.jsp";
                    }
                }
            default:
                return "owner/owner_password_edit.jsp";
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
            switch (item.getShopState()) {
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
                            @RequestParam(value = "theme") MultipartFile[] themeImages,
                            @RequestParam(value = "realName") String realName,
                            @RequestParam(value = "idNumber") String idNumber,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "shopName") String shopName,
                            @RequestParam(value = "shopDesc") String shopDesc) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }

        String contextPath = session.getServletContext().getRealPath("/");
        String person, logo;
        final int count = themeImages.length;
        String[] themes = new String[count];
        try {
            person = OwnerFileSaver.saveImage(personImage, contextPath);
            logo = OwnerFileSaver.saveImage(logoImage, contextPath);
            for (int i = 0; i < count; i++) {
                themes[i] = OwnerFileSaver.saveImage(themeImages[i], contextPath);
            }
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
                for (int i = 0; i < count; i++) {
                    list.add(themes[i]);
                }
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


    @RequestMapping(value = "goodsPage")
    public String getGoodsPage(HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/";
        }
        return "owner/show_goods.html";
    }


    /**
     * 返回商品列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/goods")
    public @ResponseBody String listGoods(HttpServletRequest request, @RequestBody String dataStr) {
        if (!checkLogin(request.getSession())) {
            return "redirect:/owner/login";
        }
        HttpSession session = request.getSession();
        Log.debug(dataStr);
        GoodsListBean goodsList = new GoodsListBean();
        GoodsRequest bean = mGson.fromJson(dataStr, GoodsRequest.class);

        //检查检查检查检确确make sure shop state is normal
        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        int state = mOwnerService.isHasShop(entity);
        if (state != IOwnerService.SHOP_STATE_USING) {
            goodsList.setSuccess(false);
        } else {
            goodsList.setSuccess(true);
        }

        int page = bean.getIndex();//Integer.parseInt(request.getParameter("page"));
        int lines = bean.getSize();//Integer.parseInt(request.getParameter("lines"));

        ShopEntity shopEntity = getShop(session);
        IListBean<GoodsDbBean> data = mOwnerService.getMyGoods(shopEntity, page, lines);


        goodsList.setCurrent((int) data.getCurrentPage());
        goodsList.setTotal((int) data.getMaxPages());
        goodsList.setCount((int) data.getNumer());


        List<GoodsListBean.DataBean> list = new ArrayList<>();
        if (data.getShopList() != null) {
            for (GoodsDbBean item : data.getShopList()) {
                GoodsListBean.DataBean goods = new GoodsListBean.DataBean();
                goods.setId(item.getGoodsId());
                goods.setName(item.getGoodsName());
                goods.setDesc(item.getIntroduction());
                goods.setPrice(item.getPrice());
                goods.setDiscount(item.getDiscount());
                goods.setCreateTime(item.getCreateTime());
                goods.setViews(item.getViews());
                goods.setState(item.getState());
                goods.setType(item.getType());
                goods.setPostWay(item.getPostWay());

                int count = item.getPicturePath().size();
                String[] photos = new String[count];
                List<PhotoEntity> photoEntities = item.getPicturePath();
                for (int i = 0; i < count; i++) {
                    photos[i] = photoEntities.get(i).getAddress();
                }
                goods.setPhotos(photos);

                list.add(goods);
            }
        }
        goodsList.setData(list);
        String temp = mGson.toJson(goodsList);
        Log.debug(temp);
        return mGson.toJson(goodsList);
    }

    @RequestMapping(value = "/addGoods", method = RequestMethod.GET)
    public String addGoodsGet(HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        return "owner/add_goods.html";
    }


    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public String addGoodsPost(HttpSession session,
                               @RequestParam("name") String name,
                               @RequestParam("desc") String desc,
                               @RequestParam("price") double price,
                               @RequestParam("inventory") int inventory,
                               @RequestParam("goods_type") String type,
                               @RequestParam("post_way")String postWay,
                               @RequestParam("photo") MultipartFile[] photos) {
        if (!checkLogin(session)) {
            return "redirect:/owner/login";
        }
        System.out.println("type:" + type);
        final int count = photos.length;
        String contextPath = session.getServletContext().getRealPath("/");
        String[] photoPaths = new String[count];
        try {
            for (int i = 0; i < count; i++) {
                photoPaths[i] = OwnerFileSaver.saveImage(photos[i], contextPath);
            }
        } catch (Exception e) {
            //服务器存储错误
            return "redirect:/";
        }
        OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        mGoodsBuilder.clear();
        mGoodsBuilder
                .setOwnerId(entity.getOwnerId())
                .setGoodsName(name)
                .setIntroduction(desc)
                .setPrice(price)
                .setType(type)
                .setPostWay(postWay)
                .setInventory(inventory);
        IUploadPictures callback = new IUploadPictures() {
            @Override
            public List<String> getPicturePaths() {
                List<String> photos = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    photos.add(photoPaths[i]);
                }
                return photos;
            }
        };
        int state = mOwnerService.newGoods(mGoodsBuilder, callback);
        if (state == IOwnerService.NEW_SUCCESS) {
            return "owner/add_goods_success.jsp";
        }

        return "owner/add_goods_fail.jsp";
    }

    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteGoods(HttpServletRequest request, @RequestBody byte[] data) {
        if (!checkLogin(request.getSession())) {
            return "redirect:/login";
        }
        String dataStr = new String(data);
        DeleteGoodsRequestBean requestBean = mGson.fromJson(dataStr, DeleteGoodsRequestBean.class);
        DeleteGoodsResponseBean responseBean = new DeleteGoodsResponseBean();
        boolean success = mOwnerService.deletGoods(requestBean.getGoodsID());
        responseBean.setSuccess(success);
        return mGson.toJson(request);
    }

    @RequestMapping(value = "goodDetail",method = RequestMethod.POST)
    public @ResponseBody String goodDetail(HttpServletRequest request,@RequestBody String data){

        GoodsDetailBean bean = mGson.fromJson(data,GoodsDetailBean.class);
        int id = bean.getId();
        GoodsDbBean goods = mOwnerService.getGoods(id);
        GoodsDetaiResponse response = new GoodsDetaiResponse();
        if (goods==null){
            response.setSuccess(false);
        }else {
            response.setSuccess(true);
            GoodsDetaiResponse.DataBean goodbean = new GoodsDetaiResponse.DataBean();
            goodbean.setId(goods.getGoodsId());
            goodbean.setName(goods.getGoodsName());
            goodbean.setDesc(goods.getIntroduction());
            goodbean.setPrice(goods.getPrice());
            goodbean.setDiscount(goodbean.getDiscount());
            goodbean.setCreateTime(goods.getCreateTime());
            goodbean.setViews(goods.getViews());
            goodbean.setState(goods.getState());
            goodbean.setPostWay(goods.getPostWay());

            List<PhotoEntity> list = goods.getPicturePath();
            int size = list.size();
            String[] photos = new String[size];
            for (int i = 0;i<size;i++){
                photos[i] = list.get(i).getAddress();
            }
            goodbean.setPhotos(photos);
            response.setData(goodbean);
        }

        return mGson.toJson(response);
    }

    /**
     * 更新商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateGoods", method = RequestMethod.POST)
    public String updateGoods(HttpServletRequest request) {
        return null;
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
        if (!mUserService.isLogin()) {
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


    private ShopEntity getShop(HttpSession session) {
        OwnerEntity ownerEntity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        IListBean<ShopAndOwnerDbBean> data = mOwnerService.getMyShop(ownerEntity, 1, 1000);
        List<ShopAndOwnerDbBean> shops = data.getShopList();
        if (shops == null) {
            return null;
        }
        ShopEntity entity;
        for (ShopAndOwnerDbBean item : shops) {
            int state = item.getShopState();
            if (state == IOwnerService.SHOP_STATE_USING) {
                entity = new ShopEntity();
                entity.setShopId(item.getShopId());
                return entity;
            }
        }
        return null;
    }

}
