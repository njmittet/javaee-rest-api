CREATE DATABASE javaee;
CREATE USER 'javaee'@'%'
  IDENTIFIED BY 'javaee';
GRANT ALL PRIVILEGES ON javaee.* TO 'javaee'@'%';
FLUSH PRIVILEGES;

USE javaee;

CREATE TABLE person (
  id        INT          NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(100) NOT NULL,
  lastname  VARCHAR(40)  NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO person (firstname, lastname) VALUES ('Isaac', 'Neewton');
INSERT INTO person(firstname, lastname) VALUES ('Marie', 'Curie');
INSERT INTO person(firstname, lastname) VALUES ('Charles', 'Darwin');
INSERT INTO person (firstname, lastname) VALUES ('Stephen', 'Hawking');
INSERT INTO person (firstname, lastname) VALUES ('Albert', 'Einstein');

COMMIT;