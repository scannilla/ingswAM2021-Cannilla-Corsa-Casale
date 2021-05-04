package it.polimi.ingsw.marbles;

import it.polimi.ingsw.marbles.MarketMarble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarketStructure {
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
        for(int i=0; i<4; i++)
            for(int j=0; j<3; j++) {
                marketStructure[i][j] = allMarbles[k];
                k++;
            }

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
        outMarble = marketStructure[3][line];
        for(int i=1; i<4; i++)
            marketStructure[i][line] = marketStructure[i-1][line];
        marketStructure[0][line] = outMarble;
        return marblesArray;
    }

    /**
     * select the column of market's matrix from which the player takes Market Marbles
     * @param column int
     * @return marblesArray
     */
    public MarketMarble[] selectColumn(int column) {
        MarketMarble[] marblesArray = new MarketMarble[3];
        System.arraycopy(marketStructure[column], 0, marblesArray, 0, 3);
        outMarble = marketStructure[column][2];
        for (int i = 1; i < 3; i++)
            marketStructure[column][i] = marketStructure[column][i - 1];
        marketStructure[column][0] = outMarble;
        return marblesArray;
    }

    /**
     * returns an array with all marbles
     * @return allMarbles MarketMarble[]
     */
    public MarketMarble[] getAllMarbles() {
        return allMarbles;
    }
}
