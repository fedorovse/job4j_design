CREATE TABLE roles(
	id SERIAL PRIMARY KEY,
	role VARCHAR(255)
);

CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	roles_id INT REFERENCES roles(id)
);

CREATE TABLE rules(
	id SERIAL PRIMARY KEY,
	rule VARCHAR(255)
);

CREATE TABLE roles_rules(
	id SERIAL PRIMARY KEY,
	roles_id INT REFERENCES roles(id),
	rules_id INT REFERENCES rules(id)
);

CREATE TABLE categories(
	id SERIAL PRIMARY KEY,
	category VARCHAR(255)
);

CREATE TABLE states(
	id SERIAL PRIMARY KEY,
	state VARCHAR(255)
);

CREATE TABLE items(
	id SERIAL PRIMARY KEY,
	item VARCHAR(255),
	user_id INT REFERENCES users(id),
	categories_id INT REFERENCES categories(id),
	states_id INT REFERENCES states(id)
);

CREATE TABLE comments(
	id SERIAL PRIMARY KEY,
	comment VARCHAR(255),
	items_id INT REFERENCES items(id)
);

CREATE TABLE attachs(
	id SERIAL PRIMARY KEY,
	attach VARCHAR(255),
	items_id INT REFERENCES items(id)
);