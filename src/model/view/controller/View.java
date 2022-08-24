package model.view.controller;

import javax.swing.*;
import java.awt.*;

public class View {

    private Controller viewController;
    private JFrame simulationFrame;

    public void initView(){
        simulationFrame = new JFrame();
        simulationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        simulationFrame.setMinimumSize(new Dimension(1200, 800));
        simulationFrame.setVisible(true);
        //simulationFrame.pack();
    }

    public void setController(Controller controller){
        viewController = controller;
    }

    public void tickSimulation(){

    }
}
