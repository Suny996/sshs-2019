-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: sshs
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `core_customise_query`
--

DROP TABLE IF EXISTS `core_customise_query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `core_customise_query` (
  `CUSTOMISE_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '查询ID',
  `USER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `ORG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构编码',
  `PAGE_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页面ID',
  `CUSTOMISE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '查询名称',
  `FIELD_CONTENTS` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '条件列',
  `FIELD_ADDONS` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '条件匹配方式',
  `CRT_USER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`CUSTOMISE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='核心框架-自定义查询信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_customise_query`
--

LOCK TABLES `core_customise_query` WRITE;
/*!40000 ALTER TABLE `core_customise_query` DISABLE KEYS */;
INSERT INTO `core_customise_query` VALUES ('71cf7fd26be9435580cf83a1c82ba137','admin',NULL,'CoderQuery','简化查询','{\"tableComment\":true}',NULL,NULL,NULL,'2018-07-26 02:49:11',NULL,NULL,NULL),('802673cf99c54f37bf98d448b5dafa45','admin',NULL,'TestCustomise','全部条件','{\"name\":true,\"city\":true,\"date\":true,\"mail\":true,\"gender\":true}',NULL,NULL,NULL,'2018-07-21 06:05:02',NULL,NULL,NULL),('92a78623aa7343cb94a6ea455d1380d3','admin',NULL,'111','简化查询','{name,mail,gender}',NULL,NULL,NULL,'2018-07-24 01:06:31',NULL,NULL,NULL),('a102a15d17b24b3d82102cebdf5890aa','admin',NULL,'CoderQuery','单一查询','{\"title\":true}',NULL,NULL,NULL,'2018-07-26 07:00:46',NULL,NULL,NULL),('d0e80ce44b7744cfb9df3c98ec7ba729','admin',NULL,'CoderList','简化查询','{\"tableName\":true}',NULL,NULL,NULL,'2018-07-19 09:18:27',NULL,NULL,NULL);
/*!40000 ALTER TABLE `core_customise_query` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-05 19:57:34
