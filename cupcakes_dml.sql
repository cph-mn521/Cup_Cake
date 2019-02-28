USE `cupcakes`;

INSERT INTO `Bottom`(`type`,`price`) VALUES('Chocolate',5);
INSERT INTO `Bottom`(`type`,`price`) VALUES('Vanilla',5);
INSERT INTO `Bottom`(`type`,`price`) VALUES('Nutmeg',5);
INSERT INTO `Bottom`(`type`,`price`) VALUES('Pistacio',6);
INSERT INTO `Bottom`(`type`,`price`) VALUES('Almond',7);

INSERT INTO `Toppings`(`type`,`price`) VALUES('Chocolate',5);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Blueberry',5);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Raspberry',5);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Crispy',6);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Strawberry',6);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Rum/Raisin',7);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Orange',8);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Lemon',8);
INSERT INTO `Toppings`(`type`,`price`) VALUES('Blue Cheese',9);

INSERT INTO `User`(`username`,`email`,`balance`) VALUES
('bittie_bertha', 'bertha@testmail.com', 305000.11),
('halltheprotocol', 'hall@testmail.com', 4000.00),
('barefooted_brandan', 'brandan@testmail.com', 50.5);

INSERT INTO `Invoice`(`user_id`,`bottom_id`,`topping_id`, `invoice_date`) VALUES
(1,4,1, '2019-02-22'),
(2,3,6, '2019-02-20');