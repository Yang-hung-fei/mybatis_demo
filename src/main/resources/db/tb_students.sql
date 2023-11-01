create table  tb_students(
	sid int primary key auto_increment,
    stu_num char(5) not null unique,
    stu_name varchar(20) not null,
    stu_gender char(2) not null,
    stu_age int not null
);