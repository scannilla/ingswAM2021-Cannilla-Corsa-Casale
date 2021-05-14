package it.polimi.ingsw.controller.networkserver;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class CheckConnection implements Runnable{
    private final PrintWriter out;

    public CheckConnection(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            out.println("ping");
        }
    }
}
