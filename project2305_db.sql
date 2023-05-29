/*
 Navicat Premium Data Transfer

 Source Server         : CAI
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : project2305_db

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/05/2023 17:45:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cai_file
-- ----------------------------
DROP TABLE IF EXISTS `cai_file`;
CREATE TABLE `cai_file`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `is_delete` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除 1代表删除，0',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建这个用户的id',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cai_file
-- ----------------------------
INSERT INTO `cai_file` VALUES (1, 'cs123', '录取通知', 'http://localhost:8443/file/userPhoto/wj9wp7.txt', '0', '2023-05-17 16:21:48', '2023-05-17 16:22:39', '1', NULL);

-- ----------------------------
-- Table structure for cai_user
-- ----------------------------
DROP TABLE IF EXISTS `cai_user`;
CREATE TABLE `cai_user`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `is_delete` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除 1代表删除，0',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建这个用户的id',
  `update_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cai_user
-- ----------------------------
INSERT INTO `cai_user` VALUES (1, '20192108', '蔡振', 'http://localhost:8443/file/userPhoto/wj9wp7.jpg', '0', '2023-05-17 16:18:43', NULL, NULL, NULL);
INSERT INTO `cai_user` VALUES (2, '20192109', '库里', 'http://localhost:8443/file/userPhoto/wj9wp7.jpg', '0', '2023-05-17 16:20:51', '2023-05-17 16:21:02', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
