package com.est.helllow.domain;

import com.est.helllow.config.BaseTimeEntity;
import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @Column(name="USER_ID", updatable = false)
    private String userId;

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

    @PrePersist
    public void prePersist(){
        this.userId= IdGenerator.generateUserId();
    }

}
