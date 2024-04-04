package com.est.helllow.domain.dto;

import com.est.helllow.domain.User;
import com.est.helllow.domain.enum_class.UserGrade;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDto {
    private String userId;
    private String userEmail;
    private String userPw;
    private String userSnsId;
    private String userName;
    private UserGrade userGrade;
    private String userImg;
    private LocalDateTime userCreated;

    public static UserResponseDto toDTO(User user) {
        return UserResponseDto.builder()
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
