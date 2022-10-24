package simulation.objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Attractor extends ForceCaster{

    public Attractor(float xPos, float yPos, float mass){
        super(xPos, yPos, mass);

        try {
            super.setForceImage(ImageIO.read(new File(getClass().getResource("/caster/images/attractor.png").toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
