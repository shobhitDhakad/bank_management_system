import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
//    JButton deposit, withdrawl, ministatement, fastcash, balanceenquiry, pinchange, exit;
//    String pinnumber;
//
//
//    FastCash(String pinnumber) {
//        this.pinnumber = pinnumber;
//
//        setLayout(null);
//
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Project icons/atm.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(0,0,900,900);
//        add(image);
//
//        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
//        text.setBounds(210,300,700,35);
//        text.setForeground(Color.WHITE);
//        text.setFont(new Font("System", Font.BOLD, 16));
//        image.add(text);
//
//         deposit = new JButton("100");
//        deposit.setBounds(170,415,150,30);
//        deposit.addActionListener(this);
//        image.add(deposit);
//
//         withdrawl = new JButton("500");
//        withdrawl.setBounds(355,415,150,30);
//        withdrawl.addActionListener(this);
//        image.add(withdrawl);
//
//
//         fastcash = new JButton("1000");
//        fastcash.setBounds(170,450,150,30);
//        fastcash.addActionListener(this);
//        image.add(fastcash);
//
//         ministatement = new JButton("2000");
//        ministatement.setBounds(355,450,150,30);
//        ministatement.addActionListener(this);
//        image.add(ministatement);
//
//         pinchange = new JButton("5000");
//        pinchange.setBounds(170,485,150,30);
//        pinchange.addActionListener(this);
//        image.add(pinchange);
//
//         balanceenquiry = new JButton("10000");
//        balanceenquiry.setBounds(355,485,150,30);
//        balanceenquiry.addActionListener(this);
//        image.add(balanceenquiry);
//
//         exit = new JButton("BACK");
//        exit.setBounds(355,520,150,30);
//        exit.addActionListener(this);
//        image.add(exit);
//
//
//
//
//
//        setSize(900,900);
//        setLocation(300,0);
//        setUndecorated(true);
//        setVisible(true);


    JLabel l1, l2;
//        JButton deposit, withdrawl, ministatement, fastcash, balanceenquiry, pinchange, exit;

    JButton b1, b2, b3, b4, b5, b6, exit, b8;
    JTextField t1;
    String pinnumber;

    FastCash(String pin) {
        this.pinnumber = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Project icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 960, 1080);
        add(l3);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        exit = new JButton("BACK");

        setLayout(null);

        l1.setBounds(235, 400, 700, 35);
        l3.add(l1);

        b1.setBounds(170, 499, 150, 35);
        l3.add(b1);

        b2.setBounds(390, 499, 150, 35);
        l3.add(b2);

        b3.setBounds(170, 543, 150, 35);
        l3.add(b3);

        b4.setBounds(390, 543, 150, 35);
        l3.add(b4);

        b5.setBounds(170, 588, 150, 35);
        l3.add(b5);

        b6.setBounds(390, 588, 150, 35);
        l3.add(b6);

        exit.setBounds(390, 633, 150, 35);
        l3.add(exit);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        exit.addActionListener(this);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else  {
            String amount = ((JButton)ae.getSource()).getText().substring(3);
            System.out.println("AMOUNT "+amount);
            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0;
                while(rs.next())  {
                    if (rs.getString("type").equals("Deposit")) {

                        balance += Long.parseLong (rs.getString("amount"));
                        System.out.println(balance);

                    }else {
                        balance -= Long.parseLong(rs.getString("amount"));
                        System.out.println(balance);

                    }

                }

                if(ae.getSource()!= exit && balance < Long.parseLong(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");

                }

                Date date = new Date();
                String query = "insert  into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs"+amount+"Debited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String args[]) {
        new FastCash("");
    }
    
}
