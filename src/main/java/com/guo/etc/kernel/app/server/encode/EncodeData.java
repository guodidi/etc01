package com.guo.etc.kernel.app.server.encode;

/**
 * Created by Administrator on 2016/4/25.
 * 本计算机中采用的编码格式是：UTF-8
 * 可以使用输出默认编码：System.out.println(Charset.defaultCharset());
 */
public class EncodeData {

    //组合为完整的数据发送帧
    public static String combinedAllData(String ... strings) {
        String subStr = combinedData(strings);
        String BCCStr = calculateBCC(subStr);
        StringBuffer stringBuffer = new StringBuffer();

        //插入FFFF和FF标志
        stringBuffer.append("FFFF");
        stringBuffer.append(subStr);
        stringBuffer.append(BCCStr);
        stringBuffer.append("FF");

        System.out.println("完整的数据帧格式如下：");
        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

    //组合数据数据帧部分
    public static String combinedData(String ... strings) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            stringBuffer.append(string+"#");
        }
        //去掉最后一个#
        String resultStr = stringBuffer.substring(0,stringBuffer.length()-1);
        System.out.println(resultStr);
        return resultStr;
    }

    /*
    * 计算BCC的值
    * 类似于中文严的UTF-8编码的编码是：0xE4^0xB8^0xA5=F9（11111001--原码）
    * 所以计算结果应为补码即为（10000110--反码-->10000111-->补码=-7）
    * 最后结果-7
    * */
    public static String calculateBCC(String sourceStr){
        System.out.println("进入BCC运算的字符串是： "+sourceStr);
        byte[] bytes = sourceStr.getBytes();
        byte resultByte =bytes[0];

        //进行异或值的计算
        for (int i=1;i<bytes.length;i++){
            resultByte= (byte)(resultByte^bytes[i]);
        }
        String str = (Integer.toHexString(resultByte));//输出是FFFFFFF9有四个字节，正好是int的类型的全部, 00000A
        String BCC = null;
        if (str.length()==1){
            BCC = "0"+str;

        }else {
            BCC = str.substring(str.length()-2);
        }
        System.out.println(BCC);
        return BCC;
    }

//FFFFguo#yao#hui#郭#杨6aFF

    /*
    * 测试字符 严 UTF-8编码： E4B8A5 异或结果：f9
    * 测试字符 连 UTF-8编码： E8BF9E 异或结果：c9
    * 测试字符 通 UTF-8编码： E9809A 异或结果：F3
    * 测试字符字符串：我是狗\我是狗。这两句话的BCC一样，所以为什么要加密的原因
    * 推荐使用Notepad++编辑器+HEX-EDIT插件，可以简单的查看某个字符的16进制编码
    * */
    public static void main(String[] args) {
        System.out.println("=====================================");
        calculateBCC("京·F23565#大型车#10#3");
    }
}


