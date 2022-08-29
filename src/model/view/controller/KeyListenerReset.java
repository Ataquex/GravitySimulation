package model.view.controller;

import java.awt.event.KeyEvent;

public class KeyListenerReset implements java.awt.event.KeyListener {
    Controller listenerController;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            listenerController.resetObjects();
        }
    }




    public void setListenerController(Controller controller){
        listenerController = controller;
    }
}
