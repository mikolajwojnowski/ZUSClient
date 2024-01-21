package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class RegistrationPanel extends JFrame {

    IDandPassword iDandPassword = new IDandPassword();
    private JPanel panel = new JPanel(new GridLayout(8, 2, 20, 20));
    private JTextField nameField = new JTextField();
    private JTextField surnameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JTextField peselField = new JTextField();
    private JTextField ageField = new JTextField();
    private JComboBox<String> sexComboBox = new JComboBox<>(new String[]{"Mężczyczna", "Kobieta"});
    private JTextField emailField = new JTextField();
    private JButton submitButton = new JButton("Prześlij");
    private JButton returnButton = new JButton("Powrót");
    JLabel warning = new JLabel("");

    public RegistrationPanel() {
        setTitle("Panel Rejestracyjny");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        warning.setVisible(false);
        warning.setForeground(Color.RED);
        warning.setBounds(250,350,270,50);
        // Add padding to the panel
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(createLabeledField("Imię:", nameField));
        panel.add(createLabeledField("Nazwisko:", surnameField));

        panel.add(createLabeledField("Hasło:", passwordField));
        panel.add(createLabeledField("PESEL:", peselField));

        panel.add(createLabeledField("Wiek:", ageField));
        panel.add(createLabeledField("Płeć:", sexComboBox));

        panel.add(createLabeledField("Email:", emailField));

        // Create a separate panel for buttons with FlowLayout
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(returnButton);
        buttonsPanel.add(submitButton);

        // Add the buttonsPanel to the main panel
        panel.add(buttonsPanel);

        panel.add(warning);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage();

                // Implement the logic to return to the previous window
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean peselB = true;
                boolean pesel1B = true;
                boolean hasloB = true;
                boolean imieB = true;
                boolean nazwiskoB = true;
                boolean emailB = true;
                boolean insert = true;

                warning.setVisible(false);


                String malpa = "@";

                String select =new String();
                String odpowiedz = new String();
                String name = nameField.getText();
                String surname = surnameField.getText();
                String password = new String(passwordField.getPassword());
                String pesel = peselField.getText();
                String age = ageField.getText();
                int wiek = Integer.parseInt(age);
                String sex = (String) sexComboBox.getSelectedItem();
                String email = emailField.getText();



                Client klient = new Client();
                klient.zapytanie("REGISTER",pesel);

                if(klient.odpowiedzOdSerwera.equals("false"))
                {
                    Main.logr.info("uzytkownik podczas rejestracji podaje istniejący juz w systemie numer pesel");
                    warning.setText("Taki uzytkownik juz istnieje !");
                    warning.setVisible(true);
                    pesel1B = false;
                }
                else
                {
                    pesel1B = true;
                }
                if(name.isEmpty())
                {
                    Main.logr.info("uzytkownik podczas rejestracji nie podal imienia");
                    warning.setText("Proszę podac imię !");
                    warning.setVisible(true);
                    imieB = false;
                }
                if(surname.isEmpty())
                {
                    Main.logr.info("uzytkownik podczas rejestracji nie podal nazwiska");
                    warning.setText("Prosze podać nazwisko !");
                    warning.setVisible(true);
                    nazwiskoB = false;
                }
                if(pesel.length()!=11)
                {

                        Main.logr.info("uzytkownik podczas rejestracji podaje błędny numer pesel");
                        warning.setText("Błędny numer pesel !");
                        warning.setVisible(true);
                        peselB = false;

                }
                if(password.length()<10)
                {
                    Main.logr.info("uzytkownik podczas rejestracji podaje haslo nie pasujące do standardów aplikacji");
                    warning.setText("Haslo powinno zawierac co najmniej 10 znakow !");
                    warning.setVisible(true);
                    hasloB = false;
                }
                if(email.contains(malpa) == false)
                {
                    Main.logr.info("uzytkownik podczas rejestracji podaje błędny adres email");
                    warning.setText("Błędny adres email !");
                    warning.setVisible(true);
                    emailB = false;
                }
                if(imieB == true && nazwiskoB  == true && emailB == true && hasloB == true && pesel1B == true && peselB == true)
                {



                    Main.logr.info("uzytkownik podejmuje próbę rejestracji");
                    System.out.println(email);
                    Main.logr.info("uzytkownik przeszedł pierwszy etap rejestracji");



                    int kodWyslany = generateAuthenticationCode();



                    EmailSenderService emailSenderService = new EmailSenderService();
                    emailSenderService.sendEmail(email,"Authentication Code",kodWyslany);



                    Main.logr.info("wyslany kod na email to: "+kodWyslany);
                    RegisterSucces registerSucces = new RegisterSucces(pesel,password,name,surname,email, kodWyslany,wiek,sex);

                    dispose();


                }


            }
        });

        add(panel);
        pack();
        setSize(600,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to create a labeled field with consistent styling
    private JPanel createLabeledField(String label, JComponent component) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setHorizontalAlignment(JLabel.RIGHT);
        fieldLabel.setPreferredSize(new Dimension(100, fieldLabel.getPreferredSize().height));
        fieldPanel.add(fieldLabel, BorderLayout.WEST);
        fieldPanel.add(component, BorderLayout.CENTER);
        return fieldPanel;
    }
    public int generateAuthenticationCode() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int kodzik = random.nextInt(max - min + 1) + min;
        return kodzik;
    }

}
