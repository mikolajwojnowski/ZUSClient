package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class RegistrationPanel extends JFrame {

    IDandPassword iDandPassword = new IDandPassword();
    private JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
    private JTextField nameField = new JTextField();
    private JTextField surnameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JTextField peselField = new JTextField();
    private JTextField ageField = new JTextField();
    private JComboBox<String> sexComboBox = new JComboBox<>(new String[]{"Male", "Female"});
    private JTextField emailField = new JTextField();
    private JButton submitButton = new JButton("Submit");
    private JButton returnButton = new JButton("Return");

    public RegistrationPanel() {
        setTitle("Enhanced Registration Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add padding to the panel
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(createLabeledField("Name:", nameField));
        panel.add(createLabeledField("Surname:", surnameField));

        panel.add(createLabeledField("Password:", passwordField));
        panel.add(createLabeledField("PESEL:", peselField));

        panel.add(createLabeledField("Age:", ageField));
        panel.add(createLabeledField("Sex:", sexComboBox));

        panel.add(createLabeledField("Email:", emailField));

        // Create a separate panel for buttons with FlowLayout
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(returnButton);
        buttonsPanel.add(submitButton);

        // Add the buttonsPanel to the main panel
        panel.add(buttonsPanel);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginPage loginPage = new LoginPage(iDandPassword.getLoginInfo());

                // Implement the logic to return to the previous window
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String password = new String(passwordField.getPassword());
                String pesel = peselField.getText();
                String age = ageField.getText();
                String sex = (String) sexComboBox.getSelectedItem();
                String email = emailField.getText();

                // Placeholder for registration logic
                String message = "Name: " + name + "\nSurname: " + surname + "\nPassword: " + password +
                        "\nPESEL: " + pesel + "\nAge: " + age + "\nSex: " + sex + "\nEmail: " + email;

                JOptionPane.showMessageDialog(RegistrationPanel.this, message, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationPanel();
        });
    }
}
