package it.polimi.ingsw.gui;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.gui.multi.BuyMarble;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProdCardSlot;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.tokens.ActionTokenPile;
import it.polimi.ingsw.leader.LeaderCard;

public class Data {

    private static String nickname;
    private static MarketStructure marketStructure;
    private static ProductionCardsMarket productionCardsMarket;
    private static PersonalBoard personalBoard;
    private static LeaderCard[] leaderCards;
    private static LeaderCard[] activeLeaderCards;
    private static LeaderCard[] toChooseLeaderCards;
    private static ActionTokenPile tokenPile;
    private static Data instance;

    private Data(){ }

    public static Data instanceCreator() {
        if(instance==null) {
            instance = new Data();
        }
        return instance;
    }

    public void setMarketStructure(MarketStructure marketStructure) {
        Data.marketStructure = marketStructure;
        MainGUI.frame.getContentPane().repaint();
    }

    public void setLeaderCards(LeaderCard[] leaderCards){
        this.leaderCards = leaderCards;
    }

    public void setProductionCardsMarket(ProductionCardsMarket productionCardsMarket) {
        Data.productionCardsMarket = productionCardsMarket;
        MainGUI.frame.getContentPane().repaint();
    }

    public void setTokenPile(ActionTokenPile tokenPile){
        Data.tokenPile = tokenPile;
        MainGUI.frame.getContentPane().repaint();
    }

    public void setPersonalBoard(PersonalBoard personalBoard) {
        Data.personalBoard = personalBoard;
        MainGUI.frame.getContentPane().repaint();
    }

    public void setNickname(String nickname){
        Data.nickname = nickname;
    }


    public MarketStructure getMarketStructure(){
        return marketStructure;
    }

    public ProductionCardsMarket getProductionCardsMarket(){
        return productionCardsMarket;
    }

    public PersonalBoard getPersonalBoard(){
        return personalBoard;
    }

    public LeaderCard[] getLeaderCards(){
        return this.leaderCards;
    }

    public String getNickname(){
        return nickname;
    }


}
