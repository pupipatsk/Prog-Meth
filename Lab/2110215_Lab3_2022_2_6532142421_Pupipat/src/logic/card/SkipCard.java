package logic.card;

import logic.game.CardColor;
import logic.game.CardSymbol;
import logic.game.GameLogic;

//You CAN modify the first line
public class SkipCard extends EffectCard{
    // TODO Implement here

	//Methods
	public SkipCard(CardColor color) {
		super(color, CardSymbol.SKIP);
	}
	public String toString() {
		return this.getColor() + " " + this.getSymbol();
	}
	public boolean canPlay() {
		BaseCard topCard = GameLogic.getInstance().getTopCard();
		return this.getColor().equals( topCard.getColor() ) || this.getSymbol().equals( topCard.getSymbol() );
	}
	public String performEffect() {
		int playerCounter=0;
		for (int i = 0; i<GameLogic.getInstance().getPlayerCount(); i++) {
			GameLogic.getInstance().goToNextPlayer();
			playerCounter = GameLogic.getInstance().getCurrentPlayer();
			if (GameLogic.getInstance().getPlayerHand(playerCounter).size() > 0) break;
			//playerCounter will point at player who had skipped
		}
		int skippedPlayer = playerCounter;
		return "Skipped player " + skippedPlayer;
	}
}
