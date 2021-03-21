package it.polimi.ingsw;

public class Ability {

    private int abilityType;
    private int resource;
//constructor Ability
    public Ability(int abilityType, int resource) {
        this.abilityType = abilityType;
        this.resource = resource;
    }
//getter ability type
    public int getAbilityType() {
        return abilityType;
    }
// getter resource
    public int getResource() {
        return resource;
    }


}
