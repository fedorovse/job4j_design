CREATE TABLE devices(
	ID SERIAL PRIMARY KEY,
	name VARCHAR(255),
	price INT
);

INSERT INTO devices(name, price)
VALUES
	('square', 10),
	('triangle', 20),
	('circle', 30),
	('rectangle', 40);

SELECT * FROM devices;

UPDATE devices SET price = 15 WHERE name = 'square';

DELETE FROM devices WHERE name = 'circle';

INSERT INTO devices (name, price) VALUES ('oval', 50);

begin transaction;

commit;

rollback;

begin transaction isolation level repeatable read;

begin transaction isolation level serializable;
