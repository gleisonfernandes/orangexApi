package com.gfsoftware.orangex.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfsoftware.orangex.entities.TweetLike;
import com.gfsoftware.orangex.entities.Comment;
import com.gfsoftware.orangex.entities.Tweet;
import com.gfsoftware.orangex.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="tb_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "email", unique = true)
	private String email;
	private String password;
	private String name;
	@Column(name = "nickname", unique = true)
	private String nickname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'Z'", timezone = "GMT")
	private LocalDate dateofbirth;
	@Column(columnDefinition = "TEXT")
	private String profileImage;
	@Column(columnDefinition = "TEXT")
	private String coverImage;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'Z'", timezone = "GMT")
	private LocalDate createdAt;
	private String bio;
	private String city;
	private String site;
	private Integer following;
	private Integer followers;
	private String roles;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Tweet> tweets = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.user")
	private Set<TweetLike> likes = new HashSet<>();
	
}
