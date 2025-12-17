CREATE DATABASE  IF NOT EXISTS `travelagencydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `travelagencydb`;
-- MySQL dump 10.13  Distrib 8.0.44, for macos15 (arm64)
--
-- Host: 127.0.0.1    Database: travelagencydb
-- ------------------------------------------------------
-- Server version	8.4.7

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
-- Table structure for table `about_us_hero_section`
--

DROP TABLE IF EXISTS `about_us_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `about_us_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `about_us_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `about_us_hero_section`
--

LOCK TABLES `about_us_hero_section` WRITE;
/*!40000 ALTER TABLE `about_us_hero_section` DISABLE KEYS */;
INSERT INTO `about_us_hero_section` VALUES (1,'About Us Hero 1','https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80','About Us','Who We Are?','We are a Sri Lanka Tourism Development Authority & Sri Lanka Civil Aviation Authority approved travel agent based in Colombo, Sri Lanka. Providing exceptional travel experiences since 2010.','Explore Sri Lanka','/destinations','Contact Us','/contact-us',1,1,'2025-12-03 05:05:15',1,'2025-12-11 17:54:11',1,NULL,NULL),(2,'About Us Hero 2','https://images.unsplash.com/photo-1563492065599-3520f775eeed?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80','Our Mission','Creating Unforgettable Journeys','To be Sri Lanka\'s most trusted travel partner, delivering authentic experiences with professional service, local expertise, and official certifications that guarantee quality and reliability.','Our Packages','/packages','View Tours','/sri-lankan-tours',1,2,'2025-12-03 05:05:15',1,'2025-12-11 17:54:11',1,NULL,NULL),(3,'About Us Hero 3','https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80','Certified Excellence','Officially Approved Travel Experts','Our dual approval from Sri Lanka Tourism Development Authority and Civil Aviation Authority ensures you receive professional, reliable, and compliant travel services for your peace of mind.','View Tours','/sri-lankan-tours','Join Us','/signup',1,3,'2025-12-03 05:05:15',1,'2025-12-11 17:54:11',1,NULL,NULL),(4,'About Us Hero 4','https://images.unsplash.com/photo-1544551763-46a013bb70d5?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80','Local Expertise','Colombo Based, Globally Connected','Based in the heart of Colombo, we combine deep local knowledge with international standards to create seamless travel experiences across Sri Lanka and beyond.','Our Destiantions','/destinations','Local Experiences','/sri-lankan-tours',1,4,'2025-12-03 05:05:15',1,'2025-12-11 17:54:11',1,NULL,NULL),(5,'About Us Hero 5','https://images.unsplash.com/photo-1559128010-7c1ad6e1b6a5?ixlib=rb-4.0.3&auto=format&fit=crop&w=2070&q=80','Trusted Partner','15+ Years of Excellence','With over 15 years in the travel industry, we have built a reputation for reliability, transparency, and exceptional customer service in Sri Lanka\'s tourism sector.','Our Packages','/packages','Contact Us','/contact-us',1,5,'2025-12-03 05:05:15',1,'2025-12-11 17:54:11',1,NULL,NULL);
/*!40000 ALTER TABLE `about_us_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accepted_payment_methods`
--

DROP TABLE IF EXISTS `accepted_payment_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accepted_payment_methods` (
  `payment_method_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `method_type` varchar(50) NOT NULL,
  `method_details` json DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `accepted_payment_methods_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `accepted_payment_methods_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accepted_payment_methods`
--

LOCK TABLES `accepted_payment_methods` WRITE;
/*!40000 ALTER TABLE `accepted_payment_methods` DISABLE KEYS */;
INSERT INTO `accepted_payment_methods` VALUES (1,1,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"American Express\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'DIGITAL_WALLET','{\"wallets\": [\"PayPal\", \"Apple Pay\", \"Google Pay\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"Discover\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'BANK_TRANSFER','{\"instructions\": \"Wire transfer details provided upon booking\"}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"American Express\", \"UnionPay\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"American Express\"]}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,5,'CORPORATE_ACCOUNT','{\"requirements\": \"Business registration required\"}',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,6,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"American Express\"]}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(10,6,'DIGITAL_WALLET','{\"wallets\": [\"Apple Pay\", \"Google Pay\"]}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,7,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"UnionPay\"]}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(12,7,'CASH','{\"currency\": \"USD\"}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(13,8,'CREDIT_CARD','{\"cards\": [\"Visa\", \"MasterCard\", \"Discover\"]}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(14,8,'CRYPTO','{\"currencies\": [\"Bitcoin\", \"Ethereum\"]}',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL);
/*!40000 ALTER TABLE `accepted_payment_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accommodation`
--

DROP TABLE IF EXISTS `accommodation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accommodation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `description` text,
  `icon_url` varchar(255) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `hover_color` varchar(20) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_accommodation_status` (`status_id`),
  CONSTRAINT `fk_accommodation_status` FOREIGN KEY (`status_id`) REFERENCES `accommodation_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodation`
--

LOCK TABLES `accommodation` WRITE;
/*!40000 ALTER TABLE `accommodation` DISABLE KEYS */;
INSERT INTO `accommodation` VALUES (1,'Ocean View Villa','Luxury Stay','A beautiful villa with an ocean view.','/icons/accommodations-icons/ocean.png','#ffffff','#000','/accommodation/ocean-view-villa',1,'2025-09-20 13:31:10',1,'2025-09-28 11:55:16',NULL,NULL,NULL,'/images/accommodations-images/ocean-view-villa.jpg'),(2,'Mountain Cabin','Cozy Cabin','A cozy cabin in the mountains.','/icons/accommodations-icons/mountain.png','#eeeeee','#000','/accommodation/mountain-cabin',1,'2025-09-20 13:31:10',1,'2025-09-28 11:55:16',NULL,NULL,NULL,'/images/accommodations-images/mountain-cabin.jpg'),(3,'City Apartment','Central Location','Apartment in the heart of the city.','/icons/accommodations-icons/apartment.png','#dddddd','#bbbbbb','/accommodation/city-apartment',1,'2025-09-20 13:31:10',1,'2025-09-28 08:18:08',NULL,NULL,NULL,'/images/accommodations-images/city-apartment.jpg'),(4,'Lake House','Private Retreat','A peaceful house by the lake.','/icons/accommodations-icons/lake.png','#cccccc','#aaaaaa','/accommodation/lake-house',1,'2025-09-20 13:31:10',1,'2025-09-28 08:18:08',NULL,NULL,NULL,'/images/accommodations-images/lake-house.jpg'),(5,'Desert Camp','Unique Escape','Experience the magic of the desert with a luxury tent under the stars.','/icons/accommodations-icons/camp.png','#cccccc','#aaaaaa','/accommodation/lake-house',1,'2025-09-20 13:31:10',1,'2025-09-28 08:18:08',NULL,NULL,NULL,'/images/accommodations-images/desert-camp.webp'),(6,'Jungle Lodge','Wildlife Adventure','Stay deep inside the jungle with guided safaris and eco-friendly lodges.','/icons/accommodations-icons/jungle.png','#cccccc','#aaaaaa','/accommodation/lake-house',1,'2025-09-20 13:31:10',1,'2025-09-28 08:18:08',NULL,NULL,NULL,'/images/accommodations-images/jungle-lodge.jpg');
/*!40000 ALTER TABLE `accommodation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accommodation_status`
--

DROP TABLE IF EXISTS `accommodation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accommodation_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_common_status` (`common_status_id`),
  CONSTRAINT `fk_common_status` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodation_status`
--

LOCK TABLES `accommodation_status` WRITE;
/*!40000 ALTER TABLE `accommodation_status` DISABLE KEYS */;
INSERT INTO `accommodation_status` VALUES (1,'AVAILABLE','Accommodation is available for booking',1,'2025-09-20 13:31:10',1,'2025-09-20 13:31:10',NULL,NULL,NULL),(2,'BOOKED','Accommodation is currently booked',1,'2025-09-20 13:31:10',1,'2025-09-20 13:31:10',NULL,NULL,NULL),(3,'UNDER_MAINTENANCE','Accommodation under maintenance',2,'2025-09-20 13:31:10',1,'2025-09-20 13:31:10',NULL,NULL,NULL),(4,'TERMINATED','Accommodation terminated',3,'2025-09-20 13:31:10',1,'2025-09-20 13:31:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `accommodation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievements` (
  `achievement_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `color` varchar(7) DEFAULT NULL,
  `hover_color` varchar(7) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`achievement_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `achievements_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements`
--

LOCK TABLES `achievements` WRITE;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
INSERT INTO `achievements` VALUES (1,'Employee of the Month','#FFD700','#FFEE58','Awarded to outstanding employee of the month',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(2,'Best Team Player','#4CAF50','#81C784','Awarded to the employee with best teamwork skills',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(3,'Innovation Award','#2196F3','#64B5F6','Awarded for outstanding innovation or idea',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL);
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievements_images`
--

DROP TABLE IF EXISTS `achievements_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievements_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `achievement_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `achievement_id` (`achievement_id`),
  KEY `status` (`status`),
  CONSTRAINT `achievements_images_ibfk_1` FOREIGN KEY (`achievement_id`) REFERENCES `achievements` (`achievement_id`),
  CONSTRAINT `achievements_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements_images`
--

LOCK TABLES `achievements_images` WRITE;
/*!40000 ALTER TABLE `achievements_images` DISABLE KEYS */;
INSERT INTO `achievements_images` VALUES (1,1,'Certificate','Certificate image','https://example.com/employee_month_cert.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(2,1,'Trophy','Trophy image','https://example.com/employee_month_trophy.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(3,1,'Badge','Badge image','https://example.com/employee_month_badge.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(4,2,'Certificate','Certificate image','https://example.com/best_team_cert.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(5,2,'Medal','Medal image','https://example.com/best_team_medal.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(6,2,'Trophy','Trophy image','https://example.com/best_team_trophy.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(7,3,'Certificate','Certificate image','https://example.com/innovation_cert.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(8,3,'Plaque','Plaque image','https://example.com/innovation_plaque.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL),(9,3,'Trophy','Trophy image','https://example.com/innovation_trophy.png',1,'2025-12-06 16:31:21',1,'2025-12-06 16:31:21',1,NULL,NULL);
/*!40000 ALTER TABLE `achievements_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `activities_category` varchar(100) DEFAULT NULL,
  `duration_hours` decimal(5,2) DEFAULT NULL,
  `available_from` time DEFAULT NULL,
  `available_to` time DEFAULT NULL,
  `price_local` decimal(10,2) DEFAULT NULL,
  `price_foreigners` decimal(10,2) DEFAULT NULL,
  `min_participate` int DEFAULT NULL,
  `max_participate` int DEFAULT NULL,
  `season` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_id` (`destination_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`),
  CONSTRAINT `activities_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (1,1,'Sigiriya Rock Climb','Climb the 1200 steps to the top of Sigiriya Rock Fortress','Adventure',3.50,'06:00:00','17:00:00',3000.00,5000.00,1,20,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(2,8,'Knuckles Mountain Trek','Full-day trekking adventure with guide in Knuckles Range','Adventure',6.00,'05:30:00','17:00:00',4000.00,6500.00,2,10,'Summer,Winter,Monsoon',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(3,13,'Kitulgala White Water Rafting','Exciting river rafting experience with safety gear','Adventure',4.00,'08:00:00','16:00:00',3500.00,5500.00,2,8,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(4,8,'Knuckles Overnight Camping','Two-day trek with guided camping in mountains','Adventure',12.00,'06:00:00','18:00:00',8000.00,12000.00,2,6,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(5,10,'Surfing Lessons','Learn to surf with experienced instructors','Water Sports',2.00,'07:00:00','17:00:00',3000.00,4500.00,1,10,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(6,4,'Kayaking in Bentota River','Paddle along scenic river with guide','Water Sports',3.00,'08:00:00','15:00:00',2500.00,4000.00,1,8,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(7,10,'Arugam Bay Surf Adventure','Advanced 3-day surf trip with equipment','Water Sports',9.00,'06:00:00','18:00:00',12000.00,20000.00,2,6,'Summer',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(8,4,'Mirissa Jet Skiing','High-speed jet ski fun on the beach','Water Sports',1.50,'09:00:00','16:00:00',4000.00,6000.00,1,5,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(9,3,'Yala Safari','Half-day jeep safari to spot leopards and elephants','Wildlife',4.00,'05:30:00','17:00:00',4000.00,6000.00,2,6,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(10,15,'Udawalawe Elephant Safari','Full-day safari to see wild elephants','Wildlife',5.00,'06:00:00','17:00:00',3500.00,5500.00,2,8,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(11,9,'Wilpattu Leopard Safari','Spot leopards, birds, and wildlife in Wilpattu','Wildlife',6.00,'05:00:00','18:00:00',5000.00,8000.00,2,6,'Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(12,14,'Minneriya Elephant Gathering','Witness mass elephant gathering in Minneriya','Wildlife',4.00,'06:00:00','18:00:00',3000.00,5000.00,2,12,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(13,4,'Whale Watching','Boat tour to see blue whales and dolphins','Marine Life',4.00,'06:00:00','12:00:00',5000.00,8000.00,4,30,'Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(14,4,'Mirissa Dolphin Cruise','Morning cruise to spot playful dolphins','Marine Life',3.00,'06:00:00','11:00:00',4000.00,6500.00,2,25,'Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(15,14,'Pigeon Island Snorkeling','Snorkel among tropical fish and coral reefs','Marine Life',4.00,'08:00:00','16:00:00',5000.00,7500.00,2,15,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(16,4,'Bentota Glass-Bottom Boat','See marine life without getting wet','Marine Life',2.00,'07:00:00','16:00:00',2500.00,4000.00,1,20,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(17,2,'Nine Arch Bridge Visit','Walk to the iconic colonial-era railway bridge','Sightseeing',2.00,'06:00:00','18:00:00',500.00,1000.00,1,50,'Summer,Winter,Monsoon,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(18,5,'Galle Fort Tour','Explore UNESCO-listed fort and streets','Sightseeing',3.00,'08:00:00','17:00:00',1500.00,2500.00,1,20,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(19,6,'Nuwara Eliya City Tour','Visit botanical gardens and Gregory Lake','Sightseeing',4.00,'07:00:00','16:00:00',2000.00,3500.00,2,15,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(20,12,'Jaffna Heritage Walk','Visit forts, temples, and local streets','Sightseeing',3.00,'08:00:00','17:00:00',2500.00,4000.00,2,15,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(21,2,'Little Adams Peak Hike','Easy hike with panoramic views of Ella','Hiking',2.50,'05:30:00','18:00:00',1000.00,1500.00,1,30,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(22,1,'Pidurangala Rock Hike','Alternative rock climb near Sigiriya','Hiking',3.00,'05:30:00','17:00:00',1500.00,2500.00,1,20,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(23,8,'Horton Plains Trail','Walk scenic trails to World\'s End and Baker\'s Falls','Hiking',5.00,'06:00:00','17:00:00',2000.00,3500.00,2,15,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(24,5,'Ella Rock Hike','Moderate hike with panoramic hill country views','Hiking',4.00,'06:00:00','16:00:00',1500.00,3000.00,1,12,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(25,7,'Kandy Temple of Tooth','Visit the sacred Buddhist temple in Kandy','Cultural',2.00,'06:00:00','18:00:00',1000.00,2000.00,1,20,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(26,12,'Kandy Perahera Experience','Watch traditional dance and elephant procession','Cultural',3.00,'18:00:00','22:00:00',1500.00,2500.00,1,15,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(27,12,'Polonnaruwa Ancient City','Explore ruins and temples of Polonnaruwa','Cultural',4.00,'06:00:00','17:00:00',2000.00,3500.00,2,12,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(28,5,'Dambulla Cave Temples','Visit historic Buddhist cave temples','Cultural',3.00,'06:00:00','17:00:00',1500.00,3000.00,2,12,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(29,6,'Bentota Yoga Session','Morning yoga session by the beach','Wellness',1.50,'06:00:00','08:00:00',2000.00,3500.00,1,15,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(30,6,'Ayurvedic Massage','Traditional Ayurvedic full-body massage','Wellness',2.00,'09:00:00','18:00:00',3500.00,6000.00,1,10,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(31,11,'Dambulla Meditation Retreat','Guided meditation and relaxation program','Wellness',3.00,'06:00:00','12:00:00',4000.00,7000.00,2,12,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(32,6,'Bentota Spa Package','Full spa day with treatments and meals','Wellness',5.00,'08:00:00','18:00:00',12000.00,20000.00,1,8,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(33,2,'Ella Sunrise Photography','Capture sunrise over tea plantations','Photography',2.00,'05:30:00','08:00:00',1000.00,2000.00,1,5,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(34,14,'Trincomalee Coastal Photography','Photograph pristine beaches and marine life','Photography',3.00,'06:00:00','11:00:00',1200.00,2500.00,1,5,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(35,4,'Mirissa Sunset Shoot','Capture sunset views over the beach','Photography',1.50,'16:30:00','18:30:00',1500.00,3000.00,1,5,'Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(36,6,'Nuwara Eliya Landscape Photography','Photograph tea estates and waterfalls','Photography',2.50,'06:00:00','11:00:00',2000.00,3500.00,1,5,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(37,12,'Jaffna Food Tour','Taste traditional Jaffna cuisine and sweets','Food & Dining',3.00,'09:00:00','14:00:00',2000.00,4000.00,1,10,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(38,5,'Kandy Street Food Walk','Sample local snacks and drinks','Food & Dining',2.00,'11:00:00','14:00:00',1500.00,3000.00,1,10,'Summer,Winter,Spring',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(39,3,'Yala Safari Picnic','Enjoy local cuisine during safari','Food & Dining',2.00,'12:00:00','14:00:00',1200.00,2000.00,2,6,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL),(40,6,'Bentota Seafood Tasting','Fresh seafood platter at beachside restaurant','Food & Dining',2.50,'18:00:00','20:30:00',2500.00,4000.00,1,8,'Summer,Winter',1,'2025-10-04 16:05:58',1,'2025-10-04 16:05:58',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_history`
--

DROP TABLE IF EXISTS `activities_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `number_of_participate` int DEFAULT NULL,
  `activity_start` datetime DEFAULT NULL,
  `activity_end` datetime DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `special_note` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_schedule_id` (`activity_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_history_ibfk_1` FOREIGN KEY (`activity_schedule_id`) REFERENCES `activities_schedule` (`id`),
  CONSTRAINT `activities_history_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_history`
--

LOCK TABLES `activities_history` WRITE;
/*!40000 ALTER TABLE `activities_history` DISABLE KEYS */;
INSERT INTO `activities_history` VALUES (1,1,'Sigiriya Climb - Group A','Morning climb with great visibility',1,8,'2025-10-15 06:30:00','2025-10-15 10:00:00',4.70,'Clear skies, excellent photos','2025-10-04 16:15:24',1,'2025-10-04 16:15:24',NULL,NULL,NULL),(2,3,'Yala Safari - Morning','Spotted 2 leopards and elephant herd',1,6,'2025-12-05 05:30:00','2025-12-05 09:30:00',5.00,'Rare double leopard sighting','2025-10-04 16:15:24',1,'2025-10-04 16:15:24',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_history_images`
--

DROP TABLE IF EXISTS `activities_history_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_history_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activities_history_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activities_history_id` (`activities_history_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_history_images_ibfk_1` FOREIGN KEY (`activities_history_id`) REFERENCES `activities_history` (`id`),
  CONSTRAINT `activities_history_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_history_images`
--

LOCK TABLES `activities_history_images` WRITE;
/*!40000 ALTER TABLE `activities_history_images` DISABLE KEYS */;
INSERT INTO `activities_history_images` VALUES (1,1,'Sunrise at Sigiriya','Beautiful sunrise during morning climb','/images/activity-history/sunrise-at-sigiriya.jpg',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL),(2,1,'Group at Summit','Participants at the top','/images/activity-history/group-at-summit.webp',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL),(3,1,'Ancient Frescoes','Famous Sigiriya frescoes','/images/activity-history/ancient-frescoes.jpg',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL),(4,2,'Leopard Sighting','One of two leopards spotted','/images/activity-history/leopard-sighting.jpg',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL),(5,2,'Second Leopard','Rare second leopard on same safari','/images/activity-history/second-leopard.jpg',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL),(6,2,'Elephant Herd','Large elephant herd near waterhole','/images/activity-history/elephant-herd.jpg',1,'2025-10-04 19:45:07',1,'2025-10-04 19:45:07',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_history_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_images`
--

DROP TABLE IF EXISTS `activities_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_images_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`),
  CONSTRAINT `activities_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_images`
--

LOCK TABLES `activities_images` WRITE;
/*!40000 ALTER TABLE `activities_images` DISABLE KEYS */;
INSERT INTO `activities_images` VALUES (1,1,'Sigiriya Rock Summit','View from the top of Sigiriya Rock','/images/activities/sigiriya-rock-summit.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(2,1,'Sigiriya Rock Path','Steps and climbing path to the summit','/images/activities/sigiriyarock-path.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(3,2,'Knuckles Trail View','Scenic trail in Knuckles Range','/images/activities/knuckles-trail-view.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(4,2,'Knuckles Forest','Dense forest along trekking path','/images/activities/knuckles-forest.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(5,3,'Rafting Rapids','Adventure on Kitulgala river rapids','/images/activities/rafting-rapids.jpeg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(6,3,'Rafting Team','Group navigating the rapids','/images/activities/rafting-team.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(7,4,'Knuckles Camp Setup','Tent and campfire at Knuckles mountains','/images/activities/knuckles-camp-setup.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(8,4,'Starry Night','Night sky view during camping','/images/activities/starry-night.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(9,5,'Surf Lessons Beach','Beginner surfing session','/images/activities/surf-lessons-beach.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(10,5,'Surf Instructor','Instructor guiding students','/images/activities/surf-instructor.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(11,6,'Kayak on River','Tourist paddling along Bentota River','/images/activities/kayak-on-river.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(12,6,'River View','Scenic river and surrounding greenery','/images/activities/river-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(13,7,'Arugam Bay Waves','Surfers catching waves at Arugam Bay','/images/activities/arugambay-waves.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(14,7,'Surf Team','Group of surfers enjoying the ocean','/images/activities/surf-team.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(15,8,'Jet Ski Splash','High-speed jet ski ride on Mirissa beach','/images/activities/jet-ski-splash.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(16,8,'Coastal View','Rider speeding along the coastline','/images/activities/coastal-view.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(17,9,'Yala Leopard Spotting','Leopard seen in Yala National Park','/images/activities/yala-leopard-spotting.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(18,9,'Elephant Herd','Elephants crossing the trail during safari','/images/activities/elephant-herd.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(19,10,'Elephant Closeup','Close encounter with wild elephants','/images/activities/elephant-closeup.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(20,10,'Safari Jeep','Jeep moving through Udawalawe National Park','/images/activities/safari-jeep.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(21,11,'Wilpattu Leopard','Leopard resting in Wilpattu National Park','/images/activities/wilpattu-leopard.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(22,11,'Bird Watching','Birds in the Wilpattu park scenery','/images/activities/bird-watching.jpeg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(23,12,'Elephant Gathering','Mass elephant gathering in Minneriya','/images/activities/elephant-gathering.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(24,12,'Sunset View','Elephants silhouetted against sunset','/images/activities/sunset-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(25,13,'Blue Whale Spotting','Whale surfacing in ocean','/images/activities/blue-whale-spotting.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(26,13,'Whale Watching Boat','Tourists on boat spotting whales','/images/activities/whale-watching-boat.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(27,14,'Playful Dolphins','Dolphins jumping near the boat','/images/activities/playful-dolphins.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(28,14,'Dolphin Tour','Cruise enjoying marine wildlife','/images/activities/dolphin-tour.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(29,15,'Coral Reefs','Snorkeling among vibrant coral reefs','/images/activities/coral-reefs.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(30,15,'Tropical Fish','Colorful fish seen while snorkeling','/images/activities/tropical-fish.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(31,16,'Glass-Bottom View','View marine life through glass-bottom boat','/images/activities/glass-bottom-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(32,16,'Marine Species','Fish and coral visible under boat','/images/activities/marine-species.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(33,17,'Nine Arch Bridge','View of iconic Nine Arch Bridge','/images/activities/nine-arch-bridge.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(34,17,'Train Crossing','Train crossing Nine Arch Bridge','/images/activities/train-crossing.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(35,18,'Galle Fort Walls','UNESCO fort walls and streets','/images/activities/galle-fort-walls.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(36,18,'Historic Streets','Walking tour inside Galle Fort','/images/activities/historic-streets.jpeg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(37,19,'Gregory Lake','Tourist enjoying boat ride at Gregory Lake','/images/activities/gregory -lake.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(38,19,'Botanical Gardens','Colorful flowers at botanical gardens','/images/activities/botanical-gardens.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(39,20,'Jaffna Temple','Historic temple in Jaffna city','/images/activities/jaffna-temple.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(40,20,'Street Exploration','Walking along local streets of Jaffna','/images/activities/street-exploration.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(41,21,'Little Adams Peak','View from top of Little Adams Peak','/images/activities/little-adams-peak.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(42,21,'Hiking Path','Trail leading up Little Adams Peak','/images/activities/hiking-path.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(43,22,'Pidurangala Summit','View from Pidurangala Rock','/images/activities/pidurangala-summit.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(44,22,'Climb Path','Pathway up Pidurangala Rock','/images/activities/climb-path.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(45,23,'Worlds End View','Panoramic view from Horton Plains','/images/activities/worlds-end-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(46,23,'Baker\'s Falls','Waterfall along Horton Plains trail','/images/activities/bakers-falls.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(47,24,'Ella Rock Summit','Scenic view from Ella Rock','/images/activities/ella-rock-summit.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(48,24,'Tea Plantation Trail','Trail passing through tea plantations','/images/activities/tea-plantation-trail.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(49,25,'Temple of Tooth Exterior','Main entrance and exterior of temple','/images/activities/temple-of-tooth-exterior.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(50,25,'Temple Interior','Inner sanctum and sacred relics','/images/activities/temple-interior.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(51,26,'Elephant Procession','Decorated elephants during Perahera festival','/images/activities/elephant-procession.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(52,26,'Cultural Dance','Traditional dancers performing','/images/activities/cultural-dance.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(53,27,'Ancient Ruins','Historical ruins in Polonnaruwa','/images/activities/ancient-ruins.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(54,27,'Temple Site','Buddhist temple ruins','/images/activities/temple-site.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(55,28,'Cave Temple Interior','Buddhist statues inside cave','/images/activities/cave-temple-interior.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(56,28,'Temple Exterior','Entrance to Dambulla Cave Temples','/images/activities/temple-exterior.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(57,29,'Morning Yoga','Yoga session at sunrise on beach','/images/activities/morning-yoga.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(58,29,'Yoga Pose','Participants performing yoga poses','/images/activities/yoga-pose.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(59,30,'Massage Room','Traditional Ayurvedic massage setup','/images/activities/massage-room.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(60,30,'Massage Therapy','Therapist performing massage','/images/activities/massage-therapy.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(61,31,'Meditation Hall','Indoor meditation session','/images/activities/meditation-hall.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(62,31,'Relaxation Garden','Meditation in serene garden','/images/activities/relaxation-garden.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(63,32,'Spa Interior','Luxurious spa treatment room','/images/activities/spa-interior.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(64,32,'Massage & Therapy','Spa therapist with client','/images/activities/massage-therapy.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(65,33,'Sunrise View','Sun rising over Ella landscape','/images/activities/sunrise-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(66,33,'Photography Spot','Tourist taking photos at viewpoint','/images/activities/photography-spot.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(67,34,'Trincomalee Beach','Photographing pristine coastline','/images/activities/trincomalee-beach.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(68,34,'Marine Life Photography','Capturing ocean wildlife','/images/activities/marine-life-photography.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(69,35,'Sunset Beach','Sunset over Mirissa beach','/images/activities/sunset-beach.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(70,35,'Golden Hour','Photographer capturing golden hour','/images/activities/golden-hour.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(71,36,'Tea Estate View','Photographing tea plantations','/images/activities/tea-estate-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(72,36,'Waterfall Scene','Capturing waterfall scenery','/images/activities/waterfall-scene.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(73,37,'Street Food Stall','Local cuisine tasting in Jaffna','/images/activities/street-food-stall.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(74,37,'Food Sampling','Tasting traditional dishes','/images/activities/food-sampling.webp',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(75,38,'Local Snacks','Sampling street food in Kandy','/images/activities/local-snacks.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(76,38,'Food Vendor','Vendor preparing local delicacies','/images/activities/food-vendor.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(77,39,'Safari Picnic','Picnic setup during Yala safari','/images/activities/safari-picnic.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(78,39,'Wildlife Dining','Enjoying picnic with wildlife view','/images/activities/wildlife-dining.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(79,40,'Seafood Platter','Fresh seafood served at beachside','/images/activities/seafood-platter.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL),(80,40,'Dining View','Table setup with ocean view','/images/activities/dining-view.jpg',1,'2025-10-05 05:46:05',1,'2025-10-05 05:46:05',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_requirement`
--

DROP TABLE IF EXISTS `activities_requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_requirement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` text,
  `status` int NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_requirement_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`),
  CONSTRAINT `activities_requirement_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_requirement`
--

LOCK TABLES `activities_requirement` WRITE;
/*!40000 ALTER TABLE `activities_requirement` DISABLE KEYS */;
INSERT INTO `activities_requirement` VALUES (1,1,'Fitness Level','Moderate','Able to climb 1200 steps',1,'#FFC107','2025-10-04 16:15:18',1,'2025-10-04 16:15:18',NULL,NULL,NULL),(2,1,'Age Limit','8+','Children under 8 not recommended',1,'#FF5722','2025-10-04 16:15:18',1,'2025-10-04 16:15:18',NULL,NULL,NULL),(3,3,'Fitness Level','Easy','Suitable for most fitness levels',1,'#4CAF50','2025-10-04 16:15:18',1,'2025-10-04 16:15:18',NULL,NULL,NULL),(4,4,'Group Size','Min 2','At least 2 people required per jeep',1,'#2196F3','2025-10-04 16:15:18',1,'2025-10-04 16:15:18',NULL,NULL,NULL),(5,5,'Swimming Ability','Not Required','Life jackets provided',1,'#00BCD4','2025-10-04 16:15:18',1,'2025-10-04 16:15:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_review`
--

DROP TABLE IF EXISTS `activities_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `review` text,
  `rating` decimal(3,2) DEFAULT NULL,
  `description` text,
  `status` int NOT NULL,
  `number_of_participate` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_schedule_id` (`activity_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_review_ibfk_1` FOREIGN KEY (`activity_schedule_id`) REFERENCES `activities_schedule` (`id`),
  CONSTRAINT `activities_review_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_review`
--

LOCK TABLES `activities_review` WRITE;
/*!40000 ALTER TABLE `activities_review` DISABLE KEYS */;
INSERT INTO `activities_review` VALUES (1,1,'Incredible Rock Fortress','Early morning climb was the best decision. Fewer crowds and amazing sunrise views from the top.',4.70,'Must-do activity in Sri Lanka',1,8,'2025-10-04 16:15:34',1,'2025-10-04 16:15:34',NULL,NULL,NULL),(2,3,'Unforgettable Safari','We saw leopards, elephants, and so many birds. Our driver/tracker was experienced and knew all the best spots.',5.00,'Best wildlife experience',1,6,'2025-10-04 16:15:34',1,'2025-10-04 16:15:34',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_review_images`
--

DROP TABLE IF EXISTS `activities_review_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_review_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activities_review_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activities_review_id` (`activities_review_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_review_images_ibfk_1` FOREIGN KEY (`activities_review_id`) REFERENCES `activities_review` (`id`),
  CONSTRAINT `activities_review_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_review_images`
--

LOCK TABLES `activities_review_images` WRITE;
/*!40000 ALTER TABLE `activities_review_images` DISABLE KEYS */;
INSERT INTO `activities_review_images` VALUES (1,1,'Guest Sunrise Photo','Beautiful sunrise captured by guest','/images/activities-review/sunrise-photo.jpg',1,'2025-10-04 19:54:12',1,'2025-10-04 19:54:12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_review_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activities_schedule`
--

DROP TABLE IF EXISTS `activities_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `activity_id` int NOT NULL,
  `assume_start_date` date DEFAULT NULL,
  `assume_end_date` date DEFAULT NULL,
  `duration_hours_start` decimal(5,2) DEFAULT NULL,
  `duration_hours_end` decimal(5,2) DEFAULT NULL,
  `special_note` text,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  KEY `status` (`status`),
  CONSTRAINT `activities_schedule_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`),
  CONSTRAINT `activities_schedule_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_schedule`
--

LOCK TABLES `activities_schedule` WRITE;
/*!40000 ALTER TABLE `activities_schedule` DISABLE KEYS */;
INSERT INTO `activities_schedule` VALUES (1,'Sigiriya Morning Climb',1,'2025-10-01','2025-12-31',3.00,4.00,'Start early to avoid heat','Daily morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(2,'Sigiriya Evening Climb',1,'2025-10-01','2025-12-31',3.00,4.00,'Sunset climb recommended','Evening departures available',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(3,'Sigiriya Weekend Climb',1,'2025-10-01','2025-12-31',3.50,4.50,'Weekend guided climb','Limited slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(4,'Knuckles Morning Trek',2,'2025-10-01','2025-11-30',5.00,6.00,'Bring water and snacks','Daily morning departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(5,'Knuckles Sunset Trek',2,'2025-10-01','2025-11-30',5.00,6.00,'Sunset scenic points included','Evening departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(6,'Knuckles Weekend Trek',2,'2025-10-01','2025-11-30',6.00,7.00,'Overnight camping option','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(7,'Kitulgala Morning Rafting',3,'2025-10-01','2025-12-31',3.50,4.00,'Safety gear provided','Daily departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(8,'Kitulgala Afternoon Rafting',3,'2025-10-01','2025-12-31',3.50,4.00,'Guided rafting included','Afternoon sessions',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(9,'Kitulgala Weekend Rafting',3,'2025-10-01','2025-12-31',4.00,4.50,'Peak water flow season','Weekend slots only',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(10,'Surfing Lessons Morning',4,'2025-10-01','2026-03-31',2.00,2.50,'Beginner friendly','Morning sessions',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(11,'Surfing Lessons Afternoon',4,'2025-10-01','2026-03-31',2.00,2.50,'Intermediate level','Afternoon sessions',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(12,'Surfing Weekend Camp',4,'2025-10-01','2026-03-31',4.00,5.00,'3-day surf camp','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(13,'Kayaking Morning',5,'2025-10-01','2026-03-31',2.50,3.00,'Calm water sections','Morning departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(14,'Kayaking Afternoon',5,'2025-10-01','2026-03-31',2.50,3.00,'Scenic route','Afternoon departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(15,'Kayaking Weekend',5,'2025-10-01','2026-03-31',3.00,3.50,'Guided tour','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(16,'Yala Morning Safari',6,'2025-10-01','2026-02-28',3.50,4.50,'Higher chance of leopard sightings','Early morning departure',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(17,'Yala Afternoon Safari',6,'2025-10-01','2026-02-28',3.50,4.50,'Sunset wildlife tour','Afternoon departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(18,'Yala Weekend Safari',6,'2025-10-01','2026-02-28',4.00,5.00,'Guided safari','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(19,'Udawalawe Morning Safari',7,'2025-10-01','2026-02-28',4.50,5.00,'Elephant sightings expected','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(20,'Udawalawe Afternoon Safari',7,'2025-10-01','2026-02-28',4.50,5.00,'Sunset safari included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(21,'Udawalawe Weekend Safari',7,'2025-10-01','2026-02-28',5.00,6.00,'Guided safari','Weekend departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(22,'Whale Watching Morning',8,'2025-11-01','2026-04-30',3.50,4.50,'Best sighting months','Morning departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(23,'Whale Watching Afternoon',8,'2025-11-01','2026-04-30',3.50,4.50,'Afternoon cruises','Limited slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(24,'Whale Watching Weekend',8,'2025-11-01','2026-04-30',4.00,5.00,'Guided tours','Weekend departures',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(25,'Nine Arch Bridge Morning Walk',9,'2025-10-01','2026-03-31',1.50,2.00,'Best during train passing','Morning sessions',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(26,'Nine Arch Bridge Afternoon Walk',9,'2025-10-01','2026-03-31',1.50,2.00,'Scenic photography','Afternoon sessions',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(27,'Nine Arch Bridge Weekend Walk',9,'2025-10-01','2026-03-31',2.00,2.50,'Guided tours','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(28,'Little Adams Peak Morning Hike',10,'2025-10-01','2026-03-31',2.00,2.50,'Panoramic views','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(29,'Little Adams Peak Afternoon Hike',10,'2025-10-01','2026-03-31',2.00,2.50,'Sunset view included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(30,'Little Adams Peak Weekend Hike',10,'2025-10-01','2026-03-31',2.50,3.00,'Guided hike','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(31,'Kandy Temple Morning Visit',11,'2025-10-01','2026-03-31',1.50,2.00,'Temple visit with guide','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(32,'Kandy Temple Afternoon Visit',11,'2025-10-01','2026-03-31',1.50,2.00,'Cultural show included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(33,'Kandy Temple Weekend Visit',11,'2025-10-01','2026-03-31',2.00,2.50,'Guided cultural experience','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(34,'Bentota Yoga Morning',12,'2025-10-01','2026-03-31',1.50,2.00,'Sunrise session','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(35,'Bentota Yoga Afternoon',12,'2025-10-01','2026-03-31',1.50,2.00,'Beach yoga included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(36,'Bentota Weekend Yoga Retreat',12,'2025-10-01','2026-03-31',2.00,3.00,'Meditation and relaxation','Weekend retreats',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(37,'Ella Sunrise Photography Morning',13,'2025-10-01','2026-03-31',2.00,2.50,'Capture sunrise views','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(38,'Ella Sunrise Photography Afternoon',13,'2025-10-01','2026-03-31',2.00,2.50,'Golden hour included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(39,'Ella Sunrise Photography Weekend',13,'2025-10-01','2026-03-31',2.50,3.00,'Guided photography tour','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(40,'Jaffna Food Tour Morning',14,'2025-10-01','2026-03-31',3.00,3.50,'Traditional dishes','Morning slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(41,'Jaffna Food Tour Afternoon',14,'2025-10-01','2026-03-31',3.00,3.50,'Cooking demo included','Afternoon slots',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL),(42,'Jaffna Food Tour Weekend',14,'2025-10-01','2026-03-31',3.50,4.00,'Guided tasting','Weekend batches',1,'2025-10-04 16:08:12',1,'2025-10-04 16:08:12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activities_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_category`
--

DROP TABLE IF EXISTS `activity_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_category_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_category`
--

LOCK TABLES `activity_category` WRITE;
/*!40000 ALTER TABLE `activity_category` DISABLE KEYS */;
INSERT INTO `activity_category` VALUES (1,'Adventure','Thrilling outdoor activities like climbing, hiking, and trekking',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(2,'Water Sports','Ocean and water-based activities including surfing, diving, and snorkeling',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(3,'Wildlife','Animal watching and safari experiences',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(4,'Marine Life','Ocean wildlife experiences like whale watching and dolphin tours',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(5,'Sightseeing','Visiting landmarks, viewpoints, and scenic locations',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(6,'Hiking','Mountain and nature trail walking activities',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(7,'Cultural','Temple visits, cultural shows, and heritage experiences',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(8,'Wellness','Yoga, meditation, spa, and relaxation activities',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(9,'Photography','Specialized photo tours and sessions',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL),(10,'Food & Dining','Culinary experiences, cooking classes, and food tours',1,'2025-10-04 16:04:35',1,'2025-10-04 16:04:35',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_category_images`
--

DROP TABLE IF EXISTS `activity_category_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_category_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_category_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_category_id` (`activity_category_id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_category_images_ibfk_1` FOREIGN KEY (`activity_category_id`) REFERENCES `activity_category` (`id`),
  CONSTRAINT `activity_category_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_category_images`
--

LOCK TABLES `activity_category_images` WRITE;
/*!40000 ALTER TABLE `activity_category_images` DISABLE KEYS */;
INSERT INTO `activity_category_images` VALUES (1,1,'Rock Climbing','Adventurers scaling cliffs in Ella','/images/activity-categories/rock-climbing.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(2,1,'Zip Lining','Thrilling zipline ride across lush valleys','/images/activity-categories/zip-lining.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(3,1,'Caving','Exploring mysterious caves and underground paths','/images/activity-categories/caving.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(4,1,'Trekking Trail','Group trekking through dense forest terrain','/images/activity-categories/trekking-trail.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(5,2,'Surfing','Surfers riding the waves at Arugam Bay','/images/activity-categories/surfing.png',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(6,2,'Scuba Diving','Exploring coral reefs and marine life underwater','/images/activity-categories/scuba-diving.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(7,2,'Jet Skiing','Tourists enjoying high-speed jet skiing','/images/activity-categories/jet-skiing.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(8,2,'Snorkeling','Colorful reef fish viewed from surface snorkel','/images/activity-categories/snorkeling.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(9,3,'Safari Jeep','Jeep driving through Yala National Park','/images/activity-categories/safari-jeep.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(10,3,'Bird Watching','Spotting exotic birds in Bundala Sanctuary','/images/activity-categories/bird-watching.webp',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(11,3,'Elephants in Wild','Herd of elephants grazing in Udawalawe','/images/activity-categories/elephants-in-wild.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(12,3,'Leopard Sighting','Rare leopard encounter at Wilpattu Park','/images/activity-categories/leopard-sighting.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(13,4,'Whale Watching','Whales breaching off Mirissa coast','/images/activity-categories/whale-watching.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(14,4,'Dolphin Tour','Dolphins swimming alongside boats','/images/activity-categories/dolphin-tour.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(15,4,'Sea Turtle Experience','Visitors observing sea turtles nesting','/images/activity-categories/sea-turtle-experience.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(16,4,'Underwater World','Colorful marine species in coral reefs','/images/activity-categories/underwater-world.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(17,5,'Scenic Viewpoint','Tourists enjoying panoramic mountain views','/images/activity-categories/scenic-viewpoint.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(18,5,'City Tour','Exploring historic streets and monuments','/images/activity-categories/city-tour.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(19,5,'Sunset Point','Beautiful sunset over coastal cliffs','/images/activity-categories/sunset-point.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(20,5,'Waterfall Visit','Tourists visiting a majestic waterfall','/images/activity-categories/waterfall-visit.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(21,6,'Mountain Trail','Hikers climbing misty mountain paths','/images/activity-categories/mountain-trail.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(22,6,'Forest Path','Exploring dense forest hiking trails','/images/activity-categories/forest-path.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(23,6,'Peak Summit','Trekkers reaching the top viewpoint','/images/activity-categories/peak-summit.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(24,6,'River Crossing','Adventure hikers crossing shallow streams','/images/activity-categories/river-crossing.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(25,7,'Temple Visit','Visitors exploring sacred temples','/images/activity-categories/temple-visit.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(26,7,'Traditional Dance','Cultural dancers performing in Kandy','/images/activity-categories/traditional-dance.webp',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(27,7,'Ancient Ruins','Tourists discovering heritage ruins','/images/activity-categories/ancient-ruins.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(28,7,'Cultural Festival','Vibrant street festival with costumes and music','/images/activity-categories/cultural-festival.png',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(29,8,'Yoga Retreat','Morning yoga session in a peaceful environment','/images/activity-categories/yoga-retreat.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(30,8,'Meditation','Meditation practice near a waterfall','/images/activity-categories/meditation.jpeg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(31,8,'Spa Therapy','Relaxing herbal spa treatments','/images/activity-categories/spa-therapy.webp',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(32,8,'Ayurvedic Massage','Traditional ayurvedic healing experience','/images/activity-categories/ayurvedic-massage.webp',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(33,9,'Wildlife Photography','Photographer capturing animals in the wild','/images/activity-categories/wildlife-photography.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(34,9,'Landscape Shot','Capturing breathtaking sunrise over hills','/images/activity-categories/landscape-shot.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(35,9,'Cultural Portraits','Portrait photography during cultural events','/images/activity-categories/cultural-portraits.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(36,9,'Night Photography','Stars and night sky captured in long exposure','/images/activity-categories/night-photography.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(37,10,'Local Cuisine','Authentic Sri Lankan rice and curry served on banana leaf','/images/activity-categories/local-cuisine.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(38,10,'Cooking Class','Tourists learning traditional cooking methods','/images/activity-categories/cooking-class.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(39,10,'Street Food','Colorful local food stalls in Colombo','/images/activity-categories/street-food.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL),(40,10,'Fine Dining','Elegant restaurant setup with local dishes','/images/activity-categories/fine-dining.jpg',1,'2025-10-05 04:56:52',1,'2025-10-05 04:56:52',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_category_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_hero_section`
--

DROP TABLE IF EXISTS `activity_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_hero_section`
--

LOCK TABLES `activity_hero_section` WRITE;
/*!40000 ALTER TABLE `activity_hero_section` DISABLE KEYS */;
INSERT INTO `activity_hero_section` VALUES (1,'sigiriya-climb','https://images.unsplash.com/photo-1548013146-72479768bada?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Climb Sigiriya Rock Fortress','Ancient Rock Palace & UNESCO Site','Ascend the 5th-century rock fortress with its legendary lion gate, ancient frescoes, and breathtaking 360° views of the surrounding jungle.','Book Climb','/activities/sigiriya-climb','History Guide','/guide/sigiriya-history',1,1,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(2,'yala-safari','https://images.unsplash.com/photo-1579444741990-6e31c9b09d52?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Yala National Park Safari','Leopard Spotting & Wildlife Adventure','Morning and evening jeep safaris in Sri Lanka\'s most famous national park, home to leopards, elephants, crocodiles, and diverse bird species.','Book Safari','/activities/yala-safari','Wildlife Guide','/guide/yala-wildlife',1,2,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(3,'ella-train','https://images.unsplash.com/photo-1592210454359-9043f067919b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Ella Scenic Train Ride','Most Beautiful Train Journey in Asia','Experience the iconic train ride from Ella to Kandy or Nuwara Eliya, passing through tea plantations, misty mountains, and spectacular viaducts.','Book Tickets','/activities/ella-train','Train Schedule','/schedule/train-timings',1,3,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(4,'mirissa-whale','https://images.unsplash.com/photo-1552465011-b4e30bf7349d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Mirissa Whale Watching','Blue Whale & Dolphin Excursions','Early morning boat tours to spot blue whales, sperm whales, and dolphins in the deep waters off Sri Lanka\'s southern coast.','Whale Tours','/activities/mirissa-whale','Season Guide','/guide/whale-season',1,4,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(5,'kandy-cultural','https://images.unsplash.com/photo-1558272729-5e0165e4fde6?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Kandy Cultural Show','Traditional Dance & Temple of the Tooth','Evening cultural performances featuring traditional Kandyan dances, fire walking, and visits to the sacred Temple of the Tooth Relic.','Cultural Tickets','/activities/kandy-cultural','Performance Times','/schedule/cultural-shows',1,5,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(6,'arugam-bay-surf','https://images.unsplash.com/photo-1506929562872-bb421503ef21?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Arugam Bay Surfing','Best Surf Point in Sri Lanka','Beginner to advanced surfing lessons at Sri Lanka\'s premier surf destination. Equipment rental and experienced instructors available.','Surf Lessons','/activities/arugam-bay-surf','Wave Report','/report/surf-conditions',1,6,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(7,'adam-peak-hike','https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Adam\'s Peak Pilgrimage','Sunrise Hike to Sacred Mountain','Night hike to summit Adam\'s Peak (Sri Pada) for sunrise views. A spiritual journey followed by thousands of pilgrims each season.','Hike Booking','/activities/adam-peak-hike','Season Dates','/guide/adams-peak-season',1,7,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(8,'galle-fort','https://images.unsplash.com/photo-1528181304800-259b08848526?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Galle Fort Walking Tour','Dutch Colonial Heritage Site','Guided walking tour through UNESCO-listed Galle Fort, exploring colonial architecture, museums, lighthouse, and boutique shops.','Book Tour','/activities/galle-fort','Fort Map','/map/galle-fort',1,8,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(9,'pinnewala-elephant','https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Pinnawala Elephant Bath','Elephant Orphanage & River Bathing','Visit the world-famous elephant orphanage and witness the daily river bathing ritual where dozens of elephants play in the water.','Visit Orphanage','/activities/pinnewala-elephant','Feeding Times','/schedule/elephant-feeding',1,9,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(10,'kitulgala-rafting','https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Kitulgala White Water Rafting','Adventure on the Kelani River','Grade 2-3 white water rafting through tropical rainforests. Also features locations from the film \"The Bridge on the River Kwai\".','Book Rafting','/activities/kitulgala-rafting','Safety Info','/guide/rafting-safety',1,10,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(11,'anuradhapura-cycle','https://images.unsplash.com/photo-1536152471326-642d4aa9cba5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Anuradhapura Cycling Tour','Ancient City Exploration by Bicycle','Cycling tour through the sacred ancient city, visiting ruins, giant stupas, and sacred Bodhi tree over 2,300 years old.','Bike Rental','/activities/anuradhapura-cycle','Route Map','/map/anuradhapura-route',1,11,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(12,'bentota-watersports','https://images.unsplash.com/photo-1544551763-46a013bb70d5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Bentota Water Sports','Jet Ski, Banana Boat & Parasailing','All-in-one water sports center offering jet skiing, banana boat rides, parasailing, windsurfing, and kayaking in the Bentota lagoon.','Water Sports','/activities/bentota-watersports','Activity Packages','/packages/watersports',1,12,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(13,'nuwara-tea','https://images.unsplash.com/photo-1523348837708-15d4a09cfac2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Nuwara Eliya Tea Tasting','Ceylon Tea Factory Tour','Tour working tea factories, learn the tea-making process from leaf to cup, and enjoy premium Ceylon tea tasting sessions.','Tea Tour','/activities/nuwara-tea','Tea Types','/guide/ceylon-tea',1,13,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(14,'polonnaruwa-ruins','https://images.unsplash.com/photo-1585506936724-fa0c19c7b7c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Polonnaruwa Archaeological Tour','Ancient Royal City Exploration','Guided tour of the medieval capital with its well-preserved ruins, giant Buddha statues, and ancient irrigation tanks.','Historical Tour','/activities/polonnaruwa-ruins','Ruins Guide','/guide/polonnaruwa-history',1,14,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL),(15,'dambulla-caves','https://images.unsplash.com/photo-1579444741963-5bce5eb9d1d2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Dambulla Cave Temples','Golden Temple & Buddha Statues','Explore five ancient cave temples filled with Buddha statues and intricate frescoes dating back to the 1st century BC.','Temple Visit','/activities/dambulla-caves','Religious Significance','/guide/cave-temples',1,15,'2025-12-16 03:32:43',1,'2025-12-16 03:32:43',1,NULL,NULL);
/*!40000 ALTER TABLE `activity_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_review_comment`
--

DROP TABLE IF EXISTS `activity_review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_review_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `comment` text NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_review_id` (`activity_review_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_review_comment_ibfk_1` FOREIGN KEY (`activity_review_id`) REFERENCES `activities_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `activity_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_review_comment`
--

LOCK TABLES `activity_review_comment` WRITE;
/*!40000 ALTER TABLE `activity_review_comment` DISABLE KEYS */;
INSERT INTO `activity_review_comment` VALUES (1,1,1,NULL,'Absolutely agree! The sunrise was breathtaking.',1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(2,1,2,NULL,'Did you find the climb difficult? Planning to go next month.',1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL),(3,1,1,NULL,'It was a bit challenging but totally worth it.',1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(4,2,2,NULL,'Wow! Sounds amazing, which park was this?',1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL),(5,2,1,NULL,'This is on my bucket list, thanks for sharing!',1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_review_comment_reaction`
--

DROP TABLE IF EXISTS `activity_review_comment_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_review_comment_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_comment_user_reaction` (`comment_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_review_comment_reaction_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `activity_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `activity_review_comment_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_review_comment_reaction`
--

LOCK TABLES `activity_review_comment_reaction` WRITE;
/*!40000 ALTER TABLE `activity_review_comment_reaction` DISABLE KEYS */;
INSERT INTO `activity_review_comment_reaction` VALUES (1,1,2,1,1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL),(2,2,1,2,1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(3,3,2,1,1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL),(4,4,1,3,1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(5,5,2,1,1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_review_comment_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_review_reaction`
--

DROP TABLE IF EXISTS `activity_review_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_review_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_review_user_reaction` (`activity_review_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `activity_review_reaction_ibfk_1` FOREIGN KEY (`activity_review_id`) REFERENCES `activities_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_review_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `activity_review_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_review_reaction`
--

LOCK TABLES `activity_review_reaction` WRITE;
/*!40000 ALTER TABLE `activity_review_reaction` DISABLE KEYS */;
INSERT INTO `activity_review_reaction` VALUES (1,1,1,1,1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(2,1,2,2,1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL),(3,2,1,1,1,'2025-10-21 15:33:27',1,'2025-10-21 15:33:27',NULL,NULL,NULL),(4,2,2,3,1,'2025-10-21 15:33:27',2,'2025-10-21 15:33:27',NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity_review_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_wishlist`
--

DROP TABLE IF EXISTS `activity_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `activity_wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_wishlist_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_wishlist_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_wishlist`
--

LOCK TABLES `activity_wishlist` WRITE;
/*!40000 ALTER TABLE `activity_wishlist` DISABLE KEYS */;
INSERT INTO `activity_wishlist` VALUES (1,1,1,1,'2025-11-29 05:01:58'),(2,1,2,1,'2025-11-29 05:01:58'),(3,2,3,2,'2025-11-29 05:01:58'),(4,2,4,1,'2025-11-29 05:01:58');
/*!40000 ALTER TABLE `activity_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_wishlist_history`
--

DROP TABLE IF EXISTS `activity_wishlist_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_wishlist_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `wishlist_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`),
  KEY `wishlist_id` (`wishlist_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `activity_wishlist_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_wishlist_history_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_wishlist_history_ibfk_3` FOREIGN KEY (`wishlist_id`) REFERENCES `activity_wishlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_wishlist_history_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_wishlist_history`
--

LOCK TABLES `activity_wishlist_history` WRITE;
/*!40000 ALTER TABLE `activity_wishlist_history` DISABLE KEYS */;
INSERT INTO `activity_wishlist_history` VALUES (1,1,1,1,1,'2025-11-29 05:01:58'),(2,2,3,3,2,'2025-11-29 05:01:58');
/*!40000 ALTER TABLE `activity_wishlist_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'221B','Baker Street','Colombo 07','Colombo','Colombo','Western',1,'00700'),(2,'10','MG Road','Koramangala','Bangalore','Bangalore','Karnataka',2,'560095');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `air_conditioning_types`
--

DROP TABLE IF EXISTS `air_conditioning_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `air_conditioning_types` (
  `ac_type_id` int NOT NULL AUTO_INCREMENT,
  `ac_type_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`ac_type_id`),
  UNIQUE KEY `ac_type_name` (`ac_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_conditioning_types`
--

LOCK TABLES `air_conditioning_types` WRITE;
/*!40000 ALTER TABLE `air_conditioning_types` DISABLE KEYS */;
INSERT INTO `air_conditioning_types` VALUES (1,'Manual','Manual air conditioning',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,'Automatic Climate','Single-zone automatic climate control',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,'Dual-Zone','Dual-zone automatic climate control',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(4,'Tri-Zone','Tri-zone automatic climate control',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(5,'Four-Zone','Four-zone automatic climate control',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,'None','No air conditioning',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `air_conditioning_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amenity_type`
--

DROP TABLE IF EXISTS `amenity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amenity_type` (
  `amenity_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `icon_class` varchar(100) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`amenity_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `amenity_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenity_type`
--

LOCK TABLES `amenity_type` WRITE;
/*!40000 ALTER TABLE `amenity_type` DISABLE KEYS */;
INSERT INTO `amenity_type` VALUES (1,'WiFi','Wireless Internet','Technology','wifi',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'Pool','Swimming Pool','Recreation','pool',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'Gym','Fitness Center','Recreation','gym',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'Spa','Spa Services','Wellness','spa',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'Parking','Car Parking','Transport','parking',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `amenity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `benefit_type`
--

DROP TABLE IF EXISTS `benefit_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `benefit_type` (
  `benefit_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`benefit_type_id`),
  KEY `fk_benefit_type_status` (`status_id`),
  CONSTRAINT `fk_benefit_type_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `benefit_type`
--

LOCK TABLES `benefit_type` WRITE;
/*!40000 ALTER TABLE `benefit_type` DISABLE KEYS */;
INSERT INTO `benefit_type` VALUES (1,'DISCOUNT','Percentage discount on bookings',1,'2025-09-24 05:37:26',1,'2025-09-24 05:37:26',NULL,NULL,NULL),(2,'CASHBACK','Cashback on payments',1,'2025-09-24 05:37:26',1,'2025-09-24 05:37:26',NULL,NULL,NULL),(3,'FREE_SERVICE','Free service or add-on',1,'2025-09-24 05:37:26',1,'2025-09-24 05:37:26',NULL,NULL,NULL),(7,'DISCOUNT','Percentage discount on bookings',1,'2025-09-24 05:42:51',1,'2025-09-24 05:42:51',NULL,NULL,NULL),(8,'CASHBACK','Cashback on payments',1,'2025-09-24 05:42:51',1,'2025-09-24 05:42:51',NULL,NULL,NULL),(9,'FREE_SERVICE','Free service or add-on',1,'2025-09-24 05:42:51',1,'2025-09-24 05:42:51',NULL,NULL,NULL);
/*!40000 ALTER TABLE `benefit_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_bookmarks`
--

DROP TABLE IF EXISTS `blog_bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_bookmarks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `blog_id` int NOT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_bookmarks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `blog_bookmarks_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`),
  CONSTRAINT `blog_bookmarks_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_bookmarks`
--

LOCK TABLES `blog_bookmarks` WRITE;
/*!40000 ALTER TABLE `blog_bookmarks` DISABLE KEYS */;
INSERT INTO `blog_bookmarks` VALUES (1,1,1,2,'2025-12-12 17:43:32',1,'2025-12-12 18:49:48',NULL,'2025-12-12 18:49:48',1),(2,1,3,1,'2025-12-12 17:43:32',1,'2025-12-12 17:43:32',NULL,NULL,NULL),(3,1,5,1,'2025-12-12 17:43:32',1,'2025-12-12 17:43:32',NULL,NULL,NULL),(4,2,2,1,'2025-12-12 17:43:32',2,'2025-12-12 17:43:32',NULL,NULL,NULL),(5,2,4,1,'2025-12-12 17:43:32',2,'2025-12-12 17:43:32',NULL,NULL,NULL),(6,2,6,1,'2025-12-12 17:43:32',2,'2025-12-12 17:43:32',NULL,NULL,NULL),(7,3,1,1,'2025-12-12 17:43:32',3,'2025-12-12 17:43:32',NULL,NULL,NULL),(8,3,7,1,'2025-12-12 17:43:32',3,'2025-12-12 17:43:32',NULL,NULL,NULL),(9,3,10,1,'2025-12-12 17:43:32',3,'2025-12-12 17:43:32',NULL,NULL,NULL),(15,1,12,1,'2025-12-12 18:07:45',1,'2025-12-12 18:07:45',NULL,NULL,NULL),(16,1,10,1,'2025-12-12 18:22:00',1,'2025-12-12 18:22:00',NULL,NULL,NULL),(17,1,1,2,'2025-12-12 18:51:28',1,'2025-12-12 18:51:46',NULL,'2025-12-12 18:51:46',1),(18,1,1,2,'2025-12-12 19:00:54',1,'2025-12-12 19:00:56',NULL,'2025-12-12 19:00:56',1),(19,1,1,2,'2025-12-12 19:00:57',1,'2025-12-12 19:00:58',NULL,'2025-12-12 19:00:58',1),(20,1,1,2,'2025-12-12 19:00:59',1,'2025-12-12 19:01:00',NULL,'2025-12-12 19:01:00',1),(21,1,1,2,'2025-12-12 19:01:00',1,'2025-12-12 19:01:01',NULL,'2025-12-12 19:01:01',1),(22,1,1,2,'2025-12-12 19:01:03',1,'2025-12-12 19:01:05',NULL,'2025-12-12 19:01:05',1),(23,1,1,2,'2025-12-12 19:01:26',1,'2025-12-12 19:01:37',NULL,'2025-12-12 19:01:37',1),(24,1,1,2,'2025-12-12 19:01:43',1,'2025-12-12 19:01:45',NULL,'2025-12-12 19:01:45',1),(25,1,1,2,'2025-12-12 19:02:13',1,'2025-12-12 19:02:14',NULL,'2025-12-12 19:02:14',1),(26,1,1,2,'2025-12-12 19:02:16',1,'2025-12-12 19:02:17',NULL,'2025-12-12 19:02:17',1),(27,1,1,2,'2025-12-12 19:04:21',1,'2025-12-12 19:04:23',NULL,'2025-12-12 19:04:23',1),(28,1,1,2,'2025-12-12 19:04:25',1,'2025-12-12 19:04:35',NULL,'2025-12-12 19:04:35',1),(29,1,1,2,'2025-12-12 19:04:37',1,'2025-12-12 19:04:38',NULL,'2025-12-12 19:04:38',1),(30,1,1,2,'2025-12-12 19:04:39',1,'2025-12-12 19:04:39',NULL,'2025-12-12 19:04:39',1),(31,1,1,2,'2025-12-12 19:04:40',1,'2025-12-12 19:04:41',NULL,'2025-12-12 19:04:41',1),(32,1,1,2,'2025-12-12 19:04:48',1,'2025-12-12 19:04:51',NULL,'2025-12-12 19:04:51',1),(33,1,1,2,'2025-12-12 19:04:52',1,'2025-12-12 19:04:53',NULL,'2025-12-12 19:04:53',1),(34,1,1,2,'2025-12-12 19:06:14',1,'2025-12-12 19:11:31',NULL,'2025-12-12 19:11:31',1),(35,1,1,2,'2025-12-12 19:11:36',1,'2025-12-12 19:14:36',NULL,'2025-12-12 19:14:36',1),(36,1,1,2,'2025-12-12 19:14:36',1,'2025-12-12 19:14:37',NULL,'2025-12-12 19:14:37',1),(37,1,1,2,'2025-12-12 19:24:04',1,'2025-12-12 19:24:07',NULL,'2025-12-12 19:24:07',1),(38,1,1,2,'2025-12-12 19:24:08',1,'2025-12-12 19:24:12',NULL,'2025-12-12 19:24:12',1),(39,1,1,2,'2025-12-12 19:24:16',1,'2025-12-12 19:24:21',NULL,'2025-12-12 19:24:21',1),(40,1,1,2,'2025-12-12 19:24:23',1,'2025-12-12 19:31:42',NULL,'2025-12-12 19:31:42',1),(41,1,1,2,'2025-12-12 19:31:43',1,'2025-12-12 19:31:45',NULL,'2025-12-12 19:31:45',1),(42,1,1,2,'2025-12-12 19:31:50',1,'2025-12-12 19:31:54',NULL,'2025-12-12 19:31:54',1),(43,1,1,2,'2025-12-12 19:32:52',1,'2025-12-12 19:33:00',NULL,'2025-12-12 19:33:00',1),(44,1,1,2,'2025-12-13 14:45:56',1,'2025-12-13 14:45:57',NULL,'2025-12-13 14:45:57',1),(45,1,1,2,'2025-12-13 15:27:31',1,'2025-12-13 15:27:32',NULL,'2025-12-13 15:27:32',1),(46,1,1,2,'2025-12-13 15:27:38',1,'2025-12-13 15:27:39',NULL,'2025-12-13 15:27:39',1),(47,1,1,2,'2025-12-13 15:27:48',1,'2025-12-13 15:27:49',NULL,'2025-12-13 15:27:49',1),(48,1,1,2,'2025-12-13 15:31:11',1,'2025-12-13 17:52:03',NULL,'2025-12-13 17:52:03',1),(49,1,1,2,'2025-12-13 17:52:04',1,'2025-12-14 09:35:02',NULL,'2025-12-14 09:35:02',1),(50,1,1,2,'2025-12-14 09:35:03',1,'2025-12-14 09:35:05',NULL,'2025-12-14 09:35:05',1),(51,1,1,2,'2025-12-14 09:35:06',1,'2025-12-14 10:24:22',NULL,'2025-12-14 10:24:22',1);
/*!40000 ALTER TABLE `blog_bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_category`
--

DROP TABLE IF EXISTS `blog_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `status` (`status`),
  CONSTRAINT `blog_category_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_category`
--

LOCK TABLES `blog_category` WRITE;
/*!40000 ALTER TABLE `blog_category` DISABLE KEYS */;
INSERT INTO `blog_category` VALUES (1,'Adventure','Adventure blogs and activities',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(2,'Travel','Travel tips and destinations',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(3,'Technology','Tech trends and gadgets',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(4,'Food','Recipes and culinary experiences',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(5,'Lifestyle','Lifestyle, wellness, and personal growth',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(6,'Health','Health and fitness tips',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `blog_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comment_reactions`
--

DROP TABLE IF EXISTS `blog_comment_reactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_comment_reactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_comment_reactions_user` (`user_id`),
  KEY `fk_comment_reactions_type` (`reaction_type_id`),
  KEY `idx_comment_id` (`comment_id`),
  CONSTRAINT `fk_comment_reactions_comment` FOREIGN KEY (`comment_id`) REFERENCES `blog_comments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_reactions_type` FOREIGN KEY (`reaction_type_id`) REFERENCES `blog_reactions_types` (`id`),
  CONSTRAINT `fk_comment_reactions_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comment_reactions`
--

LOCK TABLES `blog_comment_reactions` WRITE;
/*!40000 ALTER TABLE `blog_comment_reactions` DISABLE KEYS */;
INSERT INTO `blog_comment_reactions` VALUES (1,1,2,1,'2025-09-28 13:51:25',2,'2025-09-28 13:51:25',NULL,NULL,NULL,1),(2,1,3,2,'2025-09-28 13:51:25',3,'2025-09-28 13:51:25',NULL,NULL,NULL,1),(3,2,1,1,'2025-09-28 13:51:25',1,'2025-12-13 15:29:13',NULL,'2025-12-13 15:29:13',1,2),(4,3,1,3,'2025-09-28 13:51:25',1,'2025-09-28 13:51:25',NULL,NULL,NULL,1),(5,4,1,1,'2025-09-28 13:51:25',1,'2025-12-13 15:27:00',NULL,'2025-12-13 15:27:00',1,2),(6,5,2,2,'2025-09-28 13:51:25',2,'2025-09-28 13:51:25',NULL,NULL,NULL,1),(7,8,1,2,'2025-12-13 14:10:23',1,'2025-12-13 14:14:43',NULL,'2025-12-13 14:14:43',1,2),(8,9,1,2,'2025-12-13 14:11:06',1,'2025-12-13 14:14:08',NULL,'2025-12-13 14:12:03',1,2),(9,10,1,2,'2025-12-13 14:11:20',1,'2025-12-13 14:11:20',NULL,NULL,NULL,1),(14,8,1,2,'2025-12-13 14:22:07',1,'2025-12-13 14:22:08',NULL,'2025-12-13 14:22:08',1,2),(15,8,1,2,'2025-12-13 14:22:09',1,'2025-12-13 14:22:10',NULL,'2025-12-13 14:22:10',1,2),(16,8,1,2,'2025-12-13 14:22:10',1,'2025-12-13 14:22:11',NULL,'2025-12-13 14:22:11',1,2),(17,8,1,2,'2025-12-13 14:27:45',1,'2025-12-13 14:27:56',NULL,'2025-12-13 14:27:56',1,2),(18,8,1,2,'2025-12-13 14:28:06',1,'2025-12-13 14:28:06',NULL,NULL,NULL,1),(19,9,1,2,'2025-12-13 14:28:17',1,'2025-12-13 14:28:24',NULL,'2025-12-13 14:28:24',1,2),(20,9,1,2,'2025-12-13 14:37:11',1,'2025-12-13 14:37:30',NULL,'2025-12-13 14:37:30',1,2),(21,9,1,1,'2025-12-13 14:37:38',1,'2025-12-13 14:37:44',NULL,'2025-12-13 14:37:44',1,2),(22,9,1,1,'2025-12-13 14:37:47',1,'2025-12-13 14:37:50',NULL,'2025-12-13 14:37:50',1,2),(23,9,1,2,'2025-12-13 14:37:50',1,'2025-12-13 15:29:31',NULL,'2025-12-13 15:29:31',1,2),(24,4,1,2,'2025-12-13 15:27:00',1,'2025-12-13 15:27:05',NULL,'2025-12-13 15:27:05',1,2),(25,4,1,1,'2025-12-13 15:27:05',1,'2025-12-13 15:41:58',NULL,'2025-12-13 15:41:58',1,2),(26,15,1,1,'2025-12-13 15:28:11',1,'2025-12-13 15:42:22',NULL,'2025-12-13 15:42:22',1,2),(27,2,1,5,'2025-12-13 15:29:13',1,'2025-12-13 15:29:13',NULL,NULL,NULL,1),(28,9,1,6,'2025-12-13 15:29:31',1,'2025-12-13 15:29:31',NULL,NULL,NULL,1),(29,7,1,2,'2025-12-13 15:29:44',1,'2025-12-13 15:47:40',NULL,'2025-12-13 15:47:40',1,2),(30,17,1,5,'2025-12-13 15:29:52',1,'2025-12-13 15:47:55',NULL,'2025-12-13 15:47:55',1,2),(31,4,1,2,'2025-12-13 15:41:58',1,'2025-12-13 15:43:21',NULL,'2025-12-13 15:43:21',1,2),(32,16,1,2,'2025-12-13 15:42:04',1,'2025-12-13 15:47:45',NULL,'2025-12-13 15:47:45',1,2),(33,15,1,2,'2025-12-13 15:42:22',1,'2025-12-13 15:42:27',NULL,'2025-12-13 15:42:27',1,2),(34,4,1,2,'2025-12-13 15:46:41',1,'2025-12-13 15:46:49',NULL,'2025-12-13 15:46:49',1,2),(35,4,1,1,'2025-12-13 15:46:49',1,'2025-12-13 18:05:22',NULL,'2025-12-13 18:05:22',1,2),(36,7,1,1,'2025-12-13 15:47:40',1,'2025-12-13 15:47:40',NULL,NULL,NULL,1),(37,17,1,1,'2025-12-13 15:47:55',1,'2025-12-13 15:47:55',NULL,'2025-12-13 15:47:55',1,2),(38,17,1,2,'2025-12-13 15:48:05',1,'2025-12-14 09:44:21',NULL,'2025-12-14 09:44:21',1,2),(39,1,1,1,'2025-12-13 18:05:28',1,'2025-12-13 18:05:33',NULL,'2025-12-13 18:05:33',1,2),(40,1,1,1,'2025-12-13 18:05:39',1,'2025-12-14 10:06:16',NULL,'2025-12-14 10:06:16',1,2),(41,20,1,1,'2025-12-14 09:44:00',1,'2025-12-14 09:44:05',NULL,'2025-12-14 09:44:05',1,2),(42,19,1,2,'2025-12-14 09:44:11',1,'2025-12-14 09:44:15',NULL,'2025-12-14 09:44:15',1,2),(43,19,1,2,'2025-12-14 09:44:17',1,'2025-12-14 09:44:19',NULL,'2025-12-14 09:44:19',1,2),(44,19,1,1,'2025-12-14 09:44:19',1,'2025-12-14 09:44:19',NULL,NULL,NULL,1),(45,17,1,1,'2025-12-14 09:44:21',1,'2025-12-14 09:44:23',NULL,'2025-12-14 09:44:23',1,2),(46,17,1,2,'2025-12-14 09:44:23',1,'2025-12-14 09:44:46',NULL,'2025-12-14 09:44:46',1,2),(47,15,1,3,'2025-12-14 09:44:27',1,'2025-12-14 09:44:29',NULL,'2025-12-14 09:44:29',1,2),(48,15,1,2,'2025-12-14 09:44:29',1,'2025-12-14 09:44:29',NULL,NULL,NULL,1),(49,17,1,2,'2025-12-14 09:44:50',1,'2025-12-14 09:44:50',NULL,NULL,NULL,1),(50,20,1,2,'2025-12-14 09:45:14',1,'2025-12-14 09:45:17',NULL,'2025-12-14 09:45:17',1,2),(51,20,1,1,'2025-12-14 09:45:17',1,'2025-12-14 09:45:28',NULL,'2025-12-14 09:45:28',1,2),(52,1,1,3,'2025-12-14 10:06:16',1,'2025-12-14 10:08:58',NULL,'2025-12-14 10:08:58',1,2),(53,1,1,5,'2025-12-14 10:08:58',1,'2025-12-14 10:09:01',NULL,'2025-12-14 10:09:01',1,2),(54,1,1,3,'2025-12-14 10:09:05',1,'2025-12-14 10:13:45',NULL,'2025-12-14 10:13:45',1,2),(55,1,1,2,'2025-12-14 10:13:45',1,'2025-12-14 10:20:29',NULL,'2025-12-14 10:20:29',1,2),(56,1,1,1,'2025-12-14 10:20:29',1,'2025-12-14 10:20:31',NULL,'2025-12-14 10:20:31',1,2),(57,1,1,3,'2025-12-14 10:20:34',1,'2025-12-14 10:20:34',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `blog_comment_reactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comments`
--

DROP TABLE IF EXISTS `blog_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `comment` text NOT NULL,
  `comment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_comments_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_comments_ibfk_2` FOREIGN KEY (`parent_comment_id`) REFERENCES `blog_comments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_comments_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comments`
--

LOCK TABLES `blog_comments` WRITE;
/*!40000 ALTER TABLE `blog_comments` DISABLE KEYS */;
INSERT INTO `blog_comments` VALUES (1,1,1,NULL,'This is an amazing blog post!','2025-09-28 13:51:01',1,'2025-09-28 13:51:01',1,'2025-09-28 13:51:01',NULL,NULL,NULL),(2,1,2,NULL,'I really enjoyed reading this.','2025-09-28 13:51:01',1,'2025-09-28 13:51:01',2,'2025-09-28 13:51:01',NULL,NULL,NULL),(3,2,3,NULL,'Very informative, thanks for sharing.','2025-09-28 13:51:01',1,'2025-09-28 13:51:01',3,'2025-09-28 13:51:01',NULL,NULL,NULL),(4,1,3,1,'Totally agree with you!','2025-09-28 13:51:03',1,'2025-09-28 13:51:03',3,'2025-09-28 13:51:03',NULL,NULL,NULL),(5,1,1,2,'Glad you liked it!','2025-09-28 13:51:03',1,'2025-09-28 13:51:03',1,'2025-09-28 13:51:03',NULL,NULL,NULL),(6,2,2,3,'Yes, this was helpful.','2025-09-28 13:51:03',1,'2025-09-28 13:51:03',2,'2025-09-28 13:51:03',NULL,NULL,NULL),(7,1,1,NULL,'my first comment','2025-12-13 13:34:14',1,'2025-12-13 13:34:14',1,'2025-12-13 13:34:14',NULL,NULL,NULL),(8,1,1,1,'my first comment','2025-12-13 13:34:48',1,'2025-12-13 13:34:48',1,'2025-12-13 13:34:48',NULL,NULL,NULL),(9,1,1,2,'my first comment','2025-12-13 13:35:27',1,'2025-12-13 13:35:27',1,'2025-12-13 13:35:27',NULL,NULL,NULL),(10,1,1,3,'my first comment','2025-12-13 13:35:38',1,'2025-12-13 13:35:38',1,'2025-12-13 13:35:38',NULL,NULL,NULL),(11,1,1,NULL,'apple','2025-12-13 14:46:06',1,'2025-12-13 14:46:06',1,'2025-12-13 14:46:06',NULL,NULL,NULL),(12,1,1,11,'reply','2025-12-13 14:47:33',1,'2025-12-13 14:47:33',1,'2025-12-13 14:47:33',NULL,NULL,NULL),(13,1,1,12,'reply2','2025-12-13 14:59:00',1,'2025-12-13 14:59:00',1,'2025-12-13 14:59:00',NULL,NULL,NULL),(14,1,1,NULL,'aaa','2025-12-13 15:21:57',1,'2025-12-13 15:21:57',1,'2025-12-13 15:21:57',NULL,NULL,NULL),(15,1,1,14,'aaa','2025-12-13 15:22:02',1,'2025-12-13 15:22:02',1,'2025-12-13 15:22:02',NULL,NULL,NULL),(16,1,1,1,'aa','2025-12-13 15:27:12',1,'2025-12-13 15:27:12',1,'2025-12-13 15:27:12',NULL,NULL,NULL),(17,1,1,14,'hi','2025-12-13 15:28:03',1,'2025-12-13 15:28:03',1,'2025-12-13 15:28:03',NULL,NULL,NULL),(18,1,3,4,'Totally agree with you!','2025-09-28 13:51:03',1,'2025-09-28 13:51:03',3,'2025-09-28 13:51:03',NULL,NULL,NULL),(19,1,1,NULL,'hi, Im ','2025-12-14 09:35:44',1,'2025-12-14 09:35:44',1,'2025-12-14 09:35:44',NULL,NULL,NULL),(20,1,1,19,'yeap','2025-12-14 09:36:55',1,'2025-12-14 09:36:55',1,'2025-12-14 09:36:55',NULL,NULL,NULL);
/*!40000 ALTER TABLE `blog_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_hero_section`
--

DROP TABLE IF EXISTS `blog_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_hero_section`
--

LOCK TABLES `blog_hero_section` WRITE;
/*!40000 ALTER TABLE `blog_hero_section` DISABLE KEYS */;
INSERT INTO `blog_hero_section` VALUES (1,'Blog Hero 1','https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=2070&q=80','Travel Stories & Guides','Explore Sri Lanka with Local Experts','Discover the best destinations, hidden gems, and authentic local experiences. Updated weekly to inspire your next Sri Lankan adventure.','Our Tours','/sri-lankan-tours','Our Packages','/packages',1,1,'2025-12-11 03:00:38',1,'2025-12-11 18:56:50',1,NULL,NULL),(2,'Blog Hero 2','https://images.unsplash.com/photo-1518684079-3c830dcef090?auto=format&fit=crop&w=2070&q=80','Adventure Awaits','Unleash Your Wanderlust','Experience wildlife safaris, scenic train rides, beaches, and mountains. Our guides help you explore Sri Lanka like never before.','Our Packages','/packages','About Us','/abou-us',1,2,'2025-12-11 03:00:38',1,'2025-12-11 18:56:50',1,NULL,NULL),(3,'Blog Hero 3','https://images.unsplash.com/photo-1526778548025-fa2f459cd5c1?auto=format&fit=crop&w=2070&q=80','Cultural Insights','Discover Sri Lanka’s Rich Heritage','Dive deep into Sri Lanka’s culture, traditions, festivals, and ancient history through our curated articles.','Our Destiantions','/destinations','Contact us','/contact-us',1,3,'2025-12-11 03:00:38',1,'2025-12-11 18:56:50',1,NULL,NULL),(4,'Blog Hero 4','https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=2070&q=80','Travel Tips & Essentials','Plan Your Trip the Smart Way','From budgeting to packing to local travel rules — we share practical tips to make your Sri Lankan journey smooth and enjoyable.','Contact us','/contact-us','Our Tours','/sri-lankan-tours',1,4,'2025-12-11 03:00:38',1,'2025-12-11 18:56:50',1,NULL,NULL),(5,'Blog Hero 5','https://images.unsplash.com/photo-1500048993959-d995ddee5f06?auto=format&fit=crop&w=2070&q=80','Food & Lifestyle','Taste Sri Lanka','Explore Sri Lankan cuisine, restaurants, street food, and lifestyle blogs that bring the island’s flavors to life. tyle blogs that bring the island’s flavors to life.','About Us','/abou-us','Our Destiantions','/destinations',1,5,'2025-12-11 03:00:38',1,'2025-12-11 18:56:50',1,NULL,NULL);
/*!40000 ALTER TABLE `blog_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_images`
--

DROP TABLE IF EXISTS `blog_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  CONSTRAINT `blog_images_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_images`
--

LOCK TABLES `blog_images` WRITE;
/*!40000 ALTER TABLE `blog_images` DISABLE KEYS */;
INSERT INTO `blog_images` VALUES (1,1,'/images/blogs-images/blog-1.1.jpg',1,'2025-09-21 14:15:19',1,'2025-09-28 14:51:04',NULL,NULL,NULL),(2,1,'/images/blogs-images/blog-1.2.jpg',1,'2025-09-21 14:15:19',1,'2025-09-28 14:51:04',NULL,NULL,NULL),(3,2,'/images/blogs-images/blog-2.1.jpeg',1,'2025-09-21 14:15:19',2,'2025-09-28 14:51:04',NULL,NULL,NULL),(4,1,'/images/blogs-images/blog-1.3.jpg',1,'2025-09-21 14:15:19',1,'2025-09-21 14:15:19',NULL,NULL,NULL),(5,2,'/images/blogs-images/blog-2.2.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(6,2,'/images/blogs-images/blog-2.3.webp',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(7,3,'/images/blogs-images/blog-3.1.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(8,3,'/images/blogs-images/blog-3.2.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(9,3,'/images/blogs-images/blog-3.3.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(10,4,'/images/blogs-images/blog-4.1.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(11,4,'/images/blogs-images/blog-4.2.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(12,4,'/images/blogs-images/blog-4.3.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(13,5,'/images/blogs-images/blog-5.1.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(14,5,'/images/blogs-images/blog-5.2.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(15,5,'/images/blogs-images/blog-5.3.jpg',1,'2025-09-21 14:15:19',2,'2025-09-21 14:15:19',NULL,NULL,NULL),(16,10,'image 1',1,'2025-12-11 09:08:55',1,'2025-12-11 09:08:55',1,NULL,NULL),(17,10,'image 2',1,'2025-12-11 09:08:55',1,'2025-12-11 09:08:55',1,NULL,NULL),(18,11,'https://images.unsplash.com/photo-1507525428034-b723cf961d3e',1,'2025-12-11 09:38:20',1,'2025-12-11 09:38:20',1,NULL,NULL),(19,11,'https://images.unsplash.com/photo-1518684079-3c830dcef090',1,'2025-12-11 09:38:20',1,'2025-12-11 09:38:20',1,NULL,NULL),(20,12,'https://images.unsplash.com/photo-1507525428034-b723cf961d3e',1,'2025-12-11 12:59:36',1,'2025-12-11 12:59:36',1,NULL,NULL),(21,12,'https://images.unsplash.com/photo-1518684079-3c830dcef090',1,'2025-12-11 12:59:36',1,'2025-12-11 12:59:36',1,NULL,NULL),(22,13,'https://images.unsplash.com/photo-1507525428034-b723cf961d3e',1,'2025-12-13 11:34:21',1,'2025-12-13 11:34:21',1,NULL,NULL),(23,13,'https://images.unsplash.com/photo-1518684079-3c830dcef090',1,'2025-12-13 11:34:21',1,'2025-12-13 11:34:21',1,NULL,NULL),(24,13,'https://images.unsplash.com/photo-1526778548025-fa2f459cd5c1',1,'2025-12-13 11:34:21',1,'2025-12-13 11:34:21',1,NULL,NULL);
/*!40000 ALTER TABLE `blog_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_likes`
--

DROP TABLE IF EXISTS `blog_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL DEFAULT '1',
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `status` (`status`),
  KEY `fk_blog_likes_reaction_type` (`reaction_type_id`),
  CONSTRAINT `blog_likes_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_likes_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`),
  CONSTRAINT `fk_blog_likes_reaction_type` FOREIGN KEY (`reaction_type_id`) REFERENCES `blog_reactions_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_likes`
--

LOCK TABLES `blog_likes` WRITE;
/*!40000 ALTER TABLE `blog_likes` DISABLE KEYS */;
INSERT INTO `blog_likes` VALUES (1,1,1,1,2,'2025-09-21 14:15:19',1,'2025-12-14 09:30:28',NULL,'2025-12-14 09:30:28',1),(2,1,2,2,1,'2025-09-21 14:15:19',2,'2025-09-28 14:30:27',NULL,NULL,NULL),(3,2,3,1,1,'2025-09-21 14:15:19',3,'2025-09-21 14:15:19',NULL,NULL,NULL),(22,1,1,1,2,'2025-12-14 09:30:35',1,'2025-12-14 09:30:41',NULL,'2025-12-14 09:30:41',1),(23,1,1,2,2,'2025-12-14 09:30:41',1,'2025-12-14 09:31:14',NULL,'2025-12-14 09:31:14',1),(24,1,1,1,2,'2025-12-14 09:31:14',1,'2025-12-14 09:31:17',NULL,'2025-12-14 09:31:17',1),(25,1,1,1,2,'2025-12-14 09:33:48',1,'2025-12-14 09:33:55',NULL,'2025-12-14 09:33:55',1),(26,1,1,2,2,'2025-12-14 09:33:57',1,'2025-12-14 09:34:11',NULL,'2025-12-14 09:34:11',1),(27,1,1,1,2,'2025-12-14 09:34:11',1,'2025-12-14 09:35:15',NULL,'2025-12-14 09:35:15',1);
/*!40000 ALTER TABLE `blog_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_reactions_types`
--

DROP TABLE IF EXISTS `blog_reactions_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_reactions_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `emoji` varchar(10) NOT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_blog_react_types_status` (`common_status_id`),
  CONSTRAINT `fk_blog_react_types_status` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_reactions_types`
--

LOCK TABLES `blog_reactions_types` WRITE;
/*!40000 ALTER TABLE `blog_reactions_types` DISABLE KEYS */;
INSERT INTO `blog_reactions_types` VALUES (1,'Like','?',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL),(2,'Love','❤️',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL),(3,'Haha','?',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL),(4,'Wow','?',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL),(5,'Sad','?',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL),(6,'Angry','?',1,'2025-09-28 13:42:43',1,'2025-09-28 13:42:43',NULL,NULL,NULL);
/*!40000 ALTER TABLE `blog_reactions_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_tags`
--

DROP TABLE IF EXISTS `blog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_id` (`blog_id`,`tag_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `blog_tags_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`) ON DELETE CASCADE,
  CONSTRAINT `blog_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_tags`
--

LOCK TABLES `blog_tags` WRITE;
/*!40000 ALTER TABLE `blog_tags` DISABLE KEYS */;
INSERT INTO `blog_tags` VALUES (1,1,1,'2025-12-12 10:44:00'),(2,1,2,'2025-12-12 10:44:00'),(3,2,2,'2025-12-12 10:44:00'),(4,2,3,'2025-12-12 10:44:00'),(5,3,4,'2025-12-12 10:44:00'),(6,3,5,'2025-12-12 10:44:00'),(7,4,1,'2025-12-12 10:44:00'),(8,4,6,'2025-12-12 10:44:00'),(9,5,7,'2025-12-12 10:44:00'),(10,5,8,'2025-12-12 10:44:00'),(11,6,2,'2025-12-12 10:44:00'),(12,6,9,'2025-12-12 10:44:00'),(13,7,3,'2025-12-12 10:44:00'),(14,7,10,'2025-12-12 10:44:00'),(15,8,5,'2025-12-12 10:44:00'),(16,8,6,'2025-12-12 10:44:00'),(17,9,1,'2025-12-12 10:44:00'),(18,9,4,'2025-12-12 10:44:00'),(19,10,8,'2025-12-12 10:44:00'),(20,10,9,'2025-12-12 10:44:00'),(21,11,2,'2025-12-12 10:44:00'),(22,11,7,'2025-12-12 10:44:00'),(23,12,1,'2025-12-12 10:44:00'),(24,12,10,'2025-12-12 10:44:00');
/*!40000 ALTER TABLE `blog_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `description` text,
  `writer_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `views` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `fk_blog_category` (`category_id`),
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`),
  CONSTRAINT `fk_blog_category` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
INSERT INTO `blogs` VALUES (1,'First Blog','Introduction','This is the first blog description',1,1,'2025-09-21 14:15:19',1,'2025-12-14 10:59:18',NULL,NULL,NULL,1,300),(2,'Second Blog','Deep Dive','This is the second blog description',2,1,'2025-09-28 14:15:19',1,'2025-12-12 19:12:09',NULL,NULL,NULL,2,2),(3,'Third Blog','Deep Dive','This is the thired blog description',2,1,'2025-09-21 14:15:19',1,'2025-12-12 10:58:51',NULL,NULL,NULL,2,1),(4,'Fourth Blog','Deep Dive','This is the fourth blog description',1,1,'2025-09-21 14:15:19',1,'2025-12-12 19:09:36',NULL,NULL,NULL,3,3),(5,'Fiveth Blog','Deep Dive','This is the fiveth blog description',2,1,'2025-09-24 14:15:19',1,'2025-12-12 10:58:51',NULL,NULL,NULL,5,1),(6,'Fiveth Blog','Deep Dive','This is the fiveth blog description',2,1,'2025-09-25 14:15:19',1,'2025-12-12 10:58:51',NULL,NULL,NULL,4,1),(7,'title','sub Title','description',1,1,'2025-12-11 09:04:32',1,'2025-12-12 10:58:51',1,NULL,NULL,1,1),(8,'title','sub Title','description',1,1,'2025-12-11 09:06:18',1,'2025-12-12 10:58:51',1,NULL,NULL,6,1),(9,'title','sub Title','description',1,1,'2025-12-11 09:07:30',1,'2025-12-12 10:58:51',1,NULL,NULL,1,1),(10,'title','sub Title','description',1,1,'2025-12-11 09:08:55',1,'2025-12-12 18:38:27',1,NULL,NULL,1,17),(11,'blog 1 new','blog one new and test','bcbnaoeiw y asdgtwqi sahdqw0d wndqwidw. wdhqwhd dqwdqwd',1,1,'2025-12-11 09:38:20',1,'2025-12-12 10:58:51',1,NULL,NULL,3,1),(12,'fvfagaadfg','rsfrgqegqrgr','hjehjhvewvhcwec. wefecwecwecwecwecewce fwfrwvrwrvrv wrfrv',1,1,'2025-12-11 12:59:36',1,'2025-12-13 07:57:21',1,NULL,NULL,2,12),(13,'jsjkas','hasjhas','hhhhhhhhhhhhhhhhhkasljsalhkasjdhkajdakchbancbkuhkjqebdkbmkcdbsdhbvclwjglcjbckjwebgkwc',1,1,'2025-12-13 11:34:21',1,'2025-12-13 11:34:33',1,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `blogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_accommodation`
--

DROP TABLE IF EXISTS `booking_accommodation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_accommodation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `check_in_date` date NOT NULL,
  `check_out_date` date NOT NULL,
  `hotel_name` varchar(255) NOT NULL,
  `room_type` varchar(100) DEFAULT NULL,
  `room_number` varchar(50) DEFAULT NULL,
  `confirmation_number` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `booking_accommodation_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_accommodation`
--

LOCK TABLES `booking_accommodation` WRITE;
/*!40000 ALTER TABLE `booking_accommodation` DISABLE KEYS */;
INSERT INTO `booking_accommodation` VALUES (1,1,'2024-03-01','2024-03-04','Hotel Grand','Deluxe Double','201','HGRN201','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,2,'2024-04-10','2024-04-13','Resort Paradise','Family Suite','305','RPRD305','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,3,'2024-05-01','2024-05-04','City Hotel','Single Room','108','CHTL108','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,4,'2024-06-01','2024-06-05','Mountain Lodge','Double Room','215','MTLD215','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_accommodation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_activities`
--

DROP TABLE IF EXISTS `booking_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_activities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `activity_schedule_id` int NOT NULL,
  `activity_date` date NOT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `number_of_participants` int NOT NULL,
  `price_per_person` decimal(10,2) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  KEY `activity_schedule_id` (`activity_schedule_id`),
  KEY `status` (`status`),
  KEY `idx_booking_activities_booking` (`booking_id`),
  CONSTRAINT `booking_activities_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `booking_activities_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`),
  CONSTRAINT `booking_activities_ibfk_3` FOREIGN KEY (`activity_schedule_id`) REFERENCES `activities_schedule` (`id`),
  CONSTRAINT `booking_activities_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_activities`
--

LOCK TABLES `booking_activities` WRITE;
/*!40000 ALTER TABLE `booking_activities` DISABLE KEYS */;
INSERT INTO `booking_activities` VALUES (1,1,1,1,'2024-03-02','09:00:00','12:00:00',2,50.00,100.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,1,2,3,'2024-03-03','14:00:00','17:00:00',2,75.00,150.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,2,3,5,'2024-04-11','10:00:00','13:00:00',4,40.00,160.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,2,4,7,'2024-04-12','08:00:00','11:00:00',4,60.00,240.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,3,5,9,'2024-05-02','11:00:00','14:00:00',1,80.00,80.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(6,4,6,11,'2024-06-02','09:30:00','12:30:00',3,45.00,135.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(7,4,7,13,'2024-06-03','15:00:00','18:00:00',3,55.00,165.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(8,5,8,15,'2024-07-02','13:00:00','16:00:00',2,70.00,140.00,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_documents`
--

DROP TABLE IF EXISTS `booking_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_documents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `document_type` varchar(100) NOT NULL,
  `document_name` varchar(255) NOT NULL,
  `document_url` varchar(500) NOT NULL,
  `file_size` int DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `idx_documents_booking_id` (`booking_id`),
  CONSTRAINT `booking_documents_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `booking_documents_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_documents`
--

LOCK TABLES `booking_documents` WRITE;
/*!40000 ALTER TABLE `booking_documents` DISABLE KEYS */;
INSERT INTO `booking_documents` VALUES (1,1,'INVOICE','Invoice_BK001.pdf','/documents/invoices/INV001.pdf',2048,'application/pdf',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,1,'ITINERARY','Itinerary_BK001.pdf','/documents/itineraries/IT_BK001.pdf',3072,'application/pdf',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,2,'INVOICE','Invoice_BK002.pdf','/documents/invoices/INV002.pdf',2048,'application/pdf',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,3,'RECEIPT','Payment_Receipt_BK003.pdf','/documents/receipts/RCP_BK003.pdf',1024,'application/pdf',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,4,'TICKET','Entrance_Tickets_BK004.pdf','/documents/tickets/TKT_BK004.pdf',4096,'application/pdf',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_insurance`
--

DROP TABLE IF EXISTS `booking_insurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_insurance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `insurance_provider` varchar(255) DEFAULT NULL,
  `policy_number` varchar(100) DEFAULT NULL,
  `coverage_type` varchar(100) DEFAULT NULL,
  `premium_amount` decimal(10,2) DEFAULT NULL,
  `coverage_details` text,
  `policy_start_date` date DEFAULT NULL,
  `policy_end_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `booking_insurance_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_insurance`
--

LOCK TABLES `booking_insurance` WRITE;
/*!40000 ALTER TABLE `booking_insurance` DISABLE KEYS */;
INSERT INTO `booking_insurance` VALUES (1,1,'Global Travel Insurance','GTI789456','Comprehensive',50.00,'Medical coverage, trip cancellation, baggage loss','2024-03-01','2024-03-08','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,2,'Safe Journey Inc','SJI123789','Family Plan',100.00,'Family medical coverage, emergency evacuation','2024-04-10','2024-04-16','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,3,'Travel Guard Co','TGC456123','Single Trip',30.00,'Basic medical and trip interruption','2024-05-01','2024-05-06','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_insurance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_invoices`
--

DROP TABLE IF EXISTS `booking_invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_invoices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `invoice_number` varchar(100) NOT NULL,
  `invoice_date` date NOT NULL,
  `due_date` date NOT NULL,
  `subtotal` decimal(15,2) NOT NULL,
  `tax_amount` decimal(15,2) DEFAULT '0.00',
  `discount_amount` decimal(15,2) DEFAULT '0.00',
  `total_amount` decimal(15,2) NOT NULL,
  `amount_paid` decimal(15,2) DEFAULT '0.00',
  `balance_due` decimal(15,2) NOT NULL,
  `billing_full_name` varchar(255) NOT NULL,
  `billing_address` text NOT NULL,
  `billing_email` varchar(150) NOT NULL,
  `billing_phone` varchar(20) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoice_number` (`invoice_number`),
  KEY `status` (`status`),
  KEY `idx_invoices_booking_id` (`booking_id`),
  KEY `idx_invoices_number` (`invoice_number`),
  CONSTRAINT `booking_invoices_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `booking_invoices_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_invoices`
--

LOCK TABLES `booking_invoices` WRITE;
/*!40000 ALTER TABLE `booking_invoices` DISABLE KEYS */;
INSERT INTO `booking_invoices` VALUES (1,1,'INV001','2024-01-15','2024-02-15',1300.00,60.00,100.00,1210.00,605.00,605.00,'John Smith','123 Main St, City, State, 12345','john.smith@email.com','+1234567890',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,2,'INV002','2024-01-20','2024-03-10',2700.00,125.00,200.00,2525.00,1262.50,1262.50,'Mike Johnson','456 Oak Ave, City, State, 12346','mike.j@email.com','+1234567893',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,3,'INV003','2024-02-01','2024-02-05',850.00,40.00,50.00,820.00,820.00,0.00,'David Wilson','789 Pine Rd, City, State, 12347','david.w@email.com','+1234567896',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,4,'INV004','2024-02-10','2024-02-15',1950.00,90.00,150.00,1815.00,1815.00,0.00,'Robert Brown','321 Elm St, City, State, 12348','robert.b@email.com','+1234567898',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,5,'INV005','2024-02-15','2024-02-20',1620.00,75.00,120.00,1515.00,1515.00,0.00,'Sarah Johnson','654 Maple Dr, City, State, 12349','sarah.j@email.com','+1234567895',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_itinerary`
--

DROP TABLE IF EXISTS `booking_itinerary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_itinerary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `day_number` int NOT NULL,
  `itinerary_date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `included_meals` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_itinerary_booking_id` (`booking_id`),
  CONSTRAINT `booking_itinerary_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_itinerary`
--

LOCK TABLES `booking_itinerary` WRITE;
/*!40000 ALTER TABLE `booking_itinerary` DISABLE KEYS */;
INSERT INTO `booking_itinerary` VALUES (1,1,1,'2024-03-01','Arrival and Check-in','Arrive at destination and check into hotel','14:00:00','16:00:00','Hotel Grand','Dinner','2025-11-30 04:38:04',1),(2,1,2,'2024-03-02','City Exploration','Guided city walking tour and local market visit','09:00:00','17:00:00','City Center','Breakfast, Lunch','2025-11-30 04:38:04',1),(3,1,3,'2024-03-03','Mountain Adventure','Hiking trip to nearby mountains with picnic lunch','08:00:00','18:00:00','Mountain Range','Breakfast, Lunch','2025-11-30 04:38:04',1),(4,2,1,'2024-04-10','Welcome and Orientation','Arrival and welcome dinner','16:00:00','20:00:00','Resort Paradise','Dinner','2025-11-30 04:38:04',1),(5,2,2,'2024-04-11','Wildlife Experience','Morning wildlife safari in national park','06:00:00','13:00:00','National Park','Breakfast, Lunch','2025-11-30 04:38:04',1);
/*!40000 ALTER TABLE `booking_itinerary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_notes`
--

DROP TABLE IF EXISTS `booking_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `note_type` varchar(50) NOT NULL,
  `note_text` text NOT NULL,
  `is_important` tinyint(1) DEFAULT '0',
  `follow_up_date` date DEFAULT NULL,
  `follow_up_completed` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_notes_booking_id` (`booking_id`),
  CONSTRAINT `booking_notes_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_notes`
--

LOCK TABLES `booking_notes` WRITE;
/*!40000 ALTER TABLE `booking_notes` DISABLE KEYS */;
INSERT INTO `booking_notes` VALUES (1,1,'INTERNAL','Customer requested window seats for all flights. Confirmed with airline.',0,NULL,0,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,1,'CUSTOMER','Client called to confirm dietary requirements - vegetarian meals for both passengers.',0,NULL,0,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,2,'INTERNAL','Family with young children. Arrange adjacent rooms.',1,'2024-03-01',0,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,3,'SYSTEM','Automatic payment confirmation received.',0,NULL,0,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,4,'FOLLOW-UP','Send travel documents 2 weeks before departure.',1,'2024-05-15',0,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_participants`
--

DROP TABLE IF EXISTS `booking_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_participants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender_id` int DEFAULT NULL,
  `passport_number` varchar(50) DEFAULT NULL,
  `nationality_country_id` int DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `mobile_number` varchar(20) DEFAULT NULL,
  `emergency_contact_name` varchar(200) DEFAULT NULL,
  `emergency_contact_phone` varchar(20) DEFAULT NULL,
  `emergency_contact_relationship` varchar(100) DEFAULT NULL,
  `medical_conditions` text,
  `allergies` text,
  `special_assistance_required` tinyint(1) DEFAULT '0',
  `assistance_details` text,
  `room_sharing_with` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gender_id` (`gender_id`),
  KEY `nationality_country_id` (`nationality_country_id`),
  KEY `room_sharing_with` (`room_sharing_with`),
  KEY `idx_booking_participants_booking` (`booking_id`),
  CONSTRAINT `booking_participants_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `booking_participants_ibfk_2` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`),
  CONSTRAINT `booking_participants_ibfk_3` FOREIGN KEY (`nationality_country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `booking_participants_ibfk_4` FOREIGN KEY (`room_sharing_with`) REFERENCES `booking_participants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_participants`
--

LOCK TABLES `booking_participants` WRITE;
/*!40000 ALTER TABLE `booking_participants` DISABLE KEYS */;
INSERT INTO `booking_participants` VALUES (1,1,'John','Smith','1985-05-15',1,'AB123456',1,'john.smith@email.com','+1234567890','Jane Smith','+1234567891','Spouse','None','Peanuts',0,NULL,2,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,1,'Jane','Smith','1986-08-20',2,'AB123457',1,'jane.smith@email.com','+1234567892','John Smith','+1234567890','Spouse','Asthma','None',0,NULL,1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,2,'Mike','Johnson','1978-03-10',1,'CD123458',1,'mike.j@email.com','+1234567893','Sarah Johnson','+1234567894','Spouse','None','None',0,NULL,4,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,2,'Sarah','Johnson','1980-07-22',2,'CD123459',1,'sarah.j@email.com','+1234567895','Mike Johnson','+1234567893','Spouse','None','Shellfish',0,NULL,3,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,2,'Emily','Johnson','2010-11-05',2,'CD123460',1,NULL,NULL,'Mike Johnson','+1234567893','Father','None','Dairy',0,NULL,NULL,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(6,2,'Tom','Johnson','2012-02-15',1,'CD123461',1,NULL,NULL,'Sarah Johnson','+1234567895','Mother','None','None',0,NULL,NULL,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(7,3,'David','Wilson','1990-12-30',1,'EF123462',1,'david.w@email.com','+1234567896','Robert Wilson','+1234567897','Father','Diabetes','None',0,NULL,NULL,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(8,4,'Robert','Brown','1975-09-18',1,'GH123463',1,'robert.b@email.com','+1234567898','Lisa Brown','+1234567899','Spouse','None','None',0,NULL,9,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(9,4,'Lisa','Brown','1978-04-25',2,'GH123464',1,'lisa.b@email.com','+1234567900','Robert Brown','+1234567898','Spouse','None','Gluten',0,NULL,8,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(10,4,'Sophia','Brown','2015-06-12',2,'GH123465',1,NULL,NULL,'Robert Brown','+1234567898','Father','None','None',0,NULL,NULL,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_price_breakdown`
--

DROP TABLE IF EXISTS `booking_price_breakdown`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_price_breakdown` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `item_type` varchar(50) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_description` text,
  `quantity` int DEFAULT '1',
  `unit_price` decimal(10,2) NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `booking_price_breakdown_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_price_breakdown`
--

LOCK TABLES `booking_price_breakdown` WRITE;
/*!40000 ALTER TABLE `booking_price_breakdown` DISABLE KEYS */;
INSERT INTO `booking_price_breakdown` VALUES (1,1,'PACKAGE','Standard Tour Package','5-day cultural tour',2,500.00,1000.00,'2025-11-30 04:38:04',1),(2,1,'ACTIVITY','City Walking Tour','Guided city tour',2,50.00,100.00,'2025-11-30 04:38:04',1),(3,1,'ACTIVITY','Mountain Hiking','Guided mountain hike',2,75.00,150.00,'2025-11-30 04:38:04',1),(4,1,'DISCOUNT','Early Bird Discount','10% early booking discount',1,-100.00,-100.00,'2025-11-30 04:38:04',1),(5,1,'TAX','Service Tax','Government service tax',1,60.00,60.00,'2025-11-30 04:38:04',1),(6,1,'INSURANCE','Travel Insurance','Comprehensive travel insurance',2,25.00,50.00,'2025-11-30 04:38:04',1),(7,2,'PACKAGE','Family Tour Package','4-day family adventure',4,550.00,2200.00,'2025-11-30 04:38:04',1),(8,2,'ACTIVITY','Wildlife Safari','Jeep safari experience',4,40.00,160.00,'2025-11-30 04:38:04',1),(9,2,'ACTIVITY','Waterfall Visit','Guided waterfall tour',4,60.00,240.00,'2025-11-30 04:38:04',1),(10,2,'DISCOUNT','Family Discount','Family package discount',1,-200.00,-200.00,'2025-11-30 04:38:04',1),(11,2,'TAX','Service Tax','Government service tax',1,125.00,125.00,'2025-11-30 04:38:04',1),(12,2,'INSURANCE','Travel Insurance','Comprehensive travel insurance',4,25.00,100.00,'2025-11-30 04:38:04',1);
/*!40000 ALTER TABLE `booking_price_breakdown` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_status`
--

DROP TABLE IF EXISTS `booking_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `booking_status_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_status`
--

LOCK TABLES `booking_status` WRITE;
/*!40000 ALTER TABLE `booking_status` DISABLE KEYS */;
INSERT INTO `booking_status` VALUES (1,'PENDING','Booking is pending confirmation',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,'CONFIRMED','Booking has been confirmed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,'PAID','Booking has been fully paid',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,'IN_PROGRESS','Tour is currently ongoing',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,'BOOKING_COMPLETED','Booking has been completed',1,'2025-11-30 04:38:04',1,'2025-11-30 16:42:17',NULL),(6,'CANCELLED','Booking has been cancelled',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(7,'TOUR_COMPLETED','Tour experience finished',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `booking_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_transportation`
--

DROP TABLE IF EXISTS `booking_transportation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_transportation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `transport_type` varchar(100) NOT NULL,
  `departure_date` date NOT NULL,
  `departure_time` time DEFAULT NULL,
  `arrival_date` date DEFAULT NULL,
  `arrival_time` time DEFAULT NULL,
  `departure_location` varchar(255) DEFAULT NULL,
  `arrival_location` varchar(255) DEFAULT NULL,
  `carrier_name` varchar(255) DEFAULT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `seat_numbers` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `booking_transportation_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_transportation`
--

LOCK TABLES `booking_transportation` WRITE;
/*!40000 ALTER TABLE `booking_transportation` DISABLE KEYS */;
INSERT INTO `booking_transportation` VALUES (1,1,'FLIGHT','2024-03-01','08:00:00','2024-03-01','11:00:00','City A Airport','City B Airport','Sky Airlines','SKY123','14A,14B','2025-11-30 04:38:04',1),(2,1,'FLIGHT','2024-03-07','19:00:00','2024-03-07','22:00:00','City B Airport','City A Airport','Sky Airlines','SKY124','14A,14B','2025-11-30 04:38:04',1),(3,2,'TRAIN','2024-04-10','06:30:00','2024-04-10','09:30:00','Central Station','Mountain Station','Rail Express','RE456','B12-B15','2025-11-30 04:38:04',1);
/*!40000 ALTER TABLE `booking_transportation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `booking_reference` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  `package_schedule_id` int NOT NULL,
  `total_persons` int NOT NULL,
  `total_amount` decimal(15,2) NOT NULL,
  `discount_amount` decimal(15,2) DEFAULT '0.00',
  `tax_amount` decimal(15,2) DEFAULT '0.00',
  `insurance_amount` decimal(15,2) DEFAULT '0.00',
  `final_amount` decimal(15,2) NOT NULL,
  `booking_date` date NOT NULL,
  `travel_start_date` date NOT NULL,
  `travel_end_date` date NOT NULL,
  `booking_status_id` int NOT NULL,
  `cancellation_reason_id` int DEFAULT NULL,
  `cancellation_date` timestamp NULL DEFAULT NULL,
  `cancellation_notes` text,
  `refund_amount` decimal(15,2) DEFAULT '0.00',
  `refund_status_id` int DEFAULT NULL,
  `special_requirements` text,
  `dietary_restrictions` text,
  `insurance_required` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `booking_reference` (`booking_reference`),
  KEY `cancellation_reason_id` (`cancellation_reason_id`),
  KEY `refund_status_id` (`refund_status_id`),
  KEY `idx_bookings_user_id` (`user_id`),
  KEY `idx_bookings_package_schedule_id` (`package_schedule_id`),
  KEY `idx_bookings_status` (`booking_status_id`),
  KEY `idx_bookings_dates` (`travel_start_date`,`travel_end_date`),
  KEY `idx_bookings_reference` (`booking_reference`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`package_schedule_id`) REFERENCES `package_schedule` (`id`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`booking_status_id`) REFERENCES `booking_status` (`id`),
  CONSTRAINT `bookings_ibfk_4` FOREIGN KEY (`cancellation_reason_id`) REFERENCES `cancellation_reasons` (`id`),
  CONSTRAINT `bookings_ibfk_5` FOREIGN KEY (`refund_status_id`) REFERENCES `refund_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'BK001',1,1,2,1200.00,100.00,60.00,50.00,1210.00,'2024-01-15','2026-03-01','2026-03-07',2,NULL,NULL,NULL,0.00,NULL,'Window seat preferred','Vegetarian meals',1,'2025-11-30 04:38:04',1,'2025-11-30 16:56:33',NULL,NULL,NULL),(2,'BK002',1,3,4,2500.00,200.00,125.00,100.00,2525.00,'2024-01-20','2024-04-10','2024-04-15',7,NULL,NULL,NULL,0.00,NULL,'Early check-in requested','No seafood',0,'2025-11-30 04:38:04',1,'2025-11-30 16:45:03',NULL,NULL,NULL),(3,'BK003',1,5,1,800.00,50.00,40.00,30.00,820.00,'2024-02-01','2024-05-01','2024-05-05',1,NULL,NULL,NULL,0.00,NULL,'Solo traveler','None',1,'2025-11-30 04:38:04',1,'2025-11-30 16:45:03',NULL,NULL,NULL),(4,'BK004',1,7,3,1800.00,150.00,90.00,75.00,1815.00,'2024-02-10','2024-06-01','2024-06-07',6,NULL,NULL,NULL,0.00,NULL,'Family with children','Child meals required',0,'2025-11-30 04:38:04',1,'2025-12-01 05:35:20',NULL,NULL,NULL),(5,'BK005',1,9,2,1500.00,120.00,75.00,60.00,1515.00,'2024-02-15','2024-07-01','2024-07-06',5,NULL,NULL,NULL,0.00,NULL,'Honeymoon couple','None',1,'2025-11-30 04:38:04',1,'2025-11-30 16:45:07',NULL,NULL,NULL),(6,'BK006',1,2,2,1000.00,80.00,50.00,40.00,1010.00,'2024-01-25','2024-03-15','2024-03-18',6,1,'2024-02-10 09:00:00','Customer changed plans',800.00,3,'None',NULL,1,'2025-11-30 04:38:04',1,'2025-11-30 16:45:03',NULL,NULL,NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `browser_history`
--

DROP TABLE IF EXISTS `browser_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `browser_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  `data_id` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `browser_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `browser_history_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `browser_history`
--

LOCK TABLES `browser_history` WRITE;
/*!40000 ALTER TABLE `browser_history` DISABLE KEYS */;
INSERT INTO `browser_history` VALUES (1,'TOUR',1,1,'2025-11-25 15:53:19',1),(2,'TOUR',5,1,'2025-11-25 15:53:39',1),(3,'TOUR',2,1,'2025-11-25 15:54:05',1),(4,'TOUR',4,1,'2025-11-25 15:54:09',1),(5,'PACKAGE',4,1,'2025-11-25 15:54:16',1),(6,'PACKAGE',8,1,'2025-11-25 15:54:19',1),(7,'PACKAGE',2,1,'2025-11-25 15:54:23',1),(8,'DESTINATIONS',2,1,'2025-11-25 15:54:30',1),(9,'DESTINATIONS',3,1,'2025-11-25 15:54:33',1),(10,'DESTINATIONS',5,1,'2025-11-25 15:54:37',1),(11,'ACTIVITIES',1,1,'2025-11-25 15:54:47',1),(12,'ACTIVITIES',3,1,'2025-11-25 15:54:53',1);
/*!40000 ALTER TABLE `browser_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancellation_reasons`
--

DROP TABLE IF EXISTS `cancellation_reasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancellation_reasons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `cancellation_reasons_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancellation_reasons`
--

LOCK TABLES `cancellation_reasons` WRITE;
/*!40000 ALTER TABLE `cancellation_reasons` DISABLE KEYS */;
INSERT INTO `cancellation_reasons` VALUES (1,'CUSTOMER_REQUEST','Cancelled by customer request',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,'WEATHER_CONDITIONS','Cancelled due to bad weather',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,'HEALTH_ISSUES','Cancelled due to health problems',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,'FORCE_MAJEURE','Cancelled due to unforeseen circumstances',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,'OPERATOR_CANCELLATION','Cancelled by tour operator',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `cancellation_reasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_status`
--

DROP TABLE IF EXISTS `common_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `common_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_status`
--

LOCK TABLES `common_status` WRITE;
/*!40000 ALTER TABLE `common_status` DISABLE KEYS */;
INSERT INTO `common_status` VALUES (1,'ACTIVE','Currently active','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL),(2,'INACTIVE','Not active','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL),(3,'TERMINATED','Terminated or deleted','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL);
/*!40000 ALTER TABLE `common_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'LinkBar','Link Bar','2025-09-26 14:59:12','2025-09-26 14:59:12'),(2,'NavBar','Navigation bar','2025-09-26 14:59:12','2025-09-26 14:59:12'),(3,'HeroSection','Top banner section with background image','2025-09-26 14:59:12','2025-09-26 14:59:12');
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_theme`
--

DROP TABLE IF EXISTS `component_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_theme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `component_id` int NOT NULL,
  `theme_name` varchar(100) DEFAULT NULL,
  `theme_description` varchar(255) DEFAULT NULL,
  `primary_color` varchar(200) DEFAULT NULL,
  `secondary_color` varchar(200) DEFAULT NULL,
  `background_color` varchar(200) DEFAULT NULL,
  `gradient_enabled` tinyint(1) DEFAULT '0',
  `gradient_type` varchar(200) DEFAULT NULL,
  `gradient_direction` varchar(500) DEFAULT NULL,
  `gradient_start` varchar(200) DEFAULT NULL,
  `gradient_end` varchar(200) DEFAULT NULL,
  `text_primary` varchar(500) DEFAULT NULL,
  `text_secondary` varchar(500) DEFAULT NULL,
  `banner_image` varchar(255) DEFAULT NULL,
  `custom_css` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `component_id` (`component_id`),
  CONSTRAINT `component_theme_ibfk_1` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_theme`
--

LOCK TABLES `component_theme` WRITE;
/*!40000 ALTER TABLE `component_theme` DISABLE KEYS */;
INSERT INTO `component_theme` VALUES (1,1,'LINK_BAR_BACKGROUND','Gradient background for the Link Bar container',NULL,NULL,NULL,1,'linear','to right','#4C1D95','#0C4A6E','#FFFFFF','#AAAAAA',NULL,'background: linear-gradient(to right, #4C1D95, #5B21B6, #0C4A6E);','2025-09-26 15:25:49','2025-09-26 15:25:49'),(2,1,'LINK_BUTTON_FULL_BACKGROUND','Default background for full buttons','#A855F7','#38BDF8','#F3F4F6',0,NULL,NULL,NULL,NULL,'#EDE9FE','#F9FAFB',NULL,'background-color: rgba(168,85,247,0.2); border: 1px solid rgba(56,189,248,0.3);','2025-09-26 15:25:49','2025-09-26 15:25:49'),(3,1,'LINK_BUTTON_FULL_BACKGROUND_HOVER','Hover background for full buttons',NULL,NULL,NULL,1,'linear','to right','rgba(147,51,234,0.3)','rgba(14,165,233,0.3)','#FFFFFF','#CCCCCC',NULL,'border: 1px solid rgba(216,180,254,0.4);','2025-09-26 15:25:49','2025-09-26 15:25:49'),(4,1,'LINK_BUTTON_FULL_TEXT_COLOR','Text color for full buttons',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'rgba(237,233,254,0.9)','#666666',NULL,NULL,'2025-09-26 15:25:49','2025-09-26 15:25:49'),(5,1,'LINK_BUTTON_FULL_TEXT_COLOR_HOVER','Text color when hovering full buttons',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'#FFFFFF','#999999',NULL,NULL,'2025-09-26 15:25:49','2025-09-26 15:25:49'),(6,1,'LINK_BUTTON_ICON_ONLY_BACKGROUND','Default background for icon-only buttons','#A855F7','#38BDF8','#F3F4F6',0,NULL,NULL,NULL,NULL,'#FFFFFF','#AAAAAA',NULL,'background-color: rgba(168,85,247,0.2); border: 1px solid rgba(56,189,248,0.3);','2025-09-26 15:25:49','2025-09-26 15:25:49'),(7,1,'LINK_BUTTON_ICON_ONLY_BACKGROUND_HOVER','Hover background for icon-only buttons',NULL,NULL,NULL,1,'linear','to right','rgba(147,51,234,0.3)','rgba(14,165,233,0.3)','#FFFFFF','#CCCCCC',NULL,'border: 1px solid rgba(216,180,254,0.4);','2025-09-26 15:25:49','2025-09-26 15:25:49');
/*!40000 ALTER TABLE `component_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_methods`
--

DROP TABLE IF EXISTS `contact_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_methods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `action` varchar(50) NOT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `contact_methods_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_methods`
--

LOCK TABLES `contact_methods` WRITE;
/*!40000 ALTER TABLE `contact_methods` DISABLE KEYS */;
INSERT INTO `contact_methods` VALUES (1,'Call Us','+94 11 234 5678','Main Office Line','tel:+94112345678','call','phone',1,'2025-12-13 18:16:36',1,'2025-12-13 18:16:36',NULL,NULL,NULL),(2,'Working Hours','Mon - Sat: 9AM - 6PM','Sunday: 10AM - 4PM',NULL,'hours','clock',1,'2025-12-13 18:16:36',1,'2025-12-13 18:16:36',NULL,NULL,NULL),(3,'Office Location','123 Galle Road, Colombo 03','Sri Lanka','https://maps.google.com/?q=123+Galle+Road,+Colombo+03,+Sri+Lanka','location','location',2,'2025-12-13 18:16:36',1,'2025-12-14 11:23:18',NULL,NULL,NULL),(4,'Email Us','info@travelagency.com','For general inquiries','mailto:info@travelagency.com','email','email',1,'2025-12-13 18:16:36',1,'2025-12-13 18:16:36',NULL,NULL,NULL),(5,'WhatsApp','+94 77 123 4567','Instant chat support','https://wa.me/94771234567','whatsapp','whatsapp',2,'2025-12-13 18:16:36',1,'2025-12-14 11:23:18',NULL,NULL,NULL),(6,'Emergency','+94 77 987 6543','24/7 Emergency Line','tel:+94779876543','emergency','emergency',2,'2025-12-13 18:16:36',1,'2025-12-14 11:23:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `contact_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_us_hero_section`
--

DROP TABLE IF EXISTS `contact_us_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_us_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `contact_us_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_us_hero_section`
--

LOCK TABLES `contact_us_hero_section` WRITE;
/*!40000 ALTER TABLE `contact_us_hero_section` DISABLE KEYS */;
INSERT INTO `contact_us_hero_section` VALUES (1,'Contact Hero 1','https://images.unsplash.com/photo-1521791136064-7986c2920216','Contact Us','We’re Here to Help','Get in touch with our travel experts to plan your perfect journey in Sri Lanka. We’re just a message away.','Get In Touch','/about-us','View Packages','/packages',1,1,'2025-12-09 17:27:42',1,'2025-12-13 18:12:21',1,NULL,NULL),(2,'Contact Hero 2','https://images.unsplash.com/photo-1556761175-5973dc0f32e7','Speak With Experts','Professional Travel Assistance','Our Colombo-based team is ready to assist you with personalized travel solutions and expert guidance.','Call Us','/about-us','View Tours','/sri-lankan-tours',1,2,'2025-12-09 17:27:42',1,'2025-12-13 18:12:21',1,NULL,NULL),(3,'Contact Hero 3','https://images.unsplash.com/photo-1517248135467-4c7edcad34c4','Visit Our Office','Meet Us in Colombo','Visit our office to discuss your travel plans face-to-face with certified travel professionals.','Get Directions','/about-us','View Destinations','/destinations',1,3,'2025-12-09 17:27:42',1,'2025-12-13 18:12:21',1,NULL,NULL);
/*!40000 ALTER TABLE `contact_us_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`country_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Sri Lanka','Island country in South Asia',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'India','Neighboring country',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_code` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` text,
  `coupon_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  `discount_type` enum('percentage','fixed') NOT NULL,
  `discount_value` decimal(10,2) NOT NULL,
  `minimum_cart_value` decimal(10,2) DEFAULT '0.00',
  `maximum_discount` decimal(10,2) DEFAULT NULL,
  `applicable_id` int NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_until` datetime NOT NULL,
  `usage_limit_per_coupon` int DEFAULT NULL,
  `usage_limit_per_user` int DEFAULT '1',
  `total_usage_count` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `coupon_code` (`coupon_code`),
  KEY `status_id` (`status_id`),
  KEY `applicable_id` (`applicable_id`),
  KEY `coupon_type_id` (`coupon_type_id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`),
  CONSTRAINT `coupon_ibfk_2` FOREIGN KEY (`applicable_id`) REFERENCES `coupon_applicable` (`id`),
  CONSTRAINT `coupon_ibfk_3` FOREIGN KEY (`coupon_type_id`) REFERENCES `coupon_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'WELCOME20','Welcome Discount','20% off for new customers',3,1,'percentage',20.00,100.00,50.00,1,'2024-01-01 00:00:00','2024-12-31 23:59:59',1000,1,1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'SUMMER25','Summer Special','25% off on summer packages',1,1,'percentage',25.00,200.00,100.00,2,'2024-06-01 00:00:00','2024-08-31 23:59:59',500,2,0,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'FIXED50','Flat Discount','$50 off on any booking',5,1,'fixed',50.00,300.00,NULL,1,'2024-01-01 00:00:00','2024-12-31 23:59:59',NULL,1,1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'REFER30','Referral Bonus','30% off for referred friends',2,1,'percentage',30.00,150.00,75.00,3,'2024-01-01 00:00:00','2024-12-31 23:59:59',NULL,1,0,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'LOYAL15','Loyalty Reward','15% off for loyal customers',4,1,'percentage',15.00,100.00,30.00,1,'2024-01-01 00:00:00','2024-12-31 23:59:59',200,1,0,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'BDAY25','Birthday Special','25% off on your birthday month',7,1,'percentage',25.00,50.00,100.00,1,'2024-01-01 00:00:00','2024-12-31 23:59:59',NULL,1,0,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_applicable`
--

DROP TABLE IF EXISTS `coupon_applicable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_applicable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_applicable_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_applicable`
--

LOCK TABLES `coupon_applicable` WRITE;
/*!40000 ALTER TABLE `coupon_applicable` DISABLE KEYS */;
INSERT INTO `coupon_applicable` VALUES (1,'All Packages','Applicable to all travel packages',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'Specific Packages','Applicable only to selected packages',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'User Type Specific','Applicable to specific user types',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'First-time Users','Applicable only for first booking',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'Destination Specific','Applicable only for specific destinations',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'Seasonal Packages','Applicable only for seasonal packages',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_applicable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_applicable_package`
--

DROP TABLE IF EXISTS `coupon_applicable_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_applicable_package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_id` int NOT NULL,
  `package_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_coupon_package` (`coupon_id`,`package_id`),
  KEY `package_id` (`package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_applicable_package_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE,
  CONSTRAINT `coupon_applicable_package_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `coupon_applicable_package_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_applicable_package`
--

LOCK TABLES `coupon_applicable_package` WRITE;
/*!40000 ALTER TABLE `coupon_applicable_package` DISABLE KEYS */;
INSERT INTO `coupon_applicable_package` VALUES (1,2,1,1,'2025-11-27 15:00:53',1),(2,2,2,1,'2025-11-27 15:00:53',1),(3,2,3,1,'2025-11-27 15:00:53',1),(4,4,1,1,'2025-11-27 15:00:53',1);
/*!40000 ALTER TABLE `coupon_applicable_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_applicable_user_type`
--

DROP TABLE IF EXISTS `coupon_applicable_user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_applicable_user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_id` int NOT NULL,
  `user_type_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_coupon_user_type` (`coupon_id`,`user_type_id`),
  KEY `user_type_id` (`user_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_applicable_user_type_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE,
  CONSTRAINT `coupon_applicable_user_type_ibfk_2` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`),
  CONSTRAINT `coupon_applicable_user_type_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_applicable_user_type`
--

LOCK TABLES `coupon_applicable_user_type` WRITE;
/*!40000 ALTER TABLE `coupon_applicable_user_type` DISABLE KEYS */;
INSERT INTO `coupon_applicable_user_type` VALUES (1,3,1,1,'2025-11-27 15:00:53',1),(2,4,2,1,'2025-11-27 15:00:53',1),(3,5,1,1,'2025-11-27 15:00:53',1);
/*!40000 ALTER TABLE `coupon_applicable_user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_failure_log`
--

DROP TABLE IF EXISTS `coupon_failure_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_failure_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_code` varchar(50) NOT NULL,
  `user_id` int DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `user_agent` text,
  `coupon_failure_reason_id` int NOT NULL,
  `cart_value` decimal(10,2) DEFAULT NULL,
  `attempted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `package_id` int DEFAULT NULL,
  `additional_info` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `package_id` (`package_id`),
  KEY `coupon_failure_reason_id` (`coupon_failure_reason_id`),
  CONSTRAINT `coupon_failure_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `coupon_failure_log_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `coupon_failure_log_ibfk_3` FOREIGN KEY (`coupon_failure_reason_id`) REFERENCES `coupon_failure_reason` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_failure_log`
--

LOCK TABLES `coupon_failure_log` WRITE;
/*!40000 ALTER TABLE `coupon_failure_log` DISABLE KEYS */;
INSERT INTO `coupon_failure_log` VALUES (1,'WELCOME20',1,'192.168.1.100',NULL,4,50.00,'2025-11-27 15:00:53',1,'{\"required_minimum\": 100, \"user_cart\": 50}','2025-11-27 15:00:53'),(2,'EXPIRED99',NULL,'192.168.1.101',NULL,2,200.00,'2025-11-27 15:00:53',2,'{\"coupon_expiry\": \"2023-12-31\"}','2025-11-27 15:00:53'),(3,'SUMMER25',2,'192.168.1.102',NULL,7,300.00,'2025-11-27 15:00:53',3,'{\"allowed_packages\": [1,2,4], \"selected_package\": 3}','2025-11-27 15:00:53'),(4,'WELCOME20',3,'192.168.1.103',NULL,9,150.00,'2025-11-27 15:00:53',1,'{\"user_previous_usage\": true}','2025-11-27 15:00:53'),(5,'FIXED50',1,'192.168.1.104',NULL,8,400.00,'2025-11-27 15:00:53',2,'{\"advance_booking_required\": 7, \"user_booking_days\": 3}','2025-11-27 15:00:53');
/*!40000 ALTER TABLE `coupon_failure_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_failure_reason`
--

DROP TABLE IF EXISTS `coupon_failure_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_failure_reason` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `category` enum('validation','eligibility','system','usage_limit') NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_failure_reason_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_failure_reason`
--

LOCK TABLES `coupon_failure_reason` WRITE;
/*!40000 ALTER TABLE `coupon_failure_reason` DISABLE KEYS */;
INSERT INTO `coupon_failure_reason` VALUES (1,'invalid_code','Coupon code does not exist','validation',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'expired','Coupon has expired','validation',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'usage_limit_reached','Coupon usage limit reached','usage_limit',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'minimum_cart_value','Cart value below minimum requirement','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'not_applicable','Coupon not applicable for selected items','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'user_restriction','User not eligible for this coupon','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(7,'package_restriction','Package not eligible for this coupon','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(8,'date_restriction','Booking date not within valid range','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(9,'already_used','Coupon already used by this user','usage_limit',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(10,'inactive_coupon','Coupon is not active','validation',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(11,'user_type_restriction','User type not eligible','eligibility',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(12,'system_error','Technical error during validation','system',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_failure_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_rule`
--

DROP TABLE IF EXISTS `coupon_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_rule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_id` int NOT NULL,
  `coupon_rule_type_id` int NOT NULL,
  `rule_value` varchar(255) DEFAULT NULL,
  `operator` enum('equals','greater_than','less_than','between','in_list') DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `coupon_id` (`coupon_id`),
  KEY `status_id` (`status_id`),
  KEY `coupon_rule_type_id` (`coupon_rule_type_id`),
  CONSTRAINT `coupon_rule_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE,
  CONSTRAINT `coupon_rule_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`),
  CONSTRAINT `coupon_rule_ibfk_3` FOREIGN KEY (`coupon_rule_type_id`) REFERENCES `coupon_rule_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_rule`
--

LOCK TABLES `coupon_rule` WRITE;
/*!40000 ALTER TABLE `coupon_rule` DISABLE KEYS */;
INSERT INTO `coupon_rule` VALUES (1,1,1,'3','greater_than',1,'2025-11-27 15:00:53',1),(2,2,7,'Bali,Thailand,Malaysia','in_list',1,'2025-11-27 15:00:53',1),(3,2,9,'true','equals',1,'2025-11-27 15:00:53',1),(4,3,4,'7','greater_than',1,'2025-11-27 15:00:53',1),(5,4,6,'10','less_than',1,'2025-11-27 15:00:53',1),(6,5,5,'2','greater_than',1,'2025-11-27 15:00:53',1),(7,6,1,'2','greater_than',1,'2025-11-27 15:00:53',1);
/*!40000 ALTER TABLE `coupon_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_rule_type`
--

DROP TABLE IF EXISTS `coupon_rule_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_rule_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `data_type` enum('number','date','string','boolean','list') NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_rule_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_rule_type`
--

LOCK TABLES `coupon_rule_type` WRITE;
/*!40000 ALTER TABLE `coupon_rule_type` DISABLE KEYS */;
INSERT INTO `coupon_rule_type` VALUES (1,'min_booking_days','Minimum number of booking days required','number',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'max_booking_days','Maximum number of booking days allowed','number',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'specific_departure_date','Applicable only for specific departure dates','date',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'advance_booking_days','Must be booked X days in advance','number',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'group_size_min','Minimum group size required','number',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'group_size_max','Maximum group size allowed','number',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(7,'specific_destination','Applicable only for specific destinations','string',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(8,'weekday_only','Valid only for weekday bookings','boolean',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(9,'weekend_only','Valid only for weekend bookings','boolean',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(10,'seasonal_period','Valid during specific seasonal periods','date',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_rule_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_status`
--

DROP TABLE IF EXISTS `coupon_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `status_category` enum('allocated','used','expired','cancelled','pending') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_status`
--

LOCK TABLES `coupon_status` WRITE;
/*!40000 ALTER TABLE `coupon_status` DISABLE KEYS */;
INSERT INTO `coupon_status` VALUES (1,'Allocated','Coupon has been allocated to user','allocated','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'Active','Coupon is active and available for use','allocated','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'Used','Coupon has been successfully used','used','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'Expired','Coupon has expired','expired','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'Cancelled','Coupon has been cancelled','cancelled','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'Pending Verification','Coupon pending verification','pending','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(7,'Redeemed','Coupon has been redeemed','used','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(8,'Partially Used','Coupon partially used (for multi-use coupons)','used','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_type`
--

DROP TABLE IF EXISTS `coupon_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_type`
--

LOCK TABLES `coupon_type` WRITE;
/*!40000 ALTER TABLE `coupon_type` DISABLE KEYS */;
INSERT INTO `coupon_type` VALUES (1,'Seasonal','Seasonal offers and holiday discounts',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,'Referral','Referral program coupons for inviting friends',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,'First-time','Special discounts for first-time users',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,'Loyalty','Reward coupons for loyal customers',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,'Promotional','General promotional coupons',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,'Abandoned Cart','Coupons to recover abandoned bookings',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(7,'Birthday','Special birthday discounts for users',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_usage_history`
--

DROP TABLE IF EXISTS `coupon_usage_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_usage_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_user_allocation_id` int NOT NULL,
  `order_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  `original_amount` decimal(10,2) NOT NULL,
  `discount_amount` decimal(10,2) NOT NULL,
  `final_amount` decimal(10,2) NOT NULL,
  `used_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `coupon_user_allocation_id` (`coupon_user_allocation_id`),
  KEY `package_id` (`package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_usage_history_ibfk_1` FOREIGN KEY (`coupon_user_allocation_id`) REFERENCES `coupon_user_allocation` (`id`),
  CONSTRAINT `coupon_usage_history_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `coupon_usage_history_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_usage_history`
--

LOCK TABLES `coupon_usage_history` WRITE;
/*!40000 ALTER TABLE `coupon_usage_history` DISABLE KEYS */;
INSERT INTO `coupon_usage_history` VALUES (1,1,1001,1,500.00,100.00,400.00,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,3,1002,2,600.00,50.00,550.00,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_usage_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user_allocation`
--

DROP TABLE IF EXISTS `coupon_user_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_user_allocation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  `coupon_status_id` int NOT NULL,
  `allocated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` datetime NOT NULL,
  `used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_coupon_user_allocation` (`user_id`,`coupon_id`),
  KEY `coupon_id` (`coupon_id`),
  KEY `coupon_status_id` (`coupon_status_id`),
  CONSTRAINT `coupon_user_allocation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `coupon_user_allocation_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`),
  CONSTRAINT `coupon_user_allocation_ibfk_3` FOREIGN KEY (`coupon_status_id`) REFERENCES `coupon_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user_allocation`
--

LOCK TABLES `coupon_user_allocation` WRITE;
/*!40000 ALTER TABLE `coupon_user_allocation` DISABLE KEYS */;
INSERT INTO `coupon_user_allocation` VALUES (1,1,1,3,'2025-11-27 15:00:53','2024-12-31 23:59:59','2025-11-27 15:00:53','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(2,1,2,2,'2025-11-27 15:00:53','2024-08-31 23:59:59',NULL,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(3,2,3,3,'2025-11-27 15:00:53','2024-12-31 23:59:59','2025-11-27 15:00:53','2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(4,2,5,1,'2025-11-27 15:00:53','2024-12-31 23:59:59',NULL,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(5,3,4,2,'2025-11-27 15:00:53','2024-12-31 23:59:59',NULL,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL),(6,3,6,2,'2025-11-27 15:00:53','2024-12-31 23:59:59',NULL,'2025-11-27 15:00:53',1,'2025-11-27 15:00:53',NULL);
/*!40000 ALTER TABLE `coupon_user_allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user_restriction`
--

DROP TABLE IF EXISTS `coupon_user_restriction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_user_restriction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coupon_id` int NOT NULL,
  `user_id` int NOT NULL,
  `restriction_type` enum('whitelist','blacklist') DEFAULT NULL,
  `reason` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_coupon_user_restriction` (`coupon_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `coupon_user_restriction_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE,
  CONSTRAINT `coupon_user_restriction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `coupon_user_restriction_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user_restriction`
--

LOCK TABLES `coupon_user_restriction` WRITE;
/*!40000 ALTER TABLE `coupon_user_restriction` DISABLE KEYS */;
INSERT INTO `coupon_user_restriction` VALUES (1,1,1,'whitelist','VIP customer special access',1,'2025-11-27 15:00:53',1),(2,2,2,'blacklist','User abused previous coupons',1,'2025-11-27 15:00:53',1),(3,3,3,'whitelist','Employee special discount',1,'2025-11-27 15:00:53',1),(4,4,1,'whitelist','Top referrer bonus',1,'2025-11-27 15:00:53',1);
/*!40000 ALTER TABLE `coupon_user_restriction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `currency_id` int NOT NULL AUTO_INCREMENT,
  `currency_code` varchar(3) NOT NULL,
  `currency_name` varchar(100) NOT NULL,
  `symbol` varchar(10) DEFAULT NULL,
  `exchange_rate` decimal(10,4) DEFAULT '1.0000',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`currency_id`),
  UNIQUE KEY `currency_code` (`currency_code`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `currency_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'USD','US Dollar','$',1.0000,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'EUR','Euro','€',0.9200,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'GBP','British Pound','£',0.7900,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'JPY','Japanese Yen','¥',148.5000,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'AUD','Australian Dollar','A$',1.5200,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination` (
  `destination_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `destination_category` int DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`destination_id`),
  KEY `destination_category` (`destination_category`),
  KEY `status` (`status`),
  CONSTRAINT `destination_ibfk_1` FOREIGN KEY (`destination_category`) REFERENCES `destination_categories` (`id`),
  CONSTRAINT `destination_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES (1,'Kitulgala','Famous for white-water rafting and jungle adventures',1,1,'Sabaragamuwa Province',6.98860000,80.42500000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(2,'Horton Plains','Hiking trails with stunning landscapes and World End view',1,1,'Central Province',6.80260000,80.79910000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(3,'Knuckles Mountain Range','Trekking and camping in scenic mountain ranges',1,1,'Central Province',7.38980000,80.78400000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(4,'Adams Peak','Pilgrimage and trekking adventure with panoramic sunrise',1,1,'Sabaragamuwa Province',6.80900000,80.49900000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(5,'Sinharaja Forest','Rainforest trekking with waterfalls and wildlife spotting',1,1,'Southern Province',6.40200000,80.40200000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(6,'Yala National Park','Most visited and second largest national park',1,2,'Southern Province',6.37250000,81.51940000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(7,'Udawalawe National Park','Famous for large herds of elephants and safari experiences',1,2,'Southern Province',6.42670000,80.89870000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(8,'Wilpattu National Park','Largest national park, known for leopards',1,2,'North Western Province',8.45900000,80.10000000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(9,'Minneriya National Park','Famous for elephant gatherings during dry season',1,2,'North Central Province',8.00300000,80.93600000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(10,'Bundala National Park','Coastal wetlands with migratory birds and wildlife',1,2,'Southern Province',5.99100000,80.59000000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(11,'Sigiriya','Ancient rock fortress and UNESCO World Heritage site',1,3,'Central Province',7.95700000,80.76030000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(12,'Anuradhapura','Ancient capital with sacred Buddhist sites',1,3,'North Central Province',8.31140000,80.40370000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(13,'Polonnaruwa','Historical city with ancient ruins and temples',1,3,'North Central Province',7.94000000,81.00000000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(14,'Kandy','Cultural capital with Temple of the Tooth and Perahera festival',1,3,'Central Province',7.29060000,80.63370000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(15,'Galle Fort','UNESCO-listed fort with Dutch architecture and cultural sites',1,3,'Southern Province',6.03200000,80.21700000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(16,'Mirissa','Beautiful beach town famous for whale watching',1,4,'Southern Province',5.94500000,80.44890000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(17,'Unawatuna','Popular beach with water sports and nightlife',1,4,'Southern Province',5.94450000,80.25800000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(18,'Bentota','Resort town with beaches, water sports, and river tours',1,4,'Western Province',6.42100000,80.00500000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(19,'Trincomalee','East coast beach with snorkeling and diving',1,4,'Eastern Province',8.57110000,81.23350000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(20,'Arugam Bay','World-renowned surfing destination',1,4,'Eastern Province',6.83900000,81.83400000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(21,'Ella','Picturesque hill country town with stunning views',1,5,'Uva Province',6.86670000,81.04670000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(22,'Nuwara Eliya','Cool climate hill station with tea plantations',1,5,'Central Province',6.94970000,80.78910000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(23,'Haputale','Hill town with tea estates and breathtaking viewpoints',1,5,'Uva Province',6.77900000,80.99900000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(24,'Nuwara Eliya Hakgala','Tea gardens and botanical garden exploration',1,5,'Central Province',6.90000000,80.78300000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL),(25,'Dambatenne','Scenic tea estate in central highlands',1,5,'Central Province',6.84500000,80.94300000,'2025-10-04 16:04:11',1,'2025-10-04 16:04:11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_categories`
--

DROP TABLE IF EXISTS `destination_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_categories_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_categories`
--

LOCK TABLES `destination_categories` WRITE;
/*!40000 ALTER TABLE `destination_categories` DISABLE KEYS */;
INSERT INTO `destination_categories` VALUES (1,'Adventure','Thrilling activities such as hiking, rafting, and outdoor challenges',1,'2025-10-04 16:03:45',1,'2025-10-04 16:03:45',NULL,NULL,NULL),(2,'Wildlife','National parks and safaris to experience animals in their natural habitat',1,'2025-10-04 16:03:45',1,'2025-10-04 16:03:45',NULL,NULL,NULL),(3,'Cultural & Heritage','Cultural festivals, temples, and heritage sites',1,'2025-10-04 16:03:45',1,'2025-10-04 16:03:45',NULL,NULL,NULL),(4,'Beach','Coastal destinations, surfing spots, and beach resorts',1,'2025-10-04 16:03:45',1,'2025-10-04 16:03:45',NULL,NULL,NULL),(5,'Hill Country','Scenic highlands with tea estates, waterfalls, and train journeys',1,'2025-10-04 16:03:45',1,'2025-10-04 16:03:45',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_categories_images`
--

DROP TABLE IF EXISTS `destination_categories_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_categories_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_categories_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_categories_id` (`destination_categories_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_categories_images_ibfk_1` FOREIGN KEY (`destination_categories_id`) REFERENCES `destination_categories` (`id`),
  CONSTRAINT `destination_categories_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_categories_images`
--

LOCK TABLES `destination_categories_images` WRITE;
/*!40000 ALTER TABLE `destination_categories_images` DISABLE KEYS */;
INSERT INTO `destination_categories_images` VALUES (1,1,'White Water Rafting','Adventurers rafting on the Kelani River in Kitulgala','/images/destination-categories/white-water-rafting.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(2,1,'Mountain Hiking','Trekkers on scenic trails in Knuckles Range','/images/destination-categories/mountain-hiking.webp',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(3,1,'Rock Climbing','Climber scaling a rock face at Ella Gap','/images/destination-categories/rock-climbing.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(4,1,'Forest Camping','Overnight tent camp surrounded by rainforest','/images/destination-categories/forest-camping.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(5,1,'Canyoning Adventure','Exploring waterfalls and natural rock pools','/images/destination-categories/canyoning-adventure.webp',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(6,2,'Yala Leopard','Leopard resting on a tree branch in Yala National Park','/images/destination-categories/yala-leopard.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(7,2,'Elephant Herd','Elephants grazing in Udawalawe Park','/images/destination-categories/elephant-herd.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(8,2,'Bird Watching','Flamingos and pelicans at Bundala wetlands','/images/destination-categories/bird-watching.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(9,2,'Safari Jeep','Tourists enjoying a jeep safari through the wild','/images/destination-categories/safari-jeep.webp',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(10,2,'Water Buffalo Scene','Peaceful moment at Wilpattu’s natural lakes','/images/destination-categories/water-buffalo-scene.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(11,3,'Temple of the Tooth','Kandy’s sacred temple illuminated at night','/images/destination-categories/temple-of-the-tooth.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(12,3,'Ancient Ruins','Historic ruins at Polonnaruwa','/images/destination-categories/ancient-ruins.webp',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(13,3,'Sigiriya Frescoes','Famous wall paintings of ancient maidens','/images/destination-categories/sigiriya-frescoes.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(14,3,'Kandy Perahera','Colorful traditional parade with elephants and dancers','/images/destination-categories/kandy-perahera.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(15,3,'Galle Fort Streets','Colonial architecture and cobblestone alleys','/images/destination-categories/galle-fort-streets.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(16,4,'Tropical Beach','Golden sand beach with palm trees','/images/destination-categories/tropical-beach.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(17,4,'Surfing Waves','Surfers catching waves at Arugam Bay','/images/destination-categories/surfing-waves.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(18,4,'Sunset View','Beautiful sunset at Mirissa Beach','/images/destination-categories/sunset-view.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(19,4,'Snorkeling Reef','Colorful coral reef near Trincomalee','/images/destination-categories/snorkeling-reef.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(20,4,'Beach Resort','Luxury seaside resort with coconut trees','/images/destination-categories/beach-resort.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(21,5,'Tea Plantations','Lush green tea fields in Nuwara Eliya','/images/destination-categories/tea-plantations.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(22,5,'Train through Hills','Scenic blue train crossing Nine Arches Bridge','/images/destination-categories/train-through-hills.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(23,5,'Waterfalls','Ravana Falls cascading down rocky cliffs','/images/destination-categories/waterfalls.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(24,5,'Mountain Viewpoint','Panoramic view of Ella Gap valley','/images/destination-categories/mountain-viewpoint.webp',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL),(25,5,'Cool Mist Morning','Foggy sunrise over Haputale hills','/images/destination-categories/cool-mist-morning.jpg',1,'2025-10-04 19:30:16',1,'2025-10-04 19:30:16',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_categories_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_hero_section`
--

DROP TABLE IF EXISTS `destination_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_hero_section`
--

LOCK TABLES `destination_hero_section` WRITE;
/*!40000 ALTER TABLE `destination_hero_section` DISABLE KEYS */;
INSERT INTO `destination_hero_section` VALUES (1,'colombo','https://images.unsplash.com/photo-1585506936724-fa0c19c7b7c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Colombo','The Vibrant Capital City','Experience the bustling commercial capital with its mix of colonial architecture, modern skyscrapers, vibrant markets, and delicious street food.','Explore Colombo','/destinations/colombo','City Guide','/guide/colombo',1,1,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(2,'kandy','https://images.unsplash.com/photo-1558272729-5e0165e4fde6?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Kandy','Cultural Heart of Sri Lanka','The last royal capital, home to the sacred Temple of the Tooth, beautiful botanical gardens, and surrounded by misty hills.','Visit Kandy','/destinations/kandy','Cultural Events','/events/kandy',1,2,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(3,'galle','https://images.unsplash.com/photo-1528181304800-259b08848526?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Galle','Dutch Fort & Coastal Charm','A UNESCO World Heritage Site featuring a 17th-century Dutch fort, boutique hotels, art galleries, and stunning sunsets over the Indian Ocean.','Discover Galle','/destinations/galle','Fort History','/guide/galle-fort',1,3,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(4,'sigiriya','https://images.unsplash.com/photo-1548013146-72479768bada?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Sigiriya','Ancient Rock Fortress','The iconic 5th-century rock fortress known as the \"Eighth Wonder of the World\" with its lion gate, water gardens, and ancient frescoes.','Climb Sigiriya','/destinations/sigiriya','History Guide','/guide/sigiriya-history',1,4,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(5,'ella','https://images.unsplash.com/photo-1592210454359-9043f067919b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Ella','Hill Country Paradise','A charming mountain town offering spectacular hikes, tea plantations, waterfalls, and the famous Nine Arch Bridge.','Explore Ella','/destinations/ella','Hiking Trails','/guide/ella-hiking',1,5,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(6,'mirissa','https://images.unsplash.com/photo-1552465011-b4e30bf7349d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Mirissa','Whale Watching & Beach Bliss','A picturesque fishing village turned beach paradise, famous for whale watching, surfing, and stunning crescent-shaped beaches.','Beach Holidays','/destinations/mirissa','Whale Season','/guide/whale-watching',1,6,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(7,'yala','https://images.unsplash.com/photo-1579444741990-6e31c9b09d52?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Yala National Park','Wildlife Safari Destination','Sri Lanka\'s premier wildlife sanctuary, famous for its leopard population, elephants, and diverse ecosystems from jungle to beach.','Safari Tours','/destinations/yala','Wildlife Guide','/guide/yala-wildlife',1,7,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(8,'nuwara-eliya','https://images.unsplash.com/photo-1523348837708-15d4a09cfac2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Nuwara Eliya','Little England','A colonial-era hill station surrounded by tea plantations, with cool climate, golf courses, and English-style architecture.','Hill Station Getaway','/destinations/nuwara-eliya','Tea Estate Tours','/guide/nuwara-tea',1,8,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(9,'anuradhapura','https://images.unsplash.com/photo-1536152471326-642d4aa9cba5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Anuradhapura','Ancient Capital City','One of the world\'s oldest continuously inhabited cities, featuring ancient monasteries, giant stupas, and the sacred Bodhi tree.','Historical Tour','/destinations/anuradhapura','Archaeological Guide','/guide/anuradhapura-ruins',1,9,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(10,'polonnaruwa','https://images.unsplash.com/photo-1579444741963-5bce5eb9d1d2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Polonnaruwa','Medieval Capital','A UNESCO World Heritage Site with well-preserved ruins, giant Buddha statues, and the ancient Parakrama Samudra reservoir.','Explore Ruins','/destinations/polonnaruwa','Medieval History','/guide/polonnaruwa-history',1,10,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(11,'arugam-bay','https://images.unsplash.com/photo-1506929562872-bb421503ef21?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Arugam Bay','Surfing Paradise','World-class surf destination on the east coast, with consistent waves, laid-back vibe, and nearby lagoons and national parks.','Surf Packages','/destinations/arugam-bay','Wave Guide','/guide/arugam-surf',1,11,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(12,'bentota','https://images.unsplash.com/photo-1544551763-46a013bb70d5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Bentota','Beach Resort Town','A popular beach destination with golden sands, water sports, river cruises, and luxury resorts along the southwestern coast.','Beach Resorts','/destinations/bentota','Water Sports','/activities/bentota',1,12,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(13,'dambulla','https://images.unsplash.com/photo-1585506936724-fa0c19c7b7c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Dambulla','Cave Temple Complex','Home to the famous Golden Temple and cave temples filled with Buddha statues and frescoes dating back over 2,000 years.','Temple Visit','/destinations/dambulla','Religious Sites','/guide/dambulla-temples',1,13,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(14,'trincomalee','https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Trincomalee','Natural Harbor & Beaches','A historic port city with beautiful beaches, whale watching, hot springs, and one of the world\'s finest natural harbors.','Coastal Escape','/destinations/trincomalee','Beach Guide','/guide/trincomalee-beaches',1,14,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(15,'hatton','https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Hatton','Gateway to Adam\'s Peak','The main access point for the Adam\'s Peak pilgrimage, surrounded by tea estates and offering spectacular mountain views.','Pilgrimage Tours','/destinations/hatton','Climbing Info','/guide/adams-peak',1,15,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(16,'negombo','https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Negombo','Fishing Town near Airport','A traditional fishing town with canals, beaches, and seafood restaurants, conveniently located near the international airport.','Stopover Guide','/destinations/negombo','Airport Transfer','/services/airport-transfers',1,16,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(17,'udawalawe','https://images.unsplash.com/photo-1579444741963-5bce5eb9d1d2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Udawalawe','Elephant Sanctuary','National park famous for its large elephant herds, bird watching, and the Udawalawe Elephant Transit Home.','Elephant Safaris','/destinations/udawalawe','Wildlife Calendar','/guide/udawalawe',1,17,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL),(18,'jaffna','https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Jaffna','Northern Cultural Hub','The cultural capital of the Tamil people, featuring unique cuisine, Hindu temples, colonial forts, and island hopping.','Northern Tour','/destinations/jaffna','Cultural Guide','/guide/jaffna-culture',1,18,'2025-12-16 03:35:03',1,'2025-12-16 03:35:03',1,NULL,NULL);
/*!40000 ALTER TABLE `destination_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_history`
--

DROP TABLE IF EXISTS `destination_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `package_schedule_id` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `event_date` date DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_id` (`destination_id`),
  KEY `package_schedule_id` (`package_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_history_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`),
  CONSTRAINT `destination_history_ibfk_2` FOREIGN KEY (`package_schedule_id`) REFERENCES `package_schedule` (`id`),
  CONSTRAINT `destination_history_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_history`
--

LOCK TABLES `destination_history` WRITE;
/*!40000 ALTER TABLE `destination_history` DISABLE KEYS */;
INSERT INTO `destination_history` VALUES (1,1,NULL,'Adventurer’s Paradise Opens','Kitulgala gained fame for white-water rafting and adventure tourism during the 1990s.','1995-06-10',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(2,1,NULL,'Eco Lodges Introduced','The area saw the establishment of eco-lodges promoting sustainable tourism.','2012-09-15',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(3,2,NULL,'Declared a National Park','Horton Plains was officially declared a national park due to its biodiversity and natural beauty.','1988-03-01',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(4,2,NULL,'World’s End Trail Restoration','Popular hiking trails at World’s End underwent safety improvements and restoration.','2017-11-25',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(5,3,NULL,'UNESCO World Heritage Site','The Knuckles Range was added to the Central Highlands UNESCO World Heritage list.','2010-07-31',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(6,3,NULL,'Community Eco-Camping Introduced','Local communities began offering eco-friendly camping experiences to travelers.','2020-02-18',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(7,4,NULL,'Pilgrimage Route Restoration','The historic stairways and shelters for pilgrims were renovated for safety and accessibility.','2014-01-05',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(8,4,NULL,'Lighting Project Inaugurated','Solar-powered lights were installed along the path to assist pilgrims at night.','2022-12-20',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(9,5,NULL,'Declared a Biosphere Reserve','Sinharaja Forest Reserve was declared a UNESCO Biosphere Reserve due to its endemic species.','1978-05-21',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL),(10,5,NULL,'Rainforest Conservation Initiative','A government-led project began to enhance conservation and eco-tourism opportunities.','2018-09-10',1,'2025-10-23 15:08:12',1,'2025-10-23 15:08:12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_history_images`
--

DROP TABLE IF EXISTS `destination_history_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_history_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_history_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_history_id` (`destination_history_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_history_images_ibfk_1` FOREIGN KEY (`destination_history_id`) REFERENCES `destination_history` (`id`),
  CONSTRAINT `destination_history_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_history_images`
--

LOCK TABLES `destination_history_images` WRITE;
/*!40000 ALTER TABLE `destination_history_images` DISABLE KEYS */;
INSERT INTO `destination_history_images` VALUES (1,1,'Rafting on Kelani River','Tourists navigating rapids during early rafting events.','/images/destinations/kitulgala-rapids.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(2,1,'Adventure Hub','Early adventure camps built along the Kelani River.','/images/destinations/jungle-trek.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(3,2,'Eco Lodge Cabin','Sustainable wooden eco-lodge surrounded by forest.','/images/destinations/river-edge-camp.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(4,2,'Rainforest Lodge Interior','Interior view of an eco-lodge designed for nature lovers.','/images/destinations/worlds-end-View.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(5,3,'Grassland View','Endless grasslands and cloud forests of Horton Plains.','/images/destinations/bakers-falls.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(6,3,'Baker’s Falls','Famous waterfall inside Horton Plains National Park.','/images/destinations/grasslands.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(7,4,'World’s End Viewpoint','Visitors enjoying the view from World’s End cliff.','/images/destinations/knuckles-peaks.jpeg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(8,4,'Trail Restoration Work','Workers maintaining hiking paths to World’s End.','/images/destinations/camping-at-knuckles.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(9,5,'Knuckles Mountain Peaks','Misty peaks of Knuckles mountain range.','/images/destinations/river-crossing.webp',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(10,5,'UNESCO Recognition Plaque','Plaque marking Knuckles as a World Heritage site.','/images/destinations/adams-peak-sunrise.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(11,6,'Eco Camp Setup','Tents set up by local eco-tour guides.','/images/destinations/sacred-steps.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(12,6,'Campfire Experience','Travelers enjoying eco-friendly camping at Knuckles.','/images/destinations/temple-at-summit.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(13,7,'Pilgrims on Trail','Devotees ascending the Adam’s Peak staircase.','/images/destinations/sinharaja-waterfall.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(14,7,'Rest Stop Area','Renovated resting point for pilgrims midway up the peak.','/images/destinations/rainforest-trail.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(15,8,'Solar Lights at Dusk','Solar lights illuminating the trail during pilgrimage season.','/images/destinations/tropical-flora.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(16,8,'Night Pilgrimage','Pilgrims walking under newly installed solar lighting.','/images/destinations/yala-safari.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(17,9,'Dense Rainforest Canopy','Thick canopy of the Sinharaja Forest Reserve.','/images/destinations/yala-landscape.webp',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(18,9,'Endemic Flora','Rare plant species endemic to Sinharaja Forest.','/images/destinations/wildlife-close-up.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(19,10,'Tree Planting Project','Volunteers planting trees under the conservation initiative.','/images/destinations/udawalawe-elephants.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL),(20,10,'Community Awareness Program','Local students learning about rainforest conservation.','/images/destinations/safari-jeep-ride.jpg',1,'2025-10-23 15:08:12',1,'2025-10-23 16:24:54',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_history_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_images`
--

DROP TABLE IF EXISTS `destination_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_id` (`destination_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_images_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`),
  CONSTRAINT `destination_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_images`
--

LOCK TABLES `destination_images` WRITE;
/*!40000 ALTER TABLE `destination_images` DISABLE KEYS */;
INSERT INTO `destination_images` VALUES (1,1,'Kitulgala Rapids','White-water rafting on the Kelani River','/images/destinations/kitulgala-rapids.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(2,1,'Jungle Trek','Adventure trail through lush rainforest','/images/destinations/jungle-trek.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(3,1,'River Edge Camp','Camping spot beside Kelani River','/images/destinations/river-edge-camp.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(4,2,'World’s End View','Dramatic drop at Horton Plains National Park','/images/destinations/worlds-end-View.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(5,2,'Bakers Falls','Beautiful waterfall inside the park','/images/destinations/bakers-falls.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(6,2,'Grasslands','Vast plains with scenic hiking trails','/images/destinations/grasslands.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(7,3,'Knuckles Peaks','View of the Knuckles Mountain Range','/images/destinations/knuckles-peaks.jpeg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(8,3,'Camping at Knuckles','Night camping with misty views','/images/destinations/camping-at-knuckles.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(9,3,'River Crossing','Adventure trekking near Knuckles rivers','/images/destinations/river-crossing.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(10,4,'Adam’s Peak Sunrise','Spectacular sunrise view at the summit','/images/destinations/adams-peak-sunrise.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(11,4,'Sacred Steps','Pilgrims ascending Adam’s Peak','/images/destinations/sacred-steps.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(12,4,'Temple at Summit','Shrine at the peak with religious significance','/images/destinations/temple-at-summit.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(13,5,'Sinharaja Waterfall','Waterfall inside Sinharaja Forest Reserve','/images/destinations/sinharaja-waterfall.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(14,5,'Rainforest Trail','Dense forest trail with rich biodiversity','/images/destinations/rainforest-trail.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(15,5,'Tropical Flora','Rare plants and trees in Sinharaja','/images/destinations/tropical-flora.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(16,6,'Yala Safari','Leopard sighting during safari','/images/destinations/yala-safari.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(17,6,'Yala Landscape','Dry zone forest and lake view','/images/destinations/yala-landscape.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(18,6,'Wildlife Close-up','Elephants and birds in Yala','/images/destinations/wildlife-close-up.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(19,7,'Udawalawe Elephants','Elephant herd near the lake','/images/destinations/udawalawe-elephants.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(20,7,'Safari Jeep Ride','Tourists on jeep exploring wildlife','/images/destinations/safari-jeep-ride.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(21,7,'Reservoir View','Panoramic Udawalawe reservoir','/images/destinations/reservoir-view.png',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(22,8,'Wilpattu Leopard','Leopard resting near waterhole','/images/destinations/wilpattu-leopard.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(23,8,'Forest Tracks','Jeep track through Wilpattu forest','/images/destinations/forest-tracks.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(24,8,'Birdlife','Migratory birds near Villu lakes','/images/destinations/birdlife.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(25,9,'Minneriya Elephants','The Great Elephant Gathering','/images/destinations/minneriya-elephants.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(26,9,'Reservoir Sunset','Sunset reflection at Minneriya lake','/images/destinations/reservoir-sunset.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(27,9,'Wildlife Spotting','Tour jeep exploring the park','/images/destinations/wildlife-spotting.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(28,10,'Bundala Birds','Migratory flamingos in wetlands','/images/destinations/bundala-birds.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(29,10,'Coastal Lagoon','Scenic lagoon and dunes','/images/destinations/coastal-lagoon.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(30,10,'Salt Pans','Traditional salt fields near Bundala','/images/destinations/salt-pans.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(31,11,'Sigiriya Rock','Iconic rock fortress view','/images/destinations/sigiriya-rock.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(32,11,'Mirror Wall','Ancient frescoes and paintings','/images/destinations/mirror-wall.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(33,11,'Lion’s Paw Entrance','Stairway to the summit','/images/destinations/lions-paw-entrance.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(34,12,'Anuradhapura Stupa','Sacred Ruwanwelisaya Dagoba','/images/destinations/anuradhapura-stupa.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(35,12,'Sacred Bo Tree','Oldest documented tree in the world','/images/destinations/sacred-bo-tree.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(36,12,'Ancient Ruins','Stone carvings and historic sites','/images/destinations/ancient-ruins.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(37,13,'Polonnaruwa Vatadage','Circular relic house of Polonnaruwa','/images/destinations/polonnaruwa-vatadage.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(38,13,'Gal Vihara','Famous rock-cut Buddha statues','/images/destinations/gal-vihara.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(39,13,'Ancient City Walls','Ruins of the ancient kingdom','/images/destinations/ancient-city-walls.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(40,14,'Temple of the Tooth','Sacred temple in Kandy city','/images/destinations/temple-of-the-tooth.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(41,14,'Kandy Lake','Beautiful lake at the city center','/images/destinations/kandy-lake.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(42,14,'Cultural Dance','Traditional Kandyan dance performance','/images/destinations/cultural-dance.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(43,15,'Galle Fort Walls','Dutch fort overlooking the ocean','/images/destinations/galle-fort-walls.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(44,15,'Lighthouse View','Iconic Galle lighthouse','/images/destinations/lighthouse-view.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(45,15,'Old Streets','Colonial architecture and cafes','/images/destinations/old-streets.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(46,16,'Mirissa Beach','Golden sands and calm waves','/images/destinations/mirissa-beach.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(47,16,'Whale Watching','Blue whale spotting trip','/images/destinations/whale-watching.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(48,16,'Coconut Hill','Scenic hill viewpoint at Mirissa','/images/destinations/coconut-hill.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(49,17,'Unawatuna Bay','Turquoise waters and palm trees','/images/destinations/unawatuna-bay.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(50,17,'Beachfront Cafes','Popular nightlife along the shore','/images/destinations/beachfront-cafes.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(51,17,'Coral Reef','Snorkeling at Unawatuna reef','/images/destinations/coral-reef.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(52,18,'Bentota Beach','Resort-lined sandy coast','/images/destinations/bentota-beach.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(53,18,'River Boat Ride','Bentota River mangrove tour','/images/destinations/river-boat-ride.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(54,18,'Water Sports','Jet ski and banana boat rides','/images/destinations/water-sports.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(55,19,'Trincomalee Bay','Scenic coastal harbor view','/images/destinations/trincomalee-bay.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(56,19,'Pigeon Island','Snorkeling at coral reef','/images/destinations/pigeon-island.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(57,19,'Koneswaram Temple','Clifftop Hindu temple view','/images/destinations/koneswaram-temple.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(58,20,'Arugam Bay Surfing','Surfers catching morning waves','/images/destinations/arugam-bay-surfing.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(59,20,'Beach Sunrise','Golden sunrise on the east coast','/images/destinations/beach-sunrise.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(60,20,'Local Chill Spots','Cafes and palm huts by the beach','/images/destinations/local-chill-spots.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(61,21,'Ella Rock View','Breathtaking panoramic views','/images/destinations/ella-rock-view.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(62,21,'Nine Arches Bridge','Train crossing the iconic bridge','/images/destinations/nine-arches-bridge.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(63,21,'Little Adam’s Peak','Popular short hike near Ella town','/images/destinations/little-adams-peak.webp',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(64,22,'Gregory Lake','Boating and lake views at Nuwara Eliya','/images/destinations/gregory-lake.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(65,22,'Tea Plantations','Scenic green tea fields','/images/destinations/tea-plantations.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(66,22,'Colonial Buildings','British-era architecture in town','/images/destinations/colonial-buildings.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(67,23,'Lipton’s Seat','Famous viewpoint at Haputale','/images/destinations/liptons-seat.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(68,23,'Tea Factory Visit','Tour through tea production','/images/destinations/tea-factory-visit.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(69,23,'Cloud Forest Trail','Misty forest walk in Haputale hills','/images/destinations/cloud-forest-trail.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(70,24,'Hakgala Gardens','Botanical garden with diverse flora','/images/destinations/hakgala-gardens.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(71,24,'Tea Hills','Rolling hills near Hakgala area','/images/destinations/tea-hills.jpeg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(72,24,'Cool Mist Views','Foggy mornings in Nuwara Eliya hills','/images/destinations/cool-mist-views.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(73,25,'Dambatenne Estate','Historic tea estate with scenic views','/images/destinations/dambatenne-estate.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(74,25,'Tea Workers','Local workers harvesting tea leaves','/images/destinations/tea-workers.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL),(75,25,'Mountain Roads','Curvy roads through tea country','/images/destinations/mountain-roads.jpg',1,'2025-10-04 19:07:07',1,'2025-10-04 19:07:07',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_review`
--

DROP TABLE IF EXISTS `destination_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `user_id` int NOT NULL,
  `review_text` text,
  `rating` decimal(3,2) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `destination_id` (`destination_id`),
  KEY `user_id` (`user_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_review_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_review`
--

LOCK TABLES `destination_review` WRITE;
/*!40000 ALTER TABLE `destination_review` DISABLE KEYS */;
INSERT INTO `destination_review` VALUES (1,1,1,'Amazing place to visit! Highly recommend.',4.50,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(2,2,2,'Beautiful scenery but crowded on weekends.',4.00,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(3,3,1,'A must-see attraction.',5.00,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(4,4,2,'Good place for family trips.',4.20,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(5,5,1,'Nice, but a bit pricey.',3.80,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_review_comment`
--

DROP TABLE IF EXISTS `destination_review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_review_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `comment_text` text NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `parent_comment_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `review_id` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `status` (`status`),
  KEY `parent_comment_id` (`parent_comment_id`),
  CONSTRAINT `destination_review_comment_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `destination_review` (`review_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_ibfk_4` FOREIGN KEY (`parent_comment_id`) REFERENCES `destination_review_comment` (`comment_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_review_comment`
--

LOCK TABLES `destination_review_comment` WRITE;
/*!40000 ALTER TABLE `destination_review_comment` DISABLE KEYS */;
INSERT INTO `destination_review_comment` VALUES (1,1,2,'Totally agree! It was amazing when I visited.',1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL,NULL),(2,2,1,'Yes, weekends are always busy here.',1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL,NULL),(3,3,2,'Thanks for the recommendation!',1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL,NULL),(4,4,1,'Great tip for family trips.',1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL,NULL),(5,5,2,'Good to know, I might wait for discounts.',1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_review_comment_reaction`
--

DROP TABLE IF EXISTS `destination_review_comment_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_review_comment_reaction` (
  `comment_reaction_id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`comment_reaction_id`),
  KEY `comment_id` (`comment_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_review_comment_reaction_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `destination_review_comment` (`comment_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_comment_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_review_comment_reaction`
--

LOCK TABLES `destination_review_comment_reaction` WRITE;
/*!40000 ALTER TABLE `destination_review_comment_reaction` DISABLE KEYS */;
INSERT INTO `destination_review_comment_reaction` VALUES (1,1,1,1,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(2,2,2,2,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(3,3,1,1,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(4,4,2,2,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(5,5,1,1,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_review_comment_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_review_images`
--

DROP TABLE IF EXISTS `destination_review_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_review_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `review_id` (`review_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_review_images_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `destination_review` (`review_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_review_images`
--

LOCK TABLES `destination_review_images` WRITE;
/*!40000 ALTER TABLE `destination_review_images` DISABLE KEYS */;
INSERT INTO `destination_review_images` VALUES (1,1,'Sunset View','Beautiful sunset over the lake','/images/destinations/beach-sunrise.jpg',1,'2025-10-21 16:11:39',1,'2025-10-23 16:55:11',NULL,NULL,NULL),(2,1,'Entrance','Main entrance of the destination','/images/destinations/local-chill-spots.jpg',1,'2025-10-21 16:11:39',1,'2025-10-23 16:55:11',NULL,NULL,NULL),(3,2,'Scenic Spot','Panoramic view of the park','/images/destinations/ella-rock-view.jpg',1,'2025-10-21 16:11:39',2,'2025-10-23 16:55:11',NULL,NULL,NULL),(4,3,'Fountain','Historic fountain in the center','/images/destinations/nine-arches-bridge.jpg',1,'2025-10-21 16:11:39',1,'2025-10-23 16:55:11',NULL,NULL,NULL),(5,4,'Playground','Kids enjoying the playground','/images/destinations/colonial-buildings.jpg',1,'2025-10-21 16:11:39',2,'2025-10-23 16:55:11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_review_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_review_reaction`
--

DROP TABLE IF EXISTS `destination_review_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_review_reaction` (
  `review_reaction_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`review_reaction_id`),
  KEY `review_id` (`review_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `destination_review_reaction_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `destination_review` (`review_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `destination_review_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_review_reaction`
--

LOCK TABLES `destination_review_reaction` WRITE;
/*!40000 ALTER TABLE `destination_review_reaction` DISABLE KEYS */;
INSERT INTO `destination_review_reaction` VALUES (1,1,2,1,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(2,2,1,2,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(3,3,2,1,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL),(4,4,1,1,1,'2025-10-21 16:11:39',1,'2025-10-21 16:11:39',NULL,NULL,NULL),(5,5,2,2,1,'2025-10-21 16:11:39',2,'2025-10-21 16:11:39',NULL,NULL,NULL);
/*!40000 ALTER TABLE `destination_review_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_wishlist`
--

DROP TABLE IF EXISTS `destination_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `destination_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `destination_id` (`destination_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `destination_wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `destination_wishlist_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `destination_wishlist_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_wishlist`
--

LOCK TABLES `destination_wishlist` WRITE;
/*!40000 ALTER TABLE `destination_wishlist` DISABLE KEYS */;
INSERT INTO `destination_wishlist` VALUES (1,1,1,1,'2025-11-29 05:01:58'),(2,1,2,1,'2025-11-29 05:01:58'),(3,2,3,1,'2025-11-29 05:01:58'),(4,2,4,2,'2025-11-29 05:01:58');
/*!40000 ALTER TABLE `destination_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destination_wishlist_history`
--

DROP TABLE IF EXISTS `destination_wishlist_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination_wishlist_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `destination_id` int NOT NULL,
  `wishlist_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `destination_id` (`destination_id`),
  KEY `wishlist_id` (`wishlist_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `destination_wishlist_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `destination_wishlist_history_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `destination_wishlist_history_ibfk_3` FOREIGN KEY (`wishlist_id`) REFERENCES `destination_wishlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `destination_wishlist_history_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination_wishlist_history`
--

LOCK TABLES `destination_wishlist_history` WRITE;
/*!40000 ALTER TABLE `destination_wishlist_history` DISABLE KEYS */;
INSERT INTO `destination_wishlist_history` VALUES (1,1,1,1,1,'2025-11-29 05:01:58'),(2,2,4,4,2,'2025-11-29 05:01:58');
/*!40000 ALTER TABLE `destination_wishlist_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_verified`
--

DROP TABLE IF EXISTS `email_verified`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_verified` (
  `email_verified_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `send_code` varchar(50) DEFAULT NULL,
  `typed_code` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_id` int DEFAULT NULL,
  `which_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email_verified_id`),
  KEY `user_id` (`user_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `email_verified_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `email_verified_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `verified_status` (`verified_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_verified`
--

LOCK TABLES `email_verified` WRITE;
/*!40000 ALTER TABLE `email_verified` DISABLE KEYS */;
INSERT INTO `email_verified` VALUES (1,1,'662140','','2025-09-21 14:06:10','2025-11-29 04:27:04',1,'primary'),(2,2,'XYZ789',NULL,'2025-09-21 14:06:10','2025-11-29 03:30:12',1,'primary'),(3,1,'313920','','2025-09-21 14:06:10','2025-11-29 04:29:14',1,'secondary');
/*!40000 ALTER TABLE `email_verified` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_assets`
--

DROP TABLE IF EXISTS `employee_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_assets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `asset_type` varchar(100) NOT NULL,
  `asset_id` varchar(100) DEFAULT NULL,
  `asset_name` varchar(255) NOT NULL,
  `serial_number` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `assigned_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `condition_on_assignment` text,
  `condition_on_return` text,
  `notes` text,
  `assigned_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `assigned_by` (`assigned_by`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_assets_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_assets_ibfk_2` FOREIGN KEY (`assigned_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_assets_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_assets`
--

LOCK TABLES `employee_assets` WRITE;
/*!40000 ALTER TABLE `employee_assets` DISABLE KEYS */;
INSERT INTO `employee_assets` VALUES (1,5,'vehicle','CAR-001','Toyota Hiace Van','VIN123456789',NULL,'2022-06-01',NULL,'Brand new vehicle, full tank, clean interior',NULL,NULL,3,'2025-12-04 15:42:11',1),(2,4,'equipment','AUDIO-001','Tour Guide Audio System','SER123456',NULL,'2022-02-15',NULL,'New audio system with microphone and speaker',NULL,NULL,3,'2025-12-04 15:42:11',1),(3,6,'laptop','LT-001','Dell Latitude Laptop','SN7890123',NULL,'2022-07-10',NULL,'New laptop with necessary software installed',NULL,NULL,2,'2025-12-04 15:42:11',1),(4,7,'camera','CAM-001','Canon DSLR Camera','SN4567890',NULL,'2022-08-20',NULL,'Professional camera for marketing photos',NULL,NULL,2,'2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_attendance`
--

DROP TABLE IF EXISTS `employee_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `attendance_date` date NOT NULL,
  `check_in_time` time DEFAULT NULL,
  `check_out_time` time DEFAULT NULL,
  `total_hours` decimal(5,2) GENERATED ALWAYS AS ((case when (`check_out_time` > `check_in_time`) then timestampdiff(HOUR,`check_in_time`,`check_out_time`) else (timestampdiff(HOUR,`check_out_time`,`check_in_time`) + 24) end)) STORED,
  `attendance_status` enum('present','absent','half_day','leave','holiday','week_off') DEFAULT 'present',
  `late_minutes` int DEFAULT '0',
  `overtime_minutes` int DEFAULT '0',
  `notes` text,
  `recorded_by` int DEFAULT NULL,
  `recorded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_date` (`employee_id`,`attendance_date`),
  KEY `idx_employee_attendance_employee_date` (`employee_id`,`attendance_date`),
  KEY `idx_employee_attendance_date_status` (`attendance_date`,`attendance_status`),
  KEY `idx_employee_attendance_recorded_by` (`recorded_by`),
  CONSTRAINT `employee_attendance_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_attendance_ibfk_2` FOREIGN KEY (`recorded_by`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_attendance`
--

LOCK TABLES `employee_attendance` WRITE;
/*!40000 ALTER TABLE `employee_attendance` DISABLE KEYS */;
INSERT INTO `employee_attendance` (`id`, `employee_id`, `attendance_date`, `check_in_time`, `check_out_time`, `attendance_status`, `late_minutes`, `overtime_minutes`, `notes`, `recorded_by`, `recorded_at`) VALUES (1,4,'2024-03-01','09:00:00','18:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(2,4,'2024-03-02','09:05:00','18:10:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(3,4,'2024-03-04','09:15:00','18:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(4,4,'2024-03-05','09:00:00','18:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(5,4,'2024-03-06',NULL,NULL,'leave',0,0,NULL,3,'2025-12-04 15:42:11'),(6,5,'2024-03-01','22:00:00','06:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(7,5,'2024-03-02','22:05:00','06:10:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(8,5,'2024-03-03','22:00:00','06:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(9,5,'2024-03-04','22:00:00','06:00:00','present',0,0,NULL,3,'2025-12-04 15:42:11'),(10,5,'2024-03-05',NULL,NULL,'holiday',0,0,NULL,3,'2025-12-04 15:42:11'),(11,6,'2024-03-01','09:00:00','18:00:00','present',0,0,NULL,2,'2025-12-04 15:42:11'),(12,6,'2024-03-02','09:10:00','18:05:00','present',0,0,NULL,2,'2025-12-04 15:42:11'),(13,6,'2024-03-04','09:00:00','18:00:00','present',0,0,NULL,2,'2025-12-04 15:42:11'),(14,6,'2024-03-05','08:55:00','18:00:00','present',0,0,NULL,2,'2025-12-04 15:42:11'),(15,6,'2024-03-06',NULL,NULL,'',0,0,NULL,2,'2025-12-04 15:42:11');
/*!40000 ALTER TABLE `employee_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_availability`
--

DROP TABLE IF EXISTS `employee_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_availability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `availability_date` date NOT NULL,
  `time_slots` json DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `reason_if_unavailable` text,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_date` (`employee_id`,`availability_date`),
  KEY `idx_employee_availability_date` (`availability_date`),
  KEY `idx_employee_availability_status` (`is_available`),
  CONSTRAINT `employee_availability_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_availability`
--

LOCK TABLES `employee_availability` WRITE;
/*!40000 ALTER TABLE `employee_availability` DISABLE KEYS */;
INSERT INTO `employee_availability` VALUES (1,4,'2024-04-01',NULL,1,NULL,'2025-12-04 15:42:11'),(2,4,'2024-04-02',NULL,1,NULL,'2025-12-04 15:42:11'),(3,4,'2024-04-03',NULL,1,NULL,'2025-12-04 15:42:11'),(4,4,'2024-04-04',NULL,0,'Medical appointment','2025-12-04 15:42:11'),(5,4,'2024-04-05',NULL,1,NULL,'2025-12-04 15:42:11'),(6,4,'2024-04-06',NULL,0,'Weekend','2025-12-04 15:42:11'),(7,4,'2024-04-07',NULL,0,'Weekend','2025-12-04 15:42:11'),(8,5,'2024-04-01',NULL,1,NULL,'2025-12-04 15:42:11'),(9,5,'2024-04-02',NULL,1,NULL,'2025-12-04 15:42:11'),(10,5,'2024-04-03',NULL,1,NULL,'2025-12-04 15:42:11'),(11,5,'2024-04-04',NULL,1,NULL,'2025-12-04 15:42:11'),(12,5,'2024-04-05',NULL,0,'Vehicle maintenance','2025-12-04 15:42:11'),(13,5,'2024-04-06',NULL,1,NULL,'2025-12-04 15:42:11'),(14,5,'2024-04-07',NULL,1,NULL,'2025-12-04 15:42:11'),(17,8,'2024-04-01',NULL,1,NULL,'2025-12-06 15:34:51'),(18,8,'2024-04-02',NULL,1,NULL,'2025-12-06 15:34:51'),(19,8,'2024-04-03',NULL,0,'Training session','2025-12-06 15:34:51'),(20,8,'2024-04-04',NULL,1,NULL,'2025-12-06 15:34:51'),(21,8,'2024-04-05',NULL,1,NULL,'2025-12-06 15:34:51'),(22,8,'2024-04-06',NULL,0,'Weekend','2025-12-06 15:34:51'),(23,8,'2024-04-07',NULL,0,'Weekend','2025-12-06 15:34:51'),(24,9,'2024-04-01',NULL,1,NULL,'2025-12-06 15:34:51'),(25,9,'2024-04-02',NULL,1,NULL,'2025-12-06 15:34:51'),(26,9,'2024-04-03',NULL,1,NULL,'2025-12-06 15:34:51'),(27,9,'2024-04-04',NULL,0,'Medical checkup','2025-12-06 15:34:51'),(28,9,'2024-04-05',NULL,1,NULL,'2025-12-06 15:34:51'),(29,9,'2024-04-06',NULL,1,NULL,'2025-12-06 15:34:51'),(30,9,'2024-04-07',NULL,0,'Weekend','2025-12-06 15:34:51'),(31,10,'2024-04-01',NULL,1,NULL,'2025-12-06 15:34:51'),(32,10,'2024-04-02',NULL,0,'Family event','2025-12-06 15:34:51'),(33,10,'2024-04-03',NULL,1,NULL,'2025-12-06 15:34:51'),(34,10,'2024-04-04',NULL,1,NULL,'2025-12-06 15:34:51'),(35,10,'2024-04-05',NULL,1,NULL,'2025-12-06 15:34:51'),(36,10,'2024-04-06',NULL,0,'Weekend','2025-12-06 15:34:51'),(37,10,'2024-04-07',NULL,0,'Weekend','2025-12-06 15:34:51');
/*!40000 ALTER TABLE `employee_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_departments`
--

DROP TABLE IF EXISTS `employee_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(100) NOT NULL,
  `parent_department_id` int DEFAULT NULL,
  `department_head` int DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_name` (`department_name`),
  KEY `parent_department_id` (`parent_department_id`),
  KEY `status_id` (`status_id`),
  KEY `department_head` (`department_head`),
  CONSTRAINT `employee_departments_ibfk_1` FOREIGN KEY (`parent_department_id`) REFERENCES `employee_departments` (`id`),
  CONSTRAINT `employee_departments_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`),
  CONSTRAINT `employee_departments_ibfk_3` FOREIGN KEY (`department_head`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_departments`
--

LOCK TABLES `employee_departments` WRITE;
/*!40000 ALTER TABLE `employee_departments` DISABLE KEYS */;
INSERT INTO `employee_departments` VALUES (1,'Executive Management',NULL,1,'Top level management and executives','2025-12-04 15:42:11',1),(2,'Human Resources',NULL,2,'HR and personnel management','2025-12-04 15:42:11',1),(3,'Operations',NULL,3,'Daily operations and logistics','2025-12-04 15:42:11',1),(4,'Sales & Marketing',NULL,6,'Sales and marketing department','2025-12-04 15:42:11',1),(5,'Tour Operations',NULL,4,'Tour guides and operations','2025-12-04 15:42:11',1),(6,'Transportation',NULL,5,'Drivers and vehicle management','2025-12-04 15:42:11',1),(7,'Finance',NULL,NULL,'Accounting and finance','2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_designations`
--

DROP TABLE IF EXISTS `employee_designations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_designations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation_name` varchar(100) NOT NULL,
  `level` int DEFAULT '1',
  `min_salary` decimal(10,2) DEFAULT NULL,
  `max_salary` decimal(10,2) DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `designation_name` (`designation_name`),
  KEY `department_id` (`department_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_designations_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `employee_departments` (`id`),
  CONSTRAINT `employee_designations_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_designations`
--

LOCK TABLES `employee_designations` WRITE;
/*!40000 ALTER TABLE `employee_designations` DISABLE KEYS */;
INSERT INTO `employee_designations` VALUES (1,'CEO',10,300000.00,500000.00,1,'Chief Executive Officer','2025-12-04 15:42:11',1),(2,'HR Manager',8,150000.00,250000.00,2,'Human Resources Manager','2025-12-04 15:42:11',1),(3,'Operations Manager',8,140000.00,220000.00,3,'Operations Department Manager','2025-12-04 15:42:11',1),(4,'Senior Tour Guide',6,80000.00,150000.00,5,'Senior Tour Guide','2025-12-04 15:42:11',1),(5,'Tour Guide',5,60000.00,100000.00,5,'Tour Guide','2025-12-04 15:42:11',1),(6,'Senior Driver',5,70000.00,120000.00,6,'Senior Driver','2025-12-04 15:42:11',1),(7,'Driver',4,50000.00,90000.00,6,'Driver','2025-12-04 15:42:11',1),(8,'Sales Executive',5,60000.00,120000.00,4,'Sales Executive','2025-12-04 15:42:11',1),(9,'Marketing Executive',5,60000.00,120000.00,4,'Marketing Executive','2025-12-04 15:42:11',1),(10,'Accountant',6,90000.00,160000.00,7,'Finance Accountant','2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_designations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_documents`
--

DROP TABLE IF EXISTS `employee_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_documents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `document_type` varchar(100) DEFAULT NULL,
  `document_name` varchar(255) NOT NULL,
  `file_path` varchar(500) NOT NULL,
  `file_size` int DEFAULT NULL,
  `mime_type` varchar(100) DEFAULT NULL,
  `uploaded_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expiry_date` date DEFAULT NULL,
  `verified` tinyint(1) DEFAULT '0',
  `verified_by` int DEFAULT NULL,
  `verified_date` date DEFAULT NULL,
  `notes` text,
  `uploaded_by` int DEFAULT NULL,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `uploaded_by` (`uploaded_by`),
  KEY `verified_by` (`verified_by`),
  KEY `status_id` (`status_id`),
  KEY `idx_employee_documents_employee` (`employee_id`),
  CONSTRAINT `employee_documents_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_documents_ibfk_2` FOREIGN KEY (`uploaded_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_documents_ibfk_3` FOREIGN KEY (`verified_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_documents_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_documents`
--

LOCK TABLES `employee_documents` WRITE;
/*!40000 ALTER TABLE `employee_documents` DISABLE KEYS */;
INSERT INTO `employee_documents` VALUES (1,4,'License','Tour Guide License','/docs/guides/license_001.pdf',NULL,NULL,'2025-12-04 15:42:11','2025-12-31',0,NULL,NULL,NULL,2,1),(2,4,'Certificate','First Aid Certificate','/docs/guides/firstaid_001.pdf',NULL,NULL,'2025-12-04 15:42:11','2024-12-31',0,NULL,NULL,NULL,2,1),(3,5,'License','Driving License','/docs/drivers/license_001.pdf',NULL,NULL,'2025-12-04 15:42:11','2026-08-15',0,NULL,NULL,NULL,2,1),(4,5,'Insurance','Vehicle Insurance','/docs/drivers/insurance_001.pdf',NULL,NULL,'2025-12-04 15:42:11','2024-11-30',0,NULL,NULL,NULL,2,1),(5,2,'Certificate','HR Certification','/docs/hr/cert_001.pdf',NULL,NULL,'2025-12-04 15:42:11',NULL,0,NULL,NULL,NULL,1,1),(6,6,'Certificate','Sales Training Certificate','/docs/sales/cert_001.pdf',NULL,NULL,'2025-12-04 15:42:11',NULL,0,NULL,NULL,NULL,2,1),(7,8,'License','Tour Guide License','/docs/guides/license_008.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1),(8,8,'Certificate','Cultural Guide Certificate','/docs/guides/cultural_cert_008.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1),(9,9,'License','Tour Guide License','/docs/guides/license_009.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1),(10,9,'Certificate','Wildlife Guide Certificate','/docs/guides/wildlife_cert_009.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1),(11,10,'License','Tour Guide License','/docs/guides/license_010.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1),(12,10,'Certificate','Food Tourism Certificate','/docs/guides/food_cert_010.pdf',NULL,NULL,'2025-12-06 15:34:46',NULL,0,NULL,NULL,NULL,2,1);
/*!40000 ALTER TABLE `employee_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_driver_details`
--

DROP TABLE IF EXISTS `employee_driver_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_driver_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `license_type` varchar(50) DEFAULT NULL,
  `license_number` varchar(50) DEFAULT NULL,
  `license_issue_date` date DEFAULT NULL,
  `license_expiry_date` date DEFAULT NULL,
  `vehicle_types` text,
  `experience_years` int DEFAULT NULL,
  `accident_free_years` int DEFAULT NULL,
  `route_expertise` text,
  `is_available` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `employee_driver_details_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_driver_details`
--

LOCK TABLES `employee_driver_details` WRITE;
/*!40000 ALTER TABLE `employee_driver_details` DISABLE KEYS */;
INSERT INTO `employee_driver_details` VALUES (1,5,'LMV/HMV','DL12345678','2018-05-15','2028-05-15','Van, Bus, SUV, Car',10,8,'Colombo-Kandy, Colombo-Galle, Airport Transfers',1,'2025-12-04 15:42:11');
/*!40000 ALTER TABLE `employee_driver_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_emergency_contacts`
--

DROP TABLE IF EXISTS `employee_emergency_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_emergency_contacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `primary_phone` varchar(20) NOT NULL,
  `secondary_phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` text,
  `is_primary` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_emergency_contacts_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_emergency_contacts_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_emergency_contacts`
--

LOCK TABLES `employee_emergency_contacts` WRITE;
/*!40000 ALTER TABLE `employee_emergency_contacts` DISABLE KEYS */;
INSERT INTO `employee_emergency_contacts` VALUES (1,4,'Kamal Perera','Brother','+94222222222',NULL,NULL,NULL,1,'2025-12-04 15:42:11',1),(2,4,'Samantha Perera','Sister','+94222222223',NULL,NULL,NULL,0,'2025-12-04 15:42:11',1),(3,5,'Nimal Fernando','Uncle','+94333333333',NULL,NULL,NULL,1,'2025-12-04 15:42:11',1),(4,5,'Sunil Fernando','Father','+94333333334',NULL,NULL,NULL,0,'2025-12-04 15:42:11',1),(5,2,'Michael Brown','Father','+94111111112',NULL,NULL,NULL,1,'2025-12-04 15:42:11',1),(6,6,'Priya Silva','Mother','+94444444444',NULL,NULL,NULL,1,'2025-12-04 15:42:11',1),(7,8,'Sanjay Sharma','Husband','+94771111111',NULL,NULL,NULL,1,'2025-12-06 15:34:41',1),(8,9,'Mala Fernando','Wife','+94772222222',NULL,NULL,NULL,1,'2025-12-06 15:34:41',1),(9,10,'Dinesh Perera','Brother','+94773333333',NULL,NULL,NULL,1,'2025-12-06 15:34:41',1);
/*!40000 ALTER TABLE `employee_emergency_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_exit`
--

DROP TABLE IF EXISTS `employee_exit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_exit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `resignation_date` date DEFAULT NULL,
  `last_working_date` date NOT NULL,
  `exit_type` enum('resignation','termination','retirement','contract_end') DEFAULT NULL,
  `reason` text,
  `exit_interview_notes` text,
  `exit_interview_by` int DEFAULT NULL,
  `exit_interview_date` date DEFAULT NULL,
  `notice_period_served` tinyint(1) DEFAULT '1',
  `final_settlement_date` date DEFAULT NULL,
  `final_settlement_amount` decimal(10,2) DEFAULT NULL,
  `clearance_status` enum('pending','completed') DEFAULT 'pending',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id` (`employee_id`),
  KEY `exit_interview_by` (`exit_interview_by`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_exit_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_exit_ibfk_2` FOREIGN KEY (`exit_interview_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_exit_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_exit`
--

LOCK TABLES `employee_exit` WRITE;
/*!40000 ALTER TABLE `employee_exit` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_exit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_exit_clearance_items`
--

DROP TABLE IF EXISTS `employee_exit_clearance_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_exit_clearance_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_exit_id` int NOT NULL,
  `clearance_item` varchar(255) NOT NULL,
  `responsible_dept` varchar(100) DEFAULT NULL,
  `responsible_person` int DEFAULT NULL,
  `completed` tinyint(1) DEFAULT '0',
  `completion_date` date DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`id`),
  KEY `employee_exit_id` (`employee_exit_id`),
  KEY `responsible_person` (`responsible_person`),
  CONSTRAINT `employee_exit_clearance_items_ibfk_1` FOREIGN KEY (`employee_exit_id`) REFERENCES `employee_exit` (`id`) ON DELETE CASCADE,
  CONSTRAINT `employee_exit_clearance_items_ibfk_2` FOREIGN KEY (`responsible_person`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_exit_clearance_items`
--

LOCK TABLES `employee_exit_clearance_items` WRITE;
/*!40000 ALTER TABLE `employee_exit_clearance_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_exit_clearance_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_guide_specializations`
--

DROP TABLE IF EXISTS `employee_guide_specializations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_guide_specializations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `specialization_type` varchar(100) DEFAULT NULL,
  `regions` text,
  `languages` text,
  `certifications` text,
  `experience_years` int DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `employee_guide_specializations_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_guide_specializations`
--

LOCK TABLES `employee_guide_specializations` WRITE;
/*!40000 ALTER TABLE `employee_guide_specializations` DISABLE KEYS */;
INSERT INTO `employee_guide_specializations` VALUES (1,4,'Historical & Cultural','Kandy, Anuradhapura, Polonnaruwa','English, Sinhala, Basic French','Certified Cultural Guide, UNESCO Heritage Guide',8,4.80,1,'2025-12-04 15:42:11'),(2,4,'Wildlife & Nature','Yala, Udawalawe, Horton Plains','English, Sinhala','Wildlife Guide Certification, Nature Photography',6,4.50,1,'2025-12-04 15:42:11'),(5,8,'Cultural & Heritage','Colombo, Galle, Southern Coast','English, Hindi, Basic Sinhala','Cultural Heritage Guide, Photography Guide',5,4.60,1,'2025-12-06 15:34:44'),(6,9,'Wildlife & Adventure','Yala, Udawalawe, Sinharaja','English, Sinhala, Basic Tamil','Wildlife Guide, First Aid Certified',7,4.70,1,'2025-12-06 15:34:44'),(7,10,'Food & Culinary','Kandy, Ella, Hill Country','English, French, Sinhala','Food Tourism Guide, Wine Appreciation',4,4.50,1,'2025-12-06 15:34:44');
/*!40000 ALTER TABLE `employee_guide_specializations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_holidays`
--

DROP TABLE IF EXISTS `employee_holidays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_holidays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `holiday_name` varchar(100) NOT NULL,
  `holiday_date` date NOT NULL,
  `holiday_type` enum('national','regional','company','optional') DEFAULT 'national',
  `description` text,
  `year` year DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_holiday_date` (`holiday_date`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_holidays_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_holidays`
--

LOCK TABLES `employee_holidays` WRITE;
/*!40000 ALTER TABLE `employee_holidays` DISABLE KEYS */;
INSERT INTO `employee_holidays` VALUES (1,'New Year','2024-01-01','national',NULL,2024,'2025-12-04 15:42:11',1),(2,'Republic Day','2024-01-26','national',NULL,2024,'2025-12-04 15:42:11',1),(3,'Holi','2024-03-25','national',NULL,2024,'2025-12-04 15:42:11',1),(4,'Independence Day','2024-08-15','national',NULL,2024,'2025-12-04 15:42:11',1),(5,'Gandhi Jayanti','2024-10-02','national',NULL,2024,'2025-12-04 15:42:11',1),(6,'Diwali','2024-11-01','national',NULL,2024,'2025-12-04 15:42:11',1),(7,'Christmas','2024-12-25','national',NULL,2024,'2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_holidays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_incentives`
--

DROP TABLE IF EXISTS `employee_incentives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_incentives` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `incentive_date` date NOT NULL,
  `incentive_type` varchar(100) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `calculation_basis` text,
  `reference_id` varchar(100) DEFAULT NULL,
  `payment_status` enum('pending','paid') DEFAULT 'pending',
  `paid_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `employee_incentives_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_incentives`
--

LOCK TABLES `employee_incentives` WRITE;
/*!40000 ALTER TABLE `employee_incentives` DISABLE KEYS */;
INSERT INTO `employee_incentives` VALUES (1,6,'2024-03-05','commission',25000.00,'5% commission on March sales exceeding target','SALE-2024-003','paid',NULL,'2025-12-04 15:42:11'),(2,4,'2024-03-10','performance',15000.00,'Excellence in customer service ratings','PERF-2024-001','paid',NULL,'2025-12-04 15:42:11'),(3,5,'2024-03-15','safety',10000.00,'Accident-free quarter bonus','SAFE-2024-001','pending',NULL,'2025-12-04 15:42:11'),(4,6,'2024-03-20','referral',5000.00,'Customer referral bonus','REF-2024-001','paid',NULL,'2025-12-04 15:42:11');
/*!40000 ALTER TABLE `employee_incentives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_leave_applications`
--

DROP TABLE IF EXISTS `employee_leave_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_leave_applications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `leave_type_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total_days` int NOT NULL,
  `is_half_day` tinyint(1) DEFAULT '0',
  `half_day_type` enum('first_half','second_half') DEFAULT NULL,
  `reason` text,
  `contact_during_leave` varchar(20) DEFAULT NULL,
  `attachment_path` varchar(255) DEFAULT NULL,
  `applied_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `approved_by` int DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT NULL,
  `rejection_reason` text,
  `status` enum('pending','approved','rejected','cancelled') DEFAULT 'pending',
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `leave_type_id` (`leave_type_id`),
  KEY `status_id` (`status_id`),
  KEY `idx_employee_leave_applications_employee` (`employee_id`),
  KEY `idx_employee_leave_applications_status` (`status`),
  KEY `idx_employee_leave_applications_date` (`start_date`,`end_date`),
  KEY `idx_employee_leave_applications_approved_by` (`approved_by`),
  CONSTRAINT `employee_leave_applications_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_leave_applications_ibfk_2` FOREIGN KEY (`leave_type_id`) REFERENCES `employee_leave_types` (`id`),
  CONSTRAINT `employee_leave_applications_ibfk_3` FOREIGN KEY (`approved_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_leave_applications_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_leave_applications`
--

LOCK TABLES `employee_leave_applications` WRITE;
/*!40000 ALTER TABLE `employee_leave_applications` DISABLE KEYS */;
INSERT INTO `employee_leave_applications` VALUES (1,4,1,'2024-03-10','2024-03-15',5,0,NULL,'Family vacation to Maldives',NULL,NULL,'2024-02-20 09:00:00',2,'2024-02-21 14:30:00',NULL,'approved',1),(2,6,2,'2024-04-05','2024-04-05',1,0,NULL,'Doctor appointment',NULL,NULL,'2024-04-01 10:15:00',2,'2024-04-01 16:45:00',NULL,'approved',1),(3,5,3,'2024-05-20','2024-05-20',1,0,NULL,'Personal work',NULL,NULL,'2024-05-15 11:30:00',3,'2024-05-16 10:00:00',NULL,'approved',1),(4,2,1,'2024-06-01','2024-06-07',5,0,NULL,'Annual vacation',NULL,NULL,'2024-05-25 09:00:00',1,'2024-05-26 11:00:00',NULL,'approved',1),(5,7,1,'2024-07-10','2024-07-12',3,0,NULL,'Wedding ceremony',NULL,NULL,'2024-06-28 14:00:00',2,NULL,NULL,'pending',1);
/*!40000 ALTER TABLE `employee_leave_applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_leave_balances`
--

DROP TABLE IF EXISTS `employee_leave_balances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_leave_balances` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `leave_type_id` int NOT NULL,
  `year` year NOT NULL,
  `allocated_days` int DEFAULT '0',
  `used_days` int DEFAULT '0',
  `remaining_days` int GENERATED ALWAYS AS ((`allocated_days` - `used_days`)) STORED,
  `carried_forward_days` int DEFAULT '0',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_leave_year` (`employee_id`,`leave_type_id`,`year`),
  KEY `leave_type_id` (`leave_type_id`),
  KEY `idx_employee_leave_balances_employee` (`employee_id`),
  KEY `idx_employee_leave_balances_year` (`year`),
  CONSTRAINT `employee_leave_balances_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_leave_balances_ibfk_2` FOREIGN KEY (`leave_type_id`) REFERENCES `employee_leave_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_leave_balances`
--

LOCK TABLES `employee_leave_balances` WRITE;
/*!40000 ALTER TABLE `employee_leave_balances` DISABLE KEYS */;
INSERT INTO `employee_leave_balances` (`id`, `employee_id`, `leave_type_id`, `year`, `allocated_days`, `used_days`, `carried_forward_days`, `updated_at`) VALUES (1,1,1,2024,21,5,0,'2025-12-04 15:42:11'),(2,2,1,2024,21,8,0,'2025-12-04 15:42:11'),(3,3,1,2024,21,3,0,'2025-12-04 15:42:11'),(4,4,1,2024,21,10,0,'2025-12-04 15:42:11'),(5,5,1,2024,21,2,0,'2025-12-04 15:42:11'),(6,6,1,2024,21,6,0,'2025-12-04 15:42:11'),(7,7,1,2024,21,4,0,'2025-12-04 15:42:11'),(8,1,2,2024,14,1,0,'2025-12-04 15:42:11'),(9,2,2,2024,14,2,0,'2025-12-04 15:42:11'),(10,3,2,2024,14,0,0,'2025-12-04 15:42:11'),(11,4,2,2024,14,3,0,'2025-12-04 15:42:11'),(12,5,2,2024,14,1,0,'2025-12-04 15:42:11'),(13,6,2,2024,14,2,0,'2025-12-04 15:42:11'),(14,7,2,2024,14,0,0,'2025-12-04 15:42:11'),(15,1,3,2024,7,1,0,'2025-12-04 15:42:11'),(16,2,3,2024,7,2,0,'2025-12-04 15:42:11'),(17,3,3,2024,7,1,0,'2025-12-04 15:42:11'),(18,4,3,2024,7,2,0,'2025-12-04 15:42:11'),(19,5,3,2024,7,0,0,'2025-12-04 15:42:11'),(20,6,3,2024,7,1,0,'2025-12-04 15:42:11'),(21,7,3,2024,7,1,0,'2025-12-04 15:42:11'),(22,8,1,2024,21,3,0,'2025-12-06 15:33:15'),(23,8,2,2024,14,1,0,'2025-12-06 15:33:15'),(24,8,3,2024,7,0,0,'2025-12-06 15:33:15'),(25,9,1,2024,21,5,0,'2025-12-06 15:33:15'),(26,9,2,2024,14,2,0,'2025-12-06 15:33:15'),(27,9,3,2024,7,1,0,'2025-12-06 15:33:15'),(28,10,1,2024,21,2,0,'2025-12-06 15:33:15'),(29,10,2,2024,14,0,0,'2025-12-06 15:33:15'),(30,10,3,2024,7,1,0,'2025-12-06 15:33:15');
/*!40000 ALTER TABLE `employee_leave_balances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_leave_types`
--

DROP TABLE IF EXISTS `employee_leave_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_leave_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `description` text,
  `max_days_per_year` int DEFAULT '0',
  `is_paid` tinyint(1) DEFAULT '1',
  `carry_forward_allowed` tinyint(1) DEFAULT '0',
  `max_carry_forward_days` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_leave_types_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_leave_types`
--

LOCK TABLES `employee_leave_types` WRITE;
/*!40000 ALTER TABLE `employee_leave_types` DISABLE KEYS */;
INSERT INTO `employee_leave_types` VALUES (1,'Annual Leave','Paid annual vacation leave',21,1,1,0,'2025-12-04 15:42:11',1),(2,'Sick Leave','Paid sick leave',14,1,0,0,'2025-12-04 15:42:11',1),(3,'Casual Leave','Casual or personal leave',7,1,0,0,'2025-12-04 15:42:11',1),(4,'Maternity Leave','Maternity leave',180,1,0,0,'2025-12-04 15:42:11',1),(5,'Paternity Leave','Paternity leave',7,1,0,0,'2025-12-04 15:42:11',1),(6,'Bereavement Leave','Leave for family bereavement',5,1,0,0,'2025-12-04 15:42:11',1),(7,'Compensatory Leave','Compensatory off for overtime',0,1,1,0,'2025-12-04 15:42:11',1),(8,'Unpaid Leave','Leave without pay',0,0,0,0,'2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_leave_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_loans`
--

DROP TABLE IF EXISTS `employee_loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_loans` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `loan_type` enum('personal','advance','emergency') DEFAULT NULL,
  `loan_amount` decimal(10,2) NOT NULL,
  `approved_amount` decimal(10,2) DEFAULT NULL,
  `interest_rate` decimal(5,2) DEFAULT NULL,
  `tenure_months` int DEFAULT NULL,
  `emi_amount` decimal(10,2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `remaining_balance` decimal(10,2) DEFAULT NULL,
  `status` enum('pending','approved','disbursed','rejected','closed') DEFAULT 'pending',
  `approved_by` int DEFAULT NULL,
  `approved_date` date DEFAULT NULL,
  `remarks` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `approved_by` (`approved_by`),
  CONSTRAINT `employee_loans_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_loans_ibfk_2` FOREIGN KEY (`approved_by`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_loans`
--

LOCK TABLES `employee_loans` WRITE;
/*!40000 ALTER TABLE `employee_loans` DISABLE KEYS */;
INSERT INTO `employee_loans` VALUES (1,5,'personal',200000.00,200000.00,8.50,24,9083.33,'2024-01-15',NULL,NULL,'approved',2,'2024-01-10',NULL,'2025-12-04 15:42:11'),(2,4,'advance',50000.00,50000.00,0.00,5,10000.00,'2024-02-01',NULL,NULL,'approved',2,'2024-01-28',NULL,'2025-12-04 15:42:11'),(3,6,'emergency',75000.00,75000.00,6.00,12,6453.29,'2024-03-10',NULL,NULL,'approved',2,'2024-03-05',NULL,'2025-12-04 15:42:11');
/*!40000 ALTER TABLE `employee_loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_overtime_requests`
--

DROP TABLE IF EXISTS `employee_overtime_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_overtime_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `overtime_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `total_hours` decimal(5,2) NOT NULL,
  `reason` text NOT NULL,
  `approved_by` int DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT NULL,
  `overtime_rate` decimal(5,2) DEFAULT '1.50',
  `payment_status` enum('pending','paid','not_paid') DEFAULT 'pending',
  `status` enum('pending','approved','rejected') DEFAULT 'pending',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `approved_by` (`approved_by`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_overtime_requests_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_overtime_requests_ibfk_2` FOREIGN KEY (`approved_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_overtime_requests_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_overtime_requests`
--

LOCK TABLES `employee_overtime_requests` WRITE;
/*!40000 ALTER TABLE `employee_overtime_requests` DISABLE KEYS */;
INSERT INTO `employee_overtime_requests` VALUES (1,4,'2024-03-15','18:00:00','21:30:00',3.50,'Extended evening tour due to traffic delays',3,'2024-03-16 10:00:00',1.50,'pending','approved','2025-12-04 15:42:11',1),(2,5,'2024-03-20','06:00:00','10:00:00',4.00,'Early morning airport pickup',3,'2024-03-19 16:30:00',1.50,'pending','approved','2025-12-04 15:42:11',1),(3,6,'2024-03-25','18:00:00','20:00:00',2.00,'Client meeting preparation',2,'2024-03-24 14:00:00',1.50,'pending','approved','2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_overtime_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_performance_metrics`
--

DROP TABLE IF EXISTS `employee_performance_metrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_performance_metrics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `metric_date` date NOT NULL,
  `metric_type` varchar(50) DEFAULT NULL,
  `metric_value` decimal(10,2) DEFAULT NULL,
  `target_value` decimal(10,2) DEFAULT NULL,
  `achievement_percentage` decimal(5,2) DEFAULT NULL,
  `notes` text,
  `recorded_by` int DEFAULT NULL,
  `recorded_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `recorded_by` (`recorded_by`),
  CONSTRAINT `employee_performance_metrics_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_performance_metrics_ibfk_2` FOREIGN KEY (`recorded_by`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_performance_metrics`
--

LOCK TABLES `employee_performance_metrics` WRITE;
/*!40000 ALTER TABLE `employee_performance_metrics` DISABLE KEYS */;
INSERT INTO `employee_performance_metrics` VALUES (1,4,'2024-03-01','tours_completed',15.00,12.00,125.00,NULL,3,'2025-12-04 15:42:11'),(2,4,'2024-03-01','customer_rating',4.80,4.50,106.70,NULL,3,'2025-12-04 15:42:11'),(3,4,'2024-03-01','revenue_generated',450000.00,400000.00,112.50,NULL,3,'2025-12-04 15:42:11'),(4,5,'2024-03-01','trips_completed',45.00,40.00,112.50,NULL,3,'2025-12-04 15:42:11'),(5,5,'2024-03-01','safety_rating',4.90,4.80,102.10,NULL,3,'2025-12-04 15:42:11'),(6,5,'2024-03-01','mileage_covered',2500.00,2000.00,125.00,NULL,3,'2025-12-04 15:42:11'),(7,6,'2024-03-01','bookings_handled',85.00,75.00,113.30,NULL,2,'2025-12-04 15:42:11'),(8,6,'2024-03-01','revenue_generated',1200000.00,1000000.00,120.00,NULL,2,'2025-12-04 15:42:11'),(9,6,'2024-03-01','customer_satisfaction',4.70,4.50,104.40,NULL,2,'2025-12-04 15:42:11'),(10,8,'2024-03-01','tours_completed',12.00,10.00,120.00,NULL,3,'2025-12-06 15:34:54'),(11,8,'2024-03-01','customer_rating',4.70,4.50,104.40,NULL,3,'2025-12-06 15:34:54'),(12,9,'2024-03-01','tours_completed',14.00,12.00,116.70,NULL,3,'2025-12-06 15:34:54'),(13,9,'2024-03-01','customer_rating',4.80,4.50,106.70,NULL,3,'2025-12-06 15:34:54'),(14,10,'2024-03-01','tours_completed',11.00,10.00,110.00,NULL,3,'2025-12-06 15:34:54'),(15,10,'2024-03-01','customer_rating',4.60,4.50,102.20,NULL,3,'2025-12-06 15:34:54');
/*!40000 ALTER TABLE `employee_performance_metrics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_performance_reviews`
--

DROP TABLE IF EXISTS `employee_performance_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_performance_reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `review_period_start` date NOT NULL,
  `review_period_end` date NOT NULL,
  `reviewed_by` int NOT NULL,
  `review_date` date NOT NULL,
  `overall_rating` decimal(3,1) DEFAULT NULL,
  `attendance_rating` int DEFAULT NULL,
  `productivity_rating` int DEFAULT NULL,
  `quality_rating` int DEFAULT NULL,
  `teamwork_rating` int DEFAULT NULL,
  `strengths` text,
  `areas_for_improvement` text,
  `goals` text,
  `comments` text,
  `status` enum('draft','submitted','approved') DEFAULT 'draft',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  KEY `idx_employee_performance_reviews_employee` (`employee_id`),
  KEY `idx_employee_performance_reviews_reviewed_by` (`reviewed_by`),
  CONSTRAINT `employee_performance_reviews_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_performance_reviews_ibfk_2` FOREIGN KEY (`reviewed_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_performance_reviews_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_performance_reviews`
--

LOCK TABLES `employee_performance_reviews` WRITE;
/*!40000 ALTER TABLE `employee_performance_reviews` DISABLE KEYS */;
INSERT INTO `employee_performance_reviews` VALUES (1,4,'2023-07-01','2023-12-31',3,'2024-01-15',4.5,5,4,5,4,'Excellent knowledge of historical sites, great with customers','Could improve time management during tours','Complete advanced language course, Lead 50+ tours in 2024','Good performance overall','approved','2025-12-04 15:42:11',1),(2,5,'2023-07-01','2023-12-31',3,'2024-01-15',4.0,5,4,4,4,'Safe driving record, punctual, good vehicle maintenance','Could improve communication with passengers','Maintain accident-free record, Complete defensive driving course','Reliable and safe driver','approved','2025-12-04 15:42:11',1),(3,6,'2023-07-01','2023-12-31',2,'2024-01-15',4.2,4,5,4,4,'Excellent sales skills, good customer relationships','Could improve documentation of sales calls','Achieve 20% sales growth, Complete advanced sales training','Top performer in sales team','approved','2025-12-04 15:42:11',1),(4,8,'2023-10-01','2023-12-31',3,'2024-01-20',4.2,5,4,4,4,'Excellent cultural knowledge, good with international tourists','Could improve local language skills','Lead 30+ cultural tours in 2024',NULL,'approved','2025-12-06 15:34:48',1),(5,9,'2023-10-01','2023-12-31',3,'2024-01-20',4.4,5,5,4,4,'Great wildlife knowledge, excellent safety record','Could improve documentation','Complete advanced wildlife course',NULL,'approved','2025-12-06 15:34:48',1),(6,10,'2023-10-01','2023-12-31',3,'2024-01-20',4.1,4,4,5,4,'Excellent food knowledge, creative tour planning','Could improve time management','Develop 5 new food tour packages',NULL,'approved','2025-12-06 15:34:48',1);
/*!40000 ALTER TABLE `employee_performance_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary_components`
--

DROP TABLE IF EXISTS `employee_salary_components`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary_components` (
  `id` int NOT NULL AUTO_INCREMENT,
  `component_name` varchar(100) NOT NULL,
  `component_type` enum('earning','deduction') NOT NULL,
  `is_fixed` tinyint(1) DEFAULT '1',
  `is_taxable` tinyint(1) DEFAULT '1',
  `calculation_type` enum('fixed','percentage','formula') DEFAULT NULL,
  `calculation_value` decimal(10,2) DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_salary_components_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary_components`
--

LOCK TABLES `employee_salary_components` WRITE;
/*!40000 ALTER TABLE `employee_salary_components` DISABLE KEYS */;
INSERT INTO `employee_salary_components` VALUES (1,'Basic Salary','earning',1,1,NULL,NULL,'Basic salary component','2025-12-04 15:42:11',1),(2,'House Rent Allowance','earning',1,1,NULL,NULL,'HRA component','2025-12-04 15:42:11',1),(3,'Dearness Allowance','earning',1,1,NULL,NULL,'DA component','2025-12-04 15:42:11',1),(4,'Travel Allowance','earning',1,1,NULL,NULL,'Travel allowance for work','2025-12-04 15:42:11',1),(5,'Conveyance Allowance','earning',1,1,NULL,NULL,'Conveyance allowance','2025-12-04 15:42:11',1),(6,'Medical Allowance','earning',1,1,NULL,NULL,'Medical allowance','2025-12-04 15:42:11',1),(7,'Special Allowance','earning',1,1,NULL,NULL,'Special allowances','2025-12-04 15:42:11',1),(8,'Bonus','earning',0,1,NULL,NULL,'Performance bonus','2025-12-04 15:42:11',1),(9,'Incentive','earning',0,1,NULL,NULL,'Sales/Performance incentive','2025-12-04 15:42:11',1),(10,'Overtime Payment','earning',0,1,NULL,NULL,'Overtime payment','2025-12-04 15:42:11',1),(11,'Provident Fund','deduction',1,0,NULL,NULL,'PF deduction','2025-12-04 15:42:11',1),(12,'Professional Tax','deduction',1,1,NULL,NULL,'Professional tax','2025-12-04 15:42:11',1),(13,'Income Tax','deduction',1,1,NULL,NULL,'Income tax deduction','2025-12-04 15:42:11',1),(14,'Insurance','deduction',1,0,NULL,NULL,'Health insurance premium','2025-12-04 15:42:11',1),(15,'Loan Recovery','deduction',1,0,NULL,NULL,'Loan installment recovery','2025-12-04 15:42:11',1),(16,'Advance Recovery','deduction',1,0,NULL,NULL,'Salary advance recovery','2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_salary_components` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary_payment_details`
--

DROP TABLE IF EXISTS `employee_salary_payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary_payment_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `salary_payment_id` int NOT NULL,
  `component_id` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `component_type` enum('earning','deduction') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `salary_payment_id` (`salary_payment_id`),
  KEY `component_id` (`component_id`),
  CONSTRAINT `employee_salary_payment_details_ibfk_1` FOREIGN KEY (`salary_payment_id`) REFERENCES `employee_salary_payments` (`id`) ON DELETE CASCADE,
  CONSTRAINT `employee_salary_payment_details_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `employee_salary_components` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary_payment_details`
--

LOCK TABLES `employee_salary_payment_details` WRITE;
/*!40000 ALTER TABLE `employee_salary_payment_details` DISABLE KEYS */;
INSERT INTO `employee_salary_payment_details` VALUES (1,1,1,120000.00,'earning'),(2,1,2,30000.00,'earning'),(3,1,10,10500.00,'earning'),(4,1,9,15000.00,'earning'),(5,1,11,10000.00,'deduction'),(6,1,13,8500.00,'deduction'),(7,2,1,75000.00,'earning'),(8,2,2,15000.00,'earning'),(9,2,10,6000.00,'earning'),(10,2,9,10000.00,'earning'),(11,2,11,7500.00,'deduction'),(12,2,15,9083.33,'deduction'),(13,2,13,3416.67,'deduction'),(14,3,1,90000.00,'earning'),(15,3,2,18000.00,'earning'),(16,3,10,3000.00,'earning'),(17,3,9,25000.00,'earning'),(18,3,11,9000.00,'deduction'),(19,3,15,6453.29,'deduction'),(20,3,13,13046.71,'deduction');
/*!40000 ALTER TABLE `employee_salary_payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary_payments`
--

DROP TABLE IF EXISTS `employee_salary_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `payment_month` date NOT NULL,
  `basic_salary` decimal(10,2) NOT NULL,
  `total_earnings` decimal(10,2) NOT NULL,
  `total_deductions` decimal(10,2) NOT NULL,
  `gross_salary` decimal(10,2) DEFAULT NULL,
  `net_salary` decimal(10,2) GENERATED ALWAYS AS ((`total_earnings` - `total_deductions`)) STORED,
  `working_days` int DEFAULT NULL,
  `paid_days` int DEFAULT NULL,
  `leave_days` int DEFAULT NULL,
  `overtime_hours` decimal(5,2) DEFAULT NULL,
  `overtime_amount` decimal(10,2) DEFAULT NULL,
  `incentive_amount` decimal(10,2) DEFAULT NULL,
  `advance_deduction` decimal(10,2) DEFAULT NULL,
  `loan_deduction` decimal(10,2) DEFAULT NULL,
  `tax_deduction` decimal(10,2) DEFAULT NULL,
  `other_deductions` decimal(10,2) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` enum('bank_transfer','cash','cheque') DEFAULT 'bank_transfer',
  `transaction_reference` varchar(100) DEFAULT NULL,
  `notes` text,
  `processed_by` int DEFAULT NULL,
  `processed_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('pending','paid','cancelled') DEFAULT 'pending',
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_month` (`employee_id`,`payment_month`),
  KEY `status_id` (`status_id`),
  KEY `idx_employee_salary_payments_employee` (`employee_id`),
  KEY `idx_employee_salary_payments_month` (`payment_month`),
  KEY `idx_employee_salary_payments_status` (`status`),
  KEY `idx_employee_salary_payments_processed_by` (`processed_by`),
  CONSTRAINT `employee_salary_payments_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_salary_payments_ibfk_2` FOREIGN KEY (`processed_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_salary_payments_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary_payments`
--

LOCK TABLES `employee_salary_payments` WRITE;
/*!40000 ALTER TABLE `employee_salary_payments` DISABLE KEYS */;
INSERT INTO `employee_salary_payments` (`id`, `employee_id`, `payment_month`, `basic_salary`, `total_earnings`, `total_deductions`, `gross_salary`, `working_days`, `paid_days`, `leave_days`, `overtime_hours`, `overtime_amount`, `incentive_amount`, `advance_deduction`, `loan_deduction`, `tax_deduction`, `other_deductions`, `payment_date`, `payment_method`, `transaction_reference`, `notes`, `processed_by`, `processed_date`, `status`, `status_id`) VALUES (1,4,'2024-03-01',120000.00,138500.00,18500.00,NULL,22,20,2,3.50,10500.00,15000.00,10000.00,0.00,8500.00,NULL,'2024-04-01','bank_transfer',NULL,NULL,2,'2025-12-04 15:42:11','paid',1),(2,5,'2024-03-01',75000.00,91000.00,12500.00,NULL,22,22,0,4.00,6000.00,10000.00,0.00,9083.33,3416.67,NULL,'2024-04-01','bank_transfer',NULL,NULL,2,'2025-12-04 15:42:11','paid',1),(3,6,'2024-03-01',90000.00,120000.00,19500.00,NULL,22,21,1,2.00,3000.00,25000.00,0.00,6453.29,13046.71,NULL,'2024-04-01','bank_transfer',NULL,NULL,2,'2025-12-04 15:42:11','paid',1);
/*!40000 ALTER TABLE `employee_salary_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary_structures`
--

DROP TABLE IF EXISTS `employee_salary_structures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary_structures` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `component_id` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `effective_from` date NOT NULL,
  `effective_to` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_component` (`employee_id`,`component_id`,`effective_from`),
  KEY `component_id` (`component_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_salary_structures_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_salary_structures_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `employee_salary_components` (`id`),
  CONSTRAINT `employee_salary_structures_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary_structures`
--

LOCK TABLES `employee_salary_structures` WRITE;
/*!40000 ALTER TABLE `employee_salary_structures` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_salary_structures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_shifts_assignment`
--

DROP TABLE IF EXISTS `employee_shifts_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_shifts_assignment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `shift_id` int NOT NULL,
  `effective_from` date NOT NULL,
  `effective_to` date DEFAULT NULL,
  `assigned_by` int DEFAULT NULL,
  `assigned_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `shift_id` (`shift_id`),
  KEY `assigned_by` (`assigned_by`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_shifts_assignment_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_shifts_assignment_ibfk_2` FOREIGN KEY (`shift_id`) REFERENCES `employee_work_shifts` (`id`),
  CONSTRAINT `employee_shifts_assignment_ibfk_3` FOREIGN KEY (`assigned_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_shifts_assignment_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_shifts_assignment`
--

LOCK TABLES `employee_shifts_assignment` WRITE;
/*!40000 ALTER TABLE `employee_shifts_assignment` DISABLE KEYS */;
INSERT INTO `employee_shifts_assignment` VALUES (1,1,4,'2024-01-01','2024-12-31',2,'2025-12-04 15:42:11',1),(2,2,1,'2024-01-01','2024-12-31',1,'2025-12-04 15:42:11',1),(3,3,1,'2024-01-01','2024-12-31',2,'2025-12-04 15:42:11',1),(4,4,1,'2024-01-01','2024-06-30',3,'2025-12-04 15:42:11',1),(5,4,2,'2024-07-01','2024-12-31',3,'2025-12-04 15:42:11',1),(6,5,3,'2024-01-01','2024-12-31',3,'2025-12-04 15:42:11',1),(7,6,1,'2024-01-01','2024-12-31',2,'2025-12-04 15:42:11',1),(8,7,1,'2024-01-01','2024-12-31',2,'2025-12-04 15:42:11',1),(9,8,1,'2024-01-01','2024-12-31',3,'2025-12-06 15:33:15',1),(10,9,1,'2024-01-01','2024-12-31',3,'2025-12-06 15:33:15',1),(11,10,1,'2024-01-01','2024-12-31',3,'2025-12-06 15:33:15',1);
/*!40000 ALTER TABLE `employee_shifts_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_skills`
--

DROP TABLE IF EXISTS `employee_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_skills` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `skill_name` varchar(100) NOT NULL,
  `skill_category` varchar(50) DEFAULT NULL,
  `proficiency_level` enum('basic','intermediate','advanced','expert') DEFAULT 'basic',
  `certification` varchar(255) DEFAULT NULL,
  `certified_date` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `verified` tinyint(1) DEFAULT '0',
  `verified_by` int DEFAULT NULL,
  `verified_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `verified_by` (`verified_by`),
  KEY `idx_employee_skills_employee` (`employee_id`),
  CONSTRAINT `employee_skills_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_skills_ibfk_2` FOREIGN KEY (`verified_by`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_skills`
--

LOCK TABLES `employee_skills` WRITE;
/*!40000 ALTER TABLE `employee_skills` DISABLE KEYS */;
INSERT INTO `employee_skills` VALUES (1,4,'English','language','expert','IELTS 8.0',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(2,4,'French','language','intermediate','DELF B1',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(3,4,'First Aid','safety','advanced','Red Cross First Aid Certified',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(4,4,'History','knowledge','expert','Tour Guide Certification',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(5,4,'Photography','technical','intermediate',NULL,NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(6,5,'Defensive Driving','driving','advanced','Defensive Driving Certificate',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(7,5,'Vehicle Maintenance','technical','intermediate','Basic Vehicle Maintenance Course',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(8,5,'Sinhala','language','',NULL,NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(9,5,'Tamil','language','intermediate',NULL,NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(10,5,'English','language','basic',NULL,NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(11,2,'Recruitment','hr','expert','HR Certification',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(12,2,'Performance Management','hr','advanced','PM Certified',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(13,2,'Labor Law','legal','advanced','Labor Law Certificate',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(14,6,'Sales Techniques','sales','advanced','Sales Excellence Certificate',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(15,6,'Customer Relationship','sales','expert','CRM Certified',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(16,6,'Negotiation','sales','advanced','Negotiation Skills Certificate',NULL,NULL,0,NULL,NULL,'2025-12-04 15:42:11'),(29,8,'English','language','expert','IELTS 8.5',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(30,8,'Hindi','language','expert',NULL,NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(31,8,'Cultural Heritage','knowledge','advanced','Cultural Guide Certification',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(32,8,'Photography','technical','intermediate','Basic Photography Course',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(33,9,'English','language','advanced','TOEFL 110',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(34,9,'Sinhala','language','expert',NULL,NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(35,9,'Wildlife','knowledge','expert','Wildlife Guide License',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(36,9,'First Aid','safety','advanced','Red Cross Certification',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(37,10,'English','language','expert','Cambridge Advanced',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(38,10,'French','language','intermediate','DELF B2',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(39,10,'Culinary Tours','specialization','advanced','Food Tourism Certificate',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47'),(40,10,'History','knowledge','expert','History Degree',NULL,NULL,0,NULL,NULL,'2025-12-06 15:35:47');
/*!40000 ALTER TABLE `employee_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_social_media`
--

DROP TABLE IF EXISTS `employee_social_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_social_media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `platform_id` int NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `profile_url` varchar(500) NOT NULL,
  `follower_count` int DEFAULT '0',
  `is_primary` tinyint(1) DEFAULT '0',
  `is_public` tinyint(1) DEFAULT '1',
  `verified` tinyint(1) DEFAULT '0',
  `verified_by` int DEFAULT NULL,
  `verified_date` date DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_employee_platform` (`employee_id`,`platform_id`),
  KEY `verified_by` (`verified_by`),
  KEY `status_id` (`status_id`),
  KEY `idx_employee_social_media_employee` (`employee_id`),
  KEY `idx_employee_social_media_platform` (`platform_id`),
  KEY `idx_employee_social_media_primary` (`is_primary`),
  CONSTRAINT `employee_social_media_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE,
  CONSTRAINT `employee_social_media_ibfk_2` FOREIGN KEY (`platform_id`) REFERENCES `social_media_platforms` (`id`),
  CONSTRAINT `employee_social_media_ibfk_3` FOREIGN KEY (`verified_by`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_social_media_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_social_media`
--

LOCK TABLES `employee_social_media` WRITE;
/*!40000 ALTER TABLE `employee_social_media` DISABLE KEYS */;
INSERT INTO `employee_social_media` VALUES (1,1,1,'pasindudilshan','https://linkedin.com/in/pasindudilshan',2500,1,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(2,1,2,'pasindu.official','https://facebook.com/pasindu.official',1800,0,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(3,1,3,'pasindu_travels','https://instagram.com/pasindu_travels',3200,0,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(4,1,4,'pasindu_ceo','https://twitter.com/pasindu_ceo',1500,0,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(5,2,1,'adminuser.hr','https://linkedin.com/in/adminuser.hr',1200,1,1,1,1,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(6,2,2,'adminuser.hr.professional','https://facebook.com/adminuser.hr.professional',800,0,0,1,1,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(7,2,3,'adminuser_hr','https://instagram.com/adminuser_hr',950,0,1,1,1,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(8,2,4,'adminuser_hr_tweets','https://twitter.com/adminuser_hr_tweets',600,0,1,1,1,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(9,3,1,'adminuser2.ops','https://linkedin.com/in/adminuser2.ops',1800,1,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(10,3,2,'adminuser2.operations','https://facebook.com/adminuser2.operations',1200,0,0,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(11,3,3,'adminuser2_ops','https://instagram.com/adminuser2_ops',1500,0,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(12,3,4,'adminuser2_ops','https://twitter.com/adminuser2_ops',900,0,1,1,2,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(13,4,1,'davidtourguide','https://linkedin.com/in/davidtourguide',850,0,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(14,4,2,'david.tour.guide','https://facebook.com/david.tour.guide',2200,1,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(15,4,3,'david_travel_experiences','https://instagram.com/david_travel_experiences',5800,0,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(16,4,4,'david_guide_tips','https://twitter.com/david_guide_tips',1200,0,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(17,5,1,'ravidriver.pro','https://linkedin.com/in/ravidriver.pro',450,1,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(18,5,2,'ravi.driver.transport','https://facebook.com/ravi.driver.transport',1200,0,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(19,5,3,'ravi_driver_adventures','https://instagram.com/ravi_driver_adventures',1800,0,1,1,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(20,5,4,'ravi_safe_driver','https://twitter.com/ravi_safe_driver',350,0,1,0,3,NULL,NULL,'2025-12-04 15:49:18','2025-12-04 15:49:18',1),(29,8,1,'priyankasharma-guide','https://linkedin.com/in/priyankasharma-guide',850,1,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(30,8,2,'priyanka.cultural.tours','https://facebook.com/priyanka.cultural.tours',3200,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(31,8,3,'priyanka_heritage_tours','https://instagram.com/priyanka_heritage_tours',4500,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(32,8,4,'priyanka_guide','https://twitter.com/priyanka_guide',1200,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(33,9,1,'ravi-fernando-wildlife','https://linkedin.com/in/ravi-fernando-wildlife',720,1,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(34,9,2,'ravi.wildlife.adventures','https://facebook.com/ravi.wildlife.adventures',2800,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(35,9,3,'ravi_wildlife_shots','https://instagram.com/ravi_wildlife_shots',5200,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(36,9,4,'ravi_wildlife','https://twitter.com/ravi_wildlife',950,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(37,10,1,'anjaliperera-foodtours','https://linkedin.com/in/anjaliperera-foodtours',680,1,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(38,10,2,'anjali.culinary.journeys','https://facebook.com/anjali.culinary.journeys',2100,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(39,10,3,'anjali_food_travels','https://instagram.com/anjali_food_travels',3800,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1),(40,10,4,'anjali_foodie','https://twitter.com/anjali_foodie',1100,0,1,1,2,NULL,NULL,'2025-12-06 15:38:23','2025-12-06 15:38:23',1);
/*!40000 ALTER TABLE `employee_social_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_trainings`
--

DROP TABLE IF EXISTS `employee_trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_trainings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `training_name` varchar(255) NOT NULL,
  `training_type` varchar(100) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `duration_hours` int DEFAULT NULL,
  `certificate_issued` tinyint(1) DEFAULT '0',
  `certificate_path` varchar(500) DEFAULT NULL,
  `skills_acquired` text,
  `cost` decimal(10,2) DEFAULT NULL,
  `status` enum('planned','in_progress','completed','cancelled') DEFAULT 'planned',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_trainings_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_trainings_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_trainings`
--

LOCK TABLES `employee_trainings` WRITE;
/*!40000 ALTER TABLE `employee_trainings` DISABLE KEYS */;
INSERT INTO `employee_trainings` VALUES (1,4,'Advanced Tour Guiding Techniques','external','Tourism Development Authority','2023-09-10','2023-09-12',24,1,NULL,'Advanced guiding techniques, crowd management',15000.00,'completed','2025-12-04 15:42:11',1),(2,5,'Defensive Driving Course','external','Road Safety Authority','2023-10-05','2023-10-07',18,1,NULL,'Defensive driving, emergency handling',12000.00,'completed','2025-12-04 15:42:11',1),(3,6,'Advanced Sales Techniques','external','Sales Excellence Institute','2024-01-20','2024-01-22',20,1,NULL,'Advanced sales strategies, negotiation skills',18000.00,'completed','2025-12-04 15:42:11',1),(4,2,'HR Compliance Workshop','internal','Internal Training Department','2024-02-15','2024-02-16',12,0,NULL,'Latest labor laws, compliance requirements',0.00,'completed','2025-12-04 15:42:11',1),(5,8,'Advanced Cultural Guiding','external','Tourism Authority','2023-11-10','2023-11-12',20,1,NULL,'Cultural interpretation, storytelling',NULL,'completed','2025-12-06 15:34:57',1),(6,9,'Wildlife Photography for Guides','external','Photography Institute','2023-12-05','2023-12-07',18,1,NULL,'Wildlife photography, camera handling',NULL,'completed','2025-12-06 15:34:57',1),(7,10,'Food Safety for Tour Guides','external','Health Department','2024-01-15','2024-01-16',10,1,NULL,'Food safety, hygiene standards',NULL,'completed','2025-12-06 15:34:57',1);
/*!40000 ALTER TABLE `employee_trainings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_types`
--

DROP TABLE IF EXISTS `employee_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_name` (`type_name`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_types_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_types`
--

LOCK TABLES `employee_types` WRITE;
/*!40000 ALTER TABLE `employee_types` DISABLE KEYS */;
INSERT INTO `employee_types` VALUES (1,'guide','Tour guides and travel guides','2025-12-04 15:42:11',1),(2,'driver','Vehicle drivers and transportation staff','2025-12-04 15:42:11',1),(3,'executive','Management and executive staff','2025-12-04 15:42:11',1),(4,'sales','Sales and booking agents','2025-12-04 15:42:11',1),(5,'marketing','Marketing and promotion staff','2025-12-04 15:42:11',1),(6,'hr','Human resources and administration','2025-12-04 15:42:11',1),(7,'operations','Operations and logistics staff','2025-12-04 15:42:11',1),(8,'accountant','Finance and accounting staff','2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_work_history`
--

DROP TABLE IF EXISTS `employee_work_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_work_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `designation_id` int DEFAULT NULL,
  `department_id` int DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `employment_type` enum('full-time','part-time','contract','intern') DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `notes` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `designation_id` (`designation_id`),
  KEY `department_id` (`department_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_work_history_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employee_work_history_ibfk_2` FOREIGN KEY (`designation_id`) REFERENCES `employee_designations` (`id`),
  CONSTRAINT `employee_work_history_ibfk_3` FOREIGN KEY (`department_id`) REFERENCES `employee_departments` (`id`),
  CONSTRAINT `employee_work_history_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_work_history`
--

LOCK TABLES `employee_work_history` WRITE;
/*!40000 ALTER TABLE `employee_work_history` DISABLE KEYS */;
INSERT INTO `employee_work_history` VALUES (1,2,2,2,180000.00,'2021-03-10',NULL,NULL,'Promotion','Joined as HR Manager','2025-12-04 15:42:11',1),(2,4,5,5,80000.00,'2022-02-15','2023-02-14',NULL,'Initial Position','Started as Tour Guide','2025-12-04 15:42:11',1),(3,4,4,5,120000.00,'2023-02-15',NULL,NULL,'Promotion','Promoted to Senior Tour Guide','2025-12-04 15:42:11',1),(4,5,7,6,75000.00,'2022-06-01',NULL,NULL,'Hired','Joined as Driver','2025-12-04 15:42:11',1),(5,8,5,5,65000.00,'2023-04-10',NULL,NULL,'Hired','Joined as Tour Guide','2025-12-06 15:33:15',1),(6,9,5,5,70000.00,'2023-06-15',NULL,NULL,'Hired','Joined as Tour Guide','2025-12-06 15:33:15',1),(7,10,5,5,68000.00,'2023-08-22',NULL,NULL,'Hired','Joined as Tour Guide','2025-12-06 15:33:15',1);
/*!40000 ALTER TABLE `employee_work_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_work_shifts`
--

DROP TABLE IF EXISTS `employee_work_shifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_work_shifts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shift_name` varchar(50) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `break_duration_minutes` int DEFAULT '60',
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `employee_work_shifts_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_work_shifts`
--

LOCK TABLES `employee_work_shifts` WRITE;
/*!40000 ALTER TABLE `employee_work_shifts` DISABLE KEYS */;
INSERT INTO `employee_work_shifts` VALUES (1,'Morning Shift','09:00:00','18:00:00',60,NULL,'2025-12-04 15:42:11',1),(2,'Evening Shift','13:00:00','22:00:00',60,NULL,'2025-12-04 15:42:11',1),(3,'Night Shift','22:00:00','06:00:00',60,NULL,'2025-12-04 15:42:11',1),(4,'Flexi Shift','10:00:00','19:00:00',60,NULL,'2025-12-04 15:42:11',1);
/*!40000 ALTER TABLE `employee_work_shifts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `employee_code` varchar(20) DEFAULT NULL,
  `employee_type_id` int NOT NULL,
  `department_id` int DEFAULT NULL,
  `designation_id` int DEFAULT NULL,
  `hire_date` date NOT NULL,
  `employment_type` enum('full-time','part-time','contract','intern') DEFAULT 'full-time',
  `supervisor_id` int DEFAULT NULL,
  `reporting_manager_id` int DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `bank_account_number` varchar(50) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `bank_branch` varchar(100) DEFAULT NULL,
  `ifsc_code` varchar(20) DEFAULT NULL,
  `uan_number` varchar(20) DEFAULT NULL,
  `pf_number` varchar(50) DEFAULT NULL,
  `esi_number` varchar(50) DEFAULT NULL,
  `probation_period_months` int DEFAULT '3',
  `probation_end_date` date DEFAULT NULL,
  `confirmation_date` date DEFAULT NULL,
  `exit_date` date DEFAULT NULL,
  `work_location` varchar(100) DEFAULT NULL,
  `cost_center` varchar(50) DEFAULT NULL,
  `employee_grade` varchar(20) DEFAULT NULL,
  `emergency_contact_name` varchar(100) DEFAULT NULL,
  `emergency_contact_phone` varchar(20) DEFAULT NULL,
  `emergency_contact_relationship` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `employee_code` (`employee_code`),
  KEY `reporting_manager_id` (`reporting_manager_id`),
  KEY `idx_employees_user_id` (`user_id`),
  KEY `idx_employees_employee_type` (`employee_type_id`),
  KEY `idx_employees_department` (`department_id`),
  KEY `idx_employees_designation` (`designation_id`),
  KEY `idx_employees_status` (`status_id`),
  KEY `idx_employees_supervisor` (`supervisor_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`employee_type_id`) REFERENCES `employee_types` (`id`),
  CONSTRAINT `employees_ibfk_3` FOREIGN KEY (`department_id`) REFERENCES `employee_departments` (`id`),
  CONSTRAINT `employees_ibfk_4` FOREIGN KEY (`designation_id`) REFERENCES `employee_designations` (`id`),
  CONSTRAINT `employees_ibfk_5` FOREIGN KEY (`supervisor_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employees_ibfk_6` FOREIGN KEY (`reporting_manager_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `employees_ibfk_7` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,1,'EMP001',3,1,1,'2020-01-15','full-time',NULL,NULL,400000.00,'123456789012','Commercial Bank','Colombo 07','COMB1234567','UAN00123456','PF1234567','ESI123456',3,'2020-04-15',NULL,NULL,'Head Office',NULL,NULL,'Jane Doe','+94111111111','Spouse','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(2,2,'EMP002',6,2,2,'2021-03-10','full-time',1,1,200000.00,'234567890123','HSBC','Colombo 03','HSBC2345678','UAN00234567','PF2345678','ESI234567',3,'2021-06-10',NULL,NULL,'Head Office',NULL,NULL,'Michael Brown','+94111111112','Father','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(3,3,'EMP003',3,3,3,'2021-05-20','full-time',1,2,180000.00,'345678901234','NDB Bank','Colombo 05','NDB3456789','UAN00345678','PF3456789','ESI345678',3,'2021-08-20',NULL,NULL,'Head Office',NULL,NULL,'Sarah Johnson','+94111111113','Sister','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(4,4,'EMP004',1,5,4,'2022-02-15','full-time',1,2,120000.00,'456789012345','Sampath Bank','Kandy','SAMP4567890','UAN00456789','PF4567890','ESI456789',3,'2022-05-15',NULL,NULL,'Kandy Branch',NULL,NULL,'Kamal Perera','+94222222222','Brother','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(5,5,'EMP005',2,6,7,'2022-06-01','full-time',1,2,75000.00,'567890123456','People\'s Bank','Galle','PEOP5678901','UAN00567890','PF5678901','ESI567890',3,'2022-09-01',NULL,NULL,'Galle Branch',NULL,NULL,'Nimal Fernando','+94333333333','Uncle','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(6,6,'EMP006',4,4,8,'2022-07-10','full-time',1,2,90000.00,'678901234567','Hatton National Bank','Colombo 02','HNB6789012','UAN00678901','PF6789012','ESI678901',3,'2022-10-10',NULL,NULL,'Head Office',NULL,NULL,'Priya Silva','+94444444444','Mother','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(7,8,'EMP007',5,4,9,'2022-08-20','full-time',1,2,85000.00,'789012345678','DFCC Bank','Colombo 01','DFCC7890123','UAN00789012','PF7890123','ESI789012',3,'2022-11-20',NULL,NULL,'Head Office',NULL,NULL,'Rajesh Kumar','+94555555555','Cousin','2025-12-04 15:42:11','2025-12-04 15:42:11',1),(8,9,'EMP008',1,5,5,'2023-04-10','full-time',3,3,65000.00,'890123456789','Sampath Bank','Colombo 03','SAMP8901234','UAN00890123','PF8901234','ESI890123',3,'2023-07-10',NULL,NULL,'Colombo Office',NULL,NULL,'Sanjay Sharma','+94771111111','Husband','2025-12-06 15:33:15','2025-12-06 15:33:15',1),(9,10,'EMP009',1,5,5,'2023-06-15','full-time',3,3,70000.00,'901234567890','Commercial Bank','Galle','COMB9012345','UAN00901234','PF9012345','ESI901234',3,'2023-09-15',NULL,NULL,'Galle Branch',NULL,NULL,'Mala Fernando','+94772222222','Wife','2025-12-06 15:33:15','2025-12-06 15:33:15',1),(10,11,'EMP010',1,5,5,'2023-08-22','full-time',3,3,68000.00,'012345678901','Hatton National Bank','Kandy','HNB0123456','UAN01012345','PF0123456','ESI012345',3,'2023-11-22',NULL,NULL,'Kandy Branch',NULL,NULL,'Dinesh Perera','+94773333333','Brother','2025-12-06 15:33:15','2025-12-06 15:33:15',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `answer1` text,
  `answer2` text,
  `answer3` text,
  `answer4` text,
  `answer5` text,
  `display_answer` varchar(50) DEFAULT NULL,
  `faq_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faq_status_id` (`faq_status_id`),
  CONSTRAINT `faq_ibfk_1` FOREIGN KEY (`faq_status_id`) REFERENCES `faq_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'Do I need a visa to visit Sri Lanka?','Most tourists need an Electronic Travel Authorization (ETA).','You can apply online through the official immigration website.','ETA approval usually arrives within 24 hours.','Some countries are visa-exempt for short visits.','On-arrival visa facilities are limited, so apply online first.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(2,'How long does it take to get an ETA visa?','In most cases, approval takes a few hours.','During busy periods, it may take up to 2 days.','Emergency processing is available at the airport.','We recommend applying at least 3 days before your trip.','The online system is available 24/7 worldwide.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(3,'What is the best time to visit Sri Lanka?','December to April is best for the south and west coasts.','May to September is best for the east coast.','Hill country is cooler year-round, great for hiking.','Cultural festivals like Kandy Perahera are in July/August.','Wildlife safaris are best in the dry season (June–September).','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(4,'Which currency is used in Sri Lanka?','The Sri Lankan Rupee (LKR) is the official currency.','Foreign currency can be exchanged at banks and hotels.','ATMs are widely available in cities and tourist towns.','USD, EUR, and GBP are the most accepted foreign currencies.','Credit cards can be used but cash is preferred in rural areas.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(5,'Can I use credit cards in Sri Lanka?','Yes, most hotels and restaurants accept cards.','Visa and Mastercard are the most widely accepted.','American Express and Diners Club are less common.','Always carry cash for small shops and rural areas.','Some ATMs allow direct cash withdrawals from foreign cards.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(6,'Is Sri Lanka safe for foreign tourists?','Sri Lanka is generally considered very safe.','Petty theft can occur in crowded areas.','Locals are friendly and welcoming to tourists.','Avoid traveling alone late at night in isolated areas.','Tourist police are available in major destinations.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(7,'Do I need vaccinations before traveling to Sri Lanka?','No compulsory vaccines are required.','Recommended: Hepatitis A and Typhoid.','Some travelers consider Yellow Fever if coming from endemic countries.','Tetanus and Rabies shots are advisable for long stays.','Consult your doctor before travel.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(8,'What language is spoken in Sri Lanka?','Sinhala is the most widely spoken language.','Tamil is also an official language.','English is widely used in tourism and business.','Most hotels and guides speak English fluently.','Signboards in tourist areas are often bilingual.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(9,'Can I drink tap water in Sri Lanka?','It is not safe to drink tap water directly.','Always drink bottled or boiled water.','Hotels often provide filtered water.','Ice in tourist hotels is usually safe.','Avoid drinking from public taps.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(10,'What are the must-visit places in Sri Lanka?','Sigiriya Rock Fortress – UNESCO World Heritage Site.','Kandy – Temple of the Tooth.','Galle Fort – historic colonial town.','Ella – scenic train rides and tea plantations.','Yala National Park – famous for leopards.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(11,'What is the local transportation like in Sri Lanka?','Tuk-tuks are cheap and popular for short distances.','Trains offer scenic rides, especially Kandy to Ella.','Buses are affordable but crowded.','Private car rentals with drivers are convenient.','Domestic flights are available between major cities.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(12,'Do I need travel insurance for Sri Lanka?','Yes, travel insurance is strongly recommended.','It should cover medical emergencies.','Policies should include theft and lost luggage.','Adventure sports coverage is useful for safaris or surfing.','COVID-19 coverage may be required at entry.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(13,'Is there free Wi-Fi in Sri Lanka?','Most hotels and cafes offer free Wi-Fi.','Speeds may vary outside major cities.','Tourist SIM cards include mobile data.','4G coverage is available in most towns.','Some buses and trains now offer free Wi-Fi.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(14,'Which mobile SIM card is best for tourists in Sri Lanka?','Dialog is the most popular with wide coverage.','Mobitel is also widely used and reliable.','Both offer tourist SIM packages at the airport.','Etisalat and Hutch offer budget options.','SIM registration requires your passport.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(15,'What kind of food can I expect in Sri Lanka?','Traditional rice and curry with vegetables and meat.','Seafood dishes are very popular on the coasts.','Street food like kottu roti is a must-try.','Hoppers and string hoppers are common breakfast items.','Plenty of vegetarian and vegan options available.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(16,'Is tipping expected in Sri Lanka?','Tipping is not mandatory but appreciated.','A 10% tip is common in restaurants.','Some hotels include a service charge already.','Tour drivers usually receive daily tips.','Small tips for porters and tuk-tuk drivers are welcome.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(17,'What are the cultural etiquette rules in Sri Lanka?','Dress modestly when visiting temples.','Remove shoes and hats before entering temples.','Never pose with your back to a Buddha statue.','Public displays of affection are discouraged.','Use your right hand for giving and receiving items.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(18,'Can I rent a car and drive in Sri Lanka?','Yes, but you need an International Driving Permit.','Traffic can be chaotic for first-time visitors.','Most tourists prefer hiring a driver.','Cars drive on the left side of the road.','Rental cars are available in major cities.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(19,'What is the emergency contact number in Sri Lanka?','Police – 119','Ambulance/Medical emergencies – 110','Fire department – 111','Tourist Police hotline – 1912','Emergency assistance is also available via your hotel.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(20,'Are there national parks to see wildlife in Sri Lanka?','Yes, Yala is famous for leopards.','Udawalawe is known for elephants.','Minneriya has the largest elephant gathering.','Wilpattu is Sri Lanka’s oldest park.','Bundala is a paradise for birdwatchers.','answer1',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_contact_priority`
--

DROP TABLE IF EXISTS `faq_contact_priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_contact_priority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(7) DEFAULT '#6B7280',
  `sort_order` int DEFAULT '0',
  `response_time_hours` int DEFAULT NULL,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_contact_priority_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_contact_priority`
--

LOCK TABLES `faq_contact_priority` WRITE;
/*!40000 ALTER TABLE `faq_contact_priority` DISABLE KEYS */;
INSERT INTO `faq_contact_priority` VALUES (1,'low','Low priority','#6B7280',1,72,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(2,'medium','Medium priority','#10B981',2,24,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(3,'high','High priority','#F59E0B',3,4,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(4,'urgent','Urgent priority','#EF4444',4,1,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_contact_priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_contact_reply_type`
--

DROP TABLE IF EXISTS `faq_contact_reply_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_contact_reply_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_contact_reply_type_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_contact_reply_type`
--

LOCK TABLES `faq_contact_reply_type` WRITE;
/*!40000 ALTER TABLE `faq_contact_reply_type` DISABLE KEYS */;
INSERT INTO `faq_contact_reply_type` VALUES (1,'customer','Reply from customer','user',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(2,'support','Reply from support team','support_agent',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(3,'system','System generated message','auto_message',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_contact_reply_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_contact_request_replies`
--

DROP TABLE IF EXISTS `faq_contact_request_replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_contact_request_replies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faq_contact_request_id` int NOT NULL,
  `reply_type_id` int NOT NULL,
  `message` text NOT NULL,
  `internal_notes` text,
  `replied_by` int DEFAULT NULL,
  `replied_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faq_contact_request_id` (`faq_contact_request_id`),
  KEY `reply_type_id` (`reply_type_id`),
  KEY `common_status_id` (`common_status_id`),
  KEY `replied_by` (`replied_by`),
  CONSTRAINT `faq_contact_request_replies_ibfk_1` FOREIGN KEY (`faq_contact_request_id`) REFERENCES `faq_contact_requests` (`id`) ON DELETE CASCADE,
  CONSTRAINT `faq_contact_request_replies_ibfk_2` FOREIGN KEY (`reply_type_id`) REFERENCES `faq_contact_reply_type` (`id`),
  CONSTRAINT `faq_contact_request_replies_ibfk_3` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`),
  CONSTRAINT `faq_contact_request_replies_ibfk_4` FOREIGN KEY (`replied_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_contact_request_replies`
--

LOCK TABLES `faq_contact_request_replies` WRITE;
/*!40000 ALTER TABLE `faq_contact_request_replies` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq_contact_request_replies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_contact_request_status`
--

DROP TABLE IF EXISTS `faq_contact_request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_contact_request_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(7) DEFAULT '#6B7280',
  `sort_order` int DEFAULT '0',
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_contact_request_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_contact_request_status`
--

LOCK TABLES `faq_contact_request_status` WRITE;
/*!40000 ALTER TABLE `faq_contact_request_status` DISABLE KEYS */;
INSERT INTO `faq_contact_request_status` VALUES (1,'new','New request received','#EF4444',1,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(2,'in_progress','Request is being handled','#F59E0B',2,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(3,'awaiting_reply','Waiting for customer reply','#8B5CF6',3,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(4,'resolved','Issue has been resolved','#10B981',4,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(5,'closed','Request has been closed','#6B7280',5,1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_contact_request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_contact_requests`
--

DROP TABLE IF EXISTS `faq_contact_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_contact_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ticket_number` varchar(20) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `category` varchar(50) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `request_status_id` int NOT NULL,
  `priority_id` int NOT NULL,
  `assigned_to` int DEFAULT NULL,
  `first_response_at` timestamp NULL DEFAULT NULL,
  `resolved_at` timestamp NULL DEFAULT NULL,
  `closed_at` timestamp NULL DEFAULT NULL,
  `auto_reply_sent` tinyint(1) DEFAULT '0',
  `auto_reply_sent_at` timestamp NULL DEFAULT NULL,
  `reply_count` int DEFAULT '0',
  `last_reply_at` timestamp NULL DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `user_agent` text,
  `referrer_url` varchar(500) DEFAULT NULL,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `request_status_id` (`request_status_id`),
  KEY `priority_id` (`priority_id`),
  KEY `common_status_id` (`common_status_id`),
  KEY `assigned_to` (`assigned_to`),
  CONSTRAINT `faq_contact_requests_ibfk_1` FOREIGN KEY (`request_status_id`) REFERENCES `faq_contact_request_status` (`id`),
  CONSTRAINT `faq_contact_requests_ibfk_2` FOREIGN KEY (`priority_id`) REFERENCES `faq_contact_priority` (`id`),
  CONSTRAINT `faq_contact_requests_ibfk_3` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`),
  CONSTRAINT `faq_contact_requests_ibfk_4` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_contact_requests`
--

LOCK TABLES `faq_contact_requests` WRITE;
/*!40000 ALTER TABLE `faq_contact_requests` DISABLE KEYS */;
INSERT INTO `faq_contact_requests` VALUES (1,'TKT-202412345678','Pasindu','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:45:44',1,'2025-10-25 06:45:44',NULL,NULL,NULL),(4,'TKT-2024123456781','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:46:09',1,'2025-10-25 06:46:09',NULL,NULL,NULL),(5,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:46:58',1,'2025-10-25 06:46:58',NULL,NULL,NULL),(7,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:47:30',1,'2025-10-25 06:47:30',NULL,NULL,NULL),(8,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:47:31',1,'2025-10-25 06:47:31',NULL,NULL,NULL),(9,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:54:44',1,'2025-10-25 06:54:44',NULL,NULL,NULL),(10,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 06:54:59',1,'2025-10-25 06:54:59',NULL,NULL,NULL),(11,'','a','pasindu@example.co','','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:02:19',1,'2025-10-25 07:02:19',NULL,NULL,NULL),(12,'','a','pasindu@example.co','','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:02:57',1,'2025-10-25 07:02:57',NULL,NULL,NULL),(13,'','a','pasindu@example.co',' ','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:03:20',1,'2025-10-25 07:03:20',NULL,NULL,NULL),(14,'','a','pasindu@example.co','','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:03:24',1,'2025-10-25 07:03:24',NULL,NULL,NULL),(15,'','a','pasindu@example.co','','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:04:38',1,'2025-10-25 07:04:38',NULL,NULL,NULL),(16,'TKT-202412345678','Pasindu','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account. When I enter my credentials, I get an error message saying invalid credentials. I\'ve reset my password but still facing the same issue.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.1.100',NULL,NULL,1,'2025-10-25 07:05:56',1,'2025-10-25 07:05:56',NULL,NULL,NULL),(17,'','Pasindu1','pasindu@example.com','Technical Support','Unable to login to my account','I\'m having issues logging into my account.',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:06:01',1,'2025-10-25 07:06:01',NULL,NULL,NULL),(18,'','pasindu','pd.dimbulana@gmail.com','technical','123','123',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:13:28',1,'2025-10-25 07:13:28',NULL,NULL,NULL),(19,'','123','pd.dimbulana@gmail.com','technical','123','123',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-25 07:18:41',1,'2025-10-25 07:18:41',NULL,NULL,NULL),(20,'','Nipunika','nipunika@gmail.com','general','payment methods ','bla bla',1,1,NULL,NULL,NULL,NULL,0,NULL,0,NULL,'192.168.0.1',NULL,NULL,1,'2025-10-31 12:24:46',1,'2025-10-31 12:24:46',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_contact_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_hero_section`
--

DROP TABLE IF EXISTS `faq_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `faq_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_hero_section`
--

LOCK TABLES `faq_hero_section` WRITE;
/*!40000 ALTER TABLE `faq_hero_section` DISABLE KEYS */;
INSERT INTO `faq_hero_section` VALUES (1,'FAQ_MAIN_HERO','https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800&auto=format&fit=crop','Frequently Asked Questions','Everything you need to know','Find answers to the most common questions about our services, bookings, payments, and policies.','Browse FAQs','/faq','Contact Support','/contact',1,1,'2025-12-15 13:32:53',1,'2025-12-15 13:32:53',NULL,NULL,NULL),(2,'FAQ_BOOKINGS','https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?w-800&auto=format&fit=crop','Booking & Reservations','Quick answers about your bookings','Learn how to make, modify, or cancel your bookings and understand our reservation policies.','View Booking FAQs','/faq#bookings','Manage Booking','/my-bookings',1,2,'2025-12-15 13:32:53',1,'2025-12-15 13:32:53',NULL,NULL,NULL),(3,'FAQ_PAYMENTS','https://images.unsplash.com/photo-1554224155-6726b3ff858f?w-800&auto=format&fit=crop','Payments & Billing','Secure and transparent payments','Get clear information about payment methods, refunds, invoices, and transaction security.','Payment FAQs','/faq#payments','View Pricing','/pricing',1,3,'2025-12-15 13:32:53',1,'2025-12-15 13:32:53',NULL,NULL,NULL),(4,'FAQ_TRAVEL_SUPPORT','https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w-800&auto=format&fit=crop','Travel Support','We are here to help','Find support for travel changes, emergencies, documentation, and customer assistance.','Support FAQs','/faq#support','Get Help','/support',1,4,'2025-12-15 13:32:53',1,'2025-12-15 13:32:53',NULL,NULL,NULL),(5,'FAQ_ACCOUNT','https://images.unsplash.com/photo-1556742044-3c52d6e88c62?w-800&auto=format&fit=crop','Account & Profile','Manage your account easily','Learn how to create, update, and secure your account and manage your personal information.','Account FAQs','/faq#account','My Account','/account',1,5,'2025-12-15 13:32:53',1,'2025-12-15 13:32:53',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_option_type`
--

DROP TABLE IF EXISTS `faq_option_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_option_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_option_type_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_option_type`
--

LOCK TABLES `faq_option_type` WRITE;
/*!40000 ALTER TABLE `faq_option_type` DISABLE KEYS */;
INSERT INTO `faq_option_type` VALUES (1,'string','Text string value',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(2,'number','Numeric value',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(3,'boolean','True/False value',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(4,'json','JSON formatted data',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(5,'array','Array of values',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_option_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_options`
--

DROP TABLE IF EXISTS `faq_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `option_key` varchar(100) NOT NULL,
  `option_value` text,
  `option_type_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `option_key` (`option_key`),
  KEY `option_type_id` (`option_type_id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_options_ibfk_1` FOREIGN KEY (`option_type_id`) REFERENCES `faq_option_type` (`id`),
  CONSTRAINT `faq_options_ibfk_2` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_options`
--

LOCK TABLES `faq_options` WRITE;
/*!40000 ALTER TABLE `faq_options` DISABLE KEYS */;
INSERT INTO `faq_options` VALUES (1,'display_limit_mobile','5',2,'Number of FAQs to show on mobile devices',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(2,'display_limit_tablet','6',2,'Number of FAQs to show on tablet devices',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(3,'display_limit_desktop','7',2,'Number of FAQs to show on desktop devices',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(4,'contact_form_categories','[\"general\",\"technical\",\"billing\",\"feature\",\"bug\"]',4,'Available categories for contact form',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(5,'auto_reply_enabled','true',3,'Whether to send auto-reply emails',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(6,'auto_reply_subject','We have received your message',1,'Subject for auto-reply emails',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(7,'auto_reply_message','Thank you for contacting us. We will get back to you within 24 hours.',1,'Message for auto-reply emails',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(8,'support_email','support@yourcompany.com',1,'Default support email address',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(9,'response_time_hours','24',2,'Expected response time in hours',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL),(10,'enable_view_tracking','true',3,'Enable FAQ view count tracking',1,'2025-10-25 06:45:21',1,'2025-10-25 06:45:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_status`
--

DROP TABLE IF EXISTS `faq_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `faq_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_status`
--

LOCK TABLES `faq_status` WRITE;
/*!40000 ALTER TABLE `faq_status` DISABLE KEYS */;
INSERT INTO `faq_status` VALUES (1,'VISIBLE','FAQ is visible on frontend',1,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL),(2,'HIDDEN','FAQ is hidden from frontend',2,'2025-09-20 06:40:37',1,'2025-09-20 06:40:37',NULL,NULL,NULL);
/*!40000 ALTER TABLE `faq_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_view_count`
--

DROP TABLE IF EXISTS `faq_view_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq_view_count` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faq_id` int NOT NULL,
  `count` int DEFAULT '0',
  `last_view` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `faq_id` (`faq_id`),
  CONSTRAINT `faq_view_count_ibfk_1` FOREIGN KEY (`faq_id`) REFERENCES `faq` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_view_count`
--

LOCK TABLES `faq_view_count` WRITE;
/*!40000 ALTER TABLE `faq_view_count` DISABLE KEYS */;
INSERT INTO `faq_view_count` VALUES (1,1,66,'2025-12-13 10:48:28'),(2,2,14,'2025-12-13 10:48:31'),(3,3,4,'2025-10-24 17:40:01'),(4,4,2,'2025-12-15 13:55:05'),(5,5,3,'2025-10-05 08:01:53'),(6,6,1,'2025-12-15 13:54:46'),(7,7,7,'2025-11-11 05:10:49'),(8,8,2,'2025-10-24 17:40:33'),(9,9,1,'2025-10-31 12:22:30'),(10,10,0,'2025-10-24 17:42:18'),(11,11,0,'2025-10-24 17:42:18'),(12,12,0,'2025-10-24 17:42:18'),(13,13,0,'2025-10-24 17:42:18'),(14,14,3,'2025-10-24 17:55:27'),(15,15,0,'2025-10-24 17:42:18'),(16,16,0,'2025-10-24 17:42:18'),(17,17,0,'2025-10-24 17:42:18'),(18,18,0,'2025-10-24 17:42:18'),(19,19,1,'2025-10-25 04:10:30'),(20,20,3,'2025-10-25 07:15:27');
/*!40000 ALTER TABLE `faq_view_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `features`
--

DROP TABLE IF EXISTS `features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `features` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` text,
  `status` int NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `special_note` text,
  `hover_color` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`),
  KEY `status` (`status`),
  CONSTRAINT `features_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `features_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features`
--

LOCK TABLES `features` WRITE;
/*!40000 ALTER TABLE `features` DISABLE KEYS */;
INSERT INTO `features` VALUES (1,1,'Hotel Type','3-Star','Overnight stay in 3-star hotel near Sigiriya Rock.',1,'#F4B400','Comfort stay with breakfast included.','#FFD700','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(2,1,'Meal Plan','Breakfast Included','Morning buffet with local and continental options.',1,'#34A853','Includes vegetarian meals.','#66BB6A','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(3,2,'Transport Type','AC Van','Round trip from Colombo in air-conditioned van.',1,'#4285F4','Includes bottled water and WiFi.','#5C6BC0','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(4,3,'Hotel Category','4-Star','Stay in top-rated hotels in Dambulla and Kandy.',1,'#E67E22','Heritage-style rooms with modern comfort.','#FFB74D','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(5,3,'Guide Service','Included','Professional English-speaking cultural guide.',1,'#9C27B0','Available throughout the journey.','#BA68C8','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(6,4,'Meals','Full Board','Breakfast, lunch, and dinner covered.',1,'#4CAF50','Includes traditional rice & curry meals.','#81C784','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(7,5,'Safari Vehicle','4x4 Jeep','Private safari jeep with driver and tracker.',1,'#795548','Sunset safari available upon request.','#A1887F','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(8,5,'Lodging','Luxury Camp','Stay in a fully equipped tented camp with AC.',1,'#009688','Evening campfire and BBQ.','#4DB6AC','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(9,6,'Hotel View','Sea View Rooms','Accommodation facing the Indian Ocean.',1,'#03A9F4','Includes balcony and sunset views.','#4FC3F7','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(10,7,'Boat Type','Double Deck','Spacious boat with safety jackets and snacks.',1,'#2196F3','Licensed marine tour guide onboard.','#64B5F6','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(11,8,'Hotel Type','Boutique Hotel','Small mountain-view hotel with Wi-Fi.',1,'#9C27B0','Eco-friendly and cozy environment.','#CE93D8','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(12,9,'Train Class','Observation Deck','Reserved seats in scenic observation car.',1,'#607D8B','Panoramic windows for photography.','#90A4AE','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(13,10,'Spa Access','Included','Full-body massage and aromatherapy session.',1,'#E91E63','Herbal oils used for treatments.','#F48FB1','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(14,11,'Resort Rating','5-Star','Luxury Ayurveda resort with pool and sauna.',1,'#FF9800','All-inclusive with spa treatments.','#FFB74D','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(15,12,'Transport','Private Car','Pickup and drop from hotel in AC car.',1,'#2196F3','Driver with local knowledge.','#64B5F6','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(16,13,'Temple Visit','Included','Guided entry to Temple of the Tooth.',1,'#8BC34A','Includes entry ticket.','#AED581','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(17,14,'Equipment','Provided','Trekking poles, snacks, and first-aid kit.',1,'#795548','Raincoats provided if needed.','#A1887F','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL),(18,15,'Accommodation','Jungle Lodge','Stay inside park perimeter with safety facilities.',1,'#388E3C','Campfire and night safari optional.','#66BB6A','2025-10-04 16:15:12',1,'2025-10-04 16:15:12',1,NULL,NULL);
/*!40000 ALTER TABLE `features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `footer`
--

DROP TABLE IF EXISTS `footer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `footer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_footer_status` (`status`),
  CONSTRAINT `fk_footer_status` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `footer`
--

LOCK TABLES `footer` WRITE;
/*!40000 ALTER TABLE `footer` DISABLE KEYS */;
INSERT INTO `footer` VALUES (1,'Services','Our service-related links','#333333',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(2,'Quick Links','Useful navigation links','#444444',1,'2025-09-30 05:43:06',1,'2025-09-30 08:21:14',NULL,NULL,NULL),(3,'Contacts','Get in touch with us','#555555',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL);
/*!40000 ALTER TABLE `footer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `footer_others`
--

DROP TABLE IF EXISTS `footer_others`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `footer_others` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_footer_others_status` (`status`),
  CONSTRAINT `fk_footer_others_status` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `footer_others`
--

LOCK TABLES `footer_others` WRITE;
/*!40000 ALTER TABLE `footer_others` DISABLE KEYS */;
INSERT INTO `footer_others` VALUES (1,'copyright','© 2025 Felicita. All rights reserved.','/faq',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(2,'Privacy Policy','Privacy Policy','/help',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(3,'Terms of Service','Terms of Service','/blog',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(4,'Sitemap','Sitemap','/careers',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL);
/*!40000 ALTER TABLE `footer_others` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `footer_sub_items`
--

DROP TABLE IF EXISTS `footer_sub_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `footer_sub_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `footer_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_footer_sub_footer` (`footer_id`),
  KEY `fk_footer_sub_status` (`status`),
  CONSTRAINT `fk_footer_sub_footer` FOREIGN KEY (`footer_id`) REFERENCES `footer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_footer_sub_status` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `footer_sub_items`
--

LOCK TABLES `footer_sub_items` WRITE;
/*!40000 ALTER TABLE `footer_sub_items` DISABLE KEYS */;
INSERT INTO `footer_sub_items` VALUES (1,1,'Home','Back to homepage','home','/',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(2,1,'Tour Packages','Explore our packages','suitcase','/tour-packages',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(3,1,'Destinations','Top destinations to visit','map-marker','/destinations',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(4,1,'Activities','Things to do','hiking','/activities',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(5,1,'About Us','Know more about us','info-circle','/about',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(6,1,'Blog','Travel stories and news','blog','/blog',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(7,1,'Contact','Reach out to us','phone','/contact',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(8,2,'Marvellous Sri Lanka','Special travel theme','star','/marvellous-sri-lanka',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(9,2,'Romantic Sri Lanka','Romantic getaway packages','heart','/romantic-sri-lanka',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(10,2,'Booking Terms & Conditions','Read our terms','file-contract','/terms',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(11,3,'Colombo','Our main office location','map-marker','#',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(12,3,'+94 11111111','Phone contact','phone','tel:+9411111111',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL),(13,3,'felicita@example.com','Email contact','envelope','mailto:felicita@example.com',1,'2025-09-30 05:43:06',1,'2025-09-30 05:43:06',NULL,NULL,NULL);
/*!40000 ALTER TABLE `footer_sub_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_types`
--

DROP TABLE IF EXISTS `fuel_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuel_types` (
  `fuel_type_id` int NOT NULL AUTO_INCREMENT,
  `fuel_type_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`fuel_type_id`),
  UNIQUE KEY `fuel_type_name` (`fuel_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_types`
--

LOCK TABLES `fuel_types` WRITE;
/*!40000 ALTER TABLE `fuel_types` DISABLE KEYS */;
INSERT INTO `fuel_types` VALUES (1,'Petrol','Gasoline fuel',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,'Diesel','Diesel fuel',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,'Hybrid','Hybrid electric-petrol/diesel',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(4,'Electric','Fully electric vehicle',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(5,'CNG','Compressed Natural Gas',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,'LPG','Liquefied Petroleum Gas',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `fuel_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `image_link` varchar(500) DEFAULT NULL,
  `image_owner` varchar(255) DEFAULT NULL,
  `image_source` varchar(255) DEFAULT NULL,
  `image_source_link` varchar(500) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `hover_color` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gallery_status` (`status_id`),
  CONSTRAINT `fk_gallery_status` FOREIGN KEY (`status_id`) REFERENCES `gallery_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,'Art Expo 2025','Modern art exhibition',1,'Colombo','/images/gallery-images/gallary-1.jpg','John Doe','Photographer','https://photographer.com','#FFFFFF','#FF5733','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(2,'Historical Gallery','Artifacts from the 18th century',2,'Kandy','/images/gallery-images/gallary-2.jpg','Jane Smith','Museum','https://museum.com','#F0F0F0','#C70039','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(3,'Photography Showcase','Best of landscape photography',1,'Galle','/images/gallery-images/gallary-3.jpg','Alice Brown','Photographer','https://alicephotos.com','#FFFFFF','#900C3F','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(4,'Sculpture Center','Contemporary sculptures',1,'Negombo','/images/gallery-images/gallary-4.jpg','Bob Lee','Artist','https://boblee.com','#FAFAFA','#33FF57','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(5,'Digital Arts Hub','Digital and 3D art',1,'Colombo','/images/gallery-images/gallary-5.jpg','Clara White','Digital Artist','https://clara.com','#F5F5F5','#3357FF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(6,'Ancient Artifacts','Artifacts from ancient Sri Lanka',1,'Anuradhapura','/images/gallery-images/gallary-6.jpg','Museum','Museum','https://museum.lk','#EDEDED','#FF33A1','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(7,'Local Artists Expo','Showcase for local artists',1,'Matara','/images/gallery-images/gallary-7.jpg','John Doe','Gallery','https://localgallery.com','#FFFFFF','#33FFA5','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(8,'Modern Painting','Modern paintings collection',1,'Colombo','/images/gallery-images/gallary-8.jpg','Sarah Connor','Painter','https://paintings.com','#FFF5F5','#FF5733','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(9,'Photography Contest','Annual photography contest',2,'Kandy','/images/gallery-images/gallary-9.jpg','Alice Brown','Contest','https://contest.com','#F0F0F0','#FF33AA','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(10,'Sculpture Workshop','Hands-on sculpture workshop',1,'Galle','/images/gallery-images/gallary-10.jpg','Bob Lee','Workshop','https://workshop.com','#FAFAFA','#33FFAA','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(11,'Heritage Gallery','Preserving heritage arts',1,'Jaffna','/images/gallery-images/gallary-11.jpg','Jane Smith','Museum','https://heritage.lk','#FFFFFF','#FFAA33','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(12,'Marine Life Exhibit','Underwater photography',1,'Trincomalee','/images/gallery-images/gallary-12.jpg','Alice Brown','Photographer','https://alicephotos.com','#E0FFFF','#33CCFF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(13,'Flora & Fauna','Wildlife photography gallery',1,'Haputale','/images/gallery-images/gallary-13.jpg','John Doe','Photographer','https://wildlife.com','#FFFFFF','#33FFCC','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(14,'Street Art Expo','Urban street art',1,'Colombo','/images/gallery-images/gallary-14.jpg','Clara White','Artist','https://clara.com','#F5F5F5','#FF33FF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(15,'Interactive Art','Interactive installations',2,'Kandy','/images/gallery-images/gallary-15.jpg','Bob Lee','Artist','https://boblee.com','#FAFAFA','#33AAFF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(16,'Sustainable Art','Art using recycled materials',1,'Matara','/images/gallery-images/gallary-16.jpg','Sarah Connor','Artist','https://art.com','#FFFFFF','#33FF33','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(17,'Cultural Artifacts','Artifacts from local culture',1,'Anuradhapura','/images/gallery-images/gallary-17.jpg','Jane Smith','Museum','https://museum.lk','#F0F0F0','#FF3333','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(18,'Experimental Arts','Avant-garde art collection',1,'Colombo','/images/gallery-images/gallary-18.jpg','Clara White','Artist','https://clara.com','#FFFFFF','#9933FF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(19,'Art & Technology','Blend of art and tech',1,'Negombo','/images/gallery-images/gallary-19.jpg','Bob Lee','Artist','https://boblee.com','#FAFAFA','#33FFFF','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(20,'Miniature Art','Miniature models and art',1,'Galle','/images/gallery-images/gallary-20.jpg','Alice Brown','Artist','https://alicephotos.com','#FFFFFF','#FF6633','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(21,'Miniature Art','Miniature models and art',1,'Galle','/images/gallery-images/gallary-21.jpg','Alice Brown','Artist','https://alicephotos.com','#FFFFFF','#FF6633','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(22,'Miniature Art','Miniature models and art',1,'Galle','/images/gallery-images/gallary-22.jpg','Alice Brown','Artist','https://alicephotos.com','#FFFFFF','#FF6633','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(23,'Miniature Art','Miniature models and art',1,'Galle','/images/gallery-images/gallary-23.jpg','Alice Brown','Artist','https://alicephotos.com','#FFFFFF','#FF6633','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL),(24,'Miniature Art','Miniature models and art',1,'Galle','/images/gallery-images/gallary-24.jpg','Alice Brown','Artist','https://alicephotos.com','#FFFFFF','#FF6633','2025-09-24 15:26:56',1,'2025-09-30 04:32:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_status`
--

DROP TABLE IF EXISTS `gallery_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gallery_status_common_status` (`status_id`),
  CONSTRAINT `fk_gallery_status_common_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_status`
--

LOCK TABLES `gallery_status` WRITE;
/*!40000 ALTER TABLE `gallery_status` DISABLE KEYS */;
INSERT INTO `gallery_status` VALUES (1,'Open','Gallery is open for visitors',1,'2025-09-24 15:26:56',1,'2025-09-24 15:26:56',NULL,NULL,NULL),(2,'Under Maintenance','Gallery temporarily closed',2,'2025-09-24 15:26:56',1,'2025-09-24 15:26:56',NULL,NULL,NULL),(3,'Closed','Gallery permanently closed',3,'2025-09-24 15:26:56',1,'2025-09-24 15:26:56',NULL,NULL,NULL);
/*!40000 ALTER TABLE `gallery_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `gender_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`gender_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `gender_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'Male','Male gender',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Female','Female gender',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero_section`
--

DROP TABLE IF EXISTS `hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `hero_section_status_id` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hero_section_status_id` (`hero_section_status_id`),
  CONSTRAINT `hero_section_ibfk_1` FOREIGN KEY (`hero_section_status_id`) REFERENCES `hero_section_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero_section`
--

LOCK TABLES `hero_section` WRITE;
/*!40000 ALTER TABLE `hero_section` DISABLE KEYS */;
INSERT INTO `hero_section` VALUES (1,'slider-1','/images/hero-section-images/kandy-perahara.webp','Welcome to Our','Amazing Island','Discover extraordinary experiences and create unforgettable memories with our premium services','Join Us','/signup','Learn More','/about-us',1,1,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL),(2,'slider-2','/images/hero-section-images/tea-state.png','Explore New','Possibilities','Join thousands of satisfied customers who have transformed their lives with our innovative solutions','Join Us','/signup','Our Tours','/sri-lankan-tours',1,2,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL),(3,'slider-3','/images/hero-section-images/tiger.png','Your Journey','Starts here','Take the first step towards excellence with our comprehensive range of professional services','Join Us','/signup','Our Destiantions','destinations',1,3,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL),(4,'slider-3','/images/hero-section-images/beach.jpg','Your Journey','Starts here','Take the first step towards excellence with our comprehensive range of professional services','Join Us','/signup','Learn More','/about-us',1,4,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL),(5,'slider-3','/images/hero-section-images/kitulgala.webp','Your Journey','Starts here','Take the first step towards excellence with our comprehensive range of professional services','Join Us','/signup','Contact Us','/contact-us',1,5,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL),(6,'slider-3','/images/hero-section-images/sigiriya.jpg','Your Journey','Starts here','Take the first step towards excellence with our comprehensive range of professional services','Join Us','/signup','About Us','/about-us',1,6,'2025-09-19 19:34:01',1,'2025-12-11 16:42:49',NULL,NULL,NULL);
/*!40000 ALTER TABLE `hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero_section_status`
--

DROP TABLE IF EXISTS `hero_section_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hero_section_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `hero_section_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero_section_status`
--

LOCK TABLES `hero_section_status` WRITE;
/*!40000 ALTER TABLE `hero_section_status` DISABLE KEYS */;
INSERT INTO `hero_section_status` VALUES (1,'VISIBLE','Slider is visible on homepage',1,'2025-09-19 19:34:01',1,'2025-09-19 19:34:01',NULL,NULL,NULL),(2,'HIDDEN','Slider is not visible',2,'2025-09-19 19:34:01',1,'2025-09-19 19:34:01',NULL,NULL,NULL);
/*!40000 ALTER TABLE `hero_section_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiries`
--

DROP TABLE IF EXISTS `inquiries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `inquiry_type` varchar(50) DEFAULT NULL,
  `preferred_contact_method` varchar(100) DEFAULT NULL,
  `preferred_destination` varchar(100) DEFAULT NULL,
  `arrival_date` date DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `adults_count` int DEFAULT '1',
  `kids_count` int DEFAULT '0',
  `budget_range` varchar(100) DEFAULT NULL,
  `special_requirements` text,
  `inquiry_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `booking_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `inquiry_status_id` (`inquiry_status_id`),
  KEY `booking_id` (`booking_id`),
  KEY `created_by` (`created_by`),
  KEY `updated_by` (`updated_by`),
  CONSTRAINT `inquiries_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiries_ibfk_2` FOREIGN KEY (`inquiry_status_id`) REFERENCES `inquiry_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiries_ibfk_3` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `inquiries_ibfk_4` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiries_ibfk_5` FOREIGN KEY (`updated_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiries`
--

LOCK TABLES `inquiries` WRITE;
/*!40000 ALTER TABLE `inquiries` DISABLE KEYS */;
INSERT INTO `inquiries` VALUES (1,NULL,'John Doe','Sri Lanka','john.doe@example.com','94234567',NULL,'WHATSAPP','Maldives','2025-02-10','2025-02-15',2,1,NULL,'Looking for a family-friendly resort with airport transfers.',1,'2025-12-14 15:03:44',NULL,'2025-12-14 15:03:44',NULL,NULL),(2,NULL,'','Sri Lanka','john.doe@example.com','94234567',NULL,'WHATSAPP','Maldives','2025-02-10','2025-02-15',2,1,NULL,'Looking for a family-friendly resort with airport transfers.',1,'2025-12-14 15:04:43',NULL,'2025-12-14 15:04:43',NULL,NULL),(3,NULL,'aa','Sri Lanka','john.doe@example.com','94234567',NULL,'WHATSAPP','Maldives','2025-02-10','2025-02-15',2,1,NULL,'Looking for a family-friendly resort with airport transfers.',1,'2025-12-14 15:07:23',NULL,'2025-12-14 15:07:23',NULL,NULL),(4,NULL,'aa','Sri Lanka','john.doe@example.com','94234567',NULL,'WHATSAPP','Maldives',NULL,NULL,2,1,NULL,'Looking for a family-friendly resort with airport transfers.',1,'2025-12-14 15:10:45',NULL,'2025-12-14 15:10:45',NULL,NULL),(5,NULL,'aa','Sri Lanka','john.doe@example.com','94234567',NULL,'WHATSAPP','Maldives',NULL,NULL,2,1,NULL,'Looking for a family-friendly resort with airport transfers.',1,'2025-12-14 15:11:02',NULL,'2025-12-14 15:11:02',NULL,NULL),(6,NULL,'test name','South Africa',NULL,'1123123123',NULL,'CALL',NULL,NULL,NULL,1,0,NULL,NULL,1,'2025-12-14 15:18:00',NULL,'2025-12-14 15:18:00',NULL,NULL),(7,NULL,'test user 2','Sri Lanka','test@example.com','707076043',NULL,'CALL','galle','2025-12-19','2025-12-26',4,2,NULL,'sample description',1,'2025-12-14 15:20:18',NULL,'2025-12-14 15:20:18',NULL,NULL),(8,NULL,'smith','Afghanistan',NULL,'791277213',NULL,'WHATSAPP',NULL,NULL,NULL,1,0,NULL,NULL,1,'2025-12-16 15:35:59',NULL,'2025-12-16 15:35:59',NULL,NULL);
/*!40000 ALTER TABLE `inquiries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_contacts`
--

DROP TABLE IF EXISTS `inquiry_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_contacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `contact_type` varchar(50) DEFAULT NULL,
  `contact_method` varchar(50) DEFAULT NULL,
  `contact_from` int DEFAULT NULL,
  `contact_to` int DEFAULT NULL,
  `message` text,
  `sent_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `contact_from` (`contact_from`),
  KEY `contact_to` (`contact_to`),
  CONSTRAINT `inquiry_contacts_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_contacts_ibfk_2` FOREIGN KEY (`contact_from`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `inquiry_contacts_ibfk_3` FOREIGN KEY (`contact_to`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_contacts`
--

LOCK TABLES `inquiry_contacts` WRITE;
/*!40000 ALTER TABLE `inquiry_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_customer_contact`
--

DROP TABLE IF EXISTS `inquiry_customer_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_customer_contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `contact_method` varchar(50) DEFAULT NULL,
  `contacted_by` int NOT NULL,
  `contact_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `contact_notes` text,
  `next_followup_date` date DEFAULT NULL,
  `contact_result` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `contacted_by` (`contacted_by`),
  CONSTRAINT `inquiry_customer_contact_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_customer_contact_ibfk_2` FOREIGN KEY (`contacted_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_customer_contact`
--

LOCK TABLES `inquiry_customer_contact` WRITE;
/*!40000 ALTER TABLE `inquiry_customer_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_customer_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_customer_responses`
--

DROP TABLE IF EXISTS `inquiry_customer_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_customer_responses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `inquiry_packages_id` int NOT NULL,
  `response_type` enum('accept','reject','request_modification') NOT NULL,
  `customer_notes` text,
  `contact_type` varchar(50) DEFAULT NULL,
  `contact_method` varchar(50) DEFAULT NULL,
  `contact_from` int DEFAULT NULL,
  `contact_to` int DEFAULT NULL,
  `message` text,
  `responded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `inquiry_packages_id` (`inquiry_packages_id`),
  CONSTRAINT `inquiry_customer_responses_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_customer_responses_ibfk_2` FOREIGN KEY (`inquiry_packages_id`) REFERENCES `inquiry_packages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_customer_responses`
--

LOCK TABLES `inquiry_customer_responses` WRITE;
/*!40000 ALTER TABLE `inquiry_customer_responses` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_customer_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_packages`
--

DROP TABLE IF EXISTS `inquiry_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_packages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `inquiry_tours_id` int NOT NULL,
  `package_id` int NOT NULL,
  `valid_until` date DEFAULT NULL,
  `version` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `status` enum('pending','accepted','rejected') DEFAULT 'pending',
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `inquiry_tours_id` (`inquiry_tours_id`),
  KEY `package_id` (`package_id`),
  KEY `created_by` (`created_by`),
  KEY `updated_by` (`updated_by`),
  CONSTRAINT `inquiry_packages_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_packages_ibfk_2` FOREIGN KEY (`inquiry_tours_id`) REFERENCES `inquiry_tours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_packages_ibfk_3` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_packages_ibfk_4` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_packages_ibfk_5` FOREIGN KEY (`updated_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_packages`
--

LOCK TABLES `inquiry_packages` WRITE;
/*!40000 ALTER TABLE `inquiry_packages` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_status`
--

DROP TABLE IF EXISTS `inquiry_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `created_by` (`created_by`),
  KEY `updated_by` (`updated_by`),
  CONSTRAINT `inquiry_status_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `inquiry_status_ibfk_2` FOREIGN KEY (`updated_by`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_status`
--

LOCK TABLES `inquiry_status` WRITE;
/*!40000 ALTER TABLE `inquiry_status` DISABLE KEYS */;
INSERT INTO `inquiry_status` VALUES (1,'REQUESTED','Initial inquiry submitted by customer','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(2,'CONTACTED','Staff has contacted customer for details','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(3,'CREATED','Tour package has been created','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(4,'NOT_CREATED','Unable to create package (no response/unclear requirements)','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(5,'SENT_CUSTOMER','Package sent to customer for review','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(6,'ACCEPTED','Customer accepted the package','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(7,'BOOKED','Customer completed booking','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(8,'COMPLETED','Tour has been completed','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(9,'CANCELLED','Customer cancelled after booking','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(10,'REJECTED','Customer rejected the package','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL),(11,'RE_CREATED','Package modified and recreated','2025-12-14 14:25:11',1,'2025-12-14 14:25:11',NULL);
/*!40000 ALTER TABLE `inquiry_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_status_history`
--

DROP TABLE IF EXISTS `inquiry_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_status_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `old_status_id` int DEFAULT NULL,
  `new_status_id` int NOT NULL,
  `changed_by` int NOT NULL,
  `change_reason` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `old_status_id` (`old_status_id`),
  KEY `new_status_id` (`new_status_id`),
  KEY `changed_by` (`changed_by`),
  CONSTRAINT `inquiry_status_history_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_status_history_ibfk_2` FOREIGN KEY (`old_status_id`) REFERENCES `inquiry_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_status_history_ibfk_3` FOREIGN KEY (`new_status_id`) REFERENCES `inquiry_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_status_history_ibfk_4` FOREIGN KEY (`changed_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_status_history`
--

LOCK TABLES `inquiry_status_history` WRITE;
/*!40000 ALTER TABLE `inquiry_status_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_status_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_tours`
--

DROP TABLE IF EXISTS `inquiry_tours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_tours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inquiry_id` int NOT NULL,
  `tour_id` int NOT NULL,
  `valid_until` date DEFAULT NULL,
  `version` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `status` enum('pending','accepted','rejected') DEFAULT 'pending',
  PRIMARY KEY (`id`),
  KEY `inquiry_id` (`inquiry_id`),
  KEY `tour_id` (`tour_id`),
  KEY `created_by` (`created_by`),
  KEY `updated_by` (`updated_by`),
  CONSTRAINT `inquiry_tours_ibfk_1` FOREIGN KEY (`inquiry_id`) REFERENCES `inquiries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inquiry_tours_ibfk_2` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_tours_ibfk_3` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `inquiry_tours_ibfk_4` FOREIGN KEY (`updated_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_tours`
--

LOCK TABLES `inquiry_tours` WRITE;
/*!40000 ALTER TABLE `inquiry_tours` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_tours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jwt_tokens`
--

DROP TABLE IF EXISTS `jwt_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jwt_tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `token` varchar(512) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip_address` varchar(45) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `revoked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `jwt_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jwt_tokens`
--

LOCK TABLES `jwt_tokens` WRITE;
/*!40000 ALTER TABLE `jwt_tokens` DISABLE KEYS */;
INSERT INTO `jwt_tokens` VALUES (1,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODIyNzk4LCJleHAiOjE3NjM4MjI4Mjh9.L4SC3ai-xK5kYWkk-MceXykItq2Bxa_TO_4X35z-OVU','2025-11-22 14:46:38',NULL,NULL,'2025-11-22 14:47:09',0),(2,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODIzMDAyLCJleHAiOjE3NjM4MjMwMzJ9.CNIqLs4xIO9dAlZRZ7ZzWwqLn1FVEVjBnL6vsS8o55Y','2025-11-22 14:50:02',NULL,NULL,'2025-11-22 14:50:33',0),(3,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODIzMDM1LCJleHAiOjE3NjM4MjMwNjV9.fUB490XKgHupnD5b_CNlLbS0tgYVGEoNi8FVul-I9Ys','2025-11-22 14:50:35',NULL,NULL,'2025-11-22 14:51:06',0),(4,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgwNjgwLCJleHAiOjE3NjM4ODA3MTB9.jZHWaFP173gzeSv2aoeLympgJImbzCnVnz9d0W0vXyM','2025-11-23 06:51:20',NULL,NULL,'2025-11-23 06:51:51',0),(5,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgxNDY4LCJleHAiOjE3NjM4ODE0OTh9.9T464raQlQGmKmKu91Rgo76WDobrkC3Ly6zneae0gYk','2025-11-23 07:04:28',NULL,NULL,'2025-11-23 07:04:59',0),(6,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgxNTE0LCJleHAiOjE3NjM4ODE1NDR9.AwtO3Q_TaAE3i-wbHBsGlMoaGMlmYG4xcE7VKKGpW40','2025-11-23 07:05:14',NULL,NULL,'2025-11-23 07:05:44',0),(7,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgyMTc0LCJleHAiOjE3NjM4ODIyMDR9.9Xdh-C1Ba2jzKRyoBOppTInN0tgkOio36Iw9mDBx7Io','2025-11-23 07:16:14',NULL,NULL,'2025-11-23 07:16:44',0),(8,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgyMjE2LCJleHAiOjE3NjM4ODIyNDZ9.XSfwKtzTmQxZhs3se4ueDGUwa8c0Z3E-b6uWrIYNOmM','2025-11-23 07:16:56',NULL,NULL,'2025-11-23 07:17:27',0),(9,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgyMjI0LCJleHAiOjE3NjM4ODIyNTR9.x9HyUPGJPQlLBxDsqznsQVK5nQD_z4-a16c5h3cEvH4','2025-11-23 07:17:04',NULL,NULL,'2025-11-23 07:17:34',0),(10,6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1ZGlsc2hhbiIsImlhdCI6MTc2Mzg4MjYxOSwiZXhwIjoxNzYzODgyNjQ5fQ.o7J9gqIFYTbxJfFq1v5xnf9dVT4NhIgsaScZU6Sue-s','2025-11-23 07:23:39',NULL,NULL,'2025-11-23 07:24:09',0),(11,6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1ZGlsc2hhbiIsImlhdCI6MTc2Mzg4MjYzNSwiZXhwIjoxNzYzODgyNjY1fQ.qJ4ap5tjsN_FtG_S3SciY2rr1QMAgX1L0waQyXcbJQs','2025-11-23 07:23:55',NULL,NULL,'2025-11-23 07:24:25',0),(12,6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1ZGlsc2hhbiIsImlhdCI6MTc2Mzg4MjY2NSwiZXhwIjoxNzYzODgyNjk1fQ.2_CuK6RnILCCvoNl5AksQz6NC-0lO5DeJIJZ7tuMOs8','2025-11-23 07:24:25',NULL,NULL,'2025-11-23 07:24:55',0),(13,6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1ZGlsc2hhbiIsImlhdCI6MTc2Mzg4MjY5NSwiZXhwIjoxNzYzODgyNzI1fQ.c9P8JZOZtur1otXFzYmcpVbxLAtd_Cg1DZIPq_8t_uM','2025-11-23 07:24:55',NULL,NULL,'2025-11-23 07:25:26',0),(14,6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1ZGlsc2hhbiIsImlhdCI6MTc2Mzg4Mjc0NSwiZXhwIjoxNzYzODgyNzc1fQ.deq6whx2fhhw6RvIksSy3d5bTYYq1dM2nTlT3gfmFCQ','2025-11-23 07:25:45',NULL,NULL,'2025-11-23 07:26:16',0),(15,8,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXMiLCJpYXQiOjE3NjM4ODMxNjcsImV4cCI6MTc2Mzg4MzE5N30.HTwnehEsnzeiSTNEtx3CvWHlrZTcmvCKsxH1KW7WrbI','2025-11-23 07:32:47',NULL,NULL,'2025-11-23 07:33:18',0),(16,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzMzk1LCJleHAiOjE3NjM4ODM0MjV9.dO_Vwplv-yfSYPAk2xOrQGWSCeWTxkV8BAaWRbP-af0','2025-11-23 07:36:35',NULL,NULL,'2025-11-23 07:37:06',0),(17,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzNTA4LCJleHAiOjE3NjM4ODM1Mzh9.SD-yaDKguZYh7llP1Y2U6yQvUVZLQ9QGO17nMoZPb_g','2025-11-23 07:38:28',NULL,NULL,'2025-11-23 07:38:58',0),(18,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzNTQxLCJleHAiOjE3NjM4ODM1NzF9.rypxzJG_gYtK0vkj_XI34uhrJxgtCSh5Gb-I99-d9hg','2025-11-23 07:39:01',NULL,NULL,'2025-11-23 07:39:31',0),(19,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzNjE5LCJleHAiOjE3NjM4ODM2NDl9.XCcpWe15-zIvQFCKLPYdQ9dfXABcRkmueOe-7vYhs0E','2025-11-23 07:40:19',NULL,NULL,'2025-11-23 07:40:49',0),(20,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzNjc3LCJleHAiOjE3NjM4ODM3MDd9.WK6pUU6Ovi8bG1l38rJ3X11f7HaNInmtEQZvpLVQFio','2025-11-23 07:41:17',NULL,NULL,'2025-11-23 07:41:48',0),(21,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzNzIxLCJleHAiOjE3NjM4ODM3NTF9.EJobUOT2K0oRW6fIhSThMM1G2EBBW9rdTqboUazMoCM','2025-11-23 07:42:01',NULL,NULL,'2025-11-23 07:42:31',0),(22,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzODgzODQyLCJleHAiOjE3NjM4ODM4NzJ9.qf5mjtGM2N2aiHzHprOi2QlHn543NiAnHchYCmZrvLA','2025-11-23 07:44:02',NULL,NULL,'2025-11-23 07:44:32',0),(23,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2MTg0LCJleHAiOjE3NjM5ODYyMTR9.-pCBFj3PE6rmdKX8b0kT83HZp-JOZKX8OpF4JhtkZvA','2025-11-24 12:09:44',NULL,NULL,'2025-11-24 12:10:15',0),(24,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2MjE0LCJleHAiOjE3NjM5ODYyNDR9.WKomZis5JKMk48Aq6DvRfNlMDfUkzAUlW9BUF7UtKRE','2025-11-24 12:10:14',NULL,NULL,'2025-11-24 12:10:45',0),(25,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2MzY3LCJleHAiOjE3NjM5ODY2Njd9.bmMcJjUgtvVPkT9ESpDHhrB1JuEWK-eVoRffmmONc7k','2025-11-24 12:12:47',NULL,NULL,'2025-11-24 12:17:47',0),(26,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2NjUyLCJleHAiOjE3NjM5ODY5NTJ9.FZKRRuyHMlZblRUqbbFo7aCrP7oBfkEAMrqP1k2-vz8','2025-11-24 12:17:32',NULL,NULL,'2025-11-24 12:22:32',0),(27,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2ODY2LCJleHAiOjE3NjM5ODcxNjZ9.Kol85suF99_RngIW6Nzo0pRX2TObmhH09LNDpBAQLfY','2025-11-24 12:21:06',NULL,NULL,'2025-11-24 12:26:07',0),(28,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg2OTY3LCJleHAiOjE3NjM5ODcyNjd9.Cfz1jh3BKupPKWOvsKMfqFXJcWmtF8cxHTxfWt5Fdkw','2025-11-24 12:22:47',NULL,NULL,'2025-11-24 12:27:48',0),(29,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg3MjY3LCJleHAiOjE3NjM5ODc1Njd9.RcpaNKbpHEYAqMijiGo7xnl20s3gOB44E6e5o8brNLM','2025-11-24 12:27:47',NULL,NULL,'2025-11-24 12:32:48',0),(30,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg3NTExLCJleHAiOjE3NjM5ODc4MTF9.VQDvteHOXxOIl0--YcsY-X_RU9SD0xIeEdh50kC3taY','2025-11-24 12:31:51',NULL,NULL,'2025-11-24 12:36:51',0),(31,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg4MjUyLCJleHAiOjE3NjM5ODg1NTJ9.HoPMklyfPl_KL8Xev92lX0R_R5iF0OpvchjtLN4BSLA','2025-11-24 12:44:12',NULL,NULL,'2025-11-24 12:49:13',0),(32,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg4NjQxLCJleHAiOjE3NjM5ODg5NDF9.BuJVXktq0eLRLW4KWeNaue17sabBrfOYZzI694wutWY','2025-11-24 12:50:41',NULL,NULL,'2025-11-24 12:55:41',0),(33,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg5MTQ2LCJleHAiOjE3NjM5ODk0NDZ9.cMkvSTZLNLgZcDUlvaxBFe5HFpH03yzWLOAlE9LvYUQ','2025-11-24 12:59:06',NULL,NULL,'2025-11-24 13:04:07',0),(34,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg5NDUxLCJleHAiOjE3NjM5ODk3NTF9.kyodlKMkWM6C8fdO8sqn2r9ezBf0UszKl_BQ3R-bXSA','2025-11-24 13:04:11',NULL,NULL,'2025-11-24 13:09:11',0),(35,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg5NDUxLCJleHAiOjE3NjM5ODk3NTF9.kyodlKMkWM6C8fdO8sqn2r9ezBf0UszKl_BQ3R-bXSA','2025-11-24 13:04:11',NULL,NULL,'2025-11-24 13:09:11',0),(36,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTg5NDUxLCJleHAiOjE3NjM5ODk3NTF9.kyodlKMkWM6C8fdO8sqn2r9ezBf0UszKl_BQ3R-bXSA','2025-11-24 13:04:11',NULL,NULL,'2025-11-24 13:09:11',0),(37,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTkyNDY3LCJleHAiOjE3NjM5OTI3Njd9.Is6W_vJSGW56qvW3ywgVrZy67p6OTyxkz8SFI7qRbEk','2025-11-24 13:54:27',NULL,NULL,'2025-11-24 13:59:28',0),(38,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk0MjkzLCJleHAiOjE3NjM5OTQ1OTN9.Uo1XNd0fjKk_Ah74wWJkPS8iKHmsKVl8fgLF0Rs3zko','2025-11-24 14:24:53',NULL,NULL,'2025-11-24 14:29:54',0),(39,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk0ODU4LCJleHAiOjE3NjM5OTUxNTh9.0smZOSj_7DmXw6M-3IAmOgpOsIjxj7gsKM2IvaP84Y0','2025-11-24 14:34:18',NULL,NULL,'2025-11-24 14:39:19',0),(40,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk1NTIzLCJleHAiOjE3NjM5OTU4MjN9.bLrtGxAmzn7OGjZ3tYuCvd4fRws7DNqXIFGn1_D3qls','2025-11-24 14:45:23',NULL,NULL,'2025-11-24 14:50:23',0),(41,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk2ODM2LCJleHAiOjE3NjM5OTcxMzZ9.-AWPZp7RjTyItzXXOHNfTRHGhho6XEj-hjPWNlUIuMg','2025-11-24 15:07:16',NULL,NULL,'2025-11-24 15:12:17',0),(42,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk3OTkzLCJleHAiOjE3NjM5OTgyOTN9.0nobFcoHpTroblIilPwoq8DCxvP_SfG4OTU4v5WhR2o','2025-11-24 15:26:33',NULL,NULL,'2025-11-24 15:31:34',0),(43,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk5Mzk2LCJleHAiOjE3NjM5OTk2OTZ9.W5SBEhDBnT8xJ_DXVEu4Fw3KdroCnyZICSuIllfjPvs','2025-11-24 15:49:56',NULL,NULL,'2025-11-24 15:54:57',0),(44,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk5NzA1LCJleHAiOjE3NjQwMDAwMDV9.fmmrdHTqUGnwBa8Cb1cNiTbRuSMBEYwFWIwVwzZ4b68','2025-11-24 15:55:05',NULL,NULL,'2025-11-24 16:00:05',0),(45,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzYzOTk5NzA1LCJleHAiOjE3NjQwMDAwMDV9.fmmrdHTqUGnwBa8Cb1cNiTbRuSMBEYwFWIwVwzZ4b68','2025-11-24 15:55:05',NULL,NULL,'2025-11-24 16:00:05',0),(46,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAwNDY2LCJleHAiOjE3NjQwMDA3NjZ9.mTuVeDOU_rGrbYguULJtxNkmpjdZehWxUrElB2zJxV0','2025-11-24 16:07:46',NULL,NULL,'2025-11-24 16:12:47',0),(47,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAwNzcwLCJleHAiOjE3NjQwMDEwNzB9.KqiwOGWGr1uSMOe0gK78hpEJj-98girMkdt7xt6_Kik','2025-11-24 16:12:50',NULL,NULL,'2025-11-24 16:17:51',0),(48,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAwNzcwLCJleHAiOjE3NjQwMDEwNzB9.KqiwOGWGr1uSMOe0gK78hpEJj-98girMkdt7xt6_Kik','2025-11-24 16:12:50',NULL,NULL,'2025-11-24 16:17:51',0),(49,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAxNzgxLCJleHAiOjE3NjQwMDIwODF9.p9119uyAJYHn2S6TCVVNCoR1B8tj_uAIZ6VO27gGGFA','2025-11-24 16:29:41',NULL,NULL,'2025-11-24 16:34:41',0),(50,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAyMDI2LCJleHAiOjE3NjQwMDIzMjZ9.Y8hwgqd_fvFLlKJBkKx4MAISEOWmAKo_6UNkjW9Wfgc','2025-11-24 16:33:46',NULL,NULL,'2025-11-24 16:38:47',0),(51,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAzNTQ1LCJleHAiOjE3NjQwMDM4NDV9.vlGuKyXe4zucon-QK6A7eHBxbkg1LGU8wYXX8y_x-08','2025-11-24 16:59:05',NULL,NULL,'2025-11-24 17:04:05',0),(52,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDAzOTQxLCJleHAiOjE3NjQwMDQyNDF9.y8_5IXv1806t3LYIL1O_uNt04WVQktm2d3Cos1W1DBA','2025-11-24 17:05:41',NULL,NULL,'2025-11-24 17:10:41',0),(53,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDA0MDQ2LCJleHAiOjE3NjQwMDQzNDZ9.mKZTYjgFaV0bmj8FB6AMbhCYrleEYyR4ump6hzN8KhQ','2025-11-24 17:07:26',NULL,NULL,'2025-11-24 17:12:26',0),(54,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg1OTk2LCJleHAiOjE3NjQwODYyOTZ9.FAHvpEjQpY_Da0UjKH6CfEtvtVFHUVED04cQJqaV6TY','2025-11-25 15:53:16',NULL,NULL,'2025-11-25 15:58:16',0),(55,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg3NDEyLCJleHAiOjE3NjQwODc3MTJ9.iKyY-L9bSXN1xJsjp_4uYancQzBaDq6Gtw4wveQHbbc','2025-11-25 16:16:52',NULL,NULL,'2025-11-25 16:21:52',0),(56,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg3OTcwLCJleHAiOjE3NjQwODgyNzB9.3EhVlxdya5tkDc27eI3Q404sAWYsY8RTIiMkVPmdcsk','2025-11-25 16:26:10',NULL,NULL,'2025-11-25 16:31:11',0),(57,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg4MjAzLCJleHAiOjE3NjQwODg1MDN9.YgdDZKeaGmnPQQ7_HNY9UwT00RIHtBTqXtdKY0fCzUY','2025-11-25 16:30:03',NULL,NULL,'2025-11-25 16:35:03',0),(58,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg4NjYzLCJleHAiOjE3NjQwODg5NjN9.jvi-yp2xeiVp883spI7-z7aaSZ4OdYMk-RSkj2XJ01E','2025-11-25 16:37:43',NULL,NULL,'2025-11-25 16:42:43',0),(59,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MDg4NjYzLCJleHAiOjE3NjQwODg5NjN9.jvi-yp2xeiVp883spI7-z7aaSZ4OdYMk-RSkj2XJ01E','2025-11-25 16:37:43',NULL,NULL,'2025-11-25 16:42:43',0),(60,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTI5NDYxLCJleHAiOjE3NjQxMjk3NjF9.APJez0tjGjiIfyn9W5PYLMP9vYUX1UG679p8ucyP9oo','2025-11-26 03:57:41',NULL,NULL,'2025-11-26 04:02:41',0),(61,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQzMDEyLCJleHAiOjE3NjQxNDMzMTJ9.MlWCO6207AnUfYzTPmBkECtnDt1sQUD9Di2WdbHMKF0','2025-11-26 07:43:32',NULL,NULL,'2025-11-26 07:48:33',0),(62,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQzMTMwLCJleHAiOjE3NjQxNDM0MzB9.H9y9T218eVNTDou7JhJ9vkDgQXh8grnMC6I049OvgWE','2025-11-26 07:45:30',NULL,NULL,'2025-11-26 07:50:31',0),(63,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQzNzQ0LCJleHAiOjE3NjQxNDQwNDR9.jii_y7BaFdJSrRHdenoGF1x5rQ-KeEhMg-AXozcbA5M','2025-11-26 07:55:44',NULL,NULL,'2025-11-26 08:00:45',0),(64,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQzODM2LCJleHAiOjE3NjQxNDQxMzZ9.f18kijZv0u_9Ig29qpnsKxhSkHMvuejrcEtdWNYm7Wg','2025-11-26 07:57:16',NULL,NULL,'2025-11-26 08:02:16',0),(65,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQ0MTM4LCJleHAiOjE3NjQxNDQ0Mzh9.MPT37hJ_m1o9VtewHX14SAxJOhaMMqsvLvbHtwgmwmI','2025-11-26 08:02:18',NULL,NULL,'2025-11-26 08:07:19',0),(66,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQ0MTQ5LCJleHAiOjE3NjQxNDQ0NDl9.l9wjch6nldL38-GZ2UJYQPsaXCFn9RfNxNTkZmqpvYM','2025-11-26 08:02:29',NULL,NULL,'2025-11-26 08:07:29',0),(67,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTQ0MTc3LCJleHAiOjE3NjQxNDQ0Nzd9.YKAv-zw4WFXnVAQtoRrG0fycSZyZVnwg5GSnEWfwPKQ','2025-11-26 08:02:57',NULL,NULL,'2025-11-26 08:07:57',0),(68,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTYyNzUwLCJleHAiOjE3NjQxNjMwNTB9.zfNt8K6Xamn-ZznhvDqE-C3IU6KwbYVsYhcS7Ln3peM','2025-11-26 13:12:30',NULL,NULL,'2025-11-26 13:17:30',0),(69,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTYzMDU3LCJleHAiOjE3NjQxNjMzNTd9.Ot_kplHvORDuwlpFpru_634ANmUp1W6m2mlqQv385nw','2025-11-26 13:17:37',NULL,NULL,'2025-11-26 13:22:38',0),(70,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTYzMDU3LCJleHAiOjE3NjQxNjMzNTd9.Ot_kplHvORDuwlpFpru_634ANmUp1W6m2mlqQv385nw','2025-11-26 13:17:37',NULL,NULL,'2025-11-26 13:22:38',0),(71,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTYzMDU3LCJleHAiOjE3NjQxNjMzNTd9.Ot_kplHvORDuwlpFpru_634ANmUp1W6m2mlqQv385nw','2025-11-26 13:17:37',NULL,NULL,'2025-11-26 13:22:38',0),(72,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTYzMDU3LCJleHAiOjE3NjQxNjMzNTd9.Ot_kplHvORDuwlpFpru_634ANmUp1W6m2mlqQv385nw','2025-11-26 13:17:37',NULL,NULL,'2025-11-26 13:22:38',0),(73,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MTY0MDU4LCJleHAiOjE3NjQxNjQzNTh9.uT4-OTg_9KCTY_eihavRZtBt8reXCDHQQhFjGgOFn2Y','2025-11-26 13:34:18',NULL,NULL,'2025-11-26 13:39:18',0),(74,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzIyMzIyLCJleHAiOjE3NjQzMjI2MjJ9.pcpeOF92GANuqNVt2PvbTva0OipiTCM7WDyTz-kyj7o','2025-11-28 09:32:02',NULL,NULL,'2025-11-28 09:37:02',0),(75,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzI0NzI3LCJleHAiOjE3NjQzMjUwMjd9.cnosgywoHlSCOjJ3KDYHhrROkxlIwcXKQJN5u_8R82Y','2025-11-28 10:12:07',NULL,NULL,'2025-11-28 10:17:07',0),(76,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzI2OTY5LCJleHAiOjE3NjQzMjcyNjl9.-DbilJjAKKEoHY4nFYjcqO77OL-TR-zmgW4cpo-Uc44','2025-11-28 10:49:29',NULL,NULL,'2025-11-28 10:54:30',0),(77,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzI4OTI0LCJleHAiOjE3NjQ0MTUzMjR9.-KZKhmcev68gG5Rxf0jJGUILcx2HpmSXqGrcb0OrvQI','2025-11-28 11:22:04',NULL,NULL,'2025-11-29 11:22:05',0),(78,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQyOTM2LCJleHAiOjE3NjQ0MjkzMzZ9.tWsrIuyeF0nx2FxpsKtnNZSNEylWzonvPqez6aP98wg','2025-11-28 15:15:36',NULL,NULL,'2025-11-29 15:15:36',0),(79,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ1MDYzLCJleHAiOjE3NjQ0MzE0NjN9.sXOpFo6fwzb1DpQkexoqhGXIVPTsmkx4yLC-8Qh2RtY','2025-11-28 15:51:03',NULL,NULL,'2025-11-29 15:51:03',0),(80,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ1MjQxLCJleHAiOjE3NjQ0MzE2NDF9.yDteeK4o7N6DHNWB7yNtXr7qHmLEZqiJqkrZTMVh7Gk','2025-11-28 15:54:01',NULL,NULL,'2025-11-29 15:54:01',0),(81,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ2MDA3LCJleHAiOjE3NjQ0MzI0MDd9.6JMptvOLPM4jqwdZClM2MsSAp_EkK6OjCg17thcjj4U','2025-11-28 16:06:47',NULL,NULL,'2025-11-29 16:06:47',0),(82,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ2ODkwLCJleHAiOjE3NjQ0MzMyOTB9.RNIpWM5vRfKZ3E8SIWBT4azC3joWD0iYnC82yyjTHzk','2025-11-28 16:21:30',NULL,NULL,'2025-11-29 16:21:30',0),(83,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ2OTE5LCJleHAiOjE3NjQ0MzMzMTl9.J4uwvMLQasVhQXuDese6QhanntYEb7gDVHQ8ipMLYpw','2025-11-28 16:21:59',NULL,NULL,'2025-11-29 16:22:00',0),(84,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzQ5NTUxLCJleHAiOjE3NjQ0MzU5NTF9.vWpAXu_HfJp1MoIn6w6p6ipstQB8IdI6vvocijZbaUY','2025-11-28 17:05:51',NULL,NULL,'2025-11-29 17:05:51',0),(85,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzUyMTUzLCJleHAiOjE3NjQ0Mzg1NTN9.-tT6fu4-9IaGQy0RqxVpV9BT-RIvfKQ0XfneHb41GZg','2025-11-28 17:49:13',NULL,NULL,'2025-11-29 17:49:13',0),(86,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzUyMTc5LCJleHAiOjE3NjQ0Mzg1Nzl9.REz1Ha81u_E3ThtcI5pWz7nfdPRvYNKQHJVS0PLIvaw','2025-11-28 17:49:39',NULL,NULL,'2025-11-29 17:49:40',0),(87,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzU1OTY3LCJleHAiOjE3NjQ0NDIzNjd9.NefwK2ZACIDe-A-r9KfN9ivOhEhP6soUY_gG9Y5UZbw','2025-11-28 18:52:48',NULL,NULL,'2025-11-29 18:52:48',0),(88,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzU2MDEwLCJleHAiOjE3NjQ0NDI0MTB9.7R5dMEPXUi8PGYS14Zafs_kpzW3FvE3F5_NybI0D2rM','2025-11-28 18:53:30',NULL,NULL,'2025-11-29 18:53:30',0),(89,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Mzg2NzE3LCJleHAiOjE3NjQ0NzMxMTd9.WhA9M7MqpCRJVP6jJpIFVfNGmR6e1QBsCHtD4FijoYA','2025-11-29 03:25:17',NULL,NULL,'2025-11-30 03:25:18',0),(90,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Mzg3NjE1LCJleHAiOjE3NjQ0NzQwMTV9.fysSruXUXA-f9r5nDwLEjWe3-nRScd7Q48G83Oodpp4','2025-11-29 03:40:15',NULL,NULL,'2025-11-30 03:40:16',0),(91,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Mzg4NDA4LCJleHAiOjE3NjQ0NzQ4MDh9.ZTRjzr8eafVC8l7ELE-nUeYPp5J26HdINKxHfH83FSo','2025-11-29 03:53:28',NULL,NULL,'2025-11-30 03:53:29',0),(92,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Mzg4NzA4LCJleHAiOjE3NjQ0NzUxMDh9.jNK5Gw_gHaC6lkXTw8SdJ9-3KBiPLO4S2qTPS2xG_7E','2025-11-29 03:58:28',NULL,NULL,'2025-11-30 03:58:29',0),(93,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzkxMTcwLCJleHAiOjE3NjQ0Nzc1NzB9.nLO47aZLDtATKdUGeyFT8-L_PLLjqC3i4_Xmtugfw0E','2025-11-29 04:39:30',NULL,NULL,'2025-11-30 04:39:31',0),(94,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzkyNjM5LCJleHAiOjE3NjQ0NzkwMzl9.NRA2lmny0kYnpyN9s9voyU2-blXvAKOOi0IIpGRwvho','2025-11-29 05:03:59',NULL,NULL,'2025-11-30 05:04:00',0),(95,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0MzkyNjg3LCJleHAiOjE3NjQ0NzkwODd9.w-_niSNeTieCTWT9QipmGm2mTQKsmyeZJkeXFww1M68','2025-11-29 05:04:47',NULL,NULL,'2025-11-30 05:04:48',0),(96,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NDA0NTg4LCJleHAiOjE3NjQ0OTA5ODh9.iXCwOyixE8BjsUcGuaFNwanDOT5MTj5XF3aWVPmSqrs','2025-11-29 08:23:08',NULL,NULL,'2025-11-30 08:23:09',0),(97,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NDA1MjU4LCJleHAiOjE3NjQ0OTE2NTh9.FZTjNIM25uNvn82z14-ktpNyp-SKDrhJZkWQla0-WyE','2025-11-29 08:34:18',NULL,NULL,'2025-11-30 08:34:18',0),(98,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NDA1NjAxLCJleHAiOjE3NjQ0OTIwMDF9.5dmF4RNo8ylvLtKcENuSxOENxyE8st58By8xLe0iIrY','2025-11-29 08:40:01',NULL,NULL,'2025-11-30 08:40:01',0),(99,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NDc3OTMwLCJleHAiOjE3NjQ1NjQzMzB9.dEKa-k-_7SIjkPd97IJsgrOmoFUelWGoDYwzu-vHn8U','2025-11-30 04:45:30',NULL,NULL,'2025-12-01 04:45:30',0),(100,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTIzOTY1LCJleHAiOjE3NjQ2MTAzNjV9.HKt9UQRh19z1D19y02G-Xf1rBE3gSmr8u7uG-1Q3Zts','2025-11-30 17:32:45',NULL,NULL,'2025-12-01 17:32:46',0),(101,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTI2OTcwLCJleHAiOjE3NjQ2MTMzNzB9.RYlkEc8G-38Ck2grzYwXjojEnzcA-HYEmLmXR7U66iU','2025-11-30 18:22:50',NULL,NULL,'2025-12-01 18:22:51',0),(102,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTI3MzYxLCJleHAiOjE3NjQ2MTM3NjF9.ZS4Y9dj7lIgiOQbz5LzXeV3A9AxHi-eLTsDT2VPm-Uc','2025-11-30 18:29:21',NULL,NULL,'2025-12-01 18:29:22',0),(103,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTU4NTkwLCJleHAiOjE3NjQ2NDQ5OTB9.k_vMbyk5DHzlTASCz1z37yCocsbJAr2n-NQ35dp2434','2025-12-01 03:09:50',NULL,NULL,'2025-12-02 03:09:51',0),(104,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTYyMzEzLCJleHAiOjE3NjQ2NDg3MTN9.snHynIKpLt4m3pm8_5KcsUxTIz9JZfbBzWk344LS7_g','2025-12-01 04:11:53',NULL,NULL,'2025-12-02 04:11:53',0),(105,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTYyMzc3LCJleHAiOjE3NjQ2NDg3Nzd9.ZXDPBq1wRih2qtIywV8kwo_44tVZfyrcX0Fd7J1M_Dc','2025-12-01 04:12:57',NULL,NULL,'2025-12-02 04:12:57',0),(106,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTYzMTIyLCJleHAiOjE3NjQ2NDk1MjJ9.u50UcRoeNcow5UIUoUepupeIckMgqeMg0df8Z0VIct4','2025-12-01 04:25:22',NULL,NULL,'2025-12-02 04:25:23',0),(107,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY0MTc2LCJleHAiOjE3NjQ2NTA1NzZ9.ZzIz4Y0q7AlhPhpq7nW5zuXzf1PSwBASy53iMMV2O5k','2025-12-01 04:42:56',NULL,NULL,'2025-12-02 04:42:56',0),(108,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY0Mjk3LCJleHAiOjE3NjQ2NTA2OTd9.W9Aw8v9JcLgDt-Z0yQndN6TvQ93YGXw8xO3jzkvXFMY','2025-12-01 04:44:57',NULL,NULL,'2025-12-02 04:44:58',0),(109,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY1NjA5LCJleHAiOjE3NjQ2NTIwMDl9.I8Y-WQckS_-Oi0D37ZomA_F5QM5tYM5Jyi_QPMh23Ec','2025-12-01 05:06:49',NULL,NULL,'2025-12-02 05:06:49',0),(110,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY1OTg3LCJleHAiOjE3NjQ2NTIzODd9.cCuubpqsZE9-b94-qMdGX6XuKgKIBCygVzuEtMPNvX8','2025-12-01 05:13:07',NULL,NULL,'2025-12-02 05:13:07',0),(111,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY3OTk2LCJleHAiOjE3NjQ2NTQzOTZ9.SzqpKXoyAVCcYK_OneUPP9ube6oMj_NR6ReQU1sPO-M','2025-12-01 05:46:36',NULL,NULL,'2025-12-02 05:46:36',0),(112,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NTY4MTA3LCJleHAiOjE3NjQ2NTQ1MDd9.CGH3F7eMFEkh99RAAiuqHjIVLnrcKU6pNd2dlPm-C4I','2025-12-01 05:48:27',NULL,NULL,'2025-12-02 05:48:28',0),(113,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjAyMjY3LCJleHAiOjE3NjQ2ODg2Njd9.RQjPTeXrHfO2Z5rsUV0GcStf2zO7aEPNlShuUB2S9bQ','2025-12-01 15:17:47',NULL,NULL,'2025-12-02 15:17:48',0),(114,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjAyNDk5LCJleHAiOjE3NjQ2ODg4OTl9.13rl07_-fEz9Ang8kqArouK3R5yP_FxJGZ_Q9EE67Wk','2025-12-01 15:21:39',NULL,NULL,'2025-12-02 15:21:39',0),(115,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjAzNTQ4LCJleHAiOjE3NjQ2ODk5NDh9.6U4kWPM3ieOvDaSbP5u7uC2cyTIKrPCuJqE8uLz3dL4','2025-12-01 15:39:08',NULL,NULL,'2025-12-02 15:39:09',0),(116,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjAzNTkzLCJleHAiOjE3NjQ2ODk5OTN9.hSCdhuVMJ1CRkh5TdzgqQacvXGFF3ja-R7OFbtaamfA','2025-12-01 15:39:53',NULL,NULL,'2025-12-02 15:39:53',0),(117,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjA1MTY5LCJleHAiOjE3NjQ2OTE1Njl9.RG6ylhdtmwrbNQuKQ7KmonSOJxKwqPCwW-Z1t4-HuKs','2025-12-01 16:06:09',NULL,NULL,'2025-12-02 16:06:09',0),(118,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0NjA2NDc5LCJleHAiOjE3NjQ2OTI4Nzl9.gQp42asASu6NoqiqbKAbJlNuNi4hB6NkRMRR2cHyBGE','2025-12-01 16:27:59',NULL,NULL,'2025-12-02 16:27:59',0),(119,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc1MjIyLCJleHAiOjE3NjQ3NjE2MjJ9.8ZWQHjSwKpH6_CfoM0PS_F_0_ceJ1oa-CImNRlFr50k','2025-12-02 11:33:42',NULL,NULL,'2025-12-03 11:33:43',0),(120,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc4NTkxLCJleHAiOjE3NjQ3NjQ5OTF9.MGWwJ46zPERGvf-tiXHlJAYGkQDwLbrgWC6mdJkYmTw','2025-12-02 12:29:51',NULL,NULL,'2025-12-03 12:29:51',0),(121,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc4NjM3LCJleHAiOjE3NjQ3NjUwMzd9.edQUvg9Ln8T0vJ4fMy8pq1FHuuCDtEf-1K_0WEaa1vs','2025-12-02 12:30:37',NULL,NULL,'2025-12-03 12:30:38',0),(122,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc5MTU2LCJleHAiOjE3NjQ3NjU1NTZ9.LaC1bLdaCgWccu2E5pg00dAerL65M-hgl3h0AlP-KvI','2025-12-02 12:39:16',NULL,NULL,'2025-12-03 12:39:17',0),(123,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc5MzQ0LCJleHAiOjE3NjQ3NjU3NDR9.BBLGYkCiVhsyJmxDTt095w4ko-CmSo8kqa7Qu8Mn5qo','2025-12-02 12:42:24',NULL,NULL,'2025-12-03 12:42:24',0),(124,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njc5MzU1LCJleHAiOjE3NjQ3NjU3NTV9.iaGHGeXNvnqYcGk86GPU2xJyvaS-O8H_5x5SVVt4IYg','2025-12-02 12:42:35',NULL,NULL,'2025-12-03 12:42:36',0),(125,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njg3NjY4LCJleHAiOjE3NjQ3NzQwNjh9.JrNruSEkEIGtGJbwnUMoB8MC_nCDfbUi_owOPTM8z_k','2025-12-02 15:01:08',NULL,NULL,'2025-12-03 15:01:09',0),(126,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njg4MzE5LCJleHAiOjE3NjQ3NzQ3MTl9.XArRwgOA1mX1NPPi5dJ4fcUp8mY78E7EsF3sxn3_EKA','2025-12-02 15:11:59',NULL,NULL,'2025-12-03 15:11:59',0),(127,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY0Njk2MTQ4LCJleHAiOjE3NjQ3ODI1NDh9.ou55nFIQgeM3HGZeSXBMdVVjGIWWfmrFj48J_7CfMNU','2025-12-02 17:22:28',NULL,NULL,'2025-12-03 17:22:29',0),(128,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDI2Njk4LCJleHAiOjE3NjU1MTMwOTh9.tOyoqf-gH1memTf9Yl0bZrAkmekL6G23LeyvRT5SxeY','2025-12-11 04:18:19',NULL,NULL,'2025-12-12 04:18:19',0),(129,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDQzODY5LCJleHAiOjE3NjU1MzAyNjl9._gFHZMs7jCeeEOOxyqkhmnYna1TqRCi3SeuHwlx0Dms','2025-12-11 09:04:29',NULL,NULL,'2025-12-12 09:04:30',0),(130,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDQ0NTg3LCJleHAiOjE3NjU1MzA5ODd9._8U5aX4lSO0hQTKQ5js60dqoYGR71vzF5Vw4c93ycTw','2025-12-11 09:16:27',NULL,NULL,'2025-12-12 09:16:27',0),(131,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDQ1MTc0LCJleHAiOjE3NjU1MzE1NzR9.bXYffTy5xXLjPeBL59BvClEOBZ9SrHIsQ_-aOQ_oqGc','2025-12-11 09:26:14',NULL,NULL,'2025-12-12 09:26:14',0),(132,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDQ1ODY2LCJleHAiOjE3NjU1MzIyNjZ9.IVbhzkEB6t4NIZWaOEbKCNA5nTi7d9taE4e23deJwMU','2025-12-11 09:37:46',NULL,NULL,'2025-12-12 09:37:46',0),(133,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDU3OTE1LCJleHAiOjE3NjU1NDQzMTV9.aAW4jbTHTwKt4dmtbzzP-YSFpaBH4G0EJtuEbRvrBlY','2025-12-11 12:58:35',NULL,NULL,'2025-12-12 12:58:36',0),(134,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NDY1NjU3LCJleHAiOjE3NjU1NTIwNTd9.dwPxnOjpAlb7IWZfCflMlvQWCj9WboXpR9c-L-qOsqc','2025-12-11 15:07:37',NULL,NULL,'2025-12-12 15:07:38',0),(135,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTYyMzgyLCJleHAiOjE3NjU2NDg3ODJ9.4cKhRknaK8MN_e6xIYBBMkZUiOoxnGq_kYmsFgB70Cg','2025-12-12 17:59:42',NULL,NULL,'2025-12-13 17:59:43',0),(136,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTYzMzUzLCJleHAiOjE3NjU2NDk3NTN9.JlICBFyrC19BZh17AuFDjtiAJVIZcaDdr85xb7kk5lw','2025-12-12 18:15:53',NULL,NULL,'2025-12-13 18:15:54',0),(137,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTYzNzE3LCJleHAiOjE3NjU2NTAxMTd9.nrtlfUR89SLpaadc97wWp9_jVNpNT_2cBkcT8OWFFuQ','2025-12-12 18:21:57',NULL,NULL,'2025-12-13 18:21:58',0),(138,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY0NzI4LCJleHAiOjE3NjU2NTExMjh9.PA4B3YdIpF2EYnffar81HAwDwDRJZ7__Qu6baoWvETI','2025-12-12 18:38:48',NULL,NULL,'2025-12-13 18:38:49',0),(139,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY1MzI2LCJleHAiOjE3NjU2NTE3MjZ9.Qt9bMAjEmCVcMvGptt7e5JN-GUazDqfL5p3KM3Qc4Lo','2025-12-12 18:48:46',NULL,NULL,'2025-12-13 18:48:47',0),(140,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY2MDI4LCJleHAiOjE3NjU2NTI0Mjh9.CLkaI5kxGhKTxLiEab0rv6IizipeYNrkX7qHcEtKm9o','2025-12-12 19:00:28',NULL,NULL,'2025-12-13 19:00:29',0),(141,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY2NjY4LCJleHAiOjE3NjU2NTMwNjh9.bRTFqKWyl8HtoEr0Lg2jT4dtjXVZnHXmX0B8tKthTQw','2025-12-12 19:11:08',NULL,NULL,'2025-12-13 19:11:09',0),(142,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY2NzI2LCJleHAiOjE3NjU2NTMxMjZ9.DscfIKpi-r_n023C9w4BpsNw8OcdPmICpMac4lLIpl4','2025-12-12 19:12:06',NULL,NULL,'2025-12-13 19:12:07',0),(143,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY3NDQwLCJleHAiOjE3NjU2NTM4NDB9.VteGDFyod6LBOtxb03hOY5I5HeO7pVCxvRig4rCCs1A','2025-12-12 19:24:00',NULL,NULL,'2025-12-13 19:24:01',0),(144,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NTY3OTY5LCJleHAiOjE3NjU2NTQzNjl9.eQ3-AF5pLjrh_LzpbaxW-IrUl63p0FeAIYkVTpCV0DA','2025-12-12 19:32:49',NULL,NULL,'2025-12-13 19:32:50',0),(145,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjAxMTM1LCJleHAiOjE3NjU2ODc1MzV9.EPI-4rASlu6FBgwnHPH1F3oBsu-z3y0laY037JYsusg','2025-12-13 04:45:35',NULL,NULL,'2025-12-14 04:45:36',0),(146,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjI1NTEzLCJleHAiOjE3NjU3MTE5MTN9.n33lz6QkEQ_0kE8DgUlCQA8WO_HvpkOTpNwzuUyD8H8','2025-12-13 11:31:53',NULL,NULL,'2025-12-14 11:31:53',0),(147,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjMyNzgwLCJleHAiOjE3NjU3MTkxODB9.Q_PrbQVIEgBif9SHy_1bHe94wB-y-yZPEYxOD-PGpxM','2025-12-13 13:33:00',NULL,NULL,'2025-12-14 13:33:01',0),(148,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjMyODAyLCJleHAiOjE3NjU3MTkyMDJ9.MsyUP-W_yloGgAC1rjOfVO3eOyRbPUcdMjhMhB230Mo','2025-12-13 13:33:22',NULL,NULL,'2025-12-14 13:33:23',0),(149,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjMyODQ4LCJleHAiOjE3NjU3MTkyNDh9.3NeTvKGI25T-BxJben6QRTDOh1U2OVhuPvc2gPHFRXw','2025-12-13 13:34:08',NULL,NULL,'2025-12-14 13:34:08',0),(150,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjM2NDkzLCJleHAiOjE3NjU3MjI4OTN9.jn4eaifKiIDizZ-q7KsU8w0eZD3Hwoc-StrD0b7QnMA','2025-12-13 14:34:53',NULL,NULL,'2025-12-14 14:34:53',0),(151,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjM3MTMzLCJleHAiOjE3NjU3MjM1MzN9.jQqfhZuK7d3tlnge6UPzE-uX01uDZMWvPkIXX2JnxT8','2025-12-13 14:45:33',NULL,NULL,'2025-12-14 14:45:34',0),(152,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjM3OTA3LCJleHAiOjE3NjU3MjQzMDd9.W805UaquzYNGSrKDRnfMohf2J7PwNwxr7h2c9sHJ2lM','2025-12-13 14:58:27',NULL,NULL,'2025-12-14 14:58:27',0),(153,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjM5MzA5LCJleHAiOjE3NjU3MjU3MDl9.IG1CukcGBn0YpfwHBGx1GTsW4YbDJ9fYeOGVnavaS14','2025-12-13 15:21:49',NULL,NULL,'2025-12-14 15:21:50',0),(154,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjQwNTA2LCJleHAiOjE3NjU3MjY5MDZ9.L29JecsS8YYw0DEaMNm5IwHgXJGwlIOsRT3kojOYQis','2025-12-13 15:41:46',NULL,NULL,'2025-12-14 15:41:47',0),(155,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjQ4MzIwLCJleHAiOjE3NjU3MzQ3MjB9.58n7TpQyQsm2cQenMImDNxYI7DHB44dH_HeABLOsg1E','2025-12-13 17:52:00',NULL,NULL,'2025-12-14 17:52:01',0),(156,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NjQ5MDEyLCJleHAiOjE3NjU3MzU0MTJ9.LRMyuDRehXTY9D5pGDBB3JkmO_FQoAvHbK46AnfexHM','2025-12-13 18:03:32',NULL,NULL,'2025-12-14 18:03:32',0),(157,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1Njg2MTU4LCJleHAiOjE3NjU3NzI1NTh9.f4b39428MX5_uTjlbpCCBKyfmS71flxs6xhzyhr9bEw','2025-12-14 04:22:38',NULL,NULL,'2025-12-15 04:22:39',0),(158,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1Njg4MjQyLCJleHAiOjE3NjU3NzQ2NDJ9.QnELIEji-vn57SYLXHxqOse8ooJr7aJUcLtYTGq32Co','2025-12-14 04:57:22',NULL,NULL,'2025-12-15 04:57:22',0),(159,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NzA0NTAwLCJleHAiOjE3NjU3OTA5MDB9.iktGxDNblHsdZFvWybXvUVJesgLfbw2-54mdVLkKrNA','2025-12-14 09:28:20',NULL,NULL,'2025-12-15 09:28:20',0),(160,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NzA1NDM2LCJleHAiOjE3NjU3OTE4MzZ9.S0Pkz6PKSG7eMrGn70dj2J3r1DuZvci2Kwml02j6kL8','2025-12-14 09:43:56',NULL,NULL,'2025-12-15 09:43:56',0),(161,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NzA2NzcyLCJleHAiOjE3NjU3OTMxNzJ9.xxWN8QuJvlE-VHU0qpVwV9R_ouZnAZu5EPPdZvb6hqM','2025-12-14 10:06:12',NULL,NULL,'2025-12-15 10:06:12',0),(162,1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNpbmR1IiwiaWF0IjoxNzY1NzA3NjI0LCJleHAiOjE3NjU3OTQwMjR9.pWnKNJc8oWlcZb72tw5BpkqUlR-J-NgMKcwgHCGS-do','2025-12-14 10:20:24',NULL,NULL,'2025-12-15 10:20:24',0);
/*!40000 ALTER TABLE `jwt_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_bar`
--

DROP TABLE IF EXISTS `link_bar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_bar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link_bar_type_id` int NOT NULL,
  `icon_url` varchar(100) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `link_bar_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `link_bar_type_id` (`link_bar_type_id`),
  KEY `link_bar_status_id` (`link_bar_status_id`),
  CONSTRAINT `link_bar_ibfk_1` FOREIGN KEY (`link_bar_type_id`) REFERENCES `link_bar_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `link_bar_ibfk_2` FOREIGN KEY (`link_bar_status_id`) REFERENCES `link_bar_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_bar`
--

LOCK TABLES `link_bar` WRITE;
/*!40000 ALTER TABLE `link_bar` DISABLE KEYS */;
INSERT INTO `link_bar` VALUES (1,'mobile','0701111111',2,'/icons/link-bar-icons/phone.png','/home',1,'2025-09-15 16:08:46',1,'2025-09-27 09:01:01',NULL,NULL,NULL),(2,'email','example@gmail.com',2,'/icons/link-bar-icons/email.png','/dashboard',1,'2025-09-15 16:08:46',1,'2025-09-27 09:00:24',NULL,NULL,NULL),(3,'address','test address',2,'/icons/link-bar-icons/location.png','/settings',2,'2025-09-15 16:08:46',1,'2025-09-27 09:01:01',NULL,NULL,NULL),(4,'facebook','facebook',1,'/icons/link-bar-icons/facebook.png','https://www.facebook.com/',2,'2025-09-15 16:08:46',1,'2025-09-27 09:26:51',NULL,NULL,NULL),(5,'insta','insta',1,'/icons/link-bar-icons/insta.png','https://www.instagram.com/accounts/login/?hl=en',1,'2025-09-15 16:08:46',1,'2025-09-27 09:26:51',NULL,NULL,NULL),(6,'twitter','twitter',1,'/icons/link-bar-icons/twitter.png','https://x.com/i/flow/login?lang=en',2,'2025-09-15 16:08:46',1,'2025-09-27 09:26:51',NULL,NULL,NULL),(7,'youtube','youtube',1,'/icons/link-bar-icons/you-tube.png','https://www.youtube.com/',2,'2025-09-15 16:08:46',1,'2025-09-27 09:26:51',NULL,NULL,NULL),(8,'linkedin','linkedin',1,'/icons/link-bar-icons/linkedin.png','https://www.linkedin.com/',1,'2025-09-15 16:08:46',1,'2025-09-27 09:26:51',NULL,NULL,NULL);
/*!40000 ALTER TABLE `link_bar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_bar_status`
--

DROP TABLE IF EXISTS `link_bar_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_bar_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `common_status_id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `link_bar_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_bar_status`
--

LOCK TABLES `link_bar_status` WRITE;
/*!40000 ALTER TABLE `link_bar_status` DISABLE KEYS */;
INSERT INTO `link_bar_status` VALUES (1,'Visible',1,'Link bar is visible','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL),(2,'Hidden',2,'Link bar is hidden','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL),(3,'Archived',3,'Link bar is archived','2025-09-15 16:08:46',1,'2025-09-15 16:08:46',NULL,NULL,NULL);
/*!40000 ALTER TABLE `link_bar_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_bar_type`
--

DROP TABLE IF EXISTS `link_bar_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_bar_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `link_bar_type_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_bar_type`
--

LOCK TABLES `link_bar_type` WRITE;
/*!40000 ALTER TABLE `link_bar_type` DISABLE KEYS */;
INSERT INTO `link_bar_type` VALUES (1,'IMAGE_ONLY','Top navigation bar',1,'2025-09-15 16:08:46',1,'2025-09-19 15:39:21',NULL,NULL,NULL),(2,'FULL','Side menu bar',1,'2025-09-15 16:08:46',1,'2025-09-19 15:39:21',NULL,NULL,NULL);
/*!40000 ALTER TABLE `link_bar_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_type`
--

DROP TABLE IF EXISTS `meal_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_type` (
  `meal_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`meal_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `meal_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_type`
--

LOCK TABLES `meal_type` WRITE;
/*!40000 ALTER TABLE `meal_type` DISABLE KEYS */;
INSERT INTO `meal_type` VALUES (1,'Breakfast','Morning meal',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'Lunch','Midday meal',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'Dinner','Evening meal',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'Brunch','Late morning meal',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'Snacks','Light meals and snacks',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `meal_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mobile_verified`
--

DROP TABLE IF EXISTS `mobile_verified`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mobile_verified` (
  `mobile_verified_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `send_code` varchar(50) DEFAULT NULL,
  `typed_code` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_id` int DEFAULT NULL,
  `which_mobile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mobile_verified_id`),
  KEY `user_id` (`user_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `mobile_verified_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mobile_verified_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `verified_status` (`verified_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mobile_verified`
--

LOCK TABLES `mobile_verified` WRITE;
/*!40000 ALTER TABLE `mobile_verified` DISABLE KEYS */;
INSERT INTO `mobile_verified` VALUES (1,1,'639866','MOB111','2025-09-21 14:06:10','2025-11-29 04:40:52',2,'primary'),(2,2,'MOB222','WRONG','2025-09-21 14:06:10','2025-11-29 04:00:47',1,'primary'),(3,1,'AAAA','MOB111','2025-09-21 14:06:10','2025-11-29 03:20:02',1,'secondory');
/*!40000 ALTER TABLE `mobile_verified` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nav_bar`
--

DROP TABLE IF EXISTS `nav_bar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nav_bar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_navbar_navbarstatus` (`status_id`),
  CONSTRAINT `fk_navbar_navbarstatus` FOREIGN KEY (`status_id`) REFERENCES `nav_bar_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nav_bar`
--

LOCK TABLES `nav_bar` WRITE;
/*!40000 ALTER TABLE `nav_bar` DISABLE KEYS */;
INSERT INTO `nav_bar` VALUES (1,'Home','Homepage link',1,'/','2025-09-19 16:49:55',1,'2025-09-27 11:35:00',NULL,NULL,NULL),(2,'About Us','About us page link',1,'/about-us','2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(3,'Accommodation','Find places to stay',2,'/accommodation','2025-09-19 16:49:55',1,'2025-12-11 16:33:03',NULL,NULL,NULL),(4,'Blogs','Travel blogs and articles',1,'/blogs','2025-09-19 16:49:55',1,'2025-12-14 04:59:41',NULL,NULL,NULL),(5,'Contact Us','Contact information and form',1,'/contact-us','2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(6,'FAQ','Frequently Asked Questions',1,'/faq','2025-09-19 16:49:55',1,'2025-10-24 16:25:53',NULL,NULL,NULL),(7,'Sri Lankan Tours','Tour packages in Sri Lanka',1,'/sri-lankan-tours','2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(8,'Reviews','Customer reviews and testimonials',2,'/reviews','2025-09-19 16:49:55',1,'2025-12-11 16:33:03',NULL,NULL,NULL),(9,'Offers','Special travel offers and discounts',2,'/offers','2025-09-19 16:49:55',1,'2025-12-11 16:33:03',NULL,NULL,NULL),(10,'Packages','Available travel packages',1,'/packages','2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(11,'Gallery','Photo and video gallery',2,'/gallery','2025-09-19 16:49:55',1,'2025-12-11 16:33:03',NULL,NULL,NULL),(12,'Plan Your Trip','Trip planning section',2,'/plan-your-trip','2025-09-19 16:49:55',1,'2025-10-25 07:17:38',NULL,NULL,NULL),(13,'Activities','Things to do and activities',1,'/activities','2025-09-19 16:49:55',1,'2025-10-21 14:03:45',NULL,NULL,NULL),(14,'Destination','Destinations page link',1,'/destinations','2025-09-19 16:49:55',1,'2025-10-11 01:06:15',NULL,NULL,NULL);
/*!40000 ALTER TABLE `nav_bar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nav_bar_status`
--

DROP TABLE IF EXISTS `nav_bar_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nav_bar_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_navbarstatus_commonstatus` (`common_status_id`),
  CONSTRAINT `fk_navbarstatus_commonstatus` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nav_bar_status`
--

LOCK TABLES `nav_bar_status` WRITE;
/*!40000 ALTER TABLE `nav_bar_status` DISABLE KEYS */;
INSERT INTO `nav_bar_status` VALUES (1,'VISIBLE','Nav bar item is visible',1,'2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(2,'HIDDEN','Nav bar item is hidden',2,'2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL),(3,'REMOVED','Nav bar item removed',3,'2025-09-19 16:49:55',1,'2025-09-19 16:49:55',NULL,NULL,NULL);
/*!40000 ALTER TABLE `nav_bar_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nav_bar_submenu`
--

DROP TABLE IF EXISTS `nav_bar_submenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nav_bar_submenu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nav_bar_id` int NOT NULL,
  `parent_submenu_id` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link_url` varchar(255) NOT NULL,
  `icon_class` varchar(100) DEFAULT NULL,
  `sort_order` int DEFAULT '0',
  `opens_in_new_tab` tinyint(1) DEFAULT '0',
  `is_featured` tinyint(1) DEFAULT '0',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nav_bar_id` (`nav_bar_id`),
  KEY `parent_submenu_id` (`parent_submenu_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `nav_bar_submenu_ibfk_1` FOREIGN KEY (`nav_bar_id`) REFERENCES `nav_bar` (`id`) ON DELETE CASCADE,
  CONSTRAINT `nav_bar_submenu_ibfk_2` FOREIGN KEY (`parent_submenu_id`) REFERENCES `nav_bar_submenu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `nav_bar_submenu_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `nav_bar_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nav_bar_submenu`
--

LOCK TABLES `nav_bar_submenu` WRITE;
/*!40000 ALTER TABLE `nav_bar_submenu` DISABLE KEYS */;
INSERT INTO `nav_bar_submenu` VALUES (1,3,NULL,'All Accommodation','Browse all accommodation types','/accommodations','fa-list',1,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:26:02',NULL,NULL,NULL),(2,3,NULL,'Hotels','Luxury and budget hotels','/accommodations/hotels','fa-hotel',2,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:26:53',NULL,NULL,NULL),(3,3,NULL,'Resorts','Beach and mountain resorts','/accommodations/resorts','fa-umbrella-beach',3,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:25:44',NULL,NULL,NULL),(4,3,NULL,'Villas','Private vacation villas','/accommodations/villas','fa-home',4,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:25:44',NULL,NULL,NULL),(5,3,NULL,'Restaurants','Fine dining experiences','/accommodations/restaurants','fa-utensils',5,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:25:44',NULL,NULL,NULL),(6,3,NULL,'Hostels','Budget accommodations','/accommodations/hostels','fa-users',6,0,0,1,'2025-10-25 08:08:06',1,'2025-10-25 11:25:44',NULL,NULL,NULL);
/*!40000 ALTER TABLE `nav_bar_submenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nearby_destinations`
--

DROP TABLE IF EXISTS `nearby_destinations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nearby_destinations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `nearby_destination_id` int NOT NULL,
  `distance_km` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_nearby_pair` (`destination_id`,`nearby_destination_id`),
  KEY `nearby_destination_id` (`nearby_destination_id`),
  CONSTRAINT `nearby_destinations_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`),
  CONSTRAINT `nearby_destinations_ibfk_2` FOREIGN KEY (`nearby_destination_id`) REFERENCES `destination` (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nearby_destinations`
--

LOCK TABLES `nearby_destinations` WRITE;
/*!40000 ALTER TABLE `nearby_destinations` DISABLE KEYS */;
INSERT INTO `nearby_destinations` VALUES (1,1,4,36.00),(2,1,14,53.00),(3,1,2,58.00),(4,1,22,87.00),(5,1,7,94.00),(6,2,22,24.00),(7,2,24,30.00),(8,2,21,54.00),(9,2,4,65.00),(10,2,23,63.00),(11,3,14,41.00),(12,3,4,73.00),(13,3,22,82.00),(14,3,11,85.00),(15,3,13,92.00),(16,4,1,36.00),(17,4,14,52.00),(18,4,22,65.00),(19,4,7,76.00),(20,4,6,93.00),(21,5,7,74.00),(22,5,6,96.00),(23,5,18,93.00),(24,5,17,104.00),(25,5,16,116.00),(26,6,10,21.00),(27,6,7,66.00),(28,6,16,88.00),(29,6,20,123.00),(30,6,15,122.00),(31,7,6,66.00),(32,7,5,74.00),(33,7,4,76.00),(34,7,22,85.00),(35,7,18,97.00),(36,8,12,66.00),(37,8,9,111.00),(38,8,11,134.00),(39,8,13,156.00),(40,8,19,170.00),(41,9,11,32.00),(42,9,12,88.00),(43,9,13,62.00),(44,9,19,109.00),(45,9,14,92.00),(46,10,6,21.00),(47,10,16,86.00),(48,10,7,88.00),(49,10,17,99.00),(50,10,18,111.00),(51,11,9,32.00),(52,11,13,51.00),(53,11,14,72.00),(54,11,12,75.00),(55,11,19,118.00),(56,12,8,66.00),(57,12,11,75.00),(58,12,9,88.00),(59,12,13,104.00),(60,12,19,135.00),(61,13,11,51.00),(62,13,9,62.00),(63,13,12,104.00),(64,13,14,96.00),(65,13,19,89.00),(66,14,3,41.00),(67,14,11,72.00),(68,14,22,77.00),(69,14,1,53.00),(70,14,4,52.00),(71,15,17,6.00),(72,15,18,54.00),(73,15,16,35.00),(74,15,5,95.00),(75,15,6,122.00),(76,16,15,35.00),(77,16,17,34.00),(78,16,10,86.00),(79,16,6,88.00),(80,16,18,73.00),(81,17,15,6.00),(82,17,16,34.00),(83,17,18,60.00),(84,17,5,104.00),(85,17,10,99.00),(86,18,17,60.00),(87,18,15,54.00),(88,18,16,73.00),(89,18,5,93.00),(90,18,7,97.00),(91,19,13,89.00),(92,19,9,109.00),(93,19,12,135.00),(94,19,20,175.00),(95,19,11,118.00),(96,20,6,123.00),(97,20,9,145.00),(98,20,13,149.00),(99,20,19,175.00),(100,20,21,134.00),(101,21,22,55.00),(102,21,23,25.00),(103,21,25,28.00),(104,21,24,59.00),(105,21,2,54.00),(106,22,24,8.00),(107,22,2,24.00),(108,22,14,77.00),(109,22,21,55.00),(110,22,4,65.00),(111,23,25,10.00),(112,23,21,25.00),(113,23,22,30.00),(114,23,2,63.00),(115,23,24,41.00),(116,24,22,8.00),(117,24,2,30.00),(118,24,21,59.00),(119,24,23,41.00),(120,24,25,49.00),(121,25,23,10.00),(122,25,21,28.00),(123,25,24,49.00),(124,25,22,60.00),(125,25,2,70.00);
/*!40000 ALTER TABLE `nearby_destinations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `our_core_values`
--

DROP TABLE IF EXISTS `our_core_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `our_core_values` (
  `value_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `icon_name` varchar(100) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `order_no` int NOT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`value_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `our_core_values_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `our_core_values`
--

LOCK TABLES `our_core_values` WRITE;
/*!40000 ALTER TABLE `our_core_values` DISABLE KEYS */;
INSERT INTO `our_core_values` VALUES (1,'Authenticity','We believe in showcasing the real Sri Lanka - its culture, people, and untouched beauty.','MapPin','blue',1,1,'2025-12-11 18:04:19',1,'2025-12-11 18:04:19',NULL,NULL,NULL),(2,'Passion','Our love for travel and Sri Lanka drives everything we do, ensuring unforgettable experiences.','Heart','red',2,1,'2025-12-11 18:04:19',1,'2025-12-11 18:04:19',NULL,NULL,NULL),(3,'Excellence','We strive for perfection in every detail, from planning to execution of your journey.','Award','amber',3,1,'2025-12-11 18:04:19',1,'2025-12-11 18:04:19',NULL,NULL,NULL);
/*!40000 ALTER TABLE `our_core_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `our_features`
--

DROP TABLE IF EXISTS `our_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `our_features` (
  `feature_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `color` varchar(7) DEFAULT NULL,
  `hover_color` varchar(7) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`feature_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `our_features_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `our_features`
--

LOCK TABLES `our_features` WRITE;
/*!40000 ALTER TABLE `our_features` DISABLE KEYS */;
INSERT INTO `our_features` VALUES (1,'Easy & Quick Booking','icon-book-now.png','#FCE5F0','#F8D0E0','Lorem ipsum dolor sit amet, consectetur adipiscing elit.',1,'2025-12-06 15:55:36',1,'2025-12-06 15:55:36',NULL,NULL,NULL),(2,'Best Guide','icon-guide.png','#E9FDE9','#D0F0D8','Lorem ipsum dolor sit amet, consectetur adipiscing elit.',1,'2025-12-06 15:55:36',1,'2025-12-06 15:55:36',NULL,NULL,NULL),(3,'Extended Customization','icon-customization.png','#FFF4E1','#FFE7B3','Lorem ipsum dolor sit amet, consectetur adipiscing elit.',1,'2025-12-06 15:55:36',1,'2025-12-06 15:55:36',NULL,NULL,NULL),(4,'Customer Care 24/7','icon-customer-care.png','#E6F0FF','#BFD9FF','Lorem ipsum dolor sit amet, consectetur adipiscing elit.',1,'2025-12-06 15:55:36',1,'2025-12-06 15:55:36',NULL,NULL,NULL);
/*!40000 ALTER TABLE `our_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `our_service_status`
--

DROP TABLE IF EXISTS `our_service_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `our_service_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_common_status_service` (`common_status_id`),
  CONSTRAINT `fk_common_status_service` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `our_service_status`
--

LOCK TABLES `our_service_status` WRITE;
/*!40000 ALTER TABLE `our_service_status` DISABLE KEYS */;
INSERT INTO `our_service_status` VALUES (1,'ACTIVE','Service is available',1,'2025-09-20 12:01:16',1,'2025-09-20 12:01:16',NULL,NULL,NULL),(2,'INACTIVE','Service is not available',2,'2025-09-20 12:01:16',1,'2025-09-20 12:01:16',NULL,NULL,NULL),(3,'TERMINATED','Service discontinued',1,'2025-09-20 12:01:16',1,'2025-09-20 12:01:16',NULL,NULL,NULL);
/*!40000 ALTER TABLE `our_service_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `our_services`
--

DROP TABLE IF EXISTS `our_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `our_services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `our_service_status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_our_service_status` (`our_service_status_id`),
  CONSTRAINT `fk_our_service_status` FOREIGN KEY (`our_service_status_id`) REFERENCES `our_service_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `our_services`
--

LOCK TABLES `our_services` WRITE;
/*!40000 ALTER TABLE `our_services` DISABLE KEYS */;
INSERT INTO `our_services` VALUES (1,'Guide Services','Modern & Scalable','Organise your tour with us and be sure to receive guidance from an expert local guide on your tour. ','/images/our-services-images/guide-image.webp','#0073D2',1,'2025-09-20 12:01:25',1,'2025-09-28 07:43:21',NULL,NULL,NULL,'/icons/our-services-icons/guide.png'),(2,'MICE Services','iOS & Android','Being one of the latest forms of tourism we provide pioneer services in this sector in Sri Lanka.  ','/images/our-services-images/handshake-image.jpg','#16A34A',1,'2025-09-20 12:01:25',1,'2025-09-28 07:43:49',NULL,NULL,NULL,'/icons/our-services-icons/services.png'),(3,'Transport','Secure & Reliable','Need to get around while on vacation in Sri Lanka? Tangerine Tours is well appointed with a large fleet of vehicles','/images/our-services-images/travel-image.jpg','#F59E0B',1,'2025-09-20 12:01:25',1,'2025-09-28 07:43:49',NULL,NULL,NULL,'/icons/our-services-icons/handshake.png');
/*!40000 ALTER TABLE `our_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `our_story_timeline`
--

DROP TABLE IF EXISTS `our_story_timeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `our_story_timeline` (
  `story_id` int NOT NULL AUTO_INCREMENT,
  `year_label` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` varchar(500) NOT NULL,
  `icon_name` varchar(100) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `order_no` int NOT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`story_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `our_story_timeline_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `our_story_timeline`
--

LOCK TABLES `our_story_timeline` WRITE;
/*!40000 ALTER TABLE `our_story_timeline` DISABLE KEYS */;
INSERT INTO `our_story_timeline` VALUES (1,'2026 - The Beginning','The Beginning','Founded in Colombo with a simple mission: to share the beauty of Sri Lanka with the world. Started as a small travel desk with just three passionate team members.','Calendar','blue',1,1,'2025-12-11 18:04:19',1,'2025-12-11 18:30:39',NULL,NULL,NULL);
/*!40000 ALTER TABLE `our_story_timeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_hero_section`
--

DROP TABLE IF EXISTS `package_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `package_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_hero_section`
--

LOCK TABLES `package_hero_section` WRITE;
/*!40000 ALTER TABLE `package_hero_section` DISABLE KEYS */;
INSERT INTO `package_hero_section` VALUES (1,'classic-sri-lanka','https://images.unsplash.com/photo-1548013146-72479768bada?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Classic Sri Lanka Tour','14 Days | Cultural Triangle, Hills & Beaches','Experience the best of Sri Lanka in this comprehensive tour covering ancient cities, hill country, wildlife, and beautiful beaches. Perfect for first-time visitors.','Book Now','/packages/classic-sri-lanka','View Itinerary','/itinerary/classic-sri-lanka',1,1,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(2,'beach-bliss','https://images.unsplash.com/photo-1552465011-b4e30bf7349d?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Beach Bliss Package','7 Days | South Coast Beaches','Relax on Sri Lanka\'s finest beaches from Bentota to Mirissa. Enjoy water sports, whale watching, sunset cruises, and seafood feasts.','Explore Package','/packages/beach-bliss','Beach Guide','/guide/beaches',1,2,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(3,'wildlife-safari','https://images.unsplash.com/photo-1579444741990-6e31c9b09d52?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Wildlife Safari Expedition','10 Days | Yala, Udawalawe & Wilpattu','An exclusive wildlife package featuring multiple safaris, leopard tracking, bird watching, and staying in luxury safari camps.','Book Safari','/packages/wildlife-safari','Wildlife Calendar','/calendar/best-safari-times',1,3,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(4,'hill-country-escape','https://images.unsplash.com/photo-1592210454359-9043f067919b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Hill Country Escape','8 Days | Tea Trails & Mountain Views','Journey through misty mountains, tea plantations, and colonial hill stations. Includes train rides, hiking, and tea factory visits.','Book Escape','/packages/hill-country-escape','Tea Experiences','/experiences/tea',1,4,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(5,'luxury-honeymoon','https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Luxury Honeymoon Special','12 Days | Romantic Getaway','Ultimate romantic experience with private villas, candlelit dinners, couple spa treatments, and exclusive experiences across the island.','Plan Honeymoon','/packages/luxury-honeymoon','Romantic Add-ons','/addons/romantic',1,5,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(6,'family-adventure','https://images.unsplash.com/photo-1506929562872-bb421503ef21?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Family Adventure Package','10 Days | Fun for All Ages','Perfect family vacation featuring elephant orphanage visits, turtle conservation, kid-friendly beaches, and interactive cultural experiences.','Family Booking','/packages/family-adventure','Child Activities','/activities/kids',1,6,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(7,'budget-backpacker','https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Budget Backpacker Tour','15 Days | Affordable Adventure','Explore Sri Lanka on a budget with local homestays, public transport experiences, street food tours, and hidden gems.','Book Budget Tour','/packages/budget-backpacker','Cost Calculator','/tools/budget-calculator',1,7,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(8,'wellness-retreat','https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Ayurveda Wellness Retreat','7 Days | Rejuvenation Program','Holistic wellness package including Ayurvedic treatments, yoga sessions, meditation, detox programs, and organic meals.','Book Retreat','/packages/wellness-retreat','Wellness Guide','/guide/ayurveda',1,8,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(9,'adventure-extreme','https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Extreme Adventure Package','10 Days | Adrenaline Rush','For thrill-seekers: white water rafting, mountain biking, surfing lessons, hiking, and other extreme sports across Sri Lanka.','Adventure Booking','/packages/adventure-extreme','Activity Levels','/guide/adventure-levels',1,9,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(10,'cultural-immersion','https://images.unsplash.com/photo-1558272729-5e0165e4fde6?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Cultural Immersion Tour','12 Days | Deep Cultural Experience','Go beyond typical tourism with village stays, traditional craft workshops, cooking classes, and local festival participation.','Cultural Booking','/packages/cultural-immersion','Cultural Calendar','/calendar/festivals',1,10,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(11,'short-break','https://images.unsplash.com/photo-1528181304800-259b08848526?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Weekend Getaway','3 Days | Quick Escape','Perfect short break packages for weekend warriors. Choose from beach relaxation, hill station escapes, or cultural quick tours.','Weekend Deals','/packages/short-break','Last Minute','/deals/last-minute',1,11,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL),(12,'monsoon-magic','https://images.unsplash.com/photo-1536152471326-642d4aa9cba5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Monsoon Magic','8 Days | Rainy Season Special','Experience Sri Lanka during monsoon season with special discounts, indoor activities, spa packages, and unique rainy day experiences.','Monsoon Offers','/packages/monsoon-magic','Season Guide','/guide/best-time-to-visit',1,12,'2025-12-16 03:29:02',1,'2025-12-16 03:29:02',1,NULL,NULL);
/*!40000 ALTER TABLE `package_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_history`
--

DROP TABLE IF EXISTS `package_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_schedule_id` int NOT NULL,
  `number_of_participate` int DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `hover_color` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_schedule_id` (`package_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_history_ibfk_1` FOREIGN KEY (`package_schedule_id`) REFERENCES `package_schedule` (`id`),
  CONSTRAINT `package_history_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_history`
--

LOCK TABLES `package_history` WRITE;
/*!40000 ALTER TABLE `package_history` DISABLE KEYS */;
INSERT INTO `package_history` VALUES (1,1,6,4.80,1,'Sigiriya Package October','Completed successfully',1,'#FF6B6B','#FF5252','2025-10-15','2025-10-15','2025-10-04 16:15:26',1,'2025-10-04 16:15:26',NULL,NULL,NULL),(2,2,4,4.90,3,'Ella Package November','Outstanding experience',1,'#4ECDC4','#3BB8B0','2025-11-08','2025-11-10','2025-10-04 16:15:26',1,'2025-10-04 16:15:26',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_history_images`
--

DROP TABLE IF EXISTS `package_history_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_history_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_schedule_id` (`package_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_history_images_ibfk_1` FOREIGN KEY (`package_schedule_id`) REFERENCES `package_schedule` (`id`),
  CONSTRAINT `package_history_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_history_images`
--

LOCK TABLES `package_history_images` WRITE;
/*!40000 ALTER TABLE `package_history_images` DISABLE KEYS */;
INSERT INTO `package_history_images` VALUES (1,1,'Sigiriya Package Memories 1','Participants enjoying the tour','/images/package-history/sigiriya-package-memories-1.png',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(2,1,'Sigiriya Package Memories 2','Lunch break with traditional food','/images/package-history/sigiriya-package-memories-2.webp',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(3,2,'Ella Package Day 1','Arrival and hotel check-in','/images/package-history/ella-package-day-1.webp',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(4,2,'Ella Package Day 2','Hiking and exploring','/images/package-history/ella-package-day-2.jpg',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(5,2,'Ella Package Day 3','Final day breakfast view','/images/package-history/ella-package-day-3.webp',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(6,3,'Yala Safari Package','Morning safari departure','/images/package-history/yala-safari-package.jpg',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL),(7,3,'Yala Camping Experience','Luxury camping setup','/images/package-history/yala-camping-experience.jpg',1,'2025-10-04 19:51:12',1,'2025-10-04 19:51:12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_history_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_images`
--

DROP TABLE IF EXISTS `package_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_images_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `package_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_images`
--

LOCK TABLES `package_images` WRITE;
/*!40000 ALTER TABLE `package_images` DISABLE KEYS */;
INSERT INTO `package_images` VALUES (1,1,'Sigiriya Rock View','Panoramic view of Sigiriya Rock Fortress',1,'/images/packages/sigiriya-rock-view.jpg','#FF6B6B','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(2,1,'Ancient Steps','Tourists climbing the ancient rock steps',1,'/images/packages/ancient-steps.jpg','#FF5252','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(3,2,'Sunrise at Sigiriya','Golden sunrise view from Sigiriya summit',1,'/images/packages/sunrise-at-sigiriya.jpg','#FFA07A','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(4,2,'Cultural Guide Tour','Guide explaining Sigiriya frescoes',1,'/images/packages/cultural-guide-tour.jpg','#FF8C69','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(5,3,'Anuradhapura Stupa','Ancient stupa from the cultural triangle',1,'/images/packages/anuradhapura-stupa.jpg','#AA96DA','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(6,3,'Polonnaruwa Ruins','Historic stone ruins surrounded by greenery',1,'/images/packages/polonnaruwa-ruins.jpg','#9580D0','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(7,4,'Triangle Express Bus','Tour bus ready for quick 3-day route',1,'/images/packages/triangle-express-bus.jpg','#9370DB','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(8,4,'Sigiriya Stopover','Travelers taking photos at Sigiriya',1,'/images/packages/sigiriya-stopover.jpg','#836FFF','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(9,5,'Leopard Spotting','Jeep group spotting a leopard at Yala',1,'/images/packages/leopard-spotting.jpg','#95E1D3','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(10,5,'Luxury Camp','Luxury tent setup near safari site',1,'/images/packages/luxury-camp.jpg','#7DD4C5','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(11,6,'Private Jeep Safari','Exclusive jeep ride with guide',1,'/images/packages/private-jeep-safari.png','#20B2AA','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(12,6,'Elephants at Dusk','Elephants walking in golden hour light',1,'/images/packages/elephants-at-dusk.jpg','#2E8B57','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(13,7,'Mirissa Beach','Tourists enjoying sunbathing on Mirissa beach',1,'/images/packages/mirissa-beach.jpg','#F38181','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(14,7,'Water Sports','Jet skiing and parasailing activities',1,'/images/packages/water-sports.jpg','#F06868','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(15,8,'Whale Watching Boat','Boat heading to deep sea whale watching',1,'/images/packages/whale-watching-boat.webp','#FFB6C1','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(16,8,'Blue Whale','Rare sight of blue whale surfacing',1,'/images/packages/blue-whale.jpg','#FF69B4','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(17,9,'Nine Arches Bridge','Famous railway bridge in Ella',1,'/images/packages/nine-arches-bridge.webp','#4ECDC4','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(18,9,'Tea Plantations','Tea workers in lush green plantations',1,'/images/packages/tea-plantations.jpg','#3BB8B0','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(19,10,'Train Journey','Scenic train ride from Kandy to Ella',1,'/images/packages/train-journey.jpeg','#48D1CC','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(20,10,'Tea Tasting','Tourists tasting fresh Ceylon tea',1,'/images/packages/tea-tasting.jpg','#40E0D0','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(21,11,'Spa Treatment','Relaxing full-body spa session',1,'/images/packages/spa-treatment.jpg','#FFD700','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(22,11,'Yoga by the Beach','Morning yoga session on the beach',1,'/images/packages/yoga-by-the-beach.jpg','#FFC107','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(23,12,'Ayurveda Garden','Herbal therapy garden and Ayurveda center',1,'/images/packages/ayurveda-garden.webp','#DAA520','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(24,12,'Luxury Resort','Beachfront wellness resort',1,'/images/packages/luxury-resort.jpg','#B8860B','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(25,13,'Temple of the Tooth','Historic Kandy temple exterior view',1,'/images/packages/temple-of-the-tooth.jpg','#BA55D3','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(26,13,'Cultural Dance','Traditional Kandyan dance performance',1,'/images/packages/cultural-dance.jpg','#9932CC','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(27,14,'Botanical Garden','Royal botanical gardens Peradeniya',1,'/images/packages/botanical-garden.jpg','#8A2BE2','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(28,14,'Cultural City Tour','Visitors exploring Kandy streets',1,'/images/packages/cultural-city-tour.jpg','#6A5ACD','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(29,15,'Knuckles Range View','Mountain landscape with morning mist',1,'/images/packages/knuckles-range-view.jpg','#32CD32','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(30,15,'Group Trekking','Group hiking through forest trails',1,'/images/packages/group-trekking.jpg','#228B22','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(31,16,'Campfire Night','Campers enjoying night fire in mountains',1,'/images/packages/campfire-night.jpg','#006400','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(32,16,'Tents in Valley','Camping tents under starry sky',1,'/images/packages/tents-in-valley.jpg','#2E8B57','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(33,17,'Safari Jeep','Jeep heading through Wilpattu trails',1,'/images/packages/safari-jeep.webp','#8B4513','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(34,17,'Luxury Lodge','High-end safari lodge interior',1,'/images/packages/luxury-lodge.jpg','#A0522D','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(35,18,'Camping Site','Tented camp setup near Wilpattu lake',1,'/images/packages/camping-site.webp','#D2691E','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(36,18,'Wildlife Spot','Leopard walking through bush',1,'/images/packages/wildlife-spot.webp','#CD853F','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(37,19,'Surf Action','Surfer catching a wave at Arugam Bay',1,'/images/packages/surf-action.jpg','#00CED1','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(38,19,'Beach Chill','Backpackers relaxing by the surf boards',1,'/images/packages/beach-chill.jpg','#20B2AA','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(39,20,'Pro Surf Competition','Professional surfers performing tricks',1,'/images/packages/pro-surf-competition.webp','#1E90FF','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(40,20,'Sunset Surf','Surfboards against orange sunset',1,'/images/packages/sunset-surf.jpg','#4169E1','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(41,21,'Ayurveda Treatment','Traditional oil therapy',1,'/images/packages/ayurveda-treatment.jpg','#FF8C00','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(42,21,'Meditation Hall','Guests meditating in calm environment',1,'/images/packages/meditation-hall.jpg','#FF7F50','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(43,22,'Detox Program','Guests enjoying herbal detox drinks',1,'/images/packages/detox-program.jpg','#FF6347','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(44,22,'Resort Stay','Healing resort room interior',1,'/images/packages/resort-stay.jpeg','#FF4500','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(45,23,'Jaffna Fort','Historic Dutch fort of Jaffna',1,'/images/packages/jaffna-fort.jpg','#FF1493','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(46,23,'Temple Visit','Colorful Hindu temple exterior',1,'/images/packages/temple-visit.jpg','#DB7093','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(47,24,'Jaffna Cuisine','Traditional northern Sri Lankan dishes',1,'/images/packages/jaffna-cuisine.jpg','#C71585','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(48,24,'Cultural Streets','Busy Jaffna market streets',1,'/images/packages/cultural-streets.jpg','#8B008B','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(49,25,'Rafting Rapids','Adventure seekers paddling through rapids',1,'/images/packages/rafting-rapids.jpg','#40E0D0','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(50,25,'River Group','Team photo after rafting',1,'/images/packages/river-group.jpg','#48D1CC','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(51,26,'Canyoning Adventure','Tourists descending waterfalls',1,'/images/packages/canyoning-adventure.jpg','#4682B4','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(52,26,'Rope Jump','Thrill-seeker jumping into river canyon',1,'/images/packages/rope-jump.webp','#5F9EA0','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(53,27,'Pigeon Island','Snorkelers exploring coral reef',1,'/images/packages/pigeon-island.webp','#00FA9A','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(54,27,'Boat Ride','Tourists boarding snorkeling boat',1,'/images/packages/boat-ride.jpg','#00FF7F','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(55,28,'Dolphin Watching','Dolphins jumping near boat',1,'/images/packages/dolphin-watching.jpg','#3CB371','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(56,28,'Beach Resort','Trincomalee beach resort sunrise',1,'/images/packages/beach-resort.jpg','#2E8B57','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(57,29,'Elephant Herd','Group of elephants near water hole',1,'/images/packages/elephant-herd.jpg','#B22222','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(58,29,'Jeep Trail','Tourists riding through national park',1,'/images/packages/jeep-trail.webp','#DC143C','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(59,30,'Overnight Camp','Camp setup near Udawalawe park',1,'/images/packages/overnight-camp.jpg','#CD5C5C','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL),(60,30,'Morning Safari','Early morning jeep safari',1,'/images/packages/morning-safari.jpg','#8B0000','2025-10-04 18:07:24',1,'2025-10-04 18:07:24',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_review`
--

DROP TABLE IF EXISTS `package_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `review` text,
  `rating` decimal(3,2) DEFAULT NULL,
  `description` text,
  `status` int NOT NULL,
  `number_of_participate` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_schedule_id` (`package_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_review_ibfk_1` FOREIGN KEY (`package_schedule_id`) REFERENCES `package_schedule` (`id`),
  CONSTRAINT `package_review_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_review`
--

LOCK TABLES `package_review` WRITE;
/*!40000 ALTER TABLE `package_review` DISABLE KEYS */;
INSERT INTO `package_review` VALUES (1,1,'Great Value Package','Everything was well organized. Transport was comfortable and guide was fantastic.',4.80,'Excellent day trip option',1,6,'2025-10-04 16:15:36',1,'2025-10-04 16:15:36',NULL,NULL,NULL),(2,2,'Wonderful Ella Package','The hotel was beautiful, food was great, and activities were well planned. Could not ask for more!',4.90,'Highly recommended 3-day trip',1,4,'2025-10-04 16:15:36',1,'2025-10-04 16:15:36',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_review_comment`
--

DROP TABLE IF EXISTS `package_review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_review_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `comment` text NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_review_id` (`package_review_id`),
  KEY `user_id` (`user_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_review_comment_ibfk_1` FOREIGN KEY (`package_review_id`) REFERENCES `package_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_ibfk_3` FOREIGN KEY (`parent_comment_id`) REFERENCES `package_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_review_comment`
--

LOCK TABLES `package_review_comment` WRITE;
/*!40000 ALTER TABLE `package_review_comment` DISABLE KEYS */;
INSERT INTO `package_review_comment` VALUES (1,1,2,NULL,'Totally agree! The guide was super friendly.',1,'2025-10-15 15:06:38',2,'2025-10-15 15:06:38',NULL,NULL,NULL),(2,1,3,NULL,'We had a similar experience last month, amazing value.',1,'2025-10-15 15:06:38',3,'2025-10-15 15:06:38',NULL,NULL,NULL),(3,1,1,1,'Glad to hear that! Our guide was Chaminda, he was great.',1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL),(4,2,1,NULL,'We stayed in the same hotel, and it was very clean.',1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL),(5,2,3,NULL,'The food was indeed fantastic, especially the breakfast!',1,'2025-10-15 15:06:38',3,'2025-10-15 15:06:38',NULL,NULL,NULL),(6,2,2,4,'Yes! The buffet was delicious ?',1,'2025-10-15 15:06:38',2,'2025-10-15 15:06:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_review_comment_reaction`
--

DROP TABLE IF EXISTS `package_review_comment_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_review_comment_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id` (`comment_id`,`user_id`,`reaction_type_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_review_comment_reaction_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `package_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `package_review_comment_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_review_comment_reaction`
--

LOCK TABLES `package_review_comment_reaction` WRITE;
/*!40000 ALTER TABLE `package_review_comment_reaction` DISABLE KEYS */;
INSERT INTO `package_review_comment_reaction` VALUES (1,1,1,1,1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL),(2,1,3,2,1,'2025-10-15 15:06:38',3,'2025-10-15 15:06:38',NULL,NULL,NULL),(3,3,2,1,1,'2025-10-15 15:06:38',2,'2025-10-15 15:06:38',NULL,NULL,NULL),(4,4,3,1,1,'2025-10-15 15:06:38',3,'2025-10-15 15:06:38',NULL,NULL,NULL),(5,6,1,2,1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_review_comment_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_review_images`
--

DROP TABLE IF EXISTS `package_review_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_review_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_review_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_review_id` (`package_review_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_review_images_ibfk_1` FOREIGN KEY (`package_review_id`) REFERENCES `package_review` (`id`),
  CONSTRAINT `package_review_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_review_images`
--

LOCK TABLES `package_review_images` WRITE;
/*!40000 ALTER TABLE `package_review_images` DISABLE KEYS */;
INSERT INTO `package_review_images` VALUES (1,1,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-1.webp',1,'2025-10-04 19:54:11',1,'2025-10-15 16:19:33',NULL,NULL,NULL),(2,1,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-2.webp',1,'2025-10-04 19:54:11',1,'2025-10-15 16:19:33',NULL,NULL,NULL),(3,1,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-3.webp',1,'2025-10-04 19:54:11',1,'2025-10-04 19:54:11',NULL,NULL,NULL),(4,1,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-4.jpg',1,'2025-10-04 19:54:11',1,'2025-10-04 19:54:11',NULL,NULL,NULL),(5,1,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-5.webp',1,'2025-10-04 19:54:11',1,'2025-10-04 19:54:11',NULL,NULL,NULL),(6,2,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-2.webp',1,'2025-10-04 19:54:11',1,'2025-10-04 19:54:11',NULL,NULL,NULL),(7,2,'Happy Guests','Group photo with guide','/images/package-review/happy-guests-5.webp',1,'2025-10-04 19:54:11',1,'2025-10-04 19:54:11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_review_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_review_reaction`
--

DROP TABLE IF EXISTS `package_review_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_review_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `package_review_id` (`package_review_id`,`user_id`,`reaction_type_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_review_reaction_ibfk_1` FOREIGN KEY (`package_review_id`) REFERENCES `package_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_review_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `package_review_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_review_reaction`
--

LOCK TABLES `package_review_reaction` WRITE;
/*!40000 ALTER TABLE `package_review_reaction` DISABLE KEYS */;
INSERT INTO `package_review_reaction` VALUES (1,1,1,1,1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL),(2,1,2,2,1,'2025-10-15 15:06:38',2,'2025-10-15 15:06:38',NULL,NULL,NULL),(3,1,3,1,1,'2025-10-15 15:06:38',3,'2025-10-15 15:06:38',NULL,NULL,NULL),(4,2,1,2,1,'2025-10-15 15:06:38',1,'2025-10-15 15:06:38',NULL,NULL,NULL),(5,2,2,1,1,'2025-10-15 15:06:38',2,'2025-10-15 15:06:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_review_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_schedule`
--

DROP TABLE IF EXISTS `package_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `package_id` int NOT NULL,
  `assume_start_date` date DEFAULT NULL,
  `assume_end_date` date DEFAULT NULL,
  `duration_start` int DEFAULT NULL,
  `duration_end` int DEFAULT NULL,
  `special_note` text,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `tour_shedule_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`),
  KEY `status` (`status`),
  CONSTRAINT `package_schedule_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `package_schedule_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_schedule`
--

LOCK TABLES `package_schedule` WRITE;
/*!40000 ALTER TABLE `package_schedule` DISABLE KEYS */;
INSERT INTO `package_schedule` VALUES (1,'Sigiriya Day - Morning Departure',1,'2025-10-05','2025-10-05',1,1,'Morning batch with sunrise view','Morning start with guide and breakfast stop.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(2,'Sigiriya Day - Evening Departure',1,'2025-10-05','2025-10-05',1,1,'Evening batch with sunset view','Evening start, includes sunset point visit.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(3,'Sigiriya Sunrise - Early Trek',2,'2025-11-05','2025-11-05',1,1,'Early morning group','Depart at 5 AM for sunrise hike.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(4,'Sigiriya Sunrise - Late Trek',2,'2025-11-06','2025-11-06',1,1,'Late start for relaxed visitors','Depart at 8 AM for guided hike.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(5,'Cultural Triangle - Morning Start',3,'2025-10-10','2025-10-15',5,5,'Standard group','Complete 5-day cultural experience.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(6,'Cultural Triangle - Afternoon Start',3,'2025-10-15','2025-10-20',5,5,'Second group batch','Same route with alternate timing.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(7,'Triangle Express - Early Batch',4,'2025-10-12','2025-10-14',3,3,'Morning travel group','Fast-paced cultural highlights.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(8,'Triangle Express - Late Batch',4,'2025-10-16','2025-10-18',3,3,'Afternoon start','Flexible schedule for late arrivals.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(9,'Yala Safari - Day 1 Start',5,'2025-11-10','2025-11-11',2,2,'Group 1','Includes one night luxury camp.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(10,'Yala Safari - Day 2 Start',5,'2025-11-12','2025-11-13',2,2,'Group 2','Second group departure.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(11,'Yala Premium - Group A',6,'2025-11-15','2025-11-16',2,2,'Private jeep package','Includes naturalist guide.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(12,'Yala Premium - Group B',6,'2025-11-17','2025-11-18',2,2,'Private jeep - alternate batch','Luxury vehicle upgrade.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(13,'Mirissa Escape - Weekend 1',7,'2025-10-20','2025-10-22',3,3,'First weekend group','Water sports & relaxation.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(14,'Mirissa Escape - Weekend 2',7,'2025-10-27','2025-10-29',3,3,'Second weekend batch','Similar itinerary.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(15,'Whale Watching - Early Morning',8,'2025-11-05','2025-11-05',1,1,'Morning sea trip','Best for whale spotting.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(16,'Whale Watching - Midday Trip',8,'2025-11-06','2025-11-06',1,1,'Midday calm sea ride','Ideal for photography.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(17,'Ella 3-Day - Group A',9,'2025-10-12','2025-10-14',3,3,'First batch','Explore Ella waterfalls.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(18,'Ella 3-Day - Group B',9,'2025-10-15','2025-10-17',3,3,'Second batch','Train and hiking combo.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(19,'Ella Train Tour - Weekday Batch',10,'2025-11-05','2025-11-06',2,2,'Weekday group','Tea estates & scenic ride.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(20,'Ella Train Tour - Weekend Batch',10,'2025-11-08','2025-11-09',2,2,'Weekend group','Same route with flexible timing.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(21,'Bentota Spa - Short Stay',11,'2025-10-10','2025-10-12',3,3,'Weekend spa retreat','Includes massage & yoga.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(22,'Bentota Spa - Extended',11,'2025-10-13','2025-10-15',3,3,'Extended session','Includes Ayurveda meals.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(23,'Luxury Wellness - Week 1',12,'2025-11-05','2025-11-09',5,5,'First group','Full 5-day rejuvenation.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(24,'Luxury Wellness - Week 2',12,'2025-11-12','2025-11-16',5,5,'Second group','Alternative schedule.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(25,'Kandy Half-Day - Morning',13,'2025-10-01','2025-10-01',1,1,'Morning temple tour','Includes dance show.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(26,'Kandy Half-Day - Evening',13,'2025-10-01','2025-10-01',1,1,'Evening dance show','Includes cultural performance.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(27,'Kandy Full - Day 1 Start',14,'2025-11-10','2025-11-10',1,1,'Full experience group 1','Temple & gardens tour.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(28,'Kandy Full - Day 2 Start',14,'2025-11-12','2025-11-12',1,1,'Second batch','Evening show included.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(29,'Knuckles Day - Hike 1',15,'2025-10-05','2025-10-05',1,1,'Morning trek','Includes lunch.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL),(30,'Knuckles Day - Hike 2',15,'2025-10-07','2025-10-07',1,1,'Afternoon trek','Includes dinner.',1,'2025-10-04 16:13:46',1,'2025-10-04 16:14:46',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_type`
--

DROP TABLE IF EXISTS `package_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `package_type_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_type`
--

LOCK TABLES `package_type` WRITE;
/*!40000 ALTER TABLE `package_type` DISABLE KEYS */;
INSERT INTO `package_type` VALUES (1,'All-Inclusive','Everything included – accommodation, meals, drinks, transport, and activities',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(2,'Full-Board','Accommodation with breakfast, lunch, and dinner included',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(3,'Half-Board','Accommodation with breakfast and either lunch or dinner included',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(4,'Bed & Breakfast','Accommodation with breakfast included only',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(5,'Day Trip','Single day excursion packages including activities and meals',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(6,'Weekend Getaway','Short stay package ideal for weekends or holidays',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(7,'Extended Stay','Longer duration package with discounted rates',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(8,'Custom','Fully customizable package based on traveler preferences',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(9,'Adventure','Adventure-focused package including hiking, safaris, and outdoor activities',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(10,'Luxury','Premium package with 5-star accommodation, private transport, and exclusive services',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(11,'Budget','Affordable travel package with basic amenities for cost-conscious travelers',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(12,'Honeymoon','Romantic package designed for couples with special activities and services',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL),(13,'Family','Family-friendly package with kid-friendly activities and accommodations',1,'2025-10-04 15:57:57',1,'2025-10-04 15:57:57',NULL,NULL,NULL);
/*!40000 ALTER TABLE `package_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_wishlist`
--

DROP TABLE IF EXISTS `package_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `package_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `package_id` (`package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `package_wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_wishlist_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_wishlist_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_wishlist`
--

LOCK TABLES `package_wishlist` WRITE;
/*!40000 ALTER TABLE `package_wishlist` DISABLE KEYS */;
INSERT INTO `package_wishlist` VALUES (1,1,1,1,'2025-11-29 05:01:57'),(2,1,2,1,'2025-11-29 05:01:57'),(3,1,3,1,'2025-11-29 05:01:57'),(4,2,4,1,'2025-11-29 05:01:57'),(5,2,5,1,'2025-11-29 05:01:57'),(6,2,6,2,'2025-11-29 05:01:57');
/*!40000 ALTER TABLE `package_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_wishlist_history`
--

DROP TABLE IF EXISTS `package_wishlist_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_wishlist_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `package_id` int NOT NULL,
  `wishlist_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `package_id` (`package_id`),
  KEY `wishlist_id` (`wishlist_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `package_wishlist_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_wishlist_history_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_wishlist_history_ibfk_3` FOREIGN KEY (`wishlist_id`) REFERENCES `package_wishlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_wishlist_history_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_wishlist_history`
--

LOCK TABLES `package_wishlist_history` WRITE;
/*!40000 ALTER TABLE `package_wishlist_history` DISABLE KEYS */;
INSERT INTO `package_wishlist_history` VALUES (1,1,1,1,1,'2025-11-29 05:01:57'),(2,1,2,2,2,'2025-11-29 05:01:57'),(3,2,4,4,1,'2025-11-29 05:01:57');
/*!40000 ALTER TABLE `package_wishlist_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packages`
--

DROP TABLE IF EXISTS `packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages` (
  `package_id` int NOT NULL AUTO_INCREMENT,
  `package_type_id` int DEFAULT NULL,
  `tour_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `total_price` decimal(10,2) DEFAULT NULL,
  `discount_percentage` decimal(5,2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `status` int NOT NULL,
  `hover_color` varchar(50) DEFAULT NULL,
  `min_person_count` int DEFAULT NULL,
  `max_person_count` int DEFAULT NULL,
  `price_per_person` decimal(10,2) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`package_id`),
  KEY `tour_id` (`tour_id`),
  KEY `status` (`status`),
  KEY `fk_package_type` (`package_type_id`),
  CONSTRAINT `fk_package_type` FOREIGN KEY (`package_type_id`) REFERENCES `package_type` (`id`),
  CONSTRAINT `packages_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  CONSTRAINT `packages_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages`
--

LOCK TABLES `packages` WRITE;
/*!40000 ALTER TABLE `packages` DISABLE KEYS */;
INSERT INTO `packages` VALUES (1,1,1,'Sigiriya Day Package','Full day tour with guide and transport',15000.00,10.00,'2025-10-01','2026-03-31','#FF6B6B',1,'#FF5252',2,8,7500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(2,1,1,'Sigiriya Sunrise Hike','Morning hike with breakfast and cultural guide',18000.00,12.00,'2025-11-01','2026-04-30','#FFA07A',1,'#FF8C69',2,6,9000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(3,1,2,'Cultural Triangle 5-Day','Complete cultural experience with expert guide',75000.00,12.00,'2025-10-01','2026-05-31','#AA96DA',1,'#9580D0',4,12,18750.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(4,1,2,'Triangle Express 3-Day','Quick exploration of Anuradhapura, Polonnaruwa, and Sigiriya',52000.00,8.00,'2025-10-01','2026-03-31','#9370DB',1,'#836FFF',2,10,17333.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(5,1,3,'Yala Safari Experience','Two-day safari with luxury camping',35000.00,5.00,'2025-11-01','2026-02-28','#95E1D3',1,'#7DD4C5',2,10,17500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(6,1,3,'Yala Premium Jeep Safari','Private jeep with naturalist guide',42000.00,10.00,'2025-11-01','2026-05-15','#20B2AA',1,'#2E8B57',2,6,21000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(7,1,4,'Mirissa Beach Escape','Relaxing beach holiday with water sports',40000.00,20.00,'2025-10-15','2026-03-15','#F38181',1,'#F06868',2,4,20000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(8,1,4,'Whale Watching Adventure','Half-day boat trip with breakfast included',18000.00,15.00,'2025-11-01','2026-04-30','#FFB6C1',1,'#FF69B4',2,10,9000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(9,1,5,'Ella 3-Day Adventure','Three days exploring Ella with accommodation',45000.00,15.00,'2025-10-01','2026-04-30','#4ECDC4',1,'#3BB8B0',2,6,22500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(10,1,5,'Ella Scenic Train & Tea Tour','2-day package including train and tea plantation visits',28000.00,10.00,'2025-11-01','2026-05-31','#48D1CC',1,'#40E0D0',2,6,14000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(11,1,6,'Bentota Spa Weekend','2-night stay with massages and yoga sessions',60000.00,15.00,'2025-10-01','2026-06-30','#FFD700',1,'#FFC107',2,4,30000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(12,1,6,'Luxury Wellness Retreat','5-day retreat with spa, meditation, and Ayurveda meals',125000.00,20.00,'2025-11-01','2026-05-31','#DAA520',1,'#B8860B',2,6,62500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(13,1,7,'Kandy Culture Half-Day','Temple visit and evening dance show',12000.00,10.00,'2025-10-01','2026-06-30','#BA55D3',1,'#9932CC',2,12,6000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(14,1,7,'Kandy Full Experience','City tour including temple, dance, and botanical gardens',22000.00,15.00,'2025-11-01','2026-04-30','#8A2BE2',1,'#6A5ACD',2,10,11000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(15,1,8,'Knuckles Day Hike','One-day trek with guide and meals',18000.00,10.00,'2025-10-01','2026-05-31','#32CD32',1,'#228B22',2,8,9000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(16,1,8,'Knuckles Camping Adventure','2-day trek with overnight camping',32000.00,12.00,'2025-11-01','2026-04-30','#006400',1,'#2E8B57',2,6,16000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(17,1,9,'Wilpattu Luxury Safari','3-day safari with luxury lodge stay',95000.00,18.00,'2025-10-15','2026-05-31','#8B4513',1,'#A0522D',2,6,47500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(18,1,9,'Wilpattu Classic Safari','2-day safari with tented camping',55000.00,10.00,'2025-11-01','2026-04-30','#D2691E',1,'#CD853F',2,8,27500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(19,1,10,'Surf Beginner Package','3-day surfing lessons with equipment',38000.00,10.00,'2025-05-01','2025-09-30','#00CED1',1,'#20B2AA',2,6,19000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(20,1,10,'Surf Pro Adventure','5-day advanced surfing experience with beach parties',68000.00,12.00,'2025-05-01','2025-09-30','#1E90FF',1,'#4169E1',2,8,34000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(21,1,11,'Ayurveda Short Retreat','3-day healing and detox program',72000.00,15.00,'2025-11-01','2026-05-31','#FF8C00',1,'#FF7F50',2,6,36000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(22,1,11,'Deep Healing Package','7-day full Ayurveda program with meals and treatments',160000.00,20.00,'2025-11-01','2026-05-31','#FF6347',1,'#FF4500',2,10,80000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(23,1,12,'Jaffna 2-Day Heritage','Explore temples, forts, and taste authentic cuisine',45000.00,10.00,'2025-10-01','2026-06-30','#FF1493',1,'#DB7093',2,10,22500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(24,1,12,'Jaffna Extended 4-Day','Deeper cultural and culinary exploration',85000.00,12.00,'2025-10-01','2026-06-30','#C71585',1,'#8B008B',2,8,42500.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(25,1,13,'Rafting Day Package','Half-day white water rafting experience',18000.00,10.00,'2025-10-01','2026-06-30','#40E0D0',1,'#48D1CC',2,10,9000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(26,1,13,'Rafting + Canyoning Combo','Adventure combo with rafting and canyoning',32000.00,15.00,'2025-11-01','2026-05-31','#4682B4',1,'#5F9EA0',2,8,16000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(27,1,14,'Snorkeling Day Trip','Pigeon Island snorkeling with guide and boat transfer',22000.00,10.00,'2025-05-01','2025-09-30','#00FA9A',1,'#00FF7F',2,12,11000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(28,1,14,'Trinco Beach Stay 3-Day','Beach resort with snorkeling and dolphin watching',60000.00,15.00,'2025-05-01','2025-09-30','#3CB371',1,'#2E8B57',2,8,30000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(29,1,15,'Elephant Day Safari','Full-day jeep safari in Udawalawe',20000.00,8.00,'2025-11-01','2026-05-31','#B22222',1,'#DC143C',2,12,10000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL),(30,1,15,'Udawalawe Overnight Safari','2-day experience with camping near park',42000.00,12.00,'2025-11-01','2026-05-31','#CD5C5C',1,'#8B0000',2,6,21000.00,'2025-10-04 16:03:21',1,'2025-10-14 14:20:14',NULL,NULL,NULL);
/*!40000 ALTER TABLE `packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `page` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `slug` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'Home','home','2025-09-26 14:56:21','2025-09-26 14:56:21'),(2,'About','about','2025-09-26 14:56:21','2025-09-26 14:56:21');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page_component`
--

DROP TABLE IF EXISTS `page_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `page_component` (
  `id` int NOT NULL AUTO_INCREMENT,
  `page_id` int NOT NULL,
  `component_id` int NOT NULL,
  `order_index` int DEFAULT '0',
  `is_visible` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `page_id` (`page_id`),
  KEY `component_id` (`component_id`),
  CONSTRAINT `page_component_ibfk_1` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE,
  CONSTRAINT `page_component_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `component` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page_component`
--

LOCK TABLES `page_component` WRITE;
/*!40000 ALTER TABLE `page_component` DISABLE KEYS */;
INSERT INTO `page_component` VALUES (1,1,1,1,1,'2025-09-26 14:59:14','2025-09-26 14:59:42'),(2,1,2,2,1,'2025-09-26 14:59:14','2025-09-26 14:59:42'),(3,1,3,3,1,'2025-09-26 14:59:14','2025-09-26 14:59:42'),(4,2,1,1,1,'2025-09-26 14:59:14','2025-09-26 14:59:42'),(5,2,2,2,1,'2025-09-26 14:59:14','2025-09-26 14:59:42'),(6,2,3,3,1,'2025-09-26 14:59:14','2025-09-26 14:59:42');
/*!40000 ALTER TABLE `page_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner_status`
--

DROP TABLE IF EXISTS `partner_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partner_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `partner_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner_status`
--

LOCK TABLES `partner_status` WRITE;
/*!40000 ALTER TABLE `partner_status` DISABLE KEYS */;
INSERT INTO `partner_status` VALUES (1,'ACTIVE','Partner is active',1,'2025-09-20 10:53:24',1,'2025-09-20 10:53:24',NULL,NULL,NULL),(2,'INACTIVE','Partner is inactive',2,'2025-09-20 10:53:24',1,'2025-09-20 10:53:24',NULL,NULL,NULL),(3,'TERMINATED','Partner is terminated',3,'2025-09-20 10:53:24',1,'2025-09-20 10:53:24',NULL,NULL,NULL);
/*!40000 ALTER TABLE `partner_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partners`
--

DROP TABLE IF EXISTS `partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `company_name` varchar(150) NOT NULL,
  `company_description` text,
  `company_logo` varchar(255) DEFAULT NULL,
  `company_website_url` varchar(255) DEFAULT NULL,
  `agreement` text,
  `partner_status_id` int NOT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `partner_status_id` (`partner_status_id`),
  CONSTRAINT `partners_ibfk_1` FOREIGN KEY (`partner_status_id`) REFERENCES `partner_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partners`
--

LOCK TABLES `partners` WRITE;
/*!40000 ALTER TABLE `partners` DISABLE KEYS */;
INSERT INTO `partners` VALUES (1,'partner-1','Tech Solutions Ltd','Leading technology solutions provider.','/images/partners-images/partner-1.webp','https://techsolutions.com','Standard Agreement',1,'2025-01-01','2025-12-31','2025-09-20 10:53:24',1,'2025-10-09 15:44:26',NULL,NULL,NULL),(2,'partner-2','Green Energy Corp','Renewable energy solutions for a sustainable future.','/images/partners-images/partner-2.jpg','https://greenenergy.com','Standard Agreement',1,'2025-03-01','2026-02-28','2025-09-20 10:53:24',1,'2025-10-09 15:44:26',NULL,NULL,NULL),(3,'partner-3','HealthPlus Inc','Innovative healthcare services and products.','/images/partners-images/partner-3.jpg','https://healthplus.com','Standard Agreement',1,'2025-05-01','2026-04-30','2025-09-20 10:53:24',1,'2025-10-09 15:44:26',NULL,NULL,NULL),(4,'partner-4','HealthPlus Inc','Innovative healthcare services and products.','/images/partners-images/partner-4.png','https://healthplus.com','Standard Agreement',1,'2025-05-01','2026-04-30','2025-09-20 10:53:24',1,'2025-09-20 11:39:45',NULL,NULL,NULL),(5,'partner-5','HealthPlus Inc','Innovative healthcare services and products.','/images/partners-images/partner-5.png','https://healthplus.com','Standard Agreement',1,'2025-05-01','2026-04-30','2025-09-20 10:53:24',1,'2025-09-20 11:39:45',NULL,NULL,NULL),(6,'partner-6','HealthPlus Inc','Innovative healthcare services and products.','/images/partners-images/partner-6.webp','https://healthplus.com','Standard Agreement',1,'2025-05-01','2026-04-30','2025-09-20 10:53:24',1,'2025-09-20 11:39:45',NULL,NULL,NULL),(7,'partner-7','HealthPlus Inc','Innovative healthcare services and products.','/images/partners-images/partner-7.png','https://healthplus.com','Standard Agreement',1,'2025-05-01','2026-04-30','2025-09-20 10:53:24',1,'2025-09-20 11:39:45',NULL,NULL,NULL);
/*!40000 ALTER TABLE `partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_methods`
--

DROP TABLE IF EXISTS `payment_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_methods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `payment_methods_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_methods`
--

LOCK TABLES `payment_methods` WRITE;
/*!40000 ALTER TABLE `payment_methods` DISABLE KEYS */;
INSERT INTO `payment_methods` VALUES (1,'CREDIT_CARD','Payment via credit card',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,'DEBIT_CARD','Payment via debit card',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,'BANK_TRANSFER','Payment via bank transfer',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,'CASH','Payment in cash',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,'DIGITAL_WALLET','Payment via digital wallet',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `payment_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_status`
--

DROP TABLE IF EXISTS `payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `payment_status_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
INSERT INTO `payment_status` VALUES (1,'PENDING','Payment is pending',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,'PROCESSING','Payment is being processed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,'COMPLETED','Payment has been completed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,'FAILED','Payment has failed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,'REFUNDED','Payment has been refunded',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `payment_reference` varchar(100) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `payment_method_id` int NOT NULL,
  `payment_status_id` int NOT NULL,
  `installment_number` int DEFAULT '1',
  `total_installments` int DEFAULT '1',
  `payment_date` timestamp NULL DEFAULT NULL,
  `due_date` date NOT NULL,
  `transaction_id` varchar(200) DEFAULT NULL,
  `gateway_response` text,
  `gateway_name` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_reference` (`payment_reference`),
  KEY `payment_method_id` (`payment_method_id`),
  KEY `payment_status_id` (`payment_status_id`),
  KEY `idx_payments_booking_id` (`booking_id`),
  KEY `idx_payments_reference` (`payment_reference`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`),
  CONSTRAINT `payments_ibfk_3` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,1,'PAY001',605.00,1,3,1,2,'2024-01-16 05:00:00','2024-01-20','TXN001',NULL,'Stripe','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,1,'PAY002',605.00,1,1,2,2,NULL,'2024-02-15',NULL,NULL,'Stripe','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,2,'PAY003',1262.50,3,3,1,2,'2024-01-21 08:50:00','2024-01-25','TXN002',NULL,'Bank Transfer','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,2,'PAY004',1262.50,3,2,2,2,NULL,'2024-03-10',NULL,NULL,'Bank Transfer','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,3,'PAY005',820.00,2,3,1,1,'2024-02-02 03:45:00','2024-02-05','TXN003',NULL,'PayPal','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(6,4,'PAY006',1815.00,1,3,1,1,'2024-02-11 11:15:00','2024-02-15','TXN004',NULL,'Stripe','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(7,5,'PAY007',1515.00,4,3,1,1,'2024-02-16 06:00:00','2024-02-20','TXN005',NULL,'Cash','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popular_destination`
--

DROP TABLE IF EXISTS `popular_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `popular_destination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destination_id` int NOT NULL,
  `rating` decimal(3,2) NOT NULL,
  `popularity` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_id` (`destination_id`),
  CONSTRAINT `popular_destination_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popular_destination`
--

LOCK TABLES `popular_destination` WRITE;
/*!40000 ALTER TABLE `popular_destination` DISABLE KEYS */;
INSERT INTO `popular_destination` VALUES (1,1,4.80,95,'2025-10-04 19:34:15',1),(2,2,4.70,90,'2025-10-04 19:34:15',1),(3,3,4.60,85,'2025-10-04 19:34:15',1),(4,4,4.50,80,'2025-10-04 19:34:15',1),(5,5,4.40,70,'2025-10-04 19:34:15',1),(6,6,4.30,65,'2025-10-04 19:34:15',1);
/*!40000 ALTER TABLE `popular_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privileges`
--

DROP TABLE IF EXISTS `privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privileges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privileges`
--

LOCK TABLES `privileges` WRITE;
/*!40000 ALTER TABLE `privileges` DISABLE KEYS */;
INSERT INTO `privileges` VALUES (5,'ADMIN_ACCESS'),(3,'DELETE_PRIVILEGE'),(1,'READ_PRIVILEGE'),(4,'USER_MANAGEMENT'),(2,'WRITE_PRIVILEGE');
/*!40000 ALTER TABLE `privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction_type`
--

DROP TABLE IF EXISTS `reaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reaction_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reaction_type_status` (`status_id`),
  CONSTRAINT `fk_reaction_type_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction_type`
--

LOCK TABLES `reaction_type` WRITE;
/*!40000 ALTER TABLE `reaction_type` DISABLE KEYS */;
INSERT INTO `reaction_type` VALUES (1,'LIKE','Thumbs up','?',1,'2025-09-24 04:18:47',1,'2025-09-24 04:18:47',NULL,NULL,NULL),(2,'LOVE','Love it','❤️',1,'2025-09-24 04:18:47',1,'2025-09-24 04:18:47',NULL,NULL,NULL),(3,'WOW','Surprised','?',1,'2025-09-24 04:18:47',1,'2025-09-24 04:18:47',NULL,NULL,NULL),(4,'HELPFUL','Helpful review','?',1,'2025-09-24 04:18:47',1,'2025-09-24 04:18:47',NULL,NULL,NULL);
/*!40000 ALTER TABLE `reaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_tokens`
--

DROP TABLE IF EXISTS `refresh_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_tokens` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `token` varchar(500) NOT NULL,
  `expiry_date` timestamp NOT NULL,
  `revoked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `refresh_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_tokens`
--

LOCK TABLES `refresh_tokens` WRITE;
/*!40000 ALTER TABLE `refresh_tokens` DISABLE KEYS */;
INSERT INTO `refresh_tokens` VALUES (26,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM2NTM1OTQsImV4cCI6MTc2MzY1NDQ5NH0.3qinj9ZMlkTIMprDv5pqKMnrHhyv8NUoQyNVy6a-M7I','2025-11-20 16:01:34',1),(27,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM2NTQ0MDAsImV4cCI6MTc2MzY1NTMwMH0.bi-y252MMi9DfiBegArvmI1F1c_8_g-6qQ-LzDVU_0Y','2025-11-20 16:15:00',1),(28,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4MjI3OTgsImV4cCI6MTc2MzgyMjg1OH0.sCvnEAyl0D3KsCiX3nQg5CJ4fB6X4WpOgB0NLeJBt_g','2025-11-22 14:47:38',1),(29,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4MjMwMDIsImV4cCI6MTc2MzgyMzA2Mn0.KPCqSCkemPV9v-npETR7lPxU-e2S7VOMe5r9OM_eK64','2025-11-22 14:51:02',1),(30,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODA2ODAsImV4cCI6MTc2Mzg4MDc0MH0.7RYgyaSegGVNTH5gUGdbvLVCZResgLc3KYlAn97fAac','2025-11-23 06:52:20',1),(31,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODE0NjgsImV4cCI6MTc2Mzg4MTUyOH0.DJrel50EZphYQOf7IhHso316QuhoxUKI95QlzJlc_aU','2025-11-23 07:05:28',1),(32,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODIxNzQsImV4cCI6MTc2Mzg4MjIzNH0.2VfNHDKNpYuSfBedD4aUNbUiwkfoNEYnObujb0LZkNo','2025-11-23 07:17:14',1),(33,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODIyMjQsImV4cCI6MTc2Mzg4MjI4NH0.o3fSrH8bzEjxKFosKV-vtYzvl3buWk0jP9HWvmebxCE','2025-11-23 07:18:04',1),(34,6,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHVkaWxzaGFuIiwiaWF0IjoxNzYzODgyNjE5LCJleHAiOjE3NjM4ODI2Nzl9.CFYRSGxjqJcHWs0JqkZ1zZTfwBuVTQr-xTnodQ8lfWU','2025-11-23 07:24:39',1),(35,6,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHVkaWxzaGFuIiwiaWF0IjoxNzYzODgyNjM1LCJleHAiOjE3NjM4ODI2OTV9.3gmnkmpzTR1N2UyySKhDMkYghfORLqBrWrfBu5trC4I','2025-11-23 07:24:55',1),(36,6,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHVkaWxzaGFuIiwiaWF0IjoxNzYzODgyNjY1LCJleHAiOjE3NjM4ODI3MjV9.HujFDr4ORDERRquRvSML9MOZt_73tJXdak1U07jj2hU','2025-11-23 07:25:25',1),(37,6,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHVkaWxzaGFuIiwiaWF0IjoxNzYzODgyNjk1LCJleHAiOjE3NjM4ODI3NTV9.gqfn4AbnkvV07MZnuAYiE187gX8EZn-qRcCNjUordFw','2025-11-23 07:25:55',1),(38,6,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHVkaWxzaGFuIiwiaWF0IjoxNzYzODgyNzQ1LCJleHAiOjE3NjM4ODI4MDV9.XN8QysDjpv9z3ZGOcPp9OyK8fbaaHBBv0SWWeY-BtVs','2025-11-23 07:26:45',0),(39,8,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhcyIsImlhdCI6MTc2Mzg4MzE2NywiZXhwIjoxNzYzODgzMjI3fQ.swPkEfRHXJFD92PYwoKnzcuow160nmE0N7kOWnEqqQE','2025-11-23 07:33:47',0),(40,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODMzOTUsImV4cCI6MTc2Mzg4MzQ1NX0.BSLAH6A1tFRaM2cNYoVtPZ-gBvGWXQLDhl_yzlKduik','2025-11-23 07:37:35',1),(41,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODM1MDgsImV4cCI6MTc2Mzg4MzU2OH0.Gw4nvMCfJgiOVsCLhuyZbs2vTRtskzweY1HYhYJSPks','2025-11-23 07:39:28',1),(42,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODM2MTksImV4cCI6MTc2Mzg4MzY3OX0.GdJkfQkr4XmxJA75F4VOEfDTVw6qxbcrpeBsenObh5M','2025-11-23 07:41:19',1),(43,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODM3MjEsImV4cCI6MTc2Mzg4Mzc4MX0.CvZRdHFDp9y2Rw9B4tNv8JcXsxrsHMXLCasHH9nUSdo','2025-11-23 07:43:01',1),(44,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM4ODM4NDIsImV4cCI6MTc2Mzg4MzkwMn0.HEtdtzNJB9ciwrJPazyuuLugkGMNOAXBuhmk9BmIBdg','2025-11-23 07:45:02',1),(45,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODYxODQsImV4cCI6MTc2Mzk4NjI0NH0.C-NP-iSuIkB4KzGIV5Wot-QHI3Qmb-JQcUR_mRgjfD0','2025-11-24 12:10:44',1),(46,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODYzNjcsImV4cCI6MTc2Mzk4Njk2N30.uEEzuj1pYN8xXUnbz5bC21BMGJxO5f5B2RHA6oHDjFY','2025-11-24 12:22:47',1),(47,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODY2NTIsImV4cCI6MTc2Mzk4NzI1Mn0.tcdompinsxG9zwmojfWe1SljDYHdO04SvHrtEAAY4GQ','2025-11-24 12:27:32',1),(48,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODY4NjYsImV4cCI6MTc2Mzk4NzQ2Nn0.0xVxDQ_rbGfiCHYyix_NKoxQR6SZeERe9M5SL_3bseg','2025-11-24 12:31:06',1),(49,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODY5NjcsImV4cCI6MTc2Mzk4NzU2N30.epmTLVsuEUtXRunf9bkcY-mkiDiW1rhBE3fvSA82FCE','2025-11-24 12:32:47',1),(50,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODcyNjcsImV4cCI6MTc2Mzk4Nzg2N30.fyJ9x8RhZ1wPUsocY7SvQ6u_hgM_DaDEcmTCJa5IfhA','2025-11-24 12:37:47',1),(51,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODc1MTEsImV4cCI6MTc2Mzk4ODExMX0.nmvZgUgSkLvkCGnW9brsSSI5OR0biqeAcxS5zzSZcbQ','2025-11-24 12:41:51',1),(52,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODgyNTIsImV4cCI6MTc2Mzk4ODg1Mn0.nMzQI89A48aV3qXMlb8xy6RNjkUDnHjSNm2jbuuFNFo','2025-11-24 12:54:12',1),(53,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5ODkxNDYsImV4cCI6MTc2Mzk4OTc0Nn0.gkZ17lk4MGV9QrKecejBpIIbhfyct-DtxiTU_4rFJac','2025-11-24 13:09:06',1),(54,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTI0NjcsImV4cCI6MTc2Mzk5MzA2N30.vSTiPTx5gYV5GQ3u2MXG-Gp9oneFR8AKZh8JvSiI1qg','2025-11-24 14:04:27',1),(55,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTQyOTMsImV4cCI6MTc2Mzk5NDg5M30.htgJaCXKy-cREB4HgUMTMFpTpR_-o5OacXcHQZZapy4','2025-11-24 14:34:53',1),(56,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTU1MjMsImV4cCI6MTc2Mzk5NjEyM30.AKDEr7rYyP1YqOnGfj88ugLdP_BGliArzJ2z-uzO3BA','2025-11-24 14:55:23',1),(57,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTY4MzYsImV4cCI6MTc2Mzk5NzQzNn0.HDQu6MpfMaskqMZNAX0tmrfHieNuWXTgbOd4p_PfYXE','2025-11-24 15:17:16',1),(58,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTc5OTMsImV4cCI6MTc2Mzk5ODU5M30.N6Vt-96bZeMMLLjPvKk3Ub-ShLSRZ4da8KaxfiRmsAQ','2025-11-24 15:36:33',1),(59,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjM5OTkzOTYsImV4cCI6MTc2Mzk5OTk5Nn0.NM53-cfkzkcj1UkgrAk3quL9PmEWVme_70L4G1GxfOo','2025-11-24 15:59:56',1),(60,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDA0NjYsImV4cCI6MTc2NDAwMTA2Nn0.5OQhYlwyAU_4T6UIOmSuQzIq8L5TM46ggSxaAgC7JsI','2025-11-24 16:17:46',1),(61,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDE3ODEsImV4cCI6MTc2NDAwMjM4MX0.50hAgu1y0m_fnWEZiqv6OgOFCn8Uf6R-B3dKoE-hadU','2025-11-24 16:39:41',1),(62,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDIwMjYsImV4cCI6MTc2NDAwMjYyNn0.6AduwcN8_f-o0vAXbcmjWP0D63uSXQik1a2lPu4sD80','2025-11-24 16:43:46',1),(63,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDM1NDUsImV4cCI6MTc2NDAwNDE0NX0.vhQUoGuFEjxmHgU5ktD3sL4YM2fxtfUT3kW2UNgaOcM','2025-11-24 17:09:05',1),(64,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDM5NDEsImV4cCI6MTc2NDAwNDU0MX0.GzE37PZJci3xvhej47pf46y2SQlSVd9eh_xkzjLzUVg','2025-11-24 17:15:41',1),(65,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwMDQwNDYsImV4cCI6MTc2NDAwNDY0Nn0.4NLzPwr8JQhpPwDclE7GgThxPLAt56HAUQaVaIoddSM','2025-11-24 17:17:26',1),(66,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwODU5OTYsImV4cCI6MTc2NDA4NjU5Nn0.DPR_ddaKSp4W0azscCtuNFRuZe9IPH7XcvjyX-4XY3I','2025-11-25 16:03:16',1),(67,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwODc0MTIsImV4cCI6MTc2NDA4ODAxMn0.jq7Sf-IXDZju9dWyUMIIzsOUAEJvugKNygn9Mr5RKhk','2025-11-25 16:26:52',1),(68,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQwODgyMDMsImV4cCI6MTc2NDA4ODgwM30.Sw8lGlhJ9d8suznwp7fDuJD_-_vkb_JSVmJATR_eUFY','2025-11-25 16:40:03',1),(69,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxMjk0NjEsImV4cCI6MTc2NDEzMDA2MX0.6mPHOf0d88oylrYKPIZXjfzW8DXBnpxL9kRhBn94h3U','2025-11-26 04:07:41',1),(70,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDMwMTIsImV4cCI6MTc2NDE0MzYxMn0.6Z1BYjQl_sgc4wpHjrNBCGrMo-ui4NBCqyrQWXWLgZw','2025-11-26 07:53:32',1),(71,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDMxMzAsImV4cCI6MTc2NDE0MzczMH0.K1lw5oFN1GF57cu6nuyXxAApEP7mkZStTeWL0mFMcFs','2025-11-26 07:55:30',1),(72,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDM3NDQsImV4cCI6MTc2NDE0NDM0NH0.UBc8zYSSnxVPtDq-UYmFrYR-arnFs2FQ0L_2AEFKayo','2025-11-26 08:05:44',1),(73,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDM4MzYsImV4cCI6MTc2NDE0NDQzNn0.sIuyKn2dt39CgCSeGk8QJSTK3uwALroDkfJhGfjl0fE','2025-11-26 08:07:16',1),(74,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDQxNDksImV4cCI6MTc2NDE0NDc0OX0.41qU9Mke_1l_sTw5-hzAJdX22qbmkjkf3ZotPOoC4AU','2025-11-26 08:12:29',1),(75,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNDQxNzcsImV4cCI6MTc2NDE0NDc3N30.J_Lt-kSmdkGETnfPr8Ifpp24LwhPAnl5MWuPjt6O6uI','2025-11-26 08:12:57',1),(76,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNjI3NTAsImV4cCI6MTc2NDE2MzM1MH0.WzaPBpcgMGkVrIk8o7Vq8sAHpvMsZPEqskyBQpd18BY','2025-11-26 13:22:30',1),(77,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQxNjQwNTgsImV4cCI6MTc2NDE2NDY1OH0._9VWp8M50VXuFrivDlwbKPBjrAsEL3oN41tYjVhNb3k','2025-11-26 13:44:18',1),(78,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzMjIzMjIsImV4cCI6MTc2NDMyMjkyMn0.3UnVUYdf6Rodz1pXyvu49d47Saxbb2YnnBFKMvieFW8','2025-11-28 09:42:02',1),(79,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzMjQ3MjcsImV4cCI6MTc2NDMyNTMyN30.cb7NBD_oQFp3mZki9jArsnyZDyhI7cer1ePUpS39_c0','2025-11-28 10:22:07',1),(80,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzMjY5NjksImV4cCI6MTc2NDMyNzU2OX0.lfjVQBJShUKp7oM4vQYRxTGbuObE6Y5tfRBCwrRoqf4','2025-11-28 10:59:29',1),(81,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzMjg5MjQsImV4cCI6MTc2NTE5MjkyNH0.0fz6dj59epjNMzQd8EIRGJXPk9Nh2407Yep4eEVzcBk','2025-12-08 11:22:04',1),(82,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDI5MzYsImV4cCI6MTc2NTIwNjkzNn0.J6r4B0rrmcvfHKcPgAJe_GepOd6HaFl4LAAvml8uNzs','2025-12-08 15:15:36',1),(83,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDUwNjMsImV4cCI6MTc2NTIwOTA2M30.kWtBHrMfrA-iJ1CchT2jMm_s9TXDkTCgPcOFx-l-Gns','2025-12-08 15:51:03',1),(84,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDUyNDEsImV4cCI6MTc2NTIwOTI0MX0.uAT2LlFYYm5vZn-IlxP9xr7XgeghBQFSXFb6_0cZUvo','2025-12-08 15:54:01',1),(85,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDYwMDcsImV4cCI6MTc2NTIxMDAwN30.cvR9mhYtFmgk5sILSK338U8GnACyDg8dLRAfE1MTRnQ','2025-12-08 16:06:47',1),(86,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDY4OTAsImV4cCI6MTc2NTIxMDg5MH0.TfYkEFMyFNvjbZFV2ubr7XW0GYgVa0DthjKjRnz-3ys','2025-12-08 16:21:30',1),(87,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDY5MTksImV4cCI6MTc2NTIxMDkxOX0.0tvkIon_9ZZGGrJWhbSROxivZ3-aZkxqLJJogL1KZYU','2025-12-08 16:21:59',1),(88,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNDk1NTEsImV4cCI6MTc2NTIxMzU1MX0.IcUKNkX8sZwrS0xNARH2nav2szMax0Sjh8v5azXntFQ','2025-12-08 17:05:51',1),(89,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNTIxNTMsImV4cCI6MTc2NTIxNjE1M30.nEQ0r-WLWMnCWBEjq673ftqwI3OldwzrRTS-hYO_lZs','2025-12-08 17:49:13',1),(90,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNTIxNzksImV4cCI6MTc2NTIxNjE3OX0.-SOR5FSfG8rtVLXLJkHhc8igyjEHdNc9krN6U4xSZEo','2025-12-08 17:49:39',1),(91,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNTU5NjgsImV4cCI6MTc2NTIxOTk2OH0.WUZeHvKK372l1SmOmG9_cngmXX7fitdDCe3BI5S9PuM','2025-12-08 18:52:48',1),(92,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzNTYwMTAsImV4cCI6MTc2NTIyMDAxMH0.g7Byzr37b5vLC6kIq-U9UfFSSeyshqZixYWRzJ9T8yo','2025-12-08 18:53:30',1),(93,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzODY3MTcsImV4cCI6MTc2NTI1MDcxN30.hzxX4l0-9zK5VJJEaqxrjAJSzi4MychVch3M7BmJclQ','2025-12-09 03:25:17',1),(94,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzODc2MTUsImV4cCI6MTc2NTI1MTYxNX0.mq-lw1uu0kDHOsnEsHJ18ghsXdr4P-ZCyp2xmX4g6aw','2025-12-09 03:40:15',1),(95,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzODg0MDgsImV4cCI6MTc2NTI1MjQwOH0.OPjMKH-KfrRMUVgcumhD0NrTfhYCStlhsQKkIHfYGQE','2025-12-09 03:53:28',1),(96,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzODg3MDgsImV4cCI6MTc2NTI1MjcwOH0.9xXs670vZCqALdnFCRF79OVKgxJFbGWDclpn51gS3oY','2025-12-09 03:58:28',1),(97,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzOTExNzAsImV4cCI6MTc2NTI1NTE3MH0.prSV2jcuDEky-LGD28hiyIQyJxM-v-fyrLmX2pJZgZE','2025-12-09 04:39:30',1),(98,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzOTI2MzksImV4cCI6MTc2NTI1NjYzOX0.dn_G0UhRQDusPO7j--2BdB2jyXQPYqmM6T6SPo2c7Hg','2025-12-09 05:03:59',1),(99,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQzOTI2ODcsImV4cCI6MTc2NTI1NjY4N30.JmSuECAG11_1BvHTOQju3z9MqOg49avvLx32sjjJjNE','2025-12-09 05:04:47',1),(100,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ0MDQ1ODgsImV4cCI6MTc2NTI2ODU4OH0.GWN5gzxBl54ELFPvhfwadG5700CusBMVYbg7Kjjz22M','2025-12-09 08:23:08',1),(101,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ0MDUyNTgsImV4cCI6MTc2NTI2OTI1OH0.I3l2V0Qts2UH8Il5xB9OG87Zmuwhu-J0XwFNfcXN7Ao','2025-12-09 08:34:18',1),(102,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ0MDU2MDEsImV4cCI6MTc2NTI2OTYwMX0.2KmMVqSgBkkYqPmCdynvjjCJO9IpTxdZOMnV-LtUwGQ','2025-12-09 08:40:01',1),(103,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ0Nzc5MzAsImV4cCI6MTc2NTM0MTkzMH0.m0eY7z6vuLfMmCA3-bR553YToAnorQzIcR4xkkcnEsI','2025-12-10 04:45:30',1),(104,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1MjM5NjUsImV4cCI6MTc2NTM4Nzk2NX0.kbbYiXmvF7sn4HgB_FSmy5b9yxJHXF-gMHOzADdmshE','2025-12-10 17:32:45',1),(105,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1MjY5NzAsImV4cCI6MTc2NTM5MDk3MH0.aQKv5WD0Bbu3Hheq9HV1XuiMKDuJjErJUplbaPkwbvg','2025-12-10 18:22:50',1),(106,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1MjczNjEsImV4cCI6MTc2NTM5MTM2MX0.nqbdXzRo2HwPTskRk4FHJvdQG50_Yy__uzWHOh9t5Xk','2025-12-10 18:29:21',1),(107,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NTg1OTAsImV4cCI6MTc2NTQyMjU5MH0.DS5KRiIEajF5A4NYyh1B4UouKAJhrgPVVrLkbdQa6yc','2025-12-11 03:09:50',1),(108,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjIzMTMsImV4cCI6MTc2NTQyNjMxM30.vv1hjwYaKb8tLSz5w6cRgE3fonqhGuTHh4tVAVa6n7c','2025-12-11 04:11:53',1),(109,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjIzNzcsImV4cCI6MTc2NTQyNjM3N30.W7f5yN_yb2WTNqhZEqNnvymTYjlSnzE1_9aS2smjykM','2025-12-11 04:12:57',1),(110,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjMxMjIsImV4cCI6MTc2NTQyNzEyMn0.zLOU_ipQQiM4Rf9sodRRB2G6XPKDKmxRo7SVuuS9XyE','2025-12-11 04:25:22',1),(111,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjQxNzYsImV4cCI6MTc2NTQyODE3Nn0.z_rKmbac_mxQtGkW963ZmK-5CIk9ZA-STcoLRTtur1s','2025-12-11 04:42:56',1),(112,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjQyOTcsImV4cCI6MTc2NTQyODI5N30.KcYxe20wlBjoG8ynyUsVEZi6VS3fX5AlvAfES95a2IM','2025-12-11 04:44:57',1),(113,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjU2MDksImV4cCI6MTc2NTQyOTYwOX0.VGqLJj_A2DV0CzQYWY3LfEomG1MxraQBIQ2X-S4I6ro','2025-12-11 05:06:49',1),(114,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjU5ODcsImV4cCI6MTc2NTQyOTk4N30.xa1sfYJeKik_CHJ7EYnw6Dx6394QBaCAgdnDF_OGHuc','2025-12-11 05:13:07',1),(115,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1Njc5OTYsImV4cCI6MTc2NTQzMTk5Nn0.s2Gt9-5e-XAZJQqiHmmF0Sj-mVbBeadeiaR8f8Bwqp8','2025-12-11 05:46:36',1),(116,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ1NjgxMDcsImV4cCI6MTc2NTQzMjEwN30.O743mR3kE58mZq9oYbHqslsi2VfKX449XmRqekcWMYA','2025-12-11 05:48:27',1),(117,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDIyNjcsImV4cCI6MTc2NTQ2NjI2N30.tP9x0ofyia2fauSj4Wyj487a1vCauoZzwv_eqMRfE1A','2025-12-11 15:17:47',1),(118,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDI0OTksImV4cCI6MTc2NTQ2NjQ5OX0.NiK0LGRqFik2_twfUSarB-lMZXWLrRhBbwYFrmVh7xg','2025-12-11 15:21:39',1),(119,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDM1NDgsImV4cCI6MTc2NTQ2NzU0OH0.OF8kWqcIJJNa4ZoCSBo4DZ5zLcxfkQCyfJs-986q5WE','2025-12-11 15:39:08',1),(120,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDM1OTMsImV4cCI6MTc2NTQ2NzU5M30.x6LO0BAtlJiVjpEuxBQ4CWBFrxctD7vw8NwWZFNMxKA','2025-12-11 15:39:53',1),(121,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDUxNjksImV4cCI6MTc2NTQ2OTE2OX0.4pPeL4Qez2bvJkN2BstjaFVOqLY7H0OuJwDjzeDFnHY','2025-12-11 16:06:09',1),(122,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2MDY0NzksImV4cCI6MTc2NTQ3MDQ3OX0.tLeun6nAwPfLpP9uIhCRUlMry8Z9oQOLnNgxNZ6WmoU','2025-12-11 16:27:59',1),(123,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2NzUyMjIsImV4cCI6MTc2NTUzOTIyMn0.2H1bf3XC6DMjD03Mv5RPMoTNGqGgvu9q9e1o2tM9d2Q','2025-12-12 11:33:42',1),(124,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2Nzg1OTEsImV4cCI6MTc2NTU0MjU5MX0.OuLW6-NqRRTZfQab-rYMUmFdc5Kh00FScs9aI-_hYqg','2025-12-12 12:29:51',1),(125,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2Nzg2MzcsImV4cCI6MTc2NTU0MjYzN30.kLHSetcdAUrPH0NJlorlsD7O15bioY6D7mObxoktTyE','2025-12-12 12:30:37',1),(126,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2NzkxNTYsImV4cCI6MTc2NTU0MzE1Nn0.L7mOTFqtmSVm022q1ZuFOYtrg3m1xtA9W0NtUi2yrOQ','2025-12-12 12:39:16',1),(127,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2NzkzNDQsImV4cCI6MTc2NTU0MzM0NH0.XnLrd1qMlzb4X9Ktb6koH-ZkFPKhPzoWwvVSGmfSnm8','2025-12-12 12:42:24',1),(128,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2NzkzNTUsImV4cCI6MTc2NTU0MzM1NX0.AiXflPbcnF7JUkwKfnlpKekgV-iK5VAC4oKXpleorxw','2025-12-12 12:42:35',1),(129,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2ODc2NjgsImV4cCI6MTc2NTU1MTY2OH0.Cw1vqubXYVWsM0QyqDT_CMdQ66bqBPv0TRcvUxstplg','2025-12-12 15:01:08',1),(130,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2ODgzMTksImV4cCI6MTc2NTU1MjMxOX0.9suUnVJ_guoyWjAFEs_H_WjFNYDzWRTXf64LTGyVN8Q','2025-12-12 15:11:59',1),(131,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjQ2OTYxNDgsImV4cCI6MTc2NTU2MDE0OH0.esW7mX2HM2dkNmhdCdKPGGePveKORv85InRdukpdUpg','2025-12-12 17:22:28',1),(132,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0MjY2OTksImV4cCI6MTc2NjI5MDY5OX0.Krz-STtxHGFrxm0RzHBU-BLMBCs2yxlt-b-0tg1-PHA','2025-12-21 04:18:19',1),(133,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NDM4NjksImV4cCI6MTc2NjMwNzg2OX0.kVn-pQUs86fy1jdpjueh2Pies8_oH2GK_xCeTm4Euik','2025-12-21 09:04:29',1),(134,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NDQ1ODcsImV4cCI6MTc2NjMwODU4N30.cujboCmwZgVif5uSNH0j1P88NwVO3JNPeNKKYrcsvak','2025-12-21 09:16:27',1),(135,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NDUxNzQsImV4cCI6MTc2NjMwOTE3NH0.0jtSp3yT7hw_-m4782ZH3BkOugovaxLkqFGW7bYi78Y','2025-12-21 09:26:14',1),(136,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NDU4NjYsImV4cCI6MTc2NjMwOTg2Nn0.tSDjZtnCVRMxErtzZgCFZrKdoR4G_3br7hVoEP_pWsk','2025-12-21 09:37:46',1),(137,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NTc5MTUsImV4cCI6MTc2NjMyMTkxNX0.wAHy5ihWFVgTjspZcic7aECqzDJETW4YwyuYHf0uzh4','2025-12-21 12:58:35',1),(138,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU0NjU2NTcsImV4cCI6MTc2NjMyOTY1N30.IKGCZchFt2SsVv4zu-4AlDEA_999Gc6AIdIVwCIJ_cA','2025-12-21 15:07:37',1),(139,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjIzODIsImV4cCI6MTc2NjQyNjM4Mn0.sw38EraG6J2gFLOkZAOmRxJj1Zilsxa2OHCPhRUjD50','2025-12-22 17:59:42',1),(140,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjMzNTMsImV4cCI6MTc2NjQyNzM1M30.OiMzKfvjFcMKYn5F1nA5lX2-p5aFUXhRJZ8wdfjGi_w','2025-12-22 18:15:53',1),(141,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjM3MTcsImV4cCI6MTc2NjQyNzcxN30.loyMj6Jc8X3EP5HvJ-64rPBv-fDYmhRnslgVSmuVk-I','2025-12-22 18:21:57',1),(142,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjQ3MjgsImV4cCI6MTc2NjQyODcyOH0.17ONFtu0ZfsfuFL_Lx1f4ecPzEJen9-MrZVKgC0weqA','2025-12-22 18:38:48',1),(143,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjUzMjYsImV4cCI6MTc2NjQyOTMyNn0.EU8NIU1u0cyrLNoDY6oUrETaK_ruccXTgV15LtFrKiM','2025-12-22 18:48:46',1),(144,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjYwMjgsImV4cCI6MTc2NjQzMDAyOH0.Vyt7xH3RoL7xLRGxOjZKcg7UVFNsXQ4fVSGPCzhMsvM','2025-12-22 19:00:28',1),(145,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjY2NjgsImV4cCI6MTc2NjQzMDY2OH0.AuYzOtYr9Bo88KZwdovvWFTru6iUCC_N0qaQjHMUrYM','2025-12-22 19:11:08',1),(146,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1NjY3MjYsImV4cCI6MTc2NjQzMDcyNn0.1kYHvH2LiaFNecazpvL8g15YOP8pxy3dEyjcem_bRXk','2025-12-22 19:12:06',1),(147,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1Njc0NDAsImV4cCI6MTc2NjQzMTQ0MH0.AKk6goYLJZ-VrBC3-i0E5cg0I_iUteahzYB0m3m8v2w','2025-12-22 19:24:00',1),(148,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU1Njc5NjksImV4cCI6MTc2NjQzMTk2OX0.EidNpgi34b6y6U-hY6rzbSBPDNnCvcYUkk0z4R7RCkA','2025-12-22 19:32:49',1),(149,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MDExMzUsImV4cCI6MTc2NjQ2NTEzNX0.UT8yv8nJ1KPF9-ZxSB04DwFBL-k6hFa5YZXNnXuru28','2025-12-23 04:45:35',1),(150,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MjU1MTMsImV4cCI6MTc2NjQ4OTUxM30.9QxDVtaXRr_UKZ1Wt-wJxo1WW3qWokLDO-gHT4oCG10','2025-12-23 11:31:53',1),(151,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzI3ODAsImV4cCI6MTc2NjQ5Njc4MH0.80Ssa9nAETQQQu6GmZOVdGNmTkcQbEbyFbzPKJRVQgs','2025-12-23 13:33:00',1),(152,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzI4MDIsImV4cCI6MTc2NjQ5NjgwMn0.mWF8sjL1lRQVzgXMPG9EUxB5C8H8iN5ynk1pvDIMVas','2025-12-23 13:33:22',1),(153,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzI4NDgsImV4cCI6MTc2NjQ5Njg0OH0.rJg7IJYHDFFftZxIgCpQSwWNrt8q73Mf0EwCmFvNXGg','2025-12-23 13:34:08',1),(154,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzY0OTMsImV4cCI6MTc2NjUwMDQ5M30.h7T27r5ViszAdm4VN9FcCo3T6Jn312u3PSyFKs03yKM','2025-12-23 14:34:53',1),(155,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzcxMzMsImV4cCI6MTc2NjUwMTEzM30.xUDod1dlQEDEtcj6GNfvyvd2yFC61OG8OEyTEMDYjVY','2025-12-23 14:45:33',1),(156,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2Mzc5MDcsImV4cCI6MTc2NjUwMTkwN30._IHQZeOtDboaKxRx1fIZWoLehGnvf34rMVpEFnks2aA','2025-12-23 14:58:27',1),(157,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2MzkzMDksImV4cCI6MTc2NjUwMzMwOX0.36Tdbb8R6kownPuRd1Ciu_RT-VXl92AhNxkUxmyzDIA','2025-12-23 15:21:49',1),(158,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2NDA1MDYsImV4cCI6MTc2NjUwNDUwNn0.xUhsbipXkc421FgntRaKFbyoHeoAvJU4GEViB6yAiLA','2025-12-23 15:41:46',1),(159,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2NDgzMjAsImV4cCI6MTc2NjUxMjMyMH0.7sCJwNyo5yrey9xnywf-DylE_YCCgd3JuoBzomVlfvY','2025-12-23 17:52:00',1),(160,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2NDkwMTIsImV4cCI6MTc2NjUxMzAxMn0.mIrF7rebUCOp6q2w2tuYKDgNsrmFxRopSj1cPVvR7uU','2025-12-23 18:03:32',1),(161,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2ODYxNTgsImV4cCI6MTc2NjU1MDE1OH0.5DJf5e8EIpulxMOu4O16TLcvGeTmLkm1qQbYv7irtzE','2025-12-24 04:22:38',1),(162,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU2ODgyNDIsImV4cCI6MTc2NjU1MjI0Mn0.RNJuhG8aZMoiGUD1E1015wRDZ6wNQN-IV7GxnBj14yg','2025-12-24 04:57:22',1),(163,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU3MDQ1MDAsImV4cCI6MTc2NjU2ODUwMH0.81PHU7zyNJGwRj6SXC42SdDOBEhXBQMwWa1PPemqOBY','2025-12-24 09:28:20',1),(164,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU3MDU0MzYsImV4cCI6MTc2NjU2OTQzNn0.Gr03NEFtvuSFMTW-orzsuudvjc4X5-6EzNg6eSC0OY8','2025-12-24 09:43:56',1),(165,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU3MDY3NzIsImV4cCI6MTc2NjU3MDc3Mn0.WRdBGykPcLdfuM9ky_E_LxCSGh00rEEo5FzVs8xZDSw','2025-12-24 10:06:12',1),(166,1,'eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoicmVmcmVzaCIsInN1YiI6InBhc2luZHUiLCJpYXQiOjE3NjU3MDc2MjQsImV4cCI6MTc2NjU3MTYyNH0.eJfQhbbdeYKbN9eDxq7a_ItL1-sLPvmPG24UoJnZZzk','2025-12-24 10:20:24',0);
/*!40000 ALTER TABLE `refresh_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund_status`
--

DROP TABLE IF EXISTS `refund_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `refund_status_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_status`
--

LOCK TABLES `refund_status` WRITE;
/*!40000 ALTER TABLE `refund_status` DISABLE KEYS */;
INSERT INTO `refund_status` VALUES (1,'PENDING','Refund is pending approval',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(2,'APPROVED','Refund has been approved',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(3,'PROCESSING','Refund is being processed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(4,'COMPLETED','Refund has been completed',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL),(5,'REJECTED','Refund has been rejected',1,'2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `refund_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refunds`
--

DROP TABLE IF EXISTS `refunds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refunds` (
  `refund_id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `refund_reference` varchar(100) NOT NULL,
  `refund_amount` decimal(15,2) NOT NULL,
  `refund_reason` text,
  `refund_status_id` int NOT NULL,
  `processed_date` timestamp NULL DEFAULT NULL,
  `processed_by` int DEFAULT NULL,
  `bank_account_number` varchar(50) DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `account_holder_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`refund_id`),
  UNIQUE KEY `refund_reference` (`refund_reference`),
  KEY `payment_id` (`payment_id`),
  KEY `refund_status_id` (`refund_status_id`),
  KEY `processed_by` (`processed_by`),
  KEY `idx_refunds_booking_id` (`booking_id`),
  CONSTRAINT `refunds_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `refunds_ibfk_2` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`payment_id`),
  CONSTRAINT `refunds_ibfk_3` FOREIGN KEY (`refund_status_id`) REFERENCES `refund_status` (`id`),
  CONSTRAINT `refunds_ibfk_4` FOREIGN KEY (`processed_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refunds`
--

LOCK TABLES `refunds` WRITE;
/*!40000 ALTER TABLE `refunds` DISABLE KEYS */;
INSERT INTO `refunds` VALUES (1,6,6,'REF001',800.00,'Customer cancellation with 80% refund according to policy',3,'2024-02-12 04:30:00',1,'1234567890','City Bank','David Wilson','2025-11-30 04:38:04',1,'2025-11-30 04:38:04',NULL);
/*!40000 ALTER TABLE `refunds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `religion`
--

DROP TABLE IF EXISTS `religion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `religion` (
  `religion_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`religion_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `religion_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `religion`
--

LOCK TABLES `religion` WRITE;
/*!40000 ALTER TABLE `religion` DISABLE KEYS */;
INSERT INTO `religion` VALUES (1,'Buddhism','Buddhist religion',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Hinduism','Hindu religion',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `religion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privileges`
--

DROP TABLE IF EXISTS `role_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privileges` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`privilege_id`),
  KEY `privilege_id` (`privilege_id`),
  CONSTRAINT `role_privileges_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `role_privileges_ibfk_2` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privileges`
--

LOCK TABLES `role_privileges` WRITE;
/*!40000 ALTER TABLE `role_privileges` DISABLE KEYS */;
INSERT INTO `role_privileges` VALUES (1,1),(2,1),(3,1),(2,2),(3,2),(2,3),(2,4),(3,4),(2,5);
/*!40000 ALTER TABLE `role_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(3,'ROLE_MODERATOR'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seasons`
--

DROP TABLE IF EXISTS `seasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seasons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `seasons_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seasons`
--

LOCK TABLES `seasons` WRITE;
/*!40000 ALTER TABLE `seasons` DISABLE KEYS */;
INSERT INTO `seasons` VALUES (1,'Spring','Mild weather, flowers bloom, popular for outdoor activities',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(2,'Summer','Hot and sunny season, ideal for beach activities, hiking, and festivals',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(3,'Autumn','Cooler weather, leaves change color, great for scenic tours',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(4,'Winter','Cold season, snow in some regions, perfect for skiing and mountain tours',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(5,'Dry Season','Sunny and dry, best time for safaris, island travel, and outdoor adventures',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(6,'Wet Season','Heavy rains and lush landscapes, waterfalls at peak beauty',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(7,'Monsoon','Characterized by seasonal rainfall, cultural festivals, and rich greenery',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(8,'Cool Season','Comfortable temperatures in tropical regions, ideal for cultural and city tours',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(9,'Peak Season','High demand period with best weather, higher prices, and crowded attractions',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(10,'Shoulder Season','Transition period between peak and low, fewer crowds, balanced prices',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL),(11,'Off Season','Least busy travel period, lowest prices, may have unfavorable weather',1,'2025-10-04 15:56:18',1,'2025-10-04 15:56:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `seasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seasons_images`
--

DROP TABLE IF EXISTS `seasons_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seasons_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `season_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `season_id` (`season_id`),
  KEY `status` (`status`),
  CONSTRAINT `seasons_images_ibfk_1` FOREIGN KEY (`season_id`) REFERENCES `seasons` (`id`),
  CONSTRAINT `seasons_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seasons_images`
--

LOCK TABLES `seasons_images` WRITE;
/*!40000 ALTER TABLE `seasons_images` DISABLE KEYS */;
INSERT INTO `seasons_images` VALUES (1,1,'Blooming Flowers','Vibrant flowers in full bloom across gardens','/images/season-images/spring-blooming-flower.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(2,1,'Green Meadows','Fresh green landscapes under clear skies','/images/season-images/green-meadows.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(3,1,'Outdoor Activities','Families enjoying outdoor picnics and walks','/images/season-images/outdoor-activities.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(4,2,'Sunny Beach','Tourists relaxing at the beach under the summer sun','/images/season-images/sunny-beach.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(5,2,'Mountain Hike','Adventure seekers hiking in sunny mountains','/images/season-images/mountain-hike.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(6,2,'Summer Festival','Local summer festivals with lights and music','/images/season-images/summer-festival.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(7,3,'Falling Leaves','Golden leaves covering forest trails','/images/season-images/falling-leaves.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(8,3,'Scenic Drive','Cars driving through colorful countryside roads','/images/season-images/scenic-drive.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(9,3,'Harvest Time','Farmers harvesting crops during autumn season','/images/season-images/harvest-time.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(10,4,'Snow Mountains','Snow-covered peaks and ski resorts','/images/season-images/snow-mountains.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(11,4,'Winter Cabin','Cozy wooden cabin with smoke rising from chimney','/images/season-images/winter-cabin.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(12,4,'Holiday Spirit','Christmas lights and snowy town streets','/images/season-images/holiday-spirit.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(13,5,'Safari Adventure','Tourists on jeep safari under sunny skies','/images/season-images/safari-adventure.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(14,5,'Island Paradise','Crystal clear beaches and palm trees','/images/season-images/island-paradise.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(15,5,'Outdoor Trekking','Hikers exploring dry landscapes','/images/season-images/outdoor-trekking.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(16,6,'Rainforest Path','Misty jungle path during rainfall','/images/season-images/rainforest-path.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(17,6,'Waterfalls','Waterfalls flowing at peak volume','/images/season-images/waterfalls.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(18,6,'Rainy Village','Traditional houses under tropical rain','/images/season-images/rainy-village.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(19,7,'Rain Clouds','Dark clouds forming before heavy rain','/images/season-images/rain-clouds.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(20,7,'Cultural Festival','Locals celebrating monsoon harvest festival','/images/season-images/cultural-festival.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(21,7,'Lush Fields','Rice paddies glowing in monsoon greenery','/images/season-images/lush-fields.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(22,8,'City Tour','Tourists exploring heritage cities in pleasant weather','/images/season-images/city-tour.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(23,8,'Countryside Walk','Villagers enjoying cool breeze on farmland','/images/season-images/countryside-walk.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(24,8,'Morning Mist','Light mist and blue skies over hills','/images/season-images/morning-mist.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(25,9,'Crowded Beach','Tourists flocking to famous beach destinations','/images/season-images/crowded-beach.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(26,9,'Resort Stay','Luxury resorts fully booked during holidays','/images/season-images/resort-stay.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(27,9,'Festival Night','Lively festival nights with fireworks','/images/season-images/festival-night.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(28,10,'Quiet Getaway','Peaceful resort during transition months','/images/season-images/quiet-getaway.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(29,10,'Balanced Weather','Comfortable mix of sunshine and breeze','/images/season-images/balanced-weather.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(30,10,'cultural-tour.jpg','Travelers exploring temples without crowds','/images/season-images/shoulder3.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(31,11,'Empty Beach','Calm and deserted coastal area during off season','/images/season-images/empty-beach.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(32,11,'Discount Travel','Hotels offering off-season promotions','/images/season-images/discount-travel.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL),(33,11,'Rainy Roads','Occasional rain and quiet towns','/images/season-images/rainy-roads.jpg',1,'2025-10-04 16:46:37',1,'2025-10-04 16:46:37',1,NULL,NULL);
/*!40000 ALTER TABLE `seasons_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider`
--

DROP TABLE IF EXISTS `service_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider` (
  `service_provider_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `service_provider_type_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  `check_in_time` time DEFAULT NULL,
  `check_out_time` time DEFAULT NULL,
  `star_rating` tinyint DEFAULT NULL,
  `currency_id` int NOT NULL,
  `cancellation_policy` text,
  `minimum_advance_booking_hours` int DEFAULT '24',
  `establishment_year` year DEFAULT NULL,
  `total_rooms` int DEFAULT '0',
  `total_employees` int DEFAULT NULL,
  `awards_certifications` text,
  `languages_spoken` json DEFAULT NULL,
  `parking_facility` tinyint(1) DEFAULT '0',
  `parking_capacity` int DEFAULT NULL,
  `wifi_available` tinyint(1) DEFAULT '0',
  `pet_friendly` tinyint(1) DEFAULT '0',
  `check_in_instructions` text,
  `special_instructions` text,
  `approval_status_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`service_provider_id`),
  KEY `user_id` (`user_id`),
  KEY `idx_service_provider_type` (`service_provider_type_id`),
  KEY `idx_service_provider_status` (`status_id`),
  KEY `idx_service_provider_approval` (`approval_status_id`),
  KEY `idx_service_provider_email` (`email`),
  KEY `idx_service_provider_currency` (`currency_id`),
  CONSTRAINT `service_provider_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_ibfk_2` FOREIGN KEY (`service_provider_type_id`) REFERENCES `service_provider_type` (`service_provider_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_ibfk_4` FOREIGN KEY (`approval_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_ibfk_5` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider`
--

LOCK TABLES `service_provider` WRITE;
/*!40000 ALTER TABLE `service_provider` DISABLE KEYS */;
INSERT INTO `service_provider` VALUES (1,1,1,'Grand Plaza Hotel','Luxury 5-star hotel in city center','123 Main Street, Cityville','+1-555-0101','info@grandplaza.com','https://grandplaza.com','14:00:00','12:00:00',5,1,'Free cancellation 24 hours before check-in',24,NULL,150,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 07:56:42',1,'2025-10-25 13:12:53',NULL,NULL,NULL),(2,2,2,'Sunset Beach Resort','Beachfront resort with spa','456 Beach Road, Coastal City','+1-555-0102','reservations@sunsetbeach.com','https://sunsetbeachresort.com','15:00:00','11:00:00',4,1,'Free cancellation 48 hours before check-in',24,NULL,200,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,3,1,'Mountain View Inn','Cozy hotel with mountain views','789 Mountain Road, Hilltown','+1-555-0103','bookings@mountainview.com','https://mountainviewinn.com','14:00:00','12:00:00',3,1,'Free cancellation 72 hours before check-in',24,NULL,80,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,1,3,'Royal Villa Estate','Private luxury villas','321 Villa Lane, Uptown','+1-555-0104','info@royalvilla.com','https://royalvilla.com','16:00:00','10:00:00',5,1,'Strict cancellation policy',24,NULL,25,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,2,1,'City Central Hotel','Modern hotel near business district','654 Business Ave, Downtown','+1-555-0105','reservations@citycentral.com','https://citycentralhotel.com','14:00:00','12:00:00',4,1,'Free cancellation 24 hours before check-in',24,NULL,120,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,1,4,'La Bella Italia','Authentic Italian cuisine with homemade pasta','789 Italian Street, Little Italy','+1-555-0201','info@labellaitalia.com','https://labellaitalia.com',NULL,NULL,4,1,NULL,24,NULL,0,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(7,2,4,'Dragon Palace','Traditional Chinese and Asian fusion cuisine','456 Oriental Avenue, Chinatown','+1-555-0202','reservations@dragonpalace.com','https://dragonpalace.com',NULL,NULL,5,1,NULL,24,NULL,0,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(8,3,4,'Burger Haven','Gourmet burgers and craft beers','123 Burger Lane, Downtown','+1-555-0203','orders@burgerhaven.com','https://burgerhaven.com',NULL,NULL,3,1,NULL,24,NULL,0,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(9,1,4,'Seafood Cove','Fresh seafood with ocean views','321 Harbor Drive, Waterfront','+1-555-0204','info@seafoodcove.com','https://seafoodcove.com',NULL,NULL,4,1,NULL,24,NULL,0,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(10,2,4,'Green Leaf Vegan','Plant-based cuisine and organic ingredients','654 Health Street, Uptown','+1-555-0205','hello@greenleafvegan.com','https://greenleafvegan.com',NULL,NULL,4,1,NULL,24,NULL,0,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,3,5,'Backpackers Paradise','Budget-friendly hostel for travelers','123 Traveler Street, Backpacker District','+1-555-0301','hello@backpackersparadise.com','https://backpackersparadise.com','14:00:00','11:00:00',3,1,'Free cancellation 48 hours before check-in',24,NULL,50,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(12,1,5,'City Center Hostel','Modern hostel in heart of downtown','456 Downtown Avenue, City Center','+1-555-0302','bookings@citycenterhostel.com','https://citycenterhostel.com','15:00:00','10:00:00',4,1,'Free cancellation 24 hours before check-in',24,NULL,75,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(13,2,5,'Mountain Base Hostel','Adventure hostel for outdoor enthusiasts','789 Adventure Road, Mountain Base','+1-555-0303','info@mountainbasehostel.com','https://mountainbasehostel.com','13:00:00','11:00:00',3,1,'Strict cancellation policy',24,NULL,40,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(14,3,5,'Beach Bum Hostel','Surf hostel with beach access','321 Beach Boulevard, Surf District','+1-555-0304','stay@beachbumhostel.com','https://beachbumhostel.com','14:00:00','12:00:00',3,1,'Free cancellation 72 hours before check-in',24,NULL,60,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(15,1,5,'Urban Oasis Hostel','Eco-friendly hostel with garden','654 Green Street, Eco District','+1-555-0305','hello@urbanoasishostel.com','https://urbanoasishostel.com','16:00:00','10:00:00',4,1,'Free cancellation 48 hours before check-in',24,NULL,55,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(16,2,1,'Ocean View Hotel','Beachfront hotel with panoramic ocean views','789 Ocean Drive, Beach City','+1-555-0106','reservations@oceanviewhotel.com','https://oceanviewhotel.com','15:00:00','11:00:00',4,1,'Free cancellation 48 hours before check-in',24,NULL,120,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(17,3,1,'Heritage Grand Hotel','Historic hotel with classic architecture','456 Heritage Street, Old Town','+1-555-0107','info@heritagegrand.com','https://heritagegrand.com','14:00:00','12:00:00',5,1,'Free cancellation 72 hours before check-in',24,NULL,80,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(18,1,1,'Garden Inn & Suites','Boutique hotel with lush gardens','321 Garden Lane, Park District','+1-555-0108','bookings@gardeninn.com','https://gardeninn.com','16:00:00','11:00:00',4,1,'Free cancellation 24 hours before check-in',24,NULL,60,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(19,3,2,'Mountain Peak Resort','Luxury mountain resort with ski-in/ski-out access','789 Mountain Peak Road, Alpine Valley','+1-555-0206','bookings@mountainpeakresort.com','https://mountainpeakresort.com','16:00:00','10:00:00',5,1,'Free cancellation 30 days before check-in',24,NULL,150,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(20,1,2,'Desert Oasis Resort','Luxury desert resort with spa and golf','456 Desert Mirage Drive, Palm Springs','+1-555-0207','reservations@desertoasis.com','https://desertoasis.com','15:00:00','11:00:00',5,1,'Free cancellation 14 days before check-in',24,NULL,200,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(21,2,2,'Tropical Paradise Resort','All-inclusive tropical resort with private beach','321 Paradise Island, Caribbean','+1-555-0208','info@tropicalparadise.com','https://tropicalparadise.com','14:00:00','12:00:00',5,1,'Free cancellation 21 days before check-in',24,NULL,300,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(22,2,3,'Cliffside Villa Retreat','Luxury cliffside villas with ocean panoramas','789 Cliffside Drive, Coastal Bluffs','+1-555-0306','bookings@cliffsidevilla.com','https://cliffsidevilla.com','15:00:00','11:00:00',5,1,'Strict cancellation - 60 days notice',24,NULL,12,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(23,3,3,'Forest Haven Villas','Secluded villas in private forest reserve','456 Forest Trail, Wilderness Area','+1-555-0307','info@foresthavenvillas.com','https://foresthavenvillas.com','16:00:00','10:00:00',5,1,'Free cancellation 45 days before check-in',24,NULL,8,NULL,NULL,NULL,0,NULL,1,1,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(24,1,3,'Vineyard Estate Villas','Luxury villas on working vineyard','321 Vineyard Lane, Wine Country','+1-555-0308','reservations@vineyardestate.com','https://vineyardestate.com','14:00:00','12:00:00',5,1,'Free cancellation 30 days before check-in',24,NULL,15,NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_amenity`
--

DROP TABLE IF EXISTS `service_provider_amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_amenity` (
  `provider_amenity_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `amenity_type_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `local_additional_charge` decimal(10,2) DEFAULT '0.00',
  `foreign_additional_charge` decimal(10,2) DEFAULT '0.00',
  `currency_id` int NOT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`provider_amenity_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `amenity_type_id` (`amenity_type_id`),
  KEY `currency_id` (`currency_id`),
  CONSTRAINT `service_provider_amenity_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_amenity_ibfk_2` FOREIGN KEY (`amenity_type_id`) REFERENCES `amenity_type` (`amenity_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_amenity_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_amenity`
--

LOCK TABLES `service_provider_amenity` WRITE;
/*!40000 ALTER TABLE `service_provider_amenity` DISABLE KEYS */;
INSERT INTO `service_provider_amenity` VALUES (1,1,1,'Premium WiFi','High-speed internet access',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,'Infinity Pool','Rooftop infinity pool',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,1,3,'Fitness Center','24/7 gym access',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,1,4,'Luxury Spa','Full-service spa treatments',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,2,2,'Beach Pool','Ocean-view swimming pool',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,2,4,'Beachside Spa','Tropical spa treatments',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,3,5,'Mountain Parking','Secure parking facility',15.00,15.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,4,2,'Private Villa Pool','Individual pool for each villa',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,5,1,'Business WiFi','Dedicated business internet',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(10,5,3,'Executive Gym','Business-class fitness center',0.00,0.00,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(11,11,1,'High-Speed WiFi','Fast internet throughout hostel',0.00,0.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(12,11,5,'Secure Lockers','Personal lockers in dorms',5.00,5.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(13,12,1,'Premium WiFi','Enhanced internet speed',0.00,0.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(14,12,5,'Luggage Storage','Secure luggage storage',3.00,3.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(15,13,1,'Basic WiFi','Standard internet access',0.00,0.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(16,13,5,'Equipment Rental','Hiking gear rental',15.00,15.00,1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_amenity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_approval`
--

DROP TABLE IF EXISTS `service_provider_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_approval` (
  `approval_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `approved_by` int DEFAULT NULL,
  `approval_status_id` int NOT NULL,
  `approval_comment` text,
  `approved_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`approval_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `approval_status_id` (`approval_status_id`),
  KEY `approved_by` (`approved_by`),
  CONSTRAINT `service_provider_approval_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_approval_ibfk_2` FOREIGN KEY (`approval_status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_approval_ibfk_3` FOREIGN KEY (`approved_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_approval`
--

LOCK TABLES `service_provider_approval` WRITE;
/*!40000 ALTER TABLE `service_provider_approval` DISABLE KEYS */;
INSERT INTO `service_provider_approval` VALUES (1,1,1,1,'All documents verified and approved','2025-10-25 07:56:42','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,2,2,1,'Property inspection completed successfully','2025-10-25 07:56:42','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,3,1,1,'Business registration verified','2025-10-25 07:56:42','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,4,2,1,'Luxury certification confirmed','2025-10-25 07:56:42','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,5,1,1,'Safety standards met','2025-10-25 07:56:42','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_bank`
--

DROP TABLE IF EXISTS `service_provider_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_bank` (
  `bank_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `account_holder_name` varchar(255) NOT NULL,
  `account_number` varchar(100) NOT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `branch_code` varchar(50) DEFAULT NULL,
  `swift_code` varchar(50) DEFAULT NULL,
  `iban` varchar(100) DEFAULT NULL,
  `currency_id` int NOT NULL,
  `is_primary` tinyint(1) DEFAULT '0',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`bank_id`),
  KEY `currency_id` (`currency_id`),
  KEY `status_id` (`status_id`),
  KEY `idx_bank_primary` (`service_provider_id`,`is_primary`),
  CONSTRAINT `service_provider_bank_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_bank_ibfk_2` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_bank_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_bank`
--

LOCK TABLES `service_provider_bank` WRITE;
/*!40000 ALTER TABLE `service_provider_bank` DISABLE KEYS */;
INSERT INTO `service_provider_bank` VALUES (1,1,'City National Bank','Grand Plaza Hotel LLC','9876543210','Main Branch',NULL,'CTBKIUS33',NULL,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Global Trust Bank','Grand Plaza Hotel LLC','1234567890','Downtown',NULL,'GTBKUS44',NULL,2,0,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Oceanic Bank','Sunset Beach Resort Inc','5556667777','Beach Branch',NULL,'OCEAUS55',NULL,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'Mountain Federal','Mountain View Inn','3334445555','Hilltown',NULL,'MTNFUS66',NULL,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Royal International','Royal Villa Estate','7778889999','Uptown',NULL,'ROYLUS77',NULL,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'Metro Commercial','City Central Hotel','1112223333','Business District',NULL,'METRUS88',NULL,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_booking_restrictions`
--

DROP TABLE IF EXISTS `service_provider_booking_restrictions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_booking_restrictions` (
  `restriction_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `restriction_type` varchar(50) NOT NULL,
  `min_stay_nights` int DEFAULT '1',
  `max_stay_nights` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`restriction_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_booking_restrictions_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_booking_restrictions_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_booking_restrictions`
--

LOCK TABLES `service_provider_booking_restrictions` WRITE;
/*!40000 ALTER TABLE `service_provider_booking_restrictions` DISABLE KEYS */;
INSERT INTO `service_provider_booking_restrictions` VALUES (1,1,'MIN_STAY',2,NULL,'2024-12-20','2025-01-05','Minimum 2 nights during holidays',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,2,'MIN_STAY',3,14,'2024-06-01','2024-08-31','Summer season restrictions',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,3,'MAX_STAY',1,30,NULL,NULL,'Maximum 30 nights stay',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,4,'MIN_STAY',5,21,NULL,NULL,'Villa minimum 5 nights stay',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,5,'MIN_STAY',1,7,'2024-09-01','2024-11-30','Fall season restrictions',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_booking_restrictions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_commission_settings`
--

DROP TABLE IF EXISTS `service_provider_commission_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_commission_settings` (
  `commission_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `commission_percentage` decimal(5,2) NOT NULL,
  `applies_to_rooms` tinyint(1) DEFAULT '1',
  `applies_to_meals` tinyint(1) DEFAULT '1',
  `applies_to_packages` tinyint(1) DEFAULT '1',
  `minimum_commission_amount` decimal(10,2) DEFAULT '0.00',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`commission_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_commission_settings_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_commission_settings_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_commission_settings`
--

LOCK TABLES `service_provider_commission_settings` WRITE;
/*!40000 ALTER TABLE `service_provider_commission_settings` DISABLE KEYS */;
INSERT INTO `service_provider_commission_settings` VALUES (1,1,15.00,1,1,1,25.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,2,12.00,1,1,1,20.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,3,18.00,1,0,1,15.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,4,10.00,1,1,1,50.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,5,16.00,1,1,1,22.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_commission_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_contact_person`
--

DROP TABLE IF EXISTS `service_provider_contact_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_contact_person` (
  `contact_person_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `is_primary` tinyint(1) DEFAULT '0',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`contact_person_id`),
  KEY `status_id` (`status_id`),
  KEY `idx_contact_primary` (`service_provider_id`,`is_primary`),
  CONSTRAINT `service_provider_contact_person_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_contact_person_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_contact_person`
--

LOCK TABLES `service_provider_contact_person` WRITE;
/*!40000 ALTER TABLE `service_provider_contact_person` DISABLE KEYS */;
INSERT INTO `service_provider_contact_person` VALUES (1,1,'Sarah Johnson','General Manager','s.johnson@grandplaza.com','+1-555-0101','+1-555-0106',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Michael Chen','Front Office Manager','m.chen@grandplaza.com','+1-555-0101','+1-555-0107',0,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Emma Rodriguez','Resort Director','e.rodriguez@sunsetbeach.com','+1-555-0102','+1-555-0108',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'David Wilson','Inn Keeper','d.wilson@mountainview.com','+1-555-0103','+1-555-0109',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Sophia Martinez','Villa Manager','s.martinez@royalvilla.com','+1-555-0104','+1-555-0110',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'James Brown','Hotel Manager','j.brown@citycentral.com','+1-555-0105','+1-555-0111',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,11,'Alex Thompson','Hostel Manager','alex@backpackersparadise.com','+1-555-0301','+1-555-0311',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(8,12,'Maria Garcia','Front Desk Manager','maria@citycenterhostel.com','+1-555-0302','+1-555-0312',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(9,13,'Jake Wilson','Adventure Coordinator','jake@mountainbasehostel.com','+1-555-0303','+1-555-0313',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_contact_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_destination`
--

DROP TABLE IF EXISTS `service_provider_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_destination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `destination_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_provider_destination` (`service_provider_id`,`destination_id`),
  KEY `destination_id` (`destination_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_destination_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_destination_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_destination_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_destination`
--

LOCK TABLES `service_provider_destination` WRITE;
/*!40000 ALTER TABLE `service_provider_destination` DISABLE KEYS */;
INSERT INTO `service_provider_destination` VALUES (1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,3,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,4,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,5,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,6,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,2,7,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,3,8,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,4,9,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(10,5,10,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_document`
--

DROP TABLE IF EXISTS `service_provider_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_document` (
  `document_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `document_type` varchar(100) NOT NULL,
  `document_name` varchar(255) NOT NULL,
  `document_url` varchar(255) NOT NULL,
  `issue_date` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `is_verified` tinyint(1) DEFAULT '0',
  `verified_by` int DEFAULT NULL,
  `verified_at` timestamp NULL DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `verified_by` (`verified_by`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_document_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_document_ibfk_2` FOREIGN KEY (`verified_by`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_document_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_document`
--

LOCK TABLES `service_provider_document` WRITE;
/*!40000 ALTER TABLE `service_provider_document` DISABLE KEYS */;
INSERT INTO `service_provider_document` VALUES (1,1,'Business License','Hotel Operating License','https://docs.example.com/gp-license.pdf','2023-01-15','2024-12-31',1,1,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Health Certificate','Food Safety Certificate','https://docs.example.com/gp-health.pdf','2023-03-20','2024-03-20',1,1,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Resort License','Beach Resort Permit','https://docs.example.com/sb-license.pdf','2023-02-10','2025-02-10',1,2,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'Inn License','Mountain Inn Certification','https://docs.example.com/mv-license.pdf','2023-01-30','2024-12-31',1,1,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Villa Permit','Luxury Villa Authorization','https://docs.example.com/rv-permit.pdf','2023-04-01','2026-04-01',1,2,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'Hotel Registration','City Hotel Registration','https://docs.example.com/cc-registration.pdf','2023-01-10','2024-12-31',1,1,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_facility`
--

DROP TABLE IF EXISTS `service_provider_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_facility` (
  `facility_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `facility_name` varchar(255) NOT NULL,
  `description` text,
  `is_available` tinyint(1) DEFAULT '1',
  `special_note` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`facility_id`),
  KEY `service_provider_id` (`service_provider_id`),
  CONSTRAINT `service_provider_facility_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_facility`
--

LOCK TABLES `service_provider_facility` WRITE;
/*!40000 ALTER TABLE `service_provider_facility` DISABLE KEYS */;
INSERT INTO `service_provider_facility` VALUES (1,1,'Conference Center','1000-person capacity conference facility',1,'Advanced booking required','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Business Center','24/7 business services',1,'Printing and copying available','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Water Sports Center','Beach equipment and water sports',1,'Seasonal operation','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'Kids Club','Children entertainment facility',1,'Ages 4-12','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,'Hiking Guide Service','Professional mountain guides',1,'Reservation recommended','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,'Private Chef Service','In-villa dining experience',1,'24-hour notice required','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,4,'Butler Service','Personal butler for each villa',1,'Included in villa rate','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,5,'Meeting Rooms','Executive meeting spaces',1,'Various sizes available','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,5,'Concierge Service','Personalized city guidance',1,'Multilingual staff','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(10,6,'Wine Cellar','Extensive collection of Italian wines',1,'Wine tasting available','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,6,'Outdoor Patio','Al fresco dining with city views',1,'Seasonal operation','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(12,7,'Private Dining Room','Traditional Chinese private room',1,'Reservation required','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(13,7,'Tea Bar','Traditional Chinese tea service',1,'Tea ceremony available','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(14,8,'Sports Bar','Multiple screens for sports viewing',1,'Game day specials','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(15,8,'Rooftop Terrace','City view dining area',1,'Weather permitting','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(16,11,'Common Kitchen','Shared kitchen for guest use',1,'Clean up after use','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(17,11,'Game Room','Pool table and board games',1,'Open until 11pm','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(18,11,'Laundry Room','Self-service laundry',1,'Tokens available at reception','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(19,12,'Rooftop Terrace','City view terrace with seating',1,'24-hour access','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(20,12,'Business Center','Computers and printing services',1,'Free for guests','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(21,12,'Bike Rental','City bikes for rent',1,'Reservation recommended','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(22,13,'Gear Storage','Secure storage for outdoor equipment',1,'Lockers available','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(23,13,'Campfire Area','Outdoor campfire pit',1,'Weather permitting','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(24,9,'Ski Rental Shop','Full ski and snowboard equipment rental',1,'Advanced booking recommended','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(25,9,'Mountain Spa','Alpine-inspired wellness center',1,'Appointments required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(26,9,'Fine Dining Restaurant','Gourmet mountain cuisine',1,'Reservation recommended','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(27,10,'Championship Golf Course','18-hole desert golf course',1,'Tee times required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(28,10,'Desert Spa','Luxury spa with desert treatments',1,'Appointments required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(29,10,'Pool Complex','Multiple pools with cabanas',1,'First come first served','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(30,11,'Water Sports Center','Snorkeling, diving, and water activities',1,'Weather dependent','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(31,11,'Kids Adventure Club','Daily activities for children',1,'Ages 4-12','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(32,11,'Beach Bar & Grill','Casual dining on the beach',1,'Open until midnight','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(33,12,'Private Infinity Pool','Each villa has private infinity pool',1,'Heated year-round','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(34,12,'Cliffside Dining','Private chef available',1,'24-hour notice required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(35,12,'Helipad','Private helipad access',1,'Advance booking required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(36,13,'Private Hot Tub','Each villa has private hot tub',1,'Wood-fired or electric','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(37,13,'Hiking Trails','Private forest trails',1,'Guided tours available','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(38,13,'Wildlife Viewing','Designated wildlife areas',1,'Respect wildlife distance','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(39,14,'Private Wine Cellar','Personal wine cellar in each villa',1,'Complimentary tasting','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(40,14,'Vineyard Tours','Private vineyard tours',1,'Daily at 10am and 3pm','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(41,14,'Wine Blending Experience','Create your own wine blend',1,'Reservation required','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_facility_image`
--

DROP TABLE IF EXISTS `service_provider_facility_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_facility_image` (
  `facility_image_id` int NOT NULL AUTO_INCREMENT,
  `facility_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_description` text,
  `caption` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`facility_image_id`),
  KEY `facility_id` (`facility_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_facility_image_ibfk_1` FOREIGN KEY (`facility_id`) REFERENCES `service_provider_facility` (`facility_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_facility_image_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_facility_image`
--

LOCK TABLES `service_provider_facility_image` WRITE;
/*!40000 ALTER TABLE `service_provider_facility_image` DISABLE KEYS */;
INSERT INTO `service_provider_facility_image` VALUES (1,1,'/images/service-provider/facilities/facility-2.jpg','Conference Center','Large event space','Professional Events',1,'2025-10-25 07:56:42',1,'2025-10-25 17:36:12',NULL,NULL,NULL),(2,2,'/images/service-provider/facilities/facility-1.jpg','Business Center','Modern work facilities','Work Smart',1,'2025-10-25 07:56:42',1,'2025-10-25 17:36:12',NULL,NULL,NULL),(3,3,'/images/service-provider/facilities/facility-5.jpg','Water Sports','Beach activity center','Ocean Fun',1,'2025-10-25 07:56:42',1,'2025-10-25 17:36:12',NULL,NULL,NULL),(4,4,'/images/service-provider/facilities/facility-4.jpeg','Kids Club','Children play area','Family Fun',1,'2025-10-25 07:56:42',1,'2025-10-25 17:40:11',NULL,NULL,NULL),(5,5,'/images/service-provider/facilities/facility-3.jpg','Hiking Guides','Mountain adventure team','Explore Nature',1,'2025-10-25 07:56:42',1,'2025-10-25 17:36:12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_facility_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_image`
--

DROP TABLE IF EXISTS `service_provider_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_description` text,
  `caption` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_image_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_image_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_image`
--

LOCK TABLES `service_provider_image` WRITE;
/*!40000 ALTER TABLE `service_provider_image` DISABLE KEYS */;
INSERT INTO `service_provider_image` VALUES (1,1,'/images/service-provider/providers/provider-1.jpg','Grand Plaza Exterior','Main hotel building exterior','Luxury City Hotel',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(2,1,'/images/service-provider/providers/provider-2.jpg','Grand Plaza Lobby','Elegant hotel lobby','Grand Entrance',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(3,2,'/images/service-provider/providers/provider-3.jpg','Sunset Beach','Private beach area','Paradise Beach',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(4,2,'/images/service-provider/providers/provider-4.jpg','Resort Pool','Infinity pool with ocean view','Ocean View Pool',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(5,3,'/images/service-provider/providers/provider-5.jpg','Mountain View','Scenic mountain landscape','Mountain Retreat',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(6,4,'/images/service-provider/providers/provider-6.jpg','Private Villa','Luxury villa exterior','Exclusive Villa',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(7,5,'/images/service-provider/providers/provider-7.jpg','City View','Downtown cityscape','City Center Location',1,'2025-10-25 07:56:42',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(8,6,'/images/service-provider/providers/provider-8.jpg','Restaurant Interior','Elegant Italian dining room','Authentic Italian Atmosphere',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(9,6,'/images/service-provider/providers/provider-9.jpg','Fresh Pasta','Handmade pasta preparation','Fresh Daily Pasta',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(10,7,'/images/service-provider/providers/provider-10.jpg','Restaurant Exterior','Traditional Chinese architecture','Dragon Palace Entrance',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(11,7,'/images/service-provider/providers/provider-11.jpg','Dim Sum Selection','Assorted dim sum dishes','Traditional Dim Sum',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(12,8,'/images/service-provider/providers/provider-12.jpg','Gourmet Burger','Signature burger with fries','Our Famous Burger',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(13,8,'/images/service-provider/providers/provider-13.jpg','Craft Beer Bar','Extensive craft beer selection','Craft Beer Haven',1,'2025-10-25 13:13:33',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(14,11,'/images/service-provider/providers/provider-14.jpg','Dormitory Room','Clean and comfortable dormitory','Comfortable Dorms',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(15,11,'/images/service-provider/providers/provider-15.jpg','Common Area','Social common area with games','Social Space',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(16,12,'/images/service-provider/providers/provider-16.jpg','Rooftop Terrace','City view rooftop area','Amazing City Views',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(17,12,'/images/service-provider/providers/provider-17.jpg','Private Room','Modern private room','Private Comfort',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(18,13,'/images/service-provider/providers/provider-18.jpg','Mountain View','Hostel with mountain backdrop','Mountain Adventure Base',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(19,13,'/images/service-provider/providers/provider-19.jpg','Campfire Area','Evening campfire gathering','Evening Campfires',1,'2025-10-25 13:15:30',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(20,6,'/images/service-provider/providers/provider-20.jpg','Ocean Front','Hotel building with ocean view','Stunning Ocean Views',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(21,6,'/images/service-provider/providers/provider-21.jpg','Infinity Pool','Infinity pool overlooking ocean','Infinity Pool Paradise',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(22,7,'/images/service-provider/providers/provider-22.jpg','Grand Lobby','Historic hotel lobby','Historic Grandeur',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(23,7,'/images/service-provider/providers/provider-23.jpg','Heritage Suite','Luxurious heritage suite','Luxury Heritage',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(24,8,'/images/service-provider/providers/provider-24.jpg','Hotel Gardens','Beautiful hotel gardens','Tranquil Gardens',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(25,8,'/images/service-provider/providers/provider-25.jpg','Garden Room','Room with garden view','Garden View Room',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(26,9,'/images/service-provider/providers/provider-26.jpg','Ski Slopes','Ski-in/ski-out access','Mountain Adventure',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(27,9,'/images/service-provider/providers/provider-27.jpg','Alpine Spa','Mountain view spa','Wellness Retreat',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(28,10,'/images/service-provider/providers/provider-28.jpg','Resort Pool','Luxury pool complex','Desert Oasis',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(29,10,'/images/service-provider/providers/provider-29.jpg','Golf Course','Championship golf','Golf Paradise',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(30,11,'/images/service-provider/providers/provider-30.jpg','Private Beach','White sand beach','Tropical Paradise',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(31,11,'/images/service-provider/providers/provider-31.jpg','Infinity Pool','Ocean view infinity pool','Endless Views',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(32,12,'/images/service-provider/providers/provider-32.jpg','Cliffside Villa','Villa on ocean cliff','Dramatic Ocean Views',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(33,12,'/images/service-provider/providers/provider-33.jpg','Infinity Pool','Cliffside infinity pool','Infinity Edge Pool',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(34,13,'/images/service-provider/providers/provider-34.jpg','Forest Villa','Villa among trees','Secluded Forest Retreat',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(35,13,'/images/service-provider/providers/provider-35.jpg','Rustic Interior','Luxury forest interior','Nature Luxury',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(36,14,'/images/service-provider/providers/provider-36.jpg','Vineyard Villa','Villa overlooking vineyards','Wine Country Luxury',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL),(37,14,'/images/service-provider/providers/provider-37.jpg','Vineyard Patio','Private vineyard patio','Al Fresco Dining',1,'2025-10-25 13:19:38',1,'2025-10-25 18:00:57',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_location`
--

DROP TABLE IF EXISTS `service_provider_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `google_place_id` varchar(255) DEFAULT NULL,
  `timezone` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `service_provider_id` (`service_provider_id`),
  CONSTRAINT `service_provider_location_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_location`
--

LOCK TABLES `service_provider_location` WRITE;
/*!40000 ALTER TABLE `service_provider_location` DISABLE KEYS */;
INSERT INTO `service_provider_location` VALUES (1,1,40.71277600,-74.00597400,'ChIJHQ6aMnBTwokRc-T-3CrcvOE','America/New_York','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,2,34.05223500,-118.24368300,'ChIJE9on3F3HwoAR9AhGJW_fL-I','America/Los_Angeles','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,3,39.73923500,-104.99025000,'ChIJzxcfI6qAa4cR1jaKJ_j0jhE','America/Denver','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,4,41.87811300,-87.62979900,'ChIJ7cv00DwsDogRAMDACa2m4K8','America/Chicago','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,5,25.76168100,-80.19178800,'ChIJEcHIDqKw2YgRZU-t3XHylv8','America/New_York','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,6,40.72345600,-74.00123400,'ChIJP5jIR8BTwokRc-T-3CrcvOE','America/New_York','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(7,7,40.71876500,-73.99876500,'ChIJdY6MnF3HwoAR9AhGJW_fL-I','America/New_York','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(8,8,40.72123400,-74.00345600,'ChIJzxcfI6qAa4cR1jaKJ_j0jhF','America/New_York','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(9,9,40.72567800,-74.00567800,'ChIJ7cv00DwsDogRAMDACa2m4K9','America/New_York','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(10,10,40.71987600,-74.00234500,'ChIJEcHIDqKw2YgRZU-t3XHylv9','America/New_York','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,11,40.71345600,-74.00678900,'ChIJHQ6aMnBTwokRc-T-3CrcvOF','America/New_York','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(12,12,40.71456700,-74.00789000,'ChIJE9on3F3HwoAR9AhGJW_fL-J','America/New_York','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(13,13,40.71567800,-74.00890100,'ChIJzxcfI6qAa4cR1jaKJ_j0jhG','America/New_York','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(14,14,40.71678900,-74.00901200,'ChIJ7cv00DwsDogRAMDACa2m4KA','America/New_York','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(15,15,40.71789000,-74.01012300,'ChIJEcHIDqKw2YgRZU-t3XHylvA','America/New_York','2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(16,6,34.01234500,-118.49876500,'ChIJdY6MnF3HwoAR9AhGJW_fL-K','America/Los_Angeles','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(17,7,41.89876500,-87.63456700,'ChIJ7cv00DwsDogRAMDACa2m4KB','America/Chicago','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(18,8,25.76543200,-80.19876500,'ChIJEcHIDqKw2YgRZU-t3XHylvB','America/New_York','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(19,9,39.65432100,-106.12345600,'ChIJzxcfI6qAa4cR1jaKJ_j0jhH','America/Denver','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(20,10,33.82345600,-116.54567800,'ChIJ7cv00DwsDogRAMDACa2m4KC','America/Los_Angeles','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(21,11,18.34567800,-64.93456700,'ChIJEcHIDqKw2YgRZU-t3XHylvC','America/Puerto_Rico','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(22,12,34.04567800,-119.67890100,'ChIJdY6MnF3HwoAR9AhGJW_fL-L','America/Los_Angeles','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(23,13,45.12345600,-123.45678900,'ChIJzxcfI6qAa4cR1jaKJ_j0jhI','America/Los_Angeles','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(24,14,38.56789000,-122.45678900,'ChIJ7cv00DwsDogRAMDACa2m4KD','America/Los_Angeles','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_meal`
--

DROP TABLE IF EXISTS `service_provider_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_meal` (
  `meal_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `meal_type_id` int NOT NULL,
  `description` text,
  `local_price` decimal(10,2) DEFAULT NULL,
  `foreign_price` decimal(10,2) DEFAULT NULL,
  `currency_id` int NOT NULL,
  `discount_percentage` decimal(5,2) DEFAULT '0.00',
  `discount_requirements` text,
  `serves_people` int DEFAULT '1',
  `cuisine_type` varchar(100) DEFAULT NULL,
  `dietary_tags` json DEFAULT NULL,
  `preparation_time` int DEFAULT NULL,
  `is_chef_special` tinyint(1) DEFAULT '0',
  `is_spicy` tinyint(1) DEFAULT '0',
  `spice_level` tinyint DEFAULT NULL,
  `serving_size` varchar(100) DEFAULT NULL,
  `calories` int DEFAULT NULL,
  `allergens` text,
  `available` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`meal_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `meal_type_id` (`meal_type_id`),
  KEY `currency_id` (`currency_id`),
  CONSTRAINT `service_provider_meal_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_meal_ibfk_2` FOREIGN KEY (`meal_type_id`) REFERENCES `meal_type` (`meal_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_meal_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_meal`
--

LOCK TABLES `service_provider_meal` WRITE;
/*!40000 ALTER TABLE `service_provider_meal` DISABLE KEYS */;
INSERT INTO `service_provider_meal` VALUES (1,1,1,'Continental Breakfast Buffet',25.00,25.00,1,0.00,NULL,1,'International','[\"vegetarian\", \"gluten-free options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,'Business Lunch Special',35.00,35.00,1,10.00,NULL,1,'American','[]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,1,3,'Gourmet Dinner Experience',85.00,85.00,1,0.00,NULL,1,'French','[\"vegetarian\", \"vegan options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,1,'Beachside Breakfast',22.00,22.00,1,0.00,NULL,1,'Tropical','[\"vegetarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,2,3,'Seafood Dinner Platter',65.00,65.00,1,15.00,NULL,2,'Seafood','[\"pescatarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,3,1,'Mountain Breakfast',18.00,18.00,1,0.00,NULL,1,'American','[\"vegetarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,4,3,'Private Villa Dinner',120.00,120.00,1,0.00,NULL,2,'Mediterranean','[\"vegetarian\", \"vegan options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,5,1,'Executive Breakfast',28.00,28.00,1,0.00,NULL,1,'International','[\"vegetarian\", \"gluten-free\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,6,3,'Spaghetti Carbonara',24.00,24.00,1,0.00,NULL,1,'Italian','[]',15,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(10,6,3,'Margherita Pizza',18.00,18.00,1,0.00,NULL,1,'Italian','[\"vegetarian\"]',20,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,6,3,'Osso Buco',36.00,36.00,1,0.00,NULL,1,'Italian','[]',30,1,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(12,6,1,'Italian Breakfast',15.00,15.00,1,10.00,NULL,1,'Italian','[\"vegetarian\"]',10,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(13,7,2,'Kung Pao Chicken',16.00,16.00,1,0.00,NULL,1,'Chinese','[]',12,0,1,3,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(14,7,2,'Vegetable Lo Mein',14.00,14.00,1,0.00,NULL,1,'Chinese','[\"vegetarian\", \"vegan\"]',10,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(15,7,3,'Peking Duck',45.00,45.00,1,0.00,NULL,2,'Chinese','[]',25,1,0,1,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(16,7,1,'Dim Sum Platter',25.00,25.00,1,15.00,NULL,2,'Chinese','[\"vegetarian options\"]',15,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(17,8,2,'Classic Cheeseburger',14.00,14.00,1,0.00,NULL,1,'American','[]',8,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(18,8,2,'BBQ Bacon Burger',18.00,18.00,1,0.00,NULL,1,'American','[]',10,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(19,8,3,'Truffle Mushroom Burger',22.00,22.00,1,0.00,NULL,1,'American','[\"vegetarian\"]',12,1,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(20,8,4,'Loaded Fries',8.00,8.00,1,0.00,NULL,2,'American','[\"vegetarian\"]',5,0,0,0,NULL,NULL,NULL,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(21,11,1,'Continental Breakfast',8.00,8.00,1,0.00,NULL,1,'International','[\"vegetarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(22,11,4,'Pizza Night',12.00,12.00,1,10.00,NULL,1,'Italian','[\"vegetarian options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(23,12,1,'Full Breakfast',12.00,12.00,1,0.00,NULL,1,'American','[\"vegetarian\", \"gluten-free options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(24,12,2,'Pack Lunch',10.00,10.00,1,0.00,NULL,1,'International','[\"vegetarian options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(25,13,1,'Trail Breakfast',6.00,6.00,1,0.00,NULL,1,'American','[\"vegetarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(26,13,4,'BBQ Dinner',15.00,15.00,1,0.00,NULL,1,'American','[\"vegetarian options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(27,6,1,'Seaside Breakfast Buffet',28.00,28.00,1,0.00,NULL,1,'International','[\"vegetarian\", \"pescatarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(28,6,3,'Oceanfront Dinner',75.00,75.00,1,10.00,NULL,1,'Seafood','[\"pescatarian\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(29,7,1,'Classic Breakfast',32.00,32.00,1,0.00,NULL,1,'Continental','[\"vegetarian\", \"gluten-free\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(30,7,3,'Fine Dining Experience',120.00,120.00,1,0.00,NULL,1,'French','[\"vegetarian options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(31,8,1,'Garden Fresh Breakfast',18.00,18.00,1,0.00,NULL,1,'Organic','[\"vegetarian\", \"vegan\", \"gluten-free\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(32,8,2,'Farm-to-Table Lunch',25.00,25.00,1,15.00,NULL,1,'American','[\"vegetarian\", \"vegan options\"]',NULL,0,0,NULL,NULL,NULL,NULL,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_meal_image`
--

DROP TABLE IF EXISTS `service_provider_meal_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_meal_image` (
  `meal_image_id` int NOT NULL AUTO_INCREMENT,
  `meal_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_description` text,
  `caption` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`meal_image_id`),
  KEY `meal_id` (`meal_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_meal_image_ibfk_1` FOREIGN KEY (`meal_id`) REFERENCES `service_provider_meal` (`meal_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_meal_image_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_meal_image`
--

LOCK TABLES `service_provider_meal_image` WRITE;
/*!40000 ALTER TABLE `service_provider_meal_image` DISABLE KEYS */;
INSERT INTO `service_provider_meal_image` VALUES (1,1,'/images/service-provider/meals/meal-1.jpg','Breakfast Buffet','Continental breakfast spread','Morning Delights',1,'2025-10-25 07:56:42',1,'2025-10-25 17:44:47',NULL,NULL,NULL),(2,2,'/images/service-provider/meals/meal-2.jpg','Business Lunch','Executive lunch special','Power Lunch',1,'2025-10-25 07:56:42',1,'2025-10-25 17:44:47',NULL,NULL,NULL),(3,3,'/images/service-provider/meals/meal-3.jpg','Gourmet Dinner','Fine dining experience','Culinary Excellence',1,'2025-10-25 07:56:42',1,'2025-10-25 17:44:47',NULL,NULL,NULL),(4,4,'/images/service-provider/meals/meal-4.jpg','Beach Breakfast','Tropical morning meal','Beachside Dining',1,'2025-10-25 07:56:42',1,'2025-10-25 17:44:47',NULL,NULL,NULL),(5,5,'/images/service-provider/meals/meal-5.jpg','Seafood Platter','Fresh seafood selection','Ocean Fresh',1,'2025-10-25 07:56:42',1,'2025-10-25 17:44:47',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_meal_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_operating_hours`
--

DROP TABLE IF EXISTS `service_provider_operating_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_operating_hours` (
  `hours_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `day_of_week` tinyint NOT NULL,
  `opens_at` time DEFAULT NULL,
  `closes_at` time DEFAULT NULL,
  `is_24_hours` tinyint(1) DEFAULT '0',
  `operating_status_id` int NOT NULL,
  `special_note` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`hours_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `operating_status_id` (`operating_status_id`),
  CONSTRAINT `service_provider_operating_hours_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_operating_hours_ibfk_2` FOREIGN KEY (`operating_status_id`) REFERENCES `service_provider_operating_hours_status` (`operating_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_operating_hours`
--

LOCK TABLES `service_provider_operating_hours` WRITE;
/*!40000 ALTER TABLE `service_provider_operating_hours` DISABLE KEYS */;
INSERT INTO `service_provider_operating_hours` VALUES (1,1,1,'06:00:00','22:00:00',0,1,'Breakfast served until 11:00','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,'06:00:00','22:00:00',0,1,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,1,3,'06:00:00','22:00:00',0,1,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,1,4,'06:00:00','22:00:00',0,1,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,1,5,'06:00:00','23:00:00',0,1,'Extended hours on Friday','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,2,1,'00:00:00','23:59:59',1,1,'24/7 reception','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,3,1,'07:00:00','21:00:00',0,1,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,4,1,'00:00:00','23:59:59',1,1,'Private villa - 24/7 access','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,5,1,'06:30:00','22:30:00',0,1,'Business center opens at 06:30','2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(10,6,1,'11:00:00','22:00:00',0,1,'Lunch specials 11am-3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,6,2,'11:00:00','22:00:00',0,1,'Lunch specials 11am-3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(12,6,3,'11:00:00','22:00:00',0,1,'Lunch specials 11am-3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(13,6,4,'11:00:00','23:00:00',0,1,'Extended hours Thursday','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(14,6,5,'11:00:00','23:00:00',0,1,'Live music Friday nights','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(15,6,6,'16:00:00','23:00:00',0,1,'Dinner only on Saturday','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(16,6,7,'16:00:00','22:00:00',0,1,'Family Sunday specials','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(17,7,1,'10:30:00','22:30:00',0,1,'Dim sum until 3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(18,7,2,'10:30:00','22:30:00',0,1,'Dim sum until 3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(19,7,3,'10:30:00','22:30:00',0,1,'Dim sum until 3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(20,7,4,'10:30:00','23:00:00',0,1,NULL,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(21,7,5,'10:30:00','23:00:00',0,1,NULL,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(22,7,6,'10:30:00','23:00:00',0,1,NULL,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(23,7,7,'10:30:00','22:30:00',0,1,NULL,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(24,8,1,'11:00:00','23:00:00',0,1,'Happy hour 4pm-7pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(25,8,2,'11:00:00','23:00:00',0,1,'Happy hour 4pm-7pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(26,8,3,'11:00:00','23:00:00',0,1,'Happy hour 4pm-7pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(27,8,4,'11:00:00','00:00:00',0,1,'Late night Thursday','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(28,8,5,'11:00:00','01:00:00',0,1,'Weekend late night','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(29,8,6,'11:00:00','01:00:00',0,1,'Weekend late night','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(30,8,7,'11:00:00','23:00:00',0,1,'Sunday brunch 11am-3pm','2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(31,6,1,'06:00:00','23:00:00',0,1,'Beach access until sunset','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(32,6,2,'06:00:00','23:00:00',0,1,'Beach access until sunset','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(33,6,3,'06:00:00','23:00:00',0,1,'Beach access until sunset','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(34,6,4,'06:00:00','23:00:00',0,1,'Beach access until sunset','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(35,6,5,'06:00:00','00:00:00',0,1,'Extended weekend hours','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(36,7,1,'00:00:00','23:59:59',1,1,'24/7 concierge service','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(37,7,2,'00:00:00','23:59:59',1,1,'24/7 concierge service','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(38,7,3,'00:00:00','23:59:59',1,1,'24/7 concierge service','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(39,8,1,'07:00:00','22:00:00',0,1,'Garden tours at 10am and 3pm','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(40,8,2,'07:00:00','22:00:00',0,1,'Garden tours at 10am and 3pm','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(41,8,3,'07:00:00','22:00:00',0,1,'Garden tours at 10am and 3pm','2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_operating_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_operating_hours_status`
--

DROP TABLE IF EXISTS `service_provider_operating_hours_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_operating_hours_status` (
  `operating_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`operating_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_operating_hours_status_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_operating_hours_status`
--

LOCK TABLES `service_provider_operating_hours_status` WRITE;
/*!40000 ALTER TABLE `service_provider_operating_hours_status` DISABLE KEYS */;
INSERT INTO `service_provider_operating_hours_status` VALUES (1,'OPEN','Fully operational',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'CLOSED','Not operating',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'HOLIDAY','Closed for holiday',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'SEASONAL','Seasonal operation',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'RENOVATION','Closed for renovation',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_operating_hours_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_package`
--

DROP TABLE IF EXISTS `service_provider_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_package` (
  `service_provider_package_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `local_price` decimal(10,2) DEFAULT NULL,
  `foreign_price` decimal(10,2) DEFAULT NULL,
  `currency_id` int NOT NULL,
  `discount_percentage` decimal(5,2) DEFAULT '0.00',
  `discount_requirements` text,
  `duration_days` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `min_persons` int DEFAULT '1',
  `max_persons` int DEFAULT NULL,
  `includes_children` tinyint(1) DEFAULT '0',
  `max_children_included` int DEFAULT '0',
  `is_customizable` tinyint(1) DEFAULT '0',
  `booking_deadline_days` int DEFAULT NULL,
  `package_code` varchar(50) DEFAULT NULL,
  `package_category` varchar(100) DEFAULT NULL,
  `season_type` varchar(50) DEFAULT NULL,
  `advance_booking_days` int DEFAULT NULL,
  `cancellation_policy` text,
  `refund_policy` text,
  `terms_conditions` text,
  `highlights` json DEFAULT NULL,
  `special_note` text,
  `requirements` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`service_provider_package_id`),
  UNIQUE KEY `package_code` (`package_code`),
  KEY `currency_id` (`currency_id`),
  KEY `idx_package_provider` (`service_provider_id`),
  KEY `idx_package_dates` (`start_date`,`end_date`),
  KEY `idx_package_status` (`status_id`),
  KEY `idx_package_dates_active` (`start_date`,`end_date`,`status_id`),
  CONSTRAINT `service_provider_package_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_package_ibfk_2` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_package_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_package`
--

LOCK TABLES `service_provider_package` WRITE;
/*!40000 ALTER TABLE `service_provider_package` DISABLE KEYS */;
INSERT INTO `service_provider_package` VALUES (1,1,'Weekend Getaway','2-night stay with breakfast and spa credit',450.00,450.00,1,10.00,NULL,2,NULL,NULL,2,2,0,0,0,NULL,'GP-WKND-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Business Traveler','3-night stay with executive amenities',600.00,600.00,1,5.00,NULL,3,NULL,NULL,1,1,0,0,0,NULL,'GP-BUS-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Beach Escape','4-night all-inclusive beach package',1200.00,1200.00,1,15.00,NULL,4,NULL,NULL,2,4,0,0,0,NULL,'SB-ESC-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'Mountain Retreat','3-night nature package with hiking',350.00,350.00,1,0.00,NULL,3,NULL,NULL,2,2,0,0,0,NULL,'MV-RET-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Luxury Villa Experience','7-night private villa with chef',4200.00,4200.00,1,0.00,NULL,7,NULL,NULL,4,6,0,0,0,NULL,'RV-LUX-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'City Break','2-night downtown experience',320.00,320.00,1,8.00,NULL,2,NULL,NULL,2,2,0,0,0,NULL,'CC-CITY-001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_package_feature`
--

DROP TABLE IF EXISTS `service_provider_package_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_package_feature` (
  `package_feature_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_package_id` int NOT NULL,
  `feature_name` varchar(255) NOT NULL,
  `feature_value` varchar(255) DEFAULT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`package_feature_id`),
  KEY `service_provider_package_id` (`service_provider_package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_package_feature_ibfk_1` FOREIGN KEY (`service_provider_package_id`) REFERENCES `service_provider_package` (`service_provider_package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_package_feature_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_package_feature`
--

LOCK TABLES `service_provider_package_feature` WRITE;
/*!40000 ALTER TABLE `service_provider_package_feature` DISABLE KEYS */;
INSERT INTO `service_provider_package_feature` VALUES (1,1,'Inclusions','Breakfast + Spa Credit','Daily breakfast and $100 spa credit',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Duration','2 Nights','Weekend stay package',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Business Amenities','Executive Lounge','Access to executive lounge',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'All-Inclusive','Meals + Activities','All meals and selected activities included',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Nature Activities','Guided Hiking','Daily guided mountain hikes',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'Private Services','Personal Chef','Dedicated chef service',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_package_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_package_image`
--

DROP TABLE IF EXISTS `service_provider_package_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_package_image` (
  `package_image_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_package_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_description` text,
  `caption` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`package_image_id`),
  KEY `service_provider_package_id` (`service_provider_package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_package_image_ibfk_1` FOREIGN KEY (`service_provider_package_id`) REFERENCES `service_provider_package` (`service_provider_package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_package_image_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_package_image`
--

LOCK TABLES `service_provider_package_image` WRITE;
/*!40000 ALTER TABLE `service_provider_package_image` DISABLE KEYS */;
INSERT INTO `service_provider_package_image` VALUES (1,1,'/images/service-provider/packages/package-1.jpg','Weekend Getaway','Relaxing weekend experience','Perfect Escape',1,'2025-10-25 07:56:42',1,'2025-10-25 17:48:29',NULL,NULL,NULL),(2,2,'/images/service-provider/packages/package-2.jpg','Business Travel','Executive business amenities','Productive Stay',1,'2025-10-25 07:56:42',1,'2025-10-25 17:48:29',NULL,NULL,NULL),(3,3,'/images/service-provider/packages/package-3.jpg','Beach Escape','Tropical beach vacation','Island Paradise',1,'2025-10-25 07:56:42',1,'2025-10-25 17:48:29',NULL,NULL,NULL),(4,4,'/images/service-provider/packages/package-4.jpg','Mountain Retreat','Nature mountain getaway','Alpine Adventure',1,'2025-10-25 07:56:42',1,'2025-10-25 17:48:29',NULL,NULL,NULL),(5,5,'/images/service-provider/packages/package-5.jpg','Villa Experience','Luxury private villa stay','Ultimate Privacy',1,'2025-10-25 07:56:42',1,'2025-10-25 17:48:29',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_package_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_package_inclusion`
--

DROP TABLE IF EXISTS `service_provider_package_inclusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_package_inclusion` (
  `inclusion_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_package_id` int NOT NULL,
  `inclusion_name` varchar(255) NOT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`inclusion_id`),
  KEY `service_provider_package_id` (`service_provider_package_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_package_inclusion_ibfk_1` FOREIGN KEY (`service_provider_package_id`) REFERENCES `service_provider_package` (`service_provider_package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_package_inclusion_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_package_inclusion`
--

LOCK TABLES `service_provider_package_inclusion` WRITE;
/*!40000 ALTER TABLE `service_provider_package_inclusion` DISABLE KEYS */;
INSERT INTO `service_provider_package_inclusion` VALUES (1,1,'Accommodation','2 nights in deluxe room',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Breakfast','Daily continental breakfast',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,1,'Spa Credit','$100 towards spa services',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'Executive Room','3 nights in executive suite',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,2,'Airport Transfer','Round-trip airport transportation',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,3,'All Meals','Breakfast, lunch, and dinner',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,3,'Water Sports','Unlimited non-motorized water sports',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,4,'Mountain Accommodation','3 nights in mountain view room',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,4,'Hiking Tours','Guided nature walks',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_package_inclusion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_review`
--

DROP TABLE IF EXISTS `service_provider_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `user_id` int NOT NULL,
  `rating` tinyint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `comment` text,
  `is_approved` tinyint(1) DEFAULT '0',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `user_id` (`user_id`),
  KEY `status_id` (`status_id`),
  KEY `idx_review_rating_approved` (`rating`,`is_approved`,`status_id`),
  CONSTRAINT `service_provider_review_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_review_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_review_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `chk_rating_range` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_review`
--

LOCK TABLES `service_provider_review` WRITE;
/*!40000 ALTER TABLE `service_provider_review` DISABLE KEYS */;
INSERT INTO `service_provider_review` VALUES (1,1,1,5,'Excellent Stay!','Amazing service and beautiful rooms. Will definitely return!',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,4,'Great Location','Perfect location and friendly staff. Room was comfortable.',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,3,5,'Paradise Found','Absolutely stunning beach and wonderful amenities.',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,1,4,'Beautiful Resort','Great food and service. Pool area was fantastic.',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,2,4,'Cozy Retreat','Perfect mountain getaway. Very peaceful and relaxing.',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,3,5,'Ultimate Luxury','The villa exceeded all expectations. Pure luxury!',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,1,4,'Business Friendly','Perfect for business travel. Great amenities and location.',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,6,1,5,'Authentic Italian Experience','The pasta was incredible and the atmosphere was perfect. Felt like we were in Italy!',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(9,6,2,4,'Great Food, Slow Service','Food was excellent but service was a bit slow during peak hours.',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(10,7,3,5,'Best Chinese in Town','The dim sum was fresh and flavorful. Will definitely be returning!',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(11,7,1,4,'Authentic Flavors','Great traditional Chinese dishes. The Peking Duck was outstanding.',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(12,8,2,4,'Amazing Burgers','Best burgers I have had in a long time. The craft beer selection is also great.',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(13,8,3,3,'Good but Pricey','Burgers were good but a bit expensive for what you get.',1,1,'2025-10-25 13:13:33',1,'2025-10-25 13:13:33',NULL,NULL,NULL),(14,11,1,4,'Great for Solo Travelers','Met amazing people here. The atmosphere is perfect for solo travelers.',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(15,11,2,3,'Basic but Clean','Rooms are basic but very clean. Good value for money.',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(16,12,3,5,'Best Hostel Experience','Incredible facilities and location. The rooftop terrace is amazing!',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(17,12,1,4,'Perfect Location','Right in the city center. Easy access to everything.',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(18,13,2,4,'Adventure Base','Perfect for hikers and outdoor enthusiasts. Great community feel.',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(19,13,3,3,'Rustic Experience','Basic amenities but authentic mountain experience.',1,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(20,6,2,5,'Perfect Beach Getaway','Amazing ocean views and friendly staff. The infinity pool was incredible!',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(21,6,3,4,'Great Location','Right on the beach with easy access to everything.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(22,7,1,5,'Historic Elegance','Beautiful historic property with modern amenities. Truly special.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(23,7,2,4,'Luxury Experience','Exceptional service and beautiful rooms. Worth every penny.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(24,8,3,4,'Peaceful Retreat','Beautiful gardens and peaceful atmosphere. Perfect for relaxation.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(25,8,1,5,'Eco-Friendly Paradise','Loved the sustainable approach and organic food. Will return!',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(26,9,1,5,'Ski Paradise','Perfect ski resort with amazing slopes and luxurious amenities.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(27,9,2,4,'Winter Wonderland','Beautiful mountain views and excellent service. Spa was incredible.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(28,10,3,5,'Desert Luxury','Stunning desert resort with world-class golf and spa facilities.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(29,10,1,4,'Golfers Dream','Championship course and perfect weather. Will definitely return.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(30,11,2,5,'Island Paradise','True all-inclusive luxury. The private beach was breathtaking.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(31,11,3,5,'Family Heaven','Perfect for families with amazing kids activities and beautiful rooms.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(32,12,1,5,'Breathtaking Views','The cliffside location is absolutely stunning. Private pool was incredible.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(33,12,2,5,'Ultimate Privacy','Complete seclusion with world-class amenities. Perfect honeymoon spot.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(34,13,3,5,'Forest Sanctuary','Tranquil forest setting with luxurious comforts. Wildlife sightings were magical.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(35,13,1,4,'Nature Luxury','Beautiful balance of wilderness and luxury. Hot tub under stars was unforgettable.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(36,14,2,5,'Wine Lovers Dream','Private vineyard access and wine experiences were exceptional.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(37,14,3,5,'Vineyard Paradise','Stunning views and premium wine amenities. Will return for harvest season.',1,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_review_rating_category`
--

DROP TABLE IF EXISTS `service_provider_review_rating_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_review_rating_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_review_rating_category_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_review_rating_category`
--

LOCK TABLES `service_provider_review_rating_category` WRITE;
/*!40000 ALTER TABLE `service_provider_review_rating_category` DISABLE KEYS */;
INSERT INTO `service_provider_review_rating_category` VALUES (1,'Cleanliness','Cleanliness of facilities',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'Service','Quality of service',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'Location','Convenience of location',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'Value','Value for money',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'Amenities','Quality of amenities',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_review_rating_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_review_rating_detail`
--

DROP TABLE IF EXISTS `service_provider_review_rating_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_review_rating_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `review_id` int NOT NULL,
  `category_id` int NOT NULL,
  `rating` tinyint NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `review_id` (`review_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `service_provider_review_rating_detail_ibfk_1` FOREIGN KEY (`review_id`) REFERENCES `service_provider_review` (`review_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_review_rating_detail_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `service_provider_review_rating_category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_review_rating_detail`
--

LOCK TABLES `service_provider_review_rating_detail` WRITE;
/*!40000 ALTER TABLE `service_provider_review_rating_detail` DISABLE KEYS */;
INSERT INTO `service_provider_review_rating_detail` VALUES (1,1,1,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,1,3,4,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,1,4,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,1,5,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,2,1,4,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,2,2,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,2,3,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,2,4,4,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(10,2,5,4,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(11,3,1,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(12,3,2,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(13,3,3,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(14,3,4,4,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(15,3,5,5,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_review_rating_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room`
--

DROP TABLE IF EXISTS `service_provider_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `room_type_id` int NOT NULL,
  `room_number` varchar(50) DEFAULT NULL,
  `description` text,
  `capacity` int DEFAULT NULL,
  `room_size` decimal(6,2) DEFAULT NULL,
  `bed_type` varchar(100) DEFAULT NULL,
  `number_of_beds` int DEFAULT NULL,
  `number_of_bathrooms` int DEFAULT '1',
  `max_adults` int DEFAULT NULL,
  `max_children` int DEFAULT NULL,
  `smoking_allowed` tinyint(1) DEFAULT '0',
  `is_accessible` tinyint(1) DEFAULT '0',
  `local_price_per_night` decimal(10,2) DEFAULT NULL,
  `foreign_price_per_night` decimal(10,2) DEFAULT NULL,
  `currency_id` int NOT NULL,
  `discount_percentage` decimal(5,2) DEFAULT '0.00',
  `discount_requirements` text,
  `room_floor` int DEFAULT NULL,
  `view_type` varchar(100) DEFAULT NULL,
  `has_balcony` tinyint(1) DEFAULT '0',
  `has_air_conditioning` tinyint(1) DEFAULT '0',
  `has_tv` tinyint(1) DEFAULT '0',
  `has_minibar` tinyint(1) DEFAULT '0',
  `has_safe` tinyint(1) DEFAULT '0',
  `has_kitchenette` tinyint(1) DEFAULT '0',
  `internet_access` tinyint(1) DEFAULT '0',
  `room_quality_rating` tinyint DEFAULT NULL,
  `extra_bed_available` tinyint(1) DEFAULT '0',
  `extra_bed_charge` decimal(10,2) DEFAULT '0.00',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `currency_id` (`currency_id`),
  KEY `idx_room_provider` (`service_provider_id`),
  KEY `idx_room_status` (`status_id`),
  KEY `idx_room_type` (`room_type_id`),
  CONSTRAINT `service_provider_room_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_ibfk_2` FOREIGN KEY (`room_type_id`) REFERENCES `service_provider_room_type` (`room_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`currency_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room`
--

LOCK TABLES `service_provider_room` WRITE;
/*!40000 ALTER TABLE `service_provider_room` DISABLE KEYS */;
INSERT INTO `service_provider_room` VALUES (1,1,2,'201','Deluxe King Room with city view',2,35.00,'King',1,1,NULL,NULL,0,0,199.00,199.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,3,'301','Executive Suite with living area',3,55.00,'King',1,1,NULL,NULL,0,0,350.00,350.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,1,'101','Standard Beach View Room',2,28.00,'Queen',1,1,NULL,NULL,0,0,150.00,150.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,4,'201','Family Room with bunk beds',4,45.00,'Queen + Bunk',2,1,NULL,NULL,0,0,220.00,220.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,1,'102','Cozy Mountain View Room',2,25.00,'Double',1,1,NULL,NULL,0,0,89.00,89.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,3,'Villa-1','Luxury Private Villa with Pool',6,120.00,'King + Queen',3,1,NULL,NULL,0,0,650.00,650.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,5,'501','Executive Business Room',2,32.00,'King',1,1,NULL,NULL,0,0,180.00,180.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,1,1,'101','Standard Room',2,25.00,'Queen',1,1,NULL,NULL,0,0,129.00,129.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,11,1,'DORM-101','8-bed mixed dormitory',8,40.00,'Bunk Beds',4,1,NULL,NULL,0,0,25.00,25.00,1,0.00,NULL,NULL,NULL,0,1,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(10,11,2,'DORM-201','6-bed female dormitory',6,35.00,'Bunk Beds',3,1,NULL,NULL,0,0,28.00,28.00,1,0.00,NULL,NULL,NULL,0,1,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(11,11,4,'PRIV-301','Private single room',1,12.00,'Single',1,1,NULL,NULL,0,0,65.00,65.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(12,12,1,'MIX-101','10-bed mixed dormitory',10,50.00,'Bunk Beds',5,1,NULL,NULL,0,0,30.00,30.00,1,0.00,NULL,NULL,NULL,0,1,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(13,12,2,'FEM-201','8-bed female dormitory',8,40.00,'Bunk Beds',4,1,NULL,NULL,0,0,32.00,32.00,1,0.00,NULL,NULL,NULL,0,1,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(14,12,6,'PRIV-301','Private twin room',2,18.00,'Twin',2,1,NULL,NULL,0,0,85.00,85.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(15,13,1,'DORM-1','6-bed mixed dormitory',6,35.00,'Bunk Beds',3,1,NULL,NULL,0,0,22.00,22.00,1,0.00,NULL,NULL,NULL,0,0,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(16,13,3,'DORM-2','4-bed male dormitory',4,25.00,'Bunk Beds',2,1,NULL,NULL,0,0,24.00,24.00,1,0.00,NULL,NULL,NULL,0,0,0,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(17,13,5,'PRIV-1','Private double room',2,15.00,'Double',1,1,NULL,NULL,0,0,70.00,70.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(18,6,2,'301','Ocean View Deluxe Room',2,38.00,'King',1,1,NULL,NULL,0,0,220.00,220.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(19,6,4,'401','Family Ocean Suite',4,55.00,'King + Double',2,1,NULL,NULL,0,0,320.00,320.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(20,6,1,'201','Standard Ocean Room',2,30.00,'Queen',1,1,NULL,NULL,0,0,180.00,180.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(21,7,3,'501','Heritage Suite',3,65.00,'King',1,1,NULL,NULL,0,0,450.00,450.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(22,7,5,'601','Executive Heritage Room',2,40.00,'King',1,1,NULL,NULL,0,0,280.00,280.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(23,7,2,'401','Deluxe Historic Room',2,35.00,'Queen',1,1,NULL,NULL,0,0,220.00,220.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(24,8,4,'101','Garden Family Suite',4,50.00,'Queen + Twin',2,1,NULL,NULL,0,0,190.00,190.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(25,8,2,'201','Garden View Deluxe',2,32.00,'King',1,1,NULL,NULL,0,0,160.00,160.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(26,8,1,'301','Standard Garden Room',2,28.00,'Queen',1,1,NULL,NULL,0,0,120.00,120.00,1,0.00,NULL,NULL,NULL,0,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(27,9,3,'MP-301','Mountain View Suite',4,75.00,'King + Sofa Bed',2,1,NULL,NULL,0,0,450.00,450.00,1,0.00,NULL,NULL,'Mountain',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(28,9,2,'MP-201','Ski Lodge Room',2,45.00,'King',1,1,NULL,NULL,0,0,320.00,320.00,1,0.00,NULL,NULL,'Slope',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(29,9,4,'MP-401','Family Chalet',6,95.00,'King + Queen + Bunk',3,1,NULL,NULL,0,0,580.00,580.00,1,0.00,NULL,NULL,'Valley',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(30,10,3,'DO-501','Desert Villa Suite',4,80.00,'King + Queen',2,1,NULL,NULL,0,0,520.00,520.00,1,0.00,NULL,NULL,'Desert',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(31,10,5,'DO-301','Golf View Room',2,42.00,'King',1,1,NULL,NULL,0,0,380.00,380.00,1,0.00,NULL,NULL,'Golf Course',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(32,10,2,'DO-201','Poolside Deluxe',2,38.00,'King',1,1,NULL,NULL,0,0,290.00,290.00,1,0.00,NULL,NULL,'Pool',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(33,11,3,'TP-601','Beachfront Suite',4,85.00,'King + Twin',2,1,NULL,NULL,0,0,680.00,680.00,1,0.00,NULL,NULL,'Ocean',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(34,11,2,'TP-401','Ocean View Room',2,48.00,'King',1,1,NULL,NULL,0,0,420.00,420.00,1,0.00,NULL,NULL,'Ocean',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(35,11,4,'TP-301','Family Bungalow',5,65.00,'King + Bunk',2,1,NULL,NULL,0,0,550.00,550.00,1,0.00,NULL,NULL,'Garden',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(36,12,3,'CV-1','Ocean Cliff Villa',6,180.00,'King + Queen + Twin',3,1,NULL,NULL,0,0,1200.00,1200.00,1,0.00,NULL,NULL,'Ocean Cliff',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(37,12,3,'CV-2','Sunset Villa',4,140.00,'King + Queen',2,1,NULL,NULL,0,0,950.00,950.00,1,0.00,NULL,NULL,'Ocean Sunset',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(38,12,3,'CV-3','Seaview Villa',8,220.00,'King + King + Queen',3,1,NULL,NULL,0,0,1600.00,1600.00,1,0.00,NULL,NULL,'Panoramic Ocean',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(39,13,3,'FH-1','Treehouse Villa',4,120.00,'King + Loft',2,1,NULL,NULL,0,0,750.00,750.00,1,0.00,NULL,NULL,'Forest Canopy',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(40,13,3,'FH-2','Creek Side Villa',6,160.00,'King + Queen + Bunk',3,1,NULL,NULL,0,0,980.00,980.00,1,0.00,NULL,NULL,'Forest Creek',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(41,13,3,'FH-3','Mountain View Villa',2,100.00,'King',1,1,NULL,NULL,0,0,650.00,650.00,1,0.00,NULL,NULL,'Mountain',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(42,14,3,'VE-1','Vineyard Master Villa',8,200.00,'King + King + Queen',3,1,NULL,NULL,0,0,1400.00,1400.00,1,0.00,NULL,NULL,'Vineyard',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(43,14,3,'VE-2','Wine Cellar Villa',4,130.00,'King + Queen',2,1,NULL,NULL,0,0,850.00,850.00,1,0.00,NULL,NULL,'Vineyard Valley',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL),(44,14,3,'VE-3','Harvest Villa',6,170.00,'King + Queen + Twin',3,1,NULL,NULL,0,0,1100.00,1100.00,1,0.00,NULL,NULL,'Sunset Vineyard',1,1,1,0,0,0,1,NULL,0,0.00,1,'2025-10-25 13:19:38',1,'2025-10-25 13:19:38',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room_amenity`
--

DROP TABLE IF EXISTS `service_provider_room_amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room_amenity` (
  `room_amenity_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `amenity_type_id` int NOT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  `additional_notes` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`room_amenity_id`),
  UNIQUE KEY `unique_room_amenity` (`room_id`,`amenity_type_id`),
  KEY `amenity_type_id` (`amenity_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_room_amenity_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `service_provider_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_amenity_ibfk_2` FOREIGN KEY (`amenity_type_id`) REFERENCES `amenity_type` (`amenity_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_amenity_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room_amenity`
--

LOCK TABLES `service_provider_room_amenity` WRITE;
/*!40000 ALTER TABLE `service_provider_room_amenity` DISABLE KEYS */;
INSERT INTO `service_provider_room_amenity` VALUES (1,1,1,1,'High-speed internet',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,2,0,'No pool access from this room',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,1,1,'Premium business internet',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,3,1,'Private gym access',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,1,1,'Standard WiFi',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,1,1,'Family-friendly internet',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,1,1,'Mountain view WiFi',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,6,1,1,'Villa premium internet',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,7,1,1,'Business-grade internet',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room_amenity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room_availability`
--

DROP TABLE IF EXISTS `service_provider_room_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room_availability` (
  `availability_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `date` date NOT NULL,
  `available_rooms` int DEFAULT '0',
  `booked_rooms` int DEFAULT '0',
  `local_price_for_date` decimal(10,2) DEFAULT NULL,
  `foreign_price_for_date` decimal(10,2) DEFAULT NULL,
  `requirements` text,
  `special_note` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`availability_id`),
  UNIQUE KEY `unique_room_date` (`room_id`,`date`),
  KEY `idx_room_availability` (`date`,`available_rooms`),
  CONSTRAINT `service_provider_room_availability_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `service_provider_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room_availability`
--

LOCK TABLES `service_provider_room_availability` WRITE;
/*!40000 ALTER TABLE `service_provider_room_availability` DISABLE KEYS */;
INSERT INTO `service_provider_room_availability` VALUES (1,1,'2024-12-25',5,0,250.00,250.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'2024-12-26',5,0,250.00,250.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'2024-12-25',2,0,450.00,450.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'2024-12-25',15,0,180.00,180.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'2024-12-25',8,0,270.00,270.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'2024-12-25',12,0,100.00,100.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,6,'2024-12-25',3,0,750.00,750.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(8,7,'2024-12-25',10,0,200.00,200.00,NULL,NULL,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(9,8,'2025-10-25',8,0,25.00,25.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(10,8,'2025-10-26',6,2,25.00,25.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(11,9,'2025-10-25',6,0,28.00,28.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(12,9,'2025-10-26',4,2,28.00,28.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(13,10,'2025-10-25',1,0,65.00,65.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(14,10,'2025-10-26',0,1,65.00,65.00,NULL,NULL,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room_feature`
--

DROP TABLE IF EXISTS `service_provider_room_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room_feature` (
  `room_feature_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `feature_name` varchar(255) NOT NULL,
  `feature_value` varchar(255) DEFAULT NULL,
  `description` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`room_feature_id`),
  KEY `room_id` (`room_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_room_feature_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `service_provider_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_feature_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room_feature`
--

LOCK TABLES `service_provider_room_feature` WRITE;
/*!40000 ALTER TABLE `service_provider_room_feature` DISABLE KEYS */;
INSERT INTO `service_provider_room_feature` VALUES (1,1,'View Type','City View','Panoramic city skyline',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Floor Level','20','High floor with better view',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'View Type','Executive View','Premium city panorama',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'Room Service','24-hour','Round-the-clock service',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,'View Type','Beach View','Direct ocean view',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,'Bed Configuration','Queen + Bunk Beds','Family-friendly setup',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,'View Type','Mountain View','Scenic mountain landscape',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room_image`
--

DROP TABLE IF EXISTS `service_provider_room_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room_image` (
  `room_image_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_description` text,
  `caption` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`room_image_id`),
  KEY `room_id` (`room_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_room_image_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `service_provider_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_room_image_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room_image`
--

LOCK TABLES `service_provider_room_image` WRITE;
/*!40000 ALTER TABLE `service_provider_room_image` DISABLE KEYS */;
INSERT INTO `service_provider_room_image` VALUES (1,1,'/images/service-provider/rooms/room-1.jpg','Deluxe Room Bedroom','Spacious bedroom with king bed','Luxury Sleeping',1,'2025-10-25 07:56:42',1,'2025-10-25 17:51:06',NULL,NULL,NULL),(2,1,'/images/service-provider/rooms/room-2.jpg','Deluxe Room Bathroom','Modern bathroom amenities','Spa-like Bathroom',1,'2025-10-25 07:56:42',1,'2025-10-25 17:51:06',NULL,NULL,NULL),(3,2,'/images/service-provider/rooms/room-3.jpg','Executive Suite Living','Separate living area','Executive Living',1,'2025-10-25 07:56:42',1,'2025-10-25 17:51:06',NULL,NULL,NULL),(4,3,'/images/service-provider/rooms/room-4.jpg','Beach View Room','Room with ocean view','Ocean Breeze',1,'2025-10-25 07:56:42',1,'2025-10-25 17:51:06',NULL,NULL,NULL),(5,4,'/images/service-provider/rooms/room-5.jpg','Family Room Interior','Spacious family accommodation','Family Comfort',1,'2025-10-25 07:56:42',1,'2025-10-25 17:51:06',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_room_type`
--

DROP TABLE IF EXISTS `service_provider_room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_room_type` (
  `room_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`room_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_room_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_room_type`
--

LOCK TABLES `service_provider_room_type` WRITE;
/*!40000 ALTER TABLE `service_provider_room_type` DISABLE KEYS */;
INSERT INTO `service_provider_room_type` VALUES (1,'Standard','Basic room with essential amenities',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'Deluxe','Upgraded room with additional features',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'Suite','Spacious room with separate living area',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'Family','Room suitable for families',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'Executive','Business-class room',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,'Dormitory - Mixed','Shared dormitory room mixed gender',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(7,'Dormitory - Female Only','Shared dormitory room female only',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(8,'Dormitory - Male Only','Shared dormitory room male only',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(9,'Private Single','Private room with single bed',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(10,'Private Double','Private room with double bed',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL),(11,'Private Twin','Private room with two single beds',1,'2025-10-25 13:15:30',1,'2025-10-25 13:15:30',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_seasonal_pricing`
--

DROP TABLE IF EXISTS `service_provider_seasonal_pricing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_seasonal_pricing` (
  `seasonal_price_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `local_multiplier` decimal(3,2) DEFAULT '1.00',
  `foreign_multiplier` decimal(3,2) DEFAULT '1.00',
  `description` text,
  `requirements` text,
  `special_note` text,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`seasonal_price_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_seasonal_pricing_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_seasonal_pricing_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_seasonal_pricing`
--

LOCK TABLES `service_provider_seasonal_pricing` WRITE;
/*!40000 ALTER TABLE `service_provider_seasonal_pricing` DISABLE KEYS */;
INSERT INTO `service_provider_seasonal_pricing` VALUES (1,1,'Summer Peak','2024-06-01','2024-08-31',1.25,1.25,'High season summer rates',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Winter Holiday','2024-12-20','2025-01-05',1.35,1.35,'Christmas and New Year premium',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Beach Season','2024-05-15','2024-09-15',1.30,1.30,'Peak beach weather pricing',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,3,'Fall Foliage','2024-09-20','2024-10-31',1.20,1.20,'Autumn leaf viewing season',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,4,'Festive Season','2024-12-01','2025-01-10',1.40,1.40,'Luxury holiday experience',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,5,'Business Conference','2024-03-01','2024-05-31',1.15,1.15,'Spring business travel season',NULL,NULL,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_seasonal_pricing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_social`
--

DROP TABLE IF EXISTS `service_provider_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_social` (
  `social_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `platform` varchar(50) NOT NULL,
  `profile_url` varchar(255) DEFAULT NULL,
  `verification_status_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`social_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `verification_status_id` (`verification_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_social_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_social_ibfk_2` FOREIGN KEY (`verification_status_id`) REFERENCES `service_provider_social_verification_status` (`verification_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `service_provider_social_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_social`
--

LOCK TABLES `service_provider_social` WRITE;
/*!40000 ALTER TABLE `service_provider_social` DISABLE KEYS */;
INSERT INTO `service_provider_social` VALUES (1,1,'Facebook','https://facebook.com/grandplazahotel',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'Instagram','https://instagram.com/grandplazahotel',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Facebook','https://facebook.com/sunsetbeachresort',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'Twitter','https://twitter.com/sunsetbeach',2,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,'Instagram','https://instagram.com/mountainviewinn',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,'Facebook','https://facebook.com/royalvillaestate',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,'LinkedIn','https://linkedin.com/company/citycentralhotel',1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_social_verification_status`
--

DROP TABLE IF EXISTS `service_provider_social_verification_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_social_verification_status` (
  `verification_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`verification_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_social_verification_status_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_social_verification_status`
--

LOCK TABLES `service_provider_social_verification_status` WRITE;
/*!40000 ALTER TABLE `service_provider_social_verification_status` DISABLE KEYS */;
INSERT INTO `service_provider_social_verification_status` VALUES (1,'VERIFIED','Officially verified',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'UNVERIFIED','Not verified',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'PENDING','Verification pending',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'REJECTED','Verification rejected',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'SUSPENDED','Temporarily suspended',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_social_verification_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_statistics`
--

DROP TABLE IF EXISTS `service_provider_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_statistics` (
  `stats_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `total_bookings` int DEFAULT '0',
  `total_revenue` decimal(15,2) DEFAULT '0.00',
  `average_rating` decimal(3,2) DEFAULT '0.00',
  `total_reviews` int DEFAULT '0',
  `occupancy_rate` decimal(5,2) DEFAULT '0.00',
  `last_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stats_id`),
  UNIQUE KEY `unique_provider_stats` (`service_provider_id`),
  CONSTRAINT `service_provider_statistics_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_statistics`
--

LOCK TABLES `service_provider_statistics` WRITE;
/*!40000 ALTER TABLE `service_provider_statistics` DISABLE KEYS */;
INSERT INTO `service_provider_statistics` VALUES (1,1,1250,450000.00,4.50,890,78.50,'2025-10-25 07:56:42'),(2,2,980,320000.00,4.30,650,82.20,'2025-10-25 07:56:42'),(3,3,450,95000.00,4.10,320,65.80,'2025-10-25 07:56:42'),(4,4,280,420000.00,4.80,190,58.30,'2025-10-25 07:56:42'),(5,5,890,280000.00,4.40,540,75.60,'2025-10-25 07:56:42');
/*!40000 ALTER TABLE `service_provider_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_tax_configuration`
--

DROP TABLE IF EXISTS `service_provider_tax_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_tax_configuration` (
  `tax_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` int NOT NULL,
  `tax_name` varchar(100) NOT NULL,
  `tax_percentage` decimal(5,2) NOT NULL,
  `tax_number` varchar(100) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `applies_to_rooms` tinyint(1) DEFAULT '1',
  `applies_to_meals` tinyint(1) DEFAULT '1',
  `applies_to_packages` tinyint(1) DEFAULT '1',
  `applies_to_amenities` tinyint(1) DEFAULT '1',
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`tax_id`),
  KEY `service_provider_id` (`service_provider_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_tax_configuration_ibfk_1` FOREIGN KEY (`service_provider_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_provider_tax_configuration_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_tax_configuration`
--

LOCK TABLES `service_provider_tax_configuration` WRITE;
/*!40000 ALTER TABLE `service_provider_tax_configuration` DISABLE KEYS */;
INSERT INTO `service_provider_tax_configuration` VALUES (1,1,'City Tax',8.50,'TX-001-GPH',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,1,'State Tax',6.00,'TX-002-GPH',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,2,'Tourism Tax',5.00,'TX-001-SBR',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,2,'Resort Fee',12.00,'RF-001-SBR',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,3,'County Tax',7.25,'TX-001-MVI',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(6,4,'Luxury Tax',10.00,'TX-001-RVE',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(7,5,'Business Tax',8.75,'TX-001-CCH',1,1,1,1,1,1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_tax_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_provider_type`
--

DROP TABLE IF EXISTS `service_provider_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_provider_type` (
  `service_provider_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`service_provider_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `service_provider_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_provider_type`
--

LOCK TABLES `service_provider_type` WRITE;
/*!40000 ALTER TABLE `service_provider_type` DISABLE KEYS */;
INSERT INTO `service_provider_type` VALUES (1,'Hotel','Accommodation provider with rooms',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(2,'Resort','Luxury accommodation with amenities',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(3,'Villa','Private vacation rental',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(4,'Restaurant','Food and beverage service',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL),(5,'Hostel','Budget accommodation',1,'2025-10-25 07:56:42',1,'2025-10-25 07:56:42',NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_provider_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_media`
--

DROP TABLE IF EXISTS `social_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) NOT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `hover_color` varchar(50) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_social_status` (`status`),
  CONSTRAINT `fk_social_status` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_media`
--

LOCK TABLES `social_media` WRITE;
/*!40000 ALTER TABLE `social_media` DISABLE KEYS */;
INSERT INTO `social_media` VALUES (1,'YouTube','Watch our travel videos','https://youtube.com/yourchannel','/icons/youtube.png','#FF0000','#CC0000',1,'2025-09-30 05:43:06',1,'2025-12-15 13:13:04',NULL,NULL,NULL,'felicita travels'),(2,'Instagram','Check our Instagram','https://instagram.com/yourpage','/icons/instagram.png','#E1306C','#C13584',1,'2025-09-30 05:43:06',1,'2025-12-15 13:13:04',NULL,NULL,NULL,'felicita travels'),(3,'Facebook','Follow us on Facebook','https://facebook.com/yourpage','/icons/facebook.png','#4267B2','#365899',2,'2025-09-30 05:43:06',1,'2025-12-15 13:18:38',NULL,NULL,NULL,'felicita travels'),(4,'Twitter','Follow us on Twitter','https://twitter.com/yourpage','/icons/twitter.png','#1DA1F2','#0d95e8',1,'2025-09-30 05:43:06',1,'2025-12-15 13:13:04',NULL,NULL,NULL,'felicita travels'),(5,'LinkedIn','Connect with us on LinkedIn','https://linkedin.com/company/yourpage','/icons/linkedin.png','#0077B5','#005582',1,'2025-09-30 05:43:06',1,'2025-12-15 13:13:04',NULL,NULL,NULL,'felicita travels'),(6,'TikTok','Short travel videos and tips','https://tiktok.com/@FelicitaTravel','/icons/tiktok.png','#000000','#1A1A1A',2,'2025-09-30 05:43:06',1,'2025-12-15 13:18:38',NULL,NULL,NULL,'felicita travels');
/*!40000 ALTER TABLE `social_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_media_best_for`
--

DROP TABLE IF EXISTS `social_media_best_for`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_media_best_for` (
  `id` int NOT NULL AUTO_INCREMENT,
  `social_media_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_social_media_id` (`social_media_id`),
  KEY `idx_status_id` (`status_id`),
  CONSTRAINT `fk_smbf_social_media` FOREIGN KEY (`social_media_id`) REFERENCES `social_media` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_smbf_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_media_best_for`
--

LOCK TABLES `social_media_best_for` WRITE;
/*!40000 ALTER TABLE `social_media_best_for` DISABLE KEYS */;
INSERT INTO `social_media_best_for` VALUES (1,3,'Travel Inspiration','Ideal for inspiring travel content',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(2,3,'Promotions','Best for sharing offers and deals',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(3,3,'Customer Reviews','Great for testimonials and reviews',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(4,2,'Travel Photos','High-quality destination photography',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(5,2,'Reels','Short engaging travel videos',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(6,2,'Destination Ideas','Visual travel inspiration',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(7,1,'Video Tours','Virtual tours of destinations',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(8,1,'Travel Guides','Detailed travel guide videos',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(9,1,'Testimonials','Customer experience videos',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(10,4,'Customer Service','Quick responses and support',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(11,4,'Updates','Real-time travel updates',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(12,4,'Travel News','Latest industry and destination news',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(13,5,'Business Travel','Corporate and business travel services',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(14,5,'Corporate Partnerships','B2B collaborations and partners',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(15,5,'Industry News','Professional travel industry updates',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(16,6,'Quick Tips','Short and engaging travel tips',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(17,6,'Trending Destinations','Popular and viral travel spots',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL),(18,6,'Fun Content','Entertaining travel-related videos',1,'2025-12-15 12:52:47',1,'2025-12-15 12:52:47',NULL,NULL,NULL);
/*!40000 ALTER TABLE `social_media_best_for` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_media_platforms`
--

DROP TABLE IF EXISTS `social_media_platforms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_media_platforms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(50) NOT NULL,
  `platform_icon` varchar(100) DEFAULT NULL,
  `base_url` varchar(100) DEFAULT NULL,
  `description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_id` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `platform_name` (`platform_name`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `social_media_platforms_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_media_platforms`
--

LOCK TABLES `social_media_platforms` WRITE;
/*!40000 ALTER TABLE `social_media_platforms` DISABLE KEYS */;
INSERT INTO `social_media_platforms` VALUES (1,'LinkedIn','fa-linkedin','https://linkedin.com','Professional networking platform','2025-12-04 15:49:18',1),(2,'Facebook','fa-facebook','https://facebook.com','Social networking platform','2025-12-04 15:49:18',1),(3,'Instagram','fa-instagram','https://instagram.com','Photo and video sharing platform','2025-12-04 15:49:18',1),(4,'Twitter/X','fa-twitter','https://twitter.com','Microblogging and social networking','2025-12-04 15:49:18',1);
/*!40000 ALTER TABLE `social_media_platforms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` text,
  `color` varchar(50) DEFAULT NULL,
  `hover_color` varchar(255) DEFAULT NULL,
  `value` int DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `statistics_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` VALUES (1,'Destinations','https://cdn-icons-png.flaticon.com/512/3178/3178288.png','Total Destinations','Amazing destinations across Sri Lanka and beyond','#3B82F6','#1D4ED8',50,1,'2025-12-03 05:50:09',1,'2025-12-03 05:50:09',1,NULL,NULL),(2,'Tours','https://cdn-icons-png.flaticon.com/512/2536/2536649.png','Total Tours','Curated tour packages for every traveler','#10B981','#047857',120,1,'2025-12-03 05:50:09',1,'2025-12-03 05:50:09',1,NULL,NULL),(3,'Completed Tours','https://cdn-icons-png.flaticon.com/512/190/190411.png','Completed Tours','Successfully completed travel experiences','#F59E0B','#D97706',850,2,'2025-12-03 05:50:09',1,'2025-12-11 18:36:23',1,NULL,NULL),(4,'Bookings','https://cdn-icons-png.flaticon.com/512/3135/3135715.png','Total Bookings','Confirmed travel bookings and reservations','#8B5CF6','#7C3AED',1200,2,'2025-12-03 05:50:09',1,'2025-12-11 18:36:23',1,NULL,NULL),(5,'Satisfied Clients','https://cdn-icons-png.flaticon.com/512/1029/1029183.png','Happy Travelers','Satisfied clients who traveled with us','#EC4899','#DB2777',10000,2,'2025-12-03 05:50:09',1,'2025-12-03 05:50:09',1,NULL,NULL),(6,'Employees','https://cdn-icons-png.flaticon.com/512/3135/3135719.png','Total Employees','Dedicated team members across our offices','#06B6D4','#0891B2',50,1,'2025-12-03 05:50:09',1,'2025-12-11 18:36:23',1,NULL,NULL),(7,'Years Experience','https://cdn-icons-png.flaticon.com/512/3208/3208720.png','Years in Operation','Over a decade of travel expertise','#EF4444','#DC2626',14,1,'2025-12-03 05:50:09',1,'2025-12-11 18:36:47',1,NULL,NULL),(8,'Awards','https://cdn-icons-png.flaticon.com/512/3135/3135716.png','Industry Awards','Recognition from tourism authorities','#14B8A6','#0D9488',8,2,'2025-12-03 05:50:09',1,'2025-12-11 18:36:23',1,NULL,NULL);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `status` (`status`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'Adventure','Blogs related to adventure activities and experiences',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(2,'Travel','Travel tips, destinations, and guides',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(3,'Technology','Latest trends, gadgets, and tech news',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(4,'Food','Recipes, restaurants, and culinary experiences',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(5,'Lifestyle','Lifestyle tips, trends, and personal growth',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(6,'Health','Health, fitness, and wellness topics',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(7,'Finance','Finance tips, investments, and money management',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(8,'Education','Educational resources and learning tips',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(9,'Sports','Sports news, events, and analysis',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL),(10,'Entertainment','Movies, music, and celebrity news',1,'2025-12-12 10:44:00',NULL,'2025-12-12 10:44:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour` (
  `tour_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `tour_type` int DEFAULT NULL,
  `tour_category` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `start_location` varchar(255) DEFAULT NULL,
  `end_location` varchar(255) DEFAULT NULL,
  `season` int DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`tour_id`),
  KEY `tour_type` (`tour_type`),
  KEY `tour_category` (`tour_category`),
  KEY `season` (`season`),
  KEY `status` (`status`),
  CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`tour_type`) REFERENCES `tour_type` (`id`),
  CONSTRAINT `tour_ibfk_2` FOREIGN KEY (`tour_category`) REFERENCES `tour_category` (`id`),
  CONSTRAINT `tour_ibfk_3` FOREIGN KEY (`season`) REFERENCES `seasons` (`id`),
  CONSTRAINT `tour_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,'Sigiriya Rock Fortress Adventure','Climb the ancient rock fortress and explore UNESCO heritage site',1,4,1,7.95700000,80.76030000,'Colombo','Sigiriya',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(2,'Cultural Triangle Tour','Visit ancient cities: Anuradhapura, Polonnaruwa, Sigiriya',2,2,5,8.31140000,80.40370000,'Colombo','Anuradhapura',2,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(3,'Yala Wildlife Safari','Spot leopards, elephants and diverse wildlife in national park',3,3,2,6.37250000,81.51940000,'Tissamaharama','Yala',2,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(4,'Mirissa Beach Retreat','Relax on pristine beaches and enjoy whale watching',4,5,2,5.94500000,80.44890000,'Galle','Mirissa',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(5,'Ella Hill Country Explorer','Scenic train journey through tea plantations and waterfalls',1,3,3,6.86670000,81.04670000,'Kandy','Ella',4,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(6,'Bentota Spa & Wellness Escape','Rejuvenate with spa treatments, yoga, and beachside relaxation',5,1,3,6.42100000,80.00500000,'Colombo','Bentota',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(7,'Kandy Temple & Cultural Dance Tour','Experience the Temple of the Tooth and traditional dance shows',2,3,2,7.29060000,80.63370000,'Kandy','Kandy',4,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(8,'Knuckles Mountain Hiking','Trek through Knuckles mountain range with scenic landscapes',1,5,2,7.38980000,80.78400000,'Kandy','Knuckles Range',3,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(9,'Wilpattu Safari Experience','Explore Sri Lanka’s largest national park with luxury camping',3,1,3,8.45900000,80.10000000,'Puttalam','Wilpattu',2,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(10,'Arugam Bay Surf Trip','World-class surfing destination popular with backpackers',4,4,4,6.83900000,81.83400000,'Colombo','Arugam Bay',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(11,'Ayurvedic Healing Retreat in Dambulla','Traditional Ayurveda treatments and meditation programs',5,5,3,7.85700000,80.65100000,'Colombo','Dambulla',4,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(12,'Jaffna Heritage & Food Tour','Discover Jaffna’s temples, forts, and unique cuisine',2,1,4,9.66850000,80.00740000,'Colombo','Jaffna',2,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(13,'Kitulgala White Water Rafting','Thrilling rafting experience on Kelani River',1,2,1,6.98860000,80.42500000,'Colombo','Kitulgala',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(14,'Trincomalee Beach & Snorkeling','Family-friendly beach stay with snorkeling at Pigeon Island',4,3,3,8.57110000,81.23350000,'Colombo','Trincomalee',1,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL),(15,'Udawalawe Elephant Safari','Jeep safari to see large herds of elephants in the wild',3,4,1,6.42670000,80.89870000,'Colombo','Udawalawe',2,1,'2025-10-04 16:02:19',1,'2025-10-04 16:02:19',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_category`
--

DROP TABLE IF EXISTS `tour_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_category_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_category`
--

LOCK TABLES `tour_category` WRITE;
/*!40000 ALTER TABLE `tour_category` DISABLE KEYS */;
INSERT INTO `tour_category` VALUES (1,'Luxury','Premium experiences with high-end accommodations',1,'2025-10-04 15:56:54',1,'2025-10-04 15:56:54',NULL,NULL,NULL),(2,'Budget','Affordable tours for budget travelers',1,'2025-10-04 15:56:54',1,'2025-10-04 15:56:54',NULL,NULL,NULL),(3,'Family','Family-friendly tours suitable for all ages',1,'2025-10-04 15:56:54',1,'2025-10-04 15:56:54',NULL,NULL,NULL),(4,'Solo','Perfect for solo travelers and backpackers',1,'2025-10-04 15:56:54',1,'2025-10-04 15:56:54',NULL,NULL,NULL),(5,'Group','Large group tours with shared experiences',1,'2025-10-04 15:56:54',1,'2025-10-04 15:56:54',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_day_accommodation`
--

DROP TABLE IF EXISTS `tour_day_accommodation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_day_accommodation` (
  `tour_day_accommodation_id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int NOT NULL,
  `day` int NOT NULL,
  `breakfast` tinyint(1) DEFAULT '0',
  `breakfast_description` text,
  `lunch` tinyint(1) DEFAULT '0',
  `lunch_description` text,
  `dinner` tinyint(1) DEFAULT '0',
  `dinner_description` text,
  `morning_tea` tinyint(1) DEFAULT '0',
  `morning_tea_description` text,
  `evening_tea` tinyint(1) DEFAULT '0',
  `evening_tea_description` text,
  `snacks` tinyint(1) DEFAULT '0',
  `snack_note` text,
  `hotel_id` int DEFAULT NULL,
  `transport_id` int DEFAULT NULL,
  `other_notes` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`tour_day_accommodation_id`),
  UNIQUE KEY `uq_tour_day` (`tour_id`,`day`),
  KEY `fk_tour_day_accommodation_hotel` (`hotel_id`),
  KEY `fk_tour_day_accommodation_transport` (`transport_id`),
  CONSTRAINT `fk_tour_day_accommodation_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `service_provider` (`service_provider_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_day_accommodation_tour` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_day_accommodation_transport` FOREIGN KEY (`transport_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_day_accommodation`
--

LOCK TABLES `tour_day_accommodation` WRITE;
/*!40000 ALTER TABLE `tour_day_accommodation` DISABLE KEYS */;
INSERT INTO `tour_day_accommodation` VALUES (1,1,1,1,'Breakfast at hotel restaurant',1,'Lunch at local restaurant',0,NULL,1,'Tea at hotel lobby',1,'Tea during evening travel',0,NULL,1,1,'Early start, light meals','2025-12-17 17:04:26',NULL,'2025-12-17 17:04:26',NULL),(2,1,2,1,'Breakfast at hotel',0,NULL,1,'Dinner at beach restaurant',0,NULL,1,'Evening tea at hotel',1,'Snacks during travel',2,1,'Check-out and transfer','2025-12-17 17:04:26',NULL,'2025-12-17 17:04:26',NULL),(3,2,1,1,'Hotel buffet breakfast',1,'Lunch en route',1,'Dinner at resort',1,'Morning tea stop',1,'Evening tea at hotel',0,NULL,1,2,'Arrival day','2025-12-17 17:04:26',NULL,'2025-12-17 17:04:26',NULL),(4,2,2,1,'Breakfast at hotel',1,'Lunch at city restaurant',1,'Dinner cultural show',0,NULL,1,'Tea during sightseeing',1,'Snacks provided',1,2,'Full sightseeing day','2025-12-17 17:04:26',NULL,'2025-12-17 17:04:26',NULL),(5,2,3,1,'Final day breakfast',0,NULL,0,NULL,0,NULL,0,NULL,0,NULL,2,2,'Departure day','2025-12-17 17:04:26',NULL,'2025-12-17 17:04:26',NULL);
/*!40000 ALTER TABLE `tour_day_accommodation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_destination`
--

DROP TABLE IF EXISTS `tour_destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_destination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int NOT NULL,
  `destination_id` int NOT NULL,
  `activities_id` int DEFAULT NULL,
  `day` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destination_id` (`destination_id`),
  KEY `tour_destination_ibfk_1` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_destination`
--

LOCK TABLES `tour_destination` WRITE;
/*!40000 ALTER TABLE `tour_destination` DISABLE KEYS */;
INSERT INTO `tour_destination` VALUES (1,1,11,1,1),(2,1,12,6,2),(3,2,4,NULL,NULL),(4,2,11,NULL,NULL),(5,2,12,NULL,NULL),(6,2,13,NULL,NULL),(7,3,5,NULL,NULL),(8,3,6,NULL,NULL),(9,4,14,NULL,NULL),(10,4,15,NULL,NULL),(11,4,16,NULL,NULL),(12,5,3,NULL,NULL),(13,5,16,NULL,NULL),(14,5,17,NULL,NULL),(15,5,18,NULL,NULL),(16,5,20,NULL,NULL),(17,6,14,NULL,NULL),(18,6,15,NULL,NULL),(19,6,19,NULL,NULL),(20,7,11,NULL,NULL),(21,7,12,NULL,NULL),(22,7,14,NULL,NULL),(23,8,2,NULL,NULL),(24,8,3,NULL,NULL),(25,9,8,NULL,NULL),(26,9,13,NULL,NULL),(27,10,18,NULL,NULL),(28,10,19,NULL,NULL),(29,11,4,NULL,NULL),(30,11,11,NULL,NULL),(31,12,12,NULL,NULL),(32,12,13,NULL,NULL),(33,13,1,NULL,NULL),(34,13,5,NULL,NULL),(35,14,10,NULL,NULL),(36,14,18,NULL,NULL),(37,14,19,NULL,NULL),(38,15,5,NULL,NULL),(39,15,6,NULL,NULL),(40,15,7,NULL,NULL),(41,1,11,2,1),(42,1,11,3,1),(43,1,12,4,2),(44,1,12,5,2),(45,1,13,9,1);
/*!40000 ALTER TABLE `tour_destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_hero_section`
--

DROP TABLE IF EXISTS `tour_hero_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_hero_section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(150) DEFAULT NULL,
  `description` text,
  `primary_button_text` varchar(50) DEFAULT NULL,
  `primary_button_link` varchar(255) DEFAULT NULL,
  `secondary_button_text` varchar(50) DEFAULT NULL,
  `secondary_button_link` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_hero_section_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_hero_section`
--

LOCK TABLES `tour_hero_section` WRITE;
/*!40000 ALTER TABLE `tour_hero_section` DISABLE KEYS */;
INSERT INTO `tour_hero_section` VALUES (1,'beach-paradise','https://images.unsplash.com/photo-1544551763-46a013bb70d5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Discover Sri Lanka\'s Golden Beaches','Where Turquoise Waters Meet Golden Sands','Experience the breathtaking coastal beauty of Sri Lanka, from the palm-fringed beaches of Mirissa to the surfing paradise of Arugam Bay. Our curated beach tours offer the perfect blend of relaxation and adventure.','Explore Beaches','beach','View Packages','colombo',1,1,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(2,'cultural-heritage','https://images.unsplash.com/photo-1585506936724-fa0c19c7b7c4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Journey Through Ancient Kingdoms','Explore Sri Lanka\'s Rich Cultural Tapestry','Walk through UNESCO World Heritage sites, ancient temples, and historic fortresses. Discover the architectural marvels of Sigiriya Rock Fortress and the sacred city of Kandy.','Cultural Tours','cultural','Plan Your Visit','galle',1,2,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(3,'wildlife-adventure','https://images.unsplash.com/photo-1579444741963-5bce5eb9d1d2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Wildlife Safari Adventures','Encounter Sri Lanka\'s Majestic Wildlife','Embark on thrilling safaris in Yala, Udawalawe, and Wilpattu National Parks. Spot leopards, elephants, sloth bears, and diverse bird species in their natural habitats.','Book Safari','wildlife','Wildlife Guide','mirissa',1,3,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(4,'tea-country','https://images.unsplash.com/photo-1523348837708-15d4a09cfac2?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Misty Tea Plantations','Breathtaking Views of Hill Country','Journey through the emerald green tea estates of Nuwara Eliya, Ella, and Hatton. Experience tea tasting, scenic train rides, and cool mountain climate.','Hill Country Tours','hill-country','Tea Experiences','yala',1,4,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(5,'adventure-tours','https://images.unsplash.com/photo-1551632811-561732d1e306?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Extreme Adventure Experiences','Thrilling Activities Across the Island','From hiking Adam\'s Peak at sunrise to white water rafting in Kitulgala, discover adrenaline-pumping adventures across Sri Lanka\'s diverse landscapes.','Adventure Packages','adventure','View Activities','ella',1,5,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(6,'ayurveda-wellness','https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Ayurveda & Wellness Retreats','Rejuvenate Your Mind, Body & Soul','Experience traditional Ayurvedic treatments, yoga retreats, and meditation sessions in serene environments. Perfect for holistic healing and relaxation.','Wellness Packages','wellness','Book Retreat','bentota',1,6,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(7,'food-tours','https://images.unsplash.com/photo-1565557623262-b51c2513a641?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Sri Lankan Culinary Journey','A Feast for Your Senses','Discover authentic Sri Lankan cuisine through cooking classes, street food tours, and traditional dining experiences. Taste spices, seafood, and exotic tropical fruits.','Food Experiences','foods','Cooking Classes','jaffna',1,7,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(8,'family-holidays','https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Perfect Family Getaways','Memorable Vacations for All Ages','Family-friendly tours featuring elephant orphanages, turtle conservation, train rides, and beach activities. Create unforgettable memories together.','Family Packages','family-holidays','Kid-Friendly Activities','kithulgala',1,8,'2025-12-15 14:05:35',1,'2025-12-16 13:11:14',1,NULL,NULL),(9,'honeymoon-paradise','https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80','Romantic Sri Lankan Escapes','Dream Honeymoon Destinations','Luxury romantic getaways featuring private villas, sunset dinners, couple spa treatments, and intimate beach experiences across the island.','Honeymoon Packages','honeymoon','Romantic Experiences','trincomalee',1,9,'2025-12-15 14:05:35',1,'2025-12-16 13:11:18',1,NULL,NULL);
/*!40000 ALTER TABLE `tour_hero_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_history`
--

DROP TABLE IF EXISTS `tour_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_schedule_id` int NOT NULL,
  `package_id` int DEFAULT NULL,
  `number_of_participate` int DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `hover_color` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `vehicle_number` varchar(255) DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  `guide_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_schedule_id` (`tour_schedule_id`),
  KEY `package_id` (`package_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_history_ibfk_1` FOREIGN KEY (`tour_schedule_id`) REFERENCES `tour_schedule` (`id`),
  CONSTRAINT `tour_history_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `tour_history_ibfk_3` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_history`
--

LOCK TABLES `tour_history` WRITE;
/*!40000 ALTER TABLE `tour_history` DISABLE KEYS */;
INSERT INTO `tour_history` VALUES (1,1,1,6,4.80,1,'Sigiriya Tour - October Group','Successful tour with positive feedback',1,'#FF6B6B','#FF5252','2025-10-15','2025-10-15','CAR-1234,CAR-5678',1,1,'2025-10-04 16:15:22',1,'2025-10-04 16:15:22',NULL,NULL,NULL),(2,2,2,4,4.90,3,'Ella Adventure - November Batch','Excellent weather and great experience',1,'#4ECDC4','#3BB8B0','2025-11-08','2025-11-10','VAN-9876',2,2,'2025-10-04 16:15:22',1,'2025-10-04 16:15:22',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_history_images`
--

DROP TABLE IF EXISTS `tour_history_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_history_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_schedule_id` (`tour_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_history_images_ibfk_1` FOREIGN KEY (`tour_schedule_id`) REFERENCES `tour_schedule` (`id`),
  CONSTRAINT `tour_history_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_history_images`
--

LOCK TABLES `tour_history_images` WRITE;
/*!40000 ALTER TABLE `tour_history_images` DISABLE KEYS */;
INSERT INTO `tour_history_images` VALUES (1,1,'Sigiriya Group Oct 15','Group photo at Sigiriya fortress base',1,'/images/tour-history/sigiriya-group-oct-15.jpg','#FF6B6B','2025-10-04 19:41:16',1,'2025-10-04 19:41:16',NULL,NULL,NULL),(2,1,'Sigiriya Summit View','View captured during October tour',1,'/images/tour-history/sigiriya-summit-view.jpg','#FF6B6B','2025-10-04 19:41:16',1,'2025-10-04 19:41:16',NULL,NULL,NULL),(3,2,'Ella Train Ride','Scenic train journey November batch',1,'/images/tour-history/ella-train-ride.jpg','#4ECDC4','2025-10-04 19:41:16',1,'2025-10-04 19:41:16',NULL,NULL,NULL),(4,2,'Ella Nine Arch Bridge','Group at Nine Arch Bridge',1,'/images/tour-history/ella-nine-arch-bridge.jpg','#4ECDC4','2025-10-04 19:41:16',1,'2025-10-04 19:41:16',NULL,NULL,NULL),(5,2,'Ella Accommodation','Beautiful hotel with mountain view',1,'/images/tour-history/ella-accommodation.webp','#4ECDC4','2025-10-04 19:41:16',1,'2025-10-04 19:41:16',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_history_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_images`
--

DROP TABLE IF EXISTS `tour_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_id` (`tour_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_images_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  CONSTRAINT `tour_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_images`
--

LOCK TABLES `tour_images` WRITE;
/*!40000 ALTER TABLE `tour_images` DISABLE KEYS */;
INSERT INTO `tour_images` VALUES (1,1,'Sigiriya Rock View','Panoramic view of Sigiriya Rock Fortress','/images/tour-images/sigiriya-rock-view.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(2,1,'Lion’s Gate Steps','Climbers ascending through the Lion’s Gate','/images/tour-images/lions-gate-steps.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(3,1,'Ancient Frescoes','Beautiful wall paintings inside the fortress','/images/tour-images/ancient-frescoes.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(4,1,'Sunset at Sigiriya','Golden hour view of the Sigiriya Rock','/images/tour-images/sunset-at-sigiriya.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(5,2,'Anuradhapura Stupa','Ancient Buddhist stupa surrounded by pilgrims','/images/tour-images/anuradhapura-stupa.jpeg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(6,2,'Polonnaruwa Ruins','Old royal palace ruins from the Polonnaruwa kingdom','/images/tour-images/polonnaruwa-ruins.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(7,2,'Sigiriya Gardens','Geometric water gardens of Sigiriya','/images/tour-images/sigiriya-gardens.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(8,2,'Mihintale Hills','Spiritual sunrise view from Mihintale','/images/tour-images/mihintale-hills.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(9,3,'Leopard in Yala','Leopard resting on a tree branch','/images/tour-images/leopard-in-yala.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(10,3,'Elephants at Waterhole','Elephants drinking at a safari waterhole','/images/tour-images/elephants-at-waterhole.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(11,3,'Safari Jeep','Tourists exploring Yala by jeep','/images/tour-images/safari-jeep.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(12,3,'Sunset Safari','Evening view of Yala National Park','/images/tour-images/sunset-safari.png',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(13,4,'Mirissa Beach','Golden beach with turquoise waves','/images/tour-images/mirissa-beach.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(14,4,'Whale Watching','Blue whale emerging from the ocean','/images/tour-images/whale-watching.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(15,4,'Coconut Hill','Iconic hilltop viewpoint in Mirissa','/images/tour-images/coconut-hill.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(16,4,'Beach Sunset','Romantic sunset along Mirissa shoreline','/images/tour-images/beach-sunset.jpeg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(17,5,'Nine Arches Bridge','Train passing over the famous bridge','/images/tour-images/nine-arches-bridge.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(18,5,'Little Adam’s Peak','Hikers reaching the summit at dawn','/images/tour-images/little-adam-peak.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(19,5,'Tea Plantations','Tea estates with workers plucking leaves','/images/tour-images/tea-plantations.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(20,5,'Ella Gap View','Scenic valley view from Ella Rock','/images/tour-images/ella-gap-view.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(21,6,'Beach Resort','Luxury spa by the ocean','/images/tour-images/beach-resort.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(22,6,'Yoga Retreat','Morning yoga session by the beach','/images/tour-images/yoga-retreat.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(23,6,'Ayurvedic Massage','Therapist performing full-body oil massage','/images/tour-images/ayurvedic-massage.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(24,6,'Tropical Gardens','Tranquil resort gardens with palm trees','/images/tour-images/tropical-gardens.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(25,7,'Temple of the Tooth','Exterior of the sacred temple','/images/tour-images/temple-of-the-tooth.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(26,7,'Cultural Dance Show','Traditional Kandyan dancers performing','/images/tour-images/cultural-dance-show.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(27,7,'Royal Botanic Gardens','Beautiful blooms in Peradeniya Gardens','/images/tour-images/royal-botanic-gardens.jpeg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(28,7,'Kandy Lake','Serene view of the lake at sunset','/images/tour-images/kandy-lake.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(29,8,'Mountain Trail','Hikers walking through misty forests','/images/tour-images/mountain-trail.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(30,8,'Waterfall Spot','Hidden waterfall deep in the Knuckles range','/images/tour-images/waterfall-spot.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(31,8,'Campfire Night','Campers resting under the stars','/images/tour-images/campfire-night.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(32,8,'Panoramic View','Breathtaking aerial view of Knuckles mountains','/images/tour-images/panoramic-view.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(33,9,'Wilpattu Lakes','Scenic lakes where animals gather','/images/tour-images/wilpattu-lakes.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(34,9,'Camping Lodges','Luxury camping under the stars','/images/tour-images/camping-lodges.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(35,9,'Wildlife Spotting','Tourists spotting animals with binoculars','/images/tour-images/wildlife-spotting.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(36,9,'Park Entrance','Main gate of Wilpattu National Park','/images/tour-images/park-entrance.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(37,10,'Surf Waves','Surfer riding perfect waves at Arugam Bay','/images/tour-images/surf-waves.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(38,10,'Beach Bars','Evening beach bars with music and bonfires','/images/tour-images/beach-bars.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(39,10,'Surf Boards','Colorful surfboards lined up on the sand','/images/tour-images/surf-boards.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(40,10,'Palm Trees','Shady palms swaying along the coast','/images/tour-images/palm-trees.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(41,11,'Ayurveda Center','Guests receiving herbal treatments','/images/tour-images/ayurveda-center.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(42,11,'Meditation Hall','Peaceful meditation in nature','/images/tour-images/meditation-hall.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(43,11,'Herbal Garden','Organic herbs used for healing','/images/tour-images/herbal-garden.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(44,11,'Relaxation Zone','Open-air spa surrounded by greenery','/images/tour-images/relaxation-zone.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(45,12,'Nallur Temple','Vibrant temple during festival','/images/tour-images/nallur-temple.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(46,12,'Jaffna Fort','Historic Dutch fort by the sea','/images/tour-images/jaffna-fort.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(47,12,'Local Cuisine','Traditional Jaffna crab curry','/images/tour-images/local-cuisine.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(48,12,'Market Visit','Vendors selling local produce','/images/tour-images/market-visit.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(49,13,'Rafting Rapids','Adventure team paddling through rapids','/images/tour-images/rafting-rapids.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(50,13,'River Base Camp','Rafting base camp beside Kelani River','/images/tour-images/river-base-camp.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(51,13,'Canyoning Jump','Tourists jumping into canyon pools','/images/tour-images/canyoning-jump.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(52,13,'Rainforest Trail','Pathway through tropical forest','/images/tour-images/rainforest-trail.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(53,14,'Pigeon Island','Snorkelers exploring coral reefs','/images/tour-images/pigeon-island.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(54,14,'Beach Resort','Resort overlooking turquoise ocean','/images/tour-images/beach-resort.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(55,14,'Dolphin Watching','Dolphins swimming near boats','/images/tour-images/dolphin-watching.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(56,14,'White Sands','Calm and scenic Trincomalee beaches','/images/tour-images/white-sands.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(57,15,'Elephant Herd','Group of elephants crossing the savanna','/images/tour-images/elephant-herd.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(58,15,'Safari Jeep Ride','Tourists viewing elephants from jeep','/images/tour-images/safari-jeep-ride.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(59,15,'Bird Watching','Exotic birds near Udawalawe reservoir','/images/tour-images/bird-watching.webp',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL),(60,15,'Morning Mist','Sunrise over the Udawalawe landscape','/images/tour-images/morning-mist.jpg',1,'2025-10-04 17:25:28',1,'2025-10-04 17:25:28',1,NULL,NULL);
/*!40000 ALTER TABLE `tour_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_review`
--

DROP TABLE IF EXISTS `tour_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_schedule_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `review` text,
  `rating` decimal(3,2) DEFAULT NULL,
  `description` text,
  `status` int NOT NULL,
  `number_of_participate` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_schedule_id` (`tour_schedule_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_review_ibfk_1` FOREIGN KEY (`tour_schedule_id`) REFERENCES `tour_schedule` (`id`),
  CONSTRAINT `tour_review_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_review`
--

LOCK TABLES `tour_review` WRITE;
/*!40000 ALTER TABLE `tour_review` DISABLE KEYS */;
INSERT INTO `tour_review` VALUES (1,1,'Amazing Sigiriya Experience','The climb was challenging but absolutely worth it. Our guide was knowledgeable and the views were breathtaking!',4.80,'Highly recommended for history buffs',1,6,'2025-10-04 16:15:32',1,'2025-10-04 16:15:32',NULL,NULL,NULL),(2,2,'Best Ella Trip Ever','Three perfect days in paradise. The train ride, hikes, and accommodation were all excellent.',4.90,'Perfect for nature lovers',1,4,'2025-10-04 16:15:32',1,'2025-10-04 16:15:32',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_review_comment`
--

DROP TABLE IF EXISTS `tour_review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_review_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_review_id` int NOT NULL,
  `parent_comment_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `comment` text NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_review_id` (`tour_review_id`),
  KEY `parent_comment_id` (`parent_comment_id`),
  KEY `user_id` (`user_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_review_comment_ibfk_1` FOREIGN KEY (`tour_review_id`) REFERENCES `tour_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_ibfk_2` FOREIGN KEY (`parent_comment_id`) REFERENCES `tour_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_review_comment`
--

LOCK TABLES `tour_review_comment` WRITE;
/*!40000 ALTER TABLE `tour_review_comment` DISABLE KEYS */;
INSERT INTO `tour_review_comment` VALUES (1,1,NULL,2,'Absolutely agree! The view from the top was breathtaking.',1,'2025-10-21 14:54:17',2,'2025-10-21 14:54:17',NULL,NULL,NULL),(2,1,NULL,2,'Was it too crowded when you went?',1,'2025-10-21 14:54:17',3,'2025-10-21 14:54:17',NULL,NULL,NULL),(3,1,2,1,'Not really, we went early morning — best time!',1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL,NULL,NULL),(4,2,NULL,1,'Ella is such a peaceful place. Loved the Nine Arches Bridge!',1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL,NULL,NULL),(5,2,NULL,1,'Did you visit Little Adam’s Peak as well?',1,'2025-10-21 14:54:17',3,'2025-10-21 14:54:17',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_review_comment_reaction`
--

DROP TABLE IF EXISTS `tour_review_comment_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_review_comment_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id` (`comment_id`,`user_id`,`reaction_type_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_review_comment_reaction_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `tour_review_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tour_review_comment_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_review_comment_reaction`
--

LOCK TABLES `tour_review_comment_reaction` WRITE;
/*!40000 ALTER TABLE `tour_review_comment_reaction` DISABLE KEYS */;
INSERT INTO `tour_review_comment_reaction` VALUES (1,1,1,1,1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL),(2,2,1,3,1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL),(3,3,2,2,1,'2025-10-21 14:54:17',3,'2025-10-21 14:54:17',NULL),(4,4,2,1,1,'2025-10-21 14:54:17',2,'2025-10-21 14:54:17',NULL),(5,5,1,3,1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL);
/*!40000 ALTER TABLE `tour_review_comment_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_review_images`
--

DROP TABLE IF EXISTS `tour_review_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_review_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_review_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `image_url` varchar(500) DEFAULT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_review_id` (`tour_review_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_review_images_ibfk_1` FOREIGN KEY (`tour_review_id`) REFERENCES `tour_review` (`id`),
  CONSTRAINT `tour_review_images_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_review_images`
--

LOCK TABLES `tour_review_images` WRITE;
/*!40000 ALTER TABLE `tour_review_images` DISABLE KEYS */;
INSERT INTO `tour_review_images` VALUES (1,1,'Guest Photo - Sigiriya Top','Photo taken by guest at summit','/images/tour-review/sigiriya-review.png',1,'2025-10-04 19:52:27',1,'2025-10-04 19:52:27',NULL,NULL,NULL),(2,1,'Guest Photo - Sigiriya Top','Photo taken by guest at summit','/images/tour-review/sigiriya-review.png',1,'2025-10-04 19:54:14',1,'2025-10-04 19:54:14',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_review_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_review_reaction`
--

DROP TABLE IF EXISTS `tour_review_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_review_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tour_review_id` int NOT NULL,
  `user_id` int NOT NULL,
  `reaction_type_id` int NOT NULL,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tour_review_id` (`tour_review_id`,`user_id`,`reaction_type_id`),
  KEY `user_id` (`user_id`),
  KEY `reaction_type_id` (`reaction_type_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_review_reaction_ibfk_1` FOREIGN KEY (`tour_review_id`) REFERENCES `tour_review` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_reaction_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_review_reaction_ibfk_3` FOREIGN KEY (`reaction_type_id`) REFERENCES `reaction_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tour_review_reaction_ibfk_4` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_review_reaction`
--

LOCK TABLES `tour_review_reaction` WRITE;
/*!40000 ALTER TABLE `tour_review_reaction` DISABLE KEYS */;
INSERT INTO `tour_review_reaction` VALUES (1,1,1,1,1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL),(2,1,2,2,1,'2025-10-21 14:54:17',2,'2025-10-21 14:54:17',NULL),(3,2,1,1,1,'2025-10-21 14:54:17',1,'2025-10-21 14:54:17',NULL),(4,2,2,3,1,'2025-10-21 14:54:17',3,'2025-10-21 14:54:17',NULL);
/*!40000 ALTER TABLE `tour_review_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_schedule`
--

DROP TABLE IF EXISTS `tour_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tour_id` int NOT NULL,
  `assume_start_date` date DEFAULT NULL,
  `assume_end_date` date DEFAULT NULL,
  `duration_start` int DEFAULT NULL,
  `duration_end` int DEFAULT NULL,
  `special_note` text,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_id` (`tour_id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_schedule_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  CONSTRAINT `tour_schedule_ibfk_2` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_schedule`
--

LOCK TABLES `tour_schedule` WRITE;
/*!40000 ALTER TABLE `tour_schedule` DISABLE KEYS */;
INSERT INTO `tour_schedule` VALUES (1,'Sigiriya October Schedule',1,'2025-10-01','2025-10-31',1,1,'Morning climb recommended','Daily departures available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(2,'Sigiriya November Schedule',1,'2025-11-01','2025-11-30',1,1,'Bring water and sunscreen','Weekday and weekend departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(3,'Sigiriya December Schedule',1,'2025-12-01','2025-12-31',1,1,'Sunrise tour highly recommended','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(4,'Cultural Triangle October Schedule',2,'2025-10-01','2025-10-31',5,5,'Guided tour preferred','Weekend batches available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(5,'Cultural Triangle November Schedule',2,'2025-11-01','2025-11-30',5,5,'Comfortable shoes recommended','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(6,'Cultural Triangle December Schedule',2,'2025-12-01','2025-12-31',5,5,'Photography allowed','Weekday and weekend tours',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(7,'Yala Safari October Schedule',3,'2025-10-01','2025-10-31',2,2,'Morning and afternoon safaris','Advance booking recommended',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(8,'Yala Safari November Schedule',3,'2025-11-01','2025-11-30',2,2,'Peak wildlife sightings','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(9,'Yala Safari December Schedule',3,'2025-12-01','2025-12-31',2,2,'Bring binoculars','Morning safaris preferred',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(10,'Mirissa January Schedule',4,'2026-01-01','2026-01-31',2,3,'Best whale watching season','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(11,'Mirissa February Schedule',4,'2026-02-01','2026-02-28',2,3,'Water sports available','Morning and afternoon tours',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(12,'Mirissa March Schedule',4,'2026-03-01','2026-03-31',2,3,'Sunset cruise included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(13,'Ella October Schedule',5,'2025-10-01','2025-10-31',3,3,'Train journey included','Weekend batches available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(14,'Ella November Schedule',5,'2025-11-01','2025-11-30',3,3,'Tea plantation visit included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(15,'Ella December Schedule',5,'2025-12-01','2025-12-31',3,3,'Scenic viewpoints included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(16,'Bentota October Schedule',6,'2025-10-01','2025-10-31',3,3,'Spa treatments included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(17,'Bentota November Schedule',6,'2025-11-01','2025-11-30',3,3,'Yoga sessions included','Weekend batches',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(18,'Bentota December Schedule',6,'2025-12-01','2025-12-31',3,3,'Beach relaxation included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(19,'Kandy October Schedule',7,'2025-10-01','2025-10-31',2,2,'Temple visit and dance show','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(20,'Kandy November Schedule',7,'2025-11-01','2025-11-30',2,2,'Cultural guide included','Weekday tours',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(21,'Kandy December Schedule',7,'2025-12-01','2025-12-31',2,2,'Evening performance recommended','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(22,'Knuckles October Schedule',8,'2025-10-01','2025-10-31',2,2,'Moderate trek','Guided tours available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(23,'Knuckles November Schedule',8,'2025-11-01','2025-11-30',2,2,'Scenic viewpoints included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(24,'Knuckles December Schedule',8,'2025-12-01','2025-12-31',2,2,'Bring hiking shoes','Weekend batches',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(25,'Wilpattu October Schedule',9,'2025-10-01','2025-10-31',3,3,'Luxury camping included','Advance booking required',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(26,'Wilpattu November Schedule',9,'2025-11-01','2025-11-30',3,3,'Morning and evening safaris','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(27,'Wilpattu December Schedule',9,'2025-12-01','2025-12-31',3,3,'Guided wildlife tours','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(28,'Arugam Bay October Schedule',10,'2025-10-01','2025-10-31',4,4,'Surf lessons included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(29,'Arugam Bay November Schedule',10,'2025-11-01','2025-11-30',4,4,'Equipment rental included','Weekends preferred',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(30,'Arugam Bay December Schedule',10,'2025-12-01','2025-12-31',4,4,'Sunset surfing sessions','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(31,'Dambulla October Schedule',11,'2025-10-01','2025-10-31',3,3,'Ayurvedic treatments included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(32,'Dambulla November Schedule',11,'2025-11-01','2025-11-30',3,3,'Meditation sessions included','Weekends available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(33,'Dambulla December Schedule',11,'2025-12-01','2025-12-31',3,3,'Herbal meals provided','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(34,'Jaffna October Schedule',12,'2025-10-01','2025-10-31',4,4,'Temple and fort visits included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(35,'Jaffna November Schedule',12,'2025-11-01','2025-11-30',4,4,'Local cuisine tasting','Weekends preferred',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(36,'Jaffna December Schedule',12,'2025-12-01','2025-12-31',4,4,'Guided cultural tours','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(37,'Kitulgala October Schedule',13,'2025-10-01','2025-10-31',1,1,'Safety gear provided','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(38,'Kitulgala November Schedule',13,'2025-11-01','2025-11-30',1,1,'Guided rafting included','Weekdays and weekends',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(39,'Kitulgala December Schedule',13,'2025-12-01','2025-12-31',1,1,'Peak water flow season','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(40,'Trincomalee October Schedule',14,'2025-10-01','2025-10-31',3,3,'Snorkeling gear provided','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(41,'Trincomalee November Schedule',14,'2025-11-01','2025-11-30',3,3,'Family-friendly tours','Weekends available',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(42,'Trincomalee December Schedule',14,'2025-12-01','2025-12-31',3,3,'Coral reef visit included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(43,'Udawalawe October Schedule',15,'2025-10-01','2025-10-31',1,1,'Elephant sightings expected','Morning safaris',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(44,'Udawalawe November Schedule',15,'2025-11-01','2025-11-30',1,1,'Guided safari included','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL),(45,'Udawalawe December Schedule',15,'2025-12-01','2025-12-31',1,1,'Afternoon safari recommended','Daily departures',1,'2025-10-04 16:07:10',1,'2025-10-04 16:07:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_type`
--

DROP TABLE IF EXISTS `tour_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  CONSTRAINT `tour_type_ibfk_1` FOREIGN KEY (`status`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_type`
--

LOCK TABLES `tour_type` WRITE;
/*!40000 ALTER TABLE `tour_type` DISABLE KEYS */;
INSERT INTO `tour_type` VALUES (1,'Adventure','Thrilling outdoor activities and experiences',1,'2025-10-04 15:56:45',1,'2025-10-04 15:56:45',NULL,NULL,NULL),(2,'Cultural','Explore heritage sites and local traditions',1,'2025-10-04 15:56:45',1,'2025-10-04 15:56:45',NULL,NULL,NULL),(3,'Wildlife','Safari and nature exploration tours',1,'2025-10-04 15:56:45',1,'2025-10-04 15:56:45',NULL,NULL,NULL),(4,'Beach','Coastal and water-based activities',1,'2025-10-04 15:56:45',1,'2025-10-04 15:56:45',NULL,NULL,NULL),(5,'Wellness','Spa, yoga, and relaxation focused tours',1,'2025-10-04 15:56:45',1,'2025-10-04 15:56:45',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tour_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_wishlist`
--

DROP TABLE IF EXISTS `tour_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_wishlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `tour_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `tour_id` (`tour_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `tour_wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_wishlist_ibfk_2` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_wishlist_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_wishlist`
--

LOCK TABLES `tour_wishlist` WRITE;
/*!40000 ALTER TABLE `tour_wishlist` DISABLE KEYS */;
INSERT INTO `tour_wishlist` VALUES (1,1,1,1,'2025-11-29 05:01:57'),(2,1,2,1,'2025-11-29 05:01:57'),(3,2,3,2,'2025-11-29 05:01:57'),(4,2,4,1,'2025-11-29 05:01:57');
/*!40000 ALTER TABLE `tour_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_wishlist_history`
--

DROP TABLE IF EXISTS `tour_wishlist_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_wishlist_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `tour_id` int NOT NULL,
  `wishlist_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `tour_id` (`tour_id`),
  KEY `wishlist_id` (`wishlist_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `tour_wishlist_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_wishlist_history_ibfk_2` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_wishlist_history_ibfk_3` FOREIGN KEY (`wishlist_id`) REFERENCES `tour_wishlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tour_wishlist_history_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_wishlist_history`
--

LOCK TABLES `tour_wishlist_history` WRITE;
/*!40000 ALTER TABLE `tour_wishlist_history` DISABLE KEYS */;
INSERT INTO `tour_wishlist_history` VALUES (1,1,1,1,1,'2025-11-29 05:01:57'),(2,2,3,3,2,'2025-11-29 05:01:57');
/*!40000 ALTER TABLE `tour_wishlist_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transmission_types`
--

DROP TABLE IF EXISTS `transmission_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transmission_types` (
  `transmission_type_id` int NOT NULL AUTO_INCREMENT,
  `transmission_type_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`transmission_type_id`),
  UNIQUE KEY `transmission_type_name` (`transmission_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transmission_types`
--

LOCK TABLES `transmission_types` WRITE;
/*!40000 ALTER TABLE `transmission_types` DISABLE KEYS */;
INSERT INTO `transmission_types` VALUES (1,'Manual','Manual transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,'Automatic','Traditional automatic transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,'CVT','Continuously Variable Transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(4,'DCT','Dual-Clutch Transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(5,'AMT','Automated Manual Transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,'Semi-Automatic','Semi-automatic transmission',1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `transmission_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `nic` varchar(20) DEFAULT NULL,
  `gender_id` int DEFAULT NULL,
  `passport_number` varchar(50) DEFAULT NULL,
  `driving_license_number` varchar(50) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `mobile_number1` varchar(20) DEFAULT NULL,
  `mobile_number2` varchar(20) DEFAULT NULL,
  `region_id` int DEFAULT NULL,
  `religion_id` int DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_status_id` int DEFAULT NULL,
  `benefits_points_count` int DEFAULT '0',
  `wallet_id` int DEFAULT NULL,
  `user_type_id` int DEFAULT NULL,
  `email2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `nic` (`nic`),
  UNIQUE KEY `email` (`email`),
  KEY `address_id` (`address_id`),
  KEY `gender_id` (`gender_id`),
  KEY `region_id` (`region_id`),
  KEY `religion_id` (`religion_id`),
  KEY `user_status_id` (`user_status_id`),
  KEY `wallet_id` (`wallet_id`),
  KEY `user_type_id` (`user_type_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`region_id`) REFERENCES `country` (`country_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_4` FOREIGN KEY (`religion_id`) REFERENCES `religion` (`religion_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_5` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`user_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_6` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`wallet_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_ibfk_7` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'pasindu','$2a$12$A3m1vy9bjtvI5hRfw5hh7etBxN.BXouSfuUTveA9yxC01fEjUrFxa','Pasindu',NULL,'Dilshan',1,'199912345V',1,'N1234567','B9876543','pd.dimbulana@gmail.com','0712345678','0776543210',1,1,'1999-12-15','/images/users/user-1.jpg','2025-09-21 14:06:10','2025-12-01 03:46:44',1,2550,1,2,'pd.dimbulana@gmail.com'),(2,'adminuser','$2a$12$A3m1vy9bjtvI5hRfw5hh7etBxN.BXouSfuUTveA9yxC01fEjUrFxa','Admin',NULL,'User',2,'200045678V',2,'M7654321','D1239876','admin@example.com','0751234567','0769876543',2,2,'1985-05-20','/images/users/user-2.jpg','2025-09-21 14:06:10','2025-12-04 16:46:48',1,1200,2,1,NULL),(3,'adminuser2','$2a$12$A3m1vy9bjtvI5hRfw5hh7etBxN.BXouSfuUTveA9yxC01fEjUrFxa','Admin',NULL,'User',1,'102045678V',2,'M7654323','D1231876','ad2min@example.com','0751234532','0769876512',2,2,'1985-05-20','/images/users/user-3.jpg','2025-09-21 14:06:10','2025-12-04 16:46:48',1,500,3,1,NULL),(4,'user3','$2a$12$A3m1vy9bjtvI5hRfw5hh7etBxN.BXouSfuUTveA9yxC01fEjUrFxa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/images/users/user-4.jpg','2025-11-18 17:25:41','2025-12-04 16:46:48',NULL,0,NULL,NULL,NULL),(5,'pasindu123','$2a$12$XPp.p/YMp8k0dSlH8OajQe.HUguKUspKTyzkgK3ocNVZK6LrqYL5e','Pasindu','','Dilshan',NULL,NULL,NULL,NULL,NULL,'pasindu1@example.com','0771234567','0711234567',NULL,NULL,NULL,'/images/users/user-5.jpg','2025-11-23 07:06:05','2025-12-04 16:46:48',NULL,0,NULL,NULL,NULL),(6,'pasindudilshan','$2a$12$d4NZzcyAHXN8NpdGlZT0L.gQju42mgHPwfn1yc1eMe78AkNMa58uK','abc','ggi','def',NULL,NULL,NULL,NULL,NULL,'pas@gmail.com','0705752632',NULL,NULL,NULL,NULL,'/images/users/user-6.jpg','2025-11-23 07:22:36','2025-12-04 16:46:48',NULL,0,NULL,NULL,NULL),(8,'pas','$2a$12$euucHq3Ht./qJjfZDImNfOwiuUSsqgtKUhLYC8uZqkQPtFnmft/iy','aaa','ccc','bbb',NULL,NULL,NULL,NULL,NULL,'pass@gmail.com','0760654628',NULL,NULL,NULL,NULL,'/images/users/user-7.jpg','2025-11-23 07:30:00','2025-12-04 16:46:48',NULL,0,NULL,NULL,NULL),(9,'priyanka_g','TourGuide@2024','Priyanka',NULL,'Sharma',NULL,'199015500123',2,NULL,NULL,'priyanka.sharma@travelagency.com','+94771234567',NULL,NULL,NULL,'1990-05-15','/images/users/user-7.jpg','2025-12-06 15:33:15','2025-12-06 15:39:31',1,0,NULL,2,NULL),(10,'ravi_f','Guide2024@Ravi','Ravi',NULL,'Fernando',NULL,'198822800456',1,NULL,NULL,'ravi.fernando@travelagency.com','+94772345678',NULL,NULL,NULL,'1988-08-22','/images/users/user-5.jpg','2025-12-06 15:33:15','2025-12-06 15:39:31',1,0,NULL,2,NULL),(11,'anjali_p','PereraGuide@123','Anjali',NULL,'Perera',NULL,'199230700789',2,NULL,NULL,'anjali.perera@travelagency.com','+94773456789',NULL,NULL,NULL,'1992-11-30','/images/users/user-2.jpg','2025-12-06 15:33:15','2025-12-06 15:39:31',1,0,NULL,2,NULL),(12,'test','$2a$12$FOTxezpcZ20ZlfTp8wIjWuD9kX4q9ilgNy2I141DXHGGt0HKh8WP6','test','test','test',NULL,NULL,NULL,NULL,NULL,'test@example.com','0760767626',NULL,NULL,NULL,NULL,NULL,'2025-12-11 16:46:59','2025-12-11 16:46:59',NULL,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_level`
--

DROP TABLE IF EXISTS `user_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_level` (
  `user_level_id` int NOT NULL AUTO_INCREMENT,
  `level` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `points_needed` int DEFAULT NULL,
  PRIMARY KEY (`user_level_id`),
  KEY `fk_user_level_status` (`status_id`),
  CONSTRAINT `fk_user_level_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level`
--

LOCK TABLES `user_level` WRITE;
/*!40000 ALTER TABLE `user_level` DISABLE KEYS */;
INSERT INTO `user_level` VALUES (1,'Silver','Entry-level user with basic benefits',1,'2025-09-24 05:42:53',1,'2025-12-01 03:37:00',NULL,NULL,NULL,1000),(2,'Gold','Premium user with better benefits',1,'2025-09-24 05:42:53',1,'2025-12-01 03:37:00',NULL,NULL,NULL,2000),(3,'Platinum','Top-tier user with maximum benefits',1,'2025-09-24 05:42:53',1,'2025-12-01 03:37:00',NULL,NULL,NULL,5000);
/*!40000 ALTER TABLE `user_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_level_benefit`
--

DROP TABLE IF EXISTS `user_level_benefit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_level_benefit` (
  `benefit_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `benefit_value` decimal(10,2) DEFAULT NULL,
  `benefit_type_id` int NOT NULL,
  `valid_from` date DEFAULT NULL,
  `valid_to` date DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`benefit_id`),
  KEY `fk_user_level_benefit_type` (`benefit_type_id`),
  KEY `fk_user_level_benefit_status` (`status_id`),
  CONSTRAINT `fk_user_level_benefit_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_user_level_benefit_type` FOREIGN KEY (`benefit_type_id`) REFERENCES `benefit_type` (`benefit_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_benefit`
--

LOCK TABLES `user_level_benefit` WRITE;
/*!40000 ALTER TABLE `user_level_benefit` DISABLE KEYS */;
INSERT INTO `user_level_benefit` VALUES (1,'10% Discount on Tours','10% off on any tour booking',10.00,1,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL),(2,'5% Cashback','5% cashback on payments above $100',5.00,2,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL),(3,'Free Airport Pickup','Free airport pickup service',0.00,3,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL),(4,'15% Discount on Packages','15% discount for Gold users',15.00,1,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL),(5,'10% Cashback','10% cashback for Platinum users',10.00,2,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL),(6,'Free Hotel Upgrade','Complimentary room upgrade',0.00,3,'2025-01-01','2025-12-31',1,'2025-09-24 05:42:55',1,'2025-09-24 05:42:55',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_level_benefit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_level_benefit_mapping`
--

DROP TABLE IF EXISTS `user_level_benefit_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_level_benefit_mapping` (
  `user_level_id` int NOT NULL,
  `benefit_id` int NOT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`user_level_id`,`benefit_id`),
  KEY `fk_mapping_benefit` (`benefit_id`),
  KEY `fk_mapping_status` (`status_id`),
  CONSTRAINT `fk_mapping_benefit` FOREIGN KEY (`benefit_id`) REFERENCES `user_level_benefit` (`benefit_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mapping_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_mapping_user_level` FOREIGN KEY (`user_level_id`) REFERENCES `user_level` (`user_level_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_benefit_mapping`
--

LOCK TABLES `user_level_benefit_mapping` WRITE;
/*!40000 ALTER TABLE `user_level_benefit_mapping` DISABLE KEYS */;
INSERT INTO `user_level_benefit_mapping` VALUES (1,1,1,'2025-09-24 05:43:00',1,'2025-09-24 05:43:00',NULL,NULL,NULL),(1,2,1,'2025-09-24 05:43:00',1,'2025-09-24 05:43:00',NULL,NULL,NULL),(1,3,1,'2025-09-24 05:43:00',1,'2025-09-24 05:43:00',NULL,NULL,NULL),(2,2,1,'2025-09-24 05:43:02',1,'2025-09-24 05:43:02',NULL,NULL,NULL),(2,3,1,'2025-09-24 05:43:02',1,'2025-09-24 05:43:02',NULL,NULL,NULL),(2,4,1,'2025-09-24 05:43:02',1,'2025-09-24 05:43:02',NULL,NULL,NULL),(3,4,1,'2025-09-24 05:43:03',1,'2025-09-24 05:43:03',NULL,NULL,NULL),(3,5,1,'2025-09-24 05:43:03',1,'2025-09-24 05:43:03',NULL,NULL,NULL),(3,6,1,'2025-09-24 05:43:03',1,'2025-09-24 05:43:03',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_level_benefit_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification_permission`
--

DROP TABLE IF EXISTS `user_notification_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notification_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `new_tours` tinyint(1) DEFAULT '0',
  `new_tours_updated_at` datetime DEFAULT NULL,
  `new_packages` tinyint(1) DEFAULT '0',
  `new_packages_updated_at` datetime DEFAULT NULL,
  `new_destinations` tinyint(1) DEFAULT '0',
  `new_destinations_updated_at` datetime DEFAULT NULL,
  `new_activities` tinyint(1) DEFAULT '0',
  `new_activities_updated_at` datetime DEFAULT NULL,
  `discounts` tinyint(1) DEFAULT '0',
  `discounts_updated_at` datetime DEFAULT NULL,
  `free_coupons` tinyint(1) DEFAULT '0',
  `free_coupons_updated_at` datetime DEFAULT NULL,
  `your_tour_details` tinyint(1) DEFAULT '0',
  `your_tour_details_updated_at` datetime DEFAULT NULL,
  `tour_reminders` tinyint(1) DEFAULT '0',
  `tour_reminders_updated_at` datetime DEFAULT NULL,
  `tour_suggestions` tinyint(1) DEFAULT '0',
  `tour_suggestions_updated_at` datetime DEFAULT NULL,
  `special_notices` tinyint(1) DEFAULT '0',
  `special_notices_updated_at` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `user_notification_permission_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification_permission`
--

LOCK TABLES `user_notification_permission` WRITE;
/*!40000 ALTER TABLE `user_notification_permission` DISABLE KEYS */;
INSERT INTO `user_notification_permission` VALUES (1,1,1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36',1,'2025-12-02 18:13:36','2025-11-28 14:07:36','2025-12-02 12:43:36');
/*!40000 ALTER TABLE `user_notification_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile_sidebar`
--

DROP TABLE IF EXISTS `user_profile_sidebar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile_sidebar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `privilege_id` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `privilege_id` (`privilege_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `user_profile_sidebar_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `user_profile_sidebar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_profile_sidebar_ibfk_2` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_profile_sidebar_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile_sidebar`
--

LOCK TABLES `user_profile_sidebar` WRITE;
/*!40000 ALTER TABLE `user_profile_sidebar` DISABLE KEYS */;
INSERT INTO `user_profile_sidebar` VALUES (1,NULL,'Profile','View and edit your profile',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:30:45',NULL,NULL,NULL,'/user'),(2,NULL,'Reviews','Your reviews',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:30:45',NULL,NULL,NULL,'/reviews'),(3,NULL,'Coupons & Offers','Your available coupons',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:30:45',NULL,NULL,NULL,'/coupons'),(4,NULL,'Wallet','Your wallet details',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:30:45',NULL,NULL,NULL,'/wallet'),(5,NULL,'Browsing History','View browsing history',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:08:17',NULL,NULL,NULL,NULL),(6,NULL,'Account Security','Security settings',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 16:36:49',NULL,NULL,NULL,NULL),(7,NULL,'Notifications','Manage notifications',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:08:17',NULL,NULL,NULL,NULL),(8,NULL,'Wish List','Your saved items',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 12:08:17',NULL,NULL,NULL,NULL),(9,2,'Package Reviews','Your package reviews',1,1,'2025-11-24 12:08:22',NULL,'2025-11-24 12:08:22',NULL,NULL,NULL,NULL),(10,2,'Tour Reviews','Your tour reviews',1,1,'2025-11-24 12:08:22',NULL,'2025-11-24 12:08:22',NULL,NULL,NULL,NULL),(11,2,'Activity Reviews','Your activity reviews',1,1,'2025-11-24 12:08:22',NULL,'2025-11-24 12:08:22',NULL,NULL,NULL,NULL),(12,2,'Destination Reviews','Your destination reviews',1,1,'2025-11-24 12:08:22',NULL,'2025-11-24 12:08:22',NULL,NULL,NULL,NULL),(13,NULL,'Completed Tours','You complete these tours',1,1,'2025-11-24 12:08:17',NULL,'2025-11-24 16:36:49',NULL,NULL,NULL,NULL),(14,NULL,'Upcoming Tours','You books these tours',1,1,'2025-11-24 12:08:17',NULL,'2025-11-30 04:51:10',NULL,NULL,NULL,NULL),(15,NULL,'User benefits','Benifits you have',1,1,'2025-11-24 12:08:17',NULL,'2025-12-01 04:21:55',NULL,NULL,NULL,NULL),(16,NULL,'Requested Tours','Tours which you requsted',1,1,'2025-11-24 12:08:17',NULL,'2025-12-01 04:21:55',NULL,NULL,NULL,NULL),(17,NULL,'Cancelled Tours','Tours which you have cancelled',1,1,'2025-11-24 12:08:17',NULL,'2025-12-01 04:21:55',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_profile_sidebar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(5,1),(6,1),(8,1),(12,1),(2,2),(3,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_status` (
  `user_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`user_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `user_status_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` VALUES (1,'Active','User account active',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Suspended','User account suspended',2,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `user_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `projects_access` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`user_type_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `user_type_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Admin','System administrator','ALL',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Customer','Regular system user','BOOKING',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_assignments`
--

DROP TABLE IF EXISTS `vehicle_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_assignments` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `remarks` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `vehicle_assignments_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_assignments_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_assignments`
--

LOCK TABLES `vehicle_assignments` WRITE;
/*!40000 ALTER TABLE `vehicle_assignments` DISABLE KEYS */;
INSERT INTO `vehicle_assignments` VALUES (1,1,'2023-06-01','2023-06-10','Tour A','Routine','2025-11-02 06:23:27',1,'2025-11-02 06:23:27',NULL,NULL,NULL,NULL),(2,2,'2023-06-05','2023-06-15','Tour B','Routine','2025-11-02 06:23:27',1,'2025-11-02 06:23:27',NULL,NULL,NULL,NULL),(3,3,'2023-06-10','2023-06-20','Tour C','Routine','2025-11-02 06:23:27',1,'2025-11-02 06:23:27',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_details`
--

DROP TABLE IF EXISTS `vehicle_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_details` (
  `vehicle_details_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `chassis_number` varchar(50) DEFAULT NULL,
  `engine_number` varchar(50) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `insurance_company` varchar(100) DEFAULT NULL,
  `insurance_policy_number` varchar(50) DEFAULT NULL,
  `insurance_expiry_date` date DEFAULT NULL,
  `emission_test_number` varchar(50) DEFAULT NULL,
  `emission_expiry_date` date DEFAULT NULL,
  `permit_number` varchar(50) DEFAULT NULL,
  `permit_expiry_date` date DEFAULT NULL,
  `warranty_expiry_date` date DEFAULT NULL,
  `gps_tracking_id` varchar(100) DEFAULT NULL,
  `last_service_date` date DEFAULT NULL,
  `next_service_date` date DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `remarks` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`vehicle_details_id`),
  UNIQUE KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_details_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_details`
--

LOCK TABLES `vehicle_details` WRITE;
/*!40000 ALTER TABLE `vehicle_details` DISABLE KEYS */;
INSERT INTO `vehicle_details` VALUES (1,1,'CHS001','ENG001','2023-01-20','InsureCo','POL001','2024-01-20','EM001','2024-01-20','PER001','2024-01-20','2025-01-20','GPS001','2023-06-01','2023-12-01',12000,'No issues','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,2,'CHS002','ENG002','2023-03-25','InsureCo','POL002','2024-03-25','EM002','2024-03-25','PER002','2024-03-25','2025-03-25','GPS002','2023-06-05','2023-12-05',10000,'Minor scratch','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,3,'CHS003','ENG003','2023-05-12','InsureCo','POL003','2024-05-12','EM003','2024-05-12','PER003','2024-05-12','2025-05-12','GPS003','2023-06-10','2023-12-10',8000,'New car','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(4,4,'CHS004','ENG004','2021-07-20','InsureCo','POL004','2022-07-20','EM004','2022-07-20','PER004','2022-07-20','2023-07-20','GPS004','2023-01-10','2023-07-10',30000,'Needs inspection','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(5,5,'CHS005','ENG005','2022-08-30','InsureCo','POL005','2023-08-30','EM005','2023-08-30','PER005','2023-08-30','2024-08-30','GPS005','2023-05-15','2023-11-15',15000,'No issues','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,6,'CHS006','ENG006','2023-02-10','InsureCo','POL006','2024-02-10','EM006','2024-02-10','PER006','2024-02-10','2025-02-10','GPS006','2023-06-20','2023-12-20',5000,'Brand new','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(7,7,'CHS007','ENG007','2023-06-15','InsureCo','POL007','2024-06-15','EM007','2024-06-15','PER007','2024-06-15','2025-06-15','GPS007','2023-07-01','2024-01-01',2000,'New purchase','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_document_types`
--

DROP TABLE IF EXISTS `vehicle_document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_document_types` (
  `document_type_id` int NOT NULL AUTO_INCREMENT,
  `document_type_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_required` tinyint(1) DEFAULT '0',
  `validity_period_months` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`document_type_id`),
  UNIQUE KEY `document_type_name` (`document_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_document_types`
--

LOCK TABLES `vehicle_document_types` WRITE;
/*!40000 ALTER TABLE `vehicle_document_types` DISABLE KEYS */;
INSERT INTO `vehicle_document_types` VALUES (1,'Insurance','Vehicle insurance document',1,12,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,'Registration','Vehicle registration certificate',1,12,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,'Permit','Travel permit document',1,12,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(4,'Emission','Emission test certificate',1,6,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(5,'Service Report','Vehicle service report',0,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,'Purchase Invoice','Vehicle purchase invoice',0,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(7,'Road Tax','Road tax payment receipt',1,12,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(8,'Fitness Certificate','Vehicle fitness certificate',1,12,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_document_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_documents`
--

DROP TABLE IF EXISTS `vehicle_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_documents` (
  `document_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `document_type_id` int DEFAULT NULL,
  `document_name` varchar(100) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `document_type_id` (`document_type_id`),
  CONSTRAINT `vehicle_documents_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_documents_ibfk_2` FOREIGN KEY (`document_type_id`) REFERENCES `vehicle_document_types` (`document_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_documents`
--

LOCK TABLES `vehicle_documents` WRITE;
/*!40000 ALTER TABLE `vehicle_documents` DISABLE KEYS */;
INSERT INTO `vehicle_documents` VALUES (1,1,1,'Insurance 2023','https://example.com/doc1.pdf','2023-01-20','2024-01-20','2025-11-02 06:22:11',1,'2025-11-02 06:22:11',NULL,NULL,NULL),(2,2,2,'Registration 2023','https://example.com/doc2.pdf','2023-03-25','2024-03-25','2025-11-02 06:22:11',1,'2025-11-02 06:22:11',NULL,NULL,NULL),(3,3,3,'Permit 2023','https://example.com/doc3.pdf','2023-05-12','2024-05-12','2025-11-02 06:22:11',1,'2025-11-02 06:22:11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_fuel_records`
--

DROP TABLE IF EXISTS `vehicle_fuel_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_fuel_records` (
  `fuel_record_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `refuel_date` date NOT NULL,
  `fuel_type_id` int DEFAULT NULL,
  `quantity_liters` decimal(10,2) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `odometer_reading` int DEFAULT NULL,
  `refuel_station` varchar(150) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`fuel_record_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `fuel_type_id` (`fuel_type_id`),
  CONSTRAINT `vehicle_fuel_records_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_fuel_records_ibfk_2` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_types` (`fuel_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_fuel_records`
--

LOCK TABLES `vehicle_fuel_records` WRITE;
/*!40000 ALTER TABLE `vehicle_fuel_records` DISABLE KEYS */;
INSERT INTO `vehicle_fuel_records` VALUES (1,1,'2023-06-01',1,50.00,100.00,12000,'Station A','2025-11-02 06:14:36',1,'2025-11-02 06:14:36',NULL,NULL,NULL),(2,2,'2023-06-05',1,45.00,90.00,10000,'Station B','2025-11-02 06:14:36',1,'2025-11-02 06:14:36',NULL,NULL,NULL),(3,3,'2023-06-10',4,75.00,0.00,8000,'Station C','2025-11-02 06:14:36',1,'2025-11-02 06:14:36',NULL,NULL,NULL),(4,1,'2023-06-01',1,50.00,100.00,12000,'Station A','2025-11-02 06:22:18',1,'2025-11-02 06:22:18',NULL,NULL,NULL),(5,2,'2023-06-05',1,45.00,90.00,10000,'Station B','2025-11-02 06:22:18',1,'2025-11-02 06:22:18',NULL,NULL,NULL),(6,3,'2023-06-10',4,75.00,0.00,8000,'Station C','2025-11-02 06:22:18',1,'2025-11-02 06:22:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_fuel_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_images`
--

DROP TABLE IF EXISTS `vehicle_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_images_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_images`
--

LOCK TABLES `vehicle_images` WRITE;
/*!40000 ALTER TABLE `vehicle_images` DISABLE KEYS */;
INSERT INTO `vehicle_images` VALUES (1,1,'/images/vehicles/vehicle-images/vehicle-1-1.webp','Front view','front1','2025-11-02 06:13:48',NULL,'2025-11-02 09:25:37',NULL,NULL,NULL),(2,1,'/images/vehicles/vehicle-images/vehicle-1-2.jpg','Side view','side1','2025-11-02 06:13:48',NULL,'2025-11-02 09:25:37',NULL,NULL,NULL),(3,2,'/images/vehicles/vehicle-images/vehicle-2-1.webp','Front view','front2','2025-11-02 06:13:48',NULL,'2025-11-02 09:25:37',NULL,NULL,NULL),(4,3,'/images/vehicles/vehicle-images/vehicle-2-2.jpg','Front view','front3','2025-11-02 06:13:48',NULL,'2025-11-02 09:25:37',NULL,NULL,NULL),(5,4,'https://example.com/vehicle4_img1.jpg','Front view','front4','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(6,5,'https://example.com/vehicle5_img1.jpg','Front view','front5','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(7,6,'https://example.com/vehicle6_img1.jpg','Front view','front6','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(8,1,'/images/vehicles/vehicle-images/vehicle-1-3.jpeg','Side view','side1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(9,1,'/images/vehicles/vehicle-images/vehicle-1-4.jpg','Side view','side1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(10,2,'/images/vehicles/vehicle-images/vehicle-2-2.jpg','Front view','front3','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(11,2,'/images/vehicles/vehicle-images/vehicle-2-2.jpg','Front view','front3','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_inspections`
--

DROP TABLE IF EXISTS `vehicle_inspections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_inspections` (
  `inspection_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `inspection_type` enum('Emission','Safety','Annual','Other') DEFAULT NULL,
  `inspection_date` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `inspection_center` varchar(150) DEFAULT NULL,
  `result` enum('Pass','Fail','Pending') DEFAULT NULL,
  `remarks` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`inspection_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_inspections_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_inspections`
--

LOCK TABLES `vehicle_inspections` WRITE;
/*!40000 ALTER TABLE `vehicle_inspections` DISABLE KEYS */;
INSERT INTO `vehicle_inspections` VALUES (1,1,'Safety','2023-01-15','2024-01-15','Inspection Center A','Pass','Good condition','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,2,'Emission','2023-02-20','2024-02-20','Inspection Center B','Pass','Clear','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,3,'Annual','2023-03-10','2024-03-10','Inspection Center C','Pending','Waiting','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_inspections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_locations`
--

DROP TABLE IF EXISTS `vehicle_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `latitude` decimal(10,6) DEFAULT NULL,
  `longitude` decimal(10,6) DEFAULT NULL,
  `recorded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_locations_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_locations`
--

LOCK TABLES `vehicle_locations` WRITE;
/*!40000 ALTER TABLE `vehicle_locations` DISABLE KEYS */;
INSERT INTO `vehicle_locations` VALUES (1,1,6.927100,79.861200,'2023-06-01 02:30:00',1,'2025-11-02 06:28:56',NULL,NULL,NULL),(2,2,6.927500,79.862000,'2023-06-05 03:30:00',1,'2025-11-02 06:28:56',NULL,NULL,NULL),(3,3,6.928000,79.863000,'2023-06-10 01:30:00',1,'2025-11-02 06:28:56',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_maintenance_schedule`
--

DROP TABLE IF EXISTS `vehicle_maintenance_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_maintenance_schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `maintenance_type` varchar(100) DEFAULT NULL,
  `interval_km` int DEFAULT NULL,
  `interval_days` int DEFAULT NULL,
  `last_maintenance_date` date DEFAULT NULL,
  `next_due_date` date DEFAULT NULL,
  `remarks` text,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `terminated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_maintenance_schedule_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_maintenance_schedule`
--

LOCK TABLES `vehicle_maintenance_schedule` WRITE;
/*!40000 ALTER TABLE `vehicle_maintenance_schedule` DISABLE KEYS */;
INSERT INTO `vehicle_maintenance_schedule` VALUES (1,1,'Oil Change',5000,180,'2023-06-01','2023-12-01','Routine check',1,NULL,NULL,'2025-11-02 06:27:17','2025-11-02 06:27:17',NULL),(2,2,'Tire Replacement',10000,365,'2023-06-05','2024-06-05','Routine check',1,NULL,NULL,'2025-11-02 06:27:17','2025-11-02 06:27:17',NULL),(3,3,'Battery Check',15000,365,'2023-06-10','2024-06-10','Routine check',1,NULL,NULL,'2025-11-02 06:27:17','2025-11-02 06:27:17',NULL);
/*!40000 ALTER TABLE `vehicle_maintenance_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_ownership_history`
--

DROP TABLE IF EXISTS `vehicle_ownership_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_ownership_history` (
  `ownership_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `owner_id` int NOT NULL,
  `purchase_date` date DEFAULT NULL,
  `purchase_price` decimal(12,2) DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  `sale_price` decimal(12,2) DEFAULT NULL,
  `remarks` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`ownership_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `vehicle_ownership_history_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_ownership_history_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_ownership_history`
--

LOCK TABLES `vehicle_ownership_history` WRITE;
/*!40000 ALTER TABLE `vehicle_ownership_history` DISABLE KEYS */;
INSERT INTO `vehicle_ownership_history` VALUES (1,1,1,'2023-01-15',25000.00,NULL,NULL,'First owner','2025-11-02 06:17:47',1,'2025-11-02 06:17:47',NULL,NULL,NULL),(2,2,1,'2023-03-20',27000.00,NULL,NULL,'First owner','2025-11-02 06:17:47',1,'2025-11-02 06:17:47',NULL,NULL,NULL),(3,3,2,'2023-05-10',45000.00,NULL,NULL,'First owner','2025-11-02 06:17:47',1,'2025-11-02 06:17:47',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_ownership_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_service_history`
--

DROP TABLE IF EXISTS `vehicle_service_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_service_history` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `service_date` date NOT NULL,
  `service_center` varchar(150) DEFAULT NULL,
  `service_type` varchar(100) DEFAULT NULL,
  `odometer_reading` int DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `description` text,
  `next_service_due` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_service_history_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_service_history`
--

LOCK TABLES `vehicle_service_history` WRITE;
/*!40000 ALTER TABLE `vehicle_service_history` DISABLE KEYS */;
INSERT INTO `vehicle_service_history` VALUES (1,1,'2023-06-01','Service Center A','Oil Change',12000,100.00,'Changed oil','2023-12-01','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,2,'2023-06-05','Service Center B','Tire Replacement',10000,400.00,'Replaced tires','2023-12-05','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,3,'2023-06-10','Service Center C','Battery Check',8000,50.00,'Battery checked','2023-12-10','2025-11-02 06:13:48',1,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_service_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_service_images`
--

DROP TABLE IF EXISTS `vehicle_service_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_service_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `service_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `vehicle_service_images_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `vehicle_service_history` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_service_images`
--

LOCK TABLES `vehicle_service_images` WRITE;
/*!40000 ALTER TABLE `vehicle_service_images` DISABLE KEYS */;
INSERT INTO `vehicle_service_images` VALUES (1,1,'https://example.com/service1_img1.jpg','Oil changed','oil1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,2,'https://example.com/service2_img1.jpg','Tires replaced','tires1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,3,'https://example.com/service3_img1.jpg','Battery checked','battery1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_service_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_specification_images`
--

DROP TABLE IF EXISTS `vehicle_specification_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_specification_images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `specification_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `specification_id` (`specification_id`),
  CONSTRAINT `vehicle_specification_images_ibfk_1` FOREIGN KEY (`specification_id`) REFERENCES `vehicle_specifications` (`specification_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_specification_images`
--

LOCK TABLES `vehicle_specification_images` WRITE;
/*!40000 ALTER TABLE `vehicle_specification_images` DISABLE KEYS */;
INSERT INTO `vehicle_specification_images` VALUES (1,1,'https://example.com/spec1_img1.jpg','Corolla front','front_spec1','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(2,2,'https://example.com/spec2_img1.jpg','Civic front','front_spec2','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL),(3,3,'https://example.com/spec3_img1.jpg','Model 3 front','front_spec3','2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_specification_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_specifications`
--

DROP TABLE IF EXISTS `vehicle_specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_specifications` (
  `specification_id` int NOT NULL AUTO_INCREMENT,
  `make` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `year` int DEFAULT NULL,
  `generation` varchar(50) DEFAULT NULL,
  `body_type` varchar(50) DEFAULT NULL,
  `price` decimal(12,2) DEFAULT NULL,
  `engine_type` varchar(50) DEFAULT NULL,
  `engine_capacity` varchar(20) DEFAULT NULL,
  `horsepower_hp` decimal(8,2) DEFAULT NULL,
  `torque_nm` decimal(8,2) DEFAULT NULL,
  `transmission_type_id` int DEFAULT NULL,
  `fuel_type_id` int DEFAULT NULL,
  `electric_range_km` decimal(8,2) DEFAULT NULL,
  `drivetrain` varchar(50) DEFAULT NULL,
  `top_speed_kmh` decimal(10,2) DEFAULT NULL,
  `acceleration_0_100` decimal(5,2) DEFAULT NULL,
  `co2_emissions_g_km` decimal(8,2) DEFAULT NULL,
  `doors` int DEFAULT NULL,
  `seat_capacity` int DEFAULT NULL,
  `dimensions` varchar(100) DEFAULT NULL,
  `wheelbase_mm` decimal(8,2) DEFAULT NULL,
  `weight_kg` decimal(10,2) DEFAULT NULL,
  `wheel_size` varchar(20) DEFAULT NULL,
  `tire_type` varchar(50) DEFAULT NULL,
  `upholstery_type` varchar(100) DEFAULT NULL,
  `ac_type_id` int DEFAULT NULL,
  `sunroof_type` enum('None','Standard','Panoramic') DEFAULT NULL,
  `cruise_control_type` enum('None','Standard','Adaptive') DEFAULT NULL,
  `entertainment_features` text,
  `comfort_features` text,
  `ncap_safety_rating` int DEFAULT NULL,
  `airbags_count` int DEFAULT NULL,
  `parking_camera` enum('None','Rear','360-degree') DEFAULT NULL,
  `lane_departure_warning` tinyint(1) DEFAULT NULL,
  `safety_features` text,
  `fuel_tank_capacity_liters` decimal(10,2) DEFAULT NULL,
  `warranty_years` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `air_condition` tinyint DEFAULT '0',
  PRIMARY KEY (`specification_id`),
  KEY `transmission_type_id` (`transmission_type_id`),
  KEY `fuel_type_id` (`fuel_type_id`),
  KEY `ac_type_id` (`ac_type_id`),
  CONSTRAINT `vehicle_specifications_ibfk_1` FOREIGN KEY (`transmission_type_id`) REFERENCES `transmission_types` (`transmission_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `vehicle_specifications_ibfk_2` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_types` (`fuel_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `vehicle_specifications_ibfk_3` FOREIGN KEY (`ac_type_id`) REFERENCES `air_conditioning_types` (`ac_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_specifications`
--

LOCK TABLES `vehicle_specifications` WRITE;
/*!40000 ALTER TABLE `vehicle_specifications` DISABLE KEYS */;
INSERT INTO `vehicle_specifications` VALUES (1,'Toyota','Corolla',2022,'12th Gen','Sedan',25000.00,'Petrol','1.8L',140.00,175.00,2,1,NULL,'FWD',180.00,9.50,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'None','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(2,'Honda','Civic',2023,'11th Gen','Sedan',27000.00,'Petrol','2.0L',158.00,187.00,2,1,NULL,'FWD',200.00,8.20,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'None','Adaptive',NULL,NULL,NULL,NULL,NULL,NULL,NULL,47.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(3,'Tesla','Model 3',2023,'Standard','Sedan',45000.00,'Electric',NULL,283.00,450.00,3,4,500.00,'RWD',233.00,5.30,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'None','Adaptive',NULL,NULL,NULL,NULL,NULL,NULL,NULL,75.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(4,'Ford','Mustang',2021,'6th Gen','Coupe',55000.00,'Petrol','5.0L V8',450.00,556.00,2,1,NULL,'RWD',250.00,4.30,NULL,2,4,NULL,NULL,NULL,NULL,NULL,NULL,2,'None','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,61.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(5,'BMW','X5',2022,'G05','SUV',65000.00,'Diesel','3.0L',335.00,450.00,2,2,NULL,'AWD',245.00,5.30,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,3,'Panoramic','Adaptive',NULL,NULL,NULL,NULL,NULL,NULL,NULL,80.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(6,'Audi','A6',2023,'C8','Sedan',60000.00,'Hybrid','2.0L',335.00,400.00,2,3,50.00,'AWD',250.00,5.10,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,3,'Standard','Adaptive',NULL,NULL,NULL,NULL,NULL,NULL,NULL,55.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(7,'Mercedes','C-Class',2022,'W206','Sedan',58000.00,'Petrol','2.0L',255.00,370.00,2,1,NULL,'RWD',240.00,6.00,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,3,'Standard','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,60.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(8,'Hyundai','Tucson',2023,'NX4','SUV',32000.00,'Diesel','2.0L',185.00,400.00,2,2,NULL,'AWD',210.00,9.00,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'Standard','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,62.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(9,'Kia','Seltos',2023,'SP2','SUV',28000.00,'Petrol','1.6L',177.00,265.00,2,1,NULL,'AWD',195.00,9.20,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'Standard','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0),(10,'Volkswagen','Golf',2022,'Mk8','Hatchback',26000.00,'Petrol','1.4L',150.00,250.00,2,1,NULL,'FWD',210.00,8.50,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,2,'None','Standard',NULL,NULL,NULL,NULL,NULL,NULL,NULL,52.00,NULL,NULL,1,'2025-11-02 06:13:48',NULL,'2025-11-02 06:13:48',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `vehicle_specifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_type`
--

DROP TABLE IF EXISTS `vehicle_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_type` (
  `vehicle_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`vehicle_type_id`),
  KEY `fk_vehicle_type_status` (`status_id`),
  CONSTRAINT `fk_vehicle_type_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_type`
--

LOCK TABLES `vehicle_type` WRITE;
/*!40000 ALTER TABLE `vehicle_type` DISABLE KEYS */;
INSERT INTO `vehicle_type` VALUES (1,'Car','Standard passenger car',1,'2025-12-17 17:30:07',NULL,'2025-12-17 17:30:07',NULL,NULL,NULL),(2,'Van','Tourist van',1,'2025-12-17 17:30:07',NULL,'2025-12-17 17:30:07',NULL,NULL,NULL),(3,'Bus','Large tour bus',1,'2025-12-17 17:30:07',NULL,'2025-12-17 17:30:07',NULL,NULL,NULL),(4,'Jeep','Off-road vehicle',1,'2025-12-17 17:30:07',NULL,'2025-12-17 17:30:07',NULL,NULL,NULL);
/*!40000 ALTER TABLE `vehicle_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_usage_log`
--

DROP TABLE IF EXISTS `vehicle_usage_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_usage_log` (
  `usage_id` int NOT NULL AUTO_INCREMENT,
  `vehicle_id` int NOT NULL,
  `driver_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  `tour_id` int DEFAULT NULL,
  `start_datetime` datetime NOT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `start_odometer` int DEFAULT NULL,
  `end_odometer` int DEFAULT NULL,
  `route_description` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `fuel_used_liters` decimal(10,2) DEFAULT NULL,
  `remarks` text,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `terminated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`usage_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `vehicle_usage_log_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_usage_log`
--

LOCK TABLES `vehicle_usage_log` WRITE;
/*!40000 ALTER TABLE `vehicle_usage_log` DISABLE KEYS */;
INSERT INTO `vehicle_usage_log` VALUES (1,1,101,1,1,'2023-06-01 08:00:00','2023-06-01 12:00:00',12000,12100,'Route A','Tour A',10.00,'No remarks',1,NULL,NULL,'2025-11-02 06:26:33','2025-11-02 06:26:33',NULL),(2,2,102,1,1,'2023-06-05 09:00:00','2023-06-05 13:00:00',10000,10120,'Route B','Tour B',12.00,'No remarks',1,NULL,NULL,'2025-11-02 06:26:33','2025-11-02 06:26:33',NULL),(3,3,103,2,1,'2023-06-10 07:00:00','2023-06-10 11:00:00',8000,8100,'Route C','Tour C',15.00,'No remarks',1,NULL,NULL,'2025-11-02 06:26:33','2025-11-02 06:26:33',NULL);
/*!40000 ALTER TABLE `vehicle_usage_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `vehicle_id` int NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(20) NOT NULL,
  `vehicle_type_id` int NOT NULL,
  `specification_id` int DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `purchase_price` decimal(12,2) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  `status_id` int DEFAULT '1',
  `owner_id` int DEFAULT NULL,
  `assigned_driver_id` int DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `registration_number` (`registration_number`),
  KEY `specification_id` (`specification_id`),
  KEY `fk_vehicles_status` (`status_id`),
  KEY `fk_vehicles_owner` (`owner_id`),
  KEY `fk_vehicles_driver` (`assigned_driver_id`),
  KEY `fk_vehicles_vehicle_type` (`vehicle_type_id`),
  CONSTRAINT `fk_vehicles_driver` FOREIGN KEY (`assigned_driver_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_vehicles_owner` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_vehicles_status` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_vehicles_vehicle_type` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`vehicle_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`specification_id`) REFERENCES `vehicle_specifications` (`specification_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (1,'ABC-1234',1,1,'2023-01-15',25000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(2,'DEF-5678',1,2,'2023-03-20',27000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(3,'TES-0001',1,3,'2023-05-10',45000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(4,'FDM-2021',1,4,'2021-07-18',55000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(5,'BMW-X5001',1,5,'2022-08-25',65000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(6,'AUD-A6001',1,6,'2023-02-05',60000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL),(7,'HYU-TUC01',1,8,'2023-06-12',32000.00,'2025-11-02 06:13:48',NULL,'2025-12-17 17:29:31',NULL,NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verified_status`
--

DROP TABLE IF EXISTS `verified_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verified_status` (
  `verified_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`verified_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `verified_status_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verified_status`
--

LOCK TABLES `verified_status` WRITE;
/*!40000 ALTER TABLE `verified_status` DISABLE KEYS */;
INSERT INTO `verified_status` VALUES (1,'Pending','Verification pending',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Verified','Successfully verified',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(3,'Rejected','Verification failed',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(4,'UNVERIFIED','unverified',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `verified_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet` (
  `wallet_id` int NOT NULL AUTO_INCREMENT,
  `wallet_number` varchar(100) NOT NULL,
  `amount` decimal(15,2) DEFAULT '0.00',
  `wallet_status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`wallet_id`),
  UNIQUE KEY `wallet_number` (`wallet_number`),
  KEY `wallet_status_id` (`wallet_status_id`),
  CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`wallet_status_id`) REFERENCES `wallet_status` (`wallet_status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,'WALLET1001',5000.00,1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'WALLET1002',0.00,1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(3,'WALLET1003',0.00,1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet_status`
--

DROP TABLE IF EXISTS `wallet_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet_status` (
  `wallet_status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`wallet_status_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `wallet_status_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `common_status` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet_status`
--

LOCK TABLES `wallet_status` WRITE;
/*!40000 ALTER TABLE `wallet_status` DISABLE KEYS */;
INSERT INTO `wallet_status` VALUES (1,'Open','Wallet is active',1,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL),(2,'Closed','Wallet is closed',2,'2025-09-21 14:06:10',1,'2025-09-21 14:06:10',NULL,NULL,NULL);
/*!40000 ALTER TABLE `wallet_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `why_choose_us`
--

DROP TABLE IF EXISTS `why_choose_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `why_choose_us` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `sub_title` varchar(100) DEFAULT NULL,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `clicked_url` varchar(255) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `why_choose_us_status_id` int NOT NULL,
  `order` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `why_choose_us_status_id` (`why_choose_us_status_id`),
  CONSTRAINT `why_choose_us_ibfk_1` FOREIGN KEY (`why_choose_us_status_id`) REFERENCES `why_choose_us_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `why_choose_us`
--

LOCK TABLES `why_choose_us` WRITE;
/*!40000 ALTER TABLE `why_choose_us` DISABLE KEYS */;
INSERT INTO `why_choose_us` VALUES (1,'quality-service','Real Time Information','','Airline schedules, seat availability and confirmation in real time','/images/why-choose-us-images/real-time-information.jpg','/icons/quality-icon.png','/services','#0073D2',1,1,'2025-09-19 20:21:48',1,'2025-09-27 11:55:29',NULL,NULL,NULL),(2,'expert-team','Customer Service','','Highly efficient customer service team on call','/images/why-choose-us-images/customer-service.jpg','/icons/team-icon.png','/team','#16A34A',1,2,'2025-09-19 20:21:48',1,'2025-09-27 11:55:29',NULL,NULL,NULL),(3,'innovation','Total Management','','We offer a complete travel package to travelers and travel agents','/images/why-choose-us-images/total-management.jpg','/icons/innovation-icon.png','/innovation','#F59E0B',1,3,'2025-09-19 20:21:48',1,'2025-09-27 11:55:29',NULL,NULL,NULL),(4,'innovation','The Extra Mile','','Value added services and travel information','/images/why-choose-us-images/extra-mile.png','/icons/innovation-icon.png','/innovation','#A19E43',1,3,'2025-09-19 20:21:48',1,'2025-09-27 11:55:29',NULL,NULL,NULL);
/*!40000 ALTER TABLE `why_choose_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `why_choose_us_status`
--

DROP TABLE IF EXISTS `why_choose_us_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `why_choose_us_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `common_status_id` (`common_status_id`),
  CONSTRAINT `why_choose_us_status_ibfk_1` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `why_choose_us_status`
--

LOCK TABLES `why_choose_us_status` WRITE;
/*!40000 ALTER TABLE `why_choose_us_status` DISABLE KEYS */;
INSERT INTO `why_choose_us_status` VALUES (1,'VISIBLE','Visible in the frontend',1,'2025-09-19 20:21:48',1,'2025-09-19 20:21:48',NULL,NULL,NULL),(2,'HIDDEN','Hidden in the frontend',2,'2025-09-19 20:21:48',1,'2025-09-19 20:21:48',NULL,NULL,NULL);
/*!40000 ALTER TABLE `why_choose_us_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_flow`
--

DROP TABLE IF EXISTS `work_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_flow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text,
  `order_number` int DEFAULT NULL,
  `icon_url` varchar(500) DEFAULT NULL,
  `icon_color` varchar(50) DEFAULT NULL,
  `bg_color` varchar(50) DEFAULT NULL,
  `connect_text` varchar(255) DEFAULT NULL,
  `link_url` varchar(500) DEFAULT NULL,
  `work_flow_status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_work_flow_status` (`work_flow_status_id`),
  CONSTRAINT `fk_work_flow_status` FOREIGN KEY (`work_flow_status_id`) REFERENCES `work_flow_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_flow`
--

LOCK TABLES `work_flow` WRITE;
/*!40000 ALTER TABLE `work_flow` DISABLE KEYS */;
INSERT INTO `work_flow` VALUES (1,'Step 1: Sign Up','Register to get started with our services',1,'/icons/work-flow-icons/icon-1.png','#FFFFFF','#0073D2','go to choose plan','/signup',1,'2025-09-20 12:39:08',1,'2025-09-27 19:12:23',NULL,NULL,NULL),(2,'Step 2: Choose Plan','Select a plan that fits your needs',2,'/icons/work-flow-icons/icon-2.png','#FFFFFF','#16A34A','prceed with payment','/plans',1,'2025-09-20 12:39:08',1,'2025-09-27 19:12:23',NULL,NULL,NULL),(3,'Step 3: Payment','Securely pay for your selected plan',3,'/icons/work-flow-icons/icon-3.png','#FFFFFF','#F59E0B','last step','/payment',1,'2025-09-20 12:39:08',1,'2025-09-27 19:12:23',NULL,NULL,NULL),(4,'Step 4: Get Started','Access your dashboard and start using our services',4,'/icons/work-flow-icons/icon-4.png','#FFFFFF','#DC2626','Finish','/dashboard',1,'2025-09-20 12:39:08',1,'2025-09-20 12:39:08',NULL,NULL,NULL);
/*!40000 ALTER TABLE `work_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_flow_status`
--

DROP TABLE IF EXISTS `work_flow_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_flow_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `common_status_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  `terminated_at` timestamp NULL DEFAULT NULL,
  `terminated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_common_status_workflow` (`common_status_id`),
  CONSTRAINT `fk_common_status_workflow` FOREIGN KEY (`common_status_id`) REFERENCES `common_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_flow_status`
--

LOCK TABLES `work_flow_status` WRITE;
/*!40000 ALTER TABLE `work_flow_status` DISABLE KEYS */;
INSERT INTO `work_flow_status` VALUES (1,'ACTIVE','Work flow step is active',1,'2025-09-20 12:39:08',1,'2025-09-20 12:39:08',NULL,NULL,NULL),(2,'INACTIVE','Work flow step is not active',2,'2025-09-20 12:39:08',1,'2025-09-20 12:39:08',NULL,NULL,NULL),(3,'TERMINATED','Work flow step discontinued',3,'2025-09-20 12:39:08',1,'2025-09-20 12:39:08',NULL,NULL,NULL);
/*!40000 ALTER TABLE `work_flow_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-17 23:31:32
