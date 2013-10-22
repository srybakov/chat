package com.ibukanov.chat.dao.impl;

import com.ibukanov.chat.dao.RecordDao;
import com.ibukanov.chat.model.Record;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordDaoImpl implements RecordDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Record> getLastRecords(int count) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select r from Record r order by r.date desc ");
        if (count != 0) {
            query.setMaxResults(count);
        }
        return (List<Record>) query.list();
    }

    @Override
    public void save(Record record) {
        sessionFactory.getCurrentSession().save(record);
    }
}
