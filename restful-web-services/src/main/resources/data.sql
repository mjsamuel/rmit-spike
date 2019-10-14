INSERT INTO comments(upspikes, author_id, downspikes, content, reply_id, thread_id, archived) VALUES
(10, 2, 1, 'This is a test comment', 1, 1, false);

INSERT INTO users(admin, email, username, password, first_name, last_name, upspikes, archived) VALUES
(true, 's1234567@student.rmit.edu.au', 'sept', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e', 'john', 'doe', 20, false),
(false, 's7654321@student.rmit.edu.au', 'user', '$2a$10$r/45rWG0VCwvjrOeVkhqWucO7zmnj7TUzZWMQFH6.h12AqSLteyN2', 'jane', 'doe', 0, false);

INSERT INTO channels(name, datetime, visibility, archived) VALUES
('SEPT', '2019-08-20 21:00:00', 1, false),
('CT2019', '2019-08-20 21:00:00', 1, false);

INSERT INTO threads(title, datetime, content, archived, upspikes, downspikes, author_id, channel_id) VALUES
('The rise and fall of SEPT at RMIT', '2019-08-29 21:08:19', 'Content of the thread', false, 10, 20, 1, 1),
('Regex to finite state automata?', '2019-08-30 21:08:19', 'How do you convert regex into a finite state automata? There isn''t a clear example in the lecture slides.' , false, 0, 0, 2, 2),
('Assignment 1 help', '2019-07-21 21:08:19', 'All questions relating to assignment 1 here' , false, 42, 0, 2, 2),
('Assignment 2 help', '2019-09-15 21:08:19', 'All questions relating to assignment 2 here' , false, 42, 0, 2, 2);

INSERT INTO channel_threads(channel_id, threads_id) VALUES
(1, 1),
(2, 2),
(2, 3),
(2, 4);

INSERT INTO subscribed_to(user_id, subscribed_to) VALUES
(1, 1),
(1, 2);
