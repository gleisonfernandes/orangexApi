package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;

public interface UserService extends UserDetailsService {

	User saveUser(User user);
	
	List<User> UserAll();

	User updateUser(User user);

	void validateEmail(String email);

	void validateNickname(String nickname);

	Optional<User> userId(Long id);

	Optional<User> userEmail(String email);
}
