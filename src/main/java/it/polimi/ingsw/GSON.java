package it.polimi.ingsw;

import com.google.gson.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class GSON {

    /**
     * Parsing of all Production Cards
     * @param file File
     * @throws IOException IOException
     */
    public static void productionCardParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        ProductionCardsDeck productionCardsDeck = gson.fromJson(streamReader, ProductionCardsDeck.class);
        streamReader.close();
    }

    /**
     * Parsing of all Leader Cards
     * @param file File
     * @throws IOException IOException
     */
    public static void leaderCardParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        LeaderCardsDeck leaderCardsDeck = gson.fromJson(streamReader, LeaderCardsDeck.class);
        streamReader.close();
    }

    /**
     * Parsing of all Action Tokens
     * @param file File
     * @throws IOException IOException
     */
    public static void actionTokensPileParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        //BROKEN, BUG ActionTokenPile actionTokenPile = gson.fromJson(streamReader, ActionTokenPile.class);
        streamReader.close();
    }

    /**
     * Parsing of Market Structure
     * @param file File
     * @throws IOException IOException
     */
    public static void marketStructureParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        MarketStructure marketStructure = gson.fromJson(streamReader, MarketStructure.class);
        streamReader.close();
    }

    public static void vaticanReportParser(File file) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        VaticanReport vaticanReport = gson.fromJson(streamReader, VaticanReport.class);
        streamReader.close();
    }
}
