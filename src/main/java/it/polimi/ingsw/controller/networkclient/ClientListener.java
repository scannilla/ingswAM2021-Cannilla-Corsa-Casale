package it.polimi.ingsw.controller.networkclient;

import com.sun.tools.javac.Main;
import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.cli.*;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.gui.local.BuyMarble;
import it.polimi.ingsw.gui.multi.Multi;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.tokens.ActionToken;


public class ClientListener implements Runnable{

    /**
     * represents the client message handler
     */
    private  final ClientMessageHandler cmHandler;

    private final boolean gui;
    
    private Data instance = Data.instanceCreator();

    private String nickname ="";

    /**
     * constructor for ClientListener
     */
    public ClientListener(ClientMessageHandler cmHandler, boolean gui) {
        this.cmHandler = cmHandler;
        this.gui = gui;
    }

    /**
     * Parallel thread to always keep listening
     */
    @Override
    public void run() {
        if(!gui) {
            while (true) {
                try {
                    Message read = cmHandler.readMessage();
                    if (read.getCode()==310)
                        nickname = read.getMessage();
                    else if(read.getNickname()== null || nickname.equals(read.getNickname())) {
                        if (read.getCode() >= 650)
                            readObject(read);
                        else
                            System.out.println(read.getMessage());
                    }
                } catch (EndingGameException e) {
                    break;
                }
            }
        }
        else {
            while (true) {
                try {
                    Message readGui = cmHandler.readMessage();
                    if (600<readGui.getCode() && readGui.getCode()<699){
                        readObjectGui(readGui, instance);
                    }
                } catch (EndingGameException e) {
                    e.printStackTrace();
                }
                
            }
        }
    }

    private void readObject(Message m) {
        ObjectMessage object = (ObjectMessage) m;
        printObj(object);
    }

    /**
     * Method used to call Cli view methods on objects
     * @param obj ObjectMessage
     */

    public static void printObj(ObjectMessage obj) {
        switch (obj.getCode()) {
            case 650:
                MarketStructure structure = (MarketStructure) obj.getObj();
                MarketDraw.draw(structure);
                break;
            case 651:
                ProductionCardsMarket prodMarket = (ProductionCardsMarket) obj.getObj();
                ProdCardsMarketDraw.drawProdCardsMarket(prodMarket);
                break;
            case 652:
                PersonalBoard personalBoard = (PersonalBoard) obj.getObj();
                PersonalBoardDraw.drawPB(personalBoard);
                break;
            case 653:
            case 654:
            case 655:
                ActiveLeaderCardsDraw.drawActiveLeaderCards((LeaderCard[]) obj.getObj());
                break;
            case 656:
                TokenDraw.drawToken((ActionToken) obj.getObj());
                break;
            case 657:
                //leaderboard draw
            case 658:
                //single player leaderboard draw (player win)
            case 659:
                //single player leaderboard draw (lorenzo win)
        }
    }

    private void readObjectGui(Message m, Data instance){
        ObjectMessage object = (ObjectMessage) m;
        switch(m.getCode()){
            case 650:
                instance.setMarketStructure((MarketStructure) object.getObj());
                break;
            case 651:
                instance.setProductionCardsMarket((ProductionCardsMarket) object.getObj());
                break;
            case 652:
                instance.setPersonalBoard((PersonalBoard) object.getObj());
                break;
            case 653:
                instance.setLeaderCards((LeaderCard[]) object.getObj());
                break;
            case 654:
                instance.setActiveLeaderCards((LeaderCard[]) object.getObj());
                break;
            case 655:
                instance.setToChooseLeaderCards((LeaderCard[]) object.getObj());
                break;

        }
        
    }
    
}
