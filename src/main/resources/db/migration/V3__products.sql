CREATE TABLE products (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   title VARCHAR(255),
   price DOUBLE PRECISION NOT NULL,
   unit VARCHAR(255),
   category_id BIGINT,
   warehouse_id BIGINT,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_WAREHOUSE FOREIGN KEY (warehouse_id) REFERENCES warehouse (id);

INSERT
INTO
  products
  (title, price, unit, category_id, warehouse_id)
VALUES
  ('Apple', 100, 'kg.', 1, 1);