package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.Turn;
import it.polimi.ingsw.production.ProdCardSlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActiveProduction extends JPanel implements ActionListener{

    private PersonalBoard personalBoard;
    private ProdCardSlot prodCardSlot;
    private final JButton selectStand, selectSlot1, selectSlot2, selectSlot3, back;
    private SPClientMessageHandler handler;
    public ActiveProduction(SPClientMessageHandler handler){
        this.handler = handler;
        back = new JButton("Go Back");
        selectStand = new JButton("select");
        selectSlot1 = new JButton("select");
        selectSlot2 = new JButton("select");
        selectSlot3 = new JButton("select");
        selectStand.setBounds(100, 600, 140, 50);
        selectSlot1.setBounds(250, 600, 140, 50);
        selectSlot2.setBounds(400, 600, 140, 50);
        selectSlot3.setBounds(550, 600, 140, 50);
        back.setBounds(650, 660, 100, 50);
        this.add(selectStand);
        this.add(selectSlot1);
        this.add(selectSlot2);
        this.add(selectSlot3);
        this.add(back);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){

        prodCardSlot = Data.instanceCreator().getPersonalBoard().getProdCardSlot();
        g.drawString("Select a production power to activate", 300, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == selectStand){
            try{
                handler.sendMessageToServer("standard production", 169);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == selectSlot1){
            try{
                handler.sendMessageToServer("card production 1", 170);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == selectSlot2){
            try{
                handler.sendMessageToServer("card production 2", 170);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == selectSlot3){
            try{
                handler.sendMessageToServer("card production 3", 170);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }


    }
}
