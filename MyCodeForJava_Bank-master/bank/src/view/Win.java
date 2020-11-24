package view;

import javax.swing.*;
import java.awt.*;

public abstract class Win extends JFrame {

    protected Win() {
    }
    protected Win(String title){
        super(title);
    }
    protected Font titleFont = new Font("微软雅黑",Font.BOLD,24);
    protected Font labelFont = new Font("微软雅黑",Font.CENTER_BASELINE,16);
    protected Font textFont = new Font("微软雅黑",Font.BOLD,14);
    protected Font buttonFont = new Font("微软雅黑",Font.CENTER_BASELINE,18);
    protected void init() {
        this.setAttributes();
        this.addAttributes();
        this.action();
        this.setSelf();
    }

    protected ImageIcon getIcon(String url, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(url);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return imageIcon;
    }
    protected abstract void setAttributes();
    protected abstract void addAttributes();
    protected abstract void action();
    protected abstract void setSelf();

}
