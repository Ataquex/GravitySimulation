package model.view.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class View {

    private Controller viewController;
    private JFrame simulationFrame;
    private JPanel simulationPanel;

    public void initView(){
        simulationFrame = new JFrame();
        simulationPanel = new JPanel(null);

        simulationFrame.setContentPane(simulationPanel);
        simulationPanel.setBackground(Color.decode("0x363636"));

        simulationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        simulationFrame.setMinimumSize(new Dimension(1200, 800));
        simulationFrame.setVisible(true);

        simulationPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("left");
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("right");
                }
            }
        });
        simulationPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println("X: " + e.getX() + "    Y: " + e.getY());
            }
        });
    }

    public void setController(Controller controller){
        viewController = controller;
    }

    public void tickSimulation(){

    }
}
