-- ----------------------------
-- Table structure for follows
-- ----------------------------
DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows`
(
    `id`       numeric      NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `uid`      varchar(255) NOT NULL COMMENT '用户uid',
    `platform` varchar(50)  NOT NULL COMMENT '仓库平台',
    `owner`    varchar(50)  NOT NULL COMMENT '仓库作者',
    `name`     varchar(50)  NOT NULL COMMENT '仓库名称',
    PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `uid`      varchar(255) NOT NULL COMMENT '用户uid',
    `telegram` varchar(50)  NOT NULL COMMENT 'Telegram',
    `isadmin`  bool DEFAULT 0 COMMENT '用户类型',
    PRIMARY KEY (`uid`) USING BTREE
);

-- ----------------------------
-- Table structure for repos
-- ----------------------------
DROP TABLE IF EXISTS `repos`;
CREATE TABLE `repos`
(
    `id`       numeric     NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `platform` varchar(50) NOT NULL COMMENT '仓库平台',
    `owner`    varchar(50) NOT NULL COMMENT '仓库作者',
    `name`     varchar(50) NOT NULL COMMENT '仓库名称',
    PRIMARY KEY (`id`)
);