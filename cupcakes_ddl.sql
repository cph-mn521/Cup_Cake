#drop schema cupcakes;
# CREATE SCHEMA `cupcakes` DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_danish_ci;
USE `cupcakes`;

DROP TABLE IF EXISTS `Bottom`;
DROP TABLE IF EXISTS `Toppings`;
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User`(
`user_id` INT NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(45) DEFAULT '1234',
`email` VARCHAR(45) NOT NULL,
`balance` DOUBLE(30,30),
PRIMARY KEY(`user_id`)
);

CREATE TABLE `Bottom`(
`bottom_id` INT AUTO_INCREMENT,
`type` VARCHAR(45) NOT NULL,
`price` FLOAT(10),
PRIMARY KEY(`bottom_id`)
);

CREATE TABLE `Toppings`(
`topping_id` INT AUTO_INCREMENT,
`type` VARCHAR(45),
`price` FLOAT(10),
PRIMARY KEY(`topping_id`)
);

