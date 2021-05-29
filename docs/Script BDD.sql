-- Crea bdd
CREATE DATABASE  IF NOT EXISTS `grupo-21-bdd-oo2-2021`;
USE `grupo-21-bdd-oo2-2021`;

-- COMPILAR SPRING ANTES DE EJECUTAR LAS SIGUIENTES LINEAS

-- Crea roles

INSERT INTO user_role VALUES(NULL, b'1', NOW(), 'ROLE_ADMIN', NOW());
INSERT INTO user_role VALUES(NULL, b'1', NOW(), 'ROLE_AUDITOR', NOW());

-- Crea usuarios test

-- ADMIN, user: admin1 - pass: 123456
INSERT INTO `grupo-21-bdd-oo2-2021`.`persona` VALUES (NULL, 'Ventura', NOW(), 12345678, 'ventura666@gmail.com', 'Luis', 1, NOW());
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$nauXseps08y1qK9Z7EkOqODxaQLOW8rVQV/jXugNFh2YB0vweEigK', 'admin1', (SELECT p.id FROM persona p where p.apellido='Ventura'), (SELECT ur.id FROM user_role ur where ur.role='ROLE_ADMIN'));

-- AUDITOR, user: auditor1 - pass: 654321
INSERT INTO `grupo-21-bdd-oo2-2021`.`persona` VALUES (NULL, 'Tauro', NOW(), 2345689, 'marce123@hotmail.com', 'Marcela', 0, NOW());
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$5cSXuenrSRurzG3S2yZWWex6Cbe9YMUUlEIbwYePC9uEkMYqu5BWW', 'auditor1', (SELECT p.id FROM persona p where p.apellido='Tauro'), (SELECT ur.id FROM user_role ur where ur.role='ROLE_AUDITOR'));