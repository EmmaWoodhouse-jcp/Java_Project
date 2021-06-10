package ManageMent;

import com.jcp.domain.Book;
import com.jcp.service.BookService.BookServiceDelete;
import com.jcp.service.BookService.BookServiceInput;
import com.jcp.service.BookService.BookServiceUpdate;
import com.jcp.service.DaoService.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookManagement extends JFrame implements ActionListener{
    private JButton Binput;
    private JButton Bupdate;
    private JButton Bdelete;
    private JButton Bquery;
    private JButton Bexit;
    private final int TYPE;
    private final MianInterface mm;
    public BookManagement(MianInterface mm){
        this.TYPE=mm.getTYPE();
        this.mm=mm;
        this.setTitle("图书管理");
        this.setSize(300,400);
        this.setLayout(null);
        this.setLocation(500,200);
        if(TYPE==2){
            Binput = new JButton("1. 图书信息录入");
            Binput.setBounds(40, 40, 150, 20);
            Bupdate = new JButton("2. 图书信息修改");
            Bupdate.setBounds(40, 80, 150, 20);
            Bdelete = new JButton("3. 图书信息删除");
            Bdelete.setBounds(40, 120, 150, 20);
            Bquery = new JButton("4. 图书信息查询");
            Bquery.setBounds(40, 160, 150, 20);
            Bexit = new JButton("5. 返回主菜单");
            Bexit.setBounds(40, 200, 150, 20);

            this.add(Binput);
            this.add(Bupdate);
            this.add(Bdelete);
            this.add(Bquery);
            this.add(Bexit);

            Binput.addActionListener(this);
            Bupdate.addActionListener(this);
            Bdelete.addActionListener(this);
            Bquery.addActionListener(this);
            Bexit.addActionListener(this);
        }
        else if(TYPE==1||TYPE==3){
            Bquery = new JButton("1. 图书信息查询");
            Bquery.setBounds(40, 40, 150, 20);
            Bexit = new JButton("2. 返回主菜单");
            Bexit.setBounds(40, 80, 150, 20);

            this.add(Bquery);
            this.add(Bexit);

            Bquery.addActionListener(this);
            Bexit.addActionListener(this);
        }
        this.setVisible(true);
    }
    //按钮事件(内部类)
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Binput) input();
        else if(e.getSource()==this.Bupdate) update();
        else if(e.getSource()==Bdelete) delete();
        else if(e.getSource()==this.Bquery) query();
        else if(e.getSource()==this.Bexit){
            this.dispose();
            this.mm.setVisible(true);
        }
    }
    //具体实现
    private void input(){
        JFrame Finput=new JFrame("图书添加");
        Finput.setSize(400,800);
        Finput.setLayout(new GridLayout(9,1,1,1));
        Finput.setLocation(500,200);
        //书号
        JPanel p1=new JPanel();
        JLabel labelBookID=new JLabel("书号（1~4位数字）：");
        JTextArea textBookID=new JTextArea(1,10);
        p1.add(labelBookID);
        p1.add(textBookID);
        Finput.add(p1);
        //书名
        JPanel p2=new JPanel();
        JLabel labelName=new JLabel("书名：");
        JTextArea textName=new JTextArea(1,10);
        p2.add(labelName);
        p2.add(textName);
        Finput.add(p2);
        //作者名
        JPanel p3=new JPanel();
        JLabel labelAuthor=new JLabel("作者名");
        JTextArea textAuthor=new JTextArea(1,10);
        p3.add(labelAuthor);
        p3.add(textAuthor);
        Finput.add(p3);
        //出版社
        JPanel p4=new JPanel();
        JLabel labelPublish=new JLabel("出版社：");
        JTextArea textPublish=new JTextArea(1,10);
        p4.add(labelPublish);
        p4.add(textPublish);
        Finput.add(p4);
        //藏书量
        JPanel p5=new JPanel();
        JLabel labelRemain=new JLabel("添加数量（藏书量）：");
        JTextArea textRemain=new JTextArea(1,10);
        p5.add(labelRemain);
        p5.add(textRemain);
        Finput.add(p5);
        //添加按钮
        JPanel p6=new JPanel();
        JButton BAdd=new JButton("添加");
        p6.add(BAdd);
        Finput.add(p6);

        Finput.setVisible(true);
        BAdd.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String no=textBookID.getText();                         //书号
                    String name=textName.getText();                         //书名
                    String author=textAuthor.getText();                     //作者名
                    String press=textPublish.getText();                     //出版社
                    int count=Integer.parseInt(textRemain.getText());       //藏书量
                    Book book=new Book(no,name,author,press,count);
                    String config="applicationContext.xml";
                    ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                    BookServiceInput bookServiceInput=(BookServiceInput)ctx.getBean("bookServiceInput");
                    bookServiceInput.input(book);
                }
            }
        );
    }
    private void update(){
        JFrame Fupdate=new JFrame("图书信息修改");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);
        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入书号：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);

        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String config = "applicationContext.xml";
                ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
                //通过bean标签获取对象
                BookService bookService = (BookService) ctx.getBean("bookService");
                List<Book> books = bookService.queryBook();
                boolean isSeek = false;
                Book book = null;
                for (int i = 0; i < books.size(); i++) {
                    if (textID.getText().equals(books.get(i).getNo())) {
                        isSeek = true;
                        book = books.get(i);
                        break;
                    }
                }
                if (isSeek) {
                    JComboBox<String> cmbChange = new JComboBox<String>();
                    cmbChange.addItem("-请选择修改项目-");
                    cmbChange.addItem("1.修改书名");
                    cmbChange.addItem("2.修改作者");
                    cmbChange.addItem("3.修改出版社");
                    cmbChange.addItem("4.修改藏书量");
                    Fupdate.add(cmbChange);
                    Fupdate.remove(p1);
                    Fupdate.setVisible(true);
                    //确定按钮
                    JPanel p8 = new JPanel();
                    JButton buttonChoice = new JButton("确定");
                    p8.add(buttonChoice);
                    Fupdate.add(p8);
                    Fupdate.setVisible(true);
                    Book finalBook = book;
                    buttonChoice.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==buttonChoice) {
                                int t = cmbChange.getSelectedIndex();
                                BookServiceUpdate bookServiceUpdate=(BookServiceUpdate) ctx.getBean("bookServiceUpdate");
                                bookServiceUpdate.Update(t,Fupdate, finalBook);
                            }
                        }
                    });
                }
                else JOptionPane.showMessageDialog(null,"无此书！");
            }
        });
    }
    private void delete(){
        JFrame Fupdate=new JFrame("图书信息删除");
        Fupdate.setSize(400,500);
        Fupdate.setLayout(new GridLayout(5,1,1,1));
        Fupdate.setLocation(500,200);

        JPanel p1=new JPanel();
        JLabel labelID=new JLabel("输入书号：");
        JTextArea textID=new JTextArea(1,10);
        JButton buttonSeek=new JButton("Seek");
        p1.add(labelID);p1.add(textID);
        p1.add(buttonSeek);
        Fupdate.add(p1);

        Fupdate.setVisible(true);
        buttonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no=textID.getText();
                //Spring IOC
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                //获取spring容器中的Service对象
                BookServiceDelete bookServiceDelete=(BookServiceDelete) ctx.getBean("bookServiceDelete");
                bookServiceDelete.Delete(no);
            }
        });
    }
    private void query(){
        JFrame Fquery=new JFrame("图书查询");
        Fquery.setLayout(null);
        Fquery.setSize(300,400);
        Fquery.setLocation(500,200);

        JButton buttonQNo=new JButton("1.按书号查询");
        buttonQNo.setBounds(40, 40, 150, 20);
        Fquery.add(buttonQNo);

        JButton buttonQName=new JButton("2.按书名查询");
        buttonQName.setBounds(40,80,150,20);
        Fquery.add(buttonQName);

        JButton buttonQAuthor=new JButton("3.按作者查询");
        buttonQAuthor.setBounds(40, 120, 150, 20);
        Fquery.add(buttonQAuthor);

        JButton buttonQAll=new JButton("4.*查看全部书籍");
        buttonQAll.setBounds(40, 160, 150, 20);
        Fquery.add(buttonQAll);

        JButton buttonExit=new JButton("5.返回主菜单");
        buttonExit.setBounds(40, 200, 150, 20);
        Fquery.add(buttonExit);

        Fquery.setVisible(true);
        //按照书号查找
        buttonQNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQNo=new JFrame("按书号查找");
                FQNo.setSize(500,600);
                FQNo.setLocation(500,200);
                FQNo.setLayout(null);
                JLabel labelNo=new JLabel("输入查询的书号");
                labelNo.setBounds(10,20,150,20);
                JTextArea textNo=new JTextArea(1,10);
                textNo.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQNo.add(labelNo);FQNo.add(textNo);FQNo.add(buttonSeek);
                FQNo.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //设置文本区的内容
                        String config="applicationContext.xml";
                        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                        BookService bookService=(BookService) ctx.getBean("bookService");
                        List<Book> books=bookService.queryBook();

                        String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                        Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                        boolean isSeeked=false;
                        int d=0;
                        for(int i=0;i<books.size();i++){
                            if(textNo.getText().equals(String.valueOf(books.get(i).getNo()))) {
                                isSeeked=true;
                                tableDate[d][0] = books.get(i).getNo();
                                tableDate[d][1] = books.get(i).getName();
                                tableDate[d][2] = books.get(i).getAuthor();
                                tableDate[d][3] = books.get(i).getPress();
                                tableDate[d][4] = books.get(i).getCount();
                                d++;
                            }
                        }
                        if(isSeeked) {
                            JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                            JTable tableBook = new JTable(tableDate, columnTitle);
                            tableBook.setEnabled(false);
                            JScrollPane p1 = new JScrollPane(tableBook);
                            p1.setBounds(0, 60, 470, 450);
                            FQNo.add(p1);
                            FQNo.setVisible(true);
                        }
                        else JOptionPane.showMessageDialog(null,"无该书号的书籍");
                    }
                });
            }
        });
        //按照书名查找
        buttonQName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQName=new JFrame("按书名查找");
                FQName.setSize(500,600);
                FQName.setLocation(500,200);
                FQName.setLayout(null);
                JLabel labelName=new JLabel("输入查询的书名");
                labelName.setBounds(10,20,150,20);
                JTextArea textName=new JTextArea(1,10);
                textName.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQName.add(labelName);FQName.add(textName);FQName.add(buttonSeek);
                FQName.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //设置文本区的内容
                        String config="applicationContext.xml";
                        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                        BookService bookService=(BookService) ctx.getBean("bookService");
                        List<Book> books=bookService.queryBook();

                        String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                        Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                        boolean isSeeked=false;
                        int d=0;
                        for(int i=0;i<books.size();i++){
                            if(textName.getText().equals(String.valueOf(books.get(i).getName()))) {
                                isSeeked=true;
                                tableDate[d][0] = books.get(i).getNo();
                                tableDate[d][1] = books.get(i).getName();
                                tableDate[d][2] = books.get(i).getAuthor();
                                tableDate[d][3] = books.get(i).getPress();
                                tableDate[d][4] = books.get(i).getCount();
                                d++;
                            }
                        }
                        if(isSeeked) {
                            JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                            JTable tableBook = new JTable(tableDate, columnTitle);
                            tableBook.setEnabled(false);
                            JScrollPane p1 = new JScrollPane(tableBook);
                            p1.setBounds(0, 60, 470, 450);
                            FQName.add(p1);
                            FQName.setVisible(true);
                        }
                        else JOptionPane.showMessageDialog(null,"无该书名的书籍");
                    }
                });
            }
        });
        //按照作者查找
        buttonQAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //面板处理
                JFrame FQAuthor=new JFrame("按作者查找");
                FQAuthor.setSize(500,600);
                FQAuthor.setLocation(500,200);
                FQAuthor.setLayout(null);
                JLabel labelAuthor=new JLabel("输入查询的作者");
                labelAuthor.setBounds(10,20,150,20);
                JTextArea textAuthor=new JTextArea(1,10);
                textAuthor.setBounds(100, 20, 150, 20);
                JButton buttonSeek=new JButton("Seek");
                buttonSeek.setBounds(270,20,150,20);
                FQAuthor.add(labelAuthor);FQAuthor.add(textAuthor);FQAuthor.add(buttonSeek);
                FQAuthor.setVisible(true);

                buttonSeek.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //设置文本区的内容
                        String config="applicationContext.xml";
                        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                        BookService bookService=(BookService) ctx.getBean("bookService");
                        List<Book> books=bookService.queryBook();

                        String[] columnTitle= new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                        Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                        boolean isSeeked=false;
                        int d=0;
                        for(int i=0;i<books.size();i++){
                            if(textAuthor.getText().equals(String.valueOf(books.get(i).getAuthor()))) {
                                isSeeked=true;
                                tableDate[d][0] = books.get(i).getNo();
                                tableDate[d][1] = books.get(i).getName();
                                tableDate[d][2] = books.get(i).getAuthor();
                                tableDate[d][3] = books.get(i).getPress();
                                tableDate[d][4] = books.get(i).getCount();
                                d++;
                            }
                        }
                        if(isSeeked) {
                            JOptionPane.showMessageDialog(null,"共找到"+d+"本");
                            JTable tableBook = new JTable(tableDate, columnTitle);
                            tableBook.setEnabled(false);
                            JScrollPane p1 = new JScrollPane(tableBook);
                            p1.setBounds(0, 60, 470, 450);
                            FQAuthor.add(p1);
                            FQAuthor.setVisible(true);
                        }
                        else JOptionPane.showMessageDialog(null,"无该作者的书籍");
                    }
                });
            }
        });
        //查看全部
        buttonQAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame FQAll=new JFrame("全部书籍");
                FQAll.setSize(500,600);
                FQAll.setLocation(500,200);
                FQAll.setLayout(null);
                //设置文本区的内容
                String config="applicationContext.xml";
                ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
                BookService bookService=(BookService) ctx.getBean("bookService");
                List<Book> books=bookService.queryBook();

                if(books.size()>0) {
                    String[] columnTitle = new String[]{"书号", "书名", "作者", "出版社", "藏书量"};
                    Object[][] tableDate = new Object[100][5];              //书的最大数量在这里设置
                    int i;
                    for (i = 0; i < books.size(); i++) {
                        tableDate[i][0] = books.get(i).getNo();
                        tableDate[i][1] = books.get(i).getName();
                        tableDate[i][2] = books.get(i).getAuthor();
                        tableDate[i][3] = books.get(i).getPress();
                        tableDate[i][4] = books.get(i).getCount();
                    }
                    JOptionPane.showMessageDialog(null, "共找到" + i + "本");
                    JTable tableBook = new JTable(tableDate, columnTitle);
                    tableBook.setEnabled(false);
                    JScrollPane p1 = new JScrollPane(tableBook);
                    p1.setBounds(0, 60, 470, 450);
                    FQAll.add(p1);
                    FQAll.setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "库存无书");
            }
        });
        //退出按钮
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fquery.dispose();
            }
        });
    }
}
