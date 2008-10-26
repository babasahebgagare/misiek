package tester;

import java.awt.Rectangle;
import java.awt.Shape;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.Renderer;
import prefuse.visual.VisualItem;
import utils.Messenger;

public class MyNodeRenderer extends AbstractShapeRenderer {

    @Override
    protected Shape getRawShape(VisualItem item) {
        //item.
        return new Rectangle((int)(item.getX()), (int)item.getY(), (int)item.getEndX()-(int)item.getX(), (int)item.getEndY()-(int)item.getY());
    }
}
