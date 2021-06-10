import ManageMent.MianInterface;
import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame implements ActionListener {
    //实现登录。
    private JTextArea text_id;
    private JTextArea text_password;
    private JButton Button_login;
    //GUI
    public Login() {
        this.setTitle("图书管理系统——Login");
        this.setLayout(new GridLayout(3,1,1,1));
        this.setSize(400,300);
        this.setLocation(500,200);
        //账号
        JPanel pid = new JPanel();
        JLabel lid = new JLabel("账号：");
        text_id=new JTextArea(1,14);
        text_id.setBounds(10,10,10,10);
        pid.add(lid);
        pid.add(text_id);
        this.add(pid);
        //密码
        JPanel ppassword = new JPanel();
        JLabel lpassword = new JLabel("密码：");
        text_password=new JTextArea(1,14);
        text_password.setBounds(10,10,10,10);
        ppassword.add(lpassword);
        ppassword.add(text_password);
        this.add(ppassword);
        //登录
        JPanel PBlogin = new JPanel();
        Button_login=new JButton("登录");
        PBlogin.add(Button_login);
        this.add(PBlogin);
        //Login按钮绑定监听器
        Button_login.addActionListener(this);
        //显示登录界面
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Button_login){
            //准备工作：把需要遍历的和文本框中的内容获取
            String textAreaID=text_id.getText();
            String textAreaPassword=text_password.getText();
            //获取list
            String config="applicationContext.xml";
            ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
            UserService userService=(UserService) ctx.getBean("userService");
            List<User> users=userService.queryUsers();

            boolean isSeeked=false;
            for(int i=0;i<users.size();i++){
                if(textAreaID.equals(String.valueOf(users.get(i).getID()))){       //账户正确
                    isSeeked=true;
                    if(textAreaPassword.equals(String.valueOf(users.get(i).getPassword()))){
                        MianInterface mianInterface=new MianInterface(users.get(i).getType(),users.get(i).getID());
                        this.setVisible(false);
                    }
                    else JOptionPane.showMessageDialog(null,"密码错误！");
                }
            }
            if(!isSeeked) JOptionPane.showMessageDialog(null,"账号不存在");
        }
    }
    public static void main(String[] args) {
        Login L= new Login();
    }
}
