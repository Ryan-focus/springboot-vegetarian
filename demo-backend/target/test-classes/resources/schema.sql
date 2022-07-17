use veganDB;

-- USER
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
                       userId INT NOT NULL auto_increment primary key,
                       email nvarchar(128) not null UNIQUE ,  -- 驗證資料庫是否唯一值
                       password nvarchar(128) not null,
                       userName nvarchar(64) not null,
                       status nvarchar (32) not null,
                       userPic LONGTEXT,
                       registerTime DATE not null ,
                       lastLoginTime TIMESTAMP not null
);

-- business

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
                         UUID nvarchar(256) ,
                         restaurantNumber int ,
                         createdTime TIMESTAMP not null ,
                         lastLoginTime TIMESTAMP not null,
                         updateTime  TIMESTAMP not null
);

-- reserve
DROP TABLE IF EXISTS reserve;
CREATE TABLE reserve (
                         reserveId INT NOT NULL auto_increment primary key,
                         reserveDateTime DATETIME not null ,
                         adult int not null ,
                         child int not null ,
                         baby int not null ,
                         reserveTime DATETIME not null ,
                         restaurantId int not null,
                         businessId int not null,
                         reserveName nvarchar(64) not null ,
                         reservePhone nvarchar(64) not null
);

-- pos
DROP TABLE IF EXISTS pos;  -- 銷售時點情報系統 簡稱pos系統 / 主要功能在於統計商品的銷售、庫存與顧客購買行為
CREATE TABLE pos (
                  posId INT NOT NULL auto_increment primary key,
                  businessId Int not null UNIQUE,
                  validDate nvarchar(64) not null,
                  expiryDate DATETIME,
                  UUID nvarchar(256)
);

-- PosBusiness
DROP TABLE IF EXISTS posBusiness;
CREATE TABLE posBusiness (
                     posBusinessId      INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     posId              INT NOT NULL,
                     businessId         INT NOT NULL,
                     visitors           INT NOT NULL,
                     turnOver           INT NOT NULL
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
                         productImage nvarchar(640),
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

--saveRestaurant
drop table if exists saveRestaurant;
CREATE TABLE saveRestaurant(
                     userId int NOT NULL,
                     restaurantNumber int NOT NULL,
                     saveDate DATETIME NOT NULL
                    
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
                     postAuditDate DATETIME,
                     postCategory NVARCHAR(256),
                     likeCount INT default 0,
                     userId INT
);

-- favorite post
drop table if exists fav_post;
CREATE TABLE fav_post(
                     postId INT NOT NULL ,
                     favDate DATETIME NOT NULL,
                     userId INT NOT NULL
                    
);
-- like post
drop table if exists like_post;
CREATE TABLE like_post(
                     postId INT NOT NULL ,
                     likeDate DATETIME NOT NULL,
                     userId INT NOT NULL
                    
);

-- forum
DROP TABLE IF EXISTS forum;
CREATE TABLE forum(
	forumId int NOT NULL auto_increment primary key,
	forumTitle nvarchar(50) NOT NULL,
	forumContent nvarchar(1024) NOT NULL,
	forumCategory VARCHAR(64) NOT NULL,
	forumImage nvarchar(640),
	forumCreateTime TIMESTAMP NOT NULL,
	forumUpdateTime TIMESTAMP NOT NULL);

drop table if exists users;