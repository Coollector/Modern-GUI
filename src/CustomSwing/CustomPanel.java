package CustomSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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

public class CustomPanel extends JPanel {
    private Color color1;
    private Color color2;
    private int direction;
    private BufferedImage image;
    private int radius = 20;


    /**
     * Creates a JPanel with standard gradient background with the colors:
     * <p>
     * <code>color1</code> new Color(46, 78, 88)
     * </p>
     * <p>
     * <code>color2</code> new Color(73, 156, 143);
     * </p>
     */
    public CustomPanel() {
        this.color1 = new Color(46, 78, 88);
        this.color2 = new Color(73, 156, 143);
        this.direction = 1;
        image = null;
    }

    /**
     * Creates a JPanel with a colored background.
     *
     * @param color the color of the background
     */
    public CustomPanel(Color color) {
        this.color1 = color;
        this.color2 = color;
        this.direction = 1;
        image = null;
    }
    /**
     * Creates a JPanel with a gradient colored background.
     * The Gradient is made from top to bottom with colors from color1 to color2
     *
     * @param color1 the first color of the background (top)
     * @param color2 the second color of the background (bottom)
     */
    public CustomPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.direction = 1;
        image = null;
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
    public CustomPanel(Color color1, Color color2, int direction) {
        this.color1 = color1;
        this.color2 = color2;
        this.direction = direction;
        image = null;
    }
    /**
     * Creates a JPanel with a gradient background and an Image as Foreground
     * Background is needed if the image has transparent parts.
     *
     * @param color1 The first color of the gradient (in hex format).
     * @param color2 The second color of the gradient (in hex format).
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
    public CustomPanel(Color color1, Color color2, int direction, BufferedImage image) throws IOException {
        this(color1, color2, direction);
        if (image != null) {
            this.image = image;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

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
        if (image != null) {
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
        g2d.fillRoundRect(0, 0, width, height, radius, radius);
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

    public void setRadius(int radius) {
        this.radius = radius;
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

    public void setImage(String imagePath) throws IOException {
        if (imagePath != null) {
            File file = new File(imagePath);
            if (file.exists()) {
                image = ImageIO.read(file);
            }
            repaint();
        } else {
            throw new IOException("Image is not existing");
        }
    }
}
