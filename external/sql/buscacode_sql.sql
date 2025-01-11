SELECT * FROM buscacode_admin.users;

SELECT * FROM buscacode_admin.galleries;

SELECT * FROM buscacode_admin.folders;

buscacode_adminusers

DESCRIBE buscacode_admin.folders;
DESCRIBE buscacode_admin.files;

ALTER TABLE buscacode_admin.files
ADD COLUMN saved_name VARCHAR(255)
AFTER absolute_path;

ALTER TABLE buscacode_admin.files
DROP COLUMN saved_name;
aa
