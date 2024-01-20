package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ChangePasswordWindow extends JFrame {

    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    public ChangePasswordWindow(String userEmail) {
        setTitle("Change Password");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordField = new JPasswordField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);
        JButton submitButton = new JButton("Submit");
        JLabel warning = new JLabel();
        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        add(warning,gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window resizable
        setResizable(false);
        setVisible(true);
        // Attach ActionListener to the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String newPassword = new String(newPasswordField.getPassword());

                String confirmPassword = new String(confirmPasswordField.getPassword());

                if(newPassword.length()<10)
                {
                    Main.logr.info("uzytkownik podczas rejestracji podaje haslo nie pasujące do standardów aplikacji");
                    warning.setText("Haslo powinno zawierac co najmniej 10 znakow !");

                }
                else{
                    if(newPassword.equals(confirmPassword))
                    {
                        Main.logr.info("haslo zmienione pomyslnie");
                        warning.setText("zmieniono pomyslnie");


                        //wstepne zapytanie do wyslania na serwer
                        String userData = newPassword +" "+ userEmail;

                        //typ komendy ktora bedzie wykonywana przez serwer
                        String commandType = "UPDATE_PASSWORD";

                        Client client = new Client();
                        client.zapytanie(commandType,userData);

                        //sprawdzenie odpowiedzi od serwera
                        if(client.odpowiedzOdSerwera.equals("UPDATED"))
                        {
                            Main.logr.info("pomyslnie zaaktualizowano dane w bazie");
                            dispose();
                            LoginPage loginPage = new LoginPage();

                        }else {
                            Main.logr.info("Blad przy aktualizowaniu danych w bazie!");
                        }
                    }
                    else {
                        warning.setText("hasla sie roznia!");
                    }

                }
            }
        });
    }


}
