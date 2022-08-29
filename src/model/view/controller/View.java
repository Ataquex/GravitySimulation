package model.view.controller;

import simulation.objects.ForceVector;

import java.awt.event.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Vector;

public class View {

    private Controller viewController;
    private JFrame simulationFrame;
    private JPanel simulationPanel;

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;
    volatile private boolean isDragged = false;
    volatile private int mouseButtonID;
    volatile private Point currentMousePosition = new Point();
    volatile private Point saveAnchorPoint = new Point();

    private Timer differentiateMousePressedMouseDragged;
    private TimerTask isMouseHolding;
    private javax.swing.Timer tickTimer;

    public void initView(ForceVector pathComponent, KeyListenerReset keyReset){
        simulationFrame = new JFrame();
        simulationPanel = new JPanel(null);

        simulationFrame.setContentPane(simulationPanel);
        simulationPanel.setBackground(Color.decode("0x363636"));

        simulationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        simulationFrame.setMinimumSize(new Dimension(1200, 800));

        pathComponent.setBounds(0, 0, simulationFrame.getWidth(), simulationFrame.getHeight());
        simulationPanel.add(pathComponent);

        simulationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                pathComponent.setBounds(0, 0, simulationFrame.getWidth(), simulationFrame.getHeight());
            }
        });

        simulationFrame.addKeyListener(keyReset);

        simulationPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseButtonID = e.getButton();
                currentMousePosition.setLocation(e.getX(), e.getY());

                if (e.getButton() == MouseEvent.BUTTON2) return;

                differentiateMousePressedMouseDragged = new Timer();
                isMouseHolding = new TimerTask(){
                    @Override
                    public void run(){
                        isDragged = true;
                        initThread();
                    }
                };
                differentiateMousePressedMouseDragged.schedule(isMouseHolding, 110);

                if (!isRunning) {
                    saveAnchorPoint.setLocation(e.getX(), e.getY());
                }

                if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
                    mouseDown = true;
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){

                if (e.getButton() == MouseEvent.BUTTON2) return;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (!isDragged) {
                        currentMousePosition = new Point(e.getX(), e.getY());
                        viewController.addNewAttractor();
                    }
                    mouseDown = false;
                    isDragged = false;
                    differentiateMousePressedMouseDragged.cancel();
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!isDragged) {
                        currentMousePosition = new Point(e.getX(), e.getY());
                        viewController.addNewRepeller();
                    }
                    mouseDown = false;
                    isDragged = false;
                    differentiateMousePressedMouseDragged.cancel();
                }
                viewController.drawAnchorToMouse(2);
            }
        });
        simulationPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentMousePosition.setLocation(e.getX(), e.getY());
            }
        });

        simulationFrame.setVisible(true);
    }

    public void setController(Controller controller){
        viewController = controller;
    }

    public void setUpTimer(int delay){
        tickTimer = new javax.swing.Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewController.tickSimulation();
            }
        });
        tickTimer.setRepeats(true);
        tickTimer.start();
    }

    public void addComponentOnPosition(JLabel component, Vector pos){
        component.setBounds((int) ((float)(pos.get(0)) - (component.getIcon().getIconWidth()/2)), (int) ((float)(pos.get(1)) - (component.getIcon().getIconHeight()/2)), component.getIcon().getIconWidth(), component.getIcon().getIconHeight());
        simulationPanel.add(component);
        simulationFrame.repaint();
    }

    public void resetView(ForceVector pathComponent){
        simulationPanel.removeAll();
        pathComponent.setBounds(0, 0, simulationFrame.getWidth(), simulationFrame.getHeight());
        simulationPanel.add(pathComponent);
        simulationFrame.repaint();

    }




    public Point getSaveAnchorPoint(){
        return saveAnchorPoint;
    }

    public Point getCurrentMousePosition(){
        return currentMousePosition;
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
                        viewController.drawAnchorToMouse(mouseButtonID);
                        try{
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } while (mouseDown);
                    if (mouseButtonID == MouseEvent.BUTTON1) {
                        viewController.addNewForceSubject();
                    }
                    isRunning = false;
                }
            }.start();
        }
    }
}