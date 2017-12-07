CREATE TABLE mc2i.utilisateur
(
    id_utilisateur int(100) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nom varchar(30) NOT NULL,
    email varchar(50) NOT NULL,
    poste varchar(50) NOT NULL,
    motdepasse varchar(256) NOT NULL,
    blocked tinyint(1) DEFAULT '0' NOT NULL,
    passChange date,
    mustChange tinyint(1) DEFAULT '0'
);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('test', 'test@test.com', 'Administrateur', '$2a$10$o3Z62sEx3vjRyknoFDXMMu3sztfUaTMhRyQxTYfkvSRmkheiRdNay', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('ca1', 'ca@gmail.com', 'Client affaire', '$2a$10$7zDxKzgK816U7WfkcLxwAuEyG6Y4qbxMRC5yvlwNmkDu3K5Cnki86', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('cr1', 'cr@gmail.com', 'Client residentiel', '$2a$10$/IH01R3AZrVKrDxs7W8Ou.jDACFDOcHAQRCkO4.MN898QdF4pN6TW', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('bonjour', 'bonjour@bonjour.com', 'Client affaire', '$2a$10$Mz7/WK.D.zD8YrMI9mA71ujYf8xB28iKmYOqJnV/6MoxgX6IJH.p2', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('test', 'test2@gmail.com', 'Client residentiel', '$2a$10$PCPLeKC2UYB1l5S02/WmN.E7PgVNTmDVsjeJu3aMAH.cJe4sgnh8u', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('bravo', 'test@test.fr', 'Client residentiel', '$2a$10$JKKhJBcSYYXN.iuavJzcsuaYnxfCwN.2UPOgbdWojoWfuhSYvWJVS', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('test', 'utilisateur1@test.com', 'Administrateur residentiel', '$2a$10$sZ.nxIL8Z4yzAr/zE2Z8IuB/z3l/e1cDsI8eihUYtoFef1zthNwSe', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('test2', 'utilisateur2@test.com', 'Administrateur affaire', '$2a$10$zoLJFn7LJUw4SEM5o2my/ua7z6oJ6mAPqeXDwNVv7otVyHc5BtHpa', 0, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, passChange, mustChange) VALUES ('demo', 'demo@demo.com', 'Client residentiel', '$2a$10$RuDP1ZKJdjxe.y/w6lYGfuo4TQAgPrstmChQJBfweRrpGB9GbyY9K', 0, null, 0);