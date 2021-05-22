package it.polimi.ingsw.controller.application;


public class Automaton {

    private GamePhase phase = GamePhase.ACCEPTANCE;

    public synchronized GamePhase getPhase() {
        return phase;
    }

    public String[] validCommands() {
        return phase.validCommands();
    }

    public synchronized void evolveGamePhase() {
        phase = phase.next();
    }



}
