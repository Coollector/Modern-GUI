package com.CustomSwing.FelixSwing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class FelixScrollPane extends JScrollPane {

    private Color scrollbarColor;
    private int radius;
    private boolean animated;

    // getters
    public int getRadius() {
        return this.radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public FelixScrollPane(Component view) {
        super(view);
        setOpaque(false);
        scrollbarColor = Color.GRAY; // Set default scrollbar color

        // Set custom scrollbar UI
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI(scrollbarColor));

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new CustomScrollBarUI(scrollbarColor));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Create a gradient paint with desired colors
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.WHITE, 0, height, Color.LIGHT_GRAY);

        g2d.setPaint(gradientPaint);
        g2d.fillRoundRect(0, 0, width, height, this.radius, this.radius);

        g2d.dispose();

        super.paintComponent(g);
    }

    public void setScrollbarColor(Color scrollbarColor) {
        this.scrollbarColor = scrollbarColor;
        updateScrollbarUI();
    }

    private void updateScrollbarUI() {
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI(scrollbarColor));

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new CustomScrollBarUI(scrollbarColor));
    }

    private static class CustomScrollBarUI extends BasicScrollBarUI {
        private final Color scrollbarColor;

        public CustomScrollBarUI(Color scrollbarColor) {
            this.scrollbarColor = scrollbarColor;
        }

        @Override
        protected void configureScrollBarColors() {
            thumbColor = scrollbarColor;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new ModernScrollBarButton(orientation);
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new ModernScrollBarButton(orientation);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(scrollbarColor);
            g2d.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 20, 20);
            g2d.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {}

        private static class InvisibleScrollBarButton extends JButton {
            private InvisibleScrollBarButton() {
                setOpaque(false);
                setFocusable(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    private static class ModernScrollBarButton extends JButton {
        private static final int BUTTON_SIZE = 16;

        public ModernScrollBarButton(int orientation) {
            setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusable(false);
            setFocusPainted(false);

            // Set arrow icon based on orientation
            if (orientation == SwingConstants.NORTH) {
                setIcon(new UpArrowIcon());
            } else if (orientation == SwingConstants.SOUTH) {
                setIcon(new DownArrowIcon());
            } else if (orientation == SwingConstants.WEST || orientation == SwingConstants.EAST) {
                setIcon(new SideArrowIcon());
            }
        }

        // Override paintComponent() to customize the button's appearance
        @Override
        protected void paintComponent(Graphics g) {
            // Custom painting code here
            // Draw the button with desired shape and style
        }
    }

}
