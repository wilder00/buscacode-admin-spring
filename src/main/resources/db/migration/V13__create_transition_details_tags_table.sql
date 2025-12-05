CREATE TABLE transition_details_tags (
  tag_id BIGINT NOT NULL,
  transaction_detail_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE,
  FOREIGN KEY (transaction_detail_id) REFERENCES transaction_details(id) ON DELETE CASCADE,
  PRIMARY KEY(tag_id, transaction_detail_id)
);
