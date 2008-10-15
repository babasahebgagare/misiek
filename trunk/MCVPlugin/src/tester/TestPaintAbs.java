/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import cytoscape.Cytoscape;
import ding.view.DGraphView;
import ding.view.InnerCanvas;
import ding.view.ViewportChangeListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import utils.Messenger;

/**
 *
 * @author misiek
 */
public class TestPaintAbs extends JComponent {

    public TestPaintAbs() {
        //  super();
    }

    @Override
    public Rectangle getBounds(Rectangle rv) {
        return super.getBounds(rv);
    }

    @Override
    public void paint(Graphics g) {
        Messenger.Message("asasa");
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        //  g2d.drawLine(10, 10, WIDTH, WIDTH);
        g2d.draw(new Line2D.Double(10, 10, 300, 300));

    //    g2d.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    @Override
    public void paintImmediately(int x, int y, int w, int h) {
        super.paintImmediately(x, y, w, h);
        Messenger.Message("al");
    }

    @Override
    public void paintImmediately(Rectangle r) {
        super.paintImmediately(r);
        Messenger.Message("al");
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
        paint(g);
    }

    @Override
    public void printAll(Graphics g) {
        super.printAll(g);
        paint(g);
    }
}
