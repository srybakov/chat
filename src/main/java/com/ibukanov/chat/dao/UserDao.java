package com.ibukanov.chat.dao;

import com.ibukanov.chat.model.User;

public interface UserDao {
    User findByLogin(String login);
    User findByNickname(String login);
	void save(User user);
    void update(User user);
}
