//建议写后台的在IDEA中装一个GsonFormat插件，用来直接生成json对于的bean对象。
# 管理店家申请模块

请求地址：/amin/apply
方法：POST
请求数据格式：json
参数描述：
{"size":5,"index":1}
size:请求数据的分页量
index：按照size分页后的数据第index页



响应数据格式：json
响应参数描述：
{
    “error”:false,
    "total":9,        //按照请求的size分页，总共有几页数据，比如size等于5，数据库有11条数据，那么total为3
    "realSize":5,      //改成响应返回的实际数据量，比如上面，当size为5，index为3时，realSize为1
    "data":
    [
      {
          "ownerName":"注册名",
          "ownerImg":"头像url",
          "ownerEmail":"注册邮箱",
          "realName":"真实名字",
          "realImg":"正面带身份证照片的url",
          "shopName":"店名",
          "shopImg":"店的logo url",
          "shopDesc":"店的描述"
      }
      ...
    ]
}


# 管理请求
对于每一个开店请求，管理员可以处理为：允许，或拒绝
处理结果提交：
网址：/admin/reply
方法：POST
数据格式：json
参数描述：
{
  "ownerName":"店家注册名"，
  "result":"0/1"    //0代表拒绝请求，1代表允许请求
}

响应格式：json
参数描述：
{
  “error”:true/false  //false：请求已经响应且处理成功，true：请求处理失败
}
