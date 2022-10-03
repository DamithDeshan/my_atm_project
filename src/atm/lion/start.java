package atm.lion;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.FlowLayout;
import java.util.Calendar;

public class start {

    public static Font font = new Font("Arial", Font.BOLD ,24);// adding font for application
    public static Calendar calendar;
    public static String time;
    public static SimpleDateFormat timeformat;
    public static JLabel l_time;

    public static void settime() throws InterruptedException {
        while(true){
            time = timeformat.format(Calendar.getInstance().getTime());
            l_time.setText(time);
            Thread.sleep(1000);
        }
    }

    public static class start_gui {
        public start_gui() throws InterruptedException {
            ImageIcon logo = new ImageIcon("logo_icon.png");// adding icon for application
            ImageIcon back = new ImageIcon("back.png");// adding background image for application
            ImageIcon card = new ImageIcon("card_types.png");

            LocalDateTime date = LocalDateTime.now();//adding the date
            //System.out.println(date);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd");//formating data
            DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH : MM : ss");//formating time
            //System.out.println(df.format(date));
            //System.out.println(tf.format(date));

            JFrame start_frame = new JFrame();//create strat frame
            start_frame.setSize(600, 450);//frame size
            start_frame.setResizable(false);//frame resize
            start_frame.setTitle("Lion Bank");//frame title
            start_frame.setIconImage(logo.getImage());//set frame icon
            start_frame.getContentPane().setBackground(Color.gray);//frame background collor
            start_frame.setContentPane(new JLabel(new ImageIcon("back.png")));
            start_frame.setLayout(null);
            start_frame.setLocationRelativeTo(null);//application

            JLabel logo_icon = new JLabel(logo);
            //logo_icon.setIcon(logo);
            logo_icon.setSize(224, 224);
            logo_icon.setLocation(190, 29);
            start_frame.add(logo_icon);

            timeformat = new SimpleDateFormat("HH : mm : ss");
            start_frame.setVisible(true);//see the frame
            time = timeformat.format(calendar.getInstance().getTime());

            JLabel l_date = new JLabel(df.format(date));
            l_date.setSize(120, 60);
            l_date.setLocation(52, 111);
            l_date.setFont(font);
            l_date.setHorizontalAlignment(JLabel.CENTER);
            start_frame.add(l_date);

            JButton start_btn = new JButton("Start");
            start_btn.setSize(300, 45);//creating text area size
            start_btn.setLocation(152, 278);//seting area location
            start_btn.setFont(font);//adding font
            start_btn.setVisible(true);
            start_btn.setVerticalTextPosition(JButton.CENTER);
            start_frame.add(start_btn);//adding the frame

            start_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    start_frame.setVisible(false);
                    new card.card_frame_gui();
                }
            });

            l_time = new JLabel(time);
            //l_time.setText();
            l_time.setSize(120, 60);
            l_time.setLocation(432, 111);
            l_time.setFont(font);
            l_time.setHorizontalAlignment(JLabel.CENTER);
            start_frame.add(l_time);

            JLabel card_icon = new JLabel(card);
            //card_icon.setIcon(back);
            card_icon.setSize(476, 50);
            card_icon.setLocation(64, 343);
            start_frame.add(card_icon);

        }
    }
}
