package com.gfsoftware.orangex.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.repositories.TweetRepository;
import com.gfsoftware.orangex.services.TweetService;

@Service
public class TweetServiceImpl implements TweetService {
	
	private TweetRepository repository;
	
	public TweetServiceImpl(TweetRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Tweet saveTweet(Tweet tweet) {
		validateTweet(tweet);
		return repository.save(tweet);
	}

	@Override
	@Transactional
	public Tweet updateTweet(Tweet tweet) {
		validateTweet(tweet);
		Objects.requireNonNull(tweet.getId());
		return repository.save(tweet);
	}

	@Override
	@Transactional
	public void deleteTweet(Tweet tweet) {
		Objects.requireNonNull(tweet.getId());
		repository.delete(tweet);
		
	}

	@Override
	@Transactional
	public List<Tweet> list() {
		return repository.findAllOrderedByIdDesc();
	}
	
	@Override
	@Transactional
	public List<Tweet> listById(Tweet tweetUser) {
		Example example = Example.of(tweetUser);
		return repository.findAll(example);
	}

	@Override
	public void validateTweet(Tweet tweet) {
		if((tweet.getDescription() == null || tweet.getDescription().trim().equals("")) && (tweet.getTweetImage() == null || tweet.getTweetImage().trim().equals(""))) {
			throw new RuleBusinessException("Informe uma conteúdo válido.");
		}
		
	}

	@Override
	public Optional<Tweet> tweetId(Long id) {
		return repository.findById(id);
	}

}
