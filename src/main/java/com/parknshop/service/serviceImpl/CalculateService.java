package com.parknshop.service.serviceImpl;

import com.parknshop.bean.CalculateDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.ICalculateService;
import com.parknshop.service.IOrderService;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by weina on 2016/12/29.
 */
@Scope(value = "prototype")
@Service
public class CalculateService implements ICalculateService {
    private final IBaseDao<Object> ordersEntityIBaseDao;



    @Autowired
    public CalculateService(IBaseDao<Object> ordersEntityIBaseDao) {
        this.ordersEntityIBaseDao = ordersEntityIBaseDao;
    }



    public static final String  headHql = "select DATE_FORMAT(createTime,'%Y-%m-%d') as date,sum(price-commission) as price from orders ";
    public static final String headHqlAdmin = "select t.day ,t.earn+t2.earn as earn  from( select DATE_FORMAT(createTime,'%Y-%m-%d') as day,sum(commission) as earn  from orders  where YEAR(createTime)=YEAR(NOW()) GROUP BY day) as t" +
            ",(select DATE_FORMAT(startTime,'%Y-%m-%d') as day,sum(price) as earn from advert  where YEAR(startTime)=YEAR(NOW()) GROUP BY day) as t2";
    public static final String year = " where YEAR(createTime)=YEAR(NOW()) ";
    public static final String yearAdmin = " where YEAR(startDate)=YEAR(NOW()) ";
    public static final String month = " where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(createTime) ";
    public static final String monthAdmin = " where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(startDate) ";
    public static final String week = " where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(createTime)  ";
    public static final String weekAdmin = " where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(startDate)  ";
    public static final String toDay =" where to_days(createTime) = to_days(now()) ";
    public static final String toDayAdmin =" where to_days(startDate) = to_days(now()) ";
    public static final String finalHql =" GROUP BY date ";

    private List<Object> calculate(String dayHql,int ownerId){
        String hql = headHql +dayHql + " and ownerId = ? and state > ? " + finalHql;
        return ordersEntityIBaseDao.executeSQL(hql,new Object[]{ownerId ,IOrderService.STATE_SEND});
    }
    private List<Object> calculateAdmin(String orderDays,String advertDays){
        String hql = createAdminHql(orderDays,advertDays);//已经发送 的 商品 和申请过的广告
        return ordersEntityIBaseDao.executeSQL(hql,new Object[]{IOrderService.STATE_SEND, IAdvertisement.AD_STATUS_APPLYING});
    }

    public static void main(String[] args){
//        String hql = "  " +
//                " select DATE_FORMAT(createTime,'%Y-%m-%d') as date,sum(price) as price from orders where YEAR(createTime)=YEAR(NOW()) GROUP BY date";
//        IBaseDao<Object> baseDao = new BaseDao<>();
//        List<Object> list = baseDao.executeSQL(hql,new Object[]{});
//        Log.debug("success" +((Object[])list.get(0))[0]);
        CalculateService calculateService = new CalculateService(new BaseDao<>());
        System.out.println(calculateService.getMonthAdmin().size());


    }

    private String createAdminHql(String orderDays,String advertDays){
        String headHqlAdmin = "select t.day ,t.earn+t2.earn as earn  from( select DATE_FORMAT(createTime,'%Y-%m-%d') as day,sum(commission) as earn  from orders  " + orderDays + " and state > ?  GROUP BY day) as t" +
                ",(select DATE_FORMAT(startTime,'%Y-%m-%d') as day,sum(price) as earn from advert  " + advertDays + " and state > ?  GROUP BY day) as t2";
        return headHqlAdmin;
    }

    private Date toDate(String ss){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(ss);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private List<CalculateDbBean> toCalculateDbBean(List<Object> list){
        List<CalculateDbBean> calculateDbBeanList = new ArrayList<>();
        try {
            for (Object o : list) {
                CalculateDbBean calculateDbBean = new CalculateDbBean();
                calculateDbBean.setDate(toDate((String) ((Object[]) o)[0]));
                calculateDbBean.setPrice((Double) ((Object[]) o)[1]);
                calculateDbBeanList.add(calculateDbBean);
            }
            return calculateDbBeanList;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<CalculateDbBean> getToday(int ownerId) {
        List<Object> list = calculate(toDay,ownerId);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getMonth(int ownerId) {
        List<Object> list = calculate(month,ownerId);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getWeek(int ownerId) {
        List<Object> list = calculate(week,ownerId);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getYear(int ownerId) {
        List<Object> list = calculate(year,ownerId);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getTodayAdmin() {
        List<Object> list = calculateAdmin(toDay,toDayAdmin);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getMonthAdmin() {
        List<Object> list = calculateAdmin(month,monthAdmin);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getWeekAdmin() {
        List<Object> list = calculateAdmin(month,weekAdmin);
        return toCalculateDbBean(list);
    }

    @Override
    public List<CalculateDbBean> getYearAdmin() {
        List<Object> list = calculateAdmin(year,yearAdmin);
        return toCalculateDbBean(list);
    }
}
