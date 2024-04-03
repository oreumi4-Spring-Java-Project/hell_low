package com.est.helllow.utils;

import java.util.UUID;

public class IdGenerator {
    public static String generatePostId(String category) {
        return "post_" + category + "_" + UUID.randomUUID().toString();
    }

    public static String generateCommentId(String category) {
        return "com_" + category + "_" + UUID.randomUUID().toString();
    }

    public static String generateLikePostId(String category) {
        return "like_" + category + "_" + UUID.randomUUID().toString();
    }
}
