package com.ibukanov.chat.dao.impl;

import com.ibukanov.chat.dao.UserDao;
import com.ibukanov.chat.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createQuery("select u from User u where u.login=:login")
                .setString("login", login).uniqueResult();
    }

    @Override
    public User findByNickname(String nickname) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createQuery("select u from User u where u.nickname=:nickname")
                .setString("nickname", nickname).uniqueResult();
    }

    @Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
