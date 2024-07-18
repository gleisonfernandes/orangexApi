package com.gfsoftware.orangex.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.services.UserService;
import com.gfsoftware.orangex.dto.UserDTO;
import com.gfsoftware.orangex.components.JwtComponent;
import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.entities.AuthRequest;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
//@RequestMapping(value = "/users")
public class UserController {

	private final UserServiceImpl service;
	private final JwtComponent jwtComponent;
	private final AuthenticationManager authenticationManager;

	UserController(UserServiceImpl service, JwtComponent jwtComponent, AuthenticationManager authenticationManager) {
		this.service = service;
		this.jwtComponent = jwtComponent;
		this.authenticationManager = authenticationManager;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.UserAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{email}")
	public ResponseEntity findAll(@PathVariable String email) {
		Optional<User> user = service.userEmail(email);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = jwtComponent.generateToken(authRequest.getEmail());
			return ResponseEntity.ok(token);
		} else {
			throw new UsernameNotFoundException("Solicitação de usuário inválida!");
		}
	}

	@PostMapping("/register")
	public ResponseEntity toSave(@RequestBody UserDTO dto) {
		User user = User.builder().email(dto.getEmail()).password(dto.getPassword()).name(dto.getName())
				.nickname(dto.getNickname()).dateofbirth(dto.getDateofbirth()).createdAt(LocalDate.now()).roles("USER")
				.build();
		try {
			User userSave = service.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro Realizado");
		} catch (RuleBusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
		return service.userId(id).map(entity -> {
			try {
				User user = new User();
				user.setId(entity.getId());
				user.setEmail(entity.getEmail());
				user.setPassword(entity.getPassword());
				user.setNickname(entity.getNickname());
				user.setDateofbirth(entity.getDateofbirth());
				user.setCreatedAt(entity.getCreatedAt());
				user.setFollowing(entity.getFollowing());
				user.setFollowers(entity.getFollowers());
				user.setRoles(entity.getRoles());
				if (dto.getName() != null) {
					user.setName(dto.getName());
				} else {
					user.setName(entity.getName());
				}
				if (dto.getProfileImage() != null) {
					user.setProfileImage(dto.getProfileImage());
				} else {
					user.setProfileImage(entity.getProfileImage());
				}
				if (dto.getCoverImage() != null) {
					user.setCoverImage(dto.getCoverImage());
				} else {
					user.setCoverImage(entity.getCoverImage());
				}
				if (dto.getBio() != null) {
					user.setBio(dto.getBio());
				} else {
					user.setBio(entity.getBio());
				}
				if (dto.getCity() != null) {
					user.setCity(dto.getCity());
				} else {
					user.setCity(entity.getCity());
				}
				if (dto.getSite() != null) {
					user.setSite(dto.getSite());
				} else {
					user.setSite(entity.getSite());
				}

				service.updateUser(user);
				return ResponseEntity.ok(user);
			} catch (RuleBusinessException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("User não encontrado.", HttpStatus.BAD_REQUEST));
	}
}
