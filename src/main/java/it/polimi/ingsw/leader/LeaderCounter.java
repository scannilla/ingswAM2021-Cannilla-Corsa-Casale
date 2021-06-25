package it.polimi.ingsw.leader;


public class LeaderCounter {

    public static int[] requiredCardCounter(int[] requiredType, int[]requiredLevel) {
        int[] result = new int[12];
        for (int i = 0; i < requiredType.length; i++) {
            result[(requiredType[i])*(requiredLevel[i])-1]++;
        }
        return result;
    }
}
