create database bookstore;

//�û�ע��
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
	id int primary key auto_increment,   -- ͼ��ID
	bookName varchar(20),    -- ͼ������
	bookClass_id int(18) not null, -- ��������ţ��������Ҫ�����������������ԡ�
		foreign key(bookClass_id) references bookClass(id) on delete cascade,
	bookNum int,    -- �����ͼ������
	bookPrice int,   -- ͼ�鵥��
	pic varchar(50) default 'images/nopic.jpg'   -- ����ʱĬ�ϵ�ͼƬ
);


create table bookClass(
	id int primary key auto_increment, -- ��Ʒ�������
	className varchar(20) not null    -- ��Ʒ��
);

INSERT INTO bookClass(id,className) VALUES(NULL,'�����ͼ��');
INSERT INTO bookClass(id,className) VALUES(NULL,'��ѧͼ��');
INSERT INTO bookClass(id,className) VALUES(NULL,'���ͼ��');
INSERT INTO bookClass(id,className) VALUES(NULL,'��ʷͼ��');


INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'��������˼��',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'php���˼��',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'java���˼��',1);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'ë��˼�뵼��',4);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'���״�ȫ',3);
INSERT INTO Book(id,bookName,bookClass_id) VALUES(NULL,'ƽ��������',2);


select * from (select b.id,bc.className,b.bookName,b.bookNum,b.bookPrice,b.pic FROM Book b LEFT JOIN bookClass bc ON b.bookClass_id=bc.id;) limit 2,5;

//��������ϲ�������һ����
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

�������װ����ͼ����Ʒ������

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





