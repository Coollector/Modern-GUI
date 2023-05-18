import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My App");
        Image defaultIcon = Toolkit.getDefaultToolkit().getImage("");
        String defaultIconPath = "";
        if (defaultIcon != null) {
            File defaultIconFile = new File(defaultIcon.getSource().toString());
            defaultIconPath = defaultIconFile.getAbsolutePath();
        }
        defaultIconPath = defaultIconPath.replaceAll("/", "\\\\");
        System.out.println("Default Icon Path: " + defaultIconPath);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
