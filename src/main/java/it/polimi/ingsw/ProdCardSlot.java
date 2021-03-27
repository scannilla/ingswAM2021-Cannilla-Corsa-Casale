package it.polimi.ingsw;

public class ProdCardSlot {
    private ProductionCard[][] slots; //ProdCards matrix
    private ProductionCard[] topCards; //active Production Cards

    public ProdCardSlot() {
        slots = new ProductionCard[3][3];
        topCards = new ProductionCard[3];
        for(int i=0; i<3; i++) {
            topCards[i]=null;
            for (int j = 0; j < 3; j++)
                slots[i][j] = null;
        }
    }

    //when a new prod card is bought it's inserted in the DevCardSlots
    /*public void insertNewCard(ProductionCard card, int place) throws IllegalArgumentException {
        int level = card.getLevel();
        if (level==1) {
            for(int i=0; i<3; i++) {
                if(slots[2][0]!=null)
                    throw new IllegalArgumentException();
                if(slots[i][0]==null) {
                    slots[i][0] = card;
                    topcards[i] = card;
                    break;
                }
            }
        }
        if (level==2) {
            if(slots[place][1]==null && slots[place][0]!=null) {
                slots[place][1] = card;
                topcards[place] = card;
            }
            else throw new IllegalArgumentException();
        }
        if (level==3) {
            if(slots[place][2]==null && slots[place][1]!=null && slots[place][0]!=null) {
                slots[place][2] = card;
                topcards[place] = card;
            }
            else throw new IllegalArgumentException();
        }
    } */

    public void insertNewCard(ProductionCard card, int column) {
        int i=0;
        while(slots[column][i]!=null && i<3)
            i++;
        slots[column][i]=card;
    }

    public ProductionCard[] getTopCards() {
        int j=0;
        for(int i=0; i<3; i++) {
            while (slots[i][j] != null && j < 3)
                j++;
            if(j==0)
                topCards[i]= null;
            else topCards[i] = slots[i][j - 1];
        }
        return topCards;
    }

    //TODO check try/catch
    public ProductionCard getCard(int column, int level) throws NullPointerException {
        try {
            return slots[column][level];
        } catch (Exception NullPointerException) {
            return null;
        }
    }
}
