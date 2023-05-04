package logic.card;

import logic.game.CardSymbol;
import logic.game.CardColor;
import logic.game.GameLogic;

//You CAN modify the first line
public class NumberCard extends BaseCard {
    // TODO Implement here
	
	//Methods
	public NumberCard(CardColor color,CardSymbol symbol) {
		super(color, symbol);
	}
	public String toString() {
		return this.getColor() + " " + this.getSymbol();
	}
	public boolean canPlay() {
		BaseCard topCard = GameLogic.getInstance().getTopCard();
		return this.getColor().equals( topCard.getColor() ) || this.getSymbol().equals( topCard.getSymbol() );
	}
	public String play() {
		GameLogic.getInstance().setTopCard(this);
		GameLogic.getInstance().getCurrentPlayerHand().remove(this);
		return null;
	}
}