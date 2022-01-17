CREATE DATABASE  IF NOT EXISTS `files` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `files`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: files
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fil_documentos`
--

DROP TABLE IF EXISTS `fil_documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_documentos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) DEFAULT NULL,
  `RUTA` longtext,
  `PESO` varchar(45) DEFAULT NULL,
  `FK_TIPO` int DEFAULT NULL,
  `FECHA_REGISTRO` datetime DEFAULT NULL,
  `FK_ESTADO` int DEFAULT NULL,
  `FK_USUARIO` int DEFAULT NULL,
  `CODIGO_U` varchar(45) DEFAULT NULL,
  `CODIGO_P` varchar(45) DEFAULT NULL,
  `FORMATO` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TIPO_DOCUMENTO_idx` (`FK_TIPO`),
  KEY `FK_ESTADO_DOCUMENTO_idx` (`FK_ESTADO`),
  KEY `FK_USUARIO_DOCMUENTO_idx` (`FK_USUARIO`),
  CONSTRAINT `FK_ESTADO_DOCUMENTO` FOREIGN KEY (`FK_ESTADO`) REFERENCES `fil_estado_documento` (`ID`),
  CONSTRAINT `FK_TIPO_DOCUMENTO` FOREIGN KEY (`FK_TIPO`) REFERENCES `fil_tipo_documento` (`ID`),
  CONSTRAINT `FK_USUARIO_DOCMUENTO` FOREIGN KEY (`FK_USUARIO`) REFERENCES `fil_usuarios` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_documentos`
--

LOCK TABLES `fil_documentos` WRITE;
/*!40000 ALTER TABLE `fil_documentos` DISABLE KEYS */;
INSERT INTO `fil_documentos` VALUES (1,'carpeta1','carpeta1',NULL,1,'2022-01-08 14:03:28',1,1,'root','UB1641650607768',NULL),(2,'carpeta2','carpeta2',NULL,1,'2022-01-08 14:03:39',1,1,'root','UB1641650619484',NULL),(3,'carpeta3','carpeta3',NULL,1,'2022-01-08 14:03:49',1,1,'root','UB1641650629075',NULL),(4,'Evidence_Who_would_I_like_to_be.doc',NULL,'1,04 MB',2,'2022-01-08 14:04:01',1,1,'root','UBF1641650641354','application/msword'),(5,'carpeta4','carpeta4',NULL,1,'2022-01-08 14:39:05',1,1,'root','UB1641652744910',NULL),(6,'mb_bios_b460m-aorus-elite_f5a (1).zip','carpeta4','9,85 MB',2,'2022-01-08 14:39:39',1,1,'UB1641652744910','UBF1641652778613','application/x-zip-compressed'),(7,'90cd7eeccfe357c07eea003b8e24fdc6-360p.mp4',NULL,'420,45 MB',2,'2022-01-08 14:42:20',1,1,'root','UBF1641652936400','video/mp4'),(8,'subcarpeta','carpeta4\\subcarpeta',NULL,1,'2022-01-08 14:47:59',1,1,'UB1641652744910','UB1641653279066',NULL),(9,'Battle4-www.gamesfull.org.torrent','carpeta2','411,11 KB',2,'2022-01-08 20:11:43',1,1,'UB1641650619484','UBF1641672702667','application/x-bittorrent'),(10,'141340.jpg','carpeta2','1,43 MB',2,'2022-01-08 20:12:08',1,1,'UB1641650619484','UBF1641672728452','image/jpeg'),(11,'carpeta5','carpeta5',NULL,1,'2022-01-09 15:39:07',1,2,'root','UB1641742747341',NULL);
/*!40000 ALTER TABLE `fil_documentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fil_estado_documento`
--

DROP TABLE IF EXISTS `fil_estado_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_estado_documento` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_estado_documento`
--

LOCK TABLES `fil_estado_documento` WRITE;
/*!40000 ALTER TABLE `fil_estado_documento` DISABLE KEYS */;
INSERT INTO `fil_estado_documento` VALUES (1,'ACTIVO'),(2,'ELIMINADO');
/*!40000 ALTER TABLE `fil_estado_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fil_estado_usuario`
--

DROP TABLE IF EXISTS `fil_estado_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_estado_usuario` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_estado_usuario`
--

LOCK TABLES `fil_estado_usuario` WRITE;
/*!40000 ALTER TABLE `fil_estado_usuario` DISABLE KEYS */;
INSERT INTO `fil_estado_usuario` VALUES (1,'ACTIVO'),(2,'BLOQUEADO');
/*!40000 ALTER TABLE `fil_estado_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fil_servidor`
--

DROP TABLE IF EXISTS `fil_servidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_servidor` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DIRECCION_FUENTE` longtext,
  `CARPETA_RAIZ` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_servidor`
--

LOCK TABLES `fil_servidor` WRITE;
/*!40000 ALTER TABLE `fil_servidor` DISABLE KEYS */;
INSERT INTO `fil_servidor` VALUES (1,'D:\\Directorio','root');
/*!40000 ALTER TABLE `fil_servidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fil_tipo_documento`
--

DROP TABLE IF EXISTS `fil_tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_tipo_documento` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_tipo_documento`
--

LOCK TABLES `fil_tipo_documento` WRITE;
/*!40000 ALTER TABLE `fil_tipo_documento` DISABLE KEYS */;
INSERT INTO `fil_tipo_documento` VALUES (1,'CARPETA'),(2,'ARCHIVO');
/*!40000 ALTER TABLE `fil_tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fil_usuarios`
--

DROP TABLE IF EXISTS `fil_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fil_usuarios` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(80) DEFAULT NULL,
  `APELLIDOS` varchar(80) DEFAULT NULL,
  `USUARIO` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(500) DEFAULT NULL,
  `FECHA_INGRESO` date DEFAULT NULL,
  `FECHA_ACTUALIZACION` datetime DEFAULT NULL,
  `FK_ESTADO` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ESTADO_USUARIO_idx` (`FK_ESTADO`),
  CONSTRAINT `FK_ESTADO_USUARIO` FOREIGN KEY (`FK_ESTADO`) REFERENCES `fil_estado_usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fil_usuarios`
--

LOCK TABLES `fil_usuarios` WRITE;
/*!40000 ALTER TABLE `fil_usuarios` DISABLE KEYS */;
INSERT INTO `fil_usuarios` VALUES (1,'ADMINISTRADOR',NULL,'ADMIN','8bab7bb7e7e5b2237f6fa41cd773af6050e8fb82','2022-01-05',NULL,1),(2,'USUARIO','','USER1','99800b85d3383e3a2fb45eb7d0066a4879a9dad0','2022-01-09','2022-01-09 00:00:00',1);
/*!40000 ALTER TABLE `fil_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-16 18:58:43
