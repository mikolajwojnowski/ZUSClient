package org.example;
import com.itextpdf.text.DocumentException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * klasa generuje informacje o akutalnej oraz przewidywanej emeryturze
 */
public class Informacje extends JFrame implements ActionListener
{
    JButton wygenerujDoPdf = new JButton("Wygeneruj do PDF");
    JButton powrot = new JButton();
    String pesel;
    String plec;
    String path;
    String wiek;

    String email;
    String haslo;
    String imie;
    String nazwisko;



    JLabel wygRaport = new JLabel();
    JPanel raportPanel = new JPanel();

    JLabel utworzonoLabel = new JLabel();
    ImageIcon powrotIcon = new ImageIcon("pliki/powrot.png");

    String raportHtml;
    String raport;

    public Informacje(String pesel) throws IOException, InterruptedException
    {
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pliki/tlo.jpg")))));

        this.pesel = pesel;

        this.generujRaport();
        Font buttonFont = new Font("Arial", Font.PLAIN, 12);
        // Set the text and style for the report label
        wygRaport.setText(raportHtml);
        wygRaport.setForeground(Color.black);
        wygRaport.setBounds(10, 0, 300, 400);
        wygRaport.setFont(buttonFont);
        // Style the "Wygeneruj do PDF" button
        wygenerujDoPdf.setBounds(490, 340, 140, 64);
        wygenerujDoPdf.addActionListener(this);

        // Style the report panel
        raportPanel.setBounds(210, 45, 300, 430);
        raportPanel.setOpaque(false); // Make the panel transparent
        raportPanel.add(wygRaport);

        // Style the "Powrot" button
        powrot.addActionListener(this);
        powrot.setIcon(powrotIcon);
        powrot.setBounds(30, 300, 80, 80);
        powrot.setOpaque(false);
        powrot.setContentAreaFilled(false);
        powrot.setBorderPainted(false);

        // Style the "Utworzono" label
        utworzonoLabel.setBounds(220, 430, 140, 40);
        utworzonoLabel.setForeground(Color.cyan);
        utworzonoLabel.setVisible(false);

        // Add components to the frame
        this.add(utworzonoLabel);
        this.add(raportPanel);
        this.add(powrot);
        this.add(wygenerujDoPdf);

        // Set frame properties
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(720, 520);
        //this.setUndecorated(true); // Remove window decorations (title bar, etc.)
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    String generujRaport() throws InterruptedException {
        Main.logr.info("uzytkownik korzysta z generatora raportów");

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
            plec = data[5];
            wiek = data[4];

        }else
        {
            Main.logr.info("nie ma danych dla takiego peselu");
        }
        System.out.println("imie" + imie);
        System.out.println("naziwsko" + nazwisko);
        System.out.println("email" + email);
        System.out.println("wiek" + wiek);
        System.out.println("plec" + plec);



        String commandType2 ="WPLATY_INFO";
        Client client1 = new Client();
        client1.zapytanie(commandType2,pesel);
        String stanKonta = client1.odpowiedzOdSerwera;
        System.out.println(stanKonta);


        String commandType3="WPLATY_INFO_AVG";
        Client client2 = new Client();
        client2.zapytanie(commandType3,pesel);
        String avgWplata = client2.odpowiedzOdSerwera;
        System.out.println(avgWplata);
        double avg = Double.parseDouble(avgWplata);


        TimeUnit.MICROSECONDS.sleep(220);

        String format;
        int lataDoEmerytury;
        int wiekEmerytalny;

        double obecnaEmerytura;
        double przewidywanaEmerytura;

        if(plec.equals("Kobieta"))
        {
            format = " Zgromadziła Pani na koncie Zus : ";
            wiekEmerytalny = 60;
            lataDoEmerytury = 60 - Integer.parseInt(wiek);
            obecnaEmerytura = Double.parseDouble(stanKonta) / 238;
        }
        else
        {
            format = " Zgromadził Pan na koncie Zus : ";
            wiekEmerytalny = 65;
            lataDoEmerytury = 65 - Integer.parseInt(wiek);
            obecnaEmerytura = Double.parseDouble(stanKonta) / 217;

        }

        System.out.println(obecnaEmerytura);
        przewidywanaEmerytura = lataDoEmerytury * 12;

        przewidywanaEmerytura = avg * przewidywanaEmerytura;
        przewidywanaEmerytura = przewidywanaEmerytura + Integer.parseInt(stanKonta);
        przewidywanaEmerytura = przewidywanaEmerytura /245;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        raport = "Na stan z dnia: " + LocalDate.now() + format + stanKonta + " zl.  Wysokosc miesiecznej emerytury na dzien dzisiejszy to " + decimalFormat.format(obecnaEmerytura) + "zl" +
                "Przewidywana miesieczna emerytura, obliczona w oparciu o aktualny stan konta  oraz wysokość miesiecznych skladek to " + decimalFormat.format(przewidywanaEmerytura) + "zl";

        raportHtml = "<html> ZUS  <br/> Na stan z dnia: " + LocalDate.now() +"<br/>" + format + stanKonta + " zł. <br/> Wysokość miesięcznej emerytury<br/> na dzien dzisiejszy to " + decimalFormat.format(obecnaEmerytura) + "zł. <br/>" +
                "Przewidywana miesięczna emerytura, obliczona<br/> w oparciu o aktualny stan konta <br/> oraz wysokość miesięcznych składek to " + decimalFormat.format(przewidywanaEmerytura) + "zł<br/>"
                + "Dyrektor Główny ZUS Mikołaj Wojnowski";

        return raport;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()== powrot)
        {
            Main.logr.info("uzytkownik wraca do menu glownego");

            this.dispose();

        }
        if(e.getSource()==wygenerujDoPdf)
        {
            Main.logr.info("uzytkownik generuje raport w postaci pdf");

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


            chooser.setAcceptAllFileFilterUsed(false);


            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                path = String.valueOf(chooser.getCurrentDirectory());

                path = path.replace("\\","\\\\");

                PdfMaker make = new PdfMaker(path,raport);

                utworzonoLabel.setVisible(true);

                raportPanel.add(utworzonoLabel);

                make.wygenerujRaport();
            }
        }
    }

}

