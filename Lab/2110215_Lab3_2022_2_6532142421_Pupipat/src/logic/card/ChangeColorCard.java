package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

//You CAN modify the first line
public class ChangeColorCard extends EffectCard{
    // TODO Implement here
	
	//Methods
	public ChangeColorCard() {
		super(null, CardSymbol.CHANGE_COLOR);
	}
	public String toString() {
		if (this.getColor() == null) {
			return "CHANGE COLOR";
		}
		else {
			return "CHANGE COLOR (" + this.getColor() + " color selected)";
		}
	}
	public boolean canPlay() {
		return true;
	}
	public String performEffect() {
		CardColor setColorTo = null;
		if (GameLogic.getInstance().getCurrentPlayerHand().size() > 0) {
			BaseCard firstCard = GameLogic.getInstance().getCurrentPlayerHand().get(0);
			if (firstCard.getColor() == null) {
				setColorTo = CardColor.RED;
			}
			else {
				setColorTo = firstCard.getColor();
			}
		}
		else {
			setColorTo = CardColor.RED;
		}
		this.setColor(setColorTo);
		return "Set color to " + setColorTo;
	}
}