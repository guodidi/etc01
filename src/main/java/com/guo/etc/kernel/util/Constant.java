package com.guo.etc.kernel.util;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface Constant {

    //定义窗体大小
    interface WindowSize{
        int width = 1000;
        int height = 700;
    }

    //定义固定对话框大小
    interface ConfirmDialogSize{
        int width = 900;
        int height = 650;
    }

    //定义操作对话框大小
    interface OperateDialogSize{
        int width = 300;
        int height = 500;
    }

    //定义主窗体上的工具按钮的标题
    interface TopCommonName {
        String buttonSimulate = "模拟收费";
        String buttonTotal = "该月总数统计";
        String buttonTypeFee = "类型费用管理";
        String buttonCar = "车辆管理";
        String buttonExit = "退出";
    }


}
