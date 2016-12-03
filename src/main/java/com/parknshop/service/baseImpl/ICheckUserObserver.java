package com.parknshop.service.baseImpl;

import com.parknshop.entity.UserEntity;

import javax.servlet.http.HttpSession;

/**
 * Created by weina on 2016/11/28.
 */
/*
  user 登录状态监听器
 */
public interface ICheckUserObserver {

    /*
    用于返回 session 中 user 登录状态
    返回 userStatus roldId
    返回session ，不存在null
    session: 若传入为空会内部生成
     */
    Object userSatus(HttpSession session);
}
