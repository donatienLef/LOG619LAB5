CREATE TABLE mc2i.logs
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account varchar(256) NOT NULL,
    date datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
    message varchar(256)
);
CREATE UNIQUE INDEX logs_id_uindex ON mc2i.logs (id);
CREATE INDEX logs_utilisateur_id_utilisateur_fk ON mc2i.logs (account);
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:07:38', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:07:38', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:09:38', 'Mot de passe modifié : false');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:09:46', 'Mot de passe modifié : false');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:09:55', 'Mot de passe modifié : true');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:08', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:08', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:20', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:20', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:29', 'Tentative de connection #2');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:29', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:36', 'Tentative de connection #3');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:10:36', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:11:16', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:11:16', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:11:31', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:11:31', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.Com', '2017-12-06 11:13:37', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.Com', '2017-12-06 11:13:37', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:46', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:46', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:54', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:54', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:59', 'Tentative de connection #2');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:14:59', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:15:07', 'Tentative de connection #3');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:15:07', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:16:06', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:16:06', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:16:20', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('test@test.com', '2017-12-06 11:16:20', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:15', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:15', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:22', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:22', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:26', 'Tentative de connection #2');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:26', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:30', 'Tentative de connection #3');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:17:30', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:19:00', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur1@test.com', '2017-12-06 11:19:00', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur2@test.com', '2017-12-06 11:19:15', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur2@test.com', '2017-12-06 11:19:15', 'Connection NOK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur2@test.com', '2017-12-06 11:19:20', 'Tentative de connection #1');
INSERT INTO mc2i.logs (account, date, message) VALUES ('utilisateur2@test.com', '2017-12-06 11:19:20', 'Connection OK');
INSERT INTO mc2i.logs (account, date, message) VALUES ('demo@demo.com', '2017-12-06 11:19:47', 'Tentative de connection #0');
INSERT INTO mc2i.logs (account, date, message) VALUES ('demo@demo.com', '2017-12-06 11:19:47', 'Connection OK');