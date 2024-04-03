package com.est.helllow.repository;

import java.util.Optional;

import com.est.helllow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByuserEmail(String userEmail);
}
