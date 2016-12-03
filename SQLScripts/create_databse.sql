/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/24 16:33:42                          */
/*==============================================================*/


drop table if exists advert;

drop table if exists cart;

drop table if exists collection;

drop table if exists commission;

drop table if exists goods;

drop table if exists orders;

drop table if exists photo;

drop table if exists role;

drop table if exists shop;

drop table if exists singleGood;

drop table if exists user;

/*==============================================================*/
/* Table: advert                                                */
/*==============================================================*/
create table advert
(
   advertId             int not null auto_increment,
   type                 int not null,
   userId               int,
   typeId               int not null,
   startTime            time not null,
   endTime              time not null,
   startDate            date not null,
   endDate              date not null,
   position             int not null,
   price                float(20,2) not null,
   state                int not null,
   primary key (advertId)
);

/*==============================================================*/
/* Table: cart                                                  */
/*==============================================================*/
create table cart
(
   cartId               int not null auto_increment,
   userId               int not null,
   goodsId              int not null,
   singleGoodId         int not null,
   amount               int not null,
   createTime           datetime not null,
   primary key (cartId)
);

/*==============================================================*/
/* Table: collection                                            */
/*==============================================================*/
create table collection
(
   collectionId         int not null auto_increment,
   userId               int not null,
   type                 int not null,
   typeId               int not null,
   createTime           datetime,
   state                int,
   primary key (collectionId)
);

/*==============================================================*/
/* Table: commission                                            */
/*==============================================================*/
create table commission
(
   commissionId         int not null auto_increment,
   rate                 float(20,2) not null,
   primary key (commissionId)
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   goodsId              int not null auto_increment,
   shopId               int not null,
   goodsName            varchar(50) not null,
   introduction         varchar(500) not null,
   price                float(20,2) not null,
   discount             float(20,2),
   inventory            int not null,
   photoGroup           varchar(50),
   createTime           datetime not null,
   views                int not null,
   state                int not null,
   primary key (goodsId)
);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   ordersId             int not null auto_increment,
   orderNumber          varchar(100) not null,
   userId               int not null,
   singleGoodId         int,
   goodsId              int not null,
   photo                varchar(200) not null,
   goodsName            varchar(100),
   goodsDescribe        varchar(200),
   amount               int not null,
   createTime           datetime not null,
   paidTime             datetime,
   address              varchar(100) not null,
   addressPhone         varchar(100) not null,
   price                float(20,2) not null,
   commission           float(20,2) not null,
   commissionRate       float(20,2) not null,
   comment              varchar(300),
   commentTime          datetime,
   state                int not null,
   primary key (ordersId)
);

/*==============================================================*/
/* Table: photo                                                 */
/*==============================================================*/
create table photo
(
   photoId              int not null auto_increment,
   photoGroup           varchar(50) not null,
   photoName            varchar(100),
   address              varchar(200) not null,
   primary key (photoId)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   roleId               int not null auto_increment,
   describes            varchar(50),
   primary key (roleId)
);

/*==============================================================*/
/* Table: shop                                                  */
/*==============================================================*/
create table shop
(
   shopId               int not null auto_increment,
   userId               int not null,
   shopName             varchar(50) not null,
   introduction         varchar(1000),
   photo                varchar(100) not null,
   views                int not null,
   createTime           datetime not null,
   state                int not null,
   primary key (shopId)
);

/*==============================================================*/
/* Table: singleGood                                            */
/*==============================================================*/
create table singleGood
(
   singleGoodId         int not null auto_increment,
   goodsId              int,
   typeDescribe         varchar(50),
   inventory            int,
   state                int,
   primary key (singleGoodId)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userId               int not null auto_increment,
   roleId               int not null,
   userName             varchar(50),
   phone                varchar(50) not null,
   passWord             varchar(50) not null,
   email                varchar(50),
   address              varchar(50),
   balance              float(20,2) not null,
   state                int,
   primary key (userId)
);

alter table cart add constraint FK_Reference_5 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table collection add constraint FK_Reference_8 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_3 foreign key (shopId)
      references shop (shopId) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_6 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_7 foreign key (goodsId)
      references goods (goodsId) on delete restrict on update restrict;

alter table shop add constraint FK_Reference_2 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table singleGood add constraint FK_Reference_4 foreign key (goodsId)
      references goods (goodsId) on delete restrict on update restrict;

alter table user add constraint FK_Reference_1 foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

