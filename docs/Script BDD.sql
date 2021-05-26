-- Crea bdd
CREATE DATABASE  IF NOT EXISTS `grupo-21-bdd-oo2-2021`;
USE `grupo-21-bdd-oo2-2021`;

-- COMPILAR SPRING ANTES DE EJECUTAR LAS SIGUIENTES LINEAS

-- Crea roles

INSERT INTO user_role VALUES(NULL, NOW(), 'ROLE_ADMIN', NOW());
INSERT INTO user_role VALUES(NULL, NOW(), 'ROLE_AUDITOR', NOW());

-- Crea usuarios test

-- ADMIN, user: admin1 - pass: 123456
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (NULL, b'1', 'Ventura', NOW(), 12345678, 'ventura666@gmail.com', 'Luis', '$2a$10$nauXseps08y1qK9Z7EkOqODxaQLOW8rVQV/jXugNFh2YB0vweEigK', 1, NOW(), 'admin1', (SELECT ur.id FROM user_role ur where ur.role='ROLE_ADMIN'));

-- AUDITOR, user: auditor1 - pass: 654321
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (NULL, b'1', 'Tauro', NOW(), 23456789, 'marce123@hotmail.com', 'Marcela', '$2a$10$5cSXuenrSRurzG3S2yZWWex6Cbe9YMUUlEIbwYePC9uEkMYqu5BWW', 2, NOW(), 'auditor1', (SELECT ur.id FROM user_role ur where ur.role='ROLE_AUDITOR'));