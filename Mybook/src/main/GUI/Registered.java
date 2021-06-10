import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registered extends JFrame implements ActionListener {

    //各个文本框
    private JTextArea textID;
    private JTextArea textPassword;
    private JComboBox<String> cmbType;
    private final JTextArea textName;
    private JTextArea textUnit;
    private JTextArea textTelephone;
    private JComboBox<String> cmbCan_borrow;
    private JButton register;
    //构造器
    public Registered(){
        //最外层
        this.setTitle("注册器");
        this.setLayout(new GridLayout(12,1,1,1));
        this.setSize(300,700);
        this.setLocation(500,200);

        //账户
        JPanel p1 = new JPanel();
        JLabel labelID = new JLabel("账户（1~9位数字）：");
        textID=new JTextArea(1,10);
        textID.setBounds(10,10,10,10);
        p1.add(labelID);
        p1.add(textID);
        this.add(p1);

        //密码
        JPanel p2 = new JPanel();
        JLabel labelPassword = new JLabel("密码（0~14位数字）：");
        textPassword=new JTextArea(1,10);
        textPassword.setBounds(10,10,10,10);
        p2.add(labelPassword);
        p2.add(textPassword);
        this.add(p2);

        //用户类型
        JPanel p3 = new JPanel();
        JLabel labelType = new JLabel("类型");
        cmbType=new JComboBox<String>();
        cmbType.addItem("--请选择--");
        cmbType.addItem("1.普通读者");
        cmbType.addItem("2.图书管理员");
        cmbType.addItem("3.系统管理员");
        p3.add(labelType);
        p3.add(cmbType);
        this.add(p3);

        //姓名
        JPanel p4 = new JPanel();
        JLabel labelName = new JLabel("姓名：");
        textName=new JTextArea(1,10);
        textName.setBounds(10,10,10,10);
        p4.add(labelName);
        p4.add(textName);
        this.add(p4);

        //单位
        JPanel p5 = new JPanel();
        JLabel labelUnit = new JLabel("单位：");
        textUnit=new JTextArea(1,10);
        textUnit.setBounds(10,10,10,10);
        p5.add(labelUnit);
        p5.add(textUnit);
        this.add(p5);

        //电话
        JPanel p6 = new JPanel();
        JLabel labelTelephone;
        labelTelephone = new JLabel("电话(11位数字（China）)：");
        textTelephone=new JTextArea(1,10);
        textTelephone.setBounds(10,10,10,10);
        p6.add(labelTelephone);
        p6.add(textTelephone);
        this.add(p6);

        //可借书数
        JPanel p7 = new JPanel();
        JLabel labelCan_borrow = new JLabel("数量");
        cmbCan_borrow=new JComboBox<String>();
        cmbCan_borrow.addItem("--请选择--");
        cmbCan_borrow.addItem("5（学生）");
        cmbCan_borrow.addItem("10（教工）");
        p7.add(labelCan_borrow);
        p7.add(cmbCan_borrow);
        this.add(p7);

        //注册按钮
        JPanel p8 = new JPanel();
        register=new JButton("注册");
        p8.add(register);
        this.add(p8);

        //绑定监听器
        register.addActionListener(this);
        this.setVisible(true);
    }
    //事件处理
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.register){
            int id=Integer.parseInt(textID.getText());
            String password=textPassword.getText();
            int type=cmbType.getSelectedIndex(); //1.普通读者 2.图书管理员 3.系统管理员
            String name=textName.getText();
            String unit=textUnit.getText();
            long telephone=Long.parseLong(textTelephone.getText());
            int can_borrow=cmbCan_borrow.getSelectedIndex()*5;//1.5(学生） 2.10（教工）

            User a_user=new User(id,password,type,name,unit,telephone,can_borrow);
            //Spring IOC
            String config="applicationContext.xml";
            ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
            //获取spring容器中的dao对象
            UserService userService=(UserService) ctx.getBean("userService");
            int nums=userService. addUser(a_user);
            System.out.println(nums);
        }
    }
    public static void main(String[] args) {
        Registered a= new Registered();
    }
}