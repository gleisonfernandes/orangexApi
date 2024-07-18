package com.gfsoftware.orangex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfsoftware.orangex.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByTweetId(Long id);
}
