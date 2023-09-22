CREATE TABLE departments(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE employees(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	departments_id INT REFERENCES departments(id)
);

INSERT INTO departments(name)
VALUES
	('one'),
	('two'),
	('three'),
	('four'),
	('five');

INSERT INTO employees(name, departments_id)
VALUES
	('A', 1),
	('B', 2),
	('C', 3),
	('D', 4),
	('E', 2),
	('F', 1),
	('G', 4),
	('H', 2),
	('I', 3),
	('J', 1),
	('K', 3);

SELECT d.name, e.name
FROM departments AS d LEFT JOIN employees AS e ON d.id = e.departments_id;

SELECT d.name, e.name
FROM departments AS d RIGHT JOIN employees AS e ON d.id = e.departments_id;

SELECT d.name, e.name
FROM departments AS d FULL JOIN employees AS e ON d.id = e.departments_id;

SELECT d.name, e.name
FROM departments AS d CROSS JOIN employees AS e;

SELECT d.name
FROM departments AS d LEFT JOIN employees AS e ON d.id = e.departments_id
WHERE e.name IS NULL;

SELECT d.name, e.name
FROM departments AS d LEFT JOIN employees AS e ON d.id = e.departments_id;

SELECT d.name, e.name
FROM employees AS e RIGHT JOIN departments AS d ON e.departments_id = d.id;

CREATE TABLE teens(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	gender VARCHAR(10)
);

INSERT INTO teens(name, gender)
VALUES
	('Оля', 'жен'),
	('Маша', 'жен'),
	('Катя', 'жен'),
	('Олег', 'муж'),
	('Иван', 'муж'),
	('Денис', 'муж');

SELECT t1.name, t2.name
FROM teens AS t1 CROSS JOIN teens AS t2
WHERE t1.gender <> t2.gender AND t1.gender = 'муж';
