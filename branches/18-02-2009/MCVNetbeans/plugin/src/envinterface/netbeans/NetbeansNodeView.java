package envinterface.netbeans;

import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;
import java.awt.Point;
import org.netbeans.api.visual.widget.Widget;

public class NetbeansNodeView extends EnvNodeView {

    private Widget nodeWidget;

    public NetbeansNodeView(EnvNetworkView envNetworkView, EnvNode node) {
        super(envNetworkView, node);
    }

    public Widget getNodeWidget() {
        return nodeWidget;
    }

    public void setNodeWidget(Widget nodeWidget) {
        this.nodeWidget = nodeWidget;
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
  //      nodeWidget.getScene().getSceneAnimator().animatePreferredLocation(nodeWidget, new Point(x, y));
    }
}
