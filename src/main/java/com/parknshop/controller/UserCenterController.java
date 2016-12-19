package com.parknshop.controller;

import com.parknshop.entity.CollectionEntity;
import com.parknshop.entity.CollectshopEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.ICustomerService;
import com.parknshop.utils.OwnerFileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by H on 2016/12/6.
 */
@Controller
public class UserCenterController {


    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value="/listUserInfo",method = RequestMethod.GET)
    public String  listUserInfo(Model model, HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:customer/login";
        }else{
            UserEntity userEntity=customerService.getCustomerById(new Integer(userId));
            model.addAttribute("user", userEntity);
            return "customer/user_info.jsp";
        }

    }

    @RequestMapping(value = "/uploadPicture",method = RequestMethod.POST)
    public  String uploadPicture(@RequestParam MultipartFile picture, HttpSession session, Model model)
    {
        String contextPath = session.getServletContext().getRealPath("/");
        String person, logo;
        try {
            person = OwnerFileSaver.saveImage(picture, contextPath);
            model.addAttribute("status",true);
        } catch (Exception e) {
            //服务器存储错误
            e.printStackTrace();
        }finally {
            return "/customer/user_info.jsp";
        }
    }
    @RequestMapping(value="/goPassword",method = RequestMethod.GET)
    public String  goPassword(){
        return "customer/password_edit.jsp";
    }
//此处需要前端验证两次密码一致性，且应用Ajax判断密码的正确性，直至输对密码为止
    @RequestMapping(value="/changePassword",method = RequestMethod.POST)
    public String  changePassword(@RequestParam String  password, @RequestParam String  pass1, Model model,HttpSession session)
    {
        UserEntity  userEntity=(UserEntity)session.getAttribute(IDefineString.SESSION_USER);
        if(userEntity!=null) {
            if (userEntity.getPassword().equals(password)) {
                customerService.changePassword(pass1, userEntity.getUserId());
                model.addAttribute("user", userEntity);
                return "customer/user_info.jsp";
            } else {
                model.addAttribute("tips","Old Password Error");
                return "customer/password_edit.jsp";
            }
        }
        else
        {
            return "redirect:customer/login";
        }
    }

    @RequestMapping(value="/listCollect",method = RequestMethod.GET)
    public String listCollect(@RequestParam int requestPage, Model  model,HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:customer/login";
        }else{
            int size = customerService.querySize(userId);
            double d = size / 4;
            int  sina =(int) Math.floor(d)+1;
            List<CollectionEntity> collectionEntities = customerService.queryAllCollect(new Integer(userId), requestPage);
            model.addAttribute("Collects", collectionEntities);
            model.addAttribute("currentPage", requestPage);
            model.addAttribute("maxSize", size);
            model.addAttribute("sina", sina);
            return "customer/good_collection.jsp";
        }

    }

    @RequestMapping(value="/listCollectShop",method = RequestMethod.GET)
    public String listCollectShop(@RequestParam int requestPage, Model  model,HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:customer/login";
        }else{
            int size = customerService.queryShopsize(new Integer(userId));
            double d = size / 4;
            int  sina =(int) Math.floor(d)+1;
            List<CollectshopEntity> collectionEntities = customerService.queryAllShop(new Integer(userId), requestPage);
            model.addAttribute("Collects", collectionEntities);
            model.addAttribute("currentPage", requestPage);
            model.addAttribute("maxSize", size);
            model.addAttribute("sina", sina);
            return "customer/shop_collection.jsp";
        }
    }

    @RequestMapping(value="/insertCollectShop",method = RequestMethod.GET)
    public String  insertCollectShop(@RequestParam int  shopId,HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:customer/login";
        }else{
            customerService.insertShop(shopId,userId);
            return "redirect:listCollectShop?requestPage=1";
        }
    }


    @RequestMapping(value="/insertCollect",method = RequestMethod.GET)
    public String  insertCollect(@RequestParam int  goodsId,HttpSession session)
    {
        int userId=getUserId(session);
        if(userId<1){
            return "redirect:customer/login";
        }else{
            customerService.insertCollect(goodsId, userId);
            return "redirect:listCollect?requestPage=1";
        }
    }

    @RequestMapping(value="/removeCollect",method = RequestMethod.GET)
    public String removeCollect(@RequestParam int collectionId)
    {
        customerService.removeCollect(new Integer(collectionId));
            return "redirect:listCollect?requestPage=1";
    }

    @RequestMapping(value="/removeCollectShop",method = RequestMethod.GET)
    public String removeCollectShop(@RequestParam int shopId)
    {
        customerService.removeShop(new Integer(shopId));
        return "redirect:listCollectShop?requestPage=1";
    }
    @RequestMapping(value="/jumpPage",method = RequestMethod.POST)
    public String jumpPage(@RequestParam int jump,@RequestParam int ty, Model model)
    {
        if(ty==1) {
            model.addAttribute("gdhds", jump);
            return "redirect:listCollect?requestPage={gdhds}";
        }else {
            model.addAttribute("gdhdsfs", jump);
            return "redirect:listCollectShop?requestPage={gdhdsfs}";
        }
    }
    private int getUserId(HttpSession session){
        try {
            return ((UserEntity) session.getAttribute(IDefineString.SESSION_USER)).getUserId();
        }catch (Exception e){
            return -1;
        }
    }
}
