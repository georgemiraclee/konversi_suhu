import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class konversi extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    
    public konversi() {
        // Set up the frame
        setTitle("Konversi Suhu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        // Set custom colors
        Color backgroundColor = new Color(240, 248, 255); // Alice Blue
        Color buttonColor = new Color(70, 130, 180);      // Steel Blue
        Color labelColor = new Color(25, 25, 112);        // Midnight Blue
        
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Create title label
        JLabel titleLabel = new JLabel("Konverter Suhu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(labelColor);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 10, 10));
        inputPanel.setBackground(backgroundColor);
        
        // Input field
        JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tempPanel.setBackground(backgroundColor);
        JLabel inputLabel = new JLabel("Masukkan Suhu: ");
        inputLabel.setForeground(labelColor);
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        tempPanel.add(inputLabel);
        tempPanel.add(inputField);
        
        // Dropdown selections
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dropdownPanel.setBackground(backgroundColor);
        
        fromUnit = new JComboBox<>(units);
        toUnit = new JComboBox<>(units);
        
        dropdownPanel.add(new JLabel("Dari: "));
        dropdownPanel.add(fromUnit);
        dropdownPanel.add(new JLabel("Ke: "));
        dropdownPanel.add(toUnit);
        
        // Result label
        resultLabel = new JLabel("Hasil: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(labelColor);
        
        // Convert button
        JButton convertButton = new JButton("Konversi");
        convertButton.setBackground(buttonColor);
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add components to input panel
        inputPanel.add(tempPanel);
        inputPanel.add(dropdownPanel);
        inputPanel.add(convertButton);
        inputPanel.add(resultLabel);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Add action listener to button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputTemp = Double.parseDouble(inputField.getText());
                    String from = (String) fromUnit.getSelectedItem();
                    String to = (String) toUnit.getSelectedItem();
                    double result = convertTemperature(inputTemp, from, to);
                    resultLabel.setText(String.format("Hasil: %.2f %s", result, to));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Masukkan angka yang valid!");
                }
            }
        });
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private double convertTemperature(double temp, String from, String to) {
        // First convert to Celsius
        double celsius = 0;
        switch (from) {
            case "Celsius":
                celsius = temp;
                break;
            case "Fahrenheit":
                celsius = (temp - 32) * 5/9;
                break;
            case "Kelvin":
                celsius = temp - 273.15;
                break;
        }
        
        // Then convert from Celsius to target unit
        switch (to) {
            case "Celsius":
                return celsius;
            case "Fahrenheit":
                return (celsius * 9/5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            default:
                return celsius;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new konversi().setVisible(true);
            }
        });
    }
}