package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.RecordDao;
import com.guo.etc.kernel.model.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Repository(value = "recordDao")
public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {
    @Override
    public boolean addRecord(Record record) {
        super.save(record);
        return false;
    }

    @Override
    public boolean deleteRecord(Long id) {
        Record record = super.findById(Record.class, id);
        if (record == null) {
            System.out.println("所查询的Record在数据库中不存在： "+id);
            return false;
        }
        super.delete(record);
        return true;
    }

    @Override
    public boolean updateRecord(Record record) {

/* 若采用以下的方法，则会造成Error中：a different object with the same identifier value was already associated with the session
  Record record1 = super.findById(Record.class,record.getId());
        if (record1 == null) {
            System.out.println("所查询的Record在数据库中不存在： "+record.getId());
            return false;
        }*/

        super.merge(record);
        return true;
    }

    @Override
    public Record findRecordById(Long id) {
        return super.findById(Record.class, id);
    }

    @Override
    public List<Record> findAllRecord() {
        return super.findAllEntity(Record.class);
    }
}
