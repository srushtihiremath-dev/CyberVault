package security.repository.service.ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPasswordField passwordField;
    private JButton unlockButton;
    private JButton createVaultButton;

    public LoginFrame() {

        setTitle("CyberVault - Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        JLabel iconLabel = new JLabel("🔐", SwingConstants.CENTER);
        JLabel titleLabel = new JLabel(
            "CYBERVAULT",
            SwingConstants.CENTER
        );

        JLabel passwordLabel = new JLabel("Master Password");

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        unlockButton = new JButton("[ UNLOCK VAULT ]");
        createVaultButton = new JButton("Create New Vault");

        JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        mainPanel.add(iconLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(passwordPanel);
        mainPanel.add(unlockButton);
        mainPanel.add(createVaultButton);

        add(mainPanel);

        unlockButton.addActionListener(e -> unlockVault());

        createVaultButton.addActionListener(e -> createVault());
    }

    private void unlockVault() {

        char[] password = passwordField.getPassword();

        if (password.length == 0) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter your master password."
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "Login interface is working. Connect vault authentication next."
        );

        passwordField.setText("");
    }

    private void createVault() {

        JOptionPane.showMessageDialog(
            this,
            "Create New Vault selected. Vault creation will be implemented next."
        );
    }
}