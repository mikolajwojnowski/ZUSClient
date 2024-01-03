package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Jest to klasa ktorej obiekt zostaje utworzony przy pomyslnym wypelnieniu formularza rejestracji
 *
 */

public class RegisterSucces extends JFrame implements ActionListener
{

    int kod;
    String pesel;
    String haslo;
    String imie;
    String nazwisko;
    String email;

    JTextField kodWerf = new JTextField();
    JLabel tekst = new JLabel();
    JPanel greenPanel = new JPanel();

    JLabel twojeDane = new JLabel();

    JLabel twojeDane2 = new JLabel();
    JPanel bgPanel = new JPanel();
    JPanel textPanel = new JPanel();

    JButton aktywuj = new JButton("Aktywacja");


    /**
     * @param pesel    pole opisuje pesel podatnika
     * @param haslo    pole opisuje haslo podatnika
     * @param imie     pole opisuje imie podatnika
     * @param nazwisko pole opisuje nazwisko podatnika
     * @param email    pole opisuje email podatnika
     */
    public RegisterSucces(String pesel, String haslo, String imie, String nazwisko, String email,int kod)
    {
        Main.logr.info("do uzytkownika wyslano emial potwierdzajacy");
        this.kod = kod;
        this.pesel = pesel;
        this.haslo = haslo;
        this.nazwisko = nazwisko;
        this.email = email;
        this.imie = imie;

        tekst.setText("ZAKŁAD UBEZPIECZEN SPOŁECZNYCH");
        tekst.setFont(new Font("Arial",Font.BOLD,30));
        tekst.setForeground(new Color(0,208,0));

        String dane1 = new String();
        dane1 = "Witaj " + imie + " " + nazwisko;
        String dane2 = new String("Na twoj adres email " + email + " zostal wyslany kod weryfikacjyny.");

        twojeDane.setText(dane1);
        twojeDane.setFont(new Font("Arial",Font.BOLD,20));

        twojeDane2.setText(dane2);
        twojeDane2.setFont(new Font("Arial",Font.BOLD,12));

        bgPanel.setBackground(new Color(146,227,151));
        bgPanel.setBounds(0,100,500,400);
        bgPanel.setLayout(new FlowLayout());
        bgPanel.add(twojeDane);
        bgPanel.add(twojeDane2);

        greenPanel.setBackground(new Color(5,111, 78));
        greenPanel.setBounds(0,0,500,100);
        greenPanel.add(tekst);

        textPanel.setBounds(200,300,100,40);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(kodWerf);

        aktywuj.setBounds(200,400,100,40);
        aktywuj.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.add(aktywuj);
        this.add(textPanel);
        this.add(bgPanel);
        this.add(greenPanel);

    }

    /** metoda dodaje nowego uzytkownia do systemu po sprawdzeniu czy kod podany przez uzytkownika jest taki sam jak ten wyslany na email
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== aktywuj)
        {
            int kodText = Integer.parseInt(kodWerf.getText());
            if(kod == kodText)
            {
                Main.logr.info("uzytkownik przeszedł drugi etap rejestracji");
                String insertion = "INSERT into podatnik values ( "+pesel+", '" + haslo +"' , '" + email + "' , '" + imie + " ' , '" + nazwisko + " ')";
                String insertion_02 = "INSERT into WARTOSC values ( '" + pesel+ "'," +0 + ")";
                Client wstaw2 = new Client();
                Client wstaw = new Client();
                wstaw.zapytanie(insertion);
                try {
                    TimeUnit.MILLISECONDS.sleep(350);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                wstaw2.zapytanie(insertion_02);

                this.dispose();
                LoginPage logowanie = new LoginPage();


            }
        }
    }


}
