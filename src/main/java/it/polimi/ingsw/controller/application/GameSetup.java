package it.polimi.ingsw.controller.application;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.ObjectMessage;
import it.polimi.ingsw.controller.networkserver.MessageHandler;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.resources.Resource;

import java.util.ArrayList;


public class GameSetup {

    private final Game game;

    private final Player player;

    private final MessageHandler mHandler;

    public GameSetup(Game game, Player player, MessageHandler mHandler) {
        this.game = game;
        this.player = player;
        this.mHandler = mHandler;
    }

    public void gameSetUp(int index) throws EndingGameException{
        try {
            switch (index) {
                case 0:
                    mHandler.sendMessageToClient("You're the first player so you're not gonna receive any resource or faith point");
                    break;
                case 1:
                    mHandler.sendMessageToClient("You're the second player so you can choose a resource to add to your warehouse depot");
                    insertResource();
                    break;
                case 2:
                    mHandler.sendMessageToClient("You're the third player so you can choose a resource to add to your warehouse depot, increased faith points by one");
                    player.increaseFaith(1);
                    insertResource();
                    break;
                case 3:
                    mHandler.sendMessageToClient("You're the fourth player so you can choose two resources to add to your warehouse depot, increased faith points by one");
                    player.increaseFaith(1);
                    for (int i = 0; i < 2; i++) {
                        insertResource();
                    }
                    break;

            }
            selectLeaderCards();
        } catch (EndingGameException e) {
            throw new EndingGameException();
        }

    }

    private void insertResource() throws EndingGameException {
        mHandler.sendMessageToClient("select now the resource: coin, stone, servant or shield");
        String chosenResource = CheckCommand.commandChecker(new String[] {"coin", "stone", "servant", "shield"}, mHandler.readClientMessage(), mHandler);
        int chosenLine;
        do {
            mHandler.sendMessageToClient("Select where to insert the chosen resource");
            chosenLine = CheckCommand.checkNumber(mHandler.readClientMessage(), mHandler);
        } while (chosenLine<1 || chosenLine>3);
        player.getPersonalBoard().getWarehouseDepot().insertNewResource(new Resource(chosenResource),chosenLine);
    }

    private void selectLeaderCards() throws EndingGameException {
        LeaderCardsDeck deck = game.getLeaderCardsDeck();
        ArrayList<LeaderCard> cards = deck.pick4cards();
        ArrayList<LeaderCard> chosenCards = new ArrayList<>();
        mHandler.sendMessageToClient("Select 2 of these 4 cards");
        mHandler.sendObjectToClient(new ObjectMessage(cards, 23, player.getNickname()));
        mHandler.sendMessageToClient("Send the number of the first card");
        String chosen = mHandler.readClientMessage();
        int card = CheckCommand.checkNumber(chosen, mHandler);
        chosenCards.add(cards.get(card-1));
        int card2 = 0;
        do {
            mHandler.sendMessageToClient("Send the number of the second card");
            chosen = mHandler.readClientMessage();
            card2 = CheckCommand.checkNumber(chosen, mHandler);
        } while (card == card2);
        chosenCards.add(cards.get(card2-1));
        LeaderCard[] selected = chosenCards.toArray(new LeaderCard[0]);
        player.setLeaderCards(selected);

    }
}
