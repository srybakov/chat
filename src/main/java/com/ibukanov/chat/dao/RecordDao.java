package com.ibukanov.chat.dao;

import com.ibukanov.chat.model.Record;

import java.util.List;

public interface RecordDao {
    List<Record> getLastRecords(int count);
    void save(Record record);
}
