package com.gfsoftware.orangex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gfsoftware.orangex.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
	@Query("SELECT e FROM Tweet e ORDER BY e.id DESC")
    List<Tweet> findAllOrderedByIdDesc();
}
