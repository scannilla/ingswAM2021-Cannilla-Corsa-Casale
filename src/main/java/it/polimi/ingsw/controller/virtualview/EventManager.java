package it.polimi.ingsw.controller.virtualview;


import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private static final LinkedHashMap<Listener, EventType> listeners = new LinkedHashMap<>();

    public static void subscribe(EventType type, Listener listener) {
        listeners.put(listener, type);
    }

    public static void unsubscribe(Listener listener){
        listeners.remove(listener);
    }

    public static void notifyListener(EventType type, Object update) {
        for (Map.Entry<Listener, EventType> entry : listeners.entrySet()) {
            if (entry.getValue().equals(type)) {
                entry.getKey().notifyChange(update);
            }
        }
    }

}
