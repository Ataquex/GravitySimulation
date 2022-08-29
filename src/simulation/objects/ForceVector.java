package simulation.objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ForceVector extends JComponent {

    private Point anchor = new Point(-1, -1);
    private Point mouse = new Point(-1, -1);

    private ArrayList<Attractor> attractorList;
    private ArrayList<Repeller> repellerList;
    private Vector subjectPosition = new Vector<>();
    private Vector subjectVelocity = new Vector<>();
    private float G_CONSTANT;

    private int rightButton = 2;
    private float mass;

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphic = (Graphics2D) g;
        graphic.setColor(Color.decode("0xfafafa"));
        graphic.setStroke(new BasicStroke(2));

        if (rightButton != 3) {
            graphic.drawLine(anchor.x, anchor.y, mouse.x, mouse.y);
        } else {
            graphic.drawLine(-1, -1, -1, -1);
        }
        if (rightButton == 1) {
            subjectVelocity.add((float)((anchor.x - mouse.x) / 30));
            subjectVelocity.add((float)((anchor.y - mouse.y) / 30));

            subjectPosition.add((float)mouse.x);
            subjectPosition.add((float)anchor.y);

            drawForceVector(graphic, 100);
        }
    }

    private void drawForceVector(Graphics2D graphics, int row){
        row--;
        Vector<Float> forceVector = new Vector<Float>();
        forceVector.add(0.0f);
        forceVector.add(0.0f);
        float force;
        float radius;

        for (Attractor attractor : attractorList) {
            forceVector.set(0, (float) attractor.getForcePos().get(0) - (float) subjectPosition.get(0));
            forceVector.set(1, (float) attractor.getForcePos().get(1) - (float) subjectPosition.get(1));
            radius = (float) (Math.sqrt((forceVector.get(0) * forceVector.get(0)) + (forceVector.get(1) * forceVector.get(1))));
            force = (G_CONSTANT * mass * attractor.getForceMass()) / (radius * radius);

            subjectVelocity.set(0, (float) subjectVelocity.get(0) + forceVector.get(0) * force);
            subjectVelocity.set(1, (float) subjectVelocity.get(1) + forceVector.get(1) * force);
        }

        for (Repeller repeller : repellerList) {
            forceVector.set(0, (float) repeller.getForcePos().get(0) - (float) subjectPosition.get(0));
            forceVector.set(1, (float) repeller.getForcePos().get(1) - (float) subjectPosition.get(1));
            radius = (float) (Math.sqrt(forceVector.get(0) * forceVector.get(0) + (forceVector.get(1) * forceVector.get(1))));
            force = (G_CONSTANT * mass * repeller.getForceMass()) / (radius * radius);

            subjectVelocity.set(0, (float) subjectVelocity.get(0) + forceVector.get(0) * force);
            subjectVelocity.set(1, (float) subjectVelocity.get(1) + forceVector.get(1) * force);
        }
        //graphics.drawLine((int)(float)subjectPosition.get(0), (int)(float)subjectPosition.get(1), (int)((float)subjectPosition.get(0) + (float)subjectVelocity.get(0)), (int)((float)subjectPosition.get(1) + (float)subjectVelocity.get(1)));

        subjectPosition.set(0, (float)subjectPosition.get(0) + (float)subjectVelocity.get(0));
        subjectPosition.set(1, (float)subjectPosition.get(1) + (float)subjectVelocity.get(1));

        if (row > 10) {
            drawForceVector(graphics, row);
        }
    }

    public void paintLine(ArrayList<Attractor> attractorList, ArrayList<Repeller> repellerList, float G){
        this.attractorList = attractorList;
        this.repellerList = repellerList;
        this.G_CONSTANT = G;
        repaint();
    }




    public void setAnchor(Point anchor){
        this.anchor = anchor;
    }

    public void setMouse(Point mouse){
        this.mouse = mouse;
    }

    public void setRightButton(int rightButton){
        this.rightButton = rightButton;
    }

    public void setMass(float mass){
        this.mass = mass;
    }
}
