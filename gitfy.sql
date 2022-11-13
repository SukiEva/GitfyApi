-- ----------------------------
-- table structure for follows
-- ----------------------------
drop table if exists follows;
create table follows
(
    id       int          not null auto_increment comment '自增id',
    uid      varchar(255) not null comment '用户uid',
    platform varchar(100) not null comment '仓库平台',
    owner    varchar(100) not null comment '仓库作者',
    name     varchar(100) not null comment '仓库名称',
    primary key (id)
);


-- ----------------------------
-- table structure for users
-- ----------------------------
drop table if exists users;
create table users
(
    uid      varchar(255) not null comment '用户uid',
    name     varchar(100) not null comment '用户名',
    password varchar(100) not null comment '用户密码',
    telegram varchar(100) not null comment 'telegram',
    isadmin  bool default 0 not null comment '用户类型',
    primary key (uid) using btree,
    unique (name)
);

-- ----------------------------
-- table structure for repos
-- ----------------------------
drop table if exists repos;
create table repos
(
    id       int          not null auto_increment comment '自增id',
    platform varchar(100) not null comment '仓库平台',
    owner    varchar(100) not null comment '仓库作者',
    name     varchar(100) not null comment '仓库名称',
    primary key (id)
);