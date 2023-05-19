import CustomSwing.CustomInputField;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    public Test() {
        setTitle("Custom Input Field Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(450, 200));
        setResizable(false);

        JPanel contentPane = new JPanel();

        CustomInputField username = new CustomInputField(400, 50);
        contentPane.add(username);

        add(contentPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
