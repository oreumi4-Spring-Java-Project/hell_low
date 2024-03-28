INSERT INTO user (user_created, user_email, user_grade, user_id, user_img, user_name, user_pw, user_sns_id) VALUES
                                                                                                                ('2024-01-05 08:23:17.000000', 'user1@example.com', 'lifts-500', 'user_0001', 'image1.jpg', 'User 1', 'password1', NULL),
                                                                                                                ('2023-11-12 14:45:32.000000', 'user2@example.com', 'lifts-400', 'user_0002', 'image2.jpg', 'User 2', 'password2', NULL),
                                                                                                                ('2024-02-28 10:30:50.000000', 'user3@example.com', 'lifts-300', 'user_0003', 'image3.jpg', 'User 3', 'password3', NULL),
                                                                                                                ('2024-03-15 09:12:21.000000', 'user4@example.com', 'non-members', 'user_0004', 'image4.jpg', 'User 4', 'password4', NULL),
                                                                                                                ('2023-12-20 11:55:44.000000', 'user5@example.com', 'lifts-500', 'user_0005', 'image5.jpg', 'User 5', 'password5', NULL),
                                                                                                                ('2024-01-08 16:20:03.000000', 'user6@example.com', 'lifts-400', 'user_0006', 'image6.jpg', 'User 6', 'password6', NULL),
                                                                                                                ('2023-10-25 13:36:59.000000', 'user7@example.com', 'lifts-300', 'user_0007', 'image7.jpg', 'User 7', 'password7', NULL),
                                                                                                                ('2024-02-10 07:08:37.000000', 'user8@example.com', 'non-members', 'user_0008', 'image8.jpg', 'User 8', 'password8', NULL),
                                                                                                                ('2023-11-30 18:42:15.000000', 'user9@example.com', 'lifts-500', 'user_0009', 'image9.jpg', 'User 9', 'password9', NULL),
                                                                                                                ('2024-01-20 12:04:56.000000', 'user10@example.com', 'lifts-400', 'user_0010', 'image10.jpg', 'User 10', 'password10', NULL);



INSERT INTO post (post_created, post_modified, category, like_counts, post_content, post_file, post_id, post_title, user_id, view_counts) VALUES
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'diets', '10', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'file1.jpg', 'post_0001', 'Post Title 1', 'user_0001', '20'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 11:00:00.000000', 'trade', '15', 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'file2.jpg', 'post_0002', 'Post Title 2', 'user_0002', '30'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'photos', '20', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'file3.jpg', 'post_0003', 'Post Title 3', 'user_0003', '40'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 13:00:00.000000', 'diets', '25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'file4.jpg', 'post_0004', 'Post Title 4', 'user_0004', '50'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 14:00:00.000000', 'trade', '30', 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'file5.jpg', 'post_0005', 'Post Title 5', 'user_0005', '60'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 15:00:00.000000', 'photos', '35', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'file6.jpg', 'post_0006', 'Post Title 6', 'user_0006', '70'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 16:00:00.000000', 'diets', '40', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.', 'file7.jpg', 'post_0007', 'Post Title 7', 'user_0007', '80'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 17:00:00.000000', 'trade', '45', 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.', 'file8.jpg', 'post_0008', 'Post Title 8', 'user_0008', '90'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 18:00:00.000000', 'photos', '50', 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit.', 'file9.jpg', 'post_0009', 'Post Title 9', 'user_0009', '100'),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 19:00:00.000000', 'diets', '55', 'Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.', 'file10.jpg', 'post_0010', 'Post Title 10', 'user_0010', '110');




INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:05:00.000000', 'Great post!', 'com_0001', 'post_0001', 'user_0002'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:10:00.000000', 'Nice work!', 'com_0002', 'post_0001', 'user_0004'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:15:00.000000', 'Well written.', 'com_0003', 'post_0001', 'user_0006');


INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:20:00.000000', 'Interesting!', 'com_0004', 'post_0002', 'user_0003'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:25:00.000000', 'I disagree with some points.', 'com_0005', 'post_0002', 'user_0005'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'Looking forward to more content.', 'com_0006', 'post_0002', 'user_0007');


INSERT INTO comment (com_created, com_modified, com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:35:00.000000', 'Amazing photo!', 'com_0007', 'post_0003', 'user_0004'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:40:00.000000', 'Love the composition.', 'com_0008', 'post_0003', 'user_0006'),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:45:00.000000', 'Where was this taken?', 'com_0009', 'post_0003', 'user_0008');

INSERT INTO post (category, post_title, post_content, post_created, post_modified) VALUES ('notice', 'title1', 'content1', NOW(), NOW());
