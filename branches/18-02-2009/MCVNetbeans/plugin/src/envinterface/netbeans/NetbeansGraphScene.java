/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package envinterface.netbeans;

import java.awt.Color;
import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import utils.ColorGenerator;

/**
 *
 * @author misiek
 */
public class NetbeansGraphScene extends GraphScene<String, String> {

    LayerWidget mainLayer = null;
    LayerWidget connLayer = null;
    LayerWidget backgroundLayer = null;
    private Border BORDER_4 = BorderFactory.createLineBorder(4);

    public NetbeansGraphScene() {
        super();
        //   getActions().addAction(ActionFactory.createWheelPanAction());

        backgroundLayer = new LayerWidget(this);
        this.addChild(backgroundLayer);
        mainLayer = new LayerWidget(this);
        this.addChild(mainLayer);
        connLayer = new LayerWidget(this);
        this.addChild(connLayer);

        getActions().addAction(ActionFactory.createRectangularSelectAction(this, backgroundLayer));
        this.getActions().addAction(ActionFactory.createZoomAction());
    }

    public Widget createConnectionWidget(NetbeansEdgeView edgeView) {
        return null;
    //    AnchorShape shape = AnchorShapeFactory.createTriangleAnchorShape(18, true, false, 17);
    }

    @Override
    protected Widget attachNodeWidget(String nodeViewID) {

        LabelWidget widget = new LabelWidget(this);
        widget.setForeground(Color.RED);

        widget.setLabel(nodeViewID);
        //    widget.setBorder(BORDER_4);
        widget.setPreferredLocation(new Point(ColorGenerator.random(200), ColorGenerator.random(200)));

        //     WidgetAction moveAction = ActionFactory.createMoveAction();
        //    widget.getActions().addAction(moveAction);
        mainLayer.addChild(widget);
        return widget;
    }

    @Override
    protected Widget attachEdgeWidget(String edgeID) {
        /*    ConnectionWidget connection = new ConnectionWidget(this);
        connection.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));

        NetbeansNodeView sourceView = (NetbeansNodeView) edgeView.getEnvNetworkView().getNodeViews().get(edgeView.getEdge().getSource().getID());
        NetbeansNodeView targetView = (NetbeansNodeView) edgeView.getEnvNetworkView().getNodeViews().get(edgeView.getEdge().getTarget().getID());

        System.out.println("sourceView: " + sourceView.getNode().getID() + " targetView: " + targetView.getNode().getID());

        connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceView.getNodeWidget()));
        connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(targetView.getNodeWidget()));
        //    connection.setSourceAnchorShape(shape);
        //   connection.setTargetAnchorShape(shape);
        edgeView.setConnectionWidget(connection);
        connLayer.addChild(connection);*/
        return null;
    }

    @Override
    protected void attachEdgeSourceAnchor(String arg0, String arg1, String arg2) {
    }

    @Override
    protected void attachEdgeTargetAnchor(String arg0, String arg1, String arg2) {
    }
}






