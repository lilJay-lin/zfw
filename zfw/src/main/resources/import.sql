drop table if exists tbl_role;
CREATE TABLE tbl_role(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false NOT NULL
);

drop table if exists tbl_user;
CREATE TABLE tbl_user(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
email varchar(100) unique,
phone_num varchar(50) unique,
head_img_url varchar(200),
password varchar(50),
salt varchar(50),
locked tinyint(1) default false,
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_permission;
CREATE TABLE tbl_permission(
id varchar(50) PRIMARY KEY,
name varchar(50) unique,
code varchar(50) unique,
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date
);

drop table if exists tr_user_role;
CREATE TABLE tr_user_role(
id varchar(50) PRIMARY KEY,
user_id varchar(50),
role_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);


drop table if exists tr_role_permission;
CREATE TABLE tr_role_permission(
id varchar(50) PRIMARY KEY,
role_id varchar(50),
permission_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date
);

drop table if exists tr_user_rep;
CREATE TABLE tr_user_rep(
id varchar(50) PRIMARY KEY,
user_id varchar(50),
real_estate_project_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);


drop table if exists tbl_real_estate_project;
CREATE TABLE tbl_real_estate_project(
id varchar(50) PRIMARY KEY,
name varchar(50),
address varchar(200),
tel varchar(50),
on_sale_date date,
on_ready_date date,
property_type varchar(20),
building_type varchar(20),
decoration_status varchar(20),
household_num int(8),
floor_area_ratio float(6,4),
green_rate float(6,4),
parking_space_num int(8),
property_years int(4),
developer varchar(50),
pre_sale_permit varchar(50),
property_company varchar(50),
property_fee float(6,2),
introduction varchar(2000),
surrounding varchar(2000),
traffic varchar(2000),
region varchar(20),
longitude float(10,6),
latitude float(10,6),
average_price int(4),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
one_room_num int(4),
two_room_num int(4),
three_room_num int(4),
four_room_num int(4),
five_room_num int(4),
over_five_room_num int(4),
min_room_gross_floor_area int(4),
max_room_gross_floor_area int(4),
sale_status varchar(20),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_house_type;
CREATE TABLE tbl_house_type(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
real_estate_project_name varchar(50),
name varchar(50),
average_price int(4),
on_sale_date date,
inside_area float(8,2),
sale_status varchar(20),
gross_floor_area float(8,2),
room_num int(4),
hall_num int(4),
kitchen_num int(4),
toilet_num int(4),
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
region varchar(20),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_video;
CREATE TABLE tbl_rep_video(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_panorama;
CREATE TABLE tbl_rep_panorama(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_image;
CREATE TABLE tbl_rep_image(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
type int(4),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_avg_price_history;
CREATE TABLE tbl_rep_avg_price_history(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
value float(8,2),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_ht_image;
CREATE TABLE tbl_ht_image(
id varchar(50) PRIMARY KEY,
house_type_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_ht_panorama;
CREATE TABLE tbl_ht_panorama(
id varchar(50) PRIMARY KEY,
house_type_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
);

drop table if exists tbl_ht_ring_display;
CREATE TABLE tbl_ht_ring_display(
id varchar(50) PRIMARY KEY,
house_type_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date date,
update_date date,
del_flag tinyint(1) default false
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
updateDate date,
del_flag tinyint(1) default false
);
