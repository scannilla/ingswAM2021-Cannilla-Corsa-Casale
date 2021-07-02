package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscardOrMoveOrTransform extends JPanel implements ActionListener {

    private final ClientMessageHandler cmHandler;
    private final JButton discard, move, transform;

    public DiscardOrMoveOrTransform(ClientMessageHandler cmHandler, int code){
        this.cmHandler = cmHandler;
        discard = new JButton("Discard");
        move= new JButton("Move");
        transform = new JButton("Transform");
        if (code == 1){
            discard.setBounds(350, 500, 100, 50);
            move.setBounds(450, 500, 100, 50);
            discard.addActionListener(this);
            move.addActionListener(this);
            this.add(discard);
            this.add(move);
        } else {
            discard.setBounds(350, 500, 100, 50);
            transform.setBounds(450, 500, 100, 50);
            discard.addActionListener(this);
            transform.addActionListener(this);
            this.add(discard);
            this.add(transform);
        }
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == discard){
            try{
                cmHandler.sendMessageToServer("discard");
            } catch (EndingGameException ex){
                MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
            }
        } else if (e.getSource() == move){
            MainGUI.changePanel(new Move(cmHandler));
        } else if(e.getSource() == transform){
           MainGUI.changePanel(new ResourceSelection(cmHandler));
        }
    }
}
