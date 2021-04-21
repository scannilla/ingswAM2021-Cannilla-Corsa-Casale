package it.polimi.ingsw;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActionTokenPile {
    /**
     * List that represents the pile with all Action Tokens
     */
    List<ActionToken> tokensPile;
    /**
     * Array with all Action Tokens
     */
    ActionToken[] allTokensArr = new ActionToken[7];
    int i = 0;

    /**
     * Create a pile of shuffled Action Tokens
     */
    public void createPile (){
        tokensPile = Arrays.asList(allTokensArr);
        Collections.shuffle(tokensPile);
        i = 0;
    }

    /**
     * return selected Action Token, if action = 5 the method reshuffles the pile
     * @return token
     */
    public ActionToken popToken() {
        ActionToken token;
        token = tokensPile.get(i);
        i++;
        if (token.getAction()==5)
            this.createPile();
        return token;
    }
}
