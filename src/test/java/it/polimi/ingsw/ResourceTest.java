package it.polimi.ingsw;

import it.polimi.ingsw.resources.Resource;
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