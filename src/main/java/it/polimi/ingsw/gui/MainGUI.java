package it.polimi.ingsw.gui;

import javax.swing.*;

public class MainGUI {

    public static JFrame frame;


    public static void mainGUI(String hostName, int portNumber){

        SwingUtilities.invokeLater(() -> createAndShowGUI(hostName, portNumber));

    }

    private static void createAndShowGUI(String hostName, int portNumber) {
        frame = new JFrame();
        frame.setSize(800, 800);
        JPanel currentPanel = new Intro(hostName, portNumber);
        frame.add(currentPanel);
        frame.setVisible(true);
        frame.setLayout(null);
    }
}
