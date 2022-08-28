package simulation.objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        try {
            subjectImage = (ImageIO.read(new File("src/caster/images/subject.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        subjectLabel = new JLabel();
        subjectLabel.setIcon(new ImageIcon(subjectImage));
    }

    public JLabel getSubjectLabel() {
        return subjectLabel;
    }

    public Vector getSubjectPosition() {
        return subjectPosition;
    }




    public void calculateForceSubjectDynamics(ArrayList<Attractor> attractorList, ArrayList<Repeller> repellerList){

    }
}
