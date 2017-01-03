package com.parknshop.controller;

import com.parknshop.bean.CalculateDbBean;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.ICalculateService;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by song on 16-12-31.
 */
@Controller
public class OwnerAnalysisController {

    public static final String TODAY = "OwnerAnalysisController.today";
    public static final String WEEK = "OwnerAnalysisController.week";
    public static final String MONTH = "OwnerAnalysisController.month";
    public static final String YEAR = "OwnerAnalysisController.year";

    final IUserService mUserService;
    final ICalculateService mCalService;


    @Autowired
    public OwnerAnalysisController(IUserService mUserService, ICalculateService mCalService) {
        this.mUserService = mUserService;
        this.mCalService = mCalService;
    }

    @RequestMapping(value = "/owner/analysis/today")
    public String today(HttpSession session, HttpServletRequest request){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        OwnerEntity owner = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        List<CalculateDbBean> todayData =  mCalService.getToday(owner.getOwnerId());
        request.setAttribute(TODAY,todayData);
        return "analysis/today.jsp";

    }

    @RequestMapping(value = "/owner/analysis/week")
    public String week(HttpSession session, HttpServletRequest request){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        OwnerEntity owner = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        List<CalculateDbBean> weekData =  mCalService.getWeek(owner.getOwnerId());
//        Log.debug("size:"+weekData.size());
        request.setAttribute(WEEK,weekData);
        return "analysis/week.jsp";

    }

    @RequestMapping(value = "/owner/analysis/month")
    public String month(HttpSession session, HttpServletRequest request){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        OwnerEntity owner = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        List<CalculateDbBean>   monthData =  mCalService.getMonth(owner.getOwnerId());
//        Log.debug("size:"+monthData.size());
        request.setAttribute(MONTH,monthData);
        return "analysis/month.jsp";

    }

    @RequestMapping(value = "/owner/analysis/year")
    public String year(HttpSession session, HttpServletRequest request){
        if (!checkLogin(session)){
            return "redirect:/owner/login";
        }
        OwnerEntity owner = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
        List<CalculateDbBean> yearData =  mCalService.getYear(owner.getOwnerId());
//        Log.debug("size:"+yearData.size());
        request.setAttribute(YEAR,yearData);
        return "analysis/year.jsp";

    }

    boolean checkLogin(HttpSession session) {
        if (!mUserService.isLogin()) {
            return false;
        }
        //防止不同类型用户的登陆影响
        try {
            OwnerEntity entity = (OwnerEntity) session.getAttribute(IDefineString.SESSION_USER);
            if (null == entity) {
                throw new Exception("未登陆");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
