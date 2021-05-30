package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.controller.Message;

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

        Socket clientSocket;
        ObjectOutputStream out;
        BufferedReader stdIn;
        try {
            clientSocket = new Socket(hostName, portNumber);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientListener(clientSocket)).start();
            String input;
            while ((input = stdIn.readLine()) != null) {
                out.writeObject(createMessage(input));
                out.flush();
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get the I/O for the current host");
            System.exit(1);
        }
    }

    private static Message createMessage(String input) {
        return new Message(10, input, "prova");
    }
}