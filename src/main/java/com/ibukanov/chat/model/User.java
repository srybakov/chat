package com.ibukanov.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false, unique = true)
	@Email
	@NotNull
	private String email;

	@Column(nullable = false)
	@NotNull
	private String password;

    public static User of(String fullName, String nickname, String login, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setNickname(nickname);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
