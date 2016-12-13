package com.parknshop.service.customerService;

import com.parknshop.entity.AddressEntity;
import java.util.List;
/**
 * Created by H on 2016/12/5.
 */

public interface IAddressService {

    public  List<AddressEntity>    getAllAddressByUserId(int userId);

    public  AddressEntity           getByAddressId(int addressId);

    public   void                 updateAddressEntity(int addressId,String  province,String country,String  others,String  name,long phone,int zip,int userId);

    public   void                 insertAddressEntity(String  province,String country,String  others,String  name,long phone,int zip,int userId);

    public   void                 deleteAddressEntity(int addressId);

    public  void                    updateAddressEntity(AddressEntity addressEntity);

}
