package it.polimi.ingsw;

public class PersonalBoard {
    /**
     * This attribute represents the position of personal faith marker on faith track
     */
    private int position;
    /**
     * This attribute represents Warehouse Depot in the personal board
     */
    private WarehouseDepot warehouseDepot;
    /**
     * This attribute represents Slots where Production Cards will be insert in
     */
    private ProdCardSlot prodCardSlot;
    /**
     * This attribute represents the strongbox in the personal board
     */
    private Strongbox strongbox;

    /**
     * This method create a new personal board with all attribute initialized to 0
     */
    public PersonalBoard() {
        position = 0;
        strongbox = new Strongbox();
        warehouseDepot = new WarehouseDepot();
        prodCardSlot = new ProdCardSlot();
    }
    /**
     * This method return faith marker's position from this personal board
     * @return position
     */
    public int getPosition() {
        return position;
    }
    /**
     * This method set faith marker's position on this personal board
     * @param position int
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Return Warehouse Depot of this Personal Board
     * @return warehouseDepot
     */
    public WarehouseDepot getWarehouseDepot() {
        return warehouseDepot;
    }

    /**
     * Return Strongbox of this Personal Board
     * @return strongbox
     */
    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ProdCardSlot getProdCardSlot() {
        return prodCardSlot;
    }
}