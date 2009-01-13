package envinterface.netbeans;

import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Utilities;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

public class NetbeansNetworkView extends EnvNetworkView {

    private JScrollPane canvas = null;
    private NetbeansGraphScene scene = new NetbeansGraphScene();

    private void initScene() {

        JFrame frame = new JFrame(this.getEnvNetwork().getTitle());
        canvas = new JScrollPane();
        frame.add(canvas);
        canvas.setSize(300, 300);
        scene = new NetbeansGraphScene();
        canvas.setViewportView(scene.createView());

        frame.setVisible(true);
    }

    public NetbeansNetworkView(EnvNetwork envNetwork) {
        super(envNetwork);

        initScene();
    }

    public EnvNodeView createNodeView(EnvNode node) {
        NetbeansNodeView nodeView = new NetbeansNodeView(this, node);
        scene.createNodeWidget(nodeView);
        return nodeView;
    }
}
