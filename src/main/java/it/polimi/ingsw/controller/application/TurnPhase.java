package it.polimi.ingsw.controller.application;

public enum TurnPhase implements Comparable<TurnPhase> {
    ERROR,
    BEFORE_ACTION,
    AFTER_ACTION,
    END_TURN;

    TurnPhase next() {
        switch (this) {
            case BEFORE_ACTION:
                return AFTER_ACTION;
            case AFTER_ACTION:
                return END_TURN;
        }
        return ERROR;
    }

    @Override
    public String toString() {
        switch (this){
            case BEFORE_ACTION:
                return "NO ACTION DONE YET";
            case AFTER_ACTION:
                return "ACTION DONE";
            case END_TURN:
                return "END TURN";
        }
        return "ERROR";
    }

    static TurnPhase fromString(String s){

        switch (s.toUpperCase().charAt(0)){
            case 'B':
                return BEFORE_ACTION;
            case 'A':
                return AFTER_ACTION;
            case 'E':
                return END_TURN;
        }
        return ERROR;
    }

    public static String[] validCommands(TurnPhase turnPhase) {
        switch (turnPhase) {
            case BEFORE_ACTION:
                return new String[] {"buy resource", "activate leader card", "buy production card", "standard production", "card production", "leader production", "move resources", "view"};
            case AFTER_ACTION:
                return new String[] {"activate leader card", "move resources", "leader production", "end turn", "view"};
            case END_TURN:
            case ERROR:
            default:
                return new String[] {};
        }
    }
}