package com.est.helllow.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.est.helllow.domain.User;
import com.est.helllow.repository.UserRepository;
import com.est.helllow.service.UserInterfaceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserInterfaceService {

	private final UserRepository userRepository;
	@Override
	public Optional<User> findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}
}