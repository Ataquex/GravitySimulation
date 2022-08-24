package model.view.controller;

import javax.swing.*;

public class Controller {

    private View controllerView;
    private Model controllerModel;

    public Controller(View view, Model model){
        controllerView = view;
        controllerModel = model;

        this.initSimulation();
    }

    private void initSimulation(){
        controllerView.setController(this);

        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                controllerView.initView();
            }
        });
    }

    public void tickSimulation(){

    }
}
