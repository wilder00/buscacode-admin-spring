flyway_schema_historySELECT * FROM buscacode_admin.users;

SELECT * FROM buscacode_admin.galleries;

SELECT * FROM buscacode_admin.folders;

DROP database buscacode_admin;
CREATE DATABASE buscacode_currenciesadmin;

use buscacode_admin;

DESCRIBE buscacode_admin.folders;
DESCRIBE buscacode_admin.files;

ALTER TABLE buscacode_admin.files
ADD COLUMN saved_name VARCHAR(255)
AFTER absolute_path;

ALTER TABLE buscacode_admin.files
DROP COLUMN saved_name;
aa
flyway_schema_history;

SELECT *, BIN_TO_UUID(id) AS id_string FROM users;

SELECT * FROM accounts WHERE owner_id = UUID_TO_BIN("f3d43956-3d21-4987-8012-0654bcd8f4ff");

INSERT INTO accounts (id, name, state, owner_id) 
VALUES (UUID(), "General account", "ACTIVE", UUID_TO_BIN("d746e078-e80c-41ee-a3af-cd2aa5c55275"));

INSERT INTO accounts (id, name, state, owner_id, currency_id)
VALUES (UUID(), "General account", "ACTIVE", UUID_TO_BIN("68b9aa15-d768-4f06-aa4b-552c03efff58"), 20);

DESCRIBE accounts;

DESCRIBE transactions;

INSERT INTO transactions(id, NAME, TYPE, amount, account_id, created_by)
VALUES(UUID(), "SALARIO", "INFLOW", 4000, "c130fc70-df99-11ef-ba08-0242ac120002", "wilder00");

INSERT INTO transactions(id, NAME, TYPE, amount, account_id, created_by)
VALUES(UUID(), "ALQUILER", "OUTFLOW", 1000, "c130fc70-df99-11ef-ba08-0242ac120002", "wilder00");

INSERT INTO transactions(id, NAME, TYPE, amount, account_id, created_by)
VALUES(UUID(), "VENTA", "INFLOW", 101, "c130fc70-df99-11ef-ba08-0242ac120002", "wilder00");

DELETE FROM transactions WHERE id = "def67cc9-df99-11ef-ba08-0242ac120002";

SELECT * FROM transactions ORDER BY created_at desc;
SELECT * FROM accounts;
