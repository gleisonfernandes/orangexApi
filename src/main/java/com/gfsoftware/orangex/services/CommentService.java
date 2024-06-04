package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository repository;
	
	public List<Comment> findAll(){
		return repository.findAll();
	}
	
	public Comment findById(Long id) {
		Optional<Comment> obj = repository.findById(id);
		return obj.get();
	}
	
	public Comment insert(Comment obj) {
		return repository.save(obj);
	}
}
