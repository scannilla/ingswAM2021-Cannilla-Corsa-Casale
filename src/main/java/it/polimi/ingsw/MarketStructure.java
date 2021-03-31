package it.polimi.ingsw;

import jdk.vm.ci.code.site.Mark;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarketStructure {
    private final MarketMarble[][] marketStructure;
    private MarketMarble outMarble;
    private final MarketMarble[] allMarbles;
    List<MarketMarble> MarblesList;

    public MarketStructure() {
        marketStructure = new MarketMarble[4][3];
        allMarbles = new MarketMarble[13];
    }

    public void initializeMarket() {
        int k=0;
        //JSON parsing
        MarblesList = Arrays.asList(allMarbles);
        Collections.shuffle(MarblesList);
        MarblesList.toArray(allMarbles);
        for(int i=0; i<4; i++)
            for(int j=0; j<3; j++) {
                marketStructure[i][j] = allMarbles[k];
                k++;
            }

    }

    public MarketMarble[] selectLine(int line) {
        MarketMarble[] marblesArray = new MarketMarble[4];
        for(int i=0; i<4; i++)
            marblesArray[i] = marketStructure[i][line];
        marketStructure[0][line] = outMarble;
        outMarble = marketStructure[4][line];
        for(int i=1; i<4; i++)
            marketStructure[i][line] = marketStructure[i-1][line];
        return marblesArray;
    }

    public MarketMarble[] selecColumn(int column) {
        MarketMarble[] marblesArray = new MarketMarble[3];
        System.arraycopy(marketStructure[column], 0, marblesArray, 0, 3);
        marketStructure[column][0] = outMarble;
        outMarble = marketStructure[column][3];
        for (int i = 1; i < 3; i++)
            marketStructure[column][i] = marketStructure[column][i - 1];
        return marblesArray;
    }
}
