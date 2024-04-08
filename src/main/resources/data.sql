INSERT INTO users (created_at,modified_at, user_email, user_grade, user_id, user_img, user_name, role, source) VALUES
                                                                                                                ('2024-01-05 08:23:17.000000','2024-01-05 08:23:17.000000', 'user1@example.com', 'SBD_300', 1, 'image1.jpg', 'User 1', 'ROLE_USER', NULL),
                                                                                                                ('2023-11-12 14:45:32.000000','2024-01-05 08:23:17.000000', 'user2@example.com', 'SBD_300', 2, 'image2.jpg', 'User 2', 'ROLE_USER', NULL),
                                                                                                                ('2024-02-28 10:30:50.000000','2024-01-05 08:23:17.000000', 'user3@example.com', 'SBD_300', 3, 'image3.jpg', 'User 3', 'ROLE_USER', NULL),
                                                                                                                ('2024-01-20 12:04:56.000000','2024-01-05 08:23:17.000000', 'user10@example.com','SBD_300', 10, 'image10.jpg', '임성현', 'ROLE_USER', NULL);

INSERT INTO post (created_at, modified_at, category, like_counts, post_content, post_file, post_id, post_title, user_id, view_counts) VALUES
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 10:30:00.000000', 'photo', 10, '오늘은 등운동 시원하게 했습니다. 여러분도 오운완 하시죠.', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img1.png', 1, '오늘은 등 쫌 잘 먹히네', 1, 20),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 11:00:00.000000', 'photo', 7, '이게 등이다..', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img2.png', 2, '오운완', 2, 30),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'photo', 3, '오늘도 화이팅이다....', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img7.png', 3, '오운완!!임돠', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'menu', 6, '아 역시 식단은 생명...', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img3.png', 4, '식단 완료..', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'menu', 9, '오늘은 그냥 포기하고 라면 먹습니다ㅎㅎㅎ.', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img4.png', 5, '그냥 라면 먹습니다!', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'menu', 22, '식단 관리 중...', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img5.png', 6, '오늘도 샐러드...', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 12:00:00.000000', 'menu', 17, '먹고 죽자!!', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img6.png', 7, '식단 실패..', 3, 40),
                                                                                                                                              ('2024-03-27 10:00:00.000000', '2024-03-27 19:00:00.000000', 'photo', 55, '오늘은 어깨하는 날이라서 어깨하는 사진 한번 올려봅니다ㅎㅎㅎ 다음에도 사진 공유하겠습니다!! ', 'https://hell-low.s3.ap-northeast-2.amazonaws.com/dummy/img8.png', 8, '어깨 신 등 장', 10, 110);

INSERT INTO comment (created_at, modified_at,com_content, com_id, post_id, user_id) VALUES
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:05:00.000000', '와 감탄이 나오네요 잘 보고 갑니다!!', 1, 8, 1),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:05:00.000000', '어깨 자세 한번 다시 보여주실 수 있나요??', 2, 8, 2),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:10:00.000000', '역시...!!', 3, 8, 2),
                                                                                           ('2024-03-27 10:00:00.000000', '2024-03-27 10:15:00.000000', '저도 열심히 하면 그정도로 잘할 수 있겠죠??', 4, 8, 1);
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
