# Owner接口定义

## 登录模块
- 请求的地址：/owner/login
- 请求方法：POST
- 请求参数格式：json
- 参数描述：{"userName": "[user name]", "password": "[password]"}

-响应格式：json
-响应描述：
{
  "error"：true/false                     //true为登录失败，错误信息在messge字段中，false为登录成功。
  "message"：“[login message]”            //登录的错误信息或者成功的信息。
  "date"：“[响应的时间，格式为：YYYY-MM-dd HH：mm：ss]”    //登录响应的时间戳
  "data"：{                                            //登录成功时候返回的用户信息，失败则为空
    "userName":"[user name]"
    "imge":"user image"                                   //用户头像网址
  }
}


## 注册模块
- 请求地址：/owner/register
- 请求放到：POST
- 请求参数格式：json
- 参数描述：
{
  "userName":"userName",
  "password":"password",
  "email":"email"
}
userName:用户名
password：用户密码
email：邮箱

- 参数有效性检查：
  方法：POST
  地址：/owner/check
  请求参数：{“data”："userName/email"}
  响应参数：{"status":false/true},false:该参数无效，true：参数有效


- 响应数据格式：json
- 响应数据描述：
{
  "error":true,
  "message":“message”,
  "date":”date“,
  "data":{
    "userName":"name",
    "imge":"userimage"
  }
}

## 申请开店
当Owner登录成功后，可以申请开店，开店需要提交的表单内容如下：
ID:身份证号
realName:真实名字
realImg：个人手持身份证的照片
shopName：店名
shopImg：商店logo
shopDesc：商店描述

请求地址：/owner/apply
方法：POST

响应格式：json
响应描述：{"error":false,}


@XDU-PM9, PARKnSHOP 2016























