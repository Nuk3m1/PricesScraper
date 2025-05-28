create database if not exists PricesScraperSystem;
use PricesScraperSystem;


CREATE TABLE if not exists items (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             product_name VARCHAR(100)        comment '商品名',
                             abrasion VARCHAR(100)           comment '磨损度',
                             price DECIMAL(10, 2)            comment '售价',
                             seek_price DECIMAL(10, 2)       comment '求购价',
                             on_sale INT                     comment '当前在售（件）',
                             on_seek INT                     comment '当前求购（件）',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
