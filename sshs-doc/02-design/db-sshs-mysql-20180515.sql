/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.20-log : Database - sshs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sshs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

/*Table structure for table `aaaa` */

DROP TABLE IF EXISTS `aaaa`;

CREATE TABLE `aaaa` (
  `w` date DEFAULT NULL,
  `s` datetime DEFAULT NULL,
  `d` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `aaaa` */

/*Table structure for table `core_customise_query` */

DROP TABLE IF EXISTS `core_customise_query`;

CREATE TABLE `core_customise_query` (
  `CUSTOMISE_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '查询ID',
  `USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构编码',
  `PAGE_ID` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面ID',
  `CUSTOMISE_NAME` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '查询名称',
  `FIELD_CONTENTS` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '条件列',
  `FIELD_ADDONS` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '条件匹配方式',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`CUSTOMISE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='核心框架-自定义查询信息表';

/*Data for the table `core_customise_query` */

/*Table structure for table `sys_authorize` */

DROP TABLE IF EXISTS `sys_authorize`;

CREATE TABLE `sys_authorize` (
  `AUTHORIZE_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限ID',
  `RESOURCE_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源ID(包括菜单ID和按钮ID)',
  `RESOURCE_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `ROLE_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色编号',
  `RESOURCE_TYPE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源类型:01-菜单,02-按钮',
  `AUTHORIZE_TYPE` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权类型:01-操作,02-授权,03-全部',
  `DATA_AUTH_TYPE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限级别，对应机构级别（RESOURCE_TYPE为01且AUTHORIZE_TYPE为01或03时该字段可用）',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`AUTHORIZE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-角色权限表';

/*Data for the table `sys_authorize` */

insert  into `sys_authorize`(`AUTHORIZE_ID`,`RESOURCE_ID`,`RESOURCE_NAME`,`ROLE_CODE`,`RESOURCE_TYPE`,`AUTHORIZE_TYPE`,`DATA_AUTH_TYPE`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('1001','01','辅助开发','1001','01','01','',NULL,NULL,'2018-05-10 16:51:27',NULL,NULL,'0000-00-00 00:00:00'),('100101','0101','代码生成','1001','01','01','01',NULL,NULL,'2018-05-10 16:52:36',NULL,NULL,'0000-00-00 00:00:00'),('1002','02','系统管理','1001','01','01',NULL,NULL,NULL,'2018-05-11 15:48:02',NULL,NULL,'0000-00-00 00:00:00'),('100201','0201','菜单管理','1001','01','01','01',NULL,NULL,'2018-05-11 15:48:30',NULL,NULL,'0000-00-00 00:00:00');

/*Table structure for table `sys_dictionaries` */

DROP TABLE IF EXISTS `sys_dictionaries`;

CREATE TABLE `sys_dictionaries` (
  `DICT_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID',
  `PARENT_ID` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级ID',
  `DICT_TYPE` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型:0-分类,1-字典项,2-字典子项,3-字典码值',
  `DICT_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码',
  `DICT_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `DICT_DESC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `STATUS` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态1:有效0:无效',
  `SORT_NO` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `IS_SYSTEM` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否系统类1:系统类0:业务类',
  `MODULE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块',
  `LEGAL_ORG` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人行社',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`DICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-数据字典表';

/*Data for the table `sys_dictionaries` */

insert  into `sys_dictionaries`(`DICT_ID`,`PARENT_ID`,`DICT_TYPE`,`DICT_CODE`,`DICT_NAME`,`DICT_DESC`,`STATUS`,`SORT_NO`,`IS_SYSTEM`,`MODULE`,`LEGAL_ORG`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('10001','0','0','1','通用字典项目','通用字典项目','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('1000101','10001','1','YN','是否','是否','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('100010101','1000101','3','1','是','是','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('100010201','1000101','3','0','否','否','1','2','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48');

/*Table structure for table `sys_dictionaries_i18n` */

DROP TABLE IF EXISTS `sys_dictionaries_i18n`;

CREATE TABLE `sys_dictionaries_i18n` (
  `I18N_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'I18N_ID',
  `DICT_ID` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ID',
  `DICT_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码',
  `LANGUAGE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '语言',
  `COUNTRY` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国家',
  `DICT_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `DICT_DESC` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `STATUS` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态1:有效0:无效',
  `LEGAL_ORG` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人行社',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`I18N_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-数据字典国际化表';

/*Data for the table `sys_dictionaries_i18n` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `MENU_CODE` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单编码',
  `MENU_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `MENU_TYPE` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单类型:0-节点，1-功能',
  `MENU_URL` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单路径URL',
  `PARENT_MENU_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属父菜单',
  `MENU_MODULE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属模块',
  `IS_RELATIVE_PATH` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否相对路径',
  `MENU_LEVEL` decimal(5,0) DEFAULT NULL COMMENT '菜单层次',
  `MENU_SEQ` decimal(5,0) DEFAULT NULL COMMENT '同级菜单顺序号',
  `MENU_ICON` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单图标路径/或样式',
  `MENU_ICONA` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单激活图标路径/或样式',
  `IS_VISIBLE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否可视:1-是,0-否',
  `OPEN_MODE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主窗口打开、弹出窗口打开',
  `MENU_TIP` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提示信息',
  `IS_START` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否启用:1-是,0-否',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`MENU_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`MENU_CODE`,`MENU_NAME`,`MENU_TYPE`,`MENU_URL`,`PARENT_MENU_CODE`,`MENU_MODULE`,`IS_RELATIVE_PATH`,`MENU_LEVEL`,`MENU_SEQ`,`MENU_ICON`,`MENU_ICONA`,`IS_VISIBLE`,`OPEN_MODE`,`MENU_TIP`,`IS_START`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('01','开发辅助','01',NULL,NULL,NULL,NULL,'1','1','fa fa-linode',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-11 14:48:07',NULL,NULL,NULL),('0101','代码生成','02','toolkit/coder/mainList.w','01',NULL,NULL,'2','1','fa fa-plus-square',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-11 16:01:46',NULL,NULL,NULL),('02','系统管理','01',NULL,NULL,NULL,NULL,'1','2','fa fa-desktop',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-11 16:01:03',NULL,NULL,NULL),('0201','菜单管理','02','system/menu/menuList.w','02',NULL,NULL,'2','1','fa fa-reorder',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-11 16:01:41',NULL,NULL,NULL);

/*Table structure for table `sys_menu_i18n` */

DROP TABLE IF EXISTS `sys_menu_i18n`;

CREATE TABLE `sys_menu_i18n` (
  `I18N_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'I18N_ID',
  `MENU_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单编码',
  `LANGUAGE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '语言',
  `COUNTRY` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国家',
  `MENU_LABEL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单显示（中文）',
  `MENU_TIP` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提示信息',
  `IS_START` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否启用:01--是,02--否',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`I18N_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-菜单国际化表';

/*Data for the table `sys_menu_i18n` */

/*Table structure for table `sys_org` */

DROP TABLE IF EXISTS `sys_org`;

CREATE TABLE `sys_org` (
  `ORG_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构ID',
  `ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构编码',
  `ORG_NAME` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构名称',
  `ORG_BRIEF` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构简称',
  `PARENT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '直接上级机构ID',
  `ORG_CATEGORY` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构类别:01--机构,02--部门',
  `ORG_LEVEL` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构层级（树形结构层级）',
  `ORG_GRADE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构级别：总行，分行，支行等',
  `PATH_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '中文路径：用‘.’隔开各层级路径名称:.a.b.c.',
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态：01--启用,02--停用',
  `COUNTRY` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国家或地区:中国、中国香港、美国等,字典表数据',
  `EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系邮箱',
  `OFFICE_ADDR` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '办公地址',
  `POST` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮政编码',
  `TEL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `CONTACT` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
  `ORG_FI_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '金融机构编码（人行）',
  `ORG_PAY_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付行号（人行）',
  `ORG_CREDIT_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构信用代码（人行）',
  `ORG_PERMIT_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '金融许可证号码（银监）',
  `ORG_REG_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业执照注册号（工商）',
  `ORG_ID_NUMBER` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组织机构代码证号码',
  `ORG_TAX_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '税务登记证号码',
  `ORG_CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成立日期',
  `ORG_START_DATE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业开始时间',
  `ORG_END_DATE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业终止时间',
  `ORG_HEADER` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构负责人',
  `NODE_NO` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '网点号',
  `HIGHER_MNG_ORG` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代管机构：01,02,03',
  `IS_LEAF` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否最末端',
  `BUSI_STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业状态',
  `AREA_CODE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '行政区划代码',
  `ORDERS` decimal(5,0) DEFAULT NULL COMMENT '顺序号',
  `OWN_LINE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属条线',
  `VER_NO` decimal(5,0) DEFAULT NULL COMMENT '版本号',
  `APPLY_STATUS` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审批状态',
  `OPER_TYPE` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作类型：合并，撤销，转移，修改',
  `OPER_RELA_ID` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联主键：合并，撤销，转移，修改的关联ID',
  `APPLY_EMP_NO` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请人',
  `APPLY_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请日期',
  `APPLY_REASON` varchar(4000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请原因',
  `PROCESS_INSTANCE_ID` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审批流ID',
  `REMARK` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-机构表';

/*Data for the table `sys_org` */

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `POST_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位主键',
  `POST_NO` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编号',
  `POST_NAME` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
  `POST_SERIAL` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位序列:管理序列、专业序列、营销序列、技能序列',
  `POST_SERIAL_CHILD` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位子序列',
  `POST_DESC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位描述',
  `STAND_ORG_CODE` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属标准部门',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-岗位表';

/*Data for the table `sys_post` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_CODE` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `ROLE_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `REMARK` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `ROLE_LEVEL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色级别:对应机构级别',
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态:01--启用，02--停用',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`ROLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_CODE`,`ROLE_NAME`,`REMARK`,`ROLE_LEVEL`,`STATUS`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('1001','测试角色',NULL,'1','1',NULL,NULL,'2018-05-10 16:49:19',NULL,NULL,'0000-00-00 00:00:00');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编号',
  `USER_NAME` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `USER_NAME_EN` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '英文名',
  `USER_NAME_PY` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拼音码',
  `USER_NAME_FR` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '曾用名',
  `ORG_CODE` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构编号:到具体的部门、支行和二级中心',
  `POST_NO` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位',
  `USER_SEX` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别:1－男性，2－女性，9－未说明性别',
  `NATIONALITY` varchar(3) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国籍',
  `ETHNIC` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '民族:01=汉族',
  `NATIVE_PLACE` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '籍贯',
  `BIRTH_PLACE` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '出生地',
  `BIRTH_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  `ID_DECIMAL` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `PHOTO_ID` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '证件照',
  `JOIN_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '入职日期',
  `STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户状态:0-无效，1-有效',
  `TELLER_ID` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '柜员编号',
  `RESIDENTIAL_ADDRESS` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '现居住地址',
  `MOBILE_PHONE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `REMARK` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `PASSWORD` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `SALT` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐（密码）',
  `ONLINE_STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在线状态:在线、离线、锁定',
  `IP_ADDR` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  `USER_THEME` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户皮肤',
  `PD_COUNT` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码输入次数',
  `PD_MODTIME` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码修改时间',
  `PD_LOCKTIME` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码锁定时间',
  `LAST_SIGNON_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后一次登陆时间',
  `LAST_SIGNOUT_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后一次退出时间',
  `LEGAL_ORG` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人机构编号',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-用户基本信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_ID`,`USER_CODE`,`USER_NAME`,`USER_NAME_EN`,`USER_NAME_PY`,`USER_NAME_FR`,`ORG_CODE`,`POST_NO`,`USER_SEX`,`NATIONALITY`,`ETHNIC`,`NATIVE_PLACE`,`BIRTH_PLACE`,`BIRTH_DATE`,`ID_DECIMAL`,`PHOTO_ID`,`JOIN_DATE`,`STATUS`,`TELLER_ID`,`RESIDENTIAL_ADDRESS`,`MOBILE_PHONE`,`REMARK`,`PASSWORD`,`SALT`,`ONLINE_STATUS`,`IP_ADDR`,`USER_THEME`,`PD_COUNT`,`PD_MODTIME`,`PD_LOCKTIME`,`LAST_SIGNON_TIME`,`LAST_SIGNOUT_TIME`,`LEGAL_ORG`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('admin','admin','系统管理员','administrator','administrator',NULL,'0001',NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-27 17:01:16',NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,NULL,NULL,NULL,'$2a$10$Adv2Bz4PzCL5.kYUK6Wx8.YmN4BGIShRhD/T18Xhcmeidr9D3NqjC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',NULL,NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,'0000-00-00 00:00:00');

/*Table structure for table `sys_user_ext` */

DROP TABLE IF EXISTS `sys_user_ext`;

CREATE TABLE `sys_user_ext` (
  `USER_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `POLITICAL_STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '政治面貌:团员、党员、其他党派人士、无党派',
  `PARTY_TIME` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '入党时间',
  `MARITAL_STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '婚姻状况:10-未婚；20-已婚；21-离婚；40-丧偶；',
  `BLOOD_TYPE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '血型:A型、B型、AB型、O型、未知血型',
  `ENGLISH_LEVEL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '英语等级',
  `COMPUTER_LEVEL` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '计算机等级',
  `WORK_TIME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '参加工作年月',
  `INDUCTION_TYPE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '入职类型:社会招录、资源性人才引进、系统内调入、系统外调入',
  `EMPLOY_WAY` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用工方式:合同制员工\n派遣制员工\n实习员工',
  `EMP_STATUS` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '员工状态:在职在岗\n在编不在岗的\n离岗退养\n转非\n退休\n死亡',
  `JOIN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入职日期',
  `HEIGHT` decimal(5,2) DEFAULT NULL COMMENT '身高',
  `WEIGHT` decimal(5,2) DEFAULT NULL COMMENT '体重',
  `CHILD_NUM` decimal(2,0) DEFAULT NULL COMMENT '子女人数',
  `HEALTH_STATE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '健康状况:健康\n一般\n慢性疾病\n生理缺陷\n残废\n较弱',
  `HIGHEST_EDU` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最高学历:中专、高中、大专、专科、本科、研究生（硕士）、研究生（博士）',
  `HIGHEST_DEGREE` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最高学位:0-其他；1-名誉博士；\n2-博士；\n3-硕士；\n4-学士；\n9-未知。',
  `CONTACT_ADDRESS` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通讯地址',
  `POSTCODE_CONTACT` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通讯地址邮编',
  `RESIDENTIAL_ADDRESS` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '现居住地址',
  `POSTCODE_RESIDENTIAL` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '现居住地邮编',
  `DOMICILE_ADDRESS` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '户籍所在地',
  `POSTCODE_DOMICILE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '户籍所在地邮编',
  `HOUSEHOLD_REG` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '户籍类型:城镇,非城镇',
  `EMAIL` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `MOBILE_PHONE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `HOME_TELPHONE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '家庭电话',
  `EMERGENCY_CONTACT` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '紧急联系人',
  `EMERGENCY_CONTACT_PHONE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '紧急联系人手机',
  `QQ` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'QQ号',
  `WECHAT` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信号',
  `REMARK` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `LEGAL_ORG` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '法人机构编号',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-用户扩展信息表';

/*Data for the table `sys_user_ext` */

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `USER_ROLE_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID',
  `USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户编号',
  `ROLE_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色编号',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`USER_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统管理-用户角色对照表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`USER_ROLE_ID`,`USER_CODE`,`ROLE_CODE`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('admin1001','admin','1001',NULL,NULL,'2018-05-10 16:49:50',NULL,NULL,'0000-00-00 00:00:00');

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='测试';

/*Data for the table `test` */

/*Table structure for table `too_coder` */

DROP TABLE IF EXISTS `too_coder`;

CREATE TABLE `too_coder` (
  `CODER_ID` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '代码生成ID',
  `TITLE` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `MODEL_NAME` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块名',
  `MODEL_NAME_CN` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块名(中文)',
  `PACKAGE_NAME` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '包名',
  `CLASS_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类名',
  `FUNCTION_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '功能名称',
  `TABLE_NAME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `TABLE_COMMENT` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表描述',
  `FIELDS` blob COMMENT '字段列表',
  `CODER_TYPE` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型(单表，树形，主从表)',
  `CRT_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`CODER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='开发辅助-代码生成表';

/*Data for the table `too_coder` */

insert  into `too_coder`(`CODER_ID`,`TITLE`,`MODEL_NAME`,`MODEL_NAME_CN`,`PACKAGE_NAME`,`CLASS_NAME`,`FUNCTION_NAME`,`TABLE_NAME`,`TABLE_COMMENT`,`FIELDS`,`CODER_TYPE`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('f084d60e54024f08922b327d48d26010','系统管理->系统管理-机构表','system','系统管理','com.sshs.system.org','Org','org','sys_org','系统管理-机构表','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\01w\0\0\01sr\0#com.sshs.toolkit.coder.model.Column\0\0\0\0\0\0\0\0\ZL\0\naddDefaultt\0Ljava/lang/String;L\0addFlagq\0~\0L\0\rcolumnCommentq\0~\0L\0columnIdq\0~\0L\0\ncolumnNameq\0~\0L\0columnScalet\0Ljava/lang/Integer;L\0\ncolumnSizeq\0~\0L\0\ncolumnTypeq\0~\0L\0componentType1q\0~\0L\0	dataRangeq\0~\0L\0defaultValueq\0~\0L\0dictCodeq\0~\0L\0listFlagq\0~\0L\0\nmodifyFlagq\0~\0L\0modifyFlag1q\0~\0L\0primaryKeyFlagq\0~\0L\0propFuncNameq\0~\0L\0propertyNameq\0~\0L\0propertyTypeq\0~\0L\0remarksq\0~\0L\0requiredFlagq\0~\0L\0\nsearchFlagq\0~\0L\0\nsearchTypeq\0~\0L\0tableCommentq\0~\0L\0	tableNameq\0~\0L\0\nvalidatorsq\0~\0xppt\0truet\0机构IDt\01t\0ORG_IDpsr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number������\0\0xp\0\0\0 t\0varcharppt\0\0q\0~\0q\0~\0q\0~\0pt\01pt\0orgIdt\0Stringpq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0状态：01--启用,02--停用t\010t\0STATUSpsq\0~\0\n\0\0\0\nt\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0statusq\0~\0pt\0falseq\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0A国家或地区:中国、中国香港、美国等,字典表数据t\011t\0COUNTRYpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0countryq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0联系邮箱t\012t\0EMAILpsq\0~\0\n\0\0\0dt\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0emailq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0办公地址t\013t\0OFFICE_ADDRpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nofficeAddrq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0邮政编码t\014t\0POSTpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0postq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0联系电话t\015t\0TELpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0telq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	联系人t\016t\0CONTACTpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0contactq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0金融机构编码（人行）t\017t\0	ORG_FI_NOpsq\0~\0\n\0\0\0<t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgFiNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0支付行号（人行）t\018t\0\nORG_PAY_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgPayNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构信用代码（人行）t\019t\0\rORG_CREDIT_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCreditNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构编码t\02t\0ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0!金融许可证号码（银监）t\020t\0\rORG_PERMIT_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgPermitNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0!营业执照注册号（工商）t\021t\0\nORG_REG_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgRegNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0组织机构代码证号码t\022t\0\rORG_ID_NUMBERpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgIdNumberq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0税务登记证号码t\023t\0\nORG_TAX_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgTaxNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0成立日期t\024t\0ORG_CREATE_DATEpsq\0~\0\n\0\0\0\0t\0	timestampppt\0CURRENT_TIMESTAMPq\0~\0q\0~\0q\0~\0pt\00pt\0\rorgCreateDatet\0Datepq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业开始时间t\025t\0ORG_START_DATEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgStartDateq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业终止时间t\026t\0ORG_END_DATEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\norgEndDateq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构负责人t\027t\0\nORG_HEADERpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0	orgHeaderq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	网点号t\028t\0NODE_NOpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0nodeNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0代管机构：01,02,03t\029t\0HIGHER_MNG_ORGpsq\0~\0\n\0\0�t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0higherMngOrgq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构名称t\03t\0ORG_NAMEpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgNameq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0是否最末端t\030t\0IS_LEAFpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0isLeafq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业状态t\031t\0BUSI_STATUSpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nbusiStatusq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0行政区划代码t\032t\0	AREA_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0areaCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	顺序号t\033t\0ORDERSq\0~\0�sq\0~\0\n\0\0\0t\0decimalppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orderst\0\nBigDecimalpq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0所属条线t\034t\0OWN_LINEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0ownLineq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	版本号t\035t\0VER_NOq\0~\0�q\0~t\0decimalppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0verNoq\0~pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0审批状态t\036t\0APPLY_STATUSpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0applyStatusq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\00操作类型：合并，撤销，转移，修改t\037t\0	OPER_TYPEpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0operTypeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0;关联主键：合并，撤销，转移，修改的关联IDt\038t\0OPER_RELA_IDpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\noperRelaIdq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	申请人t\039t\0APPLY_EMP_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\napplyEmpNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构简称t\04t\0	ORG_BRIEFpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgBriefq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0申请日期t\040t\0\nAPPLY_DATEpq\0~\0�t\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0	applyDateq\0~\0�pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0申请原因t\041t\0APPLY_REASONpsq\0~\0\n\0\0�t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0applyReasonq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0审批流IDt\042t\0PROCESS_INSTANCE_IDpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0processInstanceIdq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0备注t\043t\0REMARKpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0remarkq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	创建人t\044t\0\rCRT_USER_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0crtUserCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0创建机构t\045t\0CRT_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\ncrtOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0创建日期t\046t\0CRT_DATEpq\0~\0�t\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0crtDateq\0~\0�pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	修改人t\047t\0\rUPD_USER_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0updUserCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0修改机构t\048t\0UPD_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nupdOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0修改日期t\049t\0UPD_DATEpq\0~\0�t\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0updDateq\0~\0�pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0直接上级机构IDt\05t\0PARENT_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\rparentOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0\"机构类别:01--机构,02--部门t\06t\0ORG_CATEGORYpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCategoryq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0$机构层级（树形结构层级）t\07t\0	ORG_LEVELpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgLevelq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0*机构级别：总行，分行，支行等t\08t\0	ORG_GRADEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgGradeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0<中文路径：用‘.’隔开各层级路径名称:.a.b.c.t\09t\0	PATH_NAMEpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0pathNameq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpx',NULL,NULL,NULL,'2018-05-15 11:06:32',NULL,NULL,'2018-05-15 11:06:32');

/* Function  structure for function  `LIKED` */

/*!50003 DROP FUNCTION IF EXISTS `LIKED` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `LIKED`(I_TEXT VARCHAR(255)) RETURNS varchar(257) CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci
BEGIN
	RETURN CONCAT('%',I_TEXT,'%');
    END */$$
DELIMITER ;

/*Table structure for table `v_too_tables` */

DROP TABLE IF EXISTS `v_too_tables`;

/*!50001 DROP VIEW IF EXISTS `v_too_tables` */;
/*!50001 DROP TABLE IF EXISTS `v_too_tables` */;

/*!50001 CREATE TABLE  `v_too_tables`(
 `TABLE_NAME` varchar(64) ,
 `TABLE_COMMENT` varchar(2048) ,
 `COLUMN_ID` varchar(21) ,
 `COLUMN_NAME` varchar(64) ,
 `COLUMN_COMMENT` varchar(1024) ,
 `COLUMN_TYPE` varchar(64) ,
 `COLUMN_SIZE` bigint(21) unsigned ,
 `COLUMN_SCALE` bigint(21) unsigned ,
 `DEFAULT_VALUE` longtext ,
 `PRIMARY_KEY_FLAG` varchar(1) ,
 `REQUIRED_FLAG` varchar(3) 
)*/;

/*View structure for view v_too_tables */

/*!50001 DROP TABLE IF EXISTS `v_too_tables` */;
/*!50001 DROP VIEW IF EXISTS `v_too_tables` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_too_tables` AS (select `t`.`TABLE_NAME` AS `TABLE_NAME`,`t`.`TABLE_COMMENT` AS `TABLE_COMMENT`,cast(`c`.`ORDINAL_POSITION` as char charset utf8) AS `COLUMN_ID`,`c`.`COLUMN_NAME` AS `COLUMN_NAME`,`c`.`COLUMN_COMMENT` AS `COLUMN_COMMENT`,`c`.`DATA_TYPE` AS `COLUMN_TYPE`,(case when (`c`.`DATA_TYPE` = 'decimal') then `c`.`NUMERIC_PRECISION` when (`c`.`DATA_TYPE` like '%time%') then `c`.`DATETIME_PRECISION` else `c`.`CHARACTER_MAXIMUM_LENGTH` end) AS `COLUMN_SIZE`,`c`.`NUMERIC_SCALE` AS `COLUMN_SCALE`,`c`.`COLUMN_DEFAULT` AS `DEFAULT_VALUE`,(case when (`k`.`CONSTRAINT_NAME` = 'PRIMARY') then '1' else '0' end) AS `PRIMARY_KEY_FLAG`,(case when (`c`.`IS_NULLABLE` = 'NO') then 'on' else 'off' end) AS `REQUIRED_FLAG` from (`information_schema`.`tables` `t` join (`information_schema`.`columns` `c` left join `information_schema`.`key_column_usage` `k` on(((`c`.`TABLE_NAME` = `k`.`TABLE_NAME`) and (`c`.`COLUMN_NAME` = `k`.`COLUMN_NAME`))))) where ((`t`.`TABLE_NAME` = `c`.`TABLE_NAME`) and (`t`.`TABLE_SCHEMA` = 'sshs') and (`t`.`TABLE_TYPE` = 'BASE TABLE'))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
