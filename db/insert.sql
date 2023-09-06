
INSERT INTO
	users(name)
VALUES
	('Andrey'),
	('Ivan');

INSERT INTO
	roles(role, user_id)
VALUES
	('buyer', 1),
	('buyer', 2);

INSERT INTO
	rules(rule)
VALUES
	('buy'),
	('best');

INSERT INTO
	roles_rules(roles_id, rules_id)
VALUES
	(1, 2),
	(2, 1);

INSERT INTO
	items(item, user_id)
VALUES
	('ball', 1),
	('condom', 2);

INSERT INTO
	comments(comment, items_id)
VALUES
	('good', 1),
	('Very very good!!!', 2);

INSERT INTO
	attachs(attach, items_id)
VALUES
	('ball.jpg', 1),
	('condom.jpg', 2);

INSERT INTO
	categories(category, items_id)
VALUES
	('toys', 1),
	('18+', 2);

INSERT INTO
	states(state, items_id)
VALUES
	('sent', 1),
	('received', 2);

