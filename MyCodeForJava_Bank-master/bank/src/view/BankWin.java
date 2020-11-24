package view;

import domain.Person;
import service.Service;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankWin extends Win{
    Service service = MySpring.obj("service.Service");
    private static String user = null;
    private BankWin() {
        super("服务窗口");
        init();
    }
    private static BankWin bankWin;
    public static BankWin getBankWin(String user) {
        if(bankWin == null){
            bankWin = new BankWin();
        }
        BankWin.user = user;
        return bankWin;
    }
    private JPanel mainJPanel = new JPanel();
    private JLabel imgJLbel = new JLabel();
    private JLabel titleJLbel = new JLabel("小锐银行");
    private JButton quireJButton = new JButton("查询");
    private JButton LogoutJButton = new JButton("注销");
    private JLabel timeYJLabel = new JLabel();
    private JLabel timeDJLabel = new JLabel();
    private JButton WithdrawalJButton = new JButton("取款");
    private JButton TransferJButton = new JButton("转账");
    private JButton depositJButton = new JButton("存款");
    private String gCh = "小锐银行很高兴为你服务";
    private String gEn1 = "Xiaorui Bank is very happy";
    private String gEn2 = " to serve you.";
    private JLabel GreetingsJLabelCh = new JLabel(gCh);
    private JLabel GreetingsJLabelEn1 = new JLabel(gEn1);
    private JLabel GreetingsJLabelEn2 = new JLabel(gEn2);


    @Override
    protected void setAttributes() {
        mainJPanel.setBounds(0,0,700,600);
        imgJLbel.setBounds(300,30,80,80);
        imgJLbel.setIcon(this.getIcon("1.jpg",80,80));
        titleJLbel.setBounds(400,30,400,60);
        titleJLbel.setFont(titleFont);
        quireJButton.setBounds(60,170,80,60);
        quireJButton.setFont(buttonFont);
        LogoutJButton.setBounds(60,300,80,60);
        LogoutJButton.setFont(buttonFont);
        timeYJLabel.setBounds(60,410,180,50);
        timeYJLabel.setFont(labelFont);
        timeDJLabel.setBounds(60,460,180,50);
        timeDJLabel.setFont(labelFont);
        WithdrawalJButton.setBounds(530,170,80,60);
        WithdrawalJButton.setFont(buttonFont);
        TransferJButton.setBounds(530,300,80,60);
        TransferJButton.setFont(buttonFont);
        depositJButton.setBounds(530,430,80,60);
        depositJButton.setFont(buttonFont);
        GreetingsJLabelCh.setBounds(175,200,450,60);
        GreetingsJLabelCh.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,24));
        GreetingsJLabelEn1.setBounds(175,280,450,60);
        GreetingsJLabelEn1.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,24));
        GreetingsJLabelEn2.setBounds(175,320,450,60);
        GreetingsJLabelEn2.setFont(new Font("微软雅黑",Font.CENTER_BASELINE,24));
}

    @Override
    protected void addAttributes() {
        this.setLayout(null);
        this.mainJPanel.setLayout(null);
        this.add(mainJPanel);
        mainJPanel.add(imgJLbel);
        mainJPanel.add(titleJLbel);
        mainJPanel.add(quireJButton);
        mainJPanel.add(LogoutJButton);
        mainJPanel.add(timeYJLabel);
        mainJPanel.add(timeDJLabel);
        mainJPanel.add(WithdrawalJButton);
        mainJPanel.add(TransferJButton);
        mainJPanel.add(depositJButton);
        mainJPanel.add(GreetingsJLabelCh);
        mainJPanel.add(GreetingsJLabelEn1);
        mainJPanel.add(GreetingsJLabelEn2);
    }

    @Override
    protected void action() {
        quireJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user != null){
                    Person person = service.requrePro(user);
                    GreetingsJLabelCh.setText("用户"+person.getUser()+",你的余额还有"+person.getMoney());
                    GreetingsJLabelEn1.setText("User "+person.getUser()+", your balance");
                    GreetingsJLabelEn2.setText("has" + person.getMoney()+" yuan.");
                }
            }
        });
        LogoutJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user != null){
                    int flag = JOptionPane.showConfirmDialog(BankWin.this,"确认注销？");
                    if(flag == 0){
                        if(service.deletePro(user)){
                            BankWin.this.setVisible(false);
                            JOptionPane.showMessageDialog(BankWin.this,"注销成功！");
                        }
                    }
                }
            }
        });
        WithdrawalJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user != null){
                    Float money = null;
                    Person person = null;
                    String mony = JOptionPane.showInputDialog(BankWin.this,"请输入取款金额").trim();
                    try{
                        money = Float.parseFloat(mony);
                        if(money < 0){
                            throw new NumberFormatException();
                        }
                        person = service.requrePro(user);
                        if(money < person.getMoney() && service.updatePro(user,person.getMoney() - money)){
                            JOptionPane.showMessageDialog(BankWin.this,"取款成功！");
                        }else{
                            JOptionPane.showMessageDialog(BankWin.this,"余额不足！");
                        }
                    }catch (NumberFormatException t){
                        JOptionPane.showMessageDialog(BankWin.this,"输入取款金额有误");
                    }
                }
            }
        });
        TransferJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user != null) {
                    String transPerson = JOptionPane.showInputDialog(BankWin.this, "请输入被转入的用户");
                    if (service.requrePro(transPerson) ==  null || "".equals(transPerson)) {
                        JOptionPane.showMessageDialog(BankWin.this,"查无此人！");
                    } else {
                        if(user.equals(transPerson)){
                            JOptionPane.showMessageDialog(BankWin.this,"不能转给自己！");
                        }else {
                            String transMoney = JOptionPane.showInputDialog(BankWin.this, "请输入转账金额");
                            try {
                                Float money = Float.parseFloat(transMoney);
                                if (money < 0) {
                                    throw new NumberFormatException();
                                }
                                Person person = service.requrePro(user);
                                if (person.getMoney() < money) {
                                    JOptionPane.showMessageDialog(BankWin.this, "余额不足");
                                } else {
                                    if (service.updatePro(user, service.requrePro(user).getMoney() - money) &&
                                            service.updatePro(transPerson, service.requrePro(transPerson).getMoney() + money)) {
                                        JOptionPane.showMessageDialog(BankWin.this, "转账成功！");
                                    }
                                }
                            } catch (NumberFormatException t) {
                                JOptionPane.showMessageDialog(BankWin.this, "输入金额有误！");
                            }
                        }
                    }
                }
            }
        });
        depositJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mony = JOptionPane.showInputDialog("请输入存款金额");
                try{
                    Float money = Float.parseFloat(mony);
                    if(money <= 0){
                        throw new NumberFormatException();
                    }
                    if(service.updatePro(user, service.requrePro(user).getMoney()+money)){
                        JOptionPane.showMessageDialog(BankWin.this,"存款成功!");
                    }
                }catch (NumberFormatException t){
                    JOptionPane.showMessageDialog(BankWin.this, "输入金额有误！");
                }
            }
        });
    }


    @Override
    protected void setSelf() {
        this.setBounds(500,400,700,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        new TimeThread().start();
        new ServiceThread().start();
    }

    private class TimeThread extends Thread{
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private String time[] = simpleDateFormat.format(new Date()).split(" ");
        private String hMS[] = time[1].split(":");
        private int sMH[] = new int[3];
        {
            for(int i = 0; i < hMS.length; i ++) {
                sMH[i] = Integer.parseInt(hMS[i]);
            }
        }
        public void run() {
            while(true) {
                try {
                    if(sMH[2] < 60){
                        sMH[2] ++;
                    }else {
                        sMH[2] = 0;
                        if(sMH[1] < 60){
                            sMH[1] ++;
                        }else {
                            sMH[1] = 0;
                            if(sMH[0] < 24){
                                sMH[0] ++;
                            }else {
                                sMH[0] = 0;
                            }
                        }
                    }
                    sleep(1000);
                    String t = null;
                    if(sMH[0] < 10){
                         t = "0" + sMH[0];
                    }
                    t = sMH[0] + ":";
                    if(sMH[1] < 10){
                        t += 0;
                    }
                    t += sMH[1] + ":";
                    if(sMH[2] < 10){
                        t += 0;
                    }
                    t += sMH[2];
                    timeYJLabel.setText(time[0]);
                    timeDJLabel.setText(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class ServiceThread extends Thread{
        public void run() {
            while(true) {
                try {
                    sleep(10000);
                    GreetingsJLabelCh.setText(gCh);
                    GreetingsJLabelEn1.setText(gEn1);
                    GreetingsJLabelEn2.setText(gEn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
