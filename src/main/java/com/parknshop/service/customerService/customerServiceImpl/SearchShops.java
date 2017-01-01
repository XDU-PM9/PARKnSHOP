package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.customerService.ISearchShops;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-10.
 */
@Service
public class SearchShops implements ISearchShops {
    @Resource
    private BaseDao<ShopEntity> shopEntityBaseDao;

    private boolean orderByTime = false;
    private boolean orderByTimeDesc = false;
    private boolean orderByViews = false;
    private boolean orderByViewsDesc = false;
    private boolean anyState = false;
    private String shopName = "";
    private int start = 0;
    private int count = 0;

    private Object[] param = null;

    @Override
    public ShopEntity searchShop(int shopId) {
        try {
            return shopEntityBaseDao.find("from ShopEntity where shopId = ?", new Object[]{shopId}).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ShopEntity> searchShop(String shopName) {
        try {
            return shopEntityBaseDao.find("from ShopEntity where shopName like ?", new Object[]{"%" + shopName + "%"});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ShopEntity> searchShop(String shopName, int start, int count) {
        try {
            return shopEntityBaseDao.findNumberRows("from ShopEntity where shopName like ?", new Object[]{"%" + shopName + "%"}, start, count);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getCount(String shopName) {
        try {
            return shopEntityBaseDao.count("select count(*) from ShopEntity where shopName like ?", new Object[]{"%" + shopName + "%"});
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //获取数量
    @Override
    public long getCount() {
        param = null;
        String hql = getHql();
        if (null == hql) {
            return -1;
        }
        try {
            return shopEntityBaseDao.count("select count(*) " + hql, param);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ISearchShops setRuler(Map ruler) {
        if ("true".equals(ruler.get("time")))
            orderByTime = true;
        if ("true".equals(ruler.get("timeDesc")))
            orderByTimeDesc = true;
        if ("true".equals(ruler.get("views")))
            orderByViews = true;
        if ("true".equals(ruler.get("viewsDesc")))
            orderByViewsDesc = true;
        return this;
    }

    @Override
    public ISearchShops setByMap(Map map) throws NumberFormatException {
        initRuler();
        setRuler(map);
        String shopName = (String) map.get("shopName");
        String startString = (String) map.get("start");
        String countString = (String) map.get("count");
        if (stringNotNull(startString) && stringNotNull(countString)) {
            setScope(Integer.parseInt(startString), Integer.parseInt(countString));
        }
        setShopName(shopName);
        return this;
    }

    //根据set的情况生成hql语句
    private String getHql() {
        int count = 0;
        String hql="";
        if (!anyState) {
            hql += " state = 3";
            count++;
        }
        if (stringNotNull(shopName)) {
            if(count>0){
                hql+=" and";
            }
            hql+=" shopName like ?";
            param=new Object[]{"%" + shopName + "%"};
            count++;
        }
        if (count< 1) {
            return mergerRulertoHql("from ShopEntity");
        }
        return mergerRulertoHql("from ShopEntity where" + hql);
    }

    //把设置的各种规则并入hql语句中
    private String mergerRulertoHql(String hql) {
        //有效排序计数
        int count = 0;
        StringBuilder order = new StringBuilder("");
        if (orderByTimeDesc) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" createTime DESC");
            count++;
        } else if (orderByTime) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" createTime");
            count++;
        }
        if (orderByViewsDesc) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" views DESC");
            count++;
        } else if (orderByViews) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" views");
            count++;
        }
        if (count > 0) {
            return hql + " order by" + order;
        } else {
            return hql;
        }
    }

    public SearchShops initRuler() {
        orderByTime = false;
        orderByTimeDesc = false;
        orderByViews = false;
        orderByViewsDesc = false;
        anyState = false;
        shopName = "";
        return this;
    }

    public SearchShops setOrderByTime() {
        this.orderByTime = !this.orderByTime;
        return this;
    }

    public SearchShops setOrderByTimeDesc() {
        this.orderByTimeDesc = !this.orderByTimeDesc;
        return this;
    }

    public SearchShops setOrderByViews() {
        this.orderByViews = !this.orderByViews;
        return this;
    }

    public SearchShops setOrderByViewsDesc() {
        this.orderByViewsDesc = !this.orderByViewsDesc;
        return this;
    }

    @Override
    public ISearchShops setShopNmae(String shopNmae) {
        this.shopName=shopNmae;
        return this;
    }

    public SearchShops setAnyState() {
        this.anyState = !this.anyState;
        return this;
    }

    @Override
    public ISearchShops setScope(int start, int count) {
        this.start=start;
        this.count=count;
        return this;
    }

    @Override
    public List<ShopEntity> searchShop() {
        param=null;
        String hql=getHql();
        if(null==hql){
            return null;
        }
        try {
            if (start < 0 || count < 1) {
                return shopEntityBaseDao.find(hql, param);
            } else {
                return shopEntityBaseDao.findNumberRows(hql, param, start, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SearchShops setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    //判断String是否为空或null
    private boolean stringNotNull(String string) {
        return null != string && !"null".equals(string) && !"".equals(string);
    }
}