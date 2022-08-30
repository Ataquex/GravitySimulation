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
    private Vector subjectVelocity = new Vector<>();
    private Vector subjectPosition = new Vector<>();

    public ForceSubject(float posX, float posY, float mass, float goalX, float goalY){
        subjectVelocity.add(-((posX - goalX) / 30));
        subjectVelocity.add(-((posY - goalY) / 30));

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




    public void calculateForceSubjectDynamics(ArrayList<Attractor> attractorList, ArrayList<Repeller> repellerList, float G_CONSTANT){
        Vector<Float> forceVector = new Vector<Float>();
        forceVector.add(0.0f);
        forceVector.add(0.0f);
        float force;
        float radius;

        for (Attractor attractor : attractorList) {
            forceVector.set(0, (float) attractor.getForcePos().get(0) - (float) subjectPosition.get(0));
            forceVector.set(1, (float) attractor.getForcePos().get(1) - (float) subjectPosition.get(1));
            radius = (float) (Math.sqrt((forceVector.get(0) * forceVector.get(0)) + (forceVector.get(1) * forceVector.get(1))));
            force = (G_CONSTANT * subjectMass * attractor.getForceMass()) / (radius * radius);

            if (radius != 0) {
                subjectVelocity.set(0, (float) subjectVelocity.get(0) + forceVector.get(0) * force);
                subjectVelocity.set(1, (float) subjectVelocity.get(1) + forceVector.get(1) * force);
            }
        }

        for (Repeller repeller : repellerList) {
            forceVector.set(0, (float) repeller.getForcePos().get(0) - (float) subjectPosition.get(0));
            forceVector.set(1, (float) repeller.getForcePos().get(1) - (float) subjectPosition.get(1));
            radius = (float) (Math.sqrt(forceVector.get(0) * forceVector.get(0) + (forceVector.get(1) * forceVector.get(1))));
            force = (G_CONSTANT * subjectMass * repeller.getForceMass()) / (radius * radius);

            if (radius != 0) {
                subjectVelocity.set(0, (float) subjectVelocity.get(0) + forceVector.get(0) * force);
                subjectVelocity.set(1, (float) subjectVelocity.get(1) + forceVector.get(1) * force);
            }
        }

        subjectPosition.set(0, (float)subjectPosition.get(0) + (float)subjectVelocity.get(0));
        subjectPosition.set(1, (float)subjectPosition.get(1) + (float)subjectVelocity.get(1));
    }
}
