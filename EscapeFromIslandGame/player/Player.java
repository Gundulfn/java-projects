package player;

import java.util.Scanner;

public class Player {
	private int damage, health, money, initialHealth;
	private String name, cName;
	private Inventory inventory;

	public Player(String name) {
		this.name = name;
		this.inventory = new Inventory();
	}

	public void selectCharacter(Scanner scanner) {
		switch (characterMenu(scanner)) {
		case 1:
			setPlayerStats("Samurai", 5, 21, 15);
			break;
		case 2:
			setPlayerStats("Archer", 7, 18, 20);
			break;
		case 3:
			setPlayerStats("Knight", 8, 24, 5);
			break;
		default:
			setPlayerStats("Samurai", 5, 21, 15);
			break;
		}
		System.out.println("Character created! Name = " + getcName() + ", Damage = " + getDamage() +
				", Health = " + getHealth() + " ,Money=" + getMoney());
	}

	public int characterMenu(Scanner scanner) {
		System.out.println("Characters");
		System.out.println("1- Samurai \t Damge : 5 \t Health :21 \t Money :15");
		System.out.println("2- Archer \t Damge : 7 \t Health :18 \t Money :20");
		System.out.println("3- Knight \t Damge : 8 \t Health :24 \t Money :5");
		System.out.print("Select a character : ");
		int characterID = scanner.nextInt();

		while (characterID < 1 || characterID > 3) {
			System.out.print("Please select one of defined characters : ");
			characterID = scanner.nextInt();
		}

		return characterID;
	}

	public int getTotalDamage() {
		return this.getDamage() + this.getInventory().getDamage();
	}

	public void setPlayerStats(String cName, int dmg, int initialHealth, int mny) {
		setcName(cName);
		setDamage(dmg);
		setHealth(initialHealth);
		setMoney(mny);
		setInitialHealth(initialHealth);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getInitialHealth() {
		return initialHealth;
	}

	public void setInitialHealth(int initialHealth) {
		this.initialHealth = initialHealth;
	}
}
