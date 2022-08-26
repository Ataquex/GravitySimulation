package model.view.controller;

import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {

    private Controller viewController;
    private JFrame simulationFrame;
    private JPanel simulationPanel;

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;
    volatile private Point saveAnchorPoint;
    private Timer differentiateMousePressedMouseDragged = new Timer();

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
                if (!isRunning) {
                    saveAnchorPoint = new Point(e.getX(), e.getY());
                } else {
                    saveAnchorPoint = null;
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseDown = true;
                    initThread();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseDown = false;
                }
            }
        });
    }

    public void setController(Controller controller){
        viewController = controller;
    }

    public void tickSimulation(){

    }




    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }

    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        System.out.println(saveAnchorPoint);
                    } while (mouseDown);
                    isRunning = false;
                }
            }.start();
        }
    }
}
