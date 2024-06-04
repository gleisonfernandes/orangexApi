package com.gfsoftware.orangex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.services.TweetService;

@RestController
@RequestMapping(value = "/tweets")
public class TweetController {
	
	@Autowired
	private TweetService service;
	
	@GetMapping
	public ResponseEntity<List<Tweet>> findAll(){
		List<Tweet> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tweet> findById(@PathVariable Long id){
		Tweet obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Tweet> insert(@RequestBody Tweet obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
}
