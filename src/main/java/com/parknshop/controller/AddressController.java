package com.parknshop.controller;

import com.parknshop.entity.AddressEntity;
import com.parknshop.service.customerService.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
/**
 * Created by H on 2016/12/5.
 */
@Controller
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "/listAddress",method = RequestMethod.GET)
    public String  listAddress(@RequestParam int userId,Model model)
    {
        List<AddressEntity>   addressEntityList=addressService.getAllAddressByUserId(userId);
        model.addAttribute("addressEntityList", addressEntityList);
        return "customer/User_Address.jsp";
    }

    @RequestMapping(value = "/editAddress",method = RequestMethod.GET)
    public String  editAddress(@RequestParam int addressId,Model model)
    {
        AddressEntity addressEntity=addressService.getByAddressId(addressId);
        model.addAttribute("addressEntity",addressEntity);
        return "/customer/address-edit.jsp";
    }

    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    public String  updateAddress(@RequestParam int addressId,
                                 @RequestParam int userId,
                                // @RequestParam String  province,
                                 //@RequestParam String  country,
                                 @RequestParam String  others,
                                 @RequestParam String   name,
                                 @RequestParam int zip,
                                 @RequestParam long phoneNum,
                                 Model model)
    {
        ///
        String  province,country;
        province=null;
        country=null;
        addressService.updateAddressEntity(addressId,province,country,others,name,phoneNum,zip,userId);
        return  "redirect:listAddress?userId=6";
    }

    @RequestMapping(value = "/saveAddress",method = RequestMethod.POST)
    public String  saveAddress(@RequestParam int userId,
                                 // @RequestParam String  province,
                                 //@RequestParam String  country,
                                 @RequestParam String  others,
                                 @RequestParam String   name,
                                 @RequestParam int zip,
                                 @RequestParam long phoneNum,
                                 Model model)
    {
        ///
        String  province,country;
        province=null;
        country=null;
        addressService.insertAddressEntity(province,country,others,name,phoneNum,zip,userId);
        return  "redirect:listAddress?userId=6";
    }

    @RequestMapping(value = "/deleteAddress",method = RequestMethod.GET)
    public String  deleteAddress(@RequestParam int addressId)
    {
        addressService.deleteAddressEntity(addressId);
        return "redirect:listAddress?userId=6";
    }

}
