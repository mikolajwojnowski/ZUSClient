package org.example;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.BorderLayout;

public class LoginPage implements ActionListener {


    IDandPassword iDandPassword = new IDandPassword();
    JFrame frame = new JFrame();

    JButton loginButton = new JButton("Zaloguj");

    JButton resetButton = new JButton("Zresetuj");

    JButton registerButton = new JButton("Zarejestruj");

    JTextField userIDField = new JTextField(20);


    JPasswordField userPasswordField = new JPasswordField(20);

    JLabel userIDLabel = new JLabel("PESEL");
    JLabel userPasswordLabel = new JLabel("Hasło");

    JLabel messageLabel = new JLabel();

    JCheckBox checkBox = new JCheckBox("Zapamiętaj mnie");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();

    JPanel GridPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    ImageIcon ZusIcon = new ImageIcon("pliki/logozus.png");
    JLabel ZusLabel = new JLabel(ZusIcon);
    HashMap<String,String> logininfo = new HashMap<>();


    LoginPage(HashMap<String,String> loginInfoOriginal)
    {
        logininfo = loginInfoOriginal;

//        panel1.setBackground(Color.BLACK);
//        panel2.setBackground(Color.WHITE);
//        panel3.setBackground(Color.YELLOW);
//        panel4.setBackground(Color.magenta);
//        panel5.setBackground(Color.blue);


        panel1.setPreferredSize(new Dimension(100,250));
        panel2.setPreferredSize(new Dimension(100,100));
        panel3.setPreferredSize(new Dimension(40,100));
        panel4.setPreferredSize(new Dimension(50,100));
        panel5.setPreferredSize(new Dimension(100,20));

        panel1.add(ZusLabel);


        panel2.add(userIDLabel);
        panel2.add(userPasswordLabel);
        panel2.add(messageLabel);
        panel2.add(userIDField);
        panel2.add(userPasswordField);
        panel2.add(resetButton);
        panel2.add(loginButton);



        frame.setLayout(new BorderLayout());

        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel3,BorderLayout.EAST);
        frame.add(panel4,BorderLayout.WEST);
        frame.add(panel5,BorderLayout.SOUTH);




        gbc.insets = new Insets(5,5,5,5);
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
        //username and text fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        GridPanel.add(userIDLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        GridPanel.add(userIDField,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        GridPanel.add(userPasswordLabel,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        GridPanel.add(userPasswordField,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        GridPanel.add(checkBox,gbc);

        buttonsPanel.add(loginButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(registerButton);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        GridPanel.add(buttonsPanel,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        GridPanel.add(messageLabel);

        panel2.setLayout(new GridBagLayout());
        panel2.add(GridPanel);





        messageLabel.setFont(new Font(null, Font.BOLD,16));
        loginButton.setFocusable(false);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);


        frame.setBackground(Color.WHITE);
        frame.setTitle("Logowanie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocation(100,100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== resetButton)
        {
            userIDField.setText("");
            userPasswordField.setText("");
            messageLabel.setText("");
            checkBox.setSelected(false);

        }
        if(e.getSource() == loginButton)
        {

            Main.logr.info("uzytkownik podejmuje probe logowania");

            String pesel = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword()); //password

            if(logininfo.containsKey(userID))
            {
                if(logininfo.get(userID).equals(userPass))
                {
                    Main.logr.info("haslo prawidlowe");
                    System.out.println("Sukces");
//                    messageLabel.setForeground(Color.GREEN);
//                    messageLabel.setText("Login successful");
                    frame.dispose();
                    MainMenu menu = new MainMenu();

                }
                else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Błędne hasło");
                }

            }
            else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Błędny PESEL");
            }

        }
        if(e.getSource() == registerButton)
        {
            Main.logr.info("uzytkownik podejmuje probe zarejestrowania");
            frame.dispose();
            //Register register = new Register();
            RegistrationPanel registrationPanel = new RegistrationPanel();

        }
    }
}
