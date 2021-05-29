-- SCRIPT BDD GRUPO 21 OO2 2021
-- INFO DE USUARIOS
-- ADMIN, user: admin1 - pass: 123456
-- AUDITOR, user: auditor1 - pass: 654321


-- CREA BDD
CREATE DATABASE  IF NOT EXISTS `grupo-21-bdd-oo2-2021`;
USE `grupo-21-bdd-oo2-2021`;


-- CREA TABLAS

CREATE TABLE `lugar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigopostal` varchar(255) NOT NULL,
  `lugar` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `createdat` datetime(6) NOT NULL,
  `documento` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tipodocumento` int NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rodado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime(6) NOT NULL,
  `dominio` varchar(255) NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  `vehiculo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `role` varchar(100) NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s21d8k5lywjjc7inw14brj6ro` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `activo` bit(1) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `username` varchar(45) NOT NULL,
  `id` int NOT NULL,
  `user_role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`),
  CONSTRAINT `FKh2wc2dtfdo8maylne7mgubowq` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKq9e1epiqip339syujm4brhw7u` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso` (
  `tipo` varchar(31) NOT NULL,
  `id_permiso` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `cant_dias` int DEFAULT NULL,
  `vacaciones` bit(1) DEFAULT NULL,
  `pedido_id` int NOT NULL,
  `rodado_id` int NOT NULL,
  PRIMARY KEY (`id_permiso`),
  KEY `FKft1wik02q1fqdvh64y3pa5kj9` (`pedido_id`),
  KEY `FK4myhtjulhfu3qp0gn2ft2oarn` (`rodado_id`),
  CONSTRAINT `FK4myhtjulhfu3qp0gn2ft2oarn` FOREIGN KEY (`rodado_id`) REFERENCES `rodado` (`id`),
  CONSTRAINT `FKft1wik02q1fqdvh64y3pa5kj9` FOREIGN KEY (`pedido_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rel_permiso_lugar` (
  `fk_permiso` int NOT NULL,
  `fk_lugar` int NOT NULL,
  PRIMARY KEY (`fk_permiso`,`fk_lugar`),
  KEY `FK9x1q43hvmdy9gt9ha2qcrnty1` (`fk_lugar`),
  CONSTRAINT `FK9x1q43hvmdy9gt9ha2qcrnty1` FOREIGN KEY (`fk_lugar`) REFERENCES `lugar` (`id`),
  CONSTRAINT `FKj5rs2w5c4pi1ur6x35hrai8re` FOREIGN KEY (`fk_permiso`) REFERENCES `permiso` (`id_permiso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- CREA ROLES

INSERT INTO user_role VALUES(NULL, b'1', NOW(), 'ROLE_ADMIN', NOW());
INSERT INTO user_role VALUES(NULL, b'1', NOW(), 'ROLE_AUDITOR', NOW());


-- CREA USUARIOS

-- ADMIN, user: admin1 - pass: 123456
INSERT INTO `grupo-21-bdd-oo2-2021`.`persona` VALUES (NULL, 'Ventura', NOW(), 12345678, 'ventura666@gmail.com', 'Luis', 1, NOW());
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$nauXseps08y1qK9Z7EkOqODxaQLOW8rVQV/jXugNFh2YB0vweEigK', 'admin1', (SELECT p.id FROM persona p where p.apellido='Ventura'), (SELECT ur.id FROM user_role ur where ur.role='ROLE_ADMIN'));
-- AUDITOR, user: auditor1 - pass: 654321
INSERT INTO `grupo-21-bdd-oo2-2021`.`persona` VALUES (NULL, 'Tauro', NOW(), 2345689, 'marce123@hotmail.com', 'Marcela', 0, NOW());
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$5cSXuenrSRurzG3S2yZWWex6Cbe9YMUUlEIbwYePC9uEkMYqu5BWW', 'auditor1', (SELECT p.id FROM persona p where p.apellido='Tauro'), (SELECT ur.id FROM user_role ur where ur.role='ROLE_AUDITOR'));


-- CREA LUGARES

INSERT INTO `lugar` VALUES (1, "1846", "ADROGUE");
INSERT INTO `lugar` VALUES (2, "1846", "ALMIRANTE BROWN");
INSERT INTO `lugar` VALUES (3, "1852", "BURZACO");
INSERT INTO `lugar` VALUES (4, "1849", "CLAYPOLE");
INSERT INTO `lugar` VALUES (5, "1856", "GLEW");
INSERT INTO `lugar` VALUES (6, "1846", "JOSE MARMOL");
INSERT INTO `lugar` VALUES (7, "1854", "LONGCHAMPS");
INSERT INTO `lugar` VALUES (8, "1852", "MINISTRO RIVADAVIA");
INSERT INTO `lugar` VALUES (9, "1847", "RAFAEL CALZADA");
INSERT INTO `lugar` VALUES (10, "1870", "AVELLANEDA");
INSERT INTO `lugar` VALUES (11, "1871", "DOCK SUD");
INSERT INTO `lugar` VALUES (12, "1872", "SARANDI");
INSERT INTO `lugar` VALUES (13, "1875", "WILDE");
INSERT INTO `lugar` VALUES (14, "1824", "LANUS");
INSERT INTO `lugar` VALUES (15, "1828", "BANFIELD");
INSERT INTO `lugar` VALUES (16, "1836", "LLAVALLOL");
INSERT INTO `lugar` VALUES (17, "1832", "LOMAS DE ZAMORA");
INSERT INTO `lugar` VALUES (18, "1834", "TEMPERLEY");
INSERT INTO `lugar` VALUES (19, "1834", "TURDERA");