package View;

import Controllers.Client;
import Controllers.ConnectionHandler;

/**
 * Created by Lemba on 07.09.2018.
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost",1357);
        new ConnectionHandler(client).startListening();
    }
}
