package it.polimi.ingsw.controller.virtualview;


import it.polimi.ingsw.controller.EndingGameException;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private static final LinkedHashMap<Listener, EventType> listeners = new LinkedHashMap<>();



    public static void subscribe(EventType type, Listener listener) {
        listeners.put(listener, type);
    }

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

}
