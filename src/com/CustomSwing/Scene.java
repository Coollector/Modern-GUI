package com.CustomSwing;

import com.CustomSwing.FelixSwing.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class Scene extends FelixPanel implements ActionListener {
    FelixInputField username = new FelixInputField(400, 50, 30, "Username", new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    FelixInputField password = new FelixInputField(400, 50, 30, "Password", new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    FelixButton login = new FelixButton("Login", 195, 50, 30, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    FelixButton signUp = new FelixButton("SignUp", 195, 50, 30, new Color(171, 193, 133), new Color(73, 156, 143), new Color(0, 0, 0), false);


    JTextPane jPane = new JTextPane();
    FelixTextPane customPane = new FelixTextPane();


    FelixButton[] socials = new FelixButton[4];
    String[] socialPaths = {"pics/icons8-discord-50.png", "pics/icons8-twitter-64.png", "pics/icons8-web-50.png", "pics/icons8-github-64.png"};
    String[] socialWebs = {"https://discord.com/", "https://twitter.com/", "https://google.com/", "https://github.com/"};
    public Scene() {
        setPreferredSize(Fenster.windowSize);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setDirection(2);

        username.setIcon("pics/icons8-user-60.png");
        password.setIcon("pics/pngtree-vector-lock-icon-png-image_318067-removebg-preview.png");

        login.addActionListener(this);
        signUp.addActionListener(this);

        add(new FelixPlaceholder(450, 10));
        add(new FelixPlaceholder(25, 50));
        add(username);
        add(new FelixPlaceholder(25, 50));
        add(new FelixPlaceholder(450, 10));
        add(new FelixPlaceholder(25, 50));
        add(password);
        add(new FelixPlaceholder(25, 50));

        add(new FelixPlaceholder(450, 10));

        add(new FelixPlaceholder(25, 50));
        add(login);
        add(new FelixPlaceholder(10, 50));
        add(signUp);
        add(new FelixPlaceholder(25, 50));

        add(new FelixPlaceholder(450, 20));

        add(new FelixPlaceholder(25, 40));
        for (int i = 0; i < 4; i++) {
            try {
                socials[i] = new FelixButton(ImageIO.read(new File(socialPaths[i])), 40, 40, 20, new Color(62, 155, 142), new Color(46, 78, 88), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            socials[i].addActionListener(this);
            add(socials[i]);
            if (i < 3) {add(new FelixPlaceholder(60, 40));}
        }
        add(new FelixPlaceholder(25, 50));

        add(new FelixPlaceholder(450, 20));

        jPane.setPreferredSize(new Dimension(195, 500));

        customPane.setPreferredSize(new Dimension(195, 500));

        add(jPane);
        add(new FelixPlaceholder(10, 400));
        add(customPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Arrays.asList(socials).contains(e.getSource())) {
            System.out.println("test");
            for (int i = 0; i < 4; i++) {
                System.out.println("test2");
                if (e.getSource() == socials[i]) {
                    try {
                        Desktop.getDesktop().browse(URI.create(socialWebs[i]));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else if (e.getSource() == login) {
            System.out.println("Logging in with\nusername:\t" + username.getText() + "\npassword:\t" + password.getText());
            login.setText(username.getText());
            jPane.setText(customPane.getText());
        } else if (e.getSource() == signUp) {
            System.out.println("Sign Up with\nusername:\t" + username.getText() + "\npassword:\t" + password.getText());
        }
    }
}
