package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.model.Record;
import com.guo.etc.kernel.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class RecordServiceImplTest {

    @Autowired
    RecordService recordService;

    @Test
    public void testAddRecord() throws Exception {

        Record record = new Record();
        record.setVehicleId("京·F23565");
        record.setVehicleType("重型车");
        record.setRsuId("闽-GXsHH");
        record.setRoadId("s3");
        record.setFee(100L);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        record.setTradeTime(timestamp);
        recordService.addRecord(record);

    }

    @Test
    public void test() throws Exception {
        System.out.println("hello");
    }
}
