package simulation.objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Repeller extends ForceCaster{

    public Repeller(int xPos, int yPos, float mass){
        super(xPos, yPos, mass);

        try {
            super.setForceImage(ImageIO.read(new File("src/caster/images/repeller.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
