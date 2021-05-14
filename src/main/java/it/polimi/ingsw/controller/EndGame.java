package it.polimi.ingsw.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EndGame {

    public static void end (Socket clientSocket){
        PrintWriter out;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Game over, server shutting down");
        } catch (IOException e) {
            System.out.println("User already disconnected");
        }


    }
}
