package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID", updatable = false)
    private Long userId;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_PW", nullable = false)
    private String userPw;

    @Column(name = "USER_SNS_ID")
    private String userSnsId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_GRADE", nullable = false)
    private String userGrade;

    @Column(name = "USER_IMG", nullable = false)
    private String userImg;

    @CreatedDate
    @Column(name = "USER_CREATED")
    private LocalDateTime userCreated;
}
