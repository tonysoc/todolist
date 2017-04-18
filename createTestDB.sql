DROP DATABASE IF EXISTS test;

CREATE DATABASE test DEFAULT CHARACTER SET 'utf8';

USE test;

CREATE TABLE `tasks` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TASK_DATA` varchar(255) NOT NULL,
  `DONE` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
);

DELIMITER $$
CREATE PROCEDURE insert_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;
  WHILE i < 28 DO
    INSERT INTO TASKS(TASK_DATA, DONE) VALUES (concat('Task', i), (i%3)&1);
    set i = i + 1;
  END WHILE;
END$$
DELIMITER ;
CALL insert_test_data();
DROP PROCEDURE insert_test_data;

