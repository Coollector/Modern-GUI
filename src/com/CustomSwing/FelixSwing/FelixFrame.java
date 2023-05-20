package com.CustomSwing.FelixSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class FelixFrame extends JFrame {
    private static final String defaultIcon = "src/com.CustomSwing.CustomSwing/pics/DefaultImageIcon.png";
    private static CustomTitleBar titleBar;
    private static final JPanel titlePanel = new JPanel(new BorderLayout());

    public FelixFrame() {
        super.setUndecorated(true);
        setLayout(new BorderLayout());
        super.setBackground(Color.DARK_GRAY);
        setBackground(Color.DARK_GRAY);

        titleBar = new CustomTitleBar(getTitle(), (getIconImage() != null) ? getIconImage() : new ImageIcon(defaultIcon).getImage(), getWidth(), this);
        titlePanel.add(titleBar, BorderLayout.NORTH);
        super.add(titlePanel);

        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight() + 20, 20, 20));
    }

    public void add(JComponent component) {
        titlePanel.add(component, BorderLayout.CENTER);

        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight() + 20, 20, 20));
    }

    @Override
    public void setBackground(Color bgColor) {
        titlePanel.setBackground(bgColor);
        super.setBackground(bgColor);
        repaint();
    }

    public void setLightMode(boolean lightMode) {
        titleBar.setLightMode(lightMode);
        setBackground((lightMode)? Color.WHITE : Color.DARK_GRAY);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        titleBar.setTitle(title);
    }

    @Override
    public void setIconImage(Image icon) {
        super.setIconImage(icon);
        titleBar.setIconImage(icon);
    }

    @Override
    public void setUndecorated(boolean undecorated) {}

    public static class CustomTitleBar extends JPanel {

        public boolean lightMode = false;
        private int mouseX, mouseY;
        private final JLabel titleLabel = new JLabel();

        public CustomTitleBar(String title, Image icon, int width, JFrame frame) {
            super.setOpaque(false);
            setLayout(new BorderLayout());

            setPreferredSize(new Dimension(width, 30));

            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setOpaque(false);

            titleLabel.setText(title);
            titleLabel.setBackground((lightMode) ? Color.WHITE : Color.DARK_GRAY);
            titleLabel.setIcon(resizeIcon(icon, new Dimension(25, 25)));
            titleLabel.setForeground((lightMode) ? Color.BLACK : Color.WHITE);
            titlePanel.add(titleLabel, BorderLayout.CENTER);

            JButton closeButton = new JButton();
            closeButton.setPreferredSize(new Dimension(20, 20));
            closeButton.setContentAreaFilled(false);
            closeButton.setFocusPainted(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            closeButton.setIcon(new ImageIcon("src/com.CustomSwing.CustomSwing/pics/close.png"));
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.exit(0);
                }
            });

            JButton minimizeButton = new JButton();
            minimizeButton.setPreferredSize(new Dimension(20, 20));
            minimizeButton.setContentAreaFilled(false);
            minimizeButton.setFocusPainted(false);
            minimizeButton.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            minimizeButton.setIcon(new ImageIcon("src/com.CustomSwing.CustomSwing/pics/minimize.png"));
            minimizeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    frame.setState(Frame.ICONIFIED);
                }
            });

            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.setOpaque(false);
            buttonPanel.setPreferredSize(new Dimension(50, 20));
            buttonPanel.add(minimizeButton, BorderLayout.WEST);
            buttonPanel.add(closeButton, BorderLayout.CENTER);

            add(titlePanel, BorderLayout.WEST);
            add(buttonPanel, BorderLayout.EAST);

            setBackground((lightMode) ? Color.WHITE : Color.DARK_GRAY);


            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(CustomTitleBar.this);
                    int x = frame.getLocation().x + e.getX() - mouseX;
                    int y = frame.getLocation().y + e.getY() - mouseY;
                    frame.setLocation(x, y);
                }
            });
        }

        private ImageIcon resizeIcon(Image icon, Dimension size) {
            Image scaledImage = icon.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }

        public void setLightMode(boolean lightMode) {
            this.lightMode = lightMode;
            setBackground((this.lightMode) ? Color.WHITE : Color.DARK_GRAY);
            titleLabel.setBackground((this.lightMode) ? Color.WHITE : Color.DARK_GRAY);
            titleLabel.setForeground((this.lightMode) ? Color.BLACK : Color.WHITE);
        }

        public void setTitle(String title) {
            titleLabel.setText(title);
        }

        public void setIconImage(Image icon) {
            titleLabel.setIcon(resizeIcon(icon, new Dimension(25, 25)));
        }
    }
}

