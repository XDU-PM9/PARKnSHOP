package com.parknshop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parknshop.bean.*;
import com.parknshop.bean.CancelAdvertRequestBean;
import com.parknshop.entity.AdminEntity;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IAdminService;
import com.parknshop.service.IAdvertisement;
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
    @Autowired
    IAdvertisement mAdvert;

    Gson mGson = new GsonBuilder()
            .setDateFormat(DateFormat.getDateFormat())
            .create();

    @RequestMapping(value = "")
    public String dispatcher(){
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login.jsp";
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public String top() {
        return "admin/top.jsp";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "admin/menu.jsp";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "admin/main.jsp";
    }

    @RequestMapping(value = "applymanage", method = RequestMethod.GET)
    public String applymanage() {
        return "admin/applymanage.jsp";
    }

    @RequestMapping(value = "ownermanage", method = RequestMethod.GET)
    public String ownermanage() {
        return "admin/ownermanage.jsp";
    }
    @RequestMapping(value = "customermanage", method = RequestMethod.GET)
    public String customermanage() {
        return "admin/customermanage.jsp";
    }
    @RequestMapping(value = "shopmanage", method = RequestMethod.GET)
    public String shopmanage() {
        return "admin/shopmanage.jsp";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session) {
        if (!checkLogin(session)){
            return "redirect:/admin/login";
        }
        return "admin/index.jsp";
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
//        login = true;
        if (login) {
            String infoStr = new String(info);
            System.out.println(infoStr);
            ApplyRequestBean request = mGson.fromJson(infoStr,ApplyRequestBean.class);
            IListBean<ShopAndOwnerDbBean> dataList =
                    mAdminService.getApplyShop(request.getIndex(),request.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(request.getIndex()>total){//如果请求的页面大于实际的总页面数
                response.setError(true);
            }else {
                List<ApplyResponseBean.DataBean> data = new ArrayList<>();
                for (ShopAndOwnerDbBean entity : dataList.getShopList()) {
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
                response.setTotal((int) total);
                response.setRealSize((int) size);
                response.setData(data);
            }
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
                System.out.println("agree"+requestBean.getShopId());
               boolean state = mAdminService.suggestShop(requestBean.getShopId());
                if (state)
                    replyReponseBean.setError(false);
                else
                    replyReponseBean.setError(true);

            }
            else {
                System.out.println("disagree"+requestBean.getShopId());
                boolean state=mAdminService.rejectShop(requestBean.getShopId());
                if (state)
                    replyReponseBean.setError(false);
                else
                    replyReponseBean.setError(true);
            }
        }
        else
            replyReponseBean.setError(true);
        return mGson.toJson(replyReponseBean);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public  String loginout(){
        System.out.println("logout");
        mService.loginOut();
        return "redirect:/admin";
    }

    /**
     * 获取owner列表
     */
    @RequestMapping(value = "/applyallowner",method = RequestMethod.POST)
    public @ResponseBody String applyAllOwner(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ApplyAllOwnerResponseBean responseBean = new ApplyAllOwnerResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ApplyAllRequestBean requestBean = mGson.fromJson(infoStr,ApplyAllRequestBean.class);
            IListBean<OwnerEntity> dataList =
                    mAdminService.getOwnerList(requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<ApplyAllOwnerResponseBean.DataBean> data = new ArrayList<>();
                for (OwnerEntity entity : dataList.getShopList()) {
                    ApplyAllOwnerResponseBean.DataBean ownerListBean = new ApplyAllOwnerResponseBean.DataBean();
                    ownerListBean.setOwnerId(entity.getOwnerId());
                    ownerListBean.setUsername(entity.getUsername());
                    ownerListBean.setUserImage(entity.getUserImage());
                    ownerListBean.setRealname(entity.getRealname());
                    ownerListBean.setPhone(entity.getPhone());
                    ownerListBean.setEmail(entity.getEmail());
                    ownerListBean.setAddress(entity.getAddress());
                    ownerListBean.setState(entity.getState());
                    data.add(ownerListBean);
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(data);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
        }

    /**
     * 获取user列表
     * */
    @RequestMapping(value = "/applyalluser",method = RequestMethod.POST)
    public @ResponseBody String applyAllUser(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ApplyAllUserResponseBean responseBean = new ApplyAllUserResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ApplyAllRequestBean requestBean = mGson.fromJson(infoStr,ApplyAllRequestBean.class);
            IListBean<UserEntity> dataList =
                    mAdminService.getUserList(requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<ApplyAllUserResponseBean.DataBean> data = new ArrayList<>();
                for (UserEntity entity : dataList.getShopList()) {
                    ApplyAllUserResponseBean.DataBean userListBean = new ApplyAllUserResponseBean.DataBean();
                    userListBean.setUserId(entity.getUserId());
                    userListBean.setUsername(entity.getUsername());
                    userListBean.setUserImage(entity.getUserImage());
                    userListBean.setPhone(entity.getPhone());
                    userListBean.setEmail(entity.getEmail());
                    userListBean.setState(entity.getState());
                    data.add(userListBean);
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(data);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    /**
     * 获取shop列表
     * */
    @RequestMapping(value = "/applyallshop",method = RequestMethod.POST)
    public @ResponseBody String applyAllShop(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ApplyAllShopResponseBean responseBean = new ApplyAllShopResponseBean();
        if(isLogin){
            String infoStr = new String(info);
            ApplyAllRequestBean requestBean = mGson.fromJson(infoStr,ApplyAllRequestBean.class);
            System.out.println("before service");
            IListBean<ShopAndOwnerDbBean> dataList =
                    mAdminService.getAllShop(requestBean.getIndex(),requestBean.getSize());
            System.out.println("after service");
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                System.out.println("total:" + total + ",size:" + size);
                List<ApplyAllShopResponseBean.DataBean> data = new ArrayList<>();
                for (ShopAndOwnerDbBean bean : dataList.getShopList()) {
                    ApplyAllShopResponseBean.DataBean shopListBean = new ApplyAllShopResponseBean.DataBean();
                    shopListBean.setShopId(bean.getShopId());
                    shopListBean.setShopName(bean.getShopName());
                    shopListBean.setIntroduction(bean.getIntroduction());
                    shopListBean.setPhotoGroup(bean.getPhotoGroup());
                    shopListBean.setViews(bean.getViews());
                    shopListBean.setLogo(bean.getLogo());
                    shopListBean.setState(bean.getShopState());
                    shopListBean.setOwnerId(bean.getOwnerId());
                    data.add(shopListBean);
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(data);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    /**
     * 管理店主
     * */
    @RequestMapping(value = "/searchowner",method = RequestMethod.POST)
    public @ResponseBody String searchOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        SearchOwnerRequestBean requestBean = new SearchOwnerRequestBean();
        SearchOwnerResponseBean responseBean = new SearchOwnerResponseBean();
        SearchOwnerResponseBean.DataBean dataBean = new SearchOwnerResponseBean.DataBean();
//        isLogin=true;
        if (isLogin){
            String infoStr = new String(info);
            try {
                requestBean = mGson.fromJson(infoStr,SearchOwnerRequestBean.class);
            }catch (Exception e ){
                responseBean.setError(true);
                return mGson.toJson(responseBean);
            }

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
        responseBean.setData(dataBean);
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/blackowner",method = RequestMethod.POST)
    public @ResponseBody String blackOwner(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteOwnerRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteOwnerRequestBean.class);
            System.out.println(mAdminService.blackOwner(requestBean.getOwnerId()));
            responseBean.setError(!mAdminService.blackOwner(requestBean.getOwnerId()));

        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/whiteowner",method = RequestMethod.POST)
    public @ResponseBody String whiteOwner(@RequestBody byte[] info, HttpSession session){
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
    public @ResponseBody String deleteOwner(@RequestBody byte[] info, HttpSession session){
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
    public @ResponseBody String searchShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        SearchShopResponseBean responseBean = new SearchShopResponseBean();
        SearchShopResponseBean.DataBean dataBean = new SearchShopResponseBean.DataBean();
//        isLogin=true;
        SearchShopRequestBean requestBean = new SearchShopRequestBean();
        if (isLogin){
            String infoStr = new String(info);
            try {
                 requestBean = mGson.fromJson(infoStr, SearchShopRequestBean.class);
            }
            catch (Exception e){
                responseBean.setError(true);
                return mGson.toJson(responseBean);
            }

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

        responseBean.setData(dataBean);
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/blackshop",method = RequestMethod.POST)
    public @ResponseBody String blackShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.blackShop(requestBean.getShopId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/whiteshop",method = RequestMethod.POST)
    public @ResponseBody String whiteShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.whiteShop(requestBean.getShopId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/deleteshop",method = RequestMethod.POST)
    public @ResponseBody String deleteShop(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteShopRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteShopRequestBean.class);
            responseBean.setError(!mAdminService.deleteShop(requestBean.getShopId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    /**
     * 管理User
     * */
    @RequestMapping(value = "/searchuser",method = RequestMethod.POST)
    public @ResponseBody String searchUser(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        SearchUserRequestBean requestBean = new SearchUserRequestBean();
        SearchUserResponseBean responseBean = new SearchUserResponseBean();
        SearchUserResponseBean.DataBean dataBean = new SearchUserResponseBean.DataBean();
//        isLogin=true;
        if (isLogin){
            String infoStr = new String(info);
            try {
                requestBean = mGson.fromJson(infoStr, SearchUserRequestBean.class);
            }
            catch (Exception e){
                responseBean.setError(true);
                return mGson.toJson(responseBean);
            }
            UserEntity userEntity = mAdminService.getUserById(requestBean.getUserId());
            if(userEntity != null){
                responseBean.setError(false);
                dataBean.setUserId(userEntity.getUserId());
                dataBean.setUsername(userEntity.getUsername());
                dataBean.setUserImage(userEntity.getUserImage());
                dataBean.setPhone(userEntity.getPhone());
                dataBean.setEmail(userEntity.getEmail());
                dataBean.setState(userEntity.getState());
            }else{
                responseBean.setError(true);
            }
        }else {
            responseBean.setError(true);
        }
        responseBean.setData(dataBean);
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/blackuser",method = RequestMethod.POST)
    public @ResponseBody String blackUser(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteUserRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteUserRequestBean.class);
            responseBean.setError(!mAdminService.blackUser(requestBean.getUserId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/whiteuser",method = RequestMethod.POST)
    public @ResponseBody String whiteUser(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteUserRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteUserRequestBean.class);
            responseBean.setError(!mAdminService.whiteUser(requestBean.getUserId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
    public @ResponseBody String deleteuser(@RequestBody byte[] info, HttpSession session){
        boolean isLogin = mService.isLogin();
        BlackorWhiteorDeleteResponseBean responseBean = new BlackorWhiteorDeleteResponseBean();
//        isLogin=true;
        if(isLogin){
            String infoStr = new String(info);
            BlackorWhiteorDeleteUserRequestBean requestBean = mGson.fromJson(infoStr,BlackorWhiteorDeleteUserRequestBean.class);
            responseBean.setError(!mAdminService.deleteUser(requestBean.getUserId()));
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    //给广告列表响应Bean的DataBean添加数据
    private boolean AddInfoToAdvertDataBean(GetAdvertListResponseBean.DataBean dateBean,
                                         AdvertisementDbBean advertBean,int state){
        dateBean.setAdvertId(advertBean.getAdvertId());
        dateBean.setStartTime(advertBean.getStartTime());
        dateBean.setPrice(advertBean.getPrice());
        dateBean.setState(advertBean.getState());

        GetAdvertListResponseBean.DataBean.DetailBean detailBean
                = new GetAdvertListResponseBean.DataBean.DetailBean();
        //state为1代表广告商品列表
        if(1 == state && advertBean.getGoodsEntity() !=  null) {
            detailBean.setId(advertBean.getGoodsEntity().getGoodsId());
            detailBean.setName(advertBean.getGoodsEntity().getGoodsName());
            detailBean.setIntroduction(advertBean.getGoodsEntity().getIntroduction());
            dateBean.setDetail(detailBean);
            return true;
        }
        else if(0 == state && advertBean.getShopEntity() != null){
            detailBean.setId(advertBean.getShopEntity().getShopId());
            detailBean.setName(advertBean.getShopEntity().getShopName());
            detailBean.setIntroduction(advertBean.getShopEntity().getIntroduction());
            dateBean.setDetail(detailBean);
            return true;
        }else{
            return false;
        }

//        if(bean.getOwnerEntity() != null) {
//            GetAdvertListResponseBean.DataBean.DetailOfOwnerBean detailOfOwnerBean
//                    = new GetAdvertListResponseBean.DataBean.DetailOfOwnerBean();
//            detailOfOwnerBean.setOwnerid(bean.getOwnerEntity().getOwnerId());
//            detailBean.setName(bean.getOwnerEntity().getRealname());
//            dateBean.setDetailOfOwner(detailOfOwnerBean);
//        }else{
//            return false;
//        }
    }

    /**
     * 管理广告--top10商品
     * */
    @RequestMapping(value = "/replyGoodsAdvert",method = RequestMethod.POST)
    public @ResponseBody String replyGoogsAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ReplyReponseBean responseBean = new ReplyReponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ReplyAdvertRequestBean requestBean = mGson.fromJson(infoStr,ReplyAdvertRequestBean.class);
            if(1 == requestBean.getResult()){//管理员传入同意该广告请求
                //广告的Id
                boolean state = mAdvert.acceptGoods(requestBean.getId());
                //state为true，代表同意成功，返回error=false代表请求被同意
                responseBean.setError(!state);
            }else{
                boolean state = mAdvert.rejectGoods(requestBean.getId());
                //state为true，代表拒绝成功，返回error=false代表请求被拒绝
                responseBean.setError(!state);
            }
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/cancelGoodsAdvert",method = RequestMethod.POST)
    public @ResponseBody String cancelGoodsAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ReplyReponseBean responseBean = new ReplyReponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            CancelAdvertRequestBean requestBean = mGson.fromJson(infoStr,CancelAdvertRequestBean.class);
            //传入广告id
            boolean state = mAdvert.cancelGoods(requestBean.getId());
            responseBean.setError(!state);//处理状态为true，即处理成功，返回error=false
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getTop10GoodsAdvert")
    public @ResponseBody String getTop10GoodsAdvert(HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            List<AdvertisementDbBean> dataList =
                    mAdvert.getTopGoods();
            int size = dataList.size();
            List<GetAdvertListResponseBean.DataBean> responseDataList = new ArrayList<>();
            for (AdvertisementDbBean advertBean : dataList) {
                GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                boolean result = AddInfoToAdvertDataBean(dateBean,advertBean,1);
                if(result == true) responseDataList.add(dateBean);
                else size--;
            }
            responseBean.setError(false);
            responseBean.setTotal(1);
            responseBean.setRealSize(size);
            responseBean.setData(responseDataList);
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getAllGoodsAdvert",method = RequestMethod.POST)
    public @ResponseBody String getAllGoodsAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ApplyAllRequestBean requestBean = mGson.fromJson(infoStr,ApplyAllRequestBean.class);
            IListBean<AdvertisementDbBean> dataList =
                    mAdvert.getAllGoods(requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<GetAdvertListResponseBean.DataBean> dataBeanList = new ArrayList<>();
                for (AdvertisementDbBean advertBean : dataList.getShopList()) {
                    GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                    boolean result = AddInfoToAdvertDataBean(dateBean,advertBean,1);
                    if(result == true) dataBeanList.add(dateBean);
                    else size--;
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(dataBeanList);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getUserGoodsAdvert",method = RequestMethod.POST)
    public @ResponseBody String getUserGoodsAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            GetUserAdvertListRequestBean requestBean = mGson.fromJson(infoStr,GetUserAdvertListRequestBean.class);
            IListBean<AdvertisementDbBean> dataList =
                    mAdvert.getMyGoods(requestBean.getUserId(),requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<GetAdvertListResponseBean.DataBean> dataBeanList = new ArrayList<>();
                for (AdvertisementDbBean advertBean : dataList.getShopList()) {
                    System.out.println("total:"+total+"size:"+size);
                    GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                    boolean result = AddInfoToAdvertDataBean(dateBean,advertBean,1);
                    if(result == true) dataBeanList.add(dateBean);
                    else size--;
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(dataBeanList);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }


    /**
     * 管理广告--top5商店
     * */
    @RequestMapping(value = "/replyShopAdvert",method = RequestMethod.POST)
    public @ResponseBody String replyShopAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ReplyReponseBean responseBean = new ReplyReponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ReplyAdvertRequestBean requestBean = mGson.fromJson(infoStr,ReplyAdvertRequestBean.class);
            if(0 != requestBean.getResult()){//管理员传入同意该广告请求
                boolean state = mAdvert.acceptShop(requestBean.getId());
                //state为true，代表拒绝成功，返回error=false代表请求被同意
                responseBean.setError(!state);
            }else{
                boolean state = mAdvert.rejectShop(requestBean.getId());
                //state为true，代表拒绝成功，返回error=false代表请求被拒绝
                responseBean.setError(!state);
            }
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/cancelShopAdvert",method = RequestMethod.POST)
    public @ResponseBody String cancelShopAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        ReplyReponseBean responseBean = new ReplyReponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            CancelAdvertRequestBean requestBean =
                    mGson.fromJson(infoStr,CancelAdvertRequestBean.class);
            //传入广告id
            boolean state = mAdvert.cancelShop(requestBean.getId());
            responseBean.setError(!state);//处理状态为true，即处理成功，返回error=false
        }else{
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getTop5ShopAdvert")
    public @ResponseBody String getTop5ShopAdvert(HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            List<AdvertisementDbBean> dataList = mAdvert.getTopShop();
            int size = dataList.size();
            System.out.println(size);
            List<GetAdvertListResponseBean.DataBean> dataBeanList = new ArrayList<>();
            for (AdvertisementDbBean advertBean : dataList) {
                GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                boolean result = AddInfoToAdvertDataBean(dateBean,advertBean,0);
                if(result == true) dataBeanList.add(dateBean);
                else size--;
            }
            responseBean.setError(false);
            responseBean.setTotal(1);
            responseBean.setRealSize(size);
            responseBean.setData(dataBeanList);
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getAllShopAdvert",method = RequestMethod.POST)
    public @ResponseBody String getAllShopAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            ApplyAllRequestBean requestBean = mGson.fromJson(infoStr,ApplyAllRequestBean.class);
            IListBean<AdvertisementDbBean> dataList =
                    mAdvert.getAllShop(requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<GetAdvertListResponseBean.DataBean> dataBeanList = new ArrayList<>();
                for (AdvertisementDbBean advertBean : dataList.getShopList()) {
                    System.out.println("total:"+total+"size:"+size);
                    GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                    boolean result = AddInfoToAdvertDataBean(dateBean,advertBean,0);
                    if(result == true) dataBeanList.add(dateBean);
                    else size--;
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(dataBeanList);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }

    @RequestMapping(value = "/getUserShopAdvert",method = RequestMethod.POST)
    public @ResponseBody String getUserShopAdvert(@RequestBody byte[] info,HttpSession session){
        boolean isLogin = mService.isLogin();
        GetAdvertListResponseBean responseBean = new GetAdvertListResponseBean();
//        isLogin = true;
        if(isLogin){
            String infoStr = new String(info);
            GetUserAdvertListRequestBean requestBean = mGson.fromJson(infoStr,GetUserAdvertListRequestBean.class);
            IListBean<AdvertisementDbBean> dataList =
                    mAdvert.getMyShop(requestBean.getUserId(),requestBean.getIndex(),requestBean.getSize());
            long total = dataList.getMaxPages();
            long size = dataList.getNumer();
            if(requestBean.getIndex()>total){
                responseBean.setError(true);
            }else {
                List<GetAdvertListResponseBean.DataBean> data = new ArrayList<>();
                for (AdvertisementDbBean bean : dataList.getShopList()) {
                    GetAdvertListResponseBean.DataBean dateBean = new GetAdvertListResponseBean.DataBean();
                    boolean result = AddInfoToAdvertDataBean(dateBean,bean,0);
                    if(result == true) data.add(dateBean);
                    else size--;
                }
                responseBean.setError(false);
                responseBean.setTotal((int) total);
                responseBean.setRealSize((int) size);
                responseBean.setData(data);
            }
        }else {
            responseBean.setError(true);
        }
        return mGson.toJson(responseBean);
    }


    /**
     * 登录检查
     * @author 宋正腾
     * @param session
     * @return
     */
    private boolean checkLogin(HttpSession session) {
        if (!mService.isLogin()) {
            return false;
        }
        //防止不同类型用户的登陆影响
        try {
            AdminEntity entity = (AdminEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity) {
                throw new Exception("未登陆");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
