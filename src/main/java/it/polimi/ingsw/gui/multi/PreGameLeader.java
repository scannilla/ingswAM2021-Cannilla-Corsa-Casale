package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.leader.LeaderCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreGameLeader extends JPanel implements ActionListener {

    private LeaderCard[] toChooseLeaderCards;
    private JButton leaderOne, leaderTwo, leaderThree, leaderFour, goAhead;
    private ClientMessageHandler handler;

   public PreGameLeader(ClientMessageHandler handler){
       this.handler = handler;
       leaderOne = new JButton("Select");
       leaderTwo = new JButton("Select");
       leaderThree = new JButton("Select");
       leaderFour = new JButton("Select");
       goAhead = new JButton("Continue");
       leaderOne.setBounds(100, 550, 100, 50);
       leaderTwo.setBounds(250, 550, 100, 50);
       leaderThree.setBounds(400, 550, 100, 50);
       leaderFour.setBounds(550, 550, 100, 50);
       goAhead.setBounds(750, 750, 50, 50);
       leaderOne.addActionListener(this);
       leaderTwo.addActionListener(this);
       leaderThree.addActionListener(this);
       leaderFour.addActionListener(this);
       goAhead.addActionListener(this);
       this.add(leaderOne);
       this.add(leaderTwo);
       this.add(leaderThree);
       this.add(leaderFour);
       this.add(goAhead);
       this.setLayout(null);
       this.setSize(800, 800);
       this.setVisible(true);
       this.setBackground(Color.WHITE);

   }

   public void paint(Graphics g){
       g.drawString("Choose two of the four leader cards available", 200,200);
   }

   private void myDrawImagePNG(Graphics g){
    toChooseLeaderCards = Data.instanceCreator().getToChooseLeaderCards();
   }



    @Override
    public void actionPerformed(ActionEvent e) {
        Message confirm = null;
        chooseLeader(e);
        try {
            confirm = handler.readMessage();
        } catch (EndingGameException ex) {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Intro("error", 1));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
        if (confirm.getCode() == 310) {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else {
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new WaitingTurn(handler));
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        }
    }

public void chooseLeader(ActionEvent e){
       int numAction = 0;

       while(numAction!=2){
           if (e.getSource() == leaderOne){
               try {
                   handler.sendMessageToServer("1", 161);
               } catch(EndingGameException ex){
                   MainGUI.frame.remove(this);
                   MainGUI.frame.add(new Intro("error", 1));
                   MainGUI.frame.revalidate();
                   MainGUI.frame.repaint();
               }
           } else if(e.getSource() == leaderTwo){
               try {
                   handler.sendMessageToServer("2", 161);
               } catch(EndingGameException ex){
                   MainGUI.frame.remove(this);
                   MainGUI.frame.add(new Intro("error", 1));
                   MainGUI.frame.revalidate();
                   MainGUI.frame.repaint();
               }
           } else if(e.getSource() == leaderThree){
               try {
                   handler.sendMessageToServer("3", 161);
               } catch(EndingGameException ex){
                   MainGUI.frame.remove(this);
                   MainGUI.frame.add(new Intro("error", 1));
                   MainGUI.frame.revalidate();
                   MainGUI.frame.repaint();
               }
           } else if(e.getSource() == leaderFour){
               try {
                   handler.sendMessageToServer("4", 161);
               } catch(EndingGameException ex){
                   MainGUI.frame.remove(this);
                   MainGUI.frame.add(new Intro("error", 1));
                   MainGUI.frame.revalidate();
                   MainGUI.frame.repaint();
               }
           }
       numAction++;
       }

}


}
