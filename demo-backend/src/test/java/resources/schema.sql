use veganDB;

-- USER
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
                       userId INT NOT NULL auto_increment primary key,
                       email nvarchar(128) not null UNIQUE ,  -- 驗證資料庫是否唯一值
                       password nvarchar(128) not null,
                       userName nvarchar(64) not null,
                       status nvarchar (32) not null,
                       userPic nvarchar(64),
                       registerTime DATE not null ,
                       lastLoginTime TIMESTAMP not null
);

-- business
# 企業登入 ，應有 Id 、聯絡人姓名
# 手機號碼、電子信箱、餐廳名稱
# 餐廳所在地址 、審核、
# Logic: 商家會到前台註冊，填完表單後送資料到後台去做審核
#審核通過可以開通狀態為"試用期" 或者 已開通
# 審核未過 應可查閱原因
DROP TABLE IF EXISTS business;
CREATE TABLE business(
                         businessId int NOT NULL auto_increment primary key,
                         email varchar(256) not null UNIQUE ,
                         password nvarchar(256) not null,
                         principalName nvarchar(64) not null,
                         principalPhone nvarchar(32) not null UNIQUE ,
                         businessName nvarchar(64) not null,
                         located nvarchar(64) not null,
                         businessPic nvarchar(64),
                         status nvarchar (32) not null,
                         createdTime TIMESTAMP not null ,
                         lastLoginTime TIMESTAMP not null
);

#針對前台消費者去做設計
#每送出一個訂位記錄，就會包含 autoId、訂位日期
# Count(大人、小孩、嬰兒)、送出訂位時間、餐廳Id、商家Id,使用者ID
#
-- reserve
DROP TABLE IF EXISTS reserve;
CREATE TABLE reserve (
                         reserveId INT NOT NULL auto_increment primary key,
                         reserveDateTime DATETIME not null ,
                         adult int not null ,
                         child int not null ,
                         baby int not null ,
                         reserveTime DATE not null ,
                         restaurantId int not null,
                         businessId int not null,
                         userId int
);

#針對後台資料呈現
#訂位管理系統應包含 autoId 、businessId 、可使用天數(有效天數) 、到期日、來客數、營業額、開始使用日期、最後使用日期
DROP TABLE IF EXISTS pos;  -- 銷售時點情報系統 簡稱pos系統 / 主要功能在於統計商品的銷售、庫存與顧客購買行為
CREATE TABLE pos (
                  posId INT NOT NULL auto_increment primary key,
                  businessId Int not null UNIQUE,
                  validDate DATETIME not null,
                  expiryDate DATETIME not null,
                  visitors int not null ,
                  turnOver int not null ,
                  startDate DATETIME not null ,
                  lastUsingDate DATETIME not null 
);

-- product
DROP TABLE IF EXISTS product;
CREATE TABLE product (
-- 在 MySQL 裡面 INT 是 INTEGER 的縮寫
                         productId INT NOT NULL auto_increment primary key,
                         productName nvarchar(64) NOT NULL,
                         category nvarchar(64) NOT NULL,
                         veganCategory nvarchar(64) NOT NULL,
                         productPrice INT NOT NULL,
                         productImage nvarchar(64),
                         stock       INT NOT NULL ,
                         createdTime DATETIME NOT NULL,
                         updatedTime DATETIME NOT NULL,
                         description TEXT
);

-- Order
drop table IF EXISTS `order`;
create TABLE `order`
(
    orderId       INT                   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId        INT                   NOT NULL,
    payment       INT                   NOT NULL, -- 訂單總花費
    status        NVARCHAR(64)          NOT NULL, -- 訂單狀態
    createdTime   TIMESTAMP             NOT NULL,
    updatedTime   TIMESTAMP             NOT NULL
);

drop table IF EXISTS orders;
# CREATE TABLE orders
# (
#     order_id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
#     user_id            INT       NOT NULL,
#     total_amount       INT       NOT NULL, -- 訂單總花費
#     created_date       TIMESTAMP NOT NULL,
#     last_modified_date TIMESTAMP NOT NULL
# );


drop table IF EXISTS order_item;
CREATE TABLE order_item
(
    order_item_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT NOT NULL, -- 商品數量
    amount        INT NOT NULL  -- 商品花費
);




drop table IF EXISTS orderEntry;
# create TABLE orderEntry
# (
#     orderEntryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
#     orderUUID     VARCHAR(36) NOT NULL,
#     productId    INT NOT NULL,
#     quantity      INT NOT NULL,
#     entryPrice    INT NOT NULL
# );

drop table IF EXISTS cart;
# create TABLE cart
# (
#     id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
#     userId     VARCHAR(36) NOT NULL,
#     cartUUID    VARCHAR(36) NOT NULL,
#     createdTime   TIMESTAMP NOT NULL, -- 商品數量
#     updatedTime    TIMESTAMP  -- 商品花費
# );
drop table IF EXISTS cartEntry;
# create TABLE cartEntry
# (
#     cartEntryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
#     cartUUID     VARCHAR(36) NOT NULL,
#     productId    INT NOT NULL,
#     quantity      INT NOT NULL, -- 商品數量
#     entryPrice    INT NOT NULL  -- 商品花費
# );

-- restaurant
drop table if exists restaurant;
create table restaurant(
                       restaurantNumber int NOT NULL auto_increment primary key,
                       restaurantName nvarchar(256) NOT NULL,
                       restaurantTel nvarchar(256),
                       restaurantAddress nvarchar(256) NOT NULL,
                       restaurantCategory nvarchar(256),
                       restaurantType nvarchar(256),
                       restaurantBusinessHours nvarchar(256),
                       restaurantScore nvarchar(256),
                       imageUrl nvarchar(256),
                       createdTime TIMESTAMP NOT NULL,
                       updatedTime TIMESTAMP NOT NULL
);

-- post
drop table if exists post;
CREATE TABLE post(
                     postId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     title NVARCHAR(256) NOT NULL,
                     postedDate DATETIME NOT NULL,
                     postedText NVARCHAR(8192) NOT NULL,
                     imgUrl NVARCHAR(256),
                     postStatus NVARCHAR(256),
                     postAuditDate DATETIME NOT NULL
);

-- forum
DROP TABLE IF EXISTS forum;
CREATE TABLE forum(
                      forumId int NOT NULL auto_increment primary key,
                      forumTitle nvarchar(128) NOT NULL,
                      forumContent nvarchar(512) NOT NULL,
                      forumCreateTime TIMESTAMP NOT NULL,
                      forumUpdateTime TIMESTAMP NOT NULL
);

drop table if exists users;