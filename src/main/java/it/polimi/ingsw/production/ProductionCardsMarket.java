package it.polimi.ingsw.production;

import it.polimi.ingsw.controller.EndingGameException;
import it.polimi.ingsw.controller.virtualview.EventManager;
import it.polimi.ingsw.controller.virtualview.EventType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductionCardsMarket implements Serializable {

    /**
     * Tensor that represents Production Cards Market
     */
    private final ProductionCard[][][] productionCardsMarket = new ProductionCard[3][4][4];

    /**
     * Fill the market with all the shuffled 48 Production Cards
     * @param productionCardsDeck ProductionCardsDeck
     */
    public void setMarket(ProductionCardsDeck productionCardsDeck) {
        int k=0;
        int l=0;
        for(int i=0; i<48; i++) {
            int j=i%4;
            productionCardsMarket[l][k%4][j]=productionCardsDeck.getProductionCardsDeck()[i];
            if(j==3) {
                if(k%4==3)
                    l++;
                k++;
            }
        }
        List<ProductionCard> prodList;
        for(int i=0; i<3; i++)
            for (int j=0; j<4; j++) {
                prodList= Arrays.asList(productionCardsMarket[i][j]);
                Collections.shuffle(prodList);
                prodList.toArray(productionCardsMarket[i][j]);
            }
        EventManager.notifyListener(EventType.CARDMARKET, this);
    }

    /**
     * This method remove the Production Card that the Player had just bought, if the Player can't buy the Production
     * Card throw an Exception
     * @param row int
     * @param column int
     * @throws IllegalArgumentException e
     */
    public ProductionCard buyCard (int row, int column) throws IllegalArgumentException {
        ProductionCard card;
        for (int i=0; i<4; i++) {
            if (productionCardsMarket[row][column][i] != null) {
                card = productionCardsMarket[row][column][i];
                productionCardsMarket[row][column][i] = null;
                return card;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * return a matrix of all the Tops Production Cards in the Market
     * @return topCards
     */
    public ProductionCard[][] getTopCards() {
        int k=0;
        ProductionCard[][] topCards = new ProductionCard[3][4];
        for(int i=0; i<3; i++)
            for(int j=0; j<4; j++) {
                while (productionCardsMarket[i][j][k]==null && k<3)
                    k++;
                topCards[i][j] = productionCardsMarket[i][j][k];
            }
        return topCards;
    }

    /**
     * getter for the chosen production card
     * @param x int
     * @param y int
     * @return ProductionCardsMarket ProductionCard
     * @throws IllegalArgumentException
     */
    public ProductionCard getCard(int x, int y) throws IllegalArgumentException{
        if(productionCardsMarket[x][y][3]==null)
            throw new IllegalArgumentException();
        int k=0;
        while(productionCardsMarket[x][y][k]==null && k<3)
        k++;
        return productionCardsMarket[x][y][k];
    }

    public void deleteCards(int type) throws EndingGameException {
        for (int i = 0; i < 2; i++) {
            try {
                getCard(0, type);
                buyCard(0, type);
            } catch (IllegalArgumentException e) {
                try {
                    getCard(1, type);
                    buyCard(1, type);
                } catch (IllegalArgumentException e1) {
                    try {
                        getCard(2, type);
                        buyCard(2, type);
                    } catch (IllegalArgumentException e2) {
                        throw new EndingGameException();
                    }
                }
            }
        }
        try {
            getCard(2,type);
        } catch (IllegalArgumentException e) {
            throw new EndingGameException();
        }
    }
}
