package model.view.controller;

import simulation.objects.Attractor;
import simulation.objects.ForceVector;
import simulation.objects.ForceSubject;
import simulation.objects.Repeller;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private View controllerView;
    private Model controllerModel;
    private KeyListenerReset keyReset = new KeyListenerReset();

    public Controller(View view, Model model){
        controllerView = view;
        controllerModel = model;
        model.setForceVector(new ForceVector());
        keyReset.setListenerController(this);

        this.initSimulation();
    }

    private void initSimulation(){
        controllerView.setController(this);

        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                controllerView.initView(controllerModel.getForceVector(), keyReset);
                controllerView.setUpTimer(15);
            }
        });
    }

    public void tickSimulation(){
        ArrayList<Attractor> tempAttractorList = controllerModel.getAttractorsList();
        for(int i = 0; i < tempAttractorList.size(); i++){
            controllerView.addComponentOnPosition(tempAttractorList.get(i).getForceLabel(), tempAttractorList.get(i).getForcePos());
        }
        ArrayList<Repeller> tempRepellerList = controllerModel.getRepellersList();
        for(int i = 0; i < tempRepellerList.size(); i++){
            controllerView.addComponentOnPosition(tempRepellerList.get(i).getForceLabel(), tempRepellerList.get(i).getForcePos());
        }
        ArrayList<ForceSubject> tempSubjectList = controllerModel.getForceSubjectsList();
        for(int i = 0; i < tempSubjectList.size(); i++){
            controllerView.addComponentOnPosition(tempSubjectList.get(i).getSubjectLabel(), tempSubjectList.get(i).getSubjectPosition());
            tempSubjectList.get(i).calculateForceSubjectDynamics(tempAttractorList, tempRepellerList, controllerModel.getGRAVITATIONAL_CONSTANT());
        }
    }




    public void addNewAttractor(){
        controllerModel.addAttractorToList(new Attractor(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, controllerModel.getAttractorMass()));
    }

    public void addNewRepeller(){
        controllerModel.addRepellerToList(new Repeller(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, controllerModel.getRepellerMass()));
    }

    public void addNewForceSubject(){
        System.out.println("new ForceSubject at: " + controllerView.getCurrentMousePosition() + "        from anchor: " + controllerView.getSaveAnchorPoint());
        controllerModel.addForceSubjectToList(new ForceSubject(controllerView.getCurrentMousePosition().x, controllerView.getCurrentMousePosition().y, controllerModel.getSubjectMass(), controllerView.getSaveAnchorPoint().x, controllerView.getSaveAnchorPoint().y));
    }

    public void resetObjects(){
        controllerModel.resetObjects();
        controllerView.resetView(controllerModel.getForceVector());
    }

    public void drawAnchorToMouse(int rightButton){
        controllerModel.getForceVector().setAnchor(controllerView.getSaveAnchorPoint());
        controllerModel.getForceVector().setMouse(controllerView.getCurrentMousePosition());
        controllerModel.getForceVector().setRightButton(rightButton);
        controllerModel.getForceVector().setMass(controllerModel.getSubjectMass());
        controllerModel.getForceVector().paintLine(controllerModel.getAttractorsList(), controllerModel.getRepellersList(),controllerModel.getGRAVITATIONAL_CONSTANT());
    }
}
