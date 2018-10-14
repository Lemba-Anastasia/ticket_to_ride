package Controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lemba on 12.09.2018.
 */
public class ClavaListener implements KeyListener {
    private RequestSender requestSender;
    public ClavaListener(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()==KeyEvent.VK_A){
            requestSender.activateIntidentNode();
        }
        else if(e.getKeyChar()==KeyEvent.VK_ENTER){
           requestSender.buidRailWay();
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
