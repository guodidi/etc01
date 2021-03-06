package com.guo.etc.kernel.app.client.simulate;

import com.guo.etc.kernel.model.Record;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.RecordService;
import com.guo.etc.kernel.service.TypeService;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/29.
 */
public class DecodeData {

    private static ApplicationContext context = null;
    private static final String s = null;




    //判断格式是否正确，即判断头和尾和#
    public static boolean adjustFormat(String sourceStr) {
        if (sourceStr.startsWith("FFFF")&&sourceStr.endsWith("FF")&&sourceStr.contains("#")){
            return true;
        }
        return false;
    }

    private static boolean adjustBCC(String sourceStr) {
        String BCC = sourceStr.substring(sourceStr.length()-2);
        byte[] bytes = sourceStr.substring(0,sourceStr.length()-2).getBytes();
        byte flag = bytes[0];
        for (int i=1;i<bytes.length;i++){
            flag = (byte) (flag^bytes[i]);
        }
        String str = (Integer.toHexString(flag));
        String testBCC = null;
        if (str.length()==1){
            testBCC = "0"+str;
        }else {
            testBCC = str.substring(str.length()-2);
        }
        if (BCC.equals(testBCC)){
            return true;
        }
        return false;
    }

    //解析得到相应的数据
    public static String[] getInfo(String sourceStr){
        //定义正则匹配
        Pattern pattern = Pattern.compile("#");
        String[] result = null;
        String subStr = sourceStr.substring(0,sourceStr.length()-2);
        result = pattern.split(subStr);
        return result;
    }

    //得到相应的数据之后
    public static boolean adjust(String sourceStr ) {
        String subStr = sourceStr.substring(4,sourceStr.length()-2);
        if (adjustBCC(subStr)) {
            String[] strings = getInfo(subStr);
            System.out.println("- - - - - - - - - - - - - - - - -");
            for (String s:strings){
                System.out.println(s);
            }
            System.out.println("- - - - - - - - - - - - - - - - -");

            if (strings.length == 5) {
                System.out.println("我要开始验证OBU，车牌号码，车辆类型的关系了");
                //TODO 判断OBU，车牌号码，车辆类型完全一样，正常收费
                if (adjustInfo(strings[0],strings[1],strings[2])){
                    System.out.println("卧槽，对了，所有的信息验证成功");
                    //TODO 将该条记录插入到数据库之中
                    insertMatchSuccessInfo(strings);
                } else {
                    //TODO 判断如果不一致，说明设备不匹配
                    insertDeviceDismatch(strings);
                    System.out.println("数据不一致验证失败");
                }
            } else {
                //如果只有车牌号码
                insertNoDeviceInfo(strings);
                System.out.println("卧槽，这不对啊,信息不足呀");
            }
            //得到数据，解析完成调用判断车牌号码，Obu，
        } else {
            System.out.println("BCC验证不正确");
        }
        return false;
    }



    //仅仅是为了将ApplicationContext传入而已
    public static boolean setContext(ApplicationContext applicationContext) {
        context = applicationContext;
        System.out.println(context);
        return true;
    }

    //验证得到的字段是否一致
    public static boolean adjustInfo(String vehicleId,String vehicleType,String obuMac) {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        boolean flag = vehicleService.findByHql(vehicleId,vehicleType,obuMac);
        System.out.println("是否找到了相应的答案呢！！！！"+flag);
        if (flag == true){
            return true;
        }
        return false;
    }

    private static void insertNoDeviceInfo(String[] strings) {
        RecordService recordService = (RecordService)context.getBean(RecordService.class);
        Record record = new Record();


        try {
            record.setVehicleId(strings[0]);
            record.setRsuId(strings[2]);
            record.setRoadId(strings[3]);
        }catch (ArrayIndexOutOfBoundsException e){
            record.setVehicleId(strings[0]);
            record.setRsuId(strings[1]);
            record.setRoadId(strings[2]);
        }
        record.setFee(0L);
        record.setTradeStatus("交易失败，车上无OBU");
        record.setTradeTime(new Timestamp(System.currentTimeMillis()));
        recordService.addRecord(record);
    }

    private static void insertDeviceDismatch(String[] strings) {
        RecordService recordService = (RecordService)context.getBean(RecordService.class);
        Record record = new Record();
        record.setVehicleId(strings[0]);
        record.setVehicleType(strings[1]);
        record.setRsuId(strings[3]);
        record.setRoadId(strings[4]);
        record.setFee(0L);
        record.setTradeStatus("交易失败，设备不匹配");
        record.setTradeTime(new Timestamp(System.currentTimeMillis()));
        recordService.addRecord(record);
    }
    //取得相应的车型对应的价格
    private static void insertMatchSuccessInfo(String[] strings) {
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        RecordService recordService = (RecordService)context.getBean(RecordService.class);
        int fee = 0;
        VehicleType vehicleType = typeService.findTypeByHql(strings[1]);
        if (vehicleType != null) {
            fee = vehicleType.getFee();
            System.out.println("Fee : "+fee);
        }

        Record record = new Record();
        record.setVehicleId(strings[0]);
        record.setVehicleType(strings[1]);
        record.setRsuId(strings[3]);
        record.setRoadId(strings[4]);
        record.setFee((long) fee);
        record.setTradeStatus("交易成功");
        record.setTradeTime(new Timestamp(System.currentTimeMillis()));
        recordService.addRecord(record);
    }

}
