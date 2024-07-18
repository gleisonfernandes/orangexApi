package com.gfsoftware.orangex.services;

import java.util.List;
import java.util.Optional;

import com.gfsoftware.orangex.entities.Comment;

public interface CommentService {

	Comment saveComment(Comment comment);
	
	List<Comment> list();

	Comment updateComment(Comment comment);

	void deleteComment(Comment comment);

	List<Comment> listByTweetId(Long id);

	void validateComment(Comment comment);

	Optional<Comment> commentId(Long id);

}
