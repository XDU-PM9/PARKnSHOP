/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/30 14:12:45                          */
/*==============================================================*/


/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   addressId            int not null auto_increment,
   userId               int,
   province             varchar(255),
   country              varchar(255),
   others               varchar(255),
   phone                bigint(11),
   name                 varchar(150),
   postcode             int(6),
   primary key (addressId)
);


/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   adminId              int not null auto_increment,
   username             varchar(50),
   password             varchar(50),
   roleId               int,
   primary key (adminId)
);



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
   goodsId              int,
   createTime           datetime,
   state                int,
   primary key (collectionId)
);


/*==============================================================*/
/* Table: collectshop                                           */
/*==============================================================*/
create table collectshop
(
   scollectId           int not null auto_increment,
   userId               int,
   shopId               int,
   createTime           datetime,
   state                int,
   primary key (scollectId)
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
   addressId            int not null,
   photo                varchar(200) not null,
   goodsName            varchar(100),
   goodsDescribe        varchar(200),
   amount               int not null,
   createTime           datetime not null,
   paidTime             datetime,
   price                float(20,2) not null,
   commission           float(20,2) not null,
   commissionRate       float(20,2) not null,
   comment              varchar(300),
   commentTime          datetime,
   state                int not null,
   primary key (ordersId)
);



/*==============================================================*/
/* Table: owner                                                 */
/*==============================================================*/
create table owner
(
   ownerId              int not null auto_increment,
   roleId               int not null,
   username             varchar(50),
   phone                varchar(50) not null,
   password             varchar(50) not null,
   email                varchar(50),
   address              varchar(50),
   identityId           varchar(50),
   picture              varchar(100),
   balance              float(20,2) not null,
   confirm              varchar(50),
   state                int,
   primary key (ownerId)
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
   ownerId              int,
   shopName             varchar(50) not null,
   introduction         varchar(1000),
   photoGroup           varchar(100) not null,
   views                int not null,
   logo                 varchar(100),
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
   username             varchar(50),
   phone                varchar(50) not null,
   password             varchar(50) not null,
   email                varchar(50),
   balance              float(20,2) not null,
   confirm              varchar(50),
   state                int,
   primary key (userId)
);



alter table address add constraint FK_Reference_15 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table admin add constraint FK_Reference_11 foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

alter table cart add constraint FK_Reference_5 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table collection add constraint FK_Reference_12 foreign key (goodsId)
      references goods (goodsId) on delete restrict on update restrict;

alter table collection add constraint FK_Reference_8 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table collectshop add constraint FK_Reference_13 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table collectshop add constraint FK_Reference_14 foreign key (shopId)
      references shop (shopId) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_3 foreign key (shopId)
      references shop (shopId) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_16 foreign key (addressId)
      references address (addressId) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_6 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_7 foreign key (goodsId)
      references goods (goodsId) on delete restrict on update restrict;

alter table owner add constraint FK_Reference_10 foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

alter table shop add constraint FK_Reference_9 foreign key (ownerId)
      references owner (ownerId) on delete restrict on update restrict;

alter table singleGood add constraint FK_Reference_4 foreign key (goodsId)
      references goods (goodsId) on delete restrict on update restrict;

alter table user add constraint FK_Reference_1 foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

