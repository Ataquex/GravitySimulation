package simulation.objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Attractor extends ForceCaster{

    public Attractor(float xPos, float yPos, float mass){
        super(xPos, yPos, mass);

        try {
            super.setForceImage(ImageIO.read(new File("src/caster/images/attractor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
