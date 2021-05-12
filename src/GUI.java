import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;

public class GUI extends JPanel {
    private JLabel mainLabel;
    private JSlider lengthSlider;
    private JTextArea passwordField;
    private JRadioButton letterRadio;
    private JRadioButton digitsRadio;
    private JRadioButton specialRadio;
    private JButton refreshButton;
    private JButton copyButton;
    private JLabel strengthLabel;
    private int sliderLength;

    public GUI() {
        // Construct components
        mainLabel = new JLabel("Secure Password Generator");
        lengthSlider = new JSlider(10, 60, 12);
        passwordField = new JTextArea(5, 5);
        letterRadio = new JRadioButton("Letters");
        digitsRadio = new JRadioButton("Digits");
        specialRadio = new JRadioButton("Special Characters");
        refreshButton = new JButton("");
        copyButton = new JButton("");
        strengthLabel = new JLabel("", SwingConstants.CENTER);
        sliderLength = 12;

        // Set component properties
        passwordField.setText(PasswordGenerator.generatePassword(12));
        passwordField.setEditable(false);

        strengthLabel.setForeground(Color.GREEN);
        strengthLabel.setText("Strong password!");

        lengthSlider.setOrientation(JSlider.HORIZONTAL);
        lengthSlider.setMinorTickSpacing(1);
        lengthSlider.setMajorTickSpacing(5);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);

        ImageIcon refreshIcon = new ImageIcon("C:\\Users\\Will\\IdeaProjects\\PasswordGenerator\\img\\refresh.png");
        refreshButton.setIcon(refreshIcon);

        ImageIcon copyIcon = new ImageIcon("C:\\Users\\Will\\IdeaProjects\\PasswordGenerator\\img\\copy.png");
        copyButton.setIcon(copyIcon);

        letterRadio.setSelected(true);
        digitsRadio.setSelected(true);
        specialRadio.setSelected(true);

        // Add listener methods for relevant components
        lengthSlider.addChangeListener(e -> sliderLength = lengthSlider.getValue());

        refreshButton.addActionListener(e -> {
            String password = "";

            // Check which radio buttons are selected to generate a password with the desired character types
            if (letterRadio.isSelected() && digitsRadio.isSelected() && specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength);
            }
            else if (letterRadio.isSelected() && digitsRadio.isSelected() && !specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.LETTERS,
                        PasswordGenerator.Characters.DIGITS);
            }
            else if (letterRadio.isSelected() && !digitsRadio.isSelected() && specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.LETTERS,
                        PasswordGenerator.Characters.SPECIALS);
            }
            else if (letterRadio.isSelected() && !digitsRadio.isSelected() && !specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.LETTERS);
            }
            else if (!letterRadio.isSelected() && digitsRadio.isSelected() && specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.DIGITS,
                        PasswordGenerator.Characters.SPECIALS);
            }
            else if (!letterRadio.isSelected() && digitsRadio.isSelected() && !specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.DIGITS);
            }
            else if (!letterRadio.isSelected() && !digitsRadio.isSelected() && specialRadio.isSelected()) {
                password = PasswordGenerator.generatePassword(sliderLength, PasswordGenerator.Characters.SPECIALS);
            }

            // Update strengthLabel to display the password strength (determined by the password length)
            if (sliderLength >= 12) {
                strengthLabel.setForeground(Color.GREEN);
                strengthLabel.setText("Strong password!");
            }
            else {
                strengthLabel.setForeground(Color.ORANGE);
                strengthLabel.setText("Fairly strong password");
            }

            // Display the generated password in passwordField
            passwordField.setText(password);
        });

        copyButton.addActionListener(e -> {
            String password = passwordField.getText();

            // Enable the copyButton to copy the generated password to the system clipboard
            StringSelection passwordSelection = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(passwordSelection, null);
        });

        letterRadio.addActionListener(e -> {
            // Forces the user to select at least one character type for the password generator (radio button)
            if (!digitsRadio.isSelected() && !specialRadio.isSelected()) {
                letterRadio.setSelected(true);
            }
        });

        digitsRadio.addActionListener(e -> {
            // Forces the user to select at least one character type for the password generator (radio button)
            if (!letterRadio.isSelected() && !specialRadio.isSelected()) {
                digitsRadio.setSelected(true);
            }
        });

        specialRadio.addActionListener(e -> {
            // Forces the user to select at least one character type for the password generator (radio button)
            if (!letterRadio.isSelected() && !digitsRadio.isSelected()) {
                specialRadio.setSelected(true);
            }
        });

        // Adjust size and set layout
        setPreferredSize(new Dimension (470, 250));
        setLayout(null);

        // Add components
        add(mainLabel);
        add(lengthSlider);
        add(passwordField);
        add(letterRadio);
        add(digitsRadio);
        add(specialRadio);
        add(refreshButton);
        add(copyButton);
        add(strengthLabel);

        // Set component bounds (absolute positioning)
        mainLabel.setBounds(155, 25, 165, 30);
        lengthSlider.setBounds(120, 145, 235, 60);
        passwordField.setBounds(100, 65, 275, 45);
        letterRadio.setBounds(95, 210, 100, 25);
        digitsRadio.setBounds(200, 210, 85, 25);
        specialRadio.setBounds(290, 210, 135, 25);
        refreshButton.setBounds(380, 70, 35, 30);
        copyButton.setBounds(420, 70, 35, 30);
        strengthLabel.setBounds(170, 120, 135, 25);
    }
}
