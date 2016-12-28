//建议写后台的在IDEA中装一个GsonFormat插件，用来直接生成json对于的bean对象。

#管理登录模块

请求地址：/admin/login
方法:POST
请求数据格式：json
参数描述：
{
"userName":"userName", "password":"password"
}
响应数据格式:jason
响应参数描述:
{
      error : "true/false"         //true为登录失败，错误信息在message字段中，false为登录成功
      message : "login message"  //登录的错误信息或者成功的信息
      date : “[响应的时间，格式为：YYYY-MM-dd HH：mm：ss]”
      data : {"userName":"管理员名字"}
}


#管理注销模块

请求地址:/admin/logout
方法:POST


# 管理店家申请模块

请求地址：/admin/apply
方法：POST
请求数据格式：json
参数描述：
{"size":5,"index":1}
size:请求数据的分页量
index：按照size分页后的数据第index页
响应数据格式：json
响应参数描述：
{
    "error":false,
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
          "shopId":"shopId",
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
  "shopId":12345，
  "result":0/1    //0代表拒绝请求，1代表允许请求
}
响应格式：json
参数描述：
{
  “error”:true/false  //false：请求已经响应且处理成功，true：请求处理失败
}


#搜索店主模块

请求地址：/admin/serachowner
方法:POST
请求数据格式：json
参数描述：
{
"ownerId":"ownerid"
}
响应数据格式:jason
响应参数描述:
{
    "error": "true/false",//搜索成功或失败
    "data": {
        "ownerId": 123,
        "username": "String",
        "userImage": "String",
        "realname": "String",
        "phone": "String",
        "email": "String",
        "address": "String"
		"state":	"int"
		}
}


#拉黑店主模块

请求地址:/admin/blackowner
方法：POST
请求数据格式：json
参数描述：
{
	"ownerId":"ownerid"
}

响应参数描述：
{
	"error":boolean
}


#恢复店主模块

请求地址:/admin/whiteowner
方法：POST
请求数据格式：json
参数描述：
{
	"ownerId":"ownerid"
}
响应参数描述：
{
	"error":boolean
}


#删除店主模块

请求地址:/admin/deleteowner
方法：POST
请求数据格式：json
参数描述：
{
	"ownerId":"ownerid"
}
响应参数描述：
{
	"error":boolean
}


#搜索店铺

请求地址 /admin/searchshop
方法:POST
请求格式：JSON
参数描述：{
	"shopid":"shopid"
}
响应参数描述：
{
	"error":boolean,
	"data":{
	"shopId":"int",
	"shopName":"String",
	"introduction":"String",
	"photoGroup":	"String",
	"views":	"int",
	"logo":		"String",
	"state":	"int"
	"ownerId"	:"int"//通过shopentity.getownerbyownerid.getownerid（）拿到
	}
}


#拉黑店铺

请求地址: /admin/blackshop
方法：POST
请求格式：JSON
参数描述：
{
	"shopid":"shopid"
}
响应参数描述：
{
	"error":boolean
}


#恢复店铺

请求地址: /admin/whiteshop
方法：POST
请求格式：JSON
参数描述：
{
	"shopid":"shopid"
}
响应参数描述：
{
	"error":boolean
}


#删除店铺

请求地址: /admin/deleteshop
方法：POST
请求格式：JSON
参数描述：
{
	"shopid":"shopid"
}
响应参数描述：
{
	"error":boolean
}


#请求所有卖家列表

请求地址：/admin/applyallowner
方法:POST
请求格式:JSON
参数描述：
{
	”size：5，
	”index“:1
}
响应参数描述：
{
	"error":boolean,
	"total":9,
	"realSize":5,
	"data":[ {
        "ownerId": 123,
        "username": "String",
        "userImage": "String",
        "realname": "String",
        "phone": "String",
        "email": "String",
        "address": "String"
		"state":	"int"
		}]//注意  这个data是个list  参照ApplyResposeBean.data
}


#请求所有店铺列表

请求地址：/admin/applyallshop
方法：POST
请求格式：JSON
参数描述：
{
	”size：5，
	”index“:1
}
响应参数描述：
{
	"error":boolean,
	"total":9,
	"realSize":5,
	"data":[{
		"shopId":"int",
		"shopName":"String",
		"introduction":"String",
		"photoGroup":	"String",
		"views":	"int",
		"logo":		"String",
		"state":	"int"
		"ownerId"	:"int"//通过shopentity.getownerbyownerid.getownerid（）拿到
	}]//注意  这个data是个list  参照ApplyResposeBean.data
}


#请求所有顾客列表

请求地址：/admin/applyalluser
方法：POST
请求格式：JSON
参数描述：
{
	”size：5，
	”index“:1
}
响应参数描述：
{
	"error":boolean,
	"total":9,
	"realSize":5,
	"data":[{
		"userId":"int",
		"username":"String",
		"phone":	"String",
		"email":	String",
		"userImage":"String",
		"state":	"int"
	}]//注意  这个data是个list  参照ApplyResposeBean.data
}


#搜索顾客模块

请求地址：/admin/searchuser
方法:POST
请求数据格式：json
参数描述：
{
	"userId":"userid"
}
响应数据格式:json
响应参数描述:
{
    "error": "true/false",
    "data": {
        "userId": 123,
        "username": "String",
        "userImage": "String",
        "phone": "String",
        "email": "String",
		"state": "int"
	}
}


#拉黑顾客

请求地址/admin/blackuser
方法：POST
请求格式：JSON
参数描述：{
	"userId":"userId"
}
响应参数描述：
{
	"error":boolean
}


#恢复顾客

请求地址: /admin/whiteuser
方法：POST
请求格式：JSON
参数描述：
{
	"userId":"userId"
}
响应参数描述：
{
	"error":boolean
}


#删除顾客

请求地址: /admin/deleteuser
方法：POST
请求格式：JSON
参数描述：
{
	"userId":"userId"
}
响应参数描述：
{
	"error":boolean
}

#管理商品的广告申请，管理员可以处理为：同意、拒绝
网址：/admin/replyGoodsAdvert
方法：POST
数据格式：json
参数描述：
{
  "id":12345，	  //广告的id
  "result":0/1    //0代表管理员传入拒绝该请求，1代表允许请求
}
响应格式：json
参数描述：
{
  "error":true/false  //代表拒绝或允许的处理结果
}


#取消商品的广告位置，即将正在广告位展示的广告取消
网址：/admin/cancelGoodsAdvert
方法：POST
数据格式：json
参数描述：
{
  "id":12345，	//根据广告的id，删除正在展示的广告
}
响应格式：json
参数描述：
{
  "error":true/false  
}


#获取Top10商品广告列表，即正在展示的广告
网址：/admin/getTop10GoodsAdvert
方法：POST
数据格式：json
参数描述：
{
  //不需要请求参数，直接发送请求给上述URL即可
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":	12		//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间，Data类型
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商品的信息
			"id":12
			"name":"name"
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}


#获取广告的商品的列表（正在申请的、展示的、取消展示的。。。）
网址：/admin/getAllGoodsAdvert
方法：POST
数据格式：json
参数描述：
{
  "index":12345,	//请求第几页
  "size":5			//请求多少条
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":	12		//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间，Data类型
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商品的信息
			"id":12
			"name":"name"
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}


#获取某个店主对应的广告的商品的列表，（正在申请的、展示的、取消展示的。。。）
网址：/admin/getUserGoodsAdvert
方法：POST
数据格式：json
参数描述：
{
  "userId":12			//店主的id,对应数据库中的userId
  "index":12345,	//请求第几页
  "size":5			//请求多少条
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":	12		//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间，Data类型
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商品的信息
			"id":12
			"name":"name"
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}


#管理商店的广告，管理员可以处理为：同意、拒绝
网址：/admin/replyShopAdvert
方法：POST
数据格式：json
参数描述：
{
  "id":12345，//广告的id
  "result":0/1    //0代表管理员传入拒绝该请求，1代表允许请求
}
响应格式：json
参数描述：
{
  "error":true/false  //代表拒绝或允许的处理结果
}


#取消商店的广告位置，即将正在广告位展示的广告取消
网址：/admin/cancelShopAdvert
方法：POST
数据格式：json
参数描述：
{
  "id":12345，	//根据广告的id，删除正在展示的广告
}
响应格式：json
参数描述：
{
  "error":true/false  
}


#获取Top5商店广告列表，即正在展示的商店
网址：/admin/getTop5ShopAdvert
方法：POST
数据格式：json
参数描述：
{
  //不需要请求参数，直接发送请求给上述URL即可
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":	12		//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间，Data类型
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商品的信息
			"id":12
			"name":"name"
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}


#获取广告的商店的列表（正在申请的、展示的、取消展示的。。。）
网址：/admin/getAllShopAdvert
方法：POST
数据格式：json
参数描述：
{
  "index":12345,	//请求第几页
  "size":5			//请求多少条
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":12			//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商店信息
			"id":12,
			"name":"name",
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}


#获取某个店主对应的广告的商店的列表
网址：/admin/getUserShopAdvert
方法：POST
数据格式：json
参数描述：
{
  "userId":12			//店主的id,对应数据库中的userId
  "index":12345,	//请求第几页
  "size":5			//请求多少条
}
响应格式：json
参数描述：
{
   "error":true/false,
   "total":2,		
   "realSize":5,
   "data":[{
		"advertId":12			//广告id
		"userId":12			//广告对应的店主
		"startTime":"Date"		//广告开始时间
		"price":12.5			//广告的花费
		"state":state
		"detail":{			//该广告的商店信息
			"id":12
			"name":"name"
			"introduction"："intro"
		}
		//"detailOfOwner":{	//提出申请的店家的信息，暂时不用
		//	"ownerid":12,
		//	"realname":"realname",
		//	"phone":"029-8083028"
		//}
   }]
}



#数据库备份模块

展示备份文件
url:/admin/getallfile
参数:null
return:
        error:
        //lastbackuptime是最新备份文件时间,error=true的话lastbackuptime为null
         lastbackuptime:"String"
        //data是所有文件名集合
        data:[{
        filename:"String"
       } ]
        从service拿到的是个List
        File f =(File) list.get(list.size()-1);拿到最新备份文件
        long time = file.lastModified();//返回文件最后修改时间，是以个long型毫秒数
        String lastbackuptime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));


备份
url:/admin/backup
参数：null
return:
        error:true/false



回滚
url:/admin/rollback
参数:filename:"String"
return:
    error:true/false

删除备份
url:/admin/deletebackup
参数:filename:"String"
return:
    error:true/false