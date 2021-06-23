package it.polimi.ingsw.gui.multi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;


public class ShowPersonalBoard extends JPanel implements ActionListener {

    private PersonalBoard personalBoard;
    private boolean fromTurn;
    private final ClientMessageHandler handler;
    private final JButton back;

    public ShowPersonalBoard(ClientMessageHandler handler, boolean fromTurn){
        this.fromTurn = fromTurn;
        this.handler = handler;
        back = new JButton("Go Back");
        back.addActionListener(this);
        back.setBounds(650, 650, 100, 50);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        personalBoard = Data.instanceCreator().getPersonalBoard();
        for(int x = 50; x<1200; x = x + 50){ //percorso fede
            g.drawRect(x, 100, 50, 50);
        }
        g.drawRect(50, 600, 200, 100); //strongbox
        for (int j = 300; j<600; j = j + 150){ //prodcard slot
            g.drawRect(j, 600, 100, 200);
        }

    }







    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back && fromTurn){
            if(e.getSource() == back && fromTurn){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Turn(handler));
                MainGUI.frame.revalidate();
            } else if (e.getSource() == back && !fromTurn){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new WaitingTurn(handler));
                MainGUI.frame.revalidate();
            }
        }
    }
}
