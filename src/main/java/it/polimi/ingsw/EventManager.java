package it.polimi.ingsw;

import it.polimi.ingsw.controller.virtualview.EventType;
import it.polimi.ingsw.controller.virtualview.Listener;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventManager {
    private final LinkedHashMap<Listener, EventType> listeners = new LinkedHashMap<>();

    public void subscribe(EventType type, Listener listener) {
        listeners.put(listener, type);
    }

    public void unsubscribe(Listener listener){
        listeners.remove(listener);
    }

    public void notifyListener(EventType type, Object update) {
        for (Map.Entry<Listener, EventType> entry : listeners.entrySet()) {
            if (entry.getValue().equals(type)) {
                entry.getKey().notifyChange(update);
            }
        }
    }

}
