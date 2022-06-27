use veganDB;
-- products
drop table if exists products;

CREATE TABLE products (
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

CREATE TABLE reserve (
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
    )

-- Orders
DROP TABLE IF EXISTS orders;
CREATE TABLE `orders`
(
    order_id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id            INT       NOT NULL,
    total_amount       INT       NOT NULL, -- 訂單總花費
    created_date       TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL
);

CREATE TABLE order_item
(
    order_item_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT NOT NULL, -- 商品數量
    amount        INT NOT NULL  -- 商品花費
);
