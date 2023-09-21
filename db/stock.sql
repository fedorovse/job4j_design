CREATE TABLE type(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE product(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	type_id INT REFERENCES type(id),
	expired_date DATE,
	price DECIMAL(10,2)
);

INSERT INTO type(name)
VALUES
	('СЫР'),
	('МОЛОКО'),
	('МОРОЖЕНОЕ'),
	('ХЛЕБ'),
	('МЯСО'),
	('КОЛБАСА');

INSERT INTO product(name, type_id, expired_date, price)
VALUES
	('Вкусное мороженое', 3, '2023-10-15', 50.2),
	('мороженое Эскимо', 3, '2023-12-10', 50.2),
	('сыр Голландский', 1, '2023-11-04', 130.2),
	('сыр Никольский', 1, '2023-10-25', 1000.25),
	('хлеб Ржаной', 4, '2023-09-15', 33.2),
	('хлеб на сывортке', 4, '2023-09-12', 50.2),
	('докторская', 6, '2023-10-15', 1000.25),
	('салями', 6, '2023-10-15', 520.32),
	('молоко Купянское', 2, '2023-10-15', 10.28),
	('молоко тухлое', 2, '2023-06-03', 65.67),
	('говядина', 5, '2023-10-15', 44.62),
	('зачетная', 6, '2023-11-10', 560.72),
	('офигенная', 6, '2023-10-03', 1000.25),
	('молочная колбаса', 6, '2023-10-22', 50.2),
	('каралька', 6, '2023-12-08', 350.22),
	('хреновая', 6, '2023-12-15', 250.2),
	('жирная', 6, '2023-11-13', 560.42),
	('ливерная', 6, '2023-10-17', 220.24),
	('правильная', 6, '2023-11-25', 650.62);

SELECT type.name, product.name
FROM type INNER JOIN product ON type.id = product.type_id
WHERE type.name = 'СЫР';

SELECT name
FROM product
WHERE name LIKE '%мороженое%';

SELECT name, expired_date
FROM product
WHERE expired_date < NOW();

SELECT name, price
FROM product
WHERE price IN (
	SELECT MAX(price)
	FROM product
	GROUP BY price
	ORDER BY price DESC
	LIMIT 1
);

SELECT type.name, COUNT(product.name)
FROM type INNER JOIN product ON type.id = product.type_id
GROUP BY type.name;

SELECT type.name, product.name
FROM type INNER JOIN product ON type.id = product.type_id
WHERE type.name = 'СЫР' OR type.name = 'МОЛОКО';

SELECT name
FROM type
WHERE name IN (
	SELECT type.name
	FROM type INNER JOIN product ON type.id = product.type_id
	GROUP BY type.name
	HAVING COUNT(product.name) < 10
);

SELECT product.name, type.name
FROM type INNER JOIN product ON type.id = product.type_id;

