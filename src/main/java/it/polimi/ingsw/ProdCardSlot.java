package it.polimi.ingsw;

public class ProdCardSlot {
    /**
     * matrix representing slots where the player can insert Production Cards
     */
    private final ProductionCard[][] slots;
    /**
     * vector representing only cards that the player can activate (top cards of every column of the matrix)
     */
    private final ProductionCard[] topCards; //active Production Cards

    /**
     * constructor of a new matrix of slots and a new vector of top cards
     * initialize all to null
     */
    public ProdCardSlot() {
        slots = new ProductionCard[3][3];
        topCards = new ProductionCard[3];
    }


    /**
     * when a new Production Card is bought it is inerted in a slot
     * @param card int
     * @param place int
     * @throws IllegalArgumentException IllegalArgumentException
     */
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

    /**
     * getter of all top cards
     * @return topCards
     */
    public ProductionCard[] getTopCards() {
        return topCards;
    }

    /**
     * getter the card in column of this level (0 = level 1; 1 = level 2; 2 = level 3)
     * @param column int
     * @param level int
     * @return slots[column][level]
     * @throws NullPointerException NullPointerException
     */
    //TODO check try/catch
    public ProductionCard getCard(int column, int level) throws NullPointerException {
        try {
            return slots[column][level];
        } catch (Exception NullPointerException) {
            return null;
        }
    }

    public ProductionCard[] getActiveCardsAsArr() {
        ProductionCard[] allCardsArray = new ProductionCard[12];
        int k=0;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++) {
                if (slots[i][j] != null) {
                    allCardsArray[k] = slots[i][j];
                    k++;
                }
            }
        return allCardsArray;
    }
}