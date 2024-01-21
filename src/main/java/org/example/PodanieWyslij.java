package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Klasa dostepna z menu glowengo, odpowiada za utworzenie okna programu pozwalajacego na wybranie i wyslanie podania
 * w postaci pliku pdf do serwera
 */
public class PodanieWyslij extends JFrame implements ActionListener

{
    String pesel;
    JPanel cofnij = new JPanel();
    JLabel cofnijTxt = new JLabel("POWRÓT");
    JLabel wyslijText = new JLabel("WYŚLIJ");
    JPanel formatPdf = new JPanel();
    JLabel pdf = new JLabel("Plik powinien byc w formacie PDF");
    char[] sprawdzenie = new char[3];
    ImageIcon upload = new ImageIcon("pliki/uploading.png");
    String plikNazwa = "nazwa pliku";
    JLabel nazwaPlikuLabel = new JLabel();
    JPanel nazwaPlikuPanel = new JPanel();
    String filePath;
    JButton wyslij = new JButton();
    JButton wybierzPlik = new JButton("Wybierz plik");
    ImageIcon icon = new ImageIcon("pliki/powrot.png");
    JButton powrot = new JButton();

    /**
     *
     * @param pesel numer pesel przekazywany przez menu glowne
     * @throws IOException
     */
    public PodanieWyslij(String pesel) throws IOException {

        this.pesel = pesel;
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pliki/tlo.jpg")))));

        cofnijTxt.setFont(new Font(Font.DIALOG,Font.PLAIN,17));
        cofnij.setBounds(30,380,80,40);
        wyslijText.setFont(new Font(Font.DIALOG,Font.PLAIN,17));
        wyslij.add(wyslijText);
        cofnij.add(cofnijTxt);
        cofnij.setOpaque(false);
        pdf.setFont(new Font("Arial",Font.BOLD,17));
        pdf.setForeground(Color.BLACK);
        formatPdf.add(pdf);
        formatPdf.setBounds(100,220,400,40);
        formatPdf.setBackground(new Color(101,181,60));
        formatPdf.setVisible(false);
        formatPdf.setOpaque(false);
        nazwaPlikuPanel.setBounds(140,150,400,40);
        nazwaPlikuPanel.add(nazwaPlikuLabel);
        nazwaPlikuPanel.setBackground(Color.gray);
        nazwaPlikuPanel.setBorder(BorderFactory.createLineBorder(Color.black,4));
        wybierzPlik.setBounds(550,150,150,40);
        wybierzPlik.addActionListener(this);
        wybierzPlik.setBackground(new Color(60,160,81));
        wybierzPlik.setFocusable(false);
        wybierzPlik.setOpaque(false);
        wybierzPlik.setContentAreaFilled(false);
        wyslij.setBounds(500,300,64,64);
        wyslij.setIcon(upload);
        wyslij.addActionListener(this);
        wyslij.setBackground(new Color(101,181,60));
        wyslij.setFocusable(false);
        wyslij.setOpaque(false);
        wyslij.setContentAreaFilled(false);
        powrot.addActionListener(this);
        powrot.setBackground(Color.green);
        powrot.setIcon(icon);
        powrot.setBounds(30,300,80,80);
        powrot.setBackground(new Color(69,164,82));
        powrot.setFocusable(false);
        powrot.setOpaque(false);
        powrot.setContentAreaFilled(false);
        this.add(formatPdf);
        this.add(wybierzPlik);
        this.setLocationRelativeTo(null);
        this.add(nazwaPlikuPanel);
        this.setResizable(false);
        this.add(wyslij);
        this.add(powrot);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(720,520);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(formatPdf);
        this.add(cofnij);

    }

    /**
     * metoda tworzy JfileChooser w celu wybrania przez uzytkownika pliku który ma byc wyslany do serwera
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== powrot)
        {
            Main.logr.info("uzytkownik wraca do menu glownego");
            MainMenu menu = new MainMenu(pesel);
            this.dispose();
        }
        if(e.getSource() == wybierzPlik )
        {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION)
            {
                filePath = fileChooser.getSelectedFile().getAbsolutePath();
                Main.logr.info("uzytkownik wybiera plik do wyslania");
            }
            plikNazwa = filePath;
            nazwaPlikuLabel.setText(plikNazwa);
        }
        if(e.getSource() == wyslij)
        {
            sprawdzenie[0] =(plikNazwa.charAt(plikNazwa.length() -3));
            sprawdzenie[1] = (plikNazwa.charAt(plikNazwa.length() -2));
            sprawdzenie[2] = (plikNazwa.charAt(plikNazwa.length() -1));
            System.out.println(sprawdzenie);

            if((sprawdzenie[0] == 'p') && (sprawdzenie[1]=='d')&&(sprawdzenie[2])=='f') {
                formatPdf.setVisible(false);
                Client client = new Client();

                int lastIndex = plikNazwa.lastIndexOf("\\");
                String result = plikNazwa.substring(lastIndex+1);


                String commandType = "WYSLIJ_PODANIE";
                String zapytanie = pesel + " "+ result;
                client.zapytanie(commandType,zapytanie);

                if(client.odpowiedzOdSerwera.equals("DodanoPodanie")){
                    Main.logr.info("dodano podanie");
                }
                else
                {
                    Main.logr.info("nie dodano podania");
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Main.logr.info("uzytkownik wysyła plik PDF");
                FileSender send = new FileSender(filePath);

            }
            else
            {
                Main.logr.info("uzytkownik podczas próby wyslania podania wyslal plik niezgodny z formatem PDF");
                formatPdf.setVisible(true);
            }
        }
    }
}
