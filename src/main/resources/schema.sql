DROP TABLE prices IF EXISTS;

CREATE TABLE prices (
  id INT PRIMARY KEY,
  brand_id INT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_list BIGINT NOT NULL,
  product_id VARCHAR(50) NOT NULL,
  priority INT NOT NULL,
  price NUMERIC(20,2),
  curr VARCHAR(3) NOT NULL
);
