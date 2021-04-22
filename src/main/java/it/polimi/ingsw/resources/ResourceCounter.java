package it.polimi.ingsw.resources;

import it.polimi.ingsw.resources.Resource;

public final class ResourceCounter {
    /**
     * return an array with all the amount of each Resource
     * counter[0] = amount of coins
     * counter[1] = amount of stones
     * counter[2] = amount of servants
     * counter[3] = amount of shields
     * @param resources Resource[]
     * @return counter
     */
    public static int[] resCount(Resource[] resources){
        int[] counter = {0, 0, 0, 0};
        for (Resource resource : resources) {

            if (resource != null) {
                if (resource.getResType() == 0)
                    counter[0]++;
                if (resource.getResType() == 1)
                    counter[1]++;
                if (resource.getResType() == 2)
                    counter[2]++;
                if (resource.getResType() == 3)
                    counter[3]++;
            }
        }
        return counter;
    }
}
