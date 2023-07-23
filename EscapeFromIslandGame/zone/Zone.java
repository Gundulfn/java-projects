package location;

import player.Player;

import java.util.Scanner;

public abstract class Zone {
	private String name;

	public Zone(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void activateZone(Scanner scanner, Player player){ }
}