package it.polimi.ingsw.controller.application;


public class Automaton {

    private GamePhase phase = GamePhase.ACCEPTANCE;
    private TurnPhase turnPhase = TurnPhase.BEFORE_ACTION;
    boolean actionDone;

    public synchronized GamePhase getPhase() {
        return phase;
    }

    public String[] validCommands() {
        return phase.validCommands(turnPhase);
    }

    public synchronized void evolveGamePhase() {
        phase = phase.next();
    }

    public synchronized boolean evolveTurnPhase() {
        int currOrd = turnPhase.ordinal();
        if (currOrd == TurnPhase.AFTER_ACTION.ordinal() && !actionDone)
            return false;
        turnPhase = turnPhase.next();
        if(turnPhase==TurnPhase.END_TURN) {
            actionDone = false;
            turnPhase = TurnPhase.BEFORE_ACTION;
        }
        return true;
    }

    public synchronized boolean evolveToTurnPhase(TurnPhase tPhase) {
        if(tPhase.ordinal() >= TurnPhase.AFTER_ACTION.ordinal() && !actionDone)
            return false;
        turnPhase = tPhase;
        return true;
    }

    public synchronized void actionDone() {
        this.actionDone = true;
    }


}
