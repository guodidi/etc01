package com.guo.etc.kernel.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/25.
 */
public class SolveDate {


        public static String[] getInfo(String sourceStr){
            //定义正则匹配
            Pattern pattern = Pattern.compile("#");
            String[] result = null;
            if (sourceStr.startsWith("FFFF")&&sourceStr.endsWith("FF")){
                String subStr = sourceStr.substring(4,sourceStr.length()-2);
                result = pattern.split(subStr);
            }
            return result;
        }





















    /*
    * 若让自己采用传输格式，我可以用以下的办法，分分钟解析完毕
    * 明天要完成的就是，在接收到这一条信息的时候，然后解析它，并显示到文字输入区域
    * */
/*    public static void main(String[] args) {
        //System.out.println("HelloWorld".length());

        Pattern pattern = Pattern.compile("#");
        String s = "FFFFss#jk#45#852#lkd#guoyao#郭垚FF";
*//*        String m =String.valueOf(new Date());
        System.out.println("=================================");
        System.out.println(m.length());
        System.out.println(m);
        System.out.println("==================================");*//*
        if (s.startsWith("FFFF")&&s.endsWith("FF")){
            String subStr = s.substring(4,s.length()-2);
            String[] sp = pattern.split(subStr);
            for (String p :sp){
                System.out.println(p);
            }
        }

    }*/
}


