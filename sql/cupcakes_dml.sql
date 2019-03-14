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

INSERT INTO `User`(`username`,`email`,`balance`, `role`) VALUES
('bittie_bertha', 'bertha@testmail.com', 305000.11, 'customer'),
('halltheprotocol', 'hall@testmail.com', 4000.00, 'customer'),
('barefooted_brandan', 'brandan@testmail.com', 50.5, 'customer'),
('admin', 'admin@testmail.com', NULL, 'admin');

INSERT INTO `ShoppingCart`(`cart_id`,`bottom_id`,`topping_id`,`quantity`) VALUES
(1, 1, 3, 2),
(1, 4, 6, 1),
(2, 3, 7, 3);

INSERT INTO `Invoice`(`user_id`,`cart_id`,`invoice_date`) VALUES
(1,2, '2019-02-22'),
(2,1, '2019-02-20');