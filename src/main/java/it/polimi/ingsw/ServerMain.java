package it.polimi.ingsw;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main (int arg, String[] args) {
        int portNumber;
        String hostName;
        if(arg==2) {
            portNumber =Integer.parseInt(args[0]);
            hostName =args[1];
        }
        else {
            portNumber =16024;
            hostName ="127.0.0.1";
        }
        System.out.println("Starting server");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket clientSocket = null;
        try {
            assert serverSocket != null;
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            assert clientSocket != null;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
