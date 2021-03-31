package it.polimi.ingsw;

public class ProdCardSlot {
    private final ProductionCard[][] slots; //ProdCards matrix
    private final ProductionCard[] topCards; //active Production Cards

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
    public void insertNewCard(ProductionCard card, int place) throws IllegalArgumentException {
        int level = card.getLevel();
        String s = "Place not empty";
        if(place<0 || place>3)
            throw new IllegalArgumentException("Illegal place");
        int i=0;
        if (level==1) {
            if(slots[3][0]!=null)
                throw new IllegalArgumentException("Already full");
            while(slots[i][0]!=null && i<3)
                i++;
            slots[i][0]=card;
            topCards[i]=card;
        }
        if (level==2) {
            if(slots[place][1]==null && slots[place][0]!=null) {
                slots[place][1] = card;
                topCards[place] = card;
            }
            else throw new IllegalArgumentException(s);
        }
        if (level==3) {
            if(slots[place][2]==null && slots[place][1]!=null && slots[place][0]!=null) {
                slots[place][2] = card;
                topCards[place] = card;
            }
            else throw new IllegalArgumentException(s);
        }
    }

    public ProductionCard[] getTopCards() {
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
