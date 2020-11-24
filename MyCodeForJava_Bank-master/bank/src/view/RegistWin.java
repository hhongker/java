package view;

import domain.Person;
import service.Service;
import util.MySpring;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistWin extends Win{
    private RegistWin() {
        super("注册窗口");
        init();
    }
    private static RegistWin registWin;
    public static synchronized RegistWin getRegistWin() {
        if(registWin == null){
            registWin = new RegistWin();
        }
        return registWin;
    }

    private Service service = MySpring.obj("service.Service");

    private JPanel mainJpanel = new JPanel();
    private JLabel imgLabel = new JLabel();
    private JLabel titleLable = new JLabel("欢迎使用小锐系统");
    private JLabel userLabel = new JLabel("用户名:");
    private JLabel passwordLabel = new JLabel("密码:");
    private JLabel surePasswordLabel = new JLabel("确认密码:");
    private JLabel monyLabel = new JLabel("金额:");
    private JTextField userText = new JTextField(5);
    private JPasswordField passwordText = new JPasswordField(6);
    private JPasswordField surePasswordText = new JPasswordField(6);
    private JTextField monyText = new JPasswordField();
    private JButton sureButton = new JButton("确认");
    private JButton aginButton = new JButton("重置");
    private JButton returnButton = new JButton("返回");
    @Override
    protected void setAttributes() {
        this.mainJpanel.setBounds(0,0,650,600);
        this.imgLabel.setBounds(140,30,80,80);
        this.imgLabel.setIcon(getIcon("1.jpg",80,80));
        this.titleLable.setBounds(230,30,350,70);
        titleLable.setFont(titleFont);
        this.userLabel.setBounds(110,120,80,60);
        userLabel.setFont(labelFont);
        this.passwordLabel.setBounds(110,200,80,60);
        passwordLabel.setFont(labelFont);
        this.surePasswordLabel.setBounds(110,280,80,60);
        surePasswordLabel.setFont(labelFont);
        this.monyLabel.setBounds(110,360,80,60);
        monyLabel.setFont(labelFont);
        this.userText.setBounds(200,125,300,50);
        userText.setFont(textFont);
        passwordText.setBounds(200,205,300,50);
        surePasswordText.setBounds(200,285,300,50);
        monyText.setBounds(200,365,300,50);
        monyText.setFont(textFont);
        sureButton.setBounds(120,450,80,60);
        sureButton.setFont(buttonFont);
        aginButton.setBounds(300,450,80,60);
        aginButton.setFont(buttonFont);
        returnButton.setBounds(480,450,80,60);
        returnButton.setFont(buttonFont);
    }

    @Override
    protected void addAttributes() {
        this.setLayout(null);
        this.mainJpanel.setLayout(null);
        this.add(mainJpanel);
        this.mainJpanel.add(imgLabel);
        this.mainJpanel.add(titleLable);
        this.mainJpanel.add(userLabel);
        this.mainJpanel.add(passwordLabel);
        this.mainJpanel.add(surePasswordLabel);
        this.mainJpanel.add(monyLabel);
        this.mainJpanel.add(userText);
        this.mainJpanel.add(passwordText);
        this.mainJpanel.add(surePasswordText);
        this.mainJpanel.add(monyText);
        this.mainJpanel.add(sureButton);
        this.mainJpanel.add(aginButton);
        this.mainJpanel.add(returnButton);
    }

    private void clear() {
        userText.setText(null);
        passwordText.setText(null);
        surePasswordText.setText(null);
        monyText.setText(null);
    }
    @Override
    protected void action() {
        aginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistWin.this.clear();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistWin.this.clear();
                RegistWin.this.setVisible(false);
                LoginWin.getLoginWin().setVisible(true);
            }
        });
        sureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = RegistWin.this.userText.getText();
                String password = new String(RegistWin.this.passwordText.getPassword());
                String surePassword = new String(RegistWin.this.surePasswordText.getPassword());
                String money = RegistWin.this.monyText.getText();
                if(service.requrePro(user) == null){
                    if(password.equals(surePassword)) {
                        try{
                            Float mony = null;
                            if((mony = Float.parseFloat(money)) < 0){
                                throw new NumberFormatException();
                            }else {
                                if(service.insertPro(new Person(user,password,mony))){
                                    RegistWin.this.setVisible(false);
                                    LoginWin.getLoginWin().setVisible(true);
                                    JOptionPane.showMessageDialog(RegistWin.this,"注册成功");
                                }
                            }
                        }catch (NumberFormatException t){
                            RegistWin.this.monyText.setText(null);
                            JOptionPane.showMessageDialog(RegistWin.this,"金额输入有误！");
                        }
                    }else{
                        RegistWin.this.passwordText.setText(null);
                        RegistWin.this.surePasswordText.setText(null);
                        JOptionPane.showMessageDialog(RegistWin.this,"两次密码输入不一致");
                    }
                }else{
                    RegistWin.this.userText.setText(null);
                    JOptionPane.showMessageDialog(RegistWin.this,"用户名有误！");
                }
            }
        });
    }

    @Override
    protected void setSelf() {
        this.setBounds(300,200,650,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
