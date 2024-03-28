INSERT INTO post (category, post_title, post_content)
VALUES ('notice', 'title1', 'content1');

INSERT INTO user (user_email, user_pw, user_name, user_grade, user_img, user_created)
    VALUES('user1@example.com', 'password1', 'user1', 'lifts-500', 'image1.jpg', '2024-01-05 08:23:17.000000');

/*
INSERT INTO user (user_created, user_email, user_grade, user_img, user_name, user_pw, user_sns_id)
VALUES ('2024-01-05 08:23:17.000000', 'user1@example.com', 'lifts-500', 'image1.jpg', 'User 1', 'password1', NULL),
       ('2023-11-12 14:45:32.000000', 'user2@example.com', 'lifts-400', 'image2.jpg', 'User 2', 'password2', NULL),
       ('2024-02-28 10:30:50.000000', 'user3@example.com', 'lifts-300', 'image3.jpg', 'User 3', 'password3', NULL),
       ('2024-03-15 09:12:21.000000', 'user4@example.com', 'non-members', 'image4.jpg', 'User 4', 'password4', NULL),
       ('2023-12-20 11:55:44.000000', 'user5@example.com', 'lifts-500', 'image5.jpg', 'User 5', 'password5', NULL),
       ('2024-01-08 16:20:03.000000', 'user6@example.com', 'lifts-400', 'image6.jpg', 'User 6', 'password6', NULL),
       ('2023-10-25 13:36:59.000000', 'user7@example.com', 'lifts-300', 'image7.jpg', 'User 7', 'password7', NULL),
       ('2024-02-10 07:08:37.000000', 'user8@example.com', 'non-members', 'image8.jpg', 'User 8', 'password8', NULL),
       ('2023-11-30 18:42:15.000000', 'user9@example.com', 'lifts-500', 'image9.jpg', 'User 9', 'password9', NULL),
       ('2024-01-20 12:04:56.000000', 'user10@example.com', 'lifts-400', 'image10.jpg', 'User 10', 'password10', NULL);

INSERT INTO user (user_created, user_email, user_grade, user_id, user_img, user_name, user_pw, user_sns_id) VALUES
                                                                                                                ('2024-01-05 08:23:17.000000', 'user1@example.com', 'lifts-500', 1, 'image1.jpg', 'User 1', 'password1', NULL),
                                                                                                                ('2023-11-12 14:45:32.000000', 'user2@example.com', 'lifts-400', 2, 'image2.jpg', 'User 2', 'password2', NULL),
                                                                                                                ('2024-02-28 10:30:50.000000', 'user3@example.com', 'lifts-300', 3, 'image3.jpg', 'User 3', 'password3', NULL),
                                                                                                                ('2024-03-15 09:12:21.000000', 'user4@example.com', 'non-members', 4, 'image4.jpg', 'User 4', 'password4', NULL),
                                                                                                                ('2023-12-20 11:55:44.000000', 'user5@example.com', 'lifts-500', 5, 'image5.jpg', 'User 5', 'password5', NULL),
                                                                                                                ('2024-01-08 16:20:03.000000', 'user6@example.com', 'lifts-400', 6, 'image6.jpg', 'User 6', 'password6', NULL),
                                                                                                                ('2023-10-25 13:36:59.000000', 'user7@example.com', 'lifts-300', 7, 'image7.jpg', 'User 7', 'password7', NULL),
                                                                                                                ('2024-02-10 07:08:37.000000', 'user8@example.com', 'non-members', 8, 'image8.jpg', 'User 8', 'password8', NULL),
                                                                                                                ('2023-11-30 18:42:15.000000', 'user9@example.com', 'lifts-500', 9, 'image9.jpg', 'User 9', 'password9', NULL),
                                                                                                                ('2024-01-20 12:04:56.000000', 'user10@example.com', 'lifts-400', 10, 'image10.jpg', 'User 10', 'password10', NULL);

INSERT INTO post (post_created, post_modified, category, like_counts, post_content, post_file, post_id, post_title, user_id, view_counts) VALUES
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'diets', 10, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'file1.jpg', 1, 'Post Title 1', 1, 20),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 11:00:00.000000', 'trade', 15, 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'file2.jpg', 2, 'Post Title 2', 2, 30),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'photos', 20, 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'file3.jpg', 3, 'Post Title 3', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 13:00:00.000000', 'diets', 25, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'file4.jpg', 4, 'Post Title 4', 4, 50),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 14:00:00.000000', 'trade', 30, 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'file5.jpg', 5, 'Post Title 5', 5, 60),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 15:00:00.000000', 'photos', 35, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'file6.jpg', 6, 'Post Title 6', 6, 70),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 16:00:00.000000', 'diets', 40, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.', 'file7.jpg', 7, 'Post Title 7', 7, 80),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 17:00:00.000000', 'trade', 45, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.', 'file8.jpg', 8, 'Post Title 8', 8, 90),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 18:00:00.000000', 'photos', 50, 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit.', 'file9.jpg', 9, 'Post Title 9', 9, 100),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 19:00:00.000000', 'diets', 55, 'Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.', 'file10.jpg', 10, 'Post Title 10', 10, 110);

INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:05:00.000000', 'Great post!', 1, 1, 2),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:10:00.000000', 'Nice work!', 2, 1, 4),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:15:00.000000', 'Well written.', 3, 1, 6);

INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:20:00.000000', 'Interesting!', 4, 2, 3),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:25:00.000000', 'I disagree with some points.', 5, 2, 5),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'Looking forward to more content.', 6, 2, 7);

INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:35:00.000000', 'Amazing photo!', 7, 3, 4),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:40:00.000000', 'Love the composition.', 8, 3, 6),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:45:00.000000', 'Where was this taken?', 9, 3, 8);
 */