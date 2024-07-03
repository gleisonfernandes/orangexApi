package com.gfsoftware.orangex.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.pk.TweetLikePK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tweet_like")
public class TweetLike implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TweetLikePK id = new TweetLikePK();
	
	public TweetLike() {
	}

	public TweetLike(Tweet tweet, User user) {
		id.setTweet(tweet);
		id.setUser(user);
	}
	
	
	@JsonIgnore
	public Tweet getTweet() {
		return id.getTweet();
	}
	
	public void setTweet(Tweet tweet) {
		id.setTweet(tweet);
	}
	@JsonIgnore
	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}
	
}
