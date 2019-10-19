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
-- Temporary view structure for view `v_too_tables`
--

DROP TABLE IF EXISTS `v_too_tables`;
/*!50001 DROP VIEW IF EXISTS `v_too_tables`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_too_tables` AS SELECT 
 1 AS `TABLE_NAME`,
 1 AS `TABLE_COMMENT`,
 1 AS `COLUMN_ID`,
 1 AS `COLUMN_NAME`,
 1 AS `COLUMN_COMMENT`,
 1 AS `COLUMN_TYPE`,
 1 AS `COLUMN_SIZE`,
 1 AS `COLUMN_SCALE`,
 1 AS `DEFAULT_VALUE`,
 1 AS `PRIMARY_KEY_FLAG`,
 1 AS `REQUIRED_FLAG`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_too_tables`
--

/*!50001 DROP VIEW IF EXISTS `v_too_tables`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_too_tables` AS select `t`.`TABLE_NAME` AS `TABLE_NAME`,`t`.`TABLE_COMMENT` AS `TABLE_COMMENT`,cast(`c`.`ORDINAL_POSITION` as char charset utf8) AS `COLUMN_ID`,`c`.`COLUMN_NAME` AS `COLUMN_NAME`,`c`.`COLUMN_COMMENT` AS `COLUMN_COMMENT`,`c`.`DATA_TYPE` AS `COLUMN_TYPE`,(case when (`c`.`DATA_TYPE` = 'decimal') then `c`.`NUMERIC_PRECISION` when (`c`.`DATA_TYPE` like '%time%') then `c`.`DATETIME_PRECISION` else `c`.`CHARACTER_MAXIMUM_LENGTH` end) AS `COLUMN_SIZE`,`c`.`NUMERIC_SCALE` AS `COLUMN_SCALE`,`c`.`COLUMN_DEFAULT` AS `DEFAULT_VALUE`,(case when (`k`.`CONSTRAINT_NAME` = 'PRIMARY') then '1' else '0' end) AS `PRIMARY_KEY_FLAG`,(case when (`c`.`IS_NULLABLE` = 'NO') then 'on' else 'off' end) AS `REQUIRED_FLAG` from (`information_schema`.`TABLES` `t` join (`information_schema`.`COLUMNS` `c` left join `information_schema`.`KEY_COLUMN_USAGE` `k` on(((`c`.`TABLE_NAME` = `k`.`TABLE_NAME`) and (`c`.`COLUMN_NAME` = `k`.`COLUMN_NAME`))))) where ((`t`.`TABLE_NAME` = `c`.`TABLE_NAME`) and (`t`.`TABLE_SCHEMA` = 'sshs') and (`t`.`TABLE_TYPE` = 'BASE TABLE')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-05 19:57:37
