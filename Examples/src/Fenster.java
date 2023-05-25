import com.FelixSwing.FelixFrame;

import javax.swing.*;
import java.awt.*;

public class Fenster extends FelixFrame {

    static public Dimension windowSize = new Dimension(450, 800);

    public Fenster() {
        setSize(windowSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        setTitle("Modern GUI");
        // setIconImage(new ImageIcon("Examples/src/pics/icon.png").getImage());

        add(new Scene());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Fenster();
    }
}