package com.FelixSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * An implementation of GradientPaint and Images as Background of a JPanel
 * <p>
 * <code>direction</code> is represented by a Number:
 * </p>
 *     <ol>
 *         <li>top to bottom
 *         <li>left to right
 *         <li>bottom to top
 *         <li>right to left
 *         <li>top left to bottom right
 *         <li>bottom left to top right
 *     </ol>
 * @see #paintComponent
 */

public class FelixTextPane extends JTextPane {
    private Color color1;
    private Color color2;
    private int direction;
    private Image image;
    private int radius = 20;
    private float opacity = 1.0f;
    private boolean animatedText = true;
    private boolean animated = true;

    // getters
    public float getOpacity() {
        return this.opacity;
    }
    public int getRadius() {
        return this.radius;
    }
    public Color getColor1() { return color1; }
    public Color getColor2() {
        return this.color2;
    }
    public boolean isAnimatedText() {
        return animatedText;
    }
    public boolean isAnimated() {
        return animated;
    }

    // setters
    public void setAnimated(boolean animated) {
        this.animated = animated;
        repaint();
    }
    public void setAnimatedText(boolean animatedText) {
        this.animatedText = animatedText;
        repaint();
    }
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }
    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }
    public void setColors(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }
    public void setColor(Color color){
        this.setColors(color, color);
    }

    /**
     * Creates a JPanel with standard gradient background with the colors:
     * <p>
     * <code>color1</code> new Color(46, 78, 88)
     * </p>
     * <p>
     * <code>color2</code> new Color(73, 156, 143);
     * </p>
     */
    public FelixTextPane() {
        this(new Color(46, 78, 88), new Color(73, 156,143), 1);
    }

    /**
     * Creates a JPanel with a colored background.
     *
     * @param color the color of the background
     */
    public FelixTextPane(Color color) {
        this(color, color, 1);
    }
    /**
     * Creates a JPanel with a gradient colored background.
     * The Gradient is made from top to bottom with colors from color1 to color2
     *
     * @param color1 the first color of the background (top)
     * @param color2 the second color of the background (bottom)
     */
    public FelixTextPane(Color color1, Color color2) {
        this(color1, color2, 1);
    }
    /**
     * Creates a JPanel with a gradient background.
     *
     * @param color1 The first color of the gradient.
     * @param color2 The second color of the gradient.
     * @param direction is represented by a Number:
     *     <ol>
     *         <li>top to bottom
     *         <li>left to right
     *         <li>bottom to top
     *         <li>right to left
     *         <li>top left to bottom right
     *         <li>bottom left to top right
     *     </ol>
     */
    public FelixTextPane(Color color1, Color color2, int direction) {
        this.color1 = color1;
        this.color2 = color2;
        this.direction = direction;
        image = null;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setForeground(Color.BLACK);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (animatedText) {
                    highlightHoveredText(e.getPoint());
                }
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                if (animatedText) {
                    animateTextPane(true);
                }
                if (animated) {
                    onHover();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (animatedText) {
                    animateTextPane(false);
                }
                if (animated) {
                    onReleaseHover();
                }
            }
        });
    }

    /**
     * Creates a JPanel with a gradient background and an Image as Foreground
     * Background is needed if the image has transparent parts.
     *
     * @param color1 The first color of the gradient.
     * @param color2 The second color of the gradient.
     * @param direction is represented by a Number:
     *                  <ol>
     *                      <li>top to bottom
     *                      <li>left to right
     *                      <li>bottom to top
     *                      <li>right to left
     *                      <li>top left to bottom right
     *                      <li>bottom left to top right
     *                  </ol>
     * @param image The image to place in the foreground
     */
    public FelixTextPane(Color color1, Color color2, int direction, BufferedImage image) {
        this(color1, color2, direction);
        if (image != null) {
            this.image = image;
        }
    }

    /**
     * Sets the direction, in which the color Gradient goes.
     *
     * @param direction is represented by a Number:
     *     <ol>
     *         <li>top to bottom
     *         <li>left to right
     *         <li>bottom to top
     *         <li>right to left
     *         <li>top left to bottom right
     *         <li>bottom left to top right
     *     </ol>
     */
    public void setDirection(int direction) {
        if (direction < 0 || direction > 6) {
            throw new IllegalArgumentException("Direction must be between 1 and 6");
        }
        this.direction = direction;
        repaint();
    }

    @Override
    public void setBackground(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can't be null or empty");
        }
        this.color1 = color;
        this.color2 = color;
        repaint();
    }

    public void setBackground(Color color1, Color color2) {
        if (color1 == null || color2 == null) {
            throw new IllegalArgumentException("Color can't be null or empty");
        }
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    public void setImage(Image image) throws IOException {
        if (image != null) {
            this.image = image;
            repaint();
        } else {
            throw new IOException("Image is not existing");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradient = null;

        switch (direction) {
            case 1 -> gradient = new GradientPaint(0, 0, color1, 0, height, color2);
            case 2 -> gradient = new GradientPaint(0, 0, color1, width, 0, color2);
            case 3 -> gradient = new GradientPaint(0, height, color1, 0, 0, color2);
            case 4 -> gradient = new GradientPaint(width, 0, color1, 0, 0, color2);
            case 5 -> gradient = new GradientPaint(0, 0, color1, width, height, color2);
            case 6 -> gradient = new GradientPaint(0, height, color1, width, 0, color2);
            default -> {
            }
        }

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setPaint(gradient);

        if (this.image != null) {
            g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity));
        g2d.fillRoundRect(0, 0, width, height, this.radius, this.radius);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        g2d.setColor(getForeground());
        g2d.setFont(getFont());

        g2d.dispose();

        super.paintComponent(g);
    }



    private void highlightHoveredText(Point point) {
        StyledDocument doc = this.getStyledDocument();

        // Clear any existing highlighting
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet normalAttr = styleContext.getEmptySet();
        doc.setCharacterAttributes(0, doc.getLength(), normalAttr, true);

        // Find the position of the hovered text
        int pos = this.viewToModel2D(point);
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(pos);

        // Apply highlighting to the hovered line
        Element elem = root.getElement(line);
        AttributeSet hoverAttr = styleContext.addAttribute(normalAttr, StyleConstants.Foreground, new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), 1.0f));
        doc.setCharacterAttributes(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset(), hoverAttr, false);
    }

    private void animateTextPane(boolean animate) {
        setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), animate ? 0.6f : 1.0f));
    }
    public void onHover() {
        this.setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2);
    }
    public void onReleaseHover() {
        this.setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
    }
}
