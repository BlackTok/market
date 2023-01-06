CREATE TABLE customer_roles (
  role_id BIGINT NOT NULL,
   customer_id BIGINT NOT NULL
);

ALTER TABLE customer_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE customer_roles ADD CONSTRAINT fk_userol_on_customer FOREIGN KEY (customer_id) REFERENCES customers (id);

INSERT
INTO
  customer_roles
  (role_id, customer_id)
VALUES
  (1, 1), (2, 2);