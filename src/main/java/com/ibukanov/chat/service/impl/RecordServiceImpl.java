package com.ibukanov.chat.service.impl;

import com.ibukanov.chat.dao.RecordDao;
import com.ibukanov.chat.model.Record;
import com.ibukanov.chat.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;

    @Override
	@Transactional(readOnly = true)
    public List<Record> getLastRecords(int count) {
        return recordDao.getLastRecords(count);
    }

    @Override
    public void save(Record record) {
        recordDao.save(record);
    }
}
