drop table if exists sys_setting;
create table sys_setting (
    id bigint not null auto_increment,
    group_name varchar(255) not null default '',
    param_name varchar(255) not null default '',
    param_label varchar(512) not null default '',
    param_val varchar(512) not null default '',
    param_type varchar(128) not null default 'java.lang.Integer',
    sort int not null default 0,
    descr varchar(512) not null default '',
    enable int not null default 1,
    primary key (id)
) engine=InnoDB default charset=utf8
;

drop table if exists sys_user;
create table sys_user (
    id bigint not null auto_increment,
    user_name varchar(255) not null default '',
    real_name varchar(512) not null default '',
    user_password varchar(512) not null default '',
    city_id int not null default 590,
    salt varchar(512) not null default '',
    introduction varchar(512) not null default '',
    avatar varchar(512) not null default '/imgs/user.png',
    register_time timestamp(0) NULL DEFAULT current_timestamp,
    department varchar(255) not null default '未分配',
    department_id bigint default null,
    primary key (id)
) engine=InnoDB default charset=utf8
;

drop table if exists sys_role;
create table sys_role (
    id bigint not null auto_increment,
    name varchar(255) not null default '',
    cn_name varchar(255) not null default '',
    descr varchar(512) not null default '',
    primary key (id)
) engine=InnoDB default charset=utf8
;

drop table if exists sys_permission;
create table sys_permission (
    id bigint not null auto_increment,
    name varchar(255) not null default '',
    cn_name varchar(255) not null default '',
    descr varchar(512) not null default '',
    primary key (id)
) engine=InnoDB default charset=utf8
;

drop table if exists sys_user_role;
create table sys_user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
) engine = InnoDB default charset = utf8
;

drop table if exists sys_role_permission;
create table sys_role_permission (
    role_id bigint not null,
    permission_id bigint not null,
    primary key (role_id, permission_id)
) engine = InnoDB default charset = utf8
;

drop table if exists sys_department;
create table sys_department (
    id bigint not null auto_increment,
    name varchar(255) not null,
    leaf_flag int default 0,
    parent_name varchar(255) not null default '',
    parent_path varchar(512) not null default '/',
    department_type int not null default 1,
    sort int default 0,
    enable int default 1,
    level int default 1,
    primary key (id)
) engine = InnoDB default charset = utf8
;

drop table if exists ui_tb_column_meta;
create table ui_tb_column_meta (
   id bigint not null auto_increment,
   column_name varchar(255) default '',
   column_label varchar(255) default '',
   field_name varchar(255) default '',
   module_name varchar(255) default '',
   group_name varchar(255) default '',
   group_label varchar(255) default '',
   header_align varchar(255) default 'center',
   align varchar(255) default 'center',
   width int default 0,
   min_width int default 0,
   fixed int default 0,
   show_overflow_tooltip int default 1,
   enable int default 1,
   sort int default 1,
   formatter varchar(255) default null,
   scope_flag int default 3,
   form_ele_type varchar(255) default 'text',
   form_rule varchar(255) default 'defRule',
   extra varchar(255) default '',
   readonly_flag int default 0,
   unit varchar(255) default '',
   primary key (id)
) engine = InnoDB default charset = utf8
;
