-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: game_of_thrones
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `fullName` varchar(45) NOT NULL,
  `title` varchar(65) DEFAULT NULL,
  `family` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `family_fk_idx` (`family`),
  CONSTRAINT `fk_family` FOREIGN KEY (`family`) REFERENCES `families` (`familyName`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (1,'Daenerys','Targaryen','Daenerys Targaryen','Mother of Dragons','House Targaryen'),(2,'Samwell','Tarly','Samwell Tarly','Maester','House Tarly'),(3,'Jon','Snow','Jon Snow','King of the North','House Stark'),(4,'Arya','Stark','Arya Stark','No One','House Stark'),(5,'Sansa','Stark','Sansa Stark','Lady of Winterfell','House Stark'),(6,'Brandon','Stark','Brandon Stark','Lord of Winterfell','House Stark'),(7,'Ned','Stark','Ned Stark','Lord of Winterfell','House Stark'),(8,'Robert','Baratheon','Robert Baratheon','Lord of the Seven Kingdoms','House Baratheon'),(9,'Jamie','Lannister','Jamie Lannister','Lord Commander of the Kingsguard','House Lannister'),(10,'Cersei','Lannister','Cersei Lannister','Lady of Casterly Rock','House Lannister'),(11,'Cateyln','Stark','Catelyn Stark','Lady of Winterfell','House Stark'),(12,'Robb','Stark','Rob Stark','Lord of Winterfell','House Stark'),(13,'Theon','Greyjoy','Theon Greyjoy','Captain of Sea Bitch','House Greyjoy'),(14,'Joffrey','Baratheon','Joffrey Baratheon','Lord of the Seven Kingdoms, Protector of the Realm','House Lannister'),(15,'Tyrion','Lannister','Tyrion Lannister','Hand of the Queen','House Lannister'),(16,'Sandor','Clegane','The Hound','The Hound','House Clegane'),(17,'Petyr','Baelish','Petyr Baelish','Littlefinger','House Baelish'),(18,'Davos','Seaworth','Davos Seaworth','Hand of the King','House Seaworth'),(19,'Stannis','Baratheon','Stannis Baratheon','Lord of Dragonstone','House Baratheon'),(20,'Varys',NULL,'Varys','Master of Whisperers',NULL),(21,'Khal','Drogo','Khal Drogo','Khal','House Targaryen'),(22,'Margaery','Tyrell','Margaery Tyrell','Queen of the Seven Kingdoms','House Tyrell'),(23,'Ygritte','None','Ygritte','Spearwife','Free Folk'),(24,'Brienne','Tarth','Brienne of Tarth','Lady Brienne','Tarth'),(25,'Missandei',NULL,'Missandei','Queen\'s Personal Advisor','Naathi'),(26,'Gilly',NULL,'Gilly','The Rabbit Keeper',NULL),(27,'Viserys','Targaryan','Viserys Targaryn','King Viserys III','House Targaryen'),(28,'Rickon','Stark','Rickon Stark','Prince','House Stark'),(29,'Roose','Bolton','Roose Bolton','Lord of Dreadfort','House Bolton'),(30,'Daario','Naharis','Daario','Commander of the Second Sons','Naharis'),(31,'Shae',NULL,'Shae','Mistress','Lorath'),(32,'Tommen','Baratheon','Tommen Baratheon','Prince','House Baratheon'),(33,'Gendry','Baratheon','Gendry Baratheon','Lord of Storm\'s End','House Baratheon'),(34,'Jorah','Mormont','Jorah Mormont','Knight','House Mormont'),(35,'Robert','Baratheon','Robert Baratheon','King','House Baratheon'),(36,'Ramsey','Bolton','Ramsey Bolton','The Bastard of Bolton','House Bolton'),(37,'Talisa','Stark','Talisa Stark','Queen Consort','House Stark'),(38,'Jeor','Mormont','Jeor Mormont','Lord Commander of the Knight\'s Watch','House Mormont'),(39,'The High','Sparrow','The High Sparrow','High Septon',NULL),(40,'Oberyn','Martell','Oberyn Martell','Red Viper of Dorne','House Martell'),(41,'Melisandre','The Red Woman','Melisandre','Melisandre of Asshai',NULL),(42,'Jaqen','H\'ghar','Jaqen H\'ghar','Faceless Men of Braavos','Lorath'),(43,'Tywin','Lannister','Tywin Lannister','Lord Paramount of Westerlands','House Lannister'),(44,'Ellaria','Sand','Ellaria Sand','Paramour of Prince Oberyn Martell','Sand'),(45,'Tormund','Giantsbane','Tormund Giantsbane','Leader of the Free Folk','Giantsbane'),(46,'Yara','Greyjoy','Yara Greyjoy','Lady of the Iron Islands','House Greyjoy'),(47,'Euron','Greyjoy','Euron Greyjoy','King of the iron Islands','House Greyjoy'),(48,'Wylis','Hodor','Hodor','Servant of House Stark','House Stark'),(49,NULL,'Pycelle','Pycelle','Grand Maester of the Seven Kingdoms',NULL),(50,'Grey','Worm','Grey Worm','Commander of the Unsullied',NULL),(51,'Olenna','Tyrell','Olenna Tyrell','Queen of Thorns','House Tyrell'),(52,'Qyburn','Grand Maester','Qyburn','Former maester of the Citadel',NULL),(53,'Lord','Bronn','Lord Bronn','Lord of Highgarden',NULL);
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18 13:39:06
