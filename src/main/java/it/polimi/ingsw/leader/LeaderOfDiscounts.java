package it.polimi.ingsw.leader;

import it.polimi.ingsw.resources.Resource;

public class LeaderOfDiscounts extends LeaderCard {

    /**
     * resource that can be excluded from the price of a general card
     */
    private Resource discountedRes;



    /**
     * getter ability of this Leader Card (0 = discount)
     * @return 0
     */
    public int getAbility() {
        return 0;
    }

    /**
     * return the resource that can be excluded from the price of a general card
     * @return discountedRes
     */
    public Resource getDiscountedRes() {
        return discountedRes;
    }
}
