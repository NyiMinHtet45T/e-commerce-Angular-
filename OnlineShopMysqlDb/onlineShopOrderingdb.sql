-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: online_shop_orderingdb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `house_no` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`),
  CONSTRAINT `FK6i66ijb8twgcqtetl8eeeed6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Yangon','093','NawNi',2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_item` int(11) NOT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,0,0,1),(2,4,5800,2);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `total_price` bigint(20) NOT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (12,1,2000,2,2),(13,2,2600,2,3),(14,1,1200,2,7);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Linen Shirt'),(2,'Oversized Shirt'),(3,'Striped Shirt'),(4,'Polo Shirt'),(5,'Casual Shirt'),(6,'Joggers '),(7,'Chinos '),(8,'Track Pants'),(9,'Jeans '),(10,'Cargo Pants');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_status` tinyint(4) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `tracking_id` binary(16) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (4,0,1,1500,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,1),(5,0,3,7800,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,5),(6,0,1,1300,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',2,3),(7,0,1,1300,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,6),(8,0,1,3000,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,15),(9,0,1,3500,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',3,13);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `total_item` int(11) NOT NULL,
  `total_price` bigint(20) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'2025-01-25',5,10600,1,2),(3,'2025-01-25',3,7800,1,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,_binary '','Lightweight and breathable for warm weather.','Geo Print Shirt',1500,1),(2,_binary '','Relaxed fit for a laid-back vibe.','BEAUTE-OUS',2000,2),(3,_binary '','Simple and classic with striped patterns.','Vintage Washed Style',1300,3),(4,_binary '','Simple and classic with striped patterns.',' Contrast Trim Tee',900,3),(5,_binary '','Comfortable and casual for relaxed settings.','Comfortable Breathable Loose',2600,6),(6,_binary '','Smart-casual trousers for any occasion.','Drawstring Waist Shorts',1300,7),(7,_binary '','Sporty trousers for active lifestyles','Blend Pull-On Pants',1200,8),(8,_binary '','Durable and versatile denim trousers.','Blue Straight Trousers',1600,9),(9,_binary '','Comfortable and stylish for everyday wear.','Shoulder Hooded Teddy Jacket',2300,5),(12,_binary '','Simple and classic with striped patterns.','Patched Detail Teddy Lined Jacket',4000,3),(13,_binary '','Versatile shirt with a collar and buttons.','Mint green shirt ',3500,4),(14,_binary '','Versatile shirt with a collar and buttons.','Trim Tank Top',3500,4),(15,_binary '','Sporty trousers for active lifestyles.','New Collection _ Bershka',3000,8),(16,_binary '','Practical trousers with multiple pockets.','Bershka baggy pantolon',3000,10),(17,_binary '','Smart-casual trousers for any occasion.','Latest Fashion Trends',2400,7),(18,_binary '','Practical trousers with multiple pockets.','baggy indigo ',2000,10);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_img`
--

DROP TABLE IF EXISTS `product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_img` (
  `product_id` bigint(20) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  KEY `FKbnvmhern60vx5hlvaa3ba9u8h` (`product_id`),
  CONSTRAINT `FKbnvmhern60vx5hlvaa3ba9u8h` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_img`
--

LOCK TABLES `product_img` WRITE;
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT INTO `product_img` VALUES (1,'≡¥Öê≡¥Öº_≡¥ÿ╛≡¥Öí≡¥Öñ≡¥Ö⌐≡¥Ö¥≡¥ÖÜ≡¥Ö¡ ≡ƒÿÄ.jpg'),(1,'Men Geo Print Shirt Without Tee.jpg'),(1,'43jJsMv_.jpg'),(1,'pBgL63Bd.jpg'),(2,'1LT-jSle.jpg'),(2,'BEAUTE-OUS Varied Color Stripe T-Shirt - Red _ S.jpg'),(2,'T-shirt.jpg'),(2,'vintage outfit png.jpg'),(3,'Custom Cotton Vintage Washed Style Black Color Short-sleeved T-shirts Men\'s T-shirt Unisex T-shirts Plus Size - Buy Custom Cotton Short-sleeved T-shirts,Vintage Washed Style Unisex T-shirts,Plus Size Short-sleeved T-shir.jpg'),(3,'lGX-ObT8.jpg'),(3,'Mystichot Maxim personnalis├⌐ ligne pour animaux de compagnie T-shirt brod├⌐ - apricot _ 3XL.jpg'),(3,'zmNGXMwE.jpg'),(4,'Men Patched Detail Contrast Trim Tee.jpg'),(4,'Men Letter Patched Detail Contrast Trim Tee Without Necklace.jpg'),(4,'Men Letter Graphic Contrast Trim Tee.jpg'),(4,'4UUV427T.jpg'),(5,'0FpkD9z-.jpg'),(5,'YIYFCMNC.jpg'),(5,'Elegant Cotton Linen Pants For Men Summer Loose Comfortable pants.jpg'),(5,'Casual Sold Linen Pants For Men Summer Comfortable Breathable Loose Bulky Thin Elastic Waist Linen Trousers Male - Green _ S.jpg'),(6,'Men Letter Patched Detail Drawstring Waist Shorts.jpg'),(6,'F29DhmJG.jpg'),(6,'oe3sRLTG.jpg'),(6,'Men Letter Patched Detail Drawstring Waist Shorts.jpg'),(7,'Trousers - COS GB.jpg'),(7,'YIYFCMNC.jpg'),(7,'Trousers - COS GB.jpg'),(7,'YIYFCMNC.jpg'),(8,'HOUZHOU Oversize Jeans Men Korean Casual Distressed Denim Pants Male Blue Straight Trousers Loose High Streetwear Vintage - black _ XL.jpg'),(8,'LEILA _ ECO RECYCLED BLUE USED - ECO RECYCLED BLUE USED _ 30 _ 27.jpg'),(8,'HOUZHOU Oversize Jeans Men Korean Casual Distressed Denim Pants Male Blue Straight Trousers Loose High Streetwear Vintage - black _ XL.jpg'),(8,'LEILA _ ECO RECYCLED BLUE USED - ECO RECYCLED BLUE USED _ 30 _ 27.jpg'),(9,'Manfinity Hypemode Men Buffalo Plaid Print Zip Up Drop Shoulder Hooded Teddy Jacket Without Top.jpg'),(9,'Men Letter Patched Button Front Jacket Without Sweatshirt.jpg'),(9,'Manfinity Hypemode Men Buffalo Plaid Print Zip Up Drop Shoulder Hooded Teddy Jacket Without Top.jpg'),(9,'Men Letter Patched Button Front Jacket Without Sweatshirt.jpg'),(12,'Guys Zip Pocket Patched Detail Teddy Lined Jacket.jpg'),(12,'Men Borg Collar Letter Patched Teddy Lined Corduroy Jacket.jpg'),(12,'Guys Zip Pocket Patched Detail Teddy Lined Jacket.jpg'),(12,'Men Borg Collar Letter Patched Teddy Lined Corduroy Jacket.jpg'),(13,'Shop Men\'s Sweaters_ Crew, V-Neck & Cardigan.jpg'),(13,'Mint green shirt for men.jpg'),(13,'Shop Men\'s Sweaters_ Crew, V-Neck & Cardigan.jpg'),(13,'Mint green shirt for men.jpg'),(14,'SHEIN Qutie Cable Knit Striped Trim Tank Top.jpg'),(14,'Men Striped Trim Cable Knit Sweater Vest.jpg'),(14,'SHEIN Qutie Cable Knit Striped Trim Tank Top.jpg'),(14,'Men Striped Trim Cable Knit Sweater Vest.jpg'),(15,'WomenΓÇÖs Pants _ New Collection _ Bershka.jpg'),(15,'Casual Winter Capsule Wardrobe 2023.jpg'),(15,'WomenΓÇÖs Pants _ New Collection _ Bershka.jpg'),(15,'Casual Winter Capsule Wardrobe 2023.jpg'),(16,'Bershka baggy pantolon.jpg'),(16,'dggy-xsv.jpg'),(16,'Bershka baggy pantolon.jpg'),(16,'dggy-xsv.jpg'),(17,'Fashion Chic Clothes Online, Discover The Latest Fashion Trends.jpg'),(17,'Men Letter Patched Detail Drawstring Waist Shorts.jpg'),(17,'Fashion Chic Clothes Online, Discover The Latest Fashion Trends.jpg'),(17,'Men Letter Patched Detail Drawstring Waist Shorts.jpg'),(18,'baggy indigo jeans_ with side pockets.jpg'),(18,'CUSTOM pants UPSYCLING.jpg'),(18,'baggy indigo jeans_ with side pockets.jpg'),(18,'CUSTOM pants UPSYCLING.jpg');
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  KEY `FK6cpw2nlklblpvc7hyt7ko6v3e` (`user_id`),
  CONSTRAINT `FK6cpw2nlklblpvc7hyt7ko6v3e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'That is Soo good bro!','2025-01-25 14:17:52.624720',4,1,2);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favourite_product`
--

DROP TABLE IF EXISTS `user_favourite_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_favourite_product` (
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKhqcivj3a1yff1heb681d3gh4e` (`product_id`),
  KEY `FKjcq8wqeqrmg7h51xelemfaper` (`user_id`),
  CONSTRAINT `FKhqcivj3a1yff1heb681d3gh4e` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKjcq8wqeqrmg7h51xelemfaper` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favourite_product`
--

LOCK TABLES `user_favourite_product` WRITE;
/*!40000 ALTER TABLE `user_favourite_product` DISABLE KEYS */;
INSERT INTO `user_favourite_product` VALUES (2,3),(2,7),(2,13);
/*!40000 ALTER TABLE `user_favourite_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_role` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','Admin','$2a$10$07cHAg2Oc/T.7GIlxQ4aw.UiuoEA5aqMIpF0NtaiQuJPQQJQ1LdM6','09322372',0),(2,'nyi@gmail.com','Nyi','$2a$10$GwleFXHvdCMHcF.nuO48a.TcDYIF/jr.6yCWEBWwYPKd.ryFmnjeK','092772877',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-29 20:39:40
