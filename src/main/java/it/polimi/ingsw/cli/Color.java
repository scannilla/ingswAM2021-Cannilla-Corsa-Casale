package it.polimi.ingsw.cli;

public enum Color {
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_GREY("\u001B[37m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_PURPLE("\u001b[35m"),
    ANSI_BRIGHTWHITE("\u001b[37;1m"),
    ANSI_RESET("\u001b[0m");


    private String escape;

    Color(String escape) {
        this.escape = escape;
    }
    public String escape(){
        return escape;
    }

}
