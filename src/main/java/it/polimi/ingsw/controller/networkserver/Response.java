package it.polimi.ingsw.controller.networkserver;

import java.util.Objects;

public class Response {

    private final String message;

    private final int code;

    public Response(String message, int code) {
        this.message = message;
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return code == response.code && Objects.equals(message, response.message);
    }

}
