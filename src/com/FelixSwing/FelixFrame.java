package com.FelixSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class FelixFrame extends JFrame {
    private static final String defaultIcon = "/pics/DefaultImageIcon.png";
    private static CustomTitleBar titleBar;
    private static final JPanel titlePanel = new JPanel(new BorderLayout());
    private static Color gradientColor1;
    private static Color gradientColor2;

    public static Color getGradientColor1() {return gradientColor1;}
    public static Color getGradientColor2() {return gradientColor2;}
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
    public boolean isLightMode() {
        return titleBar.lightMode;
    }

    public void setColor(Color color) {
        setBackground(color);
        titleBar.setBackground(color);
    }

    public void setGradientColor(Color color1, Color color2) {
        gradientColor1 = color1;
        gradientColor2 = color2;
        repaint();
    }

    public FelixFrame() {
        super.setUndecorated(true);
        setLayout(new BorderLayout());
        super.setBackground(Color.DARK_GRAY);
        setBackground(Color.DARK_GRAY);
        gradientColor1 = Color.DARK_GRAY; // Default gradient color
        gradientColor2 = Color.DARK_GRAY; // Default gradient color

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
    public void setTitle(String title) {
        super.setTitle(title);
        titleBar.setTitle(title);
    }

    @Override
    public void setIconImage(Image icon) {
        super.setIconImage(icon);
        titleBar.setIconImage(icon);
    }
    public void setIconImage(String iconPath) {this.setIconImage(new ImageIcon(iconPath).getImage());}

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
            closeButton.setIcon(new ImageIcon("src/com/FelixSwing/pics/close.png"));
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.exit(0);
                }
            });
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    onPressed(closeButton);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    onReleased(closeButton);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    onHover(closeButton);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    onReleaseHover(closeButton);
                }
            });

            JButton minimizeButton = new JButton();
            minimizeButton.setPreferredSize(new Dimension(20, 20));
            minimizeButton.setContentAreaFilled(false);
            minimizeButton.setFocusPainted(false);
            minimizeButton.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            minimizeButton.setIcon(new ImageIcon("src/com/FelixSwing/pics/minimize.png"));
            minimizeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    frame.setState(Frame.ICONIFIED);
                }
            });
            minimizeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    onPressed(minimizeButton);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    onReleased(minimizeButton);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    onHover(minimizeButton);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    onReleaseHover(minimizeButton);
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

        public void setTitle(String title) {titleLabel.setText(title);}

        public void setIconImage(Image icon) {titleLabel.setIcon(resizeIcon(icon, new Dimension(25, 25)));}

        public static Image convertToImage(Icon icon) {
            if (icon instanceof ImageIcon) {
                return ((ImageIcon) icon).getImage();
            } else {
                // Create a new image and draw the icon onto it
                int width = icon.getIconWidth();
                int height = icon.getIconHeight();
                java.awt.image.BufferedImage image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                java.awt.Graphics2D g2d = image.createGraphics();
                icon.paintIcon(null, g2d, 0, 0);
                g2d.dispose();
                return image;
            }
        }

        // Animation functions --------------------------------
        public void onPressed(JButton button) {
            button.setIcon(resizeIcon(convertToImage(button.getIcon()), new Dimension(18, 18)));
        }
        public void onReleased(JButton button) {
            button.setIcon(resizeIcon(convertToImage(button.getIcon()), new Dimension(20, 20)));
        }
        public void onHover(JButton button) {
            button.setIcon(resizeIcon(convertToImage(button.getIcon()), new Dimension(19, 19)));
        }
        public void onReleaseHover(JButton button) {
            button.setIcon(resizeIcon(convertToImage(button.getIcon()), new Dimension(20, 20)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();

            // Create a gradient paint with the provided colors
            GradientPaint gradientPaint = new GradientPaint(0, height/2, gradientColor1, width, height/2, gradientColor2);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, width, height);

            g2d.dispose();

            super.paintComponent(g);
        }
    }
}

