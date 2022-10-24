package simulation.objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Repeller extends ForceCaster{

    public Repeller(float xPos, float yPos, float mass){
        super(xPos, yPos, mass);

        try {
            super.setForceImage(ImageIO.read(new File(getClass().getResource("/caster/images/repeller.png").toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
