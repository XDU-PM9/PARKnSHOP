package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IUserService;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
@Repository
public class OwnerListBean extends AbstractListBean<OwnerEntity> {

    final
    IBaseDao<OwnerEntity> mDao;

    @Autowired
    public OwnerListBean(IBaseDao<OwnerEntity> mDao) {
        this.mDao = mDao;
    }

    @Override
    protected List<OwnerEntity> initList(int page, int lines) {
        String hql = " from OwnerEntity where 1=1 and state > ?";
        Object[] param = {IUserService.STATE_DELETE};

        return mDao.find(hql,param,page,lines);
    }

    @Override
    protected long count() {
        String hql ="select count(*) from OwnerEntity where 1=1 and state > ?";
        Object[] param = {IUserService.STATE_DELETE};
        return mDao.count(hql,param);
    }
}
