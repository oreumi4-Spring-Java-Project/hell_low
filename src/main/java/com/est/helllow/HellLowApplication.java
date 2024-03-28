package com.est.helllow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //created_at, updated_at 자동 업데이트
public class HellLowApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellLowApplication.class, args);
    }

}
