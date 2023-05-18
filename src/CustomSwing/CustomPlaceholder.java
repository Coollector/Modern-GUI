package CustomSwing;

import javax.swing.*;
import java.awt.*;

public class CustomPlaceholder extends JTextField {
    public CustomPlaceholder(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        setBorder(null);
        setEditable(false);
    }
}
