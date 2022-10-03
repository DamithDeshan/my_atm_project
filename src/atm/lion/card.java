package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class card {
    static boolean true_card = false;
    static boolean database_access ;

    public static int g;

    public static int p;


    public static String e_card_no ;
    public static String client_text_data[] = new String[6];

    public static Connection con;

    public static JFrame frame;

    public static PreparedStatement pst;
    public static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application

    public static boolean database_conection() throws SQLException {//Connect to the database
        String url = "jdbc:mysql://localhost:3306/atm_clients";
        String username = "root";
        String passwold = "";
        try {
            con = DriverManager.getConnection(url, username, passwold);
        } catch (SQLException ex) {
        }
        if (con != null) {
            database_access = true;
        } else {
            database_access = false;
        }
        return database_access;
    }


    public static class card_frame_gui {
        public card_frame_gui(){
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame frame = new JFrame();
            frame.setSize(600, 450);//frame size
            frame.setResizable(false);//frame resize
            frame.setTitle("Lion Bank");//frame title
            frame.setIconImage(image.getImage());//set frame icon
            frame.getContentPane().setBackground(Color.gray);//frame background collor
            frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            frame.setVisible(true);//see the frame
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);//application

            JLabel title = new JLabel(".........Wel Come Lion Bank.........");
            title.setSize(402, 57);
            title.setLocation(99, 31);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(font);
            frame.add(title);

            JLabel e_card = new JLabel("Enter Your Card");
            e_card.setSize(402, 57);
            e_card.setLocation(99, 153);
            e_card.setFont(font);
            e_card.setHorizontalAlignment(JLabel.CENTER);
            frame.add(e_card);

            JTextArea card_no = new JTextArea();
            card_no.setSize(202, 30);//creating text area size
            card_no.setLocation(199, 208);//seting area location
            card_no.setFont(font);//adding font
            frame.add(card_no);//adding the frame

            JButton next_btn = new JButton("Next");//next butten
            next_btn.setSize(97, 45);//creating text area size
            next_btn.setLocation(444, 333);//seting area location
            next_btn.setFont(font);//adding font
            next_btn.setVisible(true);
            frame.add(next_btn);//adding the frame

            next_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    g = 0;
                    try {
                        database_conection();
                    } catch (SQLException throwables) {
                    }
                    if (database_access == true) {
                        e_card_no = card_no.getText();
                        try {
                            chack_card();
                            System.out.println("db_accessing_OK");
                        } catch (SQLException throwables) {
                        }

                        if (e_card_no.length() == 0) {
                            frame.setVisible(false);
                            JOptionPane.showMessageDialog(null, "Enter the Your Card...!");
                            try {
                                new start.start_gui();
                            } catch (InterruptedException interruptedException) {
                            }
                        } else if ((e_card_no.equalsIgnoreCase(client_text_data[3])) && ("valid".equalsIgnoreCase(client_text_data[5]))) {
                            System.out.println("card_OK");
                            frame.setVisible(false);
                            new pin.pin_gui();

                        } else if (!(e_card_no.equalsIgnoreCase(client_text_data[3]))) {
                            if (e_card_no.length() == 0) {
                                frame.setVisible(false);
                                JOptionPane.showMessageDialog(null, "Enter the Card...!");
                                try {
                                    new start.start_gui();
                                } catch (InterruptedException interruptedException) {

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Your Card is Invalid...!");
                                frame.setVisible(false);
                                card_no.setText("");
                                try {
                                    new start.start_gui();
                                } catch (InterruptedException interruptedException) {
                                }
                            }

                        } else if (!("valid".equalsIgnoreCase(client_text_data[5]))) {
                            JOptionPane.showMessageDialog(null, "Your Card is not Working...!");
                            frame.setVisible(false);
                            card_no.setText("");
                            try {
                                new start.start_gui();
                            } catch (InterruptedException interruptedException) {
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");
                        card_no.setText("");
                        frame.setVisible(false);
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {
                        }
                    }
                }
            });

            JButton clear_btn = new JButton("Clear");
            clear_btn.setSize(97, 45);//creating text area size
            clear_btn.setLocation(248, 333);//seting area location
            clear_btn.setFont(font);//adding font
            clear_btn.setVisible(true);
            frame.add(clear_btn);//adding the frame

            clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card_no.setText("");
                }
            });

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(97, 45);//creating text area size
            exit_btn.setLocation(51, 333);//seting area location
            exit_btn.setFont(font);//adding font
            exit_btn.setVisible(true);
            frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {
                        //System.out.println("not OKKKKKKK");
                    }

                }
            });

        }

    }

    public static String[] chack_card()  throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/atm_clients";
            String username = "root";
            String passwold = "";

            con = DriverManager.getConnection(url, username, passwold);

            Statement stmt = con.createStatement();
            String quary = "select * from card_details where card_number = " + e_card_no;
            ResultSet db_card;
            db_card = stmt.executeQuery(quary);
            while (db_card.next()) {//expoting the client data
                for (int i = 0; i < 7; i++) {
                    if (i < 6) {
                        client_text_data[i] = db_card.getString(i+1);
                        //System.out.println("Card Data in here");
                        //System.out.println(client_text_data[0]);
                    }
                }
            }


        } catch (SQLException throwables) {
            System.out.println("No Card Data");
        }
        //client_text_data[0] = account name
        //client_text_data[1] = account number
        //client_text_data[2] = account type
        //client_text_data[3] = card nunber
        //client_text_data[4] = card pin
        //client_text_data[5] = account status
        return client_text_data;
    }

}
