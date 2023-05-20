package com.CustomSwing.FelixSwing;

import javax.swing.*;
import java.awt.*;

public class FelixScrollPane extends JScrollPane {
    private final JTextPane label;

    public FelixScrollPane() {
        setPreferredSize(new Dimension(280, 750));
        setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        setForeground(Color.black);
        setOpaque(false);

        this.label = new FelixTextPane();
        this.label.setEditable(true);
        this.label.setOpaque(false);
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
