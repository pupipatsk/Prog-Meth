package Game.logic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Pickaxe implements BagItem {
	
	private Mineral mineral;
	private int baseDamage;
	private int damage;
	private int plus;
	private String name;
	private String imagePath;
	private int id;
	private int enchantCount = 0;
	private int basePlus = 0;
	
	
	public Pickaxe(int damage, int plus , String Name,int id) {
		this.id = id;
		this.setName(Name);
		this.setBasePlus(plus);
		this.setPlus(plus);
		if (plus != 0)this.setEnchantCount( (int)(5* Math.pow(1.5,basePlus)));
		this.baseDamage = damage;
		this.setDamage(damage);
		this.setImagePath(ClassLoader.getSystemResource("picture/pickaxe/" + Name+".png").toString());
	}
	
	public void setEnchantCount(int enchantCount) {
		this.enchantCount = enchantCount;
	}
	
	public boolean addEnchantCount(){
		enchantCount += 1;
		if (enchantCount >=  getNextEvoTime()) {basePlus++;this.setPlus(Math.max(basePlus, plus));;return true;}
		setDamage(baseDamage);
		return false;
	}
	
	public int getNextEvoTime() {
		return (int)(5* Math.pow(1.5,basePlus));
	}
	
	public int getBasePlus() {
		return basePlus;
	}
	
	public int getEnchantCount() {
		return enchantCount;
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = (int) Math.max(baseDamage*Math.pow(2, this.plus), 0);
	}
	public int getPlus() {
		return plus;
	}
	public void setPlus(int plus) {
		this.plus = Math.max(plus, basePlus);
	}
	
	public void setBasePlus(int basePlus) {
		this.basePlus = Math.max(0, basePlus);
	}
	
	public void addPlus() {
		this.plus ++;
		this.damage = (int) Math.max(baseDamage*Math.pow(2, this.plus), 0);
	}
	
	public void downPlus() {
		this.setPlus(plus-1);
		this.damage = (int) Math.max(baseDamage*Math.pow(2, this.plus), 0);
	}

	public String getName() {
		return name + " +" + this.plus;
	}
	
	public void  setName(String name) {
		this.name = name;
	}
	
	public void setImagePath(String image) {
		this.imagePath = image; 
	}
	
	public ImageView getIcon(int width, int height) {
		ImageView icon = new ImageView(new Image(imagePath));
		icon.setFitHeight(height);
		icon.setFitWidth(width);
		return icon;
				
	}

	public Mineral getMineral() {
		return mineral;
	}

	public void setMineral(Mineral mineral) {
		this.mineral = mineral;
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return this.getMineral().getCost()*(1 +this.getPlus());
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
