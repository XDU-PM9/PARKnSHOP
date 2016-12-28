package com.parknshop.service.customerService;

import com.parknshop.entity.AddressEntity;
import java.util.List;
/**
 * Created by H on 2016/12/5.
 */

public interface IAddressService {

    public  List<AddressEntity>    getAllAddressByUserId(int userId);

    public  AddressEntity           getByAddressId(int addressId);

    public boolean updateAddressEntity(int addressId, String  province, String country, String  others, String  name, long phone, int zip, int userId);

    public boolean insertAddressEntity(String  province, String country, String  others, String  name, long phone, int zip, int userId);

    public boolean deleteAddressEntity(int addressId);

    public  void                    updateAddressEntity(AddressEntity addressEntity);

}
