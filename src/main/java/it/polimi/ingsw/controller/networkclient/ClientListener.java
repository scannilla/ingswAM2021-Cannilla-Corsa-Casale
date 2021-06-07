package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.cli.ActiveLeaderCardsDraw;
import it.polimi.ingsw.cli.MarketDraw;
import it.polimi.ingsw.cli.PersonalBoardDraw;
import it.polimi.ingsw.cli.ProdCardsMarketDraw;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsMarket;


public class ClientListener implements Runnable{

    /**
     * represents the client message handler
     */
    private  final ClientMessageHandler cmHandler;

    private final boolean gui;

    /**
     * constructor for ClientListener
     */
    public ClientListener(ClientMessageHandler cmHandler, boolean gui) {
        this.cmHandler = cmHandler;
        this.gui = gui;
    }

    /**
     * this method makes the client listen to the server
     */
    @Override
    public void run() {
        if(!gui) {
            while (true) {
                try {
                    Message read = cmHandler.readMessage();
                    if(read.getCode() >= 20)
                        readObject(read);
                    else
                        System.out.println(read.getMessage());
                } catch (EndingGameException e) {
                    break;
                }
            }
        }
        else {
            //call GUI methods
        }
    }

    private void readObject(Message m) {
        ObjectMessage object = (ObjectMessage) m;
        printObj(object);
    }

    public static void printObj(ObjectMessage obj) {
        switch (obj.getCode()) {
            case 20:
                MarketStructure structure = (MarketStructure) obj.getObj();
                MarketDraw.draw(structure);
                break;
            case 21:
                ProductionCardsMarket prodMarket = (ProductionCardsMarket) obj.getObj();
                ProdCardsMarketDraw.drawProdCardsMarket(prodMarket);
                break;
            case 22:
                PersonalBoard personalBoard = (PersonalBoard) obj.getObj();
                PersonalBoardDraw.drawPB(personalBoard);
                break;
            case 23:
                ActiveLeaderCardsDraw.drawActiveLeaderCards((Player)obj.getObj());
                break;
        }
    }
}
