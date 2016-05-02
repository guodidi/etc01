package com.guo.etc.kernel.app.client.record;

import com.guo.etc.kernel.app.client.base.BasePanel;
import com.guo.etc.kernel.model.Record;
import com.guo.etc.kernel.service.RecordService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/5/2.
 */
public class RecordPanel extends BasePanel {



    private ApplicationContext context = null;
    private static RecordPanel recordPanel = null;

    private RecordPanel(ApplicationContext context) {
        super();
        this.context = context;
        this.setVisible(true);
    }

    public static RecordPanel getInstance(ApplicationContext context) {
        // if (recordPanel == null) {
        //倘若保留了if（record == null）才会new 一个Record那么会造成，你选择的行会一直保持着，无法自动消除
        recordPanel = new RecordPanel(context);
        //}
        return recordPanel;
    }


    @Override
    public String[] tableHeader() {
        return new String[] {"序号","车牌号码","车辆类型","RSU编号","车道","交易时间","交易状态","收费金额"};
    }

    @Override
    public String[][] tableData() {
        RecordService recordService = context.getBean(RecordService.class);
        List<Record> recordList = recordService.findAllRecord();
        int i = 0;
        if (recordList != null) {
            String[][] records = new String[recordList.size()][6];
            for (Record record : recordList) {
                String[] temp = new String[] {String.valueOf(record.getId()),record.getVehicleId(),record.getVehicleType(),record.getRsuId(),record.getRoadId(),convertTime(record.getTradeTime()),record.getTradeStatus(),adjustFee(record.getFee())};
                records[i++] = temp;
            }
            return records;
        }
        return null;
    }

    @Override
    public JPanel setButtonJPanel() {
        return new RecordMiddlePanel(context);
    }

    /**
     *将从数据库中读取的数据进行转换输出
     * @param timestamp
     * @return String
     */
    private String convertTime(Timestamp timestamp) {
        if (timestamp != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            return simpleDateFormat.format(timestamp);
        }
        return null;
    }

    private String adjustFee(Long fee) {
        if (fee == null) {
            return "0";
        }
        return String.valueOf(fee);
    }
}
