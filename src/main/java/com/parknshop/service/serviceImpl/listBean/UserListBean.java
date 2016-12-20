package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserService;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
@Repository
public class UserListBean extends AbstractListBean<UserEntity>{
    final
    IBaseDao<UserEntity> mDao;

    @Autowired
    public UserListBean(IBaseDao<UserEntity> mDao) {
        this.mDao = mDao;
    }

    @Override
    protected List<UserEntity> initList(int page, int lines) {
        String hql = " from UserEntity where 1=1 and state > ?";
        Object[] param = {IUserService.STATE_DELETE};

        return mDao.find(hql,param,page,lines);
    }

    @Override
    protected long count() {
        String hql ="select count(*) from UserEntity where 1=1 and state > ?";
        Object[] param = {IUserService.STATE_DELETE};
        return mDao.count(hql,param);
    }
}
