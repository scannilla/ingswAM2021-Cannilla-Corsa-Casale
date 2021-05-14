package it.polimi.ingsw.controller.networkclient;

import java.io.*;
import java.net.*;


public class ClientMain {

    /**
     * this method sets the connection with the server
     */
    public static void main(String[] args) {
        int portNumber;
        String hostName;

        if (args.length != 2) {
            portNumber = 48745;
            hostName = "127.0.0.1";
        }
        else {
            portNumber=Integer.parseInt(args[0]);
            hostName = args[1];
        }



        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            ClientListener listener = new ClientListener(socket);
            Thread listenerThread = new Thread(listener);
            listenerThread.start();
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}