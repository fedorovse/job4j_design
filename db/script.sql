CREATE TABLE friend (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
birthday DATE,
hobbi TEXT,
nick VARCHAR(30),
weight INT
);

INSERT INTO
friend (name, birthday, hobbi, nick, weight)
VALUES ('Anton', '1980-12-20', 'football', 'Gonzo', 90);

UPDATE friend SET weight = 85;

DELETE FROM friend;