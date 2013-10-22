package com.ibukanov.chat.service;

import com.ibukanov.chat.model.Record;
import java.util.List;

public interface RecordService {

    List<Record> getLastRecords(int count);
    void save(Record record);

}
