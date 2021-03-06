package it.polimi.ingsw.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientListener;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.controller.singleplayer.LocalSinglePlayer;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.controller.singleplayer.SPMessageHandler;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.local.AskNickname;
import it.polimi.ingsw.gui.local.Local;
import it.polimi.ingsw.gui.multi.AskNicknameMulti;
import it.polimi.ingsw.gui.multi.Multi;

public class Intro extends JPanel implements ActionListener {

    private final JButton local;
    private final JButton multi;
    private final String hostName;
    private final int portNumber;
    private Socket clientSocket;
    private SPClientMessageHandler spHandler;



    public Intro(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        local = new JButton("Play Local");
        multi = new JButton("Play Online");
        local.setBounds(50, 320, 100, 50);
        multi.setBounds(260, 320, 100, 50);
        multi.addActionListener(this);
        local.addActionListener(this);
        this.add(local);
        this.add(multi);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }


    public void paint(Graphics g) {
        g.drawString("Welcome to Master of Renaissance", 100, 50);
        myDrawImagePNG(g);
    }

    private void myDrawImagePNG(Graphics g) {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream("homepage.jpeg");
        BufferedImage img;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        g.drawImage(img, 100, 100, 200, 200, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientMessageHandler cmHandler = null;
        if (e.getSource() == multi) {
            try {
                clientSocket = new Socket(hostName, portNumber);
                cmHandler = new ClientMessageHandler(clientSocket);
                new Thread(new ClientListener(cmHandler, true)).start();
            } catch (UnknownHostException ex) {
                MainGUI.changePanel(new Error("Don't know about host " + hostName, null, 0));
            } catch (IOException ex) {
                MainGUI.changePanel(new Error("Couldn't get the I/O for the current host", null, 0));
            } catch (EndingGameException ex) {
                MainGUI.changePanel(new Error("Game over, disconnecting", null, 0));
            }
        }
        else if(e.getSource()==local) {
            MainGUI.changePanel(new Local(spHandler));
        }

    }

}

