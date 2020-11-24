package view;

import service.Service;
import util.MySpring;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWin extends Win{


    private LoginWin() {
        super("登录窗口");
        init();
    }
    private static LoginWin loginWin;
    public static LoginWin getLoginWin() {
        if(loginWin == null) {
            loginWin = new LoginWin();
        }
        return loginWin;
    }

    private Service service = MySpring.obj("service.Service");

    private RegistWin registWin = null;
    private JPanel mainJpanel = new JPanel();
    private JLabel imgLabel = new JLabel();
    private JLabel titleLable = new JLabel("欢迎使用小锐系统");
    private JLabel loginLabel = new JLabel("用户名:");
    private JLabel passwordLabel = new JLabel("密码:");
    private JTextField loginText = new JTextField(5);
    private JPasswordField passwordText = new JPasswordField(6);
    private JButton loginButton = new JButton("登录");
    private JButton registButton = new JButton("注册");



    private void Clear() {
        this.loginText.setText(null);
        this.passwordText.setText(null);
    }

    @Override
    protected void setAttributes() {
        mainJpanel.setBounds(0,0,500,400);
        imgLabel.setBounds(110,20,65,65);
        imgLabel.setIcon(this.getIcon("1.jpg",60,60));
        titleLable.setBounds(190,20,300,70);
        titleLable.setFont(titleFont);
        loginLabel.setBounds(60,90,70,50);
        loginLabel.setFont(labelFont);
        passwordLabel.setBounds(60,160,70,50);
        passwordLabel.setFont(labelFont);
        loginText.setBounds(115,95,330,40);
        loginText.setFont(textFont);
        passwordText.setBounds(115,165,330,40);
        loginButton.setBounds(150,250,70,50);
        loginButton.setFont(buttonFont);
        registButton.setBounds(270,250,70,50);
        registButton.setFont(buttonFont);
    }


    @Override
    protected void addAttributes() {
        this.setLayout(null);
        this.mainJpanel.setLayout(null);
        this.add(mainJpanel);
        this.mainJpanel.add(imgLabel);
        this.mainJpanel.add(titleLable);
        this.mainJpanel.add(loginLabel);
        this.mainJpanel.add(passwordLabel);
        this.mainJpanel.add(loginText);
        this.mainJpanel.add(passwordText);
        this.mainJpanel.add(loginButton);
        this.mainJpanel.add(registButton);
    }

    @Override
    protected void action() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = loginText.getText();
                String password = new String(passwordText.getPassword());
                if(service.login(user,password)){
                    LoginWin.this.setVisible(false);
                    BankWin.getBankWin(user).setVisible(true);
                }else{
                    LoginWin.this.Clear();
                    JOptionPane.showMessageDialog(LoginWin.this,"用户名或密码错误");
                }
            }
        });
        registButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWin.this.setVisible(false);
                if(LoginWin.this.registWin == null){
                    LoginWin.this.Clear();
                    LoginWin.this.registWin = RegistWin.getRegistWin();
                }else {
                    LoginWin.this.Clear();
                    LoginWin.this.registWin.setVisible(true);
                }
            }
        });

    }

    @Override
    protected void setSelf() {
        this.setBounds(300,200,500,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
