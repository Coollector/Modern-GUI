package CustomSwing;

import javax.swing.*;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {
    private final JTextPane label;

    public CustomScrollPane() {
        setPreferredSize(new Dimension(280, 750));
        setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        setForeground(Color.black);
        setOpaque(false);

        this.label = new JTextPane();
        this.label.setEditable(true);
        add(this.label);
        setViewportView(this.label);
    }

    public void setText(String text) {
        this.label.setText(text);
    }

    public String getText() {
        return this.label.getText();
    }
}
