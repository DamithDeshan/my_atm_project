package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class choice {
    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application
    public static Font tit_font = new Font("Arial", Font.BOLD,28);// adding font for application

    public static float client_amount = 0.00f;

    public static Connection con;


    public static class choice_gui {
        public choice_gui() {
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame ch_frame = new JFrame();
            ch_frame.setSize(600, 450);//frame size
            ch_frame.setResizable(false);//frame resize
            ch_frame.setTitle("Lion Bank");//frame title
            ch_frame.setIconImage(image.getImage());//set frame icon
            ch_frame.getContentPane().setBackground(Color.gray);//frame background collor
            ch_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            ch_frame.setVisible(true);//see the frame
            ch_frame.setLayout(null);
            ch_frame.setLocationRelativeTo(null);//application

            JLabel choice_title = new JLabel("Wel Come");
            choice_title.setSize(408, 57);
            choice_title.setLocation(96, 29);
            choice_title.setFont(tit_font);
            choice_title.setHorizontalAlignment(JLabel.CENTER);
            ch_frame.add(choice_title);

            JButton balance_btn = new JButton("Balance");
            balance_btn.setSize(175, 75);//creating text area size
            balance_btn.setLocation(54, 116);//seting area location
            balance_btn.setFont(font);//adding font
            ch_frame.add(balance_btn);//adding the frame

            balance_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch_frame.setVisible(false);
                    _1_balance.balance_gui();
                }
            });

            JButton withdraw_btn = new JButton("Withdrawal");
            withdraw_btn.setSize(175, 75);//creating text area size
            withdraw_btn.setLocation(366, 116);//seting area location
            withdraw_btn.setFont(font);//adding font
            ch_frame.add(withdraw_btn);//adding the frame

            withdraw_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch_frame.setVisible(false);
                    new _2_withdraw.withdro_gui();
                }
            });

            JButton deposit_btn = new JButton("Deposit");
            deposit_btn.setSize(175, 75);//creating text area size
            deposit_btn.setLocation(54, 222);//seting area location
            deposit_btn.setFont(font);//adding font
            ch_frame.add(deposit_btn);//adding the frame

            deposit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch_frame.setVisible(false);
                    new _3_deposit.deposit_gui();
                }
            });

            JButton change_pin_btn = new JButton("Change PIN");
            change_pin_btn.setSize(175, 75);//creating text area size
            change_pin_btn.setLocation(366, 222);//seting area location
            change_pin_btn.setFont(font);//adding font
            ch_frame.add(change_pin_btn);//adding the frame

            change_pin_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch_frame.setVisible(false);
                    new _4_change_pin.change_pin_gui();
                }
            });

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(158, 50);//creating text area size
            exit_btn.setLocation(221, 338);//seting area location
            exit_btn.setFont(font);//adding font
            ch_frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ch_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {
                    }
                }
            });
        }
    }

    public static float read_balance(){
        try {
            String url = "jdbc:mysql://localhost:3306/atm_clients";
            String username = "root";
            String passwold = "";
            try {
                con = DriverManager.getConnection(url, username, passwold);
            } catch (SQLException ex) {
            }
            Statement stmt = con.createStatement();
            String quary = "select * from card_details where card_number = " + card.e_card_no;
            ResultSet db_card;
            db_card = stmt.executeQuery(quary);
            //------------------------------------------------------------------------------------
            while (db_card.next()) {//expoting the client data
                for (int i = 0; i < 7; i++) {
                    if (i == 6) {
                        client_amount = db_card.getFloat(7);
                        //System.out.println(client_amount);
                    }
                }
            }
        }catch (SQLException throwables) {
        }
        return client_amount;
    }
}
