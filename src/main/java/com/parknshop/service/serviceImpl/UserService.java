package com.parknshop.service.serviceImpl;

import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.ICheckUserSubject;
import com.parknshop.service.baseImpl.IDefineString;
import com.parknshop.service.baseImpl.IHtmlContext;
import com.parknshop.service.enumStatic.LoginTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by weina on 2016/11/27.
 */
//多例
@Scope(value = "prototype")
@Service
public class UserService implements IUserService{
    final private  IBaseDao<Object> mDao;
    final private ICheckUserSubject subject;

    @Autowired
    public UserService(CheckUserListen subject, IBaseDao<Object> mDao) {
        this.subject = subject;
        this.mDao = mDao;
    }

    @Override
    public int loginAsAdmin(String user, String password) {
    return login(LoginTypeEnum.ADMIN,user,password);
}

    @Override
    public int loginAsUser(String user, String password) {
        return login(LoginTypeEnum.USER,user,password);
    }

    @Override
    public int loginAsOwner(String user, String password) {
        return login(LoginTypeEnum.OWNER,user,password);
    }

    synchronized private int login(LoginTypeEnum loginTypeEnum, String userName, String password) {
        //

        if(isLogin()){
//            //如果用户已经登录，禁止访问登录
//            return  LOGIN_HASLOGIN;
            //蜜汁修改 如果用户已经登录，帮他退出
            loginOut();

        }
        //检测输入是否为空
        if((null == userName || userName.equals("")) || (null == password || password.equals(""))){
            return  LOGIN_ERROPARAM;
        }

        StringBuffer hql = new StringBuffer();

        List<Object> param  = new ArrayList<>();
        //hql.append("from AmdminEntity where 1=1  and ( userName=?  or email=? or phone=? )and passWord=? ");
        if(LoginTypeEnum.ADMIN.equals(loginTypeEnum)){
            hql.append("from AdminEntity where 1=1  and username = ? and password=? ");
            //如果是管理员,添加判断是否为管理员账号
            hql.append(" and roleId = ?");
            param.add(userName);
            param.add(password);
            param.add(IUserService.ROLE_MANAGER);
        }else{
            //否则将管理员账号排除登录账号之外
            hql.append("from "+ loginTypeEnum.toString()+" where 1=1  and ( username=?  or email=? or phone=? )and password=? ");
            param.add(userName);
            param.add(userName);
            param.add(userName);
            param.add(password);

        }
        try {
            Object userEntity = mDao.get(hql.toString(), param);
            if(null != userEntity){
                //获取状态
                int state;
                if(LoginTypeEnum.USER.equals(loginTypeEnum)){
                    state = ((UserEntity) userEntity).getState();
                }else if(LoginTypeEnum.OWNER.equals(loginTypeEnum)){
                    state = ((OwnerEntity) userEntity).getState();
                }else {
                    //管理员
                    addLoginSession(userEntity);
                    return LOGIN_SUCCESS;
                }
                //检查状态
                if(state == STATE_DELETE){
                    return LOGIN_HASDELETE;
                }else if(state == STATE_REGISTER){
                    return LOGIN_NOACTIVE;
                }
                //登录成功
                addLoginSession(userEntity);
                return LOGIN_SUCCESS;

            }else {
                return LOGIN_ERRO;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return LOGIN_ELSE;
        }
    }

    @Autowired
    CheckUser checkUser;
    @Override
    public boolean isLogin() {
        //添加监听
       subject.add(checkUser);
       //执行监听（目前只是监听是否登录）
       Object object = subject.checkLoginSatus();
       if(null == object){
           return false;
       }else {
           return true;
       }
    }

    @Override
    public void loginOut() {
        HttpSession session = CheckUser.getRequest().getSession(true);
        session.removeAttribute(IDefineString.SESSION_USER);
    }

    @Override
    synchronized  public int registerByUser(IUserBuilder userBuilder) {
        UserEntity userEntity;
        //先检查所有属性
        try {
            userEntity = (UserEntity) userBuilder.builder();
        }catch (Exception e){
            return ERRO_TYPE;
        }
        //检查名字是否相同
        if(checkUserExist(LoginTypeEnum.USER,userEntity.getUsername())){
            return ERRO_NAME;
        }
        //检查邮箱
        if(checkUserExist(LoginTypeEnum.USER,userEntity.getEmail())){
            return ERRO_EMiAL;
        }
        //检查 手机
        if(checkUserExist(LoginTypeEnum.USER,userEntity.getPhone())){
            return ERRO_PHONE;
        }
        return register(userBuilder);
    }

    @Override
    synchronized  public int registerByOwner(IUserBuilder ownerBuilder) {
        OwnerEntity ownerEntity;
        //先检查所有属性
        try {
            ownerEntity = (OwnerEntity) ownerBuilder.builder();
        }catch (Exception e){
            return ERRO_TYPE;
        }
        //检查名字是否相同
        if(checkUserExist(LoginTypeEnum.OWNER,ownerEntity.getUsername())){
            return ERRO_NAME;
        }
        //检查邮箱
        if(checkUserExist(LoginTypeEnum.OWNER,ownerEntity.getEmail())){
            return ERRO_EMiAL;
        }
        //检查 手机
        if(checkUserExist(LoginTypeEnum.OWNER,ownerEntity.getPhone())){
            return ERRO_PHONE;
        }
        return register(ownerBuilder);
    }


    private int register(final IUserBuilder  userBuilder) {

        try{
            //发送邮件
            String confirm = userBuilder.getName() + String.valueOf(System.currentTimeMillis());
            userBuilder.setConfirm(confirm);
            Object entity = userBuilder.builder();
            int type;
            mDao.save(entity);
            if(entity.getClass().equals(UserEntity.class)){
                type = IUserService.ROLE_CUSTOMER;
            }else if(entity.getClass().equals(OwnerEntity.class)){
                type = IUserService.ROLE_SHOPOWNER;
            }else{
                return ERRO_ELSE;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendEmail(userBuilder.getEmail(),userBuilder.getName(),confirm,type);
                }
            }).start();

            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ERRO_ELSE;
        }
    }
/*
存在 返回true 不存在返回false
 */
    @Override
    public boolean checkUserExist(LoginTypeEnum loginTypeEnum,String value) {
        if( null == value || value.equals("") || loginTypeEnum.equals(LoginTypeEnum.ADMIN)){
            return  false;
        }
        String hql = "from "+loginTypeEnum.toString() + " where 1=1 and  (username=?  or email=? or phone=?) ";
        Object[] param ={value,value,value};
        Object userEntity = mDao.get(hql,param);
        if(null == userEntity){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean addBlackName(Class mClass,int id) {

        try {
            if (mClass.equals(UserEntity.class)) {
                UserEntity userEntity = (UserEntity) mDao.get(mClass, id);
                userEntity.setState(IUserService.STATE_BLAKENAME);
                mDao.update(userEntity);
                return true;
            }else if(mClass.equals(OwnerEntity.class)){
                OwnerEntity ownerEntity = (OwnerEntity)mDao.get(mClass,id);
                ownerEntity.setState(IUserService.STATE_BLAKENAME);
                mDao.update(ownerEntity);
                return true;
            }else{
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public Object getPeppleById(Class mClass, int id) {
        return  mDao.get(mClass,id);
    }

    @Override
    public boolean activateOwner(String code) {
        return activate(LoginTypeEnum.OWNER,code);
    }

    @Override
    public boolean activateUser(String code) {
        System.out.println(activate(LoginTypeEnum.USER,code));
        return false;
    }

    @Override
    public boolean resetPassword(String email) {
        return sendEamil(email,"do you want to reset your password?");
    }

    private boolean activate(LoginTypeEnum loginTypeEnum,String code){
        if(null == code|| code.equals("")){
            //参数不能为空
            return false;
        }
        String hql = "from " + loginTypeEnum.toString()+ "  where confirm = ?";
        Object[] param = {code};
        try{
            Object entity  = mDao.get(hql,param);
            //判断是否为空
            if(null == entity ){
                System.out.print("false");
                return false;
            }
            if(LoginTypeEnum.USER.equals(loginTypeEnum)){//判断是否是顾客
                if(((UserEntity)entity).getState() == IUserService.STATE_REGISTER){//如果是没有激活
                    ((UserEntity)entity).setState(STATE_USING);
                    ((UserEntity)entity).setConfirm("");
                }else{
                    System.out.print("@！");
                    return  false;
                }
            }else if(LoginTypeEnum.OWNER.equals(loginTypeEnum)){//判断是否是 商家
                if(((OwnerEntity)entity).getState() == IUserService.STATE_REGISTER){//如果没有激活
                    ((OwnerEntity)entity).setState(STATE_USING);
                    ((OwnerEntity)entity).setConfirm("");
                }else{
                    return  false;
                }
            }else{
                return  false;
            }
            //激活
            mDao.update(entity);
            System.out.print("what?");
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    //添加session
    private void addLoginSession(Object userEntity){
        HttpSession session = CheckUser.getRequest().getSession(true);
        session.setAttribute(IDefineString.SESSION_USER,userEntity);
        session.setMaxInactiveInterval(IUserService.SESSION_TIME);
    }
    private boolean sendEamil(final  String email,final String text){
        if(null == email || email.equals("")){
            return  false;
        }
        //连接地址
        String link =  text;
        // 收件人的电子邮件 ID
        String to = email;

        // 发件人的电子邮件 ID
        String from = "hewinana@163.com";
        String password = "myasd87060";
        // 假设您是从本地主机发送电子邮件
        String host = "smtp.163.com";

        // 获取系统的属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // properties.setProperty("mail.user", from);
        // properties.setProperty("mail.password", password);
        properties.put("mail.smtp.auth", "true");

        Authentication authentication = new Authentication(from, password);
        // 获取默认的 Session 对象
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, authentication);

        // 设置响应内容类型
        // response.setContentType("text/html;charset=UTF-8");
        // PrintWriter out = response.getWriter();

        try {
            // 创建一个默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // 设置 From: header field of the header.
            message.setFrom(new InternetAddress("PARKnSHOP<" + from + ">"));
            // 设置 To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 设置 Subject: header field
            message.setSubject("This is Activate email");
            // 现在设置实际消息
            message.setContent(
                    text,
                    "text/html;charset=utf-8");
            // 发送消息
            javax.mail.Transport.send(message);
            // String title = "发送电子邮件";
            // String res = "成功发送消息...";
            // String docType = "<!DOCTYPE html> \n";
            // out.println(docType + "<html>\n" + "<head><title>" + title +
            // "</title></head>\n"
            // + "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" +
            // title + "</h1>\n"
            // + "<p align=\"center\">" + res + "</p>\n" + "</body></html>");
            return  true;
        } catch (javax.mail.MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    private boolean sendEmail(final String email,final String name,final String confirm,final int type){
        if(null == email || email.equals("") || null ==confirm || confirm.equals("")||null == name || name.equals("")){
            return  false;
        }
        //连接地址
        String link =  IDefineString.HOME_ID+"/activate?code="+confirm+"&type="+String.valueOf(type);
       return sendEamil(email,IHtmlContext.HTMLEMAIL+link+IHtmlContext.LINK_HTMLEMAIL+name+IHtmlContext.HTMLEMAIL_NAME);
    }
    static class Authentication extends Authenticator {
        String username = null;
        String password = null;

        public Authentication() {
        }

        public Authentication(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            PasswordAuthentication pa = new PasswordAuthentication(username, password);
            return pa;
        }
    }
}
