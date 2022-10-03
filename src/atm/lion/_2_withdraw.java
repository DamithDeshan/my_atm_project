package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class _2_withdraw {

    public static Connection con;

    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application
    public static Font big_font = new Font("Arial", Font.BOLD,28);// adding font for application

    public static float withdrow_amount = 0.0f;
    public static float use_amount = 0.0f;
    public static float new_amount;

    public static float atm_charge = 5.0f;

    public static PreparedStatement pst;

    public static class withdro_gui{
        public withdro_gui() {
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame wh_frame = new JFrame();
            wh_frame.setSize(600, 450);//frame size
            wh_frame.setResizable(false);//frame resize
            wh_frame.setTitle("Lion Bank");//frame title
            wh_frame.setIconImage(image.getImage());//set frame icon
            wh_frame.getContentPane().setBackground(Color.gray);//frame background collor
            wh_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            wh_frame.setVisible(true);//see the frame
            wh_frame.setLayout(null);
            wh_frame.setLocationRelativeTo(null);//application

            JLabel welcome = new JLabel("WelCome " + card.client_text_data[0]);
            welcome.setSize(600, 55);
            welcome.setLocation(0, 30);
            welcome.setFont(big_font);
            welcome.setHorizontalAlignment(JLabel.CENTER);
            wh_frame.add(welcome);

            JLabel withdraw_lab = new JLabel("Withdraw Amount  : ");
            withdraw_lab.setSize(250, 45);
            withdraw_lab.setLocation(50, 143);
            withdraw_lab.setFont(font);
            withdraw_lab.setHorizontalAlignment(JLabel.CENTER);
            wh_frame.add(withdraw_lab);

            final JTextArea[] amount = {new JTextArea()};
            amount[0].setSize(240, 40);//creating text area size
            amount[0].setLocation(306, 148);//seting area location
            amount[0].setFont(big_font);//adding font
            wh_frame.add(amount[0]);//adding the frame

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(120, 40);//creating text area size
            exit_btn.setLocation(29, 347);//seting area location
            exit_btn.setFont(font);//adding font
            wh_frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wh_frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {

                    }
                }
            });

            JButton clear_btn = new JButton("Clear");
            clear_btn.setSize(120, 40);//creating text area size
            clear_btn.setLocation(234, 347);//seting area location
            clear_btn.setFont(font);//adding font
            wh_frame.add(clear_btn);//adding the frame

            clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    amount[0].setText("");
                }
            });

            JButton menu_btn = new JButton("Menu");
            menu_btn.setSize(120, 40);//creating text area size
            menu_btn.setLocation(439, 347);//seting area location
            menu_btn.setFont(font);//adding font
            wh_frame.add(menu_btn);//adding the frame

            menu_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wh_frame.setVisible(false);
                    new pin.pin_gui();
                }
            });

            JButton withdraw_btn = new JButton("Withdraw");
            withdraw_btn.setSize(210, 60);//creating text area size
            withdraw_btn.setLocation(200, 239);//seting area location
            withdraw_btn.setFont(font);//adding font
            wh_frame.add(withdraw_btn);//adding the frame

            withdraw_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String url = "jdbc:mysql://localhost:3306/atm_clients";
                    String username = "root";
                    String passwold = "";
                    try {
                        con = DriverManager.getConnection(url, username, passwold);
                    } catch (SQLException ex) {
                        wh_frame.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {

                        }
                    }
                    if (con != null) {
                        withdrow_amount = Float.parseFloat(amount[0].getText());
                        use_amount = withdrow_amount;
                        if (pin.AtmBalace <= withdrow_amount) {
                            wh_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "There is no cash in the ATM.\n   Please use another ATM");//not money in the account massage
                            new reuse_card.r_use_card();

                        } else if (withdrow_amount >= 100000) {
                            wh_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "The amount you can get is \n         only Rs.1,00,000.00");//not money in the account massage
                            amount[0].setText("");
                            new withdro_gui();

                        } else if (withdrow_amount >= choice.client_amount - 500) {
                            wh_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "You not Have a this Balance.");//not money in the account massage
                            wh_frame.setVisible(true);
                            amount[0].setText("");

                        } else if (withdrow_amount % 100 == 0) {
                            wh_frame.setVisible(false);
                            new_amount = choice.client_amount - withdrow_amount - atm_charge;
                            String update_sql = "UPDATE card_details SET balance = " + new_amount + " WHERE card_number = " + card.e_card_no;

                            try {
                                pst = con.prepareStatement(update_sql);
                            } catch (SQLException throwables) {
                            }

                            try {
                                pst.executeUpdate();//withdraw money database upbate
                            } catch (SQLException throwables) {
                            }
                            System.out.println("Account Balance Update successfully");

                            String update_sql1 = "UPDATE atm SET balance = " + (pin.AtmBalace - use_amount) + " WHERE atm_id = \"LA-001\" AND branch_id = \"LB-001\"";

                            try {
                                pst = con.prepareStatement(update_sql1);
                            } catch (SQLException throwables) {
                            }

                            try {
                                pst.executeUpdate();//withdraw atm money balance upbate
                            } catch (SQLException throwables) {
                            }
                            System.out.println("ATM Balance Update successfully");

                            use_amount = 0.0f;

                            JOptionPane.showMessageDialog(null, "Take Out your Money.");//withdraw menoy notification
                            new reuse_card.r_use_card();
                        } else {
                            wh_frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Please enter only the \nmultiples of one hundred");
                            amount[0].setText("");
                            new withdro_gui();
                        }
                        withdrow_amount = 0.0f; //finaly withdraw balance equal to 0
                    }
                }
            });
        }
    }
}
