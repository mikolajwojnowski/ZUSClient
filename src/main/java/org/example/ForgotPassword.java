package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ForgotPassword extends JFrame {

    public ForgotPassword() {
        setTitle("Forgot Password");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String userEmail;
        // Create components
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField();
        JButton resetButton = new JButton("Reset");
        JLabel warningLabel = new JLabel("");

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(emailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(resetButton, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=3;
        add(warningLabel,gbc);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window resizable
        setResizable(true);
        setVisible(true);
        // Attach ActionListener to the Reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the Reset button is clicked
                // Implement your logic for resetting the password here
                // You can access the email entered by the user using emailTextField.getText()
                // For example: String userEmail = emailTextField.getText();
                String userEmail = emailTextField.getText();
                Client client = new Client();
                String commandType = "FORGOT_PASSWORD";
                String userData = userEmail;
                client.zapytanie(commandType,userData);

                if(client.odpowiedzOdSerwera.equals("non-existing"))
                {
                    Main.logr.info("uzytkownik o podanym email nie istnieje");
                    warningLabel.setVisible(true);


                }
                else if(client.odpowiedzOdSerwera.equals("existing")) {
                    Main.logr.info("wyslano mail do zmiany hasla");
                    warningLabel.setVisible(true);
                    warningLabel.setText("wyslano mail z kodem do zmiany hasla");
                    int kodWyslany = generateAuthenticationCode();
                    EmailSenderService emailSenderService = new EmailSenderService();
                    emailSenderService.sendEmail(userEmail,"Change password code",kodWyslany);
                    ForgotPasswordCodePass forgotPasswordCodePass = new ForgotPasswordCodePass(kodWyslany,userEmail);
                    dispose();



                }


            }
        });
    }

    public int generateAuthenticationCode() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int kodzik = random.nextInt(max - min + 1) + min;
        return kodzik;
    }


}
