package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ProfileInformationWindow  extends JFrame {

    private JTextField emailTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JPasswordField passwordField;
    private JTextField peselField;

    public ProfileInformationWindow(String pesel, boolean isViewMode) {
        setTitle("Profile Information");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String imie ="";
        String nazwisko="";
        String haslo="";
        String email="";

        Client client = new Client();
        String commandType = "PROFILE_INFO";

        client.zapytanie(commandType,pesel);

        String odpowiedz = client.odpowiedzOdSerwera;


        if(!odpowiedz.isEmpty())
        {
            String[] data = odpowiedz.split("\\s+");
            imie = data[1];
            nazwisko=data[2];
            email=data[0];
            haslo=data[3];
        }else
        {
            Main.logr.info("nie ma danych dla takiego peselu");
        }


        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField(email);
        emailTextField.setEditable(!isViewMode);

        JLabel nameLabel = new JLabel("Imie:");
        nameTextField = new JTextField(imie);
        nameTextField.setEditable(!isViewMode);

        JLabel surnameLabel = new JLabel("Nazwisko:");
        surnameTextField = new JTextField(nazwisko);

        surnameTextField.setEditable(!isViewMode);

        JLabel passwordLabel = new JLabel("Haslo:");
        passwordField = new JPasswordField(haslo);
        passwordField.setEditable(!isViewMode);

        JLabel peselLabel = new JLabel("Pesel:");
        peselField = new JTextField(pesel);
        peselField.setEditable(!isViewMode);

        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(peselLabel);
        panel.add(peselField);

        add(panel);
        setVisible(true);
    }




}
