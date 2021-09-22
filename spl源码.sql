CREATE TABLE insurance_user(
id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
userId VARCHAR(255) DEFAULT NULL COMMENT '用户账号为邮箱或者手机号(自注册用户/管理员分配账号)',
userName VARCHAR(255) DEFAULT NULL COMMENT '用户真实姓名',
userPhone VARCHAR(255) DEFAULT NULL COMMENT '联系方式(手机号或邮箱号)',
idNumber VARCHAR(255) DEFAULT NULL COMMENT '用户身份证号',
userPassword VARCHAR(255) DEFAULT NULL COMMENT '用户密码',
userType INT(10) DEFAULT NULL COMMENT '用户类型(标识：0管理员 1自注册用户 2分配用户)',
creationTime DATETIME DEFAULT NULL COMMENT '创建时间',
creator VARCHAR(100) DEFAULT NULL COMMENT '创建人',
modifyDate VARCHAR(100) DEFAULT NULL COMMENT '修改时间',
modifiers VARCHAR(100) DEFAULT NULL COMMENT '修改人',
activated INT(1) DEFAULT NULL COMMENT '是否激活,(0false,1true,默认是0)',
PRIMARY KEY(id)
)COMMENT='用户表';