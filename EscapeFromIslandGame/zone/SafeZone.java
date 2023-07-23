package location;

import player.Player;

import java.util.Scanner;

public class SafeZone extends Zone {

	public SafeZone() {
		super("Safe House");
	}
	
	@Override
	public void activateZone(Scanner scanner, Player player){
		player.setHealth(player.getInitialHealth());
		System.out.println("You fully restored your health");
	}
}