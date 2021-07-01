package it.polimi.ingsw;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

import it.polimi.ingsw.tokens.ActionTokenPile;


class ActionTokenPileTest {

    InputStream fileTest = this.getClass().getClassLoader().getResourceAsStream("JsonFiles/actiontokens.json");

    @Test
    void createPile (){
        try {
            ActionTokenPile actionTokenPileTest = GSON.actionTokensPileParser(fileTest);
            actionTokenPileTest.createPile();
            for(int i=0; i<20; i++) {
                assertNotNull(actionTokenPileTest.popToken());
                System.out.println(actionTokenPileTest.popToken().getAction());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}