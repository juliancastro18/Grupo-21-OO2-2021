-- Script para insertar user y userrole hasta que no estén implementados los ABM

-- Crea roles
INSERT INTO user_role VALUES(0, NOW(), 'ROLE_ADMIN', NOW());
INSERT INTO user_role VALUES(1, NOW(), 'ROLE_AUDITOR', NOW());

-- Crea usuarios test
-- pass: 1234 y 5678 respectivamente
-- ADMIN
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (0, b'1', 'Fort', NOW(), 12345678, 'riki.fort@gmail.com', 'Ricardo', '$2a$10$lpu.VBsTYilBE5MRrRvEeucb98kecV0IsHSNp6Sg95dbZO3DhckzK', 1, NOW(), 'admin', (SELECT ur.id FROM user_role ur where ur.role='ROLE_ADMIN'));
-- AUDITOR
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (1, b'1', 'Tauro', NOW(), 23456789, 'marce@hotmail.com', 'Marcela', '$2a$10$3/r3AS0X/JakJp3jBnbZXe.mCLz7Q05seYgvR348sauOToiFrGIWG', 2, NOW(), 'marce123', (SELECT ur.id FROM user_role ur where ur.role='ROLE_AUDITOR'));