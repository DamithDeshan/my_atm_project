package atm.lion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.*;

public class pin {
    static ImageIcon image = new ImageIcon("logo.png");// adding icon for application
    public static Font font = new Font("Arial", Font.BOLD,24);// adding font for application
    public static Font pass_font = new Font("Arial", Font.BOLD,40);// adding font for application

    public static boolean database_access ;
    public static String client_text_data[] = new String[6];

    public static String e_pin_num ;
    public static String db_pin;
    public static String db;


    public static boolean access_choice = false;

    public static Connection con;

    public static String update_sql;
    public static PreparedStatement pst;

    public static float AtmBalace ;

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

    public static class pin_gui {
        pin_gui() {
            ImageIcon back = new ImageIcon("back.png");// adding background image for application

            JFrame frame = new JFrame();
            frame.setSize(600, 450);//frame size
            frame.setResizable(false);//frame resize
            frame.setTitle("Lion Bank");//frame title
            frame.setIconImage(image.getImage());//set frame icon
            frame.getContentPane().setBackground(Color.gray);//frame background colour
            frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            frame.setVisible(true);//see the frame
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);//application

            JLabel e_pin = new JLabel("PIN Number");
            e_pin.setSize(402, 57);
            e_pin.setLocation(100, 112);
            e_pin.setFont(font);
            e_pin.setHorizontalAlignment(JLabel.CENTER);
            frame.add(e_pin);

            JPasswordField card_pin_no = new JPasswordField();
            card_pin_no.setSize(65, 30);//creating text area size
            card_pin_no.setLocation(269, 154);//seting area location
            card_pin_no.setFont(pass_font);//adding font
            card_pin_no.setHorizontalAlignment(JPasswordField.CENTER);
            frame.add(card_pin_no);//adding the frame

            JButton next_btn = new JButton("Next");
            next_btn.setSize(97, 45);//creating text area size
            next_btn.setLocation(445, 327);//seting area location
            next_btn.setFont(font);//adding font
            frame.add(next_btn);//adding the frame


            next_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        database_conection();
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");//not connection message
                        frame.setVisible(false);
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {
                        }
                        access_choice = false;
                    }
                    try {
                        pin_read();

                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");//not connection message
                        frame.setVisible(false);
                        try {
                            new start.start_gui();
                        } catch (InterruptedException interruptedException) {
                        }
                        access_choice = false;
                    }

                    db_pin = db;
                    e_pin_num = card_pin_no.getText();

                    if ((database_access == true)) { //database conection successful
                        //System.out.println(e_pin_num);

                        if ((e_pin_num.equalsIgnoreCase(db_pin)) && (card.g<=2)) {//entered pin is correct
                            card.g = 0;
                            frame.setVisible(false);
                            access_choice = true;
                            choice.read_balance();
                            try {
                                atm_balance();
                            } catch (SQLException throwables) {
                                frame.setVisible(false);
                                JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");//not connection message
                                try {
                                    new start.start_gui();
                                } catch (InterruptedException interruptedException) {
                                }
                            }
                            new choice.choice_gui();
                            System.out.println("Choice OK......................");

                        }else if((!(e_pin_num.equalsIgnoreCase(db_pin))) && (card.g<=2)) {//entered pin is not correct
                                frame.setVisible(false);
                                JOptionPane.showMessageDialog(null, "Your pin Number is Incorrect.");
                                card.g++;
                                //System.out.println(card.g);
                                if(card.g <=2){
                                    new pin_gui();
                                }

                        }else {//databace not connection
                            JOptionPane.showMessageDialog(null, "Machine is not Working.\n     Use Another ATM.");//not connection message
                            frame.setVisible(false);
                            try {
                                new start.start_gui();
                            } catch (InterruptedException interruptedException) {
                            }
                            access_choice = false;

                        }

                        if (card.g == 3) {//card is block part
                            frame.setVisible(false);
                            //JOptionPane.showMessageDialog(null, "Your pin Number is Incorrect.");
                            JOptionPane.showMessageDialog(null, "Your card is Block....!");//not connection message
                            update_sql = "UPDATE card_details SET account_status = \"Cancel\" WHERE card_number = " + card.e_card_no;
                            try {
                                pst = con.prepareStatement(update_sql);
                            } catch (SQLException throwables) {
                            }
                            try {
                                pst.executeUpdate();
                            } catch (SQLException throwables) {
                            }

                            System.out.println("Update successfully");

                            try {
                                frame.setVisible(false);
                                new start.start_gui();//return card frame
                                card.g = 0;
                            } catch (InterruptedException interruptedException) {
                            }
                        }
                    }
                }
            });

            JButton clear_btn = new JButton("Clear");
            clear_btn.setSize(97, 45);//creating text area size
            clear_btn.setLocation(253, 327);//seting area location
            clear_btn.setFont(font);//adding font
            frame.add(clear_btn);//adding the frame

            clear_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card_pin_no.setText("");
                }
            });

            JButton exit_btn = new JButton("Exit");
            exit_btn.setSize(97, 45);//creating text area size
            exit_btn.setLocation(60, 327);//seting area location
            exit_btn.setFont(font);//adding font
            frame.add(exit_btn);//adding the frame

            exit_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Thank You....!\n Come Again....!");
                    try {
                        card.g = 0;
                        new start.start_gui();
                    } catch (InterruptedException interruptedException) {
                    }
                }
            });
        }
    }

    public static double atm_balance() throws SQLException {// chacking ATM Money Balance
        String url = "jdbc:mysql://localhost:3306/atm_clients";
        String username = "root";
        String passwold = "";

        con = DriverManager.getConnection(url, username, passwold);

        Statement stmt = con.createStatement();
        String quary = "select * from atm where atm_id = \"LA-001\" AND branch_id = \"LB-001\"" ;
        ResultSet db_card;
        db_card = stmt.executeQuery(quary);
        while (db_card.next()) {//expoting the client data
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    AtmBalace = db_card.getFloat(3);// asign the ATM Money Balance
                    //System.out.println(AtmBalace);
                    //System.out.println(client_text_data[0]);
                }
            }
        }
        return AtmBalace;
    }

    public static String pin_read()  throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/atm_clients";
            String username = "root";
            String passwold = "";

            con = DriverManager.getConnection(url, username, passwold);

            Statement stmt = con.createStatement();
            String quary = "select * from card_details where card_number = " + card.e_card_no;
            ResultSet db_card;
            db_card = stmt.executeQuery(quary);
            while (db_card.next()) {//expoting the client data
                for (int i = 0; i < 7; i++) {
                    if (i == 4) {
                        db = db_card.getString(5);
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
        return db;
    }
}
