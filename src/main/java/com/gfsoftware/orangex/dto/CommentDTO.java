package com.gfsoftware.orangex.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String description;
	private String image;
    private LocalDate createdAt;
	private Long tweet_id;
	private Long user_id;
}
