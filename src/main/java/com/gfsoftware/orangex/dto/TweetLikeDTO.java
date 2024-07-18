package com.gfsoftware.orangex.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TweetLikeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long tweet_id;
	private Long user_id;
}
