package atm.lion;

import atm.lion.start.start_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class _1_balance {

    public static Connection con;
    public static PreparedStatement pst;

    public static double atm_charge = 5.0d;
    public static double new_amount;

    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application
    public static Font wel_font = new Font("Arial", Font.BOLD,28);// adding font for application

    public static void balance_gui() {

        ImageIcon back = new ImageIcon("back.png");// adding background image for application

        JFrame bal_frame = new JFrame();
        bal_frame.setSize(600,450);//frame size
        bal_frame.setResizable(false);//frame resize
        bal_frame.setTitle("Lion Bank");//frame title
        bal_frame.setIconImage(image.getImage());//set frame icon
        bal_frame.getContentPane().setBackground(Color.gray);//frame background collor
        bal_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
        bal_frame.setVisible(true);//see the frame
        bal_frame.setLayout(null);
        bal_frame.setLocationRelativeTo(null);//application

        JLabel welcome = new JLabel("WelCome " + card.client_text_data[0]);
        welcome.setSize(600,55);
        welcome.setLocation(0,30);
        welcome.setFont(wel_font);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        bal_frame.add(welcome);

        JLabel ac_balance_lab = new JLabel("Account Balance    :    Rs. " + String.format("%.2f",choice.client_amount + 5));
        ac_balance_lab.setSize(600,50);
        ac_balance_lab.setLocation(0,145);
        ac_balance_lab.setFont(wel_font);
        ac_balance_lab.setHorizontalAlignment(JLabel.CENTER);
        bal_frame.add(ac_balance_lab);

        JLabel with_amount = new JLabel("WithDraw Amount    :    Rs. " + String.format("%.2f",choice.client_amount - 500 + 5));
        with_amount.setSize(600,50);
        with_amount.setLocation(0,225);
        with_amount.setFont(wel_font);
        with_amount.setHorizontalAlignment(JLabel.CENTER);
        bal_frame.add(with_amount);

        JButton menu_btn = new JButton("Menu");
        menu_btn.setSize(100,40);//creating text area size
        menu_btn.setLocation(448,330);//seting area location
        menu_btn.setFont(font);//adding font
        bal_frame.add(menu_btn);//adding the frame

        menu_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String url = "jdbc:mysql://localhost:3306/atm_clients";
                String username = "root";
                String passwold = "";
                try{
                    con = DriverManager.getConnection(url, username, passwold);
                }catch (SQLException throwables){
                }
                if(con != null){
                    bal_frame.setVisible(false);
                    //datadase update start
                    new_amount = choice.client_amount - atm_charge;
                    String  update_sql = "UPDATE card_details SET balance = " + new_amount + " WHERE card_number = " + card.e_card_no;
                    try {
                        pst = con.prepareStatement(update_sql);
                    } catch (SQLException throwables) {
                    }
                    try {
                        pst.executeUpdate();
                    } catch (SQLException throwables) {
                    }
                    //datadase update end

                    bal_frame.setVisible(false);
                    new pin.pin_gui();

                }else{
                    bal_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null,"Machine is not Working.\n     Use Another ATM.");
                    try {
                        new start_gui();
                    } catch (InterruptedException interruptedException) {
                    }
                }
            }
        });

        JButton exit_btn = new JButton("Exit");
        exit_btn.setSize(100,40);//creating text area size
        exit_btn.setLocation(51,330);//seting area location
        exit_btn.setFont(font);//adding font
        bal_frame.add(exit_btn);//adding the frame

        exit_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                bal_frame.setVisible(false);//balance frame is not visible
                JOptionPane.showMessageDialog(null,"Thank You....!\n Come Again....!");
                try {
                    new start_gui();
                } catch (InterruptedException interruptedException) {

                }
            }
        });
    }
}
