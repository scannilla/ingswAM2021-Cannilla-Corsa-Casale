package it.polimi.ingsw.gui.local;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.singleplayer.SPClientMessageHandler;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Intro;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.multi.Turn;
import it.polimi.ingsw.production.ProductionCardsMarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyProductionCard extends JPanel implements ActionListener {

    private ProductionCardsMarket productionCardsMarket;
    private final JButton buy1, buy2, buy3, buy4, buy5, buy6, buy7, buy8, buy9, back;
    private SPClientMessageHandler handler;
    public BuyProductionCard(SPClientMessageHandler handler){
        this.handler = handler;
        buy1 = new JButton("Buy");
        buy2 = new JButton("Buy");
        buy3 = new JButton("Buy");
        buy4 = new JButton("Buy");
        buy5 = new JButton("Buy");
        buy6 = new JButton("Buy");
        buy7 = new JButton("Buy");
        buy8 = new JButton("Buy");
        buy9 = new JButton("Buy");
        back = new JButton("Go Back");
        buy1.setBounds(50, 250, 100, 50);
        buy2.setBounds(200, 250, 100, 50);
        buy3.setBounds(350, 250, 100, 50);
        buy4.setBounds(50, 500, 100, 50);
        buy5.setBounds(200, 500, 100, 50);
        buy6.setBounds(350, 500, 100, 50);
        buy7.setBounds(50, 750, 100, 50);
        buy8.setBounds(200, 750, 100, 50);
        buy9.setBounds(350, 750, 100, 50);
        back.setBounds(750, 50, 100, 50);
        buy1.addActionListener(this);
        buy2.addActionListener(this);
        buy3.addActionListener(this);
        buy4.addActionListener(this);
        buy5.addActionListener(this);
        buy6.addActionListener(this);
        buy7.addActionListener(this);
        buy8.addActionListener(this);
        buy9.addActionListener(this);
        back.addActionListener(this);
        this.add(buy1);
        this.add(buy2);
        this.add(buy3);
        this.add(buy4);
        this.add(buy5);
        this.add(buy6);
        this.add(buy7);
        this.add(buy8);
        this.add(buy9);
        this.add(back);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.white);
    }

    public void paint(Graphics g){
        productionCardsMarket = Data.instanceCreator().getProductionCardsMarket();
        for(int x = 50; x < 400; x = x + 150){
            for (int y = 50; y < 750; y = y + 250){
                g.drawRect(x, y, 100, 200);
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buy1){
            try{
                handler.sendMessageToServer("buy production card -1 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if(e.getSource() == buy2){
            try{
                handler.sendMessageToServer("buy production card -1 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy3){
            try{
                handler.sendMessageToServer("buy production card -1 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy4){
            try{
                handler.sendMessageToServer("buy production card -2 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy5){
            try{
                handler.sendMessageToServer("buy production card -2 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy6){
            try{
                handler.sendMessageToServer("buy production card -2 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy7){
            try{
                handler.sendMessageToServer("buy production card -3 -1", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy8){
            try{
                handler.sendMessageToServer("buy production card -3 -2", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        }  else if(e.getSource() == buy9){
            try{
                handler.sendMessageToServer("buy production card -3 -3", 167);
            } catch (EndingGameException ex){
                MainGUI.frame.remove(this);
                MainGUI.frame.add(new Intro("error", 1));
                MainGUI.frame.revalidate();
                MainGUI.frame.repaint();
            }
        } else if (e.getSource() == back){
            MainGUI.frame.remove(this);
            MainGUI.frame.add(new Turn(handler));
            MainGUI.frame.revalidate();
        }
    }
}
