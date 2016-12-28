package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.bean.AdvertisementDbBean;
import com.parknshop.bean.HqlBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/19.
 */
@Component
@Scope(value = "prototype")
public class AdvertisementListBean extends AbstractListBean<AdvertisementDbBean> {
    @Override
    protected List<AdvertisementDbBean> initList(int page, int lines) {
        if(null !=getObject()){
            HqlBean type =(HqlBean) getObject();
            String hql = AdvertisementDbBean.hql +//
                        type.getInnerHql()+//
                        " order by advertId desc";
            List<Object> param =type.getInnerParam();
            IBaseDao<AdvertisementDbBean> dao = new BaseDao<>();
            return dao.find(hql,param,page,lines);
        }else {
            return null;
        }
    }

    @Override
    protected long count() {
        if(null !=getObject()) {
            HqlBean type =(HqlBean) getObject();
            String hql = "select count(*) from AdvertEntity where 1=1" + type.getInnerHql();
            List<Object> param =type.getInnerParam();
            IBaseDao<AdvertisementDbBean> dao = new BaseDao<>();
            return dao.count(hql,param);
        }else {
            return 0;
        }
    }
    public static void main(String[] args){
        AdvertisementListBean bean = new AdvertisementListBean();
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and typeId = ? ");
        hqlBean.getInnerParam().add(1);

        bean.init(hqlBean,0,10);
        System.out.println(bean.getNumer());
    }
}
