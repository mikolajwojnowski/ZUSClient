package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Register extends JFrame implements ActionListener{
    private JLabel JLFirstName;
    private JTextField textField1;
    private JButton SubmitButton;
    private JPanel RegisterPanel;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton returnButton;
    HashMap<String, String> logininfo;
    IDandPassword iDandPassword = new IDandPassword();

    Register()
    {

        setContentPane(RegisterPanel);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setVisible(true);

        returnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == returnButton)
                {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                }
                if(e.getSource() == SubmitButton)
                {
                    JOptionPane.showMessageDialog(Register.this,"Zarejestrowano");
                }
            }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

