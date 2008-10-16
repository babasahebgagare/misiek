package tester;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class TestPaintAbs extends JComponent {

    @Override
    public Rectangle getBounds(Rectangle rv) {
        return new Rectangle(0, 0, 3000, 3000);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.draw(new Rectangle(5, 5, 10, 10));
    }
}
