package com.gfsoftware.orangex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gfsoftware.orangex.entities.TweetLike;

@Repository
public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {

}
