package ManageMent;
import com.jcp.domain.Book;
import com.jcp.domain.Circulation;
import com.jcp.domain.User;
import com.jcp.service.CirculationService.CirculationServiceLendBook;
import com.jcp.service.CirculationService.CirculationServiceReturnBook;
import com.jcp.service.DaoService.CirculationService;
import com.jcp.service.UserService.UserServiceInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CirculationManagement extends JFrame implements ActionListener{
    private final JButton BlendBook;
    private final JButton BreturnBook;
    private final JButton Bquery;
    private final JButton Bexit;
    private final int TYPE;
    private final int ID;
    private final ManageMent.MianInterface mm;
    public CirculationManagement(ManageMent.MianInterface mm,int ID){
        this.TYPE=mm.getTYPE();
        this.ID=ID;
        this.mm=mm;
        this.setTitle("图书流通管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);

        BlendBook=new JButton("1. 借书处理");
        BlendBook.setBounds(40,40,150,20);
        BreturnBook=new JButton("2. 还书处理");
        BreturnBook.setBounds(40,80,150,20);
        Bquery=new JButton("3. 借阅信息查询");
        Bquery.setBounds(40,120,150,20);
        Bexit=new JButton("4. 返回主菜单");
        Bexit.setBounds(40,160,150,20);

        this.add(BlendBook);
        this.add(BreturnBook);
        this.add(Bquery);
        this.add(Bexit);

        BlendBook.addActionListener(this);
        BreturnBook.addActionListener(this);
        Bquery.addActionListener(this);
        Bexit.addActionListener(this);
        this.setVisible(true);
    }
    //按钮事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.BlendBook) lendBook();
        else if(e.getSource()==BreturnBook) returnBook();
        else if(e.getSource()==Bquery) query();
        else if(e.getSource()==this.Bexit){
            this.dispose();
            this.mm.setVisible(true);
        }
    }

    private void lendBook() {
        JFrame FlendBook=new JFrame("借书处理");
        FlendBook.setSize(400,500);
        FlendBook.setLayout(new GridLayout(5,1,1,1));
        FlendBook.setLocation(500,200);
        //借阅人
        JPanel p1=new JPanel();
        JLabel labelUser=new JLabel("借阅人:");
        JTextArea textUser=new JTextArea(1,10);
        p1.add(labelUser);
        p1.add(textUser);
        FlendBook.add(p1);
        //借阅书
        JPanel p2=new JPanel();
        JLabel labelBook=new JLabel("所借书:");
        JTextArea textBook=new JTextArea(1,10);
        p2.add(labelBook);
        p2.add(textBook);
        FlendBook.add(p2);
        //处理日期
        JPanel p3=new JPanel();
        JLabel labelYear=new JLabel("年:");
        JTextArea textYear=new JTextArea(1,4);
        p3.add(labelYear);p3.add(textYear);
        JLabel labelMonth=new JLabel("月:");
        JTextArea textMonth=new JTextArea(1,2);
        p3.add(labelMonth);p3.add(textMonth);
        JLabel labelDay=new JLabel("日:");
        JTextArea textDay=new JTextArea(1,2);
        p3.add(labelDay);p3.add(textDay);
        FlendBook.add(p3);

        JPanel p4=new JPanel();
        JButton buttonDetermine=new JButton("处理");
        p4.add(buttonDetermine);
        FlendBook.add(p4);
        buttonDetermine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name=textUser.getText();
                String no=textBook.getText();
                int year=Integer.parseInt(textYear.getText());
                int month=Integer.parseInt(textMonth.getText());
                int day=Integer.parseInt(textDay.getText());
                //Spring IOC
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //获取spring容器中的Service对象
                CirculationServiceLendBook circulationServiceLendBook=(CirculationServiceLendBook) ctx.getBean("circulationServiceLendBook");
                circulationServiceLendBook.Lend(name,no,year,month,day,ID);
            }
        });

        FlendBook.setVisible(true);
    }
    private void returnBook() {
        JFrame FreturnBook=new JFrame("还书处理");
        FreturnBook.setSize(400,500);
        FreturnBook.setLayout(new GridLayout(5,1,1,1));
        FreturnBook.setLocation(500,200);
        //还书人
        JPanel p1=new JPanel();
        JLabel labelUser=new JLabel("借阅人:");
        JTextArea textUser=new JTextArea(1,10);
        p1.add(labelUser);
        p1.add(textUser);
        FreturnBook.add(p1);
        //借阅书
        JPanel p2=new JPanel();
        JLabel labelBook=new JLabel("所还书:");
        JTextArea textBook=new JTextArea(1,10);
        p2.add(labelBook);
        p2.add(textBook);
        FreturnBook.add(p2);
        //处理日期
        JPanel p3=new JPanel();
        JLabel labelYear=new JLabel("年:");
        JTextArea textYear=new JTextArea(1,4);
        p3.add(labelYear);p3.add(textYear);
        JLabel labelMonth=new JLabel("月:");
        JTextArea textMonth=new JTextArea(1,2);
        p3.add(labelMonth);p3.add(textMonth);
        JLabel labelDay=new JLabel("日:");
        JTextArea textDay=new JTextArea(1,2);
        p3.add(labelDay);p3.add(textDay);
        FreturnBook.add(p3);

        JPanel p4=new JPanel();
        JButton buttonDetermine=new JButton("处理");
        p4.add(buttonDetermine);
        FreturnBook.add(p4);
        buttonDetermine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name=textUser.getText();
                String no=textBook.getText();
                int year=Integer.parseInt(textYear.getText());
                int month=Integer.parseInt(textMonth.getText());
                int day=Integer.parseInt(textDay.getText());
                //Spring IOC
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //获取spring容器中的Service对象
                CirculationServiceReturnBook circulationServiceReturnBook=(CirculationServiceReturnBook) ctx.getBean("circulationServiceReturnBook");
                circulationServiceReturnBook.Return(name,no,year,month,day,ID);
            }
        });

        FreturnBook.setVisible(true);

    }
    private void query() {
        JFrame Fquery=new JFrame("查看流通记录");
        Fquery.setLayout(null);
        Fquery.setSize(630,500);
        Fquery.setLocation(500,200);

        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        CirculationService circulationService=(CirculationService) ctx.getBean("circulationService");
        java.util.List<Circulation> circulations=circulationService.queryCirculation();

        if(circulations.size()>0) {
            String[] columnTitle = new String[]{"流水号", "用户名", "书号", "日期", "借还书类型","操作人"};
            Object[][] tableDate = new Object[100][6];              //书的最大数量在这里设置
            int i;
            for (i = 0; i < circulations.size(); i++) {
                tableDate[i][0] = String.valueOf(circulations.get(i).getSerialNo());
                tableDate[i][1] = circulations.get(i).getName();
                tableDate[i][2] = String.valueOf(circulations.get(i).getNo());
                tableDate[i][3] = circulations.get(i).getYear()+"-"+circulations.get(i).getMonth()+"-"+circulations.get(i).getDay();
                if(circulations.get(i).getType()==1) {
                    tableDate[i][4]= "借书";
                }
                else {
                    tableDate[i][4]= "还书";
                }
                tableDate[i][5] = String.valueOf(circulations.get(i).getOperator());
            }
            JOptionPane.showMessageDialog(null, "共找到" + i + "条记录");
            JTable tableBook = new JTable(tableDate, columnTitle);
            tableBook.setEnabled(false);
            JScrollPane p1 = new JScrollPane(tableBook);
            p1.setBounds(0, 0, 600, 500);
            Fquery.add(p1);
            Fquery.setVisible(true);
        }
        else JOptionPane.showMessageDialog(null, "库存无书");
    }
}
