use veganDB;
-- products
drop table if exists products;

create TABLE products (
                          id int NOT NULL auto_increment primary key,
                          name nvarchar(50) NOT NULL,
                          category nvarchar(50) NOT NULL,
                          veganCategory nvarchar(50) NOT NULL,
                          price float NOT NULL,
                          image nvarchar(50),
                          createdTime DATETIME NOT NULL,
                          updatedTime DATETIME NOT NULL,
                          description TEXT
    );
-- reserve
drop table if exists reserve;

create TABLE reserve (
                         reserveId int NOT NULL auto_increment primary key,
                         reserveDate DATETIME not null ,
                         count int not null ,
                         reserveTime DATETIME not null ,
                         restaurantId int not null,
                         userId int not null
);
-- users
drop table if exists users;
create table users(
                      userId int NOT NULL auto_increment primary key,
                      email varchar(256) not null UNIQUE ,
                      password nvarchar(256) not null,
                      userName nvarchar(64) not null,
                      status nvarchar (20) not null,
                      userPic nvarchar(50),
                      createdTime TIMESTAMP not null ,
                      lastLoginTime TIMESTAMP not null
    );

-- Order
drop table IF EXISTS `order`;
create TABLE `order`
(
    orderId       INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
    orderUUID     VARCHAR(45)   NOT NULL,
    userId        INT           NOT NULL,
    payment       VARCHAR(50)   NOT NULL, -- 訂單總花費
    shipping      VARCHAR(50)   NOT NULL,
    status        VARCHAR(50)   NOT NULL,
    createdTime   TIMESTAMP     NOT NULL,
    updatedTime   TIMESTAMP     NOT NULL
);

drop table IF EXISTS `orders`;
CREATE TABLE `orders`
(
    order_id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id            INT       NOT NULL,
    total_amount       INT       NOT NULL, -- 訂單總花費
    created_date       TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL
);


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
create TABLE orderEntry
(
    orderEntryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    orderUUID     VARCHAR(36) NOT NULL,
    productid    INT NOT NULL,
    quantity      INT NOT NULL,
    entryPrice    INT NOT NULL
);

drop table IF EXISTS cart;
create TABLE cart
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId     VARCHAR(36) NOT NULL,
    cartUUID    VARCHAR(36) NOT NULL,
    createdTime   TIMESTAMP NOT NULL, -- 商品數量
    updatedTime    TIMESTAMP  -- 商品花費
);
drop table IF EXISTS cartEntry;
create TABLE cartEntry
(
    cartEntryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cartUUID     VARCHAR(36) NOT NULL,
    productId    INT NOT NULL,
    quantity      INT NOT NULL, -- 商品數量
    entryPrice    INT NOT NULL  -- 商品花費
);

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
    updatedTime TIMESTAMP NOT NULL,
	restaurantMap nvarchar(500)
);

-- post
drop table if exists post;
CREATE TABLE post(
    postId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(256) NOT NULL,
    postedDate DATETIME NOT NULL,
    postedText NVARCHAR(8192) NOT NULL,
    imgurl NVARCHAR(256),
    postStatus NVARCHAR(256)
);

-- forum
DROP TABLE IF EXISTS forum;
CREATE TABLE forum(
	forumId int NOT NULL auto_increment primary key,
	forumTitle nvarchar(128) NOT NULL,
	forumContent nvarchar(512) NOT NULL,
	forumCreateTime TIMESTAMP NOT NULL,
	forumUpdateTime TIMESTAMP NOT NULL);

