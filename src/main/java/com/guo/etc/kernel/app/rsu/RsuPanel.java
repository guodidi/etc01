package com.guo.etc.kernel.app.rsu;

import com.guo.etc.kernel.app.base.BasePanel;
import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.service.RsuService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class RsuPanel extends BasePanel {
    
    private ApplicationContext context = null;
    private static RsuPanel rsuPanel = null;
    
    private RsuPanel(ApplicationContext context) {
        super();
        this.context = context;
        this.setVisible(true);
    }
    
    public static RsuPanel getInstance(ApplicationContext context) {
        //if (rsuPanel == null) {
            rsuPanel = new RsuPanel(context);
        //}
        return rsuPanel;
    }

    
    
    
    @Override
    public String[] tableHeader() {
        return new String[] {"序号","RSU编号","RSU名称","RSU位置"};
    }

    @Override
    public String[][] tableData() {
        RsuService rsuService = context.getBean(RsuService.class);
        List<Rsu> rsuList = rsuService.findAllRsu();
        if (rsuList != null) {
            String[][] rsus = new String[rsuList.size()][4];
            int i = 0;
            for (Rsu rsu : rsuList) {
                String[] temp = new String[] {String.valueOf(rsu.getId()),rsu.getRsuId(),rsu.getRsuName(),rsu.getRsuSite()};
                rsus[i++] = temp;
            }
            return rsus;
        }
        return null;
    }

    @Override
    public JPanel setButtonJPanel() {
        return new RsuMiddlePanel(context);
    }
}
