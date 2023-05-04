package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

import java.util.ArrayList;

//You CAN modify the first line
public class DrawTwoCard extends EffectCard{
    // TODO Implement here

	//Methods
	public DrawTwoCard(CardColor color) {
		super(color, CardSymbol.DRAW_TWO);
	}
	
	public String toString() {
		return this.getColor() + " " + this.getSymbol();
	}
	
	public boolean canPlay() {
		BaseCard topCard = GameLogic.getInstance().getTopCard();
		return this.getColor()==topCard.getColor() || this.getSymbol() == topCard.getSymbol();
	}
	
	public String performEffect() {
		GameLogic.getInstance().incrementDrawAmount(2);

		//Go to the next player whose hand is not empty.
		int nextPlayer = 0;
		GameLogic.getInstance().getPlayerHand(nextPlayer).remove(this);
		while(true) {
			GameLogic.getInstance().goToNextPlayer();
			nextPlayer = GameLogic.getInstance().getCurrentPlayer();
			if(GameLogic.getInstance().getPlayerHand(nextPlayer).size()!=0) break;	
		}
		
		// Takes Actions
		ArrayList<BaseCard> hand = GameLogic.getInstance().getPlayerHand(nextPlayer);
		for (int i=0; i<hand.size(); i++) {
			if(hand.get(i).getSymbol().toString().equals("DRAW TWO") || hand.get(i).getSymbol().toString().equals("DRAW FOUR")){		
				return "Player " + nextPlayer + " played " + hand.get(i).toString() + ". " + (hand.size()-1) + " cards remaining.\n" + hand.get(i).play();
			}
		}
		
		int drawAmount = GameLogic.getInstance().getDrawAmount();
		GameLogic.getInstance().setDrawAmount(0);
		
		return "Player " + nextPlayer + " drew " + drawAmount + " cards. " + (hand.size()+drawAmount) + " cards remaining.";
	}	
}

