CREATE TABLE customer (
      id SERIAL PRIMARY KEY,
      email VARCHAR(255) NOT NULL,
      pwd VARCHAR(255) NOT NULL,
      role VARCHAR(50) NOT NULL
);

INSERT INTO customer (email, pwd, role)
VALUES ('ramon@email.com', '54321', 'admin');
