drop database if exists aswxmall;
drop user if exists 'aswxmall'@'localhost';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database aswxmall default character set utf8mb4 collate utf8mb4_unicode_ci;
use aswxmall;
create user 'aswxmall'@'localhost' identified by 'aswxmall!@#';
grant all privileges on aswxmall.* to 'aswxmall'@'localhost';
flush privileges;