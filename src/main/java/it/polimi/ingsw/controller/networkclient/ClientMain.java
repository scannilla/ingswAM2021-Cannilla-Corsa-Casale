package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;

import java.io.*;
import java.net.*;


public class ClientMain {


    /**
     * this method sets the connection with the server
     */
    public static void main(String[] args) {
        int portNumber = 48745;
        String hostName = "127.0.0.1";
        boolean gui = false;

        for (int i = 0; i < args.length; i++) {
            try {
                switch (args[i]) {
                    case "-h":
                        hostName = args[i + 1];
                        break;
                    case "-p":
                        try {
                            portNumber = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException e) {
                            System.out.println("not a number");
                        }
                        break;
                    case "-c":
                        gui = false;
                        break;
                    case "-g":
                        gui = true;
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Invalid arguments: -h hostname -p port number -c|-g for cli or gui, leave blank for default settings");
                return;
            }
        }

        Socket clientSocket;
        BufferedReader stdIn;
        try {
            clientSocket = new Socket(hostName, portNumber);
            ClientMessageHandler cmHandler = new ClientMessageHandler(clientSocket);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientListener(cmHandler, gui)).start();
            String input;
            while ((input = stdIn.readLine()) != null) {
                cmHandler.sendMessageToServer(input);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get the I/O for the current host");
            System.exit(1);
        } catch (EndingGameException e) {
            System.err.println("Game over, disconnecting");
            System.exit(1);
        }
    }
}