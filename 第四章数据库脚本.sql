create table bookinfo(
	id INT not null primary key,
	title VARCHAR(50) not null,
	author VARCHAR(50) not null,
	bookconcern VARCHAR(100) not null,
	publish_date DATE not null,
	price FLOAT(4,2) not null,
	amount SMALLINT,
	remark VARCHAR(200)
)
ENGINE = InnoDB;

insert into bookinfo values (1, 'Java Web开发详解', '孙鑫', '电子工业出版社', '2006-4-20', 99.00, 35, null);
insert into bookinfo values (2, 'Struts 2深入详解', '孙鑫', '电子工业出版社', '2008-6-15', 79.00, 20, null);
insert into bookinfo values (3, 'Servlet/JSP深入详解', '孙鑫', '电子工业出版社', '2008-7-1', 79.00, 10, null);