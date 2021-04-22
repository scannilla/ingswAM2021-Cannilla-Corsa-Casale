package it.polimi.ingsw;

import it.polimi.ingsw.tokens.ActionToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionTokenTest {

    ActionToken actionTokenTest1 = new ActionToken(0);
    ActionToken actionTokenTest2 = new ActionToken(1);
    ActionToken actionTokenTest3 = new ActionToken(2);
    ActionToken actionTokenTest4 = new ActionToken(3);
    ActionToken actionTokenTest5 = new ActionToken(4);
    ActionToken actionTokenTest6 = new ActionToken(5);
    ActionToken actionTokenTest7 = new ActionToken(6);



    @Test
     void getAction() {

        assertEquals(0, actionTokenTest1.getAction());
        assertEquals(1, actionTokenTest2.getAction());
        assertEquals(2, actionTokenTest3.getAction());
        assertEquals(3, actionTokenTest4.getAction());
        assertEquals(4, actionTokenTest5.getAction());
        assertEquals(5, actionTokenTest6.getAction());
        assertEquals(6, actionTokenTest7.getAction());


    }
}