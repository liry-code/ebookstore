create database bookstore;

//用户注册
create table user(
	id int primary key auto_increment,
	username varchar(20) not null unique,
	password varchar(20) not null,
	repassword varchar(20) not null,
	realname varchar(50),
	sex int default 1,
	addr varchar(100),
	mailcode varchar(6),
	tel varchar(11) not null,
	email varchar(20)
);



create table Book(
	id int primary key auto_increment,   -- 图书ID
	bookName varchar(20),    -- 图书名称
	bookClass_id int(18) not null, -- 所属类别编号，外键，需要参照类别表【参照完整性】
		foreign key(bookClass_id) references bookClass(id) on delete cascade,
	bookNum int,    -- 购买的图书数量
	bookPrice int,   -- 图书单价
	pic varchar(50) default 'images/nopic.jpg'   -- 购买时默认的图片
);


create table bookClass(
	id int primary key auto_increment, -- 商品类别名称
	className varchar(20) not null    -- 商品名
);

INSERT INTO bookClass(id,className) VALUES(NULL,'计算机图书');
INSERT INTO bookClass(id,className) VALUES(NULL,'文学图书');
INSERT INTO bookClass(id,className) VALUES(NULL,'烹饪图书');
INSERT INTO bookClass(id,className) VALUES(NULL,'历史图书');


INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'计算机编程思想',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'php编程思想',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'java编程思想',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'毛泽东思想导论',4);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'菜谱大全',3);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'平凡的世界',2);


select * from (select b.id,bc.className,b.bookName,b.bookNum,b.bookPrice,b.pic FROM Book b LEFT JOIN bookClass bc ON b.bookClass_id=bc.id;) limit 2,5;

//将两个表合并产生另一个表
create table t_book as select b.id,bc.className,b.bookName,b.bookNum,b.bookPrice,b.pic,b.bookClass_id FROM Book b LEFT JOIN bookClass bc ON b.bookClass_id=bc.id;





USE bookstore;;
SELECT b.id,bc.className,b.bookName,b.bookNum,b.bookPrice,b.pic FROM Book b LEFT JOIN bookClass bc ON b.bookClass_id=bc.id;

CREATE TABLE t_book AS SELECT b.id,bc.className,b.bookName,b.bookNum,b.bookPrice,b.pic,b.bookClass_id FROM Book b LEFT JOIN bookClass bc ON b.bookClass_id=bc.id;
DROP TABLE t_book;
SET NAMES gbk;
SELECT * FROM t_book;

SELECT * FROM t_book  LIMIT 2 , 4 ;

SET NAMES gbk;
SELECT * FROM Book;
DESC Book;

SHOW TABLES;

SELECT * FROM t_book WHERE bookClass_id=1;


INSERT INTO Book(id,bookName,bookClass_id)

购买项，封装购买图书商品和数量

create table shopItem(
	id int primary key auto_increment,
	num int not null
);




CREATE TABLE book_order(
	id INT PRIMARY KEY AUTO_INCREMENT,
	book_id int not null,
	foreign key(book_id) references book(id) on delete cascade,
	price int ,
	num int
);


create table order_items(
	id int primary key auto_increment,
	book_order_id int not null,
	foreign key(book_order_id) references book_order(id) on delete cascade,
	user_id int not null,
	foreign key(user_id) references user(id) on delete cascade,
	price int default 0,
	num int
);





