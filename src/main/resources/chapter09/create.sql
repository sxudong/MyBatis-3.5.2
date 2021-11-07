/*==============================================================*/
/* Table: t_user 用户表                                         */
/*==============================================================*/
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(60) NOT NULL COMMENT '用户名称',
  `cnname` varchar(60) NOT NULL COMMENT '姓名',
  `sex` tinyint(3) NOT NULL COMMENT '性别',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '电子邮件',
  `note` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES ('1', 'zhangsan', '张三', '0', '1325656546', 'lfkdsj@dlkjf.com', 'fdsafljdsakf');
INSERT INTO `t_user` VALUES ('2', 'zlkjklfdsa', '李四', '1', '1325656546', 'lfkdsj@dlkjf.com', 'fdsafljdsakf');
INSERT INTO `t_user` VALUES ('3', 'wangwuz', '王五', '2', '1265445', '211321@fdsaf.com', '2');


/*==============================================================*/
/* Table: t_student 学生表                                      */
/*==============================================================*/
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
    `student_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
    `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 1-女 2-男',
    `locked` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '锁',
    `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '孙悟空', '13155556666', '123547', '1', '1', '2021-11-04 21:46:56', '2021-11-06 21:47:02');
INSERT INTO `student` VALUES (2, '唐生', '13255557777', '652135', '1', '1', '2021-11-01 21:50:07', '2021-11-04 21:50:11');
INSERT INTO `student` VALUES (3, '八戒', '13566669999', '2351458', '1', '1', '2021-11-03 21:50:39', '2021-11-05 21:50:43');
