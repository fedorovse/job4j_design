CREATE TABLE items (
	id serial primary key,
	buyer_name VARCHAR(255),
	delivery BOOL,
	price DECIMAL(10,2)
);

CREATE TABLE product(
	id SERIAL PRIMARY KEY,
	product_name VARCHAR(255),
	comment VARCHAR(255),
	foto VARCHAR(255),
	items_id INT REFERENCES items(id)
);

INSERT INTO items(buyer_name, delivery, price)
VALUES
	('Ivan', true, 99.52),
	('Max', true, 205.13),
	('Roman', false, 45.33),
	('Evgeniy', true, 8.7),
	('Sanya', false, 236.12);

INSERT INTO product(product_name, comment, foto, items_id)
VALUES
	('ball', 'red', 'red_ball.jpg', 1),
	('condom', 'big', 'big_condom.jpg', 2),
	('doll', 'small', 'small_doll.jpg', 3),
	('kub', 'blue', 'blue_kub', 4),
	('phone', '4 x 256', 'phone.jpg', 5),
	('slon', 'small slon', 'slon.jpg', NULL);

SELECT i.buyer_name Имя, i.delivery Доставка, i.price Цена, p.product_name AS "Название товара"
FROM items AS i INNER JOIN product AS p
ON i.id = p.items_id
WHERE i.price > 100.00
ORDER BY i.price;

SELECT i.buyer_name Имя, i.price Цена, p.product_name AS "Название товара", p.comment Описание
FROM items AS i INNER JOIN product AS p
ON i.id = p.items_id
WHERE p.product_name LIKE '%ll%'
ORDER BY i.price DESC;

SELECT i.buyer_name Имя, i.price Цена, p.product_name AS "Название товара"
FROM items AS i INNER JOIN product AS p
ON i.id = p.items_id
WHERE i.price BETWEEN 90.0 AND 220.00
ORDER BY i.price DESC;



