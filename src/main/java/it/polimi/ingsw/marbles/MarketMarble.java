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

    public MarketMarble(String color) throws IllegalArgumentException{
        switch (color) {
            case "white":
                this.color=0;
                break;
            case "grey":
                this.color=1;
                break;
            case "blue":
                this.color=2;
                break;
            case "yellow":
                this.color=3;
                break;
            case "purple":
                this.color=4;
                break;
            case "red":
                this.color=5;
                break;
            default:
                throw new IllegalArgumentException();
        }
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
