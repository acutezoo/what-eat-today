CREATE TABLE `eat_classification`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     varchar(256) NOT NULL COMMENT '登录人id',
    `name`        varchar(32)  NOT NULL COMMENT '名称',
    `remark`      varchar(256)          DEFAULT NULL COMMENT '备注',
    `delete_id`   int(11)      NOT NULL DEFAULT '0' COMMENT '逻辑删除id',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `modify_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间(数据库自动维护)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='分类';

CREATE TABLE `eat_food`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     varchar(256) NOT NULL COMMENT '登录人id',
    `classify_id` int(11)      NOT NULL DEFAULT 0 COMMENT '分类id;0=默认',
    `food_name`   varchar(64)  NOT NULL COMMENT '食物名称',
    `remark`      varchar(256)          DEFAULT NULL COMMENT '备注',
    `img_url`     varchar(256)          DEFAULT NULL COMMENT '图片地址',
    `delete_id`   int(11)      NOT NULL DEFAULT '0' COMMENT '逻辑删除id',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
    `modify_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间(数据库自动维护)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='食物';
