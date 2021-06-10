package com.jcp;

import static org.junit.Assert.assertTrue;

import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.UserServiceDelete;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //获取spring容器中的Service对象
        UserService userService=(UserService) ctx.getBean("userService");
        int nums=userService. addUser(new User(0001,"123",1,"矫春朋","黑龙江大学",10086,10));
        System.out.println(nums);
    }
    @Test
    public void test2(){    //删除
        Integer ID=1;
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //通过bean标签获取对象
        UserService userService=(UserService) ctx.getBean("userService");
        List<User> users=userService.queryUsers();

        boolean isSeek=false;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getID()==ID){
                isSeek=true;
                break;
            }
        }
        if (isSeek) {
            userService.removeUser(ID);
            JOptionPane.showMessageDialog(null, "删除成功！");
        }
        else JOptionPane.showMessageDialog(null, "不存在该用户！");
    }
    @Test//查询一个
    public void test3(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

        UserService userService=(UserService) ctx.getBean("userService");
        User user=userService.queryOneUser(653629745);
        System.out.println(user);
     }
     @Test//更改数据
    public void test4(){
         String config="applicationContext.xml";
         ApplicationContext ctx=new ClassPathXmlApplicationContext(config);

         UserService userService=(UserService) ctx.getBean("userService");
         User user=new User(653629745,"123",3,"矫朋","黑龙江大学",10086,5);
         userService.updateUser(user);
         System.out.println(user);
     }
}
