package structs.view;

import ding.view.DGraphView;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GroupNodeView extends BackgroundElement {

    Color color;

    public GroupNodeView(int centrumX, int centrumY, int width, int height, DGraphView view) {
        super(view, centrumX, centrumY, width, height);
    }

    public void setFillColor(Color c) {
        color = c;
    }

    @Override
    protected void doPaint(Graphics2D g2d) {
        g2d.setColor(color);

        Line2D l = new Line2D.Double(getStartX(), getStartY(), getEndX(), getEndY());
        Rectangle b = relativeToBounds(viewportTransform(l)).getBounds();

        //Rectangle r = new Rectangle(getVStartX(), getVStartY(), getVEndX(), getVEndY());
        Point2D start = l.getP1();
        Point2D end = l.getP2();

        double xdir = end.getX() - start.getX();
        double ydir = end.getY() - start.getY();

        double xs = xdir >= 0 ? b.x : b.x + b.width;
        double ys = ydir >= 0 ? b.y : b.y + b.height;
        double xe = xdir >= 0 ? xs + b.width : b.x;
        double ye = ydir >= 0 ? ys + b.height : b.y;

        Rectangle r = new Rectangle((int) xs, (int) ys, (int) xe, (int) ye);
        g2d.fill(r);
        g2d.draw(r);

        g2d.dispose();
    }
}
