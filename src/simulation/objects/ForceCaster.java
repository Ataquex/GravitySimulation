package simulation.objects;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

abstract public class ForceCaster {

    private float forceMass;
    private Vector forcePos = new Vector<>();
    private BufferedImage forceImage;
    private JLabel forceLabel = new JLabel();

    public ForceCaster(int xPos, int yPos, float mass){
        forceMass = mass;
        forcePos.add(xPos);
        forcePos.add(yPos);
    }

    public Vector getForcePos(){
        return forcePos;
    }

    public float getForceMass(){
        return forceMass;
    }

    public JLabel getForceLabel(){
        return forceLabel;
    }

    public void setForceImage(BufferedImage forceImage) {
        this.forceImage = forceImage;
        forceLabel.setIcon(new ImageIcon(forceImage));
    }
}
