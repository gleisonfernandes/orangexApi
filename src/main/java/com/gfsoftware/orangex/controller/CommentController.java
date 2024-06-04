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

import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@GetMapping
	public ResponseEntity<List<Comment>> findAll(){
		List<Comment> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comment> findById(@PathVariable Long id){
		Comment obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Comment> insert(@RequestBody Comment obj){
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
}
