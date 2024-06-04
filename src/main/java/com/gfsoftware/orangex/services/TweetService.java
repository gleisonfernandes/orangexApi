package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.repositories.TweetRepository;

@Service
public class TweetService {
	
	@Autowired
	private TweetRepository repository;
	
	public List<Tweet> findAll(){
		return repository.findAll();
	}
	
	public Tweet findById(Long id) {
		Optional<Tweet> obj = repository.findById(id);
		return obj.get();
	}
	
	public Tweet insert(Tweet obj) {
		return repository.save(obj);
	}
}
