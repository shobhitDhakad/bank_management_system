import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear, forgetPin; // Added forgetPin button
    JTextField cardTextField;
    JPasswordField pinTextField;
    JLabel background; // Added JLabel for background image

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);

        // Adding background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("./Project icons/bank/bankback.jpg"));
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 800, 500); // Set the size of the background JLabel
        add(background);

        // Adding other components on top of the background
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("osward", Font.BOLD, 38));
        text.setForeground(Color.WHITE);
        text.setBounds(200, 40, 400, 40);
        background.add(text);

        JLabel cardNo = new JLabel("Card Number:");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNo.setForeground(Color.WHITE);

        cardNo.setBounds(120, 150, 200, 30);
        background.add(cardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        background.add(cardTextField);

        JLabel pin = new JLabel("Pin Number:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setForeground(Color.WHITE);

        pin.setBounds(120, 220, 250, 30);
        background.add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        background.add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.addActionListener(this);
        background.add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.addActionListener(this);
        background.add(clear);

        signup = new JButton("SIGNUP");
        signup.setBounds(300, 350, 100, 30); // Adjusted width to match other buttons
        signup.addActionListener(this);
        background.add(signup);

        // Added forgetPin button
        forgetPin = new JButton("FORGET PIN");
        forgetPin.setBounds(430, 350, 150, 30); // Adjusted width to match other buttons
        forgetPin.addActionListener(this);
        background.add(forgetPin);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 500);
        setVisible(true);
        setLocation(350, 100);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = String.valueOf(pinTextField.getPassword());
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";

            try {
                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Code for login button
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        } else if (ae.getSource() == forgetPin) {
            // Code for forgetPin button
            String accountNumber = JOptionPane.showInputDialog(this, "Enter your account number:");
            if (accountNumber != null && !accountNumber.isEmpty()) {
                try {
                    Conn conn = new Conn();
                    String query = "SELECT pin FROM pin WHERE acc_no = '" + accountNumber + "'";
                    ResultSet rs = conn.s.executeQuery(query);
                    if (rs.next()) {
                        String pin = rs.getString("pin");
                        JOptionPane.showMessageDialog(this, "Your PIN is: " + pin);
                    } else {
                        JOptionPane.showMessageDialog(this, "Account number not found.");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error retrieving PIN: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account number cannot be empty.");
            }
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
