package View;

import Controllers.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lemba on 07.09.2018.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        final View view = new View(controller);
        controller.setView(view);
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.paintGraph();
            }
        });
        timer.start();
    }
}
