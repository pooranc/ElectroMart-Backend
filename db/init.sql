CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE PRECISION NOT NULL,
    stock INT NOT NULL
);

INSERT INTO product (name, description, price, stock, category) VALUES
('Laptop', 'powerfull', 1755.15, 12, "Laptop"),
('Keyboard', 'mechanic keyboard', 299.99, 30, "Small Component"),
('Mouse', 'bluetooth mouse', 149.95, 25, "Small Component");
