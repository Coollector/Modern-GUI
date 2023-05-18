import CustomSwing.CustomInputField;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    public Test() {
        setTitle("Custom Input Field Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        CustomInputField inputField = new CustomInputField(200, 40, "Enter text");
        contentPane.add(inputField, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
