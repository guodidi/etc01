package com.guo.etc.kernel.service;

import com.guo.etc.kernel.model.Record;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface RecordService {

    //增删改查
    //我不是王毛
    public boolean addRecord(Record record);

    public boolean deleteRecord(Long id);

    public boolean updateRecord(Record record);

    public Record findRecordById(Long id);

    public List<Record> findAllRecord();


}
