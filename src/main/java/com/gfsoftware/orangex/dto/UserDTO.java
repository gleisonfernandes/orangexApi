package com.gfsoftware.orangex.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String name;
	private String nickname;
	private LocalDate dateofbirth;
	private String profileImage;
	private String coverImage;
	private LocalDate createdAt;
	private String bio;
	private String city;
	private String site;
	private Integer following;
	private Integer followers;
	private String roles;
}
