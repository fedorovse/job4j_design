CREATE TABLE passenger(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE airplane_tiket(
	id SERIAL PRIMARY KEY,
	flight VARCHAR(6),
	passenger_id INT REFERENCES passenger(id)
);