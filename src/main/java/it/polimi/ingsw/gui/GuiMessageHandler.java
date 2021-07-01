package it.polimi.ingsw.gui;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.VaticanReport;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.Message;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkclient.ClientMessageHandler;
import it.polimi.ingsw.gui.local.GameOverLose;
import it.polimi.ingsw.gui.local.GameOverWin;
import it.polimi.ingsw.gui.multi.*;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsMarket;

import java.util.ArrayList;


public class GuiMessageHandler {

    private static final Data instance = Data.instanceCreator();

    private static String nickname;

    public static void readMessage(ClientMessageHandler cmHandler) throws EndingGameException {
        Message received;
        try{
            received = cmHandler.readMessage();
        } catch (EndingGameException e){
            throw new EndingGameException();
        }
        readCode(received, cmHandler);
    }

    public static void readCode(Message received, ClientMessageHandler cmHandler) {
        int code = received.getCode();
        Object object = null;
        if (code == 310)
            nickname = received.getMessage();
        if (received.getNickname() == null || nickname.equals(received.getNickname())) {
            if (code >= 650)
                object = ((ObjectMessage) received).getObj();
            switch (code) {
                case 110:
                    MainGUI.changePanel(new AskNicknameMulti(cmHandler));
                    break;
                case 111:
                    MainGUI.changePanel((new Multi(cmHandler)));
                    break;
                case 112:
                    MainGUI.changePanel(new Error(received.getMessage(), cmHandler, 8));
                    break;
                case 124:
                    MainGUI.changePanel(new Turn(cmHandler));
                    break;
                case 133:
                    MainGUI.changePanel(new DiscardOrMoveOrTransform(cmHandler, 1));
                    break;
                case 180:
                    MainGUI.changePanel(new PreGameRes(received.getMessage(), cmHandler));
                    break;
                case 183:
                    MainGUI.changePanel(new PreGameLeader(cmHandler));
                    break;
                case 405:
                    MainGUI.changePanel(new Error(received.getMessage(), cmHandler, 6));
                    break;
                case 194:
                    MainGUI.changePanel(new SelectLeaderCard(cmHandler));
                    break;
                case 195:
                    MainGUI.changePanel(new DepotSelection(cmHandler, 1));
                    break;
                case 196:
                    MainGUI.changePanel(new ResourceSelection(cmHandler));
                    break;
                case 197:
                    MainGUI.changePanel(new LineSelectionDepot(cmHandler));
                    break;
                case 198:
                    MainGUI.changePanel(new DepotSelection(cmHandler, 2));
                    break;
                case 199:
                    MainGUI.changePanel(new DiscardOrMoveOrTransform(cmHandler, 1));
                    break;
                case 202:
                    MainGUI.changePanel(new ChoosePlayers(cmHandler));
                    break;
                case 203:
                    MainGUI.changePanel(new WaitingRoom(cmHandler));
                    break;
                case 204:
                    MainGUI.changePanel(new Setup(cmHandler));
                    break;
                case 350:
                    try {
                        cmHandler.sendMessageToServer("Pong", 351);
                    } catch (EndingGameException ex) {
                        MainGUI.changePanel(new Error("FATAL ERROR", cmHandler, 0));
                    }
                    break;
                case 310:
                    Data.instanceCreator().setNickname(received.getNickname());
                    MainGUI.changePanel(new JoinGame(cmHandler));
                    break;
                case 320:
                    MainGUI.changePanel(new Turn(cmHandler));
                    break;
                case 321:
                case 322:
                case 323:
                    MainGUI.changePanel(new WaitingTurn(cmHandler));
                    break;
                case 650:
                    instance.setMarketStructure((MarketStructure) object);
                    break;
                case 651:
                    instance.setProductionCardsMarket((ProductionCardsMarket) object);
                    break;
                case 652:
                    instance.setPersonalBoard((PersonalBoard) object);
                    break;
                case 653:
                    instance.setLeaderCards((LeaderCard[]) object);
                    break;
                case 654:
                    instance.setActiveLeaderCards((LeaderCard[]) object);
                    break;
                case 655:
                    instance.setToChooseLeaderCards((LeaderCard[]) object);
                    break;
                case 657:
                    instance.setLeaderBoard((ArrayList<Player>) object);
                    MainGUI.changePanel(new GameOver());
                    break;
                case 658:
                    instance.setLeaderBoard((ArrayList<Player>) object);
                    MainGUI.changePanel(new GameOverWin());
                    break;
                case 659:
                    instance.setLeaderBoard((ArrayList<Player>) object);
                    MainGUI.changePanel(new GameOverLose());
                    break;
                case 660:
                    instance.setVatReport((VaticanReport) object);
                    break;
            }
        }
    }
}
