package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.entity.AddressEntity;
import com.parknshop.service.customerService.IAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H on 2016/12/5.
 */
@Service
public class AddressService implements IAddressService{
    @Override
    public List<AddressEntity> getAllAddressByUserId(Integer userId) {
        return null;
    }

    @Override
    public AddressEntity getByAddressId(Integer addressId) {
        return null;
    }

    @Override
    public void insertAddressEntity(AddressEntity addressEntity) {

    }

    @Override
    public void deleteAddressEntity(Integer addressId) {

    }

    @Override
    public void updateAddressEntity(AddressEntity addressEntity) {

    }
}
