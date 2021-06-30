package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.multi.AskNicknameMulti;

import javax.swing.*;

public class MainGUI {

    private static JPanel currentPanel;

    public static JFrame frame;


    public static void mainGUI(String hostName, int portNumber){

        SwingUtilities.invokeLater(() -> createAndShowGUI(hostName, portNumber));

    }

    private static void createAndShowGUI(String hostName, int portNumber) {
        frame = new JFrame();
        frame.setSize(800, 800);
        currentPanel = new Intro(hostName, portNumber);
        frame.add(currentPanel);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public static void changePanel(JPanel newPanel) {
        frame.remove(currentPanel);
        frame.add(newPanel);
        frame.revalidate();
        frame.repaint();
        currentPanel = newPanel;
    }

}
