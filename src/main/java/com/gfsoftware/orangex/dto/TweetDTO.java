package com.gfsoftware.orangex.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private LocalDate date;
    @NotNull @NotBlank
    private String description;
    private String tweetImage;
    private Integer retweets;
    @NotNull
    private Long user_id;

}
