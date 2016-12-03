package com.parknshop.service.serviceImpl;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IAdminService;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by weina on 2016/12/2.
 */
@Scope(value = "prototype")
@Service
public class AdminService  implements IAdminService{

    private final IBaseDao<ShopEntity> mDao;
    /**
     *
     * @param page  请求第几页
     * @param  lines  请求多少条
     * @return
     */
    final private IListBean listBean;

    @Autowired
    public AdminService(ShopListBean listBean, IBaseDao<ShopEntity> mDao) {
        this.listBean = listBean;
        this.mDao = mDao;
    }

    @Override
    public IListBean<ShopAndOwnerDbBean> getApplyShop(int page, int lines) {
        listBean.init(page,lines);
        return listBean;
    }

    @Override
    public boolean suggestShop(int shopId) {
       return updateShopState(IOwnerService.SHOP_STATE_USING,shopId);
    }

    @Override
    public boolean rejectShop(int shopId) {
        return updateShopState(IOwnerService.SHOP_STATE_REJECT,shopId);
    }
    //更新商店状态
    private  boolean updateShopState(int type,int shopId){
        try {
            ShopEntity entity = mDao.get(ShopEntity.class, shopId);
            if(null != entity) {
                entity.setState(type);//可以使用
                mDao.save(entity);
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
