package it.polimi.ingsw;

public class PersonalBoard {

    private int Position;
    private int[][] WarehouseDepot;
    private ProductionCard[][] DevCardSlot;
    private int[] Strongbox;

// constructor of PersonalBoard
    public PersonalBoard(int position, int[][] warehouseDepot, ProductionCard[][] devCardSlot, int[] strongbox) {
        Position = position;
        WarehouseDepot = warehouseDepot;
        DevCardSlot = devCardSlot;
        Strongbox = strongbox;
    }
// getter position
    public int getPosition() {
        return Position;
    }
// getter strongbox
    public int[] getStrongbox() {
        return Strongbox;
    }

// setter position
    public void setPosition(int position) {
        Position = position;
    }
// setter strongbox
    public void setStrongbox(int[] strongbox) {
        Strongbox = strongbox;
    }
}
