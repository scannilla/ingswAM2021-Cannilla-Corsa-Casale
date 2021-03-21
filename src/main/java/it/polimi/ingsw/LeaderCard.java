package it.polimi.ingsw;

public class LeaderCard {

    private int[] RequiredRes;
    private int[][] RequiredCard;
    private int wp;
    private Ability SpecialAbility;

    //constructor Leader Card
    public LeaderCard(int[] requiredRes, int[][] requiredCard, int wp, Ability specialAbility) {
        RequiredRes = requiredRes;
        RequiredCard = requiredCard;
        this.wp = wp;
        SpecialAbility = specialAbility;
    }

// getter Required Resources
    public int[] getRequiredRes() {

        return RequiredRes;

    }
// getter Required Card
    public int[][] getRequiredCard() {

        return RequiredCard;

    }
// getter Win Points
    public int getWp(){

        return wp;

    }
// getter Special Ability
    public Ability getSpecialAbility(){

        return SpecialAbility;

    }

}