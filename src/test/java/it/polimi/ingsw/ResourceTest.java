package it.polimi.ingsw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    @Test
    void getResType() {
        for(int i=0; i<4; i++) {
            Resource resourceTest = new Resource(i);
            assertEquals(i, resourceTest.getResType());
        }
    }
}