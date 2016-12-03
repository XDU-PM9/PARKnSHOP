package com.parknshop.service.serviceAbstract;

import com.parknshop.service.baseImpl.ICheckUserObserver;
import com.parknshop.service.baseImpl.ICheckUserSubject;
import com.parknshop.service.serviceImpl.CheckUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weina on 2016/11/28.
 */
//单列
public abstract class AbstractCheckUser implements ICheckUserSubject {
    private Map<String,ICheckUserObserver> mOberver = new HashMap<>();
    @Override
    public void add(ICheckUserObserver observer) {
        //如果没有  就创建一个监听器
        if(!mOberver.containsKey(CheckUser.class.getName())) {
            mOberver.put(observer.getClass().getName(), observer);
        }
    }

    @Override
    public void del(ICheckUserObserver observer) {
        mOberver.remove(observer.getClass().getName());
    }

    @Override
    public Object notifyObserverItem(String key) {
        ICheckUserObserver observer = mOberver.get(key);
        return observer.userSatus(null);
    }
}
