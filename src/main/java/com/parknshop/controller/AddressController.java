package com.parknshop.controller;

import com.parknshop.entity.AddressEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.customerService.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Created by H on 2016/12/5.
 */
@Controller
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IUserService mUserService;

    @RequestMapping(value = "/listAddress",method = RequestMethod.GET)
    public String  listAddress(Model model, HttpSession session)
    {
        int userId=getUserId(session);
        if (userId<0) {
            return "redirect:customer/login";
        }else{
            List<AddressEntity> addressEntityList = addressService.getAllAddressByUserId(userId);
            model.addAttribute("addressEntityList", addressEntityList);
            return "customer/address.jsp";
        }
    }

    @RequestMapping(value = "/editAddress",method = RequestMethod.GET)
    public String  editAddress(@RequestParam int addressId,Model model,HttpSession session)
    {
        if (getUserId(session)<0) {
            return "redirect:customer/login";
        } else {
            AddressEntity addressEntity = addressService.getByAddressId(addressId);
            model.addAttribute("addressEntity", addressEntity);
            return "/customer/Address_Edit.jsp";
        }
    }

    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    public String  updateAddress(@RequestParam int addressId,
                                 @RequestParam String  province,
                                 @RequestParam String  country,
                                 @RequestParam String  others,
                                 @RequestParam String   name,
                                 @RequestParam int zip,
                                 @RequestParam long phoneNum,
                                 HttpSession session)
    {
        int userId=getUserId(session);

        if (userId<0) {
            return "redirect:customer/login";
        }else{
            addressService.updateAddressEntity(addressId, province, country, others, name, phoneNum, zip, userId);
            return "redirect:listAddress";
        }
    }

    @RequestMapping(value = "/saveAddress",method = RequestMethod.POST)
    public String  saveAddress(  @RequestParam String  province,
                                 @RequestParam String  country,
                                 @RequestParam String  others,
                                 @RequestParam String   name,
                                 @RequestParam int zip,
                                 @RequestParam long phoneNum,
                                 HttpSession session)
    {
        int userId=getUserId(session);
        if (userId<0) {
            return "redirect:customer/login";
        }
        else{
            addressService.insertAddressEntity(province, country, others, name, phoneNum, zip, userId);
            return "redirect:listAddress";
        }
    }

    @RequestMapping(value = "/deleteAddress",method = RequestMethod.GET)
    public String  deleteAddress(@RequestParam int addressId,HttpSession session) {
        if (getUserId(session)<0) {
             return "redirect:customer/login";
        } else {
            addressService.deleteAddressEntity(addressId);
            return "redirect:listAddress";
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
