package envinterface.netbeans;

import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Widget;
import ui.PluginMenusHandle;

public class NetbeansNetworkView extends EnvNetworkView {

    private JScrollPane canvas = null;
    private NetbeansGraphScene scene = new NetbeansGraphScene();

    private void initScene() {

        // JFrame frame = new JFrame(this.getEnvNetwork().getTitle());

        canvas = new JScrollPane();
        PluginMenusHandle.getTabbedpane().addTab(this.getEnvNetwork().getTitle(), canvas);

        //   frame.add(canvas);
        //  canvas.setSize(300, 300);
        scene = new NetbeansGraphScene();
        canvas.setViewportView(scene.createView());

    //   frame.setVisible(true);
    }

    public NetbeansNetworkView(EnvNetwork envNetwork) {
        super(envNetwork);

        initScene();
    }

    public EnvNodeView createNodeView(EnvNode node) {
        NetbeansNodeView nodeView = new NetbeansNodeView(this, node);
        Widget newnode = scene.addNode(nodeView.getNode().getID());
        nodeView.setNodeWidget(newnode);
        //     NetbeansNetworkView netView = (NetbeansNetworkView) NetbeansInterface.getInstance().currentNetworkView();
        // newnode.getActions().addAction(netView.getScene().createSelectAction());
        return nodeView;
    }

    public NetbeansGraphScene getScene() {
        return scene;
    }

    public void setScene(NetbeansGraphScene scene) {
        this.scene = scene;
    }
}
