package it.polimi.ingsw.marbles;

import it.polimi.ingsw.resources.Resource;

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

    /**
     * this method throws an exception if the color has an invalid value
     * @throws Exception
     */
    public Resource returnAbility() throws Exception{
        if (color==0)
            throw new Exception("white");
        if (color==5)
            throw new Exception("red");
        else return (new Resource(color));
    }
}
