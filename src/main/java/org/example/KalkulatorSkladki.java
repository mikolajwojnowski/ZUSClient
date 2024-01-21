package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorSkladki extends JFrame{
    JCheckBox taxScaleCheckBox;
    JCheckBox linearTaxCheckBox;
    JTextField incomeField;
    JLabel monthsLabel;
    JTextField healthInsuranceField;
    double podatek;

    public KalkulatorSkladki(String pesel)
    {
        setTitle("Kalkulator składki zdrowotnej");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        setBackground(Color.green);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        //mainPanel.setBackground(Color.green);
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());

        taxScaleCheckBox = new JCheckBox("Zasady ogólne - podatek według skali");
        linearTaxCheckBox = new JCheckBox("Zasady ogólne - podatek liniowy");

        optionsPanel.add(taxScaleCheckBox);
        optionsPanel.add(linearTaxCheckBox);

        mainPanel.add(optionsPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        String labelText = "<html>Dochód z działalności gospodarczej w roku," +
                "za który rozliczana jest roczna składka na ubezpieczenie zdrowotne:<html>";

        JLabel incomeLabel = new JLabel(labelText);
        incomeField = new JTextField();
        incomeField.setPreferredSize(new Dimension(200, 25)); // Adjust the width of the text field
        incomeField.setHorizontalAlignment(JTextField.CENTER); // Center the text in the text field

        gbc.gridy++;
        formPanel.add(incomeLabel, gbc);
        gbc.gridy++;
        formPanel.add(incomeField, gbc);

        monthsLabel = new JLabel("Liczba miesięcy korzystania z ubezpieczenia:");
        JComboBox<Integer> monthsComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        monthsComboBox.setPreferredSize(new Dimension(200, 25)); // Adjust the width of the combo box

        gbc.gridy++;
        formPanel.add(monthsLabel, gbc);
        gbc.gridy++;
        formPanel.add(monthsComboBox, gbc);

        JLabel healthInsuranceLabel = new JLabel("Roczna składka na ubezpieczenie zdrowotne:");
         healthInsuranceField = new JTextField();
        healthInsuranceField.setEditable(false);
        healthInsuranceField.setPreferredSize(new Dimension(200, 25)); // Adjust the width of the text field
        healthInsuranceField.setHorizontalAlignment(JTextField.CENTER); // Center the text in the text field

        gbc.gridy++;
        formPanel.add(healthInsuranceLabel, gbc);
        gbc.gridy++;
        formPanel.add(healthInsuranceField, gbc);

        JButton calculateButton = new JButton("Oblicz");

        gbc.gridy++;
        formPanel.add(new JLabel()); // Empty label for spacing
        gbc.gridy++;
        formPanel.add(calculateButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);


        taxScaleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linearTaxCheckBox.setSelected(!taxScaleCheckBox.isSelected());
            }
        });

        linearTaxCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taxScaleCheckBox.setSelected(!linearTaxCheckBox.isSelected());
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation();
            }
        });


        add(mainPanel);

        setVisible(true);
    }

    public void performCalculation() {
        boolean isTaxScaleSelected = taxScaleCheckBox.isSelected();
        boolean isLinearTaxSelected = linearTaxCheckBox.isSelected();
        if(isTaxScaleSelected)
        {
            String wartosc = incomeField.getText();
            double  p = Double.parseDouble(wartosc);
             podatek = p*0.09;

            healthInsuranceField.setText(String.valueOf(podatek));
            System.out.println(podatek);



        }else if(isLinearTaxSelected)
        {
            String wartosc = incomeField.getText();
            double  p = Double.parseDouble(wartosc);
             podatek = p*0.049;
            System.out.println(podatek);

            healthInsuranceField.setText(String.valueOf(podatek));
        }

        // Perform calculations based on the selected checkboxes
        // Use values from incomeField and monthsComboBox as needed
        // Update healthInsuranceField with the calculated result
    }

}
