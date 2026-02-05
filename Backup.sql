-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: db_reviewbookhub
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_reg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e0qjwtjv73sl2c7a9nmlvvoam` (`categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'2023-12-25 16:06:03',1,'Aventura'),(1,'2023-12-25 16:06:03',2,'Ficción'),(1,'2023-12-25 16:06:03',3,'Drama'),(1,'2023-12-25 16:06:03',4,'Misterio'),(1,'2023-12-25 16:06:03',5,'Romance');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `precio` decimal(10,2) NOT NULL,
  `categoria` bigint NOT NULL,
  `fecha_reg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario` bigint NOT NULL,
  `idioma` varchar(50) NOT NULL,
  `nombre_imagen` varchar(100) NOT NULL,
  `sintesis` varchar(500) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `edicion` varchar(255) NOT NULL,
  `editorial` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n61s8dgmfjvgk18e4w53543oo` (`titulo`),
  KEY `FKo3jf2v5h3akg1vl311emjayyr` (`categoria`),
  KEY `FKh3f7jtij1msd7pc9jtrprl8iu` (`usuario`),
  CONSTRAINT `FKh3f7jtij1msd7pc9jtrprl8iu` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKo3jf2v5h3akg1vl311emjayyr` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,15.99,1,'2023-12-29 17:51:48',5,9,'Español','isla_del_tesoro.jpg','Una emocionante búsqueda de un tesoro enterrado en una isla misteriosa.','Robert Louis Stevenson','Premium','Penguin Clásicos','La isla del tesoro'),(1,12.99,1,'2023-12-29 17:53:34',6,10,'Francés','jumanji.jpg','La historia de un grupo de niños que descubren un juego de mesa mágico que los transporta a una jungla llena de peligros.','Chris Van Allsburg','Edición Especial','Houghton Mifflin Harcourt','Jumanji'),(1,17.99,1,'2023-12-29 17:59:56',13,11,'Italiano','harry_potter.jpg','La historia de un niño que descubre que es un mago y se matricula en el Colegio Hogwarts de Magia y Hechicería.','J K Rowling','Minalima','Bloomsbury Publishing','Harry Potter y la piedra filosofal'),(1,22.99,1,'2023-12-29 18:00:42',14,11,'Italiano','harry_potter_camara_secreta.jpg','La segunda entrega de la saga donde Harry descubre secretos ocultos en el Colegio Hogwarts.','J K Rowling','Extendida','Bloomsbury Publishing','Harry Potter y la cámara secreta'),(1,14.99,3,'2023-12-29 18:01:48',15,10,'Español','secreto_oculto.jpg','Una historia con giros emocionantes y personajes apasionantes que exploran los desafíos de la vida.','Michael Robotham','Primera Edición','Editorial Drama','Secretos Ocultos'),(1,18.99,4,'2023-12-29 18:02:25',16,10,'Inglés','enigma_perdido.jpg','Una trama intrigante que sigue a un detective mientras resuelve un misterio complicado que ha desconcertado a todos.','Dan Brown','Edición Especial','Editorial Misteriosa','El Simbolo Perdido'),(1,29.99,2,'2023-12-29 18:08:06',17,9,'Español','cien_anos_de_soledad.jpg','La historia de la familia Buendía y de Macondo, un pueblo ficticio en la costa caribeña de Colombia.','Gabriel García Márquez','Sudamericana','Editorial Misteriosa','Cien años de soledad'),(1,39.99,2,'2023-12-29 18:09:10',18,9,'Inglés','señor_de_los_anillos.jpg','La historia de un grupo de hobbits que se embarcan en una aventura para destruir el Anillo Único, una poderosa arma creada por el Señor Oscuro Sauron.','J R R Tolkien','HarperCollins Publishers','Editorial Misteriosa','El Señor de los Anillos'),(1,26.99,5,'2023-12-29 18:11:27',19,9,'Inglés','bajo_la_misma_estrella.jpg','La historia de dos adolescentes con cáncer que se enamoran durante un campamento de verano.','Jhon Green','Limitada','Random House','Bajo la misma estrella');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resenas`
--

DROP TABLE IF EXISTS `resenas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resenas` (
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_reg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `libro` bigint NOT NULL,
  `usuario` bigint NOT NULL,
  `comentario` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKao9sjykgcs54g7m6bsfkhdxfa` (`libro`),
  KEY `FK7n3yl0e1rae6balkayia86dxp` (`usuario`),
  CONSTRAINT `FK7n3yl0e1rae6balkayia86dxp` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKao9sjykgcs54g7m6bsfkhdxfa` FOREIGN KEY (`libro`) REFERENCES `libros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resenas`
--

LOCK TABLES `resenas` WRITE;
/*!40000 ALTER TABLE `resenas` DISABLE KEYS */;
INSERT INTO `resenas` VALUES (1,'2023-12-30 19:57:02',3,19,11,'Es un grandioso libro me hizo llorar mucho y valorar los pequeños momentos de la vida.'),(1,'2023-12-30 23:52:56',19,5,9,'Este libro es de mis favoritos'),(1,'2023-12-30 23:57:16',20,19,10,'Libros que hay que enmarcar para siempre'),(1,'2023-12-31 00:27:50',30,15,9,'dd'),(1,'2024-01-05 14:37:39',31,5,12,'Me encanta este libro'),(1,'2024-01-05 18:06:38',32,5,13,'Este libro es fantastico'),(1,'2024-01-05 18:09:10',33,15,11,'Bacan este libro');
/*!40000 ALTER TABLE `resenas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `fecha_reg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `telefono` varchar(10) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `nombres` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cdmw5hxlfj78uf4997i3qyyw5` (`correo`),
  UNIQUE KEY `UK_3m5n1w5trapxlbo2s42ugwdmd` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'2023-12-29 17:46:11',9,'0989887878','adrian.bravop@ug.edu.ec','luis123','$2a$10$yx45Yzswbb0LiPWrh7swNuaOfqZ5hzCNVLBYUdBUBtID0FWklirDO','Luis Bravo'),(1,'2023-12-29 17:47:15',10,'0989877872','jhonny.floresg@ug.edu.ec','jhonny123','$2a$10$/3shnot5jCyIKmTd5.fXXekjI9yTMvMkd4jhom6gjgxiQmUpxGPrC','Jhonny Flores'),(1,'2023-12-29 17:49:19',11,'0999909089','angelovillamar@ug.edu.ec','angelo123','$2a$10$4DV/NcHcVFRMbO4VBnYRTej8rAvZKO1gXPJ.Ny5jmp5YSChxUwj8u','Angelo Villamar'),(1,'2023-12-30 17:02:25',12,'0999990909','milton@hotmail.com','milton123','$2a$10$X5br5MHAOK..eE6iu2if..Vfe48xzA.31cMvivLw/uPknfCVWi3Jm','Milton Merchan'),(1,'2024-01-05 18:05:32',13,'0988898988','juan@hotmail.com','12','$2a$10$3ugHquRMxHchqsrj4rlko.mhB4iM0iCOGxz.M4cRTRYfWwzUW36uG','juanito perez');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-05 18:23:58
