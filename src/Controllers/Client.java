package Controllers;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    BufferedReader in = null;
    PrintWriter out = null;
    Socket fromserver = null;
    String ip;
    int port;
    private View view;
    Controller controller;
    public Client(String ip, int port){
        this.ip=ip;
        this.port=port;
        controller = new Controller();
        view = new View(controller);
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.paintGraph();
            }
        });
        timer.start();
        RequestSender requestSender = null;
        try {
            fromserver = new Socket(ip, port);
            in =new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            out = new PrintWriter(fromserver.getOutputStream(),true);
            JOptionPane.showMessageDialog(new JFrame(), "Подключено");
            requestSender = new RequestSender(in,out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "Ошибка подключения");
        }
        view.setRequestSender(requestSender);
    }

    public BufferedReader getIn() {
        return in;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }
}
