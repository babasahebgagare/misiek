package envinterface.netbeans;

import envinterface.EnvEdge;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import envinterface.EnvNode;
import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;

public class NetbeansInterface extends EnvInterface {

    private JScrollPane canvas = null;
    private Scene scene = new Scene();
    private LayerWidget mainLayer = null;
    private LayerWidget connLayer = null;

    private void initScene() {
        scene = new Scene();

        mainLayer = new LayerWidget(scene);
        scene.addChild(mainLayer);

        connLayer = new LayerWidget(scene);
        scene.addChild(connLayer);

        LayerWidget interLayer = new LayerWidget(scene);
        scene.addChild(interLayer);

        scene.getActions().addAction(ActionFactory.createZoomAction());
        /*    AnchorShape shape = AnchorShapeFactory.createTriangleAnchorShape(18, true, false, 17);
        ConnectionWidget connection = new ConnectionWidget(scene);
        connection.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
        connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(label1));
        connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(label2));
        connection.setSourceAnchorShape(shape);
        connection.setTargetAnchorShape(shape);
        scene.addChild(connection);*/
        canvas.setViewportView(scene.createView());
    }

    @Override
    public EnvNetwork createNetwork(String title) {
        JFrame frame = new JFrame(title);
        canvas = new JScrollPane();
        frame.add(canvas);

        initScene();
        frame.pack();
        frame.setVisible(true);
        return new EnvNetwork(title, title);
    }

    @Override
    public EnvNode createNode(EnvNetwork network, String ID) {
        WidgetAction moveAction = ActionFactory.createMoveAction();
        LabelWidget label1 = new LabelWidget(scene, ID);
        label1.setPreferredLocation(new Point(new Random().nextInt(100), 100));

        label1.getActions().addAction(moveAction);
        mainLayer.addChild(label1);
        return new EnvNode(network, ID, 1);
    }

    @Override
    public EnvEdge createEdge(EnvNetwork network, String ID, EnvNode source, EnvNode target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteNode(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteEdge(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EnvNetwork currentNetwork() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
