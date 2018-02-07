-- MySQL dump 10.13  Distrib 5.6.38, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: path
-- ------------------------------------------------------
-- Server version	5.6.38

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
-- Table structure for table `centernode`
--

DROP TABLE IF EXISTS `centernode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centernode` (
  `c_id` int(11) NOT NULL,
  `c_num` varchar(50) NOT NULL,
  `c_name` varchar(50) NOT NULL,
  `c_address` varchar(50) NOT NULL,
  `c_type` int(1) NOT NULL,
  `c_longitude` varchar(20) DEFAULT NULL,
  `c_latitude` varchar(20) DEFAULT NULL,
  `c_baidulongitude` float DEFAULT NULL,
  `c_baidulatitude` float DEFAULT NULL,
  `c_quatity` double DEFAULT NULL,
  `c_rem` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`c_id`,`c_num`),
  CONSTRAINT `centernode_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `questionbasic` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centernode`
--

LOCK TABLES `centernode` WRITE;
/*!40000 ALTER TABLE `centernode` DISABLE KEYS */;
/*!40000 ALTER TABLE `centernode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distance`
--

DROP TABLE IF EXISTS `distance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distance` (
  `d_id` int(11) NOT NULL,
  `start_id` int(11) NOT NULL,
  `end_id` int(11) NOT NULL,
  `standard_dis` int(11) NOT NULL,
  `standard_time` int(11) NOT NULL,
  PRIMARY KEY (`d_id`,`start_id`,`end_id`),
  CONSTRAINT `distance_ibfk_1` FOREIGN KEY (`d_id`) REFERENCES `questionbasic` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distance`
--

LOCK TABLES `distance` WRITE;
/*!40000 ALTER TABLE `distance` DISABLE KEYS */;
/*!40000 ALTER TABLE `distance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finalsolution`
--

DROP TABLE IF EXISTS `finalsolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finalsolution` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `q_id` int(11) NOT NULL,
  PRIMARY KEY (`f_id`),
  KEY `q_id` (`q_id`),
  CONSTRAINT `finalsolution_ibfk_1` FOREIGN KEY (`q_id`) REFERENCES `questionbasic` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finalsolution`
--

LOCK TABLES `finalsolution` WRITE;
/*!40000 ALTER TABLE `finalsolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `finalsolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionbasic`
--

DROP TABLE IF EXISTS `questionbasic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionbasic` (
  `q_id` int(4) NOT NULL AUTO_INCREMENT,
  `q_name` varchar(50) NOT NULL,
  `q_descript` varchar(500) DEFAULT NULL,
  `q_rem1` varchar(50) DEFAULT NULL,
  `q_rem2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`q_id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionbasic`
--

LOCK TABLES `questionbasic` WRITE;
/*!40000 ALTER TABLE `questionbasic` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionbasic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `r_id` int(11) NOT NULL,
  `f_id` int(11) NOT NULL,
  `v_id` int(11) NOT NULL,
  `route` varchar(255) NOT NULL,
  PRIMARY KEY (`r_id`,`f_id`,`v_id`),
  KEY `v_id` (`v_id`),
  KEY `f_id` (`f_id`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `questionbasic` (`q_id`),
  CONSTRAINT `route_ibfk_2` FOREIGN KEY (`v_id`) REFERENCES `vahicle` (`v_num`),
  CONSTRAINT `route_ibfk_3` FOREIGN KEY (`f_id`) REFERENCES `finalsolution` (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicenode`
--

DROP TABLE IF EXISTS `servicenode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicenode` (
  `s_id` int(11) NOT NULL,
  `s_num` varchar(50) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `s_address` varchar(50) NOT NULL,
  `s_type` int(1) NOT NULL,
  `s_longitude` varchar(20) DEFAULT NULL,
  `s_latitude` varchar(20) DEFAULT NULL,
  `s_baidulongitude` float(4,4) DEFAULT NULL,
  `s_baidulatitude` float(4,4) DEFAULT NULL,
  `s_quatity` double DEFAULT NULL,
  `s_rem` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`s_id`,`s_num`),
  CONSTRAINT `servicenode_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `questionbasic` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicenode`
--

LOCK TABLES `servicenode` WRITE;
/*!40000 ALTER TABLE `servicenode` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicenode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vahicle`
--

DROP TABLE IF EXISTS `vahicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vahicle` (
  `v_id` int(11) NOT NULL,
  `v_num` int(11) NOT NULL,
  `v_type` varchar(50) NOT NULL,
  `v_capacity` float NOT NULL,
  `v_oil` float NOT NULL,
  `v_price` float DEFAULT NULL,
  `v_date` date DEFAULT NULL,
  `v_rem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`v_id`,`v_num`),
  KEY `v_num` (`v_num`),
  CONSTRAINT `vahicle_ibfk_1` FOREIGN KEY (`v_id`) REFERENCES `questionbasic` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vahicle`
--

LOCK TABLES `vahicle` WRITE;
/*!40000 ALTER TABLE `vahicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vahicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-07  9:15:13
