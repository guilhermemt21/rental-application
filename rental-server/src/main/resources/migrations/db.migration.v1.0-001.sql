--liquibase formatted sql

--changeset guilhermemt21:1
create table property (
  id int(11) UNSIGNED not null primary key auto_increment,
    url_thumb varchar(500) null,
    `latitude` DECIMAL(11,7) NOT NULL,
    `longitude` DECIMAL(11,7) NOT NULL,
    `type` VARCHAR(500) NOT NULL,
    `address` VARCHAR(500) NOT NULL,
    `neighbourhood` VARCHAR(500) NOT NULL,
    `size` INT(11) NOT NULL,
    `bedrooms` INT(11) NOT NULL,
    `price` DECIMAL(11,2) NOT NULL
);
