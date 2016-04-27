package tcpSocket;

import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/27.
 */
public class DecodeData {


    /*
    * 判断接收到的数据的BCC是不是一致的
    * */
    public static boolean adjustBCC(String sourceStr) {
        //获取BCC的值
        String BCC = sourceStr.substring(sourceStr.length()-2);
        byte[] bytes = sourceStr.getBytes();
        byte tempStr = bytes[0];
        for (int i=1;i<bytes.length-2;i++){
            tempStr= (byte) (tempStr^bytes[i]);
        }
        //将Byte转为String，并只取后两位
        String resultStr = (Integer.toHexString(tempStr)).substring(6);

        if ((resultStr.toLowerCase()).equals(BCC.toLowerCase())){
            System.out.println("字符串验证成功！！");
            return true;
        }
        System.out.println("BCC : "+BCC);
        System.out.println("resultStr : "+resultStr);
        System.out.println("字符串验证失败！！");
        return false;
    }


    //判断输入的语句是否符合句式
    public static boolean adjustFormat(String sourceStr){
        if (sourceStr.startsWith("FFFF")&&sourceStr.endsWith("FF")&&sourceStr.contains("#")){
            System.out.println("格式符合");
            return true;
        }
        System.out.println("格式不符合要求");
        return false;
    }

    public static String[] getDataInfo(String sourceStr) {
        Pattern pattern = Pattern.compile("#");
        String subStr = sourceStr.substring(4,sourceStr.length()-2);
        return pattern.split(subStr);
    }


    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getTime());
        

    }
}
