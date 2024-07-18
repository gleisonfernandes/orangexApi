package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import com.gfsoftware.orangex.entities.Tweet;

public interface TweetService {
	
	Tweet saveTweet(Tweet tweet);
	
	Tweet updateTweet(Tweet tweet);
	
	void deleteTweet(Tweet tweet);
	
	List<Tweet> list();
	
	List<Tweet> listById(Tweet tweet);
	
	void validateTweet(Tweet tweet);
	
	Optional<Tweet> tweetId(Long id);
	
}
