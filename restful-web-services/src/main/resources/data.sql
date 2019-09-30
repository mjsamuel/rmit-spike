INSERT INTO comments(upspikes, author_id, downspikes, content, reply_id, thread_id, archived) VALUES
(10, 2, 1, 'This is a test comment', 1, 1, false);

INSERT INTO users(admin, email, username, password, first_name, last_name, upspikes, archived) VALUES
(true, 's1234567@student.rmit.edu.au', 'sept', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e', 'john', 'doe', 20, false),
(false, 's7654321@student.rmit.edu.au', 'user', '$2a$10$r/45rWG0VCwvjrOeVkhqWucO7zmnj7TUzZWMQFH6.h12AqSLteyN2', 'jane', 'doe', 0, false);

INSERT INTO channels(name, datetime, visibility, archived) VALUES
('SEPT', '2019-08-20 21:00:00', 1, false);

INSERT INTO threads(title, datetime, content, archived, upspikes, downspikes, author_id, channel_id) VALUES
('The rise and fall of SEPT at RMIT', '2019-08-29 21:08:19', 'Content of the thread', false, 10, 20, 1, 1);

INSERT INTO channel_threads(channel_id, threads_id) VALUES
(1, 1);

INSERT INTO subscribed_to(user_id, subscribed_to_id) VALUES
(1, 1);
