package model.view.controller;

import simulation.objects.ConnectVector;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private View controllerView;
    private Model controllerModel;
    private ConnectVector mouseAnchor = new ConnectVector();

    public Controller(View view, Model model){
        controllerView = view;
        controllerModel = model;

        this.initSimulation();
    }

    private void initSimulation(){
        controllerView.setController(this);

        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                controllerView.initView(mouseAnchor);
            }
        });
    }

    public void tickSimulation(){

    }




    public void addNewAttractor(){

    }

    public void addNewRepeller(){

    }

    public void decideCase(boolean button){
        mouseAnchor.setAnchor(controllerView.getSaveAnchorPoint());
        mouseAnchor.setMouse(controllerView.getCurrentMousePosition());
        mouseAnchor.paintLine();
    }
}
