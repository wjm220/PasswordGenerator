import javax.swing.*;

public class Main {

    public static void main (String[] args) {
        JFrame frame = new JFrame("Secure Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("..\\img\\lock.png").getImage());
    }
}
