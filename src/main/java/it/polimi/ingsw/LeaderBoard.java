package it.polimi.ingsw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LeaderBoard implements Serializable {


    private LinkedHashMap<String, Integer> map= new LinkedHashMap<>();


    public LeaderBoard(ArrayList<Player> players) {
        for(Player p : players) {
            map.put(p.getNickname(), p.getWp());
        }
    }

    public LeaderBoard(Player player, Player lorenzo, boolean win) {
        if(win) {
            map.put(lorenzo.getNickname(), player.getWp());
            map.put(player.getNickname(), player.getWp());
        }
        else {
            map.put(player.getNickname(), player.getWp());
            map.put(lorenzo.getNickname(), player.getWp());
        }

    }

    public LinkedHashMap<String, Integer> getMap() {
        return map;
    }
}
