CREATE TABLE students (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE authors (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE books(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	author_id INT REFERENCES authors(id)
);

CREATE TABLE orders(
	id SERIAL PRIMARY KEY,
	active BOOLEAN DEFAULT TRUE,
	book_id INT REFERENCES books(id),
	student_id INT REFERENCES students(id)
);

INSERT INTO students (name)
VALUES
	('Иван Иванов'),
	('Пётр Петров');

INSERT INTO authors (name)
VALUES
	('Пушкин А.С.'),
	('Гоголь Н.В.'),
	('Достоевский Ф.М.');

INSERT INTO books (name, author_id)
VALUES
	('Евгений Онегин', 1),
	('Капитанская дочка', 1),
	('Дубровский', 1),
	('Мертвые души', 2),
	('Вий', 2),
	('Идиот', 3),
	('Преступление и наказание', 3);

INSERT INTO orders (book_id, student_id)
VALUES
	(1, 1),
	(3, 1),
	(5, 2),
	(4, 1),
	(2, 2);

CREATE VIEW show_all_books_in_the_library
AS SELECT
	a.name AS автор,
	b.name AS название
FROM authors AS a
	INNER JOIN books AS b ON a.id = b.author_id
	LEFT JOIN orders AS o ON b.id = o.book_id
WHERE student_id IS NULL;

SELECT * FROM show_all_books_in_the_library;
