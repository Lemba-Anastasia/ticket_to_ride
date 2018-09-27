package Controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lemba on 12.09.2018.
 */
public class ClavaListener implements KeyListener {
    private Controller controller;
    public ClavaListener(Controller controller) {
        this.controller=controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()==KeyEvent.VK_A){
            controller.activateIntidentNode();
        }
        else if(e.getKeyChar()==KeyEvent.VK_ENTER){
           controller.buidRailWay();
        }
        System.out.println("TYPED");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("PRESSED: "+e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("RELEASED");
    }
}
