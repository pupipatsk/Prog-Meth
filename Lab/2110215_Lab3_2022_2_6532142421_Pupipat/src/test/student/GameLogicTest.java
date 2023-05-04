package test.student;

import logic.card.BaseCard;
import logic.card.ChangeColorCard;
import logic.card.NumberCard;
import logic.card.SkipCard;
import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    ArrayList<BaseCard> hand0;
    ArrayList<BaseCard> hand1;

    NumberCard c1;
    SkipCard c2;
    NumberCard c3;
    ChangeColorCard c4;

    @BeforeEach
    void setup() {
        c1 = new NumberCard(CardColor.YELLOW, CardSymbol.NINE);
        c2 = new SkipCard(CardColor.YELLOW);
        c3 = new NumberCard(CardColor.RED, CardSymbol.NINE);
        c4 = new ChangeColorCard();
    }

    @AfterEach
    void tearDown() {
        GameLogic.clearInstance();
    }

    @Test
    void testIsHandPlayableTrue() {
        // TODO Implement here

    	GameLogic gameInstance = GameLogic.getInstance(2);
    	
        gameInstance.getPlayerHand(0).add(c1); 
        gameInstance.setTopCard(c3);
        assertTrue(gameInstance.isHandPlayable(0));
        
        gameInstance.getPlayerHand(1).add(c4); 
        gameInstance.setTopCard(c3);
        assertTrue(gameInstance.isHandPlayable(1));
    }

    @Test
    void testIsHandPlayableFalse() {
        // TODO Implement here

		GameLogic gameInstance = GameLogic.getInstance(1);
    	
        gameInstance.getPlayerHand(0).add(c2); 
        gameInstance.setTopCard(c3);
        assertFalse(gameInstance.isHandPlayable(0));
    }

    @Test
    void testDrawLessThanDeck() {
        // TODO Implement here

    	GameLogic gameInstance = GameLogic.getInstance(1);
    	for (int i=0; i<10; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
    	ArrayList<BaseCard> deck = gameInstance.draw(8);
    	for (int i=0; i<deck.size(); i++) {
    		gameInstance.getPlayerHand(0).add(deck.get(i)); 
    	}
    	
    	assertEquals(8,gameInstance.getPlayerHand(0).size());

    }

    @Test
    void testDrawMoreThanDeck() {
        // TODO Implement here

    	GameLogic gameInstance = GameLogic.getInstance(1);
    	for (int i=0; i<10; i++)
            gameInstance.getDeck().add(new NumberCard(CardColor.randomColor(), CardSymbol.randomSymbol()));
    	ArrayList<BaseCard> deck = gameInstance.draw(12);
    	for (int i=0; i<deck.size(); i++) {
    		gameInstance.getPlayerHand(0).add(deck.get(i)); 
    	}
    	
    	assertEquals(10,gameInstance.getPlayerHand(0).size());
    }

}
