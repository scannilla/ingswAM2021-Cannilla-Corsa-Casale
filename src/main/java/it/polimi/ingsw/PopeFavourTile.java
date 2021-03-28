package it.polimi.ingsw;

public class PopeFavourTile {
    /**
     * This attribute represents the number of win points associated to this tile
     */
    private int wp;

    /**
     * This method creates a new Pope Favour Tile with this WinPoints(wp)
     * @param wp int
     */
    public PopeFavourTile(int wp) {
        this.wp = wp;
    }
    /**
     * This method returns win points of this Pope Favour Tile
     * @return wp
     */
    public int getWp() {
        return wp;
    }
}
