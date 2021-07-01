package it.polimi.ingsw;

import com.google.gson.*;
import it.polimi.ingsw.leader.LeaderCard;
import it.polimi.ingsw.leader.LeaderCardsDeck;
import it.polimi.ingsw.marbles.MarketStructure;
import it.polimi.ingsw.production.ProductionCardsDeck;
import it.polimi.ingsw.tokens.ActionTokenPile;
import it.polimi.ingsw.controller.application.Command;

import java.io.*;
import java.util.ArrayList;

public final class GSON {

    /**
     * Parsing of all Production Cards
     * @throws IOException e
     */

    public static ProductionCardsDeck productionCardParser(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        ProductionCardsDeck productionCardsDeck = gson.fromJson(streamReader, ProductionCardsDeck.class);
        streamReader.close();
        return productionCardsDeck;
    }

    /**
     * Parsing of all Leader Cards
     * @throws IOException e
     */
    public static LeaderCardsDeck leaderCardParser(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
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
     * @throws IOException e
     */

    public static ActionTokenPile actionTokensPileParser(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        ActionTokenPile actionTokenPile = gson.fromJson(streamReader, ActionTokenPile.class);
        streamReader.close();
        return actionTokenPile;
    }

    /**
     * Parsing of Market Structure
     * @throws IOException e
     */

    public static MarketStructure marketStructureParser(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        MarketStructure marketStructure = gson.fromJson(streamReader, MarketStructure.class);
        streamReader.close();
        return marketStructure;
    }

    /**
     * parsing for vatican report
     * @return vaticanReport VaticanReport
     * @throws IOException e
     */

    public static VaticanReport vaticanReportParser(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        VaticanReport vaticanReport = gson.fromJson(streamReader, VaticanReport.class);
        streamReader.close();
        return vaticanReport;
    }

    /**
     * parsing for command
     * @param jsonString String
     * @return Command
     */
    public static Command commandParser(String jsonString) {
        Gson g = new Gson();
        return g.fromJson(jsonString, Command.class);
    }

}
