package com.jcp;

import com.jcp.domain.Circulation;
import com.jcp.service.DaoService.CirculationService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CirculationTest {
    @Test
    public void test1() {
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        circulationService.addCirculation(new Circulation("1001",1002,2012,2,12,1,653629745));

    }
    @Test
    public void test2(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        List<Circulation> circulations=circulationService.queryCirculation();
        System.out.println(circulations.get(0).getOperator());
    }
}
