package model.view.controller;

import simulation.objects.Attractor;
import simulation.objects.ForcePath;
import simulation.objects.ForceSubject;
import simulation.objects.Repeller;

import java.util.ArrayList;

public class Model {
    private final float GRAVITATIONAL_CONSTANT = 10;

    private ArrayList<Attractor> simulationObjectsAttractors = new ArrayList<Attractor>();
    private ArrayList<Repeller> simulationObjectsRepellers = new ArrayList<Repeller>();
    private ArrayList<ForceSubject> simulationObjectsForceSubjects = new ArrayList<ForceSubject>();
    private ForcePath simulationObjectsForcePath;




    public void addAttractorToList(Attractor attractor){
        simulationObjectsAttractors.add(attractor);
    }

    public void addRepellerToList(Repeller repeller){
        simulationObjectsRepellers.add(repeller);
    }

    public void addForceSubjectToList(ForceSubject forceSubject){
        simulationObjectsForceSubjects.add(forceSubject);
    }

    public void setForcePath(ForcePath forcePath){
        simulationObjectsForcePath = forcePath;
    }




    public float getGRAVITATIONAL_CONSTANT() {
        return GRAVITATIONAL_CONSTANT;
    }

    public ArrayList<Attractor> getAttractorsList() {
        return simulationObjectsAttractors;
    }

    public ArrayList<Repeller> getRepellersList() {
        return simulationObjectsRepellers;
    }

    public ArrayList<ForceSubject> getForceSubjectsList() {
        return simulationObjectsForceSubjects;
    }

    public ForcePath getForcePathList() {
        return simulationObjectsForcePath;
    }
}
