package model.view.controller;

import simulation.objects.ConnectVector;

import java.awt.event.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class View {

    private Controller viewController;
    private JFrame simulationFrame;
    private JPanel simulationPanel;

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;
    volatile private boolean isDragged = false;
    volatile private boolean isRightButtonPressed;
    volatile private Point currentMousePosition = new Point();
    volatile private Point saveAnchorPoint = new Point();

    private Timer differentiateMousePressedMouseDragged;
    private TimerTask isMouseHolding;

    public void initView(ConnectVector lineComponent){

        simulationFrame = new JFrame();
        simulationPanel = new JPanel(null);

        simulationFrame.setContentPane(simulationPanel);
        simulationPanel.setBackground(Color.decode("0x363636"));

        simulationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        simulationFrame.setMinimumSize(new Dimension(1200, 800));

        lineComponent.setBounds(0, 0, simulationFrame.getWidth(), simulationFrame.getHeight());
        simulationPanel.add(lineComponent);

        simulationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                lineComponent.setBounds(0, 0, simulationFrame.getWidth(), simulationFrame.getHeight());
            }
        });

        simulationPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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

                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseDown = true;
                    isRightButtonPressed = false;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    mouseDown = true;
                    isRightButtonPressed = true;
                }
            }
            @Override
            public void mouseReleased(MouseEvent e){
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
                saveAnchorPoint.setLocation(-1, -1);
                currentMousePosition.setLocation(-1, -1);
                viewController.drawAnchorToMouse(false);
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

    public void tickSimulation(){

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
                        viewController.drawAnchorToMouse(isRightButtonPressed);
                        try{
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } while (mouseDown);
                    isRunning = false;
                    if (!isRightButtonPressed) {
                        viewController.addNewForceSubject();
                    }
                }
            }.start();
        }
    }
}