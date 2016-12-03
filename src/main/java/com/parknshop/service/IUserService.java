package com.parknshop.service;

import com.parknshop.entity.UserEntity;
import com.parknshop.service.enumStatic.LoginTypeEnum;

/**
 * Created by weina on 2016/11/27.
 */
public interface
IUserService {
    int SESSION_TIME = 1*60*60;
    /*
    用户 权限分级
     */
    int ROLE_VISITOR = 1;
    int ROLE_CUSTOMER = 2;
    int ROLE_SHOPOWNER = 3;
    int ROLE_MANAGER = 4;
    /*
    用户状态
     */
    int STATE_DELETE = -1;//删除
    int STATE_BLAKENAME = 0;//黑名单
    int STATE_REGISTER = 1;//注册但未激活
    int STATE_USING =2;//激活使用中

    /**
      统一登录接口
      userName:username
      password:密码
      参数可为空
      成功返回用户
     实体UserEntity,失败返回null
     */
    int LOGIN_SUCCESS = 2000;//登录成功
    int LOGIN_ERRO = 2001;//登陆失败
    int LOGIN_HASDELETE = 2002;//账号冻结
    int LOGIN_HASLOGIN = 2003;//你已经登录账号
    int LOGIN_NOACTIVE = 2004;//未激活
    int LOGIN_ERROPARAM =2005;//错误的参数
    int LOGIN_ELSE = 2006;//其他错误
    int loginAsAdmin(String user,String password);
    int loginAsUser(String user,String password);
    int loginAsOwner(String user,String password);

    /**
     判断用户是否已经登录
     是 返回true
     否则false
     */
    boolean isLogin();
    /**
    登出函数，注销session
     */
    void loginOut();
    /**
    注册 函数
     */
    int SUCCESS = 998;//成功
    int ERRO_EMPTYEMAIL = 999;//邮箱为空
    int ERRO_EMiAL = 1001;//邮箱重复
    int ERRO_PHONE = 1002;//手机重复
    int ERRO_NAME = 1003;//名字重复
    int ERRO_ELSE = 1000;//其他错误
    int ERRO_TYPE = 1004;//类型装换错误
    int ERRO_SENDEMAIL = 1005;//邮箱发送失败
    /**
    已经加锁操作，保证唯一性
    userBuilder 为 用户bean 构造器 ，默认 状态 为未激活状态，
    @需要注意的是！！！！！！！！！！！！
     此处IUserBuilder 需要根据对应的构造器放入相应的 子类
     @所以
     请在构造器里面 进行 自动注入
     final IUserBuilder builder
     @Autowired
     constructor(OwnerBuilder builder){
        this.builder = builder;
     }
     当然也可以
     @Autowired
     OwnerBulder builder;
     */
    int registerByOwner(IUserBuilder ownerBuilder);
    int registerByUser(IUserBuilder userBuilder);
    /**
    检测账号是否存在
    userNameType: username字类型 ，枚举类型 see： servcie.enumStatic.UserNameType
    value: 检测的值

    存在 返回true 不存在返回false
     */
    boolean checkUserExist(LoginTypeEnum loginTypeEnum,String value);

    /**
     * 添加 黑名单
     * @param mClass  写需要拉黑名单的 类  UserEntity or OwnerEntity
     * @Id 拉黑的角色id
     * @return 添加成功返回 true  失败false
     */
    boolean addBlackName(Class mClass,int id);

    /**
     *
     * @param mClass 获取的类型
     * @param id   获取 用户 的id
     * @return  返回类型 自行类型转换
     */
    Object getPeppleById(Class mClass,int id);

    /**
     * 激活函数
     * 传入 激活 口令
     * @param code
     * @return
     */
    boolean activateOwner(String code);
    boolean activateUser(String code);
}
