package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.AddressEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by H on 2016/12/5.
 */
@Service
public class AddressService implements IAddressService{

    @Resource
    private BaseDao<AddressEntity> addressEntityBaseDao;

    @Resource
    private  BaseDao<UserEntity>  userEntityBaseDao;
    @Override
    public List<AddressEntity> getAllAddressByUserId(int userId)
    {
        Object[]  params=new Object[1];
        UserEntity  userEntity=userEntityBaseDao.get(UserEntity.class,userId);
        params[0]=userEntity;
        return  addressEntityBaseDao.find("from  AddressEntity  ae where ae.userByUserId=?",params);
    }

    @Override
    public AddressEntity getByAddressId(int addressId) {
        return addressEntityBaseDao.get(AddressEntity.class,addressId);
    }

    @Override
    public void updateAddressEntity(int addressId, String province, String country, String others, String name, long phone, int zip, int userId) {
        Object[] params=new Object[8];
        params[7]=addressId;
        params[0]=userId;
        params[1]=province;
        params[2]=country;
        params[3]=others;
        params[4]=phone;
        params[5]=name;
        params[6]=zip;
        addressEntityBaseDao.insert("update  address  SET  userId=?,province=?,country=?,others=?,phone=?,name=?,postcode=? where addressId=?",params);
        return;
    }

    @Override
    public void insertAddressEntity(String province, String country, String others, String name, long phone, int zip, int userId) {
        Object[] params=new Object[7];
        params[0]=userId;
        params[1]=province;
        params[2]=country;
        params[3]=others;
        params[4]=phone;
        params[5]=name;
        params[6]=zip;
        addressEntityBaseDao.insert("insert into address(userId,province,country,others,phone,name,postcode) values(?,?,?,?,?,?,?)",params);
        return;
    }


    @Override
    public void deleteAddressEntity(int addressId) {
        addressEntityBaseDao.delete("delete from address where addressId=?",new Integer(addressId));
    }

    @Override
    public void updateAddressEntity(AddressEntity addressEntity) {
        addressEntityBaseDao.saveOrUpdate(addressEntity);
    }
}
