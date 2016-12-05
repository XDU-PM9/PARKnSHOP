package com.parknshop.service.customerService;

import com.parknshop.entity.AddressEntity;
import java.util.List;
/**
 * Created by H on 2016/12/5.
 */
public interface IAddressService {

    public  List<AddressEntity>    getAllAddressByUserId(Integer userId);

    public  AddressEntity           getByAddressId(Integer addressId);

    public   void                 insertAddressEntity(AddressEntity addressEntity);

    public   void                 deleteAddressEntity(Integer addressId);

    public  void                    updateAddressEntity(AddressEntity addressEntity);

}
