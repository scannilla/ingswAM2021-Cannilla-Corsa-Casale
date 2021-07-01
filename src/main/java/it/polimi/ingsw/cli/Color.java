package it.polimi.ingsw.cli;

/**
 * enum class for colors
 */
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


    /**
     * escape for colors
     */
    private String escape;

    /**
     * constructor for Color
     * @param escape
     */
    Color(String escape) {
        this.escape = escape;
    }

    /**
     * escape method
     * @return
     */
    public String escape(){
        return escape;
    }

}
