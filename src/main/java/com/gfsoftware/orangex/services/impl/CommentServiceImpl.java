package com.gfsoftware.orangex.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.entities.User;
import com.gfsoftware.orangex.exception.RuleBusinessException;
import com.gfsoftware.orangex.repositories.CommentRepository;
import com.gfsoftware.orangex.repositories.TweetRepository;
import com.gfsoftware.orangex.repositories.UserRepository;
import com.gfsoftware.orangex.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;
	
	@Override
	@Transactional
	public Comment saveComment(Comment comment) {
		validateComment(comment);
		return repository.save(comment);
	}
	
	@Override
	public List<Comment> list() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Comment updateComment(Comment comment) {
		validateComment(comment);
		Objects.requireNonNull(comment.getId());
		return repository.save(comment);
	}

	@Override
	@Transactional
	public void deleteComment(Comment comment) {
		Objects.requireNonNull(comment.getId());
		repository.delete(comment);
		
	}

	@Override
	@Transactional
	public List<Comment> listByTweetId(Long id) {
		return repository.findByTweetId(id);
	}

	@Override
	public void validateComment(Comment comment) {
		if((comment.getDescription() == null || comment.getDescription().trim().equals("")) && (comment.getImage() == null || comment.getImage().trim().equals(""))) {
			throw new RuleBusinessException("Informe uma conteúdo válido.");
		}
		
	}

	@Override
	public Optional<Comment> commentId(Long id) {
		return repository.findById(id);
	}
	
}
