package it.polimi.ingsw;

import java.io.Serializable;

public final class VaticanReport implements Serializable {

    /**
     * This attribute represents the length of each report
     */

    private final int[] reportsLength = new int[3];

    /**
     * This attribute represents the activation position for this Vatican Report
     */

    private final int[] activationPosition = new int[3];

    /**
     * This attribute represents if a report has already been activated for each report
     */

    private final boolean[] activatedEvent = new boolean[3];

    private final int[] winPoints = new int[8];

    private final int[] popeFavourTile = {2,3,4};

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
     * Return vector of activated events of this Vatican Report
     * @return activatedEvent boolean[]
     */

    public boolean[] getActivatedEvent() {
        return activatedEvent;
    }

    public int[] getPopeFavourTile() {
        return popeFavourTile;
    }

    public int[] getWinPoints() {
        return winPoints;
    }
}