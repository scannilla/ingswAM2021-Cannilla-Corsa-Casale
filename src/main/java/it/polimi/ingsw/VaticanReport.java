package it.polimi.ingsw;

public final class VaticanReport {

    private  int numberOfReports;

    private int[] reportsLength = new int[numberOfReports];

    private final int[] activationPosition = new int[numberOfReports];

    private int[] popeFavourTile = new int[numberOfReports];

    private final boolean[] activatedEvent = new boolean[numberOfReports];

    public void setNumberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }

    public void setActivationPosition(int[] activationPosition){
        this.activationPosition = activationPosition;
    }

    public void setPopeFavourTile(int[] popeFavourTile){
        this.popeFavourTile = popeFavourTile;
    }

    public void setActivatedEvent(boolean[] activatedEvent){
        this.activatedEvent = activatedEvent;
    }

    public void setReportsLength(int[] reportsLength) {
        this.reportsLength = reportsLength;
    }

    /**
     * Return vector of reports length of this Vatican Report
     * @return reportsLength int[]
     */

    public int[] getReportsLength() {
        return reportsLength;
    }

    public int[] getActivationPosition() {
        return activationPosition;
    }

    public int[] getPopeFavourTile() {
        return popeFavourTile;
    }

    public boolean[] getActivatedEvent() {
        return activatedEvent;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}