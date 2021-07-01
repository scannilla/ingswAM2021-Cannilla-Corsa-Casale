package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.controller.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGame extends JPanel implements ActionListener {

    private final JButton joinGame;
    private final ClientMessageHandler handler;
    public JoinGame(ClientMessageHandler handler){
        this.handler = handler;
        joinGame = new JButton("Join Game");
        joinGame.addActionListener(this);
        joinGame.setBounds(350, 350, 100, 100);
        this.add(joinGame);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == joinGame){
            try {
                handler.sendMessageToServer("join game", 111);
            } catch (EndingGameException endingGameException) {
                MainGUI.changePanel(new Error("FATAL ERROR", handler, 0));
            }
        }
    }



}
