package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class _3_deposit {

    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application
    //public static Font big_font = new Font("Arial", Font.BOLD,28);// adding font for application

    public static Connection con;
    public static float deposit_amount = 0.0f;
    public static float new_amount;
    public static PreparedStatement pst;

    public static float use_amount = 0.0f;

    public static class deposit_gui{
        public deposit_gui() {
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame dep_frame = new JFrame();
            dep_frame.setSize(600, 450);//frame size
            dep_frame.setResizable(false);//frame resize
            dep_frame.setTitle("Lion Bank");//frame title
            dep_frame.setIconImage(image.getImage());//set frame icon
            dep_frame.getContentPane().setBackground(Color.gray);//frame background collor
            dep_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            dep_frame.setVisible(true);//see the frame
            dep_frame.setLayout(null);
            dep_frame.setLocationRelativeTo(null);//application

            JLabel welcome = new JLabel("WelCome " + card.client_text_data[0]);
            welcome.setSize(600, 55);
            welcome.setLocation(0, 34);
            welcome.setFont(_2_withdraw.big_font);
            welcome.setHorizontalAlignment(JLabel.CENTER);
            dep_frame.add(welcome);

            JLabel withdraw_lab = new JLabel("Withdraw Amount  : ");
            withdraw_lab.setSize(250, 50);
            withdraw_lab.setLocation(52, 192);
            withdraw_lab.setFont(font);
            withdraw_lab.setHorizontalAlignment(JLabel.CENTER);
            dep_frame.add(withdraw_lab);

            JTextArea amount = new JTextArea();
            amount.setSize(243, 40);//creating text area size
            amount.setLocation(308, 197);//seting area location
            amount.setFont(_2_withdraw.big_font);//adding font
            dep_frame.add(amount);//adding the frame

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(120, 40);//creating text area size
            exit_btn.setLocation(32, 345);//seting area location
            exit_btn.setFont(font);//adding font
            dep_frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dep_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {

                    }
                }
            });

            JButton clear_btn = new JButton("Clear");
            clear_btn.setSize(120, 40);//creating text area size
            clear_btn.setLocation(216, 347);//seting area location
            clear_btn.setFont(font);//adding font
            dep_frame.add(clear_btn);//adding the frame

            clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    amount.setText("");
                }
            });

            JButton deposit_btn = new JButton("Deposit");
            deposit_btn.setSize(168, 40);//creating text area size
            deposit_btn.setLocation(400, 345);//seting area location
            deposit_btn.setFont(font);//adding font
            dep_frame.add(deposit_btn);//adding the frame

            deposit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String url = "jdbc:mysql://localhost:3306/atm_clients";
                    String username = "root";
                    String passwold = "";
                    try {
                        con = DriverManager.getConnection(url, username, passwold);
                    } catch (SQLException ex) {
                        dep_frame.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {
                        }
                    }


                    if (con != null) {

                        deposit_amount = Float.parseFloat(amount.getText());
                        use_amount = deposit_amount;

                        if (deposit_amount > 500000) {
                            dep_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "The amount you can deposit is \n         only Rs.5,00,000.00 ");//not money in the account massage
                            amount.setText("");
                            new deposit_gui();

                        } else if (deposit_amount % 100 == 0) {
                            dep_frame.setVisible(false);
                            new_amount = choice.client_amount + deposit_amount;

                            String update_sql = "UPDATE card_details SET balance = " + new_amount + " WHERE card_number = " + card.e_card_no;

                            try {
                                pst = con.prepareStatement(update_sql);
                            } catch (SQLException throwables) {
                            }

                            try {
                                pst.executeUpdate();
                            } catch (SQLException throwables) {
                            }

                            String update_sql2 = "UPDATE atm SET balance = " + (pin.AtmBalace + use_amount) + " WHERE atm_id = \"LA-001\" AND branch_id = \"LB-001\"";

                            try {
                                pst = con.prepareStatement(update_sql2);
                            } catch (SQLException throwables) {
                            }

                            try {
                                pst.executeUpdate();//withdraw atm money balance upbate
                            } catch (SQLException throwables) {
                            }

                            use_amount = 0.0f;

                            dep_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Your money has been\n deposited.");
                            System.out.println("Account Balance Update successfully");

                            new reuse_card.r_use_card();
                        } else {
                            dep_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Please be sure to deposit \nonly in multiples of one hundred");
                            amount.setText("");
                            new deposit_gui();
                        }
                    }
                }
            });
        }
    }
}

