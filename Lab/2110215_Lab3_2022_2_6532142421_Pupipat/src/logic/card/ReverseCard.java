package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;
import logic.game.PlayDirection;

//You CAN modify the first line
public class ReverseCard extends EffectCard{
    // TODO Implement here

	//Methods
	public ReverseCard(CardColor color) {
		super(color, CardSymbol.REVERSE);
	}
	public String toString() {
		return this.getColor() + " " + this.getSymbol();
	}
	public boolean canPlay() {
		BaseCard topCard = GameLogic.getInstance().getTopCard();
		return this.getColor().equals( topCard.getColor() ) || this.getSymbol().equals( topCard.getSymbol() );
	}
	public String performEffect() {
		PlayDirection newDirection = GameLogic.getInstance().getPlayDirection().getOpposite();
		GameLogic.getInstance().setPlayDirection(newDirection);
		return "Set direction to " + newDirection;
	}
}