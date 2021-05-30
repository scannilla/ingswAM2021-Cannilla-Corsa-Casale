package it.polimi.ingsw.controller;

import java.io.Serializable;

public class Message implements Serializable {
    private int code;
    private String message;
    private String nickname;

    public Message(int code, String message, String nickname) {
        this.code = code;
        this.message = message;
        this.nickname = nickname;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
