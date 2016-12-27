package com.parknshop.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.RoleEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.serviceImpl.OwnerService;
import com.parknshop.service.serviceImpl.builder.UserBuilder;
import com.parknshop.utils.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weina on 2016/11/28.
 */
@Controller
public class TestConroller {
    final
    IUserService service;
    final
    IUserBuilder userBuilder;
    final
    RoleEntity roleEntity;
    final
    IBaseDao<RoleEntity> mDao;

    @Autowired
    OwnerService services;
    @Autowired
    public TestConroller(IUserService service, UserBuilder userBuilder, RoleEntity roleEntity, IBaseDao<RoleEntity> mDao) {
        this.service = service;
        this.userBuilder = userBuilder;
        this.roleEntity = roleEntity;
        this.mDao = mDao;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(HttpSession session,Model model) {
//        testRegister();
        String context = session.getServletContext().getRealPath("/");
        String url = img(context);
        model.addAttribute("imgUrl",url);
        return "test.jsp";
    }



    public String img(String context){
//        try {
//            String content = "这是测试xing二维码生成";
////        String path = "D:/tt";
//        File root = new File(context);
//        String fileName = "resources/images/photos/14.jpg";
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        Map hints = new HashMap();
//        //内容所使用编码
//        hints.put(EncodeHintType.CHARACTER_SET, "gb2312");
//        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
//        //生成二维码
//        File file = new File(root,fileName);
//        if (!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        if (!file.exists()){
//            file.createNewFile();
//        }
//        MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
//        return "/resources/images/photos/14.jpg";
//    } catch (Exception e) {
//        e.printStackTrace();
//        return null;
//    }
        return null;
    }



    public void testLogin(){
        System.out.println(service.loginAsUser("admin","admin"));
    }
    public void testRegister(){
        userBuilder.clear();
        System.out.println(service.registerByUser(userBuilder.setPassWord("ww").setPhone("1234").setUserName("ww").setEmail("870603569@qq.com")));
    }
    public void testRole(){
        roleEntity.setDescribes("商家");

        try {
           mDao.save(roleEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("success");
    }
    public void testGet(){
        //测试dao get
//        AdminEntity c =(AdminEntity) service.getPeppleById(AdminEntity.class,2);
//        System.out.print(c.getPassword());
    }
    public void testShop(){
        OwnerEntity entity = new OwnerEntity();
        entity.setOwnerId(1);
        services.isHasShop(entity);
    }
}
