package simulation.objects;

import javax.swing.*;
import java.awt.*;

public class ForceVector extends JComponent {

    private Point anchor = new Point(-1, -1);
    private Point mouse = new Point(-1, -1);
    private int rightButton = 2;

    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphic = (Graphics2D) g;

        graphic.setColor(Color.decode("0xfafafa"));
        graphic.setStroke(new BasicStroke(2));
        graphic.drawLine(anchor.x, anchor.y, mouse.x, mouse.y);
        if (rightButton == 1) {
            drawForceVector(graphic);
        }
    }

    private void drawForceVector(Graphics2D graphics){
        //drawForceVector(graphics);
    }

    public void paintLine(){
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
}
