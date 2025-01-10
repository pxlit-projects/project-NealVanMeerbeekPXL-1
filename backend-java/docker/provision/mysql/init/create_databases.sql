CREATE DATABASE IF NOT EXISTS `postservice_db`;
CREATE DATABASE IF NOT EXISTS `reviewservice_db`;
CREATE DATABASE IF NOT EXISTS `commentservice_db`;

CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'%';