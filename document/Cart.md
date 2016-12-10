#购物车
##addProduct
method=post  
**参数:** goodsId(商品ID)、amount（数量）  
**返回：** "true" or "false","true"表示成功  
**注：** 如购物车中已存在该商品，我会对amount进行相加
##removeProduct
method=post  
**参数:** goodsId(商品ID)  
**返回：** "true" or "false"，"true"表示成功
##changeAmount
同addProduct  
**注：**amount为修改后的数量，例：若amount为2,则数量将修改为2
##listProduct
method=post  
**参数：** start(从第几条开始)，count(读多少条)，例：start=6,count=5;将从第6条开始读6条记录
**返回：** json,json中每条包括的内容如下：  
int cartId;(购物车ID，你不一定会用到)  
int goodsId;（商品ID）  
int amount;（数量）
double price;（价格）  
double discount;（折扣）  
String goodsName;（商品名）  
String goodsIntroduction;（商品介绍）  
String picture;（商品最近的一张图片）  
String shopName;（店铺名）  
int shopId;（店铺ID）  
String tips = "";（提示，将来可能会用）
