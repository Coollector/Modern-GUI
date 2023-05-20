package com.CustomSwing.FelixSwing;

import javax.swing.*;
import java.awt.*;

public class FelixPlaceholder extends JTextField {
    public FelixPlaceholder(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        setBorder(null);
        setEditable(false);
    }
}
