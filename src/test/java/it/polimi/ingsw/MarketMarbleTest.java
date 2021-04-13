package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketMarbleTest {

    MarketMarble marble1 = new MarketMarble(0);
    MarketMarble marble2 = new MarketMarble(1);
    MarketMarble marble3 = new MarketMarble(2);
    MarketMarble marble4 = new MarketMarble(3);
    MarketMarble marble5 = new MarketMarble(4);
    MarketMarble marble6 = new MarketMarble(5);

    @Test
    void getColor() {

        assertEquals(0, marble1.getColor());
        assertEquals(1, marble2.getColor());
        assertEquals(2, marble3.getColor());
        assertEquals(3, marble4.getColor());
        assertEquals(4, marble5.getColor());
        assertEquals(5, marble6.getColor());

    }
}