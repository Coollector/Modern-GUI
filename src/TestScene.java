import CustomSwing.CustomPanel;
import CustomSwing.CustomScrollPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestScene extends CustomPanel implements ActionListener {
    public CustomScrollPane pane = new CustomScrollPane();
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
