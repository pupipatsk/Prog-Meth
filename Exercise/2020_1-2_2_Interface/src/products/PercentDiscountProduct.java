package products;

import discount.PercentDiscountable;
import discount.Sellable;

public class PercentDiscountProduct extends BaseProduct implements PercentDiscountable, Sellable{

	private double percent;
	
	public PercentDiscountProduct(String name, int price, double percent) {
		super(name, price);
		setPercent(percent);
	}
	
	public int calcDiscountPerPiece() {
		return logic.ShopUtil.calculateDiscountUsingPercent(this.getPrice(), this.getPercent());
	}
	public int calculateDiscount(int quantity) {
		return quantity*calcDiscountPerPiece();
	}
	public String toString() {
		return this.getProductName() + " (Price: " + this.getPrice() +", "+ this.getPercent() + "% Off)";
	}
	// Getter & Setter
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		if (percent<0) {
			percent = 0;
		}
		if (percent > 100) {
			percent = 100;
		}
		this.percent = percent;
	}
}
