CREATE TABLE t_role (
  id          INT(11)     NOT NULL AUTO_INCREMENT,
  role_name   VARCHAR(60) NOT NULL,
  create_data DATETIME    NOT NULL DEFAULT current_timestamp, -- 去掉不要
  note        VARCHAR(512)         DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(60) DEFAULT NULL COMMENT '用户名称',
  `cnname` varchar(60) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',  -- 去掉不要
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '电子邮件',
  `note` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
);
