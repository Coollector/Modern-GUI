package com.FelixSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class FelixButton extends JButton {
    private Shape shape;
    private int width;
    private int height;
    private Color color1;
    private Color color2;
    private int radius;
    private boolean isTopLeftToBottomRight;
    private Image image;
    private float opacity = 1.0f;
    private String imagePath;
    private Image iconImage;
    private boolean animated = true;

    public boolean isTopLeftToBottomRight() {
        return this.isTopLeftToBottomRight;
    }
    public int getRadius() {
        return this.radius;
    }
    public Color getColor1() {
        return this.color1;
    }
    public Color getColor2() {
        return this.color2;
    }
    public float getOpacity() {
        return opacity;
    }
    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
        repaint();
    }
    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }
    public void setTopLeftToBottomRight(boolean topLeftToBottomRight) {
        this.isTopLeftToBottomRight = topLeftToBottomRight;
        repaint();
    }
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }
    public void setGradientColors(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }
    public void setColor(Color color) {
        this.color1 = color;
        this.color2 = color;
        repaint();
    }


    // Constructors --------------------------------
    public FelixButton() {
        this("", 100, 30, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(int width, int height) {
        this("", width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(int width, int height, int radius) {
        this("", width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(int width, int height, Color foreground) {
        this("", width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public FelixButton(int width, int height, int radius, Color foreground) {
        this("", width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public FelixButton(int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, boolean isTopLeftToBottomRight) {
        this("", width, height, radius, backgroundGradient1, backgroundGradient2, new Color(0, 0, 0), isTopLeftToBottomRight);
    }
    public FelixButton(int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, Color foregroundColor, boolean isTopLeftToBottomRight) {
        this("", width, height, radius, backgroundGradient1, backgroundGradient2, foregroundColor, isTopLeftToBottomRight);
    }
    public FelixButton(String text) {
        this(text, 100, 30, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(String text, int width, int height) {
        this(text, width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(String text, int width, int height, int radius) {
        this(text, width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public FelixButton(String text, int width, int height, Color foreground) {
        this(text, width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public FelixButton(String text, int width, int height, int radius, Color foreground) {
        this(text, width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public FelixButton(String text, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, boolean isTopLeftToBottomRight) {
        this(text, width, height, radius, backgroundGradient1, backgroundGradient2, new Color(0, 0, 0), isTopLeftToBottomRight);
    }
    public FelixButton(String text, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, Color foregroundColor, boolean isTopLeftToBottomRight) {
        super(text);
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.color1 = backgroundGradient1;
        this.color2 = backgroundGradient2;
        this.isTopLeftToBottomRight = isTopLeftToBottomRight;

        this.setForeground(foregroundColor);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onPressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                onReleased();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                onHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onReleaseHover();
            }
        });
    }
    public FelixButton(BufferedImage image, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, boolean isTopLeftToBottomRight) {
        super();
        this.image = image;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.color1 = backgroundGradient1;
        this.color2 = backgroundGradient2;
        this.isTopLeftToBottomRight = isTopLeftToBottomRight;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onPressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                onReleased();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                onHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onReleaseHover();
            }
        });
    }


    // Animation functions --------------------------------
    public void onPressed() {
        if (animated) {
            setPreferredSize(new Dimension(this.width - 4, this.height - 4));
            if (iconImage != null) {
                resizeImage();
            }
            setBounds(getX() + 2, getY() + 2, this.width, this.height);
            repaint();
        }
    }
    public void onReleased() {
        if (animated) {
            setPreferredSize(new Dimension(this.width + 4, this.height + 4));
            if (iconImage != null) {
                resizeImage();
            }
            setBounds(getX() - 2, getY() - 2, this.width, this.height);
            repaint();
        }
    }
    public void onHover() {
        if (animated) {
            setPreferredSize(new Dimension(this.width + 2, this.height + 2));
            if (iconImage != null) {
                resizeImage();
            }
            setBounds(getX() - 1, getY() - 1, this.width, this.height);
            repaint();
        }
    }
    public void onReleaseHover() {
        if (animated) {
            setPreferredSize(new Dimension(this.width - 2, this.height - 2));
            if (iconImage != null) {
                resizeImage();
            }
            setBounds(getX() + 1, getY() + 1, this.width, this.height);
            repaint();
        }
    }


    // set new Image and then repaint to show the image
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
    public void setImage(String iconPath) {this.setImage(new ImageIcon(iconPath).getImage());}


    private void resizeImage() {
        ImageIcon icon = new ImageIcon(this.imagePath);
        int size = this.height - 10;

        BufferedImage bi;
        try {
            bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(icon.getImage(), 0, 0, size, size, null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        iconImage = icon.getImage();

        setBorder(new EmptyBorder(0, this.height + 5, 0, this.height + 5));
    }

    public void setIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.imagePath = path;
        int size = this.height - 10;

        BufferedImage bi;
        try {
            bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(icon.getImage(), 0, 0, size, size, null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        this.iconImage = icon.getImage();

        setBorder(new EmptyBorder(0, this.height + 5, 0, this.height + 5));

        repaint();
    }

    // paint Button to be placed on screen
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity));
        RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.clip(shape);

        GradientPaint gradientPaint;
        if (isTopLeftToBottomRight) {
            gradientPaint = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
        } else {
            gradientPaint = new GradientPaint(0, getHeight(), color1, getWidth(), 0, color2);
        }
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, getWidth(), getHeight());


        if (image != null) {
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            FontMetrics metrics = g.getFontMetrics(getFont());
            int x = getWidth()/2 - metrics.stringWidth(getText())/2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(getText(), x, y);
        }

        if (this.iconImage != null) {
            g2.drawImage(this.iconImage, 5, 5, this.height - 10, this.height - 10, null);
        }

        g2.dispose();
        super.paintComponent(g);
    }


    // necessary functions
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        return shape.contains(x, y);
    }

    @Override
    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        this.width = dimension.width;
        this.height = dimension.height;
        repaint();
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
        repaint();
    }
}