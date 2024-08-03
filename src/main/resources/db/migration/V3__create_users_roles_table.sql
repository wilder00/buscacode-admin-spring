CREATE TABLE users_roles (
  role_id BIGINT NOT NULL,
  user_id BINARY(16) NOT NULL,
  PRIMARY KEY (role_id, user_id)
);
