package it.polimi.ingsw;

public class Strongbox {
    private int[] values;


    public Strongbox() {
        values = new int[4];
    }

    public int getCoins() {
        return values[0];
    }

    public int getStones() {
        return values[1];
    }

    public int getServants() {
        return values[2];
    }

    public int getShields() {
        return values[3];
    }

    public void setCoins(int coins) {
        values[0] = coins;
    }

    public void setStones(int stones) {
        values[1] = stones;
    }

    public void setServants(int servants) {
        values[2] = servants;
    }

    public void setShields(int shields) {
        values[3] = shields;
    }
}
