package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class reuse_card{

    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application

    public static class r_use_card{

        public r_use_card() {
            JFrame re_frame = new JFrame();
            re_frame.setSize(600, 450);//frame size
            re_frame.setResizable(false);//frame resize
            re_frame.setTitle("Lion Bank");//frame title
            re_frame.setIconImage(image.getImage());//set frame icon
            re_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            re_frame.getContentPane().setBackground(Color.gray);//frame background collor
            re_frame.setVisible(true);//see the frame
            re_frame.setLayout(null);
            re_frame.setLocationRelativeTo(null);//application

            JLabel re_use = new JLabel("Do you need to use the machine again ?");
            re_use.setSize(600, 77);
            re_use.setLocation(0, 30);
            re_use.setFont(_1_balance.wel_font);
            re_use.setHorizontalAlignment(JLabel.CENTER);
            re_frame.add(re_use);

            JButton yes_btn = new JButton("Yes");
            yes_btn.setSize(120, 40);//creating text area size
            yes_btn.setLocation(424, 324);//seting area location
            yes_btn.setFont(font);//adding font
            re_frame.add(yes_btn);//adding the frame

            yes_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    re_frame.setVisible(false);
                    new pin.pin_gui();
                }
            });

            JButton no_btn = new JButton("NO");
            no_btn.setSize(120, 40);//creating text area size
            no_btn.setLocation(56, 324);//seting area location
            no_btn.setFont(font);//adding font
            re_frame.add(no_btn);//adding the frame

            no_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    re_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {
                    }
                }
            });
        }
    }
}
