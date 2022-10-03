package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class _4_change_pin {

    public static Connection con;
    public static PreparedStatement pst;

    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application

    public static class change_pin_gui{
        public change_pin_gui() {
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame cha_frame = new JFrame();
            cha_frame.setSize(600, 450);//frame size
            cha_frame.setResizable(false);//frame resize
            cha_frame.setTitle("Lion Bank");//frame title
            cha_frame.setIconImage(image.getImage());//set frame icon
            cha_frame.getContentPane().setBackground(Color.gray);//frame background collor
            cha_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            cha_frame.setVisible(true);//see the frame
            cha_frame.setLayout(null);
            cha_frame.setLocationRelativeTo(null);//application

            JLabel welcome = new JLabel("WelCome " + card.client_text_data[0]);
            welcome.setSize(600, 55);
            welcome.setLocation(0, 30);
            welcome.setFont(_1_balance.wel_font);
            welcome.setHorizontalAlignment(JLabel.CENTER);
            cha_frame.add(welcome);

//----------------------------------------------------------------------------------------------------------------------

            JLabel now_pin_lab = new JLabel("PIN Number");
            now_pin_lab.setSize(200, 40);
            now_pin_lab.setLocation(32, 111);
            now_pin_lab.setFont(_1_balance.wel_font);
            now_pin_lab.setHorizontalAlignment(JLabel.CENTER);
            cha_frame.add(now_pin_lab);

            JPasswordField now_pin_pass = new JPasswordField();
            now_pin_pass.setSize(65, 30);//creating text area size
            now_pin_pass.setLocation(286, 116);//seting area location
            now_pin_pass.setFont(pin.pass_font);//adding font
            now_pin_pass.setHorizontalAlignment(JPasswordField.CENTER);
            cha_frame.add(now_pin_pass);//adding the frame

            JButton pin_clear_btn = new JButton("Clear");
            pin_clear_btn.setSize(100, 35);//creating text area size
            pin_clear_btn.setLocation(440, 114);//seting area location
            pin_clear_btn.setFont(font);//adding font
            cha_frame.add(pin_clear_btn);//adding the frame

            pin_clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    now_pin_pass.setText("");
                }
            });

//----------------------------------------------------------------------------------------------------------------------

            JLabel new_pin_lab = new JLabel("New PIN ");
            new_pin_lab.setSize(200, 40);
            new_pin_lab.setLocation(32, 177);
            new_pin_lab.setFont(_1_balance.wel_font);
            new_pin_lab.setHorizontalAlignment(JLabel.CENTER);
            cha_frame.add(new_pin_lab);

            JPasswordField new_pin_pass = new JPasswordField();
            new_pin_pass.setSize(65, 30);//creating text area size
            new_pin_pass.setLocation(286, 182);//seting area location
            new_pin_pass.setFont(pin.pass_font);//adding font
            new_pin_pass.setHorizontalAlignment(JPasswordField.CENTER);
            cha_frame.add(new_pin_pass);//adding the frame

            JButton new_clear_btn = new JButton("Clear");
            new_clear_btn.setSize(100, 35);//creating text area size
            new_clear_btn.setLocation(440, 179);//seting area location
            new_clear_btn.setFont(font);//adding font
            cha_frame.add(new_clear_btn);//adding the frame

            new_clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new_pin_pass.setText("");
                }
            });

//----------------------------------------------------------------------------------------------------------------------

            JLabel re_new_pin_lab = new JLabel("Re-New PIN");
            re_new_pin_lab.setSize(200, 40);
            re_new_pin_lab.setLocation(32, 245);
            re_new_pin_lab.setFont(_1_balance.wel_font);
            re_new_pin_lab.setHorizontalAlignment(JLabel.CENTER);
            cha_frame.add(re_new_pin_lab);

            JPasswordField re_now_pin = new JPasswordField();
            re_now_pin.setSize(65, 30);//creating text area size
            re_now_pin.setLocation(286, 250);//seting area location
            re_now_pin.setFont(pin.pass_font);//adding font
            re_now_pin.setHorizontalAlignment(JPasswordField.CENTER);
            cha_frame.add(re_now_pin);//adding the frame

            JButton re_new_clear_btn = new JButton("Clear");
            re_new_clear_btn.setSize(100, 35);//creating text area size
            re_new_clear_btn.setLocation(440, 247);//seting area location
            re_new_clear_btn.setFont(font);//adding font
            cha_frame.add(re_new_clear_btn);//adding the frame

            re_new_clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    re_now_pin.setText("");
                }
            });

//----------------------------------------------------------------------------------------------------------------------

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(120, 40);//creating text area size
            exit_btn.setLocation(44, 341);//seting area location
            exit_btn.setFont(font);//adding font
            cha_frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cha_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {

                    }
                }
            });

            JButton menu_btn = new JButton("Clear");
            menu_btn.setSize(120, 40);//creating text area size
            menu_btn.setLocation(228, 344);//seting area location
            menu_btn.setFont(font);//adding font
            cha_frame.add(menu_btn);//adding the frame

            menu_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cha_frame.setVisible(false);
                    new pin.pin_gui();
                }
            });

            JButton change_btn = new JButton("Change");
            change_btn.setSize(150, 40);//creating text area size
            change_btn.setLocation(405, 341);//seting area location
            change_btn.setFont(font);//adding font
            cha_frame.add(change_btn);//adding the frame

            change_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String url = "jdbc:mysql://localhost:3306/atm_clients";
                    String username = "root";
                    String passwold = "";
                    try {
                        con = DriverManager.getConnection(url, username, passwold);
                    } catch (SQLException throwables) {
                        cha_frame.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {
                        }
                    }
                    if (con != null) {//database conected
                        cha_frame.setVisible(false);
                        String cl_pin = pin.e_pin_num;
                        if (now_pin_pass.getText().equalsIgnoreCase(cl_pin)) { //enter pin number is correct
                            System.out.println("Enter PIN number is Correct");
                            if (new_pin_pass.getText().equalsIgnoreCase(re_now_pin.getText())) {//enter new pin equal
                                //card.p = 5;
                                String update_sql = "UPDATE card_details SET card_pin = " + re_now_pin.getText() + " WHERE card_number = " + card.e_card_no;
                                try {
                                    pst = con.prepareStatement(update_sql);
                                } catch (SQLException throwables) {
                                }
                                try {
                                    pst.executeUpdate();
                                } catch (SQLException throwables) {
                                }
                                JOptionPane.showMessageDialog(null, "Your PIN has been Changed...");
                                pin.db_pin = re_now_pin.getText();
                                new reuse_card.r_use_card();
                            } else {//enter new pin not equal
                                JOptionPane.showMessageDialog(null, "Enter new PIN not Equal...");
                                card.p++;
                                new change_pin_gui();
                            }
                        } else {//enter pin number is incorrect
                            JOptionPane.showMessageDialog(null, "Your PIN has is incorrect...");
                            card.p++;
                            new change_pin_gui();
                        }
                    /*
                    if (card.p==5){//Card is Block
                        String  update_sql = "UPDATE card_details SET account_status = " + "cancel" + " WHERE card_number = " + card.e_card_no;
                        try {
                            pst = con.prepareStatement(update_sql);
                        } catch (SQLException throwables) {
                        }
                        try {
                            pst.executeUpdate();
                        } catch (SQLException throwables) {
                        }
                        System.out.println("Your Card is Block...");
                    }*/
                    } else {//database not conected
                        cha_frame.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {

                        }
                    }
                }
            });
        }
    }
}
