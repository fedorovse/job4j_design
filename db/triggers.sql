CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	producer VARCHAR(50),
	count INT DEFAULT 0,
	price INTEGER
);

CREATE OR REPLACE FUNCTION tax()
RETURNS TRIGGER AS
$$
	BEGIN
		UPDATE products
		SET price = price + price * 0.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER trigger_tax AFTER INSERT ON products
FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

INSERT INTO products(name, producer, count, price)
VALUES
	('A', 'A1', 5, 100),
	('B', 'B1', 7, 200),
	('C', 'C1',3 , 360);

ALTER TABLE products DISABLE TRIGGER trigger_tax;

CREATE OR REPLACE FUNCTION tax_before()
RETURNS TRIGGER AS
$$
	BEGIN
		NEW.price = NEW.price + NEW.price * 0.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER before_trigger_tax
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE tax_before();

INSERT INTO products(name, producer, count, price)
VALUES
	('F', 'F1', 5, 50);

CREATE TABLE history_of_price (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	price INTEGER,
	date TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_price()
RETURNS TRIGGER AS
$$
	BEGIN
		INSERT INTO history_of_price (name, price, date)
		VALUES (NEW.name, NEW.price, NOW());
		return new;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER log_price_trigger
AFTER INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE log_price();

INSERT INTO products(name, producer, count, price)
VALUES
	('G', 'G1', 2, 500);

SELECT * FROM history_of_price;