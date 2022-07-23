SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for follows
-- ----------------------------
DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `uid`      varchar(255) NOT NULL COMMENT '用户uid',
    `platform` varchar(50)  NOT NULL COMMENT '仓库平台',
    `owner`    varchar(50)  NOT NULL COMMENT '仓库作者',
    `repo`     varchar(50)  NOT NULL COMMENT '仓库名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `uid`     varchar(255) NOT NULL COMMENT '用户 Uid',
    `tg`    varchar(50) DEFAULT NULL COMMENT 'Telegram Id',
    `isadmin` bool         DEFAULT 0 COMMENT '用户类型',
    PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for repos
-- ----------------------------
DROP TABLE IF EXISTS `repos`;
CREATE TABLE `repos`
(
    `id`       int         NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `platform` varchar(50) NOT NULL COMMENT '仓库平台',
    `owner`    varchar(50) NOT NULL COMMENT '仓库作者',
    `repo`     varchar(50) NOT NULL COMMENT '仓库名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;