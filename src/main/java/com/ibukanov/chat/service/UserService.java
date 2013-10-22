package com.ibukanov.chat.service;

import com.ibukanov.chat.model.User;

public interface UserService {

	void change(User user, String nickname, String password);
    void save(User user);
	User findByLogin(String login);

}
