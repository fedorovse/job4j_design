CREATE TABLE fauna(
	ID SERIAL PRIMARY KEY,
	name TEXT,
	avg_age INT,
	discovery_date DATE
);

INSERT INTO fauna (name, avg_age, discovery_date)
VALUES
	('fishman', 15000, '1949-05-15'),
	('fisher', 3000, NULL),
	('man', 25000, '1915-05-25'),
	('surok', 10000, '1800-03-12'),
	('zayats', 21000, '1918-05-25'),
	('riba_fish', 8000, '1835-07-09'),
	('bear', 600, '1940-05-02'),
	('tarakan', 78000, NULL),
	('pig', 800, '1949-05-15'),
	('akula', 9999, '1949-05-15');

SELECT name, avg_age, discovery_date
FROM fauna
WHERE name LIKE '%fish%';

SELECT name, avg_age, discovery_date
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT name, avg_age, discovery_date
FROM fauna
WHERE discovery_date IS NULL;

SELECT name, avg_age, discovery_date
FROM fauna
WHERE discovery_date < '1950-01-01';

