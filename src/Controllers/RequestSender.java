package Controllers;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class RequestSender {
    private BufferedReader in;
    private PrintWriter out;
    public RequestSender(BufferedReader in, PrintWriter out) {
        this.in=in;
        this.out=out;
    }

    public void sendMessage(String text) {
        out.println(text);
    }

    public void activateIntidentNode() {
        out.println("/activate intident node");
    }

    public void buidRailWay() {
        out.println("/buid a segment a rail way");
    }
}
