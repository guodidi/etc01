package com.guo.etc.kernel.app.base;

public interface Constant {
    // ����ʱ��ģ��
    public static String DatePattern = "yyyy-MM-dd HH:mm";
    interface WindowSize {// ���崰���С
        int width = 850;
        int height = 650;
    }
    interface ConfirmDialogSize {// ����̶��Ի���Ĵ�С
        int height = 130;
        int width = 280;
    }
    interface OperateDialogSize {// ��������Ի���Ĵ�С
        int height = 300;
        int width = 500;
        
    }
    interface LoginSzie {// �����¼����Ĵ�С
        int height = 220;
        int width = 450;
    }
    interface TopCommonName {// �������Ϲ��߰�ť�ı���
        String buttonOut = "������Ϣ";
        String buttonIn = "�黹��Ϣ";
        String buttonUser = "��Ա����";
        String buttonDept = "���Ź���";
        String buttonAppinances = "��Ʒ����";
        String buttonAbout = "����";
        String buttonExit = "�˳�";
    }
    interface ButtonCommonName {
        // ��¼�����ϰ�ť�ı���
        String login = "��½";
        String cancel = "ȡ��";
        // ��������ı������ı�
        String addDept = "��Ӳ���";
        String updateDept = "�޸Ĳ���";
        String deleteDept = "ɾ������";
        String outAppliance = "������Ʒ";
        String inAppliance = "�黹��Ʒ";
        String deleteApplianceUser = "ɾ���黹��Ʒ";
        String addUser = "�����Ա";
        String updateUser = "�޸���Ա";
        String deleteUser = "ɾ����Ա";
        String addAppliances = "�����Ʒ";
        String updateAppliances = "�޸���Ʒ";
        String deleteAppliances = "ɾ����Ʒ";
    }
    interface Dictionary {
        Long Yes = new Long(1);// �ѹ黹
        String YesName = "��";// ����Ա
        Long No = new Long(0);// δ�黹
        String NoName = "��";// ��ͨ�û�
    }
}
