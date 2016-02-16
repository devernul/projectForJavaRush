CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `STATUS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'New'),(2,'Process'),(3,'Complete');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `DESCRIPTION` varchar(1000) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `STATUS_IS` int(11) NOT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `FK_jguisancku5h5fgdu62u95td5` (`STATUS_IS`),
  CONSTRAINT `FK_jguisancku5h5fgdu62u95td5` FOREIGN KEY (`STATUS_IS`) REFERENCES `status` (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #1',3),(2,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #2',3),(3,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #3',3),(4,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #4',3),(5,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #5',3),(6,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #6',2),(7,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #7',2),(8,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #8',2),(9,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #9',2),(10,'2016-02-17','<p>awoke</p>\n<p>eat</p>\n<p>walk</p>\n<p>sleep</p>','Task #10',2),(11,'2016-02-17','<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">awoke</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">eat</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">walk</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">sleep</p>','Task11',1),(12,'2016-02-17','<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">awoke</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">eat</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">walk</p>\r\n<p style=\"font-family: \'Times New Roman\'; font-size: 14px; font-weight: 600; text-align: center; background-color: #fdfdfd;\">sleep</p>','Task12',1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'devernul@gmail.com','dmitry'),(2,'oleges@gmail.com','oleg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_tasks`
--

DROP TABLE IF EXISTS `users_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_tasks` (
  `USER_ID` int(11) NOT NULL,
  `TASK_ID` int(11) NOT NULL,
  KEY `FK_exj1x50t0koecpct89hwc8ift` (`TASK_ID`),
  KEY `FK_414kjd3aeekvgw68rh3n93luh` (`USER_ID`),
  CONSTRAINT `FK_414kjd3aeekvgw68rh3n93luh` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`),
  CONSTRAINT `FK_exj1x50t0koecpct89hwc8ift` FOREIGN KEY (`TASK_ID`) REFERENCES `task` (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_tasks`
--

LOCK TABLES `users_tasks` WRITE;
/*!40000 ALTER TABLE `users_tasks` DISABLE KEYS */;
INSERT INTO `users_tasks` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12);
/*!40000 ALTER TABLE `users_tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-17  3:39:12
