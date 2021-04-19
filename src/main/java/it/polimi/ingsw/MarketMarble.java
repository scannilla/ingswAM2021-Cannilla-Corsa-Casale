package it.polimi.ingsw;

public class MarketMarble {
    private final int color;

    /**
     * legend: 0=white; 1=grey=stones, 2=blue=shields, 3=yellow=coins, 4=purple=servants, 5=red=faith;
     * @param color int
     */
    public MarketMarble(int color) {
        this.color = color;
    }

    /**
     * this method returns the color of this
     * @return color int
     */
    public int getColor() {
        return color;
    }

    public void returnAbility() {
        if(color==0)
            ;
    }
}
