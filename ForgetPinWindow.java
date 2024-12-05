import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ForgetPinWindow extends JFrame implements ActionListener {
    JTextField cardTextField;
    JTextField emailTextField;
    JButton resetButton;

    ForgetPinWindow() {
        setTitle("Forget Pin");
        setLayout(new GridLayout(4, 2));

        JLabel cardLabel = new JLabel("Card Number:");
        cardTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email Address:");
        emailTextField = new JTextField();

        resetButton = new JButton("Reset PIN");
        resetButton.addActionListener(this);

        add(cardLabel);
        add(cardTextField);
        add(emailLabel);
        add(emailTextField);
        add(new JLabel()); // Empty label for alignment
        add(resetButton);

        getContentPane().setBackground(Color.WHITE);

        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    public void actionPerformed(ActionEvent ae) {
        // Reset PIN functionality
        String cardNumber = cardTextField.getText();
        String emailAddress = emailTextField.getText();

        // Connect with bank management system to reset PIN
        // Example: send a request to bank management system API
        // Simulating with a message dialog for demonstration
        JOptionPane.showMessageDialog(this, "Reset PIN request sent to bank management system.");
        dispose(); // Close the window after requesting PIN reset
    }
}
