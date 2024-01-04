package org.example;

import javax.swing.*;
import java.awt.*;

public class ProfileInformationWindow extends JFrame {

    private JTextField emailTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JPasswordField passwordField;
    private JTextField peselField;

    public ProfileInformationWindow(String email, String name, String surname, String pesel, String password, boolean isViewMode) {
        setTitle("Profile Information");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI(isViewMode);

        // Set data if not in view mode
        if (!isViewMode) {
            emailTextField.setText(email);
            nameTextField.setText(name);
            surnameTextField.setText(surname);
            passwordField.setText(password);
            peselField.setText(pesel);
        }

        setVisible(true);
    }

    private void createUI(boolean isViewMode) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField("email");
        emailTextField.setEditable(!isViewMode);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField("name");
        nameTextField.setEditable(!isViewMode);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameTextField = new JTextField("surname");

        surnameTextField.setEditable(!isViewMode);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField("password");
        passwordField.setEditable(!isViewMode);

        JLabel peselLabel = new JLabel("Pesel:");
        peselField = new JTextField("pesel");
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

        if (!isViewMode) {
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> saveProfileInformation());
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(saveButton);
        }

        add(panel);
    }

    private void saveProfileInformation() {
        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        char[] password = passwordField.getPassword(); // Note: For better security, consider using other components for password input.

        // TODO: Add logic to save the profile information (e.g., to a database or file)

        // Display a message (you can replace this with your saving logic)
        JOptionPane.showMessageDialog(this, "Profile information saved successfully.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage: create an instance for view mode
            new ProfileInformationWindow("example@gmail.com", "John", "Doe", "123456789", "password", true);
        });
    }
}
