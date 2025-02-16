CREATE TABLE files (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  original_name VARCHAR(255),
  saved_name VARCHAR(255),
  path VARCHAR(255),
  absolute_path VARCHAR(255),
  extension VARCHAR(10),
  description TEXT,
  folder_id BIGINT,
  url VARCHAR(255),
  type_file VARCHAR(150),
  created_by VARCHAR(100) COMMENT 'The username of the user',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  FOREIGN KEY (folder_id) REFERENCES folders(id) ON DELETE SET NULL
);
