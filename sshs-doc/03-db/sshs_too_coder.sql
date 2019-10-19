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
-- Table structure for table `too_coder`
--

DROP TABLE IF EXISTS `too_coder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `too_coder` (
  `CODER_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '代码生成ID',
  `TITLE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `MODEL_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块名',
  `MODEL_NAME_CN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模块名(中文)',
  `PACKAGE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '包名',
  `CLASS_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类名',
  `FUNCTION_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '功能名称',
  `TABLE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `TABLE_COMMENT` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表描述',
  `FIELDS` blob COMMENT '字段列表',
  `CODER_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型(单表，树形，主从表)',
  `CRT_USER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `CRT_ORG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机构',
  `CRT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPD_USER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `UPD_ORG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机构',
  `UPD_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`CODER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='开发辅助-代码生成表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `too_coder`
--

LOCK TABLES `too_coder` WRITE;
/*!40000 ALTER TABLE `too_coder` DISABLE KEYS */;
INSERT INTO `too_coder` VALUES ('f084d60e54024f08922b327d48d26010','系统管理->系统管理-机构表','system','系统管理','com.sshs.system.org','Org','org','sys_org','系统管理-机构表','ͭ\0sr\0java.util.ArrayListxڇa݃\0I\0sizexp\0\0\01w\0\0\01sr\0#com.sshs.toolkit.coder.model.Column\0\0\0\0\0\0\0\0\ZL\0\naddDefaultt\0Ljava/lang/String;L\0addFlagq\0~\0L\0\rcolumnCommentq\0~\0L\0columnIdq\0~\0L\0\ncolumnNameq\0~\0L\0columnScalet\0Ljava/lang/Integer;L\0\ncolumnSizeq\0~\0L\0\ncolumnTypeq\0~\0L\0componentType1q\0~\0L\0	dataRangeq\0~\0L\0defaultValueq\0~\0L\0dictCodeq\0~\0L\0listFlagq\0~\0L\0\nmodifyFlagq\0~\0L\0modifyFlag1q\0~\0L\0primaryKeyFlagq\0~\0L\0propFuncNameq\0~\0L\0propertyNameq\0~\0L\0propertyTypeq\0~\0L\0remarksq\0~\0L\0requiredFlagq\0~\0L\0\nsearchFlagq\0~\0L\0\nsearchTypeq\0~\0L\0tableCommentq\0~\0L\0	tableNameq\0~\0L\0\nvalidatorsq\0~\0xppt\0truet\0机构IDt\01t\0ORG_IDpsr\0java.lang.Integer⠤󁇸\0I\0valuexr\0java.lang.NumberƬ՝ՠ˂\0\0xp\0\0\0 t\0varcharppt\0\0q\0~\0q\0~\0q\0~\0pt\01pt\0orgIdt\0Stringpq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0状态：01--启用,02--停用t\010t\0STATUSpsq\0~\0\n\0\0\0\nt\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0statusq\0~\0pt\0falseq\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0A国家或地区:中国、中国香港、美国等,字典表数据t\011t\0COUNTRYpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0countryq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0联系邮箱t\012t\0EMAILpsq\0~\0\n\0\0\0dt\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0emailq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0办公地址t\013t\0OFFICE_ADDRpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nofficeAddrq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0邮政编码t\014t\0POSTpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0postq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0联系电话t\015t\0TELpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0telq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	联系人t\016t\0CONTACTpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0contactq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0金融机构编码（人行）t\017t\0	ORG_FI_NOpsq\0~\0\n\0\0\0<t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgFiNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0支付行号（人行）t\018t\0\nORG_PAY_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgPayNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构信用代码（人行）t\019t\0\rORG_CREDIT_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCreditNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构编码t\02t\0ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0!金融许可证号码（银监）t\020t\0\rORG_PERMIT_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgPermitNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0!营业执照注册号（工商）t\021t\0\nORG_REG_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgRegNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0组织机构代码证号码t\022t\0\rORG_ID_NUMBERpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgIdNumberq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0税务登记证号码t\023t\0\nORG_TAX_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgTaxNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0成立日期t\024t\0ORG_CREATE_DATEpsq\0~\0\n\0\0\0\0t\0	timestampppt\0CURRENT_TIMESTAMPq\0~\0q\0~\0q\0~\0pt\00pt\0\rorgCreateDatet\0Datepq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业开始时间t\025t\0ORG_START_DATEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgStartDateq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业终止时间t\026t\0ORG_END_DATEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\norgEndDateq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构负责人t\027t\0\nORG_HEADERpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0	orgHeaderq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	网点号t\028t\0NODE_NOpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0nodeNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0代管机构：01,02,03t\029t\0HIGHER_MNG_ORGpsq\0~\0\n\0\0鵜0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0higherMngOrgq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构名称t\03t\0ORG_NAMEpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgNameq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0是否最末端t\030t\0IS_LEAFpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0isLeafq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0营业状态t\031t\0BUSI_STATUSpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nbusiStatusq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0行政区划代码t\032t\0	AREA_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0areaCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	顺序号t\033t\0ORDERSq\0~\0ųq\0~\0\n\0\0\0t\0decimalppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orderst\0\nBigDecimalpq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0所属条线t\034t\0OWN_LINEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0ownLineq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	版本号t\035t\0VER_NOq\0~\0ű\0~t\0decimalppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0verNoq\0~pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0审批状态t\036t\0APPLY_STATUSpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0applyStatusq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\00操作类型：合并，撤销，转移，修改t\037t\0	OPER_TYPEpsq\0~\0\n\0\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0operTypeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0;关联主键：合并，撤销，转移，修改的关联IDt\038t\0OPER_RELA_IDpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\noperRelaIdq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	申请人t\039t\0APPLY_EMP_NOpq\0~\0[t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\napplyEmpNoq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0机构简称t\04t\0	ORG_BRIEFpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgBriefq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0申请日期t\040t\0\nAPPLY_DATEpq\0~\0Ŵ\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0	applyDateq\0~\0ʰq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0申请原因t\041t\0APPLY_REASONpsq\0~\0\n\0\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0applyReasonq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0审批流IDt\042t\0PROCESS_INSTANCE_IDpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0processInstanceIdq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0备注t\043t\0REMARKpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0remarkq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	创建人t\044t\0\rCRT_USER_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0crtUserCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0创建机构t\045t\0CRT_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\ncrtOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0创建日期t\046t\0CRT_DATEpq\0~\0Ŵ\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0crtDateq\0~\0ʰq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0	修改人t\047t\0\rUPD_USER_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0updUserCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0修改机构t\048t\0UPD_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\nupdOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0修改日期t\049t\0UPD_DATEpq\0~\0Ŵ\0	timestampppt\00000-00-00 00:00:00q\0~\0q\0~\0q\0~\0pt\00pt\0updDateq\0~\0ʰq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0直接上级机构IDt\05t\0PARENT_ORG_CODEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0\rparentOrgCodeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0\"机构类别:01--机构,02--部门t\06t\0ORG_CATEGORYpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgCategoryq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0$机构层级（树形结构层级）t\07t\0	ORG_LEVELpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgLevelq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0*机构级别：总行，分行，支行等t\08t\0	ORG_GRADEpq\0~\0t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0orgGradeq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpsq\0~\0pq\0~\0t\0<中文路径：用‘.’隔开各层级路径名称:.a.b.c.t\09t\0	PATH_NAMEpq\0~\0,t\0varcharppq\0~\0q\0~\0q\0~\0q\0~\0pt\00pt\0pathNameq\0~\0pq\0~\0q\0~\0pt\0系统管理-机构表t\0sys_orgpx',NULL,NULL,NULL,'2018-05-15 03:06:32',NULL,NULL,'2018-05-15 03:06:32');
/*!40000 ALTER TABLE `too_coder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-05 19:57:35
