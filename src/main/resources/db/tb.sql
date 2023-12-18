create table tb_students
(
    sid        int primary key auto_increment,
    stu_num    char(5)     not null unique,
    stu_name   varchar(20) not null,
    stu_gender char(2)     not null,
    stu_age    int         not null
);

-- 創建用戶表
create table users
(
    user_id       int primary key auto_increment,
    user_name     varchar(20)  not null unique,
    user_pwd      varchar(20)  not null,
    user_realname varchar(20)  not null,
    user_img      varchar(100) not null
);

-- 用戶詳情表
create table details
(
    detail_id int primary key auto_increment,
    user_addr varchar(50) not null,
    user_tel  char(11)    not null,
    user_desc varchar(200),
    uid       int         not null unique-- ,
    -- constraint FK_USER foreign key (uid) references user(user_id)
    -- 一般來說在企業中不會創建關聯，只會利用業務邏輯實現關聯，所以如上註解
);