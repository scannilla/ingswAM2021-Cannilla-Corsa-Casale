package it.polimi.ingsw.controller.networkclient;

import it.polimi.ingsw.LeaderBoard;
import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.VaticanReport;
import it.polimi.ingsw.cli.*;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.gui.Data;
import it.polimi.ingsw.gui.Error;
import it.polimi.ingsw.gui.GuiMessageHandler;
import it.polimi.ingsw.gui.MainGUI;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.tokens.ActionToken;

import java.util.ArrayList;
import java.util.Arrays;


public class ClientListener implements Runnable{

    /**
     * represents the client message handler
     */
    private  final ClientMessageHandler cmHandler;

    private final boolean gui;


    private String nickname ="";

    private static VaticanReport report;

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
                    if(read.getCode()==350) {
                        cmHandler.sendMessageToServer("pong", 351);
                    }
                    else if (read.getCode()==310)
                        nickname = read.getMessage();
                    else if(read.getNickname()== null || nickname.equals(read.getNickname())) {
                        if (read.getCode() >= 650)
                            readObject(read);
                        else
                            System.out.println(read.getMessage());
                    }
                    if(read.getCode()==0) {
                        System.exit(1);
                    }
                } catch (EndingGameException e) {
                    break;
                }
            }
        }
        else {
            while (true) {
                try {
                    GuiMessageHandler.readMessage(cmHandler);
                } catch (EndingGameException e) {
                    MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
                    break;
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
                PersonalBoardDraw.drawPB(personalBoard, report);
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
                LeaderBoard lb = (LeaderBoard) obj.getObj();
                ArrayList<String> nicknames = new ArrayList<>(lb.getMap().keySet());
                ArrayList<Integer> wp = new ArrayList<>(lb.getMap().values());
                LeaderBoardDraw.drawLeaderboard(nicknames, wp);
                break;
            case 658:
                LeaderBoard lb1;
                lb1 = (LeaderBoard) obj.getObj();
                ArrayList<Integer> wp1 = new ArrayList<>(lb1.getMap().values());
                LeaderBoardDraw.drawWin(wp1);
                break;
            case 659:
                LeaderBoard lb2;
                lb2 = (LeaderBoard) obj.getObj();
                ArrayList<Integer> wp2 = new ArrayList<>(lb2.getMap().values());
                LeaderBoardDraw.drawLose(wp2);
                break;
            case 660:
                report = (VaticanReport)obj.getObj();
        }
    }

}
