package mcv.tester;

import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ding.view.DGraphView;
import ding.view.DingCanvas;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import mcv.utils.Messenger;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


public class TestCanvas {

    public static void test() {
        final JDesktopPane cytoDesk = Cytoscape.getDesktop().getNetworkViewManager().getDesktopPane();


        //   JInternalFrame palette = new JInternalFrame("Palette", true, true, true,
        //           true);

        //   palette.addVetoableChangeListener(new IconPolice());

        // palette.setBounds(50, 50, 300, 300);
        JInternalFrame frames[] = cytoDesk.getAllFrames();

        Messenger.message(frames.length);

        //    cytoDesk.add(palette, JDesktopPane.PALETTE_LAYER);
        //   palette.setVisible(true);


        /*  CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        DGraphView dNetworkView = (DGraphView) cyNetworkView;
        
        DingCanvas BACKGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.FOREGROUND_CANVAS);
         */
        /*  BACKGROUNDCanvas.addMouseListener(new MouseListener() {
        
        public void mouseClicked(MouseEvent e) {
        Messenger.Message("saasasa");
        }
        
        public void mousePressed(MouseEvent e) {
        Messenger.Message("saasasa");
        }
        
        public void mouseReleased(MouseEvent e) {
        Messenger.Message("saasasa");
        }
        
        public void mouseEntered(MouseEvent e) {
        Messenger.Message("saasasa");
        }
        
        public void mouseExited(MouseEvent e) {
        Messenger.Message("saasasa");
        }
        });*/
        TestPaintAbs a = new TestPaintAbs();
        TestPaintAbs b = new TestPaintAbs();
        a.setBackground(new Color(1, 1, 0));
        a.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                Messenger.message("componentResized");
            }

            public void componentMoved(ComponentEvent e) {
                Messenger.message("componentMoved");
            }

            public void componentShown(ComponentEvent e) {
                Messenger.message("componentShown");
            }

            public void componentHidden(ComponentEvent e) {
                Messenger.message("componentHidden");
            }
        });
        a.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                Messenger.message("mouseClicked");
            }

            public void mousePressed(MouseEvent e) {
                Messenger.message("mousePressed");
            }

            public void mouseReleased(MouseEvent e) {
                Messenger.message("mouseReleased");
            }

            public void mouseEntered(MouseEvent e) {
                Messenger.message("mouseEntered");
            }

            public void mouseExited(MouseEvent e) {
                Messenger.message("mouseExited");
            }
        });


        //     palette.add(a);
        //     palette.add(b);
        a.setSize(40, 40);
        a.setBounds(10, 10, 40, 40);
        b.setBounds(50, 50, 90, 90);
        for (JInternalFrame frame : frames) {
            frame.add(a);
            frame.add(b);
            //   a.setVisible(true);
            frame.pack();
        //    frame.repaint();
        }
    /*   BACKGROUNDCanvas.add(a);
    cyNetworkView.updateView();
    BACKGROUNDCanvas.repaint();*/

    }

    public static void test2() {
        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        DGraphView dNetworkView = (DGraphView) cyNetworkView;
        DingCanvas BACKGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.FOREGROUND_CANVAS);


        TestPaintAbs a = new TestPaintAbs();
        TestPaintAbs b = new TestPaintAbs();
        a.setBackground(new Color(1, 1, 0));
        /* a.addComponentListener(new ComponentListener() {
        
        public void componentResized(ComponentEvent e) {
        Messenger.Message("componentResized");
        }
        
        public void componentMoved(ComponentEvent e) {
        Messenger.Message("componentMoved");
        }
        
        public void componentShown(ComponentEvent e) {
        Messenger.Message("componentShown");
        }
        
        public void componentHidden(ComponentEvent e) {
        Messenger.Message("componentHidden");
        }
        });
        a.addMouseListener(new MouseListener() {
        
        public void mouseClicked(MouseEvent e) {
        Messenger.Message("mouseClicked");
        }
        
        public void mousePressed(MouseEvent e) {
        Messenger.Message("mousePressed");
        }
        
        public void mouseReleased(MouseEvent e) {
        Messenger.Message("mouseReleased");
        }
        
        public void mouseEntered(MouseEvent e) {
        Messenger.Message("mouseEntered");
        }
        
        public void mouseExited(MouseEvent e) {
        Messenger.Message("mouseExited");
        }
        });*/

        a.setSize(40, 40);
        a.setBounds(10, 10, 40, 40);
        b.setBounds(50, 50, 90, 90);

        BACKGROUNDCanvas.add(a);
        BACKGROUNDCanvas.add(b);

    }

    public static void test3() {

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        DGraphView dNetworkView = (DGraphView) cyNetworkView;

        DingCanvas BACKGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.BACKGROUND_CANVAS);

        DingCanvas FORGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.FOREGROUND_CANVAS);
        DingCanvas NETGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.NETWORK_CANVAS);
        FORGROUNDCanvas.setVisible(false);
        NETGROUNDCanvas.setVisible(false);
        TestPaintAbs a = new TestPaintAbs();
        TestPaintAbs b = new TestPaintAbs();
        a.setBackground(new Color(1, 1, 0));
        BACKGROUNDCanvas.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                Messenger.message("back");
            }

            public void mousePressed(MouseEvent e) {
                Messenger.message("back");
            }

            public void mouseReleased(MouseEvent e) {
                Messenger.message("back");
            }

            public void mouseEntered(MouseEvent e) {
                Messenger.message("back");
            }

            public void mouseExited(MouseEvent e) {
                Messenger.message("back");
            }
        });
        a.addComponentListener(new ComponentListener() {

            public void componentResized(ComponentEvent e) {
                Messenger.message("componentResized");
            }

            public void componentMoved(ComponentEvent e) {
                Messenger.message("componentMoved");
            }

            public void componentShown(ComponentEvent e) {
                Messenger.message("componentShown");
            }

            public void componentHidden(ComponentEvent e) {
                Messenger.message("componentHidden");
            }
        });
        a.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                Messenger.message("mouseClicked");
            }

            public void mousePressed(MouseEvent e) {
                Messenger.message("mousePressed");
            }

            public void mouseReleased(MouseEvent e) {
                Messenger.message("mouseReleased");
            }

            public void mouseEntered(MouseEvent e) {
                Messenger.message("mouseEntered");
            }

            public void mouseExited(MouseEvent e) {
                Messenger.message("mouseExited");
            }
        });
        a.setSize(40, 40);
        a.setBounds(10, 10, 40, 40);
        b.setBounds(50, 50, 90, 90);
        BACKGROUNDCanvas.add(a);
        cyNetworkView.updateView();
        BACKGROUNDCanvas.repaint();

    }
}