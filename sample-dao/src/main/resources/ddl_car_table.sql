DROP TABLE IF EXISTS car;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_name` varchar(45) NOT NULL,
  `engine_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);