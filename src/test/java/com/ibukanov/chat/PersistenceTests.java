package com.ibukanov.chat;

import com.ibukanov.chat.dao.RecordDao;
import com.ibukanov.chat.dao.UserDao;
import com.ibukanov.chat.model.Record;
import com.ibukanov.chat.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTests {

	@Autowired
	private RecordDao recordDao;

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	public void testUserDao() throws Exception {
		userDao.save(User.of("full name", "nickname", "login", "test@mail.com", "password"));

		User user = userDao.findByLogin("login");
		assertEquals("full name", user.getFullName());
		assertEquals("nickname", user.getNickname());
		assertEquals("login", user.getLogin());
		assertEquals("test@mail.com", user.getEmail());
		assertEquals("password", user.getPassword());

		user = userDao.findByNickname("nickname");
		assertEquals("full name", user.getFullName());
		assertEquals("nickname", user.getNickname());
		assertEquals("login", user.getLogin());
		assertEquals("test@mail.com", user.getEmail());
		assertEquals("password", user.getPassword());
	}

	@Test
	@Transactional
	public void testRecordDao() throws Exception {
		User user = User.of("full name", "nickname", "login", "test@mail.com", "password");
		userDao.save(user);
		recordDao.save(Record.of(user, "message 1"));
		recordDao.save(Record.of(user, "message 2"));
		recordDao.save(Record.of(user, "message 3"));

		List<Record> records = recordDao.getLastRecords(3);

		assertEquals(user, records.get(0).getUser());
		assertEquals(user, records.get(1).getUser());
		assertEquals(user, records.get(2).getUser());

		assertEquals("message 3", records.get(0).getMessage());
		assertEquals("message 2", records.get(1).getMessage());
		assertEquals("message 1", records.get(2).getMessage());
	}

}
