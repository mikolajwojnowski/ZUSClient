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

    String email;
    String pensja;
    String imie;
    String nazwisko;
    JFrame frame = new JFrame();
    //JLayeredPane layeredPane = new JLayeredPane();

    //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    ImageIcon kalkulatorIcon = new ImageIcon("pliki/kalkulator.png");
    ImageIcon icon1 = new ImageIcon("pliki/bill.png");
    JButton skladkaZdrowotnaButton = new JButton("Opłać składkę emerytalną", icon1);

    JButton skladkaEmerytalnaButton = new JButton("Opłać składkę zdrowtoną",icon1);


    JButton kalkulatorButton = new JButton("Kalkulator składki zdrowotnej",kalkulatorIcon);
    ImageIcon icon2 = new ImageIcon("pliki/upload.png");
    JButton wyslijPodanieButton = new JButton("Wyślij podanie", icon2);

    ImageIcon icon3 = new ImageIcon("pliki/pobierzPodanie.png");
    JButton pobierzPodanieButton = new JButton("Pobierz Podanie", icon3);

    ImageIcon icon4 = new ImageIcon("pliki/information.png");
    JButton RaportEmerytalnyButton = new JButton("Raport Emerytalny", icon4);

    ImageIcon icon5 = new ImageIcon("pliki/user.png");
    JButton profileButton = new JButton("Profil", icon5);

    ImageIcon icon6 = new ImageIcon("pliki/logout.png");
    JButton logoutButton = new JButton("Wyloguj Się", icon6);

    //ImageIcon icon7 = new ImageIcon("pliki/powrot.png");
    //JButton returnButton = new JButton("Powrót", icon7);
    JButton informacjeButton= new JButton("Informacje");
    ImageIcon backgroundImage = new ImageIcon("pliki/tlo.jpg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    JLabel centerPanel = new JLabel();
    //JLabel westPanel = new JLabel();
    JPanel westPanel = new JPanel();
    JPanel northPanel = new JPanel();

    Font buttonFont = new Font("Arial", Font.BOLD, 10);
    public MainMenu(String pesel) {

        this.imie = imie;
        this.nazwisko=nazwisko;
        this.email = email;
        this.pesel = pesel;

        skladkaZdrowotnaButton.setSize(new Dimension(100,100));
        kalkulatorButton.setPreferredSize(new Dimension(40,40));
        wyslijPodanieButton.setPreferredSize(new Dimension(40,40));
        pobierzPodanieButton.setPreferredSize(new Dimension(40,40));
        RaportEmerytalnyButton.setPreferredSize(new Dimension(40,40));
        skladkaEmerytalnaButton.setSize(new Dimension(40,40));

        skladkaZdrowotnaButton.setFont(buttonFont);
        //button1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        //button1.setHorizontalAlignment(SwingConstants.CENTER);
        skladkaZdrowotnaButton.setHorizontalTextPosition(SwingConstants.LEADING);
        wyslijPodanieButton.setHorizontalTextPosition(SwingConstants.LEADING);
        kalkulatorButton.setHorizontalTextPosition(SwingConstants.LEADING);
        pobierzPodanieButton.setHorizontalTextPosition(SwingConstants.LEADING);
        RaportEmerytalnyButton.setHorizontalTextPosition(SwingConstants.LEADING);
        skladkaEmerytalnaButton.setHorizontalTextPosition(SwingConstants.LEADING);

        skladkaEmerytalnaButton.setFont(buttonFont);
        wyslijPodanieButton.setFont( buttonFont);
        kalkulatorButton.setFont(buttonFont);
        pobierzPodanieButton.setFont( buttonFont);
        RaportEmerytalnyButton.setFont( buttonFont);
        logoutButton.setFont(buttonFont);
        profileButton.setFont(buttonFont);
        informacjeButton.setFont(buttonFont);

        //actions listener
        skladkaZdrowotnaButton.addActionListener(this);
        wyslijPodanieButton.addActionListener(this);
        kalkulatorButton.addActionListener(this);
        pobierzPodanieButton.addActionListener(this);
        RaportEmerytalnyButton.addActionListener(this);
        skladkaEmerytalnaButton.addActionListener(this);
        profileButton.addActionListener(this);
        logoutButton.addActionListener(this);
        informacjeButton.addActionListener(this);



        frame.setLayout(new BorderLayout(30,30));
        frame.add(logoutButton,BorderLayout.SOUTH);
        //frame.add(returnButton,BorderLayout.WEST);
        frame.add(profileButton,BorderLayout.EAST);
        frame.add(informacjeButton,BorderLayout.EAST);

        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(westPanel,BorderLayout.WEST);
        frame.add(northPanel,BorderLayout.NORTH);

        centerPanel.setLayout(new GridLayout(3,2,100,60));
        centerPanel.add(skladkaZdrowotnaButton);
        centerPanel.add(wyslijPodanieButton);
        centerPanel.add(kalkulatorButton);
        centerPanel.add(pobierzPodanieButton);
        //centerPanel.add(RaportEmerytalnyButton);
        //centerPanel.add(skladkaEmerytalnaButton);

        westPanel.setLayout(new GridLayout(4,1,10,10));

        westPanel.add(profileButton);
        westPanel.add(informacjeButton);


        frame.setTitle("ZUS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions if needed
        //button 1 = opłać składke
        if(e.getSource() == skladkaZdrowotnaButton)
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
            ProfileInformationWindow profileInformationWindow = new ProfileInformationWindow(pesel,true);
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
        if(e.getSource() == pobierzPodanieButton)
        {
            Main.logr.info("uzytkownik dokonuje próby uruchomienia modułu odpowiedzialnego za pobieranie podań");

            try {
                Podanie podanie = new Podanie(pesel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        //wyslij podanie
        if(e.getSource() == wyslijPodanieButton)
        {
            Main.logr.info("uzytkownik dokonuje próby uruchomienia modułu odpowiedzialnego za wysylanie podań");

            try {
                PodanieWyslij podanie = new PodanieWyslij(pesel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==kalkulatorButton)
        {
            Main.logr.info("uzytkownik dokonuje proby uruchomienia modulu odpowiedzialnego za kalkulator skladki");
            KalkulatorSkladki kalkulatorSkladki = new KalkulatorSkladki(pesel);
        }
        if (e.getSource() == informacjeButton)
        {
            try {
                Informacje informacje = new Informacje(pesel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }



    }






}
