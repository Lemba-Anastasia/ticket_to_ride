package Controllers;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderRunnable implements Runnable {
    private Callback callback;
    private BufferedReader bufferedReader;
    public ReaderRunnable(BufferedReader bufferedReader, Callback callback) {
        this.callback = callback;
        this.bufferedReader = bufferedReader;
    }

    public void run() {
        try {
            String message;
            while ((message = bufferedReader.readLine()) != null) {
                callback.onDataReceived(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());//log
        }
    }

    public interface Callback {
        void onDataReceived(String data);
    }
}
