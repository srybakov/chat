package com.ibukanov.chat.service.impl;

import com.ibukanov.chat.dao.UserDao;
import com.ibukanov.chat.model.User;
import com.ibukanov.chat.service.UserService;
import com.ibukanov.chat.service.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

    @Override
    public void change(User user, String nickname, String password) {
        if (userDao.findByNickname(nickname) != null) {
            throw new DuplicateEntityException(User.class, "nickname", nickname);
        } else {
            user.setNickname(nickname);
            user.setPassword(password);
            userDao.update(user);
        }
    }

    @Override
	public void save(User user) {
        if (userDao.findByLogin(user.getLogin()) != null) {
            throw new DuplicateEntityException(User.class, "login", user.getLogin());
        } else {
            userDao.save(user);
        }
	}

	@Override
	@Transactional(readOnly = true)
	public User findByLogin(String login) {
		return userDao.findByLogin(login);
	}
}
