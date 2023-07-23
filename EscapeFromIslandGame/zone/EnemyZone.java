package location;

import enemy.Enemy;
import player.Player;

import java.util.Random;
import java.util.Scanner;

public abstract class EnemyZone extends Zone {
	private Enemy enemy;
	private String award;
	private static Random random = new Random();

	EnemyZone(String name, Enemy enemy, String award) {
		super(name);
		this.enemy = enemy;
		this.award = award;
	}

	public void activateZone(Scanner scanner, Player player) {
		int enemyCount = enemy.count();
		System.out.println("You are at " + this.getName());
		System.out.println("Danger! Enemies:  " + enemyCount + " of " + enemy.getName());
		System.out.print("(F)ight or (R)un:");
		String selCase = scanner.nextLine();
		selCase = selCase.toUpperCase();

		if (selCase.equals("F")) {
			if (combat(scanner, player, enemyCount)) {
				if (this.award.equals("Food") && player.getInventory().isFood() == false) {
					System.out.println(this.award + " earned! ");
					player.getInventory().setFood(true);
				} else if (this.award.equals("Water") && player.getInventory().isWater() == false) {
					System.out.println(this.award + " earned! ");
					player.getInventory().setWater(true);
				} else if (this.award.equals("Firewood") && player.getInventory().isFirewood() == false) {
					System.out.println(this.award + " earned! ");
					player.getInventory().setFirewood(true);
				}
			}
			
			if(player.getHealth() <= 0) {
				System.out.println("You died");

			}
		
		}
	}

	public boolean combat(Scanner scanner, Player player, int enemyCount) {
		// enemy hits first or not
		if(random.nextBoolean() == true){
			System.out.println();
			System.out.println(enemy.getName() + " hit you!");
			player.setHealth(player.getHealth() - (enemy.getDamage() - player.getInventory().getArmor()));
			afterHit(player);
		}

		for (int i = 0; i < enemyCount; i++) {
			int defObsHealth = enemy.getHealth();
			playerStats(player);
			enemyStats();
			while (player.getHealth() > 0 && enemy.getHealth() > 0) {

				System.out.print("<H>it or <R>un :");
				String selCase = scanner.nextLine();
				selCase = selCase.toUpperCase();
				if (selCase.equals("H")) {
					System.out.println("You hit " + enemy.getName() + "!");
					enemy.setHealth(enemy.getHealth() - player.getTotalDamage());
					afterHit(player);
					if (enemy.getHealth() > 0) {
						System.out.println();
						System.out.println(enemy.getName() + " hit you!");
						player.setHealth(player.getHealth() - (enemy.getDamage() - player.getInventory().getArmor()));
						afterHit(player);
					}
				} else {
					return false;
				}
			}

			if (enemy.getHealth() <= 0) {
				System.out.println("Defeated enemies!");
				player.setMoney(player.getMoney() + enemy.getAward());
				System.out.println("Current Money : " + player.getMoney());
				enemy.setHealth(defObsHealth);
			} else {
				return false;
			}
			System.out.println("-------------------");
		}

		return true;
	}

	public void playerStats(Player player) {
		System.out.println("Player Stats\n--------------");
		System.out.println("Health:" + player.getHealth());
		System.out.println("Damage:" + player.getTotalDamage());
		System.out.println("Money:" + player.getMoney());
		if (player.getInventory().getDamage() > 0) {
			System.out.println("Weapon:" + player.getInventory().getwName());
		}
		if (player.getInventory().getArmor() > 0) {
			System.out.println("Armor:" + player.getInventory().getaName());
		}
	}

	public void enemyStats() {
		System.out.println("\n" + enemy.getName() + " Stats\n--------------");
		System.out.println("Health:" + enemy.getHealth());
		System.out.println("Damage:" + enemy.getDamage());
		System.out.println("Award:" + enemy.getAward());
	}

	public void afterHit(Player player) {
		System.out.println("Player's health:" + player.getHealth());
		System.out.println(enemy.getName() + " Health:" + enemy.getHealth());
		System.out.println();
	}

}
