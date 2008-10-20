package tester;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class TestPaintAbs extends JComponent {

    @Override
    public Rectangle getBounds(Rectangle rv) {
        return new Rectangle(10, 10, 50, 50);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        Rectangle r = new Rectangle(1, 1, 38, 38) {
        };

        // g2d.setColor(new Color((float)0.5, (float)0.5, (float)0.5, (float)0.5));
        g2d.fill(r);
        g2d.draw(r);

    }
}
