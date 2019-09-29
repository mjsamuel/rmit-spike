insert into comments(upspikes, author_id, downspikes, content, reply_id, thread_id, archived)
values(10, 2, 1, 'This is a test comment', 1, 1, false);

insert into users(id, admin, email, username, password, first_name, last_name, upspikes, archived) values
(1, true, 's1234567@student.rmit.edu.au', 'sept', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e', 'john', 'doe', 20, false),
(2, false, 's7654321@student.rmit.edu.au', 'user', '$2a$10$r/45rWG0VCwvjrOeVkhqWucO7zmnj7TUzZWMQFH6.h12AqSLteyN2', 'jane', 'doe', 0, false);

insert into channels(id, name, datetime, visibility, archived)
values(1, 'Test Channel', 'Fri Sep 20 21:00:00 AEST 2019', 1, 1);
