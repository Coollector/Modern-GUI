import CustomSwing.CustomFrame;

import javax.swing.*;
import java.awt.*;

public class Fenster extends CustomFrame {

    static public Dimension windowSize = new Dimension(450, 800);

    public Fenster() {
        setSize(windowSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        setTitle("Modern GUI");
        setIconImage(new ImageIcon("C:\\Users\\felix\\Programmieren\\Java\\ModernGUI\\pics\\icons8-gui-64.png").getImage());

        add(new Scene());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Fenster();
    }
}