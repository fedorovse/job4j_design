CREATE TABLE fingerprint(
	id SERIAL PRIMARY KEY,
	finger VARCHAR(255)
);

CREATE TABLE human(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	fingerprint_id INT REFERENCES fingerprint(id) UNIQUE
);
