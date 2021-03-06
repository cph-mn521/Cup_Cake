#drop schema cupcakes;
# CREATE SCHEMA `cupcakes` DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_danish_ci;
USE `cupcakes`;

DROP TABLE IF EXISTS `Invoice`;
DROP TABLE IF EXISTS `ShoppingCart`;
DROP TABLE IF EXISTS `Bottom`;
DROP TABLE IF EXISTS `Toppings`;
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User`(
`user_id` INT NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(45) NOT NULL UNIQUE,
`password` VARCHAR(45) DEFAULT '1234',
`email` VARCHAR(45) NOT NULL,
`balance` DOUBLE(30,2) DEFAULT 0.0,
`role` ENUM('customer','admin') NOT NULL DEFAULT 'customer',
PRIMARY KEY(`user_id`)
);

#CREATE INDEX `username` ON `User`(`username`);

CREATE TABLE `Bottom`(
`bottom_id` INT AUTO_INCREMENT,
`type` VARCHAR(45) UNIQUE NOT NULL,
`price` FLOAT(10) UNSIGNED,
PRIMARY KEY(`bottom_id`)
);

CREATE TABLE `Toppings`(
`topping_id` INT AUTO_INCREMENT,
`type` VARCHAR(45) UNIQUE NOT NULL,
`price` FLOAT(10) UNSIGNED,
PRIMARY KEY(`topping_id`)
);

CREATE TABLE `ShoppingCart`(
`cart_id` INT NOT NULL,
`bottom_id` INT NOT NULL,
`topping_id` INT NOT NULL,
`quantity` INT UNSIGNED DEFAULT 1,
PRIMARY KEY(`cart_id`, `bottom_id`, `topping_id`),

	CONSTRAINT `Bottom_fk`
    FOREIGN KEY (`bottom_id`)
    REFERENCES `Bottom` (`bottom_id`),
    
    CONSTRAINT `Toppings_fk`
    FOREIGN KEY (`topping_id`)
    REFERENCES `Toppings` (`topping_id`)
);
    
CREATE TABLE `Invoice`(
`invoice_id` INT AUTO_INCREMENT,
`user_id` INT DEFAULT NULL,
`cart_id` INT DEFAULT NULL,
`invoice_date` DATE DEFAULT NULL,
PRIMARY KEY(`invoice_id`),
    
	CONSTRAINT `User_fk` 
	FOREIGN KEY (`user_id`)
	REFERENCES `User` (`user_id`),
    
    CONSTRAINT `ShoppingCart_fk` 
	FOREIGN KEY (`cart_id`)
	REFERENCES `ShoppingCart` (`cart_id`)
);


CREATE INDEX `invoice_id` ON `Invoice`(`invoice_id`);
