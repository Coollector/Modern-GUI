package com.CustomSwing;

import com.CustomSwing.FelixSwing.FelixFrame;

import javax.swing.*;
import java.awt.*;

public class Fenster extends FelixFrame {

    static public Dimension windowSize = new Dimension(450, 800);

    public Fenster() {
        setSize(windowSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        setTitle("Modern GUI");
        setIconImage(new ImageIcon("C:\\Users\\felix\\Programmieren\\Java\\ModernGUI\\pics\\icon.png").getImage());

        add(new Scene());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Fenster();
    }
}