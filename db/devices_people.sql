CREATE TABLE devices(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	price FLOAT
);

CREATE TABLE people(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE devices_people(
	ID SERIAL PRIMARY KEY,
	device_id INT REFERENCES devices(id),
	people_id INT REFERENCES people(id)
);

INSERT INTO devices(name, price)
VALUES
	('square', 3000.55),
	('triangle', 8000.28),
	('circle', 6000.20),
	('rectangle', 1500.63);

INSERT INTO people(name)
VALUES
	('Sanya'),
	('Andrey'),
	('Egor'),
	('Misha');

INSERT INTO devices_people(people_id, device_id)
VALUES
	(1, 1),
	(1, 2),
	(1, 4),
	(2, 2),
	(2, 3),
	(3, 1),
	(3, 2),
	(4, 1),
	(4, 1);

SELECT AVG(price)
FROM devices;

SELECT p.name, AVG(d.price)
FROM people AS p
INNER JOIN devices_people AS dp ON p.id = dp.people_id
INNER JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name, AVG(d.price)
FROM people AS p
INNER JOIN devices_people AS dp ON p.id = dp.people_id
INNER JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;