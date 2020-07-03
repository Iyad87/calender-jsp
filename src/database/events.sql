DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime DEFAULT NULL,
  `public` int(1) NOT NULL,
  `type` varchar(20) COLLATE utf8_bin NOT NULL,
  `remark` text COLLATE utf8_bin NOT NULL,
  `option` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (49,'Mechanica Beurs','2020-07-20 08:00:00','2020-07-24 17:00:00',0,'event','',0);
INSERT INTO `events` VALUES (50,'Tandarts','2020-07-16 10:00:00','2020-07-16 11:30:00',0,'private','',0);
INSERT INTO `events` VALUES (51,'Ronald Goedemondt','2020-07-11 19:00:00','2020-07-11 22:00:00',0,'show','',0);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;
