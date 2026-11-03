package com.cybervault;

import javax.swing.SwingUtilities;
import security.repository.service.ui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame = new LoginFrame();

            loginFrame.setVisible(true);
        });
    }
}