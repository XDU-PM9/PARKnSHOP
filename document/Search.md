#搜索
**搜索中名字和类型不需要完全相同，包含即可**
##searchByType
method=post  
这个其实就是按类型过滤，类型按照老师的PPT上的有以下几种：  
TV& Home Theater  
Computers & Tablets  
Cell Phones  
Cameras & Camcorders  
Audio  
Car Electronics & GPS  
Video, Games, Movies & Music  
Health, Fitness & Sports  
Home & Office  
**参数：**type(类型),start（从第几条开始）,count（读多少条）  
**返回：**json,json中每条的内容如下：  
int goodsId;  
String goodsName;  
double price;  
double discount;  
String goodsIntroduction;  
String picture;  
String shopName;  
int shopId;  
String tips = "";
##searchById
**参数：**goodsId(商品ID)
**返回：**json,一条商品记录，内容同上
##searchByName
**参数：**name(商品名字)
**返回：**json,一条商品记录，内容同上
##search
**参数：**json,json内容{  
boolean time;(按时间排序,新的在后面)  
boolean timeDesc;（按时间降序排序，新的在前面）  
boolean price;(价格)  
boolean priceDesc;  
boolean view;(浏览量)  
boolean viewDesc;  
boolean discount;（折扣）  
boolean discountDesc;  
boolean sales;（销量）  
boolean salesDesc;  
String name;（商品名）  
String type;（类型）  
**返回：**json,多条商品记录，内容同上
