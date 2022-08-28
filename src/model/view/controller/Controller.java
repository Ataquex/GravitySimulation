package model.view.controller;

import simulation.objects.Attractor;
import simulation.objects.ConnectVector;
import simulation.objects.ForceSubject;
import simulation.objects.Repeller;

import javax.swing.*;

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
        controllerModel.addAttractorToList(new Attractor(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, 50));
        System.out.println("new Attractor");
    }

    public void addNewRepeller(){
        controllerModel.addRepellerToList(new Repeller(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, -50));
        System.out.println("new Repeller");
    }

    public void addNewForceSubject(){
        controllerModel.addForceSubjectToList(new ForceSubject(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, 50));
        System.out.println("new ForceSubject");
    }

    public void drawAnchorToMouse(boolean rightButton){
        mouseAnchor.setAnchor(controllerView.getSaveAnchorPoint());
        mouseAnchor.setMouse(controllerView.getCurrentMousePosition());
        mouseAnchor.setRightButton(rightButton);
        mouseAnchor.paintLine();
    }
}
