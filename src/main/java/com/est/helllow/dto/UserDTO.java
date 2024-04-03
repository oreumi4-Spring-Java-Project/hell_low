package com.est.helllow.dto;

import com.est.helllow.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private Long userId;
    private String userEmail;
    private String userPw;
    private String userSnsId;
    private String userName;
    private String userGrade;
    private String userImg;
    private LocalDateTime userCreated;

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .userCreated(user.getCreatedAt())
                .userId(user.getUserId())
//                .userPw(user.getUserPw())
                .userEmail(user.getUserEmail())
                .userGrade(user.getUserGrade())
                .userImg(user.getUserImg())
                .userName(user.getUserName())
//                .userSnsId(user.getUserSnsId())
                .build();
    }
}
