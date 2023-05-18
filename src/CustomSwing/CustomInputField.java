package CustomSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class CustomInputField extends JTextField implements FocusListener {
/**
 * A custom input field component with a rounded shape, gradient background, and animated input hint.
 * The component is designed for use in GUI applications, where users can input text or other data.
 * <p>
 * The input field has a customizable size, radius, and colors for the background gradient, text, input hint,
 * and border. The component supports a smooth animation that moves the input hint label up and down to indicate
 * whether the field has focus or not.
 * </p>
 */
    private int height;
    private int width;
    private int radius;
    private String hintText;
    private Color hintTextColor, gradientColor1, gradientColor2, lineColor, textColor;
    private float lineOpacity;
    private final Timer lineFadeInTimer, lineFadeOutTimer;
    private Image iconImage;
    private String imagePath;

    public int getRadius() {
        return this.radius;
    }
    public String getHintText() {
        return this.hintText;
    }
    public Color getHintTextColor() {
        return this.hintTextColor;
    }
    public Color getGradientColor1() {
        return this.gradientColor1;
    }
    public Color getGradientColor2() {
        return this.gradientColor2;
    }
    public Color getLineColor() {
        return this.lineColor;
    }
    public Color getTextColor() {
        return this.textColor;
    }

    // setters
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }
    public void setHintText(String hintText) {
        this.hintText = hintText;
        repaint();
    }
    public void setHintTextColor(Color hintTextColor) {
        this.hintTextColor = hintTextColor;
        repaint();
    }
    public void setGradientColors(Color gradientColor1, Color gradientColor2) {
        this.gradientColor1 = gradientColor1;
        this.gradientColor2 = gradientColor2;
        repaint();
    }
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        repaint();
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }
    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        repaint();
    }
    @Override
    public void setPreferredSize(Dimension size) {
        this.width = (int) size.getWidth();
        this.height = (int) size.getHeight();
        repaint();
    }

    /**
     * A rounded input field with gradient background, animation and input hint.
     * @param height The height of the input field.
     * @param width The width of the input field.
     * @param radius The radius of the rounded border.
     * @param hintText The hint text to be displayed when the input field is empty.
     * @param hintTextColor The color of the hint text.
     * @param gradientColor1 The first color of the gradient background.
     * @param gradientColor2 The second color of the gradient background.
     * @param lineColor The color of the animated line.
     */
    public CustomInputField(int width, int height, int radius, String hintText, Color gradientColor1, Color gradientColor2, Color hintTextColor, Color lineColor) {
        super();
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.hintText = hintText;
        this.gradientColor1 = gradientColor1;
        this.gradientColor2 = gradientColor2;
        this.lineColor = lineColor;
        this.hintTextColor = hintTextColor;
        this.textColor = new Color(0, 0, 0);
        setForeground(this.textColor);

        setBorder(new EmptyBorder(0, 15, 0 ,15));
        setOpaque(false);
        setHorizontalAlignment(LEFT);
        setPreferredSize(new Dimension(width, height));
        setHintText(hintText);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onReleaseHover();
            }
        });

    lineOpacity = 0;
        addFocusListener(this);
        lineFadeInTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineOpacity += 0.05f;
                if (lineOpacity >= 1.0f) {
                    lineOpacity = 1.0f;
                    lineFadeInTimer.stop();
                }
                repaint();
            }
        });
        lineFadeOutTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineOpacity -= 0.05f;
                if (lineOpacity <= 0.0f) {
                    lineOpacity = 0.0f;
                    lineFadeOutTimer.stop();
                }
                repaint();
            }
        });
    }
    public CustomInputField(int width, int height, String hintText, Color gradientColor1, Color gradientColor2, Color hintTextColor, Color lineColor) {
        this(width, height, 20, hintText, gradientColor1, gradientColor2, hintTextColor, lineColor);
    }
    public CustomInputField(int width, int height, String hintText, Color gradientColor1, Color gradientColor2, Color hintTextColor) {
        this(width, height, 20, hintText, gradientColor1, gradientColor2, hintTextColor, new Color(52, 79, 88));
    }
    public CustomInputField(int width, int height, String hintText, Color gradientColor1, Color gradientColor2) {
        this(width, height, 20, hintText, gradientColor1, gradientColor2, new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(String hintText, Color gradientColor1, Color gradientColor2, Color hintTextColor, Color lineColor) {
        this(200, 40, 20, hintText, gradientColor1, gradientColor2, hintTextColor, lineColor);
    }
    public CustomInputField(String hintText, Color gradientColor1, Color gradientColor2, Color hintTextColor) {
        this(200, 40, 20, hintText, gradientColor1, gradientColor2, hintTextColor, new Color(52, 79, 88));
    }
    public CustomInputField(String hintText, Color gradientColor1, Color gradientColor2) {
        this(200, 40, 20, hintText, gradientColor1, gradientColor2, new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(Color gradientColor1, Color gradientColor2) {
        this(200, 40, 20, "", gradientColor1, gradientColor2, new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(int width, int height, int radius, String hintText) {
        this(width, height, radius, hintText, new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(int width, int height, String hintText) {
        this(width, height, 20, hintText, new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(int width, int height, int radius) {
        this(width, height, radius, "", new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(int width, int height) {
        this(width, height, 20, "", new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField(String hintText) {
        this(200, 40, 20, hintText, new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }
    public CustomInputField() {
        this(200, 40, 20, "", new Color(48, 99, 104), new Color(73, 156, 143), new Color(127, 127, 127), new Color(52, 79, 88));
    }


    private void onHover() {
        this.height = height + 2;
        this.width = width + 2;
        if (iconImage != null) {
            resizeImage();
        }
        setBounds(getX()-1, getY()-1, width, height);
        repaint();
    }
    private void onReleaseHover() {
        this.height = height - 2;
        this.width = width - 2;
        setBounds(getX()+1, getY()+1, width, height);
        if (iconImage != null) {
            resizeImage();
        };
        repaint();
    }

    private void resizeImage() {
        ImageIcon icon = new ImageIcon(this.imagePath);
        int size = height - 10;

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

        setBorder(new EmptyBorder(0, height + 5, 0, 15));
    }
    public void setIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.imagePath = path;
        int size = height - 10;

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

        setBorder(new EmptyBorder(0, height + 5, 0, 15));

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(new GradientPaint(0, 0, this.gradientColor1, getWidth(), 0, this.gradientColor2));
        g2.fillRoundRect(0, 0, width - 1, height - 1, radius, radius);

        g2.setColor(lineColor);
        int textX = 15 + ((this.iconImage != null) ? height-10 : 0);
        int textY = (height - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lineOpacity));
        g2.drawLine(textX, height - 7, width - 15, height - 5);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        if (this.iconImage != null) {
            g2.drawImage(this.iconImage, 5, 5, this.height-10, this.height-10, null);
        }

        g2.setFont(getFont());
        if (getText().isEmpty()) {
            g2.setColor(hintTextColor);
            g2.drawString(hintText, textX, textY);
        }

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public void focusGained(FocusEvent e) {
        lineOpacity = 0;
        lineFadeInTimer.start();
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        lineOpacity = 1;
        lineFadeOutTimer.start();
        repaint();
    }
}
