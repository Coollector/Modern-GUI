package com.CustomSwing;

import com.CustomSwing.FelixSwing.FelixPanel;
import com.CustomSwing.FelixSwing.FelixScrollPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestScene extends FelixPanel implements ActionListener {
    public FelixScrollPane pane = new FelixScrollPane();
    public TestScene() {
        super(new Color(46, 78, 88), new Color(73, 156, 143), 2);
        setPreferredSize(Fenster.windowSize);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBounds(0, 0, getWidth(), getHeight());

        add(pane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}