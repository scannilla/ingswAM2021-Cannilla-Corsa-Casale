package it.polimi.ingsw.resources;

import it.polimi.ingsw.resources.Resource;

public class Strongbox {
    /**
     * This attribute represents strongbox's content,
     * int[0] = number of coins
     * int[1] = number of stones
     * int[2] = number of servants
     * int[3] = number of shields
     */
    private final int[] values;
    private final int coins=0, stones=1, servants=2, shields=3;

    /**
     * This method creates a new Strongbox initialized to 0
     */
    public Strongbox() {
        values = new int[4];
    }

    /**
     * This method creates a new Strongbox initialized to the paramethers values
     * @param coins int
     * @param stones int
     * @param servants int
     * @param shields int
     */
    public Strongbox(int coins, int stones, int servants, int shields){
        values = new int[4];
        values[0] = coins;
        values[1] = stones;
        values[2] = servants;
        values[3] = shields;
    }
    /**
     * This method return amount of coins in the strongbox
     * @return int values[0]
     */
    public int getCoins() {
        return values[coins];
    }
    /**
     * This method return amount of stones in the strongbox
     * @return int values[1]
     */
    public int getStones() {
        return values[stones];
    }
    /**
     * This method return amount of servants in the strongbox
     * @return int values[2]
     */
    public int getServants() {
        return values[servants];
    }
    /**
     * This method return amount of shields in the strongbox
     * @return int values[3]
     */
    public int getShields() {
        return values[shields];
    }
    /**
     * This method sets amount of coins in the strongbox
     * @param coinsN int
     */
    public void setCoins(int coinsN) {
        values[coins] = coinsN;
    }
    /**
     * This method sets amount of stones in the strongbox
     * @param stonesN int
     */
    public void setStones(int stonesN) {
        values[stones] = stonesN;
    }
    /**
     * This method sets amount of servants in the strongbox
     * @param servantsN int
     */
    public void setServants(int servantsN) {
        values[servants] = servantsN;
    }
    /**
     * This method sets amount of shields in the strongbox
     * @param shieldsN int
     */
    public void setShields(int shieldsN) {
        values[shields] = shieldsN;
    }

    /**
     * return the difference between possessed and requested resources
     * @param resource Resource
     * @param amount int
     * @return (values[i] - amount)
     */
    public int isEnough(Resource resource, int amount) {
        int i = resource.getResType();
        return (values[i] - amount); // control in the controller if the returned int is <0
    }                                // if return<0 you haven't enough amount of resource
                                     // if =0 you have exactly the amount of resource
                                     // if >0 you have more resource of the amount

    /**
     * return an array with the amount of all Resource in the Strongbox
     * @return alResources
     */

    public void insertNewResource(Resource resource) {
        values[resource.getResType()]++;
    }

    /**
     * getter for the strongbox resource amount
     * @return values int[]
     */
    public int[] getStrongboxResourcesAmount(){
        return values;
    }

    /**
     * uses a resource if there is one of the type indicated
     * @param resource Resource
     */
    public void useResourceStrongbox(Resource resource) throws IllegalArgumentException{

        switch (resource.getResType()){
            case 0:
                values[0] = values[0] - 1;
                if (values[0] == -1){
                    throw new IllegalArgumentException();
                }
                break;
            case 1:
                values[1] = values[1] - 1;
                if (values[1] == -1){
                    throw new IllegalArgumentException();
                }
                break;
            case 2:
                values[2] = values[2] - 1;
                if (values[2] == -1){
                    throw new IllegalArgumentException();
                }
                break;
            case 3:
                values[3] = values[3] - 1;
                if (values[3] == -1){
                    throw new IllegalArgumentException();
                }
                break;

        }
    }


}


