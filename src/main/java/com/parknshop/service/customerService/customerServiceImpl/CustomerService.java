package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.CollectionEntity;
import com.parknshop.entity.CollectshopEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.customerService.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by H on 2016/12/5.
 */
@Transactional
@Service("CustomerService")
public class CustomerService  implements ICustomerService {

    @Resource
    private BaseDao<UserEntity>          userEntityBaseDao;

    @Resource
    private  BaseDao<CollectionEntity>   collectionEntityBaseDao;

    @Resource
    private  BaseDao<CollectshopEntity>  collectshopEntityBaseDao;


    @Override
    public List<UserEntity> getAllCustomer() {
        return null;
    }

    @Override
    public void setBlackList(Integer userId) {

    }

    @Override
    public void resetBlackList(Integer userId) {

    }

    @Override
    public UserEntity getCustomerById(Integer id) {
        return null;
    }

    @Override
    public void updateCustomerEntity(UserEntity userEntity) {

    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    @Override
    public void changePassword(String password) {

    }

    @Override
    public void changeUserImage(String url) {

    }

    @Override
    public List<CollectionEntity> queryAllCollect(Integer userId) {
        return null;
    }

    @Override
    public void insertCollect(Integer id, Integer userId) {

    }

    @Override
    public void removeCollect(Integer id, Integer userId) {

    }

    @Override
    public List<CollectshopEntity> queryAllShop(Integer userId) {
        return null;
    }

    @Override
    public void insertShop(Integer id, Integer userId) {

    }

    @Override
    public void removeShop(Integer id, Integer userId) {

    }
}
