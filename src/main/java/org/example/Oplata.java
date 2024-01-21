package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * klasa pozwala na dokonanie wplat do systemu
 */
public class Oplata extends JFrame implements ActionListener {
    String pesel;
    String pensja;

    JButton zatwierdz = new JButton("Zaplać");
    String dzien;
    String miesiac;
    String rok;
    String[] dzienTab = {"","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","21","22","23","24","25","26","27","28","29","30","31"};
    String[] miesiacTab = {"","01","02","03","04","05","06","07","08","09","10","11","12"};

    String[] rokTab = {"","2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033","2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042",
            "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068",
            "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094",
            "2095", "2096", "2097", "2098", "2099", "2100", "2101", "2102", "2103", "2104", "2105", "2106", "2107", "2108", "2109", "2110", "2111", "2112", "2113", "2114", "2115", "2116", "2117", "2118", "2119", "2120",
    };
    ImageIcon powrotIcon = new ImageIcon("pliki/powrot.png");
    JButton powrot = new JButton();
    JTextField pensjaField = new JTextField();
    JLabel panelWartosc = new JLabel("Wartość Składki w zł");
    JLabel dataWplaty = new JLabel("Data wpłaty");

    JLabel dokonanoWplaty = new JLabel("Dokonano Opłaty Składki Emerytalnej");

    JPanel dzienPanel = new JPanel();
    JPanel miesiacPanel = new JPanel();
    JPanel rokPanel = new JPanel();
    JPanel panelPensja = new JPanel();
    JComboBox dzienBox = new JComboBox(dzienTab);
    JComboBox miesiacBox = new JComboBox(miesiacTab);

    JComboBox rokBox = new JComboBox(rokTab);




    /**
     *
     * @param pesel nr okreslajacy do jakie uzytwkonika systemu zostanie przypisana oplata skladek(nr przekazywany przez menu glowne)
     * @param pensja konstruktor dokonuje obliczen i okresla na podstawie tego parametru okresla minimalna wartosc skladki (przekazywane przez menu glowne)
     * konstruktor tworzy okno w ktorym mamy mozliwosc ustalenia kwoty wplaty oraz jej daty
     * @throws IOException
     */
    public Oplata(String pesel, String pensja) throws IOException
    {
        Main.logr.info("uzytkownik uruchamia modul odpowiedzialny za dokonywanie oplat");
        zatwierdz.setBounds(430,400,110,40);
        zatwierdz.addActionListener(this);
        dzienBox.addActionListener(this);
        miesiacBox.addActionListener(this);
        rokBox.addActionListener(this);

        dzienPanel.setBounds(350,210,70,40);
        dzienPanel.setLayout(new BorderLayout());
        dzienPanel.add(dzienBox);
        miesiacPanel.setBounds(430,210,70,40);
        miesiacPanel.setLayout(new BorderLayout());
        miesiacPanel.add(miesiacBox);
        rokPanel.setBounds(510,210,90,40);
        rokPanel.setLayout(new BorderLayout());
        rokPanel.add(rokBox);
        dokonanoWplaty.setForeground(Color.BLUE);
        dokonanoWplaty.setVisible(false);
        dokonanoWplaty.setBounds(200,170,500,40);




       // DecimalFormat df = new DecimalFormat("##.#");
        //JLabel wartoscSkladkiLabel = new JLabel("Wartosc składki powinna wynosić minimum :" + df.format() + "zł");
       // wartoscSkladkiLabel.setBounds(150,260,400,40);
        panelWartosc.setBounds(150,240,200,40);
        dataWplaty.setBounds(430,240,200,40);
       // wartoscSkladkiLabel.setForeground(Color.BLUE);
        this.pensja = pensja;
        this.pesel = pesel;
        powrot.setBounds(100,300,64,64);
        powrot.setIcon(powrotIcon);
        powrot.setOpaque(false);
        powrot.setContentAreaFilled(false);
        powrot.addActionListener(this);
        powrot.setFocusable(true);
        this.pesel = pesel;
        panelPensja.setLayout(new BorderLayout());
        panelPensja.setBounds(150,210,100,40);
        panelPensja.add(pensjaField);
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pliki/tlo.jpg")))));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(720, 520);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.add(wartoscSkladkiLabel);
        this.add(dataWplaty);
        this.add(zatwierdz);
        this.add(powrot);
        this.add(panelWartosc);
        this.add(panelPensja);
        this.add(dzienPanel);
        this.add(rokPanel);
        this.add(miesiacPanel);
        this.add(dokonanoWplaty);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        /**
         * przycisk zamyka okno i wraca do meny glowngo
         */
        if(e.getSource() == powrot)
        {
            dispose();

        }

        if(e.getSource() == dzienBox)
        {
            dzien = (String) dzienBox.getSelectedItem();
        }
        if(e.getSource() == miesiacBox)
        {
            miesiac = (String) miesiacBox.getSelectedItem();
        }
        if(e.getSource() == rokBox)
        {
            rok = (String) rokBox.getSelectedItem();
        }
        /**
         * przycisk potwierdza dokonanie wplaty pobiera z pol kwoty oraz daty dane i przekazuje je do serwer ktory zapisuje je do bazy
         */
        if(e.getSource() == zatwierdz)
        {
            Main.logr.info("uzytkownik dokonuje oplaty na ubezpieczenie emerytalne");

            String pensja = pensjaField.getText();

            Client client = new Client();
            String commandType = "OPLAC_SKLADKE";
            String data = rok+"-"+miesiac+"-"+dzien;
            System.out.println(data);
            System.out.println(pensja);
            System.out.println(" ");
            String zapytanie = pensja +" " + data + " " +  pesel;
            client.zapytanie(commandType,zapytanie);

            if(client.odpowiedzOdSerwera.equals("Oplacono"))
            {
                Main.logr.info("oplacono skladke emerytalną pomyslnie");
                dokonanoWplaty.setVisible(true);
            }else {
                dokonanoWplaty.setText("blad");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(220);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
