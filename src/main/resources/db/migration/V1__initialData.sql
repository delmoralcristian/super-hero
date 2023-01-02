CREATE TABLE `superhero` (
  `id` long NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `attack` int(11) DEFAULT NULL,
  `defense` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `health` int(11) DEFAULT NULL,
  `death` boolean DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
);