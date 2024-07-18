package com.gfsoftware.orangex.pk;

import java.io.Serializable;

import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TweetLikePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tweet_id")
	private Tweet tweet;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
