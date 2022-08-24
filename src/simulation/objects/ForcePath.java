package simulation.objects;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

public class ForcePath {

    private Vector pathPosition = new Vector<>();
    private BufferedImage pathImage;
    private JLabel pathLabel;

    public ForcePath(int posX, int posY, int frameWidth, int frameHeight){
        pathPosition.add(posX);
        pathPosition.add(posY);
        pathImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawForcePath(ArrayList<Attractor> attractorList, ArrayList<Repeller> repellerList){

    }
}
