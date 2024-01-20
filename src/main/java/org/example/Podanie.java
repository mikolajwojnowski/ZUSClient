package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Podanie extends JFrame implements ActionListener {

    String pesel;
    boolean renta, firma, pelnomocnictwo, zasilek = false;

    ImageIcon icon = new ImageIcon("pliki/powrot.png");
    JButton powrot = new JButton();
    JPanel zasilkiPanel = new JPanel();
    JPanel firmyPanel = new JPanel();
    JPanel rentyPanel = new JPanel();
    JPanel pelnomocnictwoPanel = new JPanel();

    JButton pobierz = new JButton("pobierz");
    JButton emAndRentButton = new JButton("Emerytury, renty");
    JButton zasilkiButton = new JButton("Zasiłki");
    JButton firmyButton = new JButton("Firmy");
    JButton pelnoMocnictwoButton = new JButton("Pełnomocnictwo");

    String[] pelnomocnictwaTab = {"", "PEL Pełnomocnictwo", "PEL-Z Załącznik do pełnomocnictwa", "PEL-K Upoważnienie do reprezentowania płatnika", "PEL-O Odwołanie Pełnomocnictwa"};
    JComboBox pelnomocnictwoBox = new JComboBox((pelnomocnictwaTab));
    String[] firmyTab = {"", "Rozliczenia", "Dochodzenie należności", "Ulgi i umorzenia", "Informacje dla ubezpieczonego"};
    JComboBox firmyBox = new JComboBox(firmyTab);
    String[] zasilkiTab = {"", "Zaświadzenie z ZUS", "Zasilek Chorobowy", "Zasiłek Macierzyński", "Zasiłek Pogrzebowy"};
    JComboBox zasilekBox = new JComboBox(zasilkiTab);
    String[] emAndRentTab = {"", "Wniosek O Emeryturę", "Wniosek O Rentę", "Wniosek O Ponowne Obliczenie Emerytury", "Wniosek O Rentę Rodzinną"};
    JComboBox emAndRentBox = new JComboBox(emAndRentTab);


    public Podanie(String pesel) throws IOException {
        Main.logr.info("uzytkownik uruchamia modul odpowiedzialny za pobieranie podan");
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pliki/tlo.jpg")))));
        this.pesel = pesel;
        powrot.addActionListener(this);
        powrot.setIcon(icon);
        powrot.setBounds(0, 400, 80, 80);
        powrot.setBackground(Color.white);
        powrot.setFocusable(false);

        pelnomocnictwoBox.addActionListener(this);
        firmyBox.addActionListener(this);
        zasilekBox.addActionListener(this);
        emAndRentBox.addActionListener(this);
        emAndRentButton.setBounds(0, 0, 179, 80);
        emAndRentButton.addActionListener(this);
        zasilkiButton.setBounds(180, 0, 179, 80);
        zasilkiButton.addActionListener(this);
        firmyButton.setBounds(360, 0, 179, 80);
        firmyButton.addActionListener(this);
        pelnoMocnictwoButton.setBounds(540, 0, 179, 80);
        pelnoMocnictwoButton.addActionListener(this);
        emAndRentButton.setBackground(Color.gray);
        zasilkiButton.setBackground(Color.GRAY);
        firmyButton.setBackground(Color.gray);
        pelnoMocnictwoButton.setBackground(Color.gray);
        pobierz.setBounds(500, 300, 100, 40);
        pobierz.addActionListener(this);
        zasilkiPanel.setBounds(90, 170, 540, 30);
        zasilkiPanel.setLayout(new BorderLayout());
        zasilkiPanel.setBackground(Color.green);
        zasilkiPanel.add(zasilekBox);
        zasilkiPanel.setVisible(false);
        rentyPanel.setBounds(90, 170, 540, 30);
        rentyPanel.setLayout(new BorderLayout());
        rentyPanel.setBackground(Color.green);
        rentyPanel.add(emAndRentBox);
        rentyPanel.setVisible(false);
        firmyPanel.setBounds(90, 170, 540, 30);
        firmyPanel.setLayout(new BorderLayout());
        firmyPanel.setBackground(Color.green);
        firmyPanel.add(firmyBox);
        firmyPanel.setVisible(false);
        pelnomocnictwoPanel.setBounds(90, 170, 540, 30);
        pelnomocnictwoPanel.setLayout(new BorderLayout());
        pelnomocnictwoPanel.setBackground(Color.green);
        pelnomocnictwoPanel.add(pelnomocnictwoBox);
        pelnomocnictwoPanel.setVisible(false);
        this.setResizable(false);
        this.add(emAndRentButton);
        this.add(zasilkiButton);
        this.add(firmyButton);
        this.add(pelnoMocnictwoButton);
        this.add(pelnomocnictwoPanel);
        this.add(rentyPanel);
        this.add(firmyPanel);
        this.add(zasilkiPanel);
        this.add(pobierz);
        this.add(powrot);
        this.setLayout(null);
        this.setSize(720, 520);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == powrot) {
            this.dispose();
            MainMenu menu = new MainMenu(pesel);

        }
        if (e.getSource() == emAndRentButton) {
            emAndRentButton.setBackground(new Color(102, 204, 0));
            zasilkiButton.setBackground(Color.GRAY);
            firmyButton.setBackground(Color.gray);
            pelnoMocnictwoButton.setBackground(Color.gray);
            rentyPanel.setVisible(true);
            rentyPanel.setEnabled(false);
            pelnomocnictwoPanel.setVisible(false);
            firmyPanel.setVisible(false);
            zasilkiPanel.setVisible(false);
            renta = true;
            zasilek = false;
            pelnomocnictwo = false;
            firma = false;

        }
        if (e.getSource() == zasilkiButton) {
            zasilkiButton.setBackground(new Color(102, 204, 0));
            emAndRentButton.setBackground(Color.GRAY);
            firmyButton.setBackground(Color.gray);
            pelnoMocnictwoButton.setBackground(Color.gray);
            rentyPanel.setVisible(false);
            pelnomocnictwoPanel.setVisible(false);
            firmyPanel.setVisible(false);
            zasilkiPanel.setVisible(true);
            renta = false;
            zasilek = true;
            pelnomocnictwo = false;
            firma = false;

        }
        if (e.getSource() == firmyButton) {
            firmyButton.setBackground(new Color(102, 204, 0));
            emAndRentButton.setBackground(Color.GRAY);
            zasilkiButton.setBackground(Color.gray);
            pelnoMocnictwoButton.setBackground(Color.gray);
            rentyPanel.setVisible(false);
            pelnomocnictwoPanel.setVisible(false);
            firmyPanel.setVisible(true);
            zasilkiPanel.setVisible(false);
            renta = false;
            zasilek = false;
            pelnomocnictwo = false;
            firma = true;
        }
        if (e.getSource() == pelnoMocnictwoButton) {
            pelnoMocnictwoButton.setBackground(new Color(102, 204, 0));
            emAndRentButton.setBackground(Color.GRAY);
            zasilkiButton.setBackground(Color.gray);
            firmyButton.setBackground(Color.gray);
            rentyPanel.setVisible(false);
            pelnomocnictwoPanel.setVisible(true);
            firmyPanel.setVisible(false);
            zasilkiPanel.setVisible(false);
            renta = false;
            zasilek = false;
            pelnomocnictwo = true;
            firma = false;
        }
        if (e.getSource() == pobierz) {
            Main.logr.info("uzytkownik pobiera podanie");
            if (renta) {
                String[] linki = {"", "https://www.zus.pl/documents/10182/18428/EMP_pobierz+plik.pdf/5dd9e60b-d6ac-58ab-bd3b-8857c6f793f2?t=1644843923771",
                        "https://www.zus.pl/documents/10182/788036/ERN+%2B+Informacja.pdf/214dfaea-90e4-45d5-922b-69ba574c9f69?t=1644844349962",
                        "https://www.zus.pl/documents/10182/4447142/ERO_pobierz_plik+7.02.22.pdf/914219b8-a9e2-b82d-91c5-6e37561d5df2?t=1644244710423",
                        "https://www.zus.pl/documents/10182/788036/ERR+do+pobrania_informacja.pdf/202d83d9-0a66-440f-8fec-cd51017cbed3?t=1644844632731"};

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(linki[emAndRentBox.getSelectedIndex()]));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            if (zasilek) {
                String[] linki = {"", "https://www.zus.pl/documents/10182/788036/ZPW_nowy.pdf/19d05959-05ce-4151-a4f2-48a824d2f3d4?t=1653485702459",
                            "https://www.zus.pl/documents/10182/3456315/1156_20+ZAS-53_RODO.pdf/866a43b8-6d0e-07e6-7e97-7ba6fd48f52f?t=1640959017345",
                            "https://www.zus.pl/documents/10182/4223950/ZAM_pobierz_plik_drukowany.pdf/f1ad7e3f-0eaa-fdbc-20ec-5b07ab59566a?t=1640958805220",
                            "https://www.zus.pl/documents/10182/4447142/Z-12_pobierz_plik+7.02.22.pdf/e0fb33b4-5342-7cf5-3c73-ac32e51d63c3?t=1650361337594"};
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(linki[zasilekBox.getSelectedIndex()]));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
            if(pelnomocnictwo)
            {
                String[] linki = {"","https://www.zus.pl/documents/10182/18428/PEL_pobierz_druk.pdf/4037d71a-2216-5cea-2c50-509f4ff7516c?t=1641819699050",
                        "https://www.zus.pl/documents/10182/788036/2485_17+PEL-Z.pdf/773d3c5f-e03a-43f6-a64c-8859c6770e5d?t=1641820120900",
                        "https://www.zus.pl/documents/10182/788036/2484_17+PEL-K.pdf/0e504722-6167-4e12-b318-428528efb8ec?t=1641819861660",
                        "https://www.zus.pl/documents/10182/788036/PEL-O_RODO.pdf/e77e2b17-fb4f-f922-0caf-a013edb42a63?t=1641820032663"};
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(linki[pelnomocnictwoBox.getSelectedIndex()]));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            if(firma)
            {
                String[] linki = {"","https://www.zus.pl/documents/10182/18428/1476_21+ERP-6+-+pobierz+plik.pdf/9952fe8b-98f6-f1d7-a95c-87f4c641eb2a?t=1643897766948",
                        "https://www.zus.pl/documents/10182/788036/RD-8.pdf/bd18129c-2989-4d06-a311-5d8c6eed9a9a?t=1636552025730",
                        "https://www.zus.pl/documents/10182/0/3011_18+RKR_RODO.pdf/db360e22-dae4-0bee-4082-759028346e43?t=1580377321864",
                        "https://www.zus.pl/documents/10182/4447142/ERP-7_pobierz_plik%2Binf+7.02.22.pdf/d08ac509-ea75-9efc-876c-dcd629301498?t=1644244711533"};
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(linki[firmyBox.getSelectedIndex()]));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

    }
}
