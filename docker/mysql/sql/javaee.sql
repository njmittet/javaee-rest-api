CREATE DATABASE javaee;
CREATE USER 'javaee'@'%'
  IDENTIFIED BY 'javaee';
GRANT ALL PRIVILEGES ON javaee.* TO 'javaee'@'%';
FLUSH PRIVILEGES;

USE javaee;

CREATE TABLE identity (
  id        INT          NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(100) NOT NULL,
  lastname  VARCHAR(40)  NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO identity (firstname, lastname) VALUES ('Isaac', 'Neewton');
INSERT INTO identity (firstname, lastname) VALUES ('Marie', 'Curie');
INSERT INTO identity (firstname, lastname) VALUES ('Charles', 'Darwin');
INSERT INTO identity (firstname, lastname) VALUES ('Stephen', 'Hawking');
INSERT INTO identity (firstname, lastname) VALUES ('Albert', 'Einstein');

COMMIT;