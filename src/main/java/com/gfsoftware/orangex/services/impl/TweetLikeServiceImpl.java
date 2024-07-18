package com.gfsoftware.orangex.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfsoftware.orangex.entities.TweetLike;
import com.gfsoftware.orangex.repositories.TweetLikeRepository;
import com.gfsoftware.orangex.services.TweetLikeService;

@Service
public class TweetLikeServiceImpl implements TweetLikeService {

	@Autowired
	private TweetLikeRepository repository;
	
	@Override
	@Transactional
	public TweetLike saveTweetLike(TweetLike tweetLike) {
		return repository.save(tweetLike);
	}
	
	@Override
	public List<TweetLike> list() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void deleteTweetLike(TweetLike tweetLike) {
		Objects.requireNonNull(tweetLike);
		repository.delete(tweetLike);
		
	}

	@Override
	public Optional<TweetLike> tweetLikeId(Long id) {
		return repository.findById(id);
	}



	
}
