package logic.card;

import logic.game.CardSymbol;
import logic.game.CardColor;

//You CAN modify the first line
public abstract class BaseCard {
    // TODO Implement here
	
	//Fields
	private CardColor color;
	private CardSymbol symbol;
	
	
	//Methods
	public BaseCard(CardColor color, CardSymbol symbol) {
		setColor(color);
		setSymbol(symbol);
	}
		//abstract methods
	public abstract String toString();
	public abstract boolean canPlay();
	public abstract String play();
	
	
	//getter setter
	public CardColor getColor() {
		return this.color;
	}
	public void setColor(CardColor color) {
		this.color = color;
	}
	public CardSymbol getSymbol() {
		return this.symbol;
	}
	public void setSymbol(CardSymbol symbol) {
		this.symbol = symbol;
	}
}