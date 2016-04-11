package com.guo.etc.kernel.util;

import javax.swing.*;

/**
 * Created by Administrator on 2016/3/24.
 */
public class CommonUtil {

    //获取图片
    public static ImageIcon getImageIcon(Class<?> clazz, String path) {
        return new ImageIcon(clazz.getResource(path));
    }



}
