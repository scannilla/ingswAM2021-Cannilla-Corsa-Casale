package it.polimi.ingsw;

import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.leader.LeaderOfConversions;
import it.polimi.ingsw.leader.LeaderOfDepots;
import it.polimi.ingsw.marbles.MarketMarble;
import it.polimi.ingsw.production.ProductionCard;
import it.polimi.ingsw.resources.Resource;
import it.polimi.ingsw.resources.ResourceCounter;
import it.polimi.ingsw.resources.WarehouseDepot;

public class Player {
    /**
     * This attribute represents this player's personal board
     */
    private final PersonalBoard personalBoard;
    /**
     * This attribute represents if a player is active or not
     */
    private boolean active;
    /**
     * This attribute represents player's leader cards
     */
    private LeaderCard[] leaderCards = new LeaderCard[2];

    private final LeaderCard[] activeLeaderCards = new LeaderCard[2];
    /**
     * This attribute represents player's nickname
     */
    private final String nickname;
    /**
     * This attribute represents connected game
     */
    private Game connectedGame;
    /**
     * This attribute represents win points
     */
    private int wp=0;

    private boolean actionDone = false;

    private int productionsActivated = 0;

    private boolean isLast = false;
    /**
     * This method create a new player initializing all his attribute
     * @param nickname String
     */
    public Player(String nickname) { //player constructor
        this.personalBoard = new PersonalBoard();
        this.active = false;
        this.nickname = nickname;
    }
    /**
     * This method returns player's personal board with all its attributes
     * @return personalBoard
     */
    public PersonalBoard getPersonalBoard() { //getter of personal board
        return personalBoard;
    }
    /**
     * This method returns true if player is active, false instead
     * @return active
     */
    public boolean isActive() { //returns if the player is active or not
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * put activated LeaderCard in the array of activeLeaderCards
     * @param leaderCard LeaderCard
     * @throws IllegalArgumentException IllegalArgumentException
     */
    public void activateLeaderCard(LeaderCard leaderCard) throws IllegalArgumentException{
        if(leaderCards[0]!=leaderCard && leaderCards[1]!=leaderCard)
            throw new IllegalArgumentException("Card not found");
        if(leaderCard.getRequiredRes()[0]!=null) {
            int[] counter = ResourceCounter.resCount(leaderCard.getRequiredRes());
            int[] depotCounter = personalBoard.getWarehouseDepot().getDepotResourceAmount();
            int[] strongboxCounter = personalBoard.getStrongbox().getStrongboxResourcesAmount();
            int[] leaderDepotCounter1 = {0, 0, 0, 0};
            int[] leaderDepotCounter2 = {0, 0, 0, 0};
            if (activeLeaderCards[0].getAbility() == 1)
                leaderDepotCounter1 = ResourceCounter.resCount(((LeaderOfDepots) activeLeaderCards[0]).getExtraDepot());

            for (int i = 0; i < 4; i++) {
                if (counter[i] > (depotCounter[i] + strongboxCounter[i] + leaderDepotCounter1[i] + leaderDepotCounter2[i])) {
                    throw new IllegalArgumentException("Not enough resources");
                } else {
                    if (activeLeaderCards[0] != null)
                        activeLeaderCards[1] = leaderCard;
                    else activeLeaderCards[0] = leaderCard;
                }
            }
        }
        else {
            for(int i=0; i<leaderCard.getRequiredType().length; i++) {
                int found = leaderCard.getRequiredType().length;
                for(ProductionCard card : this.getPersonalBoard().getProdCardSlot().getActiveCardsAsArr()) {
                    if(leaderCard.getRequiredType()[i] == card.getType()) {
                        if(i-1<=leaderCard.getRequiredLevel().length && leaderCard.getRequiredLevel()[i]>=0) {
                            if (leaderCard.getRequiredLevel()[i] == card.getLevel())
                                found--;
                        }
                        else
                            found--;
                    }
                }
                if(found<=0) {
                    if (activeLeaderCards[0] != null)
                        activeLeaderCards[1] = leaderCard;
                    else activeLeaderCards[0] = leaderCard;
                }
            }
        }
        this.setWp(this.getWp()+leaderCard.getWp());
    }
    /**
     * this method buys a production card
     * @param rowIndex int
     * @param columnIndex int
     * @throws IllegalArgumentException e
     */
    public void buyProductionCard(int rowIndex, int columnIndex, int position){
        try {
            ProductionCard card = this.getConnectedGame().getCardsMarket().buyCard(rowIndex, columnIndex);
            personalBoard.getProdCardSlot().insertNewCard(card, position);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        EventManager.notifyListener(EventType.PERSONALBOARD, this.personalBoard);
    }
    /**
     * this method activated standard production power for the selected production card
     * @param position int
     * @throws IllegalArgumentException e
     */
    public void activateStandardProduction (int position) throws IllegalArgumentException{
        ProductionCard card = personalBoard.getProdCardSlot().getTopCards()[position];
        int[] required = ResourceCounter.resCount(card.getRequiredRes());
        int[] total = personalBoard.getWarehouseDepot().getDepotResourceAmount();
        for(int i=0; i<4; i++) {
            if(total[i]-required[i]<=0)
                throw new IllegalArgumentException();
        }
        if(card.getGivenFaithPoints()!=0) {
            increaseFaith(card.getGivenFaithPoints());
        }
    }
    /**
     * this method increases faith for the selected faith points
     * @param faithPoints int
     */
    public void increaseFaith(int faithPoints) {
        personalBoard.setPosition(personalBoard.getPosition()+faithPoints);
        int i=0;
        while (connectedGame.getVaticanReport().getActivatedEvent()[i] && i<connectedGame.getVaticanReport().getNumberOfReports())
            i++;
        if(personalBoard.getPosition()+faithPoints>=connectedGame.getVaticanReport().getActivationPosition()[i])
            connectedGame.activateEvent(i);
        EventManager.notifyListener(EventType.PERSONALBOARD, this.personalBoard);

    }

    /**
     * sets the connected game
     * @param connectedGame Game
     */
    public void setConnectedGame(Game connectedGame) {
        this.connectedGame = connectedGame;
    }

    /**
     * getter of the win points
     * @return wp int
     */
    public int getWp() {
        return wp;
    }

    /**
     * sets the win points
     * @param wp int
     */
    public void setWp(int wp) {
        this.wp = wp;
    }

    /**
     * this method buys resources from market
     * @param line int
     * @throws IllegalArgumentException e
     * @return MarketMarble[] marbles
     */
    public MarketMarble[] buyResourceFromMarket(int line, String sel) throws IllegalArgumentException{
        MarketMarble[]marbles;
        if(sel.equals("line"))
            marbles = connectedGame.getMarket().selectLine(line);
        else if (sel.equals("column"))
            marbles = connectedGame.getMarket().selectColumn(line);
        else
            throw new IllegalArgumentException("not a line or a column");
        for (MarketMarble marble : marbles){
            try {
                marble.returnAbility();
            } catch (Exception e) {
                if (e.getMessage().equals("red"))
                    increaseFaith(1);
            }
        }
        return marbles;
    }


    /**
     * getter for the vector of leader cards
     * @return leaderCards LeaderCard[]
     */
    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    /**
     * sets leader cards
     * @param leaderCards LeaderCard[]
     */
    public void setLeaderCards(LeaderCard[] leaderCards) {
        this.leaderCards = leaderCards;
    }

    /**
     * discards a leader card
     * @param card LeaderCard
     * @throws IllegalArgumentException e
     */
    public void discardLeaderCard(LeaderCard card) throws IllegalArgumentException{
        if ((card == activeLeaderCards[0] || card == activeLeaderCards[1]) || (card != leaderCards[0] && card != leaderCards[1]))
            throw  new IllegalArgumentException();
        else if (card == leaderCards[0]) {
            leaderCards[0] = null;
            increaseFaith(1);
        }
        else {
            leaderCards[1] = null;
            increaseFaith(1);
        }
    }

    /**
     * returns the vector of active leader cards
     * @return activeLeaderCards LeaderCard[]
     */
    public LeaderCard[] getActiveLeaderCards() {
        return activeLeaderCards;
    }

    /**
     * returns the connected game
     * @return connectedGame Game
     */
    public Game getConnectedGame() {
        return connectedGame;
    }



    public void discardedResourceUser(Player player){
        for (Player p : connectedGame.getPlayers()){
            if (p != player){
                p.increaseFaith(1);
            }
        }
    }

    public void lastPlayer() {
        this.isLast = true;
    }

    public boolean isLast() {
        return isLast;
    }

    public int getProductionsActivated() {
        return productionsActivated;
    }

    public void setProductionsActivated(int productionsActivated) {
        this.productionsActivated = productionsActivated;
    }

    public boolean isActionDone() {
        return actionDone;
    }

    public void setActionDone(boolean actionDone) {
        this.actionDone = actionDone;
    }
}
