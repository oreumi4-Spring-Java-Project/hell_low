INSERT INTO users (created_at,modified_at, user_email, user_grade, user_id, user_img, user_name, role, source) VALUES
                                                                                                                ('2024-01-05 08:23:17.000000','2024-01-05 08:23:17.000000', 'user1@example.com', 'SBD_300', 1, 'image1.jpg', 'User 1', 'ROLE_USER', NULL),
                                                                                                                ('2023-11-12 14:45:32.000000','2024-01-05 08:23:17.000000', 'user2@example.com', 'SBD_300', 2, 'image2.jpg', 'User 2', 'ROLE_USER', NULL),
                                                                                                                ('2024-02-28 10:30:50.000000','2024-01-05 08:23:17.000000', 'user3@example.com', 'SBD_300', 3, 'image3.jpg', 'User 3', 'ROLE_USER', NULL),
                                                                                                                ('2024-01-20 12:04:56.000000','2024-01-05 08:23:17.000000', 'user10@example.com','SBD_300', 10, 'image10.jpg', '임성현', 'ROLE_USER', NULL);

INSERT INTO post (created_at, modified_at, category, like_counts, post_content, post_file, post_id, post_title, user_id, view_counts) VALUES
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'menu', 10, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'http://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R110x110', 1, 'Post Title 1 asdasd ', 1, 20),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 11:00:00.000000', 'photo', 15, 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'http://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R110x110', 2, 'Post Title 2', 2, 30),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'handover', 20, 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'http://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R110x110', 3, 'Post Title 3', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 19:00:00.000000', 'menu', 55, 'Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.', 'http://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R110x110', 11, '임성현', 10, 110);

INSERT INTO comment (created_at, modified_at,com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:05:00.000000', 'Great post!', 1, 1, 1),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:10:00.000000', 'Nice work!', 2, 1, 1),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:15:00.000000', 'Well written.', 3, 1, 1);
#
# INSERT INTO comment (com_content, com_id, post_id, user_id) VALUES
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:20:00.000000', 'Interesting!', 4, 2, 3),
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:25:00.000000', 'I disagree with some points.', 5, 2, 5),
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'Looking forward to more content.', 6, 2, 7);
#
# INSERT INTO comment (com_content, com_id, post_id, user_id) VALUES
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:35:00.000000', 'Amazing photo!', 7, 3, 4),
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:40:00.000000', 'Love the composition.', 8, 3, 6),
#                                                                                            ('2024-03-27 10:00:00.000000', '2024-03-27 10:45:00.000000', 'Where was this taken?', 9, 3, 8);
