package com.est.helllow.domain;

import com.est.helllow.config.BaseTimeEntity;
import com.est.helllow.domain.enum_class.UserGrade;
import com.est.helllow.domain.enum_class.UserRole;
import com.est.helllow.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @Column(name="USER_ID", updatable = false)
    private String userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_GRADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGrade userGrade; // UserGrade 로 등급 관리

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
