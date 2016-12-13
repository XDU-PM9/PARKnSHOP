package com.parknshop.service.customerService.customerServiceImpl;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.service.customerService.ISearchProducts;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private String goodsName;
    private int goodsId;
    private String type;
    private int shopId;
    private int start;
    private int count;

    private Object[] param = null;

    //重置规则
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
        goodsName = null;
        goodsId = 0;
        type = null;
        shopId = 0;
        start = 0;
        count = 0;
        return this;
    }

    //设置规则
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

    //根据Map来设置规则
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
            return goodsEntityBaseDao.find(mergerRulertoHql("from GoodsEntity where goodsName like ?"), getLikeParamter(name));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchAllProductsByType(String type) {
        try {
            return goodsEntityBaseDao.find(mergerRulertoHql("from GoodsEntity where type like ?"), getLikeParamter(type));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type) {
        try {
            return goodsEntityBaseDao.find(mergerRulertoHql("from GoodsEntity where goodsName like ? and type like ?"), getLikeParamter(name, type));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<GoodsEntity> searchProductsByName(String name, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where GoodsName like ?", getLikeParamter(name), max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsByType(String type, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where type like ?", getLikeParamter(type), max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByName(String name, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where GoodsName like ?", getLikeParamter(name), (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByType(String type, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where type like ?", new Object[]{getLikeParamter(type)}, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsByNameAndType(String name, String type, int max, int count) {
        return getGoodsEntitys("from GoodsEntity where goodsName like ? and type like ?", getLikeParamter(name, type), max, count);
    }

    @Override
    public List<GoodsEntity> searchProductsOnePageByNameAndType(String name, String type, int pageNum, int pageSize) {
        return getGoodsEntitys("from GoodsEntity where goodsName like ? and type like ?", getLikeParamter(name, type), (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public List<GoodsEntity> searchProductsByMap(Map map) throws NumberFormatException {
        setByMap(map);
        return search();
    }

    @Override
    public GoodsEntity searchProductsById(int id) {
        try {
            return goodsEntityBaseDao.find("from GoodsEntity where goodsId = ?", new Object[]{id}).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ISearchProducts setGoodsName(String productName) {
        if (null != productName && !"".equals(productName) && !"null".equals(productName))
            this.goodsName = productName;
        return this;
    }

    @Override
    public ISearchProducts setGoodsId(int goodsId) {
        if (goodsId > 0)
            this.goodsId = goodsId;
        return this;
    }

    @Override
    public ISearchProducts setGoodsType(String type) {
        if (null != type && !"".equals(type) && !"null".equals(type))
            this.type = type;
        return this;
    }

    @Override
    public ISearchProducts setShopId(int shopId) {
        if (shopId > 0)
            this.shopId = shopId;
        return this;
    }

    //设置查询的范围（从哪开始查多少条）
    @Override
    public ISearchProducts setScope(int start, int count) {
        if (start > 0 && count < 1 && start < count)
            this.start = start;
        this.count = count;
        return this;
    }

    @Override
    public ISearchProducts setByMap(Map map) throws NumberFormatException{
        initRuler();
        setRuler(map);
        String name = (String) map.get("name");
        String type = (String) map.get("type");
        String startString=(String)map.get("start");
        String countString= (String) map.get("count");
        String shopIdString= (String) map.get("shopID");
        if(stringNotNull(shopIdString)){
            setShopId(Integer.parseInt(shopIdString));
        }
        if(stringNotNull(startString)&&stringNotNull(countString)){
            setScope(Integer.parseInt(startString),Integer.parseInt(countString));
        }
        setGoodsName(name);
        setGoodsType(type);
        return this;
    }

    //获取数量
    @Override
    public long getCount() {
        param = null;
        String hql=getHql();
        if(null==hql){
            return -1;
        }
        try {
            return goodsEntityBaseDao.count("select count(*) " + hql, param);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //搜索入口
    @Override
    public List<GoodsEntity> search() {
        param = null;
        String hql = getHql();
        if (null == hql) {
            return null;
        }
        try {
            if (start < 0 || count < 1) {
                return goodsEntityBaseDao.find(hql, param);
            } else {
                return getGoodsEntitys(hql, param, start, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //把设置的各种规则并入hql语句中
    private String mergerRulertoHql(String hql) {
        //有效排序计数
        int count = 0;
        StringBuilder order = new StringBuilder("");
        if (!anyState) {
            hql += " and state = 1";
        }
        if (orderByPriceDesc) {
            order.append(" price DESC");
            count++;
        } else if (orderByPrice) {
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

    //搜索从max开始count个GoodsEntity
    private List<GoodsEntity> getGoodsEntitys(String hql, Object[] params, int max, int count) {
        hql = mergerRulertoHql(hql);
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

    //获取“%param%"形式的参数数组
    private Object[] getLikeParamter(String param) {
        return new Object[]{"%" + param + "%"};
    }

    private Object[] getLikeParamter(String param1, String param2) {
        return new Object[]{"%" + param1 + "%", "%" + param2 + "%"};
    }

    //根据set的情况生成hql语句
    private String getHql() {
        int status = 0;
        StringBuilder hql = new StringBuilder("");
        List list = new ArrayList<Object>();
        if (null != goodsName && !"".equals(goodsName) && !"null".equals(goodsName)) {
            hql.append(" goodsName like ?");
            list.add("%" + goodsName + "%");
            status++;
        }
        if (null != type && "" != type && "null" != type) {
            if (status > 0) {
                hql.append(" and");
            }
            hql.append(" type like ?");
            list.add("%" + type + "%");
            status++;
        }
        if (shopId > 0) {
            if (status > 0) {
                hql.append(" and");
            }
            hql.append(" shopId = ?");
            list.add(shopId);
            status++;
        }
        if (goodsId > 0) {
            if (status > 0) {
                hql.append(" and");
            }
            hql.append(" goodsId = ?");
            list.add(goodsId);
            status++;
        }
        if (status < 1) {
            return null;
        }
        param = list.toArray();
        return mergerRulertoHql("from GoodsEntity where" + hql);
    }

    //判断String是否为空或null
    private boolean stringNotNull(String string){
        return null!=string&&!"null".equals(string)&&!"".equals(string);
    }
}
