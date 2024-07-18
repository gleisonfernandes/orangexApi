package com.gfsoftware.orangex.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.services.TweetService;
import com.gfsoftware.orangex.dto.TweetDTO;
import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.services.UserService;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	@Autowired
	private TweetService service;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity findAll() {
		List<Tweet> list = service.list();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/user")
	public ResponseEntity findAllUser(@RequestParam(value = "user", required = true) Long user_id) {
		Tweet tweetUser = new Tweet();
		Optional<User> user = userService.userId(user_id);
		if (!user.isPresent()) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado.");
		} else {
			tweetUser.setUser(user.get());
		}
		List<Tweet> tweets = service.listById(tweetUser);
		return ResponseEntity.ok(tweets);
	}

	@PostMapping
	public ResponseEntity saveTweet(@RequestBody TweetDTO dto) {
		try {
			Tweet entity = convert(dto);
			entity = service.saveTweet(entity);
			return new ResponseEntity(entity, HttpStatus.CREATED);
		} catch (RuleBusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TweetDTO dto) {
		return service.tweetId(id).map(entity -> {
			try {
				Tweet tweet = new Tweet();
				tweet.setId(entity.getId());
				tweet.setUser(entity.getUser());
				tweet.setCreatedAt(entity.getCreatedAt());
				if (dto.getDescription() != null) {
					tweet.setDescription(dto.getDescription());
				} else {
					tweet.setDescription(entity.getDescription());
				}
				if (dto.getTweetImage() != null) {
					tweet.setTweetImage(dto.getTweetImage());
				} else {
					tweet.setTweetImage(entity.getTweetImage());
				}

				service.updateTweet(tweet);
				return ResponseEntity.ok(tweet);
			} catch (RuleBusinessException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Tweet não encontrado.", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.tweetId(id).map(entity -> {
			service.deleteTweet(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Tweet não encontrado.", HttpStatus.BAD_REQUEST));
	}

	private Tweet convert(TweetDTO dto) {
		Tweet tweet = new Tweet();
		tweet.setCreatedAt(LocalDate.now());
		tweet.setDescription(dto.getDescription());
		tweet.setTweetImage(dto.getTweetImage());

		User user = userService.userId(dto.getUser_id())
				.orElseThrow(() -> new RuleBusinessException("Usuário não encontrado"));
		tweet.setUser(user);

		return tweet;
	}

}
