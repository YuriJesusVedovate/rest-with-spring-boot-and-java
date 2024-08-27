CREATE DATABASE IF NOT EXISTS `rest_with_spring_boot`;
USE `rest_with_spring_boot`;

CREATE TABLE IF NOT EXISTS `person`
(
    `key`         bigint       NOT NULL AUTO_INCREMENT,
    `first_name` varchar(80)  NOT NULL,
    `last_name`  varchar(80)  NOT NULL,
    `address`    varchar(100) NOT NULL,
    `gender`     varchar(6)   NOT NULL,
    PRIMARY KEY (`key`)
);