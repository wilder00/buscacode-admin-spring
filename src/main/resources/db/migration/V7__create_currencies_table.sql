CREATE TABLE currencies (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  code VARCHAR(3) NOT NULL UNIQUE,
  name VARCHAR(100)
);

INSERT INTO currencies(id, code, name) VALUES(10,'USD', 'American Dollar');
INSERT INTO currencies(id, code, name) VALUES(20,'PEN', 'Peruvian Sol');
