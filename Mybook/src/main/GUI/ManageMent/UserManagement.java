package ManageMent;

import ManageMent.MianInterface;
import com.jcp.domain.User;
import com.jcp.service.DaoService.UserService;
import com.jcp.service.UserService.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManagement extends JFrame implements ActionListener {
    private JButton Binput;
    private JButton Bupdate;
    private JButton Bdelete;
    private JButton Bquery;
    private JButton BchangePassword;
    private JButton Bexit;
    private JButton BqueryAll;
    private int TYPE;
    private MianInterface mm;                   //指向主界面的引用为操控主界面的显示

    //主面板增删
    public UserManagement(MianInterface mm){
        this.TYPE=mm.getTYPE();
        this.mm=mm;
        this.setTitle("用户管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);
        if(TYPE==3) {
            Binput = new JButton("1. 用户信息录入");
            Binput.setBounds(40, 40, 150, 20);
            Bupdate = new JButton("2. 用户信息修改");
            Bupdate.setBounds(40, 80, 150, 20);
            Bdelete = new JButton("3. 用户信息删除");
            Bdelete.setBounds(40, 120, 150, 20);
            Bquery = new JButton("4. 用户信息查询");
            Bquery.setBounds(40, 160, 150, 20);
            BchangePassword = new JButton("5. 用户密码修改");
            BchangePassword.setBounds(40, 200, 150, 20);
            BqueryAll = new JButton("*6. 展示用户信息库");
            BqueryAll.setBounds(40, 240, 150, 20);
            Bexit = new JButton("7. 返回主菜单");
            Bexit.setBounds(40, 280, 150, 20);

            this.add(Binput);
            this.add(Bupdate);
            this.add(Bdelete);
            this.add(Bquery);
            this.add(BchangePassword);
            this.add(BqueryAll);
            this.add(Bexit);

            Binput.addActionListener(this);
            Bupdate.addActionListener(this);
            Bdelete.addActionListener(this);
            Bquery.addActionListener(this);
            BchangePassword.addActionListener(this);
            BqueryAll.addActionListener(this);
            Bexit.addActionListener(this);
        }
        else if(TYPE==1||TYPE==2){
            BchangePassword = new JButton("1. 用户密码修改");
            BchangePassword.setBounds(40, 40, 150, 20);
            Bexit = new JButton("2. 返回主菜单");
            Bexit.setBounds(40, 80, 150, 20);
            this.add(BchangePassword);
            this.add(Bexit);

            BchangePassword.addActionListener(this);
            Bexit.addActionListener(this);
        }
        this.setVisible(true);
    }
    //按钮事件(内部类监听器)
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Binput) input();
        else if(e.getSource()==this.Bdelete) delete();
        else if(e.getSource()==this.Bupdate) update();
        else if(e.getSource()==this.Bquery) query();
        else if(e.getSource()==this.BchangePassword) changePassword();
        else if(e.getSource()==this.BqueryAll){
            if(TYPE==3) queryAll();
            else JOptionPane.showMessageDialog(null,"没有权限");
        }
        else if(e.getSource()==this.Bexit){
            this.dispose();
            this.mm.setVisible(true);
        }
    }

    //具体实现
    private void input(){
        JFrame Finput=new JFrame("用户添加");
        Finput.setSize(400,800);
        Finput.setLayout(new GridLayout(9,1,1,1));
        Finput.setLocation(500,200);
        //账户
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("账户（1~9位数字）：");
        JTextArea textID=new JTextArea(1,10);
        p1.add(labelID);
        p1.add(textID);
        Finput.add(p1);
        //密码
        JPanel p2=new JPanel();
        JLabel labelPassword=new JLabel("密码（0~14位数字）：");
        JTextArea textPassword=new JTextArea(1,10);
        p2.add(labelPassword);
        p2.add(textPassword);
        Finput.add(p2);
        //用户类型
        JPanel p3=new JPanel();
        JLabel labelType=new JLabel("类型");
        JComboBox<String> cmbType=new JComboBox<String>();
        cmbType.addItem("--请选择--");
        cmbType.addItem("1.普通读者");
        cmbType.addItem("2.图书管理员");
        cmbType.addItem("3.系统管理员");
        p3.add(labelType);
        p3.add(cmbType);
        Finput.add(p3);
        //姓名
        JPanel p4=new JPanel();
        JLabel labelName=new JLabel("姓名：");
        JTextArea textName=new JTextArea(1,10);
        p4.add(labelName);
        p4.add(textName);
        Finput.add(p4);
        //单位
        JPanel p5=new JPanel();
        JLabel labelUnit=new JLabel("单位：");
        JTextArea textUnit=new JTextArea(1,10);
        p5.add(labelUnit);
        p5.add(textUnit);
        Finput.add(p5);
        //电话
        JPanel p6=new JPanel();
        JLabel labelTelephone=new JLabel("电话(11位数字（China）)：");
        JTextArea textTelephone=new JTextArea(1,10);
        textTelephone.setBounds(10,10,10,10);
        p6.add(labelTelephone);
        p6.add(textTelephone);
        Finput.add(p6);
        //可借书数
        JPanel p7=new JPanel();
        JLabel labelCan_borrow=new JLabel("数量");
        JComboBox<String> cmbCan_borrow=new JComboBox<String>();
        cmbCan_borrow.addItem("--请选择--");
        cmbCan_borrow.addItem("5（学生）");
        cmbCan_borrow.addItem("10（教工）");
        p7.add(labelCan_borrow);
        p7.add(cmbCan_borrow);
        Finput.add(p7);
        //添加按钮
        JPanel p8=new JPanel();
        JButton BAdd=new JButton("添加");
        p8.add(BAdd);
        Finput.add(p8);
        Finput.setVisible(true);
        //添加监听器（匿名类）
        BAdd.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id=Integer.parseInt(textID.getText());
                    String password=textPassword.getText();
                    int type=cmbType.getSelectedIndex(); //1.普通读者 2.图书管理员 3.系统管理员
                    String name=textName.getText();
                    String unit=textUnit.getText();
                    long telephone=Long.parseLong(textTelephone.getText());
                    int can_borrow=cmbCan_borrow.getSelectedIndex()*5;//1.5(学生） 2.10（教工）
                    User one_user=new User(id, password, type, name, unit, telephone, can_borrow);
                    //Spring IOC
                    String config="applicationContext.xml";
                    ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                    //获取spring容器中的Service对象
                    UserServiceInput userServiceInput=(UserServiceInput) ctx.getBean("userServiceInput");
                    userServiceInput.input(one_user);
                }
            }
        );
    }
    private void delete(){
        JFrame Fdelete=new JFrame("用户信息删除");
        Fdelete.setSize(200,300);
        Fdelete.setLayout(new GridLayout(5,1,1,1));
        Fdelete.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fdelete.add(p1);

        Fdelete.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer ID=Integer.parseInt(textID.getText());
                //Spring IOC
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //获取spring容器中的Service对象
                UserServiceDelete userServiceDelete=(UserServiceDelete) ctx.getBean("userServiceDelete");
                userServiceDelete.Delete(ID);
            }
        });
    }
    private void update(){
        JFrame Fupdate=new JFrame("用户信息修改");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);

        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //通过bean标签获取对象
                UserService userService=(UserService) ctx.getBean("userService");
                List<User> users=userService.queryUsers();
                boolean isSeek=false;
                User user=null;
                for(int i=0;i<users.size();i++){
                    if(Integer.parseInt(textID.getText())==users.get(i).getID()){
                        isSeek=true;
                        user=users.get(i);
                        break;
                    }
                }
                if(isSeek){
                    JComboBox<String> cmbChange=new JComboBox<String>();
                    cmbChange.addItem("-请选择修改项目-");
                    cmbChange.addItem("1.修改密码");
                    cmbChange.addItem("2.修改用户类型");
                    cmbChange.addItem("3.修改姓名");
                    cmbChange.addItem("4.修改单位");
                    cmbChange.addItem("5.修改电话");
                    cmbChange.addItem("6.修改可借书数量");
                    Fupdate.add(cmbChange);
                    Fupdate.remove(p1);
                    Fupdate.setVisible(true);
                    //确定按钮
                    JPanel p8=new JPanel();
                    JButton buttonChoice=new JButton("确定");
                    p8.add(buttonChoice);
                    Fupdate.add(p8);
                    Fupdate.setVisible(true);
                    User finalUser = user;
                    buttonChoice.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ee) {
                            if(ee.getSource()==buttonChoice) {
                                int t = cmbChange.getSelectedIndex();
                                UserServiceUpdate userServiceUpdate=(UserServiceUpdate) ctx.getBean("userServiceUpdate");
                                userServiceUpdate.Update(t,Fupdate, finalUser);

                            }
                        }
                    });
                }
                else JOptionPane.showMessageDialog(null,"无该用户！");
            }
        });
    }
    private void query(){
        JFrame Fquery=new JFrame("用户信息查找");
        Fquery.setSize(500,600);
        Fquery.setLayout(new GridLayout(5,1,1,1));
        Fquery.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fquery.add(p1);

        JPanel p2=new JPanel();
        JTextArea textShowMessage=new JTextArea(18,25);
        //textShowMessage.setEnabled(false);
        Fquery.add(textShowMessage);

        Fquery.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Spring IOC
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //获取spring容器中的Service对象
                UserServiceSelect userServiceSelect=(UserServiceSelect) ctx.getBean("userServiceSelect");
                String ans=userServiceSelect.Select(Integer.parseInt(textID.getText()));
                textShowMessage.setText(ans);
                if(ans.equals("")) JOptionPane.showMessageDialog(null, "不存在该用户！");
            }
        });
    }
    private void changePassword(){
        JFrame FchangePassword=new JFrame("用户密码修改");
        FchangePassword.setSize(300,400);
        FchangePassword.setLayout(new GridLayout(5,1,1,1));
        FchangePassword.setLocation(500,200);
        //账号
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入账户：");
        JTextArea textID=new JTextArea(1,10);
        p1.add(labelID);p1.add(textID);
        FchangePassword.add(p1);
        //原密码
        JPanel p2=new JPanel();
        JLabel labelPassword_1=new JLabel("原密码：");
        JTextArea textPassword_1=new JTextArea(1,10);
        p2.add(labelPassword_1);p2.add(textPassword_1);
        FchangePassword.add(p2);
        //确认按钮
        JPanel p3=new JPanel();
        JButton buttonConfirm=new JButton("确认");
        p3.add(buttonConfirm);
        FchangePassword.add(p3);

        FchangePassword.setVisible(true);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //通过bean标签获取对象
                UserService userService=(UserService) ctx.getBean("userService");
                List<User> users=userService.queryUsers();
                boolean isSeek=false;
                User user=null;
                for(int i=0;i<users.size();i++){
                    if(Integer.parseInt(textID.getText())==users.get(i).getID()){
                        isSeek=true;
                        user=users.get(i);
                        break;
                    }
                }
                if(isSeek){
                    FchangePassword.remove(p1);
                    FchangePassword.remove(p2);
                    FchangePassword.remove(p3);
                    UserServiceChangePassword userServiceChangePassword=(UserServiceChangePassword) ctx.getBean("userServiceChangePassword");
                    userServiceChangePassword.UpdatePassword(FchangePassword,user);
                }
                else JOptionPane.showMessageDialog(null, "用户不存在或者密码错误！");
            }
        });
    }
    private void queryAll() {
        JFrame FqueryAll=new JFrame("全部用户");
        FqueryAll.setSize(1000,600);
        FqueryAll.setLayout(new GridLayout(1,1,1,1));
        FqueryAll.setLocation(500,200);

        JTextArea textAll = new JTextArea(100,50);
        JScrollPane scroll = new JScrollPane(textAll);
        FqueryAll.add(scroll);

        //service
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        UserServiceSelectAll userServiceSelectAll= (UserServiceSelectAll) ctx.getBean("userServiceSelectAll");
        String ans=userServiceSelectAll.SelectAll();
        textAll.setText(ans);

        textAll.setEnabled(false);
        FqueryAll.setVisible(true);
    }

}
