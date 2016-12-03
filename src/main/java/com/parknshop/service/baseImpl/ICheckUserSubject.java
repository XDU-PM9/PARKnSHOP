package com.parknshop.service.baseImpl;

/**
 * Created by weina on 2016/11/28.
 */
public interface ICheckUserSubject {
    /*
    观察容器
     */
     /*增加观察者*/
    void add(ICheckUserObserver observer);

    /*删除观察者*/
    void del(ICheckUserObserver observer);



    /*执行 某一观察者
    key 根据 类名字获取 观察者
    * */
    Object  notifyObserverItem(String key);
    /*执行*/
    Object checkLoginSatus();
}
