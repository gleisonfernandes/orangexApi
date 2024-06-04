package com.gfsoftware.orangex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfsoftware.orangex.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
