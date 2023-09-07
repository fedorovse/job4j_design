INSERT INTO
	roles(role)
VALUES
	('buyer'),
	('client');

INSERT INTO
	users(name, roles_id)
VALUES
	('Andrey', 1),
	('Ivan', 1);

INSERT INTO
	rules(rule)
VALUES
	('buy'),
	('best');

INSERT INTO
	roles_rules(roles_id, rules_id)
VALUES
	(1, 2),
	(1, 1);

INSERT INTO
	categories(category)
VALUES
	('toys'),
	('18+');

INSERT INTO
	states(state)
VALUES
	('sent'),
	('received');

INSERT INTO
	items(item, user_id, categories_id, states_id)
VALUES
	('ball', 1, 1, 1),
	('condom', 2, 2, 1);

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

