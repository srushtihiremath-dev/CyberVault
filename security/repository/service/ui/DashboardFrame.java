package security.repository.service.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JTable passwordTable;
    private DefaultTableModel tableModel;

    private JLabel totalPasswordsLabel;
    private JLabel categoriesLabel;
    private JLabel strongPasswordsLabel;

    public DashboardFrame() {

        setTitle("CyberVault - Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout());

        // Top heading
        JLabel heading = new JLabel(
                "CYBERVAULT - PASSWORD MANAGER",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        );

        add(heading, BorderLayout.NORTH);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 5, 10));
        sidebar.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        JButton dashboardButton = new JButton("Dashboard");
        JButton vaultButton = new JButton("Vault");
        JButton addEntryButton = new JButton("Add Entry");
        JButton generatorButton = new JButton("Generator");
        JButton settingsButton = new JButton("Settings");

        sidebar.add(dashboardButton);
        sidebar.add(vaultButton);
        sidebar.add(addEntryButton);
        sidebar.add(generatorButton);
        sidebar.add(settingsButton);

        add(sidebar, BorderLayout.WEST);

        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout(10, 15));
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel dashboardTitle = new JLabel("Dashboard Overview");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 20));

        // Statistics
        JPanel statisticsPanel = new JPanel(
                new GridLayout(1, 3, 15, 15)
        );

        totalPasswordsLabel = new JLabel("Total Passwords: 0");
        categoriesLabel = new JLabel("Categories: 0");
        strongPasswordsLabel = new JLabel("Strong Passwords: 0");

        statisticsPanel.add(createCard(totalPasswordsLabel));
        statisticsPanel.add(createCard(categoriesLabel));
        statisticsPanel.add(createCard(strongPasswordsLabel));

        // Password table
        String[] columns = {
                "Website",
                "Username",
                "Category",
                "Password"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        passwordTable = new JTable(tableModel);

        // Hide passwords by default
        passwordTable.getColumnModel()
                .getColumn(3)
                .setCellRenderer((table, value, isSelected,
                                  hasFocus, row, column) -> {

                    JLabel label = new JLabel("••••••••");
                    label.setOpaque(true);

                    if (isSelected) {
                        label.setBackground(table.getSelectionBackground());
                        label.setForeground(table.getSelectionForeground());
                    } else {
                        label.setBackground(table.getBackground());
                        label.setForeground(table.getForeground());
                    }

                    return label;
                });

        JScrollPane tableScrollPane = new JScrollPane(passwordTable);

        JPanel topContent = new JPanel(new BorderLayout(10, 15));
        topContent.add(dashboardTitle, BorderLayout.NORTH);
        topContent.add(statisticsPanel, BorderLayout.CENTER);

        contentPanel.add(topContent, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // Button actions
        dashboardButton.addActionListener(e ->
                showDashboard()
        );

        vaultButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Password vault selected."
                )
        );

        addEntryButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Connect AddEntryDialog here next."
                )
        );

        generatorButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Password Generator selected."
                )
        );

        settingsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Settings selected."
                )
        );

        // Example entries for testing the dashboard
        addPasswordEntry(
                "GitHub",
                "developer@example.com",
                "Development",
                "SamplePassword123!"
        );

        addPasswordEntry(
                "Gmail",
                "user@example.com",
                "Personal",
                "AnotherSample123!"
        );

        updateStatistics();
    }

    private JPanel createCard(JLabel label) {

        JPanel card = new JPanel(new BorderLayout());

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 15));

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        BorderFactory.createEmptyBorder(20, 10, 20, 10)
                )
        );

        card.add(label, BorderLayout.CENTER);

        return card;
    }

    public void addPasswordEntry(
            String website,
            String username,
            String category,
            String password) {

        tableModel.addRow(new Object[]{
                website,
                username,
                category,
                password
        });

        updateStatistics();
    }

    private void updateStatistics() {

        int total = tableModel.getRowCount();

        java.util.Set<String> categories =
                new java.util.HashSet<>();

        int strongPasswords = 0;

        for (int i = 0; i < total; i++) {

            categories.add(
                    String.valueOf(tableModel.getValueAt(i, 2))
            );

            String password =
                    String.valueOf(tableModel.getValueAt(i, 3));

            if (password.length() >= 12
                    && password.matches(".*[A-Z].*")
                    && password.matches(".*[a-z].*")
                    && password.matches(".*[0-9].*")
                    && password.matches(".*[^a-zA-Z0-9].*")) {

                strongPasswords++;
            }
        }

        totalPasswordsLabel.setText("Total Passwords: " + total);

        categoriesLabel.setText(
                "Categories: " + categories.size()
        );

        strongPasswordsLabel.setText(
                "Strong Passwords: " + strongPasswords
        );
    }

    private void showDashboard() {

        updateStatistics();

        JOptionPane.showMessageDialog(
                this,
                "Dashboard refreshed."
        );
    }
}