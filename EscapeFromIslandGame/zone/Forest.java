package location;

import enemy.Vampire;
import player.Player;

public class Forest extends EnemyZone {

	public Forest() {
		super("Orman", new Vampire(),"Firewood");
	}
}