package com.parknshop.bean;

/**
 * Created by niewenzhi on 2016/12/4.
 */
public class ReplyRequestBean {
/** "ownerName":"店家注册名"，
  *  "result":"0/1"    //0代表拒绝请求，1代表允许请求
 */
    private String ownerName;
    private int result;

    public void setOwnerName(String ownerName){
        this.ownerName=ownerName;
    }
    public void setResult(int result){
        this.result=result;
    }

    public String getOwnerName(){
        return this.ownerName;
    }

    public int getResult(){
        return this.result;
    }
}
