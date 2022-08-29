package model.view.controller;

import simulation.objects.Attractor;
import simulation.objects.ForceSubject;
import simulation.objects.ForceVector;
import simulation.objects.Repeller;

import java.util.ArrayList;

public class Model {
    private final float GRAVITATIONAL_CONSTANT = 6.6743f;

    private final ArrayList<Attractor> simulationObjectsAttractors = new ArrayList<Attractor>();
    private final ArrayList<Repeller> simulationObjectsRepellers = new ArrayList<Repeller>();
    private final ArrayList<ForceSubject> simulationObjectsForceSubjects = new ArrayList<ForceSubject>();
    private ForceVector simulationObjectsForceVector;

    private final float attractorMass = 0.5f;
    private final float repellerMass = -0.5f;
    private final float subjectMass = 0.5f;




    public void addAttractorToList(Attractor attractor){
        simulationObjectsAttractors.add(attractor);
    }

    public void addRepellerToList(Repeller repeller){
        simulationObjectsRepellers.add(repeller);
    }

    public void addForceSubjectToList(ForceSubject forceSubject){
        simulationObjectsForceSubjects.add(forceSubject);
    }

    public void setForceVector(ForceVector forceVector){
        simulationObjectsForceVector = forceVector;
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

    public ForceVector getForceVector() {
        return simulationObjectsForceVector;
    }

    public float getAttractorMass() {
        return attractorMass;
    }

    public float getRepellerMass() {
        return repellerMass;
    }

    public float getSubjectMass() {
        return subjectMass;
    }
}
