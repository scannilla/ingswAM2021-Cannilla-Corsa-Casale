package it.polimi.ingsw.marbles;

import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarketStructure implements Serializable {
    /**
     * matrix of Market Marble [4][3]
     */
    private final MarketMarble[][] marketStructure = new MarketMarble[4][3];
    /**
     * marble out of the structure
     */
    private MarketMarble outMarble;
    /**
     * array of all Market Marbles
     */
    private final MarketMarble[] allMarbles = new MarketMarble[13];
    /**
     * list of all Market Marbles
     */
    List<MarketMarble> MarblesList;

    /**
     * initialize the market randomising the distribution of Market Marbles
     */
    public void initializeMarket() {
        int k=0;
        MarblesList = Arrays.asList(allMarbles);
        Collections.shuffle(MarblesList);
        MarblesList.toArray(allMarbles);
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 3; j++) {
                marketStructure[i][j] = allMarbles[k];
                k++;
            }
        }
        outMarble=allMarbles[12];
        EventManager.notifyListener(EventType.MARKET, this);
    }

    /**
     * select the line of market's matrix form which the player takes Market Marbles
     * @param line int
     * @return marblesArray
     */
    public MarketMarble[] selectLine(int line) {
        MarketMarble[] marblesArray = new MarketMarble[4];
        for(int i=0; i<4; i++)
            marblesArray[i] = marketStructure[i][line];
        MarketMarble temp;
        temp =  marketStructure[0][line];
        for(int i=1; i<4; i++)
            marketStructure[i-1][line] = marketStructure[i][line] ;
        marketStructure[3][line] = outMarble;
        outMarble = temp;
        return marblesArray;
    }

    /**
     * select the column of market's matrix from which the player takes Market Marbles
     * @param column int
     * @return marblesArray
     */
    public MarketMarble[] selectColumn(int column) {
        MarketMarble temp;
        MarketMarble[] marblesArray = new MarketMarble[3];
        System.arraycopy(marketStructure[column], 0, marblesArray, 0, 3);
        temp = marketStructure[column][0];
        for (int i = 1; i < 3; i++)
            marketStructure[column][i-1] = marketStructure[column][i];
        marketStructure[column][2] = outMarble;
        outMarble = temp;
        return marblesArray;
    }

    /**
     * returns an array with all marbles
     * @return allMarbles MarketMarble[]
     */
    public MarketMarble[] getAllMarbles() {
        return allMarbles;
    }

    /**
     * Getter of Market Structure
     * @return marketStructure
     */
    public MarketMarble[][] getMarketStructure() {
        return marketStructure;
    }

    /**
     * Getter of Out Marble of Market Structure
     * @return outMarble
     */
    public MarketMarble getOutMarble() {
        return outMarble;
    }
}
