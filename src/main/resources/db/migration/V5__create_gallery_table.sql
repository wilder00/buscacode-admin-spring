CREATE TABLE galleries (
  id CHAR(36) NOT NULL PRIMARY KEY,
  folder_root_id BIGINT NOT NULL,
  created_by VARCHAR(100)  COMMENT 'The username of the user',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  FOREIGN KEY (folder_root_id) REFERENCES folders(id) ON DELETE RESTRICT
);

