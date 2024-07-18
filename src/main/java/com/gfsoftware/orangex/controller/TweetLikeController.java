package com.gfsoftware.orangex.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfsoftware.orangex.dto.TweetLikeDTO;
import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.TweetLike;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.services.TweetLikeService;
import com.gfsoftware.orangex.services.TweetService;
import com.gfsoftware.orangex.services.UserService;

@RestController
@RequestMapping("/api/likes")
public class TweetLikeController {

	@Autowired
	private TweetLikeService service;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity saveTweetLike(@RequestBody TweetLikeDTO dto) {
		try {
			TweetLike entity = convert(dto);
			entity = service.saveTweetLike(entity);
			return new ResponseEntity(entity, HttpStatus.CREATED);
		} catch (RuleBusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<TweetLikeDTO>> findAll() {
	    List<TweetLike> list = service.list();
	    
	    List<TweetLikeDTO> listDto = list.stream()
	            .map(t -> new TweetLikeDTO(t.getTweet().getId(), t.getUser().getId()))
	            .collect(Collectors.toList());

	    return ResponseEntity.ok().body(listDto);
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestParam(value = "tweet", required = true) Long tweet_id,
			@RequestParam(value = "user", required = true) Long user_id) {
		TweetLike tweetLike = new TweetLike();
		Optional<User> user = userService.userId(user_id);
		Optional<Tweet> tweet = tweetService.tweetId(tweet_id);
		tweetLike.setUser(user.get());
		tweetLike.setTweet(tweet.get());
		service.deleteTweetLike(tweetLike);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	private TweetLike convert(TweetLikeDTO dto) {
		TweetLike tweetLike = new TweetLike();
		Tweet tweet = tweetService.tweetId(dto.getTweet_id())
				.orElseThrow(() -> new RuleBusinessException("Tweet não encontrado"));
		tweetLike.setTweet(tweet);

		User user = userService.userId(dto.getUser_id())
				.orElseThrow(() -> new RuleBusinessException("Usuário não encontrado"));
		tweetLike.setUser(user);

		return tweetLike;
	}
}
