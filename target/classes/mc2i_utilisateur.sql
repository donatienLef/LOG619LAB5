CREATE TABLE mc2i.utilisateur
(
    id_utilisateur int(100) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nom varchar(30) NOT NULL,
    email varchar(50) NOT NULL,
    poste varchar(50) NOT NULL,
    motdepasse varchar(256) NOT NULL,
    blocked tinyint(1) DEFAULT '0' NOT NULL,
    oldpass varchar(256),
    passChange date,
    mustChange tinyint(1) DEFAULT '0'
);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('test', 'test@test.com', 'Administrateur', '$2a$10$EqpO1XL19A0RzJS486j3NeEsL2NfcLpenZlkKhWkwJc3NQ2YG3EZq', 1, null, null, 1);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('ca1', 'ca@gmail.com', 'Client d''affaire', '$2a$10$7zDxKzgK816U7WfkcLxwAuEyG6Y4qbxMRC5yvlwNmkDu3K5Cnki86', 0, null, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('cr1', 'cr@gmail.com', 'Client', '$2a$10$/IH01R3AZrVKrDxs7W8Ou.jDACFDOcHAQRCkO4.MN898QdF4pN6TW', 0, null, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('bonjour', 'bonjour@bonjour.com', 'Client affaire', '$2a$10$Mz7/WK.D.zD8YrMI9mA71ujYf8xB28iKmYOqJnV/6MoxgX6IJH.p2', 0, null, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('test', 'test2@gmail.com', 'client', '$2a$10$PCPLeKC2UYB1l5S02/WmN.E7PgVNTmDVsjeJu3aMAH.cJe4sgnh8u', 0, null, null, 0);
INSERT INTO mc2i.utilisateur (nom, email, poste, motdepasse, blocked, oldpass, passChange, mustChange) VALUES ('bravo', 'test@test.fr', 'Client rÃ©sidentiel', '$2a$10$JKKhJBcSYYXN.iuavJzcsuaYnxfCwN.2UPOgbdWojoWfuhSYvWJVS', 0, null, null, 0);