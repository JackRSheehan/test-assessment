CREATE TABLE people (
    id NUMERIC PRIMARY KEY,
    firstName text,
    lastName text,
    email text,
    password text
);

INSERT INTO people(id, firstName, lastName, email, password)
VALUES (1, 'john', 'smith', 'j@email.com', 'hello');


