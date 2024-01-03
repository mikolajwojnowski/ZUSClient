package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainMenu extends JFrame implements ActionListener {

    String rokUrodzenia;
    String miesiacUrodzenia;
    String dzienUrodzenia;
    String plec;
    String wiek;
    String pesel;

    String pensja;
    String imie;
    String nazwisko;
    JFrame frame = new JFrame();
    //JLayeredPane layeredPane = new JLayeredPane();

    //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    ImageIcon icon1 = new ImageIcon("pliki/bill.png");
    JButton button1 = new JButton("Opłać składkę", icon1);

    ImageIcon icon2 = new ImageIcon("pliki/upload.png");
    JButton button2 = new JButton("Wyślij podanie", icon2);

    ImageIcon icon3 = new ImageIcon("pliki/pobierzPodanie.png");
    JButton button3 = new JButton("Pobierz Podanie", icon3);

    ImageIcon icon4 = new ImageIcon("pliki/information.png");
    JButton button4 = new JButton("Raport Emerytalny", icon4);

    ImageIcon icon5 = new ImageIcon("pliki/user.png");
    JButton profileButton = new JButton("Profil", icon5);

    ImageIcon icon6 = new ImageIcon("pliki/logout.png");
    JButton logoutButton = new JButton("Wyloguj Się", icon6);

    //ImageIcon icon7 = new ImageIcon("pliki/powrot.png");
    //JButton returnButton = new JButton("Powrót", icon7);

    ImageIcon backgroundImage = new ImageIcon("pliki/tlo.jpg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    JLabel centerPanel = new JLabel();
    //JLabel westPanel = new JLabel();
    JPanel westPanel = new JPanel();
    JPanel northPanel = new JPanel();

    Font buttonFont = new Font("Arial", Font.BOLD, 14);
    public MainMenu(String pesel) {


        button1.setSize(new Dimension(100,100));
        button2.setPreferredSize(new Dimension(40,40));
        button3.setPreferredSize(new Dimension(40,40));
        button4.setPreferredSize(new Dimension(40,40));

        button1.setFont(buttonFont);
        //button1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //button1.setHorizontalAlignment(SwingConstants.CENTER);
        button1.setHorizontalTextPosition(SwingConstants.LEADING);
        button2.setHorizontalTextPosition(SwingConstants.LEADING);
        button3.setHorizontalTextPosition(SwingConstants.LEADING);
        button4.setHorizontalTextPosition(SwingConstants.LEADING);

        button2.setFont( buttonFont);
        button3.setFont( buttonFont);
        button4.setFont( buttonFont);
        logoutButton.setFont(buttonFont);
        profileButton.setFont(buttonFont);

        //actions listener
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        profileButton.addActionListener(this);
        logoutButton.addActionListener(this);




        frame.setLayout(new BorderLayout(50,50));
        frame.add(logoutButton,BorderLayout.SOUTH);
        //frame.add(returnButton,BorderLayout.WEST);
        frame.add(profileButton,BorderLayout.EAST);

        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(westPanel,BorderLayout.WEST);
        frame.add(northPanel,BorderLayout.NORTH);

        centerPanel.setLayout(new GridLayout(2,2,100,100));
        centerPanel.add(button1);
        centerPanel.add(button2);
        centerPanel.add(button3);
        centerPanel.add(button4);

        westPanel.setLayout(new GridLayout(4,1,10,10));

        westPanel.add(profileButton);


        frame.setTitle("ZUS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions if needed
        //button 1 = opłać składke
        if(e.getSource() == button1)
        {
            Main.logr.info("uzytkownik dokonuje próby uruchomienia modułu odpowiedzialnego za opłacanie składek");
            this.dispose();
            try {
                Oplata oplata = new Oplata(pesel,"10000");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == profileButton)
        {
            JOptionPane.showMessageDialog(this, "Profile information", "Profile", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource() == logoutButton)
        {
            // Implement the logic to log out
            JOptionPane.showMessageDialog(this, "Logging out...", "Log Out", JOptionPane.INFORMATION_MESSAGE);
            // You can close this window or navigate to the login screen
            frame.dispose();
            System.exit(0);

        }
        //button 3 = pobierz podanie
        if(e.getSource() == button3)
        {
            Main.logr.info("uzytkownik dokonuje próby uruchomienia modułu odpowiedzialnego za pobieranie podań");
            frame.dispose();
            try {
                Podanie podanie = new Podanie(pesel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == button2)
        {
            Main.logr.info("uzytkownik dokonuje próby uruchomienia modułu odpowiedzialnego za wysylanie podań");
            frame.dispose();
            try {
                PodanieWyslij podanie = new PodanieWyslij(pesel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }



    }






}
