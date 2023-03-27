/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : scheduling

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 27/03/2023 21:30:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_job
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_job`;
CREATE TABLE `t_sys_job`  (
  `job_id` int(0) NOT NULL AUTO_INCREMENT,
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `update_time` date NULL DEFAULT NULL,
  `job_status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_job
-- ----------------------------
INSERT INTO `t_sys_job` VALUES (3, 'schedulingTaskDemo', '2023-03-27', '10 * * * * ?', 'taskWithParams', 'marico', '每十秒执行一次\n', 1, '2023-03-27', NULL);
INSERT INTO `t_sys_job` VALUES (13, 'schedulingTaskDemo', '2023-03-27', '2 * * * * ?', 'taskWithParams', 'marico', 'test作业', 1, '2023-03-27', NULL);

SET FOREIGN_KEY_CHECKS = 1;
