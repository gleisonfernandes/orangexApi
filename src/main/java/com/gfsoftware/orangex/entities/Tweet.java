package com.gfsoftware.orangex.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_tweet")
public class Tweet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private LocalDate date;
    private String description;
    private Integer likes;
    private Integer retweets;
    private String profileimage;
    
    @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    
    
	@OneToMany(mappedBy = "tweet")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
    
	public Tweet() {
	}

	public Tweet(Long id, LocalDate date, String description, Integer likes, Integer retweets, String profileimage, User user) {
		this.id = id;
		this.date = date;
		this.description = description;
		this.likes = likes;
		this.retweets = retweets;
		this.profileimage = profileimage;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getRetweets() {
		return retweets;
	}

	public void setRetweets(Integer retweets) {
		this.retweets = retweets;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
