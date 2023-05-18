package CustomSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomButton extends JButton {
    private Shape shape;
    private final int width;
    private final int height;
    private Color color1;
    private Color color2;
    private int radius;
    private boolean isTopLeftToBottomRight;
    private BufferedImage image;

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


    // Constructors --------------------------------
    public CustomButton(String text) {
        this(text, 100, 30, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public CustomButton(String text, int width, int height) {
        this(text, width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public CustomButton(String text, int width, int height, int radius) {
        this(text, width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), new Color(0, 0, 0), true);
    }
    public CustomButton(String text, int width, int height, Color foreground) {
        this(text, width, height, 20, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public CustomButton(String text, int width, int height, int radius, Color foreground) {
        this(text, width, height, radius, new Color(73, 156, 143), new Color(171, 193, 133), foreground, true);
    }
    public CustomButton(String text, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, boolean isTopLeftToBottomRight) {
        this(text, width, height, radius, backgroundGradient1, backgroundGradient2, new Color(0, 0, 0), isTopLeftToBottomRight);
    }
    public CustomButton(String text, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, Color foregroundColor, boolean isTopLeftToBottomRight) {
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
    public CustomButton(BufferedImage image, int width, int height, int radius, Color backgroundGradient1, Color backgroundGradient2, boolean isTopLeftToBottomRight) {
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
        setBounds(getX() + 2, getY() + 2, width - 4, height - 4);
    }
    public void onReleased() {
        setBounds(getX() - 2, getY() - 2, width, height);
    }
    public void onHover() {
        setBounds(getX() - 1, getY() - 1, width + 2, height + 2);
    }
    public void onReleaseHover() {
        setBounds(getX() + 1, getY() + 1, width - 2, height - 2);
    }


    // return BufferedImage from Image Path
    public static BufferedImage loadImageFromFile(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    // set new Image and then repaint to show the image
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }



    // paint Button to be placed on screen
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
            FontMetrics metrics = g.getFontMetrics(getFont());
            int x = getWidth()/2 - metrics.stringWidth(getText())/2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(getText(), x, y);
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
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 20, 20);
        }
    }

}