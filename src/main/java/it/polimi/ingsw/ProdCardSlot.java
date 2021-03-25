package it.polimi.ingsw;

public class ProdCardSlot {
    private ProductionCard[][] slots; //ProdCards matrix
    private ProductionCard[] topcards; //active Production Cards

    public ProdCardSlot() {
        slots = new ProductionCard[3][3];
        topcards = new ProductionCard[3];
        for(int i=0; i<3; i++) {
            topcards[i]=null;
            for (int j = 0; j < 3; j++)
                slots[i][j] = null;
        }
    }

    //when a new prod card is bought it's inserted in the DevCardSlots
    public void insertNewCard(ProductionCard card, int place) throws IllegalArgumentException {
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
    }

    public ProductionCard[] getTopcards() {
        return topcards;
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
