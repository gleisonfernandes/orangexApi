package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import com.gfsoftware.orangex.entities.TweetLike;

public interface TweetLikeService {

	TweetLike saveTweetLike(TweetLike tweetLike);
	
	List<TweetLike> list();
	
	void deleteTweetLike(TweetLike tweetLike);
	
	Optional<TweetLike> tweetLikeId(Long id);
}
