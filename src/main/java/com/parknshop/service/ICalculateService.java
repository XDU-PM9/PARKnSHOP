package com.parknshop.service;

import com.parknshop.bean.CalculateDbBean;

import java.util.List;

/**
 * Created by weina on 2016/12/29.
 */
public interface ICalculateService {
    long ONE_DAY = 24*60*60*1000;

    /**
     * 商家 获取 利润  已经 扣除佣金
     * @param ownerId
     * @return
     */
    public List<CalculateDbBean> getToday(int ownerId);
    public List<CalculateDbBean> getMonth(int ownerId);
    public List<CalculateDbBean> getWeek(int ownerId);
    public List<CalculateDbBean> getYear(int ownerId);


    /**
     * 管理员获取  利润 包括 佣金 和 广告费
     */
    public List<CalculateDbBean> getTodayAdmin();
    public List<CalculateDbBean> getMonthAdmin();
    public List<CalculateDbBean> getWeekAdmin();
    public List<CalculateDbBean> getYearAdmin();

}
