package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.service.customerService.ISearchProducts;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wei on 16-12-5.
 */
@Service
public class SearchProducts implements ISearchProducts {
    @Resource
    private BaseDao<GoodsEntity> goodsEntityBaseDao;

    private boolean orderByTime = false;
    private boolean orderByTimeDesc = false;
    private boolean orderByPrice = false;
    private boolean orderByPriceDesc = false;
    private boolean orderByView = false;
    private boolean orderByViewDesc = false;
    private boolean orderBySales = false;
    private boolean orderBySalesDesc = false;
    private boolean orderByDiscount = false;
    private boolean orderByDiscountDesc = false;
    private boolean anyState = false;

    @Override
    public ISearchProducts initRuler() {
        orderByTime = false;
        orderByTimeDesc = false;
        orderByPrice = false;
        orderByPriceDesc = false;
        orderByView = false;
        orderByViewDesc = false;
        orderBySales = false;
        orderBySalesDesc = false;
        orderByDiscount = false;
        orderByDiscountDesc = false;
        anyState = false;
        return this;
    }

    @Override
    public ISearchProducts setRuler(Ruler ruler) {
        //设置各种排序规则
        switch (ruler) {
            case TIME:
                orderByTime = !orderByTime;
                break;
            case PRICE:
                orderByPrice = !orderByPrice;
                break;
            case VIEW:
                orderByView = !orderByView;
                break;
            case DISCOUNT:
                orderByDiscount = !orderByDiscount;
                break;
            case SALES:
                orderBySales = !orderBySales;
                break;
            case TIMEDESC:
                orderByTimeDesc = !orderByTimeDesc;
                break;
            case PRICEDESC:
                orderByPriceDesc = !orderByPriceDesc;
                break;
            case VIEWDESC:
                orderByViewDesc = !orderByViewDesc;
                break;
            case DISCOUNTDESC:
                orderByDiscountDesc = !orderByDiscountDesc;
                break;
            case SALESDESC:
                orderBySalesDesc = !orderBySalesDesc;
                break;
            case ANYSTATE:
                anyState = !anyState;
                break;
        }
        return this;
    }

    @Override
    public ISearchProducts setRuler(Map ruler) {
//        if ("true" == ruler.get("noFilter"))
//            anyState = true;
        if ("true" == ruler.get("time"))
            orderByTime = true;
        if ("true" == ruler.get("timeDesc"))
            orderByTimeDesc = true;
        if ("true" == ruler.get("price"))
            orderByPrice = true;
        if ("true" == ruler.get("priceDesc"))
            orderByPriceDesc = true;
        if ("true" == ruler.get("view"))
            orderByView = true;
        if ("true" == ruler.get("viewDesc"))
            orderByViewDesc = true;
        if ("true" == ruler.get("discount"))
            orderByDiscount = true;
        if ("true" == ruler.get("discountDesc"))
            orderByDiscountDesc = true;
        if ("true" == ruler.get("sales"))
            orderBySales = true;
        if ("true" == ruler.get("salesDesc"))
            orderBySalesDesc = true;
        return this;
    }

    @Override
    public List<GoodsEntity> searchAllProductsByName(String name) {
        try {
            return goodsEntityBaseDao.find(getHql("from GoodsEntity where goodsName like ?"), new Object[]{getLikeParamter(name)});
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchAllProductsByType(String type) {
        try {
            return goodsEntityBaseDao.find(getHql("from GoodsEntity where type like ?"), new Object[]{getLikeParamter(type)});
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type) {
        try {
            return goodsEntityBaseDao.find(getHql("from GoodsEntity where goodsName like ? and type like ?"), new Object[]{getLikeParamter(name), getLikeParamter(type)});
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchProductsByName(String name, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where GoodsName like ?", new Object[]{getLikeParamter(name)}, max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsByType(String type, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where type like ?", new Object[]{getLikeParamter(type)}, max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByName(String name, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where GoodsName like ?", new Object[]{getLikeParamter(name)}, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByType(String type, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where type like ?", new Object[]{getLikeParamter(type)}, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where goodsName like ? and type like ?", new Object[]{getLikeParamter(name), getLikeParamter(type)}, max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByNameAndType(String name, String type, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where goodsName like ? and type like ?", new Object[]{getLikeParamter(name), getLikeParamter(type)}, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsByMap(Map map) throws NumberFormatException{
        setRuler(map);
        String name= (String) map.get("name");
        String type= (String) map.get("type");
        int max=Integer.parseInt((String) map.get("start"));
        int count=Integer.parseInt((String) map.get("count"));
        if(null!=name&&"null"!=name&&""!=name) {
            if (null != type && "null" != type && "" != type){
                return searchProductsByNameAndType(name,type,max,count);
            }else{
                return searchProductsByName(type,max,count);
            }
        }else{
            if (null != type && "null" != type && "" != type){
                return searchProductsByNameAndType(name,type,max,count);
            }else{
                return searchProductsByName(type,max,count);
            }
        }
    }

    @Override
    public GoodsEntity searchProductsById(int id) {
        try {
            return goodsEntityBaseDao.find("from GoodsEntity where goodsId = ?", new Object[]{id}).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    private String getHql(String hql) {
        //有效排序计数
        int count = 0;
        StringBuffer order = new StringBuffer("");
        if (!anyState) {
            hql += " and state = 1";
        }
        if (orderByPriceDesc) {
//            if(count>0){
//                order.append(" ,");
//            }
            order.append(" price DESC");
            count++;
        } else if (orderByPrice) {
//            if(count>0){
//                order.append(" ,");
//            }
            order.append(" price");
            count++;
        }
        if (orderBySalesDesc) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" sales DESC");
            count++;
        } else if (orderBySales) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" sales");
            count++;
        }
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
        if (orderByDiscountDesc) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" discount DESC");
            count++;
        } else if (orderByDiscount) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" discount");
            count++;
        }
        if (orderByViewDesc) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" view DESC");
            count++;
        } else if (orderByView) {
            if (count > 0) {
                order.append(" ,");
            }
            order.append(" view");
            count++;
        }
        if (count > 0) {
            return hql + " order by" + order;
        } else {
            return hql;
        }
    }

    private List<GoodsEntity> getGoodsEntitys(String hql, Object[] params, int max, int count) {
        hql = getHql(hql);
        try {
            Session session = new Configuration().configure().buildSessionFactory().getCurrentSession();
            Query query = session.createQuery(hql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }
            List<GoodsEntity> goodsEntities = query.setFirstResult(max).setMaxResults(count).list();
            session.close();
            return goodsEntities;
        } catch (Exception e) {
            return null;
        }
    }

    private String getLikeParamter(String cause) {
        return "%" + cause + "%";
    }

//    private boolean rulerIsTrue(Map map,String s){
//        return "true" == map.get(s);
//    }
}
