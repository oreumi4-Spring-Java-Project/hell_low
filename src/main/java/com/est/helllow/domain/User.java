package com.est.helllow.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
// @Getter
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID", updatable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_GRADE", nullable = false)
    private String userGrade;

    @Column(name = "USER_IMG", nullable = false)
    private String userImg;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "SOURCE")
    @Enumerated(EnumType.STRING)
    private RegistrationSource source;

    @CreatedDate
    @Column(name = "USER_CREATED")
    private LocalDateTime userCreated;
}
