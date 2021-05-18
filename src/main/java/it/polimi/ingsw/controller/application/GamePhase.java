package it.polimi.ingsw.controller.application;

public enum GamePhase implements Comparable<GamePhase> {
    UNKNOWN,
    ACCEPTANCE,
    GAME_CREATOR,
    WAITING_ROOM,
    GAME_SETUP,
    GAME_PHASE,
    END;

    GamePhase next(){

        switch (this){
            case ACCEPTANCE:
                return GAME_CREATOR;

            case GAME_CREATOR:
                return  WAITING_ROOM;

            case WAITING_ROOM:
                return GAME_SETUP;

            case GAME_SETUP:
                return GAME_PHASE;

            case GAME_PHASE:
                return END;
        }

        return UNKNOWN;
    }

    static GamePhase fromString(String s){

        switch (s.toUpperCase().charAt(0)){
            case 'A':
                return ACCEPTANCE;
            case 'C':
                return GAME_CREATOR;
            case 'W':
                return WAITING_ROOM;
            case 'S':
                return GAME_SETUP;
            case 'G':
                return GAME_PHASE;
            case 'E':
                return END;
            case 'U':
                return UNKNOWN;
        }

        return UNKNOWN;

    }

    @Override
    public String toString() {
        switch (this){
            case ACCEPTANCE:
                return "ACCEPTANCE";

            case GAME_CREATOR:
                return "GAME CREATOR FOR THE FIRST PLAYER";

            case WAITING_ROOM:
                return "WAITING ROOM";

            case GAME_SETUP:
                return "GAME SETUP";

            case GAME_PHASE:
                return "THE ACTUAL GAME";
            case END:
                return "END GAME";
        }

        return "UNKNOWN";
    }

    public String[] validCommands (TurnPhase turnPhase) {
        switch (this) {
            case ACCEPTANCE:
                return new String[] {"create game"};
            case GAME_CREATOR:
                return new String[] {"choose players"};
            case WAITING_ROOM:
                return new String[] {"start game"};
            case GAME_SETUP:
                return new String[] {"Coin", "Stone", "Servant", "Shield"};
            case GAME_PHASE:
                return TurnPhase.validCommands(turnPhase);

        }
        return null;
    }
}