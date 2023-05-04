package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

import java.util.ArrayList;

//You CAN modify the first line
public class DrawFourCard extends EffectCard{
    // TODO Implement here
	
	//Methods
	public DrawFourCard() {
		super(null, CardSymbol.DRAW_FOUR);
	}
	
	public String toString() {
		if (this.getColor()==null) {
			return "DRAW FOUR";
		}
		else {
			return "DRAW FOUR (" + this.getColor() + " color selected)";
		}
	}
	
	public boolean canPlay() {
		return true;
	}
	
	public String performEffect() {
		ArrayList<BaseCard> nowHand = GameLogic.getInstance().getCurrentPlayerHand();
		nowHand.remove(this);
		
		//1. Set this card’s color.
		CardColor newColor = null;
		if (nowHand.isEmpty() || nowHand.get(0).getColor()==null) {
			newColor = CardColor.RED;
		}
		else {
			newColor = nowHand.get(0).getColor();
		}
		this.setColor(newColor);
		
		//2. Increment draw amount by 4.
		GameLogic.getInstance().incrementDrawAmount(4);
		
		//3. Go to the next player whose hand is not empty.
		int nextPlayer = GameLogic.getInstance().getCurrentPlayer();
		while (true) {
			GameLogic.getInstance().goToNextPlayer();
			nextPlayer = GameLogic.getInstance().getCurrentPlayer();
			if (GameLogic.getInstance().getPlayerHand(nextPlayer).size()!=0) break;
		}
		
		// Takes Actions
		ArrayList<BaseCard> hand = GameLogic.getInstance().getPlayerHand(nextPlayer);		
		for (int i=0; i<hand.size(); i++) {
			if (hand.get(i).getSymbol().toString().equals("DRAW FOUR")){
				return "Set color to " + newColor +"\nPlayer " + nextPlayer + " played " + hand.get(i).getSymbol().toString() + ". " + (hand.size()-1) + " cards remaining.\n" + hand.get(i).play();
			}				
		}
		
		int drawAmount = GameLogic.getInstance().getDrawAmount();
		GameLogic.getInstance().setDrawAmount(0);
		
		return "Set color to " + newColor + "\nPlayer " + nextPlayer + " drew " + drawAmount + " cards. " + (hand.size()+drawAmount) + " cards remaining.";
	}
}