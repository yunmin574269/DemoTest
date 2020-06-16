package com.grpc.model.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AWT小例子
 */
public class SimpleEvent2 extends JFrame implements ActionListener {
    private JButton btBlue, btDialog;

    public SimpleEvent2() {
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        btBlue = new JButton("蓝色");
        btDialog = new JButton("弹窗");

        // 将按钮添加事件监听器
        btBlue.addActionListener(this);
        btDialog.addActionListener(this);

        add(btBlue);
        add(btDialog);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ***************************事件处理***************************
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btBlue) {
            Container c = getContentPane();
            c.setBackground(Color.BLUE);
        } else if (e.getSource() == btDialog) {
            JDialog dialog = new JDialog();
            dialog.setBounds(300, 200, 400, 300);
            dialog.setVisible(true);
        }
    }


    public static void main(String[] args) {
        new SimpleEvent2();
    }
}
