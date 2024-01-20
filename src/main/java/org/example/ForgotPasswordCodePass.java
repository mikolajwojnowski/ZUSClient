package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordCodePass extends JFrame {

    public ForgotPasswordCodePass(int kod,String userEmail) {
        setTitle("Authentication Code");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel instructionsLabel = new JLabel("Please enter the authentication code sent to your email:");
        JTextField codeTextField = new JTextField(20); // Set the preferred width
        JButton submitButton = new JButton("Submit");

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increase the insets for better spacing

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        add(instructionsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow the text field to expand horizontally
        add(codeTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST; // Align the button to the left
        add(submitButton, gbc);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window resizable
        setResizable(false); // Disable resizing for simplicity

        setVisible(true);
        // Attach ActionListener to the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String kodzik = codeTextField.getText();
                int kodWpisany = Integer.parseInt(kodzik);

                if(kodWpisany == kod)
                {
                    Main.logr.info("kod poprawny, uruchomienie modulu odpowiedzialnego za zmiane hasla!");
                    ChangePasswordWindow changePasswordWindow = new ChangePasswordWindow(userEmail);
                    dispose();

                }
            }
        });
    }
}