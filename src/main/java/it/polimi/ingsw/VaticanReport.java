package it.polimi.ingsw;

import java.util.LinkedHashMap;

public final class VaticanReport {

    /**
     * This attribute represents the number of this Vatican Report
     */

    private  int numberOfReports;

    /**
     * This attribute represents the length of each report
     */

    private final int[] reportsLength = new int[numberOfReports];

    /**
     * This attribute represents the activation position for this Vatican Report
     */

    private final int[] activationPosition = new int[numberOfReports];

    /**
     * This attribute represents how many Win Points each Vatican Report gives
     */

    private final int[] popeFavourTile = new int[numberOfReports];

    /**
     * This attribute represents if a report has already been activated for each report
     */

    private final boolean[] activatedEvent = new boolean[numberOfReports];

    LinkedHashMap<Integer, Integer> winPoints = new LinkedHashMap<>();

    /**
     * activates an event
     * @param event int
     */
    public void eventActivated(int event) {
        activatedEvent[event]=true;
    }

    /**
     * Return vector of reports length of this Vatican Report
     * @return reportsLength int[]
     */

    public int[] getReportsLength() {
        return reportsLength;
    }

    /**
     * Return vector of activation positions of this Vatican Report
     * @return activatedPosition int[]
     */

    public int[] getActivationPosition() {
        return activationPosition;
    }

    /**
     * Return vector of pope favours tile of this Vatican Report
     * @return popeFavourTile int[]
     */

    public int[] getPopeFavourTile() {
        return popeFavourTile;
    }

    /**
     * Return vector of activated events of this Vatican Report
     * @return activatedEvent boolean[]
     */

    public boolean[] getActivatedEvent() {
        return activatedEvent;
    }

    /**
     * Return number of reports of this Vatican Report
     * @return numberOfReports
     */

    public int getNumberOfReports() {
        return numberOfReports;
    }
}