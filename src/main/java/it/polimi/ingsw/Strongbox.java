package it.polimi.ingsw;

public class Strongbox {
    private final int[] values;
    private final int coins=0, stones=1, servants=2, shields=3;


    public Strongbox() {
        values = new int[4];
    }

    public int getCoins() {
        return values[coins];
    }

    public int getStones() {
        return values[stones];
    }

    public int getServants() {
        return values[servants];
    }

    public int getShields() {
        return values[shields];
    }

    public void setCoins(int coinsN) {
        values[coins] = coinsN;
    }

    public void setStones(int stonesN) {
        values[stones] = stonesN;
    }

    public void setServants(int servantsN) {
        values[servants] = servantsN;
    }

    public void setShields(int shieldsN) {
        values[shields] = shieldsN;
    }
}
