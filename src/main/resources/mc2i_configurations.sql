CREATE TABLE mc2i.configurations
(
    idConfiguration int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomConfiguration varchar(30) NOT NULL,
    mdpPattern varchar(100) DEFAULT '*{8,}' NOT NULL,
    nbeTentativeMax int(2) DEFAULT '0' NOT NULL,
    nbeMinutesEntreTentative int(3) DEFAULT '0' NOT NULL,
    blocageIsPossible tinyint(1) DEFAULT '0' NOT NULL,
    changePasswordAfterNTentative tinyint(1) DEFAULT '0' NOT NULL,
    changePasswordAfterForget tinyint(1) DEFAULT '0' NOT NULL,
    changePasswordPeriod int(11) DEFAULT '0'
);
INSERT INTO mc2i.configurations (nomConfiguration, mdpPattern, nbeTentativeMax, nbeMinutesEntreTentative, blocageIsPossible, changePasswordAfterNTentative, changePasswordAfterForget, changePasswordPeriod) VALUES ('default', '[a-z]{20,}', 3, 3, 0, 0, 1, 0);