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
/*Data for the table `core_customise_query` */

insert  into `core_customise_query`(`CUSTOMISE_ID`,`USER_CODE`,`ORG_CODE`,`PAGE_ID`,`CUSTOMISE_NAME`,`FIELD_CONTENTS`,`FIELD_ADDONS`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('f6c783e8f78c4d6ebc2a34f9ec7e58b2','admin',NULL,'toolkitcodermainListw','啊啊啊','[\"tableName\"]',NULL,NULL,NULL,'2018-05-10 16:22:45',NULL,NULL,'2018-05-10 16:22:44');

/*Data for the table `sys_authorize` */

insert  into `sys_authorize`(`AUTHORIZE_ID`,`RESOURCE_ID`,`RESOURCE_NAME`,`ROLE_CODE`,`RESOURCE_TYPE`,`AUTHORIZE_TYPE`,`DATA_AUTH_TYPE`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('1001','01','辅助开发','1001','01','01','',NULL,NULL,'2018-05-10 16:51:27',NULL,NULL,'0000-00-00 00:00:00'),('100101','0101','代码生成','1001','01','01','01',NULL,NULL,'2018-05-10 16:52:36',NULL,NULL,'0000-00-00 00:00:00');

/*Data for the table `sys_coder` */

/*Data for the table `sys_dictionaries` */

insert  into `sys_dictionaries`(`DICT_ID`,`PARENT_ID`,`DICT_TYPE`,`DICT_CODE`,`DICT_NAME`,`DICT_DESC`,`STATUS`,`SORT_NO`,`IS_SYSTEM`,`MODULE`,`LEGAL_ORG`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('10001','0','0','1','通用字典项目','通用字典项目','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('1000101','10001','1','YN','是否','是否','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('100010101','1000101','3','1','是','是','1','1','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48'),('100010201','1000101','3','0','否','否','1','2','1',NULL,NULL,NULL,NULL,'2018-05-10 16:42:48',NULL,NULL,'2018-05-10 16:42:48');

/*Data for the table `sys_dictionaries_i18n` */

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`MENU_CODE`,`MENU_NAME`,`MENU_TYPE`,`MENU_URL`,`PARENT_MENU_CODE`,`MENU_MODULE`,`IS_RELATIVE_PATH`,`MENU_LEVEL`,`MENU_SEQ`,`MENU_ICON`,`MENU_ICONA`,`IS_VISIBLE`,`OPEN_MODE`,`MENU_TIP`,`IS_START`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('01','开发辅助','01',NULL,NULL,NULL,NULL,'1','1','fa fa-linode',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-10 17:09:01',NULL,NULL,'0000-00-00 00:00:00'),('0101','代码生成','02','toolkit/coder/mainList.w','01',NULL,NULL,'2','1','fa fa-desktop',NULL,NULL,NULL,NULL,'1',NULL,NULL,'2018-05-10 17:09:02',NULL,NULL,'0000-00-00 00:00:00');

/*Data for the table `sys_menu_i18n` */

/*Data for the table `sys_org` */

/*Data for the table `sys_post` */

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_CODE`,`ROLE_NAME`,`REMARK`,`ROLE_LEVEL`,`STATUS`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('1001','测试角色',NULL,'1','1',NULL,NULL,'2018-05-10 16:49:19',NULL,NULL,'0000-00-00 00:00:00');

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_ID`,`USER_CODE`,`USER_NAME`,`USER_NAME_EN`,`USER_NAME_PY`,`USER_NAME_FR`,`ORG_CODE`,`POST_NO`,`USER_SEX`,`NATIONALITY`,`ETHNIC`,`NATIVE_PLACE`,`BIRTH_PLACE`,`BIRTH_DATE`,`ID_DECIMAL`,`PHOTO_ID`,`JOIN_DATE`,`STATUS`,`TELLER_ID`,`RESIDENTIAL_ADDRESS`,`MOBILE_PHONE`,`REMARK`,`PASSWORD`,`SALT`,`ONLINE_STATUS`,`IP_ADDR`,`USER_THEME`,`PD_COUNT`,`PD_MODTIME`,`PD_LOCKTIME`,`LAST_SIGNON_TIME`,`LAST_SIGNOUT_TIME`,`LEGAL_ORG`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('admin','admin','系统管理员','administrator','administrator',NULL,'0001',NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-27 17:01:16',NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,NULL,NULL,NULL,'$2a$10$Adv2Bz4PzCL5.kYUK6Wx8.YmN4BGIShRhD/T18Xhcmeidr9D3NqjC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00',NULL,NULL,NULL,'0000-00-00 00:00:00',NULL,NULL,'0000-00-00 00:00:00');

/*Data for the table `sys_user_ext` */

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`USER_ROLE_ID`,`USER_CODE`,`ROLE_CODE`,`CRT_USER_CODE`,`CRT_ORG_CODE`,`CRT_DATE`,`UPD_USER_CODE`,`UPD_ORG_CODE`,`UPD_DATE`) values ('admin1001','admin','1001',NULL,NULL,'2018-05-10 16:49:50',NULL,NULL,'0000-00-00 00:00:00');

/*Data for the table `test` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
