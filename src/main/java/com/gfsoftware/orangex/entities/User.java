package com.gfsoftware.orangex.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfsoftware.orangex.entities.TweetLike;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="tb_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private LocalDate dateofbirth;
	private String profileImage;
	private String coverImage;
	private LocalDate joined;
	private String bio;
	private String city;
	private String site;
	private Integer following;
	private Integer followers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Tweet> tweets = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.user")
	private Set<TweetLike> likes = new HashSet<>();
	
	public Set<TweetLike> getLikes(){
		return likes;
	}
	
	public User() {
	}

	public User(Long id, String email, String password, String name, String nickname, LocalDate dateofbirth,
			String profileImage, String coverImage, LocalDate joined, String bio, String city, String site,
			Integer following, Integer followers) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.dateofbirth = dateofbirth;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
		this.joined = joined;
		this.bio = bio;
		this.city = city;
		this.site = site;
		this.following = following;
		this.followers = followers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public LocalDate getJoined() {
		return joined;
	}

	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getFollowing() {
		return following;
	}

	public void setFollowing(Integer following) {
		this.following = following;
	}

	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	
	public List<Tweet> getTweets() {
		return tweets;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
