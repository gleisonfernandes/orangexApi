package com.gfsoftware.orangex.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gfsoftware.orangex.entities.TweetLike;
import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tb_tweet")
public class Tweet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'Z'", timezone = "GMT")
    private LocalDate createdAt;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String tweetImage;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    
    @JsonIgnore
	@OneToMany(mappedBy = "tweet", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "id.tweet", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<TweetLike> likes = new HashSet<>();
      
}
