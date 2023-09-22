CREATE TABLE car_bodies(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE car_engines(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE car_transmissions(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE cars(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	body_id INT REFERENCES car_bodies(id),
	engine_id INT REFERENCES car_engines(id),
	transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name)
VALUES
	('пикап'),
	('седан'),
	('хэчбэк'),
	('джип'),
	('кроссовер');

INSERT INTO car_engines(name)
VALUES
	('V1'),
	('V2'),
	('V3'),
	('V4'),
	('V8');

INSERT INTO car_transmissions(name)
VALUES
	('t10'),
	('t20'),
	('t30'),
	('t40'),
	('t50');

INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES
	('toyota', 1, 5, 2),
	('nissan', 2, 2, 1),
	('niva', 4, 5, 3),
	('oka', 3, 1, 1),
	('zhiguli', 2, 2, 2),
	('opel', 1, null, 2),
	('ford', 2, 2, null),
	('lada', 4, null, null);

SELECT
	cars.ID,
	cars.name AS car_name,
	car_bodies.name AS body_name,
	car_engines.name AS engine_name,
	car_transmissions.name AS transmission_name
FROM cars
LEFT JOIN car_bodies ON cars.body_id = car_bodies.ID
LEFT JOIN car_engines ON cars.engine_id = car_engines.ID
LEFT JOIN car_transmissions ON cars.transmission_id = car_transmissions.ID;

SELECT car_bodies.name AS body_name
FROM car_bodies
LEFT JOIN cars ON car_bodies.ID = cars.body_id
WHERE cars.name IS NULL;

SELECT car_engines.name AS engine_name
FROM car_engines
LEFT JOIN cars ON car_engines.ID = cars.engine_id
WHERE cars.name IS NULL;

SELECT car_transmissions.name AS transmission_name
FROM car_transmissions
LEFT JOIN cars ON car_transmissions.ID = cars.transmission_id
WHERE cars.name IS NULL;

