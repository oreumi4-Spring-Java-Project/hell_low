package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class User {
    @Id
    @Column(name="USER_ID", updatable = false)
    private String user_id;

    @Column(name = "USER_EMAIL", nullable = false)
    private String user_email;

    @Column(name = "USER_PW", nullable = false)
    private String user_pw;

    @Column(name = "USER_SNS_ID")
    private String user_sns_id;

    @Column(name = "USER_NAME", nullable = false)
    private String user_name;

    @Column(name = "USER_GRADE", nullable = false)
    private String user_grade;

    @Column(name = "USER_IMG", nullable = false)
    private String user_img;

    @CreatedDate
    @Column(name = "USER_CREATED", nullable = false)
    private LocalDateTime user_created;
}
