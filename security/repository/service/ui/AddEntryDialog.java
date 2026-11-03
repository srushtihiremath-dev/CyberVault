//package com.cybervault;
package security.repository.service.ui;

import model.PasswordEntry;
import security.PasswordGenerator;

import javax.swing.*;
import java.awt.*;

public class AddEntryDialog extends JDialog {

    private JTextField websiteField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> categoryBox;
    private JTextArea notesArea;

    private PasswordEntry passwordEntry;

    public AddEntryDialog(JFrame parent) {

        super(parent, "Add Password", true);

        setSize(450, 500);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 5, 7, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel title = new JLabel("ADD PASSWORD");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // Website
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Website:"), gbc);

        websiteField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(websiteField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Generate Password button
        JButton generateButton = new JButton("Generate Strong Password");

        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(generateButton, gbc);

        generateButton.addActionListener(e -> generatePassword());

        // Category
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Category:"), gbc);

        String[] categories = {
                "Personal",
                "Development",
                "Social Media",
                "Banking",
                "Work",
                "Other"
        };

        categoryBox = new JComboBox<>(categories);

        gbc.gridx = 1;
        panel.add(categoryBox, gbc);

        // Notes
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Notes:"), gbc);

        notesArea = new JTextArea(4, 20);
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(notesArea);

        gbc.gridx = 1;
        panel.add(scrollPane, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancelButton = new JButton("Cancel");
        JButton saveButton = new JButton("Save");

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        panel.add(buttonPanel, gbc);

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> saveEntry());

        add(panel);
    }

    private void generatePassword() {

        try {
            String generatedPassword =
                    PasswordGenerator.generatePassword();

            passwordField.setText(generatedPassword);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to generate password.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void saveEntry() {

        String website = websiteField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (website.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Website is required."
            );
            websiteField.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Username is required."
            );
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Password is required."
            );
            passwordField.requestFocus();
            return;
        }

        /*
         * Create PasswordEntry here after matching
         * its constructor to your existing model class.
         */

        JOptionPane.showMessageDialog(
                this,
                "The fields are valid. Connect this dialog to VaultService to save the entry."
        );
    }

    public PasswordEntry getPasswordEntry() {
        return passwordEntry;
    }
}