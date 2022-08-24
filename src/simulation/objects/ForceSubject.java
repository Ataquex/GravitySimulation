package simulation.objects;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

public class ForceSubject {

    private BufferedImage subjectImage;
    private JLabel subjectLabel;
    private float subjectMass;
    private Vector subjectAcceleration = new Vector<>();
    private Vector subjectVelocity = new Vector<>();
    private Vector subjectPosition = new Vector<>();

    public ForceSubject(int posX, int posY, float mass){
        subjectPosition.add(posX);
        subjectPosition.add(posY);
        subjectMass = mass;
    }

    public void calculateForceSubjectDynamics(ArrayList<Attractor> attractorList, ArrayList<Repeller> repellerList){

    }
}
