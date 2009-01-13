/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package envinterface.netbeans;

import envinterface.abstractenv.EnvNodeView;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author misiek
 */
public class NetbeansGraphScene extends Scene {

    LayerWidget mainLayer = null;
    LayerWidget connLayer = null;

    public NetbeansGraphScene() {
        super();
        getActions().addAction(ActionFactory.createWheelPanAction());

        mainLayer = new LayerWidget(this);
        this.addChild(mainLayer);
        connLayer = new LayerWidget(this);
        this.addChild(connLayer);

        LayerWidget interLayer = new LayerWidget(this);
        this.addChild(interLayer);

        this.getActions().addAction(ActionFactory.createZoomAction());
    }

    public Widget createNodeWidget(NetbeansNodeView nodeView) {

        LabelWidget widget = new LabelWidget(this);
        widget.setForeground(Color.RED);
        widget.setLabel(nodeView.getNode().getID());
        widget.setPreferredLocation(new Point(100, 50));

        WidgetAction moveAction = ActionFactory.createMoveAction();
        widget.getActions().addAction(moveAction);
        nodeView.setNodeWidget(widget);
        mainLayer.addChild(widget);
        return widget;
    }

    public Widget createConnectionWidget(NetbeansEdgeView edgeView) {
    //    AnchorShape shape = AnchorShapeFactory.createTriangleAnchorShape(18, true, false, 17);

        ConnectionWidget connection = new ConnectionWidget(this);
        connection.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));

        NetbeansNodeView sourceView = (NetbeansNodeView) edgeView.getEnvNetworkView().getNodeViews().get(edgeView.getEdge().getSource().getID());
        NetbeansNodeView targetView = (NetbeansNodeView) edgeView.getEnvNetworkView().getNodeViews().get(edgeView.getEdge().getTarget().getID());

        connection.setSourceAnchor (AnchorFactory.createRectangularAnchor (sourceView.getNodeWidget()));
        connection.setSourceAnchor (AnchorFactory.createRectangularAnchor (targetView.getNodeWidget()));
    //    connection.setSourceAnchorShape(shape);
     //   connection.setTargetAnchorShape(shape);
        edgeView.setConnectionWidget(connection);
        connLayer.addChild(connection);
        return connection;
    }
}






