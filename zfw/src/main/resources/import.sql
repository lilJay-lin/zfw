drop table if exists tbl_role;
CREATE TABLE tbl_role(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
description varchar(200)
);

drop table if exists tbl_user;
CREATE TABLE tbl_user(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
email varchar(100) unique,
phoneNum varchar(50) unique,
headImgUrl varchar(200),
password varchar(50),
salt varchar(50),
locked tinyint(1),
description varchar(200),
creater varchar(50),
lastEditor varchar(50),
createDate date,
updateDate date
);

drop table if exists tbl_permission;
CREATE TABLE tbl_permission(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
code varchar(50) unique,
description varchar(200),
creater varchar(50),
lastEditor varchar(50),
createDate date,
updateDate date
);

drop table if exists tr_user_role;
CREATE TABLE tr_user_role(
id varchar(50) PRIMARY KEY,
userId varchar(50),
roleId varchar(50)
);


drop table if exists tr_role_permission;
CREATE TABLE tr_role_permission(
id varchar(50) PRIMARY KEY,
roleId varchar(50),
permissionId varchar(50)
);


drop table if exists tbl_ad;
CREATE TABLE tbl_ad(
id varchar(50) PRIMARY KEY,
name varchar(50),
code varchar(50),
description varchar(200),
creater varchar(50),
lastEditor varchar(50),
createDate date,
updateDate date
);





insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");
insert into role(id,name,description) values("id1","name1","desc1");

drop table if exists test;
CREATE TABLE test(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
description varchar(200)
);

insert into test(id,name,description) values("id1","name1","desc1") ON DUPLICATE KEY UPDATE description="a"; 

select * from test;


creater varchar(50),
lastEditor varchar(50),
createDate date,
updateDate date
