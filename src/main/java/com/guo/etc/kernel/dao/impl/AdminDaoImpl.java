package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.AdminDao;
import com.guo.etc.kernel.model.Admin;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/16.
 */
@Repository(value = "adminDao")
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
    @Override
    public boolean adjustAdmin(String[] names) {
        if (names.length == 2){
            String hql = "from "+ Admin.class.getName()+" a where a.name = '"+names[0]+"'"+" and a.password = '"+names[1]+"'";
            System.out.println("hql:-------------------------");
            System.out.println(hql);
            System.out.println("hql:-----------------------------");
            Admin admin = super.findBySql(hql);
            if (admin != null){
                return true;
            }
        }
        return false;








        /*
        *
        *
        *
        *         String hql = "from "+VehicleType.class.getName()+" t where t.type = '"+vehicleType+"'";
        System.out.println("hql is : "+hql);
        VehicleType vehicleType1 = super.findBySql(hql);
        if (vehicleType1 != null) {
            return vehicleType1;
        }
        System.out.println("查找失败");
        return null;
        *
        *
        *
        * */

    }
}
