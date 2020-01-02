-- author
INSERT INTO author(id, name, email) VALUES
('000000000-0000-0000-0000-00000000001','Maksim Kostromin', 'daggerok@gmail.com'),
('000000000-0000-0000-0000-00000000002','Maksim Ko', 'daggerok@gmail.com');
-- message
INSERT INTO message(id, body) VALUES
('000000000-0000-0000-0000-00000000001', 'hello!');
-- many-to-many
INSERT INTO message_author(message, author) VALUES
('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000001'),
('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000002');

-- -- author
-- INSERT INTO author(id, name, email, last_modified_date_time) VALUES
-- ('000000000-0000-0000-0000-00000000001','Maksim Kostromin', 'daggerok@gmail.com', NOW()),
-- ('000000000-0000-0000-0000-00000000002','Maksim Ko', 'daggerok@gmail.com', NOW());
-- -- message
-- INSERT INTO message(id, aggregate_id, body, last_modified_date_time) VALUES
-- ('000000000-0000-0000-0000-00000000001', UUID(), 'hello!', NOW());
-- -- many-to-many
-- INSERT INTO message_author(message, author) VALUES
-- ('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000001'),
-- ('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000002');

-- -- author
-- INSERT INTO author(id, name, email, last_modified_date_time) VALUES
-- ('000000000-0000-0000-0000-00000000001','Maksim Kostromin', 'daggerok@gmail.com', LOCALTIMESTAMP),
-- ('000000000-0000-0000-0000-00000000002','Maksim Ko', 'daggerok@gmail.com', LOCALTIMESTAMP);
-- -- message
-- INSERT INTO message(id, aggregate_id, body, last_modified_date_time) VALUES
-- ('000000000-0000-0000-0000-00000000001', UUID(), 'hello!', LOCALTIMESTAMP);
-- -- many-to-many
-- INSERT INTO message_author(message, author) VALUES
-- ('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000001'),
-- ('000000000-0000-0000-0000-00000000001','000000000-0000-0000-0000-00000000002');
