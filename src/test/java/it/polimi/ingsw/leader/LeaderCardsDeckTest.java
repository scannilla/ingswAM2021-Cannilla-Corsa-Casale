package it.polimi.ingsw.leader;

import it.polimi.ingsw.GSON;
import it.polimi.ingsw.resources.ResourceCounter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class LeaderCardsDeckTest {
    private final InputStream test = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/allLeaderCards.json");

    @Test
    void getLeaderCardsDeck() {
        LeaderCardsDeck deck;
        try {
            deck = GSON.leaderCardParser(test);
        } catch (IOException e) {
            return;
        }
        for(LeaderCard l : deck.getLeaderCardsDeck())
            assertNotNull(l);
        for(LeaderCard l : deck.getLeaderCardsDeck())
            assertNotEquals(10, ResourceCounter.resCount(l.getRequiredRes())[0]);
    }
}