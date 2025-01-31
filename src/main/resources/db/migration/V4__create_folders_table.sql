CREATE TABLE folders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  folder_father_id BIGINT,
  description TEXT,
  name VARCHAR(255),
  path_of_ids VARCHAR(255),
  created_by VARCHAR(100)  COMMENT 'The username of the user',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL
);

INSERT INTO folders(id, description, name, path_of_ids)
VALUES(1, 'GLOBAL ROOT FOLDER', 'ROOT', '/');
