package it.polimi.ingsw;

public class VaticanReport {

    private  int numberOfReports;

    private final int[] reportsLength = new int[numberOfReports];

    private final int[] activationPosition = new int[numberOfReports];

    private final int[] popeFavourTile = new int[numberOfReports];

    private final boolean[] activatedEvent = new boolean[numberOfReports];

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