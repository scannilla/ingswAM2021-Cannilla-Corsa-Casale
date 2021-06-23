package it.polimi.ingsw.gui.multi;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Turn extends JPanel implements ActionListener {
    private final JButton buyMarble;
    private final JButton buyProd;
    private final JButton activeLeader;
    private final JButton activeProd;
    private final JButton viewMyPB;
    private JButton viewPB1;
    private JButton viewPB2;
    private JButton viewPB3;
    private final JButton viewMarketMarble;
    private final JButton viewProdMarket;
    private final JButton endTurn;
    private final JButton viewLeaderCard;
    private final ClientMessageHandler handler;
    private int numPlayer;
    public Turn(ClientMessageHandler handler){
        this.handler = handler;
        //TODO trovare come ottenere l'indice del player che si trova in Turn
        buyMarble = new JButton ("Buy marble");
        buyMarble.setBounds(10, 110, 180, 180);
        buyProd = new JButton("Buy production card");
        buyProd.setBounds(210, 110, 180, 180);
        activeLeader = new JButton("Active leader card");
        activeLeader.setBounds(410, 110, 180, 180);
        activeProd = new JButton("Active production card");
        activeProd.setBounds(610, 110, 180, 180);
        viewMyPB = new JButton("View my personal board");
        viewMyPB.setBounds(10, 510, 180, 180);
        viewMarketMarble = new JButton("View market marble");
        viewMarketMarble.setBounds(10, 310, 380, 180);
        viewProdMarket = new JButton("View production cards market");
        viewProdMarket.setBounds(410, 310, 380, 180);
        viewLeaderCard = new JButton("View Leader Cards");
        viewLeaderCard.setBounds(610, 310, 180, 180);
        endTurn = new JButton("End turn");
        endTurn.setBounds(660, 710, 130, 80);
        switch(numPlayer){
            case 1:  viewPB1 = new JButton("View Lorenzo's personal board");
                viewPB1.setBounds(210, 510, 180, 180);
                viewPB1.addActionListener(this);
                this.add(viewPB1);
                break;
            case 2:  viewPB1 = new JButton("View nickname personal board");
                viewPB1.setBounds(210, 510, 180, 180);
                viewPB1.addActionListener(this);
                this.add(viewPB1);
                break;
            case 3: viewPB2 = new JButton("View nickname personal board");
                viewPB1 = new JButton("View nickname personal board");
                viewPB1.setBounds(210, 510, 180, 180);
                viewPB2.setBounds(410, 510, 180, 180);
                this.add(viewPB2);
                this.add(viewPB1);
                break;
            case 4: viewPB3 = new JButton("View nickname personal board");
                viewPB1 = new JButton("View nickname personal board");
                viewPB2 = new JButton("View nickname personal board");
                viewPB1.setBounds(210, 510, 180, 180);
                viewPB2.setBounds(410, 510, 180, 180);
                viewPB3.setBounds(610, 510, 180, 180);
                this.add(viewPB1);
                this.add(viewPB2);
                this.add(viewPB3);
                break;
        }
        this.add(buyMarble);
        this.add(buyProd);
        this.add(activeLeader);
        this.add(activeProd);
        this.add(viewMyPB);
        this.add(viewMarketMarble);
        this.add(viewProdMarket);
        this.add(endTurn);
        this.add(viewLeaderCard);
        buyMarble.addActionListener(this);
        buyProd.addActionListener(this);
        activeLeader.addActionListener(this);
        activeProd.addActionListener(this);
        viewMyPB.addActionListener(this);
        viewMarketMarble.addActionListener(this);
        viewProdMarket.addActionListener(this);
        endTurn.addActionListener(this);
        viewLeaderCard.addActionListener(this);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setBackground(Color.white);
    }


    public void paint(Graphics g){
        g.drawString("It is your turn", 300, 50);
        g.drawString("Choose your action", 300, 100);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
       chooseAction(e);
    }

    public void chooseAction(ActionEvent e){
        if (e.getSource() == buyMarble){
            MainGUI.frame.add(new BuyMarble(handler));
            MainGUI.frame.remove(this);
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if(e.getSource() == buyProd){
            MainGUI.frame.add(new BuyProductionCard(handler));
            MainGUI.frame.remove(this);
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if(e.getSource() == activeLeader){
            MainGUI.frame.add(new ActiveLeader(handler));
            MainGUI.frame.remove(this);
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if(e.getSource() == activeProd) {
            MainGUI.frame.add(new ActiveProduction(handler));
            MainGUI.frame.remove(this);
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if(e.getSource() == viewLeaderCard){
            MainGUI.frame.add(new ShowLeaderCard(handler, true));
            MainGUI.frame.remove(this);
            MainGUI.frame.revalidate();
            MainGUI.frame.repaint();
        } else if(e.getSource() == viewMarketMarble){
            try{
                handler.sendMessageToServer("view market", 163);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowMarketMarble(handler, true));
            MainGUI.frame.revalidate();
        } else if(e.getSource() == viewProdMarket){
            try{
                handler.sendMessageToServer("view card market", 162);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowProductionMarket(handler, true));
            MainGUI.frame.revalidate();
        } else if(e.getSource() == viewMyPB){
            try{
                handler.sendMessageToServer("view -personalboard -1", 164);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowPersonalBoard(handler, true));
            MainGUI.frame.revalidate();
        } else if (e.getSource() == viewPB1){
            try{
                handler.sendMessageToServer("view -personalboard -2", 164);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowPersonalBoard(handler, true));
            MainGUI.frame.revalidate();
        } else if(e.getSource() == viewPB2){
            try{
                handler.sendMessageToServer("view -personalboard -3", 164);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowPersonalBoard(handler, true));
            MainGUI.frame.revalidate();
        } else if(e.getSource() == viewPB3){
            try{
                handler.sendMessageToServer("view -personalboard -4", 164);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new ShowPersonalBoard(handler, true));
            MainGUI.frame.revalidate();
        } else if(e.getSource() == endTurn){
            try{
                handler.sendMessageToServer("endturn", 165);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }
    }

}
