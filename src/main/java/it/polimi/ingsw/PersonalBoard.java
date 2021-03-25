package it.polimi.ingsw;

public class PersonalBoard {

    private int position;
    private WarehouseDepot warehouseDepot;
    private ProdCardSlot prodCardSlot;
    private Strongbox strongbox;

// constructor of PersonalBoard
    public PersonalBoard() {
        position = 0;
        strongbox = new Strongbox();
        warehouseDepot = new WarehouseDepot();
        prodCardSlot = new ProdCardSlot();
    }
// getter position
    public int getPosition() {
        return position;
    }
// setter position
    public void setPosition(int position) {
        this.position = position;
    }
}
