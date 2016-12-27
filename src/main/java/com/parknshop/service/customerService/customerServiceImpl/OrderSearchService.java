package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.service.IOrderService;
import com.parknshop.service.customerService.IOrderSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
/**
 * Created by Lenovo on 2016/12/26.
 */

@Scope(value = "prototype")
@Service
public class OrderSearchService implements IOrderSearchService{

    private final static SimpleDateFormat timeformat=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BaseDao<OrdersEntity> ordersEntityBaseDao;

    private List<OrdersEntity>  find(Object[] objects)
    {
        String sql="SELECT ordersId from orders where createTime BETWEEN  ? AND  ? and userId=? order by createTime";
        List<OrdersEntity> ordersEntityList=new ArrayList<OrdersEntity>();
        List<Object> ids=ordersEntityBaseDao.executeSQL(sql,objects);
        for(int i=0;i<ids.size();i++) {
            int j=(int)ids.get(i);
            ordersEntityList.add(i, ordersEntityBaseDao.get(OrdersEntity.class,j));
        }
        return ordersEntityList;
    }
    @Override
    public List<OrdersEntity> datelySearch(String s,int userId) {
        String[] d=s.split("-");
        Date start=new Date(new Integer(d[0]).intValue()-1900,new Integer(d[1]).intValue()-1,new Integer(d[2]).intValue());
        Date date=new Date(new Integer(d[0]).intValue()-1900,new Integer(d[1]).intValue()-1,new Integer(d[2]).intValue()+1);
        return  find(new Object[]{timeformat.format(start),timeformat.format(date),userId});
    }

    @Override
    public List<OrdersEntity> weeklySearch(String s,int userId) {

        String[] d=s.split("-");
        Date date=new Date(new Integer(d[0]).intValue()-1900,new Integer(d[1]).intValue()-1,new Integer(d[2]).intValue());
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int week_index=cal.get(Calendar.DAY_OF_WEEK)-2;
        if(week_index<0)
            week_index=6;
        cal.add(Calendar.DATE,-week_index);
        Date start=cal.getTime();
        cal.add(Calendar.DATE,7);
        Date end=cal.getTime();
        return  find(new Object[]{timeformat.format(start),timeformat.format(end),userId});
    }

    @Override
    public List<OrdersEntity> monthlySearch(String s,int userId) {
        String[] d=s.split("-");
        int year=new Integer(d[0]).intValue()-1900;
        int month=new Integer(d[1]).intValue()-1;

        Date start=new Date(year,month,1);
        Date date=new Date(year,month+1,1);
        return  find(new Object[]{timeformat.format(start),timeformat.format(date),userId});
    }

    @Override
    public List<OrdersEntity> yearlySearch(String s,int userId) {
        String[] d=s.split("-");
        int year=new Integer(d[0]).intValue()-1900;
        Date start=new Date(year,1,1);
        Date date=new Date(year+1,1,1);
        return  find(new Object[]{timeformat.format(start),timeformat.format(date),userId});
    }
}
