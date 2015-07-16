drop table if exists tbl_role;
CREATE TABLE tbl_role(
id varchar(50) PRIMARY KEY,
name varchar(50),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false NOT NULL
);

drop table if exists tbl_user;
CREATE TABLE tbl_user(
id varchar(50) PRIMARY KEY,
name varchar(50),
email varchar(100),
phone_num varchar(50),
head_img_url varchar(200),
password varchar(50),
salt varchar(50),
locked tinyint(1) default false,
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_permission;
CREATE TABLE tbl_permission(
id varchar(50) PRIMARY KEY,
name varchar(50),
code varchar(50),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tr_user_role;
CREATE TABLE tr_user_role(
id varchar(50) PRIMARY KEY,
user_id varchar(50),
role_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tr_role_permission;
CREATE TABLE tr_role_permission(
id varchar(50) PRIMARY KEY,
role_id varchar(50),
permission_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tr_user_rep;
CREATE TABLE tr_user_rep(
id varchar(50) PRIMARY KEY,
user_id varchar(50),
real_estate_project_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
building_type varchar(100),
decoration_status varchar(20),
household_num int(8),
floor_area_ratio float(6,4),
green_rate float(6,4),
parking_space_num int(8),
property_years int(8),
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
average_price int(8),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
one_room_num int(8),
two_room_num int(8),
three_room_num int(8),
four_room_num int(8),
five_room_num int(8),
over_five_room_num int(8),
min_room_gross_floor_area int(8),
max_room_gross_floor_area int(8),
sale_status varchar(20),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_house_type;
CREATE TABLE tbl_house_type(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
real_estate_project_name varchar(50),
name varchar(50),
average_price int(8),
on_sale_date date,
inside_area float(8,2),
sale_status varchar(20),
gross_floor_area float(8,2),
room_num int(8),
hall_num int(8),
kitchen_num int(8),
toilet_num int(8),
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
region varchar(20),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_image;
CREATE TABLE tbl_rep_image(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
type varchar(20),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rep_avg_price_history;
CREATE TABLE tbl_rep_avg_price_history(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
value int(8),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_information;
CREATE TABLE tbl_information(
id varchar(50) PRIMARY KEY,
name varchar(50),
author varchar(50),
content varchar(6000),
summary varchar(200),
description varchar(200),
tags varchar(200),
type varchar(20),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tr_rep_info;
CREATE TABLE tr_rep_info(
id varchar(50) PRIMARY KEY,
real_estate_project_id varchar(50),
information_id varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_advertisement;
CREATE TABLE tbl_advertisement(
id varchar(50) PRIMARY KEY,
name varchar(50),
summary varchar(200),
pre_image_url varchar(200),
content_url varchar(200),
description varchar(200),
location varchar(20),
priority int(8),
active tinyint(1) default true,
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_name_list;
CREATE TABLE tbl_name_list(
id varchar(50) PRIMARY KEY,
name varchar(50),
phone_num varchar(50),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_residence_community;
CREATE TABLE tbl_residence_community(
id varchar(50) PRIMARY KEY,
name varchar(50),
address varchar(200),
on_sale_date date,
property_type varchar(20),
building_type varchar(100),
household_num int(8),
floor_area_ratio float(6,4),
green_rate float(6,4),
parking_space_num int(8),
property_years int(8),
property_company varchar(50),
property_fee float(6,2),
introduction varchar(2000),
surrounding varchar(2000),
traffic varchar(2000),
region varchar(20),
longitude float(10,6),
latitude float(10,6),
shh_average_price int(8),
shh_num int(8),
shh_one_room_num int(8),
shh_two_room_num int(8),
shh_three_room_num int(8),
shh_four_room_num int(8),
shh_five_room_num int(8),
shh_over_five_room_num int(8),
shh_min_room_gross_floor_area int(8),
shh_max_room_gross_floor_area int(8),
shh_min_total_price int(8),
shh_max_total_price int(8),
rh_average_price int(8),
rh_num int(8),
rh_one_room_num int(8),
rh_two_room_num int(8),
rh_three_room_num int(8),
rh_four_room_num int(8),
rh_five_room_num int(8),
rh_over_five_room_num int(8),
rh_min_room_gross_floor_area int(8),
rh_max_room_gross_floor_area int(8),
rh_min_rental int(8),
rh_max_rental int(8),
rh_entire_rent_num int(8),
rh_flat_share_num int(8),
tags varchar(200),
priority int(8),
active tinyint(1) default true,
description varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rc_image;
CREATE TABLE tbl_rc_image(
id varchar(50) PRIMARY KEY,
residence_community_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_second_hand_house;
CREATE TABLE tbl_second_hand_house(
id varchar(50) PRIMARY KEY,
residence_community_id varchar(50),
residence_community_name varchar(50),
name varchar(50),
region varchar(20),
phone_num varchar(20),
total_price int(8),
gross_floor_area float(8,2),
room_num int(8),
hall_num int(8),
toilet_num int(8),
forward varchar(20),
cur_floor int(8),
total_floor int(8),
decoration_status varchar(20),
address varchar(200),
introduction varchar(2000),
out_of_date tinyint(1) default false,
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shh_image;
CREATE TABLE tbl_shh_image(
id varchar(50) PRIMARY KEY,
second_hand_house_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shh_panorama;
CREATE TABLE tbl_shh_panorama(
id varchar(50) PRIMARY KEY,
second_hand_house_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rental_housing;
CREATE TABLE tbl_rental_housing(
id varchar(50) PRIMARY KEY,
residence_community_id varchar(50),
residence_community_name varchar(50),
name varchar(50),
region varchar(20),
phone_num varchar(20),
rental int(8),
gross_floor_area float(8,2),
room_num int(8),
hall_num int(8),
toilet_num int(8),
forward varchar(20),
cur_floor int(8),
total_floor int(8),
decoration_status varchar(20),
address varchar(200),
introduction varchar(2000),
facility_bed tinyint(1) default false,
facility_refrigerator tinyint(1) default false,
facility_broadband tinyint(1) default false,
facility_air_conditioner tinyint(1) default false,
facility_tv tinyint(1) default false,
facility_heating tinyint(1) default false,
facility_washer tinyint(1) default false,
facility_heater tinyint(1) default false,
lease_way varchar(20),
out_of_date tinyint(1) default false,
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rh_image;
CREATE TABLE tbl_rh_image(
id varchar(50) PRIMARY KEY,
rental_housing_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_rh_panorama;
CREATE TABLE tbl_rh_panorama(
id varchar(50) PRIMARY KEY,
rental_housing_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_assessment_item;
CREATE TABLE tbl_assessment_item(
id varchar(50) PRIMARY KEY,
name varchar(50),
type varchar(20),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_assessment_parameter;
CREATE TABLE tbl_assessment_parameter(
id varchar(50) PRIMARY KEY,
assessment_item_id varchar(50),
name varchar(50),
value int(8),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shh_floor_price_linear_function;
CREATE TABLE tbl_shh_floor_price_linear_function(
id varchar(50) PRIMARY KEY,
residence_community_id varchar(50),
param_a double(12,4),
param_b double(12,4),
forward varchar(20),
description varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shop;
CREATE TABLE tbl_shop(
id varchar(50) PRIMARY KEY,
name varchar(50),
region varchar(20),
phone_num varchar(20),
rental int(8),
total_price int(8),
gross_floor_area float(8,2),
decoration_status varchar(20),
address varchar(200),
introduction varchar(2000),
rent_or_sale varchar(20),
property_fee float(6,2),
type varchar(20),
out_of_date tinyint(1) default false,
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shop_image;
CREATE TABLE tbl_shop_image(
id varchar(50) PRIMARY KEY,
shop_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_shop_panorama;
CREATE TABLE tbl_shop_panorama(
id varchar(50) PRIMARY KEY,
shop_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_office_building;
CREATE TABLE tbl_office_building(
id varchar(50) PRIMARY KEY,
name varchar(50),
region varchar(20),
phone_num varchar(20),
rental int(8),
total_price int(8),
gross_floor_area float(8,2),
decoration_status varchar(20),
address varchar(200),
introduction varchar(2000),
rent_or_sale varchar(20),
property_fee float(6,2),
type varchar(20),
out_of_date tinyint(1) default false,
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_ob_image;
CREATE TABLE tbl_ob_image(
id varchar(50) PRIMARY KEY,
office_building_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_ob_panorama;
CREATE TABLE tbl_ob_panorama(
id varchar(50) PRIMARY KEY,
office_building_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_warehouse;
CREATE TABLE tbl_warehouse(
id varchar(50) PRIMARY KEY,
name varchar(50),
region varchar(20),
phone_num varchar(20),
rental int(8),
total_price int(8),
gross_floor_area float(8,2),
address varchar(200),
introduction varchar(2000),
rent_or_sale varchar(20),
type varchar(20),
out_of_date tinyint(1) default false,
description varchar(200),
tags varchar(200),
priority int(8),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_warehouse_image;
CREATE TABLE tbl_warehouse_image(
id varchar(50) PRIMARY KEY,
warehouse_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);

drop table if exists tbl_warehouse_panorama;
CREATE TABLE tbl_warehouse_panorama(
id varchar(50) PRIMARY KEY,
warehouse_id varchar(50),
name varchar(50),
description varchar(200),
content_url varchar(200),
pre_image_url varchar(200),
creater varchar(50),
last_editor varchar(50),
create_date timestamp DEFAULT CURRENT_TIMESTAMP,
update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
del_flag tinyint(1) default false
);


