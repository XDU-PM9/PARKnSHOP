package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.customerService.ISearchShops;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wei on 16-12-10.
 */
@Service
public class SearchShops implements ISearchShops{
    @Resource
    private BaseDao<ShopEntity> shopEntityBaseDao;
    @Override
    public ShopEntity searchShop(int shopId) {
        try {
            return shopEntityBaseDao.find("from ShopEntity where shopId = ?",new Object[]{shopId}).get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ShopEntity> searchShop(String shopName) {
        try {
            return shopEntityBaseDao.find("from ShopEntity where shopName like ?",new Object[]{"%"+shopName+"%"});
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ShopEntity> searchShop(String shopName, int start, int count) {
        try {
            return shopEntityBaseDao.findNumberRows("from ShopEntity where shopName like ?",new Object[]{"%"+shopName+"%"},start,count);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getCount(String shopName) {
        try {
            return shopEntityBaseDao.count("select count(*) from ShopEntity where shopName like ?",new Object[]{"%"+shopName+"%"});
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
