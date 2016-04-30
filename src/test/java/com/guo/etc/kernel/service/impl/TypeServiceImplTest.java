package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/4/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TypeServiceImplTest {

    @Autowired
    TypeService typeService;

    @Test
    public void testFindByHql() throws Exception {
        System.out.println("- - - start - - - ");
        typeService.findTypeByHql("小型车");

        System.out.println("- - - end - - - - ");
        typeService.findTypeByHql("大型车");
    }
}
