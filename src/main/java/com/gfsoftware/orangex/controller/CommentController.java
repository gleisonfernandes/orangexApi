package com.gfsoftware.orangex.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

import com.gfsoftware.orangex.dto.CommentDTO;
import com.gfsoftware.orangex.dto.TweetDTO;
import com.gfsoftware.orangex.dto.TweetLikeDTO;
import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.TweetLike;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.services.CommentService;
import com.gfsoftware.orangex.services.TweetService;
import com.gfsoftware.orangex.services.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll() {
	    List<Comment> list = service.list();
	    List<CommentDTO> listDto = list.stream()
	            .map(c -> new CommentDTO(c.getId(), c.getDescription(), c.getImage(), c.getCreatedAt(), c.getTweet().getId(), c.getUser().getId()))
	            .collect(Collectors.toList());

	    return ResponseEntity.ok().body(listDto);
	    //return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Comment>> findByTweetId(@RequestParam(value = "tweet", required = true) Long tweet_id){
		List<Comment> comments = service.listByTweetId(tweet_id);
		return ResponseEntity.ok().body(comments);
	}	
	
	@PostMapping
	public ResponseEntity saveComment(@RequestBody CommentDTO dto) {
		try {
			Comment entity = convert(dto);
			entity = service.saveComment(entity);
			return new ResponseEntity(entity, HttpStatus.CREATED);
		} catch (RuleBusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CommentDTO dto) {
		return service.commentId(id).map(entity -> {
			
			try {
				Comment comment = new Comment();
				comment.setId(entity.getId());
				comment.setUser(entity.getUser());
				comment.setTweet(entity.getTweet());
				comment.setCreatedAt(entity.getCreatedAt());
				if (dto.getDescription() != null) {
					comment.setDescription(dto.getDescription());
				} else {
					comment.setDescription(entity.getDescription());
				}
				comment.setImage(dto.getImage());
				

				service.updateComment(comment);
				return ResponseEntity.ok(comment);
			} catch (RuleBusinessException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet(() -> new ResponseEntity("Comment não encontrado.", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.commentId(id).map(entity -> {
			service.deleteComment(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Tweet não encontrado.", HttpStatus.BAD_REQUEST));
	}
	
	private Comment convert(CommentDTO dto) {
		Comment comment = new Comment();
		comment.setCreatedAt(LocalDate.now());
		comment.setDescription(dto.getDescription());
		comment.setImage(dto.getImage());
		
		Tweet tweet = tweetService
				.tweetId(dto.getTweet_id())
				.orElseThrow(() -> new RuleBusinessException("Tweet não encontrado"));
		comment.setTweet(tweet);

		User user = userService
				.userId(dto.getUser_id())
				.orElseThrow(() -> new RuleBusinessException("Usuário não encontrado"));
		comment.setUser(user);

		return comment;
	}
	


}
