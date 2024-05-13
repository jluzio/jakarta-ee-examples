INSERT INTO users (name, username, email) VALUES ('John Dev', 'john.dev', 'john.dev@mail.org');
INSERT INTO users (name, username, email) VALUES ('Jane Dev', 'jane.dev', 'jane.dev@mail.org');
INSERT INTO todos (title, created_date, completed, user_id) VALUES ('Cooking', current_timestamp - 2 MONTH, 0, 1);
INSERT INTO todos (title, created_date, completed, user_id) VALUES ('Dance', current_timestamp - 1 MONTH, 0, 2);
