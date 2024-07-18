package com.gfsoftware.orangex.services.impl;

import java.text.Format;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.entities.UserInfoDetails;
import com.gfsoftware.orangex.repositories.UserRepository;
import com.gfsoftware.orangex.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		if(user == null || user.stream().equals("")) {
			throw new RuleBusinessException("Prencha o campo Email.");
		}
		if(!user.isPresent()) {
			throw new RuleBusinessException("Usuário não encontrado.");
		}
		  return user.map(UserInfoDetails::new) 
		    .orElseThrow(() -> new RuleBusinessException("Usuário " + username + " não encontrado.")); 
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		validateEmail(user.getEmail());
		validateNickname(user.getNickname());
		validateUser(user);
		return repository.save(user);
	}
	
	@Override
	@Transactional
	public List<User> UserAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional
	public User updateUser(User user) {
		Objects.requireNonNull(user.getId());
		return repository.save(user);
	}


	@Override
	public Optional<User> userId(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public void validateEmail(String email) {
		boolean exist = repository.existsByEmail(email);
		if(exist) {
			throw new RuleBusinessException("Já existe um usuário com este email.");
		}
	}

	@Override
	public void validateNickname(String nickname) {
		boolean exist = repository.existsByNickname(nickname);
		if(exist) {
			throw new RuleBusinessException("Já existe um usuário com este nickname.");
		}
		
	}
	
	public void validateUser(User user) {
		if(user.getName() == null || user.getName().trim().equals("")
				|| user.getEmail() == null || user.getEmail().trim().equals("")
				|| user.getPassword() == null || user.getPassword().trim().equals("") || user.getPassword() == ""
				|| user.getNickname() == null || user.getNickname().trim().equals("")
				|| user.getDateofbirth() == null || user.getDateofbirth().equals(""))
		{
			throw new RuleBusinessException("Todos os campos devam ser preenchidos.");
		}
		
	}

	@Override
	public Optional<User> userEmail(String email) {
		return repository.findByEmail(email);
	}


}
