package it.polimi.ingsw;

public class Ability {
    /**
     * legenda:
     * 0 = discount; 1 = extra depot; 2 = conversion; 3 = production
     */
    private int abilityType;
    private int resource;


    public Ability(int abilityType, int resource) {
        this.abilityType = abilityType;
        this.resource = resource;
    }

    /**
     * getter ability type
     * @return abilityType
     */
    public int getAbilityType() {
        return abilityType;
    }


    public int getResource() {
        return resource;
    }


}
