package structs.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class GroupNodeView extends JComponent {

    int centrumX;
    int centrumY;
    int width;
    int height;

    public GroupNodeView(int centrumX, int centrumY, int width, int height) {
        this.centrumX = centrumX;
        this.centrumY = centrumY;
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getBounds(Rectangle rv) {
        int leftX = centrumX - width / 2;
        int topY = centrumY - height / 2;
        return new Rectangle(leftX, topY, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);

        Rectangle r = new Rectangle(0, 0, width, height) {
        };

        g2d.fill(r);
        g2d.draw(r);

    }
}
