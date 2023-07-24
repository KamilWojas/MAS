-- MySQL dump 10.13  Distrib 8.0.33, for macos13.3 (x86_64)
--
-- Host: localhost    Database: biblioteka
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `czytelnicy`
--

DROP TABLE IF EXISTS `czytelnicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `czytelnicy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazwa_uzytkownika` varchar(255) NOT NULL,
  `haslo` varchar(255) NOT NULL,
  `imie` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czytelnicy`
--

LOCK TABLES `czytelnicy` WRITE;
/*!40000 ALTER TABLE `czytelnicy` DISABLE KEYS */;
/*!40000 ALTER TABLE `czytelnicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `czytelnik`
--

DROP TABLE IF EXISTS `czytelnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `czytelnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czytelnik`
--

LOCK TABLES `czytelnik` WRITE;
/*!40000 ALTER TABLE `czytelnik` DISABLE KEYS */;
/*!40000 ALTER TABLE `czytelnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Kategorie`
--

DROP TABLE IF EXISTS `Kategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Kategorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Kategorie`
--

LOCK TABLES `Kategorie` WRITE;
/*!40000 ALTER TABLE `Kategorie` DISABLE KEYS */;
INSERT INTO `Kategorie` VALUES (1,'Horror'),(2,'Fiction');
/*!40000 ALTER TABLE `Kategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ksiazka`
--

DROP TABLE IF EXISTS `ksiazka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ksiazka` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tytul` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `rok_wydania` int DEFAULT NULL,
  `dostepna` tinyint(1) DEFAULT NULL,
  `wypozyczajacy` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ksiazka`
--

LOCK TABLES `ksiazka` WRITE;
/*!40000 ALTER TABLE `ksiazka` DISABLE KEYS */;
/*!40000 ALTER TABLE `ksiazka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ksiazki`
--

DROP TABLE IF EXISTS `ksiazki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ksiazki` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tytul` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `rok_wydania` varchar(10) DEFAULT NULL,
  `ilosc_sztuk` int DEFAULT NULL,
  `kategoria_id` int DEFAULT NULL,
  `ksiazka_tytul` varchar(255) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `czy_wypozyczona` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ksiazki`
--

LOCK TABLES `ksiazki` WRITE;
/*!40000 ALTER TABLE `ksiazki` DISABLE KEYS */;
INSERT INTO `ksiazki` VALUES (1,'Harry Potter','J.K. Rowling','1997',NULL,NULL,NULL,NULL,0),(2,'Harry Potter i Kamień Filozoficzny','J.K. Rowling','1997',11,NULL,NULL,NULL,0),(3,'Harry Potter i Komnata Tajemnic','J.K. Rowling','1998',10,NULL,NULL,NULL,0),(4,'Harry Potter i Więzień Azkabanu','J.K. Rowling','1999',NULL,NULL,NULL,NULL,0),(5,'Harry Potter i Czara Ognia','J.K. Rowling','2000',NULL,NULL,NULL,NULL,0),(6,'Harry Potter i Zakon Feniksa','J.K. Rowling','2003',NULL,NULL,NULL,NULL,0),(7,'Harry Potter i Książę Półkrwi','J.K. Rowling','2005',NULL,NULL,NULL,NULL,0),(8,'Harry Potter i Insygnia Śmierci','J.K. Rowling','2007',NULL,NULL,NULL,NULL,0),(9,'Władca Pierścieni: Drużyna Pierścienia','J.R.R. Tolkien','1954',8,NULL,NULL,NULL,0),(10,'Władca Pierścieni: Dwie Wieże','J.R.R. Tolkien','1954',NULL,NULL,NULL,NULL,0),(11,'Władca Pierścieni: Powrót Króla','J.R.R. Tolkien','1955',NULL,NULL,NULL,NULL,0),(12,'Harry Potter i Kamień Filozoficzny','J.K. Rowling','1997',11,NULL,NULL,NULL,0),(13,'Harry Potter i Komnata Tajemnic','J.K. Rowling','1998',10,NULL,NULL,NULL,0),(14,'Harry Potter i Więzień Azkabanu','J.K. Rowling','1999',2,NULL,NULL,NULL,0),(15,'Harry Potter i Czara Ognia','J.K. Rowling','2000',4,NULL,NULL,NULL,0),(16,'Harry Potter i Zakon Feniksa','J.K. Rowling','2003',1,NULL,NULL,NULL,0),(17,'Harry Potter i Książę Półkrwi','J.K. Rowling','2005',6,NULL,NULL,NULL,0),(18,'Harry Potter i Insygnia Śmierci','J.K. Rowling','2007',0,NULL,NULL,NULL,0),(19,'Władca Pierścieni: Drużyna Pierścienia','J.R.R. Tolkien','1954',8,NULL,NULL,NULL,0),(20,'Władca Pierścieni: Dwie Wieże','J.R.R. Tolkien','1954',3,NULL,NULL,NULL,0),(21,'Władca Pierścieni: Powrót Króla','J.R.R. Tolkien','1955',1,NULL,NULL,NULL,0),(22,'Harry Potter - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,66.78245136808435,0),(23,'Władca Pierścieni: Drużyna Pierścienia - J.R.R. Tolkien','2023-06-22','2023-07-22',NULL,NULL,NULL,15.800336801211357,0),(24,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,31.309034414352602,0),(25,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,7.289963965647691,0),(26,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,40.519256922728566,0),(27,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,13.410187828604553,0),(28,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,54.21071970290633,0),(29,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,67.21,0),(30,'Harry Potter - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,91.4,0),(31,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,10.32,0),(32,'Harry Potter - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,24.3,0),(33,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,45.16,0),(34,'Harry Potter - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,72.95,0),(35,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,88.04,0),(36,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,57.29,0),(37,'Harry Potter i Komnata Tajemnic - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,73.01,0),(38,'Harry Potter i Komnata Tajemnic - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,27.07,0),(39,'Harry Potter - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,67.3,0),(40,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,45.68,0),(41,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-22','2023-07-22',NULL,NULL,NULL,52.9,0),(42,'Harry Potter i Komnata Tajemnic - J.K. Rowling','2023-06-23','2023-07-23',NULL,NULL,NULL,81.7,0),(43,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-24','2023-07-24',NULL,NULL,NULL,96.91,0),(44,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-25','2023-07-25',NULL,NULL,NULL,11.7,0),(45,'Harry Potter i Komnata Tajemnic - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,27.88,0),(46,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,36.55,0),(47,'Harry Potter - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,99.89,0),(48,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,56.25,0),(49,'Harry Potter - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,23.66,0),(50,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,36.81,0),(51,'Harry Potter i Kamień Filozoficzny - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,4.67,0),(52,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,23.83,0),(53,'Harry Potter i Komnata Tajemnic - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,61.54,0),(54,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,74.25,0),(55,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,38.41,0),(56,'Harry Potter i Więzień Azkabanu - J.K. Rowling','2023-06-26','2023-07-26',NULL,NULL,NULL,11.33,0);
/*!40000 ALTER TABLE `ksiazki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Uzytkownicy`
--

DROP TABLE IF EXISTS `Uzytkownicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Uzytkownicy` (
  `ID_uzytkownika` int NOT NULL AUTO_INCREMENT,
  `nazwa_uzytkownika` varchar(255) DEFAULT NULL,
  `haslo` varchar(255) DEFAULT NULL,
  `imie` varchar(255) DEFAULT NULL,
  `nazwisko` varchar(255) DEFAULT NULL,
  `adres_email` varchar(255) DEFAULT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_uzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Uzytkownicy`
--

LOCK TABLES `Uzytkownicy` WRITE;
/*!40000 ALTER TABLE `Uzytkownicy` DISABLE KEYS */;
INSERT INTO `Uzytkownicy` VALUES (1,'Kamil','Wojas',NULL,NULL,NULL,'Czytelnik'),(2,'Kamil','Wojas',NULL,NULL,NULL,'Czytelnik'),(3,'Kamil','Barca',NULL,NULL,NULL,'Czytelnik'),(4,'Kamil','Wojas',NULL,NULL,NULL,'Bibliotekarz'),(5,'Maciej','Mazgaj',NULL,NULL,NULL,'Czytelnik'),(6,'Bastek','Bastek',NULL,NULL,NULL,'Czytelnik'),(7,'Kamil','Wojas',NULL,NULL,NULL,NULL),(8,'Kamil','Wojas',NULL,NULL,NULL,NULL),(9,'Kamil','1234',NULL,NULL,NULL,'Czytelnik'),(10,'Kamil','Wojas',NULL,NULL,NULL,'Czytelnik');
/*!40000 ALTER TABLE `Uzytkownicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wypozyczenia`
--

DROP TABLE IF EXISTS `wypozyczenia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wypozyczenia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_czytelnika` int DEFAULT NULL,
  `id_ksiazki` int DEFAULT NULL,
  `data_wypozyczenia` date DEFAULT NULL,
  `data_zwrotu` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_czytelnika` (`id_czytelnika`),
  KEY `id_ksiazki` (`id_ksiazki`),
  CONSTRAINT `wypozyczenia_ibfk_1` FOREIGN KEY (`id_czytelnika`) REFERENCES `czytelnik` (`id`),
  CONSTRAINT `wypozyczenia_ibfk_2` FOREIGN KEY (`id_ksiazki`) REFERENCES `ksiazka` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wypozyczenia`
--

LOCK TABLES `wypozyczenia` WRITE;
/*!40000 ALTER TABLE `wypozyczenia` DISABLE KEYS */;
/*!40000 ALTER TABLE `wypozyczenia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-27 11:09:34
