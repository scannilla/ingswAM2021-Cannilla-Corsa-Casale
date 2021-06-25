package it.polimi.ingsw.controller.virtualview;


import it.polimi.ingsw.controller.EndingGameException;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {

    /**
     * List of all subscribers differentiated by Event type
     */
    private static final LinkedHashMap<Listener, EventType> listeners = new LinkedHashMap<>();

    /**
     * Method used to subscribe a listener to the notify list
     * @param type EventType
     * @param listener Listener
     */
    public static void subscribe(EventType type, Listener listener) {
        listeners.put(listener, type);
    }

    /**
     * Used to inform all subscribers about a new update
     * @param type EventType
     * @param update Object
     */
    public static void notifyListener(EventType type, Object update){
        for (Map.Entry<Listener, EventType> entry : listeners.entrySet()) {
            if (entry.getValue().equals(type)) {
                try {
                    entry.getKey().notifyChange(update);
                } catch (EndingGameException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Used to inform a specific client about a new update
     * @param type EventType
     * @param update Object
     * @param nickname String
     */
    public static void notifyListener(EventType type, Object update, String nickname){
        for (Map.Entry<Listener, EventType> entry : listeners.entrySet()) {
            if (entry.getValue().equals(type)) {
                try {
                    entry.getKey().notifyChange(update, nickname);
                } catch (EndingGameException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Used to inform a specific client about a new update with a specific code
     * @param type EventType
     * @param update Object
     * @param nickname String
     * @param code int
     */
    public static void notifyListener(EventType type, Object update, String nickname, int code) {
        for (Map.Entry<Listener, EventType> entry : listeners.entrySet()) {
            if (entry.getValue().equals(type)) {
                try {
                    entry.getKey().notifyChange(update, nickname, code);
                } catch (EndingGameException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
