package it.polimi.ingsw;

import com.google.gson.*;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.tokens.ActionTokenPile;

import java.io.*;
import java.util.ArrayList;

public final class GSON {

    /**
     * Parsing of all Production Cards
     * @param file File
     * @throws IOException IOException
     */
    public static ProductionCardsDeck productionCardParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        ProductionCardsDeck productionCardsDeck = gson.fromJson(streamReader, ProductionCardsDeck.class);
        streamReader.close();
        return productionCardsDeck;
    }

    /**
     * Parsing of all Leader Cards
     * @param  file File
     * @throws IOException IOException
     */
    public static LeaderCardsDeck leaderCardParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        LeaderCardsDeck leaderCardsDeck = gson.fromJson(streamReader, LeaderCardsDeck.class);
        ArrayList<LeaderCard> deck = new ArrayList<>(leaderCardsDeck.getLeaderOfConversionsDeck());
        deck.addAll(leaderCardsDeck.getLeaderOfDepotsDeck());
        deck.addAll(leaderCardsDeck.getLeaderOfDiscountsDeck());
        deck.addAll(leaderCardsDeck.getLeaderOfProductionsDeck());
        leaderCardsDeck.setLeaderCardsDeck(deck);

        return leaderCardsDeck;
    }

    /**
     * Parsing of all Action Tokens
     * @param file File
     * @throws IOException IOException
     */
    public static ActionTokenPile actionTokensPileParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        ActionTokenPile actionTokenPile = gson.fromJson(streamReader, ActionTokenPile.class);
        streamReader.close();
        return actionTokenPile;
    }

    /**
     * Parsing of Market Structure
     * @param file File
     * @throws IOException IOException
     */
    public static MarketStructure marketStructureParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        MarketStructure marketStructure = gson.fromJson(streamReader, MarketStructure.class);
        streamReader.close();
        return marketStructure;
    }

    public static VaticanReport vaticanReportParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        VaticanReport vaticanReport = gson.fromJson(streamReader, VaticanReport.class);
        streamReader.close();
        return vaticanReport;
    }

}
