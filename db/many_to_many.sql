CREATE TABLE bank(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE client(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE bank_client(
	id SERIAL PRIMARY KEY,
	bank_id INT REFERENCES bank(id),
	client_id INT REFERENCES client(id)
);