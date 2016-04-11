package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.RecordDao;
import com.guo.etc.kernel.model.Record;
import com.guo.etc.kernel.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Transactional
@Service(value = "recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public boolean addRecord(Record record) {
        recordDao.addRecord(record);
        return false;
    }

    @Override
    public boolean deleteRecord(Long id) {
        recordDao.deleteRecord(id);
        return false;
    }

    @Override
    public boolean updateRecord(Record record) {
        recordDao.updateRecord(record);
        return false;
    }

    @Override
    public Record findRecordById(Long id) {
        return recordDao.findRecordById(id);
    }

    @Override
    public List<Record> findAllRecord() {
        return recordDao.findAllRecord();
    }
}
