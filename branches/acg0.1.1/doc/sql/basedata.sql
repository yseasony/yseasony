-- ----------------------------
-- Records of acg_authority
-- ----------------------------
INSERT INTO `acg_authority` VALUES ('1', 'authPage', '浏览权限', 'AUTH_AUTHPAGE');
INSERT INTO `acg_authority` VALUES ('2', 'rolePage', '角色浏览', 'AUTH_ROLEPAGE');
INSERT INTO `acg_authority` VALUES ('3', 'authSave', '保存权限', 'AUTH_AUTHSAVE');
INSERT INTO `acg_authority` VALUES ('4', 'authUpdate', '修改权限', 'AUTH_AUTHUPDATE');
INSERT INTO `acg_authority` VALUES ('5', 'authDelete', '删除权限', 'AUTH_AUTHDELETE');
INSERT INTO `acg_authority` VALUES ('6', 'roleSave', '保存角色', 'AUTH_ROLESAVE');
INSERT INTO `acg_authority` VALUES ('7', 'roleUpdate', '修改角色', 'AUTH_ROLEUPDATE');
INSERT INTO `acg_authority` VALUES ('8', 'roleDelete', '删除角色', 'AUTH_ROLEDELETE');
INSERT INTO `acg_authority` VALUES ('9', 'userPage', '后台用户浏览', 'USER_USERPAGE');
INSERT INTO `acg_authority` VALUES ('10', 'userDelete', '后台用户删除', 'USER_USERDELETE');
INSERT INTO `acg_authority` VALUES ('11', 'userUpdate', '后台用户修改', 'USER_USERUPDATE');
INSERT INTO `acg_authority` VALUES ('12', 'userSave', '后台用户添加', 'USER_USERSAVE');

-- ----------------------------
-- Records of acg_role
-- ----------------------------
INSERT INTO `acg_role` VALUES ('1', '超级管理员');
INSERT INTO `acg_role` VALUES ('2', '管理员');

-- ----------------------------
-- Records of acg_role_authority
-- ----------------------------
INSERT INTO `acg_role_authority` VALUES ('1', '1');
INSERT INTO `acg_role_authority` VALUES ('1', '2');
INSERT INTO `acg_role_authority` VALUES ('1', '3');
INSERT INTO `acg_role_authority` VALUES ('1', '4');
INSERT INTO `acg_role_authority` VALUES ('1', '5');
INSERT INTO `acg_role_authority` VALUES ('1', '6');
INSERT INTO `acg_role_authority` VALUES ('1', '7');
INSERT INTO `acg_role_authority` VALUES ('1', '8');
INSERT INTO `acg_role_authority` VALUES ('1', '9');
INSERT INTO `acg_role_authority` VALUES ('1', '10');
INSERT INTO `acg_role_authority` VALUES ('1', '11');
INSERT INTO `acg_role_authority` VALUES ('1', '12');

-- ----------------------------
-- Records of acg_user
-- ----------------------------
INSERT INTO `acg_user` VALUES ('10', 'yseasony', '123456', null, 'qwewqewq11', '', null);



