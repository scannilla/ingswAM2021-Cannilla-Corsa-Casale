package it.polimi.ingsw.controller;

public class ObjectMessage extends Message{

    private final Object obj;

    /**
     *
     * @param obj the updated object to send to the client
     * @param code the code associated with the updated object. Legend: code 20-Market, 21-Card Market, 22-Personal Board, 23-Leader Cards
     * @param nickname the user's nickname who required the update, if null the update is in broadcast
     */

    public ObjectMessage(Object obj, int code, String nickname){
        super(code, "object", nickname);
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

}


