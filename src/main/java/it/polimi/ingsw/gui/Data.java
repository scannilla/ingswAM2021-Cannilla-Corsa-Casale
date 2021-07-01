package it.polimi.ingsw.gui;

import it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.VaticanReport;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsMarket;
import it.polimi.ingsw.tokens.ActionTokenPile;
import it.polimi.ingsw.leader.LeaderCard;

import java.util.ArrayList;

public class Data {

    private static String nickname;
    private static MarketStructure marketStructure;
    private static ProductionCardsMarket productionCardsMarket;
    private static PersonalBoard personalBoard;
    private static LeaderCard[] leaderCards;
    private static LeaderCard[] activeLeaderCards;
    private static LeaderCard[] toChooseLeaderCards;
    private static ActionTokenPile tokenPile;
    private static VaticanReport vatReport;
    private static Data instance;
    private static ArrayList<Player> leaderBoard;

    private Data(){ }

    /**
     * constructor of instance if instance == null
     * else return instance
     * @return Data instance
     */
    public static Data instanceCreator() {
        if(instance==null) {
            instance = new Data();
        }
        return instance;
    }

    /**
     * setter Data.markcetStructure
     * @param marketStructure MarketStructure
     */
    public void setMarketStructure(MarketStructure marketStructure) {
        Data.marketStructure = marketStructure;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.activeLeaderCards
     * @param activeLeaderCards LeaderCard[]
     */
    public void setActiveLeaderCards(LeaderCard[] activeLeaderCards){
        Data.activeLeaderCards = activeLeaderCards;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.toChooseLeaderCard
     * @param toChooseLeaderCards LeaderCard[]
     */
    public void setToChooseLeaderCards(LeaderCard[] toChooseLeaderCards){
        Data.toChooseLeaderCards = toChooseLeaderCards;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.leaderCards
     * @param leaderCards LeaderCard
     */
    public void setLeaderCards(LeaderCard[] leaderCards){
        Data.leaderCards = leaderCards;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.productionCardMarket
     * @param productionCardsMarket ProductionCardsMarket
     */
    public void setProductionCardsMarket(ProductionCardsMarket productionCardsMarket) {
        Data.productionCardsMarket = productionCardsMarket;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.tokenPile (only singleplayer)
     * @param tokenPile ActionTokenPile
     */
    public void setTokenPile(ActionTokenPile tokenPile){
        Data.tokenPile = tokenPile;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.personalBoard
     * @param personalBoard PersonalBoard
     */
    public void setPersonalBoard(PersonalBoard personalBoard) {
        Data.personalBoard = personalBoard;
        MainGUI.frame.getContentPane().repaint();
    }

    /**
     * setter Data.nickname
     * @param nickname String
     */
    public void setNickname(String nickname){
        Data.nickname = nickname;
    }

    /**
     * setter Data.vatReport
     * @param vatReport VaticanReport
     */
    public void setVatReport(VaticanReport vatReport){
        Data.vatReport = vatReport;
    }

    public void setLeaderBoard(ArrayList<Player> leaderBoard){
        Data.leaderBoard = leaderBoard;
    }

    /**
     * getter Data.marketStructure
     * @return MarketStructure marketStructure
     */
    public MarketStructure getMarketStructure(){
        return marketStructure;
    }

    /**
     * getter Data.productionCardsMarket
     * @return ProductionCardsMarket productionCardsMarket
     */
    public ProductionCardsMarket getProductionCardsMarket(){
        return productionCardsMarket;
    }

    /**
     * getter Data.personalBoard
     * @return PersonalBoard personalBoard
     */
    public PersonalBoard getPersonalBoard(){
        return personalBoard;
    }

    /**
     * getter Data.activeLeaderCards
     * @return LeaderCard[] activeLeaderCards
     */
    public LeaderCard[] getActiveLeaderCards(){
        return activeLeaderCards;
    }

    /**
     * getter Data.leaderCards
     * @return LeaderCard[] leaderCards
     */
    public LeaderCard[] getLeaderCards(){
        return leaderCards;
    }

    /**
     * getter Data.toChooseLeaderCards
     * @return LEaderCard[] toChooseLeaderCards
     */
    public LeaderCard[] getToChooseLeaderCards(){
        return toChooseLeaderCards;
    }

    /**
     * getter Data.nickname
     * @return String nickname
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * getter Data.vatReport
     * @return VaticanReport vatReport
     */
    public VaticanReport getVatReport(){
        return vatReport;
    }

    public ArrayList<Player> getLeaderBoard(){
        return leaderBoard;
    }


}
