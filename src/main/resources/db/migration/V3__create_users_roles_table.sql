CREATE TABLE users_roles (
  role_id BIGINT NOT NULL,
  user_id BINARY(16) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
  PRIMARY KEY (role_id, user_id)
);
